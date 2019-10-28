package com.project.project6.service;

import com.project.project6.exception.MemberNotFoundException;
import com.project.project6.member.MemberDTO;
import com.project.project6.member.MemberDAO;
import org.springframework.transaction.annotation.Transactional;

public class ChangePasswordService {

	private MemberDAO memberDAO;

	public void setMemberDao(MemberDAO memberDao) {
		this.memberDAO = memberDao;
	}

	@Transactional
	public void changePassword(String email, String oldPwd, String newPwd) {
		MemberDTO memberDTO = memberDAO.getMemberByEmail(email);
		if (memberDTO == null)
			throw new MemberNotFoundException();

		memberDTO.changePassword(oldPwd, newPwd);

		memberDAO.update(memberDTO);
	}

}
