package com.sbisec.helios.ap.common.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.service.Service;

/**
 * コード値公開用汎用コントローラ（API）サービスインターフェイス
 *
 * @author 河口
 *
 */
public interface CommonCodeService extends Service {
    
    /**
     * 指定されたパラメータに対応するSQLを実行し、結果を返却する
     *
     * @param codeListId    区分ID
     * @param dispPattern   表示パターン
     * @param selectPattern 取得パターン
     * @return 区分定義リスト
     */
    public DataList<Object> getCommonCode(String codeListId, String dispPattern, String selectPattern);
}
