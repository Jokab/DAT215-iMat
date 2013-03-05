package ProductSearch;

import java.util.Comparator;

import se.chalmers.ait.dat215.project.Product;

public class SearchFilterOption {
	private final Comparator<Product> filter;
	private final String name;
	
	public SearchFilterOption(Comparator<Product> filter, String name) {
		this.filter = filter;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public Comparator<Product> getFilter() {
		return filter;
	}
}
