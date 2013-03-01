package ShoppingList;

import java.io.Serializable;
import java.util.List;

import se.chalmers.ait.dat215.project.*;

/**
 * A model of a ShoppingList, and all of the related functionality. Functions
 * basically like a ShoppingCart, except that it can be written to file. The
 * class writes itself to file
 * 
 * @author Jakob
 * 
 */
public class ShoppingList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ShoppingItem> list;
	private String name;
	
	public ShoppingList(String text) {
		this(null,text);
	}

	public ShoppingList(List<ShoppingItem> list, String name) {
		this.name = name;
		this.list = list;
	}



	/**
	 * Returns the List&lt;ShoppingList&gt; which contains the ShoppingItems in
	 * this ShoppingList.
	 * 
	 * @return The list of ShoppingItems.
	 */
	public List<ShoppingItem> getItems() {
		return this.list;
	}

	/**
	 * Returns the total price of the ShoppingList.
	 * 
	 * @return The total price.
	 */
	public double getTotal() {
		if (getItems() != null || getItems().size() != 0) {
			double total = 0;

			for (ShoppingItem item : getItems())
				total += item.getProduct().getPrice() * item.getAmount();

			return total;
		} else
			return 0;

	}

	/**
	 * Returns the number of items in the ShoppingList.
	 * 
	 * @return The number of items.
	 */
	public int getNbrItems() {
		if (getItems() != null || getItems().size() != 0) {
			return getItems().size();
		} else {
			return 0;
		}
	}

	/**
	 * Returns the name of this ShoppingList.
	 * 
	 * @return The name of this ShoppingList.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Adds an ShoppingItem to the ShoppingList.
	 * 
	 * @param item The ShoppingItem to be added.
	 */
	public void addItem(ShoppingItem item) {
		if (!this.contains(item)) {
			list.add(item);
		} else {
			ShoppingItem temp = this.getItemReference(item);
			temp.setAmount(temp.getAmount() + item.getAmount());
		}
	}

	/**
	 * Adds a product to the list of shoppingItems in the cart
	 * 
	 * @param prod
	 *            the product to be added
	 */
	public void addProduct(Product prod) {
		if (!this.contains(prod)) {
			addItem(new ShoppingItem(prod));
		} else {
			ShoppingItem temp = this.getItemReference(prod);
			temp.setAmount(temp.getAmount() + 1);
		}
	}

	/**
	 * Adds a product to the list of shoppingItems in the cart
	 * 
	 * @param prod
	 *            the product to be added
	 * @param amount
	 *            the amount to be added
	 */
	public void addProduct(Product prod, double amount) {
		if (!this.contains(prod)) {
			addItem(new ShoppingItem(prod, amount));
		} else {
			ShoppingItem temp = this.getItemReference(prod);
			temp.setAmount(temp.getAmount() + amount);
		}
	}

	/**
	 * 
	 * @param cart
	 *            the shoppingcart/"handlingslista" to be added.
	 */
	public void addCart(ShoppingCart cart) {
		List<ShoppingItem> list = cart.getItems();
		for (ShoppingItem item : list) {
			this.addItem(item);
		}
	}

	/**
	 * 
	 * @param item
	 *            the shopping item to find in the cart
	 * @return true if the item is in cart
	 */
	public boolean contains(ShoppingItem item) {
		return this.contains(item.getProduct());
	}

	/**
	 * 
	 * @param prod
	 *            the product to find in the cart
	 * @return true if the product is in the cart
	 */
	public boolean contains(Product prod) {

		for (ShoppingItem item : list) {
			if (item.getProduct().getName().equals(prod.getName())) {
				System.out.println("Does exist!");
				return true;
			}
		}

		return false;
	}

	public ShoppingItem getItemReference(ShoppingItem item) {
		for (ShoppingItem i : this.list) {
			ShoppingItem temp = i;
			if (item.getProduct().equals(temp.getProduct())) {
				return temp;
			}
		}
		return null;
	}

	public ShoppingItem getItemReference(Product prod) {
		return getItemReference(new ShoppingItem(prod));
	}

	public void addShoppingCartListener(ShoppingCartListener scl) {
		addShoppingCartListener(scl);
	}

	public void clear() {
		list.clear();
	}

	public void fireShoppingCartChanged(ShoppingItem item, boolean addEvent) {
		fireShoppingCartChanged(item, addEvent);
	}

	public void removeItem(int index) {
		list.remove(index);
	}

	public void removeItem(ShoppingItem item) {
		for (ShoppingItem i : list) {
			if (i.equals(item)) {
				list.remove(i);
			}
		}
	}

	public void removeShoppingCartListener(ShoppingCartListener scl) {
		removeShoppingCartListener(scl);
	}

	/**
	 * increases the amount of an item found in the shopping cart by 1
	 * 
	 * @param item
	 *            the item in question
	 */
	public void increaseItemAmount(ShoppingItem item) {
		this.increaseProductAmount(item.getProduct());
	}

	/**
	 * decreases the amount of an item found in the shopping cart by 1
	 * 
	 * @param item
	 *            the item in question
	 */
	public void decreaseItemAmount(ShoppingItem item) {
		this.decreaseProductAmount(item.getProduct());
	}

	/**
	 * increases the amount of a product found in the shopping cart by 1
	 * 
	 * @param prod
	 *            the product in question
	 */
	public void increaseProductAmount(Product prod) {
		if (!this.contains(prod)) {
			System.out.println("Item not in list!");
		} else {
			ShoppingItem temp = this.getItemReference(prod);
			temp.setAmount(temp.getAmount() + 1);
		}
	}

	/**
	 * decreases the amount of a product found in the shopping cart by 1
	 * 
	 * @param prod
	 *            the product in question
	 */
	public void decreaseProductAmount(Product prod) {
		if (!this.contains(prod)) {
			System.out.println("Item not in list!");
		} else {
			ShoppingItem temp = this.getItemReference(prod);
			temp.setAmount(temp.getAmount() - 1);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingList other = (ShoppingList) obj;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
