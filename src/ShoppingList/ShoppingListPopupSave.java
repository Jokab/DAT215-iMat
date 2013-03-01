package ShoppingList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Set;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;
import java.awt.GridLayout;

import java.beans.PropertyChangeSupport;

public class ShoppingListPopupSave extends JPanel {

	private ShoppingListHandler handler = ShoppingListHandler.INSTANCE;
	private Set<ShoppingList> lists;
	private final JLabel lblAngeNamn = new JLabel("Ange namn:");
	private final JButton cancelButton = new JButton("Avbryt");
	private final JButton saveButton = new JButton("Spara");
	private String[] listNames;
	private final JScrollPane scrollPane = new JScrollPane();
	private final JPanel listPanel = new JPanel();
	private final JLabel chooseListLabel = new JLabel("Välj en lista att spara i:");
	private final Color SELECTED_BG_COLOR = new Color(177,211,114);
	private final Color SELECTED_TEXT_COLOR = Color.white;
	private final Color NORMAL_BG_COLOR = Color.WHITE;
	private final Color NORMAL_TEXT_COLOR = new Color(144,144,144);
	private final Color SAVEBUTTON_GRAYED_BG = new Color(235,235,235);
	private final Color SAVEBUTTON_GRAYED_TEXT = Color.WHITE;
	private PopupListEntry selected;
	
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	

	public ShoppingListPopupSave() {
		handler.readLists();
		lists = handler.getShoppingLists();

		setLayout(null);
		setBackground(Color.WHITE);
		listPanel.setBackground(Color.WHITE);
		chooseListLabel.setForeground(NORMAL_TEXT_COLOR);
		
		saveButton.setBackground(SAVEBUTTON_GRAYED_BG);
		saveButton.setForeground(SAVEBUTTON_GRAYED_TEXT);
		
		cancelButton.setBackground(NORMAL_TEXT_COLOR);
		cancelButton.setForeground(Color.WHITE);
				
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShoppingListPopupSave.this.setVisible(false);
			}
		});
		cancelButton.setBounds(212, 233, 107, 25);
//
		add(cancelButton);
		saveButton.setEnabled(false);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				saveButtonClicked(selected);
			}
		});
		
		initListPanels();
		saveButton.setBounds(93, 233, 107, 25);

		add(saveButton);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 39, 307, 185);
		
		add(scrollPane);
		listPanel.setSize(new Dimension(200, 150));
		listPanel.setMinimumSize(new Dimension(200, 150));
		listPanel.setPreferredSize(new Dimension(200, 150));
		
		scrollPane.setViewportView(listPanel);
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.PAGE_AXIS));
		chooseListLabel.setBounds(12, 12, 147, 15);
		
		add(chooseListLabel);
	}

	private void initListPanels() {
		for(ShoppingList list : lists) {
			PopupListEntry entry = new PopupListEntry(list);
			entry.addOwnMouseListener(new EntryMouseListener());
			listPanel.add(entry);
			
		}
	}

	private void storeListNames() {
		listNames = new String[lists.size()];
		int i = 0;
		for (ShoppingList list : lists) {
			listNames[i] = list.getName();
			i++;
		}
	}
	
	private void saveButtonClicked(PopupListEntry entry) {		
		pcs.firePropertyChange(null, null, entry.getShoppingList());
	}
	
	private class EntryMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if(selected != null) {
				selected.setBackground(NORMAL_BG_COLOR);
				selected.setForeground(NORMAL_TEXT_COLOR);
			}
			saveButton.setEnabled(true);
			saveButton.setBackground(SELECTED_BG_COLOR);
			saveButton.setForeground(SELECTED_TEXT_COLOR);
			
			PopupListEntry entry = (PopupListEntry)e.getSource();
			selected = entry;
			entry.setBackground(SELECTED_BG_COLOR);
			entry.setForeground(SELECTED_TEXT_COLOR);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	private static class Main extends JFrame {
		public Main() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			setPreferredSize(new Dimension(1190, 700));

			add(new ShoppingListPopupSave());
			pack();
		}

		public static void main(String[] args) {
			new Main();
		}
	}
	

}
