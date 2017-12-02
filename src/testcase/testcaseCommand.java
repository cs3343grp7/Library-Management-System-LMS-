package testcase;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import library.*;

public class testcaseCommand {
	ByteArrayOutputStream outContent;
    @Before
    public void resetSingleton() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        Field instance = Library.class.getDeclaredField("instance");
        instance.setAccessible(true);
 
        Constructor constructor = Library.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        instance.set(null, constructor.newInstance());
		try {
			SystemDate.createTheInstance("10-Feb-2017");
		} catch (ExDayNotValid e) {
		}
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
		String input = "startNewDay 10-fff-2013";
		String[] cmdParts = input.split(" ");
		Command command = new CmdStartNewDay();
		try {
			command.execute(cmdParts);
		} catch (Exception e) {
			assertEquals(e instanceof ExDayNotValid,true);
		}
	}
	@Test
	public void testStartNewDay11() {
		String input = "startNewDay 29-Feb-2000";
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
	public void testStartNewDay12() {
		String input = "startNewDay 29-Feb-2100";
		String[] cmdParts = input.split(" ");
		Command command = new CmdStartNewDay();
		try {
			command.execute(cmdParts);
		} catch (Exception e) {
			assertEquals(e instanceof ExDayNotValid,true);
		}
	}
	@Test
	public void testStartNewDay13() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRequest();
		command.execute("request 002 B1".split(" "));
		Command command1 = new CmdStartNewDay();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command1.execute("startNewDay 28-Feb-2017".split(" "));
		} catch(Exception e){
		}
		assertEquals("Borrow period is over for B1 Core_Java."+System.getProperty("line.separator")+
				"helena's membership is now suspended until all overdue books have been returned."+System.getProperty("line.separator")+
				"Done."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testStartNewDay14() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command.execute("register 003 Daniel".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRequest();
		command.execute("request 002 B1".split(" "));
		command.execute("request 003 B1".split(" "));
		command = new CmdCheckin();
		command.execute("checkin 001 B1".split(" "));
		Command command1 = new CmdStartNewDay();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command1.execute("startNewDay 28-Feb-2017".split(" "));
		} catch(Exception e){
		}
		assertEquals("On hold period is over for B1 Core_Java."+System.getProperty("line.separator")+"Book [B1 Core_Java] is ready for pick up by [003 Daniel].  On hold due on 3-Mar-2017."+System.getProperty("line.separator")+"Done."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testStartNewDay15() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRequest();
		command.execute("request 002 B1".split(" "));
		command = new CmdCheckin();
		command.execute("checkin 001 B1".split(" "));
		Command command1 = new CmdStartNewDay();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command1.execute("startNewDay 28-Feb-2017".split(" "));
		} catch(Exception e){
		}
		assertEquals("On hold period is over for B1 Core_Java."+System.getProperty("line.separator")+"Done."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testStartNewDay16() throws Exception {
		String input = "register 001 helena";
		String[] cmdParts = input.split(" ");
		Command command = new CmdRegister();
		command.execute(cmdParts);
		input = "arrive B1 Core_Java";
		cmdParts = input.split(" ");
		command = new CmdArrive();
		command.execute(cmdParts);
		input = "checkin 001 B1";
		cmdParts = input.split(" ");
		command = new CmdCheckout();
		command.execute(cmdParts);
		command = new CmdStartNewDay();
		command.execute("startNewDate 25-Feb-2017".split(" "));
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		command.execute("startNewDate 28-Feb-2017".split(" "));
		assertEquals("Borrow period is over for B1 Core_Java."+System.getProperty("line.separator")+"helena's membership is now suspended until all overdue books have been returned."+System.getProperty("line.separator")+"Done."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testStartNewDay17() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		Command command1 = new CmdRegister();
		command1 = new CmdStartNewDay();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		command1.execute("startNewDate 28-Feb-2016".split(" "));
		assertEquals("Done."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testStartNewDay18() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		Command command1 = new CmdRegister();
		command1 = new CmdStartNewDay();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		command1.execute("startNewDate 28-Feb-2018".split(" "));
		assertEquals("Borrow period is over for B1 Core_Java."+System.getProperty("line.separator")+"helena's membership is now suspended until all overdue books have been returned."+System.getProperty("line.separator")+"Done."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testStartNewDay19() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		Command command1 = new CmdRegister();
		command1 = new CmdStartNewDay();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		command1.execute("startNewDate 10-Feb-2017".split(" "));
		assertEquals("Done."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testStartNewDay20() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		Command command1 = new CmdRegister();
		command1 = new CmdStartNewDay();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		command1.execute("startNewDate 28-Apr-2017".split(" "));
		assertEquals("Borrow period is over for B1 Core_Java."+System.getProperty("line.separator")+"helena's membership is now suspended until all overdue books have been returned."+System.getProperty("line.separator")+"Done."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testStartNewDay21() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		Command command1 = new CmdRegister();
		command1 = new CmdStartNewDay();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		command1.execute("startNewDate 10-Jan-2017".split(" "));
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
	public void testRegister01() throws ExMemberNotFound {
		String input = "register 001 helena";
		String[] cmdParts = input.split(" ");
		Command command = new CmdRegister();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try {
			command.execute(cmdParts);
		} catch (Exception e) {
		} 
		assertEquals(Library.getInstance().findMember("001").getMemberStatus().getStatus(),"Normal");
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
		}
		assertEquals("ID   Name                Arrival     Status"+System.getProperty("line.separator")+"B2   Core_Java           10-Feb-2017 Available"+System.getProperty("line.separator"),outContent.toString());
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
	public void testArrive01Redo() throws ExInsufficientCommand, ExBookIDAlreadyInUse, ExMemberNotFound, ExBookNotFound, ExBookNotAvailable, ExLoanQuotaExceeded, ExBookIsAvailable, ExBookIsBorrowedByThisMember, ExRequestQuotaExceeded, ExAlreadyRequested, ExRequestRecordNotFound, ExNotBorrowedByThisMember, ExMemberIDAlreadyInUse, ExDayNotValid, ExMemberStatusSuspended, ExBookNotBorrowed {
		String input = "listBooks";
		String[] cmdParts = input.split(" ");
		Command command = new CmdListBooks();
		String input2 = "arrive B2 Core_Java";
		String[] cmdParts2 = input2.split(" ");
		RecordedCommand command2 = new CmdArrive();
		command2.execute(cmdParts2);
		command2.undoMe();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		command2.redoMe();
		command.execute(cmdParts);
		assertEquals("ID   Name                Arrival     Status"+System.getProperty("line.separator")+"B2   Core_Java           10-Feb-2017 Available"+System.getProperty("line.separator"),outContent.toString());
	}	
	@Test
	public void testCmdRegisterundoMe() throws Exception {
		String input = "listMembers";
		String[] cmdParts = input.split(" ");
		Command command = new CmdListMembers();
		String input2 = "register 099 helena";
		String[] cmdParts2 = input2.split(" ");
		CmdRegister command2 = new CmdRegister();
		command2.execute(cmdParts2);
		command2.undoMe();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		command.execute(cmdParts);
		assertEquals("ID   Name      Join Date   #Borrowed   #Requested"+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testCmdRegisterredoMe() throws Exception {
		String input = "listMembers";
		String[] cmdParts = input.split(" ");
		Command command = new CmdListMembers();
		String input2 = "register 001 helena";
		String[] cmdParts2 = input2.split(" ");
		CmdRegister command2 = new CmdRegister();
		command2.execute(cmdParts2);
		command2.undoMe();
		command2.redoMe();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		command.execute(cmdParts);
		assertEquals("ID   Name      Join Date   #Borrowed   #Requested"+System.getProperty("line.separator")+"001  helena    10-Feb-2017   0           0"+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testCmdCheckout01() throws Exception {
		String input = "register 001 helena";
		String[] cmdParts = input.split(" ");
		Command command = new CmdRegister();
		command.execute(cmdParts);
		input = "arrive B1 Core_Java";
		cmdParts = input.split(" ");
		command = new CmdArrive();
		command.execute(cmdParts);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		input = "checkout 001 B1";
		cmdParts = input.split(" ");
		command = new CmdCheckout();
		command.execute(cmdParts);
		assertEquals(Library.getInstance().findBook("B1").getBookStatus().getStatus(),"Borrowed by 001 helena on 10-Feb-2017until 17-Feb-2017");
		assertEquals("Done. Borrow due on 17-Feb-2017."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testCmdCheckout02() throws Exception {
		String input = "register 001 helena";
		String[] cmdParts = input.split(" ");
		Command command = new CmdRegister();
		command.execute(cmdParts);
		input = "arrive B1 Core_Java";
		cmdParts = input.split(" ");
		command = new CmdArrive();
		command.execute(cmdParts);
		input = "checkout 001 B2";
		cmdParts = input.split(" ");
		command = new CmdCheckout();
		try{
			command.execute(cmdParts);
		} catch(Exception e){
			assertEquals(e instanceof ExBookNotFound,true);
		}
	}
	@Test
	public void testCmdCheckout03() throws Exception {
		String input = "register 001 helena";
		String[] cmdParts = input.split(" ");
		Command command = new CmdRegister();
		command.execute(cmdParts);
		input = "arrive B1 Core_Java";
		cmdParts = input.split(" ");
		command = new CmdArrive();
		command.execute(cmdParts);
		input = "checkout 001";
		cmdParts = input.split(" ");
		command = new CmdCheckout();
		try{
			command.execute(cmdParts);
		} catch(Exception e){
			assertEquals(e instanceof ExInsufficientCommand,true);
		}
	}
	@Test
	public void testCmdCheckout04() throws Exception {
		String input = "register 001 helena";
		String[] cmdParts = input.split(" ");
		Command command = new CmdRegister();
		command.execute(cmdParts);
		input = "arrive B1 Core_Java";
		cmdParts = input.split(" ");
		command = new CmdArrive();
		command.execute(cmdParts);
		input = "checkin 002 B1";
		cmdParts = input.split(" ");
		command = new CmdCheckout();
		try{
			command.execute(cmdParts);
		} catch(Exception e){
			assertEquals(e instanceof ExMemberNotFound,true);
		}
	}
	@Test
	public void testCmdCheckout05() throws Exception {
		String input = "register 001 helena";
		String[] cmdParts = input.split(" ");
		Command command = new CmdRegister();
		command.execute(cmdParts);
		input = "register 002 cc";
		cmdParts = input.split(" ");
		command.execute(cmdParts);
		command = new CmdRegister();
		input = "arrive B1 Core_Java";
		cmdParts = input.split(" ");
		command = new CmdArrive();
		command.execute(cmdParts);
		input = "checkout 001 B1";
		cmdParts = input.split(" ");
		command = new CmdCheckout();
		command.execute(cmdParts);
		input = "checkout 002 B1";
		cmdParts = input.split(" ");
		try{
			command.execute(cmdParts);
		} catch(Exception e){
			assertEquals(e instanceof ExBookNotAvailable,true);
		}
	}
	@Test
	public void testCmdCheckout06() throws Exception {
		String[] arr = {"1","2","3","4","5","6","7"};
		String input = "register 001 helena";
		String[] cmdParts = input.split(" ");
		Command command = new CmdRegister();
		command.execute(cmdParts);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		for(int i=0;i<7;i++){
			command = new CmdArrive();
			input = "arrive "+arr[i]+" "+arr[i];
			cmdParts = input.split(" ");
			command.execute(cmdParts);
			if(i<6){
				input = "checkin 001 "+arr[i];
				cmdParts = input.split(" ");
				command = new CmdCheckout();
				command.execute(cmdParts);
			}
				
		}
		input = "checkin 001 7";
		cmdParts = input.split(" ");
		command = new CmdCheckout();
		try{
			command.execute(cmdParts);
		} catch(Exception e){
			assertEquals(e instanceof ExLoanQuotaExceeded,true);
		}
	}
	@Test
	public void testCmdCheckout07() throws Exception {
		String input = "register 001 helena";
		String[] cmdParts = input.split(" ");
		Command command = new CmdRegister();
		command.execute(cmdParts);
		input = "arrive B1 Core_Java";
		cmdParts = input.split(" ");
		command = new CmdArrive();
		command.execute(cmdParts);
		input = "checkin 001 B1";
		cmdParts = input.split(" ");
		command = new CmdCheckout();
		command.execute(cmdParts);
		command = new CmdStartNewDay();
		command.execute("startNewDate 25-Feb-2017".split(" "));
		assertEquals(Library.getInstance().findMember("001").getMemberStatus().getStatus(),"Suspended");
		input = "arrive B2 Java";
		cmdParts = input.split(" ");
		command = new CmdArrive();
		command.execute(cmdParts);
		input = "checkin 001 B2";
		cmdParts = input.split(" ");
		command = new CmdCheckout();
		try{
			command.execute(cmdParts);
		} catch(ExMemberStatusSuspended e){
			assertEquals(e instanceof ExMemberStatusSuspended,true);
		}
	}
	@Test
	public void testCmdCheckout08() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command.execute("register 003 Daniel".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRequest();
		command.execute("request 002 B1".split(" "));
		command = new CmdCheckin();
		command.execute("checkin 001 B1".split(" "));
		command = new CmdCheckout();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command.execute("checkout 003 B1".split(" "));
		} catch(Exception e){
			assertEquals(e instanceof ExBookNotAvailable,true);
		}
	}
	@Test
	public void testCmdCheckout09() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRequest();
		command.execute("request 002 B1".split(" "));
		command = new CmdCheckin();
		command.execute("checkin 001 B1".split(" "));
		command = new CmdCheckout();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		command.execute("checkout 002 B1".split(" "));
		assertEquals("Done. Borrow due on 17-Feb-2017."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testCmdCheckout10() throws Exception {
		String input = "register 001 helena";
		String[] cmdParts = input.split(" ");
		Command command = new CmdRegister();
		command.execute(cmdParts);
		input = "arrive B1 Core_Java";
		cmdParts = input.split(" ");
		command = new CmdArrive();
		command.execute(cmdParts);
		Command command1 = new CmdStartNewDay();
		command1.execute("startNewDate 10-Jan-2017".split(" "));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		input = "checkin 001 B1";
		cmdParts = input.split(" ");
		command = new CmdCheckout();
		command.execute(cmdParts);
		assertEquals(Library.getInstance().findBook("B1").getBookStatus().getStatus(),"Borrowed by 001 helena on 10-Jan-2017until 17-Jan-2017");
		assertEquals("Done. Borrow due on 17-Jan-2017."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testCmdCheckout11() throws Exception {
		String input = "register 001 helena";
		String[] cmdParts = input.split(" ");
		Command command = new CmdRegister();
		command.execute(cmdParts);
		input = "arrive B1 Core_Java";
		cmdParts = input.split(" ");
		command = new CmdArrive();
		command.execute(cmdParts);
		Command command1 = new CmdStartNewDay();
		command1.execute("startNewDate 25-Jan-2017".split(" "));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		input = "checkin 001 B1";
		cmdParts = input.split(" ");
		command = new CmdCheckout();
		command.execute(cmdParts);
		assertEquals(Library.getInstance().findBook("B1").getBookStatus().getStatus(),"Borrowed by 001 helena on 25-Jan-2017until 1-Feb-2017");
		assertEquals("Done. Borrow due on 1-Feb-2017."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testCmdCheckout12() throws Exception {
		String input = "register 001 helena";
		String[] cmdParts = input.split(" ");
		Command command = new CmdRegister();
		command.execute(cmdParts);
		input = "arrive B1 Core_Java";
		cmdParts = input.split(" ");
		command = new CmdArrive();
		command.execute(cmdParts);
		Command command1 = new CmdStartNewDay();
		command1.execute("startNewDate 24-Jun-2017".split(" "));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		input = "checkin 001 B1";
		cmdParts = input.split(" ");
		command = new CmdCheckout();
		command.execute(cmdParts);
		assertEquals(Library.getInstance().findBook("B1").getBookStatus().getStatus(),"Borrowed by 001 helena on 24-Jun-2017until 1-Jul-2017");
		assertEquals("Done. Borrow due on 1-Jul-2017."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testCmdCheckout13() throws Exception {
		String input = "register 001 helena";
		String[] cmdParts = input.split(" ");
		Command command = new CmdRegister();
		command.execute(cmdParts);
		input = "arrive B1 Core_Java";
		cmdParts = input.split(" ");
		command = new CmdArrive();
		command.execute(cmdParts);
		Command command1 = new CmdStartNewDay();
		command1.execute("startNewDate 2-Jun-2017".split(" "));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		input = "checkin 001 B1";
		cmdParts = input.split(" ");
		command = new CmdCheckout();
		command.execute(cmdParts);
		assertEquals(Library.getInstance().findBook("B1").getBookStatus().getStatus(),"Borrowed by 001 helena on 2-Jun-2017 until 9-Jun-2017");
		assertEquals("Done. Borrow due on 9-Jun-2017."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testCmdCheckout14() throws Exception {
		String input = "register 001 helena";
		String[] cmdParts = input.split(" ");
		Command command = new CmdRegister();
		command.execute(cmdParts);
		input = "arrive B1 Core_Java";
		cmdParts = input.split(" ");
		command = new CmdArrive();
		command.execute(cmdParts);
		Command command1 = new CmdStartNewDay();
		command1.execute("startNewDate 2-Feb-2016".split(" "));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		input = "checkin 001 B1";
		cmdParts = input.split(" ");
		command = new CmdCheckout();
		command.execute(cmdParts);
		assertEquals(Library.getInstance().findBook("B1").getBookStatus().getStatus(),"Borrowed by 001 helena on 2-Feb-2016 until 9-Feb-2016");
		assertEquals("Done. Borrow due on 9-Feb-2016."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testCmdCheckout15() throws Exception {
		String input = "register 001 helena";
		String[] cmdParts = input.split(" ");
		Command command = new CmdRegister();
		command.execute(cmdParts);
		input = "arrive B1 Core_Java";
		cmdParts = input.split(" ");
		command = new CmdArrive();
		command.execute(cmdParts);
		Command command1 = new CmdStartNewDay();
		command1.execute("startNewDate 23-Feb-2016".split(" "));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		input = "checkin 001 B1";
		cmdParts = input.split(" ");
		command = new CmdCheckout();
		command.execute(cmdParts);
		assertEquals(Library.getInstance().findBook("B1").getBookStatus().getStatus(),"Borrowed by 001 helena on 23-Feb-2016until 1-Mar-2016");
		assertEquals("Done. Borrow due on 1-Mar-2016."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testCmdCheckout16() throws Exception {
		String input = "register 001 helena";
		String[] cmdParts = input.split(" ");
		Command command = new CmdRegister();
		command.execute(cmdParts);
		input = "arrive B1 Core_Java";
		cmdParts = input.split(" ");
		command = new CmdArrive();
		command.execute(cmdParts);
		Command command1 = new CmdStartNewDay();
		command1.execute("startNewDate 25-Dec-2016".split(" "));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		input = "checkin 001 B1";
		cmdParts = input.split(" ");
		command = new CmdCheckout();
		command.execute(cmdParts);
		assertEquals(Library.getInstance().findBook("B1").getBookStatus().getStatus(),"Borrowed by 001 helena on 25-Dec-2016until 1-Jan-2017");
		assertEquals("Done. Borrow due on 1-Jan-2017."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testCmdCheckout17() throws Exception {
		String input = "register 001 helena";
		String[] cmdParts = input.split(" ");
		Command command = new CmdRegister();
		command.execute(cmdParts);
		input = "arrive B1 Core_Java";
		cmdParts = input.split(" ");
		command = new CmdArrive();
		command.execute(cmdParts);
		Command command1 = new CmdStartNewDay();
		command1.execute("startNewDate 2-Dec-2016".split(" "));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		input = "checkin 001 B1";
		cmdParts = input.split(" ");
		command = new CmdCheckout();
		command.execute(cmdParts);
		assertEquals(Library.getInstance().findBook("B1").getBookStatus().getStatus(),"Borrowed by 001 helena on 2-Dec-2016 until 9-Dec-2016");
		assertEquals("Done. Borrow due on 9-Dec-2016."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testCmdCheckoutundoMe01() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRequest();
		command.execute("request 002 B1".split(" "));
		command = new CmdCheckin();
		command.execute("checkin 001 B1".split(" "));
		RecordedCommand command1 = new CmdCheckout();
		command1.execute("checkout 002 B1".split(" "));
		command1.undoMe();
		assertEquals(Library.getInstance().findBook("B1").getBookStatus()instanceof BookStatusOnhold, true);
	}
	@Test
	public void testCmdCheckoutundoMe02() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		RecordedCommand command1 = new CmdCheckout();
		command1.execute("checkout 001 B1".split(" "));
		command1.undoMe();
		assertEquals(Library.getInstance().findBook("B1").getBookStatus()instanceof BookStatusAvailable, true);
	}
	@Test
	public void testCmdCheckoutredoMe() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		RecordedCommand command1 = new CmdCheckout();
		command1.execute("checkout 001 B1".split(" "));
		command1.undoMe();
		command1.redoMe();
		assertEquals(Library.getInstance().findBook("B1").getBookStatus()instanceof BookStatusBorrowed, true);
	}
	@Test
	public void testCmdCheckin01() throws Exception{
		Command command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdCheckin();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command.execute("checkin 001 B1".split(" "));
		} catch(Exception e){
		}
		assertEquals("Done."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testCmdCheckin02() throws Exception {
		Command command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdCheckin();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command.execute("checkin 001".split(" "));
		} catch(Exception e){
			assertEquals(e instanceof ExInsufficientCommand,true);
		}
	}
	@Test
	public void testCmdCheckin03() throws Exception {
		Command command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdCheckin();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command.execute("checkin 002 B1".split(" "));
		} catch(Exception e){
			assertEquals(e instanceof ExMemberNotFound,true);
		}
	}
	@Test
	public void testCmdCheckin04() throws Exception {
		Command command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdCheckin();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command.execute("checkin 001 B2".split(" "));
		} catch(Exception e){
			assertEquals(e instanceof ExBookNotFound,true);
		}
	}
	@Test
	public void testCmdCheckin05() throws Exception {
		Command command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRegister();
		command.execute("register 002 Sing".split(" "));
		command = new CmdCheckin();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command.execute("checkin 002 B1".split(" "));
		} catch(Exception e){
			assertEquals(e instanceof ExNotBorrowedByThisMember,true);
		}
	}
	@Test
	public void testCmdCheckin06() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRequest();
		command.execute("request 002 B1".split(" "));
		command = new CmdCheckin();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command.execute("checkin 001 B1".split(" "));
		} catch(Exception e){
		}
		assertEquals("Book [B1 Core_Java] is ready for pick up by [002 Sing].  On hold due on 13-Feb-2017."+System.getProperty("line.separator")+"Done."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testCmdCheckin07() throws Exception {
		String input = "register 001 helena";
		String[] cmdParts = input.split(" ");
		Command command = new CmdRegister();
		command.execute(cmdParts);
		input = "arrive B1 Core_Java";
		cmdParts = input.split(" ");
		command = new CmdArrive();
		command.execute(cmdParts);
		input = "checkout 001 B1";
		cmdParts = input.split(" ");
		command = new CmdCheckout();
		command.execute(cmdParts);
		command = new CmdStartNewDay();
		command.execute("startNewDate 25-Feb-2017".split(" "));
		command = new CmdCheckin();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command.execute("checkin 001 B1".split(" "));
		} catch(ExMemberStatusSuspended e){
		}
		assertEquals("helena has returned all overdue book(s) and suspension is stopped."+System.getProperty("line.separator")+"Done."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testCmdCheckin08() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckin();
		try{
			command.execute("checkin 001 B1".split(" "));
		} catch(Exception e){
			assertEquals(e instanceof ExBookNotBorrowed,true);
		}
	}
	@Test
	public void testCmdCheckin09() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command.execute("register 003 Daniel".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRequest();
		command.execute("request 002 B1".split(" "));
		command.execute("request 003 B1".split(" "));
		command = new CmdCheckin();
		Command command1 = new CmdStartNewDay();
		command1.execute("startNewDate 26-Feb-2017".split(" "));
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command.execute("checkin 001 B1".split(" "));
		} catch(Exception e){
		}
		assertEquals(Library.getInstance().findBook("B1").getBookStatus().getStatus(),"On holdshelf for 002 Sing until 1-Mar-2017 + 1 request(s): 003 ");
	}
	@Test
	public void testCmdCheckin10() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command.execute("register 003 Daniel".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRequest();
		command.execute("request 002 B1".split(" "));
		command.execute("request 003 B1".split(" "));
		command = new CmdCheckin();
		Command command1 = new CmdStartNewDay();
		command1.execute("startNewDate 27-Feb-2016".split(" "));
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command.execute("checkin 001 B1".split(" "));
		} catch(Exception e){
		}
		assertEquals(Library.getInstance().findBook("B1").getBookStatus().getStatus(),"On holdshelf for 002 Sing until 1-Mar-2016 + 1 request(s): 003 ");
	}
	@Test
	public void testCmdCheckin11() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command.execute("register 003 Daniel".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRequest();
		command.execute("request 002 B1".split(" "));
		command.execute("request 003 B1".split(" "));
		command = new CmdCheckin();
		Command command1 = new CmdStartNewDay();
		command1.execute("startNewDate 2-Feb-2016".split(" "));
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command.execute("checkin 001 B1".split(" "));
		} catch(Exception e){
		}
		assertEquals(Library.getInstance().findBook("B1").getBookStatus().getStatus(),"On holdshelf for 002 Sing until 5-Feb-2016 + 1 request(s): 003 ");
	}
	@Test
	public void testCmdCheckin12() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command.execute("register 003 Daniel".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRequest();
		command.execute("request 002 B1".split(" "));
		command.execute("request 003 B1".split(" "));
		command = new CmdCheckin();
		Command command1 = new CmdStartNewDay();
		command1.execute("startNewDate 1-Jan-2016".split(" "));
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command.execute("checkin 001 B1".split(" "));
		} catch(Exception e){
		}
		assertEquals(Library.getInstance().findBook("B1").getBookStatus().getStatus(),"On holdshelf for 002 Sing until 4-Jan-2016 + 1 request(s): 003 ");
	}
	@Test
	public void testCmdCheckin13() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command.execute("register 003 Daniel".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRequest();
		command.execute("request 002 B1".split(" "));
		command.execute("request 003 B1".split(" "));
		command = new CmdCheckin();
		Command command1 = new CmdStartNewDay();
		command1.execute("startNewDate 29-Jan-2016".split(" "));
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command.execute("checkin 001 B1".split(" "));
		} catch(Exception e){
		}
		assertEquals(Library.getInstance().findBook("B1").getBookStatus().getStatus(),"On holdshelf for 002 Sing until 1-Feb-2016 + 1 request(s): 003 ");
	}
	@Test
	public void testCmdCheckin14() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command.execute("register 003 Daniel".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRequest();
		command.execute("request 002 B1".split(" "));
		command.execute("request 003 B1".split(" "));
		command = new CmdCheckin();
		Command command1 = new CmdStartNewDay();
		command1.execute("startNewDate 2-Apr-2016".split(" "));
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command.execute("checkin 001 B1".split(" "));
		} catch(Exception e){
		}
		assertEquals(Library.getInstance().findBook("B1").getBookStatus().getStatus(),"On holdshelf for 002 Sing until 5-Apr-2016 + 1 request(s): 003 ");
	}
	@Test
	public void testCmdCheckin15() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command.execute("register 003 Daniel".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRequest();
		command.execute("request 002 B1".split(" "));
		command.execute("request 003 B1".split(" "));
		command = new CmdCheckin();
		Command command1 = new CmdStartNewDay();
		command1.execute("startNewDate 28-Apr-2016".split(" "));
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command.execute("checkin 001 B1".split(" "));
		} catch(Exception e){
		}
		assertEquals(Library.getInstance().findBook("B1").getBookStatus().getStatus(),"On holdshelf for 002 Sing until 1-May-2016 + 1 request(s): 003 ");
	}
	@Test
	public void testCmdCheckin16() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command.execute("register 003 Daniel".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRequest();
		command.execute("request 002 B1".split(" "));
		command.execute("request 003 B1".split(" "));
		command = new CmdCheckin();
		Command command1 = new CmdStartNewDay();
		command1.execute("startNewDate 2-Dec-2016".split(" "));
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command.execute("checkin 001 B1".split(" "));
		} catch(Exception e){
		}
		assertEquals(Library.getInstance().findBook("B1").getBookStatus().getStatus(),"On holdshelf for 002 Sing until 5-Dec-2016 + 1 request(s): 003 ");
	}
	@Test
	public void testCmdCheckin17() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command.execute("register 003 Daniel".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRequest();
		command.execute("request 002 B1".split(" "));
		command.execute("request 003 B1".split(" "));
		command = new CmdCheckin();
		Command command1 = new CmdStartNewDay();
		command1.execute("startNewDate 29-Dec-2016".split(" "));
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command.execute("checkin 001 B1".split(" "));
		} catch(Exception e){
		}
		assertEquals(Library.getInstance().findBook("B1").getBookStatus().getStatus(),"On holdshelf for 002 Sing until 1-Jan-2017 + 1 request(s): 003 ");
	}
	@Test
	public void testCmdCheckinundoMe01() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdCheckin();
		command.execute("checkin 001 B1".split(" "));
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command.undoMe();
		} catch(Exception e){
		}
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusBorrowed,true);
	}
	@Test
	public void testCmdCheckinundoMe02() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRequest();
		command.execute("request 002 B1".split(" "));
		command = new CmdCheckin();
		command.execute("checkin 001 B1".split(" "));
		assertEquals(Library.getInstance().findBook("B1").getBookStatus().getStatus(),"On holdshelf for 002 Sing until 13-Feb-2017");
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		command.undoMe();
		assertEquals("Sorry. 002 Sing please ignore the pick up notice for B1 Core_Java."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testCmdCheckinundoMe03() throws Exception {
		String input = "register 001 helena";
		String[] cmdParts = input.split(" ");
		Command command = new CmdRegister();
		command.execute(cmdParts);
		input = "arrive B1 Core_Java";
		cmdParts = input.split(" ");
		command = new CmdArrive();
		command.execute(cmdParts);
		input = "checkout 001 B1";
		cmdParts = input.split(" ");
		command = new CmdCheckout();
		command.execute(cmdParts);
		command = new CmdStartNewDay();
		command.execute("startNewDate 25-Feb-2017".split(" "));
		RecordedCommand command1 = new CmdCheckin();
		command1.execute("checkin 001 B1".split(" "));
		command1.undoMe();
		assertEquals(Library.getInstance().findMember("001").getMemberStatus() instanceof MemberStatusSuspend,true);
	}
	@Test
	public void testCmdCheckinredoMe01() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdCheckin();
		command.execute("checkin 001 B1".split(" "));
		command.undoMe();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command.redoMe();
		} catch(Exception e){
		}
		assertEquals(Library.getInstance().findBook("B1").getBookStatus() instanceof BookStatusAvailable,true);
	}
	@Test
	public void testCmdCheckinredoMe02() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRequest();
		command.execute("request 002 B1".split(" "));
		command = new CmdCheckin();
		command.execute("checkin 001 B1".split(" "));
		command.undoMe();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		command.redoMe();
		assertEquals("Book [B1 Core_Java] is ready for pick up by [002 Sing].  On hold due on 13-Feb-2017."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testCmdCheckinredoMe03() throws Exception {
		String input = "register 001 helena";
		String[] cmdParts = input.split(" ");
		Command command = new CmdRegister();
		command.execute(cmdParts);
		input = "arrive B1 Core_Java";
		cmdParts = input.split(" ");
		command = new CmdArrive();
		command.execute(cmdParts);
		input = "checkout 001 B1";
		cmdParts = input.split(" ");
		command = new CmdCheckout();
		command.execute(cmdParts);
		command = new CmdStartNewDay();
		command.execute("startNewDate 25-Feb-2017".split(" "));
		RecordedCommand command1 = new CmdCheckin();
		command1.execute("checkin 001 B1".split(" "));
		command1.undoMe();
		command1.redoMe();
		assertEquals(Library.getInstance().findMember("001").getMemberStatus() instanceof MemberStatusNormal,true);
	}
	@Test
	public void testCmdRequest01() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRequest();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command.execute("request 002 B1".split(" "));
		} catch(Exception e){
		}
		assertEquals(Library.getInstance().findBook("B1").getBookStatus().getStatus(),"Borrowed by 001 helena on 10-Feb-2017until 17-Feb-2017+ 1 request(s): 002 ");
		assertEquals("Done. This request is no. 1 in the queue."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testCmdRequest02() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRequest();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command.execute("request 002".split(" "));
		} catch(Exception e){
			assertEquals(e instanceof ExInsufficientCommand,true);
		}
	}
	@Test
	public void testCmdRequest03() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRequest();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command.execute("request 003 B1".split(" "));
		} catch(Exception e){
			assertEquals(e instanceof ExMemberNotFound,true);
		}
	}
	@Test
	public void testCmdRequest04() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdRequest();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command.execute("request 001 B1".split(" "));
		} catch(Exception e){
			assertEquals(e instanceof ExBookIsAvailable,true);
		}
	}
	@Test
	public void testCmdRequest05() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRequest();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command.execute("request 002 B5".split(" "));
		} catch(Exception e){
			assertEquals(e instanceof ExBookNotFound,true);
		}
	}
	@Test
	public void testCmdRequest06() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRequest();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command.execute("request 001 B1".split(" "));
		} catch(Exception e){
			assertEquals(e instanceof ExBookIsBorrowedByThisMember,true);
		}
	}
	@Test
	public void testCmdRequest07() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command.execute("arrive B2 C++".split(" "));
		command.execute("arrive B3 C#".split(" "));
		command.execute("arrive B4 C".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command.execute("checkout 001 B2".split(" "));
		command.execute("checkout 001 B3".split(" "));
		command.execute("checkout 001 B4".split(" "));
		command = new CmdRequest();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command.execute("request 002 B1".split(" "));
			command.execute("request 002 B2".split(" "));
			command.execute("request 002 B3".split(" "));
			command.execute("request 002 B4".split(" "));
		} catch(Exception e){
			assertEquals(e instanceof ExRequestQuotaExceeded,true);
		}
	}
	@Test
	public void testCmdRequest08() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRequest();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command.execute("request 002 B1".split(" "));
			command.execute("request 002 B1".split(" "));
		} catch(Exception e){
			assertEquals(e instanceof ExAlreadyRequested,true);
		}
	}
	@Test
	public void testCmdRequest09() throws Exception {
		String input = "register 001 helena";
		String[] cmdParts = input.split(" ");
		Command command = new CmdRegister();
		command.execute(cmdParts);
		command.execute("register 002 Sing".split(" "));
		input = "arrive B1 Core_Java";
		cmdParts = input.split(" ");
		command = new CmdArrive();
		command.execute(cmdParts);
		command.execute("arrive B2 C++".split(" "));
		input = "checkout 001 B1";
		cmdParts = input.split(" ");
		command = new CmdCheckout();
		command.execute(cmdParts);
		command.execute("checkout 002 B2".split(" "));
		command = new CmdStartNewDay();
		command.execute("startNewDate 25-Feb-2017".split(" "));
		command = new CmdRequest();
		try{
			command.execute("request 002 B1".split(" "));
		} catch(ExMemberStatusSuspended e){
			assertEquals(e instanceof ExMemberStatusSuspended,true);
		}
	}
	@Test
	public void testCmdRequest10() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRequest();
		command.execute("request 002 B1".split(" "));
		command = new CmdCheckin();
		command.execute("checkin 001 B1".split(" "));
		command = new CmdRequest();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command.execute("request 002 B1".split(" "));
		} catch(Exception e){
			assertEquals(e instanceof ExBookIsAvailable,true);
		}
	}
	@Test
	public void testCmdRequestundoMe() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRequest();
		command.execute("request 002 B1".split(" "));
		try{
			command.undoMe();
		} catch(Exception e){
		}
		assertEquals(Library.getInstance().findMember("002").getRequestCounts(),0);
	}
	@Test
	public void testCmdRequestredoMe() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRequest();
		command.execute("request 002 B1".split(" "));
		command.undoMe();
		try{
			command.redoMe();
		} catch(Exception e){
		}
		assertEquals(Library.getInstance().findMember("002").getRequestCounts(),1);
	}
	@Test
	public void testCmdCancelRequest01() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRequest();
		command.execute("request 002 B1".split(" "));
		command = new CmdCancelRequest();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command.execute("cancelRequest 002 B1".split(" "));
		} catch(Exception e){
		}
		assertEquals(Library.getInstance().findBook("B1").getQueueList(),"");
		assertEquals("Done."+System.getProperty("line.separator"),outContent.toString());
	}
	@Test
	public void testCmdCancelRequest02() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRequest();
		command.execute("request 002 B1".split(" "));
		command = new CmdCancelRequest();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command.execute("cancelRequest 002".split(" "));
		} catch(Exception e){
			assertEquals(e instanceof ExInsufficientCommand,true);
		}
	}
	@Test
	public void testCmdCancelRequest03() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdCancelRequest();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try{
			command.execute("cancelRequest 002 B1".split(" "));
		} catch(Exception e){
			assertEquals(e instanceof ExRequestRecordNotFound,true);
		}
	}
	@Test
	public void testCmdCancelRequestundoMe() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRequest();
		command.execute("request 002 B1".split(" "));
		command = new CmdCancelRequest();
		command.execute("cancelRequest 002 B1".split(" "));
		command.undoMe();
		assertEquals(Library.getInstance().findBook("B1").getQueueList(),"002 ");
	}
	@Test
	public void testCmdCancelRequestredoMe() throws Exception {
		RecordedCommand command;
		command = new CmdRegister();
		command.execute("register 001 helena".split(" "));
		command.execute("register 002 Sing".split(" "));
		command = new CmdArrive();
		command.execute("arrive B1 Core_Java".split(" "));
		command = new CmdCheckout();
		command.execute("checkout 001 B1".split(" "));
		command = new CmdRequest();
		command.execute("request 002 B1".split(" "));
		command = new CmdCancelRequest();
		command.execute("cancelRequest 002 B1".split(" "));
		command.undoMe();
		command.redoMe();
		assertEquals(Library.getInstance().findBook("B1").getQueueList(),"");
	}
}
