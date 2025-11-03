package clone.asiana.asiana_clone.login.dto;

public class LoginResultDTO {

    public enum Status {
        SUCCESS,
        USER_NOT_FOUND,
        WRONG_ID_OR_WRONG_PASSWORD,
        ACCOUNT_LOCKED,
        ACCOUNT_SUSPENDED,
        OTP_TIME_OVER,
        WRONG_OTP

    }

    private Status status;
    private String message;

    public LoginResultDTO(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return this.status == Status.SUCCESS;
    }
}
