package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaKnockInKnockOutBrandHoldingListDao;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.beanutils.BeanUtils;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaKnockInKnockOutBrandHoldingListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaKnockInKnockOutBrandHoldingListSql001RequestModelBrokerCharge;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaKnockInKnockOutBrandHoldingListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaKnockInKnockOutBrandHoldingListSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaKnockInKnockOutBrandHoldingListSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaKnockInBrandHoldingListA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaKnockInBrandHoldingListA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaKnockInBrandHoldingListA002DtoResponseKnockInBrandHoldingList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaKnockInBrandHoldingListA004aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.IfaKnockInBrandHoldingListService;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;

/**
 * 画面ID：SUB020301_03-02
 * 画面名：ノックイン銘柄保有一覧
 *
 * @author SCSK 大崎
 2024/06/12 新規作成
 */
@Component(value = "cmpIfaKnockInBrandHoldingListService")
public class IfaKnockInBrandHoldingListServiceImpL implements IfaKnockInBrandHoldingListService {
    
    // --------------------------------
    // メッセージ
    // --------------------------------

    /** "参照可能な仲介業者コード／営業員コードが存在しません。" */
    private static final String ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST = "errors.cmn.ifaAgentCodes.notExist";

    /** "検索結果が0件です。\n条件を設定して再度検索して下さい。" */
    private static final String ERRORS_DATALIST_NOTFOUNT = "errors.dataList.notfound";

    /** "検索結果が{0}件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。"  */
    private static final String WARNINGS_DATALIST_OVER_MAX_ROWNUM = "warnings.dataList.overMaxRownum";

    // --------------------------------
    // 変数定義
    // -------------------------------- 

    /** 権限コード：SBI証券本店 の場合 */    
    private static final String AUTH_CODE_SBI = "1";

    /** 最大データリストサイズ */
    private static final int MAX_ROW_NUM = 5000;
    
    /** ダウンロード先 機能ID */
    private static final String FUNC_ID = "M12";
    
