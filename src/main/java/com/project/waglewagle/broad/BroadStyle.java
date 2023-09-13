package com.project.waglewagle.broad;

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
public class BroadStyle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer color_Code;

    @Column
    private Integer backGround_Code;

    @Column
    private Integer friend_Code;

}
