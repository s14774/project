package domain.helpers;

public class NameHelper {
	public static boolean checkName(String name) {
		if(name.matches("[A-Z][a-z]+( [A-Z][a-z]+)"))
			return true;
		return false;
	}
	
	public static boolean checkSurname(String surname) {
		if(surname.matches("^[A-Z][a-z]+(?:|(?: |-)[A-Z][a-z]+)$"))
			return true;
		return false;
	}
}
