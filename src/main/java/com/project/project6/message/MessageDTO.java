package com.project.project6.message;

import com.project.project6.member.MemberDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="message")
@Data
public class MessageDTO {

    public MessageDTO() {};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long mno;
    private String message;
    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false, updatable = false)
    private MemberDTO senderDTO;
    @ManyToOne
    @JoinColumn(name = "recipient_id", nullable = false, updatable = false)
    private MemberDTO recipientDTO;
    @Column(name="reg_date")
    private LocalDateTime registerDateTime;

    public MessageDTO(String message, MemberDTO senderDTO, MemberDTO recipientDTO, LocalDateTime registerDateTime) {
        this.message = message;
        this.senderDTO = senderDTO;
        this.recipientDTO = recipientDTO;
        this.registerDateTime = registerDateTime;
    }

}