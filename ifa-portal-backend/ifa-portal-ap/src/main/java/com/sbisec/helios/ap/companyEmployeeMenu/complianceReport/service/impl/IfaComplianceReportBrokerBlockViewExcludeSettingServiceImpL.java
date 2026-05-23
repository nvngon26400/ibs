package com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.RtnCdEnum;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.IfaComplianceReportBrokerBlockViewExcludeSettingDao;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportBrokerBlockViewExcludeSettingSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportBrokerBlockViewExcludeSettingSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportBrokerBlockViewExcludeSettingSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportBrokerBlockViewExcludeSettingSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportBrokerBlockViewExcludeSettingA002RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportBrokerBlockViewExcludeSettingA002ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportBrokerBlockViewExcludeSettingA003RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportBrokerBlockViewExcludeSettingA003ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportBrokerBlockViewExcludeSettingA004RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportBrokerBlockViewExcludeSettingA004ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportBrokerBlockViewExcludeSettingA005RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportBrokerBlockViewExcludeSettingA005ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.service.IfaComplianceReportBrokerBlockViewExcludeSettingService;

/**
 * 画面ID：SUB0505_03-01
 * 画面名：コンプライアンス通信仲介業者一括閲覧不要設定
 * 2023/11/08 新規作成
 *
 * @author SCSK 江口
 * 
 */
