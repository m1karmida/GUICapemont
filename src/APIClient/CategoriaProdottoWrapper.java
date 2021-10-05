package APIClient;

public class CategoriaProdottoWrapper {
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
