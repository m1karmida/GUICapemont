package Services.Command;

import Business.Messaggio;
import DomainClasses.CategoriaProdottoWrapper;
import Persistence.CapemontDAO;

public class GetAgentiCommandExecutor implements CommandExecutor{


    @Override
    public Object execute(Messaggio messaggio) {

        System.out.println("GET LISTA AGENTI");
        return CapemontDAO.getInstance().getAgenti();

    }
}
