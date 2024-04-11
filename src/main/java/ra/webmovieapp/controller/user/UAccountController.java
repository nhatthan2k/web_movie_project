package ra.webmovieapp.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.dto.request.InfomationRequest;
import ra.webmovieapp.model.dto.request.PasswordRequest;
import ra.webmovieapp.model.dto.wrapper.ResponseWrapper;
import ra.webmovieapp.model.entity.User;
import ra.webmovieapp.model.enums.EHttpStatus;
import ra.webmovieapp.security.UserDetail.UserLoggedIn;
import ra.webmovieapp.security.UserDetail.UserPrincipal;
import ra.webmovieapp.service.UserService;

@RestController
@RequestMapping("/v1/user/account")
@CrossOrigin("*")
public class UAccountController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserLoggedIn userLoggedIn;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<?> getInfByUserId() throws CustomException {
        Long id = userLoggedIn.getUserLoggedIn().getId();
        return new ResponseEntity<>(
                new ResponseWrapper<>(
                        EHttpStatus.SUCCESS,
                        HttpStatus.OK.value(),
                        HttpStatus.OK.name(),
                        userService.getUserById(id)
                ), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateAccount(@RequestBody InfomationRequest infomationRequest) throws CustomException {
        Long id = userLoggedIn.getUserLoggedIn().getId();
        return new ResponseEntity<>(
                new ResponseWrapper<>(
                        EHttpStatus.SUCCESS,
                        HttpStatus.OK.value(),
                        HttpStatus.OK.name(),
                        userService.updateAcc(infomationRequest, id)
                ), HttpStatus.OK);
    }
    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordRequest passwordRequest) {
        Long id = userLoggedIn.getUserLoggedIn().getId();
        User user = userService.getUserById(id);
        String oldPassword = user.getPassword();

        boolean isPasswordMatch = passwordEncoder.matches(passwordRequest.getOldPass(), oldPassword);
        if (isPasswordMatch) {
            if (!passwordRequest.getNewPass().equals(passwordRequest.getConfirmNewPass())){
                return new ResponseEntity<>(
                        new ResponseWrapper<>(
                                EHttpStatus.SUCCESS,
                                HttpStatus.OK.value(),
                                HttpStatus.OK.name(),
                                "Xác nhận mật khẩu không chính xác!"
                        ), HttpStatus.BAD_REQUEST);
            }
            user.setPassword(passwordEncoder.encode(passwordRequest.getNewPass()));
            userService.save(user);
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            "Cập nhật mật khẩu thành công"
                    ), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(
                    new ResponseWrapper<>(
                            EHttpStatus.SUCCESS,
                            HttpStatus.OK.value(),
                            HttpStatus.OK.name(),
                            "Mật khẩu cũ không chính xác!"
                    ), HttpStatus.BAD_REQUEST);
        }
    }
}
