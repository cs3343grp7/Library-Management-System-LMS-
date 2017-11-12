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
		Day day= new Day("1-Jan-2017");
		assertEquals(day.toString(),"1-Jan-2017");		
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
		assertEquals(Day.valid(2017,1,1),true);		
	}
	@Test
	public void testDayValid02() {
		assertEquals(Day.valid(2017,1,32),false);		
	}
	@Test
	public void testDayValid03() {
		assertEquals(Day.valid(2017,1,0),false);		
	}
	@Test
	public void testDayValid04() {
		assertEquals(Day.valid(2016,2,29),true);		
	}
	@Test
	public void testDayValid05() {
		assertEquals(Day.valid(2017,2,29),false);		
	}
	@Test
	public void testDayValid06() {
		assertEquals(Day.valid(2017,4,31),false);		
	}
	@Test
	public void testDayValid07() {
		assertEquals(Day.valid(2017,99,99),false);		
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
		SystemDate.createTheInstance("1-Jan-2017");
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
		SystemDate.createTheInstance("1-Jan-2017");
		Member member = new Member("1","Test");
		assertEquals(member.toString(),String.format("%-5s%-10s%-14s%-12d%d", "1","Test","1-Jan-2017",0,0));		
	}
	@Test
	public void testMembergetID01() {
		SystemDate.createTheInstance("1-Jan-2017");
		Member member = new Member("1","Test");
		assertEquals(member.getID(),"1");		
	}
	@Test
	public void testMembergetName01() {
		SystemDate.createTheInstance("1-Jan-2017");
		Member member = new Member("1","Test");
		assertEquals(member.getName(),"Test");		
	}
	@Test
	public void testMembergetListingHeader01() {
		assertEquals(Member.getListingHeader(),String.format("%-5s%-10s%-12s%-12s%s", "ID","Name","Join Date","#Borrowed","#Requested"));		
	}
	@Test
	public void testMembergetBorrowCounts() {
		SystemDate.createTheInstance("1-Jan-2017");
		Member member = new Member("1","Test");
		assertEquals(member.getBorrowCounts(),0);		
	}
	@Test
	public void testMembergetRequestCounts() {
		SystemDate.createTheInstance("1-Jan-2017");
		Member member = new Member("1","Test");
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
			System.out.println(e.getMessage());
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
}
