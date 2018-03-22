 /**
  * A senior member of staff.
  * Seniors have permission to alter the database.
  * @author Fernleigh Adams
  * @version 17/08/06
  * @see ChangeDatabase
  * @see Staff
  */
public class SeniorStaff extends Staff implements ChangeDatabase
{
	 /**
 	 * Creates a senior staff member with ID number, first and last names, phone number and password.
 	 * @param anID A unique identification number.
	 * @param aFirstName Staff member's first name.
	 * @param aSurname Staff member's surname.
	 * @param aPhNumber Staff member's personal phone number.
	 * @param aPassword A password, required to access menus with options to alter the database.
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	public SeniorStaff (int anID, String aFirstName, String aSurname, int aPhNumber, String aPassword)
	{
		super(anID, aFirstName, aSurname, aPhNumber, aPassword);
	}
	
	/**
	 * Senior staff members need only to supply their own password to gain permission to alter the database.
	 * Scalability: O(1)
	 * @param string This staff member's password.
	 * @return true If the password was entered correctly.
	 * @throws IncorrectPasswordException if password is entered incorrectly.
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	public boolean getPermission(String aPass) throws IncorrectPasswordException
	{
		if (matchPassword(aPass))
			return true;
		else
			throw new IncorrectPasswordException();
	}
	
	public boolean isSenior()
	{
		return true;
	}
}
