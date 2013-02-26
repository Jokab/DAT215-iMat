package ShoppingList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * A class for managing retreiving, deleting and other useful functionality
 * regarding ShoppingLists.
 * 
 * @author Jakob
 *
 */
public class ShoppingListHandler {

	
	
	private final static String FILE_PATH = System.getProperty("user.home")
			+ "/.dat215/imat/shoppinglists/";

	/**
	 * Returns a List&lt;ShoppingList&gt; containing all the available ShoppingLists. 
	 * 
	 * @return A List containing all the available ShoppingLists. 
	 */
	public List<ShoppingList> getShoppingLists() {
		try {
			File[] files = findFiles();
			List<ShoppingList> list = new LinkedList<ShoppingList>();

			for (int i = 0; i < files.length; i++) {
				list.add(readFile(files[i].getName()));
			}

			return list;
		} catch (Exception e) {
			System.out.println("cannot read files...");
		}

		return null;
	}

	private File[] findFiles() {
		System.out.println("finding files...");
		File dir = new File(FILE_PATH);

		return dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String fileName) {
				return fileName.endsWith(".txt");
			}
		});
	}

	/**
	 * Reads and returns the ShoppingList with the specified name.
	 * 
	 * @param name The name of the ShoppingList to be retreived.
	 * @return The ShoppingList with the specified name.
	 */
	public ShoppingList readFile(String name) {
		ShoppingList list = null;
		System.out.println(FILE_PATH + name);
		try {
			FileInputStream fileIn = new FileInputStream(FILE_PATH + name);
			ObjectInputStream in = new ObjectInputStream(fileIn);

			list = (ShoppingList) in.readObject();
			in.close();
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found.");
		} catch (IOException e) {
			System.out.println("not found");
		}

		return list;
	}

	/**
	 * Deletes the list with the specified name. Returns true if successful, else false.
	 * 
	 * @param name The name of the list to be deleted.
	 * @return True if deletion was successful, else false.
	 */
	public boolean deleteList(String name) {
		try {
			File file = new File(FILE_PATH + name + ".txt");
			if (!file.exists()) {
				throw new IllegalArgumentException("File does not exist.");
			}

			file.delete();
			return true;
		} catch (Exception e) {
		}
		
		return false;
	}
	
	/**
	 * Returns true if a list with the specified name exists.
	 * 
	 * @param name The name to be checked for existence.
	 * @return
	 */
	public boolean listExists(String name) {
		for(ShoppingList list : getShoppingLists()) {
			return name.equals(list.getName());
		}
		return false;
	}
	
	/**
	 * Returns true if the specified ShoppingList exists.
	 * 
	 * @param list The ShoppingList to be checked for existence.
	 * @return
	 */
	public boolean listExists(ShoppingList list) {
		return getShoppingLists().contains(list);
	}

}
