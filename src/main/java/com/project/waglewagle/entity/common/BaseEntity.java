package com.project.waglewagle.entity.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
@Getter
public class BaseEntity {
    @CreatedBy
    @Column(updatable = false, nullable = false)
    private String createdBy; //생성자

    @LastModifiedBy
    @Column(nullable = false)
    private String modifiedBy; //수정자
}
