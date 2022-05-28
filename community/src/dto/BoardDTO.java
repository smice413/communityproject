package dto;

import java.sql.Timestamp;

public class BoardDTO {
	
	private int num;
	private String head;
	private int star;
	private String fix;
	private String subject;
	private String id;
	private String content;
	private String board_file;
	private Timestamp writedate;
	private int readcount;
	private int bl_code;
	private String board_activate;
	private int re_count;
	private String bl_name;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public String getFix() {
		return fix;
	}
	public void setFix(String fix) {
		this.fix = fix;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getBoard_file() {
		return board_file;
	}
	public void setBoard_file(String board_file) {
		this.board_file = board_file;
	}
	public Timestamp getWritedate() {
		return writedate;
	}
	public void setWritedate(Timestamp writedate) {
		this.writedate = writedate;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public int getBl_code() {
		return bl_code;
	}
	public void setBl_code(int bl_code) {
		this.bl_code = bl_code;
	}
	public String getBoard_activate() {
		return board_activate;
	}
	public void setBoard_activate(String board_activate) {
		this.board_activate = board_activate;
	}
	public int getRe_count() {
		return re_count;
	}
	public void setRe_count(int re_count) {
		this.re_count = re_count;
	}
	public String getBl_name() {
		return bl_name;
	}
	public void setBl_name(String bl_name) {
		this.bl_name = bl_name;
	}
	
}
