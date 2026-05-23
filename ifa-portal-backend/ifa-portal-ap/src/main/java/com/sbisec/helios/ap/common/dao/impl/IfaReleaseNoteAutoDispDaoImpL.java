package com.sbisec.helios.ap.common.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbisec.helios.ap.common.dao.IfaReleaseNoteAutoDispDao;
import com.sbisec.helios.ap.common.dao.mapper.IfaReleaseNoteAutoDispMapper;
import com.sbisec.helios.ap.common.model.ReleaseNoteViewInfo;

/**
 * リリースノート表示条件
 * 2025/11/10 新規作成
 *
 * @author 大連 葉
 */
@Component
public class IfaReleaseNoteAutoDispDaoImpL implements IfaReleaseNoteAutoDispDao {

    @Autowired
    private IfaReleaseNoteAutoDispMapper mapper;

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
    public ReleaseNoteViewInfo selectReleaseNoteViewInfoSql005(String userId) throws Exception {
        return mapper.selectReleaseNoteViewInfoSql005(userId);
    }

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
    public int updateReleaseNoteNextDispFlgSql006(String userId) throws Exception {

        int rtnCnt = 0;
        rtnCnt = mapper.updateReleaseNoteNextDispFlgSql006(userId);
        return rtnCnt;
    }

}
