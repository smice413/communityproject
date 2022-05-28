package dto;

import java.sql.Timestamp;

public class BoardListDTO {
     private int bl_code;
     private String bl_name;
     private String bl_exp;
     private String bl_private;
     private String bl_passwd;
     private Timestamp bl_regdate;
     private String bl_activate;
     private String bl_category;
     private String id;
     
	public int getBl_code() {
		return bl_code;
	}
	public void setBl_code(int bl_code) {
		this.bl_code = bl_code;
	}
	public String getBl_name() {
		return bl_name;
	}
	public void setBl_name(String bl_name) {
		this.bl_name = bl_name;
	}
	public String getBl_exp() {
		return bl_exp;
	}
	public void setBl_exp(String bl_exp) {
		this.bl_exp = bl_exp;
	}
	public String getBl_private() {
		return bl_private;
	}
	public void setBl_private(String bl_private) {
		this.bl_private = bl_private;
	}
	public String getBl_passwd() {
		return bl_passwd;
	}
	public void setBl_passwd(String bl_passwd) {
		this.bl_passwd = bl_passwd;
	}
	public Timestamp getBl_regdate() {
		return bl_regdate;
	}
	public void setBl_regdate(Timestamp bl_regdate) {
		this.bl_regdate = bl_regdate;
	}
	public String getBl_activate() {
		return bl_activate;
	}
	public void setBl_activate(String bl_activate) {
		this.bl_activate = bl_activate;
	}
	public String getBl_category() {
		return bl_category;
	}
	public void setBl_category(String bl_category) {
		this.bl_category = bl_category;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
     
     
}
