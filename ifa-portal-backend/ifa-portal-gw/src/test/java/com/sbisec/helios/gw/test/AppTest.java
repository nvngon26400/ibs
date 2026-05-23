package com.sbisec.helios.gw.test;
//package com.sbisec.helios.gw.test;
//
//import com.sbisec.helios.ap.common.model.RestClientRequestModel;
//import com.sbisec.helios.ap.common.util.ApiRequestUtil;
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
//	public void testRestClientRequestModel() throws Exception {
//		RestClientRequestModel restClientRequestModel = new RestClientRequestModel("aaa","bbb",new Class[] {}, new Object[] {});
//		String result = restClientRequestModel.getRequestJsonString();
//		System.out.println(result);
//	}
//	public void testRestClientServiceNomal() throws Exception {
//		String result = ApiRequestUtil.execute(
//				"mCodeService",
//				"getMCodeList",
//				new Class[] {String.class, String.class, String.class},
//				new Object[] {"99","01","12"});
//				
//
//		System.out.println(result);
//	}
//}
