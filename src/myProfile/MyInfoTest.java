package myProfile;

import javax.swing.JFrame;

public class MyInfoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new MyInfoPanel());
		frame.setVisible(true);
		System.out.println("Done");
	}

}
