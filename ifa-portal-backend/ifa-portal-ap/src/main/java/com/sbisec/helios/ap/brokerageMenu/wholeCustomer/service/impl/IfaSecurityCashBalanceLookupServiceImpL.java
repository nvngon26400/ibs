package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaSecurityCashBalanceLookupDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaSecurityCashBalanceLookupSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaSecurityCashBalanceLookupSql001RequestModelBrokerCharge;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaSecurityCashBalanceLookupSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaSecurityCashBalanceLookupSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaSecurityCashBalanceLookupA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaSecurityCashBalanceLookupA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaSecurityCashBalanceLookupA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaSecurityCashBalanceLookupA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaSecurityCashBalanceLookupA006DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaSecurityCashBalanceLookupA006DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaSecurityCashBalanceLookupCsvItems;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaSecurityCashBalanceLookupSelectedDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.IfaSecurityCashBalanceLookupService;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util.IfaSecurityCashBalanceLookupCsvOut;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.DateFormatUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB020302_0301-01
 * 画面名：証券・金銭・残高照会
 *
 * @author SCSK濱田
 2024/05/07 新規作成
 */
/**
 * @author SCSK
 *
 */
@Component(value = "cmpIfaSecurityCashBalanceLookupService")
public class IfaSecurityCashBalanceLookupServiceImpL implements IfaSecurityCashBalanceLookupService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaSecurityCashBalanceLookupServiceImpL.class);
	
    @Autowired
    private IfaSecurityCashBalanceLookupDao dao;

    @Autowired
    private Fct030 fct030;

    @Autowired
    private Fct038 fct038;

    @Autowired
    private ComplianceService complianceService;

    @Autowired
    private CodeListService codeListService;

    /** エラーメッセージID：参照可能な仲介業者コード／営業員コードが存在しない */
    private static final String ERRORS_NO_EXIST = "errors.cmn.ifaAgentCodes.notExist";

    /** エラーメッセージID：取得結果が0件（NULL）エラー */
    private static final String ERROES_DATALIST_NOTFOUND = "errors.dataList.notfound";

    /** エラーメッセージID：取得件数上限ワーニング */
    private static final String WARNINGS_DATALIST_OVER_MAX_ROWNUM = "warnings.dataList.overMaxRownum";

    /** ワーニングメッセージID：CSV取得件数上限ワーニング */
    private static final String WARNINGS_DATALIST_CSV_OVER_MAX_ROWNUM = "warnings.dataList.csv.overMaxRownum";


    /** SBI証券本店*/
    private static final String PRIVE_ID_SBI_HEADOFFICE = "1";

    /** 取得件数上限 */
    private static final Integer MAX_ROWNUM = 5000;

    /** 画面ID */
    private static final String SCREEN_ID = "SUB020302_0301-01";

    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaSecurityCashBalanceLookupA001DtoRequest
     * Dto レスポンス：IfaSecurityCashBalanceLookupA001DtoResponse
     * model リクエスト：IfaSecurityCashBalanceLookupA001RequestModel
     * model レスポンス：IfaSecurityCashBalanceLookupA001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return dtoRes レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSecurityCashBalanceLookupA001DtoResponse> initializeA001(IfaSecurityCashBalanceLookupA001DtoRequest dtoReq)
            throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSecurityCashBalanceLookupServiceImplL.initializeA001");
        }
        
        DataList<IfaSecurityCashBalanceLookupA001DtoResponse> dtoRes = new DataList<IfaSecurityCashBalanceLookupA001DtoResponse>();
        IfaSecurityCashBalanceLookupA001DtoResponse dtoResData = new IfaSecurityCashBalanceLookupA001DtoResponse();
        
        // コードマスタより、証券・金銭・残高照会画面コメントを取得する。
        DataList<IfaSecurityCashBalanceLookupSql004ResponseModel> selSql004Res = dao.selectIfaSecurityCashBalanceLookupSql004();
        if (selSql004Res != null && selSql004Res.getDataList().size() != 0) {
            dtoResData.setComment(selSql004Res.get(0).getComment());
        } else {
            dtoResData.setComment(null);
        }
        
        dtoRes = IfaCommonUtil.createDataList(Arrays.asList(dtoResData), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), "");
        return dtoRes;
    }

    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaSecurityCashBalanceLookupA002DtoRequest
     * Dto レスポンス：IfaSecurityCashBalanceLookupA002DtoResponse
     * model リクエスト：IfaSecurityCashBalanceLookupA002RequestModel
     * model レスポンス：IfaSecurityCashBalanceLookupA002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return dtoRes レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSecurityCashBalanceLookupA002DtoResponse> displayA002(IfaSecurityCashBalanceLookupA002DtoRequest dtoReq)
            throws Exception {
                
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSecurityCashBalanceLookupServiceImplL.displayA002");
        }
        DataList<IfaSecurityCashBalanceLookupA002DtoResponse> dtoRes = new DataList<IfaSecurityCashBalanceLookupA002DtoResponse>();
        
        // ユーザアカウント情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();

        OutputFct030Dto fct030Dto = null;

        // 参照可能な仲介業者コードチェック
        // ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合：
        if (!PRIVE_ID_SBI_HEADOFFICE.equals(ua.getPrivId())) {

            // FCT030を呼び出して、参照可能な仲介業者コードと営業員コードを取得する。
            InputFct030Dto fct030InData = new InputFct030Dto();
            fct030Dto = fct030.getData(fct030InData);

            if (fct030Dto.getBrokerChargeList().size() == 0) {
                dtoRes = IfaCommonUtil.createDataList(
                        new ArrayList<>(), 
                        ErrorLevel.FATAL,
                        ERRORS_NO_EXIST,
                        IfaCommonUtil.getMessage(ERRORS_NO_EXIST)
                );
                return dtoRes;
            }
        }

        // 検索条件入力項目で証券・金銭・残高情報を検索する。      
        DataList<IfaSecurityCashBalanceLookupSql001ResponseModel> selSql001Res = getSecurityCashBalance(dtoReq, fct030Dto, MAX_ROWNUM);

        // SQL001.総件数が0件の場合、エラーメッセージを表示し、処理終了。
        if (selSql001Res != null && selSql001Res.getDataList().size() == 0) {
            dtoRes = IfaCommonUtil.createDataList(
                new ArrayList<>(),
                ErrorLevel.INFO,
                ERROES_DATALIST_NOTFOUND,
                IfaCommonUtil.getMessage(ERROES_DATALIST_NOTFOUND));
            return dtoRes;
        }

        // SQL001.総件数が出力件数上限（5000件）を超過している場合、メッセージを表示し、5000件までの検索結果を明細に表示する。
        if (selSql001Res != null && Integer.valueOf(selSql001Res.get(0).getTotalRow()) > MAX_ROWNUM) {
            dtoRes = IfaCommonUtil.createDataList(
                new ArrayList<>(),
                ErrorLevel.WARNING,
                WARNINGS_DATALIST_OVER_MAX_ROWNUM,
                IfaCommonUtil.getMessage(WARNINGS_DATALIST_OVER_MAX_ROWNUM, new String[]{Integer.toString(MAX_ROWNUM),selSql001Res.get(0).getTotalRow()}));
        }

        // データをレスポンスにコピー
        List<IfaSecurityCashBalanceLookupA002DtoResponse> dtoResDataList = new ArrayList<>();
        for (IfaSecurityCashBalanceLookupSql001ResponseModel sql001ResData : selSql001Res.getDataList()) {
            IfaSecurityCashBalanceLookupA002DtoResponse dtoResData = new IfaSecurityCashBalanceLookupA002DtoResponse();
            BeanUtils.copyProperties(dtoResData, sql001ResData);
            
            // 物理名が異なる項目をコピー
            // 預り区分
            dtoResData.setDepositType(sql001ResData.getDepositName());
            // 現在値
            dtoResData.setCurrentPrice(sql001ResData.getAppraiseMarketPrice());
            // 評価額（円貨）
            dtoResData.setValuation(sql001ResData.getCurrentPrice());
            // リストにセット
            dtoResDataList.add(dtoResData);
            
            // 要素数が上限に達したらループを抜ける
            if (dtoResDataList.size() == MAX_ROWNUM) {
                break;
            }
        }


        // 警告メッセージが未設定であればSUCCESSをセット
        if (dtoRes.getReturnCode() == null) {
            dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), "");
        }

        dtoRes.setDataList(dtoResDataList);
        return dtoRes;
    }

    /**
     * アクションID：A006
     * アクション名：CSV出力
     * Dto リクエスト：IfaSecurityCashBalanceLookupA006DtoRequest
     * Dto レスポンス：IfaSecurityCashBalanceLookupA006DtoResponse
     * model リクエスト：IfaSecurityCashBalanceLookupA006RequestModel
     * model レスポンス：IfaSecurityCashBalanceLookupA006ResponseModel
     *
     * @param dtoReq リクエスト
     * @return dtoRes レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSecurityCashBalanceLookupA006DtoResponse> csvOutputA006(
                IfaSecurityCashBalanceLookupA006DtoRequest dtoReq, String fwSessionId)
            throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSecurityCashBalanceLookupServiceImplL.csvOutputA006");
        }
        DataList<IfaSecurityCashBalanceLookupA006DtoResponse> dtoRes = new DataList<IfaSecurityCashBalanceLookupA006DtoResponse>();

        // ユーザアカウント情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();

        OutputFct030Dto fct030Dto = null;

        // 参照可能な仲介業者コードチェック
        // ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合：
        if (!PRIVE_ID_SBI_HEADOFFICE.equals(ua.getPrivId())) {

            // FCT030を呼び出して、参照可能な仲介業者コードと営業員コードを取得する。
            InputFct030Dto fct030InData = new InputFct030Dto();
            fct030Dto = fct030.getData(fct030InData);

            if (fct030Dto.getBrokerChargeList().size() == 0) {
                dtoRes = IfaCommonUtil.createDataList(
                        new ArrayList<>(), 
                        ErrorLevel.FATAL, ERRORS_NO_EXIST,
                        IfaCommonUtil.getMessage(ERRORS_NO_EXIST)
                );
                return dtoRes;
            }
        }

        // CSVダウンロードMAX件数を取得する。
        InputFct038Dto fct038InData = new InputFct038Dto();
        fct038InData.setScreenId(SCREEN_ID);
        fct038InData.setUserAuthority(ua.getPrivId());
        
        OutputFct038Dto fct038Dto = fct038.getData(fct038InData);
        // CSVダウンロードMAX件数
        int maxCsvRowNum = fct038Dto.getCsvDownloadNum();

        // 検索条件入力項目で証券・金銭・残高情報を検索する。      
        DataList<IfaSecurityCashBalanceLookupSql001ResponseModel> selSql001Res = getSecurityCashBalance(dtoReq, fct030Dto, maxCsvRowNum);

        // SQL001.総件数が0件の場合、エラーメッセージを表示し、処理終了。
        if (selSql001Res != null && selSql001Res.getDataList().size() == 0) {
            dtoRes = IfaCommonUtil.createDataList(
                new ArrayList<>(),
                ErrorLevel.INFO,
                ERROES_DATALIST_NOTFOUND,
                IfaCommonUtil.getMessage(ERROES_DATALIST_NOTFOUND));
            return dtoRes;
        }

        Integer totalRow = Integer.valueOf(selSql001Res.get(0).getTotalRow());
        Integer totalSize = totalRow;
        
        // SQL001.総件数がCSVダウンロードMAX件数を超える場合、メッセージを表示し、CSVダウンロードMAX件数までの検索結果をCSV出力する。
        if (selSql001Res != null && totalRow > maxCsvRowNum) {
            String msgTotalRow = Integer.toString(totalRow);
            totalSize = maxCsvRowNum;
            dtoRes = IfaCommonUtil.createDataList(
                new ArrayList<>(),
                ErrorLevel.WARNING,
                WARNINGS_DATALIST_CSV_OVER_MAX_ROWNUM,
                IfaCommonUtil.getMessage(WARNINGS_DATALIST_CSV_OVER_MAX_ROWNUM, new String[]{Integer.toString(maxCsvRowNum), msgTotalRow, Integer.toString(maxCsvRowNum)}));
        }

        // pattern = "0"(全項目表示)
        // pattern = "1"(返還期限,外債評価基準日,債権種別 非表示)
        // pattern = "2"(返還期限 非表示)
        // pattern = "3"(外債評価基準日,債権種別 非表示)
        String pattern = "0";
        Boolean isId14Selected = dtoReq.getSecurityTypeSecuritiesMoney().stream()
                .anyMatch(obj -> "14".equals(obj.getId()) && obj.getIsSelected());
        Boolean isSecurityTypeCreditFuturesOpSelected = checkSelected(dtoReq.getSecurityTypeCreditFuturesOp());
        if (!isId14Selected && !isSecurityTypeCreditFuturesOpSelected) {
            pattern = "1";
        } else if (!isSecurityTypeCreditFuturesOpSelected) {
            pattern = "2";
        } else if (!isId14Selected) {
            pattern = "3";
        }

        // CSV出力する
        List<IfaSecurityCashBalanceLookupCsvItems> csvItemList = new ArrayList<>();
        for (IfaSecurityCashBalanceLookupSql001ResponseModel selSql001ResData : selSql001Res.getDataList()) {
            IfaSecurityCashBalanceLookupCsvItems csvItem = new IfaSecurityCashBalanceLookupCsvItems();
            BeanUtils.copyProperties(csvItem, selSql001ResData);
            
            // 物理名が異なる項目をコピー
            // 預り区分
            csvItem.setDepositType(selSql001ResData.getDepositName());
            // 現在値
            csvItem.setCurrentPrice(selSql001ResData.getAppraiseMarketPrice());
            // 評価額（円貨）
            csvItem.setValuation(selSql001ResData.getCurrentPrice());
            
            // フォーマット変換
            // 外債評価基準日をYYYY/MM/DD形式に
            if (DateFormatUtil.convertDateString(selSql001ResData.getForeignStandardDate()).equals("-")) {
                csvItem.setForeignStandardDate("");
            } else {
                csvItem.setForeignStandardDate(DateFormatUtil.convertDateString(selSql001ResData.getForeignStandardDate())); 
            }         
            
            // 仕組債区分を変換
            csvItem.setStructuredBondClassification(
                    codeListService.getValue("STRUCTURED_BOND_TYPE", selSql001ResData.getStructuredBondClassification()));
            
            // リストにセット
            csvItemList.add(csvItem);

            // 要素数が上限に達したらループを抜ける
            if (csvItemList.size() == maxCsvRowNum) {
                break;
            }
        }


        DataList<IfaSecurityCashBalanceLookupCsvItems> csvDataList = new DataList<>();
        csvDataList.setDataList(csvItemList);

        IfaSecurityCashBalanceLookupCsvOut csvOut = new IfaSecurityCashBalanceLookupCsvOut(complianceService);
        String csvFileName = csvOut.doCreateCsvFile(csvDataList, fwSessionId, ua.getUserId(), pattern);
        
        if (dtoRes.getReturnCode() == null) {
            dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), "");
        }

        // CSVファイル作成
        // レスポンスにパターンを設定
        IfaSecurityCashBalanceLookupA006DtoResponse innerData = new IfaSecurityCashBalanceLookupA006DtoResponse();
        innerData.setPattern(pattern);
        dtoRes.setDataList(Arrays.asList(innerData));

        // レスポンスのタイトルにCSVファイル名を設定
        dtoRes.setTitle(csvFileName);
        dtoRes.setTotalSize(totalSize);

        return dtoRes;
    }


    /**
     * SQL001のリクエストをセットし、実行
     * レスポンスは抽出結果を返す
     *
     * @param <T> リクエスト型
     * @param dtoReq リクエスト
     * @param fct030Dto FCT030で取得した結果
     * @param rownum 上限桁数
     * @return SQL001の抽出結果
     * @throws Exception SQL001実行時の例外
     */
    private <T> DataList<IfaSecurityCashBalanceLookupSql001ResponseModel> getSecurityCashBalance(T dtoReq, OutputFct030Dto fct030Dto, Integer rownum) throws Exception {
        
        // リクエストを設定
        IfaSecurityCashBalanceLookupSql001RequestModel selSql001Req = new IfaSecurityCashBalanceLookupSql001RequestModel();
        BeanUtils.copyProperties(selSql001Req, dtoReq);

        // ユーザアカウント情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();

        // 権限コード
        selSql001Req.setPrivId(ua.getPrivId());
        
        // 仲介業者・営業員コードリスト
        List<IfaSecurityCashBalanceLookupSql001RequestModelBrokerCharge> brokerChargeList
                = new ArrayList<IfaSecurityCashBalanceLookupSql001RequestModelBrokerCharge>();
        if (fct030Dto != null && CollectionUtils.isNotEmpty(fct030Dto.getBrokerChargeList())) {
            for (BrokerCharge fct030BrokerCharge : fct030Dto.getBrokerChargeList()) {
                IfaSecurityCashBalanceLookupSql001RequestModelBrokerCharge sql001ReqBrokerCharge
                        = new IfaSecurityCashBalanceLookupSql001RequestModelBrokerCharge();
                BeanUtils.copyProperties(sql001ReqBrokerCharge, fct030BrokerCharge);
                brokerChargeList.add(sql001ReqBrokerCharge);
            }
        }
        selSql001Req.setBrokerChargeList(brokerChargeList);

        // 証券　金銭の選択チェック
        selSql001Req.setSecurityTypeSecuritiesMoneySelected(checkSelected(selSql001Req.getSecurityTypeSecuritiesMoney()));
        // 信用・先OPの選択チェック
        selSql001Req.setSecurityTypeCreditFuturesOpSelected(checkSelected(selSql001Req.getSecurityTypeCreditFuturesOp()));
        
        // 上限件数
        selSql001Req.setRownum(Integer.toString(rownum));

        // SQL001を実行
        DataList<IfaSecurityCashBalanceLookupSql001ResponseModel> selSql001Res = dao.selectIfaSecurityCashBalanceLookupSql001(selSql001Req);

        return selSql001Res;
    }

    /**
     * 選択アイテムのチェック
     *
     * @param selectedItems 選択アイテム
     * @return 1つでも選択されていればTRUE, 全て選択されていない場合FALSE
     */
    private boolean checkSelected(List<IfaSecurityCashBalanceLookupSelectedDtoRequest> selectedItems) {
        
        if (selectedItems != null) {
            for (IfaSecurityCashBalanceLookupSelectedDtoRequest selectedItem : selectedItems) {
                if (selectedItem.getIsSelected()) {
                    return true;
                }
            }
        }
        return false;
    }

}
