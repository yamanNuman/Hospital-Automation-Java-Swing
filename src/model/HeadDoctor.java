 package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import helper.Helper;

public class HeadDoctor extends User{
	
	Connection connect = db.connectDB();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps = null;

	public HeadDoctor(int id, String id_number, String name, String password, String type) {
		super(id, id_number, name, password, type);
		
	}
	
	public HeadDoctor() {}

	public ArrayList<User> getDoctorList () throws SQLException{
		ArrayList<User> list = new ArrayList<>();
		User obj;
		try {
			st = connect.createStatement();
			rs = st.executeQuery("SELECT * FROM users WHERE type='doctor'");
			while(rs.next()) {
				obj = new User(rs.getInt("id"),rs.getString("identify"),rs.getString("name"),rs.getString("password"),rs.getString("type"));
				list.add(obj);	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			//connect.close();
		}
		
		return list;
		
	}
	

	public void deleteDoctor(String text) throws SQLException{
		int id = Integer.parseInt(text);
		String query = "DELETE FROM users WHERE id='"+id+"'";
	try {
		st = connect.createStatement();
		ps = connect.prepareStatement(query);
		st.executeUpdate(query);
	} catch (SQLException e) {
		e.printStackTrace();
	} 
		
	}
	
	public void addDoctor(String identify, String name, String password) {
		try {
			String query = "INSERT INTO users" + "(identify,password,name,type) VALUES " + "(?,?,?,?)";
			st = connect.createStatement();
			ps = connect.prepareStatement(query);
			ps.setString(1, identify);
			ps.setString(2, password);
			ps.setString(3, name);
			ps.setString(4,"doctor");
			ps.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void addClinic(String name) {
		try {
			String query = "INSERT INTO clinic" + "(name) VALUES " + "(?)";
			st = connect.createStatement();
			ps = connect.prepareStatement(query);
			ps.setString(1, name);
			ps.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean updateDoctor(int id,String identify,String name,String password) throws SQLException {
		String query = "UPDATE users SET identify = ?,password = ?,name = ? WHERE id = ?";
		boolean key = false;
		try {
			st = connect.createStatement();
			ps = connect.prepareStatement(query);
			ps.setString(1, identify);
			ps.setString(2,password);
			ps.setString(3, name);
			ps.setInt(4, id);
			ps.executeUpdate();
			key = true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		if(key == true)
			return true;
		else
			return false;
				
	}

	public boolean addEmployee(int userID,int clinicID) {
		String query = "INSERT INTO employee" + "(clinic_id,users_id) VALUES " + "(?,?)";
		boolean key = false;
		int count = 0;
		System.out.print(userID);
		System.out.print(clinicID);
		try {
			connect = db.connectDB();
			st = connect.createStatement();
			rs = st.executeQuery("SELECT * FROM employee WHERE clinic_id=" + clinicID + " AND users_id=" + userID);
			while(rs.next()) {
				count++;
			}
			if(count == 0) {
				ps = connect.prepareStatement(query);
				ps.setInt(1, clinicID);
				ps.setInt(2, userID);
				ps.executeUpdate();
			}
			
			key = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(key == true)
			return true;
		else
			return false;
		
	}

	public ArrayList<User> getClinicDoctorList(int clinicID) {
		ArrayList<User> list = new ArrayList<>();
		User obj;
		try {
			connect = db.connectDB();
			st = connect.createStatement();
			rs = st.executeQuery("SELECT u.* FROM employee e LEFT JOIN users u ON e.users_id = u.id WHERE clinic_id = " + clinicID);
			while(rs.next()) {
				obj = new User(rs.getInt("id"),rs.getString("identify"),rs.getString("name"),rs.getString("password"),rs.getString("type"));
				list.add(obj);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
