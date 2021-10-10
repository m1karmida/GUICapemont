package Services.Command;

import Business.Messaggio;
import Persistence.CapemontDAO;

public class GetAziendaCommandExecutor implements CommandExecutor {

	public GetAziendaCommandExecutor() {
	}

	
	@Override
	public Object execute(Messaggio messaggio) {

        System.out.println("GET LISTA AZIENDE");
        return CapemontDAO.getInstance().getAziende();

	}

}
