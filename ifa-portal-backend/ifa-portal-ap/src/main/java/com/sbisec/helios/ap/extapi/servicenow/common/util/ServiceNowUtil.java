package com.sbisec.helios.ap.extapi.servicenow.common.util;

import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.extapi.servicenow.dto.common.IfaServiceNowDataList;

/**
 * ServiceNow共通Util
 *
 * @author SCSK
 *
 */
public class ServiceNowUtil {
    
    private static final String SUCCESS_MESSAGE = "";
    
    /**
     * DataListを生成する。
     *
     * @param innerDataList DataListの内部dataListに設定するリスト
     * @param affectedRows 登録・削除・更新した件数
     */
    public static void createDataListServiceNow(IfaServiceNowDataList innerDataList, int affectedRows) {
        
        createDataListServiceNow(innerDataList, affectedRows, ErrorLevel.SUCCESS, SUCCESS_MESSAGE, SUCCESS_MESSAGE);
    }
    
    /**
     * DataListを生成する。
     *
     * @param innerDataList DataListの内部dataListに設定するリスト
     * @param affectedRows 登録・削除・更新した件数
     * @param errorLevel エラーレベル(正常:0, 情報:1, 警告:2, 業務エラー:-1, システムエラー:-2)
     * @param returnCode リターンコード（メッセージID）
     * @param message メッセージ
     */
    public static void createDataListServiceNow(IfaServiceNowDataList innerDataList, int affectedRows,
            ErrorLevel errorLevel, String returnCode, String message) {
        
        innerDataList.setAffectedRows(affectedRows);
        innerDataList.setErrorLevel(errorLevel.getId());
        innerDataList.setMessage(message);
        innerDataList.setReturnCode(returnCode);
        innerDataList.setRequestedTime(IfaCommonUtil.getFormattedRequestedTime());
    }
}
