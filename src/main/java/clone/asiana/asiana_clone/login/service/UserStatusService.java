package clone.asiana.asiana_clone.login.service;

import clone.asiana.asiana_clone.login.mapper.UserStatusMapper;
import clone.asiana.asiana_clone.login.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserStatusService {

    @Autowired
    private UserStatusMapper mapper;

    public void increaseFailCount(UserVO user){

        int fail = mapper.updateFailureCountByUserId(user);
        if(fail >= 5) {
            mapper.lockAccount(user);
        }
    }
}
