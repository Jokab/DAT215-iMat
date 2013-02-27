package shoppingcart;

import java.util.Iterator;
import java.util.List;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingCartListener;
import se.chalmers.ait.dat215.project.ShoppingItem;
/**
 * 
 * @author christoffer
 * A class used to add functionality to the the singleton ShoppingCart class.
 */
public class ShoppingCartAdapter {
	public IMatDataHandler dataHandler;
	public ShoppingCart shoppingCart;
	public ShoppingCartAdapter(){
		dataHandler = IMatDataHandler.getInstance();
		shoppingCart = dataHandler.getShoppingCart();
	}
	public ShoppingCart getShoppingCart(){
		return shoppingCart;
	}
	/**
	 * Adds an item to the shopping cart. If that item already exists it's amount is increased.
	 * @param item the item to be added
	 */
	public void addItem(ShoppingItem item){
		if(!this.contains(item)){
			shoppingCart.addItem(item);
		} else {
			ShoppingItem temp = this.getItemReference(item);
			temp.setAmount(temp.getAmount() + item.getAmount());
		}
	}
	/**
	 * Adds a product to the list of shoppingItems in the cart
	 * @param prod the product to be added
	 */
	public void addProduct(Product prod){
		if(!this.contains(prod)){
			shoppingCart.addItem(new ShoppingItem(prod));
		} else {
			ShoppingItem temp = this.getItemReference(prod);
			temp.setAmount(temp.getAmount() + 1);
		}
	}
	/**
	 * Adds a product to the list of shoppingItems in the cart
	 * @param prod the product to be added
	 * @param amount the amount to be added
	 */
	public void addProduct(Product prod, double amount){
		if(!this.contains(prod)){
			shoppingCart.addItem(new ShoppingItem(prod, amount));
		} else {
			ShoppingItem temp = this.getItemReference(prod);
			temp.setAmount(temp.getAmount() + amount);
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
		List<ShoppingItem> list = shoppingCart.getItems();
		
		for(ShoppingItem item : list) {
			if(item.getProduct().getName().equals(prod.getName())) {
				System.out.println("Does exist!");
				return true;
			} 
		}
		
		return false;
	}
	public ShoppingItem getItemReference(ShoppingItem item){
		List<ShoppingItem> list = shoppingCart.getItems();
		Iterator i = list.iterator();
		while(i.hasNext()){
			ShoppingItem temp = (ShoppingItem) i.next();
			if(item.getProduct().equals(temp.getProduct())){
				return temp;
			}
		}
		return null;
	}
	public ShoppingItem getItemReference(Product prod) {
		return getItemReference(new ShoppingItem(prod));
	}
	public int getNbrItems() {
		if(shoppingCart.getItems() != null || shoppingCart.getItems().size() != 0) {
			return shoppingCart.getItems().size();
		} else {
			return 0;
		}
	}
	public void addShoppingCartListener(ShoppingCartListener scl){
		shoppingCart.addShoppingCartListener(scl);
	}
	public void clear(){
		shoppingCart.clear();
	}
	public void fireShoppingCartChanged(ShoppingItem item, boolean addEvent){
		shoppingCart.fireShoppingCartChanged(item, addEvent);
	}
	public List<ShoppingItem> getItems(){
		return shoppingCart.getItems();
	}
	public double getTotal(){
		return shoppingCart.getTotal();
	}
	public void removeItem(int index){
		shoppingCart.removeItem(index);
	}
	public void removeItem(ShoppingItem item){
		shoppingCart.removeItem(item);
		
	}
	public void removeShoppingCartListener(ShoppingCartListener scl){
		shoppingCart.removeShoppingCartListener(scl);
	}
	/**
	 * increases the amount of an item found in the shopping cart by 1
	 * @param item the item in question
	 */
	public void increaseItemAmount(ShoppingItem item){
		this.increaseProductAmount(item.getProduct());
	}
	/**
	 * decreases the amount of an item found in the shopping cart by 1
	 * @param item the item in question
	 */
	public void decreaseItemAmount(ShoppingItem item){
		this.decreaseProductAmount(item.getProduct());
	}
	/**
	 * increases the amount of an product found in the shopping cart by 1
	 * @param prod the product in question
	 */
	public void increaseProductAmount(Product prod){
		if(!this.contains(prod)){
			System.out.println("Item not in list!");
		} else {
			ShoppingItem temp = this.getItemReference(prod);
			temp.setAmount(temp.getAmount() + 1);
		}
	}
	/**
	 * decreases the amount of an product found in the shopping cart by 1
	 * @param prod the product in question
	 */
	public void decreaseProductAmount(Product prod){
		if(!this.contains(prod)){
			System.out.println("Item not in list!");
		} else {
			ShoppingItem temp = this.getItemReference(prod);
			temp.setAmount(temp.getAmount() - 1);
		}
	}
	
	/**
	 * Returns the total amount of unique products in the shoppingcart
	 * @return total amount
	 */
	public int getTotalProductsAmount() {
		return shoppingCart.getItems().size();
	}
}