package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Review;
import repository.ReserveDao;

@Service
public class ReserveService {
	
	private final ReserveDao reserveDao;
	
	@Autowired
	public ReserveService(ReserveDao reserveDao) {
		this.reserveDao = reserveDao;
	}

	public List<Review> businessReviewList(String bu_email) {
		return reserveDao.businessReviewList(bu_email);
	}

}
