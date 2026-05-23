package com.sbisec.helios.ap.brokerageMenu.customerMenu.util;

/**
 * COMET API Util.
 * 
 * @author shuchen.xin
 * @date 01/12/2022
 */
public class AmericaStockReturnCode {

	/** athena api error */
	public static final String ATHENA_API_ERROR_CODE = "athena_api_error";
	/** 検索 エラー*/
	public static final String ATHENA_API_SELECT_ERROR_CODE = "athena_select_error";
	/** 登録 エラー*/
	public static final String ATHENA_API_INSERT_ERROR_CODE = "athena_insert_error";
	/** 確認 エラー */
	public static final String ATHENA_API_CONFIRM_ERROR_CODE = "athena_confirm_error";
	/** 訂正 エラー*/
	public static final String ATHENA_API_CORRECT_ERROR_CODE = "athena_correct_error";
	/** 取消 エラー*/
	public static final String ATHENA_API_DELETE_ERROR_CODE = "athena_delete_error";
	
	/** HTTP Status Code */
	public class HttpStatusCode {
		/** OK */
		public static final int HTTP_200_CODE = 200;
		/** Bad Request */
		public static final int HTTP_400_CODE = 400;
		/** Forbidden */
		public static final int HTTP_403_CODE = 403;
		/** Not Found */
		public static final int HTTP_404_CODE = 404;
		/** Request Timeout */
		public static final int HTTP_408_CODE = 408;
		/** Internal Server */
		public static final int HTTP_500_CODE = 500;
		/** Service Unavailable */
		public static final int HTTP_503_CODE = 503;
	}
	
	/** メッセージ code */
	public class MsgCode {
		/** エラー: 注文発注前の注文データが登録できないため、注文しませんでした。 */
		public static final String ERRORS_FRS_PREORDEREXECUTION_FAILED = "errors.frs.preOrderExecution.failed";
		/** エラー: 他のユーザーは当該{0}を操作しました。ご確認ください。 */
		public static final String ERRORS_EXCLUSIVE = "errors.exclusive";
		/** エラー: 振替指示前の指示データが登録できないため、振替指示しませんでした。 */
		public static final String ERRORS_FRS_PRE_TRANSFERORDER_FAILED = "errors.frs.preTransferOrder.failed";
		/** エラー: {0}は{1}以下で入力してください。 */
		public static final String ERRORS_FRS_MAXVALUE_EXCEEDED = "errors.frs.maxValue.exceeded";
		/**
		 * エラー:当該預りは店頭取引で売却注文が行われているため、振替できません。 銘柄：[{0}]、預り区分：[{1}] 銘柄：[{0}]、預り区分：[{1}]
		 */
		public static final String ERRORS_FRS_COUNTERORDER_EXIST = "errors.frs.counterOrder.exist";
		/** 設定指示前の指示データが登録できないため、設定指示しませんでした。 */
		public static final String ERRORS_FRS_PREAUTOTRANSFERSETTING_FAILED = "errors.frs.preAutoTransferSetting.failed";

		/** 注文発注後の注文データが更新できませんでした。注文は完了しています。 */
		public static final String WARNINGS_FRS_POSTORDEREXECUTION_COMPLETED = "warnings.frs.postOrderExecution.completed";
		/** 警告： 振替指示後の指示データが更新できませんでした。振替指示は完了しています。 */
		public static final String WARNINGS_FRS_POST_ORDERTRANSFER_COMPLETED = "warnings.frs.postOrderTransfer.completed";
		/** 設定指示後の指示データが更新できませんでした。設定指示は完了しています。 */
		public static final String WARNINGS_FRS_POSTAUTOTRANSFERSETTING_COMPLETED = "warnings.frs.postAutoTransferSetting.completed";

		/** 通常：  注文発注は完了しています。 */
		public static final String INFO_FRS_POSTORDEREXECUTION_COMPLETED = "info.frs.postOrderExecution.completed";

	}

}
