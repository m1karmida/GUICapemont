package Services.Command;

import Business.Messaggio;
import DomainClasses.Ordine;
import Persistence.CapemontDAO;

public class MakeOrderCommandExecutor implements CommandExecutor{

    @Override
    public Object execute(Messaggio messaggio) {

        System.out.println("EFFETTUA ORDINE") ;
        Ordine o = (Ordine) messaggio.getObject() ;
        return (Boolean)CapemontDAO.getInstance().makeOrder(o);
    }
}
