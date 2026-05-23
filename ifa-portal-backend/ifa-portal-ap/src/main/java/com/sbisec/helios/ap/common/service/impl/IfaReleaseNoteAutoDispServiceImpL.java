package com.sbisec.helios.ap.common.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbisec.helios.ap.common.dao.IfaReleaseNoteAutoDispDao;
import com.sbisec.helios.ap.common.model.ReleaseNoteViewInfo;
import com.sbisec.helios.ap.common.service.IfaReleaseNoteAutoDispService;

/**
 * リリースノート表示条件共通サービス
 * 2025/11/10 新規作成
 *
 * @author 大連 葉
 */
@Component(value = "cmpIfaReleaseNoteAutoDispService")
public class IfaReleaseNoteAutoDispServiceImpL implements IfaReleaseNoteAutoDispService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaReleaseNoteAutoDispServiceImpL.class);

    @Autowired
    private IfaReleaseNoteAutoDispDao dao;

    /**
     * リリースノート閲覧情報取得
     * 
     * @param userId ユーザID
     * @return リリースノート閲覧情報
     * @exception Exception 例外
     */
    public ReleaseNoteViewInfo getReleaseNoteViewInfo(String userId) throws Exception {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaReleaseNoteAutoDispServiceImpL.releaseNoteViewInfo");
        }

        ReleaseNoteViewInfo sql005Res = dao.selectReleaseNoteViewInfoSql005(userId);
        return sql005Res;
    }

    /**
     * リリースノート次回表示フラグ更新
     * 
     * @param userId ユーザID
     * @return リリースノート閲覧情報
     * @exception Exception 例外
     */
    public int updateReleaseNoteNextDispFlg(String userId) throws Exception {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaReleaseNoteAutoDispServiceImpL.updateReleaseNoteNextDispFlg");
        }

        int sql006Res = dao.updateReleaseNoteNextDispFlgSql006(userId);
        return sql006Res;
    }

}
