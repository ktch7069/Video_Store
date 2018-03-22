import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This class handles all staff members (and customers) registered 
 * in the video store. 
 * Enables operations such as adding/removing a staff member from the list.
 * @author Fernleigh Adams 
 * @version 17/08/06
 */

public class UserDatabase
{
	private ArrayList<Staff> staff = new ArrayList<Staff>();
	private Random random = new Random();
	private String userDbaseName;

	/**
	 * Constructs a user database. 
	 * Will still attempt to fill the database by asking the user 
	 * for a filename.
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	public UserDatabase(String aFile) throws FileNotFoundException, InvalidIDException
	{
		fillDatabase(aFile);
		userDbaseName = aFile;
	}
	
	public UserDatabase()
	{
	}
	
	/**
	 * @author Fernleigh
	 */
	public boolean checkForValidUser()
	{
		for (Staff s : staff)
		{
			if (s instanceof SeniorStaff)
				return true;
		}
		return false;
		
	}
	
	/**
	 * Creates a unique 6-digit ID number.
	 * Scalability: O(n)
	 * @return A unique ID number.
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	private int assignID() 
	{
		int n = 0;
		do {
			n = random.nextInt(899999);
		} while (matchIDNo(n + 100000));
		return n + 100000;
	}

	/**
	 * Given details (name + phone number) about a staff member, 
	 * will assign them an ID number, set a password and add them to the database.
	 * Scalability: O(n)
	 * @param aFirstName The first name of the staff member.
	 * @param aLastName The lase name of the staff member.
	 * @param aPhoneNumber The staff member's phone number.
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	public void addNewSeniorStaff(String aFirstName, String aLastName,
			int aPhoneNumber, String aPassword) throws FileNotFoundException
	{
		int id = assignID();
		staff.add(new SeniorStaff(id, aFirstName, aLastName, aPhoneNumber,
				aPassword));
		
		PrintWriter writer = new PrintWriter(userDbaseName);
		writer.append("s ");
		writer.append(id + " ");
		writer.append(aFirstName + " ");
		writer.append(aLastName + " ");
		writer.append(aPhoneNumber + " ");
		writer.append(aPassword + " ");
		writer.close();
		
	}

	/**
	 * @author Fernleigh
	 */
	public void addNewJuniorStaff(String aFirstName, String aLastName,
			int aPhoneNumber, String aPassword, SeniorStaff aSupervisor) throws FileNotFoundException
	{
		int id = assignID();
		staff.add(new JuniorStaff(id, aFirstName, aLastName, aPhoneNumber,
				aPassword, aSupervisor));
		PrintWriter writer = new PrintWriter(userDbaseName);
		writer.append("j ");
		writer.append(id + " ");
		writer.append(aFirstName + " ");
		writer.append(aLastName + " ");
		writer.append(aPhoneNumber + " ");
		writer.append(aPassword + " ");
		writer.append(aSupervisor.getIDNo() + " ");
		writer.close();
	}
	
