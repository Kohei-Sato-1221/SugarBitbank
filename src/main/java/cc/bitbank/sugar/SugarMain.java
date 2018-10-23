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
			xrpbuyer.sendBuyOrder();
		} catch (BitbankException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