    /** ダウンロード先 カテゴリID */
    private static final String CAT_ID = "0";

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaKnockInBrandHoldingListServiceImpL.class);

    @Autowired
    private Fct030 fct030;

    @Autowired
    private IfaKnockInKnockOutBrandHoldingListDao dao;
    

    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaKnockInBrandHoldingListA002DtoRequest
     * Dto レスポンス：IfaKnockInBrandHoldingListA002DtoResponse
     * model リクエスト：IfaKnockInKnockOutBrandHoldingListSql001RequestModel
     * model レスポンス：IfaKnockInKnockOutBrandHoldingListSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaKnockInBrandHoldingListA002DtoResponse> displayA002(IfaKnockInBrandHoldingListA002DtoRequest dtoReq)
            throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaKnockInBrandHoldingListServiceImplL.displayA002");
        }
        
        //==============================
        // FCT030
        //==============================
        
        // ユーザ共通情報
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // 仲介業者営業員リスト
        OutputFct030Dto fct030Dto = new OutputFct030Dto();
        
        // 権限チェック
        // 参照権限：SBI証券本店以外の場合のみチェックを行う
        if (!StringUtils.equals(AUTH_CODE_SBI, userAccount.getPrivId())) {
            // FCT030.仲介業者営業員リストの件数が0件の場合：メッセージを表示し、処理終了
            InputFct030Dto fct030InData = new InputFct030Dto();
            fct030Dto = fct030.getData(fct030InData);
            
            if (fct030Dto == null || CollectionUtils.isEmpty(fct030Dto.getBrokerChargeList()) || fct030Dto.getBrokerChargeList().size() == 0) {
                return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST, IfaCommonUtil.getMessage(ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST));
            }
        }
        
        //==============================
        // ノックイン銘柄保有リスト(SQL001)を取得
        //==============================
        IfaKnockInKnockOutBrandHoldingListSql001RequestModel selSql001Req = new IfaKnockInKnockOutBrandHoldingListSql001RequestModel();
        BeanUtils.copyProperties(selSql001Req, dtoReq);
        selSql001Req.setBrokerChargeList(getBrokerChargeListSqlReq(fct030Dto));
        selSql001Req.setPrivId(userAccount.getPrivId());
        selSql001Req.setMaxRow(MAX_ROW_NUM);
        selSql001Req.setIsKnockIn(true); // ノックイン用のSQL生成

        // SQL001実行
        DataList<IfaKnockInKnockOutBrandHoldingListSql001ResponseModel> selSql001Res = dao.selectIfaKnockInKnockOutBrandHoldingListSql001(selSql001Req);
        
        // SQL001.総件数が0件の場合エラー
        if (selSql001Res == null || selSql001Res.getDataList().size() == 0) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.INFO, ERRORS_DATALIST_NOTFOUNT,
                    IfaCommonUtil.getMessage(ERRORS_DATALIST_NOTFOUNT));
        }
        
        // レスポンス項目のセット
        List<IfaKnockInBrandHoldingListA002DtoResponseKnockInBrandHoldingList> knockInBrandHoldingList = new ArrayList<>(); 
        for (IfaKnockInKnockOutBrandHoldingListSql001ResponseModel sqlResponse : selSql001Res.getDataList()) {
            IfaKnockInBrandHoldingListA002DtoResponseKnockInBrandHoldingList dtoResponse = new IfaKnockInBrandHoldingListA002DtoResponseKnockInBrandHoldingList();
            BeanUtils.copyProperties(dtoResponse, sqlResponse);
            knockInBrandHoldingList.add(dtoResponse);       
        }

        IfaKnockInBrandHoldingListA002DtoResponse response = new IfaKnockInBrandHoldingListA002DtoResponse();
        response.setKnockInBrandHoldingList(knockInBrandHoldingList);
        
        // SQL001.総件数の取得
        int totalRow = selSql001Res.get(0).getTotalRow();

        DataList<IfaKnockInBrandHoldingListA002DtoResponse> dtoRes = new DataList<IfaKnockInBrandHoldingListA002DtoResponse>();
        // SQL001.総件数が5000件超過の場合、ワーニング
        if (MAX_ROW_NUM < totalRow) {
            dtoRes = IfaCommonUtil.createDataList(List.of(response), ErrorLevel.WARNING, WARNINGS_DATALIST_OVER_MAX_ROWNUM, IfaCommonUtil.getMessage(WARNINGS_DATALIST_OVER_MAX_ROWNUM,
                    new String[] { String.valueOf(MAX_ROW_NUM), String.valueOf(totalRow) }));
            dtoRes.setOverMaxRownum(true);
        } else {
            dtoRes = IfaCommonUtil.createDataList(List.of(response), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
        }
        dtoRes.setMaxRownum(MAX_ROW_NUM);
        dtoRes.setTotalSize(totalRow);
        return dtoRes;
    }

    /**
     * アクションID：A004a
     * アクション名：PDF取得
     *
     * @param dtoReq リクエスト
     * @return SQL004.ファイルディレクトリ {@code DataList<String> }
     * @exception exception システムエラー
     */
    public DataList<String> getPdfA004a(IfaKnockInBrandHoldingListA004aDtoRequest dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaKnockInBrandHoldingListServiceImplL.getPdfA004a");
        }
        
        // --------------------------------
        // 登録ファイルディレクトリ取得（SQL008）
        // --------------------------------
        IfaKnockInKnockOutBrandHoldingListSql002RequestModel selSql002Req = new IfaKnockInKnockOutBrandHoldingListSql002RequestModel(FUNC_ID, CAT_ID);
        DataList<IfaKnockInKnockOutBrandHoldingListSql002ResponseModel> selSql002Res = dao.selectIfaKnockInKnockOutBrandHoldingListSql002(selSql002Req);

        // ファイルディレクトリを返却
        String ret = selSql002Res.getDataList().get(0).getFileDirectory();
        return IfaCommonUtil.createDataList(List.of(ret), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
    }

    
    /**
     * SqlReq用のbrokerChargeListを返却

     * @param fct030Dto 仲介業者営業員リスト
     * @return SQL処理結果
     */
    
    private List<IfaKnockInKnockOutBrandHoldingListSql001RequestModelBrokerCharge> getBrokerChargeListSqlReq(OutputFct030Dto fct030Dto) {
        List<IfaKnockInKnockOutBrandHoldingListSql001RequestModelBrokerCharge> brokerChargeList = new ArrayList<IfaKnockInKnockOutBrandHoldingListSql001RequestModelBrokerCharge>();
        if (fct030Dto != null && !CollectionUtils.isEmpty(fct030Dto.getBrokerChargeList())) {
            for (BrokerCharge fctRes : fct030Dto.getBrokerChargeList()) {
                IfaKnockInKnockOutBrandHoldingListSql001RequestModelBrokerCharge brokerCharge = new IfaKnockInKnockOutBrandHoldingListSql001RequestModelBrokerCharge();
                brokerCharge.setBrokerCode(fctRes.getBrokerCode()); // 仲介業者コード
                brokerCharge.setEmpCode(fctRes.getEmpCode()); // 営業員コード
                brokerChargeList.add(brokerCharge);
            }
        }
        return brokerChargeList;
    }
}
