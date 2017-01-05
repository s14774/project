package domain.helpers;

public class PeselHelper {
	public static boolean check(String correctPesel) {
		if(!correctPesel.matches("[0-9]{11}")){			
			return false;
		}
		
		int a = 0;
		int sum = 0;
		int[] weights = {1, 3, 7, 9};
		int peselControl = 0;
		
		for(char number: correctPesel.toCharArray()){
			int n = Character.getNumericValue(number);
			
			if (a == 10){
				peselControl = n; 
				break;
			} else {
				sum += n * weights[a%4];
				a++;
			}			
		}
		int control = (10 - (sum % 10)) % 10;		
		if(control != peselControl)
			return false;
		
		return true;
	}
}
