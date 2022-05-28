package dto;

import java.sql.Timestamp;

public class MemberDTO {
	
		private String id;			
		private String passwd;
		private String name;
		private String phone1;
		private String phone2;
		private String phone3;
		private String mailid;
		private String domain;
		private Timestamp regdate;
		private String member_activate;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getPasswd() {
			return passwd;
		}
		public void setPasswd(String passwd) {
			this.passwd = passwd;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPhone1() {
			return phone1;
		}
		public void setPhone1(String phone1) {
			this.phone1 = phone1;
		}
		public String getPhone2() {
			return phone2;
		}
		public void setPhone2(String phone2) {
			this.phone2 = phone2;
		}
		public String getPhone3() {
			return phone3;
		}
		public void setPhone3(String phone3) {
			this.phone3 = phone3;
		}
		public String getMailid() {
			return mailid;
		}
		public void setMailid(String mailid) {
			this.mailid = mailid;
		}
		public String getDomain() {
			return domain;
		}
		public void setDomain(String domain) {
			this.domain = domain;
		}
		public Timestamp getRegdate() {
			return regdate;
		}
		public void setRegdate(Timestamp regdate) {
			this.regdate = regdate;
		}
		public String getMember_activate() {
			return member_activate;
		}
		public void setMember_activate(String member_activate) {
			this.member_activate = member_activate;
		}
	
		
}