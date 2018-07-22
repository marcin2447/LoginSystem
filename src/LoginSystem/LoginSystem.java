package LoginSystem;

import static java.lang.System.out;

import java.util.Scanner;


public class LoginSystem {
	static Scanner reader = new Scanner(System.in);
	
	private static void signUpMenu()
	{
        String login = "";
        String password = "";
        String phoneNumber = "";
        String Email = "";
        
    	out.println("Podaj nazwe uzytkownika (min. 3 znaki): ");
    	login = reader.nextLine();
    	out.println("1. Ustal haslo\n2. Wygeneruj automatycznie");
    	int response = reader.nextInt();
    	reader.nextLine();
    	switch(response)
    	{
    	case 1:
        	out.println("Podaj haslo (min. 8 znaków, 1 du¿a litera, 1 cyfra, 1 znak specjalny): ");
        	password = reader.nextLine();
    		break;
    	case 2:
    		password = DataValidator.GeneratePassword();
        	out.println("Twoje haslo to: " + password);
    		break;
    	}

    	out.println("Podaj numer telefonu: ");
    	phoneNumber = reader.nextLine();
    	out.println("Podaj adres email: ");
    	Email = reader.nextLine();
        
    	if(DataValidator.ValidateLogin(login) && 
    			DataValidator.ValidatePassword(password) &&
    			DataValidator.ValidatePhoneNumber(phoneNumber) &&
    			DataValidator.ValidateEmailAddress(Email))
    	{
    		DataManager.AddUser(login, password, phoneNumber, Email);
    		out.println("A new account created: " + login);
    	}
    	else
    	{
    		signUpMenu();
    	}
    	
    	mainMenu();
	}
	
	private static void loginMenu()
	{
        String login = "";
        String password = "";
    	out.println("Podaj nazwe uzytkownika: ");
    	login = reader.nextLine();
    	out.println("Podaj haslo: ");
    	password = reader.nextLine();
    	
    	if(DataManager.CheckCredentials(login, password))
    	{
    		out.println("Logged in successfully");
    		accountSettingsMenu(login);
    	}
    	else out.println("Invalid credentials");
    	
    	loginMenu();
	}
	
	private static void accountSettingsMenu(String login)
	{
    	out.println("1. Zmien numer telefonu\n"
    			+ "2. Zmien adres email");
    	int response = reader.nextInt();
    	reader.nextLine(); 
    	switch(response)
    	{
    	case 1:
    		out.println("Podaj nowy numer: ");
    		String phoneNumber = reader.nextLine();
    		DataManager.ChangeUserData(login, DataManager.DataType.PHONE_NUMBER, phoneNumber);
    		break;
    	case 2:
    		out.println("Podaj nowy adres email: ");
    		String emailAddress = reader.nextLine();
    		DataManager.ChangeUserData(login, DataManager.DataType.EMAIL, emailAddress);
    		break;
    	}
    	
    	accountSettingsMenu(login);
	}
	
	private static void mainMenu()
	{
        out.println("Wybierz funkcjê:\n1.Rejestracja\n2.Logowanie");
        int response = reader.nextInt();
        
        reader.nextLine(); 
        
        switch(response)
        {
        case 1:
        	signUpMenu();
        	break;
        case 2:
        	loginMenu();
        	break;
        }
	}
	
    public static void main(String[] args) {   	
    	mainMenu();
    }
}
