package ra.webmovieapp.controller.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.webmovieapp.security.UserDetail.UserPrincipal;

@RestController
@RequestMapping("/v1/user/account")
public class UAccountController {

}
