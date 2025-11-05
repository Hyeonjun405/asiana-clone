package clone.asiana.asiana_clone.login.vo;

public class UserVO {

    private String email;
    private String password;

    public UserVO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserVO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
