package com.sbisec.helios.ap.brokerageMenu.customerMenu.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
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
 * 接触履歴の共通部品
 * 
 * @author 大連 王永宝
 */
@Component
public class IfaContactInputUtil {

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

    /** 処理区分の列挙型 */
    public enum OperationType {
        /** 1:問合せ入力 (接触履歴入力) */
        QUESTION_INPUT("1"),
        /** 2:問合せ修正 (管理項目修正) */
        QUESTION_CORRECT("2"),
        /** 3:回答入力 (追加入力) */
        ANSWER_INPUT("3");

        public String key;
        OperationType(String key) {this.key = key;}
        /**
         * キーに対応する列挙型を取得する
         * @param key 処理区分のキー
         * @return 対応する列挙型。見つからない場合はnullを返す
         */
        public static OperationType valueOfKey(String key) {
            for (OperationType type : OperationType.values()) {
                if (type.key.equals(key)) {
                    return type;
                }
            }
            return null;
        }
    }

    /** IFA専用フラグの列挙型 */
    public enum IfaSenyouFlg {
        /** 1:IFA */
        IFA("1"),
        /** 0:CCS */
        CCS("0");

        public String key;
        IfaSenyouFlg(String key) {this.key = key;}
    }

    /** 問合せ区分の列挙型 */
    public enum ToiawaseKbn {
        /** 1:大分類 */
        L("1"),
        /** 2:中分類 */
        M("2"),
        /** 3:小分類 */
        S("3");

        public String key;
        ToiawaseKbn(String key) {this.key = key;}
    }

    /** 削除フラグの列挙型 */
    public enum SakujoFlg {
        /** 0:未削除 */
        NO("0"),
        /** 1:削除済 */
        YES("1");

        public String key;
        SakujoFlg(String key) {this.key = key;}
    }

    /** 接触経路の列挙型 */
    public enum SessyokuKeiro {
        /** A：訪問 */
        A("A"),
        /** B：来店 */
        B("B"),
        /** C：架電 */
        C("C"),
        /** D：入電 */
        D("D"),
        /** E：メール送信 */
        E("E"),
        /** F：メール受信 */
        F("F"),
        /** G：郵便／書類発送 */
        G("G"),
        /** H：郵便／書類受領 */
        H("H"),
        /** I：ＦＡＸ送信 */
        I("I"),
        /** J：ＦＡＸ受信 */
        J("J");

        public String key;
        SessyokuKeiro(String key) {this.key = key;}
    }

    /** IFA入力フラグの列挙型 */
    public enum IfaNyuuryokuFlg {
        /** 0:未更新 */
        NOT("0"),
        /** 1:更新あり */
        DONE("1");

        public String key;
        IfaNyuuryokuFlg(String key) {this.key = key;}
    }

    /** APIフラグの列挙型 */
    public enum ApiFlg {
        /** 1:Fasthelp */
        FASTHELP("1"),
        /** CCS:0 */
        CCS("0");

        public String key;
        ApiFlg(String key) {this.key = key;}
    }

    /** メッセージの列挙型 */
    public enum Msg {
        /** 入力した部店口座は存在しません。<br>部店: [{0}]、口座: [{1}] */
        ERRORS_BUTENACCOUNTNOTEXIST("errors.butenAccountNotExist"),
        /** CCSIDが未登録のためご利用できません。 */
        ERRORS_CMN_CCSID_UNREGISTERED("errors.cmn.ccsid.unregistered"),
        /** 取引停止口座のため処理を進めることができません。 */
        ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE("errors.cmn.selectedAccount.outOfService"),
        /** 問合せ登録処理でエラーが発生しました。 */
        ERRORS_CMN_QUESTIONEXECUTION_FAILED("errors.cmn.questionExecution.failed"),
        /** 問合せ登録処理でエラーが発生したため、登録しませんでした。 */
        ERRORS_CMN_PREQUESTIONEXECUTION_FAILED("errors.cmn.preQuestionExecution.failed"),
        /** 問合せ登録処理後のデータ更新ができませんでした。問合せ登録は完了しています。 */
        WARNINGS_CMN_QUESTIONEXECUTION_COMPLETED("warnings.cmn.questionExecution.completed"),
    	/**
		 * 内容が正しく更新できませんでした。<br>
		 * 再度更新してください。
		 */
		warnings_cmn_questionupdateexecution_failed("warnings.cmn.questionUpdateExecution.failed"),
		/**
		 * 内容が正しく登録できませんでした。<br>
		 * 接触履歴修正画面から更新してください。
		 */
		WARNINGS_CMN_QUESTIONREGISTRATIONEXECUTION_FAILED("warnings.cmn.questionRegistrationExecution.failed");

