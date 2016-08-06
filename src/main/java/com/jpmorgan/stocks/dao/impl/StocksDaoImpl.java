package com.jpmorgan.stocks.dao.impl;

import java.util.HashMap;
import java.util.Map;


import org.springframework.stereotype.Component;

import com.jpmorgan.stocks.dao.StocksDao;
import com.jpmorgan.stocks.model.Stock;

@Component
public class StocksDaoImpl implements StocksDao{
	
	
	private Map<String, Stock> stockMap = new HashMap<String, Stock>();

	  /**
	   * @inheritDoc
	   */
	  public void addStock(Stock stock) {
	    stockMap.put(stock.getSymbol(), stock);
	  }

	  /**
	   * @inheritDoc
	   */
	  public Stock getStock(String symbol) {
	    return stockMap.get(symbol);
	  }
	  
	  /**
	   * @inheritDoc
	   */
	  public void reset() {
	    stockMap.clear();
	  }

	
}
