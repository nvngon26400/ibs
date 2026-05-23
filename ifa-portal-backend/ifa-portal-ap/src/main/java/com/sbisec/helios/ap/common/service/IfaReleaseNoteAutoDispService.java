package com.sbisec.helios.ap.common.service;

import com.sbisec.helios.ap.common.model.ReleaseNoteViewInfo;
import com.sbisec.helios.ap.service.Service;

/**
 * リリースノート画面の自動表示チェックサービスインターフェース
 */
public interface IfaReleaseNoteAutoDispService extends Service {

    /**
     * リリースノート閲覧情報取得
     * 
     * @param userId ユーザID
     * @return リリースノート閲覧情報
     * @exception Exception 例外
     */
    public ReleaseNoteViewInfo getReleaseNoteViewInfo(String userId)
            throws Exception;

    /**
     * リリースノート次回表示フラグ更新
     * 
     * @param userId ユーザID
     * @return リリースノート閲覧情報
     * @exception Exception 例外
     */
    public int updateReleaseNoteNextDispFlg(String userId)
            throws Exception;
}
