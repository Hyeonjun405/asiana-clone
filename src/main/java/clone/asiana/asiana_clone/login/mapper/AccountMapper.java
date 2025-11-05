package clone.asiana.asiana_clone.login.mapper;


import clone.asiana.asiana_clone.login.dto.AccountUserDTO;
import clone.asiana.asiana_clone.login.vo.AccountUserVO;
import clone.asiana.asiana_clone.login.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AccountMapper {

    void registerUser(AccountUserVO AccountUserVO);
    AccountUserDTO findPW(UserVO userVO);

}
