package DomainClasses;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FornitoreTest {

	Fornitore f;
	 CategoriaProdotto tipologia;
	@Before
	public void setUp() throws Exception {
		f=new Fornitore("nome","indirizzo","recapito",tipologia,"codice");
		assertNotNull(f);
	}

	@After
	public void tearDown() throws Exception {
		f=null;
		assertNull(f);
	}

	@Test
	public void test() {
		
		assertEquals(f.getNome(),"nome");
		assertEquals(f.getIndirizzo(),"indirizzo");
		
		f.setNome("nome2");
		f.setIndirizzo("indirizzo2");
		
		assertEquals(f.getNome(),"nome2");
		assertEquals(f.getIndirizzo(),"indirizzo2");
		
		
	}

}
