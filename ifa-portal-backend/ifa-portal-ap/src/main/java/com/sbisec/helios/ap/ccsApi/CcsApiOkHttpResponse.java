package com.sbisec.helios.ap.ccsApi;

import java.util.ArrayList;
import java.util.List;

import com.sbisec.helios.ap.ccsApi.exception.CcsApiException;
import com.sbisec.helios.ap.ccsApi.service.dto.ShowSubContractHistoryIFAOut;
import com.sbisec.helios.ap.ccsApi.util.CcsApiUtil;
import com.sbisec.helios.ap.ccsApi.service.dto.ShowDealingDetailIFAOut;

/**
 * CCS Apiの成功レスポンスを処理するクラス。
 * HTTPステータスコードが200の場合に使用される。
 */
public class CcsApiOkHttpResponse {

    /**
     * リクエスト成功フラグ（true: 成功 / false: 失敗）
     */
    private Boolean g_success;
    
    /**
     * HTTPステータスコード（例：200は成功）
     */
    private Integer g_stsCode;
    
    /**
     * 応答データ（文字列形式）
     */
    private String g_resData;

    /**
     * リクエスト成功フラグを取得する。
     * @return リクエスト成功フラグ
     */
    public Boolean getSuccess() {
        return g_success;
    }

    /**
     * リクエスト成功フラグを設定する。
     * @param x_success リクエスト成功フラグ
     */
    public void setSuccess(Boolean x_success) {
        this.g_success = x_success;
    }

    /**
     * HTTPステータスコードを取得する。
     * @return HTTPステータスコード
     */
    public Integer getStsCode() {
        return g_stsCode;
    }

    /**
     * HTTPステータスコードを設定する。
     * @param x_stsCode HTTPステータスコード
     */
    public void setStsCode(Integer x_stsCode) {
        this.g_stsCode = x_stsCode;
    }

    /**
     * 応答データを取得する。
     * @return 応答データ（文字列）
     */
    public String getResData() {
        return g_resData;
    }

    /**
     * 応答データを設定する。
     * @param x_resData 応答データ（文字列）
     */
    public void setResData(String x_resData) {
        this.g_resData = x_resData;
    }
    
