package service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Booking;
import model.Business;
import model.Member;
import repository.AdminDao;
import repository.MemberDao;
import repository.RoomDao;

@Service
public class AdminService {
	private Map<Object, Object> serviceMap = new HashMap<Object, Object>();
	private Map<String, Object> map = new HashMap<String, Object>();
	
	private final MemberDao memberDao;
	private final AdminDao adminDao;

	@Autowired
	public AdminService(MemberDao memberDao, AdminDao adminDao) {
		this.memberDao = memberDao;
		this.adminDao = adminDao;
	}
	
	public Boolean adminLogin(String email, String password) throws Exception{
		Member member = memberDao.selectMemberOne(email);
		if(member.getEmail().equals(email) && member.getPassword().equals(password))
			if(member.getAdmin_check().equals("1"))
				return true;
		
		return false;
	}
	
	public Map<Object, Object> areaSales(String month) throws Exception {
		String[] areas = {"서울", "경기", "강원", "부산"};
		
		if(month == null) {
			LocalDate now = LocalDate.now();
			int month1 = now.getMonthValue();
			month = "0"+month1;
		}
		String result = "";
		map.put("month", month);
		for(String area : areas) {
			map.put("area", area);
//			지역별 월매출
			Booking bo = adminDao.selectAreaSales(map);
			if(result!="") { 
				result += ","; 
			}
			if(bo == null) {
				result += "['"+area+"', "+"0"+"]";
			}
			else {
				result += "['"+area+"', "+bo.getPrice()+"]";
			}
		}
		serviceMap.clear();
		serviceMap.put("result", result);
		serviceMap.put("month", month);
		return serviceMap;
	}
	
	public Map<Object, Object> categorySales(String month) {
		
		String[] bu_id = {"1", "2", "3", "4"};
		String[] category = {"호텔","모텔","펜션","리조트"};
		if(month == null) {
			LocalDate now = LocalDate.now();
			int month1 = now.getMonthValue();
			if(!(month1 == 10 || month1 == 11 || month1 == 12))
				month = "0"+month1;
		}
		String result = "";
		map.put("month", month);
		for(int i = 0 ; i < bu_id.length; i++) {
			map.put("bu_id", bu_id[i]);
//			지역별 월매출
			Booking bo = adminDao.categorySales(map);
			if(result!="") { 
				result += ","; 
			}
			if(bo == null) {
				result += "['"+category[i]+"', "+"0"+"]";
			}
			else {
				result += "['"+category[i]+"', "+bo.getPrice()+"]";
			}
		}
		serviceMap.clear();
		serviceMap.put("result", result);
		serviceMap.put("month", month);
		return serviceMap;
	}
	
	public Map<Object, Object> sales(String bu_email) {
		String[] month = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
		map.clear();
		map.put("bu_email", bu_email);
		
		String result = "";
		for(String mon : month) {
			map.put("mon", mon);
			Booking bo = adminDao.selectSales(map);
			if(result!="") { 
				result += ","; 
			}

			if(bo == null) {
				result += "['"+mon+"월', "+"0"+"]";

			}
			else {
				result += "['"+mon+"월', "+bo.getPrice()+"]";
			}
		}
		serviceMap.clear();
		serviceMap.put("result", result);
		return serviceMap;
	}
	
	public List<Business> notApprovalBuList(int startPage, int endPage) throws Exception{
		return adminDao.notApprovalBuList(startPage, endPage);
	}
	
	public int notApprovalBuCount() throws Exception {
		return adminDao.notApprovalBuCount();
	}
	
	public int businessApproval(String bu_email) throws Exception {
		return adminDao.businessApproval(bu_email);
	}
	
	public int businessApprovalCancel(String bu_email) throws Exception {
		return adminDao.businessApprovalCancel(bu_email);
	}

	
}
