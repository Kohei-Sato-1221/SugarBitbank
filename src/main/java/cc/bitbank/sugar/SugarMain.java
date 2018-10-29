package cc.bitbank.sugar;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cc.bitbank.Bitbankcc;
import cc.bitbank.entity.enums.CurrencyPair;
import cc.bitbank.exception.BitbankException;
import cc.bitbank.sugar.restapi.RestClient;

public class SugarMain {

	public static void main(String[] args){
		String NEWLINE = System.lineSeparator();
		Bitbankcc bb = new Bitbankcc();
		List<SugarBuyer> buyers = new ArrayList<SugarBuyer>();
		bb.setKey(SugarKeyReader.getReader());
		
		RestClient.setRestApiKey(SugarKeyReader.getRestApiKey());
		StringBuilder sb = new StringBuilder();
		sb.append("Tsumitate Orders:");
		sb.append(NEWLINE);
		try{
			if(hasTradePair(args, "btc")) buyers.add(new SugarBuyer(bb, CurrencyPair.BTC_JPY, new BigDecimal("150"), new BigDecimal("250"), new BigDecimal("0.0002"), 2, 4));
			if(hasTradePair(args, "xrp")) buyers.add(new SugarBuyer(bb, CurrencyPair.XRP_JPY, new BigDecimal("300"), new BigDecimal("300"), new BigDecimal("1"), 2, 2));
			if(hasTradePair(args, "mona")) buyers.add(new SugarBuyer(bb, CurrencyPair.MONA_JPY, new BigDecimal("50"), new BigDecimal("75"), new BigDecimal("0.3"), 2, 2));
			if(hasTradePair(args, "bcc")) buyers.add(new SugarBuyer(bb, CurrencyPair.BCC_JPY, new BigDecimal("50"),  new BigDecimal("75"), new BigDecimal("0.0005"), 2, 4));
			for(SugarBuyer sbuyer : buyers) {
				sb.append(sbuyer.sendBuyOrder());
				sb.append(NEWLINE);
				Thread.sleep(500);
				sb.append(sbuyer.sendBuyOrderLower());
				Thread.sleep(500);
				sb.append(NEWLINE);
				sb.append(NEWLINE);
			}
			RestClient.get("Orders in bitbank", sb.toString());
		} catch (BitbankException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static boolean hasTradePair(String[] args, String pair) {
		if(args == null || args.length == 0) {
			return true;
		}
		for(String tempPair : args) {
			if(tempPair.equalsIgnoreCase(pair)) {
				return true;
			}
		}
		return false;
	}

}
