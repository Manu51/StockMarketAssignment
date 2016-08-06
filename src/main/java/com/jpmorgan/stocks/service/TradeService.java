/**
 * 
 */
package com.jpmorgan.stocks.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jpmorgan.stocks.model.Stock;
import com.jpmorgan.stocks.model.Trade;

/**
 * @author manisha
 *
 */

@Service
public interface TradeService {

	
	  /**
	   * Record a Trade
	   * @param trade
	   */
	  public void recordTrade(Trade trade);

	  /**
	   * Get a list of trades recorded for given stock within the last x minutes
	   * @param stock
	   * @param numberOfMinutes
	   * @return
	   */
	  public List<Trade> getTrades(Stock stock, int numberOfMinutes);

	  /**
	   * Get all trades
	   * @return
	   */
	  public List<Trade> getAllTrades();
	  
	  /**
	   * Reset trades
	   */
	  public void reset();

}
