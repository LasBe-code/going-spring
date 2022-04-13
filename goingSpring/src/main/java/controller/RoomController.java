package controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import model.Booking;
import model.Business;
import model.Member;
import model.Picture;
import model.Room;
import service.RoomDao;

public class RoomController extends MskimRequestMapping{

	private static Map<String, Object> map = new HashMap<String, Object>();
	
	// 객실정보 페이지
	
	@RequestMapping("roomlist")
	public String List(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		  
		String bu_email =(String)session.getAttribute("bu_email");
		
		Map<Integer, Object> map = new HashMap<>();
		
		RoomDao rd = new RoomDao();
		
		// business 메일을 사용하는 사업자의 객실 리스트 저장
		List<Room> list = rd.roomList(bu_email);
		List<Picture> picList = new ArrayList<Picture>();
		
		
		for(Room room : list) {
			picList = rd.selectPic(room.getPic_num());
			map.put(room.getRo_num(), picList.get(0).getLocation().trim());
		}
		request.setAttribute("picMap", map);
		request.setAttribute("list", list);
			
		return "/view/entrepreneur/roomlist.jsp";
	}
	
	
	// 객실등록 페이지
	@RequestMapping("roomInsert")
	public String roomInsert(HttpServletRequest request, HttpServletResponse response) {
		
		
		return "/view/entrepreneur/roomInsert.jsp";
	}
	
