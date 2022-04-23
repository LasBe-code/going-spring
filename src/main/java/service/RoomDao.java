package service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import model.Booking;
import model.Business;
import model.Picture;
import model.Reserved;
import model.Room;
import mybatis.RoomMapperAnno;
import util.MybatisConnection;

@Component
public class RoomDao {


	public List<Room> roomList(String bu_email) {
		
		SqlSession sqlSession = MybatisConnection.getConnection();
		try {
			
			List<Room> list = sqlSession.getMapper(RoomMapperAnno.class).roomList(bu_email);
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return null;
	}
	
	public String picMain(Map<String, Object> map) {
		
		SqlSession sqlSession = MybatisConnection.getConnection();
		
		try {
			
			String picMain = sqlSession.getMapper(RoomMapperAnno.class).picMain(map);
			return picMain;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return null;
	}

	public int nextPicNum() {
		
		SqlSession sqlSession = MybatisConnection.getConnection();
		
		try {
			
			int nextNum = sqlSession.getMapper(RoomMapperAnno.class).nextPicNum();
			return nextNum;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return 0;
	}
	
	public int nextRoNum() {
		
		SqlSession sqlSession = MybatisConnection.getConnection();
		
		try {
			
			int nextNum = sqlSession.getMapper(RoomMapperAnno.class).nextRoNum();
			return nextNum;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return 0;
	}

	public int insertRoom(Room room) {

		SqlSession sqlSession = MybatisConnection.getConnection();
		
		try {
			
			int nextNum = sqlSession.getMapper(RoomMapperAnno.class).insertRoom(room);
			return nextNum;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return 0;
	}

	public int insertPicture(Picture p) {
		
		
		SqlSession sqlSession = MybatisConnection.getConnection();
		
		try {
			
			return sqlSession.getMapper(RoomMapperAnno.class).insertPicture(p);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		return 0;
	}

	public Room selectRoom(int ro_num) {

		SqlSession sqlSession = MybatisConnection.getConnection();
		
		try {
			
			Room room = sqlSession.getMapper(RoomMapperAnno.class).selectRoom(ro_num);
			return room;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		
		return null;
	}
	
	
	public List<Picture> selectPic(int pic_num) {
		
		SqlSession sqlSession = MybatisConnection.getConnection();
		
		try {
			
			List<Picture> pic = sqlSession.getMapper(RoomMapperAnno.class).selectPic(pic_num);
			return pic;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		
		return null;
		
		
	}

	public int updatePicture(Picture p) {
		
		
		SqlSession sqlSession = MybatisConnection.getConnection();
		
		try {
			
			int updatePic = sqlSession.getMapper(RoomMapperAnno.class).updatePicture(p);
			return updatePic;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return 0;
	}

	public int updateRoom(Room room) {
		SqlSession sqlSession = MybatisConnection.getConnection();
		

		try {
			
			int updateRoom = sqlSession.getMapper(RoomMapperAnno.class).updateRoom(room);
			return updateRoom;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return 0;
		
		
	}


	public int deleteRoom(Map<String, Object> map) {

		
		SqlSession sqlSession = MybatisConnection.getConnection();
		

		try {
			
			int deleteRoom = sqlSession.getMapper(RoomMapperAnno.class).deleteRoom(map);
			return deleteRoom;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return 0;
		
	}

	public Business selectBu(String bu_email) {

		SqlSession sqlSession = MybatisConnection.getConnection();

		try {
			
			Business selectBu = sqlSession.getMapper(RoomMapperAnno.class).selectBu(bu_email);
			return selectBu;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return null;
	}


	public List<Booking> selectBkList(Map<String, Object> map) {
		
		SqlSession sqlSession = MybatisConnection.getConnection();

		try {
			
			List<Booking> selectBkList = sqlSession.getMapper(RoomMapperAnno.class).selectBkList(map);
			return selectBkList;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return null;
	}
	
	public List<Booking> searchStatus(Map<String, Object> map) {
		
		SqlSession sqlSession = MybatisConnection.getConnection();

		try {
			
			List<Booking> searchStatus = sqlSession.getMapper(RoomMapperAnno.class).searchStatus(map);
			return searchStatus;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return null;
	}
	
	
	public List<Booking> searchName(Map<String, Object> map) {

		SqlSession sqlSession = MybatisConnection.getConnection();

		try {
			
			List<Booking> searchName = sqlSession.getMapper(RoomMapperAnno.class).searchName(map);
			return searchName;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return null;
	}



	public String picList(Map<String, Object> map) {
		
		
		SqlSession sqlSession = MybatisConnection.getConnection();

		try {
			
			String picList = sqlSession.getMapper(RoomMapperAnno.class).picList(map);
			return picList;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return null;
	}

	public Business selectBusiness(String bu_email) {
		SqlSession sqlSession = MybatisConnection.getConnection();

		try {
			
			Business selectBusiness = sqlSession.getMapper(RoomMapperAnno.class).selectBusiness(bu_email);
			return selectBusiness;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return null;
	}

	public List<Picture> selectLocation(int pic_num) {
		SqlSession sqlSession = MybatisConnection.getConnection();

		try {
			
			List<Picture> selectLocation = sqlSession.getMapper(RoomMapperAnno.class).selectLocation(pic_num);
			return selectLocation;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return null;
	}

	public int deleteLocation(int pic_num) {
		SqlSession sqlSession = MybatisConnection.getConnection();

		try {
			
			int deleteLocation = sqlSession.getMapper(RoomMapperAnno.class).deleteLocation(pic_num);
			return deleteLocation;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return 0;
	}




	public Booking selectSales(Map<String, Object> map) {
		SqlSession sqlSession = MybatisConnection.getConnection();
		
		try {
			
			Booking selectSales = sqlSession.getMapper(RoomMapperAnno.class).selectSales(map);
			return selectSales;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return null;
	}

	public Booking selectAreaSales(Map<String, Object> map) {
		
		SqlSession sqlSession = MybatisConnection.getConnection();
		
		try {
			
			Booking selectAreaSales = sqlSession.getMapper(RoomMapperAnno.class).selectAreaSales(map);
			return selectAreaSales;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		return null;
	}

	public int countBoard(Map<String, Object> map) {

		SqlSession sqlSession = MybatisConnection.getConnection();
		
		try {
			
			int countBoard = sqlSession.getMapper(RoomMapperAnno.class).countBoard(map);
			return countBoard;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return 0;
	}

	public int countBoardStatus(Map<String, Object> map) {
		
		SqlSession sqlSession = MybatisConnection.getConnection();
		
		try {
			
			int countBoardStatus = sqlSession.getMapper(RoomMapperAnno.class).countBoardStatus(map);
			return countBoardStatus;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return 0;
	}

	public int countBoardSearchName(Map<String, Object> map) {
		
		SqlSession sqlSession = MybatisConnection.getConnection();
		
		try {
			
			int countBoardSearchName = sqlSession.getMapper(RoomMapperAnno.class).countBoardSearchName(map);
			return countBoardSearchName;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return 0;
	}

	public List<Business> addressList(Map<String, Object> map) {

		SqlSession sqlSession = MybatisConnection.getConnection();
		
		try {
			
			List<Business> addressList = sqlSession.getMapper(RoomMapperAnno.class).addressList(map);
			return addressList;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		return null;
	}

	public List<Booking> selectNotCheckin(Map<String, Object> map) {

		SqlSession sqlSession = MybatisConnection.getConnection();
		
		try {
			
			List<Booking> selectNotCheckin = sqlSession.getMapper(RoomMapperAnno.class).selectNotCheckin(map);
			return selectNotCheckin;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		return null;
	}

	public List<Booking> selectcheckinOk(Map<String, Object> map) {
		
		SqlSession sqlSession = MybatisConnection.getConnection();
		
		try {
			
			List<Booking> selectcheckinOk = sqlSession.getMapper(RoomMapperAnno.class).selectcheckinOk(map);
			return selectcheckinOk;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		return null;
	}

	public int updateTodayCheckin(Map<String, Object> map) {

		SqlSession sqlSession = MybatisConnection.getConnection();
		
		try {
			
			int updateTodayCheckin = sqlSession.getMapper(RoomMapperAnno.class).updateTodayCheckin(map);
			return updateTodayCheckin;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return 0;
	}

	public List<Booking> selectNotCheckOut(Map<String, Object> map) {

		SqlSession sqlSession = MybatisConnection.getConnection();
		
		try {
			
			List<Booking> selectNotCheckOut = sqlSession.getMapper(RoomMapperAnno.class).selectNotCheckOut(map);
			return selectNotCheckOut;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		return null;
	}

	public List<Booking> selectcheckOutOk(Map<String, Object> map) {


		SqlSession sqlSession = MybatisConnection.getConnection();
		
		try {
			
			List<Booking> selectcheckOutOk = sqlSession.getMapper(RoomMapperAnno.class).selectcheckOutOk(map);
			return selectcheckOutOk;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		return null;
	}

	public int updateAndDeleteTodayCheckOut(Map<String, Object> map) {

		SqlSession sqlSession = MybatisConnection.getConnection();
		try {
			int countReserved = sqlSession.getMapper(RoomMapperAnno.class).countReserved(map);
			
			if(countReserved > 0) {
				sqlSession.getMapper(RoomMapperAnno.class).deleteReserved(map);
			}
			
			int updateTodayCheckOut = sqlSession.getMapper(RoomMapperAnno.class).updateTodayCheckOut(map);
			return updateTodayCheckOut;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return 0;
	}

	
}
