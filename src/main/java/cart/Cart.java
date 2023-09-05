package cart;

import java.util.ArrayList;
import java.util.Iterator;

import anagrafiche.Product;

// questo Cart non aumenta la qta di un prodotto se lo metti due volte
// ES: pizza 1 + pizza 1 = pizza 1 e pizza 1 anzichè pizza 2
public class Cart {
	
	private ArrayList<ProductInCart> cart;
	
	public Cart() {
		cart = new ArrayList<>();
	}
	
	// add()
	public void add(int idProdotto, int qta) throws Exception {
		cart.add(
			new ProductInCart(
				new Product(idProdotto),
				qta
			)
		);
	}
	public void add(Product p, int qta) {
		cart.add(new ProductInCart(p, qta));
	}
	
	// clear()
	public void clear() {
		this.cart.clear();
	}
	
	// remove()
	public void remove(int idProdotto) {
		// dal momento che ciclo sul cart e contemporaneamente
		// lo modifico, utilizzo un Iterator
		Iterator<ProductInCart> i = cart.iterator();
		while(i.hasNext()) {
			ProductInCart p = i.next();
			if(p.getId() == idProdotto) {
				i.remove();
				// break;
			}
		}
	}
	public void remove(Product prodotto) {
		Iterator<ProductInCart> i = cart.iterator();
		while(i.hasNext()) {
			ProductInCart p = i.next();
			if(p.getId() == prodotto.getId()) {
				i.remove();
				// break;
			}
		}
	}
	
	// count()
	public int getCount() {
		int tot = 0;
		for(ProductInCart p : cart) {
			tot += p.getQta();
		}
		return tot;
	}
	
	// getTotal()
	public float getTotal() {
		float tot = 0;
		for(ProductInCart p : cart) {
			tot += p.getPrezzo() * p.getQta();
		}
		return tot;
	}
	
	// update()
	
	// productExists()
	public boolean productExists(int idProdotto) {
		for(ProductInCart p : cart) {
			if(p.getId() == idProdotto) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<ProductInCart> getItems(){
		return cart;
	}

}




