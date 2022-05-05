package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Business;
import model.Member;
import repository.AdminDao;
import repository.MemberDao;
import repository.RoomDao;

@Service
public class AdminService {
	
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
