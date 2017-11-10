
package face_01;

import group5.*;
import ImagePreview.*;
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
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.opencv.core.Core;

public class MyProject extends JFrame implements ActionListener{

	/**
	 * 
	 */


	/**
	 * @author TRUONG TRUNG THIEN
	 * @author CAC THAM SO SU DUNG
	 * @throws IOException
	 * @param tabbedPane bao gồm face detection và face verification
	 * @param panelDetect là panelChứa tính năng detect face và chứa panelSouthButton ở SOUTH
	 * @param panelCompare là panel dùng để verify 2 ảnh
	 * 
	 * @category panelDetect là một panel có BorderLayout và chứa panel button ở SOUTH 
	 * @param panelButton chứa 4 button dùng absolutlayout
	 */
	ImageIcon loading = new ImageIcon("lib/loading.gif");
	private static final long serialVersionUID = 1L;
	private JPanel panelDetect;
	private JPanel panelCompare;
	
	//-------------------------------------------
	private JButton buttonInfo;
	private JButton buttonImport;
	private JButton buttonWebcamt;
	private JButton buttonExit;
	private JButton btnCrGroup;
	private JButton btnCrPerson;
	private JButton btnAddFace;
	private JButton btnTrain;
	private JButton btnExcute;
	private JPanel panelButton;
	//-------------------------------------
	private JButton buttonBrowse1;
	private JButton buttonBrowse2;
	private JButton buttonCompare;
	private JPanel panelSouthCompare;
	private JPanel pEast;
	private JPanel pWest;
	private JLabel lbeast;
	private JLabel lbex;
	private String filenameOpen2;
	private String filenameOpen;
	private JFileChooser choose;
	private JFileChooser choose2;
	MyCanvas m=null;
	//------------------------------------------------------
	private JFileChooser chooseImport;
	public static String filenameOpenDetect_Face;
	private JLabel lblimport;
	private JLabel lbload;
	private JPanel panelIdentify;
	private JPanel pIS;
	//---------------------------------------------
	static int index = 0;
	static String setId;
	private JComboBox<Object> cbList;
	//------------------------------------------
	public MyProject() throws IOException {
		setTitle("FACE APPLYCATION INTERFACE");
		setSize(1300, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		setupGUI();
	}


	private void setupGUI() throws IOException {

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setTabPlacement(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Verdana", Font.BOLD, 14));
		tabbedPane.setForeground(Color.DARK_GRAY);;
		tabbedPane.setBackground(Color.GREEN);
		
		add(tabbedPane);
		//----------------------------
		/*
		 * COMPONENT trong tabDETECT
		 */
		panelDetect=new JPanel();
		tabbedPane.add(panelDetect, "DETECT FACE");
		panelDetect.setLayout(new BorderLayout());
		panelDetect.add(lblimport = new JLabel("Click DETECT to import image"),BorderLayout.CENTER);
		lblimport.setFont(new Font("Verdana", Font.BOLD, 25));
		lblimport.setHorizontalAlignment(JLabel.CENTER);
		panelDetect.add(panelButton=new JPanel(),BorderLayout.SOUTH);




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
		buttonImport.setForeground(Color.BLUE);
		//////----------------------------------------
		/*
		 * Component trong tab Verify
		 */
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
//-----------------------------------------------------------------------------------
		/*
		 * Component trong tab IDENTIFY
		 * 
		 */
		panelIdentify=new JPanel();
		tabbedPane.add(panelIdentify,"IDENTIFY");
		
		panelIdentify.setLayout(new BorderLayout());
		panelIdentify.add(pIS = new JPanel(), BorderLayout.SOUTH);
		pIS.add(btnCrGroup=new JButton("Create Person Group"));
		pIS.add(btnCrPerson=new JButton("Create Person "));
		pIS.add(btnAddFace=new JButton("Add Face"));
		pIS.add(btnTrain=new JButton("Train"));
		pIS.add(btnExcute=new JButton("Excute"));
		pIS.setBackground(Color.BLUE);
		//---------------- BUTTON--------------------------------------------------------------------------------------------------------------
		/**
		 *  Add button for action listener
		 */

		buttonBrowse1.addActionListener(this);
		buttonBrowse2.addActionListener(this);
		buttonCompare.addActionListener(this);
		buttonExit.addActionListener(this);
		buttonImport.addActionListener(this);
		buttonWebcamt.addActionListener(this);
		buttonInfo.addActionListener(this);
		btnCrGroup.addActionListener(this);
		btnCrPerson.addActionListener(this);
		btnAddFace.addActionListener(this);
		btnTrain.addActionListener(this);
		btnExcute.addActionListener(this);
		/*
		 *  Clock button prevent User autoClick
		 */
		UnLockVerify(false);

	}

	private void UnLockVerify(boolean b) {
		buttonCompare.setEnabled(b);
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
			System.err.println("Them anh khong thanh cong!");
		}catch (NullPointerException e) {
			System.err.println("Khong mo file!");

		}
	}

	// -------------- ACTIONPERFORMED-------------------------------
	/**
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * @param buttonBrows21 và  buttonBrowse2 cho phép mở 2 ảnh từ đường dẫn người dùng chỉ định
	 * @param buttonCompare: thao tác verify và trả về kết quả 
	 * @param buttonImport: Detect face
	 * @param buttonInfo
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
				filenameOpen=choose.getSelectedFile().getAbsolutePath();
				setImage(lbex, filenameOpen);
			}
		}
		else if(o.equals(buttonBrowse2))
		{
			choose2=new JFileChooser();
			FileNameExtensionFilter filer=new FileNameExtensionFilter("hinh anh", "jpg","tif","gif","png","bpm","jpeg");
			choose2.setFileFilter(filer);
			int retutnVal = choose2.showOpenDialog(this);
			if(retutnVal == JFileChooser.APPROVE_OPTION){
				filenameOpen2=choose2.getSelectedFile().getAbsolutePath();
				setImage(lbeast, filenameOpen2);
				UnLockVerify(true);
			}
		}
		else if(o.equals(buttonCompare))
		{
			Waiting wt=new Waiting();
			wt.setVisible(true);
			new Verify();
			String gt=Verify.Verify_request(filenameOpen, filenameOpen2);
			wt.setVisible(false);


			try{
				//			JOptionPane.showMessageDialog(null, gt);
				//			System.out.println(gt);
				String g2=gt.substring(15, 16);
				if(g2.equals("f")){
					JOptionPane.showMessageDialog(null, "Not match!");
				}else{
					String g3=gt.substring(33,37);
					System.out.println(g3);
					Double d=Double.parseDouble(g3)*100;
					JOptionPane.showMessageDialog(null, "Same person! \n With:"+String.valueOf(d)+"%");
				}
			}catch(Exception e1)
			{
				JOptionPane.showMessageDialog(null, "This is a person!");
			}


			//----------------------- TAB1---------------------------------------------------------------------------------------
		}
		else if(o.equals(buttonExit)){
			String message="Are you sure?";
			String title="WARNING!";
			int rel=JOptionPane.showConfirmDialog(null, message,title,JOptionPane.YES_NO_OPTION);
			if(rel==JOptionPane.YES_OPTION)
			{
					System.exit(1);
			}
		}
		else if(o.equals(buttonImport)){
			lblimport.setText("Please wait...");

			panelButton.add(lbload=new JLabel(loading));

			lbload.setFont(new Font("Arial", Font.BOLD, 16));
			lbload.setForeground(Color.YELLOW);
			lbload.setBounds(1000, 17, 100, 25);
			chooseImport=new JFileChooser();
			FileNameExtensionFilter filer=new FileNameExtensionFilter("Image", "jpg","tif","gif","png","bpm","jpeg","psd");
			chooseImport.setFileFilter(filer);
			int retutnVal = chooseImport.showOpenDialog(this);
			if(retutnVal == JFileChooser.APPROVE_OPTION){
				filenameOpenDetect_Face=chooseImport.getSelectedFile().getAbsolutePath();
				System.out.println(filenameOpenDetect_Face);

				int val=MyCanvas.RunApp2();
				if(val==1)
				{
					m=new MyCanvas();
					m.setBounds(0, 25, 1300, 585);
					getContentPane().add(m);

				}
			}else
			{
				lblimport.setText("Click DETECT to import image");
			}
		} 
		else if(o.equals(buttonInfo)){
			new ImageInfomation().setVisible(true);
//			m.revalidate();
//			m.repaint();
//			m=null;
		}
		else if(o.equals(buttonWebcamt)){

			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			new SnapShot().setVisible(true);
		}
		else if(o.equals(btnCrGroup)) {
			JTextField pgName = new JTextField();
			JTextField userData = new JTextField();
			Object[] mess = {
				"PersonGroup Name:", pgName,
				"User Data:", userData
			};
			int option = JOptionPane.showConfirmDialog(null, mess, "Create Person Group", JOptionPane.OK_CANCEL_OPTION);
			
			if(option == JOptionPane.OK_OPTION) {
				String spName = pgName.getText();
				String suserData = userData.getText();
				CreatePersonGroup.CreatePresonGroup(spName, suserData);
			}
		}
		else if(o.equals(btnCrPerson)) {

			JTextField namePerson = new JTextField();
			JTextField suserData = new JTextField();
			
			List<String> pGroupName;
			pGroupName = JavaSampleGetList.getList();
			String[] cb = new String[pGroupName.size()];
			for(int i = 0; i < pGroupName.size(); i++) {
				cb[i] = pGroupName.get(i);
			}
			cbList = new JComboBox<Object>(cb);
			
			Object[] mess = {
					"List PersonGroup", cbList,
					"Person Name:", namePerson,
					"User Data:", suserData
					
			};
			System.out.println("\nDemo Get List\n" + pGroupName);

			int option = JOptionPane.showConfirmDialog(null, mess, "Create Person", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION) {
				String getNameGroup = (String) cbList.getSelectedItem();
				String getName = namePerson.getText().toLowerCase();
				String getUserData = suserData.getText();
				JavaSampleCreatePerson.CreatePerson(getNameGroup, getName, getUserData);
			}
			pGroupName.removeAll(pGroupName);
		}
		else if(o.equals(btnAddFace)) {
			List<String> pGroupName;
			pGroupName = JavaSampleGetList.getList();
			
			
			FrameAddFace.getFrame(pGroupName, "addface");
			
			
			/*
			System.out.println("\nDemo Get List\n" + perSonID);
			ImageFileChooser iChooser = new ImageFileChooser();
			iChooser.setMultiSelectionEnabled(true);
			int retutnVal = iChooser.showOpenDialog(null);
			if(retutnVal == JFileChooser.APPROVE_OPTION) {
				String filenameOpenDetect_Face = iChooser.getSelectedFile().getAbsolutePath();
				
				//String faceId = JavaSampleGetFaceID.getStatus(filenameOpenDetect_Face);
				
			}
			*/
		}
		else if(o.equals(btnTrain)) {
			List<String> pGroupName;
			pGroupName = JavaSampleGetList.getList();
			String[] cb = new String[pGroupName.size()];
			for(int i = 0; i < pGroupName.size(); i++) {
				cb[i] = pGroupName.get(i);
			}
			JComboBox<Object> cbList = new JComboBox<Object>(cb);
			Object[] mess = {
					"List PersonGroup", cbList,				
			};
			int option = JOptionPane.showConfirmDialog(null, mess, "Training", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION) {
				String getGroupID = (String) cbList.getSelectedItem();
				TrainGroup.Train(getGroupID);
			}
			pGroupName.removeAll(pGroupName);		
		}
		else if(o.equals(btnExcute)) {
			
			List<String> pGroupName;
			pGroupName = JavaSampleGetList.getList();
			String[] cb = new String[pGroupName.size()];
			for(int i = 0; i < pGroupName.size(); i++) {
				cb[i] = pGroupName.get(i);
			}
			JComboBox<Object> cbList = new JComboBox<Object>(cb);
			Object[] mess = {
					"List PersonGroup", cbList,				
			};
			
			ImageFileChooser iChooser = new ImageFileChooser();
			iChooser.setMultiSelectionEnabled(true);
			int retutnVal = iChooser.showOpenDialog(this);
			if(retutnVal == JFileChooser.APPROVE_OPTION) {
				filenameOpenDetect_Face = iChooser.getSelectedFile().getAbsolutePath();
				String getGroupId = null;
				
				String faceId = JavaSampleGetFaceID.getStatus(filenameOpenDetect_Face);
				int option = JOptionPane.showConfirmDialog(null, mess, "Training", JOptionPane.OK_CANCEL_OPTION);
				if(option == JOptionPane.OK_OPTION) {
					getGroupId = (String) cbList.getSelectedItem();
				}
				
				JavaSampleIdentify.Identify(faceId, getGroupId);
				
			}
			
			pGroupName.removeAll(pGroupName);

		}
	}
}
