package com.project.project6.member;

import java.time.LocalDateTime;
import java.util.List;

public interface MemberDAO {

    public MemberDTO getMemberByEmail(String email);

    public void insert(MemberDTO memberDTO);

    public void update(MemberDTO memberDTO);

    public List<MemberDTO> getMemberList();

    public MemberDTO getMemberById(Long memId);

    public void deleteMemberById(Long id);

}
