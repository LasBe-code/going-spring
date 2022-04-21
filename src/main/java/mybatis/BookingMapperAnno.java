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
	
	@Select("	SELECT "
			+ "    	b.*, p.location , rev.rev_num as revNum "
			+ "	FROM "
			+ "    	booking b "
			+ "    	LEFT OUTER JOIN "
			+ "        	(select bo_num, rev_num from review) rev "
			+ "    	ON b.bo_num = rev.bo_num, "
			+ "    	room r "
			+ "    	LEFT OUTER JOIN "
			+ "        	(select DISTINCT pic_num, FIRST_VALUE(location) OVER(partition by pic_num) as location from picture) p "
			+ "    	ON r.pic_num = p.pic_num "
			+ "	WHERE "
			+ "    	b.email = #{email} and b.ro_num = r.ro_num")
	List<Booking> selectBookingPicRevList(String email);
}
