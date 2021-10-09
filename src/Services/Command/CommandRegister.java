package Services.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandRegister {
    private Map<Commands, CommandExecutor> commands;

    public CommandRegister(){
        commands = new HashMap<>();
    }

    public void addCommand(Commands command, CommandExecutor executor){
        commands.put(command,executor);
    }

    public CommandExecutor getCommandExecutor (Commands command){
        return commands.get(command);
    }

    public boolean hasCommand (Commands command){
        return commands.containsKey(command);
    }

}
