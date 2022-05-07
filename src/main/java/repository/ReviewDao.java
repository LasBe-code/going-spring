package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import model.Review;
import mybatis.ReviewMapperAnno;

@Repository
public class ReviewDao {
	private final SqlSession sqlSession;
	
	public ReviewDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	public List<Review> businessReviewList(String bu_email) throws Exception {
		return sqlSession.getMapper(ReviewMapperAnno.class).businessReviewList(bu_email);
	}
	public List<Review> myReview(String email) throws Exception{
		return sqlSession.getMapper(ReviewMapperAnno.class).memberReviewList(email);
	}
	public int nextRevNum() throws Exception {
		return sqlSession.getMapper(ReviewMapperAnno.class).nextRevNum();
	}
	public int insertReview(Review review) throws Exception {
		System.out.println(review);
		return sqlSession.getMapper(ReviewMapperAnno.class).insertReview(review);
	}
	public int deleteReview(int rev_num) throws Exception {
		return sqlSession.getMapper(ReviewMapperAnno.class).deleteReview(rev_num);
	}
}
