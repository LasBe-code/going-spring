package mybatis;

import java.util.List;

import org.apache.ibatis.annotations.*;
import model.Booking;
import model.Member;
import model.Picture;

public interface BookingMapperAnno {
	
	@Select("select * from booking where email = #{email}")
	//반환형 메서드이름(매개변수);
	List<Booking> getBookingSelectList(String email);
	
	@Select ("select * from booking where bo_num = #{bo_num}")
	Booking getBookingSelectDetail(String bo_num);
	
	@Update("update booking set status = 2 where bo_num = #{bo_num}")
	void updateBookingStatus(String bo_num);
	
	@Select("select p.* from picture p, room r where p.pic_num = r.pic_num and r.ro_num = #{ro_num}")
	List<Picture> bookingPictureList(int ro_num);
}
