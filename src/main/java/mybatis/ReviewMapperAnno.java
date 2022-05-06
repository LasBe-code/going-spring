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
	
	@Insert("	insert into review "
			+ "		(rev_num, bo_num, score, email, content, review_date, report) "
			+ "	values(#{rev_num},#{bo_num},#{score},"
			+ "	#{email},#{content},#{review_date}, '0')")
	int insertReview(Review review); 
}
