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
                    Utente u = (Utente) msg_stream.getObject() ;

                    System.out.println(" RICHIESTA LOGIN UTENTE USERNAME : " + u.getEmail() + " PASSWORD : " + u.getPassword());
                    if (db.makeLogin(u))
                        out.println("OK");
                    else
                        out.println("NO");

                }
                else if (cmd.equals("LOGINAZIENDA")) {

                    Azienda a = (Azienda) msg_stream.getObject() ;
                    System.out.println(" RICHIESTA LOGIN AZIENDA EMAIL : " + a.getEmail() + " PASSWORD : " + a.getPassword());
                    if (db.makeLoginAzienda(a))
                        out.println("OK");
                    else
                        out.println("NO");

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
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }
    }
}
