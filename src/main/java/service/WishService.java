package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Business;
import repository.WishDao;

@Service
public class WishService {
	private final WishDao wishDao;
	
	@Autowired
	public WishService(WishDao wishDao) {
		this.wishDao = wishDao;
	}
	
	public int insertWish(String email, String bu_email) throws Exception {
		return wishDao.insertWish(email, bu_email);
	}
	
	public int deleteWish(String email, String bu_email) throws Exception {
		return wishDao.deleteWish(email, bu_email);
	}
	
	public List<Business> wishBusinessList(String email) throws Exception {
		return wishDao.wishBusienssList(email);
	}
	
}
