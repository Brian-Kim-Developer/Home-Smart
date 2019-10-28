package com.project.project6.service;

import com.project.project6.file.FileDAO;
import com.project.project6.file.FileVO;
import com.project.project6.request.FileRequest;

import java.util.List;

public class FileService {

    private FileDAO fileDAO;

    public void setFileDAO(FileDAO fileDAO) { this.fileDAO = fileDAO; }

    public List<FileVO> getAllFiles() {
        List<FileVO> files = fileDAO.getFiles();
        return files;
    }

    public FileVO getFileByFNo(Long fno) {
        FileVO file = fileDAO.getFileByFNo(fno);
        return file;
    }

    public FileVO getTitle(FileRequest req) {
        FileVO file = fileDAO.getTitleFile(req.getBno(), req.isTitle());
        return file;
    }

    public List<FileVO> getContent(FileRequest req) {
        List<FileVO> files = fileDAO.getContentFiles(req.getBno(), req.isTitle());
        return files;
    }

}
