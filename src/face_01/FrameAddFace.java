package face_01;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import group5.JavaSampleGetPersonName;

public class FrameAddFace extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static List<JRadioButton> listRButtonGroup = new ArrayList<JRadioButton>();
	private static List<JRadioButton> listRButtonPersonName = new ArrayList<JRadioButton>();
	static List<String> personName = new ArrayList<String>();
	
	public static void getFrame(List<String> listGroup, String GId) {
		JFrame jf = new JFrame("DM Add Face");
		JPanel pWest = new JPanel();
		JPanel pEast = new JPanel();
		//
		JRadioButton jrbA;
		JRadioButton jrbB;
		//get person name
		personName = JavaSampleGetPersonName.main(GId);
		
		
		//create button group
		for(int i = 0; i < listGroup.size(); i++) {
			jrbA = new JRadioButton(listGroup.get(i));
			listRButtonGroup.add(jrbA);
		}
		
		for(int i = 0; i < personName.size(); i++) {
			jrbB = new JRadioButton(personName.get(i));
			listRButtonPersonName.add(jrbB);
		}
		
		//group button
		ButtonGroup bgA = new ButtonGroup();
		for(int i = 0; i < listRButtonGroup.size(); i++) {
			bgA.add(listRButtonGroup.get(i));
		}
		
		ButtonGroup bgB = new ButtonGroup();
		for(int i = 0; i < listRButtonPersonName.size(); i++) {
			bgB.add(listRButtonPersonName.get(i));
		}
		
		//Box
		Box boxA = Box.createVerticalBox();
		for(int i = 0; i < listRButtonGroup.size(); i++) {
			boxA.add(listRButtonGroup.get(i));
		}
		
		
		Box boxB = Box.createVerticalBox();
		for(int i = 0; i < listRButtonPersonName.size(); i++) {
			boxB.add(listRButtonPersonName.get(i));
		}
		
		boxA.add(Box.createVerticalStrut(10));
		boxB.add(Box.createVerticalStrut(10));
		
		//West Pane
		pWest.add(boxA, BorderLayout.CENTER);
		pWest.setBackground(Color.BLUE);
		pWest.setPreferredSize(new Dimension(320, 240));
		
		//create button person name/id

		
		//East Pane
		pEast.add(boxB, BorderLayout.CENTER);
		pEast.setBackground(Color.YELLOW);
		pEast.setPreferredSize(new Dimension(320, 240));
		
		//Frame
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);
		jf.setSize(640, 480);
		jf.add(pWest, BorderLayout.WEST);
		jf.add(pEast, BorderLayout.EAST);
		jf.setVisible(true);
		
		//listener
		
	}
}
