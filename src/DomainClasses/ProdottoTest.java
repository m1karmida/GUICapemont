package DomainClasses;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProdottoTest {

	Prodotto p;
	Date data;
	Azienda a;
	Fornitore fornitore;
	@Before
	public void setUp() throws Exception {
		p= new Prodotto("nome","categoria",5,5,data,a,fornitore);
		assertNotNull(p);
	}

	@After
	public void tearDown() throws Exception {
		p=null;
		assertNull(p);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void test_prodotto() {
		
		assertEquals(p.getNome(),"nome");
		assertTrue(p.getPrezzo()==5);

		p.setPrezzo(10);
		p.setNome("nome2");
		
		assertEquals(p.getNome(),"nome2");
		assertTrue(p.getPrezzo()==10);
		
		
	}

}
