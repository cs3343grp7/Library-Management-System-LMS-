package library;

import java.util.ArrayList;

public class MemberStatusSuspend implements MemberStatus
{
	private ArrayList<Book> overdueBookList;
	boolean listOpen = false;
	
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
		if(listOpen == false){
			overdueBookList = new ArrayList<Book>();
			listOpen = true;
		}
		overdueBookList.add(suspendBook);
	}
	
	public String getSuspendBookListString()
	{
		String suspendBookListString = "";
		for (Book b : overdueBookList)
			suspendBookListString += b.getID()+" ";
		return suspendBookListString;
	}
	
	public int getOverDueBookCount() {
		return overdueBookList.size();
	}
	
}