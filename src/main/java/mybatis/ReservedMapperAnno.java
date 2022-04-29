package mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

import model.Booking;
import model.Business;
import model.Picture;
import model.Reserved;
import model.Review;
import model.Room;
import model.SearchDTO;

public interface ReservedMapperAnno {
	
	// business(인원 수, 숙소 이름, 숙소 주소) + room(최저가, 인원 수 제한) + picture(숙소 사진 첫번째)
	@Select(
			"	SELECT "
			+"	b.*, r.min_price as minPrice, p.location as picLocation "
			+"	FROM "
			+"	    (select * from business where bu_id = #{bu_id} and ( bu_address like '%${bu_address}%' or bu_title like '%${bu_address}%')) b "
			+"	LEFT OUTER JOIN "
			+"	    (select bu_email, min(to_number(ro_price)) as min_price from room where ro_count >= #{ro_count} group by bu_email) r "
			+"	ON b.bu_email = r.bu_email "
			+"	LEFT OUTER JOIN "
			+"	    (select DISTINCT pic_num, FIRST_VALUE(location) OVER(partition by pic_num) as location from picture) p "
			+"	ON b.pic_num = p.pic_num "
			+"	ORDER BY 2"
			)
	List<Business> searchBusinessList(SearchDTO searchDTO);
	
	
	// Room(*) + Reserved(예약일자 중복정보) + Picture(방의 첫번째 사진)
	@Select("	SELECT "
			+ "		a.*, nvl(r.overlap, 0) as overlap, p.location "
			+ "	FROM "
			+ "		(select * from room where bu_email = #{bu_email} and ro_count >= #{ro_count}) a "
			+ "		LEFT OUTER JOIN "
			+ "			(select ro_num, count(*) as overlap from reserved where re_date BETWEEN #{checkin} and #{checkout} group by ro_num) r "
			+ "		ON a.ro_num = r.ro_num "
			+ " 	LEFT OUTER JOIN "
			+ " 		(select DISTINCT pic_num, FIRST_VALUE(location) OVER(partition by pic_num) as location from picture) p "
			+ " 	ON a.pic_num = p.pic_num "
			+ "	ORDER BY a.ro_price")
	List<Room> overlapRoomList(Map map);
	
	
	// Business에 해당하는 Review 리스트
	@Select("	SELECT "
			+ "    	ro.ro_name, review.* "
			+ "	FROM "
			+ "    	review review, booking bo, room ro, business bu "
			+ "	WHERE "
			+ "    	review.bo_num = bo.bo_num and "
			+ "    	bo.ro_num = ro.ro_num and "
			+ "    	ro.bu_email = bu.bu_email and "
			+ "    	bu.bu_email = #{bu_email} ")
	List<Review> businessReviewList(String bu_email);
	
	
	
	// Business(*) + Review(별점 평균, 리뷰 개수)
	@Select("	SELECT  "
			+ "    	b.*, nvl(score.avgScore, 0) as avgScore, nvl(score.revCount, 0) as revCount "
			+ "	FROM  "
			+ "    	business b "
			+ "    	LEFT OUTER JOIN "
			+ "        	( "
			+ "        	SELECT "
			+ "            	ro.bu_email as bu_email, TRUNC(avg(review.score), 1) as avgScore, count(review.score) as revCount "
			+ "        	FROM "
			+ "            	review review, booking bo, room ro, business bu "
			+ "        	WHERE  "
			+ "            	review.bo_num = bo.bo_num and  "
			+ "            	bo.ro_num = ro.ro_num and  "
			+ "            	ro.bu_email = bu.bu_email and  "
			+ "            	bu.bu_email = #{bu_email} "
			+ "        	GROUP BY ro.bu_email) score "
			+ "    	ON b.bu_email = score.bu_email "
			+ "	WHERE b.bu_email=#{bu_email}")
	Business reviewAvgCountBusinessOne(String bu_email);
	
//	사업자의 주소를 가져와 지도로 출력
	@Select("SELECT * FROM (select location, bu_address, bu_title, bu_email, "
			+ "	ROW_NUMBER() OVER (PARTITION BY pic_num order by pic_num) AS NUM "
			+ "	FROM (select bu.bu_address, bu.bu_id, bu.bu_title, p.location, p.pic_num,bu.bu_email from  business bu , "
			+ "	picture p where  bu.pic_num = p.pic_num and bu.bu_id = #{bu_id} and bu.bu_address like #{bu_address}||'%')) where num = 1")
	List<Business> addressList(SearchDTO searchDTO);
	
	@Select("select * from room where bu_email = #{bu_email} and ro_count >= #{ro_count}")
	List<Room> roomList(Map map);
	
	@Select("select * from reserved where ro_num = #{ro_num} and ( re_date between #{checkin} and #{checkout} ) ")
	List<Reserved> reserveCheck(Map map);
	
	
	@Select("select boseq.nextval from dual")
	int nextBoNum();
	
	@Insert("insert into booking(bo_num, email, payment, price, bu_title, ro_name, checkin, checkout, ro_num, reg_date, status) values(#{bo_num}, #{email}, #{payment}, #{price}, #{bu_title}, #{ro_name}, #{checkin}, #{checkout}, #{ro_num}, #{reg_date}, #{status})")
	int insertBooking(Booking b);
	
	@Insert("insert into reserved values(#{ro_num}, #{re_date})")
	int insertReserved(Reserved r);
	
	@Select("select * from picture where pic_num = #{pic_num}")
	List<Picture> sbPicList(int pic_num);
	
	@Delete("delete from reserved where ro_num=#{ro_num} and re_date between #{checkin} and #{checkout}")
	int cancelReserveList(Booking b);
}
