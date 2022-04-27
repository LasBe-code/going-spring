package service;

import static org.junit.Assert.*;

import org.junit.Test;

import repository.MemberDao;

public class MybatisTest2 {

	@Test
	public void testMemberTelCount() {
		MemberDao md = new MemberDao();
		assertEquals(1, md.memberTelCount("01027194917"));
	}

}
