package APIClient;

import java.io.Serializable;
import java.sql.Date;

public class Prodotto implements Serializable {

	private static final long serialVersionUID = 6529685098267757690L;
	private String nome;
	private String categoria;
	private float prezzo;
	private int quantita ;
	private int num_acquistato ;
	private Date data ;
	private Azienda a ;
	private Fornitore fornitore ;


	public Prodotto(String nome, String categoria, float prezzo,int quantita, int num_acquistato, Date data,Azienda a, Fornitore fornitore) {
		this.categoria = categoria;
		this.quantita = quantita ;
		this.nome = nome;
		this.fornitore = fornitore;
		this.prezzo = prezzo;
		this.data = data ;
		this.a = a ;
		this.num_acquistato = num_acquistato ;

	}



	public float getPrezzo() {
		return prezzo;
	}

	public String getCategoria() {
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

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setFornitore(Fornitore fornitore) {
		this.fornitore = fornitore;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public int getNum_acquistato() {
		return num_acquistato;
	}

	public void setNum_acquistato(int num_acquistato) {
		this.num_acquistato = num_acquistato;
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

	@Override
	public String toString() {
		return "Prodotto{" +
				"nome='" + nome + '\'' +
				", categoria='" + categoria + '\'' +
				", prezzo=" + prezzo +
				", quantita=" + quantita +
				", num_acquistato=" + num_acquistato +
				", data=" + data +
				", a=" + a +
				", fornitore=" + fornitore +
				'}';
	}
}