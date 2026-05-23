package com.sbisec.helios.ap.extapi.servicenow.service.impl;

import java.util.List;

import com.sbisec.helios.ap.extapi.servicenow.common.util.ServiceNowUtil;
import com.sbisec.helios.ap.extapi.servicenow.dao.IfaServiceNowMenuAndAclManagerDao;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA010RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA010ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA011RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA011ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA012RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA012ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA013RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA013ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.common.IfaServiceNowMenuDto;
import com.sbisec.helios.ap.extapi.servicenow.service.IfaServiceNowMenuAndAclManagerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * cmpIfaServiceNowMenuAndAclManagerService サービス
 *
 * @autor SCSK
 */
@Component(value = "cmpIfaServiceNowMenuAndAclManagerService")
public class IfaServiceNowMenuAndAclManagerServiceImp implements IfaServiceNowMenuAndAclManagerService {
    
    @Autowired
    private IfaServiceNowMenuAndAclManagerDao dao;
    
    @Override
    public IfaServiceNowMenuAndAclManagerA010ResponseDto invokeA010(IfaServiceNowMenuAndAclManagerA010RequestDto dtoReq)
            throws Exception {
        
        IfaServiceNowMenuAndAclManagerA010ResponseDto dtoRes = new IfaServiceNowMenuAndAclManagerA010ResponseDto();
        // 1. メニュー取得
        List<IfaServiceNowMenuDto> res = dao.selectA010Menu(dtoReq);
        
        // 2. 応答項目を設定して返却する
        dtoRes.setMenuList(res);
        ServiceNowUtil.createDataListServiceNow(dtoRes, 0);
        
        return dtoRes;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public IfaServiceNowMenuAndAclManagerA011ResponseDto invokeA011(IfaServiceNowMenuAndAclManagerA011RequestDto dtoReq)
            throws Exception {
        
        IfaServiceNowMenuAndAclManagerA011ResponseDto dtoRes = new IfaServiceNowMenuAndAclManagerA011ResponseDto();
        
        // 1. ユーザー利用できるメニューを削除 
        int total = 0;
        
        // 1.1 Cordysユーザーとメニューマッピング情報_新(TB_MED_GOV_MENU_NEW)テーブルを削除する
        total = dao.deleteA011MenuByUserId(dtoReq);
        
        // 1.2 応答.実行結果コードを設定して返却する。
        ServiceNowUtil.createDataListServiceNow(dtoRes, total);
        return dtoRes;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public IfaServiceNowMenuAndAclManagerA012ResponseDto invokeA012(IfaServiceNowMenuAndAclManagerA012RequestDto dtoReq)
            throws Exception {
        
        IfaServiceNowMenuAndAclManagerA012ResponseDto dtoRes = new IfaServiceNowMenuAndAclManagerA012ResponseDto();
        
        // 1. ユーザー利用できるメニューを登録
        // 1.1 Cordysメニュー情報_ServiceNow(TB_MED_MENU_SN)テーブルを取得する
        // -> 1.2, 1.3 クエリ内で取得

        // 1.2 Cordysユーザーとメニューマッピング情報_新(TB_MED_GOV_MENU_NEW)テーブルを削除する
        dao.deleteA012MenuByUserIdAndMenuIdAndPrivId(dtoReq);

        // 1.3 Cordysユーザーとメニューマッピング情報_新(TB_MED_GOV_MENU_NEW)テーブルを登録する
        // ※1.1取得結果の件数分、当該処理を繰り返し実施
        int total = dao.insertA012Menu(dtoReq);

        // 2. ユーザー利用できるメニューを登録
        // 2.1 旧IFAポータルのデフォルト利用メニューを取得する

        // 2.2 Cordysユーザーとメニューマッピング情報(TB_MED_GOV_MENU)テーブルを削除する
        dao.deleteA012OldMenuByUserId(dtoReq);

        // 2.3 Cordysユーザーとメニューマッピング情報(TB_MED_GOV_MENU)テーブルを登録する
        total += dao.insertA012OldMenu(dtoReq);
        // 2.4 応答.実行結果コードを設定して返却する。
        ServiceNowUtil.createDataListServiceNow(dtoRes, total);
        return dtoRes;
    }
    
    @Override
    public IfaServiceNowMenuAndAclManagerA013ResponseDto invokeA013(IfaServiceNowMenuAndAclManagerA013RequestDto dtoReq)
            throws Exception {
        
        IfaServiceNowMenuAndAclManagerA013ResponseDto dtoRes = new IfaServiceNowMenuAndAclManagerA013ResponseDto();
        // 1. ユーザー利用可能メニュー一覧取得
        List<IfaServiceNowMenuDto> res = dao.selectA013AvailableMenu(dtoReq);
        
        dtoRes.setMenuList(res);
        ServiceNowUtil.createDataListServiceNow(dtoRes, 0);
        
        return dtoRes;
    }
    
}
