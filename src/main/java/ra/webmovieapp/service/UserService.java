package ra.webmovieapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.webmovieapp.model.entity.User;

public interface UserService {
    Page<User> getAllUsers(Pageable pageable);
}
