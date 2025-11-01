package clone.asiana.asiana_clone.login.handler;

import clone.asiana.asiana_clone.login.controller.LoginController;
import clone.asiana.asiana_clone.login.exception.AuthenticationException;
import clone.asiana.asiana_clone.login.exception.OtpException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice(assignableTypes = {LoginController.class})
@Slf4j
public class LoginExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public String handlerAccountStatusException(AuthenticationException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", ex.getMessage());
        return "redirect:/login"; // 로그인 페이지로 다시 이동
    }

    @ExceptionHandler(OtpException.class)
    public String handlerVerifyCredentialsException(OtpException ex, RedirectAttributes redirectAttributes) {

        if("시스템오류".equals(ex.getMessage())) return "login"; // 로그인 페이지로 다시 이동

        redirectAttributes.addFlashAttribute("error", ex.getMessage());
        return "redirect:/login/otp";

    }


}
