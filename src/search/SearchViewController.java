package search;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import se.chalmers.ait.dat215.project.Product;

import ProductSearch.*;
import ProductSearch.ProductSearch;

import core.MainController;
import core.ViewController;

public class SearchViewController implements ViewController {
	private SearchView view;
	private ProductSearch search;
	private String searchString;
	private final MainController mainController;
	
	private SearchFilterOption[] comboBoxValues ={new SearchFilterOption(new OrderByNameAscending(), "Namn stigande"), 
													new SearchFilterOption(new OrderByNameDescending(), "Namn fallande"),
													new SearchFilterOption(new OrderByPriceAscending(), "Pris stigande"),
													new SearchFilterOption(new OrderByPriceDescending(), "Pris fallande"),};
	
	public SearchViewController (String searchString, MainController mainController) {
		this(searchString, new OrderByNameAscending(), mainController);
	}
	
	public SearchViewController(String searchString, Comparator<Product> filter, MainController mainController) {
		this.searchString = searchString;
		search = new ProductSearch(searchString, 1000, filter);
		this.mainController = mainController;
		
		view = new SearchView(comboBoxValues);
		view.setHeaderLabel(searchString);
		view.renderList(search.getProducts());
		
		for(int i = 0; i < comboBoxValues.length; i++) {
			if(filter.getClass() == comboBoxValues[i].getFilter().getClass()) {
				view.setSelectedIndexComboBox(i);
				break;
			}
		}
		view.addComboBoxListener(new ComboBoxListener());
	}
	
	@Override
	public JPanel getView() {
		return view;
	}
	
	private class ComboBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			SearchFilterOption filter = (SearchFilterOption) ((JComboBox)e.getSource()).getSelectedItem();			
			mainController.initSearchViewController(searchString, filter.getFilter());
		}
		
	}

}
