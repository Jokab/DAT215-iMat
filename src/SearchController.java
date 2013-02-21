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
			System.out.println("action performed");
			updateAutoComplete(bar.getSearchField());
		}
	}

	private void updateAutoComplete(JTextField field) {

		String input = bar.getSearchField().getText();
		ProductSearch ps = new ProductSearch(input, null);
		List<Product> list = ps.getProducts();
		JLabel[][] arr = bar.getLabelArr();

		for (Product p : list) {
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					if (j == 1) {
						arr[i][j].setText(p.getName());
					}
					if (j == 2) {
						arr[i][j].setText(Double.toString(p.getPrice()));
					}
				}
			}
		}
		bar.getPanel().setVisible(true);
	}
}
