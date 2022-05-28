package dto;

import java.sql.Timestamp;

public class ReplyDTO {

	private int re_num;
	private String re_content;
	private String id;
	private int re_ref;
	private int re_lev;
	private int re_seq;
	private Timestamp re_writedate;
	private int num;
	private String re_activate;
	
	public int getRe_num() {
		return re_num;
	}
	public void setRe_num(int re_num) {
		this.re_num = re_num;
	}
	public String getRe_content() {
		return re_content;
	}
	public void setRe_content(String re_content) {
		this.re_content = re_content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getRe_ref() {
		return re_ref;
	}
	public void setRe_ref(int re_ref) {
		this.re_ref = re_ref;
	}
	public int getRe_lev() {
		return re_lev;
	}
	public void setRe_lev(int re_lev) {
		this.re_lev = re_lev;
	}
	public int getRe_seq() {
		return re_seq;
	}
	public void setRe_seq(int re_seq) {
		this.re_seq = re_seq;
	}
	public Timestamp getRe_writedate() {
		return re_writedate;
	}
	public void setRe_writedate(Timestamp re_writedate) {
		this.re_writedate = re_writedate;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getRe_activate() {
		return re_activate;
	}
	public void setRe_activate(String re_activate) {
		this.re_activate = re_activate;
	}
}
