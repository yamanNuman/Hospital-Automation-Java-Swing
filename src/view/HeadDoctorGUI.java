package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import model.Clinic;

import helper.Helper;
import helper.Item;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Image;
import java.awt.Point;

import model.HeadDoctor;
import model.User;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JComboBox;

public class HeadDoctorGUI extends JFrame {

	private JPanel contentPane;
	static HeadDoctor headDoctor = new HeadDoctor();
	User user = new User();
	Clinic clinic = new Clinic();
	private JTextField tfDoctorName;
	private JTextField tfDoctorID;
	private JTextField tfDoctorPsw;
	private JTextField tfDoctorUserID;
	private JTable doctorTable;
	private DefaultTableModel doctorModel = null;
	private Object[] doctorData = null;

	private JTextField tfClinic;
	private DefaultTableModel clinicModel = null;
	private Object[] clinicData = null;
	private JTable clinicTable;
	private JTable employeeTable;
	private JPopupMenu clinicMenu;
	private JMenuItem updateMenu;
	private JMenuItem deleteMenu;
	private Point point;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HeadDoctorGUI frame = new HeadDoctorGUI(headDoctor);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public HeadDoctorGUI(HeadDoctor headDoc) throws SQLException {
		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[4];
		colDoctorName[0] = "ID";
		colDoctorName[1] = "Identify";
		colDoctorName[2] = "Name";
		colDoctorName[3] = "Password";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[4];
		for(int i = 0; i < headDoc.getDoctorList().size(); i++ ) {
			doctorData[0] = headDoc.getDoctorList().get(i).getId();
			doctorData[1] = headDoc.getDoctorList().get(i).getId_number();
			doctorData[2] = headDoc.getDoctorList().get(i).getName();
			doctorData[3] = headDoc.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
		
		//clinic model
		
		clinicModel = new DefaultTableModel();
		Object[] colClinicName = new Object[2];
		colClinicName[0] = "ID";
		colClinicName[1] = "Clinic Name";
		clinicModel.setColumnIdentifiers(colClinicName);
		clinicData = new Object[2];
		for(int i = 0; i < clinic.getList().size(); i++) {
			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();
			clinicModel.addRow(clinicData);
		}
		
		//worker model
		
		DefaultTableModel workerModel = new DefaultTableModel();
		Object[] colWorker = new Object[2];
		colWorker[0] = "ID";
		colWorker[1] = "Name";
		workerModel.setColumnIdentifiers(colWorker);
		Object[] workerData = new Object[2];
		
		
		setResizable(false);
		setTitle("Head Doctor Panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1041, 519);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome " + headDoc.getName());
		lblNewLabel.setBounds(10, 11, 169, 23);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Quit");
		btnNewButton.setBounds(501, 12, 146, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(btnNewButton);
		
		JTabbedPane w_pane = new JTabbedPane(JTabbedPane.TOP);
		w_pane.setBackground(Color.DARK_GRAY);
		w_pane.setFont(new Font("Tahoma", Font.BOLD, 11));
		w_pane.setBounds(10, 99, 988, 370);
		contentPane.add(w_pane);
		
		JPanel w_doctor = new JPanel();
		w_doctor.setBackground(Color.DARK_GRAY);
		w_pane.addTab("Doctor Management", null, w_doctor, null);
		w_doctor.setLayout(null);
		
		JScrollPane scrollDoctor = new JScrollPane();
		scrollDoctor.setBounds(10, 11, 382, 320);
		w_doctor.add(scrollDoctor);
		
		doctorTable = new JTable(doctorModel);
		scrollDoctor.setViewportView(doctorTable);
		
		JLabel label_doctorName = new JLabel("Name");
		label_doctorName.setForeground(Color.WHITE);
		label_doctorName.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_doctorName.setBounds(402, 11, 133, 23);
		w_doctor.add(label_doctorName);
		
		tfDoctorName = new JTextField();
		tfDoctorName.setBounds(402, 37, 206, 20);
		w_doctor.add(tfDoctorName);
		tfDoctorName.setColumns(10);
		
		JLabel label_doctorID = new JLabel("ID Number");
		label_doctorID.setForeground(Color.WHITE);
		label_doctorID.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_doctorID.setBounds(402, 68, 133, 23);
		w_doctor.add(label_doctorID);
		
		tfDoctorID = new JTextField();
		tfDoctorID.setColumns(10);
		tfDoctorID.setBounds(402, 90, 206, 20);
		w_doctor.add(tfDoctorID);
		
		JLabel label_doctorPsw = new JLabel("Password");
		label_doctorPsw.setForeground(Color.WHITE);
		label_doctorPsw.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_doctorPsw.setBounds(402, 121, 133, 23);
		w_doctor.add(label_doctorPsw);
		
		tfDoctorPsw = new JTextField();
		tfDoctorPsw.setColumns(10);
		tfDoctorPsw.setBounds(402, 145, 206, 20);
		w_doctor.add(tfDoctorPsw);
		
		JButton btnNewButton_1 = new JButton("Add");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					headDoc.addDoctor(tfDoctorID.getText(), tfDoctorName.getText(), tfDoctorPsw.getText());
				
				
				Helper.addMessage();
				try {
					updateDoctorModel();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.setBounds(466, 197, 89, 20);
		w_doctor.add(btnNewButton_1);
		
		tfDoctorUserID = new JTextField();
		
		tfDoctorUserID.setColumns(10);
		tfDoctorUserID.setBounds(402, 278, 206, 20);
		w_doctor.add(tfDoctorUserID);
		
		JLabel label_doctorpkID = new JLabel("User ID");
		label_doctorpkID.setForeground(Color.WHITE);
		label_doctorpkID.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_doctorpkID.setBounds(402, 245, 133, 23);
		w_doctor.add(label_doctorpkID);
		
		JButton btnNewButton_1_1 = new JButton("Delete");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					try {
						headDoc.deleteDoctor(tfDoctorUserID.getText());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				Helper.deleteMessage();
				try {
					updateDoctorModel();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		doctorTable.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				if(e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer.parseInt(doctorTable.getValueAt(doctorTable.getSelectedRow(),0).toString());
					String selectIdentify = doctorTable.getValueAt(doctorTable.getSelectedRow(),1).toString();
					String selectName = doctorTable.getValueAt(doctorTable.getSelectedRow(),2).toString();
					String selectPassword = doctorTable.getValueAt(doctorTable.getSelectedRow(),3).toString();
					try {
						headDoc.updateDoctor(selectID,selectIdentify,selectName,selectPassword);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		
		ImageIcon headDoctorImage = new ImageIcon("C:/Users/ymnN/Desktop/eclipse_workspace/java_swing_hospital/src/images/headdoctor.png");
		Image image = headDoctorImage.getImage();
		Image newImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		headDoctorImage = new ImageIcon(newImage);
		
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1_1.setBounds(466, 308, 89, 23);
		w_doctor.add(btnNewButton_1_1);
		
		JLabel headDoctorLabel = new JLabel(headDoctorImage);
		
		headDoctorLabel.setBounds(715, 40, 206, 269);
		w_doctor.add(headDoctorLabel);
		
		
		
		JPanel w_clinic = new JPanel();
		w_clinic.setBackground(Color.DARK_GRAY);
		w_pane.addTab("Clinic Management", null, w_clinic, null);
		w_clinic.setLayout(null);
		
		JLabel label_doctorName_1 = new JLabel("Clinic Name");
		label_doctorName_1.setBounds(360, 10, 133, 23);
		label_doctorName_1.setBackground(Color.WHITE);
		label_doctorName_1.setForeground(Color.WHITE);
		label_doctorName_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		w_clinic.add(label_doctorName_1);
		
		tfClinic = new JTextField();
		tfClinic.setBounds(360, 36, 206, 20);
		tfClinic.setBackground(Color.WHITE);
		tfClinic.setColumns(10);
		w_clinic.add(tfClinic);
		
		JButton btnNewButton_2 = new JButton("Add");
		btnNewButton_2.setBounds(360, 71, 206, 32);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				headDoc.addClinic(tfClinic.getText());
				Helper.addMessage();
				try {
					updateClinicModel();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		w_clinic.add(btnNewButton_2);
		
		JScrollPane clinicScroll = new JScrollPane();
		clinicScroll.setBounds(10, 10, 297, 322);
		w_clinic.add(clinicScroll);
		
		 clinicMenu = new JPopupMenu();
		 updateMenu = new JMenuItem("Update");
		 deleteMenu = new JMenuItem("Delete");
		 clinicMenu.add(updateMenu);
		 clinicMenu.add(deleteMenu);
		 

		
		clinicTable = new JTable(clinicModel);
		clinicTable.setComponentPopupMenu(clinicMenu);
		clinicScroll.setViewportView(clinicTable);
		clinicTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				point = e.getPoint();
				int selectedRow = clinicTable.rowAtPoint(point);
				clinicTable.setRowSelectionInterval(selectedRow, selectedRow);
				
			}
		});
		 updateMenu.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 int selectedID = Integer.parseInt(clinicTable.getValueAt(clinicTable.getSelectedRow(),0).toString());
				 Clinic selectClinic = clinic.getFetch(selectedID);
				 UpdateClinicGUI updateGUI = new UpdateClinicGUI(selectClinic);
				 updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				 updateGUI.setVisible(true);
				 updateGUI.addWindowListener(new WindowAdapter(){
					 @Override
					 public void windowClosed(WindowEvent e) {
						 try {
							 updateClinicModel();
						 } catch(SQLException e1) {
							 e1.printStackTrace();
						 }
					 }
				 });
				 
			 }
		 });
		 
		 deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int selectedID = Integer.parseInt(clinicTable.getValueAt(clinicTable.getSelectedRow(),0).toString());
				clinic.deleteClinic(selectedID);
				Helper.deleteMessage();
				try {
					updateClinicModel();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			 
		 });
		
		JScrollPane employeeScroll = new JScrollPane();
		employeeScroll.setBounds(609, 10, 364, 322);
		w_clinic.add(employeeScroll);
		
		employeeTable = new JTable();
		employeeScroll.setViewportView(employeeTable);
		
		JComboBox comboDoctorName = new JComboBox();
		comboDoctorName.setBounds(360, 254, 206, 32);
		for(int i = 0; i < headDoc.getDoctorList().size(); i++) {
			comboDoctorName.addItem(new Item(headDoc.getDoctorList().get(i).getId(),headDoc.getDoctorList().get(i).getName()));
		}
		comboDoctorName.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getKey() + " : " + item.getValue());
		});
		w_clinic.add(comboDoctorName);
		
		JButton btnNewButton_2_1 = new JButton("Add");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = clinicTable.getSelectedRow();
				if(selectedRow >= 0) {
					int selectedClinicID = Integer.parseInt(clinicTable.getModel().getValueAt(selectedRow,0).toString()); 
					Item doctorItem = (Item) comboDoctorName.getSelectedItem();
					boolean control = headDoc.addEmployee(doctorItem.getKey(), selectedClinicID);
					if(control)
						Helper.addMessage();
				}
			}
		});
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_2_1.setBounds(360, 296, 206, 32);
		w_clinic.add(btnNewButton_2_1);
		
		JLabel label_doctorName_1_1 = new JLabel("Clinic Name");
		label_doctorName_1_1.setForeground(Color.WHITE);
		label_doctorName_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_doctorName_1_1.setBackground(Color.WHITE);
		label_doctorName_1_1.setBounds(360, 138, 133, 23);
		w_clinic.add(label_doctorName_1_1);
		
		JButton buttonSelectClinic = new JButton("Select");
		buttonSelectClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectRow = clinicTable.getSelectedRow();
				if(selectRow >= 0) {
					int selectClinic = Integer.parseInt(clinicTable.getModel().getValueAt(selectRow, 0).toString());
					
					DefaultTableModel clearModel = (DefaultTableModel) employeeTable.getModel();
					clearModel.setRowCount(0);
					for(int i = 0; i < headDoc.getClinicDoctorList(selectClinic).size(); i++) {
						workerData[0] = headDoc.getClinicDoctorList(selectClinic).get(i).getId();
						workerData[1] = headDoc.getClinicDoctorList(selectClinic).get(i).getName();
						workerModel.addRow(workerData);
						employeeTable.setModel(workerModel);
					}
				}
				
			}
		});
		buttonSelectClinic.setFont(new Font("Tahoma", Font.BOLD, 12));
		buttonSelectClinic.setBounds(360, 174, 206, 32);
		w_clinic.add(buttonSelectClinic);
	}
	public void updateDoctorModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) doctorTable.getModel();
		clearModel.setRowCount(0);
		for(int i = 0; i < headDoctor.getDoctorList().size(); i++ ) {
			doctorData[0] = headDoctor.getDoctorList().get(i).getId();
			doctorData[1] = headDoctor.getDoctorList().get(i).getId_number();
			doctorData[2] = headDoctor.getDoctorList().get(i).getName();
			doctorData[3] = headDoctor.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
	}
	public void updateClinicModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) clinicTable.getModel();
		clearModel.setRowCount(0);
		for(int i = 0; i < clinic.getList().size(); i++) {
			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();
			clinicModel.addRow(clinicData);
		}
		
		
	}
}
