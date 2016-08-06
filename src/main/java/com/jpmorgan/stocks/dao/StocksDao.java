/**
 * 
 */
package com.jpmorgan.stocks.dao;

import org.springframework.stereotype.Service;

import com.jpmorgan.stocks.model.Stock;

/**
 * @author manisha
 *
 */
@Service
public interface StocksDao {

	/**
	   * Add new stock item.
	   * @param stock
	   */
	  void addStock(Stock stock);

	  /**
	   * Get a stock by giving stock symbol.
	   * @param symbol
	   * @return
	   */
	  Stock getStock(String symbol);
	  
	  /**
	   * Reset stocks
	   */
	  void reset();

}
