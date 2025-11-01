package clone.asiana.asiana_clone.login.service;

import clone.asiana.asiana_clone.login.exception.OtpException;
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

    public void verifyOtp(String otp, VerifyOtpVO verifyOtp){

        if (LocalDateTime.now().isAfter(verifyOtp.getExpireTime())) {
            throw new OtpException("OTP시간 만료");
        }

        if(!otp.equals(verifyOtp.getSysOtp())){
            throw new OtpException("OTP 정보 다시 입력");
        }

    }



    private String generateOtp() {
        SecureRandom random = new SecureRandom();
        int number = random.nextInt(1000000);
        return String.format("%06d", number);
    }
}
