package DomainClasses;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AgenteTest {

	Agente a;
	ArrayList<Ordine> ordini_gestiti;
	@Before
	public void setUp() throws Exception {
		
		a= new Agente("piva_prova","agente_prova", "cognome", "via prova 1", "3333333333", "agente",
				  "codice", ordini_gestiti);
		assertNotNull(a);
	}

	@After
	public void tearDown() throws Exception {
		a=null;
		assertNull(a);
	}

	@Test
	public void test_agente() {
		
		assertEquals(a.getNome(),"agente_prova");
		assertEquals(a.getCognome(),"cognome");
		assertEquals(a.getIndirizzo(),"via prova 1");
		
		a.setCognome("cognome2");
		
		assertEquals(a.getCognome(),"cognome2");
	}

}
