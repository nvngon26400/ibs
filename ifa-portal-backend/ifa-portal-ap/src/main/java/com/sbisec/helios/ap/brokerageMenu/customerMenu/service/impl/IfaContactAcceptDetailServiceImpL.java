package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.enums.MethodType;
import com.sbisec.helios.ap.athena.enums.OrderType;
import com.sbisec.helios.ap.athena.enums.SettlementMethod;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaContactAcceptDetailDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql008ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql009ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql010ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql011ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql012ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.JpToushinOrderBaseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.TJpOrderBaseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.TbAdditionalOrderIfaBaseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactAcceptDetailA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactAcceptDetailA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactAcceptDetailCommonResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.FcoDepositType;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.FxTradeServiceType;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.IfaBuySell;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.TcoDepositType;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.TcoOrderStatus;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.TjoTradeKbn;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.TradeMethodType;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaContactAcceptDetailService;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaContactUtil;
import com.sbisec.helios.ap.ccsApi.protocol.ShowDealingDetailIFAReq;
import com.sbisec.helios.ap.ccsApi.service.CcsApiService;
import com.sbisec.helios.ap.ccsApi.service.dto.ShowDealingDetailIFAIn;
import com.sbisec.helios.ap.ccsApi.service.dto.ShowDealingDetailIFAOut;
import com.sbisec.helios.ap.ccsApi.util.CcsApiErrUtil;
import com.sbisec.helios.ap.common.enums.JrIsaContractType;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.service.MCodeService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

/**
 * 画面ID：SUB0202_0106-02
 * 画面名：接触履歴受付詳細
 * 
 * @author 趙韫慧
 * 2025/04/22 新規作成
 */
