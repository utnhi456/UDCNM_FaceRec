package face_01;

import javax.swing.JDialog;

public class Waiting extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Waiting() {
		setTitle("PLEASE WAITING......");
		
		setLocationRelativeTo(null);		
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setSize(300,0);
	
		
	}
}
