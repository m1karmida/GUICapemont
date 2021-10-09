package DomainClasses;

import DomainClasses.CategoriaProdotto;

import java.io.Serializable;

public class CategoriaProdottoWrapper implements Serializable {
	private CategoriaProdotto categoria;
	
	public CategoriaProdottoWrapper(CategoriaProdotto categoria) {
		this.categoria = categoria;
	}

	public CategoriaProdotto getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaProdotto categoria) {
		this.categoria = categoria;
	}
	
	
}
