package SearchBar;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;

import ProductSearch.ProductSearch;

/**
 * A controller for handling input to the SearchBar and displaying the results.
 * 
 * @author Jakob
 *
 */
public class SearchController {

	private IMatDataHandler dh = IMatDataHandler.getInstance();
	private final SearchBar bar;
	private final int MAX_RESULTS = 3;

	/**
	 * @wbp.parser.entryPoint
	 */
	public SearchController(SearchBar bar) {
		this.bar = bar;
		bar.addFocusGainedListener(new BarFocusListener());
		bar.addBarActionPerformedListener(new BarActionPerformedListener());
	}

	private class BarFocusListener implements FocusListener {

		@Override
		public void focusGained(FocusEvent arg0) {
			bar.getSearchField().setText("");
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			bar.getPanel().setVisible(false);
			bar.getSearchField().setText("Sök produkt...");
		}
	}

	private class BarActionPerformedListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			updateAutoCompletePanel(bar.getSearchField());
			bar.getPanel().setVisible(true);
		}
	}

	/**
	 * Updates the panel attached to the SearchBar to reflect the list that
	 * was returned. Takes a maximum of 3 list items.
	 * 
	 * @param field The textfield to be updated.
	 */
	public void updateAutoCompletePanel(JTextField field) {
		
		bar.getPanel().removeAll();

		String input = bar.getSearchField().getText();
		ProductSearch ps = new ProductSearch(input, null, MAX_RESULTS);
		List<Product> list = ps.getProducts();
		System.out.println(list.size());
		if(list.size() != 0) {
			System.out.println(list.size());
			for (Product p : list) {
				AutoCompleteProductsPanel productPanel = new AutoCompleteProductsPanel(p);
				bar.getPanel().add(productPanel);
			}		
		} else {
			bar.getPanel().removeAll();
			bar.getPanel().setVisible(false);
			
//			bar.getPanel().revalidate();
		}
		
//		if(list.size() > 0) {
//			Dimension d = new Dimension((int) new AutoCompleteProductsPanel(list.get(0)).getSize().getWidth(),
//					(int) (new AutoCompleteProductsPanel(list.get(0)).getSize()
//							.getHeight() * list.size()));
//		}
		
		
		
	}
}
