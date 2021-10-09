package Services.Command;

import Business.Messaggio;
import DomainClasses.Prodotto;
import Persistence.CapemontDAO;

public class InsertProdottoCommandExecutor implements CommandExecutor{


    @Override
    public Object execute(Messaggio messaggio) {

        System.out.println("INSERIMENTO PRODOTTO");
        Prodotto p = (Prodotto) messaggio.getObject() ;
        return (Boolean) CapemontDAO.getInstance().inserisciProdotto(p);
    }
}
