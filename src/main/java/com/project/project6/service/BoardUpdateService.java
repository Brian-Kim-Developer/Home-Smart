package com.project.project6.service;

import com.project.project6.board.BoardDAO;
import com.project.project6.request.BoardRequest;
import com.project.project6.board.BoardVO;
import com.project.project6.file.FileDAO;
import com.project.project6.file.FileVO;
import com.project.project6.member.MemberDAO;
import com.project.project6.member.MemberDTO;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class BoardUpdateService {

    private MemberDAO memberDAO;
    private BoardDAO boardDAO;
    private FileDAO fileDAO;

    public void setBoardDAO(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    public void setFileDAO(FileDAO fileDAO) {
        this.fileDAO = fileDAO;
    }

    public void setMemberDAO(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    public Long update(BoardRequest req, Long bno, String email, MultipartFile file, MultipartFile[] files) {

        MemberDTO memberDTO = getMemberDTOByEmail(email);
        BoardVO boardVO = new BoardVO(
                req.getLocation(), req.getContent(), req.getContactInfo(),
                LocalDateTime.now(), LocalDateTime.now(), req.getDen(), req.getBathroom(), req.getBedroom(), req.getPrice(), req.getType(), memberDTO);

        fileDAO.deleteByBNo(bno);

        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();
            String destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileNameExtension;
            Blob fileData = null;
            byte[] fileByte = null;
            try {
                fileByte = file.getBytes();
                fileData = new SerialBlob(fileByte);
            } catch(IOException e) {
                e.printStackTrace();
            } catch(SQLException e) {
                e.printStackTrace();
            }
            FileVO fileVO = new FileVO(destinationFileName, fileName, file.getContentType(), fileData, true, bno);
            fileDAO.register(fileVO);
        }

        if(!files[0].isEmpty()) {
            for (MultipartFile singleFile : files) {
                String fileName = singleFile.getOriginalFilename();
                String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();
                String destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileNameExtension;
                Blob fileData = null;
                byte[] fileByte = null;
                try {
                    fileByte = singleFile.getBytes();
                    fileData = new SerialBlob(fileByte);
                } catch(IOException e) {
                    e.printStackTrace();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
                FileVO fileVO = new FileVO(destinationFileName, fileName, singleFile.getContentType(), fileData, false, bno);
                fileDAO.register(fileVO);
            }
        }

        List<FileVO> fileVOs = fileDAO.getFilesByBno(boardVO.getBno());
        boardVO.setFileVOs(fileVOs);

        boardDAO.update(boardVO, bno);

        return boardVO.getBno();
    }

    public BoardVO getBoardByBNo(Long bno) {
        BoardVO board = boardDAO.getBoardByBNo(bno);
        return board;
    }

    private MemberDTO getMemberDTOByEmail(String email) {
        MemberDTO memberDTO = memberDAO.getMemberByEmail(email);
        return memberDTO;
    }
}