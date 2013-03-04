package shoppingCart;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Iterator;
import java.util.List;

import ShoppingList.ShoppingList;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingCartListener;
import se.chalmers.ait.dat215.project.ShoppingItem;

/**
 * 
 * @author christoffer, sebastian A class used to add functionality to the the
 *         singleton ShoppingCart class.
 */
public class ShoppingCartAdapter {
	private static ShoppingCartAdapter instance = null;

	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private IMatDataHandler dataHandler;
	private ShoppingCart shoppingCart;

	private ShoppingCartAdapter() {
		dataHandler = IMatDataHandler.getInstance();
		shoppingCart = dataHandler.getShoppingCart();
	}

	public synchronized static ShoppingCartAdapter getInstance() {
		if (instance == null) {
			instance = new ShoppingCartAdapter();
		}
		return instance;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	/**
	 * Adds an item to the shopping cart. If that item already exists it's
	 * amount is increased.
	 * 
	 * @param item
	 *            the item to be added
	 */
	public void addItem(ShoppingItem item) {
		if (!this.contains(item)) {
			shoppingCart.addItem(item);
		} else {
			ShoppingItem temp = this.getItemReference(item);
			temp.setAmount(temp.getAmount() + item.getAmount());
		}
		pcs.firePropertyChange("Item added", null, null);
	}

	/**
	 * Adds a product to the list of shoppingItems in the cart
	 * 
	 * @param prod
	 *            the product to be added
	 */
	public void addProduct(Product prod) {
		if (!this.contains(prod)) {
			shoppingCart.addItem(new ShoppingItem(prod));
		} else {
			ShoppingItem temp = this.getItemReference(prod);
			temp.setAmount(temp.getAmount() + 1);
		}
		pcs.firePropertyChange("Product added", null, null);
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
			shoppingCart.addItem(new ShoppingItem(prod, amount));
		} else {
			ShoppingItem temp = this.getItemReference(prod);
			temp.setAmount(temp.getAmount() + amount);
		}
		pcs.firePropertyChange("Product added", null, null);
	}

	/**
	 * 
	 * @param cart
	 *            the shoppingcart/"handlingslista" to be added.
	 */
	public void addCart(ShoppingCart cart) {
		List<ShoppingItem> list = cart.getItems();
		Iterator i = list.iterator();
		while (i.hasNext()) {
			this.addItem((ShoppingItem) i.next());
		}
		pcs.firePropertyChange("Items added", null, null);

	}

	/**
	 * Adds a collection of shoppingitems to the shopping cart.
	 * 
	 * @param products
	 */
	public void addItems(List<ShoppingItem> shoppingitems) {
		if (shoppingitems != null && shoppingitems.size() > 0) {
			for (ShoppingItem si : shoppingitems) {
				addItem(si);
			}
			pcs.firePropertyChange("Products added", null, null);
		}
	}

	/**
	 * Adds a collection of products to the shopping cart.
	 * 
	 * @param products
	 */
	public void addProducts(List<Product> products) {
		for (Product p : products) {
			addProduct(p);
		}
		pcs.firePropertyChange("Products added", null, null);
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
		List<ShoppingItem> list = shoppingCart.getItems();

		for (ShoppingItem item : list) {
			if (item.getProduct().getName().equals(prod.getName())) {
				System.out.println("Does exist!");
				return true;
			}
		}

		return false;
	}

	public ShoppingItem getItemReference(ShoppingItem item) {
		List<ShoppingItem> list = shoppingCart.getItems();
		Iterator i = list.iterator();
		while (i.hasNext()) {
			ShoppingItem temp = (ShoppingItem) i.next();
			if (item.getProduct().equals(temp.getProduct())) {
				return temp;
			}
		}
		return null;
	}

	public ShoppingItem getItemReference(Product prod) {
		return getItemReference(new ShoppingItem(prod));
	}

	public int getNbrItems() {
		if (shoppingCart.getItems() != null
				|| shoppingCart.getItems().size() != 0) {
			return shoppingCart.getItems().size();
		} else {
			return 0;
		}
	}

	public void clear() {
		shoppingCart.clear();
		pcs.firePropertyChange("Cleared items", null, null);
	}

	public List<ShoppingItem> getItems() {
		return shoppingCart.getItems();
	}

	public double getTotal() {
		return shoppingCart.getTotal();
	}

	public void removeItem(int index) {
		shoppingCart.removeItem(index);
		pcs.firePropertyChange("Item removed", null, null);
	}

	public void removeItem(ShoppingItem item) {
		shoppingCart.removeItem(item);
		pcs.firePropertyChange("Item removed", null, null);

	}

	/**
	 * increases the amount of an item found in the shopping cart by 1
	 * 
	 * @param item
	 *            the item in question
	 */
	public void increaseItemAmount(ShoppingItem item) {
		this.increaseProductAmount(item.getProduct());
		pcs.firePropertyChange("Amount of item changed", null, null);
	}

	/**
	 * decreases the amount of an item found in the shopping cart by 1
	 * 
	 * @param item
	 *            the item in question
	 */
	public void decreaseItemAmount(ShoppingItem item) {
		this.decreaseProductAmount(item.getProduct());
		pcs.firePropertyChange("Amount of item changed", null, null);
	}

	/**
	 * increases the amount of an product found in the shopping cart by 1
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
		pcs.firePropertyChange("Amount of product changed", null, null);
	}

	/**
	 * decreases the amount of an product found in the shopping cart by 1
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
		pcs.firePropertyChange("Amount of product changed", null, null);
	}

	/**
	 * Returns the total amount of unique products in the shoppingcart
	 * 
	 * @return total amount
	 */
	public int getTotalProductsAmount() {
		return shoppingCart.getItems().size();
	}

	/**
	 * Adds a new listener to this model.
	 * 
	 * @param listener
	 */
	public void addListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Removes a listener from this model.
	 * 
	 * @param listener
	 */
	public void removeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	public void addShoppingList(ShoppingList list) {
		List<ShoppingItem> itemList = list.getItems();
		Iterator i = itemList.iterator();
		while (i.hasNext()) {
			this.addItem((ShoppingItem) i.next());
		}
	}
}