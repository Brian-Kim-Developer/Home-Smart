package com.project.project6.config;

import com.project.project6.login.AuthService;
import com.project.project6.member.MemberDAO;
import com.project.project6.member.MemberDAOJpa;
import com.project.project6.service.ChangePasswordService;
import com.project.project6.service.MemberDeleteService;
import com.project.project6.service.MemberRegisterService;
import com.project.project6.service.UserProfileService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberConfig {

    @Bean
    public MemberDAO memberDAO() {
        return new MemberDAOJpa();
    }

	/*@Bean
	public MemberDAOJdbc memberDAO() { return new MemberDAOJdbc(dataSource()); }*/

    @Bean
    public MemberRegisterService memberRegisterService() {
        return new MemberRegisterService(memberDAO());
    }

    @Bean
    public MemberDeleteService memberDeleteService() { return new MemberDeleteService(memberDAO());}

    @Bean
    public ChangePasswordService changePasswordService() {
        ChangePasswordService changePasswordService = new ChangePasswordService();
        changePasswordService.setMemberDao(memberDAO());
        return changePasswordService;
    }

    @Bean
    public AuthService authService() {
        AuthService authService = new AuthService();
        authService.setMemberDao(memberDAO());
        return authService;
    }

    @Bean
    public UserProfileService userProfileService() {
        UserProfileService userProfileService = new UserProfileService();
        userProfileService.setMemberDAO(memberDAO());
        return userProfileService;
    }

}
