package Business;

import DomainClasses.*;
import Services.Command.Commands;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

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
  

    
    /******************** metodi per Aziende ***************************************/
    
    public boolean makeRegisterAzienda(Azienda a) {
        System.out.println("REGISTERAZIENDA "+a.getEmail()+" "+a.getPassword()) ;
        Messaggio m = new Messaggio(Commands.REGISTERAZIENDA,a) ;

        try {

            obj_out.writeObject(m);
            boolean ret = (Boolean)obj_in.readObject();
            System.out.println(ret ? "REGISTRAZIONE RIUSCITA" : "REGISTRAZIONE NON RIUSCITA") ;
            return ret;

        } catch( Exception e ) {
            e.printStackTrace();
        }

        return false ;
    }
  

    public Azienda makeLoginAzienda( String email, String password ) {

        Messaggio m = new Messaggio(Commands.LOGINAZIENDA, new Azienda(email, password)) ;
        System.out.println("LOGINAZIENDA "+ email +" "+ password) ;
        Azienda az = null;
        
        try {
            obj_out.writeObject(m);
            az = (Azienda) obj_in.readObject();
            System.out.println(az != null ? "LOGIN RIUSCITO" : "LOGIN NON RIUSCITO") ;

        } catch ( Exception e ) {
            e.printStackTrace();
        }
		return az;

    }

    
    public boolean inserisciProdotto(Prodotto p) {

        Messaggio m = new Messaggio(Commands.INSERISCIPRODOTTO,p) ;
        try {

            obj_out.writeObject(m);
            Boolean ret = (Boolean)obj_in.readObject();
            System.out.println(ret ? "INSERIMENTO RIUSCITO" : "INSERIMENTO NON RIUSCITO") ;
            return ret;
        } catch( Exception e ) {

            e.printStackTrace();

        }

        return false;
    }
    
    
    public ArrayList<Prodotto> getListaProdottidiAzienda(Azienda a) {

        Messaggio m = new Messaggio(Commands.GETLISTAPRODOTTIAZIENDA,a) ;
        System.out.println("GETLISTAPRODOTTIAZIENDA") ;

        ArrayList<Prodotto> prod = new ArrayList<Prodotto>() ;

        try {
            obj_out.writeObject(m);
            prod = (ArrayList<Prodotto>) obj_in.readObject() ;
            System.out.println(prod != null ? "LISTA CORRETTAMENTE RICEVUTA" : "LISTA NON RICEVUTA CORRETTAMENTE") ;
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return prod ;
    }
    
    public ArrayList<Fornitore> getFornitori (CategoriaProdotto categoria){
    	 Messaggio m = new Messaggio(Commands.GETFORNITORI,new CategoriaProdottoWrapper(categoria)) ;

         System.out.println("GETFORNITORI: " + categoria) ;
         ArrayList<Fornitore> fornitori = new ArrayList<Fornitore>() ;

         try {
             obj_out.writeObject(m);
             fornitori = (ArrayList<Fornitore>) obj_in.readObject() ;
             System.out.println(fornitori != null ? "LISTA CORRETTAMENTE RICEVUTA" : "LISTA NON RICEVUTA CORRETTAMENTE") ;

         } catch ( Exception e ) {
             e.printStackTrace();
         }

         return fornitori ;
    }

   /*******************************************************************************/ 

    
    
    
    /******************** metodi per Clienti ***************************************/
  

    public boolean makeRegisterUtente( Persona p) {
        System.out.println("REGISTERUTENTE "+p.getEmail()+" "+p.getPassword()) ;
        Messaggio m = new Messaggio(Commands.REGISTERUTENTE,p) ;

        try {

            obj_out.writeObject(m);
            Boolean ret =  (Boolean) obj_in.readObject();

            System.out.println(ret ? "REGISTRAZIONE UTENTE EFFETTUATA" : "REGISTRAZIONE UTENTE NON EFFETTUATA") ;

            return ret;
        } catch( Exception e ) {
            e.printStackTrace();
        }
        return false;
    }

    
    
    public Persona makeLoginUtente( String email, String password ) {

        Messaggio m = new Messaggio(Commands.LOGINUTENTE,new Persona(email, password)) ;
        System.out.println("LOGINUTENTE "+ email +" "+ password) ;
        Persona p = null;
        try {
            obj_out.writeObject(m) ;
            p = (Persona) obj_in.readObject();
            System.out.println(p!=null ? "LOGIN " + p.getNome() + " " + p.getCognome() + " RIUSCITO" : "LOGIN NON RIUSCITO") ;

        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return p;
    }

    
    public boolean makeOrder( Ordine o ) {
        Messaggio m = new Messaggio(Commands.EFFETTUAORDINE,o) ;

         try {

             obj_out.writeObject(m);
             Boolean ret =  (Boolean) obj_in.readObject();
             System.out.println(ret ? "ORDINE CORRETTAMENTE EFFETTUATO" : "ORDINE NON ANDATO A BUON FINE") ;
             return ret;

         } catch (Exception e) {
             e.printStackTrace();
         }

         return true ;

     }

    
    
    public ArrayList<Prodotto> getListaProdotti() {

        Azienda a = new Azienda() ; 
        Messaggio m = new Messaggio(Commands.GETLISTAPRODOTTI,null) ;
        System.out.println("GETLISTAPRODOTTI ") ;

        ArrayList<Prodotto> prod = new ArrayList<Prodotto>() ;

        try {
            obj_out.writeObject(m);
            prod = (ArrayList<Prodotto>) obj_in.readObject() ;
            System.out.println(prod != null ? "LISTA CORRETTAMENTE RICEVUTA" : "LISTA NON RICEVUTA CORRETTAMENTE") ;

        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return prod ;
    }

    
    
    public ArrayList<Agente> getAgenti (){
   	 Messaggio m = new Messaggio(Commands.GETAGENTI,null) ;
        System.out.println("GETAGENTI ") ;

        ArrayList<Agente> agenti = new ArrayList<>() ;

        try {
            obj_out.writeObject(m);
            agenti = (ArrayList<Agente>) obj_in.readObject() ;
            System.out.println(agenti != null ? "LISTA CORRETTAMENTE RICEVUTA" : "LISTA NON RICEVUTA CORRETTAMENTE") ;
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return agenti ;
   }
    
    public ArrayList<Azienda> getAziende (){
      	 Messaggio m = new Messaggio(Commands.GETAZIENDE,null) ;
           System.out.println("GETAZIENDE ") ;

           ArrayList<Azienda> aziende = new ArrayList<>() ;

           try {
               obj_out.writeObject(m);
               aziende = (ArrayList<Azienda>) obj_in.readObject() ;
               System.out.println(aziende != null ? "LISTA CORRETTAMENTE RICEVUTA" : "LISTA NON RICEVUTA CORRETTAMENTE") ;
               
           } catch ( Exception e ) {
               e.printStackTrace();
           }

           return aziende ;
      }
    
   /*******************************************************************************/ 

  

  
}