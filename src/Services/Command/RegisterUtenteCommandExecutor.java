package Services.Command;

import Business.Messaggio;
import DomainClasses.Persona;
import Persistence.CapemontDAO;

public class RegisterUtenteCommandExecutor implements CommandExecutor{

    @Override
    public Object execute(Messaggio messaggio) {

        Persona p = (Persona) messaggio.getObject() ;
        System.out.println("RICHIESTA REGISTRAZIONE PERSONA "+p.toString());
        Boolean registrato=false;

        if (CapemontDAO.getInstance().makeRegister(p))
            registrato = true;

        return registrato;
    }
}
