package helper;
import java.sql.*;


public class DBConnection {
	private String url = "jdbc:postgresql://localhost/hospitaldb?user=postgres&password=123gsnuman";
	
	public DBConnection() {}
	
	public Connection connectDB() {
		Connection connect = null;
		try {
			
			connect = DriverManager.getConnection(url);
			return connect;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connect;
	}
}
