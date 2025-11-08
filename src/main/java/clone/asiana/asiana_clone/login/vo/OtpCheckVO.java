package clone.asiana.asiana_clone.login.vo;

public class OtpCheckVO {

    String otp;
    VerifyOtpVO verifyOtp;
    String email;

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public VerifyOtpVO getVerifyOtp() {
        return verifyOtp;
    }

    public void setVerifyOtp(VerifyOtpVO verifyOtp) {
        this.verifyOtp = verifyOtp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
