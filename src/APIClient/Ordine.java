package APIClient;

import java.util.ArrayList;
import java.sql.Date;

public class Ordine {

	private String codice;
	private int cod_prodotto;
	private float prezzo_prodotto;
	private String nome_prodotto;
	private Date data_emissione;
	private int qta_venduta;
	private String agente;
	private ArrayList<Prodotto> elenco_prodotti;

	public Ordine(String codice, int cod_prodotto, float prezzo_prodotto, String nome_prodotto,
				  Date data_emissione, int qta_venduta, String agente, ArrayList elenco_prodotti) {
		this.agente = agente;
		this.codice = codice;
		this.cod_prodotto = cod_prodotto;
		this.data_emissione = data_emissione;
		this.prezzo_prodotto = prezzo_prodotto;
		this.nome_prodotto = nome_prodotto;
		this.qta_venduta = qta_venduta;
		this.elenco_prodotti = elenco_prodotti;
	}

	public ArrayList getElenco_prodotti() {
		return elenco_prodotti;
	}

	public void setElenco_prodotti(ArrayList elenco_prodotti) {
		this.elenco_prodotti = elenco_prodotti;
	}

	public Date getData_emissione() {
		return data_emissione;
	}

	public float getPrezzo_prodotto() {
		return prezzo_prodotto;
	}

	public int getCod_prodotto() {
		return cod_prodotto;
	}

	public int getQta_venduta() {
		return qta_venduta;
	}

	public String getAgente() {
		return agente;
	}

	public String getCodice() {
		return codice;
	}

	public String getNome_prodotto() {
		return nome_prodotto;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public void setAgente(String agente) {
		this.agente = agente;
	}

	public void setCod_prodotto(int cod_prodotto) {
		this.cod_prodotto = cod_prodotto;
	}

	public void setData_emissione(Date data_emissione) {
		this.data_emissione = data_emissione;
	}

	public void setNome_prodotto(String nome_prodotto) {
		this.nome_prodotto = nome_prodotto;
	}

	public void setPrezzo_prodotto(float prezzo_prodotto) {
		this.prezzo_prodotto = prezzo_prodotto;
	}

	public void setQta_venduta(int qta_venduta) {
		this.qta_venduta = qta_venduta;
	}

}