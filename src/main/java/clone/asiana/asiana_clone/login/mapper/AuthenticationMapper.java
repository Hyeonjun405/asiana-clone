package clone.asiana.asiana_clone.login.mapper;


import clone.asiana.asiana_clone.login.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AuthenticationMapper {

    String isLocked(UserVO uservo);
    String verifyCredentials(UserVO uservo);


}
