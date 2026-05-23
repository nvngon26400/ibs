package com.sbisec.helios.ap.safe.common.constants;

/**
 * ヘッダーの項目名
 */
public final class HeaderName {

    /**
     * コンストラクタ
     */
    private HeaderName() {
    }

    /** チャネル名 */
    public static final String CHANNEL = "channel";
    /** システム名 */
    public static final String SYSTEM = "system";
    /** リクエスト元の画面ID */
    public static final String ID = "id";
    /** セッションID */
    public static final String SID = "sid";
    /** リクエスト単位でユニークなID */
    public static final String UUID = "uuid";
    /** IPアドレス */
    public static final String IP = "ip";
    /** 暗号化した顧客ID */
    public static final String TOKEN = "token";
    /** クライアントID */
    public static final String AZP = "azp";
    /** クライアントsubID */
    public static final String BRAND = "brand";

    /** ユーザークライエントのUNIXタイムスタンプ（ミリ秒単位） */
    public static final String CLIENT_TIMESTAMP = "client-timestamp";
    /** フロントサーバーのUNIXタイムスタンプ（ミリ秒単位） */
    public static final String SERVER_TIMESTAMP = "server-timestamp";
    /** クライアントのトランザクションID */
    public static final String CLIENT_TRANSACTION_ID = "Client-Transaction-Id";
    /** secret key */
    public static final String API_KEY = "apiKey";
    /** 取引認可JWT */
    public static final String X_TRADE_AUTHORIZATION = "X-Trade-Authorization";
    /** 仲介是非,仲介業者コード */
    public static final String X_INTERMEDIARY = "X-Intermediary";

}
