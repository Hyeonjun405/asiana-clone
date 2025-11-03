package clone.asiana.asiana_clone.login.service;

import clone.asiana.asiana_clone.login.dto.LoginResultDTO;
import clone.asiana.asiana_clone.login.vo.VerifyOtpVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@Slf4j
public class OtpService {

    private static final SecureRandom random = new SecureRandom();

    // OTP 발송
    public VerifyOtpVO sendOtp(String userNo){

        String otpNo = generateOtp();
        log.info("otp 번호 : " + otpNo);
        // userNo 발송처리

        VerifyOtpVO otp = new VerifyOtpVO();
        otp.setSysOtp(otpNo);
        otp.setExpireTime(LocalDateTime.now().plusMinutes(5));
        otp.setAttemptCount(0);

        return otp;
    }

    public LoginResultDTO verifyOtp(String otp, VerifyOtpVO verifyOtp){

        if (LocalDateTime.now().isAfter(verifyOtp.getExpireTime())) {
            return new LoginResultDTO(LoginResultDTO.Status.OTP_TIME_OVER, "OTP 시간 초과");
        }

        if(!otp.equals(verifyOtp.getSysOtp())){
            return new LoginResultDTO(LoginResultDTO.Status.WRONG_OTP, "OTP번호 오류");
        }

        return new LoginResultDTO(LoginResultDTO.Status.SUCCESS, "성공");
    }



    private String generateOtp() {
        SecureRandom random = new SecureRandom();
        int number = random.nextInt(1000000);
        return String.format("%06d", number);
    }
}
