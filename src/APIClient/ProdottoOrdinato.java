package APIClient;

import java.io.Serializable;
import java.sql.Date;

public class ProdottoOrdinato extends Prodotto implements Serializable {


	private static final long serialVersionUID = 1L;
	private int quantita_ordinata ;

    public ProdottoOrdinato(String nome, CategoriaProdotto categoria, float prezzo, int quantita, Date data, Azienda a, Fornitore fornitore, int quantita_ordinata, String codice) {

        super(nome,categoria,prezzo,quantita,data,a,fornitore, codice) ;
        this.quantita_ordinata = quantita_ordinata ;

    }
    public ProdottoOrdinato(Prodotto prodotto, int quantita_ordinata) {

        super(prodotto.getNome(),prodotto.getCategoria(),prodotto.getPrezzo(),
        							prodotto.getQuantita(),prodotto.getData(),prodotto.getA(),prodotto.getFornitore(), prodotto.getCodice_prodotto()); ;
        
        this.quantita_ordinata = quantita_ordinata ;

    }

    public int getQuantita_ordinata() { return this.quantita_ordinata;}
    public void setQuantita_ordinata(int quantita) { this.quantita_ordinata = quantita ;}

}
