package library;

public class BookStatusBorrowed implements BookStatus
{
	private Member borrowingMember;
	private Book theBook;
	private Day loanDate;
	private Day loanDeadLineDate;
	
	public String getStatus()
	{
		if (theBook.sizeOfQueueList() == 0)
			return String.format("%-12s%-4s%s%s%-3s%-11s%-6s%s", "Borrowed by",borrowingMember.getID(),borrowingMember.getName()," ","on",loanDate,"until",loanDeadLineDate);
		else return String.format("%-12s%-4s%s%s%-3s%-11s%-6s%-11s%-2s%d%-13s%s", "Borrowed by",borrowingMember.getID(),borrowingMember.getName(),
									" ","on",loanDate,"until",loanDeadLineDate,"+",theBook.sizeOfQueueList()," request(s):",theBook.getQueueList());
		
	}//"Borrowed by 002 jason on 8-Jan-2014 ;
	
	public void set(Member aBorrowingMember,Book aBook)
	{
		this.borrowingMember = aBorrowingMember;
		this.theBook = aBook;
		this.loanDate = SystemDate.getInstance().clone();
		this.loanDeadLineDate = loanDate.getLoanDeadLineDate();
	}
	public Member getMember()
	{
		return borrowingMember;
	}

	public void setLoanDate(Day loanDate)
	{
		this.loanDate = loanDate;
	}
	
	public void setLoanDeadLineDate(Day loanDeadLineDate)
	{
		this.loanDeadLineDate = loanDeadLineDate;
	}
	
	public Day getDate()
	{
		return loanDate;
	}
	
	public Day getDeadLineDate()
	{
		return loanDeadLineDate;
	}
}
