package APIClient;

import java.io.*;
import java.net.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Client {

    private String hostName ;
    private int portNumber ;
    private Socket socket ;
    private PrintWriter out ;
    private BufferedReader in ;
    private ObjectInputStream obj_in ;
    private ObjectOutputStream obj_out ;


   public Client( String hostName, int portNumber ) throws IOException,UnknownHostException{

        this.hostName = hostName ;
        this.portNumber = portNumber ;

            socket = new Socket(hostName, portNumber);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            obj_in = new ObjectInputStream(socket.getInputStream()) ;
            obj_out = new ObjectOutputStream(socket.getOutputStream()) ;



    }

    public void closeConnection() {
        try {

            socket.close();

        } catch ( IOException e ) { e.printStackTrace(); }

    }
    public static void main(String[] args) throws IOException {

        /*Utente u = new Utente("Via Prova 1","password","user1@mail.com","Utente1") ;
        Client c = new Client("127.0.0.1",5000) ;
        c.makeLoginUtente(u) ;
        c.closeConnection();*/


        Azienda a = new Azienda("Via Azienda1","azienda1@mail.com","password","Andreozzi S.P.A.","PIVA1") ;

        /*
        Client c = new Client("127.0.0.1",5000) ;
        c.makeRegisterAzienda(a);
        c.closeConnection();

        Client c1 = new Client("127.0.0.1",5000) ;
        c1.makeLoginAzienda(a) ;
        c1.closeConnection();*/



        /*Persona p = new Persona("Via Utente1", "user1@mail.com","password","Alessio","Andreozzi") ;
        Client c2 = new Client("127.0.0.1",5000) ;
        c2.makeRegisterUtente(p);
        c2.closeConnection();*/

        /*

        Client c3 = new Client("127.0.0.1",5000) ;
        c3.getListaProdotti() ;
        c3.closeConnection();

        */

        Fornitore f = new Fornitore("Alessio Distribution","Via Fornitore1","Recapito1","Tipologia1","F000000001") ;
        Prodotto p = new Prodotto("Lampada","Arredamento",1000,150,10, Date.valueOf(LocalDate.now()),a,f) ;

        Client c4 = new Client("127.0.0.1",5000) ;
        c4.inserisciProdotto(p); ;
        c4.closeConnection();

        Client c5 = new Client("127.0.0.1",5000) ;
        c5.getListaProdotti() ;
        c5.closeConnection();

    }

    public boolean makeLoginUtente( Utente u ) {

        Messaggio m = new Messaggio("LOGINUTENTE",u) ;
        System.out.println("LOGINUTENTE "+u.getEmail()+" "+u.getPassword()) ;

        try {
            obj_out.writeObject(m) ;
            String ret = in.readLine();
            if (ret.equals("OK"))
        		return true;
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean makeLoginAzienda( Azienda a ) {

        Messaggio m = new Messaggio("LOGINAZIENDA",a) ;
        System.out.println("LOGINAZIENDA "+a.getEmail()+" "+a.getPassword()) ;

        try {
            obj_out.writeObject(m);
            String ret = in.readLine();
            if (ret.equals("OK"))
            		return true;
        } catch ( Exception e ) {
            e.printStackTrace();
        }
		return false;

    }


    public boolean makeRegisterUtente( Persona p) {
        System.out.println("REGISTERUTENTE "+p.getEmail()+" "+p.getPassword()) ;
        Messaggio m = new Messaggio("REGISTERUTENTE",p) ;

        try {
            obj_out.writeObject(m);
            String ret = in.readLine() ;
            if (ret.equals("OK"))
        		return true;
        } catch( Exception e ) {
            e.printStackTrace();
        }
        return false;
    }

    public void makeRegisterAzienda(Azienda a) {
        System.out.println("REGISTERAZIENDA "+a.getEmail()+" "+a.getPassword()) ;
        Messaggio m = new Messaggio("REGISTERAZIENDA",a) ;

        try {
            obj_out.writeObject(m);
            String ret = in.readLine() ;
            System.out.println(ret) ;
        } catch( Exception e ) {
            e.printStackTrace();
        }
    }

    public void inserisciProdotto(Prodotto p) {

        Messaggio m = new Messaggio("INSERISCIPRODOTTO",p) ;

        //System.out.println("INSERISCIPRODOTTO "+p.getNome()+" "+p.getCategoria()+" "+p.getQuantita()+" "+p.getPrezzo()+" "+p.getNum_acquistato()+" "+p.isRecente()) ;
        //out.println("INSERISCIPRODOTTO "+p.getNome()+" "+p.getCategoria()+" "+p.getQuantita()+" "+p.getPrezzo()+" "+p.getNum_acquistato()+" "+p.isRecente()) ;
        try {

            obj_out.writeObject(m) ;
            String ret = in.readLine() ;
            System.out.println(ret) ;

        } catch( Exception e ) {

            e.printStackTrace();

        }

    }

    public ArrayList<Prodotto> getListaProdotti() {

        Azienda a = new Azienda() ; //controllare se si può aggiungere un Oggetto vuoto e serializable
        Messaggio m = new Messaggio("GETLISTAPRODOTTI",a) ;
        System.out.println("GETLISTAPRODOTTI ") ;

        ArrayList<Prodotto> prod = new ArrayList<Prodotto>() ;

        try {
            obj_out.writeObject(m);
            prod = (ArrayList<Prodotto>) obj_in.readObject() ;
            for ( Prodotto p : prod ) {
                System.out.println(p.toString()) ;
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return prod ;
    }
}