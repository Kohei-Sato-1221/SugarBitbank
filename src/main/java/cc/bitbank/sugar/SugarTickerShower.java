package cc.bitbank.sugar;

import java.io.IOException;
import java.math.BigDecimal;

import cc.bitbank.Bitbankcc;
import cc.bitbank.entity.enums.CurrencyPair;
import cc.bitbank.exception.BitbankException;

public class SugarTickerShower {

	public static void main(String[] args) {
//		RestClient.setRestApiKey(SugarKeyReader.getRestApiKey());
//		RestClient.get("sato", "body");
//		if(true) return;
		Bitbankcc bb = new Bitbankcc();
		try{
			SugarOrderValues xrpValues = SugarKeyReader.getCoinValue(CurrencyPair.XRP_JPY);
			xrpValues = xrpValues != null ? xrpValues : new SugarOrderValues("xrp_jpy", "50", "75");
			SugarBuyer xrpbuyer = new SugarBuyer(bb, xrpValues, new BigDecimal("1"), 2, 2);
			while(true) {
				try {
					xrpbuyer.showTicker();
					Thread.sleep(10000);
				}catch(Exception e) {
					System.out.println("## Exception!!");
				}				
			}
		} catch (BitbankException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
