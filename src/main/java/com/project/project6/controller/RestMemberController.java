package com.project.project6.controller;

import com.project.project6.board.BoardDAO;
import com.project.project6.board.BoardVO;
import com.project.project6.favourite.FavouriteDAO;
import com.project.project6.favourite.FavouriteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.project.project6.service.MemberRegisterService;
import com.project.project6.member.MemberDTO;
import com.project.project6.member.MemberDAO;
import com.project.project6.exception.DuplicateMemberException;
import com.project.project6.exception.MemberNotFoundException;
import com.project.project6.request.RegisterMemberRequest;

import javax.validation.Valid;

import com.project.project6.error.ErrorResponse;

@RestController
public class RestMemberController {

    private MemberDAO memberDao;
    private FavouriteDAO favouriteDAO;
    private MemberRegisterService registerService;

    @GetMapping("/api/favourite")
    public FavouriteDTO favourite() {
        return favouriteDAO.getBoardsIdByMemberId(2L);
    }

    @GetMapping("/api/members")
    public List<MemberDTO> members() {
        return memberDao.getMemberList();
    }

    @PostMapping("/api/members")
    public ResponseEntity<Object> newMember(@RequestBody @Valid RegisterMemberRequest regReq, Errors errors) {
        if (errors.hasErrors()) {
            String errorCodes = errors.getAllErrors()
                    .stream()
                    .map(error -> error.getCodes()[0])
                    .collect(Collectors.joining(","));
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("errorCodes = " + errorCodes));
        }
        try {
            Long newMemberId = registerService.register(regReq);
            URI uri = URI.create("/api/members/" + newMemberId);
            return ResponseEntity.created(uri).build();
        } catch (DuplicateMemberException dupEx) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/api/members/{id}")
    public ResponseEntity<Object> member(@PathVariable Long id) {
        MemberDTO memberDTO = memberDao.getMemberById(id);
        if (memberDTO == null) {
            throw new MemberNotFoundException();
        }
        return ResponseEntity.status(HttpStatus.OK).body(memberDTO);
    }

    @Autowired
    public void setMemberDao(MemberDAO memberDao) {
        this.memberDao = memberDao;
    }

    @Autowired
    public void setFavouriteDAO(FavouriteDAO favouriteDAO) {this.favouriteDAO = favouriteDAO; }

    @Autowired
    public void setRegisterService(MemberRegisterService registerService) {
        this.registerService = registerService;
    }

}
