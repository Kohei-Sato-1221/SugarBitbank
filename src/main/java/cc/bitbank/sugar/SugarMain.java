package cc.bitbank.sugar;

import java.io.IOException;
import java.math.BigDecimal;

import cc.bitbank.Bitbankcc;
import cc.bitbank.entity.Ticker;
import cc.bitbank.entity.enums.CurrencyPair;
import cc.bitbank.exception.BitbankException;

public class SugarMain {

	public static void main(String[] args){
		Bitbankcc bb = new Bitbankcc();
		Ticker ticker;
		
		try{
			ticker = bb.getTicker(CurrencyPair.BTC_JPY);
			BigDecimal btcprice = ticker.last;
			System.out.println("btc:" + btcprice.longValueExact());
		} catch (BitbankException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
