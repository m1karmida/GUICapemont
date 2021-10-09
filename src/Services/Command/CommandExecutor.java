package Services.Command;

import Business.Messaggio;
import Persistence.CapemontDAO;

public interface CommandExecutor {

    public Object execute(Messaggio messaggio);
}
