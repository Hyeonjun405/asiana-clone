package clone.asiana.asiana_clone.login.vo;

public class AccountUserVO {

    private String email;
    private String name;
    private String password;
    private String phone;

    public AccountUserVO(String email, String name, String password, String phone) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() { return phone; }
}
