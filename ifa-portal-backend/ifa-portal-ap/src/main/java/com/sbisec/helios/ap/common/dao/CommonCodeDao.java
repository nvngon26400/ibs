package com.sbisec.helios.ap.common.dao;

import java.util.List;

import com.sbibits.earth.model.DataList;

/**
 * コード値公開用汎用コントローラ（API）DAOのインターフェイス
 *
 * @author 河口
 *
 */
public interface CommonCodeDao {
    
    /**
     * 引数で指定されたMapperのsqlを実行する
     *
     * @param mapperName myBatisのMapper名
     * @param sqlId      SQLID
     * @param params     SQLに引き渡すパラメータ
     * @return DataListに設定したSQLの実行結果
     */
    public DataList<Object> getCommonCode(String mapperName, String sqlId, List<Object> params);
}