	/**
	 * Will add a staff member with a ser ID number and password to the database.
	 * Scalability: O(1)
	 * @param anID The staff member's pre existing ID number.
	 * @param aFirstName The first name of the staff member.
	 * @param aLastName The lase name of the staff member.
	 * @param aPhoneNumber The staff member's phone number.
	 * @param aPassword The staff member's predefined password.
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	public void addStaff(int anID, String aFirstName, String aLastName,
			int aPhoneNumber, String aPassword) throws InvalidIDException, FileNotFoundException
	{
		if (matchIDNo(anID))
		{
			anID = assignID();
			throw new InvalidIDException(aFirstName + aLastName);
		}
		staff.add(new SeniorStaff(anID, aFirstName, aLastName, aPhoneNumber,
				aPassword));
		PrintWriter writer = new PrintWriter(userDbaseName);
		writer.append("s ");
		writer.append(anID + " ");
		writer.append(aFirstName + " ");
		writer.append(aLastName + " ");
		writer.append(aPhoneNumber + " ");
		writer.append(aPassword + " ");
		writer.close();
	}
	
	/**
	 * @author Fernleigh
	 */
	public void addJuniorStaff(int anID, String aFirstName, String aLastName,
			int aPhoneNumber, String aPassword, SeniorStaff aSupervisor) throws InvalidIDException, FileNotFoundException
	{
		if (matchIDNo(anID))
		{
			anID = assignID();
			throw new InvalidIDException(aFirstName + aLastName);
		}
		staff.add(new JuniorStaff(anID, aFirstName, aLastName, aPhoneNumber,
				aPassword, aSupervisor));
		PrintWriter writer = new PrintWriter(userDbaseName);
		writer.append("j ");
		writer.append(anID + " ");
		writer.append(aFirstName + " ");
		writer.append(aLastName + " ");
		writer.append(aPhoneNumber + " ");
		writer.append(aPassword + " ");
		writer.append(aSupervisor.getIDNo() + " ");
		writer.close();
	}
	
	/**
	 * @author Fernleigh
	 */
	public void removeStaff(Staff x) throws FileNotFoundException, Exception
	{
		staff.remove(x);
		saveDatabase();
	}
	
	/**
	 * Checks to see if an ID number is already taken by a staff member.
	 * Scalability: O(n)
	 * @param anID The ID number to be matched
	 * @return True if the ID number has a match, False if it is unique.
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	private boolean matchIDNo(int anID) 
	{
		boolean matched = false;
		for (Staff s : staff) 
		{
			if (s.matchIDNo(anID))
			{
				matched = true;
				break;
			}	
		}
		return matched;

	}

/**
 * Fills the database with staff members whose details come from a specified file.
 * Scalability: O(n)
 * @param aDBFile The file holding the staff details.
 * Note that the file format must be as follows:
 * @param Line1: Staff ID number
 * @param Line2: Staff's first name
 * @param Line3: Staff's last name
 * @param Line4: Staff's phone number
 * @param Line5: Staff's password
 * @throws FileNotFoundException
 * @author Fernleigh Adams
 * @version 17/08/06
 */
	public void fillDatabase(String aDBFile) throws FileNotFoundException, InvalidIDException
	{
		FileReader reader = new FileReader(aDBFile);
		Scanner in = new Scanner(reader);
		while (in.hasNextLine()) 
		{
			String staffDetails = in.nextLine();
			Scanner line = new Scanner(staffDetails);
			String type = line.next();
			int id = line.nextInt();
			String fn = line.next();
			String sn = line.next();
			int ph = line.nextInt();
			String pw = line.next();
			if (type.equalsIgnoreCase("s"))
			{
				addStaff(id, fn, sn, ph, pw);
			}
			else if (type.equalsIgnoreCase("j"))
			{
				int supID = line.nextInt();
				try
				{
					SeniorStaff supervisor = convertToSenior(searchByID(supID));
					addJuniorStaff(id, fn, sn, ph, pw, supervisor);
				}
				catch (NoMatchException x)
				{
					throw new FileNotFoundException();
				}
			}
			line.close();
		}
		in.close();
		
		try
		{
			reader.close();
		}
		catch (Exception x)
		{
			throw new FileNotFoundException();
		}
		
		
	}

