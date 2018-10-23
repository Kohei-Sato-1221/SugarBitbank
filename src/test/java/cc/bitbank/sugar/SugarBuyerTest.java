package cc.bitbank.sugar;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import cc.bitbank.Bitbankcc;
import cc.bitbank.entity.enums.CurrencyPair;
import cc.bitbank.exception.BitbankException;
import cc.bitbank.sugar.SugarBuyer;
import cc.bitbank.sugar.SugarKeyReader;

class SugarBuyerTest {

	@Test
	void test() {
		Bitbankcc bb = new Bitbankcc();
		bb.setKey(SugarKeyReader.getReader());
		SugarBuyer xrpbuyer = null;
		try {
			xrpbuyer = new SugarBuyer(bb, CurrencyPair.XRP_JPY, new BigDecimal("300"), new BigDecimal("1"), 2, 2);
			System.out.println(xrpbuyer);
		} catch (BitbankException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BigDecimal buyPrice = xrpbuyer.calculateBuyPriceNormal();
		BigDecimal buyAmount = xrpbuyer.calculateBuyAmount(buyPrice);
		BigDecimal buyPrice2 = xrpbuyer.calculateBuyPriceLower();
		BigDecimal buyAmount2 = xrpbuyer.calculateBuyAmount(buyPrice2);
		assertEquals("true", true);
	}

}
