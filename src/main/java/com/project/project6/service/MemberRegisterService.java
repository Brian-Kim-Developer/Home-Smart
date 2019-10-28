package com.project.project6.service;

import com.project.project6.exception.DuplicateMemberException;
import com.project.project6.member.MemberDTO;
import com.project.project6.member.MemberDAO;
import com.project.project6.request.RegisterMemberRequest;

import java.time.LocalDateTime;

public class MemberRegisterService {

	private MemberDAO memberDAO;

	public MemberRegisterService(MemberDAO memberDao) {
		this.memberDAO = memberDao;
	}

	public Long register(RegisterMemberRequest req) {
		MemberDTO memberDTO = memberDAO.getMemberByEmail(req.getEmail());
		if (memberDTO != null) {
			throw new DuplicateMemberException("dup email " + req.getEmail());
		}
		MemberDTO newMemberDTO = new MemberDTO(
				req.getEmail(), req.getPassword(), req.getName(), 
				LocalDateTime.now());
		memberDAO.insert(newMemberDTO);
		return newMemberDTO.getId();
	}

}
