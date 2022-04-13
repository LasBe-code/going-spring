package model;


public class Room {
	private int ro_num;
	private String bu_email;
	private String ro_count;
	private String ro_name;
	private String ro_price;
	private String checkin;
	private String checkout;
	private String ro_info;
	private String location;
	private int pic_num;
	
	public Room() {}

	public Room(int ro_num, String bu_email, String ro_count, String ro_name, String ro_price, String checkin,
			String checkout, String ro_info, String location, int pic_num) {
		super();
		this.ro_num = ro_num;
		this.bu_email = bu_email;
		this.ro_count = ro_count;
		this.ro_name = ro_name;
		this.ro_price = ro_price;
		this.checkin = checkin;
		this.checkout = checkout;
		this.ro_info = ro_info;
		this.location = location;
		this.pic_num = pic_num;
	}

	public int getRo_num() {
		return ro_num;
	}

	public String getBu_email() {
		return bu_email;
	}

	public String getRo_count() {
		return ro_count;
	}

	public String getRo_name() {
		return ro_name;
	}

	public String getRo_price() {
		return ro_price;
	}

	public String getCheckin() {
		return checkin;
	}

	public String getCheckout() {
		return checkout;
	}

	public String getRo_info() {
		return ro_info;
	}

	public String getLocation() {
		return location;
	}

	public int getPic_num() {
		return pic_num;
	}

	public void setRo_num(int ro_num) {
		this.ro_num = ro_num;
	}

	public void setBu_email(String bu_email) {
		this.bu_email = bu_email;
	}

	public void setRo_count(String ro_count) {
		this.ro_count = ro_count;
	}

	public void setRo_name(String ro_name) {
		this.ro_name = ro_name;
	}

	public void setRo_price(String ro_price) {
		this.ro_price = ro_price;
	}

	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}

	public void setRo_info(String ro_info) {
		this.ro_info = ro_info;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setPic_num(int pic_num) {
		this.pic_num = pic_num;
	}

	@Override
	public String toString() {
		return "Room [ro_num=" + ro_num + ", bu_email=" + bu_email + ", ro_count=" + ro_count + ", ro_name=" + ro_name
				+ ", ro_price=" + ro_price + ", checkin=" + checkin + ", checkout=" + checkout + ", ro_info=" + ro_info
				+ ", location=" + location + ", pic_num=" + pic_num + "]";
	}

	
	
	
}
