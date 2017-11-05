package testcase;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Test;
import library.*;

public class testA {

//	@Test
//	public void test01() {
//		String input = "quit";
//		String input = "register 001 helena" + System.getProperty("line.separator")+
//				"quit"+System.getProperty("line.seprator");
		
//		InputStream sInputStream = System.in;
//		System.setIn(new ByteArrayInputStream(input.getBytes()));
//		System.setIn(new ByteArrayInputStream(
//		           input.getBytes()));
//		System.setIn(sInputStream);
//		    ByteArrayOutputStream outContent 
//		           = new ByteArrayOutputStream();
//		    System.setOut(new PrintStream(outContent));
//		    try {
//				String[] args = {"zxC","ZXC"};
//				Main.main(args);
//			} catch (FileNotFoundException e) {
//				System.out.println(e.getMessage());
//			}
//		    assertEquals("Done./n", outContent.toString());
//
//	}
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
	
//	@Test
//	public void test01() {
//		String input = "arrive B1 Core_Java";
//		String[] cmdParts = input.split(" ");
//		SystemDate.createTheInstance("03-Jan-2014");
//		Command command = new CmdArrive();
//		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//		System.setOut(new PrintStream(outContent));
//		try {
//			command.execute(cmdParts);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		} 
//		assertEquals("Done."+System.getProperty("line.separator"),outContent.toString());
//	}
}
