package APIClient;

import java.io.Serializable;
import java.util.ArrayList;

public class Agente implements Serializable {

	private static final long serialVersionUID = 6529685098267757690L;
	private String P_IVA;
	private String nome;
	private String  cognome;
	private String indirizzo;
	private String recapito;
	private String ruolo; //responsabile o agente comune
	private String codice;
	private ArrayList<Ordine> ordini_gestiti;

	public Agente(String P_IVA, String nome, String cognome, String indirizzo, String recapito, String ruolo,
				  String codice, ArrayList ordini_gestiti){
		this.codice = codice;
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.P_IVA = P_IVA;
		this.recapito = recapito;
		this.ruolo = ruolo;
		this.ordini_gestiti = ordini_gestiti;
	}

	public ArrayList getOrdini_gestiti() {
		return ordini_gestiti;
	}

	public String getCodice() {
		return codice;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public String getP_IVA() {
		return P_IVA;
	}

	public String getRecapito() {
		return recapito;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setOrdini_gestiti(ArrayList ordini_gestiti) {
		this.ordini_gestiti = ordini_gestiti;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public void setP_IVA(String p_IVA) {
		P_IVA = p_IVA;
	}

	public void setRecapito(String recapito) {
		this.recapito = recapito;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

}