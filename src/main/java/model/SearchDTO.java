package model;

public class SearchDTO {
	String bu_address;
	String checkin;
	String checkout;
	String ro_count;
	String bu_id;
	String lowprice;
	String highprice;
	public SearchDTO(String bu_address, String checkin, String checkout, String ro_count, String bu_id, String lowprice, String highprice) {
		super();
		this.bu_address = bu_address;
		this.checkin = checkin;
		this.checkout = checkout;
		this.ro_count = ro_count;
		this.bu_id = bu_id;
		this.lowprice = lowprice;
		this.highprice = highprice;
	}
	public String getBu_address() {
		return bu_address;
	}
	public void setBu_address(String bu_address) {
		this.bu_address = bu_address;
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
	public String getRo_count() {
		return ro_count;
	}
	public void setRo_count(String ro_count) {
		this.ro_count = ro_count;
	}
	public String getBu_id() {
		return bu_id;
	}
	public void setBu_id(String bu_id) {
		this.bu_id = bu_id;
	}
	public String getLowprice() {
		return lowprice;
	}
	public void setLowprice(String lowprice) {
		this.lowprice = lowprice;
	}
	public String getHighprice() {
		return highprice;
	}
	public void setHighprice(String highprice) {
		this.highprice = highprice;
	}
	@Override
	public String toString() {
		return " {\"bu_address\":\"" + bu_address + "\", \"checkin\":\"" + checkin + "\", \"checkout\":\"" + checkout
				+ "\", \"ro_count\":\"" + ro_count + "\", \"bu_id\":\"" + bu_id + "\", \"lowprice\":\"" + lowprice
				+ "\", \"highprice\":\"" + highprice + "\"}";
	}
}