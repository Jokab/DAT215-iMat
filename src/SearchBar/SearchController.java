package SearchBar;



import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Comparator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
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
	private final Comparator<Product> DEFAULT_COMPARATOR = null;

	/**
	 * @wbp.parser.entryPoint
	 */
	public SearchController(SearchBar bar) {
		this.bar = bar;
		bar.addFocusGainedListener(new BarFocusListener());
		bar.addBarActionPerformedListener(new BarActionPerformedListener());
		bar.addMouseListener(new BarMouseListener());
	}

	private class BarFocusListener implements FocusListener {

		@Override
		public void focusGained(FocusEvent arg0) {
			bar.getSearchField().setText("");
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			bar.getPanel().setVisible(false);
			bar.getSearchField().setText("S�k produkt...");
		}
	}

	private class BarActionPerformedListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			updateAutoCompletePanel(bar.getSearchField());
			bar.getPanel().setVisible(true);
		}
	}
	
	private class BarMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) { }

		@Override
		public void mouseEntered(MouseEvent evt) {
			if(evt.getSource().getClass().equals(AutoCompleteProductsPanel.class)) {
				AutoCompleteProductsPanel panel = (AutoCompleteProductsPanel)evt.getSource();
	
				if(panel.contains(evt.getPoint())) {
					panel.setBackground(Color.BLUE);
				}
			}
		}

		@Override
		public void mouseExited(MouseEvent evt) {
			if(evt.getSource().getClass().equals(AutoCompleteProductsPanel.class)) {
				AutoCompleteProductsPanel panel = (AutoCompleteProductsPanel)evt.getSource();
				panel.setBackground(Color.white);
			}
		}

		@Override
		public void mousePressed(MouseEvent arg0) {	}

		@Override
		public void mouseReleased(MouseEvent arg0) {}
		
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
		ProductSearch ps = new ProductSearch(input, MAX_RESULTS, null);
		List<Product> list = ps.getProducts();
		
		if(list.size() != 0) {
			System.out.println(list.size());
			for (Product p : list) {
				AutoCompleteProductsPanel productPanel = new AutoCompleteProductsPanel(p);
				productPanel.addAutoCompMouseListener(new BarMouseListener());
				bar.getPanel().add(productPanel);
			}		
		} else {
			bar.getPanel().removeAll();
			bar.getPanel().revalidate();
			bar.getPanel().setVisible(false);
		}
	}
	
	public boolean panelHasContent() {
		Component[] components = bar.getPanel().getComponents();
		if(components != null) {
			return true;
		} else {
			return false;
		}
	}
	
}
