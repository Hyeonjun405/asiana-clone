package clone.asiana.asiana_clone.login.mapper;


import clone.asiana.asiana_clone.login.vo.AccountUserVO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AccountMapper {

    void registerUser(AccountUserVO AccountUserVO);

}
