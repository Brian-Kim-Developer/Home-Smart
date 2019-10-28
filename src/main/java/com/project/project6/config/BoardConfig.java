package com.project.project6.config;

import com.project.project6.favourite.FavouriteDAO;
import com.project.project6.favourite.FavouriteDAOJpa;
import com.project.project6.member.MemberDAO;
import com.project.project6.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.project6.board.BoardDAO;
import com.project.project6.board.BoardDAOJpa;
import com.project.project6.file.FileDAO;

@Configuration
public class BoardConfig {

    @Autowired
    MemberDAO memberDAO;

    @Autowired
    FileDAO fileDAO;

    @Bean
    public BoardDAO boardDAO() {
        return new BoardDAOJpa();
    }

    @Bean
    public FavouriteDAO favouriteDAO() { return new FavouriteDAOJpa(); }

    @Bean
    public BoardRegisterService boardRegisterService() {
        BoardRegisterService boardRegisterService = new BoardRegisterService();
        boardRegisterService.setMemberDAO(memberDAO);
        boardRegisterService.setBoardDAO(boardDAO());
        boardRegisterService.setFileDAO(fileDAO);
        return boardRegisterService;
    }

    @Bean
    public BoardListService boardListService() {
        BoardListService boardListService = new BoardListService();
        boardListService.setBoardDAO(boardDAO());
        return boardListService;
    }

    @Bean
    public BoardUpdateService boardUpdateService() {
        BoardUpdateService boardUpdateService = new BoardUpdateService();
        boardUpdateService.setMemberDAO(memberDAO);
        boardUpdateService.setBoardDAO(boardDAO());
        boardUpdateService.setFileDAO(fileDAO);
        return boardUpdateService;
    }

    @Bean
    public BoardDeleteService boardDeleteService() {
        BoardDeleteService boardDeleteService = new BoardDeleteService();
        boardDeleteService.setBoardDAO(boardDAO());
        return boardDeleteService;
    }

    @Bean
    public BoardSearchService boardSearchService() {
        BoardSearchService boardSearchService = new BoardSearchService();
        boardSearchService.setBoardDAO(boardDAO());
        return boardSearchService;
    }

    @Bean
    public BoardMyListService boardMyListService() {
        BoardMyListService boardMyListService = new BoardMyListService();
        boardMyListService.setBoardDAO(boardDAO());
        return boardMyListService;
    }

    @Bean
    public BoardFavouriteService boardFavouriteService() {
        BoardFavouriteService boardFavouriteService = new BoardFavouriteService();
        boardFavouriteService.setFavouriteDAO(favouriteDAO());
        boardFavouriteService.setBoardDAO(boardDAO());
        return boardFavouriteService;
    }

}