@Component(value = "cmpIfaComplianceReportBrokerBlockViewExcludeSettingService")
public class IfaComplianceReportBrokerBlockViewExcludeSettingServiceImpL
        implements IfaComplianceReportBrokerBlockViewExcludeSettingService {
    
    private static final Logger LOGGER 
            = LoggerFactory.getLogger(IfaComplianceReportBrokerBlockViewExcludeSettingServiceImpL.class);
    
    /** 取得結果0件エラー */
    private static final String ERRORS_DATALIST_NOTFOUND = "errors.dataList.notfound";
    
    /** コンプライアンス通信一括閲覧設定の閲覧要否変更成功 */
    private static final String INFO_COR_BULK_BROWSE_FLAG = "info.COR_BROWSE_CONTROL.COR_BULK_BROWSE_FLAG";
    
    /** コンプライアンス通信一括閲覧設定の閲覧要否変更エラー */
    private static final String ERRORS_COR_BULK_BROWSE_FLAG = "errors.COR_BROWSE_CONTROL.COR_BULK_BROWSE_FLAG";
    
    @Autowired
    private IfaComplianceReportBrokerBlockViewExcludeSettingDao dao;
    
    /**
     * アクションID：A002
     * アクション名：全仲介業者名表示
     * Dto リクエスト：IfaComplianceReportBrokerBlockViewExcludeSettingA002RequestDto
     * Dto レスポンス：IfaComplianceReportBrokerBlockViewExcludeSettingA002ResponseDto
     * model リクエスト：IfaComplianceReportBrokerBlockViewExcludeSettingSql001RequestModel
     * model レスポンス：IfaComplianceReportBrokerBlockViewExcludeSettingSql001ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return コンプライアンス通信閲覧要否管理情報
     * @exception Exception SQLExceptionやIFARuntimeExceptionなど
     */
    @Override
    public DataList<IfaComplianceReportBrokerBlockViewExcludeSettingA002ResponseDto> allBrokerNameDisplayA002(
            IfaComplianceReportBrokerBlockViewExcludeSettingA002RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaComplianceReportBrokerBlockViewExcludeSettingServiceImplL.allBrokerNameDisplayA002");
        }
        
        // [SQL001.コンプライアンス通信の閲覧要否管理情報取得]の実行
        IfaComplianceReportBrokerBlockViewExcludeSettingSql001RequestModel selSql001Req
                = new IfaComplianceReportBrokerBlockViewExcludeSettingSql001RequestModel();
        DataList<IfaComplianceReportBrokerBlockViewExcludeSettingSql001ResponseModel> selSql001Res
                = dao.selectIfaComplianceReportBrokerBlockViewExcludeSettingSql001(selSql001Req);
        
        // SQL001のレスポンスをinnerDataListに格納
        List<IfaComplianceReportBrokerBlockViewExcludeSettingA002ResponseDto> innerDataList = new ArrayList<>();
        for (IfaComplianceReportBrokerBlockViewExcludeSettingSql001ResponseModel record : selSql001Res.getDataList()) {
            IfaComplianceReportBrokerBlockViewExcludeSettingA002ResponseDto browseControl = new IfaComplianceReportBrokerBlockViewExcludeSettingA002ResponseDto();
            BeanUtils.copyProperties(browseControl, record);
            
            innerDataList.add(browseControl);
        }
        
        // 結果をDTOに格納
        DataList<IfaComplianceReportBrokerBlockViewExcludeSettingA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                innerDataList,
                ErrorLevel.SUCCESS,
                RtnCdEnum.SUCCESS.getText(),
                ""
        );
        
        return dtoRes;
        
    }
    
    /**
     * アクションID：A003
     * アクション名：検索表示
     * Dto リクエスト：IfaComplianceReportBrokerBlockViewExcludeSettingA003RequestDto
     * Dto レスポンス：IfaComplianceReportBrokerBlockViewExcludeSettingA003ResponseDto
     * model リクエスト：IfaComplianceReportBrokerBlockViewExcludeSettingSql001RequestModel
     * model レスポンス：IfaComplianceReportBrokerBlockViewExcludeSettingSql001ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return コンプライアンス通信閲覧要否管理情報
     * @exception Exception SQLExceptionなど
     */
    @Override
    public DataList<IfaComplianceReportBrokerBlockViewExcludeSettingA003ResponseDto> searchDisplayA003(
            IfaComplianceReportBrokerBlockViewExcludeSettingA003RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaComplianceReportBrokerBlockViewExcludeSettingServiceImplL.searchDisplayA003");
        }
        
        // [SQL001.コンプライアンス通信の閲覧要否管理情報取得]の実行
        IfaComplianceReportBrokerBlockViewExcludeSettingSql001RequestModel selSql001Req
                = new IfaComplianceReportBrokerBlockViewExcludeSettingSql001RequestModel();
        BeanUtils.copyProperties(selSql001Req, dtoReq);
        DataList<IfaComplianceReportBrokerBlockViewExcludeSettingSql001ResponseModel> selSql001Res
                = dao.selectIfaComplianceReportBrokerBlockViewExcludeSettingSql001(selSql001Req);
        
        // 取得結果0件エラーの処理
        if (selSql001Res.size() == 0) {
            DataList<IfaComplianceReportBrokerBlockViewExcludeSettingA003ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.INFO,
                    ERRORS_DATALIST_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATALIST_NOTFOUND, new String[] {})
            );
            
            return dtoRes;
            
        }
        
        // SQL001のレスポンスをinnerDataListに格納
        List<IfaComplianceReportBrokerBlockViewExcludeSettingA003ResponseDto> innerDataList = new ArrayList<>();
        for (IfaComplianceReportBrokerBlockViewExcludeSettingSql001ResponseModel record : selSql001Res.getDataList()) {
            IfaComplianceReportBrokerBlockViewExcludeSettingA003ResponseDto browseControl = new IfaComplianceReportBrokerBlockViewExcludeSettingA003ResponseDto();
            BeanUtils.copyProperties(browseControl, record);
            
            innerDataList.add(browseControl);
        }
        
        // 結果をDTOに格納
        DataList<IfaComplianceReportBrokerBlockViewExcludeSettingA003ResponseDto> dtoRes = new DataList<>();
        dtoRes = IfaCommonUtil.createDataList(
            innerDataList,
            ErrorLevel.SUCCESS,
            RtnCdEnum.SUCCESS.getText(),
            ""
        );
        
        return dtoRes;
        
    }
    
    /**
     * アクションID：A004
     * アクション名：登録
     * Dto リクエスト：IfaComplianceReportBrokerBlockViewExcludeSettingA004RequestDto
     * Dto レスポンス：IfaComplianceReportBrokerBlockViewExcludeSettingA004ResponseDto
     * model リクエスト：IfaComplianceReportBrokerBlockViewExcludeSettingSql002RequestModel
     * model レスポンス：IfaComplianceReportBrokerBlockViewExcludeSettingSql002ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return コンプライアンス通信閲覧要否管理情報
     * @exception Exception SQLExceptionなど
     */
    @Override
    public DataList<IfaComplianceReportBrokerBlockViewExcludeSettingA004ResponseDto> registerA004(
            IfaComplianceReportBrokerBlockViewExcludeSettingA004RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaComplianceReportBrokerBlockViewExcludeSettingServiceImplL.registerA004");
        }
        
        // SQLリクエストの作成
        IfaComplianceReportBrokerBlockViewExcludeSettingSql002RequestModel insSql002Req
                = new IfaComplianceReportBrokerBlockViewExcludeSettingSql002RequestModel();
        String userId = IfaCommonUtil.getUserAccount().getUserId();
        insSql002Req.setBrokerCode(dtoReq.getBrokerCode());
        insSql002Req.setCreateBy(userId);
        insSql002Req.setUpdateBy(userId);

        // [SQL002 登録]処理の実行
        int insSql002Res = 0;
        try {
            insSql002Res = dao.insertIfaComplianceReportBrokerBlockViewExcludeSettingSql002(insSql002Req);
            
        } catch (DuplicateKeyException e) {
            // 主キー重複エラーの場合insSql002Resが0から変化しない。
            // => 後続処理でエラーのDTO Resがセットされる。
            // そのため、追加処理不要
            LOGGER.debug(
                    "{}, {}, {}",
                    "IfaComplianceReportBrokerBlockViewExcludeSettingServiceImpL",
                    "registerA004",
                    e
            );
        }
        
        // [SQL002 登録]の結果によってDTOを作成
        DataList<IfaComplianceReportBrokerBlockViewExcludeSettingA004ResponseDto> dtoRes = new DataList<>();
        if (insSql002Res == 1) {
            dtoRes = IfaCommonUtil.createDataList(
                new ArrayList<>(),
                ErrorLevel.INFO, INFO_COR_BULK_BROWSE_FLAG,
                IfaCommonUtil.getMessage(INFO_COR_BULK_BROWSE_FLAG, new String[] {})
            );
            
        } else {
            dtoRes = IfaCommonUtil.createDataList(
                new ArrayList<>(),
                ErrorLevel.FATAL,
                ERRORS_COR_BULK_BROWSE_FLAG,
                IfaCommonUtil.getMessage(ERRORS_COR_BULK_BROWSE_FLAG, new String[] {})
            );
        }
        
        return dtoRes;
        
    }
    
    /**
     * アクションID：A005
     * アクション名：登録解除
     * Dto リクエスト：IfaComplianceReportBrokerBlockViewExcludeSettingA005RequestDto
     * Dto レスポンス：IfaComplianceReportBrokerBlockViewExcludeSettingA005ResponseDto
     * model リクエスト：IfaComplianceReportBrokerBlockViewExcludeSettingSql003RequestModel
     * model レスポンス：IfaComplianceReportBrokerBlockViewExcludeSettingSql003ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return コンプライアンス通信閲覧要否管理情報
     * @exception Exception SQLExceptionなど
     */
    @Override
    public DataList<IfaComplianceReportBrokerBlockViewExcludeSettingA005ResponseDto> registrationCancellationA005(
            IfaComplianceReportBrokerBlockViewExcludeSettingA005RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaComplianceReportBrokerBlockViewExcludeSettingServiceImplL.registrationCancellationA005");
        }
        
        // [SQL003 削除]処理の実行
        IfaComplianceReportBrokerBlockViewExcludeSettingSql003RequestModel delSql003Req
                =    new IfaComplianceReportBrokerBlockViewExcludeSettingSql003RequestModel();
        BeanUtils.copyProperties(delSql003Req, dtoReq);
        int delSql003Res = dao.deleteIfaComplianceReportBrokerBlockViewExcludeSettingSql003(delSql003Req);
        
        // [SQL003 削除]の結果によってDTOを作成
        DataList<IfaComplianceReportBrokerBlockViewExcludeSettingA005ResponseDto> dtoRes = new DataList<>();
        if (delSql003Res >= 1) {
            dtoRes = IfaCommonUtil.createDataList(
                new ArrayList<>(),
                ErrorLevel.INFO,
                INFO_COR_BULK_BROWSE_FLAG,
                IfaCommonUtil.getMessage(INFO_COR_BULK_BROWSE_FLAG, new String[] {})
            );
            
        } else {
            dtoRes = IfaCommonUtil.createDataList(
                new ArrayList<>(),
                ErrorLevel.FATAL,
                ERRORS_COR_BULK_BROWSE_FLAG,
                IfaCommonUtil.getMessage(ERRORS_COR_BULK_BROWSE_FLAG, new String[] {})
            );
        }
        
        return dtoRes;
        
    }
}
