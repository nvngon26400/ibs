package com.sbisec.helios.ap.brokerageMenu.jointSubscript.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.IfaJointSubscriptSecurityCashBalanceLookupDao;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptSecurityCashBalanceLookupSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptSecurityCashBalanceLookupSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptSecurityCashBalanceLookupA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptSecurityCashBalanceLookupA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptSecurityCashBalanceLookupA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptSecurityCashBalanceLookupA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptSecurityCashBalanceLookupA004DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptSecurityCashBalanceLookupA004DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptSecurityCashBalanceLookupCsvItems;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptSecurityCashBalanceLookupSelectedDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.service.IfaJointSubscriptSecurityCashBalanceLookupService;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.util.IfaJointSubscriptSecurityCashBalanceLookupCsvOut;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.MCode;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.service.MCodeService;
import com.sbisec.helios.ap.common.util.DateFormatUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0206_04-01
 * 画面名：共同募集　証券・金銭・残高照会
 *
 * @author SBIえん
 2024/12/10 新規作成
 */
/**
 * @author SBIえん
 *
 */
@Component(value = "cmpIfaJointSubscriptSecurityCashBalanceLookupService")
public class IfaJointSubscriptSecurityCashBalanceLookupServiceImpL implements IfaJointSubscriptSecurityCashBalanceLookupService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaJointSubscriptSecurityCashBalanceLookupServiceImpL.class);
    
    @Autowired
    private IfaJointSubscriptSecurityCashBalanceLookupDao dao;

    @Autowired
    private Fct038 fct038;

    @Autowired
    private ComplianceService complianceService;

    @Autowired
    private CodeListService codeListService;
    
    @Autowired
    private MCodeService mcodeService;

    /** エラーメッセージID：取得結果が0件（NULL）エラー */
    private static final String ERROES_DATALIST_NOTFOUND = "errors.dataList.notfound";

    /** エラーメッセージID：取得件数上限ワーニング */
    private static final String WARNINGS_DATALIST_OVER_MAX_ROWNUM = "warnings.dataList.overMaxRownum";

    /** ワーニングメッセージID：CSV取得件数上限ワーニング */
    private static final String WARNINGS_DATALIST_CSV_OVER_MAX_ROWNUM = "warnings.dataList.csv.overMaxRownum";

    /** コードマスタテーブル.種別 （'99':画面コメント） */
    private static final String M_CODE_CODE_TYPE_SCREEN_COMMENT = "99";

    /** コードマスタテーブル.CODE_1 （'01':デフォルト） */
    private static final String M_CODE_CODE_1_SCREEN_COMMENT_DEFAULT = "01";

    /** コードマスタテーブル.CODE_2 （'09':証券・金銭・残高画面コメント） */
    private static final String M_CODE_CODE_2_SCREEN_COMMENT_TRUST_FEE = "09";

    /** 取得件数上限 */
    private static final Integer MAX_ROWNUM = 5000;

    /** 画面ID */
    private static final String SCREEN_ID = "SUB0206_04-01";

    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaJointSubscriptSecurityCashBalanceLookupA001DtoRequest
     * Dto レスポンス：IfaJointSubscriptSecurityCashBalanceLookupA001DtoResponse
     * model リクエスト：IfaJointSubscriptSecurityCashBalanceLookupA001RequestModel
     * model レスポンス：IfaJointSubscriptSecurityCashBalanceLookupA001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return dtoRes レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointSubscriptSecurityCashBalanceLookupA001DtoResponse> initializeA001(IfaJointSubscriptSecurityCashBalanceLookupA001DtoRequest dtoReq)
            throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaJointSubscriptSecurityCashBalanceLookupServiceImpL.initializeA001");
        }

        DataList<IfaJointSubscriptSecurityCashBalanceLookupA001DtoResponse> dtoRes = new DataList<IfaJointSubscriptSecurityCashBalanceLookupA001DtoResponse>();
        IfaJointSubscriptSecurityCashBalanceLookupA001DtoResponse dtoResData = new IfaJointSubscriptSecurityCashBalanceLookupA001DtoResponse();
        
        // コードマスタより、証券・金銭・残高照会画面コメントを取得する。
        List<MCode> selSql007Res = mcodeService.getMCodeList(M_CODE_CODE_TYPE_SCREEN_COMMENT,
                M_CODE_CODE_1_SCREEN_COMMENT_DEFAULT, M_CODE_CODE_2_SCREEN_COMMENT_TRUST_FEE);

        if (selSql007Res != null && selSql007Res.size() != 0 ) {
          dtoResData.setCommComment(selSql007Res.get(0).getName());
        } else {
          dtoResData.setCommComment(null);
        }

        dtoRes = IfaCommonUtil.createDataList(Arrays.asList(dtoResData), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), "");
        return dtoRes;
    }

    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaJointSubscriptSecurityCashBalanceLookupA002DtoRequest
     * Dto レスポンス：IfaJointSubscriptSecurityCashBalanceLookupA002DtoResponse
     * model リクエスト：IfaJointSubscriptSecurityCashBalanceLookupA002RequestModel
     * model レスポンス：IfaJointSubscriptSecurityCashBalanceLookupA002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return dtoRes レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointSubscriptSecurityCashBalanceLookupA002DtoResponse> displayA002(IfaJointSubscriptSecurityCashBalanceLookupA002DtoRequest dtoReq)
            throws Exception {
                
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaJointSubscriptSecurityCashBalanceLookupServiceImpL.displayA002");
        }
        DataList<IfaJointSubscriptSecurityCashBalanceLookupA002DtoResponse> dtoRes = new DataList<IfaJointSubscriptSecurityCashBalanceLookupA002DtoResponse>();

        // 検索条件入力項目で証券・金銭・残高情報を検索する。
        DataList<IfaJointSubscriptSecurityCashBalanceLookupSql001ResponseModel> selSql001Res = getSecurityCashBalance(dtoReq, MAX_ROWNUM);

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
        List<IfaJointSubscriptSecurityCashBalanceLookupA002DtoResponse> dtoResDataList = new ArrayList<>();
        for (IfaJointSubscriptSecurityCashBalanceLookupSql001ResponseModel sql001ResData : selSql001Res.getDataList()) {
            IfaJointSubscriptSecurityCashBalanceLookupA002DtoResponse dtoResData = new IfaJointSubscriptSecurityCashBalanceLookupA002DtoResponse();
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
     * アクションID：A004
     * アクション名：CSV出力
     * Dto リクエスト：IfaJointSubscriptSecurityCashBalanceLookupA004DtoRequest
     * Dto レスポンス：IfaJointSubscriptSecurityCashBalanceLookupA004DtoResponse
     * model リクエスト：IfaJointSubscriptSecurityCashBalanceLookupA004RequestModel
     * model レスポンス：IfaJointSubscriptSecurityCashBalanceLookupA004ResponseModel
     *
     * @param dtoReq リクエスト
     * @return dtoRes レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointSubscriptSecurityCashBalanceLookupA004DtoResponse> csvOutputA004(
            IfaJointSubscriptSecurityCashBalanceLookupA004DtoRequest dtoReq, String fwSessionId)
            throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaJointSubscriptSecurityCashBalanceLookupServiceImpL.csvOutputA004");
        }
        DataList<IfaJointSubscriptSecurityCashBalanceLookupA004DtoResponse> dtoRes = new DataList<IfaJointSubscriptSecurityCashBalanceLookupA004DtoResponse>();

        // ユーザアカウント情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();

        // CSVダウンロードMAX件数を取得する。
        InputFct038Dto fct038InData = new InputFct038Dto();
        fct038InData.setScreenId(SCREEN_ID);
        fct038InData.setUserAuthority(ua.getPrivId());
        
        OutputFct038Dto fct038Dto = fct038.getData(fct038InData);
        // CSVダウンロードMAX件数
        int maxCsvRowNum = fct038Dto.getCsvDownloadNum();

        // 検索条件入力項目で証券・金銭・残高情報を検索する。      
        DataList<IfaJointSubscriptSecurityCashBalanceLookupSql001ResponseModel> selSql001Res = getSecurityCashBalance(dtoReq, maxCsvRowNum);

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
        List<IfaJointSubscriptSecurityCashBalanceLookupCsvItems> csvItemList = new ArrayList<>();
        for (IfaJointSubscriptSecurityCashBalanceLookupSql001ResponseModel selSql001ResData : selSql001Res.getDataList()) {
            IfaJointSubscriptSecurityCashBalanceLookupCsvItems csvItem = new IfaJointSubscriptSecurityCashBalanceLookupCsvItems();
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

        DataList<IfaJointSubscriptSecurityCashBalanceLookupCsvItems> csvDataList = new DataList<>();
        csvDataList.setDataList(csvItemList);

        IfaJointSubscriptSecurityCashBalanceLookupCsvOut csvOut = new IfaJointSubscriptSecurityCashBalanceLookupCsvOut(complianceService);
        String csvFileName = csvOut.doCreateCsvFile(csvDataList, fwSessionId, ua.getUserId(), pattern);
        
        if (dtoRes.getReturnCode() == null) {
            dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), "");
        }

        // CSVファイル作成
        // レスポンスにパターンを設定
        IfaJointSubscriptSecurityCashBalanceLookupA004DtoResponse innerData = new IfaJointSubscriptSecurityCashBalanceLookupA004DtoResponse();
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
     * @param rownum 上限桁数
     * @return SQL001の抽出結果
     * @throws Exception SQL001実行時の例外
     */
    private <T> DataList<IfaJointSubscriptSecurityCashBalanceLookupSql001ResponseModel> getSecurityCashBalance(T dtoReq, Integer rownum) throws Exception {
        
        // リクエストを設定
        IfaJointSubscriptSecurityCashBalanceLookupSql001RequestModel selSql001Req = new IfaJointSubscriptSecurityCashBalanceLookupSql001RequestModel();
        BeanUtils.copyProperties(selSql001Req, dtoReq);

        // ユーザアカウント情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();

        // 権限コード
        selSql001Req.setPrivId(ua.getPrivId());
        // ユーザID
        selSql001Req.setUserId(ua.getUserId());
        
        // 証券　金銭の選択チェック
        selSql001Req.setSecurityTypeSecuritiesMoneySelected(checkSelected(selSql001Req.getSecurityTypeSecuritiesMoney()));
        // 信用・先OPの選択チェック
        selSql001Req.setSecurityTypeCreditFuturesOpSelected(checkSelected(selSql001Req.getSecurityTypeCreditFuturesOp()));
        
        // 上限件数
        selSql001Req.setRownum(Integer.toString(rownum));

        // SQL001を実行
        DataList<IfaJointSubscriptSecurityCashBalanceLookupSql001ResponseModel> selSql001Res = dao.selectIfaJointSubscriptSecurityCashBalanceLookupSql001(selSql001Req);

        return selSql001Res;
    }

    /**
     * 選択アイテムのチェック
     *
     * @param selectedItems 選択アイテム
     * @return 1つでも選択されていればTRUE, 全て選択されていない場合FALSE
     */
    private boolean checkSelected(List<IfaJointSubscriptSecurityCashBalanceLookupSelectedDtoRequest> selectedItems) {
        
        if (selectedItems != null) {
            for (IfaJointSubscriptSecurityCashBalanceLookupSelectedDtoRequest selectedItem : selectedItems) {
                if (selectedItem.getIsSelected()) {
                    return true;
                }
            }
        }
        return false;
    }

}