@Component(value = "cmpIfaContactAcceptDetailService")
public class IfaContactAcceptDetailServiceImpL implements IfaContactAcceptDetailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaBuyingPowerDomesticServiceImpL.class);

    // 受付シート: 受付詳細
    private static final String ACCEPT_SHEET_UKETSUKE = "受付詳細";

    // 大分類
    private static final String BIG_CLASS_ORDER = "注文";
    private static final String BIG_CLASS_FOREIGN_SPOT = "外国現物";
    private static final String BIG_CLASS_FOREIGN_CREDIT = "外国信用";
    private static final String BIG_CLASS_FOREIGN_STORE = "外国店頭";
    private static final String BIG_CLASS_EXCHANGE_ORDER = "為替注文";

    // ステータス
    private static final String STATUS_CORRECTION = "訂正注文有";
    private static final String STATUS_CANCELLATION = "取消";

    // 内容:内容は以下を参照
    private static final String CONTENT_SANSYOU = "内容は以下を参照";

    private static final String MORE_THAN = "以上";
    private static final String BELOW = "以下";

    // 為替取引注文テーブル（EXCHANGE_ORDER）
    // 為替取引注文状況'(取消)
    private static final String FX_ORDER_STATUS = "2";

    // ハイフン
    private static final String HYPHEN = "-";

    @Autowired
    private IfaContactAcceptDetailDao dao;
    @Autowired
    MCodeService mcodeService;
    @Autowired
    private IfaContactUtil ifaContactCommonUtil;
    @Autowired
    private CcsApiService g_ccsApiService;

    /**
     * アクションID：A001
     * アクション名：初期化
     * リクエスト：IfaContactA001DtoRequest
     * レスポンス：IfaContactA001DtoResponse
     * 
     * @param dtoReq dtoリクエスト
     * @return 接触履歴受付詳細情報
     * @exception Exception 異常
     */
    public DataList<IfaContactAcceptDetailA001ResponseDto> initializeA001(IfaContactAcceptDetailA001RequestDto dtoReq)
            throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaContactAcceptDetailServiceImpL.initializeA001");
        }

        // 接触履歴受付詳細リスト
        List<IfaContactAcceptDetailCommonResponseDto> ifaContactAcceptDetailInfoList = new ArrayList<IfaContactAcceptDetailCommonResponseDto>();
        // 参照元履歴区分
        String referenceKbn = dtoReq.getReferenceKbn();
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        // 部店コード
        String butenCode = cc.getButenCode();
        // 口座番号
        String accountNumber = cc.getAccountNumber();
        // 顧客コード
        String customerId = cc.getCustomerCode();
        // 記録日時(yyyy/MM/dd)
        String recordDate = DateUtil.dateFormat(dtoReq.getRecordDate(), DateUtil.SEPARATED_YYYYMMDD,
                DateUtil.SEPARATED_YYYYMMDD);
        // 作成者
        String createUser = dtoReq.getCreateUser();
        // ジュニアISA契約区分
        String jrIsaContractType = cc.getJrIsaContractType();

        CcsApiErrUtil ccsApiErr = new CcsApiErrUtil();

        switch (referenceKbn) {
            case IfaContactUtil.REFERENCE_KBN_4:
                // 国内株式当日注文履歴詳細(国内株式現物)取得して設定する。
                setIfaContactAcceptDetailSql002InfoList1(customerId, recordDate, createUser,
                        jrIsaContractType, ifaContactAcceptDetailInfoList);
                // 国内株式当日注文履歴詳細(国内株式信用新規(返済))取得して設定する。
                setIfaContactAcceptDetailSql002InfoList2(customerId, recordDate, createUser,
                        jrIsaContractType, ifaContactAcceptDetailInfoList);
                // 国内株式当日注文履歴詳細(国内株式現引現渡)取得して設定する。
                setIfaContactAcceptDetailSql002InfoList3(customerId, recordDate, createUser,
                        jrIsaContractType, ifaContactAcceptDetailInfoList);
                // 国内投信当日注文履歴詳細(国内投信（一般）)取得して設定する。
                setIfaContactAcceptDetailSql003InfoList1(customerId, recordDate, createUser,
                        jrIsaContractType, ifaContactAcceptDetailInfoList);
                // 国内投信当日注文履歴詳細(国内投信（累投）)取得して設定する。
                setIfaContactAcceptDetailSql003InfoList2(customerId, recordDate, createUser,
                        jrIsaContractType, ifaContactAcceptDetailInfoList);
                // その他余力拘束履歴詳細取得して設定する。
                setIfaContactAcceptDetailSql004InfoList(customerId, recordDate, createUser, jrIsaContractType,
                        ifaContactAcceptDetailInfoList);
                break;
            case IfaContactUtil.REFERENCE_KBN_7:
                // 外株委託注文接触履歴受付詳細取得して設定する。
                setIfaContactAcceptDetailSql005InfoList(butenCode, accountNumber, recordDate, createUser, jrIsaContractType,
                        ifaContactAcceptDetailInfoList);
                break;
            case IfaContactUtil.REFERENCE_KBN_8:
                // 外株店頭注文接触履歴受付詳細取得して設定する。
                setIfaContactAcceptDetailSql006InfoList(butenCode, accountNumber, recordDate, createUser, jrIsaContractType,
                        ifaContactAcceptDetailInfoList);
                break;
            case IfaContactUtil.REFERENCE_KBN_9:
                // 為替取引接触履歴受付詳細取得して設定する。
                setIfaContactAcceptDetailSql007InfoList(butenCode, accountNumber, recordDate, createUser,
                        ifaContactAcceptDetailInfoList);
                break;
            case IfaContactUtil.REFERENCE_KBN_1:
                // 遷移元画面パラメータ.参照元履歴区分＝1(CCS履歴)の場合
                // ユーザ共通情報.CCSログイン用ID＝未設定(Null または空文字)のチェック
                ccsApiErr.fromCcsIdWarn();
                if (ccsApiErr.getErrLevel() != 0) {
                    break;
                }
                // CCSAPIを呼出し、CCS接触履歴詳細リストを取得する
                List<ShowDealingDetailIFAOut.ContactDetail> p_ccsApiOutList = getCcsApiOutList(dtoReq, ccsApiErr);
                // CCS API正常の場合、項目編集を行う
                if (ccsApiErr.getErrLevel() == 0) {
                    setIfaContactAcceptDetailCcsApiInfoList(p_ccsApiOutList, ifaContactAcceptDetailInfoList);
                }
                break;
        }
        // 編集後の接触履歴受付詳細リストを取得する.
        List<IfaContactAcceptDetailCommonResponseDto> IfaContactAcceptDetailResList = getIfaContactResList(
                ifaContactAcceptDetailInfoList, referenceKbn);
        // 接触履歴受付詳細1行目をセットする
        setIfaContactResListFristRow(IfaContactAcceptDetailResList, dtoReq);
        // 戻り値をセットする
        List<IfaContactAcceptDetailA001ResponseDto> a001ResDtoList = new ArrayList<IfaContactAcceptDetailA001ResponseDto>();
        IfaContactAcceptDetailA001ResponseDto a001ResDto = new IfaContactAcceptDetailA001ResponseDto();
        a001ResDto.setContactAcceptDetailInfoList(IfaContactAcceptDetailResList);
        a001ResDtoList.add(a001ResDto);
        return ifaContactCommonUtil.createDataList(a001ResDtoList, ccsApiErr);
    }

    /**
     * 国内株式注文履歴詳細(国内株式現物)取得して設定する.
     *
     * @param customerId                 顧客コード
     * @param recordDate                 記録日時
     * @param createUser                 作成者
     * @param jrIsaContractType          ジュニアISA契約区分
     * @param IfaContactAcceptDetailList 接触履歴受付詳細リスト
     * @return
     */
    private void setIfaContactAcceptDetailSql002InfoList1(String customerId, String recordDate,
            String createUser, String jrIsaContractType,
            List<IfaContactAcceptDetailCommonResponseDto> ifaContactAcceptDetailList) throws Exception {

        // 国内株式注文履歴詳細取得SQLのリクエストモデル
        IfaContactAcceptDetailSql002RequestModel selSql002Req = new IfaContactAcceptDetailSql002RequestModel();
        // 顧客コード
        selSql002Req.setCustomerId(customerId);
        // 記録日時
        selSql002Req.setRecordDate(recordDate);
        // 作成者
        selSql002Req.setCreateUser(createUser);
        // 取引種別リスト(1:現物買付 2:現物売却)
        List<String> tradeKbnList = Arrays.asList(TjoTradeKbn.STOCK_BUY.getId(), TjoTradeKbn.STOCK_SALE.getId());
        // 取引種別リスト
        selSql002Req.setTradeKbnList(tradeKbnList);

        // 国内株式注文履歴詳細を取得する。
        DataList<IfaContactAcceptDetailSql002ResponseModel> selSql002Res = dao
                .selectIfaContactAcceptDetailSql002(selSql002Req);

        if (selSql002Res != null && CollectionUtils.isNotEmpty(selSql002Res.getDataList())) {

            // 接触履歴受付詳細モデル
            IfaContactAcceptDetailCommonResponseDto a002ContactDetailListDto = null;

            // IFA注文番号毎の1番目レコードの注文状況情報を取得する
            Map<String, List<String>> orderStatusInfoMap = getOrderStatusInfoMapFor002();

            for (IfaContactAcceptDetailSql002ResponseModel sql002ResponseModel : selSql002Res.getDataList()) {

                // 接触履歴受付詳細モデル
                a002ContactDetailListDto = new IfaContactAcceptDetailCommonResponseDto();

                // 大分類
                a002ContactDetailListDto.setBigClass(BIG_CLASS_ORDER);
                // 中分類
                a002ContactDetailListDto
                        .setMidClass(ifaContactCommonUtil.getTradeKbnNm(sql002ResponseModel.getTradeKbn()));
                // 記録日時
                a002ContactDetailListDto.setRecordDate(sql002ResponseModel.getCreateTime());
                // ステータス
                a002ContactDetailListDto
                        .setStatus(getStatusFor002(orderStatusInfoMap, sql002ResponseModel.getIfaOrderNo()));
                // 内容
                TJpOrderBaseModel tJpOrderBaseModel = new TJpOrderBaseModel();
                BeanUtils.copyProperties(sql002ResponseModel, tJpOrderBaseModel);
                a002ContactDetailListDto.setContents(ifaContactCommonUtil.getContentsFor002(tJpOrderBaseModel,
                        jrIsaContractType, IfaContactUtil.IFA_CONTACT_ACCEPT_DETAIL));

                // 接触履歴情報リストに接触履歴情報を追加する。
                ifaContactAcceptDetailList.add(a002ContactDetailListDto);
            }
        }
    }

    /**
     * 国内株式注文履歴詳細(国内株式信用新規(返済))取得して設定する.
     * 
     * @param customerId                 顧客コード
     * @param recordDate                 記録日時
     * @param createUser                 作成者
     * @param jrIsaContractType          ジュニアISA契約区分
     * @param ifaContactAcceptDetailList 接触履歴受付詳細リスト
     * @return
     */
    private void setIfaContactAcceptDetailSql002InfoList2(String customerId, String recordDate,
            String createUser, String jrIsaContractType,
            List<IfaContactAcceptDetailCommonResponseDto> ifaContactAcceptDetailList) throws Exception {

        // 国内株式注文履歴詳細取得SQLのリクエストモデル
        IfaContactAcceptDetailSql002RequestModel selSql002Req = new IfaContactAcceptDetailSql002RequestModel();
        // 顧客コード
        selSql002Req.setCustomerId(customerId);
        // 記録日時
        selSql002Req.setRecordDate(recordDate);
        // 作成者
        selSql002Req.setCreateUser(createUser);
        // 取引種別リスト(3:信用新規買、4:信用新規売、5:信用返済買、6:信用返済売)
        List<String> tradeKbnList = Arrays.asList(TjoTradeKbn.NEW_BUY.getId(), TjoTradeKbn.NEW_SALE.getId(),
                TjoTradeKbn.REPAYMENT_BUY.getId(), TjoTradeKbn.REPAYMENT_SALE.getId());
        // 取引種別リスト
        selSql002Req.setTradeKbnList(tradeKbnList);

        // 国内株式注文履歴詳細を取得する。
        DataList<IfaContactAcceptDetailSql002ResponseModel> selSql002Res = dao
                .selectIfaContactAcceptDetailSql002(selSql002Req);

        if (selSql002Res != null && CollectionUtils.isNotEmpty(selSql002Res.getDataList())) {

            // 接触履歴受付詳細モデル
            IfaContactAcceptDetailCommonResponseDto a002ContactDetailListDto = null;

            // IFA注文番号毎の1番目レコードの注文状況情報を取得する
            Map<String, List<String>> orderStatusInfoMap = getOrderStatusInfoMapFor002();

            for (IfaContactAcceptDetailSql002ResponseModel sql002ResponseModel : selSql002Res.getDataList()) {

                // 接触履歴受付詳細モデル
                a002ContactDetailListDto = new IfaContactAcceptDetailCommonResponseDto();

                // 大分類
                a002ContactDetailListDto.setBigClass(BIG_CLASS_ORDER);
                // 中分類
                a002ContactDetailListDto.setMidClass(ifaContactCommonUtil.getMidClass(sql002ResponseModel.getTradeKbn(),
                        sql002ResponseModel.getPaymentLimit(), sql002ResponseModel.getDailyCreditKbn(),
                        sql002ResponseModel.getPaymentLimitDate(), sql002ResponseModel.getDateKbn()));
                // 記録日時
                a002ContactDetailListDto.setRecordDate(sql002ResponseModel.getCreateTime());
                // ステータス
                a002ContactDetailListDto
                        .setStatus(getStatusFor002(orderStatusInfoMap, sql002ResponseModel.getIfaOrderNo()));
                // 内容
                TJpOrderBaseModel tJpOrderBaseModel = new TJpOrderBaseModel();
                BeanUtils.copyProperties(sql002ResponseModel, tJpOrderBaseModel);
                a002ContactDetailListDto.setContents(ifaContactCommonUtil.getContentsFor003(tJpOrderBaseModel,
                        IfaContactUtil.IFA_CONTACT_ACCEPT_DETAIL));

                // 接触履歴情報リストに接触履歴情報を追加する。
                ifaContactAcceptDetailList.add(a002ContactDetailListDto);
            }
        }
    }

    /**
     * 国内株式注文履歴詳細(国内株式現引現渡)取得して設定する.
     * 
     * @param customerId                 顧客コード
     * @param recordDate                 記録日時
     * @param createUser                 作成者
     * @param jrIsaContractType          ジュニアISA契約区分
     * @param ifaContactAcceptDetailList 接触履歴受付詳細リスト
     * @return
     */
    private void setIfaContactAcceptDetailSql002InfoList3(String customerId, String recordDate,
            String createUser, String jrIsaContractType,
            List<IfaContactAcceptDetailCommonResponseDto> ifaContactAcceptDetailList) throws Exception {

        // 国内株式注文履歴詳細取得SQLのリクエストモデル
        IfaContactAcceptDetailSql002RequestModel selSql002Req = new IfaContactAcceptDetailSql002RequestModel();
        // 顧客コード
        selSql002Req.setCustomerId(customerId);
        // 記録日時
        selSql002Req.setRecordDate(recordDate);
        // 作成者
        selSql002Req.setCreateUser(createUser);
        // 取引種別リスト(7:現渡、8:現引)
        List<String> tradeKbnList = Arrays.asList(TjoTradeKbn.PRESENT.getId(), TjoTradeKbn.CURRENT.getId());
        // 取引種別リスト
        selSql002Req.setTradeKbnList(tradeKbnList);

        // 国内株式注文履歴詳細を取得する。
        DataList<IfaContactAcceptDetailSql002ResponseModel> selSql002Res = dao
                .selectIfaContactAcceptDetailSql002(selSql002Req);

        if (selSql002Res != null && CollectionUtils.isNotEmpty(selSql002Res.getDataList())) {

            // 接触履歴受付詳細モデル
            IfaContactAcceptDetailCommonResponseDto a002ContactDetailListDto = null;

            // IFA注文番号毎の1番目レコードの注文状況情報を取得する
            Map<String, String> orderStatusInfoMap = getOrderStatusInfoMapFor002Step3();

            for (IfaContactAcceptDetailSql002ResponseModel sql002ResponseModel : selSql002Res.getDataList()) {

                // 接触履歴受付詳細モデル
                a002ContactDetailListDto = new IfaContactAcceptDetailCommonResponseDto();

                // 大分類
                a002ContactDetailListDto.setBigClass(BIG_CLASS_ORDER);
                // 中分類
                a002ContactDetailListDto.setMidClass(ifaContactCommonUtil.getMidClass(sql002ResponseModel.getTradeKbn(),
                        sql002ResponseModel.getPaymentLimit(), sql002ResponseModel.getDailyCreditKbn(),
                        sql002ResponseModel.getPaymentLimitDate(), sql002ResponseModel.getDateKbn()));
                // 記録日時
                a002ContactDetailListDto.setRecordDate(sql002ResponseModel.getCreateTime());
                // ステータス
                a002ContactDetailListDto
                        .setStatus(getStatus(orderStatusInfoMap.get(sql002ResponseModel.getIfaOrderNo())));
                // 内容
                TJpOrderBaseModel tJpOrderBaseModel = new TJpOrderBaseModel();
                BeanUtils.copyProperties(sql002ResponseModel, tJpOrderBaseModel);
                a002ContactDetailListDto.setContents(ifaContactCommonUtil.getContentsFor015(tJpOrderBaseModel,
                        IfaContactUtil.IFA_CONTACT_ACCEPT_DETAIL));
                // 接触履歴情報リストに接触履歴情報を追加する。
                ifaContactAcceptDetailList.add(a002ContactDetailListDto);
            }
        }
    }

    /**
     * 国内投信注文履歴詳細(国内投信（一般）)取得して設定する.
     * 
     * @param customerId                 顧客コード
     * @param recordDate                 記録日時
     * @param createUser                 作成者
     * @param jrIsaContractType          ジュニアISA契約区分
     * @param IfaContactAcceptDetailList 接触履歴受付詳細リスト
     * @return
     */
    private void setIfaContactAcceptDetailSql003InfoList1(String customerId, String recordDate,
            String createUser, String jrIsaContractType,
            List<IfaContactAcceptDetailCommonResponseDto> ifaContactAcceptDetailList) throws Exception {

        // 国内投信注文履歴詳細取得SQLのリクエストモデル
        IfaContactAcceptDetailSql003RequestModel selSql003Req = new IfaContactAcceptDetailSql003RequestModel();
        // 顧客コード
        selSql003Req.setCustomerId(customerId);
        // 記録日時
        selSql003Req.setRecordDate(recordDate);
        // 作成者
        selSql003Req.setCreateUser(createUser);
        // 取引種別リスト(0:購入(一般)、3:買取（一般）、7:解約(一般))
        List<String> tradeTypeList = Arrays.asList(IfaContactUtil.TradeType.TRADE_TYPE_0,
                IfaContactUtil.TradeType.TRADE_TYPE_3, IfaContactUtil.TradeType.TRADE_TYPE_7);
        // 取引種別リスト
        selSql003Req.setTradeTypeList(tradeTypeList);

        // 国内投信注文履歴詳細を取得する。
        DataList<IfaContactAcceptDetailSql003ResponseModel> selSql003Res = dao
                .selectIfaContactAcceptDetailSql003(selSql003Req);

        if (selSql003Res != null && CollectionUtils.isNotEmpty(selSql003Res.getDataList())) {

            // 接触履歴受付詳細モデル
            IfaContactAcceptDetailCommonResponseDto a003ContactDetailListDto = null;

            // IFA注文番号毎の1番目レコードの注文状況情報を取得する
            Map<String, String> orderStatusInfoMap = getOrderStatusInfoMapFor003();

            for (IfaContactAcceptDetailSql003ResponseModel sql003ResponseModel : selSql003Res.getDataList()) {

                // 接触履歴受付詳細モデル
                a003ContactDetailListDto = new IfaContactAcceptDetailCommonResponseDto();

                // 大分類
                a003ContactDetailListDto.setBigClass(BIG_CLASS_ORDER);
                // 中分類
                a003ContactDetailListDto
                        .setMidClass(ifaContactCommonUtil.getSomeJtoTradeKbnNm(sql003ResponseModel.getTradeKbn()));
                // 記録日時
                a003ContactDetailListDto.setRecordDate(sql003ResponseModel.getCreateTime());
                // ステータス
                a003ContactDetailListDto
                        .setStatus(getStatus(orderStatusInfoMap.get(sql003ResponseModel.getIfaOrderNo())));
                // 内容
                JpToushinOrderBaseModel jpToushinOrderBaseModel = new JpToushinOrderBaseModel();
                BeanUtils.copyProperties(sql003ResponseModel, jpToushinOrderBaseModel);
                a003ContactDetailListDto.setContents(ifaContactCommonUtil.getContentsFor016(jpToushinOrderBaseModel,
                        jrIsaContractType, IfaContactUtil.IFA_CONTACT_ACCEPT_DETAIL));

                // 接触履歴情報リストに接触履歴情報を追加する。
                ifaContactAcceptDetailList.add(a003ContactDetailListDto);
            }
        }
    }

    /**
     * 国内投信注文履歴詳細(国内投信（累投）)取得して設定する.
     * 
     * @param customerId                 顧客コード
     * @param recordDate                 記録日時
     * @param createUser                 作成者
     * @param jrIsaContractType          ジュニアISA契約区分
     * @param ifaContactAcceptDetailList 接触履歴受付詳細リスト
     * @return
     */
    private void setIfaContactAcceptDetailSql003InfoList2(String customerId, String recordDate,
            String createUser, String jrIsaContractType,
            List<IfaContactAcceptDetailCommonResponseDto> ifaContactAcceptDetailList) throws Exception {

        // 国内投信注文履歴詳細取得SQLのリクエストモデル
        IfaContactAcceptDetailSql003RequestModel selSql003Req = new IfaContactAcceptDetailSql003RequestModel();
        // 顧客コード
        selSql003Req.setCustomerId(customerId);
        // 記録日時
        selSql003Req.setRecordDate(recordDate);
        // 作成者
        selSql003Req.setCreateUser(createUser);
        // 取引種別リスト(1:購入（累投）、4:買取（累投）、6:全額買取、8:解約（累投）、10:全額解約)
        List<String> tradeTypeList = Arrays.asList(IfaContactUtil.TradeType.TRADE_TYPE_1,
                IfaContactUtil.TradeType.TRADE_TYPE_4, IfaContactUtil.TradeType.TRADE_TYPE_6,
                IfaContactUtil.TradeType.TRADE_TYPE_8, IfaContactUtil.TradeType.TRADE_TYPE_10);
        // 取引種別リスト
        selSql003Req.setTradeTypeList(tradeTypeList);

        // 国内投信注文履歴詳細を取得する。
        DataList<IfaContactAcceptDetailSql003ResponseModel> selSql003Res = dao
                .selectIfaContactAcceptDetailSql003(selSql003Req);

        if (selSql003Res != null && CollectionUtils.isNotEmpty(selSql003Res.getDataList())) {

            // 接触履歴受付詳細モデル
            IfaContactAcceptDetailCommonResponseDto a003ContactDetailListDto = null;

            // IFA注文番号毎の1番目レコードの注文状況情報を取得する
            Map<String, String> orderStatusInfoMap = getOrderStatusInfoMapFor003();

            // 同一のIFA注文番号内で最大情報を取得する
            Map<String, List<String>> maxInfoMap = ifaContactCommonUtil.getJtoMaxInfoMap();

            for (IfaContactAcceptDetailSql003ResponseModel sql003ResponseModel : selSql003Res.getDataList()) {

                // 接触履歴受付詳細モデル
                a003ContactDetailListDto = new IfaContactAcceptDetailCommonResponseDto();

                // 大分類
                a003ContactDetailListDto.setBigClass(BIG_CLASS_ORDER);
                // 中分類
                a003ContactDetailListDto
                        .setMidClass(ifaContactCommonUtil.getJtoTradeKbnNm(sql003ResponseModel.getTradeKbn()));
                // 記録日時
                a003ContactDetailListDto.setRecordDate(sql003ResponseModel.getCreateTime());
                // ステータス
                a003ContactDetailListDto
                        .setStatus(getStatus(orderStatusInfoMap.get(sql003ResponseModel.getIfaOrderNo())));
                // 内容
                JpToushinOrderBaseModel jpToushinOrderBaseModel = new JpToushinOrderBaseModel();
                BeanUtils.copyProperties(sql003ResponseModel, jpToushinOrderBaseModel);
                a003ContactDetailListDto.setContents(ifaContactCommonUtil.getContentsFor017(jpToushinOrderBaseModel,
                        jrIsaContractType, maxInfoMap.get(sql003ResponseModel.getIfaOrderNo()),
                        IfaContactUtil.IFA_CONTACT_ACCEPT_DETAIL));

                // 接触履歴情報リストに接触履歴情報を追加する。
                ifaContactAcceptDetailList.add(a003ContactDetailListDto);
            }
        }
    }

    /**
     * その他余力拘束履歴詳細取得して設定する.
     * 
     * @param customerId                 顧客コード
     * @param recordDate                 記録日時
     * @param createUser                 作成者
     * @param jrIsaContractType          ジュニアISA契約区分
     * @param ifaContactAcceptDetailList 接触履歴受付詳細リスト
     * @return
     */
    private void setIfaContactAcceptDetailSql004InfoList(String customerId, String recordDate,
            String createUser, String jrIsaContractType,
            List<IfaContactAcceptDetailCommonResponseDto> ifaContactAcceptDetailList) throws Exception {

        // その他余力拘束履歴詳細情報取得SQLのリクエストモデル
        IfaContactAcceptDetailSql004RequestModel selSql004Req = new IfaContactAcceptDetailSql004RequestModel();
        // 顧客コード
        selSql004Req.setCustomerId(customerId);
        // 記録日時
        selSql004Req.setRecordDate(recordDate);
        // 作成者
        selSql004Req.setCreateUser(createUser);

        // その他余力拘束履歴詳細を取得する。
        DataList<IfaContactAcceptDetailSql004ResponseModel> selSql004Res = dao
                .selectIfaContactAcceptDetailSql004(selSql004Req);

        if (selSql004Res != null && CollectionUtils.isNotEmpty(selSql004Res.getDataList())) {

            // 接触履歴受付詳細モデル
            IfaContactAcceptDetailCommonResponseDto a004ContactDetailListDto = null;

            // トランザクションID毎の1番目レコードの注文状況情報を取得する
            Map<String, String> torikeshiKbnInfoMap = getTorikeshiKbnInfoMapFor004();

            // 同一のトランザクションID内で最大情報を取得する
            Map<String, List<String>> maxInfoMap = ifaContactCommonUtil.getTaoiMaxInfoMap();

            for (IfaContactAcceptDetailSql004ResponseModel sql004ResponseModel : selSql004Res.getDataList()) {

                // 接触履歴受付詳細モデル
                a004ContactDetailListDto = new IfaContactAcceptDetailCommonResponseDto();

                // 大分類
                a004ContactDetailListDto.setBigClass(BIG_CLASS_ORDER);
                // 中分類
                a004ContactDetailListDto.setMidClass(IfaContactUtil.MID_CLASS);
                // 記録日時
                a004ContactDetailListDto.setRecordDate(sql004ResponseModel.getTourokuNichiji());
                // ステータス
                a004ContactDetailListDto
                        .setStatus(getStatusFor004(torikeshiKbnInfoMap.get(sql004ResponseModel.getTranId())));
                // 内容
                TbAdditionalOrderIfaBaseModel tbAdditionalOrderIfaBaseModel = new TbAdditionalOrderIfaBaseModel();
                BeanUtils.copyProperties(sql004ResponseModel, tbAdditionalOrderIfaBaseModel);
                a004ContactDetailListDto.setContents(ifaContactCommonUtil.getContentsFor018(
                        tbAdditionalOrderIfaBaseModel, jrIsaContractType,
                        maxInfoMap.get(sql004ResponseModel.getTranId()), IfaContactUtil.IFA_CONTACT_ACCEPT_DETAIL));

                ifaContactAcceptDetailList.add(a004ContactDetailListDto);
            }
        }
    }

    /**
     * 外株委託注文接触履歴受付詳細取得して設定する.
     * 
     * @param butenCode                  部店コード
     * @param accountNumber              口座番号
     * @param recordDate                 記録日時
     * @param createUser                 作成者
     * @param jrIsaContractType          ジュニアISA契約区分
     * @param ifaContactAcceptDetailList 接触履歴受付詳細リスト
     * @return
     */
    private void setIfaContactAcceptDetailSql005InfoList(String butenCode, String accountNumber, String recordDate,
            String createUser, String jrIsaContractType,
            List<IfaContactAcceptDetailCommonResponseDto> ifaContactAcceptDetailList) throws Exception {

        // 外株委託注文接触履歴受付詳細取得SQLのリクエストモデル
        IfaContactAcceptDetailSql005RequestModel selSql005Req = new IfaContactAcceptDetailSql005RequestModel();
        // 部店コード
        selSql005Req.setButenCode(butenCode);
        // 口座番号
        selSql005Req.setAccountNumber(accountNumber);
        // 記録日時
        selSql005Req.setRecordDate(recordDate);
        // 作成者
        selSql005Req.setCreateUser(createUser);

        // 外株委託注文接触履歴受付詳細を取得する。
        DataList<IfaContactAcceptDetailSql005ResponseModel> selSql005Res = dao
                .selectIfaContactAcceptDetailSql005(selSql005Req);

        if (selSql005Res != null && CollectionUtils.isNotEmpty(selSql005Res.getDataList())) {

            // 接触履歴受付詳細モデル
            IfaContactAcceptDetailCommonResponseDto a005ContactDetailListDto = null;

            // IFA注文番号毎の1番目レコードの注文種別区分情報を取得する
            Map<String, String> orderTypeInfoMap = getOrderTypeInfoMapFor005();

            for (IfaContactAcceptDetailSql005ResponseModel sql005ResponseModel : selSql005Res.getDataList()) {

                // 接触履歴受付詳細モデル
                a005ContactDetailListDto = new IfaContactAcceptDetailCommonResponseDto();

                // 大分類
                a005ContactDetailListDto.setBigClass(getBigClassFor005(sql005ResponseModel.getMethodType()));
                // 中分類
                a005ContactDetailListDto.setMidClass(getMidClassFor005(sql005ResponseModel));
                // 記録日時
                a005ContactDetailListDto.setRecordDate(sql005ResponseModel.getCreateTime());
                // ステータス
                a005ContactDetailListDto
                        .setStatus(getStatusFor005(orderTypeInfoMap.get(sql005ResponseModel.getIfaOrderNo())));
                // 内容
                a005ContactDetailListDto.setContents(getContentsFor005(sql005ResponseModel, jrIsaContractType));

                ifaContactAcceptDetailList.add(a005ContactDetailListDto);
            }
        }
    }

    /**
     * 外株店頭注文接触履歴受付詳細取得して設定する.
     * 
     * @param butenCode                  部店コード
     * @param accountNumber              口座番号
     * @param recordDate                 記録日時
     * @param createUser                 作成者
     * @param jrIsaContractType          ジュニアISA契約区分
     * @param ifaContactAcceptDetailList 接触履歴受付詳細リスト
     * @return
     */
    private void setIfaContactAcceptDetailSql006InfoList(String butenCode, String accountNumber, String recordDate,
            String createUser, String jrIsaContractType,
            List<IfaContactAcceptDetailCommonResponseDto> ifaContactAcceptDetailList) throws Exception {

        // 外株店頭注文接触履歴受付詳細取得SQLのリクエストモデル
        IfaContactAcceptDetailSql006RequestModel selSql006Req = new IfaContactAcceptDetailSql006RequestModel();
        // 部店コード
        selSql006Req.setButenCode(butenCode);
        // 口座番号
        selSql006Req.setAccountNumber(accountNumber);
        // 記録日時
        selSql006Req.setRecordDate(recordDate);
        // 作成者
        selSql006Req.setCreateUser(createUser);

        // 外株店頭注文接触履歴受付詳細を取得する。
        DataList<IfaContactAcceptDetailSql006ResponseModel> selSql006Res = dao
                .selectIfaContactAcceptDetailSql006(selSql006Req);

        if (selSql006Res != null && CollectionUtils.isNotEmpty(selSql006Res.getDataList())) {

            // 接触履歴受付詳細モデル
            IfaContactAcceptDetailCommonResponseDto a006ContactDetailListDto = null;

            for (IfaContactAcceptDetailSql006ResponseModel sql006ResponseModel : selSql006Res.getDataList()) {

                // 接触履歴受付詳細モデル
                a006ContactDetailListDto = new IfaContactAcceptDetailCommonResponseDto();

                // 大分類
                a006ContactDetailListDto.setBigClass(BIG_CLASS_FOREIGN_STORE);
                // 中分類
                a006ContactDetailListDto.setMidClass(getBuySellNm(sql006ResponseModel.getTradeType()));
                // 記録日時
                a006ContactDetailListDto.setRecordDate(sql006ResponseModel.getCreateTime());
                // ステータス
                a006ContactDetailListDto
                        .setStatus(TcoOrderStatus.valueOfId(sql006ResponseModel.getOrderStatus()) != null
                                ? TcoOrderStatus.valueOfId(sql006ResponseModel.getOrderStatus()).getLabel()
                                : StringUtil.EMPTY_STRING);
                // 内容
                a006ContactDetailListDto.setContents(getContentsFor006(sql006ResponseModel, jrIsaContractType));

                ifaContactAcceptDetailList.add(a006ContactDetailListDto);
            }
        }
    }

    /**
     * 為替取引接触履歴受付詳細取得して設定する.
     * 
     * @param butenCode                  部店コード
     * @param accountNumber              口座番号
     * @param recordDate                 記録日時
     * @param createUser                 作成者
     * @param ifaContactAcceptDetailList 接触履歴受付詳細リスト
     * @return
     */
    private void setIfaContactAcceptDetailSql007InfoList(String butenCode, String accountNumber, String recordDate,
            String createUser, List<IfaContactAcceptDetailCommonResponseDto> ifaContactAcceptDetailList)
            throws Exception {

        // 為替取引接触履歴受付詳細取得SQLのリクエストモデル
        IfaContactAcceptDetailSql007RequestModel selSql007Req = new IfaContactAcceptDetailSql007RequestModel();
        // 部店コード
        selSql007Req.setButenCode(butenCode);
        // 口座番号
        selSql007Req.setAccountNumber(accountNumber);
        // 記録日時
        selSql007Req.setRecordDate(recordDate);
        // 作成者
        selSql007Req.setCreateUser(createUser);

        // 為替取引接触履歴受付詳細を取得する。
        DataList<IfaContactAcceptDetailSql007ResponseModel> selSql007Res = dao
                .selectIfaContactAcceptDetailSql007(selSql007Req);

        if (selSql007Res != null && CollectionUtils.isNotEmpty(selSql007Res.getDataList())) {

            // 接触履歴受付詳細モデル
            IfaContactAcceptDetailCommonResponseDto a007ContactDetailListDto = null;

            // 同一の数据キー毎の1番目レコードの為替取引注文状況情報を取得する
            Map<String, String> fxOrderStatusInfoMap = getFxOrderStatusInfoMapFor007();

            for (IfaContactAcceptDetailSql007ResponseModel sql007ResponseModel : selSql007Res.getDataList()) {

                // 接触履歴受付詳細モデル
                a007ContactDetailListDto = new IfaContactAcceptDetailCommonResponseDto();

                // 大分類
                a007ContactDetailListDto.setBigClass(BIG_CLASS_EXCHANGE_ORDER);
                // 中分類
                a007ContactDetailListDto.setMidClass(getBuySellNm(sql007ResponseModel.getTradeType()));
                // 記録日時
                a007ContactDetailListDto.setRecordDate(sql007ResponseModel.getCreateTime());
                // ステータス
                a007ContactDetailListDto
                        .setStatus(getStatusFor007(fxOrderStatusInfoMap, sql007ResponseModel.getTradeType(),
                                sql007ResponseModel.getCurrencyCode(), sql007ResponseModel.getFxOrderAmount(),
                                sql007ResponseModel.getFxSpecificAccountCode(), sql007ResponseModel.getOrderNo()));
                // 内容
                a007ContactDetailListDto.setContents(getContentsFor007(sql007ResponseModel));

                ifaContactAcceptDetailList.add(a007ContactDetailListDto);
            }
        }
    }

    /**
     * 編集後の接触履歴受付詳細リストを取得する.
     * 
     * @param ifaContactAcceptDetailList 接触履歴受付詳細リスト
     * @param referenceKbn               参照元履歴区分
     * @return 接触履歴受付詳細リスト(編集後)
     */
    private List<IfaContactAcceptDetailCommonResponseDto> getIfaContactResList(
            List<IfaContactAcceptDetailCommonResponseDto> ifaContactAcceptDetailList, String referenceKbn) {

        List<IfaContactAcceptDetailCommonResponseDto> ifaContactResList = new ArrayList<IfaContactAcceptDetailCommonResponseDto>();
        if (ifaContactAcceptDetailList != null && CollectionUtils.isNotEmpty(ifaContactAcceptDetailList)) {
            // 参照元履歴区分:CCS履歴:1  以外の場合、以下ロジックを行う。
            if (!IfaContactUtil.REFERENCE_KBN_1.equals(referenceKbn)) {
                // 1.結果リストをまとめて記録日時の降順にソートする
                List<IfaContactAcceptDetailCommonResponseDto> ifaContactSortedList = ifaContactAcceptDetailList.stream()
                        .sorted(Comparator.comparing(IfaContactAcceptDetailCommonResponseDto::getRecordDate).reversed())
                        .collect(Collectors.toList());
                // 2.明細は最大100件まで表示する
                ifaContactResList = new ArrayList<IfaContactAcceptDetailCommonResponseDto>(
                        new ArrayList<IfaContactAcceptDetailCommonResponseDto>(
                                ifaContactSortedList.subList(0, Math.min(ifaContactSortedList.size(), 100))));
            // 参照元履歴区分: CCS履歴:1 の場合、接触履歴受付詳細リストをそのまま戻します。
            } else {
                return ifaContactAcceptDetailList;
            }
        }
        return ifaContactResList;
    }

    /**
     * 大分類を取得する.
     * 
     * @param methodType 仕法区分
     * @return bigClass 大分類
     */
    private String getBigClassFor005(String methodType) throws Exception {

        // 大分類
        String bigClass = StringUtil.EMPTY_STRING;

        if (MethodType.SPOT.getIfaCd().equals(methodType)) {
            // 仕法区分＝'0'(現物)の場合、 大分類("外国現物")
            bigClass = BIG_CLASS_FOREIGN_SPOT;
        } else if (MethodType.CREDIT_NEW.getIfaCd().equals(methodType)
                || MethodType.CREDIT_REPAY.getIfaCd().equals(methodType)) {
            // 仕法区分＝'10'(信用新規)、'11'(信用返済)の場合、 大分類("外国信用")
            bigClass = BIG_CLASS_FOREIGN_CREDIT;
        }
        return bigClass;
    }

    /**
     * 中分類を取得する.
     * 
     * @param ifaContactAcceptDetailSql005ResponseModel 外株委託注文接触履歴受付詳細情報
     * @return midClass 中分類
     */
    private String getMidClassFor005(
            IfaContactAcceptDetailSql005ResponseModel ifaContactAcceptDetailSql005ResponseModel) throws Exception {

        // 中分類
        String midClass = StringUtil.EMPTY_STRING;

        // 仕法区分
        String methodType = ifaContactAcceptDetailSql005ResponseModel.getMethodType();
        // 売買区分
        String tradeType = ifaContactAcceptDetailSql005ResponseModel.getTradeType();

        if (MethodType.SPOT.getIfaCd().equals(methodType)) {
            // 仕法区分＝'0'(現物)の場合、
            midClass = getBuySellNm(tradeType);
        } else if (MethodType.CREDIT_NEW.getIfaCd().equals(methodType)
                || MethodType.CREDIT_REPAY.getIfaCd().equals(methodType)) {
            // 仕法区分＝'10'(信用新規)、'11'(信用返済)の場合、

            // 受渡方法(中分類作成用)を取得する
            String methodTypeStr = StringUtil.EMPTY_STRING;
            if (MethodType.CREDIT_NEW.getIfaCd().equals(methodType)) {
                methodTypeStr = "新規";
            } else if (MethodType.CREDIT_REPAY.getIfaCd().equals(methodType)) {
                methodTypeStr = "返済";
            }

            // 売買区分(中分類作成用)を取得する
            String tradeTypeStr = StringUtil.EMPTY_STRING;
            if (IfaBuySell.SELL.getId().equals(tradeType)) {
                tradeTypeStr = "売";
            } else if (IfaBuySell.BUY.getId().equals(tradeType)) {
                tradeTypeStr = "買";
            }
            // 中分類 = 受渡方法+売買区分
            midClass = methodTypeStr + tradeTypeStr;
        }

        return midClass;
    }

    /**
     * 内容を取得する.
     * 
     * @param ifaContactAcceptDetailSql005ResponseModel 外株委託注文接触履歴受付詳細情報
     * @param jrIsaContractType                         ジュニアISA契約区分
     * @return contents 内容
     */
    private String getContentsFor005(
            IfaContactAcceptDetailSql005ResponseModel ifaContactAcceptDetailSql005ResponseModel,
            String jrIsaContractType) throws Exception {

        // 内容
        String contents = StringUtil.EMPTY_STRING;

        // 1.価格(内容作成用)を取得する
        // 価格(内容作成用)
        String priceDisp = StringUtil.EMPTY_STRING;
        // 価格条件区分
        String tradeMethodType = ifaContactAcceptDetailSql005ResponseModel.getTradeMethodType();
        // 指値
        String limitPrice = ifaContactAcceptDetailSql005ResponseModel.getLimitPrice();
        // 発火条件価格
        String stopPrice = ifaContactAcceptDetailSql005ResponseModel.getStopPrice();
        // 通貨コード
        String currencyCode = ifaContactAcceptDetailSql005ResponseModel.getCurrencyCode();

        // 1.1トリガ(価格作成用)を取得する
        // トリガ(価格作成用)
        String buySellDisp = StringUtil.EMPTY_STRING;
        // 売買区分
        String tradeType = ifaContactAcceptDetailSql005ResponseModel.getTradeType();
        if (IfaBuySell.BUY.getId().equals(tradeType)) {
            // 売買区分が3:買付の場合、"以上"
            buySellDisp = MORE_THAN;
        } else if (IfaBuySell.SELL.getId().equals(tradeType)) {
            // 売買区分が1:売却の場合、"以下"
            buySellDisp = BELOW;
        }

        // 1.2価格(内容作成用)を取得する
        if (TradeMethodType.LIMIT.getId().equals(tradeMethodType)) {
            // 価格条件区分が1:指値の場合、"指値:"+trim(trim(trailing '.' from trim('0' from to_char(nvl(A.指値,'0'),'999,999,990.99999999'))))+A.通貨コード
            priceDisp = "指値:" + limitPrice + currencyCode;
        } else if (TradeMethodType.MARKET.getId().equals(tradeMethodType)) {
            // 価格条件区分が2:成行の場合、"成行"
            priceDisp = TradeMethodType.MARKET.getLabel();
        } else if (TradeMethodType.STOP_PRICE_LIMIT.getId().equals(tradeMethodType)) {
            // 価格条件区分が3:逆指値（指値）の場合、
            // "現在値が"+trim(trim(trailing '.' from trim('0' from to_char(nvl(A.発火条件価格,'0'),'999,999,990.99999999'))))+A.通貨コード+トリガ+"になった時点で"+trim(trim(trailing '.' from trim('0' from to_char(nvl(A.指値,'0'),'999,999,990.99999999'))))+A.通貨コード+"で発注"
            priceDisp = "現在値が" + stopPrice + currencyCode + buySellDisp + "になった時点で" + limitPrice + currencyCode + "で発注";
        } else if (TradeMethodType.STOP_PRICE_MARKET.getId().equals(tradeMethodType)) {
            // 価格条件区分が4:逆指値（成行）の場合、
            // "現在値が"+trim(trim(trailing '.' from trim('0' from to_char(nvl(A.発火条件価格,'0'),'999,999,990.99999999'))))+A.通貨コード+トリガ+"になった時点で成行で発注"
            priceDisp = "現在値が" + stopPrice + currencyCode + buySellDisp + "になった時点で成行で発注";
        }

        // 2.注文数量(内容作成用)を取得する
        // 注文数量
        String orderQuantity = ifaContactAcceptDetailSql005ResponseModel.getOrderQuantity();
        // 注文数量(内容作成用)を取得する
        String quantityDisp = ifaContactCommonUtil.getQuantityDisp(orderQuantity);

        // 3.預り区分(内容作成用)を取得する
        // 預り区分
        String depositType = ifaContactAcceptDetailSql005ResponseModel.getDepositType();
        // 預り区分(内容作成用)
        String depositTypeDisp = getDepositTypeDispFor005(jrIsaContractType, depositType);

        // 4.決済方法(内容作成用)を取得する
        // 決済方法(内容作成用)
        String settlementTypeDisp = StringUtil.EMPTY_STRING;
        // 決済区分
        String settlementType = ifaContactAcceptDetailSql005ResponseModel.getSettlementType();
        // 決済区分が1:"円貨決済" 2:"外貨決済"
        settlementTypeDisp = SettlementMethod.getByIfaCd(settlementType).getName();

        // 5.ユーザー名(内容作成用)
        String userNm = ifaContactAcceptDetailSql005ResponseModel.getUserNm();

        // 6.ティッカーコード(内容作成用)
        String brandCode = ifaContactAcceptDetailSql005ResponseModel.getBrandCode();

        // 7.銘柄名(内容作成用)
        String brandName = ifaContactAcceptDetailSql005ResponseModel.getBrandName();

        // 内容 = B.ユーザー名+"　"+A.ティッカーコード+"　"+A.銘柄名+"　"+価格+"　"+注文数量+"　"+預り区分+"  "+決済方法
        contents = userNm + ifaContactCommonUtil.getDefaultVal(brandCode)
                + ifaContactCommonUtil.getDefaultVal(brandName) + ifaContactCommonUtil.getDefaultVal(priceDisp)
                + ifaContactCommonUtil.getDefaultVal(quantityDisp) + ifaContactCommonUtil.getDefaultVal(depositTypeDisp)
                + ifaContactCommonUtil.getDefaultValWithFullSpace(settlementTypeDisp);

        return contents;
    }

    /**
     * 内容を取得する.
     * 
     * @param ifaContactAcceptDetailSql006ResponseModel 外株店頭注文接触履歴受付詳細情報
     * @param jrIsaContractType                         ジュニアISA契約区分
     * @return contents 内容
     */
    private String getContentsFor006(
            IfaContactAcceptDetailSql006ResponseModel ifaContactAcceptDetailSql006ResponseModel,
            String jrIsaContractType) throws Exception {

        // 内容
        String contents = StringUtil.EMPTY_STRING;

        // 1.注文数量(内容作成用)を取得する
        // 注文数量
        String orderCount = ifaContactAcceptDetailSql006ResponseModel.getOrderCount();
        // 注文株数(内容作成用)を取得する
        String orderCountDisp = getOrderCountyDisp(orderCount);

        // 2.預り区分(内容作成用)を取得する
        // 預り区分
        String depositType = ifaContactAcceptDetailSql006ResponseModel.getDepositType();
        // 預り区分(内容作成用)
        String depositTypeDisp = getDepositTypeDispFor006(jrIsaContractType, depositType);

        // 3.決済方法(内容作成用)を取得する
        // 決済方法(内容作成用)
        String settlementTypeDisp = StringUtil.EMPTY_STRING;
        // 決済通貨
        String settlementType = ifaContactAcceptDetailSql006ResponseModel.getKessaiTuka();
        // 決済区分が 2:"外貨決済"
        if (SettlementMethod.FOREIGN_SETTLEMENT.getIfaCd().equals(settlementType)) {
            settlementTypeDisp = SettlementMethod.FOREIGN_SETTLEMENT.getName();
        }

        // 5.ユーザー名(内容作成用)
        String userNm = ifaContactAcceptDetailSql006ResponseModel.getUserNm();

        // 6.ティッカーコード(内容作成用)
        String brandCode = ifaContactAcceptDetailSql006ResponseModel.getBrandCode();

        // 7.銘柄名(内容作成用)
        String brandName = ifaContactAcceptDetailSql006ResponseModel.getBrandName();

        // 内容 = C.ユーザー名+"　"+A.銘柄コード+"　"+B.銘柄名+"　"+注文数量+"　"+預り区分+"  "+決済方法
        contents = userNm + ifaContactCommonUtil.getDefaultVal(brandCode)
                + ifaContactCommonUtil.getDefaultVal(brandName) + ifaContactCommonUtil.getDefaultVal(orderCountDisp)
                + ifaContactCommonUtil.getDefaultVal(depositTypeDisp)
                + ifaContactCommonUtil.getDefaultValWithFullSpace(settlementTypeDisp);

        return contents;
    }

    /**
     * 内容を取得する.
     * 
     * @param ifaContactAcceptDetailSql007ResponseModel 為替取引接触履歴受付詳細情報
     * @return contents 内容
     */
    private String getContentsFor007(
            IfaContactAcceptDetailSql007ResponseModel ifaContactAcceptDetailSql007ResponseModel) throws Exception {

        // 内容
        String contents = StringUtil.EMPTY_STRING;

        // 1.為替取引サービス種別(内容作成用)を取得する
        String fxTradeServiceTypeDisp = StringUtil.EMPTY_STRING;
        // 為替取引サービス種別
        // 1:"IFA" 2:"ﾘﾃｰﾙ"
        String fxTradeServiceType = ifaContactAcceptDetailSql007ResponseModel.getFxTradeServiceType();
        fxTradeServiceTypeDisp = FxTradeServiceType.valueOfId(fxTradeServiceType) != null
                ? FxTradeServiceType.valueOfId(fxTradeServiceType).getLabel()
                : StringUtil.EMPTY_STRING;

        // 2.ユーザー名(内容作成用)
        String userNmDisp = ifaContactAcceptDetailSql007ResponseModel.getUserNm();

        // 3.通貨名(内容作成用)
        String currencyNameDisp = ifaContactAcceptDetailSql007ResponseModel.getCurrencyName();

        // 4.為替注文金額(内容作成用)
        String fxOrderAmountDisp = ifaContactAcceptDetailSql007ResponseModel.getFxOrderAmount();

        // 5.通貨コード(内容作成用)
        String currencyCodeDisp = ifaContactAcceptDetailSql007ResponseModel.getCurrencyCode();

        // 6.為替レート(内容作成用)
        String exchangeRateDisp = ifaContactAcceptDetailSql007ResponseModel.getExchangeRate();

        // 内容 = B.ユーザー名+"　"+A.通貨名+"　"+A.為替注文金額+A.通貨コード+"　"+A.為替レート+"円/"+A.通貨コード＋為替サービス種別
        contents = userNmDisp + ifaContactCommonUtil.getDefaultVal(currencyNameDisp)
                + ifaContactCommonUtil.getDefaultVal(fxOrderAmountDisp) + currencyCodeDisp
                + ifaContactCommonUtil.getDefaultVal(exchangeRateDisp) + "円/" + currencyCodeDisp
                + fxTradeServiceTypeDisp;

        return contents;
    }

    /**
     * 預り区分(内容作成用)を取得する.
     * 
     * @param jrIsaContractType ジュニアISA契約区分
     * @param depositType       預り区分
     * @return 預り区分(内容作成用)
     */
    private String getDepositTypeDispFor005(String jrIsaContractType, String depositType) throws Exception {

        // 預り区分(内容作成用)
        String depositTypeDisp = StringUtil.EMPTY_STRING;

        if (JrIsaContractType.CONTRACT.getId().equals(jrIsaContractType)) {
            // ジュニアISA契約区分='1'(契約済)の場合、預り区分の値毎に設定
            // H:"総合口座－NISA(成長投資枠)"、4:"総合口座－旧NISA"、2:"総合口座－特定"、J:"ジュニアNISA口座－継続NISA"、6:"ジュニアNISA口座－特定"、5:"ジュニアNISA口座－一般"、7:"ジュニアNISA口座－旧NISA"、その他の場合:"総合口座－一般"
            if (FcoDepositType.FCO_DEPOSIT_TYPE_H.getId().equals(depositType)) {
                // H:"総合口座－NISA(成長投資枠)"
                depositTypeDisp = IfaContactUtil.INTEGRATED_ACCOUNT + IfaContactUtil.FULL_HYPHEN
                        + FcoDepositType.FCO_DEPOSIT_TYPE_H.getLabel();
            } else if (FcoDepositType.FCO_DEPOSIT_TYPE_4.getId().equals(depositType)) {
                // 4:"総合口座－旧NISA"
                depositTypeDisp = IfaContactUtil.INTEGRATED_ACCOUNT + IfaContactUtil.FULL_HYPHEN
                        + FcoDepositType.FCO_DEPOSIT_TYPE_4.getLabel();
            } else if (FcoDepositType.FCO_DEPOSIT_TYPE_2.getId().equals(depositType)) {
                // 2:"総合口座－特定"
                depositTypeDisp = IfaContactUtil.INTEGRATED_ACCOUNT + IfaContactUtil.FULL_HYPHEN
                        + FcoDepositType.FCO_DEPOSIT_TYPE_2.getLabel();
            } else if (FcoDepositType.FCO_DEPOSIT_TYPE_J.getId().equals(depositType)) {
                // J:"ジュニアNISA口座－継続NISA"
                depositTypeDisp = FcoDepositType.FCO_DEPOSIT_TYPE_J.getLabel();
            } else if (FcoDepositType.FCO_DEPOSIT_TYPE_6.getId().equals(depositType)) {
                // 6:"ジュニアNISA口座－特定"
                depositTypeDisp = FcoDepositType.FCO_DEPOSIT_TYPE_6.getLabel();
            } else if (FcoDepositType.FCO_DEPOSIT_TYPE_5.getId().equals(depositType)) {
                // 5:"ジュニアNISA口座－一般"
                depositTypeDisp = FcoDepositType.FCO_DEPOSIT_TYPE_5.getLabel();
            } else if (FcoDepositType.JTO_DEPOSIT_TYPE_7.getId().equals(depositType)) {
                // 7:"ジュニアNISA口座－旧NISA"
                depositTypeDisp = FcoDepositType.JTO_DEPOSIT_TYPE_7.getLabel();
            } else {
                // その他の場合:"総合口座－一般"
                depositTypeDisp = IfaContactUtil.INTEGRATED_ACCOUNT + IfaContactUtil.FULL_HYPHEN
                        + FcoDepositType.FCO_DEPOSIT_TYPE_OTHER.getLabel();
            }
        } else {
            // ジュニアISA契約区分≠'1'(契約済)の場合、預り区分の値毎に設定
            // H:"NISA(成長投資枠)"、4:"旧NISA"、2:"特定"、その他の場合:"一般"
            if (FcoDepositType.FCO_DEPOSIT_TYPE_H.getId().equals(depositType)) {
                // H:"NISA(成長投資枠)"
                depositTypeDisp = FcoDepositType.FCO_DEPOSIT_TYPE_H.getLabel();
            } else if (FcoDepositType.FCO_DEPOSIT_TYPE_4.getId().equals(depositType)) {
                // 4:"旧NISA"
                depositTypeDisp = FcoDepositType.FCO_DEPOSIT_TYPE_4.getLabel();
            } else if (FcoDepositType.FCO_DEPOSIT_TYPE_2.getId().equals(depositType)) {
                // 2:"特定"
                depositTypeDisp = FcoDepositType.FCO_DEPOSIT_TYPE_2.getLabel();
            } else {
                // その他の場合:"一般"
                depositTypeDisp = FcoDepositType.FCO_DEPOSIT_TYPE_OTHER.getLabel();
            }
        }
        return depositTypeDisp;
    }

    /**
     * 預り区分(内容作成用)を取得する.
     * 
     * @param jrIsaContractType ジュニアISA契約区分
     * @param depositType       預り区分
     * @return 預り区分(内容作成用)
     */
    private String getDepositTypeDispFor006(String jrIsaContractType, String depositType) throws Exception {

        // 預り区分(内容作成用)
        String depositTypeDisp = StringUtil.EMPTY_STRING;

        if (JrIsaContractType.CONTRACT.getId().equals(jrIsaContractType)) {
            // ジュニアISA契約区分='1'(契約済)の場合、預り区分の値毎に設定
            // 2:"総合口座－特定"、その他の場合:"総合口座－一般"
            if (TcoDepositType.TCO_DEPOSIT_TYPE_2.getId().equals(depositType)) {
                // 2:"総合口座－特定"
                depositTypeDisp = IfaContactUtil.INTEGRATED_ACCOUNT + IfaContactUtil.FULL_HYPHEN
                        + TcoDepositType.TCO_DEPOSIT_TYPE_2.getLabel();
            } else {
                // その他の場合:"総合口座－一般"
                depositTypeDisp = IfaContactUtil.INTEGRATED_ACCOUNT + IfaContactUtil.FULL_HYPHEN
                        + TcoDepositType.TCO_DEPOSIT_TYPE_1.getLabel();
            }
        } else {
            // ジュニアISA契約区分≠'1'(契約済)の場合、預り区分の値毎に設定
            // 2:"特定"、その他の場合:"一般"
            if (TcoDepositType.TCO_DEPOSIT_TYPE_2.getId().equals(depositType)) {
                // 2:"特定"
                depositTypeDisp = TcoDepositType.TCO_DEPOSIT_TYPE_2.getLabel();
            } else {
                // その他の場合:"一般"
                depositTypeDisp = TcoDepositType.TCO_DEPOSIT_TYPE_1.getLabel();
            }
        }
        return depositTypeDisp;
    }

    /**
     * 売買区分名を取得する.
     * 
     * @param buySellCd 売買区分
     * @return 売買区分名
     */
    private String getBuySellNm(String buySellCd) throws Exception {

        // 預り区分(内容作成用)
        String buySellNm = StringUtil.EMPTY_STRING;

        buySellNm = IfaBuySell.valueOfId(buySellCd) != null ? IfaBuySell.valueOfId(buySellCd).getLabel()
                : StringUtil.EMPTY_STRING;
        return buySellNm;
    }

    /**
     * ステータスを取得する.
     * 
     * @param orderStatusDataMap IFA注文番号毎の1番目レコードの注文状況情報
     * @param ifaOrderNo         IFA注文番号
     * @return ステータス
     */
    private String getStatusFor002(Map<String, List<String>> orderStatusInfoMap, String ifaOrderNo) throws Exception {

        // ステータス
        String status = StringUtil.EMPTY_STRING;

        // IFA注文番号毎の1番目レコードの注文状況情報リスト
        List<String> orderStatusInfoList = orderStatusInfoMap.get(ifaOrderNo);

        // 1番目レコードの注文状況
        String orderStatus = StringUtil.EMPTY_STRING;
        // 2番目レコードの注文状況
        String preOrderStatus = StringUtil.EMPTY_STRING;

        if (orderStatusInfoList != null && CollectionUtils.isNotEmpty(orderStatusInfoList)) {
            // 1番目レコードの注文状況
            orderStatus = orderStatusInfoList.get(0);
            // 2番目レコードの注文状況
            preOrderStatus = orderStatusInfoList.get(1);
        }

        if (IfaContactUtil.OrderStatus.TJO_ORDER_STATUS_0.equals(orderStatus)) {
            // 1番目レコードのA.注文状況＝'0'(新規(発注)):"-"
            status = IfaContactUtil.HALF_HYPHEN;
        } else if (IfaContactUtil.OrderStatus.TJO_ORDER_STATUS_1.equals(orderStatus)) {
            // 1番目レコードのA.注文状況＝'1'(訂正):"訂正注文有"
            status = STATUS_CORRECTION;
        } else if (IfaContactUtil.OrderStatus.TJO_ORDER_STATUS_2.equals(orderStatus)) {
            if (IfaContactUtil.OrderStatus.TJO_ORDER_STATUS_1.equals(preOrderStatus)) {
                // 1番目レコードのA.注文状況＝'2'(取消) かつ 2番目以降のレコードのA.注文状況＝'1'(訂正) : "訂正注文有"
                status = STATUS_CORRECTION;
            } else {
                // 1番目レコードのA.注文状況＝'2'(取消) かつ 2番目以降のレコードのA.注文状況≠'1'(訂正) : "取消"
                status = STATUS_CANCELLATION;
            }
        }
        return status;
    }

    /**
     * ステータスを取得する.
     * 
     * @param orderStatus 注文状況
     * @return ステータス
     */
    private String getStatus(String orderStatus) throws Exception {

        // ステータス
        String status = StringUtil.EMPTY_STRING;

        if (IfaContactUtil.OrderStatus.TJO_ORDER_STATUS_0.equals(orderStatus)) {
            // 1番目レコードのA.注文状況＝'0'(新規(発注)):"-"
            status = IfaContactUtil.HALF_HYPHEN;
        } else if (IfaContactUtil.OrderStatus.TJO_ORDER_STATUS_2.equals(orderStatus)) {
            // 1番目レコードのA.注文状況＝'2'(取消):"取消"
            status = STATUS_CANCELLATION;
        }
        return status;
    }

    /**
     * ステータスを取得する.
     * 
     * @param torikeshiKbn 取消区分
     * @return ステータス
     */
    private String getStatusFor004(String torikeshiKbn) throws Exception {

        // ステータス
        String status = StringUtil.EMPTY_STRING;

        if (IfaContactUtil.TorikeshiKbn.TORIKESHIKBN_1.equals(torikeshiKbn)) {
            // 枝番が最大であるレコードが取消区分＝'1'(取消済)の場合、ステータス:"取消"
            status = STATUS_CANCELLATION;
        } else {
            // 枝番が最大であるレコードが取消区分＝'1'(取消済)以外の場合、ステータス:"-"
            status = IfaContactUtil.HALF_HYPHEN;
        }
        return status;
    }

    /**
     * ステータスを取得する.
     * 
     * @param orderType 注文種別区分
     * @return ステータス
     */
    private String getStatusFor005(String orderType) throws Exception {

        // ステータス
        String status = StringUtil.EMPTY_STRING;

        if (OrderType.CANCELLATION.getIfaCd().equals(orderType)) {
            // 1番目のレコードの注文種別区分＝'2'(取消)の場合、ステータス:"取消"
            status = STATUS_CANCELLATION;
        } else {
            // 1番目のレコードの注文種別区分＝'2'(取消)以外の場合、ステータス:"-"
            status = IfaContactUtil.HALF_HYPHEN;
        }
        return status;
    }

    /**
     * ステータスを取得する.
     * 
     * @param fxOrderStatusInfoMap 数据キー毎の為替取引注文状況情報
     * @param tradeType            取引種別
     * @param currencyCode         通貨コード
     * @param currencyName         通貨名
     * @param orderNo              注文番号
     * @param fxCycleNo            為替取引サイクル番号
     * @param fxEventId            為替取引注文イベントID
     * @return ステータス
     */
    private String getStatusFor007(Map<String, String> fxOrderStatusInfoMap, String tradeType, String currencyCode,
            String fxOrderAmount, String fxSpecificAccountCode, String orderNo) throws Exception {

        // ステータス
        String status = StringUtil.EMPTY_STRING;

        // 数据キー
        String dataKey = getDataKey(tradeType, currencyCode, fxOrderAmount, fxSpecificAccountCode, orderNo);
        // 為替取引注文状況
        String fxOrderStatus = fxOrderStatusInfoMap.get(dataKey);

        if (FX_ORDER_STATUS.equals(fxOrderStatus)) {
            // IFA注文サブ番号が最大であるレコードが為替取引注文状況＝'2'(取消)の場合、ステータス:"取消"
            status = STATUS_CANCELLATION;
        } else {
            // IFA注文サブ番号が最大であるレコードが為替取引注文状況＝'2'(取消)以外の場合、ステータス:"-"
            status = IfaContactUtil.HALF_HYPHEN;
        }
        return status;
    }

    /**
     * IFA注文番号毎の1番目レコードの注文状況情報を取得する.
     * 
     * @return IFA注文番号毎の注文状況情報
     */
    private Map<String, List<String>> getOrderStatusInfoMapFor002() throws Exception {

        // IFA注文番号毎の注文状況情報
        Map<String, List<String>> orderStatusInfoMap = new HashMap<>();

        // 同一のIFA注文番号内1番目レコードの注文状況情報を取得する。
        DataList<IfaContactAcceptDetailSql010ResponseModel> selSql010Res = dao.selectIfaContactAcceptDetailSql010();

        if (selSql010Res != null && CollectionUtils.isNotEmpty(selSql010Res.getDataList())) {

            for (IfaContactAcceptDetailSql010ResponseModel selSql010Info : selSql010Res.getDataList()) {
                // 最大情報の初期化
                List<String> info = new ArrayList<>();
                // 1番目レコードの注文状況
                info.add(0, selSql010Info.getOrderStatus());
                // 2番目レコードの注文状況
                info.add(1, selSql010Info.getPreOrderStatus());
                // IFA注文番号毎情報を設定する。
                orderStatusInfoMap.put(selSql010Info.getIfaOrderNo(), info);
            }
        }
        return orderStatusInfoMap;
    }

    /**
     * IFA注文番号毎の1番目レコードの注文状況情報を取得する.
     * 
     * @return IFA注文番号毎の注文状況情報
     */
    private Map<String, String> getOrderStatusInfoMapFor002Step3() throws Exception {

        // IFA注文番号毎の注文状況情報
        Map<String, String> orderStatusInfoMap = new HashMap<>();

        // 同一のIFA注文番号内1番目レコードの注文状況情報を取得する。
        DataList<IfaContactAcceptDetailSql010ResponseModel> selSql010Res = dao.selectIfaContactAcceptDetailSql010();

        if (selSql010Res != null && CollectionUtils.isNotEmpty(selSql010Res.getDataList())) {

            for (IfaContactAcceptDetailSql010ResponseModel selSql010Info : selSql010Res.getDataList()) {
                // IFA注文番号毎情報を設定する。
                orderStatusInfoMap.put(selSql010Info.getIfaOrderNo(), selSql010Info.getOrderStatus());
            }
        }
        return orderStatusInfoMap;
    }

    /**
     * IFA注文番号毎の1番目レコードの注文状況情報を取得する.
     * 
     * @return IFA注文番号毎の注文状況情報
     */
    private Map<String, String> getOrderStatusInfoMapFor003() throws Exception {

        // IFA注文番号毎の注文状況情報
        Map<String, String> orderStatusInfoMap = new HashMap<>();

        // 同一のIFA注文番号内1番目レコードの注文状況情報を取得する。
        DataList<IfaContactAcceptDetailSql011ResponseModel> selSql011Res = dao.selectIfaContactAcceptDetailSql011();

        if (selSql011Res != null && CollectionUtils.isNotEmpty(selSql011Res.getDataList())) {

            for (IfaContactAcceptDetailSql011ResponseModel selSql011Info : selSql011Res.getDataList()) {
                // IFA注文番号毎情報を設定する。
                orderStatusInfoMap.put(selSql011Info.getIfaOrderNo(), selSql011Info.getOrderStatus());
            }
        }
        return orderStatusInfoMap;
    }

    /**
     * トランザクションID毎の1番目レコードの取消区分情報を取得する.
     * 
     * @return トランザクションID毎の取消区分情報
     */
    private Map<String, String> getTorikeshiKbnInfoMapFor004() throws Exception {

        // トランザクションID毎の取消区分情報
        Map<String, String> torikeshiKbnInfoMap = new HashMap<>();

        // 同一のトランザクションID内1番目レコードの取消区分情報を取得する。
        DataList<IfaContactAcceptDetailSql012ResponseModel> selSql012Res = dao.selectIfaContactAcceptDetailSql012();

        if (selSql012Res != null && CollectionUtils.isNotEmpty(selSql012Res.getDataList())) {

            for (IfaContactAcceptDetailSql012ResponseModel selSql012Info : selSql012Res.getDataList()) {
                // トランザクションID毎情報を設定する。
                torikeshiKbnInfoMap.put(selSql012Info.getTranId(), selSql012Info.getTorikeshiKbn());
            }
        }
        return torikeshiKbnInfoMap;
    }

    /**
     * IFA注文番号毎の1番目レコードの注文種別区分情報を取得する.
     * 
     * @return IFA注文番号毎の注文種別区分情報
     */
    private Map<String, String> getOrderTypeInfoMapFor005() throws Exception {

        // トランザクションID毎の取消区分情報
        Map<String, String> orderTypeInfoMap = new HashMap<>();

        // 同一のIFA注文番号内1番目レコードの注文種別区分情報を取得する。
        DataList<IfaContactAcceptDetailSql008ResponseModel> selSql008Res = dao.selectIfaContactAcceptDetailSql008();

        if (selSql008Res != null && CollectionUtils.isNotEmpty(selSql008Res.getDataList())) {

            for (IfaContactAcceptDetailSql008ResponseModel selSql008Info : selSql008Res.getDataList()) {
                // IFA注文番号毎情報を設定する。
                orderTypeInfoMap.put(selSql008Info.getIfaOrderNo(), selSql008Info.getOrderType());
            }
        }
        return orderTypeInfoMap;
    }

    /**
     * 数据キー毎の1番目レコードの為替取引注文状況情報を取得する.
     * 
     * @return 数据キー毎の為替取引注文状況情報
     */
    private Map<String, String> getFxOrderStatusInfoMapFor007() throws Exception {

        // 数据キー毎の取消区分情報
        Map<String, String> fxOrderStatusInfoMap = new HashMap<>();

        // 同一の数据キー内1番目レコードの為替取引注文状況情報を取得する。
        DataList<IfaContactAcceptDetailSql009ResponseModel> selSql009Res = dao.selectIfaContactAcceptDetailSql009();

        if (selSql009Res != null && CollectionUtils.isNotEmpty(selSql009Res.getDataList())) {

            for (IfaContactAcceptDetailSql009ResponseModel selSql009Info : selSql009Res.getDataList()) {
                // 数据キー毎情報を設定する。
                // 取引種別
                String tradeType = selSql009Info.getTradeType();
                // 通貨コード
                String currencyCode = selSql009Info.getCurrencyCode();
                // 為替注文金額
                String fxOrderAmount = selSql009Info.getFxOrderAmount();
                // 口座分類（為替取引）
                String fxSpecificAccountCode = selSql009Info.getFxSpecificAccountCode();
                // 注文番号
                String orderNo = selSql009Info.getOrderNo();
                // 為替取引注文状況
                String fxOrderStatus = selSql009Info.getFxOrderStatus();
                // 数据キー
                String dataKey = getDataKey(tradeType, currencyCode, fxOrderAmount, fxSpecificAccountCode, orderNo);

                fxOrderStatusInfoMap.put(dataKey, fxOrderStatus);
            }
        }

        return fxOrderStatusInfoMap;
    }

    /**
     * 接触履歴受付詳細1行目をセットする
     * 
     * @param ifaContactAcceptDetailList List<IfaContactAcceptDetailCommonResponseDto>
     * @param dtoReq IfaContactAcceptDetailA001RequestDto
     * @throws Exception
     */
    private void setIfaContactResListFristRow (List<IfaContactAcceptDetailCommonResponseDto> ifaContactAcceptDetailList,
            IfaContactAcceptDetailA001RequestDto dtoReq) throws Exception {
        // 遷移元画面パラメータ.参照元履歴区分 != 1(CCS履歴)の場合
        if (!IfaContactUtil.REFERENCE_KBN_1.equals(dtoReq.getReferenceKbn())) {
            IfaContactAcceptDetailCommonResponseDto firstDto = new IfaContactAcceptDetailCommonResponseDto();
            // 受付シート: 固定文字"受付詳細"
            firstDto.setAcceptSheetNo(ACCEPT_SHEET_UKETSUKE);
            // 大分類 遷移元画面パラメータ.大分類
            firstDto.setBigClass(dtoReq.getBigClass());
            // 中分類: 固定記号"ハイフン"
            firstDto.setMidClass(HYPHEN);
            // 記録日時 遷移元画面パラメータ.記録日時
            firstDto.setRecordDate(dtoReq.getRecordDate());
            // ステータス: 固定記号"ハイフン"
            firstDto.setStatus(HYPHEN);
            // 内容: 固定文字"内容は以下を参照"
            firstDto.setContents(CONTENT_SANSYOU);
            // 1行目をセットする
            ifaContactAcceptDetailList.add(0, firstDto);
        }
        return;
    }

    /**
     * 注文数量を编辑する.
     * 
     * @param orderCount 注文数量
     * @return 注文数量(編集後)
     */
    public String getOrderCountyDisp(String orderCount) {

        String orderCountyDisp = StringUtil.EMPTY_STRING;

        if (!StringUtil.isNullOrEmpty(orderCount)) {
            // NULL以外の場合:注文数量:"注文数量:"+注文数量
            orderCountyDisp = "注文数量:" + orderCount;
        }
        return orderCountyDisp;
    }

    /**
     * データキーを取得する.
     * 
     * @param tradeType             取引種別
     * @param currencyCode          通貨コード
     * @param fxOrderAmount         為替注文金額
     * @param fxSpecificAccountCode 口座分類（為替取引）
     * @param orderNo               注文番号
     * @return データキー
     */
    public String getDataKey(String tradeType, String currencyCode, String fxOrderAmount, String fxSpecificAccountCode,
            String orderNo) {

        String dataKey = StringUtil.EMPTY_STRING;

        dataKey = getDefaultValWithHalfHyphen(tradeType) + getDefaultValWithHalfHyphen(currencyCode)
                + getDefaultValWithHalfHyphen(fxOrderAmount) + getDefaultValWithHalfHyphen(fxSpecificAccountCode)
                + getDefaultValWithHalfHyphen(orderNo);

        return dataKey;
    }

    /**
     * 文字列を编辑する.
     * 
     * @param val 文字列
     * @return 文字列(編集後)
     */
    public String getDefaultValWithHalfHyphen(String val) {

        String resultVal = StringUtil.isNullOrEmpty(val) ? StringUtil.EMPTY_STRING
                : IfaContactUtil.HALF_HYPHEN + val;

        return resultVal;
    }

    /* ------------------- 以下は： CCS接触履歴詳細用 プライベートメソッド ------------------- */
    /**
     * CCS APIを呼出し、CCS接触履歴詳細リストの情報を取得する
     * 
     * @param x_dtoReq
     * @param ccsApiErr CCS API ERROR
     * @return List<ShowDealingDetailIFAOut.ContactDetail>
     * @throws Exception API呼び出しでエラーが発生した場合
     */
    private List<ShowDealingDetailIFAOut.ContactDetail> getCcsApiOutList(IfaContactAcceptDetailA001RequestDto x_dtoReq, CcsApiErrUtil ccsApiErr) throws Exception {
        ccsApiErr.setErrLevel(0);
        ccsApiErr.setErrId(StringUtil.EMPTY_STRING);
        ccsApiErr.setErrMsg(StringUtil.EMPTY_STRING);
        // APIのIN引数をセットする
        ShowDealingDetailIFAReq p_req = new ShowDealingDetailIFAReq();
        ShowDealingDetailIFAIn p_in = new ShowDealingDetailIFAIn();
        p_in.setAccountId(StringUtil.fillLeft(IfaCommonUtil.getCustomerCommon().getCustomerCode(), '0', 9));    // 顧客ID(前ゼロ埋め)
        p_in.setShousaiKbn(x_dtoReq.getShousaiKbn());                                                           // 詳細区分
        p_in.setRecordDate(StringUtil.fillRight(x_dtoReq.getRecordDate(), ' ', 20));                            // 記録日時(後半角スペース埋め)
        p_in.setEdaban(StringUtil.fillLeft(x_dtoReq.getEdaban(), '0', 5));                                      // 枝番(前ゼロ埋め)
        p_in.setBigClass(StringUtil.fillRight(x_dtoReq.getBigClass(), ' ', 20));                                // 大分類(後半角スペース埋め)
        p_in.setUserId(StringUtil.fillRight(IfaCommonUtil.getUserAccount().getCcsUserId(), ' ', 8));            // ユーザ共通情報.CCSログイン用ID(左詰め、後"△"埋め)
        p_req.setParameter(p_in);
        //
        ShowDealingDetailIFAOut p_out = null;
        try {
            p_out = g_ccsApiService.calShowDealingDetailIFA(p_req).getCcsApiOut();
            ccsApiErr.fromApiOut(p_out);
        } catch (Exception e) {
            LOGGER.error("IfaContactAcceptDetailServiceImpL.getCcsApiOutList(calShowDealingDetailIFA) error.", e);
            ccsApiErr.setErrLevel(-1);
            ccsApiErr.setErrId(StringUtil.EMPTY_STRING);
            ccsApiErr.setErrMsg(StringUtil.EMPTY_STRING);
        }
        if (ccsApiErr.getErrLevel() != 0) {
            return null;
        }
        return p_out.getContactDetailList();
    }

    /**
     * CCS接触履歴詳細リストの情報により、IfaContactAcceptDetailCommonResponseDtoに変換してリストに追加します。
     * 
     * @param x_ccsApiList API OUTのCCS接触履歴リスト
     * @param x_ifaContactAcceptDetailList 接触履歴受付詳細リスト
     */
    private void setIfaContactAcceptDetailCcsApiInfoList(List<ShowDealingDetailIFAOut.ContactDetail> x_ccsApiList,
            List<IfaContactAcceptDetailCommonResponseDto> x_ifaContactAcceptDetailList)
            throws Exception {
        // 接触履歴詳細データが存在する場合のみ処理
        Optional.ofNullable(x_ccsApiList)
            .filter(CollectionUtils::isNotEmpty)
            .ifPresent((p_list) -> {
                IntStream.range(0, p_list.size()).forEach(index -> {
                    IfaContactAcceptDetailCommonResponseDto p_dto = new IfaContactAcceptDetailCommonResponseDto();
                    p_dto.setAcceptSheetNo(p_list.get(index).getUketsukesheet());   // 受付シート
                    p_dto.setBigClass(p_list.get(index).getBigClass());             // 大分類
                    p_dto.setMidClass(p_list.get(index).getMidClass());             // 中分類
                    p_dto.setRecordDate(p_list.get(index).getRecordDate());         // 記録日時
                    p_dto.setStatus(p_list.get(index).getOrderStatus());            // ステータス
                    p_dto.setContents(p_list.get(index).getContent());              // 内容
                    // 接触履歴受付詳細1行目.内容 = 固定文字"内容は以下を参照"
                    if (index == 0) {
                        p_dto.setContents(CONTENT_SANSYOU);
                    }
                    x_ifaContactAcceptDetailList.add(p_dto);
                });
            });
    }
}
