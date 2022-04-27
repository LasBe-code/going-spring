package repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mybatis.MemberMapperAnno;

@Repository
public class TestDao {
	@Autowired
	SqlSession sqlSession;
	
	public int memberTelCount(String tel) {
		return sqlSession.getMapper(MemberMapperAnno.class).memberTelCount(tel);
	}
}
