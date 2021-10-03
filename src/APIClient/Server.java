package APIClient;

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

        DBConnectorPostgres db = new DBConnectorPostgres() ;

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
                String cmd = msg_stream.getCommand();
                //Routes based on command selected

                if (cmd.equals("LOGINUTENTE")) {

                    //System.out.println(msg_stream.getObject().getClass()) ;
                    Persona p = (Persona) msg_stream.getObject() ;

                    System.out.println(" RICHIESTA LOGIN UTENTE USERNAME : " + p.getEmail() + " PASSWORD : " + p.getPassword());
                    Persona p_ret = db.makeLogin(p) ;
                    if (p_ret != null )
                        obj_out.writeObject(p_ret);
                    else
                        obj_out.writeObject(p) ;

                }
                else if (cmd.equals("LOGINAZIENDA")) {

                    Azienda a = (Azienda) msg_stream.getObject() ;
                    System.out.println(" RICHIESTA LOGIN AZIENDA EMAIL : " + a.getEmail() + " PASSWORD : " + a.getPassword());
                    Azienda a_ret = db.makeLoginAzienda(a) ;
                    if (a_ret != null )
                        obj_out.writeObject(a_ret);
                    else
                        obj_out.writeObject(a);

                }
                else if (cmd.equals("REGISTERUTENTE")) {

                    Persona p = (Persona) msg_stream.getObject() ;
                    System.out.println("RICHIESTA REGISTRAZIONE PERSONA "+p.toString());
                    if (db.makeRegister(p)) {
                        out.println("REGISTRAZIONE EFFETTUATA!");
                    } else {
                        out.println("LA REGISTRAZIONE NON E' ANDATA A BUON FINE");
                    }
                }


                else if (cmd.equals("REGISTERAZIENDA")) {

                    Azienda a = (Azienda) msg_stream.getObject() ;
                    System.out.println("RICHIESTA REGISTRAZIONE AZIENDA "+a.toString());
                    if (db.makeRegisterAzienda(a)) {
                        out.println("REGISTRAZIONE EFFETTUATA!");
                    } else {
                        out.println("LA REGISTRAZIONE NON E' ANDATA A BUON FINE");
                    }

                }

                else if (cmd.equals("GETLISTAPRODOTTI")) {

                    System.out.println("GET LISTA PRODOTTI");
                    obj_out.writeObject(db.getListaProdotti());

                }
                /*else if (cmd.equals("GETLISTAPRODOTTIRECENTI")) {

                    System.out.println("GET LISTA PRODOTTI RECENTI");
                    obj_out.writeObject(db.getListaProdottiRecenti());

                }*/

                else if (cmd.equals("GETLISTAPRODOTTIAZIENDA")) {

                    Azienda a = (Azienda) msg_stream.getObject() ;
                    System.out.println("GET LISTA PRODOTTI RECENTI. AZIENDA : " + a.toString());
                    obj_out.writeObject(db.getListaProdottiDiAzienda(a));

                }
                else if (cmd.equals("INSERISCIPRODOTTO")) {

                    System.out.println("INSERIMENTO PRODOTTO");
                    Prodotto p = (Prodotto) msg_stream.getObject() ;
                    boolean ret = db.inserisciProdotto(p);
                    if (ret) out.println("PRODOTTO INSERITO");
                    else out.println("PRODOTTO NON INSERITO");

                }

                else if( cmd.equals("EFFETTUAORDINE")) {
                    System.out.println("EFFETTUA ORDINE") ;
                    Ordine o = (Ordine) msg_stream.getObject() ;
                    if(db.makeOrder(o)) out.println("PROVA") ;

                }
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }
    }
}
