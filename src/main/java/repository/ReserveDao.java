package repository;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import model.Booking;
import model.Business;
import model.Picture;
import model.Reserved;
import model.Review;
import model.Room;
import mybatis.ReservedMapperAnno;
import mybatis.RoomMapperAnno;
import util.MybatisConnection;

@Component
public class ReserveDao{
	
	public List<Room> roomList(Map map) {
			
		SqlSession sqlSession = MybatisConnection.getConnection();
		try {
			
			List<Room> list = sqlSession.getMapper(ReservedMapperAnno.class).roomList(map);
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return null;
	}
	
	
	public List<Reserved> reserveCheck(Map map) {
		
		SqlSession sqlSession = MybatisConnection.getConnection();
		try {
			
			List<Reserved> list = sqlSession.getMapper(ReservedMapperAnno.class).reserveCheck(map);
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return null;
	}
	
	
	public List<Room> overlapRoomList(Map map) {
		
		SqlSession sqlSession = MybatisConnection.getConnection();
		try {
			
			List<Room> list = sqlSession.getMapper(ReservedMapperAnno.class).overlapRoomList(map);
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return null;
	}
	
	
	public List<Review> businessReviewList(String bu_email){
		SqlSession sqlSession = MybatisConnection.getConnection();
		try {
			
			List<Review> list = sqlSession.getMapper(ReservedMapperAnno.class).businessReviewList(bu_email);
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return null;
	}
	
	
	public Business reviewAvgCountBusinessOne(String bu_email){
		SqlSession sqlSession = MybatisConnection.getConnection();
		try {
			
			Business b = sqlSession.getMapper(ReservedMapperAnno.class).reviewAvgCountBusinessOne(bu_email);
			return b;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return null;
	}
	
	
	public int insertBooking(Booking b) {
		
		SqlSession sqlSession = MybatisConnection.getConnection();
		try {
			
			int result = sqlSession.getMapper(ReservedMapperAnno.class).insertBooking(b);
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return 0;
	}
	
	
	public int insertReserved(Reserved r) {
		SqlSession sqlSession = MybatisConnection.getConnection();
		try {
			
			int result = sqlSession.getMapper(ReservedMapperAnno.class).insertReserved(r);
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return 0;
	}
	
	
	public int cancelReserveList(Booking b) {
		SqlSession sqlSession = MybatisConnection.getConnection();
		try {
			
			int result = sqlSession.getMapper(ReservedMapperAnno.class).cancelReserveList(b);
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return 0;
	}
	
}
