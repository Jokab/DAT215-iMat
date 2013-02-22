package ProductSearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import se.chalmers.ait.dat215.project.*;

public class ProductSearch {

	private String name;
	private List<Product> productList;
	private int results;

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

	public ProductSearch(String name, int results) {
		this(name, null, results);
	}

	/**
	 * Creates a ProductSearch object which searches for the name and sorts it
	 * by the comparator, then cuts the list to the size of results.
	 * 
	 * @param name
	 *            The product name, any result that is a substring of the
	 *            submitted name will be returned.
	 * @param comparator
	 *            The comparator to be used.
	 * @param results
	 *            The number of search results to be returned.
	 */
	public ProductSearch(String name, Comparator<Product> comparator,
			int results) {
		this.name = name;
		doSearch(name, comparator);
		if(results >= 2) {
			productList = productList.subList(0, productList.size());
		}
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
		List<Product> list = new ArrayList<Product>(results);

		// If name is null, search for nothing instead of null.
		if (name == null) {
			list = IMatDataHandler.getInstance().findProducts("");
		} else {
			list = IMatDataHandler.getInstance().findProducts(name);
		}

		// No point in sorting a list that is less than two elements.
		if (list.size() < 2) {
			this.productList = list;
		}

		if (comparator != null) {

			Collections.sort(list, comparator);
		}

		this.productList = list;
	}

	/**
	 * Returns a list of the products sorted according to the filter(s).
	 * 
	 * @return A list of the products sorted according to the filter(s).
	 */
	public List<Product> getProducts() {
		return this.productList;
	}

}
