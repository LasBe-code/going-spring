package mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;
import model.Booking;
import model.Business;
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
	
	//  최근 가장 많이 팔린 숙소 10개 Business(*) => (숙소 객실 중 최저가 + 별점 평균 + 숙소 첫번째 사진)
	  //     숙소 별 총 예약 횟수는 임의로 revCount에 저장
	  @Select("    SELECT "
		  + "        nvl(minPrice, 0) as minPrice, nvl(avgScore, 0) as avgScore, "
	      + "        bu.*, nvl(r.countBooking, 0) as revCount, p.location as picLocation "
	      + "    FROM "
	      + "        business bu "
	      + ""
	      + "        LEFT OUTER JOIN " // 판매 순위 10등 계산 
	      + "        ( "
	      + "            SELECT "
	      + "                r.bu_email, count(b.bo_num) as countBooking "
	      + "            FROM "
	      + "                booking b, room r "
	      + "            WHERE "
	      + "                reg_date between #{beforeDay} and #{today} and b.ro_num = r.ro_num "
	      + "            GROUP BY r.bu_email "
	      + "            ORDER BY 2 desc "
	      + "        ) r "
	      + "        ON bu.bu_email = r.bu_email "
	      + ""
	      + "        LEFT OUTER JOIN  " // 숙소의 첫번째 사진
	      + "        ( "
	      + "            SELECT  "
	      + "                DISTINCT pic_num, FIRST_VALUE(location) OVER(partition by pic_num) as location  "
	      + "            FROM picture "
	      + "        ) p "
	      + "        ON bu.pic_num = p.pic_num "
	      + ""
	      + "        LEFT OUTER JOIN " // 숙소 별점 평균
	      + "        ( "
	      + "        SELECT ro.bu_email as bu_email, TRUNC(avg(review.score)) as avgScore "
	      + "        FROM review review, booking bo, room ro, business bu "
	      + "        WHERE  "
	      + "            review.bo_num = bo.bo_num and  "
	      + "            bo.ro_num = ro.ro_num and  "
	      + "            ro.bu_email = bu.bu_email "
	      + "        GROUP BY ro.bu_email "
	      + "        ) score "
	      + "    ON bu.bu_email = score.bu_email "
	      + ""
	      + "    LEFT OUTER JOIN " // 숙소의 객실 중 최저가
	      + "    ( "
	      + "        SELECT bu_email, min(to_number(ro_price)) as minPrice "
	      + "        FROM room  "
	      + "        WHERE ro_count >= 2 group by bu_email "
	      + "    ) min "
	      + "    ON bu.bu_email = min.bu_email "
	      + ""
	      + "    WHERE rownum < 11 "
	      + "    ORDER BY revCount desc ")
	  List<Business> hot10BusinessList(Map map);
}
