package checkout;

import javax.swing.JFrame;

public class VerificationPanelTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		VerificationPanel  vp = new VerificationPanel();
		frame.add(vp);
		frame.setVisible(true);
		vp.setVisible(true);
		frame.setSize(vp.getMinimumSize());
		System.out.println("done");
	}

}
