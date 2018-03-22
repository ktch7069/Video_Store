/**
 * 
 * @author Fernleigh Adams
 * @version 17/08/06
 */
public class InvalidIDException extends Exception
{
	String userName;
	
	public InvalidIDException(String aName)
	{
		super();
		userName = aName;
	}
	
	public String getName()
	{
		return userName;
	}
}
