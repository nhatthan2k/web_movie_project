package ra.webmovieapp.service;

import org.springframework.stereotype.Service;
import ra.webmovieapp.model.entity.User;

@Service
public interface UserService {
    User getUserById(Long userId);
}
