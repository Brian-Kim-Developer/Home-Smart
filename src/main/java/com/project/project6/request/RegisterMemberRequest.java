package com.project.project6.request;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class RegisterMemberRequest {

	private String email;
	private String password;
	private String confirmPassword;
	private String name;

	public boolean isPasswordEqualToConfirmPassword() {
		return password.equals(confirmPassword);
	}
}