	@RequestMapping("roomInsertPro")
	public String roomInsertPro(HttpServletRequest request, HttpServletResponse response) {

		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
		HttpSession session = request.getSession();
		  
		String bu_email =(String)session.getAttribute("bu_email");
		 
		
		String path = getServletContext().getRealPath("/") + "/roomimgupload/";
		int size = 10 * 1024 * 1024;
		MultipartRequest multi = null;
		try {
			multi = new MultipartRequest(request, path, size, "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		RoomDao rd = new RoomDao();
		Room room = new Room();
		Picture p = new Picture();
		
		int roomNum = rd.nextRoNum();
		int picNum = rd.nextPicNum();
		
		room.setRo_num(roomNum);
		room.setRo_name(multi.getParameter("roName"));
		room.setBu_email(bu_email);
		room.setRo_price(multi.getParameter("roPrice"));
		room.setCheckin(multi.getParameter("checkIn"));
		room.setCheckout(multi.getParameter("checkOut"));
		room.setRo_count(multi.getParameter("roCount"));
		room.setRo_info(multi.getParameter("roomInfo"));
		room.setPic_num(picNum);
		
		String[] picList = multi.getParameter("picLocation").split("\\n");
		
		for (String pic : picList) {
			p = new Picture(picNum, pic.trim());
			rd.insertPicture(p);
		}
		
		
		
		// room객체에 저장된 값을 room table에 저장
		int rnum = rd.insertRoom(room);
		
		// p객체에 picture table에 저장
		
		String msg = "객실 등록시 오류가 발생했습니다.";
		String url = request.getContextPath() + "/room/roomInsert?bu_email="+bu_email;
		
		
		if(rnum > 0) {
			msg = "객실 등록이 완료되었습니다.";
			url = request.getContextPath() + "/room/roomlist?bu_email="+bu_email;
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);

		return "/view/alert.jsp";
		
	}
	
	
	@RequestMapping("roominfo")
	public String roominfo(HttpServletRequest request, HttpServletResponse response) {
		RoomDao rd = new RoomDao();
		int ro_num = Integer.parseInt(request.getParameter("ro_num"));
		Room room = rd.selectRoom(ro_num);
		
		List<Picture> picList = rd.selectPic(room.getPic_num());
		List<String> p_list = new ArrayList<String>();
		
		for(int i=0; i<picList.size();i++) {
			p_list.add(picList.get(i).getLocation());
		}
		
		
		// 선택한 객실의 정보를 가져와서 저장
		
		String info = room.getRo_info().replace("\r\n", "<br/>");
		
		request.setAttribute("p_list", p_list);
		request.setAttribute("room", room);
		request.setAttribute("ro_num", ro_num);
		request.setAttribute("pic_num", room.getPic_num());
		request.setAttribute("info", info);
		
		return "/view/entrepreneur/roominfo.jsp";
	}
	
	
	@RequestMapping("roomUpdate")
	public String roomUpdate(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		  
		String bu_email =(String)session.getAttribute("bu_email");
		 
		 
		int ro_num = Integer.parseInt(request.getParameter("ro_num"));
		int pic_num = Integer.parseInt(request.getParameter("pic_num"));
		
		RoomDao rd = new RoomDao();
		String pic = "";
		
		Room room = rd.selectRoom(ro_num);
		List<Picture> piclist = rd.selectPic(pic_num);
		
		for(Picture p : piclist) {
			pic += p.getLocation()+"\n";
		}
		
		request.setAttribute("pic_num", pic_num);
		request.setAttribute("room", room);
		request.setAttribute("ro_num", ro_num);
		request.setAttribute("pic", pic);
		
		return "/view/entrepreneur/roomUpdate.jsp";
	}
	
	
	@RequestMapping("roomUpdatePro")
	public String roomUpdatPro(HttpServletRequest request, HttpServletResponse response) {
		
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
		HttpSession session = request.getSession();
		  
		String bu_email =(String)session.getAttribute("bu_email");
		 
		
		int ro_num = Integer.parseInt(request.getParameter("ro_num"));
		int pic_num = Integer.parseInt(request.getParameter("pic_num"));
		
		String path = getServletContext().getRealPath("/") + "/roomimgupload/";
		int size = 10 * 1024 * 1024;
		MultipartRequest multi = null;
		try {
			multi = new MultipartRequest(request, path, size, "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		RoomDao rd = new RoomDao();
		Room room = rd.selectRoom(ro_num);
		int p = rd.deleteLocation(pic_num);
		
		room.setRo_name(multi.getParameter("roName"));
		room.setBu_email(bu_email);
		room.setRo_price(multi.getParameter("roPrice"));
		room.setCheckin(multi.getParameter("checkIn"));
		room.setCheckout(multi.getParameter("checkOut"));
		room.setRo_count(multi.getParameter("roCount"));
		room.setRo_info(multi.getParameter("roomInfo"));
		
		String[] picList = multi.getParameter("picLocation").split("\n");
		Picture picLocation = null;
		int pic = 0;
		for(String lo : picList) {
			picLocation = new Picture(pic_num,lo);

			pic = rd.insertPicture(picLocation);
		}
		
		// room객체에 저장된 값을 room table에 저장
		int rnum = rd.updateRoom(room);
		
		
		String msg = "객실 수정시 오류가 발생했습니다.";
		String url = request.getContextPath() + "/room/roomUpdate?ro_num="+ro_num+"&pic_num="+pic_num;
		
		
		if(rnum > 0 && pic > 0) {
			msg = "객실 수정이 완료되었습니다.";
			url = request.getContextPath() + "/room/roomlist?bu_email="+bu_email;
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		
		return "/view/alert.jsp";
	}
	
	
	@RequestMapping("roomDelete")
	public String roomDelete(HttpServletRequest request, HttpServletResponse response) {
		
		String ro_num = request.getParameter("ro_num");
		
		request.setAttribute("ro_num", ro_num);
		
		return "/view/entrepreneur/roomDelete.jsp";
	}
	
	
	@RequestMapping("roomDeletePro")
	public String roomDeletePro(HttpServletRequest request, HttpServletResponse response) {
		
		
		HttpSession session = request.getSession();
		  
		String bu_email =(String)session.getAttribute("bu_email");
		 
		
		String pwd = request.getParameter("pwd");
		String ro_num = request.getParameter("ro_num");
		
		RoomDao rd = new RoomDao();
		
		// 사업자 비밀번호 찾기
		Business business = rd.selectBu(bu_email);
		int room = 0;
		
		String msg = "객실 삭제시 오류가 발생했습니다.";
		String url = request.getContextPath() + "/room/roomDelete?ro_num="+ro_num;
		
		if(pwd == null || pwd.equals("") || !pwd.equals(business.getBu_password())) {
			msg = "비밀번호가 틀렸습니다.";
		}else {
			// 비밀번호가 일치하면 객실 삭제
			room = rd.deleteRoom(map);
		}
		
		if(room > 0) {
			msg = "객실 삭제가 완료되었습니다.";
			url = request.getContextPath() + "/room/roomlist?bu_email="+bu_email;
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		
		return "/view/alert.jsp";
	}
	
	
	
	
	
	@RequestMapping("reservation")
	public String reservation(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		 
		String bu_email =(String)session.getAttribute("bu_email");
		
		RoomDao rd = new RoomDao();		
				
		// 페이지 번호
		int pageInt;
		// 한페이지에 출력할 게시글 갯수
		int limit = 10;
		String pageNum;
		
		// pageNum을 세션에 저장해서 작업후 뒤로가기할때 바로전에 보던 페이지 출력
		if(request.getParameter("pageNum") != null){
			session.setAttribute("pageNum", request.getParameter("pageNum"));
		}
		pageNum = (String) session.getAttribute("pageNum");
		if(pageNum == null)
			pageNum = "1";
				
		pageInt = Integer.parseInt(pageNum);
				
		// -----------------------------------------------------------------------------
		// 게시글 갯수를 확인하는 메서드
		int count = rd.countBoard(bu_email);
		int boardNum = count - (pageInt - 1) *limit;
		
		int bottomLine = 3;
		int startNum = (pageInt - 1) / bottomLine * bottomLine + 1;
		int endNum = startNum + bottomLine - 1;
		
		int maxNum = (count / limit) + (count % limit == 0 ? 0 : 1);
		if(endNum > maxNum){
			endNum = maxNum;
		}
		
		// 한페이지에 출력할 게시글 rownum의 번호
		int startPage = (pageInt-1)*limit + 1;
		int endPage = (pageInt-1)*limit + limit;

		// =========== 현재 시간 ==============
		LocalDate now = LocalDate.now();
		// 포맷 정의
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		// 포맷 적용
		String nowDay = now.format(formatter);
		
		map.clear();
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("bu_email", bu_email);
		map.put("nowDay", nowDay);
		
		// 예약 내역 찾기
		List<Booking> bk = rd.selectBkList(map);
		List<Member> m = rd.selectMember(map);
		
		request.setAttribute("m", m);
		request.setAttribute("bk", bk);
		request.setAttribute("boardNum", boardNum);
		request.setAttribute("bottomLine", bottomLine);
		request.setAttribute("startNum", startNum);
		request.setAttribute("endNum", endNum);
		request.setAttribute("maxNum", maxNum);
		request.setAttribute("pageInt", pageInt);
		
		return "/view/entrepreneur/reservation.jsp";
	}
	
}
