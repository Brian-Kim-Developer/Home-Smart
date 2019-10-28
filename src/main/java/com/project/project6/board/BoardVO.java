package com.project.project6.board;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.project.project6.favourite.FavouriteDTO;
import com.project.project6.file.FileVO;
import com.project.project6.member.MemberDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "board")
@Data
public class BoardVO {

    public BoardVO() {};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;
    private String location;
    private String content;
    @Column(name = "contact_info")
    private String contactInfo;
    @Column(name = "reg_date")
    private LocalDateTime regDate;
    @Column(name = "update_date")
    private LocalDateTime updateDate;
    private int den;
    private int bathroom;
    private int bedroom;
    private int price;
    private String type;
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false, updatable = false)
    private MemberDTO memberDTO;
    @OneToMany(fetch = FetchType.EAGER, mappedBy="bno")
    private List<FileVO> fileVOs = new ArrayList<>();;

    public BoardVO(String location, String content,
                   String contactInfo, LocalDateTime regDate, LocalDateTime updateDate, int den, int bathroom, int bedroom, int price, String type, MemberDTO memberDTO) {
        this.location = location;
        this.content = content;
        this.contactInfo = contactInfo;
        this.regDate = regDate;
        this.updateDate = updateDate;
        this.den = den;
        this.bathroom = bathroom;
        this.bedroom = bedroom;
        this.price = price;
        this.type = type;
        this.memberDTO = memberDTO;
    }
}
