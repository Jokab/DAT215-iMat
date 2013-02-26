package menu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map.Entry;

import se.chalmers.ait.dat215.project.ProductCategory;

import core.HeaderView;
import core.MainController;

import ProductCategories.ProductCategories;
/**
 * Controller for the menu
 * @author Sebastian Blomberg
 *
 */
public class MenuController {
	private HeaderView view;
	private MainController controller;
	public MenuController(HeaderView headerView, MainController mainController) {
		this.view = headerView;
		this.controller = mainController;
		MenuPanel menu = new MenuPanel();
		
		ProductCategories categories = ProductCategories.getInstance();
		
		for(final String s : categories.getCategories()) {
			final MenuButton button = new MenuButton(s);
			menu.addButton(button);
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					controller.initProductListController(s);
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					view.setSubmenuPanel(createSubmenu(s));
					button.toggle();
				}
				@Override
				public void mouseExited(MouseEvent e) {
					button.toggle();
				}
			});
		}
		view.setMenuPanel(menu);
	}
	
	private SubmenuPanel createSubmenu(String category) {
		ProductCategories categories = ProductCategories.getInstance();
		SubmenuPanel submenu = new SubmenuPanel();
		
		for(final Entry<ProductCategory, String> entry : categories.getSubcategories(category).entrySet()) {
			final SubmenuButton button = new SubmenuButton(entry.getValue());
			submenu.addButton(button);
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					controller.initProductListController(entry.getValue(), entry.getKey());
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					button.toggle();
				}
				@Override
				public void mouseExited(MouseEvent e) {
					button.toggle();
				}
			});
		}
		
		return submenu;
		
	}
	
}
