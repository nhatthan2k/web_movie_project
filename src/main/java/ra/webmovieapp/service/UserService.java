package ra.webmovieapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.webmovieapp.model.dto.request.InfomationRequest;
import ra.webmovieapp.model.entity.User;
import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.dto.auth.JwtResponse;
import ra.webmovieapp.model.dto.auth.LoginRequest;
import ra.webmovieapp.model.dto.auth.RegisterRequest;

public interface UserService {
    Page<User> getAllUsers(Pageable pageable);

    Page<User> searchUsers(String keyWord, Pageable pageable);

    User getUserById(Long userId);

    JwtResponse handleLogin(LoginRequest loginRequest) throws CustomException;

    User handleRegister(RegisterRequest registerRequest) throws CustomException;

    User updateAcc(InfomationRequest infomationRequest, Long id);

    User save(User user);
}
