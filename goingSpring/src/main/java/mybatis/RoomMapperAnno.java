package mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import model.Booking;
import model.Business;
import model.Member;
import model.Picture;
import model.Room;

public interface RoomMapperAnno {

	@Select("select * from room where bu_email = #{bu_email} order by lpad(ro_price, 10, '0')")
	List<Room> roomList(String bu_email);
	
	//roominfo 슬라이드에 나올 첫번째 사진
	@Select("select substr(location ,0,INSTRB(location,',')-1) location "
			+ "from room r, picture p where r.bu_email = #{bu_email} and r.pic_num = p.pic_num and r.ro_num = #{ro_num}")
	String picMain(Map<String, Object> map);
	
	@Select("select picseq.nextval from dual")
	int nextPicNum();
	
	
	@Select("select roseq.nextval from dual")
	int nextRoNum();
	
	@Insert("insert into room (ro_num, bu_email, ro_count, ro_name, ro_price, checkin, checkout, ro_info, pic_num) "
			+ " values (#{ro_num}, #{bu_email}, #{ro_count}, #{ro_name}, #{ro_price}, #{checkin}, #{checkout}, #{ro_info}, #{pic_num}) ")
	int insertRoom(Room room);

	
	@Insert("insert into picture values(#{pic_num}, #{location})")
	int insertPicture(Picture p);
	
	
	@Select("select * from room where ro_num = #{ro_num}")
	Room selectRoom(int ro_num);
	
	@Select("select * from picture where pic_num = #{pic_num}")
	List<Picture> selectPic(int pic_num);

	
	
	@Update("update picture set location = #{location} where pic_num = #{pic_num}")
	int updatePicture(Picture p);

	
	@Update("update room set  bu_email = #{bu_email}, ro_count = #{ro_count}, ro_name = #{ro_name}, ro_price = #{ro_price}, "
			+ " checkin = #{checkin}, checkout = #{checkout}, ro_info = #{ro_info} where ro_num = #{ro_num}")
	int updateRoom(Room room);

	
	@Delete("delete from room where bu_email = #{bu_email} and ro_num = #{ro_num}")
	int deleteRoom(Map<String, Object> map);

	
	@Select("select b.bu_password from business b where b.bu_email = #{bu_email} ")
	Business selectBu(String bu_email);

	
	@Select("select * from (select rownum rnum, a.* from (select bo.ro_name, ro.ro_count, bo.checkin, bo.checkout, bo.status "
			+ "	from booking bo, room ro where ro.bu_email = #{bu_email} and ro.ro_name = bo.ro_name order by checkin "
			+ "	) a)  where rnum between #{startPage} and #{endPage}")
	List<Booking> selectBkList(Map<String, Object> map);

	@Select("select rnum, name, tel, email from (select rownum rnum, a.* "
			+ " from(select m.name, m.tel, m.email, b.checkin, b.checkout from member m, room r, booking b "
			+ " where r.bu_email = #{bu_email} and r.ro_name = b.ro_name and m.email = b.email order by checkin) a) "
			+ " where rnum between #{startPage} and #{endPage}")
	List<Member> selectMember(Map<String, Object> map);
	
	
	@Select("select count(*) from booking bk, business bu where bk.bu_title = bu.bu_title and bu.bu_email = #{bu_email}")
	int countBoard(String bu_email);

	// roominfo 슬라이드에 나올 사진들(2번부터) 
	@Select("select substr(location ,INSTRB(location,',')+2) location "
			+ " from room r, picture p where r.bu_email = #{bu_email} and "
			+ " r.pic_num = p.pic_num and r.ro_num = #{ro_num}")
	String picList(Map<String, Object> map);

	@Select("select * from business where bu_email = #{bu_email}")
	Business selectBusiness(String bu_email);

	@Select("select location from picture where pic_num = #{pic_num}")
	List<Picture> selectLocation(int pic_num);

	@Delete("delete from picture where pic_num = #{pic_num}")
	int deleteLocation(int pic_num);

	

	
	
}
