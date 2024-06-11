package TOYS.dto;

public class User {
	private int userId;
	private  String userName;
	private String userEmail;
	private long userPhone;
	private int userAge;
	private String userAddress;
	private double userWallet;
	private String userPassword;
	public int getUserid() {
		return userId;
	}
	public void setUserid(int userid) {
		this.userId = userid;
	}
	public String getUsername() {
		return userName;
	}
	public void setUsername(String username) {
		this.userName = username;
	}
	public String getUseremail() {
		return userEmail;
	}
	public void setUseremail(String useremail) {
		this.userEmail = useremail;
	}
	public long getUserphone() {
		return userPhone;
	}
	public void setUserphone(long userphone) {
		this.userPhone = userphone;
	}
	public int getUserage() {
		return userAge;
	}
	public void setUserage(int userage) {
		this.userAge = userage;
	}
	public String getUseraddress() {
		return userAddress;
	}
	public void setUseraddress(String useraddress) {
		this.userAddress = useraddress;
	}
	public double getUserwallet() {
		return userWallet;
	}
	public void setUserwallet(double userwallet) {
		this.userWallet = userwallet;
	}
	public String getUserpassword() {
		return userPassword;
	}
	public void setUserpassword(String userpassword) {
		this.userPassword = userpassword;
	}
	public User(String username, String useremail, long userphone, String useraddress,
			double userwallet, String userpassword) {
		this.userName = username;
		this.userEmail = useremail;
		this.userPhone = userphone;
		this.userAge = userAge;
		this.userAddress = useraddress;
		this.userWallet = userwallet;
		this.userPassword = userpassword;
	}
	
	public User(int userid, String username, String useremail, long userphone, String useraddress,
			double userwallet, String userpassword) {
		super();
		this.userId = userid;
		this.userName = username;
		this.userEmail = useremail;
		this.userPhone = userphone;
		this.userAge = userAge;
		this.userAddress = useraddress;
		this.userWallet = userwallet;
		this.userPassword = userpassword;
	}
	
	
	public String toString() {
		return "User [userid=" + userId + ", username=" + userName + ", useremail=" + userEmail + ", userphone="
				+ userPhone + ", userage=" + userAge + ", useraddress=" + userAddress + ", userwallet=" + userWallet
				+ ", userpassword=" + userPassword + ", getUserid()=" + getUserid() + ", getUsername()=" + getUsername()
				+ ", getUseremail()=" + getUseremail() + ", getUserphone()=" + getUserphone() + ", getUserage()="
				+ getUserage() + ", getUseraddress()=" + getUseraddress() + ", getUserwallet()=" + getUserwallet()
				+ ", getUserpassword()=" + getUserpassword() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	

}
