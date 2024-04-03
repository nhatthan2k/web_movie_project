package ra.webmovieapp.service;

import ra.webmovieapp.exception.CustomException;
import ra.webmovieapp.model.dto.auth.JwtResponse;
import ra.webmovieapp.model.dto.auth.LoginRequest;
import ra.webmovieapp.model.dto.auth.RegisterRequest;
import ra.webmovieapp.model.entity.User;

public interface UserService {
    User getUserById(Long userId);
    JwtResponse handleLogin(LoginRequest loginRequest) throws CustomException;
    User handleRegister(RegisterRequest registerRequest) throws CustomException;

}
