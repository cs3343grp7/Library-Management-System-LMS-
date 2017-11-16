package library;

import java.util.ArrayList;

public class MemberStatusSuspend implements MemberStatus
{
	private ArrayList<Book> overdueBookList;
	
	public String getStatus()
	{
		return "Suspended";
	}
	
	public void removeFromSuspendList(Book b)
	{	
		overdueBookList.remove(b);
	}
	
	public void addSuspendBook(Book suspendBook)
	{
		overdueBookList.add(suspendBook);
	}
	
	public int getOverDueBookCount() {
		return overdueBookList.size();
	}
	
}