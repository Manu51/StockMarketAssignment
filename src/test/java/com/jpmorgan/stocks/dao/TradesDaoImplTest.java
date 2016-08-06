/**
 * 
 */
package com.jpmorgan.stocks.dao;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.stocks.dao.impl.TradesDaoImpl;
import com.jpmorgan.stocks.model.Stock;
import com.jpmorgan.stocks.model.Stock.StockType;
import com.jpmorgan.stocks.model.Trade;
import com.jpmorgan.stocks.model.Trade.Indicator;


/**
 * @author manisha
 *
 */
public class TradesDaoImplTest {

	private TradesDaoImpl tradeDao = null;
	  private Stock testStock1;
	  private Stock testStock2;
	  private Stock testStock3;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		tradeDao = new TradesDaoImpl();
		testStock1 = new Stock("Test", StockType.COMMON, 1, 0, 6);
		testStock2 = new Stock("Test2", StockType.COMMON, 2, 0, 3);
		testStock3 = new Stock("Test3", StockType.PREFERRED, 1, 1, 8);
	 
	}

	/**
	 * Test method for {@link com.jpmorgan.stocks.dao.impl.TradesDaoImpl#addTrade(com.jpmorgan.stocks.model.Trade)}.
	 */
	@Test
	public void testAddTrade() {
		Trade trade = new Trade(testStock1, Calendar.getInstance().getTime(), 1, Indicator.BUY, 1.0);
	    tradeDao.addTrade(trade);
	    assertEquals(1, tradeDao.getAllTrades().size());
	}

	/**
	 * Test method for {@link com.jpmorgan.stocks.dao.impl.TradesDaoImpl#getTrades(com.jpmorgan.stocks.model.Stock, int)}.
	 */
	@Test
	public void testGetTrades() {
		Trade testTrade = new Trade(testStock1,
		        Calendar.getInstance().getTime(), 1, Indicator.BUY, 1.0);
		    tradeDao.addTrade(testTrade);

		    Calendar c = Calendar.getInstance();
		    c.add(Calendar.MINUTE, -20);
		    Trade testTrade2 = new Trade(testStock1, c.getTime(), 1, Indicator.SELL, 1.0);
		    tradeDao.addTrade(testTrade2);
		    List<Trade> trades = tradeDao.getTrades(testStock1, 15);
		    assertEquals(1, trades.size());
	}

	/**
	 * Test method for {@link com.jpmorgan.stocks.dao.impl.TradesDaoImpl#getAllTrades()}.
	 */
	@Test
	public void testGetAllTrades() {
		   Trade testTrade = new Trade(testStock1,
		        Calendar.getInstance().getTime(), 1, Indicator.BUY, 1.0);
		    tradeDao.addTrade(testTrade);

		    Trade testTrade2 = new Trade(testStock2,
		        Calendar.getInstance().getTime(), 1, Indicator.BUY, 1.0);
		    tradeDao.addTrade(testTrade2);

		    Trade testTrade3 = new Trade(testStock3,
		        Calendar.getInstance().getTime(), 1, Indicator.BUY, 1.0);
		    tradeDao.addTrade(testTrade3);

		    assertEquals(3, tradeDao.getAllTrades().size());
	}

	
}
