package DatabaseLayer;

import Models.User;
import Helper.DatabaseConnector;
import java.sql.Connection;
//import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DLUser {
	private User user;
	private DatabaseConnector db;
	private Connection connection;
	
	
	public DLUser() throws Exception {
		this.user = new User();
		try {
			this.db = DatabaseConnector.getInstance();
			this.connection = this.db.getConnection();
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	public DLUser(User user) throws Exception {
		this.user = user;
		try {
			this.db = DatabaseConnector.getInstance();
			this.connection = this.db.getConnection();
		} catch (Exception ex) {
			throw ex;
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public User save() throws Exception {
		try {
			// prepare for the data to be returned in case of insert
			String generatedColumns[] = {"id"};
			// create the statement
			String query = "INSERT INTO user(name, address) VALUES(?,?)";
			PreparedStatement statement = this.connection.prepareStatement(query, generatedColumns);
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
			// create the statement
			String query = "UPDATE user  SET name=? , address=? WHERE id=?";
			PreparedStatement statement = this.connection.prepareStatement(query);
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
			// create the statement
			String query = "DELETE FROM user WHERE id=?";
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setInt(1, user.getUserId());
			// execute the query
			statement.executeUpdate();
		}catch(Exception ex) {
			throw ex;
		}
	}
	
	public ArrayList<User> getAllUser() throws Exception {
		try {
			ArrayList<User> users = new ArrayList<User>();
			String query = "SELECT * FROM user ORDER BY name";
			Statement statement = this.connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()) {
				User u = new User();
				u.setUserId(rs.getInt("id"));
				u.setName(rs.getString("name"));
				u.setAddress(rs.getString("address"));
				users.add(u);
			}
			return users;
		}
		catch(Exception ex) {
			throw ex;
		}
	}
	
	public ArrayList<User> searchUser(String[] keys, String[] values) throws Exception{
		ArrayList<User> users = new ArrayList<User>();
		try {
			int keyLength = keys.length;
			String where = "";
			for(int i=0; i<keyLength; ++i) {
				if(i==0) {
					where = where+" WHERE "+ keys[i]+" LIKE '%"+values[i]+"%' ";
				}else {
					where = where+" AND "+ keys[i]+" LIKE '%"+values[i]+"%' ";
				}
			}
			String query = "SELECT * FROM user"+where+" ORDER BY name";
			Statement statement = this.connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()) {
				User u = new User();
				u.setUserId(rs.getInt("id"));
				u.setName(rs.getString("name"));
				u.setAddress(rs.getString("address"));
				users.add(u);
			}
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
		return users;
		
	}

}
