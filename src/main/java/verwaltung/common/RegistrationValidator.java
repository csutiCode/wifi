package verwaltung.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
//methoden, die in gewissen Eingabefelder eingetragene Daten kontrollieren
public class RegistrationValidator {
	
	//Regex für E-mail
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
			Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	//Regex für Telefonnummer
	public static final Pattern VALID_PHONE_REGEX = 
			Pattern.compile("^\\+(?:[0-9] ?){6,14}[0-9]$");
		
	public static boolean validateEmail(String email) {
			Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
	        return matcher.matches();
	}
	public static boolean validatePhone(String phone) {
			Matcher matcher = VALID_PHONE_REGEX.matcher(phone);
			return matcher.matches();
			
	}
}		

