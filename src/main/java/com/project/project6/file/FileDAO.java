package com.project.project6.file;

import java.util.List;

public interface FileDAO {

    public List<FileVO> getFiles();
    public FileVO getFileByFNo(Long fno);
    public List<FileVO> getFilesByBno(Long bno);
    public FileVO getTitleFile(Long bno, boolean isTitle);
    public List<FileVO> getContentFiles(Long bno, boolean isTitle);
    public void register(FileVO fileVO);
    public void update(FileVO fileVO);
    public void deleteByBNo(Long bno);

}
