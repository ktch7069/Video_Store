/**
 * The Video Store handles both User and Video databases.
 * It is responsible for completing tasks set by a user through menus.
 * Note: Actual printing of menus/messages and reading user input is 
 * handled by the Terminal.
 * A Terminal is created in the constructor of the Video Store.
 * @author Fernleigh Adams
 * @version 17/08/06
 * @see Terminal
 * @see UserDatabase
 *
 */

//import java.util.Scanner;
import java.io.*;

public class VideoStore 
{
	private Terminal t;
	private UserDatabase userDatabase;
	private MediaDatabase m;
	private Staff currentUser;
	public static final String EMPTYSTR = "SOMETHINGSILLYv9pwned";
	public static final int EMPTYINT = -1;
	public static final String SAMPLEUDB = "SAMPLEUDB.txt";
	public static final String SAMPLEMDB = "SAMPLEMDB.txt";
	public static final String SAMPLELOG = "SAMPLELOG.txt";
	/*
	 * Access Levels: 
	 * 0 = exit program 
	 * 1 = general user 
	 * 2 = staff member
	 */
	private int accessLevel = 1;

	/**
	 * @author Fernleigh
	 */
	public VideoStore() 
	{
			t = new Terminal();
			userDatabase = instantiateUserDatabase(t.loadUserDatabase());
			m = instantiateMediaDatabase(t.loadMediaDatabase());
	}

	/**
	 * Calls the terminal to print the menu intended for general users.
	 * There are three options:
	 * 1- Search for an item of stock.
	 * 2- Log in as a staff member.
	 * 3- Exit the program.
	 * Scalability: O(n)
	 * @author 
	 * @version 17/08/06
	 */
	public void userMenu() 
	{
		int userChoice = t.userMenu();
		switch (userChoice) {
		case 1:
			searchForItemMenu();
			break;
			
		case 2:
			if (userDatabase.allUsers().size()==0)
				addAStaff();
			else if (logInStaff())
				accessLevel = 2;
			break;
		case 3:
			accessLevel = 0;
			return;
		default:
			t.invalidChoice();
			break;
		}
	}

	/**
	 * Calls the terminal to print the menu intended for users who are members of staff.
	 * There are six options:
	 * 1- Search for an item of stock.
	 * 2- Add an item of stock to the database.
	 * 3- Remove an item of stock from the database.
	 * 4- Search for a member of staff.
	 * 5- Log out the staff member.
	 * 6- Exit the program.
	 * 7- Print a list of stock
	 * 8- New staff member
	 * 9- Delete staff member
	 * Scalability: O(1)
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	public void staffMenu() 
	{
		int staffChoice = t.staffMenu(currentUser.getIDNo(), currentUser.getFirstName() + " " + currentUser.getSurname());
		switch (staffChoice) 
		{
		case 1:
			searchForItemMenu();
			break;
		case 2:
			addAnItem();
			break;
		case 3:
			removeAnItem();
			break;
		case 4:
			searchForStaffMenu();
			break;
		case 5:
			accessLevel = 1;
			break;
		case 6:
			accessLevel = 0;
			break;
		case 7:
			try
			{
				t.printObjectArray(m.getMediaDB());
			}
			catch(NullPointerException e)
			{
				t.errorNoMatch();
			}
				break;
		case 8:
			addAStaff();
			break;
		case 9:
			int id = t.receiveUserID();
			if (id == currentUser.getIDNo())
			{
				t.errorDeleteCurrentUser();
				break;
			}
			else
			{
				try
				{
					userDatabase.removeStaff(userDatabase.searchByID(id));
				}
				catch (NoMatchException e)
				{
					t.errorNoUser();
				}
				catch (Exception a)
				{
					t.errorCannotRemoveUser();
				}
			}
		default:
			t.invalidChoice();
			break;
		}

	}
	/**
	 * 1: Search for staff member.
	 * 2: Search by ID number.
	 * 3: Search by first name.
	 * 4: Search by last name.
	 *
	 */
	public void searchForStaffMenu()
	{
		try
		{
			int choice = t.searchForStaffMenu();
			switch(choice)
			{
			case 1: t.printSingleStaffSearch(userDatabase.searchIdenticalUser(t.receiveFirstName(), t.receiveLastName(), t.receivePhoneNumber())); break;
			case 2: t.printSingleStaffSearch(userDatabase.searchByID(t.receiveUserID())); break;
			case 3: t.printStaffSearch(userDatabase.searchByFirstName(t.receiveFirstName())); break;
			case 4: t.printStaffSearch(userDatabase.searchBySurname(t.receiveLastName())); break;
			}
		}
		catch (NoMatchException noResult)
		{
			t.errorNoMatch();
		}
	}
	/**
	 * Checks the appropriate menu to call, then calls that method.
	 * Scalability: O(1)
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	public void callMenu() 
	{
		if (accessLevel == 1)
			userMenu();
		else if (accessLevel == 2)
			staffMenu();
	}

	/**
	 * Attempts to log in a staff member.
	 * Will ask for an ID number (and check the input is a valid ID number).
	 * Will ask for the password (and check the input is the correct password).
	 * If both values are correct, the user is logged in.
	 * Scalability: O(n)
	 * @return true If stall member was successfully logged in
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	public boolean logInStaff() 
	{
		try
		{
			int uID = t.receiveUserID();
			Staff theUser = userDatabase.searchByID(uID);
			String theUserPassword = t.receivePassword();
			while (!theUser.matchPassword(theUserPassword)) 
			{
				switch (t.incorrectPassword()) 
				{
				case 1:
					theUserPassword = t.receivePassword();
					break;
				case 2:
					return false;
				default:
					t.invalidChoice();
					break;
				}
			}
			if (theUser.matchPassword(theUserPassword))
			{
				currentUser = theUser; 
				return true;
			}
			else
				return false;
		}
		catch (Exception InvalidIDException)
		{
			t.invalidID();
			return false;
		}
	}

	/*
	 * 1 = title
	 * 2 = genre
	 * 3 = year
	 * 4 = item type
	 * 9 = cancel
	 */
	public void searchForItemMenu()
	{
		try
		{
			switch(t.searchForItemMenu())
			{
			case 1:
				t.printObjectArray(m.searchByTitle(t.getTitle())); break;
			case 2 :
				
				 m.searchSingleTitleByName(t.getTitle()); break;
			case 3:
				t.printObjectArray(m.searchByGenre(t.getGenre())); break;
			case 4:
				t.printObjectArray(m.searchByYear(t.getYear())); break;
			case 5:
				String type = t.getMediaType();
				if (type.equals(EMPTYSTR))
					break;
				else
					t.printObjectArray(m.searchByMediaType(type));
			case 9:
				return;
			default:
				t.invalidChoice();
				return;
			}
		}
		catch(NoMatchException noData)
		{
			t.errorNoMatch();
		}
	}
	
