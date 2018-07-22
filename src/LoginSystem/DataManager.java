package LoginSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;




public class DataManager {

	public enum DataType
	{
		LOGIN,
		PASSWORD,
		PHONE_NUMBER,
		EMAIL;
	}
	
	static String dataPath = ".\\data.txt";
	
	
	private static List<String> GetFileContent()
	{
		List<String> data = new ArrayList<String>();
		
		try
		{
			File file = new File(dataPath);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String temp;
			while ((temp = br.readLine()) != null)
			{
				data.add(temp);
			}
			br.close();
		}
		catch(IOException ie)
		{
			System.out.println("File not found");
			return null;
		}
		
		return data;	
	}
	
	public static List<String> GetData(DataType dataType)
	{
		List<String> data = new ArrayList<String>();
		GetFileContent().forEach(x -> data.add(x.split("\\s+")[dataType.ordinal()]));
		return data;
	}
	
	public static boolean AddUser(String login, String password, String phoneNumber, String Email)
	{
		String userData = login + ' ' + password + ' ' + phoneNumber + ' ' + Email;
		try {
		    Files.write(Paths.get(dataPath), (userData + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
		    return true;
		}catch (IOException e) {
			System.out.println("Couldn't write to file: " + e.getMessage());
			return false;
		}
	}
	
	public static boolean ChangeUserData(String login, DataType dataType, String updatedData)
	{
		List<String> data = GetFileContent();
		int index = GetData(DataType.LOGIN).indexOf(login);
		System.out.println("before: " + data.get(index));
		String[] bits = data.get(index).split("\\s+");
		bits[dataType.ordinal()] = updatedData;
		String newData = "";
		for(int i = 0; i < bits.length; ++i) newData += bits[i] + ' ';
		data.set(index, newData);
		System.out.println("after: " + data.get(index));
		String newContent = "";
		for(String dt : data) newContent += dt + System.lineSeparator();
		
		try {
			Files.write(Paths.get(dataPath), newContent.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
		    return true;
		}catch (IOException e) {
			System.out.println("Couldn't write to file: " + e.getMessage());
			return false;
		}
	}
	
	public static boolean CheckCredentials(String login, String password)
	{
		int lineNumber;
		if((lineNumber = GetData(DataType.LOGIN).indexOf(login)) != -1)
		{
			if(password.equals(GetData(DataType.PASSWORD).get(lineNumber)))
				return true;
		}
		
		return false;
	}
	
	
}
