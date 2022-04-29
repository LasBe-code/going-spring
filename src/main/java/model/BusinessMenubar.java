package model;

public class BusinessMenubar {

	private int menu_num;
	private String menu_name;
	private String menu_url;
	private int status;

	public BusinessMenubar() {}

	public BusinessMenubar(int menu_num, String menu_name, String menu_url, int status) {
		super();
		this.menu_num = menu_num;
		this.menu_name = menu_name;
		this.menu_url = menu_url;
		this.status = status;
	}

	public int getMenu_num() {
		return menu_num;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public String getMenu_url() {
		return menu_url;
	}

	public int getStatus() {
		return status;
	}

	public void setMenu_num(int menu_num) {
		this.menu_num = menu_num;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BusinessMenubar [menu_num=" + menu_num + ", menu_name=" + menu_name + ", menu_url=" + menu_url
				+ ", status=" + status + "]";
	}
	
	
}
