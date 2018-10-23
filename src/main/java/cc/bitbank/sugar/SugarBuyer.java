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
	private BigDecimal minimumBuyAmount;
	private int roundPrice = 0;
	private int roundAmt = 0;
	Bitbankcc bb;
	Ticker ticker;
	
	public SugarBuyer(Bitbankcc bb, CurrencyPair pair, BigDecimal baseAmountJPY, BigDecimal minimumBuyAmount, int roundPrice, int roundAmt) throws BitbankException, IOException {
		this.bb = bb;
		this.pair = pair;
		this.baseAmountJPY = baseAmountJPY;
		this.ticker = bb.getTicker(pair);
		this.minimumBuyAmount = minimumBuyAmount;
		this.roundPrice = roundPrice;
		this.roundAmt = roundAmt;
	}
	
	public void sendBuyOrder() throws BitbankException, IOException {
		BigDecimal buyPrice = calculateBuyPriceNormal();
		BigDecimal buyAmount = calculateBuyAmount(buyPrice);
		BigDecimal buyPricelow = calculateBuyPriceLower();
		BigDecimal buyAmountlow = calculateBuyAmount(buyPricelow);
		Order order = bb.sendOrder(pair, buyPrice, buyAmount, OrderSide.BUY, OrderType.LIMIT);
		System.out.println(order);
		Order order2 = bb.sendOrder(pair, buyPricelow, buyAmountlow, OrderSide.BUY, OrderType.LIMIT);
		System.out.println(order2);
	}
	
	public BigDecimal calculateBuyAmount(BigDecimal buyPrice) {
		BigDecimal retValue = baseAmountJPY.divide(buyPrice, roundAmt, BigDecimal.ROUND_HALF_UP);
		if(retValue.compareTo(minimumBuyAmount) < 0) {
			retValue = minimumBuyAmount;
		}
//		System.out.println("calculateBuyAmount: " + retValue);
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
}
