package com.crm.cn.exception;

import com.crm.cn.http.AxiosStatus;

public class LoginException extends RuntimeException {
    private AxiosStatus axiosStatus;

    public LoginException(AxiosStatus axiosStatus) {
        this.axiosStatus = axiosStatus;
    }

    public AxiosStatus getAxiosStatus() {
        return axiosStatus;
    }

    public void setAxiosStatus(AxiosStatus axiosStatus) {
        this.axiosStatus = axiosStatus;
    }
}
