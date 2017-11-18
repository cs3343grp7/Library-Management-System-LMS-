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

import org.junit.Before;
import org.junit.Test;
import library.*;

public class testA {

    @Before
    public void resetSingleton() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        Field instance = Library.class.getDeclaredField("instance");
        instance.setAccessible(true);
 
        Constructor constructor = Library.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        instance.set(null, constructor.newInstance());
		try {
			SystemDate.createTheInstance("1-Jan-2017");
		} catch (ExDayNotValid e) {
		}
		
    }
    
	@Test
	public void testStartNewDay01() {
		String input = "startNewDay 03-Jan-2014";
		String[] cmdParts = input.split(" ");
		Command command = new CmdStartNewDay();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try {
			command.execute(cmdParts);
		} catch (Exception e) {
		}
		assertEquals("Done."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testStartNewDay02() {
		String input = "startNewDay 32-Jan-2014";
		String[] cmdParts = input.split(" ");
		Command command = new CmdStartNewDay();
		try {
			command.execute(cmdParts);
		} catch (Exception e) {
			assertEquals(e instanceof ExDayNotValid,true);
		}
	}
	@Test
	public void testStartNewDay03() {
		String input = "startNewDay 30-Apr-2014";
		String[] cmdParts = input.split(" ");
		Command command = new CmdStartNewDay();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try {
			command.execute(cmdParts);
		} catch (Exception e) {
		}
		assertEquals("Done."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testStartNewDay04() {
		String input = "startNewDay 31-Apr-2014";
		String[] cmdParts = input.split(" ");
		Command command = new CmdStartNewDay();
		try {
			command.execute(cmdParts);
		} catch (Exception e) {
			assertEquals(e instanceof ExDayNotValid,true);
		}
	}
	@Test
	public void testStartNewDay05() {
		String input = "startNewDay 28-Feb-2013";
		String[] cmdParts = input.split(" ");
		Command command = new CmdStartNewDay();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try {
			command.execute(cmdParts);
		} catch (Exception e) {
		}
		assertEquals("Done."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testStartNewDay06() {
		String input = "startNewDay 29-Feb-2012";
		String[] cmdParts = input.split(" ");
		Command command = new CmdStartNewDay();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try {
			command.execute(cmdParts);
		} catch (Exception e) {
		}
		assertEquals("Done."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testStartNewDay07() {
		String input = "startNewDay 29-Feb-2013";
		String[] cmdParts = input.split(" ");
		Command command = new CmdStartNewDay();
		try {
			command.execute(cmdParts);
		} catch (Exception e) {
			assertEquals(e instanceof ExDayNotValid,true);
		}
	}
	@Test
	public void testStartNewDay08() {
		String input = "startNewDay 30-Feb-2012";
		String[] cmdParts = input.split(" ");
		Command command = new CmdStartNewDay();
		try {
			command.execute(cmdParts);
		} catch (Exception e) {
			assertEquals(e instanceof ExDayNotValid,true);
		}
	}
	@Test
	public void testStartNewDay09() {
		String input = "startNewDay 0-Feb-2013";
		String[] cmdParts = input.split(" ");
		Command command = new CmdStartNewDay();
		try {
			command.execute(cmdParts);
		} catch (Exception e) {
			assertEquals(e instanceof ExDayNotValid,true);
		}
	}
	@Test
	public void testStartNewDay10() {
		String input = "startNewDay 0-fff-2013";
		String[] cmdParts = input.split(" ");
		Command command = new CmdStartNewDay();
		try {
			command.execute(cmdParts);
		} catch (Exception e) {
			assertEquals(e instanceof ExDayNotValid,true);
		}
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
		} 
		assertEquals("Done."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testArrive02() {
		String input = "arrive B1 Core_Java";
		String[] cmdParts = input.split(" ");
		Command command = new CmdArrive();
		try {
			command.execute(cmdParts);
			command.execute(cmdParts);
		} catch (Exception e) {
			assertEquals(e instanceof ExBookIDAlreadyInUse,true);
		} 
		
	}
	@Test
	public void testArrive03() {
		String input = "arrive B2";
		String[] cmdParts = input.split(" ");
		Command command = new CmdArrive();
		try {
			command.execute(cmdParts);
		} catch (Exception e) {
			assertEquals("Insufficient command arguments!",e.getMessage());
		} 
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
			command.execute(cmdParts);
		} catch (Exception e) {
			assertEquals(e instanceof ExMemberIDAlreadyInUse,true);
		} 
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
			assertEquals(e instanceof ExInsufficientCommand,true);
		} 
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
		assertEquals("ID   Name                Arrival     Status"+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testListBooks02() throws Exception{
		String input = "listBooks";
		String[] cmdParts = input.split(" ");
		Command command = new CmdListBooks();
		String input2 = "arrive B2 Core_Java";
		String[] cmdParts2 = input2.split(" ");
		Command command2 = new CmdArrive();
		command2.execute(cmdParts2);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try {
			command.execute(cmdParts);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		assertEquals("ID   Name                Arrival     Status"+System.getProperty("line.separator")+"B2   Core_Java           1-Jan-2017  Available"+System.getProperty("line.separator"),outContent.toString());
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
	public void testCmdArriveundoMe01() throws Exception {
		String input = "listBooks";
		String[] cmdParts = input.split(" ");
		Command command = new CmdListBooks();
		String input2 = "arrive B2 Core_Java";
		String[] cmdParts2 = input2.split(" ");
		RecordedCommand command2 = new CmdArrive();
		command2.execute(cmdParts2);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try {
			command2.undoMe();
			command.execute(cmdParts);
		} catch (Exception e) {
		}
		assertEquals("ID   Name                Arrival     Status"+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testCmdArriveundoMe02(){
		RecordedCommand command2 = new CmdArrive();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		RecordedCommand.undoOneCommand();
		assertEquals("Nothing to undo.",outContent.toString());
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
