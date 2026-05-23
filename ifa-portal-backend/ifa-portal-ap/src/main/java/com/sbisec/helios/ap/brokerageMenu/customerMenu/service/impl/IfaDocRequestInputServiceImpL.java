package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaDocRequestInputDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql008ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql009RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql009ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql010RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql010ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql011RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql011ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql012RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql012ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA007RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA007ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA008RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA008ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA009RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA009ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA013RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA013ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA014RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA014ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaDocRequestInputService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.MCode;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.MCodeService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundFundDocReadHistoryBulkReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundFundDocReadHistoryBulkRes;
import com.sbisec.helios.ap.safe.service.SafeCommonService;
import com.sbisec.helios.ap.safe.service.SafeFundTradeService;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundDocReadHistoryBulk;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundDocReadHistoryBulkApiIn;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundDocReadHistoryBulkApiOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundDocReadHistoryBulkItem;
import com.sbisec.helios.ap.safe.utils.SafeApiUtil;
import com.sbibits.earth.util.StringUtil;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_0704-01 画面名：書類請求入力
 *
 * @author xin.huang
 */
@Component(value = "cmpIfaDocRequestInputService")
public class IfaDocRequestInputServiceImpL implements IfaDocRequestInputService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaDocRequestInputServiceImpL.class);

    @Autowired
    private IfaDocRequestInputDao dao;

    @Autowired
    private Fct001 fct001;

    @Autowired
    private MCodeService mCodeService;

    @Autowired
    private SafeFundTradeService safeFundTradeService;

    @Autowired
    private SafeCommonService safeCommonService;

    /** 権限なし */
    private static final String NO_AUTHORITY = "0";

    /** 取引停止口座 */
    private static final String SUSPENSION_ACCOUNT = "1";

    /** 書類区分(1：書類分類) */
    private static final String SHORUI_KBN_1 = "1";

    /** 書類区分(2：書類) */
    private static final String SHORUI_KBN_2 = "2";

    /** 削除フラグ */
    private static final String SAKUJO_FLG = "0";

    /** 共通スペース */
    private static final String SHARED_SPACE = " ";

    /** 種別 */
    private static final String CODE_TYPE = "98";

    /** 交付区分 */
    private static final String HASSOU_STS_BM = "BM";

    /** BM交付種別 */
    private static final String BM_KOUFU_SHUBETSU_ONE = "1";

    /** BM交付種別 */
    private static final String BM_KOUFU_SHUBETSU_TWO = "2";

    /** 購入可否判定区分 */
    private static final String FUND_MD_BUY_KUBUN_SIX = "6";

    /** データ出力区分 */
    private static final String DATA_OUTPUT_KBN_ZERO = "0";

    /** 目論見書閲覧区分 */
    private static final String PROSPECTUS_ZERO = "0";

    enum MessageId {
        // errors.butenAccountNotExis
        ERRORS_BUTENACCOUNTNOTEXIST("errors.butenAccountNotExist"),
        // errors.cmn.selectedAccount.outOfService
        ERRORS_OUT_OF_SERVICE("errors.cmn.selectedAccount.outOfService"),
        // errors.cmn.getPaperBunruiList.failed
        ERRORS_CMN_GETPAPERBUNRUILIST_FAILED("errors.cmn.getPaperBunruiList.failed"),
        // errors.cmn.getRequestPaperList.failed
        ERRORS_CMN_GETREQUESTPAPERLIST_FAILED("errors.cmn.getRequestPaperList.failed"),
        // errors.cmn.getPaperList.failed
        ERRORS_CMN_GETPAPERLIST_FAILED("errors.cmn.getPaperList.failed"),
        // errors.cmn.getPaperData.failed
        ERRORS_CMN_GETPAPERDATA_FAILED("errors.cmn.getPaperData.failed"),
        // errors.cmn.getPaperData.invalid
        ERRORS_CMN_GETPAPERDATA_INVALID("errors.cmn.getPaperData.invalid"),
        // errors.cmn.getFundData.failed
        ERRORS_CMN_GETFUNDDATA_FAILED("errors.cmn.getFundData.failed"),
        // errors.accurately
        ERRORS_ACCURATELY("errors.accurately"),
        // errors.cmn.selectPapeData.unavailable
        ERRORS_CMN_SELECTPAPEDATA_UNAVAILABLE("errors.cmn.selectPapeData.unavailable"),
        // errors.cmn.getMpBrokerJudge.failed
        ERRORS_CMN_GETMPBROKERJUDGE_FAILED("errors.cmn.getMpBrokerJudge.failed"),
        // errors.cmn.getRequestPaper.failed
        ERRORS_CMN_GETREQUESTPAPER_FAILED("errors.cmn.getRequestPaper.failed"),
        // errors.cmn.ccsid.unregistered
        ERRORS_CMN_CCSID_UNREGISTERED("errors.cmn.ccsid.unregistered"),
        // errors.cmn.faceToFaceFund.unavailable
        ERRORS_CMN_FACETOFACEFUND_UNAVAILABLE("errors.cmn.faceToFaceFund.unavailable"),
        // errors.cmn.getProspectus.failed
        ERRORS_CMN_GETPROSPECTUS_FAILED("errors.cmn.getProspectus.failed"),
        // errors.cmn.getProspectus.invalid
        ERRORS_CMN_GETPROSPECTUS_INVALID("errors.cmn.getProspectus.invalid"),
        // errors.cmn.getEdelivAgreementCount.failed
        ERRORS_CMN_GETEDELIVAGREEMENTCOUNT_FAILED("errors.cmn.getEdelivAgreementCount.failed"),
        // errors.cmn.getEdelivAgreementCount.invalid
        ERRORS_CMN_GETEDELIVAGREEMENTCOUNT_INVALID("errors.cmn.getEdelivAgreementCount.invalid"),
        // errors.cmn.getFundName.failed
        ERRORS_CMN_GETFUNDNAME_FAILED("errors.cmn.getFundName.failed"),
        // errors.cmn.getBmDelivery.failed
        ERRORS_CMN_GETBMDELIVERY_FAILED("errors.cmn.getBmDelivery.failed");

        private String key;

        private MessageId(String key) {
            this.key = key;
        }
    }

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dtoリクエスト：IfaDocRequestInputA001RequestDto
     * Dtoレスポンス：IfaDocRequestInputA001ResponseDto
     * Modelリクエスト：IfaDocRequestInputSql001RequestModel
     * Modelレスポンス：IfaDocRequestInputSql001ResponseModel
     * Modelリクエスト：IfaDocRequestInputSql002RequestModel
     * Modelレスポンス：IfaDocRequestInputSql002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    public DataList<IfaDocRequestInputA001ResponseDto> initializeA001(IfaDocRequestInputA001RequestDto dtoReq)
            throws Exception {

        DataList<IfaDocRequestInputA001ResponseDto> dtoRes = new DataList<IfaDocRequestInputA001ResponseDto>();
        List<IfaDocRequestInputA001ResponseDto> resDtoList = new ArrayList<IfaDocRequestInputA001ResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDocRequestInputServiceImpL.initializeA001");
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

        // ③書類分類リストを取得する。
        IfaDocRequestInputSql001RequestModel sql001Req = new IfaDocRequestInputSql001RequestModel();
        // 書類区分("1")をセットする
        sql001Req.setShoruiKbn(SHORUI_KBN_1);
        // 削除フラグ("0")をセットする
        sql001Req.setSakujoFlg(SAKUJO_FLG);
        DataList<IfaDocRequestInputSql001ResponseModel> sql001Res = dao.selectIfaDocRequestInputSql001(sql001Req);
        // SQLの実行結果が0件の場合はレスポンス結果を処理せず終了する
        if (sql001Res.getDataList().isEmpty()) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CMN_GETPAPERBUNRUILIST_FAILED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_GETPAPERBUNRUILIST_FAILED.key));
            return dtoRes;
        }

        // ④書類請求一覧表示対象データを取得する。
        IfaDocRequestInputSql002RequestModel sql002Req = new IfaDocRequestInputSql002RequestModel();
        // 部店コード(顧客共通情報.部店コード)をセットする
        sql002Req.setButenCode(cc.getButenCode());
        // 口座番号(顧客共通情報.口座番号)をセットする
        sql002Req.setAccountNumber(cc.getAccountNumber());

        DataList<IfaDocRequestInputSql002ResponseModel> sql002Res = new DataList<IfaDocRequestInputSql002ResponseModel>();
        try {
            sql002Res = dao.selectIfaDocRequestInputSql002(sql002Req);
        } catch (Exception e) {
            // 書類請求一覧表示対象データを取得できない場合、エラーを返す。
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CMN_GETREQUESTPAPERLIST_FAILED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_GETREQUESTPAPERLIST_FAILED.key,
                            new String[] { cc.getCustomerCode() }));
            return dtoRes;
        }

        IfaDocRequestInputA001ResponseDto resDto = new IfaDocRequestInputA001ResponseDto();
        // 書類分類リスト
        resDto.setShoruiBunruiSelect(sql001Res.getDataList());
        // 書類請求一覧
        resDto.setShoruiList(sql002Res.getDataList());
        // レスポンスをコントローラーに返却する。
        resDtoList.add(resDto);
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }

    /**
     * アクションID：A002
     * アクション名：分類選択-書類リスト取得
     * Dtoリクエスト：IfaDocRequestInputA002RequestDto
     * Dtoレスポンス：IfaDocRequestInputA002ResponseDto
     * Modelリクエスト：IfaDocRequestInputSql003RequestModel
     * Modelレスポンス：IfaDocRequestInputSql003ResponseModel
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 書類リスト取得の際、例外が発生した場合
     */
    public DataList<IfaDocRequestInputA002ResponseDto> selectShoruiListA002(IfaDocRequestInputA002RequestDto dtoReq)
            throws Exception {
        DataList<IfaDocRequestInputA002ResponseDto> dtoRes = new DataList<IfaDocRequestInputA002ResponseDto>();
        List<IfaDocRequestInputA002ResponseDto> resDtoList = new ArrayList<IfaDocRequestInputA002ResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDocRequestInputServiceImpL.selectShoruiListA002");
        }

        // ③書類リストを取得する。
        IfaDocRequestInputSql003RequestModel sql003Req = new IfaDocRequestInputSql003RequestModel();
        // 書類区分("2")をセットする
        sql003Req.setShoruiKbn(SHORUI_KBN_2);
        // 分類コード(リクエスト.分類コード )をセットする
        sql003Req.setBunruiCd(dtoReq.getBunruiCd());
        // 削除フラグ("0")をセットする
        sql003Req.setSakujoFlg(SAKUJO_FLG);
        DataList<IfaDocRequestInputSql003ResponseModel> sql003Res = dao.selectIfaDocRequestInputSql003(sql003Req);
        // SQLの実行結果が0件の場合はレスポンス結果を処理せず終了する
        if (sql003Res.getDataList().isEmpty()) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CMN_GETPAPERLIST_FAILED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_GETPAPERLIST_FAILED.key));
            return dtoRes;
        }

        IfaDocRequestInputA002ResponseDto resDto = new IfaDocRequestInputA002ResponseDto();
        // 書類リスト
        resDto.setShoruiSelect(sql003Res.getDataList());
        // レスポンスをコントローラーに返却する。
        resDtoList.add(resDto);
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }

    /**
     * アクションID：A003
     * アクション名：書類選択-書類データ取得
     * Dtoリクエスト：IfaDocRequestInputA003RequestDto
     * Dtoレスポンス：IfaDocRequestInputA003ResponseDto
     * Modelリクエスト：IfaDocRequestInputSql004RequestModel
     * Modelレスポンス：IfaDocRequestInputSql004ResponseModel
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 書類データ取得の際、例外が発生した場合
     */
    public DataList<IfaDocRequestInputA003ResponseDto> selectShoruiDateA003(IfaDocRequestInputA003RequestDto dtoReq)
            throws Exception {
        DataList<IfaDocRequestInputA003ResponseDto> dtoRes = new DataList<IfaDocRequestInputA003ResponseDto>();
        List<IfaDocRequestInputA003ResponseDto> resDtoList = new ArrayList<IfaDocRequestInputA003ResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDocRequestInputServiceImpL.selectShoruiDateA003");
        }

        // ②仲介業者種別の値を取得する。
        String brokerShubetsu = null;
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        // ユーザ共通情報.権限コードの値が"1"または"2"の場合：仲介業者種別の値に"0"(SBI証券社員)を設定
        if (PrivId.HEAD_OFFICE.getId().equals(userAccount.getPrivId())
                || PrivId.BRANCH.getId().equals(userAccount.getPrivId())) {
            brokerShubetsu = "0";
        } else {
            // 顧客共通情報.仲介業者コード = "0509"の場合：仲介業者種別の値に"1"(MP(共同店舗含む)外務員)を設定
            CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
            if ("0509".equals(cc.getBrokerCode())) {
                brokerShubetsu = "1";
            } else {
                // MP仲介業者判定
                DataList<IfaDocRequestInputSql008ResponseModel> sql008Res = new DataList<IfaDocRequestInputSql008ResponseModel>();
                IfaDocRequestInputSql008RequestModel sql008Req = new IfaDocRequestInputSql008RequestModel();
                sql008Req.setBrokerCode(cc.getBrokerCode());
                try {
                    sql008Res = dao.selectIfaDocRequestInputSql008(sql008Req);
                } catch (Exception e) {
                    // MP仲介業者判定の結果(件数)が取得できない場合、エラーを返す。
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            MessageId.ERRORS_CMN_GETMPBROKERJUDGE_FAILED.key,
                            IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_GETMPBROKERJUDGE_FAILED.key));
                    return dtoRes;
                }
                // MP仲介業者判定の結果(件数)が"0"の場合：仲介業者種別の値に"2"(その他の仲介業者外務員)を設定
                if (sql008Res.getDataList().size() == 0 || "0".equals(sql008Res.get(0).getBrokerNum())) {
                    brokerShubetsu = "2";
                } else {
                    brokerShubetsu = "1";
                }
            }
        }

        // ②交付区分の表示設定を取得する。
        IfaDocRequestInputSql009RequestModel sql9Req = new IfaDocRequestInputSql009RequestModel();
        // 分類コード
        sql9Req.setShoruiBunruiCd(dtoReq.getBunruiCd());
        // 書類コード
        sql9Req.setShoruiCd(dtoReq.getShoruiCd());
        // 仲介業者種別
        sql9Req.setBrokerShubetsu(brokerShubetsu);
        DataList<IfaDocRequestInputSql009ResponseModel> sql009Res = dao.selectIfaDocRequestInputSql009(sql9Req);
        // 発送ステータス表示種別の値が"1"、"2"、"3"以外の場合、エラーを返す。
        if (!sql009Res.getDataList().isEmpty() && (!"1".equals(sql009Res.get(0).getHassouSts())
                && !"2".equals(sql009Res.get(0).getHassouSts()) && !"3".equals(sql009Res.get(0).getHassouSts()))) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CMN_SELECTPAPEDATA_UNAVAILABLE.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_SELECTPAPEDATA_UNAVAILABLE.key));
            return dtoRes;
        }
        // ②書類データを取得する。
        IfaDocRequestInputSql004RequestModel sql4Req = new IfaDocRequestInputSql004RequestModel();
        // 書類区分("2")をセットする
        sql4Req.setShoruiKbn(SHORUI_KBN_2);
        // 分類コード(リクエスト.分類コード)をセットする
        sql4Req.setBunruiCd(dtoReq.getBunruiCd());
        // 書類コード(リクエスト.書類コード)をセットする
        sql4Req.setShoruiCd(dtoReq.getShoruiCd());
        // 削除フラグ("0")をセットする
        sql4Req.setSakujoFlg(SAKUJO_FLG);
        DataList<IfaDocRequestInputSql004ResponseModel> sql004Res = dao.selectIfaDocRequestInputSql004(sql4Req);
        // SQLの実行結果が0件の場合はレスポンス結果を処理せず終了する
        if (sql004Res.getDataList().isEmpty()) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CMN_GETPAPERDATA_FAILED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_GETPAPERDATA_FAILED.key));
            return dtoRes;
        }

        // ③取得した書類データに対してチェックを行う。
        DataList<IfaDocRequestInputA003ResponseDto> shoruiDateCheck = shoruiDateCheck(sql004Res);
        if (!StringUtil.isNullOrEmpty(shoruiDateCheck.getMessage())) {
            return shoruiDateCheck;
        }

        // ④コメントを取得する。
        List<MCode> mCodeList = mCodeService.getMCodeList(CODE_TYPE, dtoReq.getBunruiCd(), dtoReq.getShoruiCd());
        if (mCodeList != null && mCodeList.size() > 0) {
            sql004Res.get(0).setComment(mCodeList.get(0).getName());
        }

        IfaDocRequestInputA003ResponseDto resDto = new IfaDocRequestInputA003ResponseDto();
        // 発送ステータス表示種別
        if (!sql009Res.getDataList().isEmpty()) {
            sql004Res.get(0).setHassouSts(sql009Res.get(0).getHassouSts());
        }
        // 書類データ
        resDto.setShoruiDate(sql004Res.get(0));

        // レスポンスをコントローラーに返却する。
        resDtoList.add(resDto);
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }

    /**
     * 書類チェック
     *
     * @param sql004Res リクエスト
     * @return DataList
     */
    private DataList<IfaDocRequestInputA003ResponseDto> shoruiDateCheck(
            DataList<IfaDocRequestInputSql004ResponseModel> sql004Res) {
        DataList<IfaDocRequestInputA003ResponseDto> dtoRes = new DataList<IfaDocRequestInputA003ResponseDto>();
        List<IfaDocRequestInputA003ResponseDto> resDtoList = new ArrayList<IfaDocRequestInputA003ResponseDto>();
        IfaDocRequestInputSql004ResponseModel sql004Model = sql004Res.get(0);

        // チェック値(必須入力、サイズ、数値、住所T、必須入力T、サイズT、数値T、部数指定可否)
        // 必須入力
        String[] hissuArr = { "0", "1" };
        // サイズ
        String[] sizeArr = { "0", "1", "2", "3", "4" };
        // 数値
        String[] sujiKomokuArr = { "0", "1" };
        // 住所T
        String[] addressTArr = { "1", "2", "3", "4", "11", "12", "13", "14" };
        // 必須入力T
        String[] hissuTArr = { "0", "1" };
        // サイズT
        String[] sizeTArr = { "0", "1", "2" };
        // 数値T
        String[] sujiKomokuTArr = { "0", "1" };
        // 部数指定可否
        String[] busuuShiteiKahiArr = { "0", "1" };

        // 「内容」に値が設定されている場合
        if (!StringUtil.isNullOrEmpty(sql004Model.getNaiyou())) {
            // 「必須入力(内容)」に値が未設定または"0"、"1"以外の値が設定されている場合、エラーを返す。
            if (!Arrays.asList(hissuArr).contains(sql004Model.getHissuNaiyou())) {
                return createShoruiError(resDtoList);
            }
            // 「サイズ(内容)」に値が未設定または"0"、"1"、"2"、"3"、"4"以外の値が設定されている場合、エラーを返す。
            if (!Arrays.asList(sizeArr).contains(sql004Model.getSizeNaiyou())) {
                return createShoruiError(resDtoList);
            }
            // 「数値(内容)」に値が未設定または"0"、"1"以外の値が設定されている場合、エラーを返す。
            if (!Arrays.asList(sujiKomokuArr).contains(sql004Model.getSujiKomokuNaiyou())) {
                return createShoruiError(resDtoList);
            }
        }
        // 「備考」に値が設定されている場合
        if (!StringUtil.isNullOrEmpty(sql004Model.getBikou())) {
            // 「必須入力(備考)」に値が未設定または"0"、"1"以外の値が設定されている場合、エラーを返す。
            if (!Arrays.asList(hissuArr).contains(sql004Model.getHissuBikou())) {
                return createShoruiError(resDtoList);
            }
            // 「サイズ(備考)」に値が未設定または"0"、"1"、"2"、"3"、"4"以外の値が設定されている場合、エラーを返す。
            if (!Arrays.asList(sizeArr).contains(sql004Model.getSizeBikou())) {
                return createShoruiError(resDtoList);
            }
            // 「数値(備考)」に値が未設定または"0"、"1"以外の値が設定されている場合、エラーを返す。
            if (!Arrays.asList(sujiKomokuArr).contains(sql004Model.getSujiKomokuBikou())) {
                return createShoruiError(resDtoList);
            }
        }
        // 「オプション1」に値が設定されている場合
        if (!StringUtil.isNullOrEmpty(sql004Model.getOption1())) {
            // 「選択11」、「選択12」、「選択13」、「選択14」、「選択15」の全てで値が未設定の場合、エラーを返す。
            if (StringUtil.isNullOrEmpty(sql004Model.getSentaku11())
                    && StringUtil.isNullOrEmpty(sql004Model.getSentaku12())
                    && StringUtil.isNullOrEmpty(sql004Model.getSentaku13())
                    && StringUtil.isNullOrEmpty(sql004Model.getSentaku14())
                    && StringUtil.isNullOrEmpty(sql004Model.getSentaku15())) {
                return createShoruiError(resDtoList);
            }
            // 「必須入力O1」に値が未設定または"0"、"1"以外の値が設定されている場合、エラーを返す。
            if (!Arrays.asList(hissuArr).contains(sql004Model.getHissuO1())) {
                return createShoruiError(resDtoList);
            }
        }
        // 「オプション2」に値が設定されている場合
        if (!StringUtil.isNullOrEmpty(sql004Model.getOption2())) {
            // 「選択21」、「選択22」、「選択23」、「選択24」、「選択25」の全てで値が未設定の場合、エラーを返す。
            if (StringUtil.isNullOrEmpty(sql004Model.getSentaku21())
                    && StringUtil.isNullOrEmpty(sql004Model.getSentaku22())
                    && StringUtil.isNullOrEmpty(sql004Model.getSentaku23())
                    && StringUtil.isNullOrEmpty(sql004Model.getSentaku24())
                    && StringUtil.isNullOrEmpty(sql004Model.getSentaku25())) {
                return createShoruiError(resDtoList);
            }
            // 「必須入力O2」に値が未設定または"0"、"1"以外の値が設定されている場合、エラーを返す。
            if (!Arrays.asList(hissuArr).contains(sql004Model.getHissuO2())) {
                return createShoruiError(resDtoList);
            }
        }
        // 「オプション3」に値が設定されている場合
        if (!StringUtil.isNullOrEmpty(sql004Model.getOption3())) {
            // 「選択31」、「選択32」、「選択33」、「選択34」、「選択35」の全てで値が未設定の場合、エラーを返す。
            if (StringUtil.isNullOrEmpty(sql004Model.getSentaku31())
                    && StringUtil.isNullOrEmpty(sql004Model.getSentaku32())
                    && StringUtil.isNullOrEmpty(sql004Model.getSentaku33())
                    && StringUtil.isNullOrEmpty(sql004Model.getSentaku34())
                    && StringUtil.isNullOrEmpty(sql004Model.getSentaku35())) {
                return createShoruiError(resDtoList);
            }
            // 「必須入力O3」に値が未設定または"0"、"1"以外の値が設定されている場合、エラーを返す。
            if (!Arrays.asList(hissuArr).contains(sql004Model.getHissuO3())) {
                return createShoruiError(resDtoList);
            }
        }
        // 「テキスト1」に値が設定されている場合
        if (!StringUtil.isNullOrEmpty(sql004Model.getTxt1())) {
            // 「住所T1」に"1"、"2"、"3"、"4"、"11"、"12"、"13"、"14"以外の値が設定されている場合、エラーを返す。
            if (!StringUtil.isNullOrEmpty(sql004Model.getAddressT1())
                    && !Arrays.asList(addressTArr).contains(sql004Model.getAddressT1())) {
                return createShoruiError(resDtoList);
            }
            // 「必須入力T1」に値が未設定または"0"、"1"以外の値が設定されている場合、エラーを返す。
            if (!Arrays.asList(hissuTArr).contains(sql004Model.getHissuT1())) {
                return createShoruiError(resDtoList);
            }
            // 「住所T1」に値が未設定の場合
            if (StringUtil.isNullOrEmpty(sql004Model.getAddressT1())) {
                // 「サイズT1」に値が未設定または"0"、"1"、"2"以外の値が設定されている場合、エラーを返す。
                if (!Arrays.asList(sizeTArr).contains(sql004Model.getSizeT1())) {
                    return createShoruiError(resDtoList);
                }
                // 「数値T1」に値が未設定または"0"、"1"以外の値が設定されている場合、エラーを返す。
                if (!Arrays.asList(sujiKomokuTArr).contains(sql004Model.getSujiKomokuT1())) {
                    return createShoruiError(resDtoList);
                }
            }

        }
        // 「テキスト2」に値が設定されている場合
        if (!StringUtil.isNullOrEmpty(sql004Model.getTxt2())) {
            // 「住所T2」に"1"、"2"、"3"、"4"、"11"、"12"、"13"、"14"以外の値が設定されている場合、エラーを返す。
            if (!StringUtil.isNullOrEmpty(sql004Model.getAddressT2())
                    && !Arrays.asList(addressTArr).contains(sql004Model.getAddressT2())) {
                return createShoruiError(resDtoList);
            }
            // 「必須入力T2」に値が未設定または"0"、"1"以外の値が設定されている場合、エラーを返す。
            if (!Arrays.asList(hissuTArr).contains(sql004Model.getHissuT2())) {
                return createShoruiError(resDtoList);
            }
            // 「住所T2」に値が未設定の場合
            if (StringUtil.isNullOrEmpty(sql004Model.getAddressT2())) {
                // 「サイズT2」に値が未設定または"0"、"1"、"2"以外の値が設定されている場合、エラーを返す。
                if (!Arrays.asList(sizeTArr).contains(sql004Model.getSizeT2())) {
                    return createShoruiError(resDtoList);
                }
                // 「数値T2」に値が未設定または"0"、"1"以外の値が設定されている場合、エラーを返す。
                if (!Arrays.asList(sujiKomokuTArr).contains(sql004Model.getSujiKomokuT2())) {
                    return createShoruiError(resDtoList);
                }
            }

        }
        // 「テキスト3」に値が設定されている場合
        if (!StringUtil.isNullOrEmpty(sql004Model.getTxt3())) {
            // 「住所T3」に"1"、"2"、"3"、"4"、"11"、"12"、"13"、"14"以外の値が設定されている場合、エラーを返す。
            if (!StringUtil.isNullOrEmpty(sql004Model.getAddressT3())
                    && !Arrays.asList(addressTArr).contains(sql004Model.getAddressT3())) {
                return createShoruiError(resDtoList);
            }
            // 「必須入力T3」に値が未設定または"0"、"1"以外の値が設定されている場合、エラーを返す。
            if (!Arrays.asList(hissuTArr).contains(sql004Model.getHissuT3())) {
                return createShoruiError(resDtoList);
            }
            // 「住所T3」に値が未設定の場合
            if (StringUtil.isNullOrEmpty(sql004Model.getAddressT3())) {
                // 「サイズT3」に値が未設定または"0"、"1"、"2"以外の値が設定されている場合、エラーを返す。
                if (!Arrays.asList(sizeTArr).contains(sql004Model.getSizeT3())) {
                    return createShoruiError(resDtoList);
                }
                // 「数値T3」に値が未設定または"0"、"1"以外の値が設定されている場合、エラーを返す。
                if (!Arrays.asList(sujiKomokuTArr).contains(sql004Model.getSujiKomokuT3())) {
                    return createShoruiError(resDtoList);
                }
            }

        }
        // 「テキスト4」に値が設定されている場合
        if (!StringUtil.isNullOrEmpty(sql004Model.getTxt4())) {
            // 「住所T4」に"1"、"2"、"3"、"4"、"11"、"12"、"13"、"14"以外の値が設定されている場合、エラーを返す。
            if (!StringUtil.isNullOrEmpty(sql004Model.getAddressT4())
                    && !Arrays.asList(addressTArr).contains(sql004Model.getAddressT4())) {
                return createShoruiError(resDtoList);
            }
            // 「必須入力T4」に値が未設定または"0"、"1"以外の値が設定されている場合、エラーを返す。
            if (!Arrays.asList(hissuTArr).contains(sql004Model.getHissuT4())) {
                return createShoruiError(resDtoList);
            }
            // 「住所T4」に値が未設定の場合
            if (StringUtil.isNullOrEmpty(sql004Model.getAddressT4())) {
                // 「サイズT4」に値が未設定または"0"、"1"、"2"以外の値が設定されている場合、エラーを返す。
                if (!Arrays.asList(sizeTArr).contains(sql004Model.getSizeT4())) {
                    return createShoruiError(resDtoList);
                }
                // 「数値T4」に値が未設定または"0"、"1"以外の値が設定されている場合、エラーを返す。
                if (!Arrays.asList(sujiKomokuTArr).contains(sql004Model.getSujiKomokuT4())) {
                    return createShoruiError(resDtoList);
                }
            }

        }
        // 「テキスト5」に値が設定されている場合
        if (!StringUtil.isNullOrEmpty(sql004Model.getTxt5())) {
            // 「住所T5」に"1"、"2"、"3"、"4"、"11"、"12"、"13"、"14"以外の値が設定されている場合、エラーを返す。
            if (!StringUtil.isNullOrEmpty(sql004Model.getAddressT5())
                    && !Arrays.asList(addressTArr).contains(sql004Model.getAddressT5())) {
                return createShoruiError(resDtoList);
            }
            // 「必須入力T5」に値が未設定または"0"、"1"以外の値が設定されている場合、エラーを返す。
            if (!Arrays.asList(hissuTArr).contains(sql004Model.getHissuT5())) {
                return createShoruiError(resDtoList);
            }
            // 「住所T5」に値が未設定の場合
            if (StringUtil.isNullOrEmpty(sql004Model.getAddressT5())) {
                // 「サイズT5」に値が未設定または"0"、"1"、"2"以外の値が設定されている場合、エラーを返す。
                if (!Arrays.asList(sizeTArr).contains(sql004Model.getSizeT5())) {
                    return createShoruiError(resDtoList);
                }
                // 「数値T5」に値が未設定または"0"、"1"以外の値が設定されている場合、エラーを返す。
                if (!Arrays.asList(sujiKomokuTArr).contains(sql004Model.getSujiKomokuT5())) {
                    return createShoruiError(resDtoList);
                }
            }

        }
        // 「部数指定可否」に値が未設定または"0"、"1"以外の値が設定されている場合、エラーを返す。
        if (!Arrays.asList(busuuShiteiKahiArr).contains(sql004Model.getBusuuShiteiKahi())) {
            return createShoruiError(resDtoList);
        }
        // 「BM交付種別」に"2"が設定されている場合 かつ 「銘柄コード」の値がnullの場合、エラーを返す。
        if ("2".equals(sql004Model.getBmKoufuShubetsu()) && StringUtil.isNullOrEmpty(sql004Model.getMeigaraCd())) {
            return createShoruiError(resDtoList);
        }
        return dtoRes;
    }

    /**
     * 書類エラーを作成
     *
     * @param resDtoList リクエスト
     * @return DataList
     */
    private DataList<IfaDocRequestInputA003ResponseDto> createShoruiError(
            List<IfaDocRequestInputA003ResponseDto> resDtoList) {
        return IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_CMN_GETPAPERDATA_INVALID.key,
                IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_GETPAPERDATA_INVALID.key));
    }

    /**
     * アクションID：A005
     * アクション名：投信銘柄情報取得
     * Dtoレスポンス：IfaDocRequestInputA005RequestDto
     * Dtoレスポンス：IfaDocRequestInputA005ResponseDto
     * Modelリクエスト：IfaDocRequestInputSql006RequestModel
     * Modelレスポンス：IfaDocRequestInputSql006ResponseModel
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 投信銘柄情報取得の際、例外が発生した場合
     */
    public DataList<IfaDocRequestInputA005ResponseDto> selectMFNameA005(IfaDocRequestInputA005RequestDto dtoReq)
            throws Exception {
        DataList<IfaDocRequestInputA005ResponseDto> dtoRes = new DataList<IfaDocRequestInputA005ResponseDto>();
        List<IfaDocRequestInputA005ResponseDto> resDtoList = new ArrayList<IfaDocRequestInputA005ResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDocRequestInputServiceImpL.selectMFNameA005");
        }

        // ④投信銘柄名の取得を行う。
        IfaDocRequestInputSql006RequestModel sql006Req = new IfaDocRequestInputSql006RequestModel();
        // NRIコード(リクエスト.銘柄コード(上4桁)+リクエスト.銘柄コード(下3桁)+" ")をセットする
        sql006Req.setFundNricode(dtoReq.getFundNricode4() + dtoReq.getFundNricode3() + SHARED_SPACE);
        DataList<IfaDocRequestInputSql006ResponseModel> sql006Res = dao.selectIfaDocRequestInputSql006(sql006Req);
        // SQLの実行結果が0件の場合はレスポンス結果を処理せず終了する
        if (sql006Res.getDataList().isEmpty()) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CMN_GETFUNDDATA_FAILED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_GETFUNDDATA_FAILED.key));
            return dtoRes;
        }

        IfaDocRequestInputA005ResponseDto resDto = new IfaDocRequestInputA005ResponseDto();
        // 投信銘柄名
        resDto.setFundName(sql006Res.get(0).getFundName());
        // 銘柄コード(上4桁)
        resDto.setFundNricode4(dtoReq.getFundNricode4());
        // 銘柄コード(下3桁)
        resDto.setFundNricode3(dtoReq.getFundNricode3());
        // 協会コード
        resDto.setFundCode(sql006Res.get(0).getFundCode());
        // 購入可否判定区分
        resDto.setFundMdBuyKubun(sql006Res.get(0).getFundMdBuyKubun());

        // レスポンスをコントローラーに返却する。
        resDtoList.add(resDto);
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }

    /**
     * アクションID：A007
     * アクション名：書類請求確認
     * Dtoレスポンス：IfaDocRequestInputA007RequestDto
     * Dtoレスポンス：IfaDocRequestInputA007ResponseDto
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 書類請求確認の際、例外が発生した場合
     */
    public DataList<IfaDocRequestInputA007ResponseDto> executeConfirmA007(IfaDocRequestInputA007RequestDto dtoReq)
            throws Exception {
        DataList<IfaDocRequestInputA007ResponseDto> dtoRes = new DataList<IfaDocRequestInputA007ResponseDto>();
        List<IfaDocRequestInputA007ResponseDto> resDtoList = new ArrayList<IfaDocRequestInputA007ResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDocRequestInputServiceImpL.executeConfirmA007");
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

        // ③交付区分で"BM交付"を選択 かつリクエスト.BM交付種別が"1" の場合、以下の処理を実行する。
        if (HASSOU_STS_BM.equals(dtoReq.getHassouSts()) && BM_KOUFU_SHUBETSU_ONE.equals(dtoReq.getBmKoufuShubetsu())) {
            // リクエスト.購入可否判定区分が"6"の場合、エラーを返す。
            if (FUND_MD_BUY_KUBUN_SIX.equals(dtoReq.getFundMdBuyKubun())) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        MessageId.ERRORS_CMN_FACETOFACEFUND_UNAVAILABLE.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_FACETOFACEFUND_UNAVAILABLE.key));
                // レスポンスを呼出元に返却する
                return dtoRes;
            } else {
                // リクエスト.購入可否判定区分が"6"以外の場合：投信閲覧履歴一括照会外部チャネル用APIを実行する。
                // Safe Api: /safe/fundTrade/fund/fund_doc_read_history/bulk
                GetFundFundDocReadHistoryBulkReq safeApi001Req = new GetFundFundDocReadHistoryBulkReq();
                safeApi001Req.getHeader().setToken(SafeApiUtil.getToken(cc.getButenCode(), cc.getAccountNumber()));
                FundDocReadHistoryBulkApiIn fundDocReadHistoryBulkApiIn = new FundDocReadHistoryBulkApiIn();
                fundDocReadHistoryBulkApiIn.setDataOutputKbn(DATA_OUTPUT_KBN_ZERO); // "0"：未閲覧分の目論見書のみ出力
                FundDocReadHistoryBulkItem fundDocReadHistoryBulkItem = new FundDocReadHistoryBulkItem();
                fundDocReadHistoryBulkItem.setFundType(SHARED_SPACE); // △:未入力、口数/金額の一括取得
                fundDocReadHistoryBulkItem.setFundCode(dtoReq.getFundCode()); // リクエスト.協会コード
                List<FundDocReadHistoryBulkItem> fundsList = new ArrayList<>();
                fundsList.add(fundDocReadHistoryBulkItem);
                fundDocReadHistoryBulkApiIn.setFunds(fundsList);
                safeApi001Req.setParameter(fundDocReadHistoryBulkApiIn);
                GetFundFundDocReadHistoryBulkRes safeApi001Res = null;
                try {
                    safeApi001Res = safeFundTradeService.getBulkFundDocReadHistory(safeApi001Req);
                } catch (Exception e) {
                    // エラー(API応答なし)の場合、エラーを返す。
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            MessageId.ERRORS_CMN_GETPROSPECTUS_FAILED.key,
                            IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_GETPROSPECTUS_FAILED.key));
                    // レスポンスを呼出元に返却する
                    return dtoRes;
                }
                FundDocReadHistoryBulkApiOut fundDocReadHistoryBulkApiOut = safeApi001Res
                        .getFundDocReadHistoryBulkApiOut();
                dtoRes = safeCommonService.checkSafeBussinessWarning(dtoRes, fundDocReadHistoryBulkApiOut);
                boolean prospectus = false;
                if (fundDocReadHistoryBulkApiOut != null && fundDocReadHistoryBulkApiOut.getFunds() != null
                        && fundDocReadHistoryBulkApiOut.getFunds().size() > 0) {
                    List<FundDocReadHistoryBulk> funds = fundDocReadHistoryBulkApiOut.getFunds();
                    for (FundDocReadHistoryBulk fund : funds) {
                        if (PROSPECTUS_ZERO.equals(fund.getProspectus())) {
                            prospectus = true;
                            break;
                        }
                    }
                } else {
                    // ファンド一覧にデータが存在しない場合、エラーを返す。
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            MessageId.ERRORS_CMN_GETPROSPECTUS_INVALID.key,
                            IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_GETPROSPECTUS_INVALID.key));
                    // レスポンスを呼出元に返却する
                    return dtoRes;
                }
                // ファンド一覧.目論見書閲覧区分の値が"0"のデータが存在する場合、エラーを返す。
                if (prospectus) {
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            MessageId.ERRORS_CMN_GETPROSPECTUS_INVALID.key,
                            IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_GETPROSPECTUS_INVALID.key));
                    // レスポンスを呼出元に返却する
                    return dtoRes;
                }

            }
        }

        // ④交付区分で"BM交付"を選択 かつ リクエスト.BM交付種別が"2" の場合、以下の処理を実行する。
        if (HASSOU_STS_BM.equals(dtoReq.getHassouSts()) && BM_KOUFU_SHUBETSU_TWO.equals(dtoReq.getBmKoufuShubetsu())) {
            // 未閲読目論見書件数取得を実行する。
            IfaDocRequestInputSql010RequestModel sql010Req = new IfaDocRequestInputSql010RequestModel();
            // 銘柄コード
            sql010Req.setFundCode(dtoReq.getMeigaraCd());
            // 部店コード
            sql010Req.setButenCode(cc.getButenCode());
            // 口座番号
            sql010Req.setAccountNumber(cc.getAccountNumber());
            // 法人区分
            sql010Req.setCorporationType(cc.getCorporationType());
            DataList<IfaDocRequestInputSql010ResponseModel> sql010Res = new DataList<IfaDocRequestInputSql010ResponseModel>();
            try {
                sql010Res = dao.selectIfaDocRequestInputSql010(sql010Req);
            } catch (Exception e) {
                // 未閲読目論見書件数が取得できない場合、エラーを返す。
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        MessageId.ERRORS_CMN_GETEDELIVAGREEMENTCOUNT_FAILED.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_GETEDELIVAGREEMENTCOUNT_FAILED.key));
                // レスポンスを呼出元に返却する
                return dtoRes;
            }

            // 未閲読目論見書件数が"0"の場合、エラーを返す。
            if (sql010Res.getDataList().isEmpty() || "0".equals(sql010Res.getDataList().get(0).getDataNum())) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        MessageId.ERRORS_CMN_GETEDELIVAGREEMENTCOUNT_INVALID.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_GETEDELIVAGREEMENTCOUNT_INVALID.key));
                // レスポンスを呼出元に返却する
                return dtoRes;
            }

            // ⑤交付区分で"BM交付"を選択 かつ リクエスト.BM交付種別が"2"の場合は投信銘柄名を取得する。
            IfaDocRequestInputSql011RequestModel sql011Req = new IfaDocRequestInputSql011RequestModel();
            // 銘柄コード
            sql011Req.setFundCode(dtoReq.getMeigaraCd());
            DataList<IfaDocRequestInputSql011ResponseModel> sql011Res = dao.selectIfaDocRequestInputSql011(sql011Req);
            // 投信銘柄名を取得できない場合、エラーを返す。
            if (sql011Res.getDataList().isEmpty()) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        MessageId.ERRORS_CMN_GETFUNDNAME_FAILED.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_GETFUNDNAME_FAILED.key));
                // レスポンスを呼出元に返却する
                return dtoRes;
            }

            IfaDocRequestInputA007ResponseDto resDto = new IfaDocRequestInputA007ResponseDto();
            // 投信銘柄名
            resDto.setFundName(sql011Res.get(0).getFundName());
            // レスポンスをコントローラーに返却する。
            resDtoList.add(resDto);
        }

        if (dtoRes.getErrorLevel() == ErrorLevel.WARNING.getId()) {
            if (resDtoList != null) {
                dtoRes.setDataList(resDtoList);
            }
            dtoRes.setTotalSize(dtoRes.getDataList().size());
            dtoRes.setMaxRownum(dtoRes.getDataList().size());
            dtoRes.setRequestedTime(IfaCommonUtil.getFormattedRequestedTime());
            return dtoRes;
        }

        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }

    /**
     * アクションID：A008
     * アクション名：書類請求取消
     * Dtoレスポンス：IfaDocRequestInputA008RequestDto
     * Dtoレスポンス：IfaDocRequestInputA008ResponseDto
     * Modelリクエスト：IfaDocRequestInputSql007RequestModel
     * Modelレスポンス：IfaDocRequestInputSql007ResponseModel
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 書類請求取消の際、例外が発生した場合
     */
    public DataList<IfaDocRequestInputA008ResponseDto> cancelConfirmA008(IfaDocRequestInputA008RequestDto dtoReq)
            throws Exception {
        DataList<IfaDocRequestInputA008ResponseDto> dtoRes = new DataList<IfaDocRequestInputA008ResponseDto>();
        List<IfaDocRequestInputA008ResponseDto> resDtoList = new ArrayList<IfaDocRequestInputA008ResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDocRequestInputServiceImpL.cancelConfirmA008");
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

        // ③書類請求データを取得する。
        IfaDocRequestInputSql007RequestModel sql007Req = new IfaDocRequestInputSql007RequestModel();
        // 書類請求NO(リクエスト.書類請求NO)をセットする
        sql007Req.setShoruiSeikyuuNo(dtoReq.getShoruiSeikyuuNo());
        // 枝番(リクエスト.枝番)をセットする
        sql007Req.setEdaban(dtoReq.getEdaban());
        DataList<IfaDocRequestInputSql007ResponseModel> sql007Res = dao.selectIfaDocRequestInputSql007(sql007Req);
        // SQLの実行結果が0件の場合はレスポンス結果を処理せず終了する
        if (sql007Res.getDataList().isEmpty()) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CMN_GETREQUESTPAPER_FAILED.key, IfaCommonUtil.getMessage(
                            MessageId.ERRORS_CMN_GETREQUESTPAPER_FAILED.key, new String[] { cc.getCustomerCode() }));
            return dtoRes;
        }

        IfaDocRequestInputA008ResponseDto resDto = new IfaDocRequestInputA008ResponseDto();
        // 書類請求データ
        resDto.setShoruiModel(sql007Res.get(0));
        resDtoList.add(resDto);
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }

    /**
     * アクションID：A009
     * アクション名：書類請求詳細
     * Dtoレスポンス：IfaDocRequestInputA009RequestDto
     * Dtoレスポンス：IfaDocRequestInputA009ResponseDto
     * Modelリクエスト：IfaDocRequestInputSql007RequestModel
     * Modelレスポンス：IfaDocRequestInputSql007ResponseModel
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 書類請求詳細の際、例外が発生した場合
     */
    public DataList<IfaDocRequestInputA009ResponseDto> detailA009(IfaDocRequestInputA009RequestDto dtoReq)
            throws Exception {
        DataList<IfaDocRequestInputA009ResponseDto> dtoRes = new DataList<IfaDocRequestInputA009ResponseDto>();
        List<IfaDocRequestInputA009ResponseDto> resDtoList = new ArrayList<IfaDocRequestInputA009ResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDocRequestInputServiceImpL.detailA009");
        }

        // ①書類請求データを取得する。
        IfaDocRequestInputSql007RequestModel sql007Req = new IfaDocRequestInputSql007RequestModel();
        // 書類請求NO(リクエスト.書類請求NO)をセットする
        sql007Req.setShoruiSeikyuuNo(dtoReq.getShoruiSeikyuuNo());
        // 枝番(リクエスト.枝番)をセットする
        sql007Req.setEdaban(dtoReq.getEdaban());
        DataList<IfaDocRequestInputSql007ResponseModel> sql007Res = dao.selectIfaDocRequestInputSql007(sql007Req);
        // SQLの実行結果が0件の場合はレスポンス結果を処理せず終了する
        if (sql007Res.getDataList().isEmpty()) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CMN_GETREQUESTPAPER_FAILED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_GETREQUESTPAPER_FAILED.key,
                            new String[] { IfaCommonUtil.getCustomerCommon().getCustomerCode() }));
            return dtoRes;
        }

        IfaDocRequestInputA009ResponseDto resDto = new IfaDocRequestInputA009ResponseDto();
        // 書類請求データ
        resDto.setShoruiModel(sql007Res.get(0));
        resDtoList.add(resDto);
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }

    /**
     * アクションID：A013
     * アクション名：BM交付取消
     * Dtoレスポンス：IfaDocRequestInputA013RequestDto
     * Dtoレスポンス：IfaDocRequestInputA013ResponseDto
     * Modelリクエスト：IfaDocRequestInputSql012RequestModel
     * Modelレスポンス：IfaDocRequestInputSql012ResponseModel
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception BM交付取消の際、例外が発生した場合
     */
    public DataList<IfaDocRequestInputA013ResponseDto> cancelConfirmA013(IfaDocRequestInputA013RequestDto dtoReq)
            throws Exception {
        DataList<IfaDocRequestInputA013ResponseDto> dtoRes = new DataList<IfaDocRequestInputA013ResponseDto>();
        List<IfaDocRequestInputA013ResponseDto> resDtoList = new ArrayList<IfaDocRequestInputA013ResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDocRequestInputServiceImpL.cancelConfirmA013");
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

        // ②BM交付データを取得する。
        IfaDocRequestInputSql012RequestModel sql012Req = new IfaDocRequestInputSql012RequestModel();
        // BM交付番号
        sql012Req.setBmDeliveryNo(dtoReq.getBmDeliveryNo());
        DataList<IfaDocRequestInputSql012ResponseModel> sql012Res = dao.selectIfaDocRequestInputSql012(sql012Req);
        // BM交付データを取得できない場合、エラーを返す。
        if (sql012Res.getDataList().isEmpty()) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CMN_GETBMDELIVERY_FAILED.key, IfaCommonUtil.getMessage(
                            MessageId.ERRORS_CMN_GETBMDELIVERY_FAILED.key, new String[] { cc.getCustomerCode() }));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }

        IfaDocRequestInputA013ResponseDto resDto = new IfaDocRequestInputA013ResponseDto();
        // BM交付取消データ
        resDto.setBmShoruiModel(sql012Res.get(0));
        resDtoList.add(resDto);
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }

    /**
     * アクションID：A014
     * アクション名：BM交付詳細
     * Dtoレスポンス：IfaDocRequestInputA014RequestDto
     * Dtoレスポンス：IfaDocRequestInputA014ResponseDto
     * Modelリクエスト：IfaDocRequestInputSql012RequestModel
     * Modelレスポンス：IfaDocRequestInputSql012ResponseModel
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 書類請求詳細の際、例外が発生した場合
     */
    public DataList<IfaDocRequestInputA014ResponseDto> detailA014(IfaDocRequestInputA014RequestDto dtoReq)
            throws Exception {
        DataList<IfaDocRequestInputA014ResponseDto> dtoRes = new DataList<IfaDocRequestInputA014ResponseDto>();
        List<IfaDocRequestInputA014ResponseDto> resDtoList = new ArrayList<IfaDocRequestInputA014ResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDocRequestInputServiceImpL.detailA014");
        }

        // ①BM交付データを取得する。
        IfaDocRequestInputSql012RequestModel sql012Req = new IfaDocRequestInputSql012RequestModel();
        // BM交付番号
        sql012Req.setBmDeliveryNo(dtoReq.getBmDeliveryNo());
        DataList<IfaDocRequestInputSql012ResponseModel> sql012Res = dao.selectIfaDocRequestInputSql012(sql012Req);
        // SQLの実行結果が0件の場合はレスポンス結果を処理せず終了する
        if (sql012Res.getDataList().isEmpty()) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CMN_GETBMDELIVERY_FAILED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_GETBMDELIVERY_FAILED.key,
                            new String[] { IfaCommonUtil.getCustomerCommon().getCustomerCode() }));
            return dtoRes;
        }

        IfaDocRequestInputA014ResponseDto resDto = new IfaDocRequestInputA014ResponseDto();
        // BM交付詳細データ
        resDto.setBmShoruiModel(sql012Res.get(0));
        resDtoList.add(resDto);
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }
}
