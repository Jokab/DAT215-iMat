import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;

import se.chalmers.ait.dat215.project.Product;

import ProductSearch.ProductSearch;

public class SearchController {

	private final SearchBar bar;
	private final int MAX_RESULTS = 4;

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
			// don't need to listen to this
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			bar.getPanel().setVisible(false);
		}
	}

	private class BarActionPerformedListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			updateAutoCompletePanel(bar.getSearchField());
			bar.getPanel().setVisible(true);
		}
	}

	public void updateAutoCompletePanel(JTextField field) {

		bar.getPanel().removeAll();

		String input = bar.getSearchField().getText();
		ProductSearch ps = new ProductSearch(input, null, MAX_RESULTS);
		List<Product> list = ps.getProducts();

		for (Product p : list) {
			System.out.println(p.getName());
			AutoCompleteProductsPanel productPanel = new AutoCompleteProductsPanel(
					p);
			bar.getPanel().add(productPanel);
		}
		if(list.size() > 0) {
			Dimension d = new Dimension((int) new AutoCompleteProductsPanel(list.get(0)).getSize().getWidth(),
					(int) (new AutoCompleteProductsPanel(list.get(0)).getSize()
							.getHeight() * list.size()));
			System.out.println(new AutoCompleteProductsPanel(list.get(0)).getSize().getWidth());
		}
		
		
		
		bar.getPanel().revalidate();
		bar.getPanel().repaint();

	}
}
