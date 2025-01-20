package com.restapi.cicd.response;

public class CustomApiResponse<T> {
    private String Status;
    private T data;
    private String message;

    public CustomApiResponse(String status, T data, String message) {
        Status = status;
        this.data = data;
        this.message = message;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
