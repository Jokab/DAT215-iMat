package ProductSearch;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import se.chalmers.ait.dat215.project.*;

public class ProductSearch {

	private String name;
	private List<Product> productList;

	/**
	 * Creates a ProductSearch object which searches for the name.
	 * 
	 * @param name
	 *            The product name, any result that is a substring of the
	 *            submitted name will be returned.
	 */
	public ProductSearch(String name) {
		this(name, null);
	}

	/**
	 * Creates a ProductSearch object which searches for the name and sorts it
	 * by the comparator.
	 * 
	 * @param name
	 *            The product name, any result that is a substring of the
	 *            submitted name will be returned.
	 * @param comparator
	 *            The comparator to be used.
	 */
	public ProductSearch(String name, Comparator<Product> comparator) {
		this.name = name;
		doSearch(name, comparator);
	}

	private void doSearch(String name, Comparator<Product> comparator) {
		List<Product> list;

		// If name is null, search for nothing instead of null.
		if (name == null) {
			list = IMatDataHandler.getInstance().findProducts("");
		} else {
			list = IMatDataHandler.getInstance().findProducts(name);
		}

		// No point in sorting a list that is less than two elements.
		if (list.size() < 2) {
			System.out.println("List is too small to sort.");
			this.productList = list;
		}

		if (comparator != null) {
			Collections.sort(list, comparator);
		}

		this.productList = list;
	}

	/**
	 * @return A list of the products sorted according to the filter.
	 */
	public List<Product> getProducts() {
		return this.productList;
	}

}
