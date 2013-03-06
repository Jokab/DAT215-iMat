package SearchBar;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Comparator;
import java.util.List;

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
	private final SearchBar searchBar;
	private final int MAX_RESULTS = 6;
	private final Comparator<Product> DEFAULT_COMPARATOR = null;
	private final AutoCompleteContainer resultContainer;
	private final MainController mainController;

	private String lastSearchString;

	private final AutoCompleteResultButton autoCompleteButton = new AutoCompleteResultButton();

	/**
	 * @wbp.parser.entryPoint
	 */
	public SearchController(SearchBar bar,
			final AutoCompleteContainer resultContainer,
			MainController mainController) {
		this.searchBar = bar;
		this.resultContainer = resultContainer;
		this.mainController = mainController;
		bar.addFocusGainedListener(new BarFocusListener());
		bar.addBarActionPerformedListener(new BarActionPerformedListener());

		SearchButtonListener l = new SearchButtonListener();
		autoCompleteButton.addActionListener(l);
		bar.getSearchField().addActionListener(l);
		bar.addSearchButtonListener(l);
		bar.getSearchField().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					resultContainer.setVisible(false);
				}
			}
		});

		Toolkit.getDefaultToolkit().addAWTEventListener(new OutsideClickListener(), AWTEvent.MOUSE_EVENT_MASK);

		this.searchBar.getSearchField().getDocument()
				.addDocumentListener(new DocumentListener() {
					@Override
					public void changedUpdate(DocumentEvent e) {
						update();
					}

					@Override
					public void removeUpdate(DocumentEvent e) {
						update();
					}

					@Override
					public void insertUpdate(DocumentEvent e) {
						update();
					}

					public void update() {
						if (searchBar.getSearchField().getText() != null
								&& !searchBar.getSearchField().getText()
								.isEmpty())
							updateAutoCompletePanel(SearchController.this.searchBar
									.getSearchField());
					}
				});
	}
	
	private class BarFocusListener implements FocusListener {

		@Override
		public void focusGained(FocusEvent arg0) {
			searchBar.getSearchField().setText("");
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			resultContainer.setVisible(false);
		}
	}

	private class BarActionPerformedListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			updateAutoCompletePanel(searchBar.getSearchField());
			resultContainer.setVisible(true);
		}
	}

	private class BarMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent evt) {
			if (evt.getSource().getClass()
					.equals(AutoCompleteProductsPanel.class)) {
				Product p = ((AutoCompleteProductsPanel) evt.getSource())
						.getProduct();
				mainController.initProductListController(ProductCategories.getInstance().getCategory(p.getCategory()), 
						p.getCategory(), p);
				resultContainer.setVisible(false);
				lastSearchString = searchBar.getSearchField().getText();
				searchBar.getSearchField().setFocusable(false);
				searchBar.getSearchField().setFocusable(true);
			}
		}

		@Override
		public void mouseEntered(MouseEvent evt) {
			if (evt.getSource().getClass()
					.equals(AutoCompleteProductsPanel.class)) {
				AutoCompleteProductsPanel panel = (AutoCompleteProductsPanel) evt
						.getSource();

				if (panel.contains(evt.getPoint())) {
					panel.setBackground(new Color(250, 250, 250));
				}
			}
		}

		@Override
		public void mouseExited(MouseEvent evt) {
			if (evt.getSource().getClass()
					.equals(AutoCompleteProductsPanel.class)) {
				AutoCompleteProductsPanel panel = (AutoCompleteProductsPanel) evt
						.getSource();
				panel.setBackground(Color.white);
			}
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
		}

	}
	
	private class OutsideClickListener implements AWTEventListener {
		public void eventDispatched(AWTEvent event) {
			
			if (event.getClass().equals(MouseEvent.class)) {
				MouseEvent evt = (MouseEvent) event;
				if (evt.getID() == MouseEvent.MOUSE_CLICKED
						&& (!searchBar.contains(evt.getPoint()) && !resultContainer.contains(evt
								.getPoint()))
						&& !autoCompleteButton.contains(evt.getPoint())) {
					resultContainer.setVisible(false);
					searchBar.getSearchField().setText("Sök efter produkt...");
					searchBar.getSearchField().setFocusable(false);
					searchBar.getSearchField().setFocusable(true);
				}
			}
			
		}
	}

	private class SearchButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mainController.initSearchViewController(lastSearchString);
			searchBar.getSearchField().setFocusable(false);
			searchBar.getSearchField().setFocusable(true);
		}

	}

	/**
	 * Updates the panel attached to the SearchBar to reflect the list that was
	 * returned. Takes a maximum of 3 list items.
	 * 
	 * @param field
	 *            The textfield to be updated.
	 */
	public void updateAutoCompletePanel(JTextField field) {

		resultContainer.removeAll();
		lastSearchString = searchBar.getSearchField().getText();
		ProductSearch ps = new ProductSearch(lastSearchString, MAX_RESULTS,
				null);
		List<Product> list = ps.getProducts();
		if (list.size() != 0) {
			for (Product p : list) {
				AutoCompleteProductsPanel productPanel = new AutoCompleteProductsPanel(
						p);
				productPanel.addAutoCompMouseListener(new BarMouseListener());
				resultContainer.add(productPanel);
			}
			resultContainer.add(autoCompleteButton);
			resultContainer.setVisible(true);
		} else {
			resultContainer.setVisible(false);
		}
		resultContainer.repaint();
		resultContainer.revalidate();
	}

	public boolean panelHasContent() {
		Component[] components = resultContainer.getComponents();
		if (components != null) {
			return true;
		} else {
			return false;
		}
	}

}
