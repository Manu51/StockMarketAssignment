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
public interface StockService {

	/**
	   * Adds the given stock
	   * @param stock
	   */
	  public void addStock(Stock stock);

	  /**
	   * Get a Stock by giving stock symbol 
	   * @param symbol
	   * @return
	   */
	  public Stock getStock(String symbol);

	  /**
	   * Calculate the dividend yield for a given stock and price
	   * @param stock
	   * @param price
	   * @return
	   */
	  public double calculateDividendYield(Stock stock, double price);

	  /**
	   * Calculate the P/E ration for a given stock and price
	   * @param stock
	   * @param price
	   * @return
	   */
	  public double calculatePERatio(Stock stock, double price);

	  /**
	   * Calculate the volume weighted stock price based on a list of trades
	   * @param trades
	   * @return
	   */
	  public double calculateVolumeWeightedStockPrice(List<Trade> trades);

	  /**
	   * Calculate the GBCE for a list of trades
	   * @param trades
	   * @return
	   */
	  public double calculateGBCE(List<Trade> trades);
	  
	  /**
	   * Reset {@code Stock}s
	   */
	  public void reset();

}
