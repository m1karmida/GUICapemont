package Services;

import DomainClasses.CategoriaProdottoWrapper;
import Business.Messaggio;
import Persistence.CapemontDAO;
import DomainClasses.Azienda;
import DomainClasses.Ordine;
import DomainClasses.Persona;
import DomainClasses.Prodotto;
import Services.Command.*;

import java.net.*;
import java.io.*;

public class Server implements Runnable {

    private Socket my_socket ;
    private Invoker inv ;

    public Server( Socket my_socket, Invoker inv) {
        this.my_socket = my_socket ;
        this.inv = inv ;

    }

    public static void main(String[] args) throws IOException {

        int portNumber = 5000;
        ServerSocket serverSocket = new ServerSocket(portNumber);
        Socket clientSocket = new Socket() ;
        ObjectOutputStream obj_out ;
        ObjectInputStream obj_in ;
        Invoker invoker = createCommands();


        String inputLine;
       
        while ( true ) {
            clientSocket = serverSocket.accept() ;
            System.out.println("Connesso, creo il Thread") ;
            new Thread(new Server(clientSocket, invoker)).start() ;

        }
    }

    public void run() {

        try {
            ObjectOutputStream obj_out = new ObjectOutputStream(my_socket.getOutputStream()) ;
            ObjectInputStream obj_in = new ObjectInputStream(my_socket.getInputStream()) ;
            Messaggio msg_stream = (Messaggio) obj_in.readObject();
            obj_out.writeObject(inv.execute(msg_stream));

        } catch(Exception e ) {
            e.printStackTrace();
        }
    }

    private static Invoker createCommands(){
        CommandRegister register = new CommandRegister();

        register.addCommand(Commands.EFFETTUAORDINE,new MakeOrderCommandExecutor());
        register.addCommand(Commands.GETAGENTI,new GetAgentiCommandExecutor());
        register.addCommand(Commands.REGISTERUTENTE,new RegisterUtenteCommandExecutor());
        register.addCommand(Commands.REGISTERAZIENDA, new RegisterAziendaCommandExecutor());
        register.addCommand(Commands.GETFORNITORI, new GetFornitoriCommandExecutor());
        register.addCommand(Commands.GETLISTAPRODOTTI,new GetListaProdottiCommandExecutor());
        register.addCommand(Commands.GETLISTAPRODOTTIAZIENDA, new GetListaProdottiAziendaCommandExecutor());
        register.addCommand(Commands.INSERISCIPRODOTTO, new InsertProdottoCommandExecutor());
        register.addCommand(Commands.LOGINAZIENDA, new LoginAziendaCommandExecutor());
        register.addCommand(Commands.LOGINUTENTE, new LoginUtenteCommandExecutor());

        return new Invoker(register);
    }
}
