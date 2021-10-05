package APIClient;

import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;

public class DBConnectorPostgres {

    private final String url = "jdbc:postgresql://localhost/capemont_db" ; //Parametri da se
    private final String user = "postgres" ;
    private final String pwd = "6454" ;

    public Persona makeLogin(String email, String password) {

        String query = "SELECT * FROM PERSONE WHERE EMAIL='" + email + "' AND PASSWORD='" + password + "' ;" ;
        Persona p = null;
        
        try {
            Connection conn = DriverManager.getConnection(url,user,pwd) ;
            Statement stm = conn.createStatement() ;
            ResultSet rst = stm.executeQuery(query) ;

            if ( rst.next() ) {
            	p = new Persona(email, password);	
            	
                p.setIndirizzo(rst.getString("INDIRIZZO"));
                p.setNome(rst.getString("NOME"));
                p.setCognome(rst.getString("COGNOME")) ;
                
            }

            conn.close() ;
        } catch ( SQLException ex ) {
            System.out.println(ex.getMessage()) ;
        }
        return p;
    }


    
    
    public Azienda makeLoginAzienda(String email, String password) {

        String query = "SELECT * FROM AZIENDE WHERE EMAIL='"+ email +"' AND PASSWORD='"+ password +"' ;" ;
        Azienda az = null;
        
        try {
            Connection conn = DriverManager.getConnection(url,user,pwd) ;
            Statement stm = conn.createStatement() ;
            ResultSet rst = stm.executeQuery(query) ;

            if (rst.next()) {
            	az = new Azienda(email,password);
                az.setNome(rst.getString("NOME"));
                az.setP_IVA(rst.getString("PIVA"));
                az.setIndirizzo(rst.getString("INDIRIZZO"));
            }

            conn.close() ;
        } catch ( SQLException ex ) {
            System.out.println(ex.getMessage()) ;
        }
        return az ;

    }

    
    public boolean inserisciProdotto( Prodotto p ) {

        String query = "INSERT INTO PRODOTTI VALUES (DEFAULT,'"+p.getNome()+"','"+p.getCategoria()+"',"+p.getQuantita()+","+p.getPrezzo()+",current_date,"+p.getA().getEmail()+"','"+p.getFornitore().getCodice()+"');" ; ;

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
    public ArrayList<Fornitore> getFornitori(CategoriaProdotto categoria) {

        String query = "SELECT * FROM FORNITORI WHERE tipologia ='" + categoria + "' ;" ;
        ArrayList<Fornitore> fornitori = new ArrayList<>() ;

        try {
            Connection conn = DriverManager.getConnection(url,user,pwd) ;
            Statement stm = conn.createStatement() ;
            Statement stm1 = conn.createStatement() ;
            Statement stm2 = conn.createStatement() ;
            ResultSet rst = stm.executeQuery(query) ;
            while (rst.next() ) {

                String codice = rst.getString("codice") ;
                String nome = rst.getString("nome") ;
                String tipologia = rst.getString("tipologia") ;
                String indirizzo = rst.getString("indirizzo");
                String recapito = rst.getString("recapito") ;

                Fornitore f = new Fornitore(nome, indirizzo, recapito, categoria, codice) ;
                fornitori.add(f);
                
            }

            conn.close() ;

        } catch( Exception e ) {
            e.printStackTrace();
        }

        return fornitori ;
    }
    
    

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

                String codice = rst.getString("codice") ;
                String nome = rst.getString("nome") ;
                CategoriaProdotto categoria = CategoriaProdotto.valueOf(rst.getString("categoria")) ;
                int quantita = Integer.parseInt(rst.getString("quantita"));
                float prezzo = Float.parseFloat(rst.getString("prezzo")) ;
                Date data = Date.valueOf(rst.getString("recente")) ;
                String codice_fornitore = rst.getString("CODICE_FORNITORE") ;
                String email_azienda = rst.getString("EMAIL_AZIENDA") ;

                ResultSet rst1 = stm1.executeQuery("SELECT * FROM FORNITORI WHERE CODICE='"+codice_fornitore+"' ;") ;
                Fornitore f = new Fornitore () ;
                while ( rst1.next() ) {

                    f.setCodice(codice_fornitore);
                    f.setIndirizzo(rst1.getString("INDIRIZZO"));
                    f.setNome(rst1.getString("NOME")) ;
                    f.setTipologia(Enum.valueOf(CategoriaProdotto.class,rst1.getString("TIPOLOGIA").toLowerCase())) ;
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

                Prodotto p = new Prodotto(nome,categoria,prezzo,quantita,data,a,f) ;
                p.setCodice_prodotto(codice);
                prodotti.add(p) ;

            }

            conn.close() ;

        } catch( Exception e ) {
            e.printStackTrace();
        }

