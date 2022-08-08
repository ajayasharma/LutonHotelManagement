package DatabaseLayer;

import Models.User;
import Helper.DatabaseConnector;
import java.sql.Connection;
//import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DLUser {
	private User user;
	
	public DLUser() {
		this.user = new User();
	}
	
	public DLUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public User save() throws Exception {
		try {
			// get the connection
			DatabaseConnector db = DatabaseConnector.getInstance();
			Connection con = db.getConnection();
			// prepare for the data to be returned in case of insert
			String generatedColumns[] = {"id"};
			// create the statement
			String query = "INSERT INTO user(name, address) VALUES(?,?)";
			PreparedStatement statement = con.prepareStatement(query, generatedColumns);
			statement.setString(1, this.user.getName());
			statement.setString(2, this.user.getAddress());
			// execute the query
			int noOfUpdate = statement.executeUpdate();
			if(noOfUpdate>0) {
				ResultSet rs = statement.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					this.user.setUserId(id);
				}	
			}
			return this.user;
		}catch(Exception ex) {
			throw ex;
		}
	}
	
	public User update() throws Exception {
		try {
			// get the connection
			DatabaseConnector db = DatabaseConnector.getInstance();
			Connection con = db.getConnection();
			// create the statement
			String query = "UPDATE user  SET name=? , address=? WHERE id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, this.user.getName());
			statement.setString(2, this.user.getAddress());
			statement.setInt(3, user.getUserId());
			// execute the query
			statement.executeUpdate();
			return this.user;
		}catch(Exception ex) {
			throw ex;
		}
	}
	
	public void delete() throws Exception {
		try {
			// get the connection
			DatabaseConnector db = DatabaseConnector.getInstance();
			Connection con = db.getConnection();
			// create the statement
			String query = "DELETE FROM user WHERE id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, user.getUserId());
			// execute the query
			statement.executeUpdate();
		}catch(Exception ex) {
			throw ex;
		}
	}
	/*
	public ArrayList<User> getAllUser() throws Exception {
		
	}*/

}
