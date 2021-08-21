package com.utilities;

public class SqlConstant {
	
	public static final String InsertTrade="INSERT INTO store (Trade_ID, Version, CounterParty_ID, Book_ID, Maturity_Date, Created_Date, Expired) VALUES (?,?,?,?,?,?,?)";
	public static final String UpdateExpiryFlag="update store set Expired='Y' where Maturity_Date<CURRENT_DATE";

}
