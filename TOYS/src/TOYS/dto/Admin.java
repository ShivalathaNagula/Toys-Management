package TOYS.dto;

public class Admin {
	private int adminId;
	private String adminName;
	private String adminEmail;
	private long adminPhone;
	private String toyStore;
	private String password;
	
	
	

	public Admin(int adminId, String adminName, String adminEmail, long adminPhone, String toyStore, String password) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminEmail = adminEmail;
		this.adminPhone = adminPhone;
		this.toyStore = toyStore;
		this.password = password;
	}
	
	
	public Admin(String name, String email, long phone, String toyStoreName, String password) {
		this.adminName = name;
		this.adminEmail = email;
		this.adminPhone = phone;
		this.toyStore = toyStoreName;
		this.password = password;
	}
	
	
	
	
	
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public long getAdminPhone() {
		return adminPhone;
	}
	public void setAdminPhone(long adminPhone) {
		this.adminPhone = adminPhone;
	}
	public String getToyStore() {
		return toyStore;
	}
	public void setToyStore(String toyStore) {
		this.toyStore = toyStore;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", adminEmail=" + adminEmail + ", adminPhone="
				+ adminPhone + ", toyStore=" + toyStore + ", password=" + password + ", getAdminId()=" + getAdminId()
				+ ", getAdminName()=" + getAdminName() + ", getAdminEmail()=" + getAdminEmail() + ", getAdminPhone()="
				+ getAdminPhone() + ", getToyStore()=" + getToyStore() + ", getPassword()=" + getPassword()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	

}
