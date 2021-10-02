package APIClient;

import java.io.Serializable;
import java.util.ArrayList;

public class Persona extends Utente implements Serializable {

	private String cognome;
	private ArrayList<Ordine> ordini_effettuati;

	public Persona(String indirizzo, String email, String password, String nome, String cognome) {
		super(indirizzo, password, email, nome);
		this.cognome = cognome;
		this.ordini_effettuati = new ArrayList<Ordine>() ;

	}

	public Persona(String indirizzo, String email, String password, String nome, String cognome,
				   ArrayList ordini_effettuati) {
		super(indirizzo, password, email, nome);
		this.cognome = cognome;
		this.ordini_effettuati = ordini_effettuati;
	}

	public Persona(String email, String password) {
		super(email,password);
	}
	
	public ArrayList getOrdini_effettuati() {
		return ordini_effettuati;
	}

	public void setOrdini_effettuati(ArrayList ordini_effettuati) {
		this.ordini_effettuati = ordini_effettuati;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


}