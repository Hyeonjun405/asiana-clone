package clone.asiana.asiana_clone.login.service;

import clone.asiana.asiana_clone.login.mapper.AccountMapper;
import clone.asiana.asiana_clone.login.vo.AccountUserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountService {

    @Autowired
    private AccountMapper mapper;

    // 패스워드 찾기
    public void findPassword(String email){
      log.info("메일 발송!");
    }

    // 계정 등록
    public void registerAccount(AccountUserVO userVO){
        mapper.registerUser(userVO);
    }
}
