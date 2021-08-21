package com.batch;

import java.util.LinkedList;

import com.bean.Trade;
import com.dao.TradeDAO;
import com.utilities.Utilities;
import com.utilities.Validation;

public class TradeBatch {
	public static void main(String args[]) {

		Utilities utilities = new Utilities();
		Validation validation = new Validation();
		LinkedList<Trade> tradeList = utilities
				.readTradeData("C:\\Users\\91812\\Desktop\\Trade_Assignment\\Trade_Data.csv");
		for (Trade t : tradeList) {
			System.out.println(t);
		}

		LinkedList<Trade> validatedList = validation.checkVersionOfTrade(tradeList);
		System.out.println("Validated List");
		for (Trade t : validatedList) {
			System.out.println(t);

		}

		LinkedList<Trade> finalList = validation.checkMaturityDate(validatedList);
		System.out.println("Maturity Date checked List");
		for (Trade t : finalList) {
			System.out.println(t);

		}

		TradeDAO dao = new TradeDAO();

		for (Trade t : finalList) {
			dao.insertTrade(t);

		}

	}

}
