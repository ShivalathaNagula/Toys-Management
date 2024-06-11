package TOYS.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import TOYS.dao.AdminCrud;
import TOYS.dao.ToysCrud;
import TOYS.dao.UserCrud;
import TOYS.dto.Admin;
import TOYS.dto.Toys;
import TOYS.dto.User;

public class ToyStoreManagement {

	static Scanner scan = new Scanner(System.in);
	static ToysCrud crud = new ToysCrud();
	static AdminCrud crud1 = new AdminCrud();
	static UserCrud crud2 = new UserCrud();

	public static void main(String[] args) throws Exception {
		crud.createTable();
		crud1.createTable();
		crud2.createTable();
		System.out.println("----------------------------------------------------------------------------");
		System.out.println("-******************Welcome to Toy Store Management*************************-");
		System.out.println("----------------------------------------------------------------------------");

		toyMain();

	}

	// boolean value=true;
	// do {
	//
	// ****************
	// *************************************************
	public static void toyMain() throws Exception {
		boolean value=true;
		do {
			System.out.println("\n 1.Admin \n 2.User \n 3.Exit");
			int choice=scan.nextInt();

			if(choice==1) {
				admin();
			}else if(choice==2) {
				user();
			}else {
				System.out.println("\t~~~~~~~~~~~~~~~~ThankYou Visit Again~~~~~~~~~~~~~~~~~~");
				value=false;
			}
		}while(value);

	}

	// ***************************************************
	// *****************************************************
	// *****************adminpage**************************
	// ***************************************************
	// ******************************************************
	public static void admin() throws Exception {
		System.out.println("\t----------Welcome to Admin Page-------------");
		boolean check = true;
		do {
			System.out.println("\tEnter Choice : \n\t 1.Registration \n\t 2.Login \n\t 3.Exit");
			int choice = scan.nextInt();
			switch (choice) {
			case 1: {
				adminRegister();
			}
				break;
			case 2: {
				adminLogin();
			}
				break;
			case 3: {
				toyMain();
			}
				break;
			default: {
				toyMain();
			}
			}
		} while (check);
	}

