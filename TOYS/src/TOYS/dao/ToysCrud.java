package TOYS.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import TOYS.dto.Admin;
import TOYS.dto.Toys;

public class ToysCrud {

	private static final String String = null;



	public Connection createTable() throws Exception {

		
		
//		 FOREIGN KEY (toyEmail) REFERENCES ADMIN(adminEmail)
		//create table
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/toystore?createDatabaseIfNotExist=true", "root", "root");
		PreparedStatement ps = con.prepareStatement("create table if not exists ToyTable(toyId int primary key AUTO_INCREMENT , toyName varchar(45),toyBrand varchar(45) ,toyPrice double , quantity int , toyEmail varchar(45) )");
		ps.execute();
		return con;
	}

	
	
	//*******************insert toy data***********************
	//******************************************************
	public int toysRegister(Toys toys) throws Exception {
		Connection con=createTable();
		PreparedStatement ps = con.prepareStatement("insert into ToyTable(toyId,toyName,toyBrand,toyPrice,quantity,toyEmail) values(?,?,?,?,?,?)");
		ps.setInt(1,toys.getToyId());
		ps.setString(2, toys.getToyName());
		ps.setString(3, toys.getToyBrand());
		ps.setDouble(4, toys.getToyPrice());
		ps.setInt(5, toys.getQuantity());
		ps.setString(6,toys.getToyEmail());
		int res=ps.executeUpdate();
		return res;
		
	}


//************************fetch toy data*******************
	//*******************************************************
	public void fetchToyDetail(String email1) throws Exception {
		Connection con = createTable();
		PreparedStatement ps = con.prepareStatement("select * from ToyTable where toyEmail=?");
		ps.setString(1,email1);
		ResultSet res = ps.executeQuery();
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("TOY ID \t Toy Name \t Toy Brand \t Toy Price \t Quantity \t Toy Email ");				
		System.out.println("-----------------------------------------------------------------------------------------------");
		while(res.next()) {
			int toyId = res.getInt("toyId");
			String toyName  = res.getString("toyName");
			String toyBrand = res.getString("toyBrand");
			double toyPrice = res.getDouble("toyPrice");
			int quantity = res.getInt("quantity");
			String toyEmail = res.getString("toyEmail");
			System.out.println(toyId +"\t" + toyName +"\t\t" + toyBrand +"\t\t" + toyPrice + "\t\t" + quantity +"\t\t" + toyEmail);	
		}	
	}



	public void deleteToyDetail(int toyId) throws Exception {
		Connection con=createTable();
		PreparedStatement ps = con.prepareStatement("delete from ToyTable where toyId=?");
		ps.setInt(1, toyId);
		ps.execute();
	}



	//insert toy date
	public int insertToyDetail(Toys toys, Admin admin) throws Exception {
		Connection con = createTable();
		PreparedStatement ps = con.prepareStatement("insert into toyTable( toyName ,toyBrand , toyPrice , quantity ,toyEmail) values(? , ?  , ?  , ? , ?)");
		ps.setString(1, toys.getToyName());
		ps.setString(2, toys.getToyBrand());
		ps.setDouble(3, toys.getToyPrice());
		ps.setInt(4, toys.getQuantity());
		ps.setString(5, admin.getAdminEmail());
		
		int result = ps.executeUpdate();
		return result;
	}



	public Toys updateToyDetail(Toys toy, int choice, Object o) throws Exception {
		Connection con = createTable();
		PreparedStatement ps;
		// update name
		if(choice==1) {
			
			String name = (String)o;
			ps = con.prepareStatement("update toyTable set toyName=? where toyId=?");
			ps.setString(1, name);
			ps.setInt(2, toy.getToyId());
			 ps.executeUpdate();
			 return new Toys(toy.getToyId(), toy.getToyName(), toy.getToyBrand(), toy.getToyPrice(), toy.getQuantity(), toy.getToyEmail());	
		}
		
		
		//update brand name
		else if(choice==2) {
			String brandName = (String) o;
			ps = con.prepareStatement("update toyTable set toyBrand=? where toyId=?");
			ps.setString(1, brandName);
			ps.setInt(2, toy.getToyId());
			ps.executeUpdate();
			return new Toys(toy.getToyId(), toy.getToyName(), toy.getToyBrand(), toy.getToyPrice(), toy.getQuantity(), toy.getToyEmail());
	
		}
		
		//toyPrice
		else if(choice==3) {
			double price =  (double) o;
			ps = con.prepareStatement("update toyTable set toyPrice=? where toyId=?");
			ps.setDouble(1 , price);
			ps.setInt(2, toy.getToyId());
			ps.executeUpdate();
			return new Toys(toy.getToyId(), toy.getToyName(), toy.getToyBrand(), toy.getToyPrice(), toy.getQuantity(), toy.getToyEmail());
		
			
		}
		
		//quantity
		else if(choice==4) {
			int quantity=(int) o;
			ps=con.prepareStatement("update ToyTable set quantity=? where toyId=?");
			ps.setInt(1, quantity);
			ps.setInt(2, toy.getToyId());
			ps.executeUpdate();
			return new Toys(toy.getToyId(), toy.getToyName(), toy.getToyBrand(), toy.getToyPrice(), toy.getQuantity(), toy.getToyEmail());
		

			
		}
		else {
		return null;
		}
	
		
	}



	




//	public int fetchToyDetail(Toys toys, Admin admin) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
	
	
	
	

}
