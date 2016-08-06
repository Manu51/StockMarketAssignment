package com.jpmorgan.stocks.model;

import java.util.Date;

public class Trade implements Comparable<Trade> {

	private Stock stock;
	private Date timestamp;
	private int quantity;
	private double price;

	public enum Indicator
	{
		BUY,
		SELL
	}
   
	public Indicator indicator;
	
	public Trade(Stock stock, Date timestamp, int quantity, Indicator indicator, double price) {
	    super();
	    this.stock = stock;
	    this.timestamp = timestamp;
	    this.quantity = quantity;
	    this.indicator = indicator;
	    this.price = price;
	  }
	
	public Indicator getIndicator() {
		return indicator;
	}

	public void setIndicator(Indicator indicator) {
		this.indicator = indicator;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	@Override
	public int compareTo(Trade trade) {
		return trade.getTimestamp().compareTo(this.timestamp);
	}


}
