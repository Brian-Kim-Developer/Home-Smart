package com.project.project6.service;

import com.project.project6.member.MemberDAO;
import com.project.project6.member.MemberDTO;

public class UserProfileService {

    private MemberDAO memberDAO;

    public void setMemberDAO(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    public MemberDTO getUserProfile(Long id) {
        MemberDTO member = memberDAO.getMemberById(id);
        return member;
    }

}
