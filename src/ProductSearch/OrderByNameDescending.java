package ProductSearch;
import java.util.Comparator;

import se.chalmers.ait.dat215.project.Product;


public class OrderByNameDescending implements Comparator<Product>{

	@Override
	public int compare(Product o1, Product o2) {	
		return -(o1.getName().compareTo(o2.getName()));
	} 
		 
}
