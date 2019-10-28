package com.project.project6.controller;

import com.project.project6.file.FileVO;
import com.project.project6.service.FileService;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

@RestController
public class ImageController {

    private FileService fileService;

    @Autowired
    public void setImageService(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping("/showImage")
    @Synchronized
    public void showImage(@RequestParam("imgId") Long imgId
            , HttpServletResponse response) throws Exception  {
        System.out.println("image id = " + imgId);
        InputStream is = null;
        byte[] bytes;

        /* DB의 BLOB 타입의 내용을 가져와서 bytes 변수에 담아보자. */
        FileVO file = fileService.getFileByFNo(imgId);
        bytes = file.getFileData().getBytes(1, (int)file.getFileData().length());

        /* 저는 jpg 파일로 고정이라 이렇게 했지만 여러분은 타입을 얻어와야 한다. */
        String content_type = file.getFileType();
        response.setContentType(content_type);  // Content Type Set

        /* String --> InputStream 타입으로 변환 */
        is = new ByteArrayInputStream(bytes);

        /* 이제 OutputStream 으로 출력해보자*/
        ServletOutputStream os = response.getOutputStream();

        int binaryRead;

        while ((binaryRead = is.read()) != -1)	{
            os.write(binaryRead);
        }

    }


}
