package service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import repository.MemberDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml")
public class MemberServiceTest {
	
	@Autowired
	MemberDao memberDao;

	@Test
	public void testGetMemberOne() throws Exception {
		System.out.println(memberDao.selectMemberOne("aaa@naver.com"));
	}
}
