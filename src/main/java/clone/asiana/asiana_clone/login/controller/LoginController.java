package clone.asiana.asiana_clone.login.controller;

import clone.asiana.asiana_clone.login.dto.AccountUserDTO;
import clone.asiana.asiana_clone.login.dto.userDTO;
import clone.asiana.asiana_clone.login.service.LoginService;
import clone.asiana.asiana_clone.login.vo.AccountUserVO;
import clone.asiana.asiana_clone.login.vo.UserVO;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping
    public String loginPage(){return "login/login";}

    @PostMapping
    public String login(@ModelAttribute userDTO userInfo, HttpSession session) {

        UserVO userVo = new UserVO(userInfo.getEmail(), userInfo.getPassword());

        // 계정 상태 확인
        loginService.checkAccountStatus(userVo.getEmail());
        log.info("게정 상태 확인 완료");
        
        // ID/PWD 확인
        loginService.verifyCredentials(userVo);
        log.info("계정 ID/PWD 일치 확인");

        //로그인 정보 저장
        session.setAttribute("email", userVo.getEmail());
        log.info("세션에 저장한 ID : " + userVo.getEmail());

        // 2차 검증 여부 확인후 분기처리
        switch(loginService.checkSecondVerification(userVo.getEmail())){
            case "SKIP" :
                //세션에 로그인 인증 정보 저장
                session.setAttribute("isLoggedIn", true);
                log.info("SKIP 사용자");
                return "redirect:/";
            
            case "OTP" :
                loginService.sendOtp(userVo.getEmail());
                log.info("OTP 사용자");
                return "redirect:/login/otp";

            default : return "redirect:/login";
        }
    }

    @GetMapping("/otp")
    public String otpLoginPage() {
        return "login/otp";
    }

    @PostMapping("/otp")
    public String otpVerify(@RequestParam String otp, HttpSession session) {
        log.info("OTP점검");
        log.info("계정 : " + session.getAttribute("email"));

       if(session.getAttribute("email") == null) {
           log.info("세션에 계정이 없음");
           return "redirect:/login";
       }

       if(!loginService.verifyOtp(otp, String.valueOf(session.getAttribute("email")))){
           //로그인 실패
           return "redirect:/login/otp";
       }
          //세션에 로그인 인증 정보 저장
          session.setAttribute("isLoggedIn", true);

          log.info("로그인성공");

          //로그인 성공
          return "redirect:/";
    }

    @PostMapping("/otp/resend")
    public String resendOtp(HttpSession session) {
        //otp 재발송
        loginService.sendOtp(String.valueOf(session.getAttribute("email")));
        log.info("OTP 재발송");
        return "redirect:/login/otp";
    }

    @GetMapping("/findAccount")
    public String findAccountPage() {
        return "login/findAccount";
    }

    @PostMapping("/findAccount")
    public String findAccount(@RequestParam String email) {
        //계정 비밀번호 찾기
        loginService.findPassword(email);
        log.info("패스워드 찾음");
        return "redirect:/login";
    }

    @GetMapping("/registerAccount")
    public String registerAccount() {
        return "login/registerAccount";
    }

    @PostMapping("/createAccount")
    public String createAccount(AccountUserDTO userDTO) {

        AccountUserVO accountUser = new AccountUserVO(userDTO.getEmail(), userDTO.getName(), userDTO.getPassword());

        //계정 등록
        loginService.registerAccount(accountUser);
        log.info("계정생성");
        return "redirect:/login";
    }

}
