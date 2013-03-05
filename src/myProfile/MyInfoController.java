package myProfile;

import javax.swing.JPanel;

import core.ViewController;

public class MyInfoController implements ViewController {

	private MyInfoPanel view = new MyInfoPanel();
	
	public MyInfoController() {
		
	}
	
	@Override
	public JPanel getView() {
		return view;
	}

}
