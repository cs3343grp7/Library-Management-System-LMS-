package library;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections; //provides sorting

public class Library {
	private ArrayList<Member> allMembers;
	private ArrayList<Book> allBooks;
	
	private static Library instance = new Library();
	private Library()
	{
		allMembers = new ArrayList<Member>();
		allBooks = new ArrayList<Book>();
	}
	
	public static Library getInstance()
	{
		return instance;
	}
	
	public void addMember(Member m)
	{
		allMembers.add(m);
		Collections.sort(allMembers);
	}
	
	public void addBook(Book b)
	{		
		allBooks.add(b);
		Collections.sort(allBooks);
	}
	
	public void removeMember(Member m)
	{
		allMembers.remove(m);
		Collections.sort(allMembers);
	}
	
	public void removeBook(Book b)
	{
		allBooks.remove(b);
		Collections.sort(allBooks);
	}
	
	public void listLibraryMembers()
	{
		System.out.println(Member.getListingHeader());
		for (Member m:allMembers)
			System.out.println(m);
	}

	public void listLibraryBooks() {
		System.out.println(Book.getListingHeader());
		for (Book b:allBooks)
			System.out.println(b);	
	}
	
	public Member findMember(String targetID) throws ExMemberNotFound
	{
		Member targetMember = null;
		
		for (Member m:allMembers)
			if (m.getID().equals(targetID))
				targetMember = m;
		
		if (targetMember == null)
			throw new ExMemberNotFound();
		else return targetMember;
	}
	
	public boolean IdNotExists(String _id) throws ExMemberIDAlreadyInUse 
	{
		for(Member m:allMembers)
		{
			if (m.getID().equals(_id))
				throw new ExMemberIDAlreadyInUse(m);
		}
		return true;
	}
	
	public Book findBook(String targetID) throws ExBookNotFound
	{
		Book targetBook = null;
		
		for (Book b:allBooks)
			if (b.getID().equals(targetID))
				targetBook = b;
		
		if (targetBook == null)
			throw new ExBookNotFound();
		else return targetBook;
	}
	
	public ArrayList<Book> getBookList()
	{
		return allBooks;
	}

	public void saveData() throws FileNotFoundException, UnsupportedEncodingException {
		//Save Member
		PrintWriter memberDataWriter = new PrintWriter("memberData.txt", "UTF-8");
		
		for (Member m : allMembers)
		{
			int mStatusNum = 0;
			if (m.getMemberStatus() instanceof MemberStatusSuspend)
				mStatusNum = 1;
			
			if (mStatusNum == 0)
				memberDataWriter.println(m.getID()+":"+m.getName()+":"+m.getJoinDate()+":"+m.getBorrowCounts()+":"+m.getRequestCounts()+":"+mStatusNum);
			else
				memberDataWriter.println(m.getID()+":"+m.getName()+":"+m.getJoinDate()+":"+m.getBorrowCounts()+":"+m.getRequestCounts()+":"+mStatusNum+":"+((MemberStatusSuspend)m.getMemberStatus()).getOverDueBookCount()+":"+((MemberStatusSuspend)m.getMemberStatus()).getSuspendBookListString());
		}

		memberDataWriter.close();
		
		
		//Save Book
		PrintWriter bookDataWriter = new PrintWriter("bookData.txt", "UTF-8");
		
		for (Book b : allBooks)
		{
			int bStatusNum = 0;
			if (b.getBookStatus() instanceof BookStatusBorrowed)
				bStatusNum = 1;
			else if (b.getBookStatus() instanceof BookStatusOnhold)
				bStatusNum = 2;
			
			if(bStatusNum == 0)
				bookDataWriter.println(b.getID()+":"+b.getName()+":"+b.getArrivalDate()+":"+b.sizeOfQueueList()+":"+b.getQueueList()+":"+bStatusNum);
			else if(bStatusNum == 1)
				bookDataWriter.println(b.getID()+":"+b.getName()+":"+b.getArrivalDate()+":"+b.sizeOfQueueList()+":"+b.getQueueList()+":"+bStatusNum+":"+((BookStatusBorrowed)b.getBookStatus()).getMember().getID()+":"+((BookStatusBorrowed)b.getBookStatus()).getDate());
			else
				bookDataWriter.println(b.getID()+":"+b.getName()+":"+b.getArrivalDate()+":"+b.sizeOfQueueList()+":"+b.getQueueList()+":"+bStatusNum+":"+((BookStatusOnhold)b.getBookStatus()).getMember().getID()+":"+((BookStatusOnhold)b.getBookStatus()).getOnholdDate());
		}
		
		bookDataWriter.close();
	}
	
}