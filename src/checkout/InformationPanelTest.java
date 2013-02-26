package checkout;

import javax.swing.JFrame;

import se.chalmers.ait.dat215.project.IMatDataHandler;

public class InformationPanelTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IMatDataHandler dataHandler = IMatDataHandler.getInstance();
		JFrame frame = new JFrame();
		InformationPanel info = new InformationPanel();
		frame.setSize(info.getMinimumSize());
		frame.add(info);
		frame.setVisible(true);
		info.setVisible(true);
		System.out.println("done");

	}

}
