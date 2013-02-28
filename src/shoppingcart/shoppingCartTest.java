package shoppingcart;

import java.util.Iterator;
import java.util.List;


import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingItem;

public class shoppingCartTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IMatDataHandler dataHandler = IMatDataHandler.getInstance();
		List<Product> productList = dataHandler.findProducts("a");
		if(productList == null){
			System.out.println("NULL");
		}
		if(productList.isEmpty()){
			System.out.println("Empty");
		}
		Iterator i = productList.iterator();
		Product temp1 = (Product) i.next();
		
		ShoppingItem temp2 = new ShoppingItem(temp1);
		ShoppingCartAdapter cart = ShoppingCartAdapter.getInstance();
		
		System.out.println("Starting test 1: adding items to the cart");
		cart.addItem(new ShoppingItem(temp1));
		cart.addItem(temp2);
		List<ShoppingItem> tempList = cart.getItems();
		System.out.println(tempList.size());
		dataHandler.getShoppingCart().addItem(temp2);
		System.out.println(tempList.size());
		System.out.println("Done, print should be 1; 2");
		
		System.out.println("Starting test 2: clearing cart");
		cart.clear();
		System.out.println(tempList.size());
		System.out.println("Done, print should be 0");
		
		System.out.println("Starting test 3: adding items and checking amount");
		cart.addItem(new ShoppingItem(temp1, 2));
		Iterator it = tempList.iterator();
		ShoppingItem temp3 = (ShoppingItem) it.next();
		System.out.println(temp3.getAmount());
		cart.addProduct(temp1, 3);
		System.out.println(temp3.getAmount());
		cart.addProduct(temp1, 3);
		System.out.println(temp3.getAmount());
		cart.increaseProductAmount(temp1);
		System.out.println(temp3.getAmount());
		cart.decreaseItemAmount(temp2);
		System.out.println(temp3.getAmount());
		System.out.println("Done, print should be 2;5;8;9;8");
	}

}
