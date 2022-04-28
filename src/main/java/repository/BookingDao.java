package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Booking;
import model.Picture;
import model.Review;
import mybatis.BookingMapperAnno;
import mybatis.ReviewMapperAnno;

@Repository
public class BookingDao {
	
	private final SqlSession sqlSession;
	
	@Autowired
	public BookingDao(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
		System.out.println("BookingDao SqlSession On -> "+this.sqlSession);
	}
	
	public List<Booking> getBookingSelectList(String email) throws Exception {
		return sqlSession.getMapper(BookingMapperAnno.class).getBookingSelectList(email);
	}
	
	public List<Booking> selectBookingPicRevList(String bo_num) throws Exception {
		return sqlSession.getMapper(BookingMapperAnno.class).selectBookingPicRevList(bo_num);
	}

	public Booking getBookingSelectDetail(String email) throws Exception {
		return sqlSession.getMapper(BookingMapperAnno.class).getBookingSelectDetail(email);
	}

	public int updateBookingStatus(String bo_num) throws Exception {
		return sqlSession.getMapper(BookingMapperAnno.class).updateBookingStatus(bo_num);
	}

	public List<Picture> bookingPictureList(int ro_num) throws Exception {
		return sqlSession.getMapper(BookingMapperAnno.class).bookingPictureList(ro_num);
	}

	public int nextRevNum() throws Exception {
		return sqlSession.getMapper(ReviewMapperAnno.class).nextRevNum();
	}
	
	public int insertReview(Review review) throws Exception {
		return sqlSession.getMapper(ReviewMapperAnno.class).insertReview(review);
	}
}
