package com.jpmorgan.stocks.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jpmorgan.stocks.dao.StocksDao;
import com.jpmorgan.stocks.model.Stock;
import com.jpmorgan.stocks.model.Stock.StockType;
import com.jpmorgan.stocks.model.Trade;
import com.jpmorgan.stocks.service.StockService;

@Component
public class StockServiceImpl implements StockService{

	
	 @Autowired
	  private StocksDao stockDao;

	  /**
	   * @inheritDoc
	   */
	  public void addStock(Stock stock) {
	    stockDao.addStock(stock);
	  }

	  /**
	   * @inheritDoc
	   */
	  public Stock getStock(String symbol) {
	    return stockDao.getStock(symbol);
	  }

	  /**
	   * @inheritDoc
	   */
	  public double calculateDividendYield(Stock stock, double price) {
	    if (StockType.PREFERRED.equals(stock.getType())) {
	      return (stock.getFixedDividend() * stock.getParValue()) / price;
	    }
	    double result = stock.getLastDividend() / price;
	    return round(result, 2);
	  }

	  /**
	   * @inheritDoc
	   */
	  public double calculatePERatio(Stock stock, double price) {
	    double result = price / stock.getLastDividend();
	    return round(result, 2);
	  }

	  /**
	   * @inheritDoc
	   */
	  public double calculateVolumeWeightedStockPrice(List<Trade> trades) {
	    double sumOfPriceQuantity = 0;
	    int sumOfQuantity = 0;

	    for (Trade trade : trades) {
	      sumOfPriceQuantity = sumOfPriceQuantity + (trade.getPrice() * trade.getQuantity());
	      sumOfQuantity = sumOfQuantity + trade.getQuantity();
	    }
	    double result = sumOfPriceQuantity / sumOfQuantity;
	    return round(result, 2);
	  }

	  /**
	   * @inheritDoc
	   */
	  public double calculateGBCE(List<Trade> trades) {
	    double total = 1;
	    for (Trade trade : trades) {
	      total = total * trade.getPrice();
	    }
	    double result = Math.pow(total, (1D / trades.size()));
	    return round(result, 2);
	  }
	  
	  /**
	   * Round the result up to number of places
	   * @param value
	   * @param places
	   * @return
	   */
	  private static double round(double value, int places) {
	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	  }
	  /**
	   * @inheritDoc
	   */
	  public void reset() {
	    stockDao.reset();
	  }
}
