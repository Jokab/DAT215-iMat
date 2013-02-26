package ShoppingList;
import java.util.List;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingItem;


public class ShoppingListTest {
	
	public static void main(String[] args) {
		IMatDataHandler dm = IMatDataHandler.getInstance();
		ShoppingCart cart = dm.getShoppingCart();
		Product p = new Product();
		p.setName("Apa");
		p.setPrice(20);
		
		Product p1 = new Product();
		p1.setName("Aphjärna");
		p1.setPrice(15);
		
		ShoppingItem item1 = new ShoppingItem(p, 5);
		cart.addItem(item1);
		ShoppingItem item2 = new ShoppingItem(p1, 10);
		cart.addItem(item2);
		
		
		
		dm.placeOrder();
		List<Order> orderList = dm.getOrders();
		for(ShoppingItem item : orderList.get(0).getItems()) {
			System.out.println(item.getProduct().getName());
		}
		
		ShoppingList list = new ShoppingList(orderList.get(0), "Vardag");
		ShoppingList list1 = new ShoppingList(orderList.get(0), "Apa");
		ShoppingList list2 = new ShoppingList(orderList.get(0), "hejh");
		
		System.out.println(list.getTotal() + "\tExpected: 250");
		System.out.println(list.getNbrItems()+ "\tExpected: 2");
	}
}
