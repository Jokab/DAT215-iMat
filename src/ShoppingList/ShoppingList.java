package ShoppingList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import se.chalmers.ait.dat215.project.*;


public class ShoppingList implements Serializable {
	
	private List<ShoppingItem> list;
	private String name;
	
	private final static String FILE_PATH = System.getProperty("user.home") + "/.dat215/imat/shoppinglists/";
	
	public ShoppingList(String name) {
		this(null, name);
	}
	
	public ShoppingList(List<ShoppingItem> list, String name) {
		this.name = name;
		this.list = list;
		writeToFile();
	}
	
//	public ShoppingList(List<Product> list, String name) {
//		this.name = name;
//		for(Product p : list) {
//			addProduct(p);
//		}
//		writeToFile();
//	}
	
	
	
	public List<ShoppingItem> getItems() {
		return this.list;
	}

	public double getTotal() {
		if(getItems() != null || getItems().size() != 0) {
			double total = 0;
			
			for(ShoppingItem item : getItems()) 
				total += item.getProduct().getPrice() * item.getAmount();	
			
			return total;
		} else 
			return 0;
		
	}
	
	public int getNbrItems() {
		if(getItems() != null || getItems().size() != 0) {
			return getItems().size();
		} else {
			return 0;
		}
	}
	
	public String getName() {
		return this.name;
	}
	

	
	public void addItem(ShoppingItem item){
		if(!this.contains(item)){
			addItem(item);
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
			addItem(new ShoppingItem(prod));
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
			addItem(new ShoppingItem(prod, amount));
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
		for(ShoppingItem item : list) {
			this.addItem(item);
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

		for(ShoppingItem item : list) {
			if(item.getProduct().getName().equals(prod.getName())) {
				System.out.println("Does exist!");
				return true;
			} 
		}
		
		return false;
	}
	
	
	public ShoppingItem getItemReference(ShoppingItem item){
		for(ShoppingItem i : this.list) {
			ShoppingItem temp = i;
			if(item.getProduct().equals(temp.getProduct())){
				return temp;
			}
		}
		return null;
	}
	
	public ShoppingItem getItemReference(Product prod) {
		return getItemReference(new ShoppingItem(prod));
	}
	
	public void addShoppingCartListener(ShoppingCartListener scl){
		addShoppingCartListener(scl);
	}
	public void clear(){
		list.clear();
	}
	public void fireShoppingCartChanged(ShoppingItem item, boolean addEvent){
		fireShoppingCartChanged(item, addEvent);
	}

	public void removeItem(int index){
		list.remove(index);
	}
	public void removeItem(ShoppingItem item){
		for(ShoppingItem i : list) {
			if(i.equals(item)) {
				list.remove(i);
			}
		}	
	}
	
	public void removeShoppingCartListener(ShoppingCartListener scl){
		removeShoppingCartListener(scl);
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
	
	private void writeToFile() {
		try {
			File file = new File(FILE_PATH + this.name + ".txt");
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));

			out.writeObject(this);
			out.flush();
			out.close();		
		} catch (IOException e) {
			System.out.println("File does not exist.");
		}
	}
	
}
