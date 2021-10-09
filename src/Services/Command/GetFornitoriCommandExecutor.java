package Services.Command;

import Business.Messaggio;
import DomainClasses.CategoriaProdottoWrapper;
import Persistence.CapemontDAO;

public class GetFornitoriCommandExecutor implements CommandExecutor{


    @Override
    public Object execute(Messaggio messaggio) {

        System.out.println("GET LISTA FORNITORI");
        CategoriaProdottoWrapper categoria  =  (CategoriaProdottoWrapper) messaggio.getObject();
        return CapemontDAO.getInstance().getFornitori(categoria.getCategoria());

    }
}
