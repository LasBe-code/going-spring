package mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import model.Business;
import model.Wish;

public interface WishMapperAnno {
	
	@Insert("INSERT INTO wish VALUES(#{email}, #{bu_email})")
	public int insertWish(Map map);
	
	@Delete("DELETE FROM wish WHERE email = #{email} and bu_email = #{bu_email} ")
	public int deleteWish(Map map);
	
	@Select("	SELECT  "
			+ "    	bu.*, p.location "
			+ "	FROM "
			+ "    	wish w, business bu "
			+ "    	LEFT OUTER JOIN "
			+ "        	( "
			+ "        	SELECT DISTINCT  "
			+ "            	pic_num, FIRST_VALUE(location) OVER(partition by pic_num) as location "
			+ "        	FROM picture "
			+ "        	) p "
			+ "    	ON bu.pic_num = p.pic_num "
			+ "	WHERE "
			+ "    	w.bu_email = bu.bu_email and w.email = #{email}" )
	public List<Business> wishBusinessList(String email);
}
