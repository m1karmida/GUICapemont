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

public class Server {

    public static void main(String[] args) throws IOException {

        int portNumber = 5000;
        ServerSocket serverSocket = new ServerSocket(portNumber);
        Socket clientSocket = new Socket() ;
        PrintWriter out ;
        BufferedReader in ;
        ObjectOutputStream obj_out ;
        ObjectInputStream obj_in ;
        Invoker invoker = createCommands();


        String inputLine;
       
        while ( true ) {
            clientSocket = serverSocket.accept() ;
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            obj_out = new ObjectOutputStream(clientSocket.getOutputStream()) ;
            obj_in = new ObjectInputStream(clientSocket.getInputStream()) ;
            in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));

            //Read command
            //String cmd = in.readLine() ;
            //String[] split = cmd.split(" ") ;

            try {


                Messaggio msg_stream = (Messaggio) obj_in.readObject();
                obj_out.writeObject(invoker.execute(msg_stream));

            //Routes based on command selected

//                if (cmd.equals("LOGINUTENTE")) {
//
//                    //System.out.println(msg_stream.getObject().getClass()) ;
//                    Persona p = (Persona) msg_stream.getObject() ;
//
//                    System.out.println(" RICHIESTA LOGIN UTENTE USERNAME : " + p.getEmail() + " PASSWORD : " + p.getPassword());
//                    p = db.makeLogin(p.getEmail(),p.getPassword()) ;
//                    obj_out.writeObject(p);
//
//                }
//                else if (cmd.equals("LOGINAZIENDA")) {
//
//                    Azienda a = (Azienda) msg_stream.getObject() ;
//                    System.out.println(" RICHIESTA LOGIN AZIENDA EMAIL : " + a.getEmail() + " PASSWORD : " + a.getPassword());
//                    a = db.makeLoginAzienda(a.getEmail(),a.getPassword()) ;
//                    obj_out.writeObject(a);
//
//                }
                
//                else if (cmd.equals("REGISTERUTENTE")) {
//
//                    Persona p = (Persona) msg_stream.getObject() ;
//                    System.out.println("RICHIESTA REGISTRAZIONE PERSONA "+p.toString());
//                    if (db.makeRegister(p))
//                        out.println(true);
//                    else
//                        out.println(false);
//                }
//
//
//                else if (cmd.equals("REGISTERAZIENDA")) {
//
//                    Azienda a = (Azienda) msg_stream.getObject() ;
//                    System.out.println("RICHIESTA REGISTRAZIONE AZIENDA "+a.toString());
//                    if (db.makeRegisterAzienda(a))
//                        out.println(true);
//                    else
//                        out.println(false);
//
//                }
                
//                else if (cmd.equals("GETFORNITORI")) {
//
//                    System.out.println("GET LISTA FORNITORI");
//                    CategoriaProdottoWrapper categoria  =  (CategoriaProdottoWrapper) msg_stream.getObject();
//                    obj_out.writeObject(db.getFornitori(categoria.getCategoria()));
//
//                }
//
//                else if (cmd.equals("GETAGENTI")) {
//
//                    System.out.println("GET LISTA AGENTI");
//                    obj_out.writeObject(db.getAgenti());
//
//                }

//                else if (cmd.equals("GETLISTAPRODOTTI")) {
//
//                    System.out.println("GET LISTA PRODOTTI");
//                    obj_out.writeObject(db.getListaProdotti());
//
//                }
//
//                else if (cmd.equals("GETLISTAPRODOTTIAZIENDA")) {
//
//                    Azienda a = (Azienda) msg_stream.getObject() ;
//                    System.out.println("GET LISTA PRODOTTI RECENTI. AZIENDA : " + a.toString());
//                    obj_out.writeObject(db.getListaProdottiDiAzienda(a));
//
//                }
//                else if (cmd.equals("INSERISCIPRODOTTO")) {
//
//                    System.out.println("INSERIMENTO PRODOTTO");
//                    Prodotto p = (Prodotto) msg_stream.getObject() ;
//                    boolean ret = db.inserisciProdotto(p);
//                    if (ret) out.println("PRODOTTO INSERITO");
//                    else out.println("PRODOTTO NON INSERITO");
//
//                }
//
//                else if( cmd.equals("EFFETTUAORDINE")) {
//                    System.out.println("EFFETTUA ORDINE") ;
//                    Ordine o = (Ordine) msg_stream.getObject() ;
//                    if(db.makeOrder(o)) out.println("PROVA") ;

                //}
            } catch ( Exception e ) {
                e.printStackTrace();
            }
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
