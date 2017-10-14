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

	@Test
	public void test01() {
		String input = "quit";
//		String input = "register 001 helena" + System.getProperty("line.separator")+
//				"quit"+System.getProperty("line.seprator");
		
//		InputStream sInputStream = System.in;
//		System.setIn(new ByteArrayInputStream(input.getBytes()));
		System.setIn(new ByteArrayInputStream(
		           input.getBytes()));
//		System.setIn(sInputStream);
		    ByteArrayOutputStream outContent 
		           = new ByteArrayOutputStream();
		    System.setOut(new PrintStream(outContent));
		    try {
				String[] args = {"zxC","ZXC"};
				Main.main(args);
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			}
		    assertEquals("Done./n", outContent.toString());

	}

}
