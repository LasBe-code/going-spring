package repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Business;
import model.Picture;
import model.SearchDTO;
import mybatis.BookingMapperAnno;
import mybatis.ReservedMapperAnno;
import util.DateParse;

@Repository
public class SearchDao {
	Map map = new HashMap();

	private final SqlSession sqlSession;

	@Autowired
	public SearchDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
		System.out.println("SearchDao SqlSession On -> " + this.sqlSession);
	}

	// business(인원 수, 숙소 이름, 숙소 주소) + room(최저가, 인원 수 제한) + picture(숙소 사진 첫번째)
	public List<Business> searchBusinessList(SearchDTO searchDTO) throws Exception {
		if (searchDTO.getLowprice() ==null) searchDTO.setLowprice("10000");
		if (searchDTO.getHighprice() ==null) searchDTO.setHighprice("1500000");
		return sqlSession.getMapper(ReservedMapperAnno.class).searchBusinessList(searchDTO);
	}

	public List<Picture> sbPicList(int pic_num) throws Exception {
		return sqlSession.getMapper(ReservedMapperAnno.class).sbPicList(pic_num);
	}

	// 사업자의 주소를 가져와 지도로 출력
	List<Business> addressList(SearchDTO searchDTO) throws Exception {
		return sqlSession.getMapper(ReservedMapperAnno.class).addressList(searchDTO);
	}

//	최근(between -> reg_date / 2주 정도) 많이 예약(Booking -> count(*))된 순으로 정렬된 Business의 정보
	public List<Business> hot10BusinessList() throws Exception {
		map.clear();
		String today = DateParse.getTodayPlus(0);
		map.put("today", today);
		map.put("beforeDay", DateParse.datePlus(today, -60));
		return sqlSession.getMapper(BookingMapperAnno.class).hot10BusinessList(map);
	}
}