    /**
     *接触履歴APIの応答データを解析し、オブジェクトを返す。
     * 
     * @return 解析後の CcsApi1Out オブジェクト
     * @throws CcsApiException 応答データの解析中にエラーが発生した場合
     */
    public ShowSubContractHistoryIFAOut getShowSubContractHistoryIFAResponseData() throws CcsApiException {
        try {
            // レスポンスがStringタイプであるかを確認する
            if (!(this.g_resData instanceof String)) {
                throw new CcsApiException("responseData is not a String");
            }
            // レスポンスをString型にキャストし、ローカル変数に格納
            String p_resStr = (String) this.g_resData;
            // nullチェック追加（現状ではnullが許容されていないため）
            if (p_resStr == null) {
                throw new CcsApiException("responseData is invalid");
            }
            // 文字列を Shift_JIS 形式のバイト配列に変換
            byte[] p_bytes = p_resStr.getBytes(CcsApiUtil.CHARSET_MS932);
            // バイト配列の読み込み開始位置を初期化
            int p_offset = 0;

            // CcsApi1Out オブジェクトを生成
            ShowSubContractHistoryIFAOut p_apiOut = new ShowSubContractHistoryIFAOut();
            // Contact オブジェクトのリストを初期化
            List<ShowSubContractHistoryIFAOut.Contact> p_contactList = new ArrayList<ShowSubContractHistoryIFAOut.Contact>();
            // Contact オブジェクトを一時的に保持する変数
            ShowSubContractHistoryIFAOut.Contact p_contact = null;
            /* 以下：レスポンス値をセットする */
            // 顧客ID:9バイト
            p_apiOut.setAccountId(getBytesToString(p_bytes, p_offset, p_apiOut.ACCOUNT_ID_LEN));
            p_offset += p_apiOut.ACCOUNT_ID_LEN;
            // エラーコード:6バイト
            p_apiOut.setErrorCode(getBytesToString(p_bytes, p_offset, p_apiOut.ERROR_CODE_LEN));
            p_offset += p_apiOut.ERROR_CODE_LEN;
            // エラーメッセージ:40バイト
            p_apiOut.setErrorMessage(getBytesToString(p_bytes, p_offset, p_apiOut.ERROR_MESSAGE_LEN));
            p_offset += p_apiOut.ERROR_MESSAGE_LEN;
            // 該当履歴件数トータル:3バイト
            p_apiOut.setTotalNumber(getBytesToString(p_bytes, p_offset, p_apiOut.TOTAL_NUMBER_LEN));
            p_offset += p_apiOut.TOTAL_NUMBER_LEN;
            // CCS接触履歴リスト    繰り返しMAX100件
            while (p_offset + p_apiOut.LIST_LENS <= p_bytes.length) {
                p_contact = new ShowSubContractHistoryIFAOut.Contact();
                // 大分類:20バイト
                p_contact.setBigClass(getBytesToString(p_bytes, p_offset, p_apiOut.BIG_CLASS_LEN));
                p_offset += p_apiOut.BIG_CLASS_LEN;
                // 中分類:210バイト
                p_contact.setMidClass(getBytesToString(p_bytes, p_offset, p_apiOut.MID_CLASS_LEN));
                p_offset += p_apiOut.MID_CLASS_LEN;
                // 記録日時:20バイト
                p_contact.setRecordDate(getBytesToString(p_bytes, p_offset, p_apiOut.RECORD_DATE_LEN));
                p_offset += p_apiOut.RECORD_DATE_LEN;
                // ステータス:20バイト
                p_contact.setOrderStatus(getBytesToString(p_bytes, p_offset, p_apiOut.ORDER_STATUS_LEN));
                p_offset += p_apiOut.ORDER_STATUS_LEN;
                // 内容:800バイト
                p_contact.setContent(getBytesToString(p_bytes, p_offset, p_apiOut.CONTENT_LEN));
                p_offset += p_apiOut.CONTENT_LEN;
                // 担当者名:40バイト
                p_contact.setUserMei(getBytesToString(p_bytes, p_offset, p_apiOut.USER_MEI_LEN));
                p_offset += p_apiOut.USER_MEI_LEN;
                // 枝番:5バイト
                p_contact.setEdaban(getBytesToString(p_bytes, p_offset, p_apiOut.EDABAN_LEN));
                p_offset += p_apiOut.EDABAN_LEN;
                // 詳細区分:1バイト
                p_contact.setShousaiKbn(getBytesToString(p_bytes, p_offset, p_apiOut.SHOUSAI_KBN_LEN));
                p_offset += p_apiOut.SHOUSAI_KBN_LEN;

                p_contactList.add(p_contact);
            }
            p_apiOut.setContactList(p_contactList);

            return p_apiOut;
        } catch (Exception e) {
            throw new CcsApiException(e);
        }
    }

