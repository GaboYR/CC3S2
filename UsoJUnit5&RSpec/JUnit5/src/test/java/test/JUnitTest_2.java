package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class JUnitTest_2 {

    @Test
    void testAssertEqual() {
        assertEquals("ABC", "ABC");
        assertEquals(20, 20, "optional assertion message");
        assertEquals(2 + 2, 4);
    }

    @Test
    void testAssertFalse() {
        assertFalse("FirstName".length() == 10);
        assertFalse(10 > 20, "assertion message");
    }

    @Test
    void testAssertNull() {
        String str1 = null;
        String str2 = "abc";
        assertNull(str1);
        assertNotNull(str2);
    }

    @Test
    void testAssertAll() {
        String str1 = "abc";
        String str2 = "pqr";
        String str3 = "xyz";
        assertAll("numbers",
                () -> assertEquals(str1, "abc"),
                () -> assertEquals(str2, "pqr"),
                () -> assertEquals(str3, "xyz")
        );
        //uncomment below code and understand each assert execution
        /*assertAll("numbers",
		  () -> assertEquals(str1,"abc"),
		  () -> assertEquals(str2,"pqr1"),
		  () -> assertEquals(str3,"xyz1")
	 );*/
    }

    @Test
    void testAssertTrue() {
        assertTrue("FirstName".startsWith("F"));
//	 assertTrue(10  {
//	      throw new IllegalArgumentException("Illegal Argument Exception occured");
//	 });
//	 assertEquals("Illegal Argument Exception occured", exception.getMessage());
    }
}
