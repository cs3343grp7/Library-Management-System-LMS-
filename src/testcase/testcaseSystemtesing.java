package testcase;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import library.*;

public class testcaseSystemtesing {
	ByteArrayOutputStream outContent;
	ByteArrayInputStream in;
	String str;
	String borrowDate;
	String onholdDate;
	String[] args = {"main","src//testcase//memberData.txt","src//testcase//bookData.txt"};
	@Before
	public void initialize() throws NoSuchFieldException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException, ExDayNotValid, FileNotFoundException, UnsupportedEncodingException{
        Field instance = Library.class.getDeclaredField("instance");
        instance.setAccessible(true);
        Constructor constructor = Library.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        instance.set(null, constructor.newInstance());
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_WEEK, 7);
		String[] temp = cal.getTime().toString().split(" ");
		Day d = new Day(temp[2]+"-"+temp[1]+"-"+temp[5]);
		borrowDate= " Borrow due on "+d.toString()+".";
		cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_WEEK, 3);
		temp = cal.getTime().toString().split(" ");
		d = new Day(temp[2]+"-"+temp[1]+"-"+temp[5]);
		onholdDate= d.toString();
		PrintWriter memberDataWriter = new PrintWriter("src//testcase//memberData.txt", "UTF-8");
        memberDataWriter.close();
        PrintWriter bookDataWriter = new PrintWriter("src//testcase//bookData.txt", "UTF-8");
        bookDataWriter.close();
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
        PrintWriter memberDataWriter = new PrintWriter("src//testcase//memberData.txt", "UTF-8");
        memberDataWriter.close();
        PrintWriter bookDataWriter = new PrintWriter("src//testcase//bookData.txt", "UTF-8");
        bookDataWriter.close();
    }
	@Test
	public void testMain01() throws Exception {
		String str = "quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> END",outContent.toString());
	}
	@Test
	public void testMain02() throws Exception {
		String str = ""+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> \n> END",outContent.toString());
	}
	@Test
	public void testMain03() throws Exception {
		String str = "asdas"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Unknown command - ignored!"+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdStartNewDay01() throws Exception {
		String str = "startNewDay 03-Jan-2014"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdStartNewDay02() throws Exception {
		String str = "startNewDay 32-Jan-2014"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Invalid date input!"+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdStartNewDay03() throws Exception {
		String str = "startNewDay 30-Apr-2014"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdStartNewDay04() throws Exception {
		String str = "startNewDay 31-Apr-2014"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Invalid date input!"+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdStartNewDay05() throws Exception {
		String str = "startNewDay 28-Feb-2013"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdStartNewDay06() throws Exception {
		String str = "startNewDay 29-Feb-2012"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdStartNewDay07() throws Exception {
		String str = "startNewDay 29-Feb-2013"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Invalid date input!"+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdStartNewDay08() throws Exception {
		String str = "startNewDay 30-Feb-2012"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Invalid date input!"+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdStartNewDay09() throws Exception {
		String str = "startNewDay 0-Feb-2012"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Invalid date input!"+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdStartNewDay10() throws Exception {
		String str = "startNewDay 10-fff-2012"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Invalid date input!"+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdStartNewDay11() throws Exception {
		String str = "startNewDay 29-Feb-2000"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdStartNewDay12() throws Exception {
		String str = "startNewDay 29-Feb-2100"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Invalid date input!"+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdStartNewDay13() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"register 002 cc"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"startNewDay 21-Feb-2099"+System.getProperty("line.separator")
						+"startNewDay 28-Feb-2099"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Borrow period is over for B1 Core_Java."+System.getProperty("line.separator")
					+"helena's membership is now suspended until all overdue books have been returned."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> Borrow period is over for B1 Core_Java."+System.getProperty("line.separator")
					+"helena's membership is now suspended until all overdue books have been returned."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdStartNewDay14() throws Exception, ExBookNotFound {
		String str = "startNewDay 1-Dec-2017"+System.getProperty("line.separator")
						+"register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"register 002 cc"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"checkin 001 B1"+System.getProperty("line.separator")
						+"startNewDay 28-Feb-2099"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done. Borrow due on 8-Dec-2017."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Book [B1 Core_Java] is ready for pick up by [002 cc].  On hold due on 4-Dec-2017."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> On hold period is over for B1 Core_Java."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdStartNewDay15() throws Exception, ExBookNotFound {
		String str = 	"startNewDay 1-Dec-2017"+System.getProperty("line.separator")
						+"register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"register 002 cc"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"checkin 001 B1"+System.getProperty("line.separator")
						+"startNewDay 28-Feb-2011"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done. Borrow due on 8-Dec-2017."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Book [B1 Core_Java] is ready for pick up by [002 cc].  On hold due on 4-Dec-2017."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdStartNewDay16() throws Exception, ExBookNotFound {
		String str = "startNewDay 1-Dec-2017"+System.getProperty("line.separator")
						+"register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"register 002 cc"+System.getProperty("line.separator")
						+"register 003 dd"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"request 003 B1"+System.getProperty("line.separator")
						+"checkin 001 B1"+System.getProperty("line.separator")
						+"startNewDay 28-Feb-2099"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done. Borrow due on 8-Dec-2017."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 2 in the queue."+System.getProperty("line.separator")
					+"\n> Book [B1 Core_Java] is ready for pick up by [002 cc].  On hold due on 4-Dec-2017."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> On hold period is over for B1 Core_Java."+System.getProperty("line.separator")
					+"Book [B1 Core_Java] is ready for pick up by [003 dd].  On hold due on 3-Mar-2099."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdStartNewDay17() throws Exception, ExBookNotFound {
		String str = "startNewDay 1-Jan-2017"+System.getProperty("line.separator")
						+"register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"register 002 cc"+System.getProperty("line.separator")
						+"register 003 dd"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"request 003 B1"+System.getProperty("line.separator")
						+"checkin 001 B1"+System.getProperty("line.separator")
						+"startNewDay 28-Feb-2017"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done. Borrow due on 8-Jan-2017."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 2 in the queue."+System.getProperty("line.separator")
					+"\n> Book [B1 Core_Java] is ready for pick up by [002 cc].  On hold due on 4-Jan-2017."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> On hold period is over for B1 Core_Java."+System.getProperty("line.separator")
					+"Book [B1 Core_Java] is ready for pick up by [003 dd].  On hold due on 3-Mar-2017."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdStartNewDay18() throws Exception, ExBookNotFound {
		String str = "startNewDay 1-Jan-2017"+System.getProperty("line.separator")
						+"register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"register 002 cc"+System.getProperty("line.separator")
						+"register 003 dd"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"request 003 B1"+System.getProperty("line.separator")
						+"checkin 001 B1"+System.getProperty("line.separator")
						+"startNewDay 20-Jan-2017"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done. Borrow due on 8-Jan-2017."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 2 in the queue."+System.getProperty("line.separator")
					+"\n> Book [B1 Core_Java] is ready for pick up by [002 cc].  On hold due on 4-Jan-2017."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> On hold period is over for B1 Core_Java."+System.getProperty("line.separator")
					+"Book [B1 Core_Java] is ready for pick up by [003 dd].  On hold due on 23-Jan-2017."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdStartNewDay19() throws Exception, ExBookNotFound {
		String str = "startNewDay 15-Jan-2017"+System.getProperty("line.separator")
						+"register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"register 002 cc"+System.getProperty("line.separator")
						+"register 003 dd"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"request 003 B1"+System.getProperty("line.separator")
						+"checkin 001 B1"+System.getProperty("line.separator")
						+"startNewDay 10-Jan-2017"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done. Borrow due on 22-Jan-2017."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 2 in the queue."+System.getProperty("line.separator")
					+"\n> Book [B1 Core_Java] is ready for pick up by [002 cc].  On hold due on 18-Jan-2017."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
	}
	@Test
	public void testArrive01() throws Exception {
		String str = "arrive B1 Core_Java"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testArrive02() throws Exception {
		str = "arrive B1"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Insufficient command arguments!"+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testArrive03() throws Exception {
		str = "arrive B1 Core_Java"+System.getProperty("line.separator")+"arrive B1 Core_Java"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")+"\n> Book ID already in use: B1 Core_Java"+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testRegister01() throws Exception {
		String str = "register 001 helena"+System.getProperty("line.separator")+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")+"\n> END",outContent.toString());
	}
	@Test
	public void testRegister02() throws Exception {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"register 001 helena"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
						+"\n> Member ID already in use: 001 helena"+System.getProperty("line.separator")
						+"\n> END",outContent.toString());
	}
	@Test
	public void testRegister03() throws Exception {
		String str = "register 001"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Insufficient command arguments!"+System.getProperty("line.separator")
						+"\n> END",outContent.toString());
	}
	@Test
	public void testlistBooks01() throws Exception {
		String str = "listBooks"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> ID   Name                Arrival     Status"+System.getProperty("line.separator")
						+"\n> END",outContent.toString());
	}
	@Test
	public void testlistBooks02() throws Exception {
		String str = "startNewDay 1-Jan-2017"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"listBooks"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
						+"\n> Done."+System.getProperty("line.separator")
						+"\n> ID   Name                Arrival     Status"+System.getProperty("line.separator")
						+"B1   Core_Java           1-Jan-2017  Available"+System.getProperty("line.separator")
						+"\n> END",outContent.toString());
	}
	@Test
	public void testListMembers01() throws Exception {
		String str ="listMembers"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> ID   Name      Join Date   #Borrowed   #Requested"+System.getProperty("line.separator")
						+"\n> END",outContent.toString());
	}
	@Test
	public void testListMembers02() throws Exception {
		String str = "startNewDay 1-Dec-2017"+System.getProperty("line.separator")
						+"register 001 daniel"+System.getProperty("line.separator")
						+"listMembers"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
						+"\n> Done."+System.getProperty("line.separator")
						+"\n> ID   Name      Join Date   #Borrowed   #Requested"+System.getProperty("line.separator")
						+"001  daniel    1-Dec-2017    0           0"+System.getProperty("line.separator")
						+"\n> END",outContent.toString());
	}
	@Test
	public void testArriveundo() throws Exception {
		String str = "arrive B1 Core_Java"+System.getProperty("line.separator")
						+"undo"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
						+"\n> "+System.getProperty("line.separator")
						+"\n> END",outContent.toString());
		try {
			Library.getInstance().findBook("B1").getName();
		} catch (ExBookNotFound e) {
			assertEquals(e instanceof ExBookNotFound, true);
		}
	}
	@Test
	public void testArriveredo() throws Exception, ExBookNotFound {
		String str = "arrive B1 Core_Java"+System.getProperty("line.separator")
						+"undo"+System.getProperty("line.separator")
						+"redo"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
						+"\n> "+System.getProperty("line.separator")
						+"\n> "+System.getProperty("line.separator")
						+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getName(),"Core_Java");
	}
	@Test
	public void testRegisterundo() throws Exception {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"undo"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
		+"\n> "+System.getProperty("line.separator")
		+"\n> END",outContent.toString());
		try {
			Library.getInstance().findMember("001");
		} catch (ExMemberNotFound e) {
			assertEquals(e instanceof ExMemberNotFound, true);
		}
	}
	@Test
	public void testRegisterredo() throws Exception, ExMemberNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"undo"+System.getProperty("line.separator")
						+"redo"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> "+System.getProperty("line.separator")
					+"\n> "+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findMember("001").getName(),"helena");
	}
	@Test
	public void testCmdCheckout01() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdCheckout02() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B2"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Book not found!"+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdCheckout03() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Insufficient command arguments!"+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdCheckout04() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 002 B1"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Member not found!"+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdCheckout05() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B2"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Book not found!"+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdCheckout06() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"register 002 cc"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"checkout 002 B1"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Book not available!"+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdCheckout07() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"arrive B2 Core_Java"+System.getProperty("line.separator")
						+"arrive B3 Core_Java"+System.getProperty("line.separator")
						+"arrive B4 Core_Java"+System.getProperty("line.separator")
						+"arrive B5 Core_Java"+System.getProperty("line.separator")
						+"arrive B6 Core_Java"+System.getProperty("line.separator")
						+"arrive B7 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"checkout 001 B2"+System.getProperty("line.separator")
						+"checkout 001 B3"+System.getProperty("line.separator")
						+"checkout 001 B4"+System.getProperty("line.separator")
						+"checkout 001 B5"+System.getProperty("line.separator")
						+"checkout 001 B6"+System.getProperty("line.separator")
						+"checkout 001 B7"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Loan quota exceeded!"+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
	}
	@Test
	public void testCmdCheckout08() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"arrive B2 C++"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"startNewDay 25-Feb-2018"+System.getProperty("line.separator")
						+"checkout 001 B2"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Borrow period is over for B1 Core_Java."+System.getProperty("line.separator")
					+"helena's membership is now suspended until all overdue books have been returned."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> This member's status is suspended, all request / borrowing actions are denied."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdCheckout09() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"register 002 cc"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdCheckout10() throws Exception, ExBookNotFound {
		String str = "startNewDay 1-Dec-2017"+System.getProperty("line.separator")
						+"register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"register 002 cc"+System.getProperty("line.separator")
						+"register 003 dd"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"checkin 001 B1"+System.getProperty("line.separator")
						+"checkout 003 B1"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done. Borrow due on 8-Dec-2017."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Book [B1 Core_Java] is ready for pick up by [002 cc].  On hold due on 4-Dec-2017."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> Book not available!"+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusOnhold,true);
	}
	@Test
	public void testCmdCheckout11() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"startNewDay 10-Jan-2017"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done. Borrow due on 17-Jan-2017."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdCheckout12() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"startNewDay 25-Jan-2017"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done. Borrow due on 1-Feb-2017."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdCheckout13() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"startNewDay 24-Jun-2017"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done. Borrow due on 1-Jul-2017."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdCheckout14() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"startNewDay 2-Jun-2017"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done. Borrow due on 9-Jun-2017."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdCheckout15() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"startNewDay 2-Feb-2017"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done. Borrow due on 9-Feb-2017."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdCheckout16() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"startNewDay 23-Feb-2016"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done. Borrow due on 1-Mar-2016."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdCheckout17() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"startNewDay 25-Dec-2016"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done. Borrow due on 1-Jan-2017."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdCheckout18() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"startNewDay 1-Dec-2016"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done. Borrow due on 8-Dec-2016."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdCheckout19() throws Exception {
		String temp = "startNewDay 22-Feb-2017";
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+temp+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"quit";
		String[] arr = temp.split(" ");
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		Day d= new Day(arr[1]);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done. Borrow due on 1-Mar-2017."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdCheckout20() throws Exception {
		String temp = "startNewDay 3-Feb-2016";
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+temp+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"quit";
		String[] arr = temp.split(" ");
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		Day d= new Day(arr[1]);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done. Borrow due on 10-Feb-2016."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdCheckoutUndo01() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"undo"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> "+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusAvailable,true);
	}
	@Test
	public void testCmdCheckoutUndo02() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"register 002 sing"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"checkin 001 B1"+System.getProperty("line.separator")
						+"checkout 002 B1"+System.getProperty("line.separator")
						+"undo"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Book [B1 Core_Java] is ready for pick up by [002 sing].  On hold due on "+onholdDate+"."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> "+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusOnhold,true);
	}
	@Test
	public void testCmdCheckoutRedo() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"undo"+System.getProperty("line.separator")
						+"redo"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> "+System.getProperty("line.separator")
					+"\n> "+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdCheckin01() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"checkin 001 B1"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusAvailable,true);
	}
	@Test
	public void testCmdCheckin02() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"checkin 001"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Insufficient command arguments!"+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdCheckin03() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"checkin 001 B2"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Book not found!"+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdCheckin04() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"checkin 002 B1"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Member not found!"+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdCheckin05() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"register 002 cc"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"checkin 002 B1"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> The book is not borrowed by this member!"+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdCheckin06() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"register 002 cc"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"checkin 001 B1"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Book [B1 Core_Java] is ready for pick up by [002 cc].  On hold due on "+onholdDate+"."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusOnhold,true);
	}
	@Test
	public void testCmdCheckin07() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"startNewDay 25-Feb-2099"+System.getProperty("line.separator")
						+"checkin 001 B1"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Borrow period is over for B1 Core_Java."+System.getProperty("line.separator")
					+"helena's membership is now suspended until all overdue books have been returned."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> helena has returned all overdue book(s) and suspension is stopped."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusAvailable,true);
	}
	@Test
	public void testCmdCheckin08() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkin 001 B1"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Book is not borrowed!"+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusAvailable,true);
	}
	@Test
	public void testCmdCheckin09() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkin 001 B1"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Book is not borrowed!"+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusAvailable,true);
	}
	@Test
	public void testCmdCheckin10() throws Exception {
		String temp = "startNewDay 26-Feb-2017";
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"register 002 Sing"+System.getProperty("line.separator")
						+"register 003 Daniel"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"request 003 B1"+System.getProperty("line.separator")
						+temp+System.getProperty("line.separator")
						+"checkin 001 B1"+System.getProperty("line.separator")
						+"quit";
		String[] arr = temp.split(" ");
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		Day d= new Day(arr[1]);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 2 in the queue."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Book [B1 Core_Java] is ready for pick up by [002 Sing].  On hold due on "+d.getOnholdDeadLineDate()+"."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusOnhold,true);
	}
	@Test
	public void testCmdCheckin11() throws Exception {
		String temp = "startNewDay 27-Feb-2016";
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"register 002 Sing"+System.getProperty("line.separator")
						+"register 003 Daniel"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"request 003 B1"+System.getProperty("line.separator")
						+temp+System.getProperty("line.separator")
						+"checkin 001 B1"+System.getProperty("line.separator")
						+"quit";
		String[] arr = temp.split(" ");
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		Day d= new Day(arr[1]);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 2 in the queue."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Book [B1 Core_Java] is ready for pick up by [002 Sing].  On hold due on "+d.getOnholdDeadLineDate()+"."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusOnhold,true);
	}
	@Test
	public void testCmdCheckin12() throws Exception {
		String temp = "startNewDay 1-Jan-2016";
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"register 002 Sing"+System.getProperty("line.separator")
						+"register 003 Daniel"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"request 003 B1"+System.getProperty("line.separator")
						+temp+System.getProperty("line.separator")
						+"checkin 001 B1"+System.getProperty("line.separator")
						+"quit";
		String[] arr = temp.split(" ");
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		Day d= new Day(arr[1]);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 2 in the queue."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Book [B1 Core_Java] is ready for pick up by [002 Sing].  On hold due on "+d.getOnholdDeadLineDate()+"."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusOnhold,true);
	}
	@Test
	public void testCmdCheckin13() throws Exception {
		String temp = "startNewDay 29-Jan-2016";
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"register 002 Sing"+System.getProperty("line.separator")
						+"register 003 Daniel"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"request 003 B1"+System.getProperty("line.separator")
						+temp+System.getProperty("line.separator")
						+"checkin 001 B1"+System.getProperty("line.separator")
						+"quit";
		String[] arr = temp.split(" ");
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		Day d= new Day(arr[1]);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 2 in the queue."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Book [B1 Core_Java] is ready for pick up by [002 Sing].  On hold due on "+d.getOnholdDeadLineDate()+"."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusOnhold,true);
	}
	@Test
	public void testCmdCheckin14() throws Exception {
		String temp = "startNewDay 2-Feb-2016";
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"register 002 Sing"+System.getProperty("line.separator")
						+"register 003 Daniel"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"request 003 B1"+System.getProperty("line.separator")
						+temp+System.getProperty("line.separator")
						+"checkin 001 B1"+System.getProperty("line.separator")
						+"quit";
		String[] arr = temp.split(" ");
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		Day d= new Day(arr[1]);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 2 in the queue."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Book [B1 Core_Java] is ready for pick up by [002 Sing].  On hold due on "+d.getOnholdDeadLineDate()+"."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusOnhold,true);
	}
	@Test
	public void testCmdCheckin15() throws Exception {
		String temp = "startNewDay 28-Apr-2016";
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"register 002 Sing"+System.getProperty("line.separator")
						+"register 003 Daniel"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"request 003 B1"+System.getProperty("line.separator")
						+temp+System.getProperty("line.separator")
						+"checkin 001 B1"+System.getProperty("line.separator")
						+"quit";
		String[] arr = temp.split(" ");
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		Day d= new Day(arr[1]);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 2 in the queue."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Book [B1 Core_Java] is ready for pick up by [002 Sing].  On hold due on "+d.getOnholdDeadLineDate()+"."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusOnhold,true);
	}
	@Test
	public void testCmdCheckin16() throws Exception {
		String temp = "startNewDay 2-Jun-2016";
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"register 002 Sing"+System.getProperty("line.separator")
						+"register 003 Daniel"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"request 003 B1"+System.getProperty("line.separator")
						+temp+System.getProperty("line.separator")
						+"checkin 001 B1"+System.getProperty("line.separator")
						+"quit";
		String[] arr = temp.split(" ");
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		Day d= new Day(arr[1]);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 2 in the queue."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Book [B1 Core_Java] is ready for pick up by [002 Sing].  On hold due on "+d.getOnholdDeadLineDate()+"."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusOnhold,true);
	}
	@Test
	public void testCmdCheckin17() throws Exception {
		String temp = "startNewDay 29-Dec-2016";
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"register 002 Sing"+System.getProperty("line.separator")
						+"register 003 Daniel"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"request 003 B1"+System.getProperty("line.separator")
						+temp+System.getProperty("line.separator")
						+"checkin 001 B1"+System.getProperty("line.separator")
						+"quit";
		String[] arr = temp.split(" ");
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		Day d= new Day(arr[1]);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 2 in the queue."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Book [B1 Core_Java] is ready for pick up by [002 Sing].  On hold due on "+d.getOnholdDeadLineDate()+"."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusOnhold,true);
	}
	@Test
	public void testCmdCheckin18() throws Exception {
		String temp = "startNewDay 2-Dec-2016";
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"register 002 Sing"+System.getProperty("line.separator")
						+"register 003 Daniel"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"request 003 B1"+System.getProperty("line.separator")
						+temp+System.getProperty("line.separator")
						+"checkin 001 B1"+System.getProperty("line.separator")
						+"quit";
		String[] arr = temp.split(" ");
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		Day d= new Day(arr[1]);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 2 in the queue."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Book [B1 Core_Java] is ready for pick up by [002 Sing].  On hold due on "+d.getOnholdDeadLineDate()+"."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusOnhold,true);
	}
	@Test
	public void testCmdCheckin19() throws Exception {
		String temp = "startNewDay 22-Feb-2017";
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"register 002 Sing"+System.getProperty("line.separator")
						+"register 003 Daniel"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"request 003 B1"+System.getProperty("line.separator")
						+temp+System.getProperty("line.separator")
						+"checkin 001 B1"+System.getProperty("line.separator")
						+"quit";
		String[] arr = temp.split(" ");
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		Day d= new Day(arr[1]);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 2 in the queue."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Book [B1 Core_Java] is ready for pick up by [002 Sing].  On hold due on "+d.getOnholdDeadLineDate()+"."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusOnhold,true);
	}
	@Test
	public void testCmdCheckinundo01() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"checkin 001 B1"+System.getProperty("line.separator")
						+"undo"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> "+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdCheckinundo02() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"register 002 Sing"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"checkin 001 B1"+System.getProperty("line.separator")
						+"undo"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Book [B1 Core_Java] is ready for pick up by [002 Sing].  On hold due on "+onholdDate+"."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> Sorry. 002 Sing please ignore the pick up notice for B1 Core_Java."+System.getProperty("line.separator")
					+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdCheckinundo03() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"startNewDay 25-Feb-2097"+System.getProperty("line.separator")
						+"checkin 001 B1"+System.getProperty("line.separator")
						+"undo"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Borrow period is over for B1 Core_Java."+System.getProperty("line.separator")
					+"helena's membership is now suspended until all overdue books have been returned."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> helena has returned all overdue book(s) and suspension is stopped."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> "+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdCheckinredo01() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"checkin 001 B1"+System.getProperty("line.separator")
						+"undo"+System.getProperty("line.separator")
						+"redo"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> "+System.getProperty("line.separator")
					+"\n> "+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusAvailable,true);
	}
	@Test
	public void testCmdCheckinredo02() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"register 002 Sing"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"checkin 001 B1"+System.getProperty("line.separator")
						+"undo"+System.getProperty("line.separator")
						+"redo"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Book [B1 Core_Java] is ready for pick up by [002 Sing].  On hold due on "+onholdDate+"."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> Sorry. 002 Sing please ignore the pick up notice for B1 Core_Java."+System.getProperty("line.separator")
					+System.getProperty("line.separator")
					+"\n> Book [B1 Core_Java] is ready for pick up by [002 Sing].  On hold due on "+onholdDate+"."+System.getProperty("line.separator")
					+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusOnhold,true);
	}
	@Test
	public void testCmdCheckinredo03() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"startNewDay 25-Feb-2097"+System.getProperty("line.separator")
						+"checkin 001 B1"+System.getProperty("line.separator")
						+"undo"+System.getProperty("line.separator")
						+"redo"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Borrow period is over for B1 Core_Java."+System.getProperty("line.separator")
					+"helena's membership is now suspended until all overdue books have been returned."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> helena has returned all overdue book(s) and suspension is stopped."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> "+System.getProperty("line.separator")
					+"\n> helena has returned all overdue book(s) and suspension is stopped."+System.getProperty("line.separator")
					+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusAvailable,true);
	}
	@Test
	public void testCmdRequest01() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"register 002 cc"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdRequest02() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"register 002 cc"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Insufficient command arguments!"+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdRequest03() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Member not found!"+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdRequest04() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"request 001 B1"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> The book is currently available!"+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusAvailable,true);
	}
	@Test
	public void testCmdRequest05() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"request 001 B2"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Book not found!"+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusAvailable,true);
	}
	@Test
	public void testCmdRequest06() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 001 B1"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> The book is already borrowed by the same member!"+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdRequest07() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"register 002 cc"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"arrive B2 C++"+System.getProperty("line.separator")
						+"arrive B3 C"+System.getProperty("line.separator")
						+"arrive B4 C#"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"checkout 001 B2"+System.getProperty("line.separator")
						+"checkout 001 B3"+System.getProperty("line.separator")
						+"checkout 001 B4"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"request 002 B2"+System.getProperty("line.separator")
						+"request 002 B3"+System.getProperty("line.separator")
						+"request 002 B4"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Book request quota exceeded!"+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdRequest08() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"register 002 cc"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> The same member has already requested the book!"+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdRequest09() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"arrive B2 C+"+System.getProperty("line.separator")
						+"register 002 cc"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"checkout 002 B2"+System.getProperty("line.separator")
						+"startNewDay 25-Feb-2099"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Borrow period is over for B1 Core_Java."+System.getProperty("line.separator")
					+"helena's membership is now suspended until all overdue books have been returned."+System.getProperty("line.separator")
					+"Borrow period is over for B2 C+."+System.getProperty("line.separator")
					+"cc's membership is now suspended until all overdue books have been returned."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> This member's status is suspended, all request / borrowing actions are denied."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdRequest10() throws Exception, ExBookNotFound {
		String str = "startNewDay 1-Dec-2017"+System.getProperty("line.separator")
						+"register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"register 002 cc"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"checkin 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done. Borrow due on 8-Dec-2017."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Book [B1 Core_Java] is ready for pick up by [002 cc].  On hold due on 4-Dec-2017."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> The book is currently available!"+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusOnhold,true);
	}
	@Test
	public void testCmdRequestundo() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"register 002 cc"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"undo"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> "+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdRequestredo() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"register 002 cc"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"undo"+System.getProperty("line.separator")
						+"redo"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> "+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdCancelRequest01() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"register 002 cc"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"cancelRequest 002 B1"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdCancelRequest02() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"register 002 cc"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"cancelRequest 002"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Insufficient command arguments!"+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdCancelRequest03() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"register 002 cc"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"cancelRequest 002 B1"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Request record is not found!"+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdCancelRequestundo() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"register 002 cc"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"cancelRequest 002 B1"+System.getProperty("line.separator")
						+"undo"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> "+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdCancelRequestredo() throws Exception, ExBookNotFound {
		String str = "register 001 helena"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"register 002 cc"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"cancelRequest 002 B1"+System.getProperty("line.separator")
						+"undo"+System.getProperty("line.separator")
						+"redo"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+borrowDate+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> "+System.getProperty("line.separator")
					+"\n> "+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testIntegration() throws Exception, ExBookNotFound {
		String str = "startNewDay 1-Dec-2017"+System.getProperty("line.separator")
						+"register 001 helena"+System.getProperty("line.separator")
						+"register 002 cc"+System.getProperty("line.separator")
						+"register 003 ss"+System.getProperty("line.separator")
						+"register 004 dd"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"arrive B2 C"+System.getProperty("line.separator")
						+"arrive B3 C++"+System.getProperty("line.separator")
						+"arrive B4 C#"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"checkout 001 B2"+System.getProperty("line.separator")
						+"checkout 001 B3"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"request 002 B2"+System.getProperty("line.separator")
						+"request 003 B2"+System.getProperty("line.separator")
						+"checkin 001 B1"+System.getProperty("line.separator")
						+"checkin 001 B2"+System.getProperty("line.separator")
						+"listMembers"+System.getProperty("line.separator")
						+"listBooks"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done. Borrow due on 8-Dec-2017."+System.getProperty("line.separator")
					+"\n> Done. Borrow due on 8-Dec-2017."+System.getProperty("line.separator")
					+"\n> Done. Borrow due on 8-Dec-2017."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 2 in the queue."+System.getProperty("line.separator")
					+"\n> Book [B1 Core_Java] is ready for pick up by [002 cc].  On hold due on 4-Dec-2017."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> Book [B2 C] is ready for pick up by [002 cc].  On hold due on 4-Dec-2017."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> ID   Name      Join Date   #Borrowed   #Requested"+System.getProperty("line.separator")
					+"001  helena    1-Dec-2017    1           0"+System.getProperty("line.separator")
					+"002  cc        1-Dec-2017    0           0"+System.getProperty("line.separator")
					+"003  ss        1-Dec-2017    0           1"+System.getProperty("line.separator")
					+"004  dd        1-Dec-2017    0           0"+System.getProperty("line.separator")
					+"\n> ID   Name                Arrival     Status"+System.getProperty("line.separator")
					+"B1   Core_Java           1-Dec-2017  On holdshelf for 002 cc until 4-Dec-2017"+System.getProperty("line.separator")
					+"B2   C                   1-Dec-2017  On holdshelf for 002 cc until 4-Dec-2017 + 1 request(s): 003 "+System.getProperty("line.separator")
					+"B3   C++                 1-Dec-2017  Borrowed by 001 helena on 1-Dec-2017 until 8-Dec-2017"+System.getProperty("line.separator")
					+"B4   C#                  1-Dec-2017  Available"+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
	}
	@Test
	public void testIntegration02() throws Exception, ExBookNotFound {
		String str = "startNewDay 1-Dec-2017"+System.getProperty("line.separator")
						+"register 001 helena"+System.getProperty("line.separator")
						+"register 002 cc"+System.getProperty("line.separator")
						+"arrive B1 Core_Java"+System.getProperty("line.separator")
						+"checkout 001 B1"+System.getProperty("line.separator")
						+"request 002 B1"+System.getProperty("line.separator")
						+"listMembers"+System.getProperty("line.separator")
						+"listBooks"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done. Borrow due on 8-Dec-2017."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> ID   Name      Join Date   #Borrowed   #Requested"+System.getProperty("line.separator")
					+"001  helena    1-Dec-2017    1           0"+System.getProperty("line.separator")
					+"002  cc        1-Dec-2017    0           1"+System.getProperty("line.separator")
					+"\n> ID   Name                Arrival     Status"+System.getProperty("line.separator")
					+"B1   Core_Java           1-Dec-2017  Borrowed by 001 helena on 1-Dec-2017 until 8-Dec-2017 + 1 request(s): 002 "+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
	}
	@Test
	public void testIntegration03() throws Exception, ExBookNotFound {
		String str = "undo"+System.getProperty("line.separator")
						+"redo"+System.getProperty("line.separator")
						+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Nothing to undo."+System.getProperty("line.separator")
					+System.getProperty("line.separator")
					+"\n> Nothing to redo."+System.getProperty("line.separator")
					+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
	}
	@Test
	public void testIntegration04() throws Exception, ExBookNotFound {
		PrintWriter memberDataWriter = new PrintWriter("src//testcase//memberDataFIFO.txt", "UTF-8");
        memberDataWriter.close();
        PrintWriter bookDataWriter = new PrintWriter("src//testcase//bookDataFIFO.txt", "UTF-8");
        bookDataWriter.close();
		String str = "startNewDay 2-Jan-2017"+System.getProperty("line.separator")
			+"arrive 01 cc"+System.getProperty("line.separator")
			+"arrive 02 dd"+System.getProperty("line.separator")
			+"arrive 03 ff"+System.getProperty("line.separator")
			+"arrive 04 ee"+System.getProperty("line.separator")
			+"arrive 05 gg"+System.getProperty("line.separator")
			+"arrive 06 java"+System.getProperty("line.separator")
			+"arrive 07 c"+System.getProperty("line.separator")
			+"register 001 a"+System.getProperty("line.separator")
			+"register 002 b"+System.getProperty("line.separator")
			+"register 003 c"+System.getProperty("line.separator")
			+"register 004 d"+System.getProperty("line.separator")
			+"register 005 e"+System.getProperty("line.separator")
			+"register 006 f"+System.getProperty("line.separator")
			+"checkout 001 01"+System.getProperty("line.separator")
			+"checkout 001 02"+System.getProperty("line.separator")
			+"checkout 001 03"+System.getProperty("line.separator")
			+"checkout 001 04"+System.getProperty("line.separator")
			+"checkout 001 05"+System.getProperty("line.separator")
			+"checkout 001 06"+System.getProperty("line.separator")
			+"checkout 001 07"+System.getProperty("line.separator")
			+"request 002 01"+System.getProperty("line.separator")
			+"request 002 02"+System.getProperty("line.separator")
			+"request 002 03"+System.getProperty("line.separator")
			+"request 003 01"+System.getProperty("line.separator")
			+"request 003 02"+System.getProperty("line.separator")
			+"checkin 001 01"+System.getProperty("line.separator")
			+"request 004 01"+System.getProperty("line.separator")
			+"request 005 01"+System.getProperty("line.separator")
			+"startNewDay 1-Apr-2017"+System.getProperty("line.separator")
			+"checkin 001 05"+System.getProperty("line.separator")
			+"checkout 004 05"+System.getProperty("line.separator")
			+"quit";
		in = new ByteArrayInputStream(str.getBytes());
		String[] args = {"main","src//testcase//memberDataFIFO.txt","src//testcase//bookDataFIFO.txt"};
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done. Borrow due on 9-Jan-2017."+System.getProperty("line.separator")
					+"\n> Done. Borrow due on 9-Jan-2017."+System.getProperty("line.separator")
					+"\n> Done. Borrow due on 9-Jan-2017."+System.getProperty("line.separator")
					+"\n> Done. Borrow due on 9-Jan-2017."+System.getProperty("line.separator")
					+"\n> Done. Borrow due on 9-Jan-2017."+System.getProperty("line.separator")
					+"\n> Done. Borrow due on 9-Jan-2017."+System.getProperty("line.separator")
					+"\n> Loan quota exceeded!"+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 1 in the queue."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 2 in the queue."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 2 in the queue."+System.getProperty("line.separator")
					+"\n> Book [01 cc] is ready for pick up by [002 b].  On hold due on 5-Jan-2017."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 2 in the queue."+System.getProperty("line.separator")
					+"\n> Done. This request is no. 3 in the queue."+System.getProperty("line.separator")
					+"\n> On hold period is over for 01 cc."+System.getProperty("line.separator")

					+"Book [01 cc] is ready for pick up by [003 c].  On hold due on 4-Apr-2017."+System.getProperty("line.separator")
					+"Borrow period is over for 02 dd."+System.getProperty("line.separator")
					
					+"a's membership is now suspended until all overdue books have been returned."+System.getProperty("line.separator")
					+"Borrow period is over for 03 ff."+System.getProperty("line.separator")
					
					+"a's membership is now suspended until all overdue books have been returned."+System.getProperty("line.separator")
					+"Borrow period is over for 04 ee."+System.getProperty("line.separator")
					+"a's membership is now suspended until all overdue books have been returned."+System.getProperty("line.separator")
					+"Borrow period is over for 05 gg."+System.getProperty("line.separator")
					+"a's membership is now suspended until all overdue books have been returned."+System.getProperty("line.separator")
					+"Borrow period is over for 06 java."+System.getProperty("line.separator")
					+"a's membership is now suspended until all overdue books have been returned."+System.getProperty("line.separator")
					+"Done."+System.getProperty("line.separator")
					+"\n> Done."+System.getProperty("line.separator")
					+"\n> Done. Borrow due on 8-Apr-2017."+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
	}
	@Test
	public void testIntegration05() throws Exception, ExBookNotFound {
		String str = "listMembers"+System.getProperty("line.separator")
						+"listBooks"+System.getProperty("line.separator")
						+"quit";
		String[] args = {"main","src//testcase//memberDataFIFO.txt","src//testcase//bookDataFIFO.txt"};
		in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(args);
		System.setIn(System.in);
		assertEquals("Book Data Imported."+System.getProperty("line.separator")
					+"Book Data Imported."+System.getProperty("line.separator")
					+"Book Data Imported."+System.getProperty("line.separator")
					+"Book Data Imported."+System.getProperty("line.separator")
					+"Book Data Imported."+System.getProperty("line.separator")
					+"Book Data Imported."+System.getProperty("line.separator")
					+"Book Data Imported."+System.getProperty("line.separator")
					+"Member Data Imported."+System.getProperty("line.separator")
					+"Member Data Imported."+System.getProperty("line.separator")
					+"Member Data Imported."+System.getProperty("line.separator")
					+"Member Data Imported."+System.getProperty("line.separator")
					+"Member Data Imported."+System.getProperty("line.separator")
					+"Member Data Imported."+System.getProperty("line.separator")
					+"\n> ID   Name      Join Date   #Borrowed   #Requested"+System.getProperty("line.separator")
					+"001  a         2-Jan-2017    4           0"+System.getProperty("line.separator")
					+"002  b         2-Jan-2017    0           2"+System.getProperty("line.separator")
					+"003  c         2-Jan-2017    0           1"+System.getProperty("line.separator")
					+"004  d         2-Jan-2017    1           1"+System.getProperty("line.separator")
					+"005  e         2-Jan-2017    0           1"+System.getProperty("line.separator")
					+"006  f         2-Jan-2017    0           0"+System.getProperty("line.separator")
					+"\n> ID   Name                Arrival     Status"+System.getProperty("line.separator")
					+"01   cc                  2-Jan-2017  On holdshelf for 003 c until 4-Apr-2017 + 2 request(s): 004 005 "+System.getProperty("line.separator")
					+"02   dd                  2-Jan-2017  Borrowed by 001 a on 2-Jan-2017 until 9-Jan-2017 + 2 request(s): 002 003 "+System.getProperty("line.separator")
					+"03   ff                  2-Jan-2017  Borrowed by 001 a on 2-Jan-2017 until 9-Jan-2017 + 1 request(s): 002 "+System.getProperty("line.separator")
					+"04   ee                  2-Jan-2017  Borrowed by 001 a on 2-Jan-2017 until 9-Jan-2017"+System.getProperty("line.separator")
					+"05   gg                  2-Jan-2017  Borrowed by 004 d on 1-Apr-2017 until 8-Apr-2017"+System.getProperty("line.separator")
					+"06   java                2-Jan-2017  Borrowed by 001 a on 2-Jan-2017 until 9-Jan-2017"+System.getProperty("line.separator")
					+"07   c                   2-Jan-2017  Available"+System.getProperty("line.separator")
					+"\n> END",outContent.toString());
	}
}

