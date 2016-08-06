package com.jpmorgan.stocks.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jpmorgan.stocks.dao.TradesDao;
import com.jpmorgan.stocks.model.Stock;
import com.jpmorgan.stocks.model.Trade;
import com.jpmorgan.stocks.service.TradeService;

@Component
public class TradeServiceImpl implements TradeService {

	 @Autowired
	  private TradesDao tradeDao;

	  /**
	   * @inheritDoc
	   */
	  public void recordTrade(Trade trade) {
	    if (trade != null && trade.getStock() != null) {
	      tradeDao.addTrade(trade);
	    }
	  }

	  /**
	   * @inheritDoc
	   */
	  public List<Trade> getTrades(Stock stock, int minutes) {
	    return tradeDao.getTrades(stock, minutes);
	  }

	  /**
	   * @inheritDoc
	   */
	  public List<Trade> getAllTrades() {
	    return tradeDao.getAllTrades();
	  }

	  /**
	   * @inheritDoc
	   */
	  public void reset() {
	    tradeDao.reset();
	  }
}
