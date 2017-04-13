package com.model.dao;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;


import com.model.User;

public class UserDAO {

	private static UserDAO instance;
	private static final HashMap<String, User> allUsers = new HashMap<>();//username - > user
	
	private UserDAO(){		
		try {
			getAllUsers();
		} catch (SQLException e) {
			System.out.println("userdao"+e.getMessage());
		}		
	}
	
	public static synchronized UserDAO getInstance(){
		if(instance == null){
			instance = new UserDAO();
		}
		return instance;
	}

	public synchronized void addUser(User u) throws SQLException {			
		
		String sql = "INSERT INTO users (username, password,email, role) values (?, md5(?),?, ?)";
		System.out.println(u);
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		st.setString(1, u.getUsername());
		st.setString(2, u.getPassword());		
		st.setString(3, u.getEmail());
		st.setInt(4, 1);
		st.executeUpdate();
		ResultSet res = st.getGeneratedKeys();
		res.next();
		int userId =res.getInt(1);
		u.setUserId(userId);
		allUsers.put(u.getUsername(), u);
	}
	
	private HashMap<String, User> getAllUsers() throws SQLException{
		if(allUsers.isEmpty()){
			String sql = "SELECT user_id, username, password, email FROM users;";
			PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while(res.next()){
				User u =new User();
				u.setUsername(res.getString("username"));
				u.setPassword(res.getString("password")); 
				u.setEmail(res.getString("email"));
				int id=res.getInt("user_id");
				u.setUserId(id);
				allUsers.put(u.getUsername(), u);				
			}
		}
		return allUsers;
	}

	
	/*public synchronized boolean validLogin(String username, String password) throws SQLException {
		MessageDigest messageDigest;
		String result=null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(password.getBytes(Charset.forName("UTF8")));
			final byte[] resultByte = messageDigest.digest();
			result = Hex.encodeHexString(resultByte);
			System.out.println(result);
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e.getLocalizedMessage());
		}
		if (allUsers.containsKey(username)) {
			User u=allUsers.get(username);
			return u.getPassword().equals(result);
		}
		return false;
	}
	
	
	public int getUserId (String username) {
		if (allUsers.containsKey(username)) {
			User u=allUsers.get(username);
			int id=u.getId();
			return id;
		}
		return 0;
	}
	
	public User getUser (String username) {
		if (allUsers.containsKey(username)) {
			User u=allUsers.get(username);
			return u;			
		}
		return null;
	}*/
}
