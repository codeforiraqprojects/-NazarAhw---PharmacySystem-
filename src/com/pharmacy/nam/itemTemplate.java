package com.pharmacy.nam;

import java.util.Calendar;

import com.SQLiteConnect.myPro.DBconnection;
//this class work as Item have same fields in the DB
//use it for more secure and prepare the data before show it or pass it to DB
public class itemTemplate {
	//each field have getter and setter
static String _ItemName;
static double _Price;
static int _Qantity;
static int _CountryId;
static String _ExpireDate;
static int _EntryUserid;
static String _EntryDate;

public static String get_ItemName() {
	return _ItemName;
}
public static void set_ItemName(String _ItemName) {
	itemTemplate._ItemName = _ItemName;
}
public static double get_Price() {
	return _Price;
}
public static void set_Price(double _Price) {
	itemTemplate._Price = _Price;
}
public static int get_Qantity() {
	return _Qantity;
}
public static void set_Qantity(int _Qantity) {
	itemTemplate._Qantity = _Qantity;
}
public static int get_Country() {
	return _CountryId;
}
public static void set_Country(String Country) {
	//set the value from DB
	itemTemplate._CountryId = DBconnection.getCountryId(Country);
}
public static String get_ExpireDate() {
	return _ExpireDate;
}
public static void set_ExpireDate(String _ExpireDate) {
	itemTemplate._ExpireDate = _ExpireDate;
}
public static int get_EntryUserid() {
	return _EntryUserid;

}
public static void set_EntryUserid(int _EntryUserid) {
	//set the value from users class
	itemTemplate._EntryUserid = users.Get_userId();
}
public static String get_EntryDate() {
	return _EntryDate;
}
public static void set_EntryDate() {
	itemTemplate._EntryDate = Calendar.getInstance().getTime().toString();
}

}
