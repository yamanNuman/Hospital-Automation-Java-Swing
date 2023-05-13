package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import helper.Helper;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import model.Clinic;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UpdateClinicGUI extends JFrame {

	private JPanel w_pane;
	private JTextField tfClinicName;
	private static Clinic clinic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateClinicGUI frame = new UpdateClinicGUI(clinic);
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
	public UpdateClinicGUI(Clinic clinic) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 225, 150);
		w_pane = new JPanel();
		w_pane.setBackground(Color.DARK_GRAY);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel labelClinicName = new JLabel("Clinic Name");
		labelClinicName.setForeground(Color.WHITE);
		labelClinicName.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelClinicName.setBounds(10, 10, 169, 20);
		w_pane.add(labelClinicName);
		JButton buttonClinicUpdate = new JButton("Update");

		
		tfClinicName = new JTextField();
		tfClinicName.setBounds(10, 40, 169, 21);
		tfClinicName.setText(clinic.getName());
		
		w_pane.add(tfClinicName);
		tfClinicName.setColumns(10);
		
		buttonClinicUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					clinic.updateClinic(clinic.getId(),tfClinicName.getText());
					Helper.updateMessage();
					dispose();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		

		buttonClinicUpdate.setFont(new Font("Tahoma", Font.BOLD, 12));
		buttonClinicUpdate.setBounds(10, 71, 169, 32);
		w_pane.add(buttonClinicUpdate);
	}
}
