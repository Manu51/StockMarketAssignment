/**
 * 
 */
package com.jpmorgan.stocks.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jpmorgan.stocks.dao.TradesDao;
import com.jpmorgan.stocks.model.Stock;
import com.jpmorgan.stocks.model.Trade;

/**
 * @author manisha
 *
 */
@Component
public class TradesDaoImpl implements TradesDao{


	private Map<String, List<Trade>> tradeMap = new HashMap<String, List<Trade>>(); 

	/**
	 * @inheritDoc
	 */
	public void addTrade(Trade trade) {
		List<Trade> trades = new ArrayList<Trade>();
		if (tradeMap.containsKey(trade.getStock().getSymbol())) {
			trades = tradeMap.get(trade.getStock().getSymbol());
		}
		trades.add(trade);
		tradeMap.put(trade.getStock().getSymbol(), trades);
	}

	/**
	 * @inheritDoc
	 */
	public List<Trade> getTrades(Stock stock, int minutes) {
		List<Trade> result = new ArrayList<Trade>();
		Date afterDate = getDateXMinutesEarlier(minutes);
		List<Trade> trades = tradeMap.get(stock.getSymbol());
		if(!(trades == null || trades.isEmpty())){
			Collections.sort(trades); 
			Iterator<Trade> it = trades.iterator();
			while (it.hasNext()) {
				Trade trade = it.next();
				if (trade.getTimestamp().before(afterDate)) { 
					break;
				}
				result.add(trade);
			}
			}

		return result;
	}

	/**
	 * @inheritDoc
	 */
	public List<Trade> getAllTrades() {
		List<Trade> result = new ArrayList<Trade>();
		for (String stockSymbol: tradeMap.keySet()) {
			result.addAll(tradeMap.get(stockSymbol));
		}
		return result;
	}

	
	/**
	 * Gives a Date which is x number of minutes earlier than current time.
	 * @param minutes
	 * @return
	 */
	private Date getDateXMinutesEarlier(int minutes){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, -minutes);
		return c.getTime();
	}
	
	/**
	   * @inheritDoc
	   */
	  public void reset(){
	    tradeMap.clear();
	  }

}
