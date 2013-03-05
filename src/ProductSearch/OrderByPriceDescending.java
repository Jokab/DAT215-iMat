package ProductSearch;
import java.util.Comparator;

import se.chalmers.ait.dat215.project.Product;


public class OrderByPriceDescending implements Comparator<Product> {
	@Override
	public int compare(Product o1, Product o2) {	
		if(o1.getPrice() - o2.getPrice() > 0) {
			return -1;
		}
		if(o1.getPrice() - o2.getPrice() < 0) {
			return 11;
		} else {
			return 0;
		}
	} 
}
