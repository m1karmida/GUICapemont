package Services.Command;

import Business.Messaggio;
import DomainClasses.Azienda;
import DomainClasses.Persona;
import Persistence.CapemontDAO;

public class RegisterAziendaCommandExecutor implements CommandExecutor{

    @Override
    public Object execute(Messaggio messaggio) {

        Azienda a = (Azienda) messaggio.getObject() ;
        System.out.println("RICHIESTA REGISTRAZIONE AZIENDA "+a.toString());

        Boolean registrato = false;

        if (CapemontDAO.getInstance().makeRegisterAzienda(a))
            registrato = true;
        return registrato;
    }
}
