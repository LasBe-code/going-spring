package mybatis;

import org.apache.ibatis.annotations.*;
import model.Review;

public interface ReviewMapperAnno {
//	객실 등록시 시퀀스 +1
	@Select("select revseq.nextval from dual")
	int nextRevNum();
	
	@Insert("	insert into review "
			+ "		(rev_num, bo_num, score, email, content, review_date) "
			+ "	values(#{rev_num},#{bo_num},#{score},"
			+ "	#{email},#{content},#{review_date})")
	int insertReview(Review review); 
}
