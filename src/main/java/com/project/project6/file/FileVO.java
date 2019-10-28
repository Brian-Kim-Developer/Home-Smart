package com.project.project6.file;

import com.project.project6.board.BoardVO;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "file")
@Data
public class FileVO {

    public FileVO() {};
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fno;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "file_ori_name")
    private String fileOriName;
    @Column(name = "file_type")
    private String fileType;
    @Lob
    @Column(name = "file_data")
    private Blob fileData;
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "is_title")
    private boolean isTitle;
    private Long bno;

    public FileVO(String fileName, String fileOriName, String fileType, Blob fileData, boolean isTitle, Long bno) {
        this.fileName = fileName;
        this.fileOriName = fileOriName;
        this.fileType = fileType;
        this.fileData = fileData;
        this.isTitle = isTitle;
        this.bno = bno;
    }

}
