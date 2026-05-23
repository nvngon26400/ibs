package jp.co.sbisec.pcenter.dto.heracross;


import java.util.ArrayList;
import java.util.List;

public class RealStockSnapshotstub {

	public RealQuoteSnapshotOut getDtoApi001Response() {
		RealQuoteSnapshotOut realQuoteSnapshotOut = new RealQuoteSnapshotOut();
		RealQuoteSnapshot realQuoteSnapshot = new RealQuoteSnapshot();

		//TODO スタブ設定の為除去すること
		realQuoteSnapshot.setMarketCode("TKY");
		realQuoteSnapshot.setProductCode("1301");
		realQuoteSnapshot.setCurPrice("0");
		realQuoteSnapshot.setPriceChange("+0");
		realQuoteSnapshot.setHighPrice("0");
		realQuoteSnapshot.setLowPrice("0");
		realQuoteSnapshot.setCurPriceDate("20240301");
		realQuoteSnapshot.setCurPriceTime("");
		realQuoteSnapshot.setOpenPrice("0");
		realQuoteSnapshot.setLastClosingPrice("3565");
		realQuoteSnapshot.setVolume("0");
		realQuoteSnapshot.setYtdHighPrice("12250");
		realQuoteSnapshot.setYtdLowPrice("490");
		realQuoteSnapshot.setOpenPriceTime("0");
		realQuoteSnapshot.setHighPriceTime("0");
		realQuoteSnapshot.setLowPriceTime("0");
		realQuoteSnapshot.setVolTime("0");
		realQuoteSnapshot.setBidPrice("795");
		realQuoteSnapshot.setAskPrice("795");
		realQuoteSnapshot.setBidSize("292100");
		realQuoteSnapshot.setAskSize("11029500");
		realQuoteSnapshot.setBidPrice1("795");
		realQuoteSnapshot.setBidPrice2("0");
		realQuoteSnapshot.setBidPrice3("0");
		realQuoteSnapshot.setBidPrice4("0");
		realQuoteSnapshot.setBidPrice5("0");
		realQuoteSnapshot.setBidPrice6("0");
		realQuoteSnapshot.setBidPrice7("0");
		realQuoteSnapshot.setBidPrice8("0");
		realQuoteSnapshot.setBidPrice9("0");
		realQuoteSnapshot.setBidPrice10("0");
		realQuoteSnapshot.setAskPrice1("795");
		realQuoteSnapshot.setAskPrice2("944");
		realQuoteSnapshot.setAskPrice3("945");
		realQuoteSnapshot.setAskPrice4("946");
		realQuoteSnapshot.setAskPrice5("0");
		realQuoteSnapshot.setAskPrice6("0");
		realQuoteSnapshot.setAskPrice7("0");
		realQuoteSnapshot.setAskPrice8("0");
		realQuoteSnapshot.setAskPrice9("0");
		realQuoteSnapshot.setAskPrice10("0");
		realQuoteSnapshot.setBidSize1("292100");
		realQuoteSnapshot.setBidSize2("0");
		realQuoteSnapshot.setBidSize3("0");
		realQuoteSnapshot.setBidSize4("0");
		realQuoteSnapshot.setBidSize5("0");
		realQuoteSnapshot.setBidSize6("0");
		realQuoteSnapshot.setBidSize7("0");
		realQuoteSnapshot.setBidSize8("0");
		realQuoteSnapshot.setBidSize9("0");
		realQuoteSnapshot.setBidSize10("0");
		realQuoteSnapshot.setAskSize1("11029500");
		realQuoteSnapshot.setAskSize2("3100");
		realQuoteSnapshot.setAskSize3("300");
		realQuoteSnapshot.setAskSize4("2400");
		realQuoteSnapshot.setAskSize5("0");
		realQuoteSnapshot.setAskSize6("0");
		realQuoteSnapshot.setAskSize7("0");
		realQuoteSnapshot.setAskSize8("0");
		realQuoteSnapshot.setAskSize9("0");
		realQuoteSnapshot.setAskSize10("0");
		realQuoteSnapshot.setMarketCap("0");
		realQuoteSnapshot.setCurPriceTick("0");
		realQuoteSnapshot.setTurnoverAmount("0");
		realQuoteSnapshot.setCurPriceTone("0");
		realQuoteSnapshot.setLastClosingPriceDate("20240229");
		realQuoteSnapshot.setPerChange("+0");
		realQuoteSnapshot.setMBidTone("G");
		realQuoteSnapshot.setMAskTone("S");
		realQuoteSnapshot.setVwap("0");
		realQuoteSnapshot.setImpAskSize("11029500");
		realQuoteSnapshot.setImpBidSize("281200");
		realQuoteSnapshot.setYtdHighPriceDate("20230105");
		realQuoteSnapshot.setYtdLowPriceDate("20231229");
		realQuoteSnapshot.setBuyMarginNc("+0");
		realQuoteSnapshot.setSellMarginNc("+0");
		realQuoteSnapshot.setBuyMargin("0");
		realQuoteSnapshot.setSellMargin("0");
		realQuoteSnapshot.setCombineAskSize("0");
		realQuoteSnapshot.setCombineBidSize("0");
		realQuoteSnapshot.setOrderUnit("01");

		List<RealQuoteSnapshot> values = new ArrayList<RealQuoteSnapshot>();
		values.add(realQuoteSnapshot);

		realQuoteSnapshotOut.setResult("0");
		realQuoteSnapshotOut.setFeedData(values);
		return realQuoteSnapshotOut;
	}
}

