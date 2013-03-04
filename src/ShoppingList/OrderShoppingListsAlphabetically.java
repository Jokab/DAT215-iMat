package ShoppingList;

import java.util.Comparator;

public class OrderShoppingListsAlphabetically implements
		Comparator<ShoppingList> {

	@Override
	public int compare(ShoppingList o1, ShoppingList o2) {
		 return o1.getName().compareTo(o2.getName());
	}

}
