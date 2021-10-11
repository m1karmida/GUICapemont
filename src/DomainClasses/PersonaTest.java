package DomainClasses;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class PersonaTest {

    Persona p;
    ArrayList ordini_effettuati;
    @Before
    public void setUp() throws Exception {
        p=new Persona("indirizzo",  "email",  "password",  "nome",  "cognome",
                 ordini_effettuati);
        assertNotNull(p);
    }

    @After
    public void tearDown() throws Exception {
        p=null;
        assertNull(p);
    }

    @Test
    public void test() {

        assertEquals(p.getNome(),"nome");
        assertEquals(p.getIndirizzo(),"indirizzo");

        p.setNome("nome2");
        p.setIndirizzo("indirizzo2");

        assertEquals(p.getNome(),"nome2");
        assertEquals(p.getIndirizzo(),"indirizzo2");


    }

}
