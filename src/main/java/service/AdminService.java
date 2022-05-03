package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Member;
import repository.MemberDao;
import repository.RoomDao;

@Service
public class AdminService {
	
	private final MemberDao memberDao;
	private final RoomDao roomDao;

	@Autowired
	public AdminService(MemberDao memberDao, RoomDao roomDao) {
		this.memberDao = memberDao;
		this.roomDao = roomDao;
	}
	
	public Boolean adminLogin(String email, String password) throws Exception{
		Member member = memberDao.selectMemberOne(email);
		if(member.getEmail().equals(email) && member.getPassword().equals(password))
			if(member.getAdmin_check().equals("1"))
				return true;
		
		return false;
	}

}
