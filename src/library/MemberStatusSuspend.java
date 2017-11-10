package library;

public class MemberStatusSuspend implements MemberStatus
{
	private ArrayList<Book> suspendBooks;
	
	public String getStatus()
	{
		return "Suspended";
	}
	
	public void addSuspendBook(Book suspendBook)
	{
		suspendBooks.add(suspendBook);
	}
	
}