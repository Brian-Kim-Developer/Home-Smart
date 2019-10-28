package com.project.project6.service;

import com.project.project6.member.MemberDAO;

public class MemberDeleteService {

    private MemberDAO memberDAO;

    public MemberDeleteService(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    public void deleteMemberByMemId(Long memId) {
        memberDAO.deleteMemberById(memId);
    }

}
