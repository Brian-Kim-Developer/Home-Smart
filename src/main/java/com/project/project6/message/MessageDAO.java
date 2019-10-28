package com.project.project6.message;

import java.util.List;

public interface MessageDAO {

    // select * from message where recipient_id = #{recipient_id};
    public List<MessageDTO> getMessageListByRecipientId(Long recipient_id);

    // select * from message where sender_id = #{sender_id};
    public List<MessageDTO> getMessageListBySenderId(Long sender_id);

    // insert into board (mno, sender_id, recipient_id, message)
    // values (seq_board.nextval, #{sender_id}, #{recipient_id}, #{message})
    public void insert(MessageDTO message);

    // delete * from message where sender_id = #{sender_id};
    public void deleteAllMessagesByMemId(Long memId);

}
