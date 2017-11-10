package face_01;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.JSONObject;
import org.opencv.core.Core;

public class MyProject2 extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTabbedPane tabbedPane = new JTabbedPane();
	private JPanel panelDetect;
	private JPanel panelCompare;
	//-------------------------------------------
	private JButton buttonInfo;
	private JButton buttonImport;
	private JButton buttonWebcamt;
	private JButton buttonExit;
	private JPanel panelButton;
	//-------------------------------------
	private JButton buttonBrowse1;
	private JButton buttonBrowse2;
	private JPanel panelSouthCompare;
	private JButton buttonCompare;

	private JPanel pEast;
	private JPanel pWest;
	private JLabel lbex;
	private JLabel lbeast;
	JLabel lbLoading;
	JLabel lbLoading2;
	private JFileChooser choose;
	private JFileChooser chooseImport;
	private String filenameOpen;
	private JFileChooser choose2;
	private String filenameOpen2;
	public static String filenameOpenDetect_Face;
	private JLabel lblimport;
	private static String output = "lib/temp.jpg";
	
	//---------------------------------------------
	public MyProject2() throws IOException {
		setTitle("[UDCNM] FACE DETECTOR");
		setSize(1300, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setupGUI();
	}
	/**
	 * @author TRUONG TRUNG THIEN
	 * @author CAC THAM SO SU DUNG
	 * @throws IOException
	 * @param tabbedPane
	 * @param panelDetect
	 * @param
	 */

	private void setupGUI() throws IOException {
		
		tabbedPane.setTabPlacement(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.YELLOW);
		add(tabbedPane);
		//----------------------------
		panelDetect=new JPanel();
		tabbedPane.add(panelDetect, "DETECT FACE");
		panelDetect.setLayout(new BorderLayout());
		//panelDetect.add(lbdetect=new JLabel(), BorderLayout.CENTER);
		panelDetect.add(lblimport = new JLabel("Click DETECT to import image"),BorderLayout.CENTER);
		lblimport.setFont(new Font("Verdana", Font.BOLD, 25));
		lblimport.setHorizontalAlignment(JLabel.CENTER);
		panelDetect.add(panelButton=new JPanel(),BorderLayout.SOUTH);
//		panelDetect.add(lbdraw);
		
		
		
		
		panelButton.setPreferredSize(new Dimension(1000, 50));
		panelButton.setLayout(null);
		panelButton.add(buttonInfo=new JButton("INFO"));
		panelButton.add(buttonImport=new JButton("DETECT"));
		panelButton.add(buttonWebcamt=new JButton("WEBCAM"));
		panelButton.add(buttonExit=new JButton("EXIT"));
		panelButton.setBackground(Color.BLUE);
		buttonInfo.setBounds(200, 17, 100, 25);
		buttonImport.setBounds(400, 17, 100, 25);
		buttonWebcamt.setBounds(600, 17, 100, 25);
		buttonExit.setBounds(800, 17, 100, 25);
		//buttonImport.setBorder(; //10 is the radius
	    buttonImport.setForeground(Color.BLUE);
		//////----------------------------------------
		panelCompare=new JPanel();
		tabbedPane.add(panelCompare, "VERIFYCATION");
		panelCompare.setLayout(new BorderLayout());
		panelSouthCompare=new JPanel();
		panelSouthCompare.add(buttonBrowse1=new JButton("Browse"));
		panelSouthCompare.add(buttonCompare=new JButton("EXECUTE"));
		panelSouthCompare.add(buttonBrowse2=new JButton("Browse"));
		
		panelCompare.add(panelSouthCompare, BorderLayout.SOUTH );
		panelSouthCompare.setBackground(Color.BLUE);
		
		//
		panelCompare.add(pWest=new JPanel(),BorderLayout.WEST);
		pWest.add(lbex=new JLabel());
		pWest.setPreferredSize(new Dimension(650, 600));
		
		
		
		//
		panelCompare.add(pEast=new JPanel(),BorderLayout.EAST);
		pEast.add(lbeast=new JLabel());
		pEast.setPreferredSize(new Dimension(650, 600));
		
		
		
		
		
		//---------------- BUTTON-------------------------
		buttonBrowse1.addActionListener(this);
		buttonBrowse2.addActionListener(this);
		buttonCompare.addActionListener(this);
		buttonExit.addActionListener(this);
		buttonImport.addActionListener(this);
		buttonWebcamt.addActionListener(this);
		
	}
	
	//---------------RESIZE AND SET IMAGE INTO PANEL----------------------------------------------------------------------------
	private void setImage(JLabel label, String filename){
		lblimport.removeAll();
		try {
			BufferedImage image = ImageIO.read(new File(filename));
			image=ImageResizer.resize(filename, 650, 600);
			
			ImageIcon icon = new ImageIcon(image);
			
			label.setIcon(icon);
			
		} catch (IOException e) {
			System.err.println("khong the dua anh len");
		}catch (NullPointerException e) {
			System.err.println("khong mo file!");
			
		}
	}
	// -------------- ACTIONPERFORMED-------------------------------
	/**
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * @param 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o=e.getSource();
		//-----------------Handling TAB2------------------------------------------------------------------------------
		if(o.equals(buttonBrowse1))
		{
			choose=new JFileChooser();
			FileNameExtensionFilter filer=new FileNameExtensionFilter("hinh anh", "jpg","tif","gif","png","bpm");
			choose.setFileFilter(filer);
			int retutnVal = choose.showOpenDialog(this);
			if(retutnVal == JFileChooser.APPROVE_OPTION){
				filenameOpen = choose.getSelectedFile().getAbsolutePath();
				setImage(lbex, filenameOpen);
				
			}
		}else if(o.equals(buttonBrowse2)) {
			choose2=new JFileChooser();
			FileNameExtensionFilter filer=new FileNameExtensionFilter("hinh anh", "jpg","tif","gif","png","bpm","jpeg");
			choose2.setFileFilter(filer);
			int retutnVal = choose2.showOpenDialog(this);
			if(retutnVal == JFileChooser.APPROVE_OPTION){
				filenameOpen2 = choose2.getSelectedFile().getAbsolutePath();
				setImage(lbeast, filenameOpen2);
			}
		}else if(o.equals(buttonCompare))	{
//			if(lbex.getIcon() == null || lbeast.getIcon() == null) {
//				JOptionPane.showMessageDialog(null, "joking!?");
//			}
//			else {
				startWork(filenameOpen, filenameOpen2);
//			}
			
			//----------------------- TAB1---------------------------------------------------------------------------------------
		}else if(o.equals(buttonExit)){
			JOptionPane.showConfirmDialog(null, "Are you sure?");
			System.exit(1);
		}else if(o.equals(buttonImport)){
			panelDetect.revalidate();
			panelDetect.repaint();

			chooseImport = new JFileChooser();
			FileNameExtensionFilter filer = new FileNameExtensionFilter("hinh anh", "jpg","tif","gif","png","bpm","jpeg");
			chooseImport.setFileFilter(filer);
			int retutnVal = chooseImport.showOpenDialog(this);
			if(retutnVal == JFileChooser.APPROVE_OPTION){
				
				filenameOpenDetect_Face = chooseImport.getSelectedFile().getAbsolutePath();
				
				startWorkDetect(filenameOpenDetect_Face, output);
			}
		} else if(o.equals(buttonInfo)){
			
		}else if(o.equals(buttonWebcamt)){
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			new SnapShot().setVisible(true);
		}
		
	}
	
	//https://www.codementor.io/isaib.cicourel/swingworker-in-java-du1084lyl
	private void startWork(String s1, String s2) {
		SwingWorker<Boolean, Integer> worker = new SwingWorker<Boolean, Integer>() {
			
			@Override
			protected Boolean doInBackground() throws Exception {
				publish();
				new Verify();
				String gt = Verify.Verify_request(s1, s2);
				JSONObject obj = new JSONObject(gt);
				double perV = obj.getDouble("confidence");
				String iS;
				if(perV >= 0.5) {
					 iS = "Same person!";
				}
				else {
					iS = "Not same person!";
				}
				JOptionPane.showMessageDialog(null, iS + "\nConfidence:" + perV);
				removeLoadingMes();
				//
				Thread.sleep(100);
				
				return false;
			}
			
			@Override
			protected void process(List<Integer> chunks) {
				addLoadingMes();
			}

		};
		worker.execute();
	}
	
	private void startWorkDetect(String s1, String output) {
		SwingWorker<Boolean, Integer> worker = new SwingWorker<Boolean, Integer>() {

			@Override
			protected Boolean doInBackground() throws Exception {
				buttonImport.setEnabled(false);
				try {
					ImageResizer.resize(s1, output, 1300, 600);
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}	
				MyCanvas m = new MyCanvas();
				m.setBounds(0, 25, 1300, 600);
				getContentPane().add(m);	
				Thread.sleep(1000);
				publish();
				return null;
			}
			
			@Override
			protected void process(List<Integer> chunks) {
				// TODO Auto-generated method stub
				buttonImport.setEnabled(true);
			}
		};
		worker.execute();
	}
	
	private void addLoadingMes() {
		ImageIcon loading = new ImageIcon("lib/loading.gif");
		
		lbLoading2 = new JLabel("Loading...", JLabel.RIGHT);
		lbLoading2.setForeground(Color.YELLOW);
		lbLoading2.setFont(new Font("Verdana", Font.BOLD, 16));
		lbLoading = new JLabel(loading, JLabel.RIGHT);
		buttonBrowse1.setEnabled(false);
		buttonBrowse2.setEnabled(false);
		buttonCompare.setEnabled(false);
		panelSouthCompare.add(lbLoading);
		panelSouthCompare.add(lbLoading2);
		panelSouthCompare.revalidate();
		panelSouthCompare.repaint();
	}
	
	private void removeLoadingMes() {
		panelSouthCompare.remove(lbLoading);
		panelSouthCompare.remove(lbLoading2);
		buttonBrowse1.setEnabled(true);
		buttonBrowse2.setEnabled(true);
		buttonCompare.setEnabled(true);
		panelSouthCompare.revalidate();
		panelSouthCompare.repaint();
	}
}