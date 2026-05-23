package com.sbisec.helios.ap.ccsApi.util;

import java.util.Optional;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.ccsApi.enums.ErrorCode;
import com.sbisec.helios.ap.ccsApi.service.dto.CcsApiCommOutIF;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CCS API ERROR 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CcsApiErrUtil {

    /** エラーラベル（-1:接続失敗 0:正常 1:エラー 2:ワーニング） */
    private int errLevel = 0;
    /** エラーメッセージID */
    private String errId = StringUtil.EMPTY_STRING;
    /** エラーメッセージ */
    private String errMsg = StringUtil.EMPTY_STRING;

    /**
     * CCS API の出力結果からエラー情報を設定します。
     * 
     * 出力結果が null またはエラーコードが空の場合、エラー情報は設定されません。
     * また、エラーコードが "IFAP00 該当のデータはありません。" の場合もエラー情報は設定されません。
     * それ以外の場合は、エラー情報を設定して返却します。
     * 
     * @param x_apiOut CCS API の出力結果オブジェクト
     */
    public void fromApiOut(CcsApiCommOutIF x_apiOut) {
        // 引数がnullの場合、エラー情報を設定しないで返す
        if (x_apiOut == null) {
            return;
        }
        // エラーコードとメッセージを取得し、nullの場合は空文字に置き換える
        String p_errorCode = Optional.ofNullable(x_apiOut.getErrorCode()).orElse(StringUtil.EMPTY_STRING);
        String p_errorMessage = Optional.ofNullable(x_apiOut.getErrorMessage()).orElse(StringUtil.EMPTY_STRING);
        // エラーコードが空または"IFAP00 該当のデータはありません。"の場合、エラー情報を設定しない
        if (!StringUtil.isNullOrEmpty(p_errorCode) && !ErrorCode.IFAP00.getId().equals(p_errorCode)) {
            // それ以外の場合はエラー情報を設定して返す
            this.setErrLevel(1);
            this.setErrId(p_errorCode);
            this.setErrMsg(p_errorMessage);
        }
    }

    /**
     * ユーザ共通情報.CCSログイン用IDのチェックを行う
     */
    public void fromCcsIdWarn() {
        String ccsUserId = IfaCommonUtil.getUserAccount().getCcsUserId();
        // 未設定(Null または空文字）の場合：取引不可エラーを返す。
        if (StringUtil.isNullOrEmpty(ccsUserId)) {
            this.setErrLevel(2);
            this.setErrId(StringUtil.EMPTY_STRING);
            this.setErrMsg(StringUtil.EMPTY_STRING);
        }
    }
}
