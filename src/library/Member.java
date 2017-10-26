package library;
public class Member implements Comparable<Member>{
	private String id;
	private String name;
	private Day joinDate;
	private int borrowCounts;
	private int requestCounts;
	
	public Member(String id, String name)
	{
		this.id = id;
		this.name = name;
		this.joinDate = SystemDate.getInstance().clone();
		this.borrowCounts  = 0;
		this.requestCounts = 0;
	}
	
	public static String getListingHeader()
	{
		return String.format("%-5s%-10s%-12s%-12s%s", "ID","Name","Join Date","#Borrowed","#Requested");
	}
	
	public String getID()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getBorrowCounts()
	{
		return borrowCounts;
	}
	
	public int getRequestCounts()
	{
		return requestCounts;
	}
	
	public void borrowBook(Book checkoutBook) throws ExBookNotAvailable, ExLoanQuotaExceeded
	{
		boolean isOnholdMember = false;
		
		if (!(checkoutBook.getBookStatus() instanceof BookStatusAvailable)) //First check to minimize every borrow need to go through below
			//borrowed / onhold
			if (checkoutBook.getBookStatus() instanceof BookStatusBorrowed)
				throw new ExBookNotAvailable();
			else if (((BookStatusOnhold)checkoutBook.getBookStatus()).getMember() != this) //is onhold for someone, 			
				throw new ExBookNotAvailable();// but the one who want to borrow the book is not the onhold person.
			else	 isOnholdMember = true; //If borrower = first requester
		
		if (this.getBorrowCounts()>5)
		{
			throw new ExLoanQuotaExceeded();
		}
		
		this.borrowCounts += 1;
		checkoutBook.setBookStatus(new BookStatusBorrowed());
		((BookStatusBorrowed)checkoutBook.getBookStatus()).set(this,checkoutBook);
	}
	
	public void returnBook(Book checkinBook) throws ExBookNotAvailable, ExLoanQuotaExceeded
	{
	
	}
	
	@Override
	public String toString()
	{
		return String.format("%-5s%-10s%-14s%-12d%d", id,name,joinDate,borrowCounts,requestCounts);
	}
	
	public void borrowed()
	{
		this.borrowCounts += 1;
	}
	
	public void returned()
	{
		this.borrowCounts -= 1;
	}
	
	@Override
	public int compareTo(Member another)
	{
		return this.id.compareTo(another.id);
	}

	public void requested() {
		requestCounts += 1;	
	}
	
	public void requestCancel()
	{
		requestCounts -= 1;
	}
}
