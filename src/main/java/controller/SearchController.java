package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Business;
import model.Picture;
import service.SearchDao;

public class SearchController extends MskimRequestMapping{
	@RequestMapping("main")
	public String main(HttpServletRequest request, HttpServletResponse response) {
		return "/view/search/main.jsp";
	}
	@RequestMapping("search")
	public String search(HttpServletRequest request, HttpServletResponse response) {
		String bu_address = request.getParameter("bu_address");
		String checkin = request.getParameter("checkin");
		String checkout = request.getParameter("checkout");
		String ro_count = request.getParameter("ro_count");
		String bu_id = request.getParameter("bu_id");
		Map map = new HashMap();
		map.put("bu_address", bu_address);
		map.put("bu_id", bu_id);
		
		SearchDao sd = new SearchDao();
		List<Business> list = sd.businessList(map);
		System.out.println("bu_List"+list);
		List<Picture> picList = new ArrayList<>();
		List<Business> list2 = sd.buidList(map);
		String minPrice = null;
		
		Map<Integer, String> picMap = new HashMap<>();
		Map<Integer, String> minPriceMap = new HashMap<>();
		
		for(Business b : list) {
			picList = sd.sbPicList(b.getPic_num());
			System.out.println(picList+"//////////////"+b.getPic_num());
			if(!picList.isEmpty()) picMap.put(b.getPic_num(), picList.get(0).getLocation());
			else  picMap.put(b.getPic_num(), "");
			
			minPrice = sd.roomMinPrice(b.getBu_email());
			minPriceMap.put(b.getPic_num(), minPrice);
		}
		System.out.println(picMap);
		System.out.println(minPriceMap);
		
		request.setAttribute("picMap", picMap);
		request.setAttribute("minPriceMap", minPriceMap);
		
		map.clear();
		for(Business b : list2) {
			picList = sd.sbPicList(b.getPic_num());
			System.out.println(picList+"//////////////"+b.getPic_num());
			if(!picList.isEmpty()) picMap.put(b.getPic_num(), picList.get(0).getLocation());
			else  picMap.put(b.getPic_num(), "");
			
			minPrice = sd.roomMinPrice(b.getBu_email());
			minPriceMap.put(b.getPic_num(), minPrice);
		}
		System.out.println(picMap);
		System.out.println(minPriceMap);
		
		request.setAttribute("picMap", picMap);
		request.setAttribute("minPriceMap", minPriceMap);
		LocalDate now = LocalDate.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	    String nowDay = now.format(formatter);
	    String nextDay = "" + (Integer.parseInt(nowDay)+1);
		if(checkin == null) checkin = nowDay;
		checkin = checkin.replaceAll("-", "");
		if(checkout == null) checkout = nextDay;
		checkout = checkout.replaceAll("-", "");
		request.setAttribute("bu_list", list);
		request.setAttribute("checkin", checkin);
		request.setAttribute("checkout", checkout);
		request.setAttribute("ro_count", ro_count);
		return "/view/search/search.jsp";
	}
}
