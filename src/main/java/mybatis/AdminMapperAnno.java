package mybatis;

import java.util.Map;

import org.apache.ibatis.annotations.Select;

import model.Booking;

public interface AdminMapperAnno {
//	사업자 월별 매출
	@Select("select sum(bo.price) price from business bu, booking bo "
			+ " where bu.bu_title = bo.bu_title and bu.bu_email = #{bu_email} and checkin like '____'||#{mon}||'%' ")
	Booking selectSales(Map<String, Object> map);

	
//	지역별 월별 매출
	@Select("select sum(bo.price) price from business bu, booking bo "
			+ "where bu.bu_title = bo.bu_title and bu.bu_address like #{area}||'%' and bo.checkin like '____'||#{month}||'%' ")
	Booking selectAreaSales(Map<String, Object> map);
}
