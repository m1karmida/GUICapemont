package Services.Command;

import Business.Messaggio;
import DomainClasses.Azienda;
import Persistence.CapemontDAO;

public class LoginAziendaCommandExecutor implements CommandExecutor {

    public LoginAziendaCommandExecutor(){

    }

    @Override
    public Object execute(Messaggio messaggio) {
        Azienda a = (Azienda) messaggio.getObject() ;
        System.out.println(" RICHIESTA LOGIN AZIENDA EMAIL : " + a.getEmail() + " PASSWORD : " + a.getPassword());
        return CapemontDAO.getInstance().makeLoginAzienda(a.getEmail(),a.getPassword()) ;
    }
}
