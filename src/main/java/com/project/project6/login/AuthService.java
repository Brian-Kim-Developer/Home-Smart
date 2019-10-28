package com.project.project6.login;

import com.project.project6.exception.WrongIdPasswordException;
import com.project.project6.member.MemberDAO;
import com.project.project6.member.MemberDTO;

public class AuthService {

    private MemberDAO memberDao;

    public void setMemberDao(MemberDAO memberDao) {
        this.memberDao = memberDao;
    }

    public AuthInfo authenticate(String email, String password) {
        MemberDTO memberDTO = memberDao.getMemberByEmail(email);
        if (memberDTO == null) {
            System.out.println("null");
            throw new WrongIdPasswordException();
        }
        if (!memberDTO.matchPassword(password)) {
            System.out.println(memberDTO.getEmail());
            System.out.println(memberDTO.getPassword());
            System.out.println("does not match");
            throw new WrongIdPasswordException();
        }
        return new AuthInfo(memberDTO.getId(), memberDTO.getEmail(), memberDTO.getName());
    }
}
