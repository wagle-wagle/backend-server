package com.project.waglewagle.Notification;

import lombok.Getter;

@Getter
public class Notification {
    private String title;

    private String userName;

    private String message;

    private String type;

    public Notification(String title, String userName, String message, String type) {
        this.title = title;
        this.userName = userName;
        this.message = message;
        this.type = type;
    }

}