        return prodotti ;
    }

    public ArrayList<Prodotto> getListaProdottiDiAzienda(Azienda a) {

        String query = "SELECT * FROM PRODOTTI WHERE EMAIL_AZIENDA='"+a.getEmail()+"' ;" ;
        ArrayList<Prodotto> prodotti = new ArrayList<Prodotto>() ;

        try {
            Connection conn = DriverManager.getConnection(url,user,pwd) ;
                        Statement stm = conn.createStatement() ;
            Statement stm1 = conn.createStatement() ;
            ResultSet rst = stm.executeQuery(query) ;
            while (rst.next() ) {

                String codice = rst.getString("codice") ;
                String nome = rst.getString("nome") ;
                CategoriaProdotto categoria = CategoriaProdotto.valueOf(rst.getString("categoria")) ;
               int quantita = Integer.parseInt(rst.getString("quantita"));
                float prezzo = Float.parseFloat(rst.getString("prezzo")) ;
                Date data = Date.valueOf(rst.getString("recente")) ;
                String codice_fornitore = rst.getString("CODICE_FORNITORE") ;

                ResultSet rst1 = stm1.executeQuery("SELECT * FROM FORNITORI WHERE CODICE='"+codice_fornitore+"' ;") ;
                Fornitore f = new Fornitore () ;
                while ( rst1.next() ) {

                    f.setCodice(codice_fornitore);
                    f.setIndirizzo(rst1.getString("INDIRIZZO"));
                    f.setNome(rst1.getString("NOME")) ;
                    f.setTipologia(Enum.valueOf(CategoriaProdotto.class,rst1.getString("TIPOLOGIA").toLowerCase())) ;
                    f.setRecapito(rst1.getString("RECAPITO")) ;
                }

                Prodotto p = new Prodotto(nome,categoria,prezzo,quantita,data,a,f) ;
                p.setCodice_prodotto(codice);
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

    public boolean makeOrder( Ordine o ) {
        ArrayList<ProdottoOrdinato> prodotti_ordinati = o.getElenco_prodotti() ;

        try {

            Connection conn = DriverManager.getConnection(url,user,pwd) ;
            CallableStatement cstmt = conn.prepareCall("{? = CALL check_prodotto(?,?)}") ;
            boolean check_all_products = true ;

            for (ProdottoOrdinato p : prodotti_ordinati) {

                cstmt.registerOutParameter(1, Types.BOOLEAN) ;
                System.out.println(p.getCodice_prodotto()) ;
                cstmt.setInt(2,Integer.parseInt(p.getCodice_prodotto())) ;
                System.out.println(p.getQuantita_ordinata()) ;
                cstmt.setInt(3, p.getQuantita_ordinata()) ;
                cstmt.executeUpdate() ;
                boolean result = cstmt.getBoolean(1) ;
                if (!result) check_all_products = false ;
                System.out.println("RISULTATO CHECK : "+result) ;
            }

            if ( check_all_products ) {

                CallableStatement cstmt1 = conn.prepareCall("{? = CALL update_quantita_prodotto(?,?)}") ;

                int somma_totale = 0 ;
                for ( ProdottoOrdinato p : prodotti_ordinati ) {
                    cstmt1.registerOutParameter(1, Types.BOOLEAN);
                    cstmt1.setInt(2, Integer.parseInt(p.getCodice_prodotto()));
                    cstmt1.setInt(3, p.getQuantita_ordinata()) ;
                    somma_totale += p.getQuantita_ordinata() ;
                    cstmt1.executeUpdate() ;
                }

                String query = "INSERT INTO ORDINI VALUES (DEFAULT,'"+o.getData_emissione()+"','"+somma_totale+"','"+o.getAgente().getP_IVA()+"',"+somma_totale+",'"+o.getPersona().getEmail()+"') ;" ;
                Statement stm = conn.createStatement() ;
                int exec_insert = stm.executeUpdate(query) ;

                String query2 = "SELECT MAX(CODICE) AS CODICE FROM ORDINI ;" ;
                Statement stm2 = conn.createStatement() ;
                ResultSet rst = stm2.executeQuery(query2) ;
                String codice_ordine = "" ;

                while (rst.next() ) {
                    codice_ordine = rst.getString("CODICE") ;
                }

                int insert_done = 0 ;
                for ( ProdottoOrdinato p : prodotti_ordinati ) {

                    String query3 = "INSERT INTO PRODOTTI_ORDINATI VALUES(DEFAULT,"+codice_ordine+","+p.getCodice_prodotto()+");" ;
                    Statement stm3 = conn.createStatement() ;
                    int insert = stm.executeUpdate(query3) ;
                    System.out.println("Numero di insert effettuati : "+(++insert_done)) ;

                }

            }
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
        return true ;
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
