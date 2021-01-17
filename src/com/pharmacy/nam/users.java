package com.pharmacy.nam;
//this class for save logged user information
public class users {
	//all fields have getter and setter
	static int _UserId;
	static String _FullName;
	static String _UserName;
	static String _Password;
	static int _IsAdmin;
	static int _IsUser;

	public static int Get_userId() {
		return _UserId;
	}

	public static String Get_FullName() {
		return _FullName;
	}

	public static String Get_UserName() {
		return _UserName;
	}

	public static String Get_Password() {
		return _Password;
	}

	public static int Get_IsAdmin() {
		return _IsAdmin;
	}

	public static int Get_IsUser() {
		return _IsUser;
	}

	public static void Set_UserId(int UserId) {
		_UserId = UserId;
	}

	public static void Set_FullName(String FullName) {
		_FullName = FullName;
	}

	public static void Set_UserName(String UserName) {
		_UserName = UserName;
	}

	public static void Set_Password(String Password) {
		_Password = Password;
	}

	public static void Set_IsAdmin(int IsAdmin) {
		_IsAdmin = IsAdmin;
	}

	public static void Set_IsUser(int IsUser) {
		_IsUser = IsUser;
	}

	//use it where make Logoff
	public static void reset() {
		Set_FullName("");
		Set_IsAdmin(0);
		Set_IsUser(0);
		Set_Password("");
		Set_UserId(0);
		Set_UserName("");
	}

}