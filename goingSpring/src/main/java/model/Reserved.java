package model;

public class Reserved {
	private int ro_num;
	private String re_date;
	
	public Reserved() {}

	public Reserved(int ro_num, String re_date) {
		super();
		this.ro_num = ro_num;
		this.re_date = re_date;
	}

	public int getRo_num() {
		return ro_num;
	}

	public void setRo_num(int ro_num) {
		this.ro_num = ro_num;
	}

	public String getRe_date() {
		return re_date;
	}

	public void setRe_date(String re_date) {
		this.re_date = re_date;
	}

	@Override
	public String toString() {
		return "{\"ro_num\":\"" + ro_num + "\", \"re_date\":\"" + re_date + "\"}";
	}

	
	
}
