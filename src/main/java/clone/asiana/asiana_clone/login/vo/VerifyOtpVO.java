package clone.asiana.asiana_clone.login.vo;

import java.time.LocalDateTime;

public class VerifyOtpVO {

    private String sysOtp;
    private LocalDateTime expireTime;
    private int attemptCount; // optional

    public String getSysOtp() {
        return sysOtp;
    }

    public void setSysOtp(String sysOtp) {
        this.sysOtp = sysOtp;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public int getAttemptCount() {
        return attemptCount;
    }

    public void setAttemptCount(int attemptCount) {
        this.attemptCount = attemptCount;
    }
}
