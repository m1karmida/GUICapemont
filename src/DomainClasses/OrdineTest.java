package DomainClasses;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

public class OrdineTest {

    Ordine o;
    Date data_emissione;
    Agente agente= new Agente("piva_prova","agente_prova", "cognome", "via prova 1", "3333333333", "agente",
            "codice");
    Persona persona;
    @Before
    public void setUp() throws Exception {
        o=new Ordine( "codice",data_emissione, agente, persona);
        assertNotNull(o);
    }

    @After
    public void tearDown() throws Exception {
        o=null;
        assertNull(o);
    }

    @Test
    public void test() {

        assertEquals(o.getCodice(),"codice");
        assertEquals(o.getAgente(),agente);

        o.setCodice("codice2");
        agente= new Agente("piva_prova","agente_prova", "cognome", "via prova 1", "3333333333", "agente",
                "codice2");
        o.setAgente(agente);

        assertEquals(o.getCodice(),"codice2");
        assertEquals(o.getAgente(),agente);



    }

}

