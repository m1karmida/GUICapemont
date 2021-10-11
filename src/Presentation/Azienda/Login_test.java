package Presentation.Azienda;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Robot;
import java.lang.reflect.Field;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import javax.swing.JPasswordField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class Login_test {

	LoginAzienda la;
	
	
	@Before
	public void setUp() throws Exception {

		la=new LoginAzienda(null);
		assertNotNull(la);
	}

	@After
	public void tearDown() throws Exception {
		la=null;
		assertNull(la);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void test_click_login() throws InterruptedException {
		la.setVisible(true);
		Thread.sleep(1000);
		Robot robot;
		try
        {
			robot = new Robot();
            robot.mouseMove(900,380);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.delay(1000);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);    
            
            robot.keyPress(KeyEvent.VK_A);
		    robot.delay(200);
		    robot.keyPress(KeyEvent.VK_P);
		    robot.delay(200);
		    robot.keyPress(KeyEvent.VK_P);
		    robot.delay(200);
		    robot.keyPress(KeyEvent.VK_L);
		    robot.delay(200);
		    robot.keyPress(KeyEvent.VK_E);
		    robot.delay(1000);
		    robot.keyPress(KeyEvent.VK_I);
		    robot.delay(200);
		    robot.keyPress(KeyEvent.VK_C);
		    robot.delay(200);
		    robot.keyPress(KeyEvent.VK_L);
		    robot.delay(200);
		    robot.keyPress(KeyEvent.VK_O);
		    robot.delay(200);
		    robot.keyPress(KeyEvent.VK_U);
		    robot.delay(200);
		    robot.keyPress(KeyEvent.VK_D);
		    robot.delay(200);
		    robot.keyPress(KeyEvent.VK_PERIOD);
		    robot.delay(200);
		    robot.keyPress(KeyEvent.VK_C);
		    robot.delay(200);
		    robot.keyPress(KeyEvent.VK_O);
		    robot.delay(200);
		    robot.keyPress(KeyEvent.VK_M);
		    robot.delay(200);
			
		    robot.mouseMove(900,450);
		    robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.delay(1000);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);    
            
            robot.keyPress(KeyEvent.VK_A);
		    robot.delay(200);
		    robot.keyPress(KeyEvent.VK_P);
		    robot.delay(200);
		    robot.keyPress(KeyEvent.VK_P);
		    robot.delay(200);
		    robot.keyPress(KeyEvent.VK_L);
		    robot.delay(200);
		    robot.keyPress(KeyEvent.VK_E);
		    robot.delay(200);
		    
		    robot.mouseMove(750,500);
		    robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.delay(1000);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
		    
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
		

	
		Thread.sleep(1000);

	}

}
