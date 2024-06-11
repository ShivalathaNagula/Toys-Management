package TOYS.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

import TOYS.dto.Toys;
import TOYS.dto.User;



public class UserCrud {

	public Connection createTable() throws SQLException {
		DriverManager.registerDriver(new Driver());
		Connection con = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/toystore?createDatabaseIfNotExist=true", "root", "root");
		Statement s = con.createStatement();
		s.execute("create table if not exists user(userid int primary key AUTO_INCREMENT ,username varchar(45),useremail varchar(45) unique,userphone bigint(10),userage int,useraddress varchar(45),userwallet double,userpassword varchar(45))");
		return con;
	}
	public int registerUser(User user) throws SQLException {
		Connection con=createTable();
		PreparedStatement ps = con.prepareStatement("insert into user(username,useremail,userphone,userage,useraddress,userwallet,userpassword) values(?,?,?,?,?,?,?)");
		ps.setString(1, user.getUsername());
		ps.setString(2,user.getUseremail());
		ps.setLong(3, user.getUserphone());
		ps.setInt(4,user.getUserage());
		ps.setString(5, user.getUseraddress());
		ps.setDouble(6,user.getUserwallet());
		ps.setString(7,user.getUserpassword());
		int res=ps.executeUpdate();
		return res;
		
		
		
	}
	public User fetchUserDetail(String email) throws SQLException {
		Connection con = createTable();
		PreparedStatement ps = con.prepareStatement("select * from user where userEmail = ?");
		ps.setString(1, email);
		ResultSet res = ps.executeQuery();
		if(res.next()) {
			int userId = res.getInt("userId");
			String userName  = res.getString("userName");
			String userEmail = res.getString("userEmail");
			long userPhone = res.getLong("userPhone");
			String address = res.getString("userAddress");
			int wallet  = res.getInt("userWallet");
			String password = res.getString("userPassword");
			
			User user = new User(userId, userName, userEmail, userPhone, address , wallet, password);
			return user;
		}else {
			return null;
		}

	}
	
	
	
	
	
	
	
	public void deleteUserDetail(String useremail) throws SQLException {
		Connection con=createTable();
		PreparedStatement ps = con.prepareStatement("delete from user where userEmail=?");
		ps.setString(1, useremail);
		ps.execute();
		
	}
	
	
	
	
	public User updateUserDetail(User user, int choice,Object o) throws SQLException {
		Connection con = createTable();
		PreparedStatement ps;
		// update user name
		if(choice==1) {
			String name = (String)o;
			ps = con.prepareStatement("update user set username=? where userid=?");
			ps.setString(1, name);
			ps.setString(2, user.getUseremail());
			 ps.executeUpdate();
			 return new User(user.getUserid(), user.getUsername(), user.getUseremail(), user.getUserphone(), user.getUseraddress(), user.getUserwallet() , user.getUserpassword());
			
		}
		//update phone
				else if(choice==3) {
					long phone = (long) o;
					ps = con.prepareStatement("update user set userPhone=? where userEmail=?");
					ps.setLong(1 , phone);
					ps.setString(2, user.getUseremail());
					ps.executeUpdate();
					return new User(user.getUserid(), user.getUsername(), user.getUseremail(), user.getUserphone(), user.getUseraddress(), user.getUserwallet() , user.getUserpassword());
				}
				//update address
				else if(choice ==4) {
					String address = (String) o;
					ps = con.prepareStatement("update user set useraddress=? where userEmail=?");
					ps.setString(1, address);
					ps.setString(2, user.getUseremail());
					ps.executeUpdate();
					return new User(user.getUserid(), user.getUsername(), user.getUseremail(), user.getUserphone(), user.getUseraddress(), user.getUserwallet() , user.getUserpassword());
				}
				//update wallet Value
				else if(choice==5) {
					double wallet = (double) o;
					ps = con.prepareStatement("update user set userwallet=? where userEmail=?");
					ps.setDouble(1, wallet);
					ps.setString(2, user.getUseremail());
					ps.executeUpdate();
					return new User(user.getUserid(), user.getUsername(), user.getUseremail(), user.getUserphone(), user.getUseraddress(), user.getUserwallet() , user.getUserpassword());
				}
				//update password
				else if(choice==6) {
					String password = (String) o;
					ps = con.prepareStatement("update user set userpassword=? where userEmail=?");
					ps.setString(1, password);
					ps.setString(2, user.getUseremail());
					ps.executeUpdate();
					return new User(user.getUserid(), user.getUsername(), user.getUseremail(), user.getUserphone(), user.getUseraddress(), user.getUserwallet() , user.getUserpassword());
				}
				else {
					return null;
				}
	}
	
	
	
	
	public void fetchAllToyDetails() throws Exception {
		Connection con = createTable();
		PreparedStatement ps = con.prepareStatement("select toyId , toyName , toyBrand , toyPrice , Quantity  from toyTable");
//		ps.setString(1, email1);
		ResultSet res = ps.executeQuery();
		System.out.println("\t-----------------------------------------------------------------------------");
		System.out.println("\tTOY ID \t Toy Name \t Toy Brand \t Toy Price \t Quantity ");				
		System.out.println("\t-----------------------------------------------------------------------------");
		while(res.next()) {
			int toyId = res.getInt("toyId");
			String toyName  = res.getString("toyName");
			String toyBrand = res.getString("toyBrand");
			double toyPrice = res.getDouble("toyPrice");
			int quantity = res.getInt("quantity");
			System.out.println("\t" + toyId +"\t" + toyName +"\t\t" + toyBrand +"\t\t" + toyPrice + "\t\t" + quantity );
		}
		System.out.println("\t-------------------------------------------------------------------------------");


		
	}
	public Toys fetchToyDetailWithID(int toyId) throws SQLException {
		Toys toy ;
		Connection con = createTable();
		PreparedStatement ps = con.prepareStatement("select toyId , toyName , toyBrand , toyPrice , Quantity  from toyTable where toyId =?");
		ps.setInt(1, toyId);
		ResultSet res = ps.executeQuery();
		System.out.println("\t-----------------------------------------------------------------------------");
		System.out.println("\tTOY ID \t Toy Name \t Toy Brand \t Toy Price \t Quantity ");				
		System.out.println("\t-----------------------------------------------------------------------------");
		if(res.next()) {
			int toyId1 = res.getInt("toyId");
			String toyName  = res.getString("toyName");
			String toyBrand = res.getString("toyBrand");
			double toyPrice = res.getDouble("toyPrice");
			int quantity = res.getInt("quantity");
			System.out.println("\t" + toyId1 +"\t" + toyName +"\t\t" + toyBrand +"\t\t" + toyPrice + "\t\t" + quantity );
			System.out.println("\t-------------------------------------------------------------------------------");

			return new Toys(toyId1 , toyName , toyBrand , toyPrice , quantity);
		}else {
			return null;
		}

	}
	
	public void addMoneyToWallet(String email,double wallet) throws Exception {
		Connection con = createTable();
		PreparedStatement ps = con.prepareStatement("update user set wallet=? where email=?");
		ps.setDouble(1, wallet);
		ps.setString(2, email);
		
	}
	public void walletData(String useremail, double walletCurrentAmount) throws Exception {
		
		Connection con = createTable();
		PreparedStatement ps = con.prepareStatement("update user set walletCurrentAmount=? where email=?");
		ps.setDouble(1, walletCurrentAmount);
		ps.setString(2, useremail);
	}
	
	
}
