package ProductSearch;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ProductCategories.ProductCategories;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ProductCategory;

/**
 * A class to filter products in iMatDatahandler.
 * @author Sebastian Blomberg
 *
 */
public class ProductFilter {

	/**
	 * Returns a list with all products belonging to the given category
	 * @param category
	 * @return list of products
	 */
	public static List<Product> getProductByCategory(String category) {
		Map<ProductCategory, String> subcats = ProductCategories.getInstance().getSubcategories(category);
		List<Product> products = new LinkedList<Product>();
		
		for(ProductCategory subcat : subcats.keySet()) {
			products.addAll(IMatDataHandler.getInstance().getProducts(subcat));
		}
		
		return products;
	}
	
	/**
	 * Returns a list with all products belonging to the given category sorted in the specified order
	 * @param category
	 * @param filter
	 * @return
	 */
	public static List<Product> getProductByCategory(String category, Comparator<Product> filter) {
		Map<ProductCategory, String> subcats = ProductCategories.getInstance().getSubcategories(category);
		List<Product> products = new LinkedList<Product>();
		
		for(ProductCategory subcat : subcats.keySet()) {
			products.addAll(IMatDataHandler.getInstance().getProducts(subcat));
		}
		
		Collections.sort(products, filter);
		
		return products;
	}
	
	/**
	 * Returns a list with all products belonging to the giver subcategory
	 * @param subcat
	 * @return
	 */
	public static List<Product> getProductBySubcategory(ProductCategory subcat) {
		return IMatDataHandler.getInstance().getProducts(subcat);
	}
	
	public static List<Product> getProductBySubcategory(ProductCategory subcat, Comparator<Product> filter) {
		List<Product> results = IMatDataHandler.getInstance().getProducts(subcat);
		Collections.sort(results, filter);
		return results;
	}
}