	public static void adminRegister() throws Exception {
		int data = 0;

		System.out.println("enter adminName");
		String name = scan.next();
		System.out.println("enter adminEmail");
		String email = scan.next();
		System.out.println("enter adminPhone");
		Long phn = scan.nextLong();
		System.out.println("enter toyStore");
		String shopName = scan.next();
		System.out.println("enter password");
		String password = scan.next();

		Admin admin = new Admin(name, email, phn, shopName, password);
		try {
			data = crud1.registerAdmin(admin);
			System.out.println("************data saved successfully.....!!***************");
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

//**********************************************************
	// ******************adminLogin****************************\
	// **********************************************************
	public static void adminLogin() throws Exception {
		System.out.println("\n\t\t----------Enter Data for Login------------");
		System.out.print("\n\t\tEnter email :");
		String email = scan.next();
		System.out.print("\n\t\tEnter password : ");
		String password = scan.next();
		Admin a = crud1.fetchAdminDetailorLogin(email);
		if (a != null) {
			if (a.getPassword().equals(password)) {
				System.out.println("\n\t\t*********login success... Dear " + a.getAdminName() + "************");
				// *********** admin Section**********************
				adminSectionIfEmailCorrect(a);

			} else {
				System.out.println("\n\t\t\t Entered wrong password....check again\n");
				adminLogin();
			}
		}

	}

	// ***********************************************************
	// ***************AdminsectionIfEmailand password is correct***************
	// *************************************************************
	public static void adminSectionIfEmailCorrect(Admin a) throws Exception {
		boolean check = true;
		do {
			System.out.println("\n\t\t\t------------Welcome to Admin Section------------");
			System.out.println(
					"\n\t\t\tEnter Your Choice : \n\t\t\t 1.Admin Profile \n\t\t\t 2.Toy Store \n\t\t\t 3.Exit");
			int choice = scan.nextInt();
			switch (choice) {

			// *************Admin profile********************
			// ***********************************************

			case 1: {
				System.out.println("\t\t\t\t**************Welcome to Admin Profile Section********************");
				boolean profileCheck = true;
				do {
					System.out.println(
							"\n\t\t\t\tChoice Admin Profile : \n\t\t\t\t 1.Fetch Admin Detail \n\t\t\t\t 2.Update Admin Detail \n\t\t\t\t 3.Delete Admin Detail \n\t\t\t\t 4.Exit");
					int profileChoice = scan.nextInt();
					switch (profileChoice) {
					case 1: {
						System.out.println("\t\t\t\t\t**********Admin Details************");
						System.out.println("\t\t\t\t\t-----------------------------------");
						System.out.println(
								"\t\t\t\t\tAdmin ID        :  " + a.getAdminId() + "\n\t\t\t\t\tAdmin Name      :  "
										+ a.getAdminName() + "\n\t\t\t\t\tAdmin Email     :  " + a.getAdminEmail()
										+ "\n\t\t\t\t\tAdmin Phone     :  " + a.getAdminPhone()
										+ "\n\t\t\t\t\tToy Store Name  :  " + a.getToyStore()
										+ "\n\t\t\t\t\tAdmin Password  :  " + a.getPassword());
						System.out.println("\t\t\t\t\t------------------------------------");
					}
						break;
					case 2: {
						adminUpdateIfEmailCorrect(a);
					}
						break;
					case 3: {
						crud1.deleteAdminDetail(a.getAdminEmail());
						System.out.println("\n\t\t\t\t\t*******Admin Data Deleted*************");
						admin();
						profileCheck = false;
						break;
					}
					default: {
						adminSectionIfEmailCorrect(a);
						profileCheck = false;
						break;
					}

					}

				} while (profileCheck);
			}
				break;

			// ********************************************
			// **************Toystore*********************
			// ********************************************
			case 2: {
				toyStore(a);

			}
				break;
			case 3: {
				admin();

			}
				break;
			default: {
				admin();

			}
			}

		} while (check);

	}

	public static void adminUpdateIfEmailCorrect(Admin a) throws ClassNotFoundException, SQLException, IOException {
		boolean check = true;
		do {
			System.out.println(
					"\n\t\t\t\t\tWhich Column you want to update :\n\t\t\t\t\t 1.Admin Name \n\t\t\t\t\t 2.Admin Email \n\t\t\t\t\t 3.Admin PhoneNumber \n\t\t\t\t\t 4.Store Name \n\t\t\t\t\t 5.Password \n\t\t\t\t\t 6.Exit ");
			int choice = scan.nextInt();
			if (choice == 1) {
				System.out.println("\t\t\t\t\tYou are Updating Admin name ");
				System.out.print("\n\t\t\t\t\tEnter new Name :");
				String adminName = scan.next();
				crud1.updateAdminDetail(a, choice, adminName);
				System.out.println("\n\t\t\t\t\t************* Name Updated**************");
			}

		} while (check);

	}

//********************************************************
//***********************************************************
//******************toystore*****************************
//*******************************************************
//********************************************************
	public static void toyStore(Admin admin) throws Exception {
		boolean check = true;
		do {
			System.out.println(
					"\n\t\t\t\t********Which Operation You want to perform********* \n\t\t\t\t 1.Upload Toy Detail \n\t\t\t\t 2.Update Toy Detail \n\t\t\t\t 3.Fetch All the Toy \n\t\t\t\t 4.Delete Toy Detail \n\t\t\t\t 5.Exit ");
			int choice = scan.nextInt();
			if (choice == 1) {

				// **********Insert Toy Data*****************

				uploadToyData(admin);
			} else if (choice == 2) {

				// **************Update Toy Data*****************

				updateToyDetail();
			} else if (choice == 3) {
				// *****************Fetch Toy Data******************
				crud.fetchToyDetail(admin.getAdminEmail());
			} else if (choice == 4) {
				// *************Delete Toy Detail********************
				System.out.println("\n\t\t\t\tEnter Toy ID to Perform Delete Operation : ");
				int toyId = scan.nextInt();

				Toys toy = new Toys(toyId);
				crud.deleteToyDetail(toyId);
				System.out.println("\n\t\t\t\t**********Data Deleted successfully*************");

				System.out.print("\n\n\t\t\t\tDo You Want to Insert new Row (Y/N) : ");
				String deleteChoice = scan.next();
				if (deleteChoice.equalsIgnoreCase("y")) {
					uploadToyData(admin);
				} else {
					toyStore(admin);

				}

			} else {
				adminSectionIfEmailCorrect(admin);
			}

		} while (check);

	}

	// *******************************************************
	// ******************** Insert Toy Data***********************
	// ********************************************************

	public static void uploadToyData(Admin admin) throws Exception {
		System.out.println("\n\t\t\t\t\t--------Insert New Toy Details----------- ");
		int data = 0;
		System.out.print("\n\t\t\t\t\tEnter Toy Name  : ");
		String toyName = scan.next();
		System.out.print("\n\t\t\t\t\tEnter Toy Brand Name : ");
		String toyBrand = scan.next();
		System.out.print("\n\t\t\t\t\tEnter Toy Price : ");
		double price = scan.nextDouble();
		System.out.print("\n\t\t\t\t\tEnter toy Quantity : ");
		int quantity = scan.nextInt();

		Toys toys = new Toys(toyName, toyBrand, price, quantity, admin.getAdminEmail());
		data = crud.insertToyDetail(toys, admin);
		System.out.println("\n\t\t\t\t\t******Toy Data inserted Successfully***********");

		System.out.print("\n\t\t\t\t\tDo you want to insert more Data(Y/N) : ");
		String choice = scan.next();
		if (choice.equalsIgnoreCase("y")) {
			uploadToyData(admin);
		} else {
			toyStore(admin);
		}

	}

	// ******************************************************************
	// **************** Update toy detail*********************************
	// ******************************************************************

	public static void updateToyDetail() throws Exception {
		boolean check = true;
		do {
			System.out.println(
					"\n\t\t\t\t\tWhich Column You want to Update :  \n\t\t\t\t\t 1.Toy Name \n\t\t\t\t\t 2.Toy Brand Name \n\t\t\t\t\t 3.Toy Price \n\t\t\t\t\t 4.Quantity \n\t\t\t\t\t 5.Exit");
			int choice = scan.nextInt();

			System.out.print("\n\t\t\t\t\t\tEnter Toy ID : ");
			int id = scan.nextInt();

			Toys toy = new Toys(id);
			if (choice == 1) {
				System.out.println("\t\t\t\t\tYou are Updating toyBrand ");
				System.out.print("\n\t\t\t\t\tEnter new toyBrand Name :");
				String toyName = scan.next();
				crud.updateToyDetail(toy, choice, toyName);
				System.out.println("\n\t\t\t\t\t*************Name Updated**************");
			} else if (choice == 2) {
				System.out.println("\t\t\t\t\tYou are Updating Toy toyBrand ");
				System.out.print("\n\t\t\t\t\tEnter new Name :");
				String toyBrand = scan.next();
				crud.updateToyDetail(toy, choice, toyBrand);
				System.out.println("\n\t\t\t\t\t*************toyBrand Name Updated**************");

			} else if (choice == 3) {
				System.out.println("\t\t\t\t\tYou are Updating Toy price ");
				System.out.print("\n\t\t\t\t\tEnter new price Name :");
				String price = scan.next();
				crud.updateToyDetail(toy, choice, price);
				System.out.println("\n\t\t\t\t\t*************price Name Updated**************");

			} else if (choice == 4) {
				System.out.println("\t\t\t\t\tYou are Updating Toy Quantity ");
				System.out.print("\n\t\t\t\t\tEnter new quantity Name :");
				String quantity = scan.next();
				crud.updateToyDetail(toy, choice, quantity);
				System.out.println("\n\t\t\t\t\t*************Toyquantity Name Updated**************");

			} else {
				break;
			}

			System.out.print("\n\t\t\t\t\tDo you Want to update Anything Else(Y/N) : ");
			String choiceNext = scan.next();
			if (choiceNext.equalsIgnoreCase("Y")) {
				check = true;
			} else if (choiceNext.equalsIgnoreCase("N")) {
				check = false;
			} else {
				check = false;
			}

		} while (check);

	}

	// *****************************************************************
	// *****************************************************************
	// ****************************************************************
	// ****************** USER DETAILS ********************************
	// *****************************************************************
	// *****************************************************************
	// *****************************************************************

	public static void user() throws Exception {
		System.out.println("\t*********This is User Section*****************");

		System.out.println("\tEnter Your CHoice : \n\t 1.Register YourSelf \n\t 2.Login \n\t 3.Exit");
		int choice = scan.nextInt();
		if (choice == 1) {
			userRegister();
		} else if (choice == 2) {
			userLogin();
		} else {
			toyMain();
		}

	}

	// **********************************************************************
	// **********************USER REGISTERATION******************************
	// ************************************************************************

	public static void userRegister() throws Exception {
		int data = 0;
		System.out.println("\t\t--------Enter Data for Registration---------");
		System.out.print("\n\t\tEnter Your Name : ");
		String userName = scan.next();
		System.out.print("\n\t\tEnter Your Email : ");
		String userEmail = scan.next();
		System.out.print("\n\t\tEnter Your Phone : ");
		long userPhone = scan.nextLong();
		System.out.print("\n\t\tEnter Your Wallet Balance : ");
		double wallet = scan.nextDouble();
		System.out.print("\n\t\tEnter Your Address : ");
		String address = scan.next();
		System.out.print("\n\t\tEnter Your Password : ");
		String password = scan.next();

		User user = new User(userName, userEmail, userPhone, address, wallet, password);

		try {
			data = crud2.registerUser(user);
			System.out.println("\n\t\t*********** Data Saved Successfully *************");
			userLogin();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}

	}

	// *********************************************************
	// *****************USER LOGIN****************************
	// ******************************************************
	public static void userLogin() throws Exception {
		System.out.println("\n\t\t----------Enter Your Data for Login------------");
		System.out.print("\n\t\tEnter email :");
		String email = scan.next();
		System.out.print("\n\t\tEnter password : ");
		String password = scan.next();

		User user = crud2.fetchUserDetail(email);
		if (user != null) {
			if (user.getUserpassword().equals(password)) {
				System.out.println("\n\t\t*********login success... Dear " + user.getUsername() + "************");
				// *********** User Section**********************
				userSection(user);
			} else {
				System.out.println("\n\t\t Entered wrong password....check again\n");
				userLogin();
			}
		} else {
			System.out.println("\n\t\t Entered wrong email...check again\n");
			userLogin();
			user();

		}

	}

	// *******************************************************************
	// **********************User Section*****************************
	// ***************************************************************

	public static void userSection(User user) throws Exception {
		System.out.println("\n\t\t\tEnter Your Choice : \n\t\t\t 1.User Profile \n\t\t\t 2.Toy Store \n\t\t\t 3.Exit");
		int choice = scan.nextInt();
		if (choice == 1) {
			userProfile(user);
		} else if (choice == 2) {
			toyStoreDetail(user);
		} else {
			user();
		}

	}

	// ******************************************************************
	// *********************user Profile*********************************
	// ******************************************************************

	public static void userProfile(User user) throws Exception {
		System.out.println(
				"\t\t\t\t*******User Profile*********** \n\t\t\t\t 1.Fetch Your Detail \n\t\t\t\t 2.Update Your Data \n\t\t\t\t 3.Delete Your Profile \n\t\t\t\t 4.Exit");
		int choice = scan.nextInt();
		if (choice == 1) {
			System.out.println("\t\t\t\t\t*************** User Details **********************");
			System.out.println("\t\t\t\t\t-------------------------------------------------------");
			System.out.println("\t\t\t\t\tUser ID         :  " + user.getUserid() + "\n\t\t\t\t\tUser Name      :  "
					+ user.getUsername() + "\n\t\t\t\t\tUser Email     :  " + user.getUseremail()
					+ "\n\t\t\t\t\tUser Phone     :  " + user.getUserphone() + "\n\t\t\t\t\tUser Addresss  :  "
					+ user.getUseraddress() + "\n\t\t\t\t\tWallet Rs.     :  " + user.getUserwallet()
					+ "\n\t\t\t\t\tPassword       :  " + user.getUserpassword());
			System.out.println("\t\t\t\t\t-------------------------------------------------------\n");
			userProfile(user);
		} else if (choice == 2) {
			updateUserDetail(user);
			userProfile(user);
		} else if (choice == 3) {
			System.out.print("\n\t\t\t\t****Are you Sure ? You want to delete a Your Detail (Y/N) :");
			String deleteChoice = scan.next();
			if (deleteChoice.equalsIgnoreCase("y")) {
				crud2.deleteUserDetail(user.getUseremail());
				System.out.println("\n\t\t\t***********YOUR DATA IS DELETED SUCCESSFULLY*****************\n");
				user();
			} else {
				userProfile(user);
			}
		} else {
			userSection(user);
		}

	}

	// **********************************************************
	// ******************* Update User Detail *******************
	// ***********************************************************

	public static void updateUserDetail(User user) throws Exception {

		boolean check = true;
		do {
			System.out.println("\n\t\t\t\t\tWhich Column You want to Update :  " + "\n\t\t\t\t\t 1.User Name "
					+ "\n\t\t\t\t\t 2.User Email " + "\n\t\t\t\t\t 3.Phone Number " + "\n\t\t\t\t\t 4.User Address "
					+ "\n\t\t\t\t\t 5.Wallet Rs. " + "\n\t\t\t\t\t 6.Password " + "\n\t\t\t\t\t 7.Exit");
			int choice = scan.nextInt();
			if (choice == 1) {
				System.out.println("\t\t\t\t\tYou are Updating Your Name ");
				System.out.print("\n\t\t\t\t\tEnter new Name :");
				String userName = scan.next();
				crud2.updateUserDetail(user, choice, userName);
				System.out.println("\n\t\t\t\t\t*************Name Updated**************");
			} else if (choice == 2) {
				System.out.println("\t\t\t\t\tYou are Updating Your Email ");
				System.out.print("\n\t\t\t\t\tEnter new Email Id :");
				String userEmail = scan.next();
				crud2.updateUserDetail(user, choice, userEmail);
				System.out.println("\n\t\t\t\t\t*************Email Updated**************");
			} else if (choice == 3) {
				System.out.println("\t\t\t\t\tYou are Updating Phone Number ");
				System.out.print("\n\t\t\t\t\tEnter new Phone :");
				long phone = scan.nextLong();
				crud2.updateUserDetail(user, choice, phone);
				System.out.println("\n\t\t\t\t\t*************Phone Updated **************");
			} else if (choice == 4) {
				System.out.println("\t\t\t\t\tYou are Updating Your Address ");
				System.out.print("\n\t\t\t\t\tEnter new Address :");
				String address = scan.next();
				crud2.updateUserDetail(user, choice, address);
				System.out.println("\n\t\t\t\t\t*************Address Updated**************");
			} else if (choice == 5) {
				System.out.println("\t\t\t\t\tYou are Updating Your Wallet Rs. ");
				System.out.print("\n\t\t\t\t\tEnter new Wallet Data :");
				double wallet = scan.nextDouble();
				crud2.updateUserDetail(user, choice, wallet);
				System.out.println("\n\t\t\t\t\t*************Wallet Data Updated**************");
			} else if (choice == 6) {
				System.out.println("\t\t\t\t\tYou are Updating Your Password ");
				System.out.print("\n\t\t\t\t\tEnter new Password :");
				String password = scan.next();
				crud2.updateUserDetail(user, choice, password);
				System.out.println("\n\t\t\t\t\t*************Password Updated**************");
			} else {
				userProfile(user);
			}

			System.out.print("\n\t\t\t\t\tDo you Want to update Anything Else(Y/N) : ");
			String choiceNext = scan.next();
			if (choiceNext.equalsIgnoreCase("Y")) {
				check = true;
			} else if (choiceNext.equalsIgnoreCase("N")) {
				check = false;
			} else {
				check = false;
			}

		} while (check);

	}

	// **************************************************************
	// *********************TOY STORE DETAIL************************
	// **************************************************************

	static List<String> list = new ArrayList<String>();
	static double addToCart;

	public static void toyStoreDetail(User user) throws Exception {
		System.out.println("\t\t\t\t********Welcome to Toy Store**************\n");
		System.out.println("\t\t\t\tEnter Your Choice \n\t\t\t\t 1.Fetch All Toy Details \n\t\t\t\t 2.Exit");
		int choice = scan.nextInt();
//			double addToCart = 0;

		double walletCurrentAmount = user.getUserwallet();

		if (choice == 1) {
			// Choice = 1 FetchDetail

			crud2.fetchAllToyDetails();
			boolean check = true;

			// *************Want to buy any product or not**********************
			System.out.print("\n\t\t\t\t Do You Want to Buy Any Product (Y/N) : ");
			String userChoice = scan.next();

			if (userChoice.equalsIgnoreCase("y")) {
				addToCart();
				do {
					// *************** want to buy more product or not*********************
					System.out.print("\n\t\t\t Do You Want to Buy More Product : (Y/N) ");
					String userAgainChoice = scan.next();
					if (userAgainChoice.equalsIgnoreCase("y")) {
						addToCart();
					} else {
						System.out.println("\n\t\t**************Do you want to Purchase the Products (Y/N) :");
						String purchase = scan.next();
						if (purchase.equalsIgnoreCase("y")) {
							if (addToCart >= user.getUserwallet()) {
								System.out.println("\n\t\tYour Current Wallet Balance is : " + user.getUserwallet());
								System.out.println("\n\t\t--------------------------------------");
								System.out.println("\t\t  Your Total Bill is : " + addToCart);
								System.out.println("\t\t--------------------------------------");

								System.out.println("\n\t\t~Balance Insufficient");

								System.out.print("\n\t\t~~~Do You Want to Add Money To Your Wallet (Y/N) :");
								String walletYesNO = scan.next();

								if (walletYesNO.equalsIgnoreCase("y")) {
									System.out.print("\n\t\tHow much Amount you want to add : ");
									double amountAdd = scan.nextDouble();

									walletCurrentAmount += amountAdd;
									crud2.walletData(user.getUseremail(), walletCurrentAmount);

									System.out.println(
											"\n\t\tNow Your Current wallet Balance is  : " + walletCurrentAmount);

									System.out.println("\n\t\tYou Want to Purchase the Products or not (Y/N) : ");
									String purchaseOrNot = scan.next();

									if (purchaseOrNot.equalsIgnoreCase("y")) {
										System.out.println("\n\t\t Your Bill is Here :");
										System.out.println("\t\t----------------------------------------------");
										System.out.println("\t\t Toy Name \t Toy Quantity \t Toy Price  ");
										System.out.println("\t\t-----------------------------------------------");
										System.out.println(list);
										System.out.println("\t\t-----------------------------------------------");
										System.out.println("\t\t                        Total Amount  :" + addToCart);

										System.out.println(
												"\n\t**********  THANKYOU TO BUYING PRODUCT FROM OUR STORE ***************");

										walletCurrentAmount -= addToCart;
										crud2.walletData(userChoice, walletCurrentAmount);

										System.out.println(
												"\n\t\tNow Your Current Wallet Balance is : " + walletCurrentAmount);

										System.out.println("\n\t\t~~Now you Can Exit From Your Profile");
										userSection(user);
									} else {
										System.out.println(
												"\\n\\t**********  THANKYOU TO VISITING OUR STORE , VISIT AGAIN..!!! ***************");
										System.out.println("\n\t\t~~Now you Can Exit From Your Profile");
										userSection(user);
									}

								} else {
									System.out.println(
											"\\n\\t**********  THANKYOU TO VISITING OUR STORE , VISIT AGAIN..!!! ***************");
									System.out.println("\n\t\t~~Now you Can Exit From Your Profile");
									userSection(user);
								}
							} else {
								System.out.println("\n\t\t Your Bill is Here :");
								System.out.println("\t\t----------------------------------------------");
								System.out.println("\t\t Toy Name \t Toy Quantity \t Toy Price  ");
								System.out.println("\t\t-----------------------------------------------");
								System.out.println(list);
								System.out.println("\t\t-----------------------------------------------");
								System.out.println("\t\t                        Total Amount  :" + addToCart);

								walletCurrentAmount -= addToCart;
								crud2.walletData(userChoice, walletCurrentAmount);

								System.out.println("\t\tNow Your Current Wallet Balance is : " + walletCurrentAmount);

								System.out.println(
										"\n\t**********  THANKYOU TO BUYING PRODUCT FROM OUR STORE ***************");

								System.out.println("\n\t\t~~Now you Can Exit From Your Profile");
								userSection(user);

							}

						} else {
							System.out.println("\n\t\t*Visit Again");
//									userSection(user);
						}
					}
				} while (check);
			}

			else {
				userSection(user);
			}
		} else {
			// choice = 2 Exit
			userSection(user);
		}
	}

	public static void addToCart() throws Exception {
		System.out.print("\n\t\tIf yes then Choose Toy Id : ");
		int toyId = scan.nextInt();
		double toyPrice = 0;

		System.out.println("\n\t**This is Your Toy Detail");
		Toys toy = crud2.fetchToyDetailWithID(toyId);

		System.out.print("\n\t\tQuantity of Toy : ");
		int toyQuantity = scan.nextInt();

		if (toyQuantity > toy.getQuantity()) {
			System.out.println("\t\t~~Stock is not Aviailable");
		} else {
			toyPrice = toy.getToyPrice() * toyQuantity;
			addToCart += toyPrice;
			System.out.println("\n\t~Item is added in your Cart");
//				System.out.println("Bill : " + addToCart);

			list.add("\t\t " + toy.getToyName() + "\t\t" + toyQuantity + "   \t   " + toyPrice + " \n");
		}

	}
}
