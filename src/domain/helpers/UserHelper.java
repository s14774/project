package domain.helpers;

public class UserHelper {
	public static boolean checkLogin(String login) {
		if(login.contains(" "))
			return false;
		return true;
	}
	
	public static boolean checkPassword(String password) {
		if(password.length() < 8)
			return false;
		return true;
	}
}
