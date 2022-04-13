package model;

public class Picture {
	private int pic_num;
	private String location;
	
	public Picture() {}

	public Picture(int pic_num, String location) {
		super();
		this.pic_num = pic_num;
		this.location = location;
	}

	public int getPic_num() {
		return pic_num;
	}

	public void setPic_num(int pic_num) {
		this.pic_num = pic_num;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "{\"pic_num\":\"" + pic_num + "\", \"location\":\"" + location + "\"}";
	}

	
	
}
