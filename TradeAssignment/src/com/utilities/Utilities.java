package com.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import com.bean.Trade;

public class Utilities {

	public LinkedList<Trade> readTradeData(String filename) {
		LinkedList<Trade> tradeList = new LinkedList<Trade>();
		try {
			FileReader f1 = new FileReader(filename);
			BufferedReader br = new BufferedReader(f1);
			String s;
			br.readLine();

			while ((s = br.readLine()) != null) {
				// System.out.println(s);
				String[] sArray = s.split(",", -1);
				Trade trade = new Trade();
				trade.setTradeId(sArray[0]);
				trade.setVersion(Integer.parseInt(sArray[1]));
				trade.setCpId(sArray[2]);
				trade.setBookId(sArray[3]);
				trade.setMaturityDate(LocalDate.parse(sArray[4], DateTimeFormatter.ofPattern("dd/MM/yyyy")));
				if (sArray[5].equals("<today date>"))
					trade.setCreatedDate(LocalDate.now());
				else {
					try {
						LocalDate localDate = LocalDate.parse(sArray[5], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
						trade.setCreatedDate(localDate);
					}

					catch (Exception e) {
						System.out.println("Error while parsing" + e.getMessage());
					}
				}

				trade.setExpired(sArray[6]);
				tradeList.add(trade);

			}
		}

		catch (Exception e) {
			// System.out.println("Error: "+ e.getMessage());
			e.printStackTrace();
		}
		return tradeList;
	}

	public int getIndex(LinkedList<Trade> tradeList, Trade trade) {
		int index = 0;

		for (Trade t : tradeList) {
			if (t.getTradeId().equals(trade.getTradeId()) && t.getVersion() == trade.getVersion()) {
				return index;

			}

			index++;
		}

		return -1;
	}

}
