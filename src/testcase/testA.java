package testcase;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;
import library.*;

public class testA {

	@Test
	public void test01() throws FileNotFoundException { 
		String[] arg = {"123","DSFDF"};
		Main.main(arg);
	}

}
