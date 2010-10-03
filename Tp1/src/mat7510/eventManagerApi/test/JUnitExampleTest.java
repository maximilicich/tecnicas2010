package mat7510.eventManagerApi.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class JUnitExampleTest {

	@BeforeClass
	public static void init() {
		System.out.println("init...");
	}
	
	@AfterClass
	public static void ending() {
		System.out.println("ending...");
	}
	
	@Before
	public void setUp() {
		System.out.println("setUp()...");
	}
	
	@After
	public void tearDown() {
		System.out.println("tearDown()...");
	}
	
	@Test 
	public void simpleAddTrue() {
		System.out.println("simpleAddTrue()");
	    Integer m12 = new Integer(12); 
	    Integer m14 = new Integer(14); 
	    Integer expected= new Integer(26); 
	    
	    assertTrue( m12 + m14 == expected);
	}

	@Test 
	public void simpleAddFalse() {
		System.out.println("simpleAddFalse()");
	    Integer m12 = new Integer(12); 
	    Integer m14 = new Integer(14); 
	    Integer expected= new Integer(26); 
	    
	    assertFalse( m12 + m14 != expected);
	}

	
}
