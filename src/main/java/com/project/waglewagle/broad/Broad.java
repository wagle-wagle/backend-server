package com.project.waglewagle.broad;

import com.project.waglewagle.broad.dto.BroadStyleDTO;
import com.project.waglewagle.entity.common.BaseTimeEntity;
import com.project.waglewagle.post.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "broad", cascade = CascadeType.REMOVE)
    private List<Post> posts = new ArrayList<>();


    @Column
    private String url;

    public void updateTitle(String newTitle) {
        this.title = newTitle;
    }
}
