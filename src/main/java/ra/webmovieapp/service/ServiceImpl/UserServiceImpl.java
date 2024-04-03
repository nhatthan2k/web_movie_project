package ra.webmovieapp.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.webmovieapp.model.entity.User;
import ra.webmovieapp.repository.UserRepository;
import ra.webmovieapp.service.UserService;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAllUser(pageable);
    }
}
