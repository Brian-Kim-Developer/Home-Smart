package com.project.project6.config;

import com.project.project6.file.FileDAO;
import com.project.project6.file.FileDAOJpa;
import com.project.project6.service.FileService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileConfig {

    @Bean
    public FileDAO fileDAO() {
        return new FileDAOJpa();
    }

    @Bean
    public FileService fileService() {
        FileService fileService = new FileService();
        fileService.setFileDAO(fileDAO());
        return fileService;
    }

}
