package DomainClasses;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AziendaTest {

	Azienda a;
	@Before
	public void setUp() throws Exception {
		a=new Azienda("indirizzo","email","password","nome","P_IVA");
		assertNotNull(a);
	}

	@After
	public void tearDown() throws Exception {
		a=null;
		assertNull(a);
	}

	@Test
	public void test_azienda() {
		assertEquals(a.getP_IVA(),"P_IVA");
		
		a.setP_IVA("IVA");
		
		assertEquals(a.getP_IVA(),"IVA");
	}

}
