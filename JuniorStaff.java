/**
 * A junior member of staff. 
 * Juniors work a set number of shifts (per week [implied]).
 * Each junior is assigned a senior staff member as a supervisor.
 * If the junior wishes to alter the database, they must seek permission (in the form of a password) from their supervisor.
 * @author Fernleigh Adams
 * @version 17/08/06
 * @see ChangeDatabase
 * @see SeniorStaff
 * @see Staff
 */

public class JuniorStaff extends Staff implements ChangeDatabase
{
	private SeniorStaff supervisor;
	
	/**
	 * Creates a junior staff member with ID number, first and last names, phone number and password.
	 * @param anID A unique identification number.
	 * @param aFirstName Staff member's first name.
	 * @param aSurname Staff member's surname.
	 * @param aPhNumber Staff member's personal phone number.
	 * @param aPassword A password, required to access menus with options to alter the database.
	 * @param shifts Number of shifts the staff member works (per week).
	 * @param aSenior The junior staff member's assigned senior supervisor.
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 * @see Senior
	 * 
	 */
	public JuniorStaff (int anID, String aFirstName, String aSurname, int aPhNumber, String aPassword, SeniorStaff aSenior)
	{
		super(anID, aFirstName, aSurname, aPhNumber, aPassword);
		supervisor = aSenior;
	}
	
	/** 
	 * Determines whether this staff member has permission to change the database.
	 * The supervisor is expected to supply his password, which will be verified.
	 * Scalability: O(1)
	 * @param string The password of this staff member's supervisor.
	 * @return true If supervisor enters his/her correct password.
	 * @throws IncorrectPasswordException If password is incorrect.
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 * @see ChangeDatabase
	 * @see SeniorStaff
	 */
	public boolean getPermission(String seniorPass) throws IncorrectPasswordException
	{
		if (supervisor.matchPassword(seniorPass))
			return true;
		else
			throw new IncorrectPasswordException();
	}
	
	public SeniorStaff getSupervisor()
	{
		return supervisor;
	}
	
	public boolean isSenior()
	{
		return false;
	}
}
