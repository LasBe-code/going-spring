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
import model.SearchDTO;
import repository.RoomDao;
import repository.SearchDao;
import service.SearchService;
import util.DateParse;

@Controller
@RequestMapping("/search/")
public class SearchController{
	
	HttpServletRequest request;
	Model model;
	HttpSession session;
	
	private final SearchService searchService;
	
	@Autowired
	public SearchController(SearchService searchService) {
		this.searchService=searchService;
	}
	
	@ModelAttribute
	void init(HttpServletRequest request, Model model) {
		this.request = request;
		this.model = model;
		this.session = request.getSession();
	}
	
	@RequestMapping("main")
	public String main() {
	    model.addAttribute("today", DateParse.strToDate(DateParse.getTodayPlus(0)));
	    model.addAttribute("tomorrow", DateParse.strToDate(DateParse.getTodayPlus(1)));
		return "/view/search/main";
	}
	
	@RequestMapping("search")
	public String search(SearchDTO searchDTO) {
		// yyyyMMdd -> yyyy-MM-dd
		String today = DateParse.strToDate(DateParse.getTodayPlus(0));
		String tomorrow = DateParse.strToDate(DateParse.getTodayPlus(1));
		
		// 카테고리 별 검색 시 현재 날짜로 날짜 설정
		if(searchDTO.getCheckin() == null) searchDTO.setCheckin(today);
		if(searchDTO.getCheckout() == null) searchDTO.setCheckout(tomorrow);
		
		try {
			
			List<Business> bu_list = searchService.searchBusinessList(searchDTO);
			model.addAttribute("bu_list", bu_list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("searchDTO", searchDTO);
		model.addAttribute("today", today);
		model.addAttribute("tomorrow", tomorrow);
		return "/view/search/search";
	}
	
	
	@RequestMapping("map")
	public String map(SearchDTO searchDTO) {
		
		
		List<Business> addressList = new ArrayList<Business>();
		String bu_id = request.getParameter("bu_id");
		String ro_count = request.getParameter("ro_count");
		String checkin = request.getParameter("checkin");
		String checkout = request.getParameter("checkout");
		String bu_address = request.getParameter("bu_address");
		
				
		if(bu_address == null  || bu_id == null || checkin == null ||  checkout == null) {
			bu_address = "서울";
			bu_id = "1";
			checkin = DateParse.strToDate(DateParse.getTodayPlus(0));
			checkout = DateParse.strToDate(DateParse.getTodayPlus(1));
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
