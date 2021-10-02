package APIClient;

import java.io.Serializable;

public class Utente implements Serializable {

	private String indirizzo;
	private String password;
	private String email;
	private String nome;

	public Utente() {
		this.indirizzo = "" ;
		this.password = "" ;
		this.email = "" ;
		this.nome = "" ;
	}
	
	
	public Utente(String indirizzo, String password, String email, String nome) {
		this.email = email;
		this.password = password;
		this.nome = nome;
		this.indirizzo = indirizzo;
	}
	
	public Utente(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getNome() {
		return nome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public void visualizzazione_piu_recenti() {
		// TODO - implement Utente.visualizzazione_pi�_recenti
		throw new UnsupportedOperationException();
	}

	public void visualizzazione_piu_acquistati() {
		// TODO - implement Utente.visualizzazione_pi�_acquistati
		throw new UnsupportedOperationException();
	}

	public void visualizzazione_prodotti() {
		// TODO - implement Utente.visualizzazione_prodotti
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		return "Utente{" +
				"indirizzo='" + indirizzo + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", nome='" + nome + '\'' +
				'}';
	}
}