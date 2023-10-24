package com.project.waglewagle.Notification.DTO;

import com.project.waglewagle.Notification.Notification;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponse {
    private String id;

    private String title;

    private String userName;

    private String message;

    private Integer shapeType;

    private String type;

    public void mapper(String id, Notification notification) {
        this.id = id;
        this.title = notification.getTitle();
        this.userName = notification.getUserName();
        this.message = notification.getMessage();
        this.shapeType = notification.getShapeType();
        this.type = notification.getType();
    }
}
