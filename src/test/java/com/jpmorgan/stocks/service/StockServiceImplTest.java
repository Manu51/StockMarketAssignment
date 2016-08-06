/**
 * 
 */
package com.jpmorgan.stocks.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import com.jpmorgan.stocks.model.Stock;
import com.jpmorgan.stocks.model.Stock.StockType;
import com.jpmorgan.stocks.model.Trade;
import com.jpmorgan.stocks.model.Trade.Indicator;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * @author manisha
 *
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = {"classpath:/testApplicationContext.xml"})
public class StockServiceImplTest {

	 @Autowired
	  private StockService stockService;

	  
	  private Stock testStock1;
	  private Stock testStock2;
	  private Stock testStock3;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		testStock1 = new Stock("TEST", StockType.COMMON, 1, 0, 4);
		testStock2 = new Stock("TEST2", StockType.PREFERRED, 10, 2, 1);
		testStock3 = new Stock("TEST3", StockType.COMMON, 10, 2, 1);
	}
	
	@After
	  public void destroy() {
	    stockService.reset();
	  }

	/**
	 * Test method for {@link com.jpmorgan.stocks.service.impl.StockServiceImpl#addStock(com.jpmorgan.stocks.model.Stock)}.
	 */
	@Test
	public void testAddandGetStock() {
		 stockService.addStock(testStock1);
		 Stock result = stockService.getStock(testStock1.getSymbol());
		 assertEquals(testStock1, result);
	}

	
	/**
	 * Test method for {@link com.jpmorgan.stocks.service.impl.StockServiceImpl#calculateDividendYield(com.jpmorgan.stocks.model.Stock, double)}.
	 */
	@Test
	public void testCalculateDividendYield() {
		assertEquals(0.67, stockService.calculateDividendYield(testStock1, 1.5), 0);
	}

	/**
	 * Test method for {@link com.jpmorgan.stocks.service.impl.StockServiceImpl#calculatePERatio(com.jpmorgan.stocks.model.Stock, double)}.
	 */
	@Test
	public void testCalculatePERatio() {
		assertEquals(2.0, stockService.calculatePERatio(testStock1, 2), 0);
		 
	}

	/**
	 * Test method for {@link com.jpmorgan.stocks.service.impl.StockServiceImpl#calculateVolumeWeightedStockPrice(java.util.List)}.
	 */
	@Test
	public void testCalculateVolumeWeightedStockPrice() {
		 List<Trade> trades = new ArrayList<Trade>();
		    trades.add(new Trade(testStock1, Calendar.getInstance().getTime(), 1, Indicator.BUY, 1));
		    trades.add(new Trade(testStock1, Calendar.getInstance().getTime(), 2, Indicator.BUY, 1.5));
		    trades.add(new Trade(testStock1, Calendar.getInstance().getTime(), 3, Indicator.BUY, 2));
		    assertEquals(1.67, stockService.calculateVolumeWeightedStockPrice(trades), 0);

	}

	/**
	 * Test method for {@link com.jpmorgan.stocks.service.impl.StockServiceImpl#calculateGBCE(java.util.List)}.
	 */
	@Test
	public void testCalculateGBCE() {
		List<Trade> trades = new ArrayList<Trade>();
	    trades.add(new Trade(testStock1, Calendar.getInstance().getTime(), 1, Indicator.BUY, 1));
	    trades.add(new Trade(testStock2, Calendar.getInstance().getTime(), 2, Indicator.BUY, 1.5));
	    trades.add(new Trade(testStock3, Calendar.getInstance().getTime(), 3, Indicator.BUY, 2));
	    assertEquals(1.44, stockService.calculateGBCE(trades), 0);

	}

}
