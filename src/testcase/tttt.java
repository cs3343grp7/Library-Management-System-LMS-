package testcase;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import library.*;

public class tttt {
	ByteArrayOutputStream outContent;
	ByteArrayInputStream in;
	String str;
	@Before
	public void initialize() throws NoSuchFieldException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException{
        Field instance = Library.class.getDeclaredField("instance");
        instance.setAccessible(true);
        Constructor constructor = Library.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        instance.set(null, constructor.newInstance());
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	}
    @After
    public void clear() throws Exception{
    	Field field = RecordedCommand.class.getDeclaredField("undoList");
    	field.setAccessible(true);
    	field.set(null, new ArrayList<>());
        ArrayList<RecordedCommand> arr = (ArrayList<RecordedCommand>) field.get(field);
        arr.clear();
    	Field field2 = RecordedCommand.class.getDeclaredField("redoList");
    	field2.setAccessible(true);
    	field2.set(null, new ArrayList<>());
        ArrayList<RecordedCommand> arr2 = (ArrayList<RecordedCommand>) field.get(field);
        arr2.clear();
    }
	@Test
	public void testMain01() throws FileNotFoundException {
		String str = "quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(null);
		System.setIn(System.in);
		assertEquals("\n> END",outContent.toString());
	}
	@Test
	public void testMain02() throws FileNotFoundException {
		String str = ""+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(null);
		System.setIn(System.in);
		assertEquals("\n> \n> END",outContent.toString());
	}
	@Test
	public void testMain03() throws FileNotFoundException {
		String str = "asdas"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(null);
		System.setIn(System.in);
		assertEquals("\n> Unknown command - ignored!"+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdStartNewDay01() throws FileNotFoundException {
		String str = "startNewDay 03-Jan-2014"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(null);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdStartNewDay02() throws FileNotFoundException {
		String str = "startNewDay 32-Jan-2014"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(null);
		System.setIn(System.in);
		assertEquals("\n> Invalid date input!"+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdStartNewDay03() throws FileNotFoundException {
		String str = "startNewDay 30-Apr-2014"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(null);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdStartNewDay04() throws FileNotFoundException {
		String str = "startNewDay 31-Apr-2014"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(null);
		System.setIn(System.in);
		assertEquals("\n> Invalid date input!"+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdStartNewDay05() throws FileNotFoundException {
		String str = "startNewDay 28-Feb-2013"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(null);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdStartNewDay06() throws FileNotFoundException {
		String str = "startNewDay 29-Feb-2012"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(null);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdStartNewDay07() throws FileNotFoundException {
		String str = "startNewDay 29-Feb-2013"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(null);
		System.setIn(System.in);
		assertEquals("\n> Invalid date input!"+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdStartNewDay08() throws FileNotFoundException {
		String str = "startNewDay 30-Feb-2012"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(null);
		System.setIn(System.in);
		assertEquals("\n> Invalid date input!"+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdStartNewDay09() throws FileNotFoundException {
		String str = "startNewDay 0-Feb-2012"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(null);
		System.setIn(System.in);
		assertEquals("\n> Invalid date input!"+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdStartNewDay10() throws FileNotFoundException {
		String str = "startNewDay 10-fff-2012"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(null);
		System.setIn(System.in);
		assertEquals("\n> Invalid date input!"+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdStartNewDay11() throws FileNotFoundException {
		String str = "startNewDay 29-Feb-2000"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(null);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdStartNewDay12() throws FileNotFoundException {
		String str = "startNewDay 29-Feb-2100"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(null);
		System.setIn(System.in);
		assertEquals("\n> Invalid date input!"+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testArrive01() throws FileNotFoundException {
		String str = "arrive B1 Core_Java"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(null);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testArrive02() throws FileNotFoundException {
		str = "arrive B1"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(null);
		System.setIn(System.in);
		assertEquals("\n> Insufficient command arguments!"+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testArrive03() throws FileNotFoundException {
		str = "arrive B1 Core_Java"+System.getProperty("line.separator")+"arrive B1 Core_Java"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(null);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")+"\n> Book ID already in use: B1 Core_Java"+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testRegister01() throws FileNotFoundException {
		String str = "register 001 helena"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(null);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testRegister02() throws FileNotFoundException {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"register 001 helena"+System.getProperty("line.separator")
						+"listMembers"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(null);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
						+"\n> "+System.getProperty("line.separator")
						+"\n> END",outContent.toString());
	}
}

