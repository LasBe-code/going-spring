package mybatis;

import java.util.List;

import org.apache.ibatis.annotations.*;

import model.Business;
import model.Member;
import model.Picture;

public interface MemberMapperAnno {
	
	@Insert("insert into member values(#{email}, #{password}, #{tel}, #{name})")
	int insertMember(Member member);
	
	@Insert("insert into business values(#{bu_email},#{bu_password},#{bu_tel},#{bu_name},#{bu_address},#{bu_id},#{bu_title},#{pic_num})")
	int insertBusiness(Business business);
	
	@Insert("insert into picture values(#{pic_num},#{location})")
	int insertPicture(Picture p);
	
	@Select("select * from member where email=#{email}")
	Member selectMemberOne(String email);
	
	@Select("select * from business where bu_email=#{bu_email}")
	Business selectBusinessOne(String bu_email);

	@Update("update business set bu_tel = #{bu_tel}, bu_name = #{bu_name}, bu_address = #{bu_address}, bu_id = #{bu_id}, bu_title = #{bu_title} where bu_email = #{bu_email}")
	int updateBusiness(Business b);

	@Update("update picture set location = #{location} where pic_num = #{pic_num}")
	int updatePicture(Picture p);

	@Select("select * from picture where pic_num = #{pic_num}")
	List<Picture> selectPic(int pic_num);

}