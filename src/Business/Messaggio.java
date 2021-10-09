package Business;

import Services.Command.CommandRegister;
import Services.Command.Commands;

import java.io.Serializable;

public class Messaggio implements Serializable {

    private static final long serialVersionUID = 6529685098267757690L;
    private Commands command ;
    private Object object ;

    public Messaggio(Commands command, Object object) {
        this.command = command;
        this.object = object;
    }

    public Commands getCommand() {
        return command;
    }

    public void setCommand(Commands command) {
        this.command = command;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
