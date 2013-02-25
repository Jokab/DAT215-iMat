package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import core.HeaderView;

import ProductCategories.ProductCategories;

public class MenuController {
	private HeaderView view;
	public MenuController(HeaderView headerView) {
		this.view = headerView;
		MenuPanel menu = new MenuPanel();
		
		ProductCategories categories = ProductCategories.getInstance();
		
		for(final String s : categories.getCategories()) {
			MenuButton button = new MenuButton(s);
			menu.addButton(button);
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					view.setSubmenuPanel(createSubmenu(s));
				}
			});
		}
		view.setMenuPanel(menu);
	}
	
	private SubmenuPanel createSubmenu(String category) {
		ProductCategories categories = ProductCategories.getInstance();
		SubmenuPanel submenu = new SubmenuPanel();
		
		for(final String s : categories.getSubcategories(category).values()) {
			SubmenuButton button = new SubmenuButton(s);
			submenu.addButton(button);
		}
		
		return submenu;
		
	}
	
}
