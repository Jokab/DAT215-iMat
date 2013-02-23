package ShoppingList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.ShoppingItem;
import se.chalmers.ait.dat215.project.util.IOUtilities;


public class ShoppingList {
	
	private Order order;
	private String name;
	
	public ShoppingList(Order order, String name) {
		this.order = order;
		this.name = name;
		writeToFile();
		
	}
	
	public double getTotal() {
		if(order.getItems() != null || order.getItems().size() != 0) {
			double total = 0;
			
			for(ShoppingItem item : order.getItems()) 
				total += item.getProduct().getPrice() * item.getAmount();	
			
			return total;
		} else 
			return 0;
		
	}
	
	public int getNbrItems() {
		if(order.getItems() != null || order.getItems().size() != 0) {
			return order.getItems().size();
		} else {
			return 0;
		}
		
	}
	
	public String getName() {
		return this.name;
	}
	
	private void writeToFile() {
		
		try {
			File file = new File(System.getProperty("user.home") + "/.dat215/imat/shoppinglists.txt");
			System.out.println(file.getAbsoluteFile());
			BufferedWriter out;
			if(!(file.exists())) {
				out = new BufferedWriter(new FileWriter(file));
			} else {
				out = new BufferedWriter(new FileWriter(file,true));
			}
				
			out.write(this.name);
			out.newLine();
			out.close();
		} catch (IOException e) {
			System.out.println("File does not exist?");
		}
	}
}
