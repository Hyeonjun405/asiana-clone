package clone.asiana.asiana_clone.login.service;

import clone.asiana.asiana_clone.login.exception.AuthenticationException;
import clone.asiana.asiana_clone.login.mapper.AuthenticationMapper;
import clone.asiana.asiana_clone.login.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    AuthenticationMapper authenticationMapper;


    // 계정 상태 확인 (휴면/잠금/정상 등)
    public void checkAccountStatus(UserVO user){

        String userStatus = authenticationMapper.isLocked(user);

        if(userStatus == null) throw new AuthenticationException("계정 정보 없음");

        switch(userStatus){
            case "ACTIVE": return; // 등록+ACTIVE 계정은 통과
            case "LOCKED" : throw new AuthenticationException("계정 잠금 상태");
            case "SUSPENDED": throw new AuthenticationException("계정 정지 상태");
            default : throw new AuthenticationException("계정 정보 없음");
        }

    }

    // ID/PWD 확인
    public void verifyCredentials(UserVO user){

        // 계정이 있을 경우 true리턴
        if(!authenticationMapper.verifyCredentials(user)){

            authenticationMapper.updateFailureCountByUserId(user);

            throw new AuthenticationException("ID 또는 패스워드 틀림");
        }


    }

    // SKIP / OTP
    public String checkSecondVerification(String userNo){

        return "OTP";
    }

}