	/*
	public void searchForSomething(int x)
	{
		String searchingTitle = EMPTYSTR;
		int searchingYear = EMPTYINT;
		String searchingGenre = EMPTYSTR;
		searchingTitle = t.getTitle();
		searchingYear = t.getYear();
		searchingGenre = t.getGenre();
		switch(x){
		case 1: searchForCD(searchingTitle, searchingYear, searchingGenre); break;
		case 2: searchForLP(searchingTitle, searchingYear, searchingGenre); break;
		case 3: searchForDVD(searchingTitle, searchingYear, searchingGenre); break;
		case 4: searchForVHS(searchingTitle, searchingYear, searchingGenre); break;
		case 5: searchForGeneric(searchingTitle, searchingYear, searchingGenre); break;
		default: break;
		}
	}
	*/
	
	public void addAStaff()
	{
		switch(t.addStaffMenu())
		{
		case 1:
			newJuniorStaff();
			break;
		case 2:
			newSeniorStaff();
			break;
		default:
			t.invalidChoice();
			break;
		}
	}
	
	/**
	 * @author Fernleigh
	 */
	public void addAnItem()
	{
		switch (t.chooseItemTypeMenu())
		{
		case 1: 
			Vhs aVhs = newVideo();
			m.addVhs(aVhs);
			try
			{
				m.writeVhsToDisk(aVhs);
			}
			catch (Exception b)
			{
				t.cannotSaveDatabase();
			}
			break;
		case 2:
			DVD aDvd = newDVD();
			m.addDVD(aDvd);
			try
			{
				m.writeDvdToDisk(aDvd);
			}
			catch (Exception b)
			{
				t.cannotSaveDatabase();
			}
			break;
		case 3:
			Cd aCd = newCd();
			m.addCd(aCd);
			try
			{
				m.writeCdToDisk(aCd);
			}
			catch (Exception c)
			{
				t.cannotSaveDatabase();
			}
			break;
		case 4:
			Lp aLp = newLp();
			m.addLp(aLp);
			try
			{
				m.writeLpToDisk(aLp);
			}
			catch (Exception d)
			{
				t.cannotSaveDatabase();
			}
			break;
		case 9:
			break;
		default:
			t.invalidChoice();
			break;
		}
	}
	
