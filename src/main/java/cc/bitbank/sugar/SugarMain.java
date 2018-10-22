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

public class SugarMain {

	public static void main(String[] args){
		Bitbankcc bb = new Bitbankcc();
		bb.setKey(SugarKeyReader.getReader());
		Ticker ticker;
//		while(true) {
			try{
				ticker = bb.getTicker(CurrencyPair.XRP_JPY);
				System.out.println(ticker.toString());
				Order order = bb.sendOrder(CurrencyPair.XRP_JPY, BigDecimal.valueOf(50), BigDecimal.valueOf(1), OrderSide.BUY, OrderType.LIMIT);
				System.out.println(order);
			} catch (BitbankException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
//		}
	}

}
