package com.project.waglewagle.post;

import com.project.waglewagle.broad.Broad;
import com.project.waglewagle.broad.BroadStyle;
import com.project.waglewagle.entity.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ColumnDefault;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "broad_id")
    private Broad broad;

    @Column
    private String version;

    @Column
    private String message;

    @Column
    private String nickName;

    @Column
    @ColumnDefault("false")
    private Boolean isRead;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "style_id")
    private PostStyle postStyle;

    public void read(){
        this.isRead = true;
    }

}
