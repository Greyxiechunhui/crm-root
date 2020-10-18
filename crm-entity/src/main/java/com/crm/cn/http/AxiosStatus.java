package com.crm.cn.http;

public enum AxiosStatus {
    OK(20000,"操作成功"),
    ERROR(50000,"操作失败")

    ;

    private int status;

    private String message;

    AxiosStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
