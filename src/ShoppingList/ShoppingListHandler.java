package ShoppingList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.List;

public class ShoppingListHandler {

	private final static String FILE_PATH = System.getProperty("user.home")
			+ "/.dat215/imat/shoppinglists/";

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

		return dir.listFiles();
				
//				new FilenameFilter() {
//			public boolean accept(File dir, String fileName) {
//				return fileName.endsWith(".txt");
//			}
//		});
	}

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
	
	public boolean deleteFile(String name) {
		try {
			File file = new File(FILE_PATH + name + ".txt");
			if(!file.exists()) {
				throw new IllegalArgumentException("File does not exist.");
			}
			
			file.delete();
			return true;
		} catch(Exception e) { }
		return false;
	}

}
