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
		try{
			SugarBuyer xrpbuyer = new SugarBuyer(bb, CurrencyPair.XRP_JPY, new BigDecimal("300"), new BigDecimal("1"), 2, 2);
			SugarBuyer monabuyer = new SugarBuyer(bb, CurrencyPair.MONA_JPY, new BigDecimal("50"), new BigDecimal("0.3"), 2, 2);
			SugarBuyer bccbuyer = new SugarBuyer(bb, CurrencyPair.BCC_JPY, new BigDecimal("50"), new BigDecimal("0.0005"), 2, 4);
			xrpbuyer.sendBuyOrder();
			Thread.sleep(500);
			xrpbuyer.sendBuyOrderLower();
			Thread.sleep(500);
			monabuyer.sendBuyOrder();
			Thread.sleep(500);
			monabuyer.sendBuyOrderLower();
			Thread.sleep(500);
			bccbuyer.sendBuyOrder();
			Thread.sleep(500);
			bccbuyer.sendBuyOrderLower();
			Thread.sleep(500);
		} catch (BitbankException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