        public String key;
        Msg(String key) {this.key = key;}
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

		ApiResp(String key) {
			this.key = key;
		}
	}
	
	/** 処理クラスを定義する列挙型 */
	public enum ClassName {
		EXECSQL002("execSql002"),
		EXECSQL003("execSql003"),
		EXECSQL004("execSql004"),
		EXECSQL005("execSql005"),
		EXECSQL006("execSql006"),
		EXECSQL009("execSql009"),
		EXECSQL011("execSql011"),
		CONFIRM("confirm"),
		CORRECT("correct"),
		CALLCCSAPI("callCCSApi");
		
		public String key;
		
		ClassName(String key) {
			this.key = key;
		}
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
     * ユーザ共通情報.CCSログイン用IDのチェックを行う
     * @return ErrorResponseDto チェック結果
     */
    public ErrorResponseDto checkHasCcsId() {
        ErrorResponseDto result = new ErrorResponseDto();
        // ユーザ共通情報.CCSログイン用IDを取得する
        String ccsUserId = IfaCommonUtil.getUserAccount().getCcsUserId();
        // 未設定(Null または空文字）の場合：取引不可エラーを返す。
        if (StringUtil.isNullOrEmpty(ccsUserId)) {
            result.setErrorFlg(true);
            result.setErrorMessageId(Msg.ERRORS_CMN_CCSID_UNREGISTERED.key);
            result.setErrorMessage(IfaCommonUtil.getMessage(Msg.ERRORS_CMN_CCSID_UNREGISTERED.key));
        }
        return result;
    }
    
    /**
     * 内容を782byte（半角1byte、全角2byteとして扱う）毎に分割し、内容リストを作成する
     * @param input 入力内容
     * @param maxBytes 782byte
     * @return List<String> 内容リスト
     */
    public List<String> splitStringByByteLength(String input, int maxBytes) {
        List<String> result = new ArrayList<>();
        if (StringUtils.isEmpty(input)) {
            return result;
        }

        int currentStart = 0;
        int currentByteCount = 0;
        int i = 0;
        int check = 0;

        while (i < input.length()) {
            char c = input.charAt(i);
            int byteCount = (c <= 0x7F) || (c >= '\uFF61' && c <= '\uFF9F') ? 1 : 2;
            if (c == '～') byteCount = 3;

            if (currentByteCount + byteCount > maxBytes && check < 4) {
                // 現在のサブストリングを分割し、新しいサブストリングを開始
                result.add(input.substring(currentStart, i));
                currentStart = i;
                currentByteCount = 0;
                check++;
            } else {
                currentByteCount += byteCount;
                i++;
            }
        }
        // 残りの文字を追加
        result.add(input.substring(currentStart));
        return result;
    }
    
    /**
     * 18byte未満の場合は後ろスペース埋め
     * @param input 入力内容
     * @param maxLength 最大桁数
     * @return
     */
    public static String padStringByByte(String input, char addChar, int maxLength) {
        
        int byteLength = 0;
        
        for(int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int byteCount = (c <= 0x7F) || (c >= '\uFF61' && c <= '\uFF9F') ? 1 : 2;
            byteLength += byteCount;
        }
        
        // 埋める必要のあるスペースの数を計算する
        int spacesToAdd = maxLength - byteLength;
        
        // 18byte未満の場合は後ろスペース埋め
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < spacesToAdd; i++) {
            sb.append(addChar);
        }
        return sb.toString();
    }
    
    /**
	 * 
	 * @param timeStr にシステム日時
	 * @param time    秒
	 * @return 回答日時を設定
	 * @throws DateTimeParseException
	 */
	public String addOneSecond(String timeStr, int time) throws DateTimeParseException {

		// 1. 入力値の null/空文字チェック
		if (timeStr == null || timeStr.trim().isEmpty()) {
			// ログ出力（必要に応じて）
			return null; // または null、あるいは業務仕様に合わせたデフォルト値
		}

		String normalized = timeStr.replaceAll("[^0-9]", "");
		try {
			// 日付時刻フォーマットの定義
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateUtil.NOT_SEPARATED_YYYYMMDD_HHMMSS);

			// 文字列をLocalDateTimeオブジェクトとして解析
			LocalDateTime dateTime = LocalDateTime.parse(normalized, formatter);

			// n秒加算
			LocalDateTime newDateTime = dateTime.plusSeconds(time);

			// 文字列の書式設定
			return newDateTime.format(formatter);

		} catch (DateTimeParseException e) {
			// 解析エラーが発生した場合のハンドリング
			// ログに詳細（どの値がエラーになったか）を記録することを推奨
			return timeStr;
		}
	}
}