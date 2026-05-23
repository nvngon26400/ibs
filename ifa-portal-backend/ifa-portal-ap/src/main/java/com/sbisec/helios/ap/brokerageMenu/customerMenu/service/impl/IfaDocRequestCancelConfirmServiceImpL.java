package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaDocRequestCancelConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestCancelConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestCancelConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestCancelConfirmA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestCancelConfirmA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestCancelConfirmA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaDocRequestCancelConfirmService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

/**
 * 画面ID：SUB0202_0704-03_1 画面名：書類請求取消
 *
 * @author xin.huang
 */
@Component(value = "cmpIfaDocRequestCancelConfirmService")
public class IfaDocRequestCancelConfirmServiceImpL implements IfaDocRequestCancelConfirmService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaDocRequestCancelConfirmServiceImpL.class);

    @Autowired
    private IfaDocRequestCancelConfirmDao dao;

    @Autowired
    private Fct001 fct001;

    /** 権限なし */
    private static final String NO_AUTHORITY = "0";

    /** 取引停止口座 */
    private static final String SUSPENSION_ACCOUNT = "1";

    /** 取消区分 */
    private static final String TORIKESHI_KBN = "1";

    enum MessageId {
        // errors.butenAccountNotExis
        ERRORS_BUTENACCOUNTNOTEXIST("errors.butenAccountNotExist"),
        // errors.cmn.selectedAccount.outOfService
        ERRORS_OUT_OF_SERVICE("errors.cmn.selectedAccount.outOfService"),
        // errors.cmn.cancelRequestPaper.failed
        ERRORS_CMN_CANCELREQUESTPAPER_FAILED("errors.cmn.cancelRequestPaper.failed"),
        // errors.cmn.cancelRequestPaper.failed
        ERRORS_CANCEL_BM_DELIVERY_FAILED("errors.cancelBmDelivery.failed"),
        // errors.cmn.ccsid.unregistered
        ERRORS_CMN_CCSID_UNREGISTERED("errors.cmn.ccsid.unregistered");

        private String key;

        private MessageId(String key) {
            this.key = key;
        }
    }

    /**
     * アクションID：A002
     * アクション名：書類請求取消
     * Dtoレスポンス：IfaDocRequestCancelConfirmA002RequestDto
     * Dtoレスポンス：IfaDocRequestCancelConfirmA002ResponseDto
     * Modelリクエスト：IfaDocRequestCancelConfirmSql001RequestModel
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 書類請求取消の際、例外が発生した場合
     */
    public DataList<IfaDocRequestCancelConfirmA002ResponseDto> cancelConfirmA002(IfaDocRequestCancelConfirmA002RequestDto dtoReq)
            throws Exception {
        DataList<IfaDocRequestCancelConfirmA002ResponseDto> dtoRes = new DataList<IfaDocRequestCancelConfirmA002ResponseDto>();
        List<IfaDocRequestCancelConfirmA002ResponseDto> resDtoList = new ArrayList<IfaDocRequestCancelConfirmA002ResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDocRequestInputServiceImpL.cancelConfirmA002");
        }

        // ①利用者の口座に対する権限チェックを行う
        InputFct001Dto fct001Req = new InputFct001Dto();
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        // 部店コード(顧客共通情報.部店コード)をセットする
        fct001Req.setButenCode(cc.getButenCode());
        // 口座番号(顧客共通情報.口座番号)をセットする
        fct001Req.setAccountNumber(cc.getAccountNumber());
        // 共通関数FCT001を呼び出す
        OutputFct001Dto fct001Res = new OutputFct001Dto();
        fct001Res = fct001.doCheck(fct001Req);
        // FCT001.対象顧客参照権限有無 == "0"(権限なし)の場合
        if (StringUtils.equals(fct001Res.getTargetCustomerRefAuthFlag(), NO_AUTHORITY)) {
            // レスポンスに権限なしエラー(errors.butenAccountNotExist)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                            new String[] { cc.getButenCode(), cc.getAccountNumber() }));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }
        // FCT001.取引停止フラグ == "1"(取引停止口座)の場合
        if (StringUtils.equals(fct001Res.getTradeSuspendFlag(), SUSPENSION_ACCOUNT)) {
            // レスポンスに取引停止口座エラー(errors.cmn.selectedAccount.outOfService)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_OUT_OF_SERVICE.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_OUT_OF_SERVICE.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }
        
        // ②ユーザ共通情報.CCSログイン用IDのチェックを行う。
        if (StringUtil.isNullOrEmpty(IfaCommonUtil.getUserAccount().getCcsUserId())) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CMN_CCSID_UNREGISTERED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_CCSID_UNREGISTERED.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }

        // ③指定した書類請求の取消を行う。
        IfaDocRequestCancelConfirmSql001RequestModel sql001Req = new IfaDocRequestCancelConfirmSql001RequestModel();
        // 書類請求NO(リクエスト.書類請求NO)をセットする
        sql001Req.setShoruiSeikyuuNo(dtoReq.getShoruiSeikyuuNo());
        // 枝番(リクエスト.枝番)をセットする
        sql001Req.setEdaban(dtoReq.getEdaban());
        // 取消区分("1")をセットする
        sql001Req.setTorikeshiKbn(TORIKESHI_KBN);
        // ユーザーID(ユーザー共通情報.ユーザーID)をセットする
        sql001Req.setUserId(IfaCommonUtil.getUserAccount().getUserId());
        try {
            dao.updateIfaDocRequestCancelConfirSql001(sql001Req);
        } catch (Exception e) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CMN_CANCELREQUESTPAPER_FAILED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_CANCELREQUESTPAPER_FAILED.key));
            return dtoRes;
        }

        // レスポンスをコントローラーに返却する。
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }

    /**
     * アクションID：A005
     * アクション名：書類請求取消
     * Dtoレスポンス：IfaDocRequestCancelConfirmA005RequestDto
     * Dtoレスポンス：IfaDocRequestCancelConfirmA005ResponseDto
     * Modelリクエスト：IfaDocRequestCancelConfirmSql002RequestModel
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 書類請求取消の際、例外が発生した場合
     */
    public DataList<IfaDocRequestCancelConfirmA005ResponseDto> cancelConfirmA005(IfaDocRequestCancelConfirmA005RequestDto dtoReq)
            throws Exception {
        DataList<IfaDocRequestCancelConfirmA005ResponseDto> dtoRes = new DataList<IfaDocRequestCancelConfirmA005ResponseDto>();
        List<IfaDocRequestCancelConfirmA005ResponseDto> resDtoList = new ArrayList<IfaDocRequestCancelConfirmA005ResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDocRequestInputServiceImpL.cancelConfirmA005");
        }

        // 利用者の口座に対する権限チェックを行う
        InputFct001Dto fct001Req = new InputFct001Dto();
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        // 部店コード(顧客共通情報.部店コード)をセットする
        fct001Req.setButenCode(cc.getButenCode());
        // 口座番号(顧客共通情報.口座番号)をセットする
        fct001Req.setAccountNumber(cc.getAccountNumber());
        // 共通関数FCT001を呼び出す
        OutputFct001Dto fct001Res = new OutputFct001Dto();
        fct001Res = fct001.doCheck(fct001Req);
        // FCT001.対象顧客参照権限有無 == "0"(権限なし)の場合
        if (StringUtils.equals(fct001Res.getTargetCustomerRefAuthFlag(), NO_AUTHORITY)) {
            // レスポンスに権限なしエラー(errors.butenAccountNotExist)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                            new String[] { cc.getButenCode(), cc.getAccountNumber() }));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }
        // FCT001.取引停止フラグ == "1"(取引停止口座)の場合
        if (StringUtils.equals(fct001Res.getTradeSuspendFlag(), SUSPENSION_ACCOUNT)) {
            // レスポンスに取引停止口座エラー(errors.cmn.selectedAccount.outOfService)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_OUT_OF_SERVICE.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_OUT_OF_SERVICE.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }

        // ②ユーザ共通情報.CCSログイン用IDのチェックを行う。
        if (StringUtil.isNullOrEmpty(IfaCommonUtil.getUserAccount().getCcsUserId())) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CMN_CCSID_UNREGISTERED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_CCSID_UNREGISTERED.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }

        // 指定したBM交付の取消を行う。
        IfaDocRequestCancelConfirmSql002RequestModel sql002Req = new IfaDocRequestCancelConfirmSql002RequestModel();
        // BM交付番号(リクエスト.BM交付番号)をセットする
        sql002Req.setBmDeliveryNo(dtoReq.getBmDeliveryNo());
        // ユーザーID(ユーザー共通情報.ユーザーID)をセットする
        sql002Req.setUserId(IfaCommonUtil.getUserAccount().getUserId());
        try {
            dao.updateIfaDocRequestCancelConfirSql002(sql002Req);
        } catch (Exception e) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CANCEL_BM_DELIVERY_FAILED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CANCEL_BM_DELIVERY_FAILED.key));
            return dtoRes;
        }

        // レスポンスをコントローラーに返却する。
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }
}
