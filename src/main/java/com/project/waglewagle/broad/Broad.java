package com.project.waglewagle.broad;

import com.project.waglewagle.entity.common.BaseTimeEntity;
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
public class Broad extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String version;

    @Column
    private String title;

    @OneToOne
    @JoinColumn(name = "style_id")
    private BroadStyle broadStyle;

    @Column
    private String url;
}
