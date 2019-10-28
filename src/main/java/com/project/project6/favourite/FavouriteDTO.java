package com.project.project6.favourite;

import com.project.project6.member.MemberDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "favourite")
@Data
public class FavouriteDTO {

    public FavouriteDTO(){};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favourite_id;
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false, updatable = false)
    private MemberDTO memberDTO;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> bnos;

    public FavouriteDTO(MemberDTO memberDTO, List<Long> bnos) {
        this.memberDTO = memberDTO;
        this.bnos = bnos;
    }

}
