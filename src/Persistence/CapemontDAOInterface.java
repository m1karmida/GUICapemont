package Persistence;

import DomainClasses.*;

import java.util.ArrayList;

public interface CapemontDAOInterface {

    public Persona makeLogin(String email, String password) ;
    public Azienda makeLoginAzienda(String email, String password) ;
    public boolean inserisciProdotto(Prodotto p) ;
    public ArrayList<Fornitore> getFornitori(CategoriaProdotto categoria) ;
    public ArrayList<Agente> getAgenti() ;
    public ArrayList<Prodotto> getListaProdotti() ;
    public ArrayList<Prodotto> getListaProdottiDiAzienda(Azienda a) ;
    public boolean makeRegister(Persona p) ;
    public boolean makeRegisterAzienda( Azienda a ) ;
    public boolean makeOrder( Ordine o ) ;


}
