package com.example.api_java_flutter.models;

public class Message {
    private int code;
    private String message;

    public String getMessage() {
        return message;
    }
    public int getCode() {
        return code;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setCode(int code) {
        this.code = code;
    }

    public Message() {
    }
    Message(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
