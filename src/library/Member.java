package library;
public class Member implements Comparable<Member>{
	private String id;
	private String name;
	private Day joinDate;
	private MemberStatus memberStatus;
	private int borrowCounts;
	private int requestCounts;
	
	
	public Member(String id, String name, MemberStatus aState)
	{
		this.id = id;
		this.name = name;
		this.joinDate = SystemDate.getInstance().clone();
		this.memberStatus = aState;
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
	
	public void setMemberStatus(MemberStatus newState)
	{
		this.memberStatus = newState;
	}
	
	public MemberStatus getMemberStatus()
	{
		return memberStatus;
	}
	
	//add a new exception to throw when memberStatus == Sus in checkout (dont let him borrow)
	public void borrowBook(Book checkoutBook) throws ExBookNotAvailable, ExLoanQuotaExceeded, ExMemberStatusSuspended
	{
		
		if (!(checkoutBook.getBookStatus() instanceof BookStatusAvailable)) //First check to minimize every borrow need to go through below
			//borrowed / onhold
			if (checkoutBook.getBookStatus() instanceof BookStatusBorrowed)
				throw new ExBookNotAvailable();
			else if (((BookStatusOnhold)checkoutBook.getBookStatus()).getMember() != this) //is onhold for someone, 			
				throw new ExBookNotAvailable();// but the one who want to borrow the book is not the onhold person.
		
		if (this.getBorrowCounts()>5)
		{
			throw new ExLoanQuotaExceeded();
		}
		if (this.getMemberStatus() instanceof MemberStatusSuspend) {
			throw new ExMemberStatusSuspended();
		}
		
		this.borrowCounts += 1;
		checkoutBook.setBookStatus(new BookStatusBorrowed());
		((BookStatusBorrowed)checkoutBook.getBookStatus()).set(this,checkoutBook);
	}
	
	public void returnBook(Book checkinBook) throws ExNotBorrowedByThisMember, ExBookNotBorrowed 
	{
		if (checkinBook.getBookStatus() instanceof BookStatusBorrowed)
		{	
			if (((BookStatusBorrowed)checkinBook.getBookStatus()).getMember()!=this)
					throw new ExNotBorrowedByThisMember();
		}
		else throw new ExBookNotBorrowed();
		
		if(this.getMemberStatus() instanceof MemberStatusSuspend) {
			((MemberStatusSuspend)this.getMemberStatus()).removeFromSuspendList(checkinBook);
			if(((MemberStatusSuspend)this.getMemberStatus()).getOverDueBookCount()==0) {
				System.out.println(this.getName()+" has returned all overdue book(s) and suspension is stopped.");
				this.setMemberStatus(new MemberStatusNormal());
			}
		}

			checkinBook.setBookStatus(new BookStatusAvailable());
			this.borrowCounts -= 1;
	}
	
	public void requestBook(Book requestingBook) throws ExBookIsAvailable, ExBookIsBorrowedByThisMember, ExRequestQuotaExceeded, ExAlreadyRequested, ExMemberStatusSuspended
	{

			if (requestingBook.getBookStatus() instanceof BookStatusAvailable)
				throw new ExBookIsAvailable();
			else if (requestingBook.getBookStatus() instanceof BookStatusBorrowed)
			{
				if (((BookStatusBorrowed)requestingBook.getBookStatus()).getMember() == this)
					throw new ExBookIsBorrowedByThisMember();
			}
			else  if (((BookStatusOnhold)requestingBook.getBookStatus()).getMember() == this)
				throw new ExBookIsAvailable(); //one more checking on instanceof onHold before this if
					//No need one more checking as after all if, the bookStatus must be onHold
						
			if (this.requestCounts>2)
				throw new ExRequestQuotaExceeded();
			
			if (requestingBook.memberFoundInQueue(this))
				throw new ExAlreadyRequested();
			if (this.getMemberStatus() instanceof MemberStatusSuspend) {
				throw new ExMemberStatusSuspended();
			}
							
			requestingBook.addInQueueList(this);
			this.requestCounts += 1;
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
