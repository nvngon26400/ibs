package com.sbisec.helios.ap.common.dao;

import com.sbisec.helios.ap.common.model.ReleaseNoteViewInfo;

/**
 * リリースノート表示条件
 * 2025/11/10 新規作成
 *
 * @author 大連 葉
 */
public interface IfaReleaseNoteAutoDispDao {

    /**
     * SQLID：Sql005
     * SQL名：リリースノート閲覧情報取得
     * SQLタイプ：select
     * リクエストクラス：String
     * レスポンスクラス：ReleaseNoteViewInfo
     *
     * @param userId ユーザID
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public ReleaseNoteViewInfo selectReleaseNoteViewInfoSql005(String userId) throws Exception;

    /**
     * SQLID：Sql006
     * SQL名：リリースノート次回表示フラグ更新
     * SQLタイプ：update
     * リクエストクラス：String
     *
     * @param userId ユーザID
     * @return 更新レコード数
     * @exception exception システムエラー
     */
    public int updateReleaseNoteNextDispFlgSql006(String userId) throws Exception;

}
