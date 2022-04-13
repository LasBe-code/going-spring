package model;


public class Review {
	private int rev_num, bo_num;
	private String email, content;
	private String checkin, checkout;
	private int pic_num;
	
	public Review() {}

	public Review(int rev_num, int bo_num, String email, String content, String checkin, String checkout, int pic_num) {
		super();
		this.rev_num = rev_num;
		this.bo_num = bo_num;
		this.email = email;
		this.content = content;
		this.checkin = checkin;
		this.checkout = checkout;
		this.pic_num = pic_num;
	}

	public int getRev_num() {
		return rev_num;
	}

	public void setRev_num(int rev_num) {
		this.rev_num = rev_num;
	}

	public int getBo_num() {
		return bo_num;
	}

	public void setBo_num(int bo_num) {
		this.bo_num = bo_num;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCheckin() {
		return checkin;
	}

	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}

	public int getPic_num() {
		return pic_num;
	}

	public void setPic_num(int pic_num) {
		this.pic_num = pic_num;
	}

	@Override
	public String toString() {
		return "{\"rev_num\":\"" + rev_num + "\", \"bo_num\":\"" + bo_num + "\", \"email\":\"" + email
				+ "\", \"content\":\"" + content + "\", \"checkin\":\"" + checkin + "\", \"checkout\":\"" + checkout
				+ "\", \"pic_num\":\"" + pic_num + "\"}";
	}

	
	
}
