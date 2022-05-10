package mybatis;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

public interface WishMapperAnno {
	
	@Insert("INSERT INTO wish VALUES(#{email}, #{bu_email})")
	public int insertWish(Map map);
	
	@Delete("DELETE FROM wish WHERE email = #{email} and bu_email = #{bu_email} ")
	public int deleteWish(Map map);
}
