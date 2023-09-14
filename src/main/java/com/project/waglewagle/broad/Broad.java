package com.project.waglewagle.broad;

import com.project.waglewagle.broad.dto.BroadStyleDTO;
import com.project.waglewagle.entity.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

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

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "style_id")
    private BroadStyle broadStyle;

    @Column
    private String url;

    public void updateTitle(String newTitle) {
        this.title = newTitle;
    }
}
