//package com.sbibits.cbcommon.online;
//
//import java.text.DecimalFormat;
//
//import com.sbisec.helops.gw.common.dto.DtoIn;
//import com.sbisec.helops.gw.common.dto.QueryAccountBalanceIn;
//import com.sbisec.helops.gw.common.dto.QueryAccountBalanceInData;
//import com.sbisec.helops.gw.common.dto.QueryAccountBalanceOut;
//import com.sbisec.helops.gw.common.dto.RequestHeader;
//import com.sbisec.helops.gw.common.dto.yanap.NriQueryAccountBalanceIn;
//import com.sbisec.helops.gw.common.util.HttpApiWrapper;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
///**
// * Unit test for simple App.
// */
//public class AppTest extends TestCase {
//
//	/**
//	 * Create the test case.
//	 *
//	 * @param testName name of the test case
//	 */
//	public AppTest(String testName) {
//		super(testName);
//	}
//
//	/**
//	 * suite.
//	 * 
//	 * @return the suite of tests being tested
//	 */
//	public static Test suite() {
//		return new TestSuite(AppTest.class);
//	}
//
//	/**
//	 * Rigourous Test :-).
//	 */
//	public void testApp() {
//		assertTrue(true);
//	}
//
//	/**
//	 * Rigourous Test :-).
//	 */
//	public void testApiWrapper() throws Exception {
//		QueryAccountBalanceInData accountBalanceInData = new QueryAccountBalanceInData("123","123456");
//		QueryAccountBalanceIn input = new QueryAccountBalanceIn(new RequestHeader(), accountBalanceInData) ;
//		QueryAccountBalanceOut nrioutput = null;
//		int kozaNo = Integer.parseInt(input.getIndata().getKozaNo());
//
//		/** 口座番号int->String変換用 */
//		DecimalFormat dcfAcc = new DecimalFormat("0000000");
//
//		input.getIndata().setKozaNo(dcfAcc.format(kozaNo));
//
//		// ECGateway呼び出し
//		final HttpApiWrapper wrapper = HttpApiWrapper.get();
//
//		// Request Headerに「部店」を設定する
//		RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
//		// api inputを作成する
//		DtoIn dtoIn = new NriQueryAccountBalanceIn(header, input.getIndata());
//		// NRI_QueryAccountBalanceを呼び出す
//		nrioutput = wrapper.call(dtoIn, QueryAccountBalanceOut.class);
//	}
//}
