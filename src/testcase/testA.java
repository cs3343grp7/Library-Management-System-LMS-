package testcase;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.Test;
import library.*;

public class testA {

	@Test
	public void test01() {
		String input = "register 001 helena";
		String[] args = input.split(" ");
		System.setIn(new ByteArrayInputStream(
		           input.getBytes()));
		    ByteArrayOutputStream outContent 
		           = new ByteArrayOutputStream();
		    System.setOut(new PrintStream(outContent));
		    try {
				Main.main(args);
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			}
		    assertEquals("Done.", outContent.toString());

	}

}
