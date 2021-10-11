package Presentation;
import static org.junit.Assert.*;


import org.eclipse.swt.events.KeyEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.awt.*;
import java.util.*;
import java.lang.reflect.Field;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Component;



public class InitTest {

	Init init;
	JButton btnRegistra;
	JRootPane rootpane;
	Container container;
	JButton prova;
	
	@Before
	public void setUp() throws Exception {
		
		init=new Init();
	}

	@After
	public void tearDown() throws Exception {
	}

	@SuppressWarnings("deprecation")
	@Test
	public void test() throws AWTException {
		
		init.setVisible(true);
		try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		Robot robot = new Robot();
		try
		{
		Component[] componenti ={};
		rootpane=(JRootPane) init.getComponent(0);
		container=rootpane.getContentPane();
		componenti=container.getComponents();
		prova=(JButton) componenti[2];
		prova.doClick();
		try{Thread.sleep(1000);}catch(InterruptedException e){}
		System.out.println("Bottone Cliente funzionante");
		}
		catch(IllegalArgumentException e)
		{
			fail("Exception");
		}
		
		try
		{
		Component[] componenti ={};
		rootpane=(JRootPane) init.getComponent(0);
		container=rootpane.getContentPane();
		componenti=container.getComponents();
		prova=(JButton) componenti[1];
		prova.doClick();
		try{Thread.sleep(1000);}catch(InterruptedException e){}
		System.out.println("Bottone Azienda funzionante");
		}
		catch(IllegalArgumentException e)
		{
			fail("Exception");
		}
		
		try
		{
		Component[] componenti ={};
		rootpane=(JRootPane) init.getComponent(0);
		container=rootpane.getContentPane();
		componenti=container.getComponents();
		prova=(JButton) componenti[4];
		prova.doClick();
		try{Thread.sleep(1000);}catch(InterruptedException e){}
		System.out.println("Bottone Registrati funzionante");
		}
		catch(IllegalArgumentException e)
		{
			fail("Exception");
		}
		/*robot.mouseMove(50,140);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		try{Thread.sleep(250);}catch(InterruptedException e){}
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		
		robot.delay(1000);
		System.out.println("Bottone Azienda funzionante");
		}
		catch(IllegalArgumentException e)
		{
			fail("Exception");
		}
		
		try
		{
			
		robot.mouseMove(300,140);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		try{Thread.sleep(250);}catch(InterruptedException e){}
		robot.mouseRelease(InputEvent.BUTTON1_MASK);

		robot.delay(1000);
		System.out.println("Bottone Cliente funzionante");
		}
		catch(IllegalArgumentException e)
		{
			fail("Exception");
		}
		
		try
		{
			
		robot.mouseMove(160,200);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		try{Thread.sleep(250);}catch(InterruptedException e){}
		robot.mouseRelease(InputEvent.BUTTON1_MASK);

		robot.delay(1000);
		System.out.println("Bottone Registrati funzionante");
		}
		catch(IllegalArgumentException e)
		{
			fail("Exception");
		}*/

	}

}
