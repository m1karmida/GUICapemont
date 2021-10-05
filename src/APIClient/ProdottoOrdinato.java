package APIClient;

import java.io.Serializable;
import java.sql.Date;

public class ProdottoOrdinato extends Prodotto implements Serializable {

    private int quantita_ordinata ;

    public ProdottoOrdinato(String nome, String categoria, float prezzo, int quantita, Date data, Azienda a, Fornitore fornitore, int quantita_ordinata) {

        super(nome,categoria,prezzo,quantita,data,a,fornitore) ;
        this.quantita_ordinata = quantita_ordinata ;

    }

    public int getQuantita_ordinata() { return this.quantita_ordinata;}
    public void setQuantita_ordinata(int quantita) { this.quantita_ordinata = quantita ;}

}
