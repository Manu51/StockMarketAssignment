/**
 * 
 */
package com.jpmorgan.stocks.service;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jpmorgan.stocks.model.Stock;
import com.jpmorgan.stocks.model.Stock.StockType;
import com.jpmorgan.stocks.model.Trade;
import com.jpmorgan.stocks.model.Trade.Indicator;


/**
 * @author manisha
 *
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = {"classpath:/testApplicationContext.xml"})
public class TradeServiceImplTest {

	  @Autowired
	  private TradeService tradeService;

	  private Stock testStock1;
	  private Stock testStock2;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testStock1 = new Stock("TEST", StockType.COMMON, 1, 0, 1);
		testStock2 = new Stock("TEST2", StockType.COMMON, 2, 0, 2);
	}
	
	@After
	  public void destroy() {
	    tradeService.reset();
	  }

	/**
	 * Test method for {@link com.jpmorgan.stocks.service.impl.TradeServiceImpl#recordTrade(com.jpmorgan.stocks.model.Trade)}.
	 */
	@Test
	public void testRecordTrade() {
		Trade trade = new Trade(testStock1, Calendar.getInstance().getTime(), 1, Indicator.BUY, 1.0);
	    tradeService.recordTrade(trade);
	    List<Trade> result = tradeService.getTrades(testStock1, 15);
	    assertNotNull(result);
	    assertEquals(1, result.size());
	}

	/**
	 * Test method for {@link com.jpmorgan.stocks.service.impl.TradeServiceImpl#getTrades(com.jpmorgan.stocks.model.Stock, int)}.
	 */
	@Test
	public void testGetTrades() {
		Calendar c = Calendar.getInstance();
	    c.add(Calendar.MINUTE, -20);
	    Trade testTrade1 = new Trade(testStock1, c.getTime(), 1, Indicator.BUY, 1.0);
	    tradeService.recordTrade(testTrade1);

	    Date time = Calendar.getInstance().getTime();
	    Trade testTrade2 = new Trade(testStock1, time, 1, Indicator.BUY, 1.0);
	    tradeService.recordTrade(testTrade2);

	    List<Trade> result = tradeService.getTrades(testStock1, 15);
	    assertNotNull(result);
	    assertEquals(1, result.size());
	    
	}

	/**
	 * Test method for {@link com.jpmorgan.stocks.service.impl.TradeServiceImpl#getAllTrades()}.
	 */
	@Test
	public void testGetAllTrades() {
		tradeService.recordTrade(new Trade(testStock1, Calendar.getInstance().getTime(), 1, Indicator.BUY, 1.0));
	    tradeService.recordTrade(new Trade(testStock1, Calendar.getInstance().getTime(), 2, Indicator.BUY, 2.0));
	    tradeService.recordTrade(new Trade(testStock2, Calendar.getInstance().getTime(), 3, Indicator.BUY, 3.0));
	    tradeService.recordTrade(new Trade(testStock2, Calendar.getInstance().getTime(), 4, Indicator.SELL, 4.0));
	    List<Trade> result = tradeService.getAllTrades();
	    assertEquals(4, result.size());
	}

}
