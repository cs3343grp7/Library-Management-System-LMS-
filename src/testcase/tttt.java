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
	@Test
	public void testMain() throws FileNotFoundException {
		String str = "quit";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes());
		System.setIn(in);
		Main.main(null);
		System.setIn(System.in);
		assertEquals("Done."+System.getProperty("line.separator"),outContent.toString());
	}
}
