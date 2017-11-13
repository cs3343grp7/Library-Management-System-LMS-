package library;

public class MemberStatusSuspend implements MemberStatus
{
	private ArrayList<Book> suspendBookList;
	
	public String getStatus()
	{
		return "Suspended";
	}
	
	public void removeFromSuspendList(Book b)
	{	
		suspendBookList.remove(b);
	}
	
	public void addSuspendBook(Book suspendBook)
	{
		suspendBookList.add(suspendBook);
	}
	
}