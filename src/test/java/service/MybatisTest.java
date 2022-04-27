package service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import repository.MemberDao;
import repository.TestDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml")
public class MybatisTest {
	@Autowired
	TestDao testDao;
	
	@Test
	public void test() throws Exception {
		int result = testDao.memberTelCount("01027194917");
		assertTrue(result == 1);
		System.out.println(result);
	}
	
}
