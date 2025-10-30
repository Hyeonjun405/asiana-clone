package clone.asiana.asiana_clone.login.handler;

import clone.asiana.asiana_clone.login.controller.LoginController;
import clone.asiana.asiana_clone.login.exception.AccountStatusException;
import clone.asiana.asiana_clone.login.exception.VerifyCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = {LoginController.class})
public class LoginExceptionHandler {

    @ExceptionHandler(AccountStatusException.class)
    public String handlerAccountStatusException(AccountStatusException ex) {
        return "redirect:/login"; // 로그인 페이지로 다시 이동
    }

    @ExceptionHandler(VerifyCredentialsException.class)
    public String handlerVerifyCredentialsException(VerifyCredentialsException ex) {
        return "redirect:/login"; // 로그인 페이지로 다시 이동
    }
}
