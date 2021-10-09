package Services.Command;

import Business.Messaggio;
import DomainClasses.Persona;
import Persistence.CapemontDAO;

public class LoginUtenteCommandExecutor implements CommandExecutor {


    public LoginUtenteCommandExecutor(){

    }

    @Override
    public Object execute(Messaggio messaggio) {

        CapemontDAO db = CapemontDAO.getInstance();
        Persona p = (Persona) messaggio.getObject();
        System.out.println(" RICHIESTA LOGIN UTENTE USERNAME : " + p.getEmail() + " PASSWORD : " + p.getPassword());
        return db.makeLogin(p.getEmail(),p.getPassword()) ;

    }
}
