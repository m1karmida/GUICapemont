package APIClient;

import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Date;

public class Ordine implements Serializable {

	private static final long serialVersionUID = 6529685098267757690L;
	private String codice;
	private Date data_emissione;
	private int qta_venduta;
	private Agente agente;
	private Persona persona ;
	private ArrayList<ProdottoOrdinato> elenco_prodotti;

	public Ordine(String codice,
				  Date data_emissione, int qta_venduta, Agente agente, ArrayList<ProdottoOrdinato> elenco_prodotti, Persona persona) {
		this.agente = agente;
		this.codice = codice;

		this.data_emissione = data_emissione;


		this.qta_venduta = qta_venduta;
		this.elenco_prodotti = elenco_prodotti;
		this.persona = persona ;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public void setElenco_prodotti(ArrayList<ProdottoOrdinato> elenco_prodotti) {
		this.elenco_prodotti = elenco_prodotti;
	}

	public ArrayList<ProdottoOrdinato> getElenco_prodotti() {
		return elenco_prodotti;
	}

	public Date getData_emissione() {
		return data_emissione;
	}

	public int getQta_venduta() {
		return qta_venduta;
	}

	public Agente getAgente() {
		return agente;
	}

	public String getCodice() {
		return codice;
	}


	public void setCodice(String codice) {
		this.codice = codice;
	}

	public void setAgente(Agente agente) {
		this.agente = agente;
	}

	public void setData_emissione(Date data_emissione) {
		this.data_emissione = data_emissione;
	}

	public void setQta_venduta(int qta_venduta) {
		this.qta_venduta = qta_venduta;
	}

}