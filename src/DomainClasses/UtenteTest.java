package DomainClasses;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UtenteTest {

	Utente u;
	
	@Before
	public void setUp() throws Exception {
		u= new Utente("indirizzo","password","email","nome");
		assertNotNull(u);
	}

	@After
	public void tearDown() throws Exception {
		u=null;
		assertNull(u);
	}

	@Test
	public void test_utente() {
		assertEquals(u.getEmail(),"email");
		assertEquals(u.getNome(),"nome");
		
		u.setEmail("email2");
		u.setNome("nome2");
		
		assertEquals(u.getEmail(),"email2");
		assertEquals(u.getNome(),"nome2");
	}

}
