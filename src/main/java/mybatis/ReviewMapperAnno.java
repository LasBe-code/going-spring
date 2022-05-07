package mybatis;

import java.util.List;

import org.apache.ibatis.annotations.*;
import model.Review;

public interface ReviewMapperAnno {
	//	객실 등록시 시퀀스 +1
	@Select("select revseq.nextval from dual")
	int nextRevNum();
	
	// 고객이 작성한 리뷰 리스트
	@Select("	SELECT "
			+ "    	b.bu_title, b.ro_name, b.checkin, b.checkout, rev.* "
			+ "	FROM "
			+ "    	review rev, booking b "
			+ "	WHERE "
			+ "    	rev.bo_num = b.bo_num and rev.email = #{email} "
			+ "	ORDER BY review_date DESC ")
	List<Review> memberReviewList(String email);
	
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
	
	@Insert("	insert into review "
			+ "		(rev_num, bo_num, score, email, content, review_date, report) "
			+ "	values(#{rev_num},#{bo_num},#{score},"
			+ "	#{email},#{content},#{review_date}, '0')")
	int insertReview(Review review); 
	
	@Delete(" DELETE FROM review WHERE rev_num=#{rev_num}")
	int deleteReview(int rev_num);
}
