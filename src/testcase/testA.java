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
	public void testStartNewDay01() {
		String input = "startNewDay 03-Jan-2014";
		String[] cmdParts = input.split(" ");
		SystemDate.createTheInstance("03-Jan-2014");
		Command command = new CmdStartNewDay();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try {
			command.execute(cmdParts);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		assertEquals("Done."+System.getProperty("line.separator"),outContent.toString());
	}

	@Test
	public void testArrive01() {
		String input = "arrive B1 Core_Java";
		String[] cmdParts = input.split(" ");
		Command command = new CmdArrive();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try {
			command.execute(cmdParts);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		assertEquals("Done."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testArrive02() {
		String input = "arrive B1 Core_Java";
		String[] cmdParts = input.split(" ");
		Command command = new CmdArrive();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try {
			command.execute(cmdParts);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		assertEquals("Book ID already in use: B1 Core_Java"+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testArrive03() {
		String input = "arrive B2";
		String[] cmdParts = input.split(" ");
		Command command = new CmdArrive();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try {
			command.execute(cmdParts);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		assertEquals("Insufficient command arguments!"+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testRegister01() {
		String input = "register 001 helena";
		String[] cmdParts = input.split(" ");
		Command command = new CmdRegister();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try {
			command.execute(cmdParts);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		assertEquals("Done."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testRegister02() {
		String input = "register 001 helena";
		String[] cmdParts = input.split(" ");
		Command command = new CmdRegister();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try {
			command.execute(cmdParts);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		assertEquals("Book ID already in use: 001 helena"+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testRegister03() {
		String input = "register";
		String[] cmdParts = input.split(" ");
		Command command = new CmdRegister();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try {
			command.execute(cmdParts);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		assertEquals("Insufficient command arguments!"+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testListBooks01() {
		String input = "listBooks";
		String[] cmdParts = input.split(" ");
		Command command = new CmdListBooks();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try {
			command.execute(cmdParts);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		assertEquals("ID   Name                Arrival     Status"+System.getProperty("line.separator")+"B1   Core_Java           3-Jan-2014  Available"+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testListMembers01() {
		String input = "listMembers";
		String[] cmdParts = input.split(" ");
		Command command = new CmdListMembers();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try {
			command.execute(cmdParts);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		assertEquals("ID   Name      Join Date   #Borrowed   #Requested"+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testArrive01Undo() {
		String input = "arrive B2 TestBook";
		String[] cmdParts = input.split(" ");
		RecordedCommand command = new CmdArrive();
		String listBooks = "listBooks";
		String[] arrListBooks = listBooks.split(" ");
		Command cmdListBooks = new CmdListBooks();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try {
			command.execute(cmdParts);
			RecordedCommand.undoOneCommand();
			cmdListBooks.execute(arrListBooks);			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		assertEquals("Done."+System.getProperty("line.separator")+"ID   Name                Arrival     Status"+System.getProperty("line.separator")+"B1   Core_Java           3-Jan-2014  Available"+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testArrive02Redo() {
		String listBooks = "listBooks";
		String[] arrListBooks = listBooks.split(" ");
		Command cmdListBooks = new CmdListBooks();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try {
			RecordedCommand.redoOneCommand();
			cmdListBooks.execute(arrListBooks);			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		assertEquals("ID   Name                Arrival     Status"+System.getProperty("line.separator")+"B1   Core_Java           3-Jan-2014  Available"+System.getProperty("line.separator")+"B2   TestBook            3-Jan-2014  Available"+System.getProperty("line.separator"),outContent.toString());
	}
}
