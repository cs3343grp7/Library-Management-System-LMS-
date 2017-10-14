package library;

public class ExInsufficientCommand extends Exception 
{
	public ExInsufficientCommand()
	{
		super("Insufficient command arguments!");
	}
}
