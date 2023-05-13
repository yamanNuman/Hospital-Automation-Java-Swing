package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import helper.Helper;

public class Doctor extends User{
	Statement st = null;
	ResultSet rs = null;
	Connection connect = null;
	PreparedStatement ps = null;
	
	public Doctor(int id,String id_number,String name,String password,String type) {
		super(id, id_number, name, password, type);
	}
	public Doctor() {
		super();
	}
	
}
