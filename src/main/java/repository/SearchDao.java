package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Business;
import model.Picture;
import model.SearchDTO;
import mybatis.ReservedMapperAnno;

@Repository
public class SearchDao {

	private final SqlSession sqlSession;

	@Autowired
	public SearchDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
		System.out.println("SearchDao SqlSession On -> " + this.sqlSession);
	}

	// business(인원 수, 숙소 이름, 숙소 주소) + room(최저가, 인원 수 제한) + picture(숙소 사진 첫번째)
	public List<Business> searchBusinessList(SearchDTO searchDTO) throws Exception {
		return sqlSession.getMapper(ReservedMapperAnno.class).searchBusinessList(searchDTO);
	}

	public List<Picture> sbPicList(int pic_num) throws Exception {
		return sqlSession.getMapper(ReservedMapperAnno.class).sbPicList(pic_num);
	}

	//	사업자의 주소를 가져와 지도로 출력
	List<Business> addressList(SearchDTO searchDTO) throws Exception {
		return sqlSession.getMapper(ReservedMapperAnno.class).addressList(searchDTO);
	}
}