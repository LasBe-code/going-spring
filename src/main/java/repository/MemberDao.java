package repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import model.Business;
import model.Member;
import model.Picture;
import mybatis.MemberMapperAnno;
import util.MybatisConnection;

@Repository
public class MemberDao{
	private Map<String, Object> map = new HashMap<>();
	
	private final SqlSession sqlSession;
	
	@Autowired
	public MemberDao(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
		System.out.println(sqlSession);
	}
	
	public int insertMember(HttpServletRequest request) {
		Member m = new Member(
				(String)request.getParameter("email"),
				request.getParameter("password"),
				request.getParameter("tel"),
				request.getParameter("name")
				);
		return sqlSession.getMapper(MemberMapperAnno.class).insertMember(m);
	}
	
	public int insertBusiness(HttpServletRequest request) {
		RoomDao rd = new RoomDao();
		Picture p = new Picture();
		Business b = new Business(
				request.getParameter("bu_email"),
				request.getParameter("bu_password"),
				request.getParameter("bu_tel"),
				request.getParameter("bu_name"),
				request.getParameter("bu_address"),
				request.getParameter("bu_id"),
				request.getParameter("bu_title"),
				rd.nextPicNum()
				);
		String[] picList = request.getParameter("picLocation").split("\\n");
		
		for(String pic : picList) {
			p = new Picture(b.getPic_num(), pic.trim());
			rd.insertPicture(p);
		}
				
		int insertBusiness = sqlSession.getMapper(MemberMapperAnno.class).insertBusiness(b);
		int insertPicture = sqlSession.getMapper(MemberMapperAnno.class).insertPicture(p);
		if(insertBusiness > 0 && insertPicture > 0) 
			return 1;
		
		return 0;
	}
	
	public Member selectMemberOne(String email) {
		return sqlSession.getMapper(MemberMapperAnno.class).selectMemberOne(email);
	}
	
	public Business selectBusinessOne(String bu_email) {
		return sqlSession.getMapper(MemberMapperAnno.class).selectBusinessOne(bu_email);
	}

	public int updateBusiness(HttpServletRequest request) {
		RoomDao rd = new RoomDao();
		
		String[] picList = request.getParameter("picLocation").split("\n");
		Business sb = rd.selectBusiness(request.getParameter("bu_email"));
		Picture insertP = new Picture();
		String pass = request.getParameter("bu_password");
		int p = 0;
		if(sb.getBu_password().equals(pass)) {
			// 입력한 비밀번호와 저장된 비밀번호가 같으면 pic_num이 같은 데이터는 삭제
			p = rd.deleteLocation(sb.getPic_num());
			// 비밀번호가 같으면 사업자 정보수정
			sb.setBu_tel(request.getParameter("bu_tel"));
			sb.setBu_name(request.getParameter("bu_name"));
			sb.setBu_address(request.getParameter("bu_address"));
			sb.setBu_id(request.getParameter("bu_id"));
			sb.setBu_title(request.getParameter("bu_title"));
			
		}else {
			// 비밀번호가 다르면 -1 리턴
			return -1;
		}
		int insertPicture = 0;
		
		int insertBusiness = sqlSession.getMapper(MemberMapperAnno.class).updateBusiness(sb);
		for(String pic : picList) {
			insertP.setPic_num(sb.getPic_num());
			insertP.setLocation(pic);
			insertPicture = sqlSession.getMapper(MemberMapperAnno.class).insertPicture(insertP);
		}
		if(insertBusiness > 0 && insertPicture > 0) {
			return 1;
		}
		return 0;
	}

	public List<Picture> selectPic(int pic_num) {
		return sqlSession.getMapper(MemberMapperAnno.class).selectPic(pic_num);
	}
	
	public int memberTelCount(String tel) {
		return sqlSession.getMapper(MemberMapperAnno.class).memberTelCount(tel);
	}
	
	public int businessTelCount(String bu_tel) {
		return sqlSession.getMapper(MemberMapperAnno.class).businessTelCount(bu_tel);
	}
}
