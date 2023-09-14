package com.project.waglewagle.entity.common;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
@Getter
public class BaseTimeEntity {

    @Column(updatable = false)
    private Long createdTime; // 생성시간
    @PrePersist
    public void setCreatedTime(){
        this.createdTime = System.currentTimeMillis() / 1000;
    }

    @Column
    private Long updatedTime; // 수정시간
    @PreUpdate
    public void setUpdatedTime(){
        this.updatedTime = System.currentTimeMillis() /1000;
    }

}
