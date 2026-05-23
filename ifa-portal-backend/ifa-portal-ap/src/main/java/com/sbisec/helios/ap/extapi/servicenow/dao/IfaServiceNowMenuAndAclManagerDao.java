package com.sbisec.helios.ap.extapi.servicenow.dao;

import java.util.List;

import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA010RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA011RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA012RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA013RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.common.IfaServiceNowMenuDto;

/**
 * IfaServiceNowMenuAndAclManagerDao
 *
 * @author SCSK
 */
public interface IfaServiceNowMenuAndAclManagerDao {
    
    /**
     * メニュー取得
     */
    public List<IfaServiceNowMenuDto> selectA010Menu(IfaServiceNowMenuAndAclManagerA010RequestDto req) throws Exception;
    
    /**
     * ユーザ利用できるメニューを削除
     */
    public int deleteA011MenuByUserId(IfaServiceNowMenuAndAclManagerA011RequestDto req) throws Exception;
    
    /**
     * ユーザ利用できるメニューを登録
     */
    public int deleteA012MenuByUserIdAndMenuIdAndPrivId(IfaServiceNowMenuAndAclManagerA012RequestDto dtoReq) throws Exception;
    
    /**
     * ユーザ利用できるメニューを登録
     */
    public int insertA012Menu(IfaServiceNowMenuAndAclManagerA012RequestDto dtoReq) throws Exception;
    
    /**
     * ユーザ利用できるメニューを登録
     */
    public int deleteA012OldMenuByUserId(IfaServiceNowMenuAndAclManagerA012RequestDto dtoReq) throws Exception;
    
    /**
     * ユーザ利用できるメニューを登録
     */
    public int insertA012OldMenu(IfaServiceNowMenuAndAclManagerA012RequestDto dtoReq) throws Exception;
    
    /**
     * ユーザ利用可能メニュー一覧取得
     */
    public List<IfaServiceNowMenuDto> selectA013AvailableMenu(IfaServiceNowMenuAndAclManagerA013RequestDto dtoReq)
            throws Exception;
    
}
