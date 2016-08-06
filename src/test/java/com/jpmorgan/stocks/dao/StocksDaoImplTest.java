/**
 * 
 */
package com.jpmorgan.stocks.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.stocks.dao.impl.StocksDaoImpl;
import com.jpmorgan.stocks.model.Stock;
import com.jpmorgan.stocks.model.Stock.StockType;

/**
 * @author manisha
 *
 */
public class StocksDaoImplTest {

	
	 private StocksDaoImpl stockDao;
	 private Stock testStock;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		stockDao = new StocksDaoImpl();
	    testStock = new Stock("TEST", StockType.COMMON,8 , 0, 80);

	}

	/**
	 * Test method for {@link com.jpmorgan.stocks.dao.impl.StocksDaoImpl#addStock(com.jpmorgan.stocks.model.Stock)}.
	 */
	@Test
	public void testAddandGetStock() {
		stockDao.addStock(testStock);
		assertEquals(testStock, stockDao.getStock(testStock.getSymbol()));
	}

	

}