	/**
	 * @author Fernleigh
	 */
	public void removeAnItem()
	{
		try
		{
			switch (t.chooseItemTypeMenu())
			{
			case 1: 
				m.removeVhs((Vhs)(m.searchById(t.getID())));
				break;
			case 2:
				m.removeDVD((DVD)(m.searchById(t.getID())));
				break;
			case 3:
				m.removeCd((Cd)(m.searchById(t.getID())));
				break;
			case 4:
				m.removeLp((Lp)(m.searchById(t.getID())));
				break;
			case 9:
				break;
			default:
				t.invalidChoice();
				break;
			}
		}
		catch (NoMatchException e)
		{
			t.errorCannotRemoveItem();
		}
	}
	
	
	/**
	 * The basic method of the program.
	 * Continuously calls a menu until asked to terminate.
	 * When asked to terminate, will ask user for a file to back up the database to.
	 * Scalability: O(n)
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	public void run() 
	{
		while (accessLevel != 0) 
		{
			if (!userDatabase.checkForValidUser())
			{
				System.out.println(userDatabase.checkForValidUser());
				t.errorNoValidUser();
			}
			callMenu();
		}
		backupAllDatabases();
		t.exitMessage();
		System.exit(0);
	}
	
	/**
	 * @author Fernleigh
	 */
	public void backupAllDatabases()
	{
		try
		{
			userDatabase.saveDatabase();
		}
		catch(Exception FileNotFoundException)
		{
			t.cannotSaveDatabase();
		}
	}
	
	/**
	 * @author Fernleigh
	 */
	public UserDatabase instantiateUserDatabase(String aFile)
	{
		UserDatabase uDB = null;
		try
		{
			uDB = new UserDatabase(aFile);	
		}
		catch (InvalidIDException idEx)
		{
			t.errorCorruptID(idEx.getName());
		}
		catch (Exception e)
		{
			t.cannotLoadDatabase();
			uDB = new UserDatabase();
		}
		return uDB;
	}
	
	/**
	 * @author Fernleigh
	 */
	public MediaDatabase instantiateMediaDatabase(String aFile)
	{
		MediaDatabase mDB = null;
		try
		{
			mDB = new MediaDatabase(aFile);
		}
		catch (Exception e)
		{
			try
			{
				if(t.errorDefaultMediaDB())
					mDB = new MediaDatabase(SAMPLEMDB);
				else
				{
					t.errorVital();
					System.exit(0);
				}
				
			}
			catch (Exception f)
			{
				t.errorVital();
				System.exit(0);
			}
		}
		return mDB;
	}
	
	public DVD newDVD()
	{
		String title = t.getTitle();
		int year = t.getYear();
		String genre = t.getGenre();
		int duration = t.getDuration();
		String studio = t.getStudio();
		String classification = t.getClassification();
		String director = t.getDirector();
		String features = t.getFeatures();
		DVD dvd = new DVD(title, director, year, genre, "dvd", duration, studio, classification, features);
		return dvd;
	}
	
	public Cd newCd()
	{
		String title = t.getTitle();
		int year = t.getYear();
		String genre = t.getGenre();
		int duration = t.getDuration();
		String artist = t.getArtist();
		int tracks = t.getTracks();
		Cd cd = new Cd(title, year, genre, "cd", duration, artist, tracks);
		return cd;
	}
	
	public Lp newLp()
	{
		String title = t.getTitle();
		int year = t.getYear();
		String genre = t.getGenre();
		int duration = t.getDuration();
		String artist = t.getArtist();
		int songs = t.getSongs();
		Lp lp = new Lp(title, year, genre, "lp", duration, artist, songs);
		return lp;
	}
	
	public Vhs newVideo()
	{
		String title = t.getTitle();
		int year = t.getYear();
		String genre = t.getGenre();
		int duration = t.getDuration();
		String studio = t.getStudio();
		String classification = t.getClassification();
		String director = t.getDirector();
		Vhs video = new Vhs(title, director, year, genre, "vhs", duration, studio, classification);
		return video;
	}
	
	/**
	 * @author Fernleigh
	 */
	public void newSeniorStaff()
	{
		String fn = t.receiveFirstName();
		String ln = t.receiveLastName();
		int ph = t.receivePhoneNumber();
		String pw = t.setNewPassword();
		try
		{
			if (((ChangeDatabase)currentUser).getPermission(t.receivePassword()) || !userDatabase.checkForValidUser())
				userDatabase.addNewSeniorStaff(fn, ln, ph, pw);			
		}
		catch (IncorrectPasswordException e)
		{
			t.errorUnauthorised();
		}
		catch (FileNotFoundException f)
		{
			t.errorCannotAddUser();
		}
	}
	
	/**
	 * @author Fernleigh
	 */
	public void newJuniorStaff()
	{
		String fn = t.receiveFirstName();
		String ln = t.receiveLastName();
		int ph = t.receivePhoneNumber();
		String pw = t.setNewPassword();
		int seniorID = t.receiveSeniorUserID();
		try
		{
			Staff supervisor = userDatabase.searchByID(seniorID);
			if (supervisor instanceof SeniorStaff && ((ChangeDatabase)currentUser).getPermission(t.receiveYourPassword()))
				userDatabase.addNewJuniorStaff(fn, ln, ph, pw, (SeniorStaff)supervisor);
			else
				t.errorCannotAddUser();	
		}
		catch (IncorrectPasswordException e)
		{
			t.errorUnauthorised();
		}
		catch (NoMatchException n)
		{
			t.errorCannotAddUser();
		}
		catch (FileNotFoundException f)
		{
			t.errorCannotAddUser();
		}
	}
	
}