    /**
     * 接触履歴詳細APIの応答データを解析し、オブジェクトを返す。
     * 
     * @return 解析後の CcsApi2Out オブジェクト
     * @throws CcsApiException 応答データの解析中にエラーが発生した場合
     */
    public ShowDealingDetailIFAOut getShowDealingDetailIFAResponseData() throws CcsApiException {
        try {
            // レスポンスがStringタイプであるかを確認する
            if (!(this.g_resData instanceof String)) {
                throw new CcsApiException("responseData is not a String");
            }
            // レスポンスをString型にキャストし、ローカル変数に格納
            String p_resStr = (String) this.g_resData;
            // nullチェック追加（現状ではnullが許容されていないため）
            if (p_resStr == null) {
                throw new CcsApiException("responseData is invalid");
            }
            // 文字列を Shift_JIS 形式のバイト配列に変換
            byte[] p_bytes = p_resStr.getBytes(CcsApiUtil.CHARSET_MS932);
            // バイト配列の読み込み開始位置を初期化
            int p_offset = 0;

            // CcsApi2Out オブジェクトを生成
            ShowDealingDetailIFAOut p_apiOut = new ShowDealingDetailIFAOut();
            // Contact オブジェクトのリストを初期化
            List<ShowDealingDetailIFAOut.ContactDetail> p_contactList = new ArrayList<ShowDealingDetailIFAOut.ContactDetail>();
            // Contact オブジェクトを一時的に保持する変数
            ShowDealingDetailIFAOut.ContactDetail p_contactDtl = null;
            /* 以下：レスポンス値をセットする */
            // 顧客ID:9バイト
            p_apiOut.setAccountId(this.getBytesToString(p_bytes, p_offset, p_apiOut.ACCOUNT_ID_LEN));
            p_offset += p_apiOut.ACCOUNT_ID_LEN;
            // エラーコード:6バイト
            p_apiOut.setErrorCode(getBytesToString(p_bytes, p_offset, p_apiOut.ERROR_CODE_LEN));
            p_offset += p_apiOut.ERROR_CODE_LEN;
            // エラーメッセージ:40バイト
            p_apiOut.setErrorMessage(getBytesToString(p_bytes, p_offset, p_apiOut.ERROR_MESSAGE_LEN));
            p_offset += p_apiOut.ERROR_MESSAGE_LEN;
            // 該当履歴件数トータル:3バイト
            p_apiOut.setTotalNumber(getBytesToString(p_bytes, p_offset, p_apiOut.TOTAL_NUMBER_LEN));
            p_offset += p_apiOut.TOTAL_NUMBER_LEN;
            // CCS接触履歴明細リスト    繰り返しMAX100件
            while (p_offset + p_apiOut.LIST_LENS <= p_bytes.length) {
                p_contactDtl = new ShowDealingDetailIFAOut.ContactDetail();
                // 受付シート:10バイト
                p_contactDtl.setUketsukesheet(getBytesToString(p_bytes, p_offset, p_apiOut.UKETSUKESHEET_LEN));
                p_offset += p_apiOut.UKETSUKESHEET_LEN;
                // 大分類:20バイト
                p_contactDtl.setBigClass(getBytesToString(p_bytes, p_offset, p_apiOut.BIG_CLASS_LEN));
                p_offset += p_apiOut.BIG_CLASS_LEN;
                // 中分類:210バイト
                p_contactDtl.setMidClass(getBytesToString(p_bytes, p_offset, p_apiOut.MID_CLASS_LEN));
                p_offset += p_apiOut.MID_CLASS_LEN;
                // 記録日時:20バイト
                p_contactDtl.setRecordDate(getBytesToString(p_bytes, p_offset, p_apiOut.RECORD_DATE_LEN));
                p_offset += p_apiOut.RECORD_DATE_LEN;
                // ステータス:20バイト
                p_contactDtl.setOrderStatus(getBytesToString(p_bytes, p_offset, p_apiOut.ORDER_STATUS_LEN));
                p_offset += p_apiOut.ORDER_STATUS_LEN;
                // 内容:500バイト
                p_contactDtl.setContent(getBytesToString(p_bytes, p_offset, p_apiOut.CONTENT_LEN));
                p_offset += p_apiOut.CONTENT_LEN;
                
                p_contactList.add(p_contactDtl);
            }
            p_apiOut.setContactDetailList(p_contactList);

            return p_apiOut;
        } catch (Exception e) {
            throw new CcsApiException(e);
        }
    }

    /**
     * バイト配列から指定された範囲の文字列を取得する。
     * <br>文字列の右端空白(全半角)を削除する
     * 
     * @param bytes バイト配列
     * @param offset 開始位置
     * @param length 取得するバイト数
     * @return 文字列（エラー時はnull）
     */
    private String getBytesToString(byte[] bytes, int offset, int length) {
        try {
            if (offset < 0 || offset + length > bytes.length) {
                return null;
            }
            return new String(bytes, offset, length, CcsApiUtil.CHARSET_MS932).replaceAll("[ 　]+$", "");
        } catch (Exception e) {
            return null;
        }
    }
}