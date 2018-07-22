package LoginSystem;

import java.util.Random;

public class DataValidator {

	static String specialChars = "!@#$%^&*()_+{}|:<>?";
	static int minLoginLength = 3;
	static int minPwdLength = 8;
	
	public static boolean ValidateLogin(String login)
	{	
		if(login.length() < minLoginLength)
		{
			System.out.println("Login jest za krótki.");
			return false;
		}
		
		if(DataManager.GetData(DataManager.DataType.LOGIN).contains(login))
		{
			System.out.println("Login jest ju¿ zajêty.");
			return false;
		}
		
		return true;
	}
	
	public static boolean ValidatePassword(String password)
	{
		if(password.length() < minPwdLength)
		{
			System.out.println("Has³o jest za krótkie.");
			return false;
		}
		
		boolean hasCapital = false;
		boolean hasDigit = false;
		boolean hasSpecialChar = false;
		
		for(int i = 0; i < password.length(); ++i)
		{
			char c = password.charAt(i);
			if(Character.isDigit(c)) hasDigit = true;
			if(Character.isUpperCase(c)) hasCapital = true;
			if(specialChars.indexOf(c) != -1) hasSpecialChar = true;
		}

		if(hasCapital && hasDigit && hasSpecialChar) return true;
		else 
		{
			System.out.println("Has³o nie posiada minimum 1 cyfry,"
					+ "1 du¿ej litery i 1 znaku specjalnego.");
			return false;
		}
	}
	
	public static boolean ValidateEmailAddress(String address)
	{
		boolean hasAt = false;
		boolean hasDot = false;
		
		if(address.length() < 7) return false; // Domena 2 znaki, @. 2 znaki, 2 znaki serwera (np. gmail. o2), min. 1 znak adresu
		
		for(int i = 0; i < address.length(); ++i)
		{
			char c = address.charAt(i);
			if(c == '@') hasAt = true;
			if(c == '.') hasDot = true;
		}
		
		return (hasAt && hasDot);
	}
	
	public static boolean ValidatePhoneNumber(String PhoneNumber)
	{
		for(int i = 0; i < PhoneNumber.length(); ++i)
		{
			char c = PhoneNumber.charAt(i);
			if(!Character.isDigit(c) && c != '+') return false;
		}
		return true;
	}
	
	public static String GeneratePassword()
	{
		String result = "";
		Random r = new Random();
		String letters = "qwertyuiopasdfghjklzxcvbnm";
		String digits = "123567890";
		for(int i = 0; i < 16; ++i)
		{
			if(i%8 == 0)
			{
				result += specialChars.charAt(r.nextInt(specialChars.length()));
				continue;
			}
			if(i%3 == 0)
			{
				result += digits.charAt(r.nextInt(digits.length()));
				continue;
			}
			
			if(i%2 == 0)
			{
				result += Character.toUpperCase(letters.charAt(r.nextInt(letters.length())));
			}
			else result += letters.charAt(r.nextInt(letters.length()));
		}
		return result;
	}
	
	
}


