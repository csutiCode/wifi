package verwaltung.test;
import verwaltung.common.Validator;

public class ValidatorTest {

	public static void main(String[] args) {
		
		
		Validator validator = new Validator();
		boolean email = validator.validateEmail("erTTTTTT.no@gmail.com");
		System.out.println(email);
		

	}

}
