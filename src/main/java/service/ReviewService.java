package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.Review;
import repository.ReviewDao;
import util.DateParse;

@Service
public class ReviewService {
	private final ReviewDao reviewDao;
	
	@Autowired
	public ReviewService(ReviewDao reviewDao) {
		this.reviewDao = reviewDao;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public int writeReview(Review review) throws Exception {
		review.setRev_num(reviewDao.nextRevNum());
		review.setReview_date(DateParse.getTodayPlus(0));
		return reviewDao.insertReview(review);
	}
	
	public int deleteReview(int rev_num) throws Exception {
		return reviewDao.deleteReview(rev_num);
	}
	
	public int reportCancel(int rev_num) throws Exception {
		return reviewDao.reportCancel(rev_num);
	}
	
	public List<Review> myReivew(String email) throws Exception{
		return reviewDao.myReview(email);
	}
	
	public List<Review> businessReviewList(String bu_email) throws Exception {
		return reviewDao.businessReviewList(bu_email);
	}

}
