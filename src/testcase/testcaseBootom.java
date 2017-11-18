package testcase;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.Test;
import library.*;
public class testcaseBootom {
    @Before
    public void resetSingleton() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        Field instance = Library.class.getDeclaredField("instance");
        instance.setAccessible(true);
 
        Constructor constructor = Library.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        instance.set(null, constructor.newInstance());
    }
	@Test
	public void testDay01() {
		Day day= new Day(2017,1,1);
		assertEquals(day.toString(),"1-Jan-2017");		
	}
	@Test
	public void testDay02() {
		Day day= null;
		try {
			day = new Day("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		assertEquals(day.toString(),"1-Jan-2017");		
	}
	@Test
	public void testDay03() {
		Day day= null;
		String result = null; 
		try {
			day = new Day("199-Jan-2017");
		} catch (ExDayNotValid e) {
			result = e.getMessage();
		}
		assertEquals(result,"Invalid date input!");		
	}
	@Test
	public void testDayisLeapYear01() {
		assertEquals(Day.isLeapYear(400),true);		
	}
	@Test
	public void testDayisLeapYear02() {
		assertEquals(Day.isLeapYear(100),false);		
	}
	@Test
	public void testDayisLeapYear03() {
		assertEquals(Day.isLeapYear(2016),true);		
	}
	@Test
	public void testDayisLeapYear04() {
		assertEquals(Day.isLeapYear(2017),false);		
	}
	@Test
	public void testDayValid01() {
		Day day = new Day(2017,1,1);
		assertEquals(day.toString(),"1-Jan-2017");		
	}
	@Test
	public void testDayValid02() {
		Day day= null;
		String result = null; 
		try {
			day = new Day("32-Jan-2017");
		} catch (ExDayNotValid e) {
			result = e.getMessage();
		}
		assertEquals(result,"Invalid date input!");		
	}
	@Test
	public void testDayValid03() {
		Day day = new Day(2017,4,15);
		assertEquals(day.toString(),"15-Apr-2017");			
	}
	@Test
	public void testDayValid04() {
		Day day= null;
		String result = null; 
		try {
			day = new Day("31-Jun-2017");
		} catch (ExDayNotValid e) {
			result = e.getMessage();
		}
		assertEquals(result,"Invalid date input!");			
	}
	@Test
	public void testDayValid05() {
		Day day = new Day(2017,2,28);
		assertEquals(day.toString(),"28-Feb-2017");				
	}
	@Test
	public void testDayValid06() {
		Day day = new Day(2012,2,29);
		assertEquals(day.toString(),"29-Feb-2012");				
	}
	@Test
	public void testDayValid07() {
		Day day= null;
		String result = null; 
		try {
			day = new Day("29-Feb-2017");
		} catch (ExDayNotValid e) {
			result = e.getMessage();
		}
		assertEquals(result,"Invalid date input!");			
	}
	@Test
	public void testDayValid08() {
		Day day= null;
		String result = null; 
		try {
			day = new Day("30-Feb-2016");
		} catch (ExDayNotValid e) {
			result = e.getMessage();
		}
		assertEquals(result,"Invalid date input!");			
	}
	@Test
	public void testDayValid09() {
		Day day= null;
		String result = null; 
		try {
			day = new Day("0-Feb-111");
		} catch (ExDayNotValid e) {
			result = e.getMessage();
		}
		assertEquals(result,"Invalid date input!");			
	}
	@Test
	public void testDayDatePassed01() {
		Day day1= new Day(2017,1,1);
		Day day2= new Day(2018,1,1);
		assertEquals(day1.datePassed(day2),true);		
	}
	@Test
	public void testDayDatePassed02() {
		Day day1= new Day(2018,1,1);
		Day day2= new Day(2017,1,1);
		assertEquals(day1.datePassed(day2),false);		
	}
	@Test
	public void testDayDatePassed03() {
		Day day1= new Day(2017,1,1);
		Day day2= new Day(2017,2,1);
		assertEquals(day1.datePassed(day2),true);		
	}
	@Test
	public void testDayDatePassed04() {
		Day day1= new Day(2017,2,1);
		Day day2= new Day(2017,1,1);
		assertEquals(day1.datePassed(day2),false);		
	}
	@Test
	public void testDayDatePassed05() {
		Day day1= new Day(2017,1,1);
		Day day2= new Day(2017,1,2);
		assertEquals(day1.datePassed(day2),true);		
	}
	@Test
	public void testDayDatePassed06() {
		Day day1= new Day(2017,1,3);
		Day day2= new Day(2017,1,1);
		assertEquals(day1.datePassed(day2),false);		
	}
	@Test
	public void testDayDatePassed07() {
		Day day1= new Day(2017,1,1);
		Day day2= new Day(2017,1,1);
		assertEquals(day1.datePassed(day2),false);		
	}
	@Test
	public void testSystemDate01() {
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		SystemDate systemDate= SystemDate.getInstance();
		assertEquals(systemDate.toString(),"1-Jan-2017");		
	}
	@Test
	public void testLibrary01() {
		Library library= Library.getInstance();
		assertEquals(library instanceof Library,true);		
	}
	@Test
	public void testMember01() {
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		assertEquals(member.toString(),String.format("%-5s%-10s%-14s%-12d%d", "1","Test","1-Jan-2017",0,0));		
	}
	@Test
	public void testMemberGetMemberStatus01(){
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		MemberStatus memberStatus = member.getMemberStatus();
		assertEquals(memberStatus instanceof MemberStatusNormal,true);		
	}
	@Test
	public void testMemberSetMemberStatus01(){
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		member.setMemberStatus(new MemberStatusSuspend());
		MemberStatus memberStatus = member.getMemberStatus();
		assertEquals(memberStatus instanceof MemberStatusSuspend,true);		
	}
	@Test
	public void testMembergetID01() {
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		assertEquals(member.getID(),"1");		
	}
	@Test
	public void testMembergetName01() {
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		assertEquals(member.getName(),"Test");		
	}
	@Test
	public void testMembergetListingHeader01() {
		assertEquals(Member.getListingHeader(),String.format("%-5s%-10s%-12s%-12s%s", "ID","Name","Join Date","#Borrowed","#Requested"));		
	}
	@Test
	public void testMembergetBorrowCounts() {
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		assertEquals(member.getBorrowCounts(),0);		
	}
	@Test
	public void testMembergetRequestCounts() {
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}		
		Member member = new Member("1","Test",new MemberStatusNormal());
		assertEquals(member.getRequestCounts(),0);		
	}
	@Test
	public void testDaygetLoanDeadLineDate01() {
		Day day= new Day(2017,1,1);
		assertEquals(day.getLoanDeadLineDate().toString(),"8-Jan-2017");		
	}
	@Test
	public void testDaygetLoanDeadLineDate02() {
		Day day= new Day(2017,1,25);
		assertEquals(day.getLoanDeadLineDate().toString(),"1-Feb-2017");		
	}
	@Test
	public void testDaygetLoanDeadLineDate03() {
		Day day= new Day(2017,9,25);
		assertEquals(day.getLoanDeadLineDate().toString(),"2-Oct-2017");		
	}
	@Test
	public void testDaygetLoanDeadLineDate04() {
		Day day= new Day(2017,11,2);
		assertEquals(day.getLoanDeadLineDate().toString(),"9-Nov-2017");		
	}
	@Test
	public void testDaygetLoanDeadLineDate05() {
		Day day= new Day(2017,2,2);
		assertEquals(day.getLoanDeadLineDate().toString(),"9-Feb-2017");		
	}
	@Test
	public void testDaygetLoanDeadLineDate06() {
		Day day= new Day(2017,2,25);
		assertEquals(day.getLoanDeadLineDate().toString(),"4-Mar-2017");	
	}
	@Test
	public void testDaygetLoanDeadLineDate07() {
		Day day= new Day(2016,2,2);
		assertEquals(day.getLoanDeadLineDate().toString(),"9-Feb-2016");	
	}
	@Test
	public void testDaygetLoanDeadLineDate08() {
		Day day= new Day(2016,2,25);
		assertEquals(day.getLoanDeadLineDate().toString(),"3-Mar-2016");	
	}
	@Test
	public void testDaygetLoanDeadLineDate09() {
		Day day= new Day(2017,12,1);
		assertEquals(day.getLoanDeadLineDate().toString(),"8-Dec-2017");	
	}
	@Test
	public void testDaygetLoanDeadLineDate10() {
		Day day= new Day(2017,12,25);
		assertEquals(day.getLoanDeadLineDate().toString(),"1-Jan-2018");	
	}
	@Test
	public void testDaygetOnholdDeadLineDate01() {
		Day day= new Day(2017,1,1);
		assertEquals(day.getOnholdDeadLineDate().toString(),"4-Jan-2017");	
	}
	@Test
	public void testDaygetOnholdDeadLineDate02() {
		Day day= new Day(2017,1,29);
		assertEquals(day.getOnholdDeadLineDate().toString(),"1-Feb-2017");	
	}
	@Test
	public void testDaygetOnholdDeadLineDate03() {
		Day day= new Day(2017,6,1);
		assertEquals(day.getOnholdDeadLineDate().toString(),"4-Jun-2017");	
	}
	@Test
	public void testDaygetOnholdDeadLineDate04() {
		Day day= new Day(2017,6,29);
		assertEquals(day.getOnholdDeadLineDate().toString(),"2-Jul-2017");	
	}
	@Test
	public void testDaygetOnholdDeadLineDate05() {
		Day day= new Day(2017,2,1);
		assertEquals(day.getOnholdDeadLineDate().toString(),"4-Feb-2017");	
	}
	@Test
	public void testDaygetOnholdDeadLineDate06() {
		Day day= new Day(2017,2,28);
		assertEquals(day.getOnholdDeadLineDate().toString(),"3-Mar-2017");	
	}
	@Test
	public void testDaygetOnholdDeadLineDate07() {
		Day day= new Day(2016,2,5);
		assertEquals(day.getOnholdDeadLineDate().toString(),"8-Feb-2016");	
	}
	@Test
	public void testDaygetOnholdDeadLineDate08() {
		Day day= new Day(2016,2,28);
		assertEquals(day.getOnholdDeadLineDate().toString(),"2-Mar-2016");	
	}
	@Test
	public void testDaygetOnholdDeadLineDate09() {
		Day day= new Day(2017,12,29);
		assertEquals(day.getOnholdDeadLineDate().toString(),"1-Jan-2018");	
	}
	@Test
	public void testDaygetOnholdDeadLineDate10() {
		Day day= new Day(2017,12,11);
		assertEquals(day.getOnholdDeadLineDate().toString(),"14-Dec-2017");	
	}
	@Test
	public void testBook01() {
		String id ="01";
		String name ="CS3343";
		BookStatus bookStatus =new BookStatusAvailable();
		Book book= new Book(id,name,bookStatus);
		assertEquals(String.format("%-5s%-20s%-12s%s", id,name,SystemDate.getInstance().clone(),bookStatus.getStatus()),book.toString());		
	}
	@Test
	public void testBookgetName01() {
		String id ="01";
		String name ="CS3343";
		BookStatus bookStatus =new BookStatusAvailable();
		Book book= new Book(id,name,bookStatus);
		assertEquals(name,book.getName());		
	}
	@Test
	public void testBookgetID01() {
		String id ="01";
		String name ="CS3343";
		BookStatus bookStatus =new BookStatusAvailable();
		Book book= new Book(id,name,bookStatus);
		assertEquals(id,book.getID());		
	}
	@Test
	public void testBookgetBookStatus01() {
		String id ="01";
		String name ="CS3343";
		BookStatus bookStatus =new BookStatusAvailable();
		Book book= new Book(id,name,bookStatus);
		assertEquals("Available",book.getBookStatus().getStatus());		
	}
	@Test
	public void testBooksetBookStatus01() {
		String id ="01";
		String name ="CS3343";
		BookStatus bookStatus =new BookStatusAvailable();
		Book book= new Book(id,name,bookStatus);
		book.setBookStatus(new BookStatusBorrowed());
		assertEquals(book.getBookStatus() instanceof BookStatusBorrowed,true);		
	}
	@Test
	public void testBookcompareTo01() {
		String id1 ="01";
		String name1 ="CS3343";
		BookStatus bookStatus1 =new BookStatusAvailable();
		Book book1= new Book(id1,name1,bookStatus1);
		String id2 ="02";
		String name2 ="CS3344";
		BookStatus bookStatus2 =new BookStatusAvailable();
		Book book2= new Book(id2,name2,bookStatus2);
		assertEquals(book1.compareTo(book2),-1);		
	}
	@Test
	public void testBookcompareTo02() {
		String id1 ="01";
		String name1 ="CS3343";
		BookStatus bookStatus1 =new BookStatusAvailable();
		Book book1= new Book(id1,name1,bookStatus1);
		String id2 ="01";
		String name2 ="CS3344";
		BookStatus bookStatus2 =new BookStatusAvailable();
		Book book2= new Book(id2,name2,bookStatus2);
		assertEquals(book1.compareTo(book2),0);		
	}
	@Test
	public void testBookgetListingHeader() {
		assertEquals(Book.getListingHeader(),String.format("%-5s%-20s%-12s%s", "ID","Name","Arrival","Status"));		
	}
	@Test
	public void testLibrary01listLibraryBooks() {
		Library library = Library.getInstance();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		library.listLibraryBooks();
		assertEquals(outContent.toString(),String.format("%-5s%-20s%-12s%s", "ID","Name","Arrival","Status")+System.getProperty("line.separator"));		
	}
	@Test
	public void testLibrary02addBook() {
		String id ="01";
		String name ="CS3343";
		BookStatus bookStatus =new BookStatusAvailable();
		Book book = new Book(id,name,bookStatus);
		Library library = Library.getInstance();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		library.addBook(book);
		library.listLibraryBooks();
		assertEquals(outContent.toString(),String.format("%-5s%-20s%-12s%s", "ID","Name","Arrival","Status")+System.getProperty("line.separator")+String.format("%-5s%-20s%-12s%s", id, name,"1-Jan-2017",bookStatus.getStatus())+System.getProperty("line.separator"));
	}
	@Test
	public void testLibrary03removeBook() {
		String id ="02";
		String name ="Test";
		BookStatus bookStatus =new BookStatusAvailable();
		Book book = new Book(id,name,bookStatus);
		Library library = Library.getInstance();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		library.addBook(book);
		library.listLibraryBooks();
		assertEquals(outContent.toString(),String.format("%-5s%-20s%-12s%s", "ID","Name","Arrival","Status")+System.getProperty("line.separator")+String.format("%-5s%-20s%-12s%s", id, name,"1-Jan-2017",bookStatus.getStatus())+System.getProperty("line.separator"));
		library.removeBook(book);
		library.listLibraryBooks();
		assertEquals(outContent.toString(),String.format("%-5s%-20s%-12s%s", "ID","Name","Arrival","Status")+System.getProperty("line.separator")+String.format("%-5s%-20s%-12s%s", id, name,"1-Jan-2017",bookStatus.getStatus())+System.getProperty("line.separator")+String.format("%-5s%-20s%-12s%s", "ID","Name","Arrival","Status")+System.getProperty("line.separator"));
	}
	@Test
	public void testLibrary04findBook01() {
		String id ="02";
		String name ="Test";
		BookStatus bookStatus =new BookStatusAvailable();
		Book addBook = new Book(id,name,bookStatus);
		Library library = Library.getInstance();
		library.addBook(addBook);
		Book book = null;
		try {
			book = library.findBook("02");
		} catch (Exception e) {
		}
		assertEquals(addBook.toString() ,book.toString());
	}
	@Test
	public void testLibrary04findBook02() {
		String id ="02";
		String name ="Test";
		BookStatus bookStatus =new BookStatusAvailable();
		Book addBook = new Book(id,name,bookStatus);
		Library library = Library.getInstance();
		library.addBook(addBook);
		Book book = null;
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try {
			book = library.findBook("01");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		assertEquals(outContent.toString(), "Book not found!"+System.getProperty("line.separator"));
	}
	@Test
	public void testLibrarygetBookList() {
		ArrayList<Book> allBooks;
		Library library = Library.getInstance();
		String id ="02";
		String name ="Test";
		BookStatus bookStatus =new BookStatusAvailable();
		Book addBook = new Book(id,name,bookStatus);
		library.addBook(addBook);
		allBooks = library.getBookList();
		assertEquals(allBooks.contains(addBook),true);		
	}
	@Test
	public void testBookgetQueueList() {
		String id ="01";
		String name ="CS3343";
		BookStatus bookStatus =new BookStatusAvailable();
		Book book = new Book(id,name,bookStatus);
		assertEquals(book.getQueueList(),"");
	}
	@Test
	public void testBookaddInQueueList() {
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		String id ="01";
		String name ="CS3343";
		BookStatus bookStatus =new BookStatusAvailable();
		Book book = new Book(id,name,bookStatus);
		book.addInQueueList(member);
		assertEquals(book.getQueueList(),"1 ");
	}
	@Test
	public void testBookStatusAvailablegetStatus() {
		BookStatusAvailable bookStatus =new BookStatusAvailable();
		assertEquals(bookStatus.getStatus(),"Available");
	}
	
	@Test
	public void testBookStatusOnholdset01() {
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		String id ="01";
		String name ="CS3343";
		BookStatusOnhold bookStatus =new BookStatusOnhold();
		Book book = new Book(id,name,bookStatus);
		bookStatus.set(member,book);
		assertEquals(bookStatus.getStatus(),"On holdshelf for 1   Test until 4-Jan-2017");
	}
	@Test
	public void testBookStatusBorrowedset01() {
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		String id ="01";
		String name ="CS3343";
		BookStatusBorrowed bookStatus =new BookStatusBorrowed();
		Book book = new Book(id,name,bookStatus);
		bookStatus.set(member,book);
		assertEquals(bookStatus.getStatus(),"Borrowed by 1   Test on 1-Jan-2017 until 8-Jan-2017");
	}
	@Test
	public void testBookStatusOnholdgetStatus() {
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		String id ="01";
		String name ="CS3343";
		BookStatusOnhold bookStatus =new BookStatusOnhold();
		Book book = new Book(id,name,bookStatus);
		book.addInQueueList(member);
		bookStatus.set(member,book);
		assertEquals(bookStatus.getStatus(),"On holdshelf for 1   Test until 4-Jan-2017 + 1 request(s): 1 ");
	}
	@Test
	public void testBookStatusBorrowedgetStatus() {
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		String id ="01";
		String name ="CS3343";
		BookStatusBorrowed bookStatus =new BookStatusBorrowed();
		Book book = new Book(id,name,bookStatus);
		book.addInQueueList(member);
		bookStatus.set(member,book);
		assertEquals(bookStatus.getStatus(),"Borrowed by 1   Test on 1-Jan-2017 until 8-Jan-2017 + 1 request(s): 1 ");
	}
	@Test
	public void testBookStatusOnholdgetDate() {
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		String id ="01";
		String name ="CS3343";
		BookStatusOnhold bookStatus =new BookStatusOnhold();
		Book book = new Book(id,name,bookStatus);
		book.addInQueueList(member);
		bookStatus.set(member,book);

		assertEquals(bookStatus.getDate().toString(),"4-Jan-2017");
	}
	@Test
	public void testBookStatusBorrowedgetDate() {
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		String id ="01";
		String name ="CS3343";
		BookStatusBorrowed bookStatus =new BookStatusBorrowed();
		Book book = new Book(id,name,bookStatus);
		book.addInQueueList(member);
		bookStatus.set(member,book);

		assertEquals(bookStatus.getDate().toString(),"1-Jan-2017");
	}
	@Test
	public void testBookaddInQueueListWithIndex01() {
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		String id ="01";
		String name ="CS3343";
		BookStatus bookStatus =new BookStatusAvailable();
		Book book = new Book(id,name,bookStatus);
		book.addInQueueListWithIndex(0,member);
		assertEquals(book.getQueueList(),"1 ");
	}
	@Test
	public void testBookaddInQueueListWithIndex02() {
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
			
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		String id ="01";
		String name ="CS3343";
		BookStatus bookStatus =new BookStatusAvailable();
		Book book = new Book(id,name,bookStatus);
		book.addInQueueList(member);
		member = new Member("10","Test",new MemberStatusNormal());
		book.addInQueueListWithIndex(0,member);
		assertEquals(book.getQueueList(),"10 1 ");
	}
	@Test
	public void testBooksizeOfQueueList01() {
		String id ="01";
		String name ="CS3343";
		BookStatus bookStatus =new BookStatusAvailable();
		Book book = new Book(id,name,bookStatus);
		assertEquals(book.sizeOfQueueList(),0);
	}
	@Test
	public void testBooksizeOfQueueList02() {
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		String id ="01";
		String name ="CS3343";
		BookStatus bookStatus =new BookStatusAvailable();
		Book book = new Book(id,name,bookStatus);
		book.addInQueueList(member);
		assertEquals(book.sizeOfQueueList(),1);
	}
	@Test
	public void testBookmemberFoundInQueue01() {
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		String id ="01";
		String name ="CS3343";
		BookStatus bookStatus =new BookStatusAvailable();
		Book book = new Book(id,name,bookStatus);
		assertEquals(book.memberFoundInQueue(member),false);
	}
	@Test
	public void testBookmemberFoundInQueue02() {
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		String id ="01";
		String name ="CS3343";
		BookStatus bookStatus =new BookStatusAvailable();
		Book book = new Book(id,name,bookStatus);
		book.addInQueueList(member);
		assertEquals(book.memberFoundInQueue(member),true);
	}
	@Test
	public void testBookmemberFoundInQueue03() {
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		String id ="01";
		String name ="CS3343";
		BookStatus bookStatus =new BookStatusAvailable();
		Book book = new Book(id,name,bookStatus);
		book.addInQueueList(member);
		member = new Member("9","New Test",new MemberStatusNormal());
		assertEquals(book.memberFoundInQueue(member),false);
	}
	@Test
	public void testBookleaveQueueList01() {
		String id ="01";
		String name ="CS3343";
		BookStatus bookStatus =new BookStatusAvailable();
		Book book = new Book(id,name,bookStatus);
		try{
			book.leaveQueueList();
		} catch(ArrayIndexOutOfBoundsException e){
			assertEquals(book.getQueueList(),"");
		}
	}
	@Test
	public void testBookleaveQueueList02() {
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		String id ="01";
		String name ="CS3343";
		BookStatus bookStatus =new BookStatusAvailable();
		Book book = new Book(id,name,bookStatus);
		book.addInQueueList(member);
		book.leaveQueueList();
		assertEquals(book.getQueueList(),"");
	}
	@Test
	public void testBookremoveFromQueueList01() {
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		String id ="01";
		String name ="CS3343";
		BookStatus bookStatus =new BookStatusAvailable();
		Book book = new Book(id,name,bookStatus);
		book.addInQueueList(member);
		book.removeFromQueueList(member);
		assertEquals(book.getQueueList(),"");
	}
	@Test
	public void testBooktakeFromQueueList01() {
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
			// TODO Auto-generated catch block
			
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		String id ="01";
		String name ="CS3343";
		BookStatus bookStatus =new BookStatusAvailable();
		Book book = new Book(id,name,bookStatus);
		book.addInQueueList(member);
		Member temp = book.takeFromQueueList();
		assertEquals(book.getQueueList(),"");
		boolean result = (temp==member);
		assertEquals(result,true);
	}
	@Test
	public void testBooktakeFromQueueList02() {
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member1 = new Member("1","Test",new MemberStatusNormal());
		Member member2 = new Member("9","Other Test",new MemberStatusNormal());
		String id ="01";
		String name ="CS3343";
		BookStatus bookStatus =new BookStatusAvailable();
		Book book = new Book(id,name,bookStatus);
		book.addInQueueList(member1);
		Member temp = book.takeFromQueueList();
		assertEquals(book.getQueueList(),"");
		boolean result = (temp==member2);
		assertEquals(result,false);
	}
	@Test
	public void testBooklistOrderInQueueList() {
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member1 = new Member("1","Test",new MemberStatusNormal());
		String id ="01";
		String name ="CS3343";
		BookStatus bookStatus =new BookStatusAvailable();
		Book book = new Book(id,name,bookStatus);
		book.addInQueueList(member1);
		assertEquals(book.listOrderInQueueList(member1),0);
	}
	@Test
	public void testMemberborrowed() {
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		member.borrowed();
		assertEquals(member.toString(),"1    Test      1-Jan-2017    1           0");
	}
	@Test
	public void testMemberreturned() {
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		member.borrowed();
		member.returned();
		assertEquals(member.toString(),"1    Test      1-Jan-2017    0           0");
	}
	@Test
	public void testMemberrequested() {
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		member.requested();
		assertEquals(member.toString(),"1    Test      1-Jan-2017    0           1");
	}
	@Test
	public void testMemberrequestCancel() {
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		member.requested();
		member.requestCancel();
		assertEquals(member.toString(),"1    Test      1-Jan-2017    0           0");
	}
	@Test
	public void testMembercompareTo01() {
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member1 = new Member("1","Test1",new MemberStatusNormal());
		Member member2 = new Member("2","Test2",new MemberStatusNormal());
		int result = member1.compareTo(member2);
		assertEquals(result,-1);
	}
	@Test
	public void testMembercompareTo02() {
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member1 = new Member("1","Test1",new MemberStatusNormal());
		Member member2 = new Member("1","Test2",new MemberStatusNormal());
		int result = member1.compareTo(member2);
		assertEquals(result,0);
	}
	@Test
	public void testMemberborrowBook01(){
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		String id ="01";
		String name ="CS3343";
		BookStatus bookStatus =new BookStatusAvailable();
		Book book = new Book(id,name,bookStatus);
		try {
			member.borrowBook(book);
		} catch (ExBookNotAvailable e) {
		} catch (ExLoanQuotaExceeded e) {
		} catch (ExMemberStatusSuspended e) {
		}
		BookStatusBorrowed newBookStatus = (BookStatusBorrowed) book.getBookStatus(); 
		boolean result = (newBookStatus.getMember()==member);
		assertEquals(result,true);
		result = newBookStatus instanceof BookStatusBorrowed;
		assertEquals(result,true);
	}
	@Test
	public void testMemberborrowBook02(){
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		String id ="01";
		String name ="CS3343";
		BookStatus bookStatus =new BookStatusBorrowed();
		Book book = new Book(id,name,bookStatus);
		try {
			member.borrowBook(book);
		} catch (ExBookNotAvailable e) {
			assertEquals(e.getMessage(),"Book not available!");
		} catch (ExLoanQuotaExceeded e) {
		} catch (ExMemberStatusSuspended e) {
		}
	}
	@Test
	public void testMemberborrowBook03(){
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		String id ="01";
		String name ="CS3343";
		BookStatus bookStatus =new BookStatusOnhold();
		Book book = new Book(id,name,bookStatus);
		try {
			member.borrowBook(book);
		} catch (ExBookNotAvailable e) {
			assertEquals(e.getMessage(),"Book not available!");
		} catch (ExLoanQuotaExceeded e) {
		} catch (ExMemberStatusSuspended e) {
		}
	}
	@Test
	public void testMemberborrowBook04(){
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		String id ="01";
		String name ="CS3343";
		BookStatusOnhold bookStatus =new BookStatusOnhold();
		Book book = new Book(id,name,bookStatus);
		bookStatus.set(member,book);
		try {
			member.borrowBook(book);
		} catch (ExBookNotAvailable e) {
		} catch (ExLoanQuotaExceeded e) {
		} catch (ExMemberStatusSuspended e) {
		}
		BookStatusBorrowed newBookStatus = (BookStatusBorrowed) book.getBookStatus(); 
		boolean result = (newBookStatus.getMember()==member);
		assertEquals(result,true);
		result = newBookStatus instanceof BookStatusBorrowed;
		assertEquals(result,true);
	}
	@Test
	public void testMemberborrowBook05(){
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		String id ="01";
		String name ="CS3343";
		BookStatusOnhold bookStatus =new BookStatusOnhold();
		Book book = new Book(id,name,bookStatus);
		bookStatus.set(member,book);
		for(int i=0;i<6;i++)
			member.borrowed();
		try {
			member.borrowBook(book);
		} catch (ExBookNotAvailable e) {
		} catch (ExLoanQuotaExceeded e) {
			assertEquals(e.getMessage(),"Loan quota exceeded!");
		} catch (ExMemberStatusSuspended e) {
		}
	}
	@Test
	public void testMemberborrowBook06(){
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member = new Member("1","Test",new MemberStatusSuspend());
		String id ="01";
		String name ="CS3343";
		BookStatusOnhold bookStatus =new BookStatusOnhold();
		Book book = new Book(id,name,bookStatus);
		bookStatus.set(member,book);
		try {
			member.borrowBook(book);
		} catch (ExBookNotAvailable e) {
		} catch (ExLoanQuotaExceeded e) {
		} catch (ExMemberStatusSuspended e) {
			assertEquals(e.getMessage(),"This member's status is suspended, all request / borrowing actions are denied.");
		}
	}
	@Test
	public void testMemberreturnBook01() throws ExBookNotAvailable, ExLoanQuotaExceeded, ExMemberStatusSuspended{
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		Member member = new Member("1","Test",new MemberStatusNormal());
		String id ="01";
		String name ="CS3343";
		BookStatus bookStatus =new BookStatusAvailable();
		Book book = new Book(id,name,bookStatus);
		member.borrowBook(book);
		BookStatusBorrowed newBookStatus = (BookStatusBorrowed) book.getBookStatus(); 
		boolean result = (newBookStatus.getMember()==member);
		assertEquals(result,true);
		result = newBookStatus instanceof BookStatusBorrowed;
		assertEquals(result,true);
		try {
			member.returnBook(book);
		} catch (ExNotBorrowedByThisMember e) {
		}
	}
}
