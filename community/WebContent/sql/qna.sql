create table qna(qna_num number primary key,
				 id varchar2(20) references member2(id),
				 qna_subject varchar2(100),
				 qna_content varchar2(4000),
				 qna_file varchar2(50),
				 qna_ref number,
				 qna_lev number,
				 qna_seq number,
				 qna_writedate timestamp,
				 qna_activate varchar2(10));

select * from tab;
select * from qna;

insert into qna values(qna_seq.nextval,'','문의글','문의글 내용','',qna_seq.nextval,0,0,sysdate,'Y');
update qna set qna_activate = 'N' where qna_num = 130;

select * from qna where qna_activate = 'Y';


create sequence qna_seq
		start with 1
		increment by 1
		nocache;