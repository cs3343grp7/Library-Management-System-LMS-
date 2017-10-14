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
	public void test01() {
		String input = "arrive B1 Core_Java";
		String[] cmdParts = input.split(" ");
		SystemDate.createTheInstance("03-Jan-2014");
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
}
