package model;

public class Member {
	private String email, password, tel, name, admin_check;
	
	public Member() {}
	public Member(String email, String password, String tel, String name) {
		super();
		this.email = email;
		this.password = password;
		this.tel = tel;
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	// Admin 코드 setter 보안문제로 설정 x
	public String getAdmin_check() {
		return admin_check;
	}
	@Override
	public String toString() {
		return "{\"email\":\"" + email + "\", \"password\":\"" + password + "\", \"tel\":\"" + tel + "\", \"name\":\""
				+ name + "\"}";
	}
	
	
	
}
