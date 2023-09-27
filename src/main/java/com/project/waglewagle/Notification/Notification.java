package com.project.waglewagle.Notification;

import lombok.Getter;

@Getter
public class Notification {
    private Long unixTime;

    private String userName;

    private String type;

    public Notification(Long unixTime, String userName, String type) {
        this.unixTime = unixTime;
        this.userName = userName;
        this.type = type;
    }

    public String toString(){
        return unixTime+","+userName+","+type;
    }
}
