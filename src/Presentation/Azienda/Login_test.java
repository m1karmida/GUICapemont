package Presentation.Azienda;

import static org.junit.Assert.*;

import java.awt.Robot;
import java.lang.reflect.Field;

import org.eclipse.swt.events.KeyEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Login_test {

	LoginAzienda la;
	@Before
	public void setUp() throws Exception {
		la=new LoginAzienda();
		assertNotNull(la);
	}

	@After
	public void tearDown() throws Exception {
		la=null;
		assertNull(la);
	}

	@Test
	public void test_click_login() {
		String stringa ="vincenzo";
		Robot robot;
		try
        {
			robot = new Robot();
            boolean upperCase = Character.isUpperCase( stringa.charAt(0) );
            String variableName = "VK_" + stringa.toUpperCase();

            Class clazz = KeyEvent.class;
            Field field = clazz.getField( variableName );
            int keyCode = field.getInt(null);

            robot.delay(1000);


            robot.keyPress( keyCode );
            robot.keyRelease( keyCode );
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
		

	}

}
