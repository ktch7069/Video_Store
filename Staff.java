/**
 * Simulates generic member of staff.
 * Staff members have passwords that will allow them to 
 * alter the database (add/remove movies, and so etc.).
 * @author Fernleigh Adams 
 * @version 17/08/06
 * @see User
 */

public class Staff
{
	protected int idNo;

	protected String firstName;

	protected String surname;

	protected int phone;
	
	protected String password;
	
	protected boolean senior;

	/**
	 * Creates a generic staff member with ID number, first and last names, phone number and password.
	 * @param anID A unique identification number.
	 * @param aFirstName Staff member's first name.
	 * @param aSurname Staff member's surname.
	 * @param aPhNumber Staff member's personal phone number.
	 * @param aPassword A password, required to access menus with options to alter the database.
	 * @author Fernleigh Adams 
	 * @version 17/08/06
	 */
	public Staff(int anID, String aFirstName, String aSurname, int aPhNumber, String aPassword) 
	{
		idNo = anID;
		firstName = aFirstName;
		surname = aSurname;
		phone = aPhNumber;
		password = aPassword;
	}

	/**
	 * 
	 * @return The user's ID number.
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	public int getIDNo() {
		return idNo;
	}

	/**
	 * 
	 * @return The user's phone number.
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	public int getPhone() {
		return phone;
	}

	/**
	 * 
	 * @return The user's first name.
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 
	 * @return The user's surname.
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * Checks to see if the ID number supplied matches the user's.
	 * Scalability: O(1)
	 * @param anID The ID that will be checked against the user's.
	 * @return true If the ID numbers match, 
	 * 		false If the ID numbers do not match.
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	public boolean matchIDNo(int anID)
	{
		if (idNo == anID)
			return true;
		else
			return false;
	}

	/**
	 * Changes the staff member's current password.
	 * Scalability: O(1)
	 * @param newPassword The new password.
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	public void setPassword(String newPassword) 
	{
		password = newPassword;
	}
	/**
	 * Checks to see if a password is correct.
	 * Scalability: O(1)
	 * @param aPass The password to check
	 * @return true If the password was correct, otherwise false.
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	public boolean matchPassword(String aPass) 
	{
		if (aPass.equals(password))
			return true;
		else
			return false;
	}
	
	/**
	 * Scalability: O(1)
	 * @return Staff member's password
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	public String getPassword()
	{
		return password;
	}

}
