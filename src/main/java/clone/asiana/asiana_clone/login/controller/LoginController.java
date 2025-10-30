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

        UserVO userVo = new UserVO(userInfo.getUserNO(), userInfo.getPassword());

        // 계정 상태 확인
        loginService.checkAccountStatus(userVo.getUserNo());

        // ID/PWD 확인
        loginService.verifyCredentials(userVo);

        //로그인 정보 저장
        session.setAttribute("userNo", userVo.getUserNo());

        // 2차 검증 여부 확인후 분기처리
        switch(loginService.checkSecondVerification(userVo.getUserNo())){
            case "SKIP" :
                //세션에 로그인 인증 정보 저장
                session.setAttribute("isLoggedIn", true);
                return "redirect:/";
            
            case "OTP" :
                loginService.sendOtp(userVo.getUserNo());
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

       if(session.getAttribute("userNo") == null) return "redirect:/login";

       if(!loginService.verifyOtp(otp, String.valueOf(session.getAttribute("userNo")))){
           //로그인 실패
           return "redirect:/login/otp";
       }
          //세션에 로그인 인증 정보 저장
          session.setAttribute("isLoggedIn", true);

          //로그인 성공
          return "redirect:/";
    }

    @PostMapping("/otp/resend")
    public String resendOtp(HttpSession session) {
        //otp 재발송
        loginService.sendOtp(String.valueOf(session.getAttribute("userNo")));
        return "redirect:/login/otp";
    }

    @GetMapping("/findAccount")
    public String findAccountPage() {
        return "login/findAccount";
    }

    @PostMapping("/findAccount")
    public String findAccount(@RequestParam String email) {
        //계정 찾기
        loginService.findPassword(email);
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
        return "redirect:/login";
    }

}
