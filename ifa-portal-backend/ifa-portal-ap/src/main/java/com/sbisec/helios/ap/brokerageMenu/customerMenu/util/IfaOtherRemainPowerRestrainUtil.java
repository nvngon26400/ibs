package com.sbisec.helios.ap.brokerageMenu.customerMenu.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.common.enums.TargetCustomerReferenceAuthorityFlag;
import com.sbisec.helios.ap.common.enums.TradeSuspendFlag;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import lombok.Data;

/**
 * その他余力拘束の共通部品
 * 
 * @author 大連 王永宝
 */
@Component
public class IfaOtherRemainPowerRestrainUtil {

    /**
     * FCT001 利用者顧客参照権限チェック
     */
    @Autowired
    private Fct001 fct001;

    /**
     * エラー時のメッセージ格納クラス
     */
    @Data
    public class ErrorResponseDto {
        /** エラー判定 */
        private boolean errorFlg = false;
        /** エラーメッセージID */
        private String errorMessageId;
        /** エラーメッセージ */
        private String errorMessage;
    }

    /** IFAその他注文履歴テーブルの主キーの列挙型 */
    public enum TbAdditionalOrderIfaPrimaryKey {
        /** 枝番:1 */
        EDABAN(1);
        public Integer key;
        TbAdditionalOrderIfaPrimaryKey(Integer key) {this.key = key;}
    }

    /** メッセージの列挙型 */
    public enum Msg {
        /** 入力した部店口座は存在しません。<br>部店: [{0}]、口座: [{1}] */
        ERRORS_BUTENACCOUNTNOTEXIST("errors.butenAccountNotExist"),
        /** 取引停止口座のため処理を進めることができません。 */
        ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE("errors.cmn.selectedAccount.outOfService"),
        /** CCSIDが未登録のためご利用できません。 */
        ERRORS_CMN_CCSID_UNREGISTERED("errors.cmn.ccsid.unregistered"),
        /** {0}を入力してください。 */
        ERRORS_REQUIRED("errors.required"),
        /** 指定した注文は取消できません。 */
        ERRORS_CMN_ORDERCANCEL_OUTOFSERVICE("errors.cmn.orderCancel.outOfService"),
        /** 注文発注前の注文データが登録できないため、注文しませんでした。 */
        ERRORS_FRS_PREORDEREXECUTION_FAILED("errors.frs.preOrderExecution.failed"),
        /** 注文取消処理でエラーが発生しました。\nエラーコード：{0}\nエラーメッセージ：{1} */
        ERRORS_CMN_ORDEREXECUTIONCANCEL_FAILED("errors.cmn.orderExecutionCancel.failed"),
        /** 注文処理でエラーが発生しました。\nエラーコード：{0}\nエラーメッセージ：{1} */
        ERRORS_CMN_ORDEREXECUTION_FAILED("errors.cmn.orderExecution.failed"),
        /** 注文発注後の注文データが更新できませんでした。注文は完了しています。 */
        WARNINGS_FRS_POSTORDEREXECUTION_COMPLETED("warnings.frs.postOrderExecution.completed"),
        /** {0}({1}) */
        ERRORS_CMN_APIERROR("errors.cmn.apiError");
        public String key;
        Msg(String key) {this.key = key;}
    }

    /** ジュニアNISA口座開設フラグの列挙型 */
    public enum IsOpenedJrNisa {
        /** ジュニアNISA口座開設フラグ:'1'(開設済) */
        YES("1"),
        /** ジュニアNISA口座開設フラグ:'0'(未開設) */
        NO("0");

        public String key;
        IsOpenedJrNisa(String key) {this.key = key;}
    }

    /** 拘束種別の列挙型 */
    public enum RestrainType {
        /** 買付余力:1 */
        BUYING_POWER_TOTAL("1"),
        /** NISA（成長投資枠）投資可能枠:4 */
        ISA_SEITYO_BUY_LIMIT("4"),
        /** NISA（つみたて投資枠）投資可能枠:5 */
        ISA_TSUMITATE_BUY_LIMIT("5"),
        /** 買付余力・NISA（成長投資枠）投資可能枠:6 */
        ISA_SEITYO_BUY_LIMIT_POWER_TOTAL("6"),
        /** 買付余力・NISA（つみたて投資枠）投資可能枠:7 */
        ISA_TSUMITATE_BUY_LIMIT_POWER_TOTAL("7");

