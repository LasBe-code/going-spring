package service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import model.Booking;
import model.Picture;
import model.Review;
import mybatis.BookingMapperAnno;
import mybatis.ReviewMapperAnno;
import util.MybatisConnection;

@Component
public class ReservationDao {

	public List<Booking> getBookingSelectList(String email) {
		SqlSession sqlSession = MybatisConnection.getConnection();
		try {
			return sqlSession.getMapper(BookingMapperAnno.class).getBookingSelectList(email);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		return null;
	}
	
	public List<Booking> selectBookingPicRevList(String bo_num) {
		SqlSession sqlSession = MybatisConnection.getConnection();

		try {
			return sqlSession.getMapper(BookingMapperAnno.class).selectBookingPicRevList(bo_num);
			// getMapper는 BookingMapperAnno에 Mapping한 getBookingSelectDetail을 가져오겠다는 뜻

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
			// close 이유는 SqlSession의 연결을 끊기위해 사용
		}
		return null;
	}

	public Booking getBookingSelectDetail(String email) {
		SqlSession sqlSession = MybatisConnection.getConnection();

		try {
			return sqlSession.getMapper(BookingMapperAnno.class).getBookingSelectDetail(email);
			// getMapper는 BookingMapperAnno에 Mapping한 getBookingSelectDetail을 가져오겠다는 뜻

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
			// close 이유는 SqlSession의 연결을 끊기위해 사용
		}
		return null;
	}

	public void updateBookingStatus(String bo_num) {
		SqlSession sqlSession = MybatisConnection.getConnection();

		try {
			sqlSession.getMapper(BookingMapperAnno.class).updateBookingStatus(bo_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
	}

	public List<Picture> bookingPictureList(int ro_num) {
		SqlSession sqlSession = MybatisConnection.getConnection();

		try {
			return sqlSession.getMapper(BookingMapperAnno.class).bookingPictureList(ro_num);
			// getMapper는 BookingMapperAnno에 Mapping한 getBookingSelectDetail을 가져오겠다는 뜻

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
			// close 이유는 SqlSession의 연결을 끊기위해 사용
		}
		return null;
	}

	public int nextRevNum() {
		SqlSession sqlSession = MybatisConnection.getConnection();

		try {
			return sqlSession.getMapper(ReviewMapperAnno.class).nextRevNum();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		return 0;
	}
	
	public int insertReview(Review review) {
		SqlSession sqlSession = MybatisConnection.getConnection();

		try {
			return sqlSession.getMapper(ReviewMapperAnno.class).insertReview(review);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		return 0;
	}
}
