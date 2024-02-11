package utility;

public class CheckNumbers {
	
	public <T> boolean checkNumber(T n){
		try {
			if(n instanceof Number nn) {
				return true;
			}
			if(n instanceof String s) {
				Number cn = Double.parseDouble(s);
				return true;
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

}
