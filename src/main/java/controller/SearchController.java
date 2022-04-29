package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import model.Business;
import model.SearchDTO;
import repository.RoomDao;
import repository.SearchDao;
import service.RoomService;
import service.SearchService;
import util.DateParse;

@Controller
@RequestMapping("/search/")
public class SearchController{
	
	HttpServletRequest request;
	Model model;
	HttpSession session;
	
	private final SearchService searchService;
	private final RoomService roomService;
	
	@Autowired
	public SearchController(SearchService searchService, RoomService roomService) {
		this.searchService = searchService;
		this.roomService = roomService;
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
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			map = roomService.addressList(searchDTO);
			
			if(searchDTO.getBu_address() == null || searchDTO.getRo_count() == null || searchDTO.getCheckin() == null
				||	searchDTO.getCheckout() == null || searchDTO.getBu_address() ==null) {
				searchDTO.setBu_id((String) map.get("bu_id"));
				searchDTO.setRo_count((String)map.get("ro_count"));
				searchDTO.setCheckin((String)map.get("checkin"));
				searchDTO.setCheckout((String)map.get("checkout"));
				searchDTO.setBu_address((String)map.get("bu_address"));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("searchDTO", searchDTO);
		model.addAttribute("bu_email", map.get("bu_email"));
		model.addAttribute("roomPic", map.get("roomPic"));
		model.addAttribute("roomTitle", map.get("roomTitle"));
		model.addAttribute("resultAddress", map.get("resultAddress"));
		
		return "/view/search/map";
	}
}
