package model;

public class Wish {
	private String email, bu_email;

	public Wish(String email, String bu_email) {
		super();
		this.email = email;
		this.bu_email = bu_email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBu_email() {
		return bu_email;
	}

	public void setBu_email(String bu_email) {
		this.bu_email = bu_email;
	}

	@Override
	public String toString() {
		return "{\"email\":\"" + email + "\", \"bu_email\":\"" + bu_email + "\"}";
	}
	
}
