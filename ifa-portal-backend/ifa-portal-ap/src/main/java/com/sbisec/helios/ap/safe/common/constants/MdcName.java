package com.sbisec.helios.ap.safe.common.constants;

/**
 * MDCの項目名
 */
public final class MdcName {

    /**
     * コンストラクタ
     */
    private MdcName() {
    }

    /** チャネル名 */
    public static final String CHANNEL = "channel";
    /** システム名 */
    public static final String SYSTEM = "system";
    /** リクエスト元システム名 */
    public static final String CLIENT = "client";
    /** リクエスト元の画面ID */
    public static final String ID = "id";
    /** セッションID */
    public static final String SID = "sid";
    /** リクエスト単位でユニークなID */
    public static final String UUID = "uuid";
    /** IPアドレス */
    public static final String IP = "ip";
    /** ユーザーエージェント */
    public static final String USER_AGENT = "userAgent";
    /** 暗号化した顧客ID */
    public static final String TOKEN = "token";
}
