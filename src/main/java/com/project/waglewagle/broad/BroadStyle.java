package com.project.waglewagle.broad;

import com.project.waglewagle.broad.dto.BroadStyleDTO;
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
    private Integer colorCode;

    @Column
    private Integer backGroundCode;

    @Column
    private Integer friendCode;

    public void update(BroadStyleDTO request) {
        if(request.getColorCode() != null){
            this.colorCode = request.getColorCode();
        }
        if(request.getBackGroundCode() != null){
            this.friendCode = request.getFriendCode();
        }
        if(request.getFriendCode() != null){
            this.friendCode = request.getFriendCode();
        }
    }
}
