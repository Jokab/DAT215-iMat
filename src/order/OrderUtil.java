package order;

import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.ShoppingItem;

public class OrderUtil {

	
	public static double getTotal(Order order) {
		double sum = 0;
		for(ShoppingItem item : order.getItems()) {
			sum += item.getProduct().getPrice();
		}
		
		return sum;
	}
	
	

}
