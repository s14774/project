package test.java;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import domain.helpers.UserHelper;

public class UserHelperTest {
	@Test
	public void checkLoginTest() {
		String uncorrectLogin = "lasdasdog inasdasd";
		boolean isCorrect = UserHelper.checkLogin(uncorrectLogin);
		assertFalse(isCorrect);
	}
	
	@Test
	public void checkPasswordMinimumLengthTest() {
		String uncorrectPassword = "qweasd1";
		boolean isCorrect = UserHelper.checkPassword(uncorrectPassword);
		assertFalse(isCorrect);
	}
}
