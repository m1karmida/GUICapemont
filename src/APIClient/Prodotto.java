package APIClient;

import java.io.Serializable;
import java.sql.Date;

public class Prodotto implements Serializable {

	private static final long serialVersionUID = 6529685098267757690L;
	private String codice_prodotto ;
	private String nome;
	private CategoriaProdotto categoria;
	private float prezzo;
	private int quantita ;
	private Date data ;
	private Azienda a ;
	private Fornitore fornitore ;


	public Prodotto(String nome, CategoriaProdotto categoria, float prezzo,int quantita, Date data,Azienda a, Fornitore fornitore) {
		this.categoria = categoria;
		this.quantita = quantita ;
		this.nome = nome;
		this.fornitore = fornitore;
		this.prezzo = prezzo;
		this.data = data ;
		this.a = a ;
		this.codice_prodotto = "" ;

	}
	
	public Prodotto(String nome, CategoriaProdotto categoria, float prezzo,int quantita, Azienda a, Fornitore fornitore) {
		this.categoria = categoria;
		this.quantita = quantita ;
		this.nome = nome;
		this.fornitore = fornitore;
		this.prezzo = prezzo;
		this.a = a ;
		this.codice_prodotto = "" ;

	}


	public void setCodice_prodotto(String codice) { this.codice_prodotto = codice; }
	public String getCodice_prodotto() { return this.codice_prodotto ;}

	public float getPrezzo() {
		return prezzo;
	}

	public CategoriaProdotto getCategoria() {
		return categoria;
	}

	public Fornitore getFornitore() {
		return fornitore;
	}

	public String getNome() {
		return nome;
	}

	public int getQuantita() { return this.quantita ;}
	public void setQuantita( int quantita ) { this.quantita = quantita ;}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCategoria(CategoriaProdotto categoria) {
		this.categoria = categoria;
	}

	public void setFornitore(Fornitore fornitore) {
		this.fornitore = fornitore;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Azienda getA() {
		return a;
	}

	public void setA(Azienda a) {
		this.a = a;
	}

	//TODO: formattare i toString
	@Override
	public String toString() {
		return "Prodotto{" +
				"nome='" + nome + '\'' +
				", categoria='" + categoria + '\'' +
				", prezzo=" + prezzo +
				", quantita=" + quantita +
				", data=" + data +
				", a=" + a +
				", fornitore=" + fornitore +
				'}';
	}
}