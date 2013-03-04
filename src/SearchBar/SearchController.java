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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import core.MainController;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;

import ProductCategories.ProductCategories;
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
	private final int MAX_RESULTS = 6;
	private final Comparator<Product> DEFAULT_COMPARATOR = null;
	private final AutoCompleteContainer resultContainer;
	private final MainController mainController;
	/**
	 * @wbp.parser.entryPoint
	 */
	public SearchController(final SearchBar bar, AutoCompleteContainer resultContainer, MainController mainController) {
		this.bar = bar;
		this.resultContainer = resultContainer;
		this.mainController = mainController;
		bar.addFocusGainedListener(new BarFocusListener());
		bar.addBarActionPerformedListener(new BarActionPerformedListener());
		
		this.bar.getSearchField().getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				  updateAutoCompletePanel(SearchController.this.bar.getSearchField());
			  }
			  public void removeUpdate(DocumentEvent e) {
				  updateAutoCompletePanel(SearchController.this.bar.getSearchField());
			  }
			  public void insertUpdate(DocumentEvent e) {
				  updateAutoCompletePanel(SearchController.this.bar.getSearchField());
			  }
		});
	}

	private class BarFocusListener implements FocusListener {

		@Override
		public void focusGained(FocusEvent arg0) {
			bar.getSearchField().setText("");
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			resultContainer.setVisible(false);
			bar.getSearchField().setText("Sök produkt...");
		}
	}

	private class BarActionPerformedListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			updateAutoCompletePanel(bar.getSearchField());
			resultContainer.setVisible(true);
		}
	}
	
	private class BarMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent evt) {
			if(evt.getSource().getClass().equals(AutoCompleteProductsPanel.class)) {
				Product p = ((AutoCompleteProductsPanel)evt.getSource()).getProduct();
				mainController.initProductListController(ProductCategories.getInstance().getCategory(p.getCategory()), p.getCategory());
				resultContainer.setVisible(false);
				bar.getSearchField().setText("Sök produkt...");
				bar.getSearchField().setFocusable(false);
				bar.getSearchField().setFocusable(true);
			}
		}

		@Override
		public void mouseEntered(MouseEvent evt) {
			if(evt.getSource().getClass().equals(AutoCompleteProductsPanel.class)) {
				AutoCompleteProductsPanel panel = (AutoCompleteProductsPanel)evt.getSource();
	
				if(panel.contains(evt.getPoint())) {
					panel.setBackground(new Color(250, 250, 250));
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
		
		resultContainer.removeAll();
		String input = bar.getSearchField().getText();
		ProductSearch ps = new ProductSearch(input, MAX_RESULTS, null);
		List<Product> list = ps.getProducts();
		if(list.size() != 0) {
			for (Product p : list) {
				AutoCompleteProductsPanel productPanel = new AutoCompleteProductsPanel(p);
				productPanel.addAutoCompMouseListener(new BarMouseListener());
				resultContainer.add(productPanel);
			}
			resultContainer.setVisible(true);
		} else {
			resultContainer.setVisible(false);
		}
		resultContainer.revalidate();
	}
	
	public boolean panelHasContent() {
		Component[] components = resultContainer.getComponents();
		if(components != null) {
			return true;
		} else {
			return false;
		}
	}
	
}
