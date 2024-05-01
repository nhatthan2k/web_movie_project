package ra.webmovieapp.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.dto.wrapper.ResponseWrapper;
import ra.webmovieapp.model.entity.User;
import ra.webmovieapp.model.enums.EHttpStatus;
import ra.webmovieapp.security.UserDetail.UserLoggedIn;
import ra.webmovieapp.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/v1/admin/users")
@CrossOrigin("*")
public class AUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserLoggedIn userLogin;

    @GetMapping
    public ResponseEntity<?> getAllUsersToPage(
            @RequestParam(defaultValue = "5", name = "limit") int limit,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "id", name = "sort") String sort,
            @RequestParam(defaultValue = "asc", name = "order") String order,
            @RequestParam("search") String search
    ) throws CustomException {
        try {
            Pageable pageable;
            if (order.equals("asc")) pageable = PageRequest.of(page, limit, Sort.by(sort).ascending());
            else pageable = PageRequest.of(page, limit, Sort.by(sort).descending());
            Page<User> users = userService.searchUsers(search, pageable);
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            users
                    ), HttpStatus.OK
            );
        } catch (Exception exception) {
            throw new CustomException("Gặp lỗi khi truy vấn! :((");
        }
    }

    @PutMapping("/{userId}/status")
    public ResponseEntity<?> switchUserStatus(@PathVariable("userId") String userId) throws CustomException {
        try {
            Long id = Long.parseLong(userId);
            User user = userService.getUserById(id);
            if (user != null) {
                if (user.getId().equals(userLogin.getUserLoggedIn().getId())) {
                    throw new CustomException("Cant switch this account status");
                }
                user.setStatus(!user.getStatus());
                User updatedUser = userService.save(user);
                return new ResponseEntity<>(
                        new ResponseWrapper<>(
                                EHttpStatus.SUCCESS,
                                HttpStatus.OK.value(),
                                HttpStatus.OK.name(),
                                updatedUser
                        ), HttpStatus.OK);
            }
            // ? Xử lý Exception cần tìm được user theo id trước khi khoá/mở khoá trong Controller.
            throw new CustomException("User is not exists.");
        } catch (NumberFormatException e) {
            throw new CustomException("Incorrect id number format");
        }
    }
}
