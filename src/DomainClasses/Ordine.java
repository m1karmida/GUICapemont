package DomainClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Date;

public class Ordine implements Serializable {

	private static final long serialVersionUID = 6529685098267757690L;
	private String codice;
	private Date data_emissione;
	private Agente agente;
	private Persona persona ;
	private ArrayList<ProdottoOrdinato> elenco_prodotti;

	public Ordine(String codice,Date data_emissione,  Agente agente, Persona persona) {
		this.agente = agente;
		this.codice = codice;
		this.data_emissione = data_emissione;
		this.persona = persona ;
		this.elenco_prodotti = new ArrayList<>();
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public void  addElenco_prodotti(ProdottoOrdinato prodotto) {
		this.elenco_prodotti.add(prodotto);
	}
	
	
	public boolean removeElenco_prodotti(String codice) {
		int index = 0;
		for (ProdottoOrdinato po : elenco_prodotti) {
			if (po.getCodice_prodotto().equals(codice))
				break;
			index++;
		}
		if (index<elenco_prodotti.size()) {
			elenco_prodotti.remove(index);
			return true;
		}
		return false;
	}

	public ArrayList<ProdottoOrdinato> getElenco_prodotti() {
		return elenco_prodotti;
	}

	public void clearElencoProdotti(){
		this.elenco_prodotti.clear();
	}
	public Date getData_emissione() {
		return data_emissione;
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

	@Override
	public String toString() {
		String ordine = "Ordine: \n";
		for (ProdottoOrdinato po : elenco_prodotti)
			ordine += po.toString() + "\n";
		return ordine;
	}
}