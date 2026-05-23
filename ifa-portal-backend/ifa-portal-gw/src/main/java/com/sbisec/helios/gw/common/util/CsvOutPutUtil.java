package com.sbisec.helios.gw.common.util;

import java.io.File;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.StringUtil;
//import com.sbibits.horus.ap.common.enums.ErrorLevel;
//import com.sbibits.horus.ap.compliance.model.GetIdAndDirectoryModel;
//import com.sbibits.horus.ap.compliance.service.ComplianceService;

public abstract class CsvOutPutUtil {
	/** ComplianceService */
//	private ComplianceService complianceService =
//			(ComplianceService) SerivceContext.getInstance().getService("com.sbibits.horus.ap.compliance.service.ComplianceService");

	protected static final String TEMPORARY_CSV_FILE_NAME     = "%s_%s_%s.csv";
	protected static final String CSV_TEMPORARY_DATE_PATTERN  = "yyyyMMddHHmmssSSS";
	protected static final String CSV_TEMPORARY_FUNC_ID       = "M6";
	protected static final String CSV_TEMPORARY_CAT_ID        = "0";
	protected static final String CSV_SEPARATER               = ",";
	protected static final String DOUBLE_QUOTATION            = "\"";

	protected String getCsvHeader() {
		throw new UnsupportedOperationException("When the controller using doOutputCsvFile(), it's have to override getCsvHeader().");
	}

	protected String getCsvHeader(String pattern) {
		throw new UnsupportedOperationException("When the controller using doOutputCsvFile(), it's have to override getCsvHeader().");
	}

	protected String getCsvLine(ModelBase model) {
		throw new UnsupportedOperationException("When the controller using doOutputCsvFile(), it's have to override getCsvLine().");
	}

	protected String getCsvLine(ModelBase model, String pattern) {
		throw new UnsupportedOperationException("When the controller using doOutputCsvFile(), it's have to override getCsvLine().");
	}

	/**
	 * doCreateCsvFile
	 * csvファイルをテンポラリディレクトリーに出力する。
	 * @param DataList dataList
	 * @param String sessionId
	 * @param String userId
	 * @param String pattern
	 * @return String フルパスファイル名
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public String doCreateCsvFile(DataList dataList, String sessionId, String userId, String pattern ) throws Exception {
		// Create csv temporary file name
		String tmpCsv = String.format( TEMPORARY_CSV_FILE_NAME
									 , DateTimeFormatter.ofPattern(CSV_TEMPORARY_DATE_PATTERN).format(LocalDateTime.now())
									 , sessionId
									 , userId
									 );
		try {
			tmpCsv = getCsvTemporaryDirectory() + tmpCsv;
			File file = new File(tmpCsv);
			PrintWriter out = new PrintWriter(file);

			List list = dataList.getDataList();
			int listSize = list.size();

			// Write header
			out.println(pattern != null ? getCsvHeader(pattern) : getCsvHeader());

			for (int i = 0; i < listSize; i++) {
				if (pattern != null) {
					out.println(getCsvLine((ModelBase) list.get(i), pattern));
				} else {
					out.println(getCsvLine((ModelBase) list.get(i)));
				}
			}

			out.flush();
			out.close();

		// Catch and ingore.
		} catch (Exception e) {
			throw e;
		}

		return tmpCsv;
	}

	/**
	 * getCsvTemporaryDirectory
	 * csvファイル出力用テンポラリディレクトリーを取得
	 *
	 * @return csvファイル出力用テンポラリディレクトリー<br/>
	 *         TB_MED_TEMP_FILE_DIR（"M6","0"）
	 * @throws Exception
	 */
	protected String getCsvTemporaryDirectory() throws Exception {
//		try{
//			DataList<GetIdAndDirectoryModel> dataList = new DataList<GetIdAndDirectoryModel>();
//			dataList = complianceService.GetIdAndDirectory(CSV_TEMPORARY_FUNC_ID, CSV_TEMPORARY_CAT_ID);
//			if (dataList.getDataList().isEmpty()) {
//				return String.valueOf(ErrorLevel.FATAL.getId());
//			}
//			return dataList.get(0).getFileDir();
//		}catch(Exception e){
//			throw e;
//		}
		
		return "dummy";
	}

	/**
	 * addEscapeDoubleQuotation
	 * ダブルコーテーション「"」を「""」に変換する。
	 * @param src
	 * @return
	 */
	protected static String addEscapeDoubleQuotation(String src) {
		src = src.replaceAll("\"", "\"\"");
		return src;
	}

	/**
	 * addZero
	 *
	 * @param val
	 * @return String
	 */
	protected static String addZero(String val)
	{
		if (val == null)
			return "";
		if(val.length()>0 && val.startsWith("."))
			return ("0"+val);
		return val;
	}

	/**
	 * addZero
	 *
	 * @param num
	 * @return String
	 */
	protected String addZero(BigDecimal num)
	{
		String result = StringUtil.numberNullToZero(num);
		if(result.length()>0 && result.startsWith("."))
			return ("0"+result);
		return result;
	}


	/**
	 * addPourcent
	 *
	 * @param val
	 * @return String
	 */
	protected static String addPourcent(String val)
	{
		if (val == null) return "";
		if(val.length()>0)
			return (val+"%");
		return val;
	}

	/**
	 * addPourcent
	 *
	 * @param num
	 * @return String
	 */
	protected String addPourcent(BigDecimal num)
	{
		String result = addZero(num);
		if (result == null) return "";
		if(result.length()>0)
			return (result+"%");
		return result;
	}


}
