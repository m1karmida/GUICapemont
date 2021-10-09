package Services.Command;

import Business.Messaggio;
import DomainClasses.Azienda;
import Persistence.CapemontDAO;

public class GetListaProdottiAziendaCommandExecutor implements CommandExecutor{

    @Override
    public Object execute(Messaggio messaggio) {
        Azienda a = (Azienda) messaggio.getObject() ;
        System.out.println("GET LISTA PRODOTTI RECENTI. AZIENDA : " + a.toString());
        return CapemontDAO.getInstance().getListaProdottiDiAzienda(a);

    }
}
