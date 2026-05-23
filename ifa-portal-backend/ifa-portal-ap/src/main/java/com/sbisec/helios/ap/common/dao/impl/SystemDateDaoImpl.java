package com.sbisec.helios.ap.common.dao.impl;

import java.util.Date;

import com.sbisec.helios.ap.common.dao.SystemDateDao;
import com.sbisec.helios.ap.common.dao.mapper.SystemDateMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * システム日付データアクセスオブジェクトの実装クラス。
 * <p>
 * このクラスはデータベース操作を行うマッパーを呼び出すことで、システム日付を取得します。
 * </p>
 */
@Component
public class SystemDateDaoImpl implements SystemDateDao {
    
    @Autowired
    private SystemDateMapper systemDateMapper;
    
    /**
     * データベースから現在のシステム日付を取得する。
     *
     * @return データベースのシステム日付を{@link java.util.Date}オブジェクトとして返却する。
     * @throws Exception データベースアクセス中に発生した例外。
     */
    @Override
    public Date getSystemDate() throws Exception {
        
        return systemDateMapper.getSystemDate();
    }
}
