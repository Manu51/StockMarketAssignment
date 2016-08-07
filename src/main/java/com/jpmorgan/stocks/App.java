package com.jpmorgan.stocks;

import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jpmorgan.stocks.exception.StockMarketException;
import com.jpmorgan.stocks.model.Stock;
import com.jpmorgan.stocks.model.Stock.StockType;
import com.jpmorgan.stocks.model.Trade;
import com.jpmorgan.stocks.model.Trade.Indicator;
import com.jpmorgan.stocks.service.StockService;
import com.jpmorgan.stocks.service.TradeService;
import com.jpmorgan.stocks.service.impl.StockServiceImpl;
import com.jpmorgan.stocks.service.impl.TradeServiceImpl;

public class App 
{

	static Logger logger = Logger.getLogger(App.class);

	private ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
	private StockService stockService= (StockServiceImpl)context.getBean("stockServiceImpl");
	private TradeService tradeService=(TradeServiceImpl)context.getBean("tradeServiceImpl");

	private  Scanner scanner;

	public static void main(String[] args) {

		App app = new App();
		app.run();

	}

	private void run() {
		sampleStocks();
		displayMenu();
		String select = null;
		scanner = new Scanner(System.in);
		while (true) {
			select = scanner.nextLine();
			try {
				int option = Integer.parseInt(select);
				Stock userStock;
				double userPrice;

				switch (option) {
				case 1:
					userStock = getUserStock();
					userPrice = getUserStockPrice();
					calculateDividendYield(userStock, userPrice);
					break;
				case 2:
					userStock = getUserStock();
					userPrice = getUserStockPrice();
					calculatePERatio(userStock, userPrice);
					break;
				case 3:
					userStock = getUserStock();
					int shareQuantity = getUserShareQuantity();
					Indicator userIndicator = getUserIndicator();
					userPrice = getUserStockPrice();
					recordTrade(userStock, shareQuantity, userIndicator, userPrice);
					break;
				case 4:
					userStock = getUserStock();
					calculateVolumeWeightedStockPrice(userStock);
					break;
				case 5:
					calculateGBCE();
					break;
				case 6:
					scanner.close();
					System.exit(0);
					break;
				default:
					displayResult("Invalid option.Please input an option from the below options:");
					break;
				}
			} catch (NumberFormatException e) {
				logger.error("Invalid Option: Please input a number");
			} catch (StockMarketException e1) {
				logger.error(e1.getMessage());
			}
			System.out.println("");
			displayMenu();
		}
	}

	private void sampleStocks() {
		stockService.addStock(new Stock("TEA", StockType.COMMON, 0, 0, 100));
		stockService.addStock(new Stock("POP", StockType.COMMON, 8, 0, 100));
		stockService.addStock(new Stock("ALE", StockType.COMMON, 23, 0, 60));
		stockService.addStock(new Stock("GIN", StockType.PREFERRED, 8, 2, 100));
		stockService.addStock(new Stock("JOE", StockType.COMMON, 13, 0, 250));
	}

	private static void displayMenu() {
		System.out.println("");
		System.out.println("JPMorgan : Super Simple Stock Market Assignment");
		System.out.println("");
		System.out.println("Please input an option from the following:");
		System.out.println("1: Calculate dividend yield for a given stock");
		System.out.println("2: Calculate P/E ratio for a given stock");
		System.out.println("3: Record a trade for a stock");
		System.out.println("4: Calculate Volume Weighted Stock Price for stock");
		System.out.println("5: Calculate GBCE All Share Index");
		System.out.println("6: Exit");
	}


	private Stock getUserStock() throws StockMarketException {
		System.out.println("Please input stock symbol");
		String stockSymbol = scanner.nextLine();
		Stock stock = stockService.getStock(stockSymbol);
		if (stock == null) {
			throw new StockMarketException("Stock not found. Please enter a valid stock symbol.");
		}
		return stock;
	}

	private double getUserStockPrice() throws StockMarketException {
		System.out.println("Please input stock price");
		String stockPrice = scanner.nextLine();
		try {
			double result = Double.parseDouble(stockPrice);
			if (result <= 0) {
				throw new StockMarketException("Invalid price: Please input price greater than 0.");
			}
			return result;
		} catch (NumberFormatException e) {
			throw new StockMarketException("Invalid price: Please input a number");
		}
	}

	private Indicator getUserIndicator() throws StockMarketException {
		System.out.println("Please input trade indicator (BUY/SELL)");
		String type = scanner.nextLine();
		try {
			return Indicator.valueOf(type.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new StockMarketException("Invalid Trade Indicator: Please input BUY or SELL");
		}
	}

	private int getUserShareQuantity() throws StockMarketException {
		System.out.println("Please input quantity of shares");
		String quantity = scanner.nextLine();
		try {
			int result = Integer.parseInt(quantity);
			if (result <= 0) {
				throw new StockMarketException("Invalid quantity: Please input quantity greater than 0");
			}
			return result;
		} catch (NumberFormatException e) {
			throw new StockMarketException("Invalid quantity: Please enter a number.");
		}
	}


	private void calculateDividendYield(Stock stock, double price) {

		double result = stockService.calculateDividendYield(stock, price);
		displayResult("Dividend Yield: " + result);
	}

	private void calculatePERatio(Stock stock, double price) {
		double result = stockService.calculatePERatio(stock, price);
		displayResult("PE Ratio: " + result);
	}

	private void calculateVolumeWeightedStockPrice(Stock stock) {
		List<Trade> trades = tradeService.getTrades(stock, 15);
		if (trades == null || trades.isEmpty()) {
			displayResult("Cannot calculate Volume Weighted Stock Price: No trade record found for the stock.");
		} else {
			double result = stockService.calculateVolumeWeightedStockPrice(trades);
			displayResult("Volume Weighted Stock Price: " + result);
		}
	}

	private void recordTrade(Stock stock, int quantity, Indicator indicator, double price) {
		tradeService.recordTrade(new Trade(stock, Calendar.getInstance().getTime(),
				quantity, indicator, price));
		displayResult("Trade recorded for stock "+stock.getSymbol());
	}

	private void calculateGBCE() {
		List<Trade> allTrades = tradeService.getAllTrades();
		if (allTrades == null || allTrades.isEmpty()) {
			displayResult("Cannot calculate GBCE: No trade record found for the stock.");
		} else {
			displayResult("GBCE: " + stockService.calculateGBCE(allTrades));
		}
	}

	private static void displayResult(String result) {
		System.out.println("-------------------------------------");
		logger.info(result);
		System.out.println("-------------------------------------");
	}
}