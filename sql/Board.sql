create table tbl_board (
  bno int,
  title varchar(200) not null,
  content varchar(2000) not null,
  writer varchar(50) not null,
  regdate datetime default current_timestamp, 
  updatedate datetime default current_timestamp
);

alter table tbl_board add constraint pk_board 
primary key (bno);

ALTER table tbl_board MODIFY bno INT NOT NULL AUTO_INCREMENT;