package cart;

import anagrafiche.Product;

public class ProductInCart extends Product {
	
	private int qta;
	
	public ProductInCart(Product p, int qta) {
		super(p.getId(), p.getNome(), p.getDescrizione(), p.getPrezzo());
		this.qta = qta;
	}

	public ProductInCart(
		int id, 
		String nome, 
		String descrizione, 
		float prezzo, 
		int qta
	) {
		super(id, nome, descrizione, prezzo);
		this.qta = qta;
	}

	public int getQta() {
		return qta;
	}

	public void setQta(int qta) {
		this.qta = qta;
	}
	

}
