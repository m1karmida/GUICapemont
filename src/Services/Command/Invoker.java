package Services.Command;

import Business.Messaggio;

import java.util.Map;

public class Invoker {

    private CommandRegister register;


    public Invoker(CommandRegister register){
        this.register = register;
    }

    public Object execute (Messaggio messaggio){
        CommandExecutor executor = register.getCommandExecutor(messaggio.getCommand());
        return executor.execute(messaggio);
    }
}
