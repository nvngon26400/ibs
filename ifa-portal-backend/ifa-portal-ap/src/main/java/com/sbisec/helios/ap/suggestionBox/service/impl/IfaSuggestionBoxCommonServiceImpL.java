package com.sbisec.helios.ap.suggestionBox.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.MCode;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.service.MCodeService;
import com.sbisec.helios.ap.suggestionBox.dao.IfaSuggestionBoxCommonDao;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonSql002RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonSql002ResponseModel;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonA001DtoRequest;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonA001DtoResponse;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonA002DtoRequest;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonA002DtoResponse;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonA006CsvItem;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonA006aDtoRequest;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonA006aDtoResponse;
import com.sbisec.helios.ap.suggestionBox.service.IfaSuggestionBoxCommonService;
import com.sbisec.helios.ap.suggestionBox.util.IfaSuggestionBoxCommonCsvOut;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;


@Component(value = "cmpIfaSuggestionBoxCommonService")
public class IfaSuggestionBoxCommonServiceImpL implements IfaSuggestionBoxCommonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaSuggestionBoxCommonServiceImpL.class);

    // --------------------------------
    // メッセージ
    // --------------------------------

    /** コードテーブル.種別.画面コメント */
    private static final String M_CODE_CODE_TYPE_SCREEN_COMMENT = "99";

    /** コードテーブル.CODE_1.画面コメント.デフォルト */
    private static final String M_CODE_CODE_1_SCREEN_COMMENT_DEFAULT = "01";

    /** コードテーブル.CODE_2.画面コメント.皆様からの要望画面　コメント */
    private static final String M_CODE_CODE_2_SCREEN_COMMENT_SUGBOX_COMMON= "29";

    /** 画面項目.ステータス.全て */
    private static final String STATUS_ALL= " ";

    /**
     * 共通関数Function001クラス
     */
    @Autowired
    MCodeService mcodeService;

    @Autowired
    private IfaSuggestionBoxCommonDao dao;

    @Autowired
    private Fct038 fct038;

    @Autowired
    private ComplianceService complianceService;

    /** FCT038 画面ID*/
    private static final String SCREEN_ID = "SUB0511_02-01";

    /** 最大データリストサイズ. */
    private static final int MAX_ROW_NUM = 5000;


    /** 検索結果が0件です。\n条件を設定して再度検索して下さい。. */
    private static final String ERRORS_DATA_LIST_NOT_FOUND = "errors.dataList.notfound";

    /** 検索結果が{0}件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。. */
    private static final String WARNINGS_DATA_LIST_OVER_MAX_ROWNUM 
    = "warnings.dataList.overMaxRownum";

    /** 検索結果が{0}件を超過しています（{1}件ヒット）。\n{2}件のcsv出力を行います。*/
    private static final String WARNINGS_DATALIST_CSV_OVERMAXROWNUM = "warnings.dataList.csv.overMaxRownum";

    @Autowired
    private CodeListService codeListService;

    /** 区分.目安箱ステータス */
    private static final String SUG_BOX_STATUS = "SUG_BOX_STATUS";

    /** 区分.目安箱カテゴリ */
    private static final String SUG_BOX_CATEGORY = "SUG_BOX_CATEGORY";

    /**
     * Dto リクエスト：IfaSuggestionBoxCommonA001DtoRequest
     * Dto レスポンス：IfaSuggestionBoxCommonA001DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaSuggestionBoxCommonA001DtoResponse> initializeA001(
            IfaSuggestionBoxCommonA001DtoRequest dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSuggestionBoxCommonServiceImpL.initializeA001");
        }

        IfaSuggestionBoxCommonA001DtoResponse dtoResponse = new IfaSuggestionBoxCommonA001DtoResponse();

        List<MCode> sql001ResultList = mcodeService.getMCodeList(
                M_CODE_CODE_TYPE_SCREEN_COMMENT,
                M_CODE_CODE_1_SCREEN_COMMENT_DEFAULT,
                M_CODE_CODE_2_SCREEN_COMMENT_SUGBOX_COMMON
                );

        // 画面infoコメントをレスポンスにセットする
        if (sql001ResultList != null && sql001ResultList.size() > 0) {
            if(!StringUtils.isEmpty(sql001ResultList.get(0).getName())) {
                dtoResponse.setSugBoxCommonScreenComment(sql001ResultList.get(0).getName());
            }
        }

        // ===============================================
        // 明細情報取得
        // ===============================================
        //SQLリクエストを作成
        IfaSuggestionBoxCommonSql002RequestModel sql002Req = new IfaSuggestionBoxCommonSql002RequestModel();

        if(!StringUtils.equals(dtoReq.getStatus(), STATUS_ALL)){
            sql002Req.setStatus(dtoReq.getStatus());
        }

        if(dtoReq.getTitle().length() > 0) {
            sql002Req.setTitle(dtoReq.getTitle());
        }
        sql002Req.setRegisterDateFrom(dtoReq.getRegisterDateFrom());
        sql002Req.setRegisterDateTo(dtoReq.getRegisterDateTo());
        sql002Req.setMaxRowNum(MAX_ROW_NUM);

        //SQL実行結果
        List<IfaSuggestionBoxCommonSql002ResponseModel> sql002ResultList = dao.selectIfaSuggestionBoxCommonSql002(sql002Req).getDataList();

        // レスポンスDTODataList
        DataList<IfaSuggestionBoxCommonA001DtoResponse> dtoResDataList = new DataList<IfaSuggestionBoxCommonA001DtoResponse>();
        // レスポンスＤＴＯList
        List<IfaSuggestionBoxCommonA001DtoResponse> dtoResponseList = new ArrayList<IfaSuggestionBoxCommonA001DtoResponse>();

        // 結果が0件の場合　0件エラーを出力
        if (sql002ResultList.isEmpty() || sql002ResultList.size() == 0) {
            //  画面infoコメント のみ出力
            dtoResponseList.add(dtoResponse);

            dtoResDataList = IfaCommonUtil.createDataList(
                    dtoResponseList,
                    ErrorLevel.INFO,
                    ERRORS_DATA_LIST_NOT_FOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATA_LIST_NOT_FOUND)
                    );
            return dtoResDataList;
        }

        // SQL呼び出し結果をレスポンスDTOに設定
        dtoResponse.setSuggestionList(sql002ResultList);
        // レスポンスDTOをレスポンスDTOListに格納
        dtoResponseList.add(dtoResponse);

        int totalRow = sql002ResultList.get(0).getTotalCount();

        // 件数が5001件以上の場合ワーニングを返す
        if (MAX_ROW_NUM < totalRow) {
            dtoResDataList = IfaCommonUtil.createDataList(
                    dtoResponseList,
                    ErrorLevel.WARNING,
                    WARNINGS_DATA_LIST_OVER_MAX_ROWNUM
                    , IfaCommonUtil.getMessage(WARNINGS_DATA_LIST_OVER_MAX_ROWNUM,
                            new String[] { String.valueOf(MAX_ROW_NUM), String.valueOf(totalRow) }));
        } else {
            // レスポンスDTOListからレスポンスDTODataListを作成
            dtoResDataList = IfaCommonUtil.createDataList(dtoResponseList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
        }

        // 画面側へレスポンスDTODataListを返却
        return dtoResDataList;

    }


    /**
     * Dto リクエスト：IfaSuggestionBoxCommonA002DtoRequest
     * Dto レスポンス：IfaSuggestionBoxCommonA002DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 表示処理で例外が発生した場合
     */
    public DataList<IfaSuggestionBoxCommonA002DtoResponse> displayA002(
            IfaSuggestionBoxCommonA002DtoRequest dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSuggestionBoxCommonServiceImpL.displayA002");
        }

        // ===============================================
        // 明細情報取得
        // ===============================================
        //SQLリクエストを作成
        IfaSuggestionBoxCommonSql002RequestModel sql002Req = new IfaSuggestionBoxCommonSql002RequestModel();

        if(!StringUtils.equals(dtoReq.getStatus(), STATUS_ALL)){
            sql002Req.setStatus(dtoReq.getStatus());
        }

        if(dtoReq.getTitle().length() > 0) {
            sql002Req.setTitle(dtoReq.getTitle());
        }
        sql002Req.setRegisterDateFrom(dtoReq.getRegisterDateFrom());
        sql002Req.setRegisterDateTo(dtoReq.getRegisterDateTo());
        sql002Req.setMaxRowNum(MAX_ROW_NUM);

        //SQL実行結果
        List<IfaSuggestionBoxCommonSql002ResponseModel> sql002ResultList = dao.selectIfaSuggestionBoxCommonSql002(sql002Req).getDataList();

        // レスポンスDTODataList
        DataList<IfaSuggestionBoxCommonA002DtoResponse> dtoResDataList = new DataList<IfaSuggestionBoxCommonA002DtoResponse>();
        // レスポンスＤＴＯList
        List<IfaSuggestionBoxCommonA002DtoResponse> dtoResponseList = new ArrayList<IfaSuggestionBoxCommonA002DtoResponse>();


        // 結果が0件の場合　0件エラーを出力
        if (sql002ResultList.isEmpty() || sql002ResultList.size() == 0) {
            dtoResDataList = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.INFO,
                    ERRORS_DATA_LIST_NOT_FOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATA_LIST_NOT_FOUND)
                    );
            return dtoResDataList;
        }

        IfaSuggestionBoxCommonA002DtoResponse dtoResponse = new IfaSuggestionBoxCommonA002DtoResponse();

        // SQL呼び出し結果をレスポンスDTOに設定
        dtoResponse.setSuggestionList(sql002ResultList);
        // レスポンスDTOをレスポンスDTOListに格納
        dtoResponseList.add(dtoResponse);

        int totalRow = sql002ResultList.get(0).getTotalCount();

        // 件数が5001件以上の場合ワーニングを返す
        if (MAX_ROW_NUM < totalRow) {
            dtoResDataList = IfaCommonUtil.createDataList(
                    dtoResponseList,
                    ErrorLevel.WARNING,
                    WARNINGS_DATA_LIST_OVER_MAX_ROWNUM
                    , IfaCommonUtil.getMessage(WARNINGS_DATA_LIST_OVER_MAX_ROWNUM,
                            new String[] { String.valueOf(MAX_ROW_NUM), String.valueOf(totalRow) }));
        } else {
            // レスポンスDTOListからレスポンスDTODataListを作成
            dtoResDataList = IfaCommonUtil.createDataList(dtoResponseList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
        }

        // 画面側へレスポンスDTODataListを返却
        return dtoResDataList;

    }



    /**
     * Dto リクエスト：IfaSuggestionBoxCommonA006DtoRequest
     * Dto レスポンス：IfaSuggestionBoxCommonA006DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception CSV出力処理で例外が発生した場合
     */
    public DataList<IfaSuggestionBoxCommonA006aDtoResponse> csvOutputA006(
            IfaSuggestionBoxCommonA006aDtoRequest dtoReq, String fwSessionId) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSuggestionBoxCommonServiceImpL.csvOutputA006");
        }

        // ===============================================
        // CSVダウンロードMAX件数取得
        // ===============================================
        InputFct038Dto inputFct038Dto = new InputFct038Dto();
        inputFct038Dto.setScreenId(SCREEN_ID);
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        inputFct038Dto.setUserAuthority(userAccount.getPrivId());
        OutputFct038Dto outputFct038Dto = fct038.getData(inputFct038Dto);    
        int csvMaxLimitNum = outputFct038Dto.getCsvDownloadNum();

        // ===============================================
        // 明細情報取得
        // ===============================================
        //SQLリクエストを作成
        IfaSuggestionBoxCommonSql002RequestModel sql002Req = new IfaSuggestionBoxCommonSql002RequestModel();

        if(!StringUtils.equals(dtoReq.getStatus(), STATUS_ALL)){
            sql002Req.setStatus(dtoReq.getStatus());
        }

        if(dtoReq.getTitle().length() > 0) {
            sql002Req.setTitle(dtoReq.getTitle());
        }
        sql002Req.setRegisterDateFrom(dtoReq.getRegisterDateFrom());
        sql002Req.setRegisterDateTo(dtoReq.getRegisterDateTo());
        // 上限にはFCT038で取得した値を使用
        sql002Req.setMaxRowNum(csvMaxLimitNum);

        //SQL実行結果
        List<IfaSuggestionBoxCommonSql002ResponseModel> sql002ResultList = dao.selectIfaSuggestionBoxCommonSql002(sql002Req).getDataList();

        // レスポンスDTODataList
        DataList<IfaSuggestionBoxCommonA006aDtoResponse> dtoResDataList = new DataList<IfaSuggestionBoxCommonA006aDtoResponse>();
        // レスポンスＤＴＯList
        List<IfaSuggestionBoxCommonA006aDtoResponse> dtoResponseList = new ArrayList<IfaSuggestionBoxCommonA006aDtoResponse>();

        // ===============================================
        // 検索結果検証
        // ===============================================        
        // 結果が0件の場合　0件エラーを出力
        if (sql002ResultList.isEmpty() || sql002ResultList.size() == 0) {
            dtoResDataList = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.INFO,
                    ERRORS_DATA_LIST_NOT_FOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATA_LIST_NOT_FOUND)
                    );
            return dtoResDataList;
        }

        IfaSuggestionBoxCommonA006aDtoResponse dtoResponse = new IfaSuggestionBoxCommonA006aDtoResponse();

        // SQL呼び出し結果をレスポンスDTOに設定
        dtoResponse.setSuggestionList(sql002ResultList);//(sql002ResultList);
        // レスポンスDTOをレスポンスDTOListに格納
        dtoResponseList.add(dtoResponse);

        int totalRow = sql002ResultList.get(0).getTotalCount();

        // 件数がCSV上限件以上の場合ワーニングを返す
        if (csvMaxLimitNum < totalRow) {
            dtoResDataList = IfaCommonUtil.createDataList(
                    dtoResponseList,
                    ErrorLevel.WARNING,
                    WARNINGS_DATALIST_CSV_OVERMAXROWNUM
                    , IfaCommonUtil.getMessage(WARNINGS_DATALIST_CSV_OVERMAXROWNUM,
                            new String[] { String.valueOf(csvMaxLimitNum), String.valueOf(totalRow),String.valueOf(csvMaxLimitNum) }));

            dtoResDataList.setMaxRownum(csvMaxLimitNum);
            dtoResDataList.setTotalSize(totalRow);

        } else {
            // レスポンスDTOListからレスポンスDTODataListを作成
            dtoResDataList = IfaCommonUtil.createDataList(dtoResponseList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
        }

        List<IfaSuggestionBoxCommonA006CsvItem> csvItemList = new ArrayList<>();
        for(IfaSuggestionBoxCommonSql002ResponseModel sql002Result : sql002ResultList) {

            // SQLの結果をcsv用クラスへ振替え
            IfaSuggestionBoxCommonA006CsvItem csvItem = setCsvItem(sql002Result);

            csvItemList.add(csvItem);
        }

        // CSV出力
        DataList<IfaSuggestionBoxCommonA006CsvItem> csvDataList = new DataList<>();
        csvDataList.setDataList(csvItemList);

        CsvOutPutUtil csvOutPutUtil = new IfaSuggestionBoxCommonCsvOut(complianceService);

        String title = csvOutPutUtil.doCreateCsvFile(csvDataList, fwSessionId,
                IfaCommonUtil.getUserAccount().getUserId(), null);

        dtoResDataList.setTitle(title);

        // 画面側へレスポンスDTODataListを返却
        return dtoResDataList;

    }

    private IfaSuggestionBoxCommonA006CsvItem setCsvItem(IfaSuggestionBoxCommonSql002ResponseModel sql002Result) {

        IfaSuggestionBoxCommonA006CsvItem csvItem = new IfaSuggestionBoxCommonA006CsvItem();

        csvItem.setSbcNo(sql002Result.getSbcNo());
        csvItem.setUpdateDate(sql002Result.getUpdateDate());
        csvItem.setCreateDate(sql002Result.getCreateDate());
        csvItem.setTitle(sql002Result.getTitle());

        // 区分.目安箱カテゴリ より表示パターン1の区分値を取得
        csvItem.setCategory(codeListService.getValue(SUG_BOX_CATEGORY, sql002Result.getCategory()));
        // 区分.目安箱ステータス より表示パターン1の区分値を取得
        csvItem.setStatus(codeListService.getValue(SUG_BOX_STATUS, sql002Result.getStatus()));

        csvItem.setSuggestion(sql002Result.getSuggestion());


        return csvItem;

    }

}
