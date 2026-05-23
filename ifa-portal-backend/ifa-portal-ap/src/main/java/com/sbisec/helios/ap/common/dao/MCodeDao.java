package com.sbisec.helios.ap.common.dao;

import java.util.List;

import com.sbisec.helios.ap.common.model.MCode;

/**
 * コードマスタ
 *
 * @author SCSK
 *
 */
public interface MCodeDao {
    
    /**
     * コードマスタリスト取得処理
     *
     * @return コードマスタリスト
     * @throws Exception 例外
     */
    public List<MCode> getMCodeList() throws Exception;
}
