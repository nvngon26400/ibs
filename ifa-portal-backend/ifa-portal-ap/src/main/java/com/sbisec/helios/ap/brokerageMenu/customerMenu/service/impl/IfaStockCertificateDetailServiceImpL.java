package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaStockCertificateDetailDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaStockCertificateDetailSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaStockCertificateDetailSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaStockCertificateDetailA001DetailDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaStockCertificateDetailA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaStockCertificateDetailService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

/**
 * 画面ID：SUB0202_0703-03
 * 画面名：株券詳細
 *
 * @author SBI大連 董
 *2025/03/20 新規作成
 */

@Component(value = "cmpIfaStockCertificateDetailService")
public class IfaStockCertificateDetailServiceImpL implements IfaStockCertificateDetailService{
    /** ロガー */
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaStockCertificateDetailServiceImpL.class);
    // --------------------------------
    // メッセージ
    // --------------------------------
    
    /** 検索結果が0件です。\n条件を設定して再度検索して下さい。 */
    private static final String ERRORS_DATALIST_NOT_FOUND = "errors.dataList.notfound";
    
    /** "検索結果が{0}件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。" */
    private static final String WARNINGS_DATA_LIST_OVER_MAX_ROWNUM = "warnings.dataList.overMaxRownum";
    
    /** 最小取得件数 */
    private static final int MIN_COUNT = 1;
    
    /** 最大取得件数（表示用） */
    private static final int MAX_COUNT_DISPLAY = 5000;
    
    @Autowired
    private IfaStockCertificateDetailDao dao;
    
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * DTO リクエスト：IfaStockCertificateDetailDtoRequest
     * DTO レスポンス：IfaStockCertificateDetailA001DtoResponse
     * model リクエスト：IfaStockCertificateDetailSql001RequestModel
     * model レスポンス：IfaStockCertificateDetailSql001ResponseModel
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaStockCertificateDetailA001DtoResponse> initializeA001(
            IfaStockCertificateDetailA001DtoResponse dtoReq) throws Exception {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaStockCertificateDetailServiceImpL.initializeA001");
        }
        List<IfaStockCertificateDetailA001DtoResponse> resList = new ArrayList<IfaStockCertificateDetailA001DtoResponse>();
        DataList<IfaStockCertificateDetailA001DtoResponse> dtoRes = new DataList<IfaStockCertificateDetailA001DtoResponse>();
        List<IfaStockCertificateDetailA001DetailDtoResponse> detailInfoList = new ArrayList<IfaStockCertificateDetailA001DetailDtoResponse>();
        // 部店
        String butenCode = IfaCommonUtil.getCustomerCommon().getButenCode();
        // 口座
        String accountNumber = IfaCommonUtil.getCustomerCommon().getAccountNumber();
        // SQL001のリクエスト値を設定
        IfaStockCertificateDetailSql001RequestModel selSql001Req = new IfaStockCertificateDetailSql001RequestModel();
        BeanUtils.copyProperties(dtoReq, selSql001Req);
        selSql001Req.setButenCode(butenCode);
        selSql001Req.setAccountNumber(accountNumber);
        
        // 株券詳細リストを取得する
        DataList<IfaStockCertificateDetailSql001ResponseModel> selSql001ResList = new DataList<IfaStockCertificateDetailSql001ResponseModel>();
        selSql001ResList = getStockCertificateDetailList(selSql001Req);
        
        // 株券詳細リストの件数が0件の場合、0件メッセージをセットして終了
        if (ObjectUtils.isEmpty(selSql001ResList) || selSql001ResList.size() < MIN_COUNT) {
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.INFO, ERRORS_DATALIST_NOT_FOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATALIST_NOT_FOUND));
            return dtoRes;
        }
        
        IfaStockCertificateDetailA001DtoResponse res = new IfaStockCertificateDetailA001DtoResponse();
     // レスポンスの設定
        for ( IfaStockCertificateDetailSql001ResponseModel selSql001Res : selSql001ResList.getDataList()) {
            IfaStockCertificateDetailA001DetailDtoResponse detailList = new IfaStockCertificateDetailA001DetailDtoResponse();
            BeanUtils.copyProperties(selSql001Res, detailList);
            detailInfoList.add(detailList);
        }
        res.setStockDetailList(detailInfoList);;
        resList.add(res);
        
        // 株券詳細リストの総件数が最大取得件数を超過していた場合、件数超過メッセージをセットして終了
        if (selSql001ResList.get(0).getTotalCount() > MAX_COUNT_DISPLAY) {
            String msg = IfaCommonUtil.getMessage(WARNINGS_DATA_LIST_OVER_MAX_ROWNUM,
                    new String[] { String.valueOf(MAX_COUNT_DISPLAY),
                            String.valueOf(selSql001ResList.get(0).getTotalCount()) });
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.WARNING, WARNINGS_DATA_LIST_OVER_MAX_ROWNUM, msg);
        } else {
            // 正常終了
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        }
        return dtoRes;
    }

    /**
     * 株券詳細リスト取得
     *
     * @param selSql001Req SQL001 リクエスト
     * @return IfaStockCertificateDetailSql001ResponseModel 株券詳細
     */
    private DataList<IfaStockCertificateDetailSql001ResponseModel> getStockCertificateDetailList(
            IfaStockCertificateDetailSql001RequestModel selSql001Req) throws Exception {
        
        DataList<IfaStockCertificateDetailSql001ResponseModel> selSql001ResList = new DataList<IfaStockCertificateDetailSql001ResponseModel>();
     // 株券詳細リスト
        selSql001ResList = dao.selectIfaStockCertificateDetailSql001(selSql001Req); 
        return selSql001ResList;
    }

}
