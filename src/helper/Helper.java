package helper;

import javax.swing.JOptionPane;

public class Helper {
	
	public static void showMessage(String str) {
		String msg;
		
		if(str == "fill") {
			msg = "Please fill in all fields.";
		} else {
			msg = str;
		}

		JOptionPane.showMessageDialog(null, msg,"Message",JOptionPane.INFORMATION_MESSAGE);
	}
	public static void successfulMessage() {
		JOptionPane.showMessageDialog(null, "Login Successful.");
	}
	public static void loginErrorMessage() {
		JOptionPane.showMessageDialog(null, "You entered wrong information.");
	}
	public static void deleteMessage() {
		JOptionPane.showMessageDialog(null, "Deleted.");
	}
	public static void addMessage() {
		JOptionPane.showMessageDialog(null,"Added");
	}
	public static void updateMessage() {
		JOptionPane.showMessageDialog(null, "Updated");
	}
	public static enum type {
		doctor,headdoctor,patient
	}
}
