/**
 * A terminal is responsible for printing messages to the screen,
 * and reading/returning user input.
 * There are no parameters in the constructor, and Terminal 
 * does not call on the methods of any other classes. 
 * Hence, technically, Terminals can be created anywhere, as required.
 * If desired, each class may create it's own Terminal.
 * @author Fernleigh Adams
 * @version 17/08/06
 */

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Terminal 
{
	Scanner userIn;

	/**
	 * 
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	public Terminal() 
	{
		userIn = new Scanner(System.in);
	}

	/**
	 * Obtains a string from a line of user input.
	 * Scalability: O(1)
	 * @return The text input by user.
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	public String getString()
	{
		String xyz = userIn.nextLine();
		return xyz;
	}
	
	/**
	 * Converts a line of user input into an integer.
	 * Scalability: O(1)
	 * @return The integer input by user.
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	public int getInt()
	{
		/*
		int x = Integer.parseInt(getString());
		return x;
		*/
		
		int x = 0;
		do
		{
			try
			{
				x = Integer.parseInt(getString());
				break;
			}
			catch(Exception NumberFormatException)
			{
				System.out.println("Please enter a number.");
			}
			
		}while(true);
		
		return x;
		
	}
	
	/**
	 * Requests the user inputs an ID and reads their response.
	 * Scalability: O(1)
	 * @return The 6 digit ID of a user (as an int).
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	public int receiveUserID() 
	{
		System.out.println("Please enter user ID:");
		int id = getInt();
		while (id<100000 || id>999999)
		{
			System.out.println("The number must be 6 digits long.");
			id = getInt();
		}
		return id;
	}
	
	public int receiveSeniorUserID()
	{
		System.out.println("A senior staff member needs to be allocated.");
		return receiveUserID();
	}

	/**
	 * Receives and confirms a password by asking the user to type it twice.
	 * The process will repeat itself infinitely until the user
	 * is able to enter the same password twice in a row.
	 * Scalability: O(1)
	 * @return The new password, once it has been entered twice.
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 * @see receivePassword
	 * @see confirmPassword
	 */
	public String setNewPassword()
	{
		while(true)
		{
			String newPassword = receivePassword();
			if(confirmPassword().equals(newPassword))
				return newPassword;	
		}
	}
	
	/**
	 * Requests user enter a password and reads the input
	 * Scalability: O(1)
	 * @return the typed password
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	public String receivePassword() 
	{
		System.out.println("Please enter a password:");
		return getString();
	}

	public String receiveYourPassword() 
	{
		System.out.println("Please enter your password:");
		return getString();
	}
	
	/**
	 * Requests user enter a password "again" and reads the input
	 * Scalability: O(1)
	 * @return the typed password
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	public String confirmPassword() {
		System.out.println("Please enter your password again:");
		return getString();
	}

	/**
	 * Reports that an incorrect password has been entered
	 * and offers the user a choice:
	 * 1- a re-attempt at entering the password;
	 * 2- return to the main menu.
	 * Scalability: O(1)
	 * @return A numerical value (corresponding to the user's choice)
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	public int incorrectPassword() {
		System.out.println("Your password was incorrect.");
		System.out.println("Do you wish to...");
		System.out.println("1: Re-enter the password.");
		System.out.println("2: Return to the main menu.");
		return getInt();
	}

	/**
	 * Requests a filename for a purpose.
	 * Scalability: O(1)
	 * @param action What the filename is required for.
	 * @return The filename.
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	public String setFile() 
	{
		System.out.println("Please enter a filename.");
		return getString();
	}

	public String loadUserDatabase()
	{
		System.out.println("If you wish, a previously saved user database can be loaded.");
		System.out.println("If you do not specify a valid database, a new blank database will be created.");
		String file = setFile();
		while (file == null || file.equals(""))
		{
			System.out.println("At least, you must enter a filename so that the database can be saved.");
			file = setFile();
		}
		return file;
	}
	
	public String saveUserDatabase()
	{
		System.out.println("Please select a location to which the user database will be saved.");
		return setFile();
	}
	
	public String loadMediaDatabase()
	{
		System.out.println("If you wish, a previously saved media database can be loaded.");
		System.out.println("If you do not specify a valid database, a new blank database will be created.");
		String file = setFile();
		while (file == null || file.equals(""))
		{
			System.out.println("At least, you must enter a filename so that the database can be saved.");
			file = setFile();
		}
		return file;
	}
	
	public String saveMediaDatabase()
	{
		System.out.println("Please select a location to which the media database will be saved.");
		return setFile();
	}
	
	/**
	 * Prints the menu intended for general users.
	 * There are three options:
	 * 1- Search for an item of stock.
	 * 2- Log in as a staff member.
	 * 3- Exit the program.
	 * Reads & returns the user's selection.
	 * Scalability: O(1)
	 * @return The user's selection.
	 * @author Fernleigh, Kai
	 * @version 17/08/06
	 */
	public int userMenu() 
	{
		System.out.println("\n\nWelcome to Fernleigh, Kate & Kai's Blockbuster Video Store!");
		System.out.println("Please select an option");
		System.out.println("1: Search for an item.");
		System.out.println("2: Log in as staff member.");
		System.out.println("3: Exit.");
		return getInt();
	}

	public int addStaffMenu()
	{
		System.out.println("Add a Junior or Senior staff member?");
		System.out.println("1: Junior.");
		System.out.println("2: Senior.");
		return getInt();
	}
	/**
	 * Prints the menu intended for logged in staff members.
	 * There are six options:
	 * 1- Search for an item of stock.
	 * 2- Add an item of stock to the database.
	 * 3- Remove an item of stock from the database.
	 * 4- Search for a member of staff.
	 * 5- Log out the staff member.
	 * 6- Exit the program.
	 * Reads & returns the staff member's selection.
	 * Scalability: O(1)
	 * @return The staff member's selection.
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	public int staffMenu(int id, String name) 
	{
		System.out.println("Currently logged in as " + id + " (" + name + ") ");
		System.out.println("Please select an option");
		System.out.println("1: Search for an item.");
		System.out.println("2: Add an item of stock.");
		System.out.println("3: Remove an item of stock.");
		System.out.println("4: Search for a staff member.");
		System.out.println("5: Log out staff member.");
		System.out.println("6: Exit Program.");
		System.out.println("7: Print list of stock.");
		System.out.println("8: Add staff member");
		System.out.println("9: Remove staff member");
		return getInt();
	}

	public int searchForStaffMenu()
	{
		System.out.println("Please select an option");
		System.out.println("1: Search for specific staff member.");
		System.out.println("2: Search by ID number.");
		System.out.println("3: Search by first name.");
		System.out.println("4: Search by last name.");
		return getInt();
	}
	
	public int searchForItemMenu()
	{
		System.out.println("How would you like to search?");
		System.out.println("1: By Title");
		System.out.println("2. By Title - Speed Search(Hashmap)!!");
		System.out.println("2: By Genre");
		System.out.println("3: By Year");
		System.out.println("4: By Item Type");
		System.out.println("9: Cancel");
		return getInt();
	}
	
	public int chooseItemTypeMenu()
	{
		System.out.println("Please select the type of item:");
		System.out.println("1: Video");
		System.out.println("2: DVD");
		System.out.println("3: CD");
		System.out.println("4: LP");
		System.out.println("9: Cancel");
		return getInt();
	}
	
	public int removeItemMenu()
	{
		System.out.println("Please select an option");
		return getInt();
	}
	
	public void invalidChoice() 
	{
		System.out.println("That is not a valid choice.");
	}
	
	public void invalidID() 
	{
		System.out.println("That is not a valid ID number.");
	}
	
	public void newID()
	{
		System.out.println("A random ID number has been assigned.");
	}
	public void cannotLoadDatabase() 
	{
		System.out.println("Warning: The database filename was incorrect.");
		System.out.println("The database could not be loaded.");
	}
	
	public void cannotSaveDatabase() 
	{
		System.out.println("The specified file may be corrupt or may not exist.");
		System.out.println("The database could not be saved.");
		System.out.println();
	}
	
	public void errorCorruptID(String name)
	{
		System.out.println("There is a conflict between two (or more) user IDs.");
		System.out.println("The user " + name + " could not be added to the database.");
		System.out.println("Please either:");
		System.out.println("> Re-enter the user with a different ID number, or");
		System.out.println("> Create a new user to generate a valid ID number.");
	}
	
	public void errorNoUser()
	{
		System.out.println("No such user.");
	}
	
	public void errorCannotAddUser()
	{
		System.out.println("Could not add the user to the database.");
	}
	
	public void errorCannotRemoveUser()
	{
		System.out.println("Could not delete the user from the database.");
	}
	
	public void errorCannotRemoveItem()
	{
		System.out.println("Could not delete the item from the database.");
	}
	
	public void errorUnauthorised()
	{
		System.out.println("You are not authorised to do that.");
	}
	
	public void errorNoValidUser()
	{
		System.out.println("There are no authorised users in the database.");
		System.out.println("Please add an authorised user.");
	}
	
	public void errorNoMatch()
	{
		System.out.println("No matches were found.");
	}
	
	public void errorDeleteCurrentUser()
	{
		System.out.println("Cannot delete the current user.");
	}
	
	public boolean errorDefaultUserDB()
	{
		System.out.println("The User Database could not be loaded.");
		System.out.println("The default file " + VideoStore.SAMPLEUDB + " will be used.");
		System.out.println("This will overwrite the existing file.");
		System.out.println("Is this ok? (y/n)");
		String line = getString();
		while(!line.equalsIgnoreCase("y") && !line.equalsIgnoreCase("n"))
		{
			System.out.println("Please enter yes (y) or no (n):");
			line = getString();
		}
		if (line.equalsIgnoreCase("y"))
			return true;
		else
			return false;
	}
	
	public boolean errorDefaultMediaDB()
	{
		System.out.println("The Media Database could not be loaded.");
		System.out.println("The default file " + VideoStore.SAMPLEMDB + " will be used.");
		System.out.println("This will overwrite the existing file.");
		System.out.println("Is this ok? (y/n)");
		String line = getString();
		while(!line.equalsIgnoreCase("y") && !line.equalsIgnoreCase("n"))
		{
			System.out.println("Please enter yes (y) or no (n):");
			line = getString();
		}
		if (line.equalsIgnoreCase("y"))
			return true;
		else
			return false;
	}
	
	public void errorVital()
	{
		System.out.println("The system has encountered vital errors and cannot continue.");
	}
	
	public void successfulAddition()
	{
		System.out.println("Successfully added to the database.");
	}
	/**
	 * Prints a staff member's details (IDNo, name, & phone no) 
	 * in the following format:
	 * ID - FirstName Surname - Phone Number
	 * Scalability: O(1)
	 * @param u The staff member whose details will be printed
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 * @see Staff
	 */
	public void printStaffDetails(Staff u)
	{
		System.out.print(u.getIDNo());
		System.out.print("  -  ");
		System.out.print(u.getFirstName() + " " + u.getSurname());
		System.out.print("  -  ");
		System.out.println(u.getPhone());
	}
	
	/**
	 * Prints the details of all staff members in a given ArrayList.
	 * Scalability: O(n)
	 * @param aList The list of staff whose details will be printed.
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 * @see printStaffDetails
	 */
	public void printStaffSearch(ArrayList<Staff> aList)
	{
		printStaffSearchHeader();
		for (Staff s : aList)
			printStaffDetails(s);
	}
	
	private void printStaffSearchHeader()
	{
		System.out.println("ID Number - First Name - Surname - Phone Number");
	}
	
	public void printSingleStaffSearch(Staff single)
	{
		printStaffSearchHeader();
		printStaffDetails(single);
	}
	
	public void printNoMatchingUserError()
	{
		System.out.println("No matching users could be found.");
	}
	
	/*
     * Kate Grabowski 02/09/06
     * Prints out a specific media object's information.
     * O(1)
     * @ param m
     **/         
    public void printInfo(Media m)
    {
        System.out.println("ID = " + m.getId());
        System.out.println("	Title = " + m.getTitle());
        System.out.println("	Year = " + m.getYear());
        System.out.println("	Genre = " + m.getGenre());
        System.out.println("	Media Type = " + m.getMediaType());
    }
	
    public void printObjectArray(List<Media> list)
    {
    	for (Media m : list)
    		printInfo(m);
    }
    
	public String receiveFirstName()
	{
		System.out.println("Please enter a first name.");
		return getString();
	}
	
	public String receiveLastName()
	{
		System.out.println("Please enter a last name.");
		return getString();
	}
	
	public int receivePhoneNumber()
	{
		System.out.println("Please enter a phone number.");
		return getInt();
	}
	
	public String getTitle()
	{
		System.out.println("Please enter a title : ");
		return getString();
	}

	public String getGenre()
	{
		System.out.println("Please enter a genre: ");
		return getString();
	}
	
	public int getYear()
	{
		System.out.println("Please enter a year: ");
		return getInt();
	}
	
	public String getMediaType()
	{
		switch (chooseItemTypeMenu())
		{
		case 1:
			return "vhs";
		case 2:
			return "dvd";
		case 3:
			return "cd";
		case 4:
			return "lp";
		default:
			return VideoStore.EMPTYSTR;
			
		}
	}
	
	public String getArtist()
	{
		System.out.println("Please enter an artist: ");
		String line = getString();
		while(line.length()==0)
		{
			System.out.println("Please enter an artist:");
			line = getString();
		}
		return line;
	}
	
	public String getStudio()
	{
		System.out.println("Please enter a studio: ");
		return getString();
	}
	
	public String getClassification()
	{
		System.out.println("Please enter a classification (G, PG, M, MA, or R) (use uppercase): ");
		String line = getString();
		while((!line.equals("G"))&&(!line.equals("PG"))&&(!line.equals("M"))&&(!line.equals("MA"))&&(!line.equals("R")))
		{
			System.out.println("Please enter either G,PG,M,MA,R in uppercase only!");
			line = getString();
		}
		return line;
	}
	
	public String getDirector()
	{
		System.out.println("Please enter a director: ");
		String line = getString();
		while(line.length()==0)
		{
			System.out.println("Please enter a director:");
			line = getString();
		}
		return line;
	}
	
	public String getFormat()
	{
		System.out.println("Please enter a format (PAL or NTSC in uppercase): ");
		String line = getString();
		while((!line.equals("PAL"))&&(!line.equals("NTSC")))
		{
			System.out.println("Please enter either PAL, NTSC in uppercase only.");
			line = getString();
		}
		return line;
	}
	
	public String getFeatures()
	{
		System.out.println("Does the DVD have special features?");
		System.out.println("Please enter YES/NO (upper case): ");
		String line = getString();
		while((!line.equals("YES"))&&(!line.equals("NO")))
		{
			System.out.println("Please enter YES/NO (upper case): ");
			line = getString();
		}
		return line;
	}
	
	public String getLayer()
	{
		System.out.println("Please enter the layer type (SINGLE or DOUBLE [uppercase]: ");
		String line = getString();
		while((!line.equals("SINGLE"))&&(!line.equals("DOUBLE")))
		{
			System.out.println("Please enter either SINGLE, DOUBLE in uppercase only");
			line = getString();
		}
		return line;
	}
	
	public int getLength()
	{
		System.out.println("Please enter a length: ");
		return getInt();
	}
	
	public int getRegion()
	{
		System.out.println("Please enter the regional code: ");
		return getInt();
	}
	
	public int getScenes()
	{
		System.out.println("Please enter the number of scenes: ");
		return getInt();
	}
	
	public int getTracks()
	{
		System.out.println("Please enter the number of tracks: ");
		return getInt();
	}
	
	public int getSongs()
	{
		System.out.println("Please enter the number of songs: ");
		return getInt();
	}
	
	public int getID()
	{
		System.out.println("Please enter an id number: ");
		return getInt();
	}
	
	public int getDuration()
	{
		System.out.println("Please enter the duration (in minutes): ");
		return getInt();
	}
	
	public void invalidSearch()
	{
		System.out.println("Could not process your search");
	}
	public void exitMessage()
	{
		System.out.println("Exiting...Have a good day.");
	}
	
}
