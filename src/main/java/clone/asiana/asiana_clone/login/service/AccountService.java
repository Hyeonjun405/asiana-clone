package clone.asiana.asiana_clone.login.service;

import clone.asiana.asiana_clone.login.dto.AccountUserDTO;
import clone.asiana.asiana_clone.login.dto.LoginResultDTO;
import clone.asiana.asiana_clone.login.mapper.AccountMapper;
import clone.asiana.asiana_clone.login.vo.AccountUserVO;
import clone.asiana.asiana_clone.login.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountService {

    @Autowired
    private AccountMapper mapper;

    // 패스워드 찾기
    public LoginResultDTO findPassword(UserVO userVO){

      AccountUserDTO userInfo = mapper.findPW(userVO);

      if(userInfo== null) return new LoginResultDTO(LoginResultDTO.Status.USER_NOT_FOUND, "정보없음");
      
      log.info("패스워드 찾기 결과: " + userInfo.getPassword());

      log.info("유저 패스 워드 메일 발송!");

      return new LoginResultDTO(LoginResultDTO.Status.SUCCESS, "정상");
    }

    // 계정 등록
    public void registerAccount(AccountUserVO userVO){
        mapper.registerUser(userVO);
    }
}
