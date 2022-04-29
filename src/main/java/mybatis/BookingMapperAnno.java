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
	
	// 예약 취소로 변경
	@Update("update booking set status = 2 where bo_num = #{bo_num}")
	int updateBookingStatus(String bo_num);
	
	@Select("select p.* from picture p, room r where p.pic_num = r.pic_num and r.ro_num = #{ro_num}")
	List<Picture> bookingPictureList(int ro_num);
	
	
	// 고객 예약 내역 + 예약 객실의 첫번째 사진 + 예약에 대한 리뷰 여부
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
			+ "    	b.email = #{email} and b.ro_num = r.ro_num"
			+ "	ORDER BY reg_date desc ")
	List<Booking> selectBookingPicRevList(String email);
}
