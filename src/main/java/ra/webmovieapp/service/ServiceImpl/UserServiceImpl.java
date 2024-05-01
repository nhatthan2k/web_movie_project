package ra.webmovieapp.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.webmovieapp.model.dto.request.InfomationRequest;
import ra.webmovieapp.model.entity.User;
import ra.webmovieapp.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.dto.auth.JwtResponse;
import ra.webmovieapp.model.dto.auth.LoginRequest;
import ra.webmovieapp.model.dto.auth.RegisterRequest;
import ra.webmovieapp.model.entity.Role;
import ra.webmovieapp.model.enums.ERoleName;
import ra.webmovieapp.security.Jwt.JwtProvider;
import ra.webmovieapp.security.UserDetail.UserPrincipal;
import ra.webmovieapp.service.RoleService;
import ra.webmovieapp.service.UserService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAllUser(pageable);
    }

    @Override
    public Page<User> searchUsers(String keyWord, Pageable pageable) {
        if (keyWord.isEmpty()) {
            return userRepository.findAllUser(pageable);
        } else {
            return userRepository.findAllByFullNameOrUsernameContainingIgnoreCase(keyWord, keyWord, pageable);
        }
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public JwtResponse handleLogin(LoginRequest loginRequest) throws CustomException {
        Authentication authentication;
        try {
            authentication = authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new CustomException("Username or password is wrong.");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        if (!userPrincipal.getUser().getStatus())
            throw new CustomException("This account is inactive.");
        return JwtResponse.builder()
                .accessToken(jwtProvider.generateToken(userPrincipal))
                .fullName(userPrincipal.getUser().getFullName())
                .username(userPrincipal.getUsername())
                .status(userPrincipal.getUser().getStatus())
                .roles(userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public User handleRegister(RegisterRequest registerRequest) throws CustomException {
        if (userRepository.existsByUsername(registerRequest.getUsername()))
            throw new CustomException("Username is exists");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleService.findByRoleName(ERoleName.ROLE_USER));
        User users = User.builder()
                .fullName(registerRequest.getFullName())
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .avatar(registerRequest.getAvatar())
                .phone(registerRequest.getPhone())
                .status(true)
                .roles(userRoles)
                .build();
        return userRepository.save(users);
    }

    @Override
    public User updateAcc(InfomationRequest infomationRequest, Long id) {
        User userOld = getUserById(id);
        Set<Role> roles = userOld.getRoles();
        User user = User.builder()
                .fullName(infomationRequest.getFullName())
                .username(userOld.getUsername())
                .password(userOld.getPassword())
                .email(infomationRequest.getEmail())
                .avatar(infomationRequest.getAvatar())
                .phone(infomationRequest.getPhone())
                .status(userOld.getStatus())
                .address(infomationRequest.getAddress())
                .roles(roles)
                .build();
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

}
