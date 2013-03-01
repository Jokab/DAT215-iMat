package checkout;

import javax.swing.JFrame;

public class ReceiptPanelTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame testFrame = new JFrame();
		ReceiptPanel panel = new ReceiptPanel();
		testFrame.add(panel);
		testFrame.setVisible(true);
		testFrame.setSize(800,500);
		System.out.println("Done");
	}

}
