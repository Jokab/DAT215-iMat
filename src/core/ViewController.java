package core;

import javax.swing.JPanel;

/**
 * An interface to be implemented by all view-controllers,
 * to be able to store history.
 * @author Sebastian Blomberg
 *
 */
public interface ViewController {
	public JPanel getView();
}
