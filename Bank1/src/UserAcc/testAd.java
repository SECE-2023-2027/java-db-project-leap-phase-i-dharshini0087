package UserAcc;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import admin.admin;

public class testAd {
    admin Admin = new admin();

    @Test
    public void testLogin() {  
        int actualOutput = admin.login("anu", "1223");
        boolean expectedOutput = true; 
        assertEquals(expectedOutput, actualOutput, "Admin login test failed");
    }

	private void assertEquals(boolean expectedOutput, int actualOutput, String string) {
		// TODO Auto-generated method stub
		
	}
}