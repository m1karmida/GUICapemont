package APIClient;

import java.sql.*;
import java.util.ArrayList;

public class DBConnectorPostgres {

    private final String url = "jdbc:postgresql://localhost/capemont_db" ; //Parametri da se
    private final String user = "postgres" ;
    private final String pwd = "password" ;

    public boolean makeLogin(Utente u) {

        String query = "SELECT * FROM PERSONE WHERE EMAIL='"+u.getEmail()+"' AND PASSWORD='"+u.getPassword()+"' ;" ;

        try {
            Connection conn = DriverManager.getConnection(url,user,pwd) ;
            Statement stm = conn.createStatement() ;
            ResultSet rst = stm.executeQuery(query) ;
            if ( rst.next() ) return true ;

            conn.close() ;
        } catch ( SQLException ex ) {
            System.out.println(ex.getMessage()) ;
        }
        return false ;
    }


    public boolean makeLoginAzienda(Azienda a) {

        String query = "SELECT * FROM AZIENDE WHERE EMAIL='"+a.getEmail()+"' AND PASSWORD='"+a.getPassword()+"' ;" ;

        try {
            Connection conn = DriverManager.getConnection(url,user,pwd) ;
            Statement stm = conn.createStatement() ;
            ResultSet rst = stm.executeQuery(query) ;
            if ( rst.next() ) return true ;

            conn.close() ;
        } catch ( SQLException ex ) {
            System.out.println(ex.getMessage()) ;
        }
        return false ;
    }

    public boolean inserisciProdotto( Prodotto p ) {

        String query = "INSERT INTO PRODOTTI VALUES (DEFAULT,'"+p.getNome()+"','"+p.getCategoria()+"',"+p.getQuantita()+","+p.getPrezzo()+","+p.getNum_acquistato()+",'"+p.getData()+"','"+p.getA().getEmail()+"','"+p.getFornitore().getCodice()+"');" ; ;

        try {
            Connection conn = DriverManager.getConnection(url,user,pwd) ;
            Statement stm = conn.createStatement() ;
            int exec_insert = stm.executeUpdate(query) ;
            if ( exec_insert > 0 ) return true ;

            conn.close() ;
        } catch ( SQLException ex ) {
            System.out.println(ex.getMessage()) ;
        }
        return false ;


    }

    /*
    public ArrayList<Prodotto> getListaProdottiRecenti() {

        String query = "SELECT * FROM PRODOTTI WHERE RECENTE=TRUE;" ;
        ArrayList<Prodotto> prodotti = new ArrayList<Prodotto>() ;

        try {
            Connection conn = DriverManager.getConnection(url,user,pwd) ;
            Statement stm = conn.createStatement() ;
            ResultSet rst = stm.executeQuery(query) ;
            while (rst.next() ) {

                String nome = rst.getString("nome") ;
                String categoria = rst.getString("categoria") ;
                int quantita = Integer.parseInt(rst.getString("QUANTITÀ"));
                float prezzo = Float.parseFloat(rst.getString("prezzo")) ;
                int num_acquistato = Integer.parseInt(rst.getString("num_acquistato")) ;
                boolean recente = Boolean.parseBoolean(rst.getString("recente")) ;
                Prodotto p = new Prodotto(nome,categoria,quantita,prezzo,num_acquistato,recente) ;
                prodotti.add(p) ;

            }

            conn.close() ;

        } catch( Exception e ) {
            e.printStackTrace();
        }

        return prodotti ;

    }

     */

    public ArrayList<Prodotto> getListaProdotti() {

        String query = "SELECT * FROM PRODOTTI;" ;
        ArrayList<Prodotto> prodotti = new ArrayList<Prodotto>() ;

        try {
            Connection conn = DriverManager.getConnection(url,user,pwd) ;
            Statement stm = conn.createStatement() ;
            Statement stm1 = conn.createStatement() ;
            Statement stm2 = conn.createStatement() ;
            ResultSet rst = stm.executeQuery(query) ;
            while (rst.next() ) {

                String nome = rst.getString("nome") ;
                String categoria = rst.getString("categoria") ;
                int quantita = Integer.parseInt(rst.getString("quantita"));
                float prezzo = Float.parseFloat(rst.getString("prezzo")) ;
                int num_acquistato = Integer.parseInt(rst.getString("num_acquistato")) ;
                Date data = Date.valueOf(rst.getString("recente")) ;
                String codice_fornitore = rst.getString("CODICE_FORNITORE") ;
                String email_azienda = rst.getString("EMAIL_AZIENDA") ;

                ResultSet rst1 = stm1.executeQuery("SELECT * FROM FORNITORI WHERE CODICE='"+codice_fornitore+"' ;") ;
                Fornitore f = new Fornitore () ;
                while ( rst1.next() ) {

                    f.setCodice(codice_fornitore);
                    f.setIndirizzo(rst1.getString("INDIRIZZO"));
                    f.setNome(rst1.getString("NOME")) ;
                    f.setTipologia(rst1.getString("TIPOLOGIA")) ;
                    f.setRecapito(rst1.getString("RECAPITO")) ;
                }

                ResultSet rst2 = stm2.executeQuery("SELECT * FROM AZIENDE WHERE EMAIL='"+email_azienda+"' ;") ;
                Azienda a = new Azienda() ;

                while ( rst2.next() ) {

                    a.setIndirizzo(rst2.getString("INDIRIZZO"));
                    a.setNome(rst2.getString("NOME")) ;
                    a.setEmail(rst2.getString("EMAIL")) ;
                    a.setP_IVA(rst2.getString("PIVA"));

                }

                Prodotto p = new Prodotto(nome,categoria,prezzo,quantita,num_acquistato,data,a,f) ;
                prodotti.add(p) ;

            }

            conn.close() ;

        } catch( Exception e ) {
            e.printStackTrace();
        }

        return prodotti ;
    }

