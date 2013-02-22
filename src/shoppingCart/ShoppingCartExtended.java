package shoppingCart;

import java.util.Iterator;
import java.util.List;

import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingItem;

public class ShoppingCartExtended extends ShoppingCart {
	/**
	 * Adds an item to the shopping cart. If that item already exists it's amount is increased.
	 */
	public void addItem(ShoppingItem item){
		if(!this.contains(item)){
			super.addItem(item);
		} else {
			ShoppingItem temp = this.getItemReference(item);
			temp.setAmount(temp.getAmount() + item.getAmount());
		}
	}
	/**
	 * 
	 * @param cart the shoppingcart/"handlingslista" to be added.
	 */
	public void addCart(ShoppingCart cart){
		List<ShoppingItem> list = cart.getItems();
		Iterator i = list.iterator();
		while(i.hasNext()){
			this.addItem((ShoppingItem) i.next());
		}
	}
	/**
	 * 
	 * @param item the shopping item to find in the cart
	 * @return true if the item is in cart
	 */
	public boolean contains(ShoppingItem item){
		return this.contains(item.getProduct());
	}
	/**
	 * 
	 * @param prod the product to find in the cart
	 * @return true if the product is in the cart
	 */
	public boolean contains(Product prod){
		List<ShoppingItem> list = this.getItems();
		Iterator i = list.iterator();
		while(i.hasNext()){
			ShoppingItem temp = (ShoppingItem) i.next();
			if(prod.equals(temp.getProduct())){
				return true;
			}
		}
		return false;
	}
	public ShoppingItem getItemReference(ShoppingItem item){
		List<ShoppingItem> list = this.getItems();
		Iterator i = list.iterator();
		while(i.hasNext()){
			ShoppingItem temp = (ShoppingItem) i.next();
			if(item.getProduct().equals(temp.getProduct())){
				return temp;
			}
		}
		return null;
	}
}