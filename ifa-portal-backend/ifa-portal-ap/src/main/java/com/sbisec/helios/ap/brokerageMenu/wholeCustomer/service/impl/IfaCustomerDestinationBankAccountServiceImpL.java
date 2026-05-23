package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaCustomerDestinationBankAccountDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCustomerDestinationBankAccountA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCustomerDestinationBankAccountA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCustomerDestinationBankAccountDtoResponseCustomerDestinationBankAccount;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaCustomerDestinationBankAccountSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaCustomerDestinationBankAccountSql001RequestModelBrokerCharge;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaCustomerDestinationBankAccountSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCustomerDestinationBankAccountA004aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCustomerDestinationBankAccountA004aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.IfaCustomerDestinationBankAccountService;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;

import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util.IfaCustomerDestinationBankAccountCsvOut;
import com.sbisec.helios.ap.compliance.service.ComplianceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * 画面ID：SUB020303-01
 * 画面名：顧客振込先金融機関口座

 * @author 大崎 辰弥
    2023/10/27 新規作成
 */

@Component(value = "cmpIfaCustomerDestinationBankAccountService")
public class IfaCustomerDestinationBankAccountServiceImpL 
        implements IfaCustomerDestinationBankAccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaCustomerDestinationBankAccountServiceImpL.class);
    // --------------------------------
    // メッセージ
    // --------------------------------

    /** "参照可能な仲介業者コード／営業員コードが存在しません。" */

    private static final String ERRORS_CMN_IFAAGENTCODES_NOTEXIST = "errors.cmn.ifaAgentCodes.notExist";

    /** "検索結果が0件です。\n条件を設定して再度検索して下さい。" */

    private static final String ERRORS_DATALIST_NOTFOUNT = "errors.dataList.notfound";

    /**
     * "検索結果が{0}件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。" {0}："5,000"
     * {1}：顧客振込先金融機関口座リストの件数
     */

    private static final String WARNINGS_DATALIST_OVERMAXROWNUM = "warnings.dataList.overMaxRownum";

    /**
     * "検索結果が{0}件を超過しています（{1}件ヒット）。\n{2}件のcsv出力を行います。" {0}：CSVダウンロードMAX件数
     * {1}：顧客振込先金融機関口座リストの件数 {2}：CSVダウンロードMAX件数
     */

    private static final String WARNINGS_DATALIST_CSV_OVERMAXROWNUM = "warnings.dataList.csv.overMaxRownum";
    
    // --------------------------------
    // 変数定義
    // --------------------------------
    /** 権限コード：SBI証券本店 の場合 */
    
    private static final String AUTH_CODE_SBI = "1";
    
    /** 最大データリストサイズ */
    
    private static final int MAX_ROW_NUM = 5000;
    
    /** 画面ID */
    
    private static final String SCREEN_ID = "SUB020303-01";
     
    @Autowired
    private IfaCustomerDestinationBankAccountDao dao;
    
    @Autowired
    private Fct030 fct030;
    
    @Autowired
    private Fct038 fct038;
    
    /**
     * ComplianceService(CSV証跡登録用)クラス
     */
    
    @Autowired
    private ComplianceService complianceService;
    
    /**
     * アクションID：A002 アクション名：表示 Dto
     * リクエスト：IfaCustomerDestinationBankAccountA002DtoRequest Dto
     * レスポンス：IfaCustomerDestinationBankAccountA002DtoResponse model
     * リクエスト：IfaCustomerDestinationBankAccountSql001RequestModel model
     * レスポンス：IfaCustomerDestinationBankAccountSql001ResponseModel

     * @param dtoReq リクエスト
     * @return IfaCustomerDestinationBankAccountA002DtoResponse
     * @exception Exception SQLExceptionなど 
     */
    public DataList<IfaCustomerDestinationBankAccountA002DtoResponse> displayA002(
            IfaCustomerDestinationBankAccountA002DtoRequest dtoReq) throws Exception {
        
        DataList<IfaCustomerDestinationBankAccountA002DtoResponse> dtoRes = new DataList<IfaCustomerDestinationBankAccountA002DtoResponse>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaCustomerDestinationBankAccountServiceImplL.displayA002");
        }
        
        // 仲介業者営業員リスト
        OutputFct030Dto fct030Dto = new OutputFct030Dto();
        
        // ユーザ共通情報
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // 権限チェック
        if (! StringUtils.equals(AUTH_CODE_SBI, userAccount.getPrivId())) {
            InputFct030Dto fct030InData = new InputFct030Dto();
            fct030Dto = fct030.getData(fct030InData);
            
            if (CollectionUtils.isEmpty(fct030Dto.getBrokerChargeList()) || fct030Dto.getBrokerChargeList().size() == 0) {
                return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_CMN_IFAAGENTCODES_NOTEXIST, IfaCommonUtil.getMessage(ERRORS_CMN_IFAAGENTCODES_NOTEXIST, new String[] {}));
            }
        }

        IfaCustomerDestinationBankAccountSql001RequestModel selSql001Req = new IfaCustomerDestinationBankAccountSql001RequestModel();
        BeanUtils.copyProperties(selSql001Req, dtoReq);
        selSql001Req.setBrokerChargeList(getBrokerChargeListSqlReq(fct030Dto));
        selSql001Req.setPrivId(userAccount.getPrivId());
        selSql001Req.setMaxRow(MAX_ROW_NUM);

        // 仲介業者除外フラグ
        if ("".equals(dtoReq.getChkBrokerCodeExclude())) {
            selSql001Req.setChkBrokerCodeExclude("0");
        } else {
            selSql001Req.setChkBrokerCodeExclude("1");
        }
        
        // SQL001 顧客振込先金融機関口座一覧取得
        DataList<IfaCustomerDestinationBankAccountSql001ResponseModel> selSql001Res = dao.selectIfaCustomerDestinationBankAccountSql001(selSql001Req);

        // 取得件数チェック
        // 顧客振込先金融機関口座リストの件数が0件の場合：メッセージを表示し、処理終了。
        // 顧客振込先金融機関口座リストの件数が5000件より大きい場合：
        // メッセージを表示し、レスポンスに顧客振込先金融機関口座リストの前から5000件をセットし、次の処理へ。
        if (selSql001Res == null || selSql001Res.getDataList().size() == 0) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.INFO, ERRORS_DATALIST_NOTFOUNT,
                    IfaCommonUtil.getMessage(ERRORS_DATALIST_NOTFOUNT));
        }
        
        // レスポンスに顧客振込先金融機関口座リストをセット
        List<IfaCustomerDestinationBankAccountA002DtoResponse> responseList = new ArrayList<IfaCustomerDestinationBankAccountA002DtoResponse>();
        IfaCustomerDestinationBankAccountA002DtoResponse response = new IfaCustomerDestinationBankAccountA002DtoResponse();
        
        response.setCustomerDestinationBankAccountList(setCustomerDestinationBankAccountList(selSql001Res));
        responseList.add(response);
        
        int totalRow = selSql001Res.get(0).getTotalRow();
        
        if (MAX_ROW_NUM < totalRow) {
            dtoRes = IfaCommonUtil.createDataList(responseList, ErrorLevel.WARNING, WARNINGS_DATALIST_OVERMAXROWNUM, IfaCommonUtil.getMessage(WARNINGS_DATALIST_OVERMAXROWNUM,
                    new String[] { String.valueOf(MAX_ROW_NUM), String.valueOf(totalRow) }));
        } else {
            dtoRes = IfaCommonUtil.createDataList(responseList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
        }

        return dtoRes;
    }
    
    /**
     * アクションID：A004 アクション名：CSV出力 Dto
     * リクエスト：IfaCustomerDestinationBankAccountA004aDtoRequest Dto
     * レスポンス：IfaCustomerDestinationBankAccountA004aDtoResponse model
     * リクエスト：IfaCustomerDestinationBankAccountSql001RequestModel model
     * レスポンス：IfaCustomerDestinationBankAccountSql001ResponseModel

     * @param dtoReq リクエスト
     * @return IfaCustomerDestinationBankAccountA004aDtoResponse
     * @exception Exception SQLExceptionなど 
     */
    
    public DataList<IfaCustomerDestinationBankAccountA004aDtoResponse> csvOutputA004a(
            IfaCustomerDestinationBankAccountA004aDtoRequest dtoReq, String fwSessionId) throws Exception {
        
        DataList<IfaCustomerDestinationBankAccountA004aDtoResponse> dtoRes = new DataList<IfaCustomerDestinationBankAccountA004aDtoResponse>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaCustomerDestinationBankAccountServiceImplL.csvOutputA004a");
        }
        
        // 仲介業者営業員リスト
        OutputFct030Dto fct030Dto = new OutputFct030Dto();

        // ユーザ共通情報
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // 権限チェック
        if (! StringUtils.equals(AUTH_CODE_SBI, userAccount.getPrivId())) {
            InputFct030Dto fct030InData = new InputFct030Dto();
            fct030Dto = fct030.getData(fct030InData);
            
            if (CollectionUtils.isEmpty(fct030Dto.getBrokerChargeList()) || fct030Dto.getBrokerChargeList().size() == 0) {
                return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_CMN_IFAAGENTCODES_NOTEXIST, IfaCommonUtil.getMessage(ERRORS_CMN_IFAAGENTCODES_NOTEXIST, new String[] {}));
            }
        }
        
        // SQL001 顧客振込先金融機関口座一覧取得
        IfaCustomerDestinationBankAccountSql001RequestModel selSql001Req = new IfaCustomerDestinationBankAccountSql001RequestModel();
        BeanUtils.copyProperties(selSql001Req, dtoReq);
        selSql001Req.setBrokerChargeList(getBrokerChargeListSqlReq(fct030Dto));
        selSql001Req.setPrivId(userAccount.getPrivId());
        
        // 仲介業者除外フラグ
        if ("".equals(dtoReq.getChkBrokerCodeExclude())) {
            selSql001Req.setChkBrokerCodeExclude("0");
        } else {
            selSql001Req.setChkBrokerCodeExclude("1");
        }
        
        // 仲介業者コード
        if (!StringUtil.isNullOrEmpty(dtoReq.getBrokerCode())) {   
            List<String> brokerCodeList = new ArrayList<String>();        
            brokerCodeList.addAll(Arrays.asList(dtoReq.getBrokerCode().split(",")));
            selSql001Req.setBrokerCodeList(brokerCodeList);
        }
        // CSVダウンロードMAX件数を取得する。
        InputFct038Dto fct038InData = new InputFct038Dto();
        fct038InData.setScreenId(SCREEN_ID);
        fct038InData.setUserAuthority(userAccount.getPrivId());
        
        OutputFct038Dto fct038Dto = fct038.getData(fct038InData);
        // CSVダウンロードMAX件数
        int maxCsvRowNum = fct038Dto.getCsvDownloadNum();
        
        selSql001Req.setMaxRow(maxCsvRowNum);
        DataList<IfaCustomerDestinationBankAccountSql001ResponseModel> selSql001Res = dao.selectIfaCustomerDestinationBankAccountSql001(selSql001Req);
       
        // 件数チェック
        // 顧客振込先金融機関口座リストの件数が0件の場合：メッセージを表示し、処理終了。
        // 顧客振込先金融機関口座リストの件数がCSVダウンロードMAX件数より大きい場合：メッセージを表示し、次の処理へ。
        if (selSql001Res == null || selSql001Res.getDataList().size() == 0) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.INFO, ERRORS_DATALIST_NOTFOUNT,
                    IfaCommonUtil.getMessage(ERRORS_DATALIST_NOTFOUNT));
        }
        
        // CSVファイル用のデータを作成
        IfaCustomerDestinationBankAccountCsvOut csvOut = new IfaCustomerDestinationBankAccountCsvOut(complianceService);
        DataList<IfaCustomerDestinationBankAccountDtoResponseCustomerDestinationBankAccount> exportList = new DataList<IfaCustomerDestinationBankAccountDtoResponseCustomerDestinationBankAccount>();
        exportList.setDataList(setCustomerDestinationBankAccountList(selSql001Res));

        int totalRow = selSql001Res.get(0).getTotalRow();
        
        if (maxCsvRowNum < totalRow) {
            dtoRes = IfaCommonUtil.createDataList(null, ErrorLevel.WARNING, WARNINGS_DATALIST_CSV_OVERMAXROWNUM, IfaCommonUtil.getMessage(WARNINGS_DATALIST_CSV_OVERMAXROWNUM,
                    new String[] { String.valueOf(maxCsvRowNum), String.valueOf(totalRow), String.valueOf(maxCsvRowNum)}));
        } else {
            dtoRes = IfaCommonUtil.createDataList(null, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
        }
        // CSVファイル作成
        // レスポンスのタイトルにCSVファイル名を設定
        dtoRes.setTitle(csvOut.doCreateCsvFile(exportList, fwSessionId, userAccount.getUserId(), null));
        dtoRes.setTotalSize(totalRow);
        return dtoRes;
    }

    /**
     * SqlReq用のbrokerChargeListを返却する処理

     * @return brokerChargeList
     */
    
    private List<IfaCustomerDestinationBankAccountSql001RequestModelBrokerCharge> getBrokerChargeListSqlReq(OutputFct030Dto fct030Dto) {
        List<IfaCustomerDestinationBankAccountSql001RequestModelBrokerCharge> brokerChargeList = new ArrayList<IfaCustomerDestinationBankAccountSql001RequestModelBrokerCharge>();
        if (fct030Dto != null && !CollectionUtils.isEmpty(fct030Dto.getBrokerChargeList())) {
            for (BrokerCharge fctRes : fct030Dto.getBrokerChargeList()) {
                IfaCustomerDestinationBankAccountSql001RequestModelBrokerCharge brokerCharge = new IfaCustomerDestinationBankAccountSql001RequestModelBrokerCharge();
                brokerCharge.setBrokerCode(fctRes.getBrokerCode());
                brokerCharge.setEmpCode(fctRes.getEmpCode());
                brokerChargeList.add(brokerCharge);
            }
        }
        return brokerChargeList;
    }
    
    /**
     * 顧客振込先金融機関口座リストの設定
     *
     * @param sql001Res SQL001結果
     * @return 顧客振込先金融機関口座リスト
     */
    
    private List<IfaCustomerDestinationBankAccountDtoResponseCustomerDestinationBankAccount> setCustomerDestinationBankAccountList(
            DataList<IfaCustomerDestinationBankAccountSql001ResponseModel> sql001Res) {
    
        List<IfaCustomerDestinationBankAccountDtoResponseCustomerDestinationBankAccount> customerDestinationBankAccountList = new ArrayList<>();
        
        for (IfaCustomerDestinationBankAccountSql001ResponseModel sqlRes : sql001Res.getDataList()) {
            IfaCustomerDestinationBankAccountDtoResponseCustomerDestinationBankAccount 
                    customerDestinationBankAccount = new 
                    IfaCustomerDestinationBankAccountDtoResponseCustomerDestinationBankAccount();
    
            customerDestinationBankAccount.setBrokerName(sqlRes.getBrokerName());
            customerDestinationBankAccount.setBrokerChargeCode(sqlRes.getBrokerChargeCode());
            customerDestinationBankAccount.setEmployeeName(sqlRes.getEmployeeName());
            customerDestinationBankAccount.setButenCode(sqlRes.getButenCode());
            customerDestinationBankAccount.setAccountNumber(sqlRes.getAccountNumber());
            customerDestinationBankAccount.setCustomerAttributeName(sqlRes.getCustomerAttributeName());
            customerDestinationBankAccount.setCustomerNameKanji(sqlRes.getCustomerNameKanji());
            customerDestinationBankAccount.setCustomerNameKana(sqlRes.getCustomerNameKana());
            customerDestinationBankAccount.setBankNameKanji(sqlRes.getBankNameKanji());
            customerDestinationBankAccount.setBranchNameKanji(sqlRes.getBranchNameKanji());
            customerDestinationBankAccount.setDepositType(sqlRes.getDepositType());
            customerDestinationBankAccount.setDestinationAccountNumber(sqlRes.getDestinationAccountNumber());
            customerDestinationBankAccount.setBrokerCode(sqlRes.getBrokerCode());
            customerDestinationBankAccount.setBrokerageBranchCode(sqlRes.getBrokerageBranchCode());
            customerDestinationBankAccount.setBranchNameOfBroker(sqlRes.getBranchNameOfBroker());
    
            customerDestinationBankAccountList.add(customerDestinationBankAccount);
        }
        return customerDestinationBankAccountList;
    }

}
