package com.project.waglewagle.post;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class PostStyle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer shapeCode;

    @Column
    private Integer sortCode;

    @Column
    private Integer fontCode;

    @Column
    private Integer fontColorCode;
}
