package com.sbisec.helios.ap.common.dao;

import java.util.Date;

/**
 * システム日付を取得するためのDAO（Data Access Object）インターフェース。
 * データベースからシステム日付を取得するためのメソッドを提供します。
 */
public interface SystemDateDao {
    
    /**
     * データベースから現在のシステム日付を取得する。
     *
     * @return データベースのシステム日付を{@link java.util.Date}オブジェクトとして返却する。
     * @throws Exception データベースアクセス中に発生した例外。
     */
    Date getSystemDate() throws Exception;
}