	/**
	 * Saves details about staff members in a database to a file.
	 * Note that the information is saved in an appropriate format
	 * so the database can be loaded by the fillDatabase method.
	 * Scalability: O(n)
	 * @param aFile The file where the information will be saved.
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	public void saveDatabase() throws FileNotFoundException
	{
		PrintWriter writer = new PrintWriter(userDbaseName);
		
		for (Staff u : staff) 
		{
			if (u instanceof SeniorStaff)
			{
				writer.print("s ");
				u = (SeniorStaff)u;
			}
			else if (u instanceof JuniorStaff)
			{
				u = (JuniorStaff)u;
				writer.print("j ");
			}
			writer.print(u.getIDNo() + " ");
			writer.print(u.getFirstName() + " ");
			writer.print(u.getSurname() + " ");
			writer.print(u.getPhone() + " ");
			writer.print(u.getPassword() + " ");
			if (u instanceof JuniorStaff)
				writer.println(((JuniorStaff)u).getSupervisor().getIDNo());
			else
				writer.println();
		}
		writer.close();
	}

	/**
	 * Searches for staff members with a certain first name.
	 * Scalability: O(n)
	 * @param aFirstName The first name to be searched for.
	 * @return An ArrayList of staff with the specified first name.
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	public ArrayList<Staff> searchByFirstName(String aFirstName) throws NoMatchException
	{
		ArrayList<Staff> match = new ArrayList<Staff>();
		for (Staff s : staff)
			if (s.getFirstName().equalsIgnoreCase(aFirstName)) {
				match.add(s);
			}
		if (match.size()==0)
			throw new NoMatchException();
		return match;
	}

	/**
	 * Searches for staff members with a certain surname.
	 * Scalability: O(n)
	 * @param aSurname The first name to be searched for.
	 * @return An ArrayList of staff with the specified surname.
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	public ArrayList<Staff> searchBySurname(String aSurname) throws NoMatchException
	{
		ArrayList<Staff> match = new ArrayList<Staff>();
		for (Staff s : staff)
			if (s.getSurname().equalsIgnoreCase(aSurname)) {
				match.add(s);
			}
		if (match.size()==0)
			throw new NoMatchException();
		return match;
	}

	/**
	 * Searches for staff members with a certain phone number.
	 * Scalability: O(n)
	 * @param aPhone The phone number to be searched for.
	 * @return An ArrayList of staff with the specified phone number.
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	public ArrayList<Staff> searchByPhone(int aPhone) throws NoMatchException
	{
		ArrayList<Staff> match = new ArrayList<Staff>();
		for (Staff s : staff)
			if (s.getPhone() == aPhone) 
			{
				match.add(s);
			}
		if (match.size()==0)
			throw new NoMatchException();
		return match;
	}

	/**
	 * Searches for staff with a specified ID number.
	 * As each ID is unique, a maximum of one staff member will be returned.
	 * Scalability: O(n)
	 * @param anID The ID number to be searched for.
	 * @return The staff member with the matching ID.
	 * @throws InvalidIDException If no staff members have the specified ID number.
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 * @see Staff
	 */
	public Staff searchByID(int anID) throws NoMatchException
	{
		Staff match = null;
		for (Staff s : staff)
			if (s.getIDNo() == anID) 
			{
				match = s;
			}
		if (match == null)
		{
			throw new NoMatchException();
		}
		return match;
	}

	/**
	 * @author Fernleigh
	 */
	public SeniorStaff convertToSenior(Staff sta)
	{
		if (sta instanceof SeniorStaff)
			return (SeniorStaff)sta;
		else return null;
	}
	/**
	 * Searches for a user with an exact 
	 * first name, last name, and phone number.
	 * Scalability: O(n)
	 * @param aFirstName The first name of the user to be found.
	 * @param aLastName The last name of the user to be found.
	 * @param aPhoneNumber The phone number of the user to be found.
	 * @return The staff member with the matching details.
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	public Staff searchIdenticalUser(String aFirstName, String aLastName,
			int aPhoneNumber) throws NoMatchException
	{
		Staff matchingUser = null;
		for (Staff s : staff) {
			if (s.getSurname().equalsIgnoreCase(aLastName))
				if (s.getFirstName().equalsIgnoreCase(aFirstName))
					if (s.getPhone() == aPhoneNumber)
						matchingUser = s;
		}
		if (matchingUser == null)
			throw new NoMatchException();
			
		return matchingUser;
	}

	/**
	 * @author Fernleigh
	 */
	public ArrayList<Staff> allUsers()
	{
		return staff;
	}
}