    public ArrayList<Prodotto> getListaProdottiDiAzienda(Azienda a) {

        String query = "SELECT * FROM PRODOTTI WHERE EMAIL='"+a.getEmail()+"' ;" ;
        ArrayList<Prodotto> prodotti = new ArrayList<Prodotto>() ;

        try {
            Connection conn = DriverManager.getConnection(url,user,pwd) ;
            Statement stm = conn.createStatement() ;
            ResultSet rst = stm.executeQuery(query) ;
            while (rst.next() ) {

                String nome = rst.getString("nome") ;
                String categoria = rst.getString("categoria") ;
                int quantita = Integer.parseInt(rst.getString("QUANTITÀ"));
                float prezzo = Float.parseFloat(rst.getString("prezzo")) ;
                int num_acquistato = Integer.parseInt(rst.getString("num_acquistato")) ;
                Date data = Date.valueOf(rst.getString("recente")) ;
                String codice_fornitore = rst.getString("CODICE_FORNITORE") ;

                rst = stm.executeQuery("SELECT * FROM FORNITORI WHERE CODICE='"+codice_fornitore+"' ;") ;
                Fornitore f = new Fornitore () ;
                while ( rst.next() ) {

                    f.setCodice(codice_fornitore);
                    f.setIndirizzo(rst.getString("INDIRIZZO"));
                    f.setNome(rst.getString("NOME")) ;
                    f.setTipologia(rst.getString("TIPOLOGIA")) ;
                    f.setRecapito(rst.getString("RECAPITO")) ;
                }

                Prodotto p = new Prodotto(nome,categoria,prezzo,quantita,num_acquistato,data,a,f) ;
                prodotti.add(p) ;

            }

            conn.close() ;

        } catch( Exception e ) {
            e.printStackTrace();
        }

        return prodotti ;
    }


    public boolean makeRegister(Persona p) {


        String query = "INSERT INTO PERSONE VALUES ('"+p.getNome()+"','"+p.getCognome()+"','"+p.getEmail()+"','"+p.getPassword()+"','"+p.getIndirizzo()+"') ;" ;

        try {
            Connection conn = DriverManager.getConnection(url,user,pwd) ;
            Statement stm = conn.createStatement() ;
            int exec_insert = stm.executeUpdate(query) ;
            if ( exec_insert > 0 ) return true ;

            conn.close() ;
        } catch ( SQLException ex ) {
            System.out.println(ex.getMessage()) ;
        }
        return false ;
    }


    public boolean makeRegisterAzienda( Azienda a ) {


        String query = "INSERT INTO AZIENDE VALUES ('"+a.getNome()+"','"+a.getP_IVA()+"','"+a.getPassword()+"','"+a.getEmail()+"','"+a.getIndirizzo()+"') ;" ;

        try {
            Connection conn = DriverManager.getConnection(url,user,pwd) ;
            Statement stm = conn.createStatement() ;
            int exec_insert = stm.executeUpdate(query) ;
            if ( exec_insert > 0 ) return true ;

            conn.close() ;
        } catch ( SQLException ex ) {
            System.out.println(ex.getMessage()) ;
        }
        return false ;
    }

   /* public static void main( String [] args ) {

        String url = "jdbc:postgresql://localhost/capemont_db" ;
        String user = "postgres" ;
        String pwd = "password" ;
        String query = "SELECT * FROM CAPOCCHIA" ;

        try {
            Connection conn = DriverManager.getConnection(url,user,pwd) ;
            Statement stm = conn.createStatement() ;
            ResultSet rst = stm.executeQuery(query) ;
            while( rst.next() ) {
                System.out.println(rst.getString("pene")) ;
            }
            conn.close() ;
        } catch ( SQLException ex ) {
            System.out.println(ex.getMessage()) ;
        }
    }*/
}
