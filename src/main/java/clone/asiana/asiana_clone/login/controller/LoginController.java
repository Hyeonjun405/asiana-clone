package clone.asiana.asiana_clone.login.controller;

import clone.asiana.asiana_clone.login.dto.AccountUserDTO;
import clone.asiana.asiana_clone.login.dto.LoginResultDTO;
import clone.asiana.asiana_clone.login.dto.UserDTO;
import clone.asiana.asiana_clone.login.service.AccountService;
import clone.asiana.asiana_clone.login.service.AuthenticationService;
import clone.asiana.asiana_clone.login.service.OtpService;
import clone.asiana.asiana_clone.login.vo.AccountUserVO;
import clone.asiana.asiana_clone.login.vo.UserVO;
import clone.asiana.asiana_clone.login.vo.VerifyOtpVO;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    OtpService OtpService;

    @Autowired
    AccountService AccountService;

    @GetMapping
    public String loginPage(){return "login/login";}

    @PostMapping
    public String login(@ModelAttribute UserDTO userInfo, HttpSession session, Model model) {

        UserVO userVo = new UserVO(userInfo.getEmail(), userInfo.getPassword());

        // 계정 상태 확인
        log.info("계정 상태 확인");
        LoginResultDTO result = authenticationService.checkAccountStatus(userVo);
        if(!result.isSuccess()){
            model.addAttribute("error", result.getMessage());
            return "login/login";
        }

        // ID/PWD 확인
        log.info("계정 ID/PWD 일치 확인");
        result = authenticationService.verifyCredentials(userVo);
        if(!result.isSuccess()){
            model.addAttribute("error", result.getMessage());
            return "login/login";
        }

        //로그인 정보 저장
        session.setAttribute("email", userVo.getEmail());
        session.setAttribute("userName", result.getMessage());
        log.info("세션에 저장한 ID : " + userVo.getEmail());

        // 2차 검증 여부 확인후 분기처리
        switch(authenticationService.checkSecondVerification(userVo.getEmail())){
            case "SKIP" :
                //세션에 로그인 인증 정보 저장
                session.setAttribute("isLoggedIn", true);
                log.info("SKIP 사용자");
                return "redirect:/";
            
            case "OTP" :
                VerifyOtpVO verifyOtp = OtpService.sendOtp(userVo.getEmail());
                session.setAttribute("verifyOtpVO", verifyOtp);

                log.info("OTP 사용자");
                return "redirect:/login/otp";

            default : return "redirect:/login";
        }
    }

    @GetMapping("/otp")
    public String otpLoginPage(@RequestParam(value = "error", required = false) String error,
                               Model model) {
        model.addAttribute("error", error);
        return "login/otp";
    }

    @PostMapping("/otp")
    public String otpVerify(@RequestParam String otp, HttpSession session, RedirectAttributes redirectAttributes) {
       log.info("OTP 번호검증");

       if(session.getAttribute("email") == null) return "redirect:/login";

       //OTP 번호검증
       LoginResultDTO result = OtpService.verifyOtp(otp, (VerifyOtpVO) session.getAttribute("verifyOtpVO"));

        if(!result.isSuccess()){
            redirectAttributes.addAttribute("error", result.getMessage());
            return "redirect:/login/otp";
        }

       session.setAttribute("isLoggedIn", true);

       log.info("로그인성공");

       //로그인 성공
       return "redirect:/";
    }

    @PostMapping("/otp/resend")
    public String resendOtp(HttpSession session) {
        log.info("왜지?");
        VerifyOtpVO verifyOtp = OtpService.sendOtp(String.valueOf(session.getAttribute("email")));

        session.setAttribute("verifyOtpVO", verifyOtp);
        log.info("OTP 발송");
        return "redirect:/login/otp";
    }

    @GetMapping("/findAccount")
    public String findAccountPage() {
        return "login/findAccount";
    }

    @PostMapping("/findAccount")
    public String findAccount(@ModelAttribute AccountUserDTO userInfo, RedirectAttributes redirectAttributes) {

        UserVO userVO = new UserVO(userInfo.getEmail());

        //계정 비밀번호 찾기
        LoginResultDTO result = AccountService.findPassword(userVO);
        if(!result.isSuccess()){
            redirectAttributes.addFlashAttribute("error", result.getMessage());
            return "redirect:/login";
        }

        log.info("패스워드 찾음");
        return "redirect:/login";
    }

    @GetMapping("/registerAccount")
    public String registerAccount() {
        return "login/registerAccount";
    }

    @PostMapping("/createAccount")
    public String createAccount(AccountUserDTO userDTO) {

        AccountUserVO accountUser = new AccountUserVO(userDTO.getEmail(), userDTO.getName(), userDTO.getPassword(), userDTO.getPhone());

        //계정 등록
        AccountService.registerAccount(accountUser);
        log.info("계정생성");
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();
        return "redirect:/";
    }

}
