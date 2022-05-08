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
	
	// 고객 예약 내역 + 예약 객실의 첫번째 사진 + 예약에 대한 리뷰 여부
	public List<Booking> selectBookingPicRevList(String email) throws Exception {
		return sqlSession.getMapper(BookingMapperAnno.class).selectBookingPicRevList(email);
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

	public List<Review> businessReviewList(String bu_email) {
		return sqlSession.getMapper(BookingMapperAnno.class).businessReviewList(bu_email);
	}
}
