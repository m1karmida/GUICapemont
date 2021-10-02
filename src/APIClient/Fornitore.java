package APIClient;

import java.io.Serializable;
import java.util.ArrayList;

public class Fornitore implements Serializable {

	private static final long serialVersionUID = 6529685098267757690L;
	private String nome;
	private String indirizzo;
	private String recapito;
	private String tipologia;
	private String codice;
	private ArrayList<Prodotto> prodotti_forniti;

	public Fornitore() {
		this.nome = "" ;
		this.indirizzo = "" ;
		this.recapito = "" ;
		this.tipologia = "" ;
		this.codice = "" ;
		prodotti_forniti = new ArrayList<Prodotto>() ;

	}

	public Fornitore(String nome, String indirizzo, String recapito, String tipologia, String codice) {
		this.codice = codice;
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.recapito = recapito;
		this.tipologia = tipologia;
		this.prodotti_forniti = new ArrayList<Prodotto>() ;
	}

	public Fornitore(String nome, String indirizzo, String recapito, String tipologia, String codice,
					 ArrayList prodotti_forniti) {
		this.codice = codice;
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.recapito = recapito;
		this.tipologia = tipologia;
		this.prodotti_forniti = prodotti_forniti;
	}

	public ArrayList getProdotti_forniti() {
		return prodotti_forniti;
	}

	public void setProdotti_forniti(ArrayList prodotti_forniti) {
		this.prodotti_forniti = prodotti_forniti;
	}

	public String getNome() {
		return nome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public String getRecapito() {
		return recapito;
	}

	public String getCodice() {
		return codice;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setRecapito(String recapito) {
		this.recapito = recapito;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	@Override
	public String toString() {
		return "Fornitore{" +
				"nome='" + nome + '\'' +
				", indirizzo='" + indirizzo + '\'' +
				", recapito='" + recapito + '\'' +
				", tipologia='" + tipologia + '\'' +
				", codice='" + codice + '\'' +
				'}';
	}
}