        public String key;
        RestrainType(String key) {this.key = key;}
    }

    /** 口座区分の列挙型 */
    public enum AccountType {
        /** ジュニアNISA口座:1 */
        JUNIOR_NISA_ACCOUNT("1"),
        /** 総合口座:△ */
        GENERAL_ACCOUNT(" ");

        public String key;
        AccountType(String key) {this.key = key;}
    }

    /** 売買区分の列挙型 */
    public enum BuySellKbn {
        /** BUY:K */
        BUY("K");

        public String key;
        BuySellKbn(String key) {this.key = key;}
    }

    /** 商品区分の列挙型 */
    public enum SecId {
        /** Z9 */
        Z9("Z9");

        public String key;
        SecId(String key) {this.key = key;}
    }

    /** 発注区分の列挙型 */
    public enum HattyuuKbn {
        /** その他余力拘束 */
        OTHER("3");

        public String key;
        HattyuuKbn(String key) {this.key = key;}
    }

    /** 通貨の列挙型 */
    public enum Ccy {
        /** JPY */
        JPY("JPY");

        public String key;
        Ccy(String key) {this.key = key;}
    }

    /** ステータスコードの列挙型 */
    public enum StsCd {
        /** 未発注:0 */
        UNORDER("0"),
        /** 発注済:1 */
        ORDERED("1");

        public String key;
        StsCd(String key) {this.key = key;}
    }

    /** 取消区分の列挙型 */
    public enum TorikeshiKbn {
        /** 取消済:1 */
        CANCELED("1");

        public String key;
        TorikeshiKbn(String key) {this.key = key;}
    }

    /** 削除フラグの列挙型 */
    public enum SakujoFlg {
        /** 未削除:0 */
        UNDELETE("0");

        public String key;
        SakujoFlg(String key) {this.key = key;}
    }

    /** 特定預り売買区分の列挙型 */
    public enum TokuteiAzukariBaibaiKbn {
        /** ジュニアNISA口座:1 */
        JRNISA("1"),
        /** 総合口座:"" */
        GENERAL("");

        public String key;
        TokuteiAzukariBaibaiKbn(String key) {this.key = key;}
    }

    /** 受付経路区分の列挙型 */
    public enum UketsukeKeiroKbn {
        /** 仲介業者:3 */
        BROKER("3"),
        /** CCS:1 */
        CCS("1");

        public String key;
        UketsukeKeiroKbn(String key) {this.key = key;}
    }

    /** マル優口座区分の列挙型 */
    public enum MaruyuKouzaKbn {
        /** マル優口座:1 */
        YES("1"),
        /** マル優口座以外:△ */
        NO(" ");

        public String key;
        MaruyuKouzaKbn(String key) {this.key = key;}
    }

    /** API発注状態 */
    public enum ApiResp {
        /** API発注正常 */
        API_RESP_OK("API_RESP_OK"),
        /** API発注エラー(API応答あり) */
        API_RESP_NG_ON("API_RESP_NG_ON"),
        /** API発注エラー(API応答なし) */
        API_RESP_NG_OFF("API_RESP_NG_OFF");

        public String key;
        ApiResp(String key) {this.key = key;}
    }

