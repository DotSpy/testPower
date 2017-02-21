package com.power.entity;

public class LogEnry {

    private String ip;
    private String login;
    private LoginStatus status;
    private Long epochTime;

    private LogEnry(Builder builder) {
        ip = builder.ip;
        login = builder.login;
        status = builder.status;
        epochTime = builder.epochTime;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getIp() {
        return ip;
    }

    public String getLogin() {
        return login;
    }

    public LoginStatus getStatus() {
        return status;
    }

    public Long getEpochTime() {
        return epochTime;
    }

    public static final class Builder {
        private String ip;
        private String login;
        private LoginStatus status;
        private Long epochTime;

        private Builder() {
        }

        public Builder withIp(String val) {
            ip = val;
            return this;
        }

        public Builder withLogin(String val) {
            login = val;
            return this;
        }

        public Builder withStatus(LoginStatus val) {
            status = val;
            return this;
        }

        public Builder withEpochTime(Long val) {
            epochTime = val;
            return this;
        }

        public LogEnry build() {
            return new LogEnry(this);
        }
    }
}
