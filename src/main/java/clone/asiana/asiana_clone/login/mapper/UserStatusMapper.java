package clone.asiana.asiana_clone.login.mapper;

import clone.asiana.asiana_clone.login.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserStatusMapper {

    int updateFailureCountByUserId(UserVO uservo);
    void lockAccount(UserVO uservo);
    void lockAccountLog(UserVO uservo);

}
