package ra.webmovieapp.security.UserDetail;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ra.webmovieapp.model.entity.User;
import ra.webmovieapp.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserLoggedIn {
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserLoggedIn.class);

    public User getUserLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            UserPrincipal userPrinciple = (UserPrincipal) authentication.getPrincipal();
            User userOption = userService.getUserById(userPrinciple.getUser().getId());
            if (userOption != null) return userOption;
        }
        logger.error("User - UserController - User id is not found.");
        return null;
    }
}
