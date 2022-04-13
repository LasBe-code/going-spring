package model;


public class Booking {
	private String bo_num;
	private String email, payment;
	private String price;
	private String bu_title;
	private String ro_name;
	private String checkin, checkout;
	private int ro_num;
	private String reg_date; // 예약한 날짜확인 
	private int status;//예약 상태 확인
	private int ro_count;

	public Booking(){}

	public Booking(String bo_num, String email, String payment, String price, String bu_title, String ro_name,
			String checkin, String checkout, int ro_num, String reg_date, int status, int ro_count) {
		super();
		this.bo_num = bo_num;
		this.email = email;
		this.payment = payment;
		this.price = price;
		this.bu_title = bu_title;
		this.ro_name = ro_name;
		this.checkin = checkin;
		this.checkout = checkout;
		this.ro_num = ro_num;
		this.reg_date = reg_date;
		this.status = status;
		this.ro_count = ro_count;
	}

	public String getBo_num() {
		return bo_num;
	}

	public void setBo_num(String bo_num) {
		this.bo_num = bo_num;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getBu_title() {
		return bu_title;
	}

	public void setBu_title(String bu_title) {
		this.bu_title = bu_title;
	}

	public String getRo_name() {
		return ro_name;
	}

	public void setRo_name(String ro_name) {
		this.ro_name = ro_name;
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

	public int getRo_num() {
		return ro_num;
	}

	public void setRo_num(int ro_num) {
		this.ro_num = ro_num;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRo_count() {
		return ro_count;
	}

	public void setRo_count(int ro_count) {
		this.ro_count = ro_count;
	}
	
}
