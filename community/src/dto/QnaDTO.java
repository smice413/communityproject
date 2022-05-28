package dto;

import java.sql.Timestamp;

public class QnaDTO {

	private int qna_num;
	private String id;
	private String qna_subject;
	private String qna_content;
	private String qna_file;
	private int qna_ref;
	private int qna_lev;
	private int qna_seq;
	private Timestamp qna_writedate;
	private String qna_activate;
	
	
	public int getQna_num() {
		return qna_num;
	}
	public void setQna_num(int qna_num) {
		this.qna_num = qna_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQna_subject() {
		return qna_subject;
	}
	public void setQna_subject(String qna_subject) {
		this.qna_subject = qna_subject;
	}
	public String getQna_content() {
		return qna_content;
	}
	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}
	public String getQna_file() {
		return qna_file;
	}
	public void setQna_file(String qna_file) {
		this.qna_file = qna_file;
	}
	public int getQna_ref() {
		return qna_ref;
	}
	public void setQna_ref(int qna_ref) {
		this.qna_ref = qna_ref;
	}
	public int getQna_lev() {
		return qna_lev;
	}
	public void setQna_lev(int qna_lev) {
		this.qna_lev = qna_lev;
	}
	public int getQna_seq() {
		return qna_seq;
	}
	public void setQna_seq(int qna_seq) {
		this.qna_seq = qna_seq;
	}
	public Timestamp getQna_writedate() {
		return qna_writedate;
	}
	public void setQna_writedate(Timestamp qna_writedate) {
		this.qna_writedate = qna_writedate;
	}
	public String getQna_activate() {
		return qna_activate;
	}
	public void setQna_activate(String qna_activate) {
		this.qna_activate = qna_activate;
	}
	
	
	
	
}
