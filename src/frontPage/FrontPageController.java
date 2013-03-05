package frontPage;

import javax.swing.JPanel;

import core.ViewController;

public class FrontPageController implements ViewController {
	
	private final FrontPageView view;
	
	public FrontPageController() {
		this.view = new FrontPageView();
	}

	@Override
	public JPanel getView() {
		return this.view;
	}
}
