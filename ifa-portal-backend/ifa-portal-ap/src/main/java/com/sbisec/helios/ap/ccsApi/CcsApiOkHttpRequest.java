package com.sbisec.helios.ap.ccsApi;

/**
 * CCS API に対して HTTP リクエストを送信するためのリクエストオブジェクト。
 * URL を設定し、HTTP リクエストの送信先を指定するためのクラス。
 */
public class CcsApiOkHttpRequest {

    /**
     * コンストラクタ
     * 
     * 初期化用のデフォルトコンストラクタ。
     */
    public CcsApiOkHttpRequest() {
    }

    /**
     * URL を保持するフィールド
     * 
     * HTTP リクエストを送信する対象の URL を格納します。
     */
    private String url;

    /**
     * URL を取得するメソッド
     * 
     * @return URL 文字列
     */
    public String getUrl() {
        return url;
    }

    /**
     * URL を設定するメソッド
     * 
     * @param url 設定する URL 文字列
     */
    public void setUrl(String url) {
        this.url = url;
    }
}