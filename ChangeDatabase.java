/**
 * Objects with this interface have the potential to change the database.
 * @author Fernleigh Adams
 * @version 17/08/06
 *
 */

public interface ChangeDatabase
{
	/** 
	 * Determines whether this object has permission to change the database.
	 * @param string The password of a user that has permission to change the database.
	 * @return true If permission is granted, false if not.
	 * @throws IncorrectPasswordException If password is incorrect.
	 * @author Fernleigh Adams
	 * @version 17/08/06
	 */
	boolean getPermission(String string) throws IncorrectPasswordException;
}
