package clone.asiana.asiana_clone.login.service;

import clone.asiana.asiana_clone.login.vo.AccountUserVO;
import clone.asiana.asiana_clone.login.vo.UserVO;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    // 계정 상태 확인 (휴면/잠금/정상 등)
    public void checkAccountStatus(String userNo){

    }

    // ID/PWD 확인
    public void verifyCredentials(UserVO userVO){

    }

    // 2차 검증
    // SKIP / OTP
    public String checkSecondVerification(String userNo){

        return "OTP";
    }

    // OTP 발송
    public void sendOtp(String userNo){
        //초기화 작업

    }

    public boolean verifyOtp(String otp, String userNo){
        return true;
    }

    // ID 찾기
    public void findId(){

    }

    // 패스워드 찾기
    public void findPassword(String email){

    }

    // 계정 등록
    public void registerAccount(AccountUserVO userVO){

    }
}
