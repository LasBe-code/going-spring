package repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mybatis.ReservedMapperAnno;
import mybatis.WishMapperAnno;

@Repository
public class WishDao {
	Map map = new HashMap();
	
	private final SqlSession sqlSession;

	@Autowired
	public WishDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
		System.out.println("WishDao SqlSession On -> " + this.sqlSession);
	}
	
	public int insertWish(String email, String bu_email) throws Exception {
		map.clear();
		map.put("email", email);	map.put("bu_email", bu_email);
		return sqlSession.getMapper(WishMapperAnno.class).insertWish(map);
	}
	
	public int deleteWish(String email, String bu_email) throws Exception {
		map.clear();
		map.put("email", email);	map.put("bu_email", bu_email);
		return sqlSession.getMapper(WishMapperAnno.class).deleteWish(map);
	}
}
