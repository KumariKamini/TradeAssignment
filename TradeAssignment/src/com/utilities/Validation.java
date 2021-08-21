package com.utilities;

import java.util.HashMap;
import java.util.LinkedList;

import com.bean.Trade;
import com.exception.InvalidVersionException;

public class Validation {
	Utilities utilities = new Utilities();

	public LinkedList<Trade> checkVersionOfTrade(LinkedList<Trade> tradeList) {

		LinkedList<Trade> result = new LinkedList<Trade>();

		HashMap<String, Integer> versionMap = new HashMap<String, Integer>();
		for (Trade t : tradeList) {
			if (versionMap.containsKey(t.getTradeId())) {
				int lastVersion = versionMap.get(t.getTradeId());
				try {
					if (t.getVersion() > lastVersion) {
						result.add(t);
					}

					else if (t.getVersion() == lastVersion) {

						int index = utilities.getIndex(result, t);

						if (index != -1) {
							result.set(index, t);
						}
					}

					else if (t.getVersion() < lastVersion) {
						throw new InvalidVersionException("InvalidVersion");

					}

				} catch (InvalidVersionException e) {
					System.out.println(t);
				}

				catch (Exception e) {

					e.printStackTrace();
				}
			} else {

				versionMap.put(t.getTradeId(), t.getVersion());
				result.add(t);

			}
		}

		return result;

	}

	public LinkedList<Trade> checkMaturityDate(LinkedList<Trade> tradeList) {
		LinkedList<Trade> result = new LinkedList<Trade>();
		for (Trade t : tradeList) {
			if (t.getMaturityDate().isAfter(t.getCreatedDate())) {
				result.add(t);
			}
		}

		return result;
	}

}
