package repository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import model.Booking;
import model.Business;
import model.Picture;
import model.Room;
import mybatis.RoomMapperAnno;

@Component
public class RoomDao {
	private final SqlSession sqlSession;
	
	@Autowired
	public RoomDao(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}

	public List<Room> roomList(String bu_email) throws Exception {	
		return sqlSession.getMapper(RoomMapperAnno.class).roomList(bu_email);
	}
	
	public int nextPicNum() throws Exception  {
		return sqlSession.getMapper(RoomMapperAnno.class).nextPicNum();
	}
	
	public int nextRoNum()  throws Exception {
		return sqlSession.getMapper(RoomMapperAnno.class).nextRoNum();
	}

	public int insertRoom(Room room) throws Exception  {
		return sqlSession.getMapper(RoomMapperAnno.class).insertRoom(room);
	}

	public int insertPicture(Picture p) throws Exception  {
		return sqlSession.getMapper(RoomMapperAnno.class).insertPicture(p);
	}

	public Room selectRoom(int ro_num) throws Exception  {
		return sqlSession.getMapper(RoomMapperAnno.class).selectRoom(ro_num);
	}
	
	
	public List<Picture> selectPic(int pic_num) throws Exception {
		return sqlSession.getMapper(RoomMapperAnno.class).selectPic(pic_num);
		
	}

	public int updatePicture(Picture p)  throws Exception {
		return sqlSession.getMapper(RoomMapperAnno.class).updatePicture(p);
	}

	public int updateRoom(Room room)  throws Exception {
		return sqlSession.getMapper(RoomMapperAnno.class).updateRoom(room);
	}


	public int deleteRoom(Map<String, Object> map)  throws Exception {
		return sqlSession.getMapper(RoomMapperAnno.class).deleteRoom(map);
	}

	public Business selectBu(String bu_email) throws Exception  {
		return sqlSession.getMapper(RoomMapperAnno.class).selectBu(bu_email);
	}


	public List<Booking> selectBkList(Map<String, Object> map) throws Exception  {
		return sqlSession.getMapper(RoomMapperAnno.class).selectBkList(map);
	}
	
	public List<Booking> searchStatus(Map<String, Object> map) throws Exception  {
		return sqlSession.getMapper(RoomMapperAnno.class).searchStatus(map);
	}
	
	
	public List<Booking> searchName(Map<String, Object> map) throws Exception  {
		return sqlSession.getMapper(RoomMapperAnno.class).searchName(map);
	}

	public Business selectBusiness(String bu_email) throws Exception  {
		return sqlSession.getMapper(RoomMapperAnno.class).selectBusiness(bu_email);
	}

	public List<Picture> selectLocation(int pic_num) throws Exception  {
		return sqlSession.getMapper(RoomMapperAnno.class).selectLocation(pic_num);
	}

	public int deleteLocation(int pic_num) throws Exception  {
		return sqlSession.getMapper(RoomMapperAnno.class).deleteLocation(pic_num);
	}

	public Booking selectSales(Map<String, Object> map) throws Exception  {
		return sqlSession.getMapper(RoomMapperAnno.class).selectSales(map);
	}

	public int countBoard(Map<String, Object> map)  throws Exception {
		return sqlSession.getMapper(RoomMapperAnno.class).countBoard(map);
	}

	public int countBoardStatus(Map<String, Object> map) throws Exception  {
		return sqlSession.getMapper(RoomMapperAnno.class).countBoardStatus(map);
	}

	public int countBoardSearchName(Map<String, Object> map) throws Exception  {
		return sqlSession.getMapper(RoomMapperAnno.class).countBoardSearchName(map);
	}

	public List<Business> addressList(Map<String, Object> map) throws Exception  {
		return sqlSession.getMapper(RoomMapperAnno.class).addressList(map);
	}

	public List<Booking> selectNotCheckin(Map<String, Object> map) throws Exception  {
		return sqlSession.getMapper(RoomMapperAnno.class).selectNotCheckin(map);
	}

	public List<Booking> selectcheckinOk(Map<String, Object> map) throws Exception  {
		return sqlSession.getMapper(RoomMapperAnno.class).selectcheckinOk(map);
	}

	public int updateTodayCheckin(Map<String, Object> map)  throws Exception {
		return sqlSession.getMapper(RoomMapperAnno.class).updateTodayCheckin(map);
	}

	public List<Booking> selectNotCheckOut(Map<String, Object> map) throws Exception  {
		return sqlSession.getMapper(RoomMapperAnno.class).selectNotCheckOut(map);
	}

	public List<Booking> selectcheckOutOk(Map<String, Object> map)  throws Exception {
		return sqlSession.getMapper(RoomMapperAnno.class).selectcheckOutOk(map);
	}

	public int updateAndDeleteTodayCheckOut(Map<String, Object> map)  throws Exception {
		return sqlSession.getMapper(RoomMapperAnno.class).countReserved(map);
	}

	public Room getRo_name(Map<String, Object> map) {
		return sqlSession.getMapper(RoomMapperAnno.class).getRo_name(map);
	}

	public int updateReply(Map<String, Object> map) {
		return sqlSession.getMapper(RoomMapperAnno.class).updateReply(map);
	}

	public int deleteReply(Integer rev_num) {
		return sqlSession.getMapper(RoomMapperAnno.class).deleteReply(rev_num);
	}

	public int reviewApproval(Integer rev_num) {
		return sqlSession.getMapper(RoomMapperAnno.class).reviewApproval(rev_num);
	}

	public int reportCancle(Integer rev_num) {
		return sqlSession.getMapper(RoomMapperAnno.class).reportCancle(rev_num);
	}
}