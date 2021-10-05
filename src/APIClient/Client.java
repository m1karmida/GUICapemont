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
  
    
//    public static void main(String[] args) throws IOException {
//
//        /*Utente u = new Utente("Via Prova 1","password","user1@mail.com","Utente1") ;
//        Client c = new Client("127.0.0.1",5000) ;
//        c.makeLoginUtente(u) ;
//        c.closeConnection();*/
//
//
//        Azienda a = new Azienda("Via Azienda1","azienda1@mail.com","password","Andreozzi S.P.A.","PIVA1") ;
//
//        Azienda a2 = new Azienda("azienda1@mail.com","password") ;
//
//        /*
//        Client c = new Client("127.0.0.1",5000) ;
//        c.makeRegisterAzienda(a);
//        c.closeConnection();*/
//
//        Client c1 = new Client("127.0.0.1",5000) ;
//        a2 = c1.makeLoginAzienda(a2) ;
//        System.out.println(a2.getP_IVA()) ;
//        c1.closeConnection();
//
//
//
//        Persona p = new Persona("user1@mail.com","password") ;
//        Client c2 = new Client("127.0.0.1",5000) ;
//        c2.makeLoginUtente(p);
//        c2.closeConnection();
//
//        /*
//
//        Client c3 = new Client("127.0.0.1",5000) ;
//        c3.getListaProdotti() ;
//        c3.closeConnection();
//
//        */
//
//        ArrayList<Ordine> ordini_gestiti = new ArrayList<Ordine>() ;
//        Persona pers = new Persona("Via Indirizzo1", "alessioandreox@yahoo.it","password","Alessio","Andreozzi" );
//        Agente ag = new Agente("AP000000002","Alessio","Andreozzi","Via Indirizzo1","000","Agente1","a002",ordini_gestiti) ;
//        Fornitore f = new Fornitore("Alessio Distribution","Via Fornitore1","Recapito1","Tipologia1","F000000001") ;
//        ProdottoOrdinato p1 = new ProdottoOrdinato("Lampada","Arredamento",1000,150,10, Date.valueOf(LocalDate.now()),a,f,10) ;
//        ProdottoOrdinato p2 = new ProdottoOrdinato("Lampada","Arredamento",1000,150,10, Date.valueOf(LocalDate.now()),a,f,10) ;
//        ProdottoOrdinato p3 = new ProdottoOrdinato("Lampada","Arredamento",1000,150,10, Date.valueOf(LocalDate.now()),a,f,10) ;
//        ProdottoOrdinato p4 = new ProdottoOrdinato("Lampada","Arredamento",1000,150,10, Date.valueOf(LocalDate.now()),a,f,10) ;
//        p1.setCodice_prodotto("12");
//        p2.setCodice_prodotto("13");
//        p3.setCodice_prodotto("14");
//        p4.setCodice_prodotto("15");
//
//        ArrayList<ProdottoOrdinato> prod_ord_list = new ArrayList<ProdottoOrdinato>() ;
//        prod_ord_list.add(p1) ;
//        prod_ord_list.add(p2) ;
//        prod_ord_list.add(p3) ;
//        prod_ord_list.add(p4) ;
//
//        Ordine o = new Ordine("1",Date.valueOf(LocalDate.now()), 100, ag, prod_ord_list,pers ) ;
//
//        Client c4 = new Client("127.0.0.1",5000) ;
//        c4.makeOrder(o); ;
//        c4.closeConnection();
//
//        Client c5 = new Client("127.0.0.1",5000) ;
//        c5.getListaProdotti() ;
//        c5.closeConnection();
//
//        Client c6 = new Client("127.0.0.1",5000) ;
//        Azienda azienda1 = new Azienda("azienda1@mail.com","password") ;
//        c6.getListaProdottidiAzienda(azienda1) ;
//        c6.closeConnection();
//
//    }
    
    /******************** metodi per Aziende ***************************************/
    
    public boolean makeRegisterAzienda(Azienda a) {
        System.out.println("REGISTERAZIENDA "+a.getEmail()+" "+a.getPassword()) ;
        Messaggio m = new Messaggio("REGISTERAZIENDA",a) ;

        try {

            obj_out.writeObject(m);
            return Boolean.parseBoolean(in.readLine());

        } catch( Exception e ) {
            e.printStackTrace();
        }

        return false ;
    }
  

    public Azienda makeLoginAzienda( String email, String password ) {

        Messaggio m = new Messaggio("LOGINAZIENDA", new Azienda(email, password)) ;
        System.out.println("LOGINAZIENDA "+ email +" "+ password) ;
        Azienda az = null;
        
        try {
            obj_out.writeObject(m);
            az = (Azienda) obj_in.readObject();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
		return az;

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
    
    
    public ArrayList<Prodotto> getListaProdottidiAzienda(Azienda a) {

        Messaggio m = new Messaggio("GETLISTAPRODOTTIAZIENDA",a) ;
        System.out.println("GETLISTAPRODOTTIAZIENDA") ;

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
    
    public ArrayList<Fornitore> getFornitori (CategoriaProdotto categoria){
    	 Messaggio m = new Messaggio("GETFORNITORI",categoria) ;
         System.out.println("GETFORNITORI") ;

         ArrayList<Fornitore> fornitori = new ArrayList<Fornitore>() ;

         try {
             obj_out.writeObject(m);
             fornitori = (ArrayList<Fornitore>) obj_in.readObject() ;
             for ( Fornitore f : fornitori ) {
                 System.out.println(f.toString()) ;
             }
         } catch ( Exception e ) {
             e.printStackTrace();
         }

         return fornitori ;
    }

   /*******************************************************************************/ 

    
    
    
    /******************** metodi per Clienti ***************************************/
  

    public boolean makeRegisterUtente( Persona p) {
        System.out.println("REGISTERUTENTE "+p.getEmail()+" "+p.getPassword()) ;
        Messaggio m = new Messaggio("REGISTERUTENTE",p) ;

        try {

            obj_out.writeObject(m);
            return Boolean.parseBoolean(in.readLine());
        } catch( Exception e ) {
            e.printStackTrace();
        }
        return false;
    }

    
    
    public Persona makeLoginUtente( String email, String password ) {

        Messaggio m = new Messaggio("LOGINUTENTE",new Persona(email, password)) ;
        System.out.println("LOGINUTENTE "+ email +" "+ password) ;
        Persona p = null;
        try {
            obj_out.writeObject(m) ;
            p = (Persona) obj_in.readObject();
            System.out.println(p.toString()) ;

        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return p;
    }

    
    public boolean makeOrder( Ordine o ) {
        Messaggio m = new Messaggio("EFFETTUAORDINE",o) ;

         try {

             obj_out.writeObject(m);
             String ret = in.readLine() ;
             System.out.println(ret) ;
         } catch (Exception e) {
             e.printStackTrace();
         }

         return true ;

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

   /*******************************************************************************/ 

  

  
}