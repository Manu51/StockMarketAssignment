/**
 * 
 */
package com.jpmorgan.stocks.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jpmorgan.stocks.model.Stock;
import com.jpmorgan.stocks.model.Trade;

/**
 * @author manisha
 *
 */

@Service
public interface TradesDao {

	/**
	   * Add a trade to the memory
	   * @param trade
	   */
	  void addTrade(Trade trade);

	  /**
	   * Get all trades for given stock in the last x minutes
	   * @param stock
	   * @param minutes
	   * @return
	   */
	  List<Trade> getTrades(Stock stock, int minutes);

	  /**
	   * Get all the available trades for all stocks
	   * @return
	   */
	  List<Trade> getAllTrades();
	  
	  /**
	   * Reset trades
	   */
	  void reset();
}
