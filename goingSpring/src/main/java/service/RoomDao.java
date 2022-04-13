package service;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import model.Booking;
import model.Business;
import model.Member;
import model.Picture;
import model.Room;
import mybatis.RoomMapperAnno;
import util.MybatisConnection;

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

	public int countBoard(String bu_email) {
		SqlSession sqlSession = MybatisConnection.getConnection();

		try {
			
			int countBoard = sqlSession.getMapper(RoomMapperAnno.class).countBoard(bu_email);
			return countBoard;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return 0;
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

	public List<Member> selectMember(Map<String, Object> map) {
		SqlSession sqlSession = MybatisConnection.getConnection();

		try {
			
			List<Member> selectMember = sqlSession.getMapper(RoomMapperAnno.class).selectMember(map);
			return selectMember;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(sqlSession);
		}
		
		return null;
	}


	
}
