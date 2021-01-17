package com.SQLiteConnect.myPro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import com.pharmacy.nam.itemTemplate;
import com.pharmacy.nam.users;
import com.pharmacy.nam.usersSearch;

//use this class as interface and manipulate with DB directly  
public class DBconnection {

	static Connection cn;

	public static usersSearch um = new usersSearch();
	
	//for make connect
	public static Connection connect() {

		try {
			Class.forName("org.sqlite.JDBC");

			// create a connection to the database
			Connection cn = DriverManager.getConnection("jdbc:sqlite:/D:/JAVA Training 2019/Workspace2/PharmacySW/DB/pharmacyDB.db");
			//JOptionPane.showMessageDialog(null, "Connection established.");
			return cn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}

	//bring user data from DB
	public static users login(String username, String pw) {
		ResultSet rs = null;
		
		try {
			cn = connect();
			String sql = "select * from Users where UserName = ? and Password = ?";
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.setString(1, username.trim());
			pst.setString(2, pw.trim());
			rs = pst.executeQuery();
			if (!rs.equals(null)) {	
				if (!rs.getString("UserName").isEmpty() || !rs.getString("UserName").equals(null))
				{
					//put the data to users class
					users u = new users();
					u.Set_UserId(rs.getInt("UserId"));
					u.Set_FullName(rs.getString("FullName"));
					u.Set_UserName(rs.getString("UserName"));
					u.Set_Password(rs.getString("Password"));

					u.Set_IsAdmin(rs.getInt("IsAdmin"));
					u.Set_IsUser(rs.getInt("IsUser"));

					rs.close();
//					if (!pst.isClosed()) pst.close();
//		        	if (!cn.isClosed()) cn.close();
					return u;
				} else {
					JOptionPane.showMessageDialog(null, "User doesnt exist");
					return null;
				}

			} else {
				JOptionPane.showMessageDialog(null, "User doesnt exist");
				return null;
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "The User is not register \n" + e1);
			return null;
		}
	}

	//for get all items form DB
	public static ResultSet LoadData() {
		ResultSet rs = null;
		try {
			cn = connect();

			String sql = "select Items.ID, Items.ItemName as 'Item Name', Items.Price as 'Price/IQD', Items.Qantity, MadeBy.Country, Items.ExpireDate as 'Expire Date', Users.FullName as 'User Full Name', Items.EntryDate as 'Entry datetime' from Items inner join Users on Users.UserId = Items.EntryUserId inner join MadeBy on MadeBy.MadeID = Items.Made order by Items.ItemName asc";
			Statement st = cn.createStatement();
			rs = st.executeQuery(sql);

			if (!rs.equals(null)) {
				return rs;
			} else {
				JOptionPane.showMessageDialog(null, "No Data for Loading !!!");
				return null;
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
			return null;
		}
	}

	//bring all countries from DB
	public static ResultSet LoadCountry() {
		ResultSet rs = null;
		try {
			cn = connect();
			String sql = "select MadeID,Country from MadeBy order by Country asc";
			PreparedStatement st = cn.prepareStatement(sql);
			rs = st.executeQuery();

			if (!rs.equals(null)) {
				return rs;
			} else {
				JOptionPane.showMessageDialog(null, "No Data for Loading !!!");
				return null;
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
			return null;
		}
	}

	//for insert new item to DB
	public static boolean InsertItem() {
		try {
			String sql = "insert into Items (ItemName,Price,Qantity,Made,ExpireDate,EntryUserId,EntryDate) values (?,?,?,?,?,?,?)";
			Connection con = connect();
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, itemTemplate.get_ItemName());
			pst.setDouble(2, itemTemplate.get_Price());
			pst.setInt(3, itemTemplate.get_Qantity());
			pst.setInt(4, itemTemplate.get_Country());
			pst.setString(5, itemTemplate.get_ExpireDate());
			pst.setInt(6, itemTemplate.get_EntryUserid());
			pst.setString(7, itemTemplate.get_EntryDate().toString());
			pst.execute();

			// pst.close();
//			if (!pst.isClosed()) pst.close();
//        	if (!con.isClosed()) con.close();
			JOptionPane.showMessageDialog(null, "Added successfully !");
			return true;
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
			return false;
		}
	}

	//for insert item as history to DB
	public static boolean InsertItem(int id) {
		try {
			String sql = "insert into History (ID, ItemName,Price,Qantity,Made,ExpireDate,EntryUserId,EntryDate) select ID, ItemName,Price,Qantity,Made,ExpireDate,EntryUserId,EntryDate from Items where ID = ?";
			Connection con = connect();
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			pst.execute();

			//JOptionPane.showMessageDialog(null, "Added to history successfully !");
			return true;
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,"Added to history: " + e1);
			return false;
		}
	}

	//get country id by country name
	public static int getCountryId(String country) {
		ResultSet rs = null;
		try {
			cn = connect();
			String sql = "select MadeID from MadeBy where Country = '" + country + "' limit 1";
			Statement st = cn.createStatement();
			rs = st.executeQuery(sql);

			if (!rs.equals(null)) {
				int id = rs.getInt("MadeID");
				if (!st.isClosed())
					st.close();
				if (!cn.isClosed())
					cn.close();

				return id;
			} else {
				JOptionPane.showMessageDialog(null, "No Data for Loading !!!");
				return 0;
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
			return 0;
		}
	}

	//get country name by country id
	public static String getCountryId(int madeId) {
		ResultSet rs = null;
		try {
			cn = connect();
			String sql = "select Country from MadeBy where MadeID = " + madeId + " limit 1";
			Statement st = cn.createStatement();
			rs = st.executeQuery(sql);

			if (!rs.equals(null)) {
				String s = rs.getString("Country").toString();
				if (!st.isClosed())
					st.close();
				if (!cn.isClosed())
					cn.close();

				return s;
			} else {
				JOptionPane.showMessageDialog(null, "No Data for Loading !!!");
				return "";
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
			return "";
		}
	}

	//delete or update item from DB
	public static boolean UpdateDeleteItem(int op, int id) {
		String sql = "";
		try {
			if (op == 1) {
				sql = "update Items set ItemName = ?, Price = ? , Qantity = ? , Made = ?, ExpireDate = ?,EntryUserId = ?,EntryDate = ? where ID = ?";
				Connection con = connect();
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, itemTemplate.get_ItemName());
				pst.setDouble(2, itemTemplate.get_Price());
				pst.setInt(3, itemTemplate.get_Qantity());
				pst.setInt(4, itemTemplate.get_Country());
				pst.setString(5, itemTemplate.get_ExpireDate());
				pst.setInt(6, itemTemplate.get_EntryUserid());
				pst.setString(7, itemTemplate.get_EntryDate().toString());
				pst.setInt(8, id);

				pst.execute();
				JOptionPane.showMessageDialog(null, "Update successfully !");
			} else if (op == 2) {

				sql = "delete from Items where ID = ?";
				Connection con = connect();
				PreparedStatement pst = con.prepareStatement(sql);

				pst.setInt(1, id);

				pst.execute();
				JOptionPane.showMessageDialog(null, "Delete successfully !");
			}

			// pst.close();
//		if (!pst.isClosed()) pst.close();
//    	if (!con.isClosed()) con.close();

			return true;
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
			return false;
		}
	}

	//get user data by id
	public static usersSearch login(int Id) {
		try {
			cn = connect();
			String sql = "select * from Users where UserId = ?";
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.setInt(1, Id);
			ResultSet rs = pst.executeQuery();
			if (!rs.equals(null)) {
				if (!rs.getString("UserName").isEmpty() || !rs.getString("UserName").equals(null))

				{
					um.Set_UserId(rs.getInt("UserId"));
					um.Set_FullName(rs.getString("FullName"));
					um.Set_UserName(rs.getString("UserName"));
					um.Set_Password(rs.getString("Password"));

					um.Set_IsAdmin(rs.getInt("IsAdmin"));
					um.Set_IsUser(rs.getInt("IsUser"));
					rs.close();
					pst.close();
					return um;
				} else {
					JOptionPane.showMessageDialog(null, "doesnt exist1");
					return null;
				}

			} else {
				JOptionPane.showMessageDialog(null, "doesnt exist2");
				return null;
			}
		} catch (

		SQLException e1) {
			JOptionPane.showMessageDialog(null, "Not Exsits");
			return null;
		}
	}

	//update user info 
	public static boolean updateUserInfo(int id, String fullname, String username, String pass, int admin, int user) {
		try {
			cn = connect();
			String sql = "update Users set FullName = ?,UserName = ?,Password = ?,IsAdmin = ?,IsUser = ? where UserId = ?";
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.setString(1, fullname);
			pst.setString(2, username);
			pst.setString(3, pass);
			pst.setInt(4, admin);
			pst.setInt(5, user);
			pst.setInt(6, id);
			pst.executeUpdate();

			pst.close();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex);
			return false;
		}
		return true;
	}

	//insert new user
	public static boolean InsertUserInfo(String fullname, String username, String pass, int admin, int user) {
		try {
			cn = connect();
			String sql = "insert into Users (FullName,UserName,Password,IsAdmin,IsUser) VALUES(?,?,?,?,?)";
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.setString(1, fullname);
			pst.setString(2, username);
			pst.setString(3, pass);
			pst.setInt(4, admin);
			pst.setInt(5, user);
			pst.execute();

			pst.close();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex);
			return false;
		}
		return true;
	}

