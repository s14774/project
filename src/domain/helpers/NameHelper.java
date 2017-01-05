package domain.helpers;

public class NameHelper {
	public static boolean check(String name) {
		//if(name.matches(".*\\d+.*"))
		if(name.matches("[A-Z][a-z]+( [A-Z][a-z]+)"))
			return true;
		return false;
	}
}
