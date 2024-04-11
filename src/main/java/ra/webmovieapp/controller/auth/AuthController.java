package ra.webmovieapp.controller.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.dto.auth.LoginRequest;
import ra.webmovieapp.model.dto.auth.RegisterRequest;
import ra.webmovieapp.model.dto.wrapper.ResponseWrapper;
import ra.webmovieapp.model.enums.EHttpStatus;
import ra.webmovieapp.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/sign-in")
    public ResponseEntity<?> handleLogin(@RequestBody @Valid LoginRequest loginRequest) throws CustomException {
        return new ResponseEntity<>(
                new ResponseWrapper<>(
                        EHttpStatus.SUCCESS,
                        HttpStatus.OK.value(),
                        HttpStatus.OK.name(),
                        userService.handleLogin(loginRequest)
                ), HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> handleRegister(@RequestBody @Valid RegisterRequest RegisterRequest) throws CustomException {
        return new ResponseEntity<>(
                new ResponseWrapper<>(
                        EHttpStatus.SUCCESS,
                        HttpStatus.CREATED.value(),
                        HttpStatus.CREATED.name(),
                        userService.handleRegister(RegisterRequest)
                ), HttpStatus.CREATED);
    }
}
