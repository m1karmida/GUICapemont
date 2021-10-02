package APIClient;

import java.io.Serializable;
import java.util.ArrayList;

public class Azienda extends Utente implements Serializable {

	private String P_IVA;
	private ArrayList<Prodotto> prodotti_propri;

	public Azienda() {
		super() ;
		this.P_IVA = "" ;
		this.prodotti_propri = new ArrayList<Prodotto>() ;

	}
	public Azienda(String indirizzo, String email, String password, String nome, String P_IVA) {
		super(indirizzo, password, email, nome);
		this.P_IVA = P_IVA;
		this.prodotti_propri = new ArrayList<Prodotto>() ;

	}
	public Azienda(String indirizzo, String email, String password, String nome, String P_IVA,
				   ArrayList prodotti_propri) {
		super(indirizzo, password, email, nome);
		this.P_IVA = P_IVA;
		this.prodotti_propri = prodotti_propri ;
	}

	public ArrayList getProdotti_propri() {
		return prodotti_propri;
	}

	public void setProdotti_propri(ArrayList prodotti_propri) {
		this.prodotti_propri = prodotti_propri;
	}

	public String getP_IVA() {
		return P_IVA;
	}

	public void setP_IVA(String p_IVA) {
		this.P_IVA = p_IVA;
	}

	public void inserisci() {
		// TODO - implement Azienda.inserisci
		throw new UnsupportedOperationException();
	}

	public void visualizzazione_storico_vendite() {
		// TODO - implement Azienda.visualizzazione_storico_vendite
		throw new UnsupportedOperationException();
	}

	public void visualizzazione_prodotti_propri() {
		// TODO - implement Azienda.visualizzazione_prodotti_propri
		throw new UnsupportedOperationException();
	}

}