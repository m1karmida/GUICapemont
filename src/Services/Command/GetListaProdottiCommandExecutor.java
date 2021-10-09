package Services.Command;

import Business.Messaggio;
import Persistence.CapemontDAO;

public class GetListaProdottiCommandExecutor implements CommandExecutor{

    @Override
    public Object execute(Messaggio messaggio) {
        System.out.println("GET LISTA PRODOTTI");
        return CapemontDAO.getInstance().getListaProdotti();
    }
}