    /**
     * FCT001 利用者の口座に対する権限チェック
     * @param cc 顧客共通情報
     * @return ErrorResponseDto チェック結果
     */
    public ErrorResponseDto callFct001(CustomerCommon cc) {
        InputFct001Dto input = new InputFct001Dto();
        // 口座番号
        input.setAccountNumber(cc.getAccountNumber());
        // 部店コード
        input.setButenCode(cc.getButenCode());
        // 共通関数定義書_FCT001_利用者顧客参照権限チェック 参照
        OutputFct001Dto output = fct001.doCheck(input);
        ErrorResponseDto result = new ErrorResponseDto();
        // 権限なしエラー
        if (output == null) {
            result.setErrorFlg(true);
            result.setErrorMessageId(Msg.ERRORS_BUTENACCOUNTNOTEXIST.key);
            result.setErrorMessage(
                    IfaCommonUtil.getMessage(Msg.ERRORS_BUTENACCOUNTNOTEXIST.key,
                            new String[] { cc.getButenCode(), cc.getAccountNumber() }));
            // 対象顧客参照権限有無＝"0"（権限なし）：権限なしエラーを返す。
        } else if (StringUtils.equals(output.getTargetCustomerRefAuthFlag(),
                TargetCustomerReferenceAuthorityFlag.KENGEN_NASHI.getId())) {
            result.setErrorFlg(true);
            result.setErrorMessageId(Msg.ERRORS_BUTENACCOUNTNOTEXIST.key);
            result.setErrorMessage(
                    IfaCommonUtil.getMessage(Msg.ERRORS_BUTENACCOUNTNOTEXIST.key,
                            new String[] { cc.getButenCode(), cc.getAccountNumber() }));
            // 取引停止フラグ＝"1"（取引停止口座）：取引停止口座エラーを返す。
        } else if (StringUtils.equals(output.getTradeSuspendFlag(), TradeSuspendFlag.SUSPEND.getId())) {
            result.setErrorFlg(true);
            result.setErrorMessageId(Msg.ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE.key);
            result.setErrorMessage(IfaCommonUtil.getMessage(
                    Msg.ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE.key, new String[] {}));
        }
        return result;
    }

    /**
     * オブジェクトの存在チェック.
     * @param target Nullチェック対象
     * @return true:存在する　false:存在しない
     */
    public boolean checkNotNullObjects(Object target) {
        return !Objects.isNull(target);
    }

    /**
     * APIリクエスト項目：口座番号設定値作成
     * @param accountNo 口座番号
     * @return 0埋めの口座番号
     */
    public String createApiRequestAccountNo(String accountNo) {
        return String.format("%7s", accountNo).replace(" ", "0");
    }

    /**
     * 指定された日付と時間をフォーマットして、"yyyy/MM/dd HH:mm:ss"形式の文字列を返します。
     * @param date 日付部分（"yyyyMMdd"形式）
     * @param time 時間部分（"HHmmss"形式）
     * @return フォーマットされた日付時間の文字列。変換に失敗した場合は空文字列を返します。
     */
    public String formatDateTime(String date, String time) {
        // 戻り値を初期化
        String rtn = StringUtil.EMPTY_STRING;
        // 日付の検証
        if (date == null || !date.matches("\\d{8}")) { // yyyyMMdd形式の確認
            return rtn; // 空または不正な形式の場合、空文字列を返す
        }
        // 時間の検証
        if (time == null || !time.matches("\\d{6}")) { // HHmmss形式の確認
            return rtn; // 空または不正な形式の場合、空文字列を返す
        }
        try {
            // 日付と時間を結合してDateオブジェクトに変換
            Date dt = DateUtil.parse(date + " "+ time, "yyyyMMdd HHmmss");
            // Dateオブジェクトを指定されたフォーマットで文字列に変換
            rtn = DateUtil.format(dt, DateUtil.SEPARATED_YYYYMMDD_HHMMSS);
        } catch (Exception e) {
            // 例外が発生した場合は戻り値を空文字列に設定
            rtn = StringUtil.EMPTY_STRING;
        }
        return rtn; // フォーマットされた日付時間を返す
    }

    /**
     * 文字列をBigDecimalに変換する
     * @param val 変換する文字列。 nullの場合はBigDecimal.ZEROを返します。
     *            変換できない場合もBigDecimal.ZEROを返します。
     * @return 変換されたBigDecimalの値。
     */
    public BigDecimal convert2BigDecimal(String val) {
        if (StringUtil.isNullOrEmpty(val)) {
            return BigDecimal.ZERO;
        }
        try {
            return new BigDecimal(val);
        } catch (NumberFormatException e) {
            return BigDecimal.ZERO;
        }
    }
 
}