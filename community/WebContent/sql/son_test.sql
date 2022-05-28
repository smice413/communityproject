select * from tab; 
select * from seq;
select * from board order by board_file asc;
select * from boardlist;
select * from recom
select * from reply where re_activate='N'

select * from member;

update member set member_activate='Y' where id='ljpson'
update board set id='toto' where head='후기';
update board set board_activate='N' where num=9;
update board set subject='aaaa aaaaaaaa aaaaaaa aaaaaaa aaaaaaaaaa' where num=36;
update reply set re_ref=8 where re_num=8;

delete from board where content is null;
delete from reply;

insert into member values('test','1234','홍길동','010','1234','5678','test','naver.com',sysdate,'Y');
insert into boardlist values(board_seq.nextval,'bltest','게시판 테스트','Y','1234',sysdate,'Y','영화','test');
insert into reply values(reply_seq.nextval,'댓글의 댓글bltest 댓글 테스트bltest 댓글 테스트bltest bltest 댓글 테스트','test',10,1,0,sysdate,'18','Y');

select * from boardlist where bl_category='요리';
select * from boardlist where bl_category='리뷰'
select * from board where head='후기';
select * from board where board_file like '%gu%';

select * from board left outer join boardlist using(bl_code) where num=18;
select * from board left outer join reply using(num) where num=18;
//인기글 리스트
select * from ( select rownum rnum, b.* from 
( select * from board where board_activate='Y'
and head!='공지' and board_file is not null 
order by readcount desc) b ) 
where rnum >= 1 and rnum <= 4
//최신글 리스트
select * from ( select rownum rnum, b.* from 
( select * from board where board_activate='Y'
and head!='공지' and board_file is not null 
order by writedate desc) b ) 
where rnum >= 1 and rnum <= 10

select count(*) from reply where num=18

select * from ( select rownum rnum, b.* from 
( select * from reply order by re_num, re_writedate) b ) where rnum >= 1 and rnum <= 20

select * from reply where num=? order by re_num "+sort+" and re_writedate

select * from ( select rownum rnum, b.* from 
( select * from reply where num=40 order by re_ref asc, re_seq asc) b ) 
 where rnum >= 1 and rnum <= 20;