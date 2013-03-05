package order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.ShoppingItem;

public class OrderUtil {

	private static final DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	public static double getTotal(Order order) {
		double sum = 0;
		for (ShoppingItem item : order.getItems()) {
			sum += item.getProduct().getPrice();
		}

		return sum;
	}

	public static String convertDateToFormattedString(Date date) {
		return df.format(date);
	}

}
