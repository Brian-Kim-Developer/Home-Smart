package com.project.project6.service;

import javax.sql.rowset.serial.SerialBlob;

import lombok.Synchronized;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import com.project.project6.board.BoardDAO;
import com.project.project6.request.BoardRequest;
import com.project.project6.board.BoardVO;
import com.project.project6.file.FileVO;
import com.project.project6.file.FileDAO;
import com.project.project6.member.MemberDAO;
import com.project.project6.member.MemberDTO;

public class BoardRegisterService {

    private MemberDAO memberDAO;
    private BoardDAO boardDAO;
    private FileDAO fileDAO;

    public void setBoardDAO(BoardDAO boardDAO) { this.boardDAO = boardDAO; }
    public void setFileDAO(FileDAO fileDAO) { this.fileDAO = fileDAO; }
    public void setMemberDAO(MemberDAO memberDAO) {this.memberDAO = memberDAO; }

    @Synchronized
    public Long register(BoardRequest req, String email, MultipartFile file, MultipartFile[] files) {

        MemberDTO memberDTO = getMemberDTOByEmail(email);
        BoardVO boardVO = new BoardVO(
                req.getLocation(), req.getContent(), req.getContactInfo(),
                LocalDateTime.now(), LocalDateTime.now(), req.getDen(), req.getBathroom(), req.getBedroom(), req.getPrice(), req.getType(), memberDTO);

        boardDAO.insert(boardVO);

        if(!file.isEmpty()) {
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
            FileVO fileVO = new FileVO(destinationFileName, fileName, file.getContentType(), fileData, true, boardVO.getBno());
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
                FileVO fileVO = new FileVO(destinationFileName, fileName, singleFile.getContentType(), fileData, false, boardVO.getBno());
                fileDAO.register(fileVO);
            }
        }

        List<FileVO> fileVOs = fileDAO.getFilesByBno(boardVO.getBno());
        boardVO.setFileVOs(fileVOs);

        boardDAO.update(boardVO, boardVO.getBno());

        return boardVO.getBno();
    }

    /*public Long register(BoardRequest req, String email, MultipartFile file, MultipartFile[] files) {
        MemberDTO memberDTO = getMemberDTOByEmail(email);
        BoardVO boardVO = new BoardVO(
                req.getLocation(), req.getContent(), req.getContactInfo(),
                LocalDateTime.now(), LocalDateTime.now(), req.getDen(), req.getBathroom(), req.getBedroom(), req.getPrice(), req.getType(), memberDTO);

        boardDAO.insert(boardVO);

        if(!files.isEmpty()) {

            String fileName = files.getOriginalFilename();
            String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();
            File destinationFile;
            String destinationFileName;
            String fileUrl = "C:\\Users\\lg\\Desktop\\Spring Images\\";

            do {
                destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileNameExtension;
                destinationFile = new File(fileUrl + destinationFileName);
            } while (destinationFile.exists());

            destinationFile.getParentFile().mkdirs();
            try {
                files.transferTo(destinationFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
            FileVO fileVO = new FileVO(boardVO.getBno(), destinationFileName, fileName, fileUrl);
            fileDAO.register(fileVO);
        }

        return boardVO.getBno();
    }*/

    private MemberDTO getMemberDTOByEmail(String email) {
        MemberDTO memberDTO = memberDAO.getMemberByEmail(email);
        return memberDTO;
    }

}
