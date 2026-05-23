package com.sbisec.helios.ap.extapi.servicenow.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA010RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA011RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA012RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA013RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.common.IfaServiceNowMenuDto;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * IfaServiceNowMenuAndAclManager Mapper
 *
 * @author SCSK
 */
@Mapper
public interface IfaServiceNowMenuAndAclManagerMapper {
    
    /**
     * メニュー取得
     */
    public List<IfaServiceNowMenuDto> selectA010Menu(@Param("req") IfaServiceNowMenuAndAclManagerA010RequestDto req)
            throws Exception;
    
    /**
     * ユーザ利用できるメニューを削除
     */
    public int deleteA011MenuByUserId(@Param("req") IfaServiceNowMenuAndAclManagerA011RequestDto req) throws Exception;
    
    /**
     * ユーザ利用できるメニューを削除
     * -> Cordysユーザーとメニューマッピング情報_新(TB_MED_GOV_MENU_NEW)テーブルを削除する
     */
    public int deleteA012MenuByUserIdAndMenuIdAndPrivId(@Param("req") IfaServiceNowMenuAndAclManagerA012RequestDto req) throws Exception;
    
    /**
     * ユーザ利用できるメニューを登録
     * -> Cordysユーザーとメニューマッピング情報_新(TB_MED_GOV_MENU_NEW)テーブルを登録する
     */
    public int insertA012Menu(@Param("req") IfaServiceNowMenuAndAclManagerA012RequestDto req) throws Exception;
    
        /**
     * ユーザ利用できるメニューを削除
     * -> Cordysユーザーとメニューマッピング情報_新(TB_MED_GOV_MENU)テーブルを削除する
     */
    public int deleteA012OldMenuByUserId(@Param("req") IfaServiceNowMenuAndAclManagerA012RequestDto req) throws Exception;
    
    /**
     * ユーザ利用できるメニューを登録
     * -> Cordysユーザーとメニューマッピング情報_新(TB_MED_GOV_MENU)テーブルを登録する
     */
    public int insertA012OldMenu(@Param("req") IfaServiceNowMenuAndAclManagerA012RequestDto req) throws Exception;

    /**
     * ユーザ利用可能メニュー一覧取得
     */
    public List<IfaServiceNowMenuDto> selectA013AvailableMenu(@Param("req") IfaServiceNowMenuAndAclManagerA013RequestDto req)
            throws Exception;
    
}
