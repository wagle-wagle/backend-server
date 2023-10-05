package com.project.waglewagle.Notification;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    private String title;

    private String userName;

    private String message;

    private String type;

}
