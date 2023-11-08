package com.example.trip.controller.constant;

import lombok.Data;

@Data
public class Message {
    private int status;
    private String message;
    private Object data;

    public Message(){
        this.status = StatusCode.BAD_REQUEST;
        this.data = null;
        this.message = null;
    }


}
