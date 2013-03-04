package ProductCategories;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import se.chalmers.ait.dat215.project.ProductCategory;

/**
 * A class to represent categories and their subcategories from <code>ProductCategory</code>.
 * This is a <strong>singelton</strong> class
 * @author Sebastian Blomberg
 * @see se.chalmers.ait.dat215.project.ProductCategory
 */
public class ProductCategories {
	private static Map<String, Map<ProductCategory, String>> categories = new HashMap<String, Map<ProductCategory, String>>(); 
	private static ProductCategories instance = null; 
	
	private ProductCategories() {
		instance = this;
		
		Map<ProductCategory, String> meatSubcategories = new HashMap<ProductCategory, String>();
		meatSubcategories.put(ProductCategory.MEAT, "N�TK�TT");
		categories.put("k�tt", meatSubcategories);
		
		Map<ProductCategory, String> fishSubcategories = new HashMap<ProductCategory, String>();
		fishSubcategories.put(ProductCategory.FISH, "SKALDJUR");
		categories.put("fisk", fishSubcategories);
		
		Map<ProductCategory, String> greensSubcategories = new HashMap<ProductCategory, String>();
		greensSubcategories.put(ProductCategory.BERRY, "B�R");
		greensSubcategories.put(ProductCategory.EXOTIC_FRUIT, "EXOTISKA FRUKTER");
		greensSubcategories.put(ProductCategory.FRUIT, "STENFRUKT");
		greensSubcategories.put(ProductCategory.MELONS, "MELONER");
		greensSubcategories.put(ProductCategory.VEGETABLE_FRUIT, "GR�NSAKSFRUKTER");
		greensSubcategories.put(ProductCategory.ROOT_VEGETABLE, "ROTFRUKTER");
		greensSubcategories.put(ProductCategory.POD, "BALJV�XTER");
		greensSubcategories.put(ProductCategory.CITRUS_FRUIT, "CITRUSFRUKTER");
		greensSubcategories.put(ProductCategory.CABBAGE, "K�L");
		categories.put("frukt & gr�nt", greensSubcategories);
		
		
		Map<ProductCategory, String> pantrySubcategories = new HashMap<ProductCategory, String>();
		pantrySubcategories.put(ProductCategory.SWEET, "GODIS");
		pantrySubcategories.put(ProductCategory.POTATO_RICE, "POTATIS & RIS");
		pantrySubcategories.put(ProductCategory.BREAD, "BR�D");
		pantrySubcategories.put(ProductCategory.FLOUR_SUGAR_SALT, "MJ�L");
		pantrySubcategories.put(ProductCategory.HERB, "�RTER");
		pantrySubcategories.put(ProductCategory.NUTS_AND_SEEDS, "N�TTER & FR�N");
		pantrySubcategories.put(ProductCategory.PASTA, "PASTA");
		categories.put("skafferi", pantrySubcategories);
		
		Map<ProductCategory, String> drinksSubcategories = new HashMap<ProductCategory, String>();
		drinksSubcategories.put(ProductCategory.HOT_DRINKS, "VARMA DRYCKER");
		drinksSubcategories.put(ProductCategory.COLD_DRINKS, "KALLA DRYCKER");
		categories.put("drycker", drinksSubcategories);
		
		Map<ProductCategory, String> dairySubcategories = new HashMap<ProductCategory, String>();
		dairySubcategories.put(ProductCategory.DAIRIES, "MJ�LK");
		categories.put("mejeri", dairySubcategories);
	}
	
	public static synchronized ProductCategories getInstance() {
		if(instance == null) {
			new ProductCategories();
		}
		return instance;
		
	}
	
	public String getCategory(ProductCategory subcategory) {
		for(Map.Entry<String, Map<ProductCategory, String>> entry: categories.entrySet()) {
			if(entry.getValue().containsKey(subcategory)) {
				return entry.getKey();
			}
		}
		return "K�tt";
	}
	
	public Set<String> getCategories() {
		return categories.keySet();
	}
	
	public Map<ProductCategory, String> getSubcategories(String category) {
		return categories.get(category);
	}
}
