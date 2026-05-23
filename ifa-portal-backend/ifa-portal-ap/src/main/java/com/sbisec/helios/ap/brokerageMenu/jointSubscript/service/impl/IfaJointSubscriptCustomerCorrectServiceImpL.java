package com.sbisec.helios.ap.brokerageMenu.jointSubscript.service.impl;

import java.util.Arrays;
import java.util.Collections;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.IfaJointSubscriptCustomerManageDao;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerCorrectA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerCorrectA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerCorrectA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerCorrectA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerCorrectA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerCorrectA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageCommModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.service.IfaJointSubscriptCustomerCorrectService;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.util.IfaJointSubscriptCustomerException;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.util.IfaJointSubscriptCustomerManageUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

/**
 * サービスインターフェースの実装クラス
 * 画面ID：SUB0206_01-03
 * 画面名：共同募集 顧客修正
 * 2024/12/10 新規作成
 *
 * @author 大連 王永宝
 */
@Component(value = "cmpIfaJointSubscriptCustomerCorrectService")
public class IfaJointSubscriptCustomerCorrectServiceImpL implements IfaJointSubscriptCustomerCorrectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaJointSubscriptCustomerCorrectServiceImpL.class);

    @Autowired
    private IfaJointSubscriptCustomerManageDao g_dao;

    @Autowired
    private IfaJointSubscriptCustomerManageUtil g_util;

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaJointSubscriptCustomerCorrectA001RequestDto
     * Dto レスポンス：IfaJointSubscriptCustomerCorrectA001ResponseDto
     *
     * @param x_dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaJointSubscriptCustomerCorrectA001ResponseDto> initializeA001(
            IfaJointSubscriptCustomerCorrectA001RequestDto x_dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("IfaJointSubscriptCustomerCorrectServiceImpL.initializeA001");

        // 共同募集　顧客管理一覧選択した行目のデータ（リクエストパラメータ）により、画面項目の値を設定する
        IfaJointSubscriptCustomerCorrectA001ResponseDto p_dtoRes = new IfaJointSubscriptCustomerCorrectA001ResponseDto();
        BeanUtils.copyProperties(p_dtoRes, x_dtoReq);
        // 取得したデータをレスポンスモデルに代入し、レスポンスを返却する
        return IfaCommonUtil.createDataList(
                            Arrays.asList(p_dtoRes),
                            ErrorLevel.SUCCESS,
                            StringUtil.EMPTY_STRING,
                            StringUtil.EMPTY_STRING
                        );
    }

    /**
     * アクションID：A002
     * アクション名：修正(修正入力)
     * Dto リクエスト：IfaJointSubscriptCustomerCorrectA002RequestDto
     * Dto レスポンス：IfaJointSubscriptCustomerCorrectA002ResponseDto
     *
     * @param x_dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaJointSubscriptCustomerCorrectA002ResponseDto> confirmCorrectA002(
            IfaJointSubscriptCustomerCorrectA002RequestDto x_dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("IfaJointSubscriptCustomerCorrectServiceImpL.confirmCorrectA002");

        // レスポンスセット
        IfaJointSubscriptCustomerCorrectA002ResponseDto p_a002Dto = new IfaJointSubscriptCustomerCorrectA002ResponseDto();
        BeanUtils.copyProperties(p_a002Dto, x_dtoReq);
        /* 業務チェックを行う */
        String[] p_chkRtn = null;
        // ユーザ共通情報.権限コードは本店、支店、仲介業者内管 (1~3)権限以外の場合、エラーメッセージを表示し、処理を終了
        p_chkRtn = g_util.checkPrivId(IfaCommonUtil.getUserAccount().getPrivId(), 
                IfaJointSubscriptCustomerManageUtil.Message.MSG_PARAMETER_CORRECT.key);
        if (p_chkRtn != null) {
            return IfaCommonUtil.createDataList(Arrays.asList(p_a002Dto), ErrorLevel.FATAL, p_chkRtn[0], p_chkRtn[1]);
        }
        // 契約締結日は未来日付の場合、エラーメッセージを表示し、処理終了
        p_chkRtn = g_util.checkContractDate(x_dtoReq.getContractDate());
        if (p_chkRtn != null) {
            return IfaCommonUtil.createDataList(Arrays.asList(p_a002Dto), ErrorLevel.FATAL, p_chkRtn[0], p_chkRtn[1]);
        }
        // 終了日が同意日より前の日付の場合、エラーメッセージを表示し、処理終了
        p_chkRtn = g_util.checkEndDate(x_dtoReq.getStartDate(), x_dtoReq.getEndDate());
        if (p_chkRtn != null) {
            return IfaCommonUtil.createDataList(Arrays.asList(p_a002Dto), ErrorLevel.FATAL, p_chkRtn[0], p_chkRtn[1]);
        }
        // 支払率が99.9999999を超える場合、エラーメッセージを表示し、処理終了
        p_chkRtn = g_util.checkJointRewardRate(x_dtoReq.getJointRewardRate());
        if (p_chkRtn != null) {
            return IfaCommonUtil.createDataList(Arrays.asList(p_a002Dto), ErrorLevel.FATAL, p_chkRtn[0], p_chkRtn[1]);
        }
        // 共募支店名を取得する
        String p_jointBranchName = g_util.getBranchName(g_dao, p_a002Dto.getJointBranchCode());
        p_a002Dto.setJointBranchName(p_jointBranchName);
        // レスポンス戻す
        return IfaCommonUtil.createDataList(Arrays.asList(p_a002Dto), ErrorLevel.SUCCESS, StringUtils.EMPTY, StringUtils.EMPTY);
    }

    /**
     * アクションID：A004
     * アクション名：修正(修正確認)
     * Dto リクエスト：IfaJointSubscriptCustomerCorrectA004RequestDto
     * Dto レスポンス：IfaJointSubscriptCustomerCorrectA004ResponseDto
     *
     * @param x_dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaJointSubscriptCustomerCorrectA004ResponseDto> executeCorrectA004(
            IfaJointSubscriptCustomerCorrectA004RequestDto x_dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("IfaJointSubscriptCustomerCorrectServiceImpL.executeCorrectA004");

        /* 業務チェックを行う */
        String[] p_chkRtn = null;
        //入力した部店、口座番号のデータが存在しない場合、エラーメッセージを表示し、処理終了。
        p_chkRtn = g_util.checkButenAndAccountExistence(g_dao,
                x_dtoReq.getButenCode(), x_dtoReq.getAccountNumber());
        if (p_chkRtn != null) {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, p_chkRtn[0], p_chkRtn[1]);
        }

        //入力した部店、口座番号は共同募集の契約先仲介業者の顧客ではない場合、エラーメッセージを表示し、処理終了。
        p_chkRtn = g_util.checkBrokerExistence(g_dao,
                x_dtoReq.getButenCode(), x_dtoReq.getAccountNumber());
        if (p_chkRtn != null) {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, p_chkRtn[0], p_chkRtn[1]);
        }

        /* 以下、修正処理を行う。 */
        IfaJointSubscriptCustomerManageCommModel p_commModel = new IfaJointSubscriptCustomerManageCommModel();
        BeanUtils.copyProperties(p_commModel, x_dtoReq);
        p_commModel.setEditStatus(IfaJointSubscriptCustomerManageUtil.EditStats.CORRECT.key);
        // DBの共通処理部を行う。
        String p_dbResult = StringUtil.EMPTY_STRING;
        try {
            p_dbResult = g_util.execDBcommonLogic(g_dao, p_commModel);
        } catch (IfaJointSubscriptCustomerException ex) {
            p_dbResult = ex.getMessageId();
        }
        // 戻る
        return g_util.creatDataList(p_dbResult, 
                IfaJointSubscriptCustomerManageUtil.Message.MSG_PARAMETER_CORRECT.key);
    }
}