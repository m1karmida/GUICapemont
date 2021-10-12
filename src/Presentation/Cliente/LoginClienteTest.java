package Presentation.Cliente;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Business.Client;
import DomainClasses.Persona;

public class LoginClienteTest {
	
	LoginCliente lo;
	Persona persona;
	
	@Before
	public void setUp() throws Exception {
		try
		{
			Client c = new Client("93.88.110.173", 5000);
			
			
			persona = new Persona("via prova 1",  "email_prova",  "password_prova",  "nome_prova",  "cognome_prova");
			c.makeRegisterUtente(persona);
		
		} 
		catch (IOException e) 
		{
			System.out.println("Errore di inserimento nel database");
		}
		
		assertNotNull(persona);
		}

	@After
	public void tearDown() throws Exception {
		persona=null;
		assertNull(persona);
	}

	@Test
	public void test() {
		try {
			Client c = new Client("93.88.110.173", 5000);
			persona = c.makeLoginUtente("email_prova",  "password_prova");
		} catch (IOException e) {
			System.out.println("Errore di login");
		}
		
	}

}
