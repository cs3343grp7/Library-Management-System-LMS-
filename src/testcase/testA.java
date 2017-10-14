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
		String[] input = {"register 001 helena","quite"};
		
		System.setIn(new ByteArrayInputStream(
		           input[0].getBytes()));
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
