package service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import model.Room;
import repository.MemberDao;
import repository.RoomDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml")
public class RoomDaoTest {
	
	@Autowired
	RoomDao rd;
	
	@Autowired
	MemberDao memberDao;
	
	@Test
	public void testSelectRoom() {
		Room room = rd.selectRoom(3);
		System.out.println("testSelectRoom");
		
		int result = memberDao.businessTelCount("01000001111");
		
		assertTrue(result == 0);
	}
	
	@Test
	public void memberDaoTest() {
		assertTrue(memberDao.businessTelCount("0") == 12);
	}

}
