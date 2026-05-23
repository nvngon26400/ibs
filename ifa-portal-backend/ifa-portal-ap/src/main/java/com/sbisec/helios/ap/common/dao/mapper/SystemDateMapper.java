package com.sbisec.helios.ap.common.dao.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;

/**
 * システム日付
 * データベースからシステム日付を取得するための操作を提供します。
 */
@Mapper
public interface SystemDateMapper {
    
    /**
     * データベースから現在のシステム日付を取得する。
     *
     * @return システム日付
     */ 
    Date getSystemDate();
}
