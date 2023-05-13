package model;
import helper.DBConnection;

public class User {
	protected int id;
	protected String id_number;
	public String name;
	protected String password;
	protected String type;
	DBConnection db = new DBConnection();
	
	
	public User(int id,String id_number,String name,String password,String type) {
		this.id = id;
		this.id_number = id_number;
		this.name = name;
		this.password = password;
		this.type = type;
	}
	
	public User() {}
	
	public String getId_number() {
		return id_number;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
