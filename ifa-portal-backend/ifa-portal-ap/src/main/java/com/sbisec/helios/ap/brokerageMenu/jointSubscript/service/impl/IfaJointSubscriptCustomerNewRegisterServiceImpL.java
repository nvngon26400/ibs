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
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageCommModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerNewRegisterA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerNewRegisterA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerNewRegisterA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerNewRegisterA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerNewRegisterA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerNewRegisterA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.service.IfaJointSubscriptCustomerNewRegisterService;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.util.IfaJointSubscriptCustomerException;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.util.IfaJointSubscriptCustomerManageUtil;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.util.IfaJointSubscriptCustomerManageUtil.Message;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

/**
 * サービスインターフェースの実装クラス
 * 画面ID：SUB0206_01-02
 * 画面名：共同募集 顧客新規登録
 * 2024/12/10 新規作成
 *
 * @author 大連 王永宝
 */
@Component(value = "cmpIfaJointSubscriptCustomerNewRegisterService")
public class IfaJointSubscriptCustomerNewRegisterServiceImpL implements IfaJointSubscriptCustomerNewRegisterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaJointSubscriptCustomerNewRegisterServiceImpL.class);

    @Autowired
    private IfaJointSubscriptCustomerManageDao g_dao;

    @Autowired
    private IfaJointSubscriptCustomerManageUtil g_util;

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaJointSubscriptCustomerNewRegisterA001RequestDto
     * Dto レスポンス：IfaJointSubscriptCustomerNewRegisterA001ResponseDto
     *
     * @param x_dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaJointSubscriptCustomerNewRegisterA001ResponseDto> initializeA001(
            IfaJointSubscriptCustomerNewRegisterA001RequestDto x_dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("IfaJointSubscriptCustomerNewRegisterServiceImpL.initializeA001");

        // レスポンスを返却する
        return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.SUCCESS, StringUtils.EMPTY, StringUtils.EMPTY);
    }

    /**
     * アクションID：A002
     * アクション名：登録確認(新規登録入力)
     * Dto リクエスト：IfaJointSubscriptCustomerNewRegisterA002RequestDto
     * Dto レスポンス：IfaJointSubscriptCustomerNewRegisterA002ResponseDto
     *
     * @param x_dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaJointSubscriptCustomerNewRegisterA002ResponseDto> confirmRegisterA002(
            IfaJointSubscriptCustomerNewRegisterA002RequestDto x_dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("IfaJointSubscriptCustomerNewRegisterServiceImpL.confirmNewRegisterA002");

        // レスポンスセット
        IfaJointSubscriptCustomerNewRegisterA002ResponseDto p_a002Dto = new IfaJointSubscriptCustomerNewRegisterA002ResponseDto();
        BeanUtils.copyProperties(p_a002Dto, x_dtoReq);
        /* 業務チェックを行う */
        String[] p_chkRtn = null;
        // ユーザ共通情報.権限コードは本店、支店、仲介業者内管 (1~3)権限以外の場合、エラーメッセージを表示し、処理を終了
        p_chkRtn = g_util.checkPrivId(IfaCommonUtil.getUserAccount().getPrivId(), 
                IfaJointSubscriptCustomerManageUtil.Message.MSG_PARAMETER_REGISTER.key);
        if (p_chkRtn != null) {
            return IfaCommonUtil.createDataList(Arrays.asList(p_a002Dto), ErrorLevel.FATAL, p_chkRtn[0], p_chkRtn[1]);
        }
        // 契約締結日は未来日付の場合、エラーメッセージを表示し、処理終了
        p_chkRtn = g_util.checkContractDate(x_dtoReq.getContractDate());
        if (p_chkRtn != null) {
            return IfaCommonUtil.createDataList(Arrays.asList(p_a002Dto), ErrorLevel.FATAL, p_chkRtn[0], p_chkRtn[1]);
        }
        // 同意日は未来日付の場合、エラーメッセージを表示し、処理終了
        p_chkRtn = g_util.checkStartDate(x_dtoReq.getStartDate());
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
        // 仲介業者コードと仲介業者名を取得する
        String[] p_brokerArr = g_util.getBrokerCodeAndName(g_dao, p_a002Dto.getButenCode(), p_a002Dto.getAccountNumber());
        // 仲介業者コードと仲介業者名がなければ、エラーメッセージを表示し、処理終了。
        if (p_brokerArr == null) {
            return IfaCommonUtil.createDataList(Arrays.asList(p_a002Dto), ErrorLevel.FATAL, 
                            IfaJointSubscriptCustomerManageUtil.Message.ERRORS_JOINT_DATA_BROKER_NOTEXIST.key, 
                            IfaCommonUtil.getMessage(Message.ERRORS_JOINT_DATA_BROKER_NOTEXIST.key,
                            new String[] {p_a002Dto.getButenCode(), p_a002Dto.getAccountNumber()}));
        }
        p_a002Dto.setBrokerCode(p_brokerArr[0]);
        p_a002Dto.setBrokerName(p_brokerArr[1]);
        // 入力した新しい共同募集顧客データが既に存在する場合、エラーメッセージを表示し、処理終了
        p_chkRtn = g_util.checkCustomerExistence(g_dao, x_dtoReq.getButenCode(), x_dtoReq.getAccountNumber());
        if (p_chkRtn != null) {
            return IfaCommonUtil.createDataList(Arrays.asList(p_a002Dto), ErrorLevel.FATAL, p_chkRtn[0], p_chkRtn[1]);
        }
        // 共募支店名を取得する
        String p_jointBranchName = g_util.getBranchName(g_dao, p_a002Dto.getJointBranchCode());
        // 共募支店名がなければ、エラーメッセージを表示し、処理終了。
        if (p_jointBranchName == null) {
            return IfaCommonUtil.createDataList(Arrays.asList(p_a002Dto), ErrorLevel.FATAL, 
                            IfaJointSubscriptCustomerManageUtil.Message.ERRORS_JOINT_DATA_BRANCH_NOTEXIST.key, 
                            IfaCommonUtil.getMessage(Message.ERRORS_JOINT_DATA_BRANCH_NOTEXIST.key,
                            new String[] {p_a002Dto.getJointBranchCode()}));
        }
        p_a002Dto.setJointBranchName(p_jointBranchName);
        // レスポンス戻す
        return IfaCommonUtil.createDataList(Arrays.asList(p_a002Dto), ErrorLevel.SUCCESS, StringUtils.EMPTY, StringUtils.EMPTY);
    }

    /**
     * アクションID：A004
     * アクション名：登録(新規登録確認)
     * Dto リクエスト：IfaJointSubscriptCustomerNewRegisterA004RequestDto
     * Dto レスポンス：IfaJointSubscriptCustomerNewRegisterA004ResponseDto
     *
     * @param x_dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaJointSubscriptCustomerNewRegisterA004ResponseDto> executeRegisterA004(
            IfaJointSubscriptCustomerNewRegisterA004RequestDto x_dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("IfaJointSubscriptCustomerNewRegisterServiceImpL.executeNewRegisterA004");

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
        // 入力した新しい共同募集顧客データが既に存在する場合、エラーメッセージを表示し、処理終了
        p_chkRtn = g_util.checkCustomerExistence(g_dao,
                x_dtoReq.getButenCode(), x_dtoReq.getAccountNumber());
        if (p_chkRtn != null) {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, p_chkRtn[0], p_chkRtn[1]);
        }
        /* 以下、新規登録処理を行う。 */
        IfaJointSubscriptCustomerManageCommModel p_commModel = new IfaJointSubscriptCustomerManageCommModel();
        BeanUtils.copyProperties(p_commModel, x_dtoReq);
        p_commModel.setEditStatus(IfaJointSubscriptCustomerManageUtil.EditStats.REGISTER.key);
        // DBの共通処理部を行う。
        String p_dbResult = StringUtil.EMPTY_STRING;
        try {
            p_dbResult = g_util.execDBcommonLogic(g_dao, p_commModel);
        } catch (IfaJointSubscriptCustomerException ex) {
            p_dbResult = ex.getMessageId();
        }
        // 戻る
        return g_util.creatDataList(p_dbResult, 
                IfaJointSubscriptCustomerManageUtil.Message.MSG_PARAMETER_REGISTER.key);
    }

}