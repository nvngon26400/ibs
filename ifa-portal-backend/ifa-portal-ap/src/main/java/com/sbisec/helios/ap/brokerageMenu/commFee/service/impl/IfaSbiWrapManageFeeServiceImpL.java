package com.sbisec.helios.ap.brokerageMenu.commFee.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct007;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct007Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct007Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.IfaSbiWrapManageFeeDao;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaSbiWrapManageFeeSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaSbiWrapManageFeeSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaSbiWrapManageFeeA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaSbiWrapManageFeeA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaSbiWrapManageFeeA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaSbiWrapManageFeeA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaSbiWrapManageFeeCsvItems;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaSbiWrapManageFeeSbiWrapManageFeeInfoList;
import com.sbisec.helios.ap.brokerageMenu.commFee.service.IfaSbiWrapManageFeeService;
import com.sbisec.helios.ap.brokerageMenu.commFee.util.IfaSbiWrapManageFeeCsvOut;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.DateFormatUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 画面ID：SUB020504-01
 * 画面名：SBIラップ管理報酬
 *
 * @author 松尾
 2024/06/18 新規作成
 */
@Component(value = "cmpIfaSbiWrapManageFeeService")
public class IfaSbiWrapManageFeeServiceImpL implements IfaSbiWrapManageFeeService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaSbiWrapManageFeeServiceImpL.class);
    
    @Autowired
    private IfaSbiWrapManageFeeDao dao;
    
    @Autowired
    private Fct007 fct007;
    
    @Autowired
    private Fct030 fct030;
    
    @Autowired
    private Fct038 fct038;
    
    @Autowired
    private ComplianceService complianceService;
    
    /** 登録日チェックエラー */
    private static final String ERRORS_DATE_SPECIFYFROMTO = "errors.date.specifyFromTo";
    
    /** "登録日" */
    private static final String REGISTERDDATE = "登録日";
    
    /** 区分.カレンダー区分 カレンダーベース */
    private static final String CALENDARBASE = "1";
    
    /** 12ケ月後 */
    private static final Integer AFTERONEYEAR = 365;
    
    /** 登録日期間チェックエラー */
    private static final String ERRORS_DATERANGE = "errors.dateRange";
    
    /** "12ケ月" */
    private static final String ONEYEAR = "12ケ月";
    
    /** FCT030エラー */
    private static final String ERRORS_CMN_IFAAGENTCODES_NOTEXIST = "errors.cmn.ifaAgentCodes.notExist";
    
    /** 画面ID */
    private static final String SCREEN_ID = "SUB020504-01";

    /** 権限コード：SBI証券本店 の場合. */
    private static final String AUTH_CODE_SBI = "1";

    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaSbiWrapManageFeeA002RequestDto
     * Dto レスポンス：IfaSbiWrapManageFeeA002ResponseDto
     * model リクエスト：IfaSbiWrapManageFeeSQL001RequestModel
     * model レスポンス：IfaSbiWrapManageFeeSQL001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSbiWrapManageFeeA002ResponseDto> displayA002(IfaSbiWrapManageFeeA002RequestDto dtoReq)
            throws Exception {
        
        DataList<IfaSbiWrapManageFeeA002ResponseDto> dtoRes = new DataList<IfaSbiWrapManageFeeA002ResponseDto>();
        List<IfaSbiWrapManageFeeA002ResponseDto> resDtoList = new ArrayList<IfaSbiWrapManageFeeA002ResponseDto>();
        IfaSbiWrapManageFeeA002ResponseDto resDto = new IfaSbiWrapManageFeeA002ResponseDto();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaSbiWrapManageFeeServiceImplL.displayA002");
        
        //①   リクエスト.登録日From、リクエスト.登録日Toのチェックを行う。
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date fromDate = sdFormat.parse(dtoReq.getRegisteredDateFrom());
        Date toDate = sdFormat.parse(dtoReq.getRegisteredDateTo());
        // リクエスト.登録日To ＜ リクエスト.登録日Fromの場合、メッセージを表示し、処理終了。
        if (toDate.compareTo(fromDate) < 0) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_DATE_SPECIFYFROMTO,
                    IfaCommonUtil.getMessage(ERRORS_DATE_SPECIFYFROMTO, new String[] { REGISTERDDATE }));
            return dtoRes;
        }
        // リクエスト.登録日Fromを基準に12ケ月後の日付を取得し、
        InputFct007Dto fct007Req = new InputFct007Dto();
        // 基準日
        fct007Req.setStandardDate(fromDate);
        // カレンダー区分
        fct007Req.setCalendarType(CALENDARBASE);
        // 日数
        fct007Req.setDay(AFTERONEYEAR);
        OutputFct007Dto fct007Res = new OutputFct007Dto();
        fct007Res = fct007.getData(fct007Req);
        // リクエスト.登録日To ＞ レスポンス.指定日の場合、メッセージを表示し、処理終了。
        if (toDate.compareTo(fct007Res.getDesignatedDate()) > 0) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_DATERANGE,
                    IfaCommonUtil.getMessage(ERRORS_DATERANGE, new String[] { REGISTERDDATE, ONEYEAR }));
            return dtoRes;
        }
        // 上記以外の場合、次の処理へ。
        
        //② ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合：参照可能な仲介業者コードをと営業員コードを取得する。
        OutputFct030Dto fct030Res = new OutputFct030Dto();
        if (!StringUtils.equals(AUTH_CODE_SBI, IfaCommonUtil.getUserAccount().getPrivId())) {
            fct030Res = fct030.getData(new InputFct030Dto());
            // FCT030.仲介業者営業員リストの件数が０件の場合：メッセージを表示し、処理終了。
            if (ObjectUtils.isEmpty(fct030Res.getBrokerChargeList()) || fct030Res.getBrokerChargeList().size() == 0) {
                // エラー( errors.cmn.ifaAgentCodes.notExist)を返す
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_CMN_IFAAGENTCODES_NOTEXIST,
                        IfaCommonUtil.getMessage(ERRORS_CMN_IFAAGENTCODES_NOTEXIST));
                return dtoRes;
            }
        }
        
        //③ SBIラップ管理報酬情報データリストを取得する。
        IfaSbiWrapManageFeeSql001RequestModel selSql001Req = new IfaSbiWrapManageFeeSql001RequestModel();
        // 部店コード
        selSql001Req.setButenCode(dtoReq.getButenCode());
        // 口座番号
        selSql001Req.setAccountNumber(dtoReq.getAccountNumber());
        // 登録日From
        selSql001Req.setRegisteredDateFrom(dtoReq.getRegisteredDateFrom());
        // 登録日To
        selSql001Req.setRegisteredDateTo(dtoReq.getRegisteredDateTo());
        // 仲介業者コード
        selSql001Req.setBrokerCode(dtoReq.getBrokerCode());
        // 仲介業者除外
        selSql001Req.setChkBrokerCodeExclude(dtoReq.getChkBrokerCodeExclude());
        // 権限
        selSql001Req.setAuthority(IfaCommonUtil.getUserAccount().getPrivId());
        // 最大件数
        selSql001Req.setQuerySizeLimit(5000);
        // FCT030.仲介業者営業員リスト.仲介業者コード
        if (!CollectionUtils.isEmpty(fct030Res.getBrokerChargeList())) {
            List<IfaSbiWrapManageFeeSql001RequestModel.BrokerCharge> chargeList = new ArrayList<IfaSbiWrapManageFeeSql001RequestModel.BrokerCharge>();
            for (BrokerCharge brokerCharge : fct030Res.getBrokerChargeList()) {
                IfaSbiWrapManageFeeSql001RequestModel.BrokerCharge charge = new IfaSbiWrapManageFeeSql001RequestModel.BrokerCharge();
                charge.setBrokerCode(brokerCharge.getBrokerCode());
                charge.setEmpCode(brokerCharge.getEmpCode());
                chargeList.add(charge);
            }
            selSql001Req.setBrokerChargeList(chargeList);
            selSql001Req.setFct030BrokerCode(fct030Res.getBrokerChargeList().get(0).getBrokerCode());
            // FCT030.仲介業者営業員リスト.営業員コード
            selSql001Req.setFct030ChargeCode(fct030Res.getBrokerChargeList().get(0).getEmpCode());
        }

        DataList<IfaSbiWrapManageFeeSql001ResponseModel> selSql001Res = dao
                .selectIfaSbiWrapManageFeeSql001(selSql001Req);
        
        // SQL001.総件数
        int totalRow = 0;
        if (!(selSql001Res.getDataList().size() == 0)) {
            totalRow = selSql001Res.get(0).getTotalRow();
        }
        List<IfaSbiWrapManageFeeSbiWrapManageFeeInfoList> sbiWrapManageFeeInfoListList = new ArrayList<IfaSbiWrapManageFeeSbiWrapManageFeeInfoList>();
        // SQL001.総件数が0件の場合、メッセージを表示し、処理終了。
        if (totalRow == 0) {
            dtoRes.setDataList(resDtoList);
            dtoRes.setTotalSize(0);
            dtoRes.setMaxRownum(5000);
            dtoRes.setRequestedTime(IfaCommonUtil.getFormattedRequestedTime());
            
            return dtoRes;
            // SQL001.総件数が5000件超過の場合、
            // メッセージを表示し、レスポンスにSBIラップ管理報酬情報データリストの先頭から5000件をセットし、次の処理へ。
        } else if (totalRow > 5000) {
            for (IfaSbiWrapManageFeeSql001ResponseModel sqlRec : selSql001Res.getDataList()) {
                IfaSbiWrapManageFeeSbiWrapManageFeeInfoList sbiWrapManageFeeInfoList = new IfaSbiWrapManageFeeSbiWrapManageFeeInfoList();
                // 仲介業者コード
                sbiWrapManageFeeInfoList.setBrokerCode(sqlRec.getBrokerCode());
                // 仲介業者名
                sbiWrapManageFeeInfoList.setBrokerName(sqlRec.getBrokerName());
                // 営業員コード
                sbiWrapManageFeeInfoList.setEmpCode(sqlRec.getBrokerChargeCode());
                // 営業員名
                sbiWrapManageFeeInfoList.setBrokerChargeName(sqlRec.getBrokerChargeName());
                // 部店
                sbiWrapManageFeeInfoList.setButen(sqlRec.getButenCode());
                // 口座番号
                sbiWrapManageFeeInfoList.setAccountNumber(sqlRec.getAccountNumber());
                // 扱者コード
                sbiWrapManageFeeInfoList.setDealerNumber(sqlRec.getDealerNumber());
                // 顧客名
                sbiWrapManageFeeInfoList.setCustomerName(sqlRec.getNameKanji());
                // 対象年月日（YYYY/MM/DD）
                sbiWrapManageFeeInfoList.setTargetDateYmd(sqlRec.getSumDate());
                // 運用報酬徴収情報ID
                sbiWrapManageFeeInfoList.setOperationFeeCollectionInfoId(sqlRec.getAdvisoryFeeCollectionId());
                // NAME
                sbiWrapManageFeeInfoList.setName(sqlRec.getAdvisoryServiceId());
                // 手数料（円貨）
                sbiWrapManageFeeInfoList.setYenFee(sqlRec.getFee());
                
                sbiWrapManageFeeInfoListList.add(sbiWrapManageFeeInfoList);
            }
            resDto.setSbiWrapManageFeeInfoList(sbiWrapManageFeeInfoListList);
            resDtoList.add(resDto);
            dtoRes.setDataList(resDtoList);
            dtoRes.setTotalSize(totalRow);
            dtoRes.setMaxRownum(5000);
            dtoRes.setRequestedTime(IfaCommonUtil.getFormattedRequestedTime());
            dtoRes.setOverMaxRownum(true);
            
            return dtoRes;
        } else {
            for (IfaSbiWrapManageFeeSql001ResponseModel sqlRec : selSql001Res.getDataList()) {
                IfaSbiWrapManageFeeSbiWrapManageFeeInfoList sbiWrapManageFeeInfoList = new IfaSbiWrapManageFeeSbiWrapManageFeeInfoList();
                // 仲介業者コード
                sbiWrapManageFeeInfoList.setBrokerCode(sqlRec.getBrokerCode());
                // 仲介業者名
                sbiWrapManageFeeInfoList.setBrokerName(sqlRec.getBrokerName());
                // 営業員コード
                sbiWrapManageFeeInfoList.setEmpCode(sqlRec.getBrokerChargeCode());
                // 営業員名
                sbiWrapManageFeeInfoList.setBrokerChargeName(sqlRec.getBrokerChargeName());
                // 部店
                sbiWrapManageFeeInfoList.setButen(sqlRec.getButenCode());
                // 口座番号
                sbiWrapManageFeeInfoList.setAccountNumber(sqlRec.getAccountNumber());
                // 扱者コード
                sbiWrapManageFeeInfoList.setDealerNumber(sqlRec.getDealerNumber());
                // 顧客名
                sbiWrapManageFeeInfoList.setCustomerName(sqlRec.getNameKanji());
                // 対象年月日（YYYY/MM/DD）
                sbiWrapManageFeeInfoList.setTargetDateYmd(sqlRec.getSumDate());
                // 運用報酬徴収情報ID
                sbiWrapManageFeeInfoList.setOperationFeeCollectionInfoId(sqlRec.getAdvisoryFeeCollectionId());
                // NAME
                sbiWrapManageFeeInfoList.setName(sqlRec.getAdvisoryServiceId());
                // 手数料（円貨）
                sbiWrapManageFeeInfoList.setYenFee(sqlRec.getFee());
                
                sbiWrapManageFeeInfoListList.add(sbiWrapManageFeeInfoList);
            }
        }
        // 上記以外の場合、次の処理へ。
        
        resDto.setSbiWrapManageFeeInfoList(sbiWrapManageFeeInfoListList);
        resDtoList.add(resDto);
        dtoRes.setDataList(resDtoList);
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "0", "");
        return dtoRes;
    }
    
    /**
     * アクションID：A003
     * アクション名：CSV出力
     * Dto リクエスト：IfaSbiWrapManageFeeA003RequestDto
     * Dto レスポンス：IfaSbiWrapManageFeeA003ResponseDto
     * model リクエスト：IfaSbiWrapManageFeeSQL001RequestModel
     * model レスポンス：IfaSbiWrapManageFeeSQL001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSbiWrapManageFeeA003ResponseDto> csvOutputA003(IfaSbiWrapManageFeeA003RequestDto dtoReq,
            String fwSessionId) throws Exception {
        
        DataList<IfaSbiWrapManageFeeA003ResponseDto> dtoRes = new DataList<IfaSbiWrapManageFeeA003ResponseDto>();
        List<IfaSbiWrapManageFeeA003ResponseDto> resDtoList = new ArrayList<IfaSbiWrapManageFeeA003ResponseDto>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaSbiWrapManageFeeServiceImplL.csvOutputA003");
        
        //①   A002の①~②の処理を行う。
        //①   リクエスト.登録日From、リクエスト.登録日Toのチェックを行う。
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date fromDate = sdFormat.parse(dtoReq.getRegisteredDateFrom());
        Date toDate = sdFormat.parse(dtoReq.getRegisteredDateTo());
        // リクエスト.登録日To ＜ リクエスト.登録日Fromの場合、メッセージを表示し、処理終了。
        if (toDate.compareTo(fromDate) < 0) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_DATE_SPECIFYFROMTO,
                    IfaCommonUtil.getMessage(ERRORS_DATE_SPECIFYFROMTO, new String[] { REGISTERDDATE }));
            return dtoRes;
        }
        // リクエスト.登録日Fromを基準に12ケ月後の日付を取得し、
        InputFct007Dto fct007Req = new InputFct007Dto();
        // 基準日
        fct007Req.setStandardDate(fromDate);
        // カレンダー区分
        fct007Req.setCalendarType(CALENDARBASE);
        // 日数
        fct007Req.setDay(AFTERONEYEAR);
        OutputFct007Dto fct007Res = new OutputFct007Dto();
        fct007Res = fct007.getData(fct007Req);
        // リクエスト.登録日To ＞ レスポンス.指定日の場合、メッセージを表示し、処理終了。
        if (toDate.compareTo(fct007Res.getDesignatedDate()) > 0) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_DATERANGE,
                    IfaCommonUtil.getMessage(ERRORS_DATERANGE, new String[] { REGISTERDDATE, ONEYEAR }));
            return dtoRes;
        }
        // 上記以外の場合、次の処理へ。
        
        //② ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合：参照可能な仲介業者コードをと営業員コードを取得する。
        OutputFct030Dto fct030Res = new OutputFct030Dto();
        if (!StringUtils.equals(AUTH_CODE_SBI, IfaCommonUtil.getUserAccount().getPrivId())) {
            fct030Res = fct030.getData(new InputFct030Dto());
            // FCT030.仲介業者営業員リストの件数が０件の場合：メッセージを表示し、処理終了。
            if (ObjectUtils.isEmpty(fct030Res.getBrokerChargeList()) || fct030Res.getBrokerChargeList().size() == 0) {
                // エラー( errors.cmn.ifaAgentCodes.notExist)を返す
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_CMN_IFAAGENTCODES_NOTEXIST,
                        IfaCommonUtil.getMessage(ERRORS_CMN_IFAAGENTCODES_NOTEXIST));
                return dtoRes;
            }
        }

        //②   CSVダウンロードMAX件数を取得し、+1した値を最大取得件数にセットする。
        InputFct038Dto fct038InData = new InputFct038Dto();
        fct038InData.setScreenId(SCREEN_ID);
        fct038InData.setUserAuthority(IfaCommonUtil.getUserAccount().getPrivId());
        OutputFct038Dto fct038Dto = fct038.getData(fct038InData);
        // CSVダウンロードMAX件数
        int maxCsvRowNum = fct038Dto.getCsvDownloadNum();
        
        //③   SBIラップ管理報酬情報データリストを取得する。
        IfaSbiWrapManageFeeSql001RequestModel selSql001Req = new IfaSbiWrapManageFeeSql001RequestModel();
        // 部店コード
        selSql001Req.setButenCode(dtoReq.getButenCode());
        // 口座番号
        selSql001Req.setAccountNumber(dtoReq.getAccountNumber());
        // 登録日From
        selSql001Req.setRegisteredDateFrom(dtoReq.getRegisteredDateFrom());
        // 登録日To
        selSql001Req.setRegisteredDateTo(dtoReq.getRegisteredDateTo());
        // 仲介業者コード
        selSql001Req.setBrokerCode(dtoReq.getBrokerCode());
        // 仲介業者除外
        selSql001Req.setChkBrokerCodeExclude(dtoReq.getChkBrokerCodeExclude());
        // 権限
        selSql001Req.setAuthority(IfaCommonUtil.getUserAccount().getPrivId());
        // 最大件数
        selSql001Req.setQuerySizeLimit(maxCsvRowNum);
        
        // FCT030.仲介業者営業員リスト.仲介業者コード
        if (!CollectionUtils.isEmpty(fct030Res.getBrokerChargeList())) {
            List<IfaSbiWrapManageFeeSql001RequestModel.BrokerCharge> chargeList = new ArrayList<IfaSbiWrapManageFeeSql001RequestModel.BrokerCharge>();
            for (BrokerCharge brokerCharge : fct030Res.getBrokerChargeList()) {
                IfaSbiWrapManageFeeSql001RequestModel.BrokerCharge charge = new IfaSbiWrapManageFeeSql001RequestModel.BrokerCharge();
                charge.setBrokerCode(brokerCharge.getBrokerCode());
                charge.setEmpCode(brokerCharge.getEmpCode());
                chargeList.add(charge);
            }
            selSql001Req.setBrokerChargeList(chargeList);
            selSql001Req.setFct030BrokerCode(fct030Res.getBrokerChargeList().get(0).getBrokerCode());
            // FCT030.仲介業者営業員リスト.営業員コード
            selSql001Req.setFct030ChargeCode(fct030Res.getBrokerChargeList().get(0).getEmpCode());
        }

        DataList<IfaSbiWrapManageFeeSql001ResponseModel> selSql001Res = dao
                .selectIfaSbiWrapManageFeeSql001(selSql001Req);
        // SQL001.総件数
        int totalRow = 0;
        if (!(selSql001Res.getDataList().size() == 0)) {
            totalRow = selSql001Res.get(0).getTotalRow();
        }
        // SQL001.総件数が0件の場合、メッセージを表示し、処理終了。
        if (totalRow == 0) {
            dtoRes.setDataList(resDtoList);
            dtoRes.setTotalSize(0);
            dtoRes.setMaxRownum(5000);
            dtoRes.setRequestedTime(IfaCommonUtil.getFormattedRequestedTime());

            return dtoRes;
            // SQL001.総件数＞CSVダウンロードMAX件数の場合、メッセージを表示し、次の処理へ。
        } else if (totalRow > maxCsvRowNum) {
            dtoRes.setDataList(resDtoList);
            dtoRes.setTotalSize(totalRow);
            dtoRes.setMaxRownum(maxCsvRowNum);
            dtoRes.setRequestedTime(IfaCommonUtil.getFormattedRequestedTime());
            dtoRes.setOverMaxRownum(true);
        }
        
        //④  CSVファイル用のデータを作成する。
        // ※SQL001.総件数＞CSVダウンロードMAX件数の場合、
        // SBIラップ管理報酬情報データリストの先頭からCSVダウンロードMAX件数分のデータのみを処理対象とする。
        IfaSbiWrapManageFeeCsvOut csvOut = new IfaSbiWrapManageFeeCsvOut(complianceService);
        DataList<IfaSbiWrapManageFeeCsvItems> exportList = new DataList<IfaSbiWrapManageFeeCsvItems>();
        
        List<IfaSbiWrapManageFeeCsvItems> csvItemsList = new ArrayList<>();
        for (IfaSbiWrapManageFeeSql001ResponseModel sqlRes : selSql001Res.getDataList()) {
            IfaSbiWrapManageFeeCsvItems csvItems = new IfaSbiWrapManageFeeCsvItems();
            // 仲介業者コード
            csvItems.setBrokerCode(sqlRes.getBrokerCode());
            // 仲介業者名
            csvItems.setBrokerName(sqlRes.getBrokerName());
            // 営業員コード
            csvItems.setEmpCode(sqlRes.getBrokerChargeCode());
            // 営業員名
            csvItems.setBrokerChargeName(sqlRes.getBrokerChargeName());
            // 部店
            csvItems.setButen(sqlRes.getButenCode());
            // 口座番号
            csvItems.setAccountNumber(sqlRes.getAccountNumber());
            // 扱者コード
            csvItems.setDealerNumber(sqlRes.getDealerNumber());
            // 顧客名（漢字）
            csvItems.setCustomerNameKanji(sqlRes.getNameKanji());
            // 手数料徴収日
            csvItems.setFeeCollectionDate(DateFormatUtil.dateFormatToSlash(sqlRes.getSumDate()));
            // 手数料番号
            csvItems.setFeeNumber(sqlRes.getAdvisoryFeeCollectionId());
            // 運用サービスID
            csvItems.setOperationServiceId(sqlRes.getAdvisoryServiceId());
            // 徴収額(税抜)
            csvItems.setCollectionAmount(sqlRes.getFee());
            
            csvItemsList.add(csvItems);
        }
        exportList.setDataList(csvItemsList);
        
        //⑤   CSVファイルを出力する。
        dtoRes.setTitle(
                csvOut.doCreateCsvFile(exportList, fwSessionId, IfaCommonUtil.getUserAccount().getUserId(), null));
        dtoRes.setTotalSize(totalRow);
        return dtoRes;
    }
}
