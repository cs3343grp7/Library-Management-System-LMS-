package library;

public class BookStatusOnhold implements BookStatus
{
	private Member onholdingMember;
	private Book theBook;
	private Day onholdDate;
	private Day onholdDeadLineDate;
	
	public String getStatus()
	{
		if (theBook.sizeOfQueueList() == 0)
			return String.format("%-17s%-4s%s%s%-6s%s", "On holdshelf for",onholdingMember.getID(),onholdingMember.getName()," ","until",onholdDeadLineDate);
		else return String.format("%-17s%-4s%s%s%-6s%-11s%-2s%d%-13s%s", "On holdshelf for",onholdingMember.getID(),onholdingMember.getName(),
									" ","until",onholdDeadLineDate,"+",theBook.sizeOfQueueList()," request(s):",theBook.getQueueList());
		
	}//"Borrowed by 002 jason on 8-Jan-2014 ;
	
	public void set(Member aOnholdingMember,Book aBook)
	{
		this.onholdingMember = aOnholdingMember;
		this.theBook = aBook;
		this.onholdDate = SystemDate.getInstance().clone();
		this.onholdDeadLineDate = onholdDate.getOnholdDeadLineDate();
	}
	
	public Member getMember()
	{
		return onholdingMember;
	}
	
	public void setOnholdDate(Day onholdDate)
	{
		this.onholdDate = onholdDate;
	}
	
	public void setOnholdDeadLineDate(Day onholdDeadLineDate)
	{
		this.onholdDeadLineDate = onholdDeadLineDate;
	}
	
	public Day getDate()
	{
		return onholdDeadLineDate;
	}
	
	public Day getOnholdDate()
	{
		return onholdDate;
	}
}
