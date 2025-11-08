package clone.asiana.asiana_clone.login.service;

import clone.asiana.asiana_clone.login.dto.LoginResultDTO;
import clone.asiana.asiana_clone.login.mapper.AuthenticationMapper;
import clone.asiana.asiana_clone.login.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationService {

    @Autowired
    AuthenticationMapper authenticationMapper;

    @Autowired
    UserStatusService userStatus;


    // 계정 상태 확인 (휴면/잠금/정상 등)
    public LoginResultDTO checkAccountStatus(UserVO user){

        String userStatus = authenticationMapper.isLocked(user);

        if(userStatus == null) return new LoginResultDTO(LoginResultDTO.Status.USER_NOT_FOUND, "정보없음");

        LoginResultDTO.Status status;
        switch(userStatus){
            case "ACTIVE": return new LoginResultDTO(LoginResultDTO.Status.SUCCESS, "성공");
            case "LOCKED" : return new LoginResultDTO(LoginResultDTO.Status.ACCOUNT_LOCKED, "계정 잠김");
            case "SUSPENDED": return new LoginResultDTO(LoginResultDTO.Status.ACCOUNT_SUSPENDED, "계정 정지");
            default : return new LoginResultDTO(LoginResultDTO.Status.USER_NOT_FOUND, "정보없음");
        }
    }

    // ID/PWD 확인
    public LoginResultDTO verifyCredentials(UserVO user){

        // 계정이 있을 경우 true리턴해서 if 통과함.
        String name = authenticationMapper.verifyCredentials(user);
        if(name == null){

            userStatus.increaseFailCount(user);
            return new LoginResultDTO(LoginResultDTO.Status.WRONG_ID_OR_WRONG_PASSWORD, "ID또는 패스워드틀림");
        }

        return new LoginResultDTO(LoginResultDTO.Status.SUCCESS, name);
    }

    // SKIP / OTP
    public String checkSecondVerification(String userNo){

        return "OTP";
    }

}
