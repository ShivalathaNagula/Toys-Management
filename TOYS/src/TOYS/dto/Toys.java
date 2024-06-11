package TOYS.dto;

public class Toys {
//	toyId int primary key AUTO_INCREMENT , toyName varchar(45),
//	toyBrand varchar(45) ,toyPrice double , quantity int 
//	, toyEmail varchar(45)
	private int toyId;
	private String toyName;
	private String toyBrand;
	private double toyPrice;
	private int quantity;
	private String toyEmail;
	
	
	public int getToyId() {
		return toyId;
	}
	public void setToyId(int toyId) {
		this.toyId = toyId;
	}
	public String getToyName() {
		return toyName;
	}
	public void setToyName(String toyName) {
		this.toyName = toyName;
	}
	public String getToyBrand() {
		return toyBrand;
	}
	public void setToyBrand(String toyBrand) {
		this.toyBrand = toyBrand;
	}
	public double getToyPrice() {
		return toyPrice;
	}
	public void setToyPrice(double toyPrice) {
		this.toyPrice = toyPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getToyEmail() {
		return toyEmail;
	}
	public void setToyEmail(String toyEmail) {
		this.toyEmail = toyEmail;
	}
//	public Toys(int toyId) {
//		super();
//		this.toyId = toyId;
//		this.toyName = toyName;
//		this.toyBrand = toyBrand;
//		this.toyPrice = toyPrice;
//		this.quantity = quantity;
//		this.toyEmail = toyEmail;
//	}
	public Toys(String toyName, String toyBrand, double toyPrice, int quantity, String toyEmail) {
		super();
		this.toyId = toyId;
		this.toyName = toyName;
		this.toyBrand = toyBrand;
		this.toyPrice = toyPrice;
		this.quantity = quantity;
		this.toyEmail = toyEmail;
	}
	
	
	
	public Toys(int toyId2) {
		this.toyId=toyId2;
	}
	
	
	public Toys(int toyId1, String toyName2, String toyBrand2, double toyPrice2, int quantity2) {
		super();
		this.toyId = toyId1;
		this.toyName = toyName2;
		this.toyBrand = toyBrand2;
		this.toyPrice = toyPrice2;
		this.quantity = quantity2;
		
	}
	
	public Toys(int toyId2, String toyName2, String toyBrand2, double toyPrice2, int quantity2, String toyEmail2) {
		this.toyId = toyId2;
		this.toyName = toyName2;
		this.toyBrand = toyBrand2;
		this.toyPrice = toyPrice2;
		this.quantity = quantity2;
		this.toyEmail=toyEmail2;
		
	}
	public String toString() {
		return "Toys [toyId=" + toyId + ", toyName=" + toyName + ", toyBrand=" + toyBrand + ", toyPrice=" + toyPrice
				+ ", quantity=" + quantity + ", toyEmail=" + toyEmail + ", getToyId()=" + getToyId() + ", getToyName()="
				+ getToyName() + ", getToyBrand()=" + getToyBrand() + ", getToyPrice()=" + getToyPrice()
				+ ", getQuantity()=" + getQuantity() + ", getToyEmail()=" + getToyEmail() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	

}
