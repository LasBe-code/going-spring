package repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Booking;
import model.Business;
import mybatis.AdminMapperAnno;
import mybatis.RoomMapperAnno;

@Repository
public class AdminDao {
	private Map map=new HashMap();
	private final SqlSession sqlSession;
	
	@Autowired
	public AdminDao(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
		System.out.println("AdminDao SqlSession On -> "+this.sqlSession);
	}
	
	public List<Business> notApprovalBuList(int startPage, int endPage) throws Exception {
		map.clear();
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		return sqlSession.getMapper(AdminMapperAnno.class).notApprovalBuList(map);
	}
	
	public Booking categorySales(Map<String, Object> map) {
		return sqlSession.getMapper(AdminMapperAnno.class).categorySales(map);
	}
	
	public Booking selectAreaSales(Map<String, Object> map) throws Exception  {
		return sqlSession.getMapper(AdminMapperAnno.class).selectAreaSales(map);
	}
	
	public int businessApproval(String bu_email) throws Exception {
		return sqlSession.getMapper(AdminMapperAnno.class).businessApproval(bu_email);
	}
	
	public int businessApprovalCancel(String bu_email) throws Exception {
		return sqlSession.getMapper(AdminMapperAnno.class).deleteBusiness(bu_email);
	}

	public int notApprovalBuCount() throws Exception{
		return sqlSession.getMapper(AdminMapperAnno.class).notApprovalBuCount();
	}

	public Booking selectSales(Map<String, Object> map) {
		return sqlSession.getMapper(AdminMapperAnno.class).selectSales(map);
	}

}
