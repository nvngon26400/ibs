package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaContactInputDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactInputSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactInputSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactInputSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactInputSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactInputSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactInputSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactInputSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactInputSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactInputSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactAnswerListDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactInputA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactInputA007RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactInputA007ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactInputA008RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactInputA008ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactInputX001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactInputX001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactToiawaseListDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaContactInputService;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaContactInputUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

/**
 * サービスインターフェースの実装クラス
 * 画面ID:SUB0202_0106-03
 * 画面名:接触履歴入力
 * 2025/04/24 新規作成
 *
 * @author 大連 王永宝
 */
@Component(value = "cmpIfaContactInputService")
public class IfaContactInputServiceImpL implements IfaContactInputService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaContactInputServiceImpL.class);

    @Autowired
    private IfaContactInputDao g_dao;

    @Autowired
    private IfaContactInputUtil g_util;

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaContactInputA001RequestDto
     * Dto レスポンス：IfaContactInputA001ResponseDto
     *
     * @param x_dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaContactInputA001ResponseDto> initializeA001(IfaContactInputA001RequestDto x_dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("IfaContactInputServiceImpL.initializeA001");

        // 戻り値の初期化
        IfaContactInputA001ResponseDto p_resDto = new IfaContactInputA001ResponseDto();
        // 処理区分: '1'(接触履歴入力)固定
        p_resDto.setOperationType(IfaContactInputUtil.OperationType.QUESTION_INPUT.key);

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
        /* 2.問合せカテゴリリスト（大）を取得する SQL001 */
        // SQLリクエストを作成する
        IfaContactInputSql001RequestModel p_sql001Req = new IfaContactInputSql001RequestModel();
        p_sql001Req.setToiawaseKbn(IfaContactInputUtil.ToiawaseKbn.L.key);      // 問合せ区分 = 1:大分類
        p_sql001Req.setIfaSenyouFlg(IfaContactInputUtil.IfaSenyouFlg.IFA.key);  // IFA専用フラグ = 1:IFA専用
        p_sql001Req.setSakujoFlg(IfaContactInputUtil.SakujoFlg.NO.key);         // 削除フラグ = 0:未削除
        // SQL実行
        DataList<IfaContactInputSql001ResponseModel> p_sql001Res = g_dao.selectIfaContactInputSql001(p_sql001Req);
        if (p_sql001Res != null && 0 < p_sql001Res.size()) {
            List<IfaContactInputSql001ResponseModel> p_sql001List = p_sql001Res.getDataList();
            // データリストをストリームに変換し、各要素を指定したクラス型に変換する
            List<IfaContactToiawaseListDto> p_toiawaseList = p_sql001List.stream().map(p_sql001Model -> {
                IfaContactToiawaseListDto p_dto = new IfaContactToiawaseListDto();
                p_dto.setToiawaseCd(p_sql001Model.getToiawaseCd());
                p_dto.setToiawaseMei(p_sql001Model.getToiawaseMei());
                return p_dto;
            }).filter(Objects::nonNull).collect(Collectors.toList());           // nullでない要素だけをリストに収集する
            // 問合せカテゴリリストを格納する
            p_resDto.setToiawaseDList(p_toiawaseList);
        }

        // レスポンスを返却する
        return IfaCommonUtil.createDataList(Arrays.asList(p_resDto), ErrorLevel.SUCCESS, StringUtils.EMPTY, StringUtils.EMPTY);
    }

    /**
     * アクションID:X001
     * アクション名:追加入力/管理項目修正
     * リクエスト:IfaContactInputX001RequestDto
     * レスポンス:IfaContactInputX001ResponseDto
     *
     * @param x_dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaContactInputX001ResponseDto> initializeX001(IfaContactInputX001RequestDto x_dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("IfaContactInputServiceImpL.initializeX001");

        // 戻り値の初期化
        IfaContactInputX001ResponseDto p_resDto = new IfaContactInputX001ResponseDto();
        BeanUtils.copyProperties(x_dtoReq, p_resDto);

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
        /* 2.問合せカテゴリリスト（大）を取得する SQL001 */
        // SQLリクエストを作成する
        IfaContactInputSql001RequestModel p_sql001Req = new IfaContactInputSql001RequestModel();
        p_sql001Req.setToiawaseKbn(IfaContactInputUtil.ToiawaseKbn.L.key);      // 問合せ区分 = 1:大分類
        p_sql001Req.setIfaSenyouFlg(IfaContactInputUtil.IfaSenyouFlg.IFA.key);  // IFA専用フラグ = 1:IFA専用
        p_sql001Req.setSakujoFlg(IfaContactInputUtil.SakujoFlg.NO.key);         // 削除フラグ = 0:未削除
        // SQL実行
        DataList<IfaContactInputSql001ResponseModel> p_sql001Res = g_dao.selectIfaContactInputSql001(p_sql001Req);
        if (p_sql001Res != null && 0 < p_sql001Res.size()) {
            List<IfaContactInputSql001ResponseModel> p_sql001List = p_sql001Res.getDataList();
            // データリストをストリームに変換し、各要素を指定したクラス型に変換する
            List<IfaContactToiawaseListDto> p_toiawaseList = p_sql001List.stream().map(p_sql001Model -> {
                IfaContactToiawaseListDto p_dto = new IfaContactToiawaseListDto();
                p_dto.setToiawaseCd(p_sql001Model.getToiawaseCd());
                p_dto.setToiawaseMei(p_sql001Model.getToiawaseMei());
                return p_dto;
            }).filter(Objects::nonNull).collect(Collectors.toList());           // nullでない要素だけをリストに収集する
            // 問合せカテゴリリスト（大）を格納する
            p_resDto.setToiawaseDList(p_toiawaseList);
        }
        /* 3.問合せ情報を取得する。 SQL002 */
        // SQLリクエストを作成する
        IfaContactInputSql002RequestModel p_sql002Req = new IfaContactInputSql002RequestModel();
        p_sql002Req.setIfaToiawaseNo(x_dtoReq.getIfaToiawaseNo());              // IFA問合せNO = リクエスト.IFA問合せNO
        p_sql002Req.setSakujoFlg(IfaContactInputUtil.SakujoFlg.NO.key);         // 削除フラグ = 0:未削除
        // SQL実行
        DataList<IfaContactInputSql002ResponseModel> p_sql002Res = g_dao.selectIfaContactInputSql002(p_sql002Req);
        if (p_sql002Res != null && 0 < p_sql002Res.size()) {
            BeanUtils.copyProperties(p_sql002Res.get(0), p_resDto);
        }
        /* 5.回答情報取得 SQL005 */
        // SQLリクエストを作成する
        IfaContactInputSql005RequestModel p_sql005Req = new IfaContactInputSql005RequestModel();
        p_sql005Req.setIfaToiawaseNo(x_dtoReq.getIfaToiawaseNo());              // IFA問合せNO = リクエスト.IFA問合せNO
        p_sql005Req.setSakujoFlg(IfaContactInputUtil.SakujoFlg.NO.key); // 削除フラグ = '0'
        p_sql005Req.setTourokuGroup("0"); // 登録グループ = 0
        // SQL実行
        DataList<IfaContactInputSql005ResponseModel> p_sql005Res = g_dao.selectIfaContactInputSql005(p_sql005Req);
        if (p_sql005Res != null && 0 < p_sql005Res.size()) {
            List<IfaContactInputSql005ResponseModel> p_sql005List = p_sql005Res.getDataList();
            List<IfaContactAnswerListDto> p_answerList = p_sql005List.stream().map(p_sql005Model -> {
                IfaContactAnswerListDto p_dto = new IfaContactAnswerListDto();
                BeanUtils.copyProperties(p_sql005Model, p_dto);
                return p_dto;
            }).filter(Objects::nonNull).collect(Collectors.toList());
            // 回答情報リスト（中）を格納する
            p_resDto.setAnswerList(p_answerList);
        }
        /* 5.問合せカテゴリリスト（中）取得 SQL003 */
        // SQLリクエストを作成する
        IfaContactInputSql003RequestModel p_sql003Req = new IfaContactInputSql003RequestModel();
        p_sql003Req.setToiawaseKbn(IfaContactInputUtil.ToiawaseKbn.M.key); // 問合せ区分＝2:中分類
        p_sql003Req.setIfaSenyouFlg(IfaContactInputUtil.IfaSenyouFlg.IFA.key); // IFA専用フラグ = 1
        p_sql003Req.setSakujoFlg(IfaContactInputUtil.SakujoFlg.NO.key); // 削除フラグ = '0'
        p_sql003Req.setToiawaseD(p_resDto.getToiawaseDCd()); // 問合せカテゴリ(大分類) = リクエスト.問合せカテゴリコード（大）
        // SQL実行
        DataList<IfaContactInputSql003ResponseModel> p_sql003Res = g_dao.selectIfaContactInputSql003(p_sql003Req);
        if (p_sql003Res != null && 0 < p_sql003Res.size()) {
            List<IfaContactInputSql003ResponseModel> p_sql003List = p_sql003Res.getDataList();
            List<IfaContactToiawaseListDto> p_toiawaseList = p_sql003List.stream().map(p_sql003Model -> {
                IfaContactToiawaseListDto p_dto = new IfaContactToiawaseListDto();
                p_dto.setToiawaseCd(p_sql003Model.getToiawaseCd());
                p_dto.setToiawaseMei(p_sql003Model.getToiawaseMei());
                return p_dto;
            }).filter(Objects::nonNull).collect(Collectors.toList());
            // 問合せカテゴリリスト（中）を格納する
            p_resDto.setToiawaseList(p_toiawaseList);
        }
        /* 6.問合せカテゴリリスト（小）取得 SQL004 */
        // SQLリクエストを作成する
        IfaContactInputSql004RequestModel p_sql004Req = new IfaContactInputSql004RequestModel();
        p_sql004Req.setToiawaseKbn(IfaContactInputUtil.ToiawaseKbn.S.key); // 問合せ区分＝2:小分類
        p_sql004Req.setIfaSenyouFlg(IfaContactInputUtil.IfaSenyouFlg.IFA.key); // IFA専用フラグ = 1
        p_sql004Req.setSakujoFlg(IfaContactInputUtil.SakujoFlg.NO.key); // 削除フラグ = '0'
        p_sql004Req.setToiawaseD(p_resDto.getToiawaseDCd()); // 問合せカテゴリ(大分類) = リクエスト.問合せカテゴリコード（大）
        p_sql004Req.setToiawaseT(p_resDto.getToiawaseCd()); // 問合せカテゴリ(中分類) = リクエスト.問合せカテゴリコード（中）
        // SQL実行
        DataList<IfaContactInputSql004ResponseModel> p_sql004Res = g_dao.selectIfaContactInputSql004(p_sql004Req);
        if (p_sql004Res != null && 0 < p_sql004Res.size()) {
            List<IfaContactInputSql004ResponseModel> p_sql004List = p_sql004Res.getDataList();
            List<IfaContactToiawaseListDto> p_toiawaseList = p_sql004List.stream().map(p_sql004Model -> {
                IfaContactToiawaseListDto p_dto = new IfaContactToiawaseListDto();
                p_dto.setToiawaseCd(p_sql004Model.getToiawaseCd());
                p_dto.setToiawaseMei(p_sql004Model.getToiawaseMei());
                return p_dto;
            }).filter(Objects::nonNull).collect(Collectors.toList());
            // 問合せカテゴリリスト（小）を格納する
            p_resDto.setToiawaseSList(p_toiawaseList);
        }
        
        // レスポンスを返却する
        return IfaCommonUtil.createDataList(Arrays.asList(p_resDto), ErrorLevel.SUCCESS, StringUtils.EMPTY, StringUtils.EMPTY);
    }

    /**
     * アクションID:A002
     * アクション名:確認
     * リクエスト:IfaContactInputA002RequestDto
     * レスポンス:IfaContactInputA002ResponseDto
     *
     * @param x_dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaContactInputA002ResponseDto> confirmA002(IfaContactInputA002RequestDto x_dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("IfaContactInputServiceImpL.confirmA002");

        // 戻り値の初期化
        IfaContactInputA002ResponseDto p_resDto = new IfaContactInputA002ResponseDto();
        BeanUtils.copyProperties(x_dtoReq, p_resDto);

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
        /* 2.ユーザ共通情報.CCSログイン用IDのチェックを行う。 */
        p_errDto = g_util.checkHasCcsId();
        if (p_errDto.isErrorFlg()) {
            return IfaCommonUtil.createDataList(Arrays.asList(p_resDto),
                    ErrorLevel.FATAL,
                    p_errDto.getErrorMessageId(),
                    p_errDto.getErrorMessage());
        }
        // レスポンスを返却する
        return IfaCommonUtil.createDataList(Arrays.asList(p_resDto), ErrorLevel.SUCCESS, StringUtils.EMPTY, StringUtils.EMPTY);
    }

    /**
     * アクションID:A007
     * アクション名:カテゴリ選択（大）
     * リクエスト:IfaContactInputA007RequestDto
     * レスポンス:IfaContactInputA007ResponseDto
     *
     * @param x_dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaContactInputA007ResponseDto> categoryChangeA007(IfaContactInputA007RequestDto x_dtoReq)
        throws Exception {
        
        if (LOGGER.isDebugEnabled()) LOGGER.debug("IfaContactInputServiceImpL.categoryChangeA007");
        // 戻り値の初期化
        IfaContactInputA007ResponseDto p_resDto = new IfaContactInputA007ResponseDto();
        /* 問合せカテゴリリスト（中）取得 SQL003 */
        // SQLリクエストを作成する
        IfaContactInputSql003RequestModel p_sql003Req = new IfaContactInputSql003RequestModel();
        p_sql003Req.setToiawaseKbn(IfaContactInputUtil.ToiawaseKbn.M.key); // 問合せ区分＝2:中分類
        p_sql003Req.setIfaSenyouFlg(IfaContactInputUtil.IfaSenyouFlg.IFA.key); // IFA専用フラグ = 1
        p_sql003Req.setSakujoFlg(IfaContactInputUtil.SakujoFlg.NO.key); // 削除フラグ = '0'
        p_sql003Req.setToiawaseD(x_dtoReq.getToiawaseDCd()); // 問合せカテゴリ(大分類) = リクエスト.問合せカテゴリコード（大）
        // SQL実行
        DataList<IfaContactInputSql003ResponseModel> p_sql003Res = g_dao.selectIfaContactInputSql003(p_sql003Req);
        if (p_sql003Res != null && 0 < p_sql003Res.size()) {
            List<IfaContactInputSql003ResponseModel> p_sql003List = p_sql003Res.getDataList();
            List<IfaContactToiawaseListDto> p_toiawaseList = p_sql003List.stream().map(p_sql003Model -> {
                IfaContactToiawaseListDto p_dto = new IfaContactToiawaseListDto();
                p_dto.setToiawaseCd(p_sql003Model.getToiawaseCd());
                p_dto.setToiawaseMei(p_sql003Model.getToiawaseMei());
                return p_dto;
            }).filter(Objects::nonNull).collect(Collectors.toList());
            // 問合せカテゴリリスト（中）を格納する
            p_resDto.setToiawaseList(p_toiawaseList);
        }
        
        // レスポンスを返却する
        return IfaCommonUtil.createDataList(Arrays.asList(p_resDto), ErrorLevel.SUCCESS, StringUtils.EMPTY, StringUtils.EMPTY);
    }

    /**
     * アクションID:A008
     * アクション名:カテゴリ選択（中）
     * リクエスト:IfaContactInputA008RequestDto
     * レスポンス:IfaContactInputA008ResponseDto
     *
     * @param x_dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaContactInputA008ResponseDto> categoryChangeA008(IfaContactInputA008RequestDto x_dtoReq)
        throws Exception {
        
        if (LOGGER.isDebugEnabled()) LOGGER.debug("IfaContactInputServiceImpL.categoryChangeA008");
        // 戻り値の初期化
        IfaContactInputA008ResponseDto p_resDto = new IfaContactInputA008ResponseDto();
        /* 6.問合せカテゴリリスト（小）取得 SQL004 */
        // SQLリクエストを作成する
        IfaContactInputSql004RequestModel p_sql004Req = new IfaContactInputSql004RequestModel();
        p_sql004Req.setToiawaseKbn(IfaContactInputUtil.ToiawaseKbn.S.key); // 問合せ区分＝2:小分類
        p_sql004Req.setIfaSenyouFlg(IfaContactInputUtil.IfaSenyouFlg.IFA.key); // IFA専用フラグ = 1
        p_sql004Req.setSakujoFlg(IfaContactInputUtil.SakujoFlg.NO.key); // 削除フラグ = '0'
        p_sql004Req.setToiawaseD(x_dtoReq.getToiawaseDCd()); // 問合せカテゴリ(大分類) = リクエスト.問合せカテゴリコード（大）
        p_sql004Req.setToiawaseT(x_dtoReq.getToiawaseCd()); // 問合せカテゴリ(中分類) = リクエスト.問合せカテゴリコード（中）
        // SQL実行
        DataList<IfaContactInputSql004ResponseModel> p_sql004Res = g_dao.selectIfaContactInputSql004(p_sql004Req);
        if (p_sql004Res != null && 0 < p_sql004Res.size()) {
            List<IfaContactInputSql004ResponseModel> p_sql004List = p_sql004Res.getDataList();
            List<IfaContactToiawaseListDto> p_toiawaseList = p_sql004List.stream().map(p_sql004Model -> {
                IfaContactToiawaseListDto p_dto = new IfaContactToiawaseListDto();
                p_dto.setToiawaseCd(p_sql004Model.getToiawaseCd());
                p_dto.setToiawaseMei(p_sql004Model.getToiawaseMei());
                return p_dto;
            }).filter(Objects::nonNull).collect(Collectors.toList());
            // 問合せカテゴリリスト（中）を格納する
            p_resDto.setToiawaseSList(p_toiawaseList);
        }
        
        // レスポンスを返却する
        return IfaCommonUtil.createDataList(Arrays.asList(p_resDto), ErrorLevel.SUCCESS, StringUtils.EMPTY, StringUtils.EMPTY);
    }
}