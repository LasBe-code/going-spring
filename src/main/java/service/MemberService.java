package service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Business;
import model.Member;
import model.Picture;
import repository.MemberDao;
import repository.RoomDao;
import util.Naver_Sens_V2;

@Service
public class MemberService {

	private final MemberDao memberDao;
	private final RoomDao roomDao;

	@Autowired
	public MemberService(MemberDao memberDao, RoomDao roomDao) {
		this.memberDao = memberDao;
		this.roomDao = roomDao;
	}

	public Member getMemberOne(String email) throws Exception {
		return memberDao.selectMemberOne(email);
	}

	public Business getBusinessOne(String bu_email) throws Exception {
		return memberDao.selectBusinessOne(bu_email);
	}

	public int signupMember(Member member) throws Exception {
		return memberDao.insertMember(member);
	}

	public int signupBusiness(Business business, String picLocation) throws Exception {
		int picNum = roomDao.nextPicNum();
		int buResult = memberDao.insertBusiness(business, picNum);
		int picResult = 0;

		// 사진 등록
		String[] picList = picLocation.split("\\n");
		for (String pic : picList) {
			picResult = roomDao.insertPicture(new Picture(business.getPic_num(), pic.trim()));
			if (picResult == 0)
				return 0;
		}

		return buResult;
	}

	public int memberTelCount(String tel) throws Exception {
		return memberDao.memberTelCount(tel);
	}

	public int businessTelCount(String tel) throws Exception {
		return memberDao.businessTelCount(tel);
	}
	
	// 문자인증 코드 전송
	public String sendRandomMessage(String tel) {
		Naver_Sens_V2 message = new Naver_Sens_V2();
		Random rand = new Random();
		String numStr = "";
		for (int i = 0; i < 6; i++) {
			String ran = Integer.toString(rand.nextInt(10));
			numStr += ran;
		}
		System.out.println("회원가입 문자 인증 => " + numStr);
		
//		message.send_msg(tel, numStr);
		
		return numStr;
	}
	
	public List<Picture> getPicList(int pic_num) throws Exception{
		return memberDao.selectPic(pic_num);
	}
	
	public int modifyBusiness(Business business, String picLocation) throws Exception {
		//기존 사업자 정보 불러오기
		Business beforeBusiness = memberDao.selectBusinessOne(business.getBu_email());
		
		//비밀번호 다를 때
		if(!beforeBusiness.getBu_password().equals(business.getBu_password())) {
			return -1;
		
		//비밀번호 같을 때	
		} else {
			
		//기존 사진 전부 삭제
			roomDao.deleteLocation(beforeBusiness.getPic_num());
			
		//사진 새로 등록
			int picResult;
			String[] picList = picLocation.split("\\n");
			for (String pic : picList) {
				picResult = roomDao.insertPicture(
						new Picture(beforeBusiness.getPic_num(), pic.trim()));
				if (picResult == 0)
					return 0;
			}
		
		//사업자 업데이트
			return memberDao.updateBusiness(business);
		}
	}
}
