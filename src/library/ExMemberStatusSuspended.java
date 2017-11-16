package library;

public class ExMemberStatusSuspended extends Exception {

	public ExMemberStatusSuspended()
	{
		super("This member's status is suspended, all request / borrowing actions are denied.");
	}
	
}