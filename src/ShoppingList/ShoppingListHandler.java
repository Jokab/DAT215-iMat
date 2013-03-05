package ShoppingList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A singleton class for managing retrieving, deleting and other useful functionality
 * regarding ShoppingLists.
 * 
 * @author Jakob
 * 
 */
public enum ShoppingListHandler implements Serializable {

	INSTANCE;

	public static final String FILE_PATH = System.getProperty("user.home")
			+ "/.dat215/imat/shoppinglists.data";

	private ShoppingListHolder shoppingListHolder = new ShoppingListHolder();

	private ShoppingListHandler() {
		;
	}

	/**
	 * Reads the ShoppingListHolder and loads it into memory for future use.
	 * Must be called before stored ShoppingLists can be retrieved.
	 * 
	 */
	public void readLists() {
		File file = new File(FILE_PATH);
		if (!file.exists()) {
			writeLists();
		}

		try {
			FileInputStream fileIn = new FileInputStream(FILE_PATH);
			ObjectInputStream in = new ObjectInputStream(fileIn);

			shoppingListHolder = (ShoppingListHolder) in.readObject();
			in.close();
		} catch (ClassNotFoundException e) {
			Logger.getLogger(ShoppingListHandler.class.getName()).log(
					Level.SEVERE, null, e);
		} catch (IOException e) {
			Logger.getLogger(ShoppingListHandler.class.getName()).log(
					Level.SEVERE, null, e);
		}
	}

	/**
	 * Returns true if a list with the specified name exists.
	 * 
	 * @param name
	 *            The name to be checked for existence.
	 * @return
	 */
	public boolean listExists(String name) {
		if (shoppingListHolder != null) {
			for (ShoppingList list : shoppingListHolder.shoppingLists) {
				return name.equals(list.getName());
			}
		}

		return false;

	}

	/**
	 * Returns true if the specified ShoppingList exists.
	 * 
	 * @param list
	 *            The ShoppingList to be checked for existence.
	 * @return
	 */
	public boolean listExists(ShoppingList list) {
		if (shoppingListHolder != null) {
			return shoppingListHolder.shoppingLists.contains(list);
		}

		return false;
	}

	/**
	 * Writes the lists that have been added to file. Cannot be called unless
	 * the holder has been read.
	 * 
	 */
	public void writeLists() {
		// if (shoppingListHolder == null) {
		// // If we haven't got anything to save, no point in saving.
		// return;
		// }

		try {
			File file = new File(FILE_PATH);
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(file));

			out.writeObject(shoppingListHolder);
			out.
			flush();
			out.close();

		} catch (IOException e) {
			Logger.getLogger(ShoppingListHandler.class.getName()).log(
					Level.SEVERE, null, e);
		}
	}

	/**
	 * Returns a Set&lt;ShoppingList&gt; containing all the available
	 * ShoppingLists.
	 * 
	 * @return A Set&lt;ShoppingList&gt; containing all the available
	 *         ShoppingLists.
	 */
	public Set<ShoppingList> getShoppingLists() {
		if (shoppingListHolder != null) {
			return shoppingListHolder.shoppingLists;
		}

		return null;
	}

	public void addShoppingList(ShoppingList list) {
		if (shoppingListHolder != null) {
			shoppingListHolder.shoppingLists.add(list);
		}
	}

	/**
	 * Deletes the list with the specified name. Returns true if successful,
	 * else false.
	 * 
	 * @param name
	 *            The name of the list to be deleted.
	 * @return True if deletion was successful, else false.
	 */
	public boolean removeShoppingList(String name) {
		for (ShoppingList list : shoppingListHolder.shoppingLists) {
			if (list.getName().equals(name)) {
				shoppingListHolder.shoppingLists.remove(list);
				return true;
			}
		}
		

		return false;
	}
	
	public ShoppingList getList(String name) {
		for(ShoppingList list : getShoppingLists()) {
			if(list.getName().equals(name)) {
				return list;
			}
		}
		
		return null;
	}

	private class ShoppingListHolder implements Serializable {

		private Set<ShoppingList> shoppingLists;

		ShoppingListHolder() {
			shoppingLists = new HashSet<ShoppingList>();
		}
	}

}
