package cc.bitbank.sugar;

import java.io.IOException;
import java.math.BigDecimal;

import cc.bitbank.Bitbankcc;
import cc.bitbank.entity.Order;
import cc.bitbank.entity.Ticker;
import cc.bitbank.entity.enums.CurrencyPair;
import cc.bitbank.entity.enums.OrderSide;
import cc.bitbank.entity.enums.OrderType;
import cc.bitbank.exception.BitbankException;

public class SugarBuyer {
	private CurrencyPair pair;
	private BigDecimal baseAmountJPY;
	private BigDecimal baseAmountJPYLow; 
	private BigDecimal minimumBuyAmount;
	private int roundPrice = 0;
	private int roundAmt = 0;
	Bitbankcc bb;
	Ticker ticker;
	
	public SugarBuyer(Bitbankcc bb, CurrencyPair pair, BigDecimal baseAmountJPY, BigDecimal baseAmountJPYLow,BigDecimal minimumBuyAmount, int roundPrice, int roundAmt) throws BitbankException, IOException {
		this.bb = bb;
		this.pair = pair;
		this.baseAmountJPY = baseAmountJPY;
		this.baseAmountJPYLow = baseAmountJPYLow;
		this.ticker = bb.getTicker(pair);
		this.minimumBuyAmount = minimumBuyAmount;
		this.roundPrice = roundPrice;
		this.roundAmt = roundAmt;
	}
	
	public String sendBuyOrder() throws BitbankException, IOException {
		BigDecimal buyPrice = calculateBuyPriceNormal();
		BigDecimal buyAmount = calculateBuyAmount(buyPrice, baseAmountJPY);
		System.out.println(buyPrice + " " + buyAmount);
		Order order = bb.sendOrder(pair, buyPrice, buyAmount, OrderSide.BUY, OrderType.LIMIT);
		System.out.println("" + order);
		return pair + " price:" + buyPrice + " vol:" + buyAmount;
	}
	
	public String sendBuyOrderLower() throws BitbankException, IOException {
		BigDecimal buyPricelow = calculateBuyPriceLower();
		BigDecimal buyAmountlow = calculateBuyAmount(buyPricelow, baseAmountJPYLow);
		Order order2 = bb.sendOrder(pair, buyPricelow, buyAmountlow, OrderSide.BUY, OrderType.LIMIT);
		System.out.println("" + order2);
		return pair + " price:" + buyPricelow + " vol:" + buyAmountlow;
	}
	
	public BigDecimal calculateBuyAmount(BigDecimal buyPrice, BigDecimal baseAmountJPY) {
		BigDecimal retValue = baseAmountJPY.divide(buyPrice, roundAmt, BigDecimal.ROUND_HALF_UP);
		if(retValue.compareTo(minimumBuyAmount) < 0) {
			System.out.println("set minimuBuyAmount:" + minimumBuyAmount);
			retValue = minimumBuyAmount;
		}
		return retValue;
	}
	
	public BigDecimal calculateBuyPriceNormal() {
		return calculateBuyPrice("0.6","0.4");
	}
	
	public BigDecimal calculateBuyPriceLower() {
		return calculateBuyPrice("0.2","0.8");
	}
	
	private BigDecimal calculateBuyPrice(String percent1, String percent2) {
		BigDecimal lastPrice = ticker.last;
		BigDecimal lowPrice = ticker.low;
//		System.out.println("lastPrice:" + lastPrice + "/ lowPrice:" + lowPrice);
		lastPrice = lastPrice.multiply(new BigDecimal(percent1));
	    lowPrice = lowPrice.multiply(new BigDecimal(percent2));
//	    System.out.println("#lastPrice:" + lastPrice + "/ #lowPrice:" + lowPrice);
	    BigDecimal retValue = lastPrice.add(lowPrice);
	    retValue = retValue.setScale(roundPrice, BigDecimal.ROUND_HALF_UP);
//	    System.out.println("calculateBuyPrice: " + retValue);
	    return retValue;
	}
	
	public void showTicker() throws BitbankException, IOException {
		this.ticker = this.bb.getTicker(this.pair);
		System.out.println(this.ticker); 
	}
}
