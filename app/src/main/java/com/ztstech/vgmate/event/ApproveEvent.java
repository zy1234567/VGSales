package com.ztstech.vgmate.event;

/**
 * Created by dongdong on 2018/4/23.
 */

public class ApproveEvent {
    private String message;

    public ApproveEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
