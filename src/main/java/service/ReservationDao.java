package service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import model.Booking;
import model.Picture;
import mybatis.BookingMapperAnno;
import util.MybatisConnection;

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
	
	public Booking getBookingSelectDetail(String bo_num) {
		SqlSession sqlSession = MybatisConnection.getConnection();
		
		try {
			return sqlSession.getMapper(BookingMapperAnno.class).getBookingSelectDetail(bo_num);
			//getMapper는 BookingMapperAnno에 Mapping한 getBookingSelectDetail을 가져오겠다는 뜻
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
			//close 이유는 SqlSession의 연결을 끊기위해 사용
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
	
	public List<Picture> bookingPictureList(int ro_num){
		SqlSession sqlSession = MybatisConnection.getConnection();
		
		try {
			return sqlSession.getMapper(BookingMapperAnno.class).bookingPictureList(ro_num);
			//getMapper는 BookingMapperAnno에 Mapping한 getBookingSelectDetail을 가져오겠다는 뜻
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
			//close 이유는 SqlSession의 연결을 끊기위해 사용
		}
		return null;
	}
	
	/*
	 * 요약 *
	  1. status => 1인 bookingList를 검색
	  2. 검색된 booking의 체크아웃 날짜와 현재 날짜를 비교한다.
	  3. 체크아웃 날짜가 현재 날짜와 같거나, 이전이라면 status값을 3으로 변경한다.
	*/
	//메서드를 만드세요...
	//status값을 바꾸는 메서드를.....
	// 힌트 :1. sql문의 검색 조건을 email과 status 두개를 쓴다면... 검색이 가능하겠지?
	//		2. sql문의 조건(bo_num을 사용해야겠지?)을 in 구문을 사용하여 한번에 바꿀수 있어요....  구글 검색검색
	/*
	 반환형 메서드이름(매개변수){
	 	//1. bookingList를 받을 객체 생성
		//2. 1번에서 생성한 객체에 bookingList를 담는다.
		//3. for(Booking b : bookingList){
		 			(여기에 로직~~)
		  }문으로 반복하고  if문을 이용하여 체크아웃 날짜를 확인하고, 현재날짜와 비교한다.
		 	(필수는 아님)3-1 util패키지에 class를 만들어서 현재날짜와 체크아웃 날짜를 비교하여 true, false를 반환하는 메서드를 만들어서... 사용할듯... 
		//4. 3번을 수행한 후, 날짜비교(결과값 true, false)에 따라 status값을 3으로 변경한다.
	 
	 }
	 */
}
