package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import model.Business;
import repository.RoomDao;
import repository.SearchDao;
import util.DateParse;

@Controller
@RequestMapping("/search/")
public class SearchController{
	
	
	HttpServletRequest request;
	Model model;
	HttpSession session;
	
	@Autowired
	SearchDao searchDao;
	
	@Autowired
	RoomDao rd;
	
	@ModelAttribute
	void init(HttpServletRequest request, Model model) {
		this.request = request;
		this.model = model;
		this.session = request.getSession();
	}
	
	
	@RequestMapping("main")
	public String main() {
		DateParse dateParse = new DateParse();
		String today = dateParse.getTodayPlus(0);
	    String tomorrow = dateParse.getTodayPlus(1);
	    model.addAttribute("today", dateParse.strToDate(today));
	    model.addAttribute("tomorrow", dateParse.strToDate(tomorrow));
		return "/view/search/main";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("search")
	public String search() {
		String bu_address = request.getParameter("bu_address");
		String checkin = request.getParameter("checkin");
		String checkout = request.getParameter("checkout");
		String ro_count = request.getParameter("ro_count");
		String bu_id = request.getParameter("bu_id");
		
		DateParse dateParse = new DateParse();
		Map map = new HashMap();
		map.put("bu_address", bu_address == null ? "" : bu_address);
		map.put("bu_id", bu_id);
		map.put("ro_count", ro_count);
		
		List<Business> list = searchDao.searchBusinessList(map);
		
		String today = dateParse.getTodayPlus(0);
		String tomorrow = dateParse.getTodayPlus(1);
		
		// yyyyMMdd -> yyyy-MM-dd
		today = dateParse.strToDate(today);
		tomorrow = dateParse.strToDate(tomorrow);
		
		// 카테고리 별 검색 시 현재 날짜로 날짜 설정
		if(checkin == null) checkin = today;
		if(checkout == null) checkout = tomorrow;
		
		model.addAttribute("bu_list", list);
		model.addAttribute("checkin", checkin);
		model.addAttribute("checkout", checkout);
		
		model.addAttribute("bu_id", bu_id);
		model.addAttribute("search", bu_address);
		model.addAttribute("ro_count", ro_count);
		model.addAttribute("today", today);
		model.addAttribute("tomorrow", tomorrow);
		return "/view/search/search";
	}
	
	
	@RequestMapping("map")
	public String map() {
		
		DateParse dp = new DateParse();
		
		List<Business> addressList = new ArrayList<Business>();
		String bu_id = request.getParameter("bu_id");
		String ro_count = request.getParameter("ro_count");
		String checkin = request.getParameter("checkin");
		String checkout = request.getParameter("checkout");
		String bu_address = request.getParameter("bu_address");
		
				
		if(bu_address == null  || bu_id == null || checkin == null ||  checkout == null) {
			bu_address = "서울";
			bu_id = "1";
			checkin = dp.strToDate(dp.getTodayPlus(0));
			checkout = dp.strToDate(dp.getTodayPlus(1));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bu_address", bu_address);
		map.put("bu_id", bu_id);
		
		String resultAddress = "[";
		String roomTitle = "[";
		String roomPic = "[";
		String bu_email = "[";
		addressList = rd.addressList(map);
		
		for(int i = 0 ; i < addressList.size(); i++) {
			if(i == addressList.size()-1) {
				resultAddress += "'"+addressList.get(i).getBu_address().trim()+"'";
				roomTitle += "'"+addressList.get(i).getBu_title().trim()+"'";
				roomPic += "'"+addressList.get(i).getLocation().trim()+"'";
				bu_email += "'"+addressList.get(i).getBu_email().trim()+"'";
			}
			else {
				resultAddress += "'"+addressList.get(i).getBu_address().trim()+"', ";
				roomTitle += "'"+addressList.get(i).getBu_title().trim()+"', ";
				roomPic += "'"+addressList.get(i).getLocation().trim()+"', ";
				bu_email += "'"+addressList.get(i).getBu_email().trim()+"', ";
			}
		}
		resultAddress += "]";
		roomTitle += "]";
		roomPic += "]";
		bu_email += "]";
		
		
		model.addAttribute("resultAddress", resultAddress);
		model.addAttribute("roomTitle", roomTitle);
		model.addAttribute("roomPic", roomPic);
		model.addAttribute("bu_email", bu_email);
		model.addAttribute("bu_id", bu_id);
		model.addAttribute("ro_count", ro_count);
		model.addAttribute("checkin", checkin);
		model.addAttribute("checkout", checkout);
		model.addAttribute("bu_address", bu_address);
		
		return "/view/search/map";
	}
}
