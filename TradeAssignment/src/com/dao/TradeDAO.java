package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bean.Trade;
import com.utilities.SqlConstant;

public class TradeDAO {

	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:33067/trade", "test", "test");

		}

		catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	public void insertTrade(Trade t) {
		System.out.println("Inserting Trade data,tradeID: " + t.getTradeId() + " Version: " + t.getVersion());
		Connection con = getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(SqlConstant.InsertTrade);
			stmt.setString(1, t.getTradeId());
			stmt.setInt(2, t.getVersion());
			stmt.setInt(3, t.getVersion());
			stmt.setString(4, t.getBookId());
			stmt.setDate(5, java.sql.Date.valueOf(t.getMaturityDate()));
			stmt.setDate(6, java.sql.Date.valueOf(t.getCreatedDate()));
			stmt.setString(7, t.getExpired());

			stmt.executeUpdate();

		}

		catch (Exception e) {
			System.out.println(e);
		}

		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void updateExpiry() {

		Connection con = getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(SqlConstant.UpdateExpiryFlag);
			stmt.executeUpdate();
		}

		catch (Exception e) {
			System.out.println(e);
		}

		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
