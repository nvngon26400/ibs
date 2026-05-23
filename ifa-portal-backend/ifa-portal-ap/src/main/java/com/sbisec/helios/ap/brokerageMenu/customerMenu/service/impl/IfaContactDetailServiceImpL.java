package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaContactDetailDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactDetailSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactDetailSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactDetailSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactDetailSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactDetailA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactDetailA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaContactDetailService;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaContactInputUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

/**
 * サービスインターフェースの実装クラス
 * 画面ID:SUB0202_0106-06
 * 画面名:接触履歴詳細
 * 2025/04/24 新規作成
 *
 * @author 大連 王永宝
 */
@Component(value = "cmpIfaContactDetailService")
public class IfaContactDetailServiceImpL implements IfaContactDetailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaContactDetailServiceImpL.class);

    @Autowired
    private IfaContactDetailDao g_dao;

    @Autowired
    private IfaContactInputUtil g_util;

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaContactDetailA001RequestDto
     * Dto レスポンス：IfaContactDetailA001ResponseDto
     *
     * @param x_dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaContactDetailA001ResponseDto> initializeA001(IfaContactDetailA001RequestDto x_dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("IfaContactDetailServiceImpL.initializeA001");

        // 戻り値の初期化
        IfaContactDetailA001ResponseDto p_resDto = new IfaContactDetailA001ResponseDto();

        // 顧客共通情報の取得
        CustomerCommon p_cc = IfaCommonUtil.getCustomerCommon();

        /* 1.利用者の口座に対する権限チェックを行う。 */
        IfaContactInputUtil.ErrorResponseDto p_errDto = g_util.callFct001(p_cc);
        if (p_errDto.isErrorFlg()) {
            return IfaCommonUtil.createDataList(Arrays.asList(p_resDto), 
                    ErrorLevel.FATAL, 
                    p_errDto.getErrorMessageId(),
                    p_errDto.getErrorMessage());
        }

        /* 2.問合せ情報を取得する */
        IfaContactDetailSql001ResponseModel p_sql001Res = this.execSql001(x_dtoReq);
        // 各要素をIfaContactDetailA001ResponseDtoにマッピングする
        BeanUtils.copyProperties(p_sql001Res, p_resDto);

        /* 3.問合せ・回答内容情報を取得する */
        List<IfaContactDetailSql002ResponseModel> p_sql002ResList = this.execSql002(x_dtoReq);
        if (p_sql002ResList != null && p_sql002ResList.size() > 0) {
            // 各要素をIfaContactDetailA001ResponseDto.AnswerList にマッピングする
            List<IfaContactDetailA001ResponseDto.AnswerList> p_answerList = p_sql002ResList.stream().map(model -> {
                IfaContactDetailA001ResponseDto.AnswerList p_answer = new IfaContactDetailA001ResponseDto.AnswerList();
                BeanUtils.copyProperties(model, p_answer);
                // 変換されたデータを返す
                return p_answer;
            // 結果をListに収集する
            }).collect(Collectors.toList());
            // answerListに変換されたリストをセットする
            p_resDto.setAnswerList(p_answerList);
        }
        // レスポンスを返却する
        return IfaCommonUtil.createDataList(Arrays.asList(p_resDto), ErrorLevel.SUCCESS, StringUtils.EMPTY, StringUtils.EMPTY);
    }
    

    /**
     * SQL001.問合せカテゴリ(中分類)を取得する
     * @param x_dtoReq IfaContactDetailA001RequestDto
     * @return エラーがあれば、null
     */
    private IfaContactDetailSql001ResponseModel execSql001(IfaContactDetailA001RequestDto x_dtoReq) {
        IfaContactDetailSql001RequestModel p_sql001Req = new IfaContactDetailSql001RequestModel();
        /* 以下は抽出条件のセット */
        // IFA問合せNO = リクエスト.IFA問合せNO
        p_sql001Req.setIfaToiawaseNo(x_dtoReq.getIfaToiawaseNo());
        // 問合せ区分 = 2:中分類
        p_sql001Req.setToiawaseKbn(IfaContactInputUtil.ToiawaseKbn.L.key);
        // 削除フラグ = 0:未削除
        p_sql001Req.setSakujoFlg(IfaContactInputUtil.SakujoFlg.NO.key);
        /* SQL実行 */
        try {
            DataList<IfaContactDetailSql001ResponseModel> p_sql001Res = g_dao.selectIfaContactDetailSql001(p_sql001Req);
            if (p_sql001Res != null && 0 < p_sql001Res.size()) {
                return p_sql001Res.get(0);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    /**
     * SQL002.回答内容情報取得
     * @param x_dtoReq IfaContactDetailA001RequestDto
     * @return エラーがあれば、null
     */
    private List<IfaContactDetailSql002ResponseModel> execSql002(IfaContactDetailA001RequestDto x_dtoReq) {
        IfaContactDetailSql002RequestModel p_sql002Req = new IfaContactDetailSql002RequestModel();
        /* 以下は抽出条件のセット */
        // IFA問合せNO = リクエスト.IFA問合せNO
        p_sql002Req.setIfaToiawaseNo(x_dtoReq.getIfaToiawaseNo());
        // 削除フラグ = 0:未削除
        p_sql002Req.setSakujoFlg(IfaContactInputUtil.SakujoFlg.NO.key);
        /* SQL実行 */
        try {
            DataList<IfaContactDetailSql002ResponseModel> p_sql002Res = g_dao.selectIfaContactDetailSql002(p_sql002Req);
            if (p_sql002Res != null && 0 < p_sql002Res.size()) {
                return p_sql002Res.getDataList();
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}