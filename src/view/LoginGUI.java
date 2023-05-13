package view;

import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import helper.DBConnection;
import helper.Helper;
import model.Doctor;
import model.HeadDoctor;
import model.Patient;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;


public class LoginGUI extends JFrame {

	
	private JPanel w_pane;
	private JTextField tf_idNumber;
	private JTextField tf_doctorID;
	private JPasswordField doctorPswfield;
	private JPasswordField patientPswfield;
	String type;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setResizable(false);
		setTitle("Kocaeli City Hospital");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 500);
		w_pane = new JPanel();
		w_pane.setForeground(Color.WHITE);
		w_pane.setBackground(Color.DARK_GRAY);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		
		ImageIcon loginImage = new ImageIcon("C:/Users/ymnN/Desktop/eclipse_workspace/java_swing_hospital/src/images/hospital.png");
		Image image = loginImage.getImage();
		Image newImg = image.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		loginImage = new ImageIcon(newImg);
		w_pane.setLayout(null);
		JLabel label_logo = new JLabel(loginImage);
		label_logo.setBounds(158,11,60,60);
		
		//JLabel label_logo = new JLabel(new ImageIcon(getClass().getResource("C:/Users/ymnN/Desktop/eclipse_workspace/java_swing_hospital/src/images/hospital.png")));
		
		w_pane.add(label_logo);
		
		JLabel lblNewLabel = new JLabel("Welcome to Hospital Management Panel");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 82, 364, 25);
		w_pane.add(lblNewLabel);
		
		JTabbedPane w_tabPane = new JTabbedPane(JTabbedPane.TOP);
		w_tabPane.setForeground(Color.BLACK);
		w_tabPane.setFont(new Font("Tahoma", Font.BOLD, 11));
		w_tabPane.setBackground(Color.DARK_GRAY);
		w_tabPane.setBounds(10, 118, 364, 332);
		w_pane.add(w_tabPane);
		
		
		ImageIcon patientImage = new ImageIcon("C:/Users/ymnN/Desktop/eclipse_workspace/java_swing_hospital/src/images/patient.png");
		Image image2 = patientImage.getImage();
		Image newImg2 = image2.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		patientImage = new ImageIcon(newImg2);
		
		JPanel w_patientLogin = new JPanel();
		w_patientLogin.setForeground(Color.WHITE);
		w_patientLogin.setBackground(Color.DARK_GRAY);
		w_tabPane.addTab("Patient", null, w_patientLogin, null);
		w_patientLogin.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ID Number");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 11, 65, 65);
		w_patientLogin.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1.setBounds(10, 60, 65, 65);
		w_patientLogin.add(lblNewLabel_1_1);
		
		tf_idNumber = new JTextField();
		tf_idNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
		tf_idNumber.setBounds(109, 33, 217, 20);
		w_patientLogin.add(tf_idNumber);
		tf_idNumber.setColumns(10);
		
		JButton btnNewButton = new JButton("REGISTER");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(109, 126, 100, 23);
		w_patientLogin.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("LOGIN");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tf_idNumber.getText().length() == 0 || patientPswfield.getText().length() == 0) {
					Helper.showMessage("fill");
				} else {
					DBConnection db = new DBConnection();
					Connection connect = db.connectDB();
					
					try {
						Statement st = connect.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM users");
						
						
						while(rs.next()) {
							boolean isTrue = tf_idNumber.getText().equals(rs.getString("identify")) && patientPswfield.getText().equals(rs.getString("password"));
							if(isTrue == true) {
								type = rs.getString("type");
								break;
							}		
						}
						if(type.equals("patient")) {
							Patient patient = new Patient(rs.getInt("id"),rs.getString("identify"),rs.getString("name"),rs.getString("password"),rs.getString("type"));
						
							
							Helper.successfulMessage();
							PatientGUI patientGUI = new PatientGUI();
							patientGUI.setVisible(true);
							dispose();
						} else {
							Helper.loginErrorMessage();
						}
					} catch (SQLException e1) {
					
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.setBounds(226, 126, 100, 23);
		w_patientLogin.add(btnNewButton_1);
		JLabel patient_logo = new JLabel(patientImage);
		patient_logo.setBounds(141, 160, 157, 111);
		w_patientLogin.add(patient_logo);
		
		patientPswfield = new JPasswordField();
		patientPswfield.setBounds(107, 82, 219, 20);
		w_patientLogin.add(patientPswfield);
		
		JPanel w_doctorLogin = new JPanel();
		w_doctorLogin.setLayout(null);
		w_doctorLogin.setForeground(Color.WHITE);
		w_doctorLogin.setBackground(Color.DARK_GRAY);
		w_tabPane.addTab("Doctor", null, w_doctorLogin, null);
		
		JLabel lblNewLabel_1_2 = new JLabel("ID Number");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2.setBounds(10, 11, 65, 65);
		w_doctorLogin.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Password");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1_1.setBounds(10, 60, 65, 65);
		w_doctorLogin.add(lblNewLabel_1_1_1);
		
		tf_doctorID = new JTextField();
		tf_doctorID.setFont(new Font("Tahoma", Font.BOLD, 12));
		tf_doctorID.setColumns(10);
		tf_doctorID.setBounds(109, 33, 217, 20);
		w_doctorLogin.add(tf_doctorID);
		
		JButton btnNewButton_1_1 = new JButton("LOGIN");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tf_doctorID.getText().length() == 0 || doctorPswfield.getText().length() == 0) {
					Helper.showMessage("fill");
				} else {
					DBConnection db =  new DBConnection();
					try {
						Connection connect = db.connectDB();
						Statement st = connect.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM users");
						
					while(rs.next()) {
						boolean isTrue = tf_doctorID.getText().equals(rs.getString("identify")) && doctorPswfield.getText().equals(rs.getString("password"));
						if(isTrue == true) {
							type = rs.getString("type");
							break;
						}	
					}
						if(type.equals("doctor")) {
							Doctor doc = new Doctor(rs.getInt("id"),rs.getString("identify"),rs.getString("name"),rs.getString("password"),rs.getString("type"));
							Helper.successfulMessage();
							DoctorGUI docGUI = new DoctorGUI(doc);
							docGUI.setVisible(true);
							dispose();
											
						} else if (type.equals("headdoctor")){
							HeadDoctor headDoc = new HeadDoctor(rs.getInt("id"),rs.getString("identify"),rs.getString("name"),rs.getString("password"),rs.getString("type"));
							//headDoc.setName(rs.getString("name"));
							Helper.successfulMessage();
							HeadDoctorGUI headDocGUI = new HeadDoctorGUI(headDoc);
							headDocGUI.setVisible(true);
							dispose();
							
											
						} else {
							Helper.loginErrorMessage();
						}
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1_1.setBounds(109, 126, 217, 23);
		w_doctorLogin.add(btnNewButton_1_1);
		
		
		
		ImageIcon doctorImage = new ImageIcon("C:/Users/ymnN/Desktop/eclipse_workspace/java_swing_hospital/src/images/doctor.png");
		Image image3 = doctorImage.getImage();
		Image newImg3 = image3.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		doctorImage = new ImageIcon(newImg3);
		
		JLabel lblNewLabel_2 = new JLabel(doctorImage);
		lblNewLabel_2.setBounds(139, 160, 149, 133);
		w_doctorLogin.add(lblNewLabel_2);
		
		doctorPswfield = new JPasswordField();
		doctorPswfield.setBounds(109, 82, 217, 20);
		w_doctorLogin.add(doctorPswfield);
		
		
	}
}
