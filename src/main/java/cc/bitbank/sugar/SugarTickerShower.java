package cc.bitbank.sugar;

import java.io.IOException;
import java.math.BigDecimal;

import cc.bitbank.Bitbankcc;
import cc.bitbank.entity.enums.CurrencyPair;
import cc.bitbank.exception.BitbankException;
import cc.bitbank.sugar.restapi.RestClient;

public class SugarTickerShower {

	public static void main(String[] args) {
//		RestClient.setRestApiKey(SugarKeyReader.getRestApiKey());
//		RestClient.get("sato", "body");
//		if(true) return;
		Bitbankcc bb = new Bitbankcc();
//		bb.setKey(SugarKeyReader.getReader());
		try{
			SugarBuyer xrpbuyer = new SugarBuyer(bb, CurrencyPair.XRP_JPY, new BigDecimal("300"),new BigDecimal("300"), new BigDecimal("1"), 2, 2);
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