	//insert user info as history
	public static boolean TranferToUsersHistory(int Id) {
		try {
			cn = connect();
			String sql = "insert into UsersHistory select * from Users where UserId = ?";
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.setInt(1, Id);
			pst.execute();

			pst.close();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex);
			// JOptionPane.showMessageDialog(null, "Not Deleted");
			return false;
		}
		return true;
	}

	//delete user by id
	public static boolean DeleteUserInfo(int Id) {
		try {
			cn = connect();
			String sql = "delete from Users where UserId = ?";
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.setInt(1, Id);
			pst.execute();

			pst.close();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex);
			// JOptionPane.showMessageDialog(null, "Not Deleted");
			return false;
		}
		return true;
	}
	
	public static ResultSet GetReportAll() {
		ResultSet rs = null;
		try {
			cn = connect();
			String sql = "select * from Items";
			PreparedStatement st = cn.prepareStatement(sql);
			rs = st.executeQuery();

			if (!rs.equals(null)) {
				return rs;
			} else {
				JOptionPane.showMessageDialog(null, "No Data for Loading !!!");
				return null;
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
			return null;
		}
	}
	public static ResultSet GetReportLessQuantity() {
		ResultSet rs = null;
		try {
			cn = connect();
			String sql = "select * from Items where Qantity <= 10 order by Qantity";
			PreparedStatement st = cn.prepareStatement(sql);
			rs = st.executeQuery();

			if (!rs.equals(null)) {
				return rs;
			} else {
				JOptionPane.showMessageDialog(null, "No Data for Loading !!!");
				return null;
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
			return null;
		}
	}
	public static ResultSet GetReportLessExpire() {
		ResultSet rs = null;
		try {
			cn = connect();
			//String sql = "SELECT * from Items where ExpireDate <= strftime('%d/%m/%Y',date('now','+30 day'))";
			String sql = "select * from Items where (substr(ExpireDate,7,4) < substr(strftime('%d/%m/%Y',date('now','+30 day')),7,4)) or (substr(ExpireDate,4,2) <= substr(strftime('%d/%m/%Y',date('now','+30 day')),4,2)) and (substr(ExpireDate,1,2) <= substr(strftime('%d/%m/%Y',date('now','+30 day')),1,2)) or ((substr(ExpireDate,7,4) = substr(strftime('%d/%m/%Y',date('now','+30 day')),7,4)) and (substr(ExpireDate,4,2) < substr(strftime('%d/%m/%Y',date('now','+30 day')),4,2))) or((substr(ExpireDate,7,4) = substr(strftime('%d/%m/%Y',date('now','+30 day')),7,4)) and (substr(ExpireDate,4,2) = substr(strftime('%d/%m/%Y',date('now','+30 day')),4,2)) and (substr(ExpireDate,1,2) <= substr(strftime('%d/%m/%Y',date('now','+30 day')),1,2)))";
			PreparedStatement st = cn.prepareStatement(sql);
			rs = st.executeQuery();

			if (!rs.equals(null)) {
				return rs;
			} else {
				JOptionPane.showMessageDialog(null, "No Data for Loading !!!");
				return null;
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
			return null;
		}
	}

}
