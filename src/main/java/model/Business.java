package model;

public class Business {
	private String bu_email, bu_password, bu_tel, bu_name, bu_address;
	private String bu_id;
	private String bu_title;
	private int pic_num;
	
	public Business() {}

	public Business(String bu_email, String bu_password, String bu_tel, String bu_name, String bu_address, String bu_id,
			String bu_title, int pic_num) {
		super();
		this.bu_email = bu_email;
		this.bu_password = bu_password;
		this.bu_tel = bu_tel;
		this.bu_name = bu_name;
		this.bu_address = bu_address;
		this.bu_id = bu_id;
		this.bu_title = bu_title;
		this.pic_num = pic_num;
	}

	public String getBu_email() {
		return bu_email;
	}

	public void setBu_email(String bu_email) {
		this.bu_email = bu_email;
	}

	public String getBu_password() {
		return bu_password;
	}

	public void setBu_password(String bu_password) {
		this.bu_password = bu_password;
	}

	public String getBu_tel() {
		return bu_tel;
	}

	public void setBu_tel(String bu_tel) {
		this.bu_tel = bu_tel;
	}

	public String getBu_name() {
		return bu_name;
	}

	public void setBu_name(String bu_name) {
		this.bu_name = bu_name;
	}

	public String getBu_address() {
		return bu_address;
	}

	public void setBu_address(String bu_address) {
		this.bu_address = bu_address;
	}

	public String getBu_id() {
		return bu_id;
	}

	public void setBu_id(String bu_id) {
		this.bu_id = bu_id;
	}

	public String getBu_title() {
		return bu_title;
	}

	public void setBu_title(String bu_title) {
		this.bu_title = bu_title;
	}

	public int getPic_num() {
		return pic_num;
	}

	public void setPic_num(int pic_num) {
		this.pic_num = pic_num;
	}

	@Override
	public String toString() {
		return "{\"bu_email\":\"" + bu_email + "\", \"bu_password\":\"" + bu_password + "\", \"bu_tel\":\"" + bu_tel
				+ "\", \"bu_name\":\"" + bu_name + "\", \"bu_address\":\"" + bu_address + "\", \"bu_id\":\"" + bu_id
				+ "\", \"bu_title\":\"" + bu_title + "\", \"pic_num\":\"" + pic_num + "\"}";
	}
	
	
}
