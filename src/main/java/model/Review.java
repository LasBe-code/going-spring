package model;


public class Review {
	
	private int rev_num, score;
	private String bo_num, email, content, review_date;
	
	private String ro_name;
	
	public Review() {}
	
	public Review(int rev_num, int score, String bo_num, 
			String email, String content, String review_date) {
		super();
		this.rev_num = rev_num;
		this.score = score;
		this.bo_num = bo_num;
		this.email = email;
		this.content = content;
		this.review_date = review_date;
	}

	public Review(int rev_num, String bo_num, int score, String email, String content, String review_date, String ro_name) {
		super();
		this.rev_num = rev_num;
		this.bo_num = bo_num;
		this.score = score;
		this.email = email;
		this.content = content;
		this.review_date = review_date;
		this.ro_name = ro_name;
	}

	public int getRev_num() {
		return rev_num;
	}

	public void setRev_num(int rev_num) {
		this.rev_num = rev_num;
	}

	public String getBo_num() {
		return bo_num;
	}

	public void setBo_num(String bo_num) {
		this.bo_num = bo_num;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
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

	public String getReview_date() {
		return review_date;
	}

	public void setReview_date(String review_date) {
		this.review_date = review_date;
	}
	
	public String getRo_name() {
		return ro_name;
	}

	public void setRo_name(String ro_name) {
		this.ro_name = ro_name;
	}

	@Override
	public String toString() {
		return "{\"rev_num\":\"" + rev_num + "\", \"score\":\"" + score + "\", \"bo_num\":\"" + bo_num
				+ "\", \"email\":\"" + email + "\", \"content\":\"" + content + "\", \"review_date\":\"" + review_date
				+ "\", \"ro_name\":\"" + ro_name + "\"}\n";
	}

	
}
