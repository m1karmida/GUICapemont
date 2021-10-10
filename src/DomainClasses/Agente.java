package DomainClasses;

import java.io.Serializable;
import java.util.ArrayList;

public class Agente implements Serializable {

	private static final long serialVersionUID = 6529685098267757690L;
	private String P_IVA;
	private String nome;
	private String  cognome;
	private String email;
	private String indirizzo;
	private String recapito; 
	private String ruolo;
	private ArrayList<Ordine> ordini_gestiti;

	public Agente(String P_IVA, String nome, String cognome, String indirizzo, String email, String ruolo, String recapito){
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.P_IVA = P_IVA;
		this.recapito = recapito;
		this.ruolo = ruolo;
		this.setEmail(email);
		this.ordini_gestiti = new ArrayList<>();
	}

	public ArrayList getOrdini_gestiti() {
		return ordini_gestiti;
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

	public void addOrdini_gestiti(Ordine ordine) {
		this.ordini_gestiti.add(ordine);
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

	@Override
	public String toString() {
		return this.nome + " " + this.cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}