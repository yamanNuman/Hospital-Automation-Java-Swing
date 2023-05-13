package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import helper.DBConnection;

public class Clinic {

	private int id;
	private String name;
	Statement st = null;
	ResultSet rs = null;
	DBConnection db = new DBConnection();
	Connection connect;
	PreparedStatement ps = null;
	
	public Clinic(int id,String name) {
		this.id = id;
		this.name = name;
	}
	public Clinic () {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public ArrayList<Clinic> getList() throws SQLException {
		ArrayList<Clinic> list = new ArrayList<>();
		Clinic obj;
		
		try {
			connect = db.connectDB();
			st = connect.createStatement();
			rs = st.executeQuery("SELECT * FROM clinic");
			while(rs.next()) {
				obj = new Clinic(rs.getInt("id"),rs.getString("name"));
				list.add(obj);
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
		}
		
		return list;
		
	}
	public boolean updateClinic(int id,String name) throws SQLException {
		String query = "UPDATE clinic SET name = ? WHERE id = ?";
		boolean key = false;
		try {
			connect = db.connectDB();
			st = connect.createStatement();
			ps = connect.prepareStatement(query);
			ps.setString(1,name);
			ps.setInt(2, id);
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
	public boolean deleteClinic(int id) {
		String query = "DELETE FROM clinic WHERE id='"+id+"'";
		boolean key = false;
		try {
			connect = db.connectDB();
			st = connect.createStatement();
			ps = connect.prepareStatement(query);
			st.executeUpdate(query);
			key = true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		if(key == true)
			return true;
		else
			return false;
	}
	public Clinic getFetch(int id) {
		Clinic c = new Clinic();
		try {
			Connection connect = db.connectDB();
			st = connect.createStatement();
			rs = st.executeQuery("SELECT * FROM clinic WHERE id=" + id);
			while(rs.next()) {
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				break;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
}
