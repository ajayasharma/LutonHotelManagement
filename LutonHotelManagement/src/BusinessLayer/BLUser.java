package BusinessLayer;
// This class uses the user model to receive and send data to the database layer
// This class uses the user model to receive data from the frontend layer
import Models.User;

import java.util.ArrayList;

import DatabaseLayer.DLUser;
import Helper.InputException;

public class BLUser {
	User user;
	
	public BLUser() {
		this.user = new User();
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) throws InputException {
		try {
			if(this.validateUser(user)) {
				this.user = user;
			}
		}catch(InputException ex) {
			throw ex;
		}
	}
	
	private boolean validateUser(User user) throws InputException {
		if(user.getName() == null || user.getName().length() == 0) {
			throw new InputException("User name cannot be empty.");
		}
		if(user.getAddress() == null || user.getAddress().length() == 0) {
			throw new InputException("User address cannot be empty.");
		}
		return true;
	}
	
	// save the user, update the user, delete the user, getUserList
	
	public User save() throws Exception {
		// This function saves the user detail to database and returns the user object after saving
		try {
			DLUser dlUser = new DLUser(this.user);
			return dlUser.save();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public User update() throws Exception {
		// This function saves the user detail to database and returns the user object after saving
		try {
			DLUser dlUser = new DLUser(this.user);
			return dlUser.update();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void delete() throws Exception {
		// This function saves the user detail to database and returns the user object after saving
		try {
			DLUser dlUser = new DLUser(this.user);
			dlUser.delete();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public ArrayList<User> getAllUser() throws Exception {
		try {
			DLUser dlUser = new DLUser(this.user);
			return dlUser.getAllUser();
		}catch(Exception e) {
			throw e;
		}
	}
	
	public ArrayList<User> searchUser(String[] keys, String[] values) throws Exception{
		try {
			DLUser dlUser = new DLUser(this.user);
			return dlUser.searchUser(keys, values);
		}catch(Exception e) {
			throw e;
		}
	}
}
