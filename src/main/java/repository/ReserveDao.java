package repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Booking;
import model.Business;
import model.Picture;
import model.Reserved;
import model.Review;
import model.Room;
import mybatis.ReservedMapperAnno;
import mybatis.RoomMapperAnno;

@SuppressWarnings("unchecked")
@Repository
public class ReserveDao{
	Map map = new HashMap();
	private final SqlSession sqlSession;
	
	@Autowired
	public ReserveDao(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
		System.out.println("ReserveDao SqlSession On -> "+this.sqlSession);
	}
	
	public List<Room> roomList(Map map) throws Exception {
		return sqlSession.getMapper(ReservedMapperAnno.class).roomList(map);
	}
	
	public Room roomSelectOne(int ro_num) throws Exception{
		return sqlSession.getMapper(RoomMapperAnno.class).selectRoom(ro_num);
	}
	
	public List<Reserved> reserveCheck(Map map) throws Exception {
		return sqlSession.getMapper(ReservedMapperAnno.class).reserveCheck(map);
	}
	
	
	public List<Room> overlapRoomList(String bu_email, String ro_count,
									String checkin, String checkout) throws Exception {
		map.clear();
		map.put("bu_email", bu_email); 	map.put("ro_count", ro_count);
		map.put("checkin", checkin); 	map.put("checkout", checkout);
		return sqlSession.getMapper(ReservedMapperAnno.class).overlapRoomList(map);
	}
	
	
	public List<Review> businessReviewList(String bu_email) throws Exception {
		return sqlSession.getMapper(ReservedMapperAnno.class).businessReviewList(bu_email);
	}
	
	public List<Picture> getPicList(int pic_num) throws Exception {
		return sqlSession.getMapper(ReservedMapperAnno.class).sbPicList(pic_num);
	}
	public Business reviewAvgCountBusinessOne(String bu_email) throws Exception {
		return sqlSession.getMapper(ReservedMapperAnno.class).reviewAvgCountBusinessOne(bu_email);
	}
	
	
	public int insertBooking(Booking b) throws Exception {
		return sqlSession.getMapper(ReservedMapperAnno.class).insertBooking(b);
	}
	
	
	public int insertReserved(Reserved r) throws Exception {
		return sqlSession.getMapper(ReservedMapperAnno.class).insertReserved(r);
	}
	
	
	public int cancelReserveList(Booking b) throws Exception {
			return sqlSession.getMapper(ReservedMapperAnno.class).cancelReserveList(b);
	}
	
}
