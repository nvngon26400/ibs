package com.sbisec.helios.ap.safe.common.constants;

/**
 * 共通エラーコードの定数クラス
 */
public class CommonErrorCode {

    /** Safeサービスメンテナンス */
    public static final String SERVICE_UNAVAILABLE = "0990";
    /** 必須エラー */
    public static final String REQUIRED = "1000";
    /** フォーマット不正 */
    public static final String FORMAT_INVALID = "1010";
    /** 数値フォーマット不正 */
    public static final String NUMBER_INVALID = "1020";
    /** 整数ではない */
    public static final String INTEGER_INVALID = "1021";
    /** 日付フォーマット不正 */
    public static final String DATE_INVALID = "1030";
    /** 範囲不正 */
    public static final String RANGE_INVALID = "1040";
    /** 未定義バリデーション */
    public static final String PARAMETER_INVALID = "1050";
    /** 無効な連携ステータス*/
    public static final String AUTHZ_STATUS_INVALID = "1051";
    /** 未知のサーバアドレス*/
    public static final String SERVER_IP_UNKNOWN = "1052";
    /** アカウントが存在しない */
    public static final String ACCOUNT_NOT_FOUND = "2020";
    /** ログイン認証エラー */
    public static final String LOGIN_UNAUTHORIZED = "2040";
    /** アカウントロック */
    public static final String ACCOUNT_LOCKED = "2041";
    /** 無効口座のため、連携不可 */
    public static final String ACCOUNT_SATUS_CLOSED = "2063";
    /** 初回ログイン登録未実施のため、連携不可 */
    public static final String ACCOUNT_INFO_NOT_REGISTERED = "2064";
    /** マイナンバー未承認のため、連携不可 */
    public static final String ACCOUNT_MYNUMBER_NOT_ACCEPTED = "2065";
    /** 本人確認書類未受領のため、連携不可 */
    public static final String IDENTITY_CONFIRM_DOC_NOT_ACCEPTED = "2066";
    /** 本人限定郵便未受領のため、連携不可 */
    public static final String RESTRICTED_DELIV_MAIL_NOT_ACCEPTED = "2067";
    /** 強制PW変更が必要です */
    public static final String PASSWORD_CHANGE_REQUIRED = "2068";
    /** クレジットカード情報が存在しない場合のエラー */
    public static final String CREDIT_CARD_DATA_NOT_EXIST = "2069";
    /** 取引認可jwtが無効です。 */
    public static final String TRADE_JWT_INVALID = "2070";
    /** プロパー顧客アクセス制御 */
    public static final String ACCOUNT_PROPER_INVALID = "2071";
    /** 仲介業者アクセス制御 */
    public static final String ACCOUNT_INTERMEDIARY_INVALID = "2072";
    /** IOエラー */
    public static final String IO_ERROR = "9010";
    /** DBエラー */
    public static final String DB_ERROR = "9020";
    /** DBデータ無し */
    public static final String DATA_NOT_FOUND = "9021";
    /** DBデータ未更新 */
    public static final String CAN_NOT_UPDATE = "9022";
    /** 外部API呼び出しエラー */
    public static final String EXTERNAL_API_ERROR = "9030";
    /** 外部API取得失敗 */
    public static final String EXTERNAL_API_FAILED = "9031";
    /** 外部APIから取得した情報無し */
    public static final String EXTERNAL_API_EMPTY = "9032";
    /** 流量制限 */
    public static final String RATE_LIMIT = "9040";
    /** 無効メールテンプレート */
    public static final String MAIL_TEMPLATE_INVALID = "9050";
    /** MIMEメッセージヘルパー初期化失敗 */
    public static final String MIME_MESSAGE_INIT_FAILED = "9051";
    /** 暗号化エラー */
    public static final String ENCRYPT_FAILED = "9061";
    /** 復号化エラー */
    public static final String DECRYPT_FAILED = "9062";
    /** 汎用システムエラー */
    public static final String SYSTEM_ERROR = "9999";
}
