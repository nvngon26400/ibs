package com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.IfaDateUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.IfaSelfInspectItemManageDao;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectItemManageSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectItemManageSql002ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectItemManageSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectItemManageSql003ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectItemManageSql004RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectItemManageSql005RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectItemManageSql006RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectItemManageSql006ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectItemManageSql007RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectItemManageSql007ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectItemManageSql008RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectItemManageSql008ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectItemManageSql009RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA001ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA002RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA002ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA003RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA003ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA009RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA009ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA010RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA010ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA012RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA012ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageBrokerTypeDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageRegisterDateDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageSelfAssessmentItemDto; 
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.service.IfaSelfInspectItemManageService;

/**
 * 画面ID：SUB0506_02-01
 * 画面名：自己点検項目管理
 * 2024/06/19 新規作成
 *
 * @author SCSK 江口
 */
@Component(value = "cmpIfaSelfInspectItemManageService")
public class IfaSelfInspectItemManageServiceImpL implements IfaSelfInspectItemManageService {

    /** 情報.検索結果が0件です。\n条件を設定して再度検索して下さい。 */
    private static final String ERRORS_DATALIST_NOT_FOUND = "errors.dataList.notfound";

    /** 情報.自己点検項目を削除しました。 */
    private static final String INFO_DELETE_COMPLETED = "info.deleteCompleted";

    /** 情報.自己点検項目を更新しました。 */
    private static final String INFO_UPDATE_COMPLETED = "info.updateCompleted";

    /** 情報.{0}件のデータが登録されました。 */
    private static final String INFO_UPLOAD_INSERT_COMPLETED = "info.uploadInsertCompleted";

    /** エラー.理由必須のため回答は入力できません。 */
    private static final String ERRORS_COR_SELF_CHECK_ANSWER_REASON_NECESSARY = "errors.corSelfCheck.answer.reasonNecessary";

    /** エラー.理由不要のため回答を入力してください。 */
    private static final String ERRORS_COR_SELF_CHECK_ANSWER_REASON_UNNECESSARY = "errors.corSelfCheck.answer.reasonUnnecessary";

    /** エラー.1つの仲介業者に複数の設問が割り当たっています。 */
    private static final String ERRORS_COR_SELF_CHECK_BROKER_QUESTION_DUPLICATE = "errors.corSelfCheck.brokerQuestion.duplicate";

    /** エラー.業者区分がいずれの仲介業者にも割り当たっていません。 */
    private static final String ERRORS_COR_SELF_CHECK_BROKER_DIVISION_UNSELECTED = "errors.corSelfCheck.brokerDivision.unselected";

    /** エラー.登録済みの業者区分があるため、全ての業者区分に設問を登録することができません。 */
    private static final String ERRORS_COR_SELF_CHECK_BROKER_DIVISION_REGISTERED = "errors.corSelfCheck.brokerDivision.registered";

    /** エラー.入力した業者区分は存在しません。 */
    private static final String ERRORS_INPUT_ITEM_NOT_EXIST = "errors.inputItemNotExist";

    /** 業者区分リスト.登録状況.登録済 */
    private static final String BROKER_TYPE_LIST_REGISTER_STATUS_REGISTERED = "登録済";

    /** 業者区分リスト.登録状況.未登録 */
    private static final String BROKER_TYPE_LIST_REGISTER_STATUS_NOT_REGISTERED = "未登録";

    /** 確認初期値.はい */
    private static final String CONFIRMATION_INIT_YES = "1";

    /** 表示フラグ.表示 */
    private static final String DISPLAY_FLAG_DISPLAY = "1";

    /** 理由必須フラグ.理由不要 */
    private static final String REASON_REQUIRED_FLAG_UNNECESSARY = "0";

    /** 理由必須フラグ.理由必須 */
    private static final String REASON_REQUIRED_FLAG_NECESSARY = "1";

    /** ロガー */
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaSelfInspectItemManageServiceImpL.class);

    @Autowired
    private IfaDateUtil ifaDateUtil;

    @Autowired
    private IfaSelfInspectItemManageDao dao;


    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaSelfInspectItemManageA001RequestDto
     * Dto レスポンス：IfaSelfInspectItemManageA001ResponseDto
     *
     * @return 初期化に必要な情報
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaSelfInspectItemManageA001ResponseDto> initializeA001() throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSelfInspectItemManageServiceImplL.initializeA001");
        }

        IfaSelfInspectItemManageA001ResponseDto innerData = new IfaSelfInspectItemManageA001ResponseDto();

        /* ====================================================================== */
        /* 1. 登録年月のdropDownListデータを取得する。                              */
        /* ====================================================================== */

        // SQL001を実行する
        DataList<IfaSelfInspectItemManageSql001ResponseModel> selSql001Res = dao.selectIfaSelfInspectItemManageSql001();

        // SQL001の結果をレスポンスにコピーする
        List<IfaSelfInspectItemManageRegisterDateDto> registerDateList = new ArrayList<>();
        for (IfaSelfInspectItemManageSql001ResponseModel val : Optional.ofNullable(selSql001Res)
                .map(val -> val.getDataList())
                .orElse(Collections.emptyList())
        ) {
            IfaSelfInspectItemManageRegisterDateDto registerDate = new IfaSelfInspectItemManageRegisterDateDto();
            BeanUtils.copyProperties(registerDate, val);

            registerDateList.add(registerDate);
        }

        innerData.setRegisterDateList(registerDateList);

        /* ====================================================================== */
        /* 2. 業者区分名称を取得する。                                              */
        /* ====================================================================== */

        // SQL002を実行する
        DataList<IfaSelfInspectItemManageSql002ResponseModel> selSql002Res = dao.selectIfaSelfInspectItemManageSql002();

        /* ====================================================================== */
        /* 3. A002を呼び出す                                                       */
        /* ====================================================================== */

        // SQL002の結果をA002リクエストにコピーする
        List<IfaSelfInspectItemManageBrokerTypeDto> brokerTypeList = new ArrayList<>();
        for (IfaSelfInspectItemManageSql002ResponseModel val : Optional.ofNullable(selSql002Res)
                .map(val -> val.getDataList())
                .orElse(Collections.emptyList())
        ) {
            IfaSelfInspectItemManageBrokerTypeDto brokerType = new IfaSelfInspectItemManageBrokerTypeDto();
            BeanUtils.copyProperties(brokerType, val);

            brokerTypeList.add(brokerType);
        }

        IfaSelfInspectItemManageA002RequestDto dtoReqA002 = new IfaSelfInspectItemManageA002RequestDto();
        dtoReqA002.setBrokerTypeList(brokerTypeList);

        // A002リクエスト.登録年月に来月を設定
        LocalDateTime currentMonth = ifaDateUtil.getCurrentLocalDateTime(ZoneId.of("Asia/Tokyo"));
        LocalDateTime nextMonth = currentMonth.plusMonths(1L);
        dtoReqA002.setAssignMonthList(nextMonth.format(DateTimeFormatter.ofPattern(IfaDateUtil.YYYYMM)));

        // A002を呼び出す
        DataList<IfaSelfInspectItemManageA002ResponseDto> dtoResA002 = displayYearMonthChangeA002(dtoReqA002);

        // A002の結果をA001のレスポンスにコピーする
        innerData.setBrokerTypeList(
                Optional.ofNullable(dtoResA002)
                        .map(val -> val.get(0).getBrokerTypeList())
                        .orElse(Collections.emptyList())
        );

        /* ====================================================================== */
        /* レスポンスを返却する                                                    */
        /* ====================================================================== */

        DataList<IfaSelfInspectItemManageA001ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                Arrays.asList(innerData),
                ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.name(),
                StringUtil.EMPTY_STRING
        );
        return dtoRes;
    }

    /**
     * アクションID：A002
     * アクション名：表示年月変更
     * Dto リクエスト：IfaSelfInspectItemManageA002RequestDto
     * Dto レスポンス：IfaSelfInspectItemManageA002ResponseDto
     * model リクエスト：IfaSelfInspectItemManageSql001RequestModel
     * model レスポンス：IfaSelfInspectItemManageSql001ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return 表示年月変更に必要な情報
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaSelfInspectItemManageA002ResponseDto> displayYearMonthChangeA002(
            IfaSelfInspectItemManageA002RequestDto dtoReq
    ) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSelfInspectItemManageServiceImplL.displayYearMonthChangeA002");
        }

        /* ====================================================================== */
        /* 1. 自己点検項目情報を取得する                                           */
        /* ====================================================================== */

        // DTOリクエストからSQL003リクエストにパラメータをコピーする
        IfaSelfInspectItemManageSql003RequestModel selSql003Req = new IfaSelfInspectItemManageSql003RequestModel();
        BeanUtils.copyProperties(selSql003Req, dtoReq);

        // SQL003を実行する
        DataList<IfaSelfInspectItemManageSql003ResponseModel> selSql003Res = dao.selectIfaSelfInspectItemManageSql003(selSql003Req);
        
        // SQL003の結果をNULL安全に変換する
        List<IfaSelfInspectItemManageSql003ResponseModel> safeSelSql003Res = Optional.ofNullable(selSql003Res)
                .map(DataList::getDataList)
                .orElse(Collections.emptyList());

        /* ====================================================================== */
        /* 2. 業者区分毎の登録状況を設定する                                        */
        /* ====================================================================== */

        // リクエスト.業者区分リスト毎に、SQL003で取得した登録情報があるか否かを設定する
        List<IfaSelfInspectItemManageBrokerTypeDto> brokerTypeList = Optional.ofNullable(dtoReq)
                .map(val -> val.getBrokerTypeList())
                .orElse(Collections.emptyList())
                .stream()
                .map(val -> {
                    val.setRegisterStatus(
                            safeSelSql003Res.stream().anyMatch(
                                    sql003Res -> StringUtils.equals(val.getBrokerType(), sql003Res.getBrokerType())
                            )
                            ? BROKER_TYPE_LIST_REGISTER_STATUS_REGISTERED
                            : BROKER_TYPE_LIST_REGISTER_STATUS_NOT_REGISTERED
                    );
                    return val;
                })
                .collect(Collectors.toList());

        // 登録情報を含めた業者区分リストをレスポンスにコピーする
        IfaSelfInspectItemManageA002ResponseDto innerData = new IfaSelfInspectItemManageA002ResponseDto();
        innerData.setBrokerTypeList(brokerTypeList);

        /* ====================================================================== */
        /* レスポンスを返却する                                                    */
        /* ====================================================================== */

        DataList<IfaSelfInspectItemManageA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                Arrays.asList(innerData),
                ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.name(),
                StringUtil.EMPTY_STRING
        );
        return dtoRes;
    }

    /**
     * アクションID：A003
     * アクション名：検索表示
     * Dto リクエスト：IfaSelfInspectItemManageA003RequestDto
     * Dto レスポンス：IfaSelfInspectItemManageA003ResponseDto
     * model リクエスト：IfaSelfInspectItemManageSql001RequestModel
     * model レスポンス：IfaSelfInspectItemManageSql001ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return 検索表示に必要な情報
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaSelfInspectItemManageA003ResponseDto> searchDisplayA003(
            IfaSelfInspectItemManageA003RequestDto dtoReq
    ) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSelfInspectItemManageServiceImplL.searchDisplayA003");
        }

        /* ====================================================================== */
        /* 2. 自己点検項目情報を取得する                                           */
        /* ====================================================================== */

        // DTOリクエストからSQL003リクエストにパラメータをコピーする
        IfaSelfInspectItemManageSql003RequestModel selSql003Req = new IfaSelfInspectItemManageSql003RequestModel();
        BeanUtils.copyProperties(selSql003Req, dtoReq);

        // SQL003を実行する
        DataList<IfaSelfInspectItemManageSql003ResponseModel> selSql003Res = dao.selectIfaSelfInspectItemManageSql003(selSql003Req);

        // 検索結果が0件の場合、取得結果0件エラーを返す
        if (
                selSql003Res == null
                || selSql003Res.size() == 0
        ) {
            DataList<IfaSelfInspectItemManageA003ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.INFO,
                    ERRORS_DATALIST_NOT_FOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATALIST_NOT_FOUND)
            );

            return dtoRes;
        }

        // SQL003の結果をレスポンスにコピーする
        List<IfaSelfInspectItemManageSelfAssessmentItemDto> selfAssessmentItemList = new ArrayList<>();
        
        for (IfaSelfInspectItemManageSql003ResponseModel val : selSql003Res.getDataList()) {
            IfaSelfInspectItemManageSelfAssessmentItemDto selfAssessmentItem = new IfaSelfInspectItemManageSelfAssessmentItemDto();
            BeanUtils.copyProperties(selfAssessmentItem, val);
            selfAssessmentItemList.add(selfAssessmentItem);
        }

        /* ====================================================================== */
        /* レスポンスを返却する                                                    */
        /* ====================================================================== */

        IfaSelfInspectItemManageA003ResponseDto innerData = new IfaSelfInspectItemManageA003ResponseDto();
        innerData.setSelfAssessmentItemList(selfAssessmentItemList);
        
        DataList<IfaSelfInspectItemManageA003ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                Arrays.asList(innerData),
                ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.name(),
                StringUtil.EMPTY_STRING
        );

        return dtoRes;
    }

    /**
     * アクションID：A009
     * アクション名：更新
     * Dto リクエスト：IfaSelfInspectItemManageA009RequestDto
     * Dto レスポンス：IfaSelfInspectItemManageA009ResponseDto
     * model リクエスト：IfaSelfInspectItemManageSql001RequestModel
     * model レスポンス：IfaSelfInspectItemManageSql001ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return 更新に必要な情報
     * @exception Exception システムエラー
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataList<IfaSelfInspectItemManageA009ResponseDto> updateA009(
            IfaSelfInspectItemManageA009RequestDto dtoReq
    ) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSelfInspectItemManageServiceImplL.updateA009");
        }

        /* ====================================================================== */
        /* 1. 業者区分リストを取得する                                             */
        /*    リクエスト.業者区分が設定されている場合：                             */
        /*        リクエスト.業者区分が1件のみ登録されたリストを取得する。           */
        /*                                                                        */
        /*    リクエスト.業者区分が未設定の場合：                                   */
        /*        SQL002から業者区分リストを取得する。                              */
        /*        自己点検項目が登録済みの業者区分があればエラーを返却する。          */
        /* ====================================================================== */

        List<String> brokerTypeList = new ArrayList<>();

        // リクエスト.業者区分が選択されている場合
        if (!StringUtil.isNullOrEmpty(dtoReq.getBrokerType())) {
            brokerTypeList = Arrays.asList(dtoReq.getBrokerType());

        } else { // リクエスト.業者区分が選択されていない場合
            // SQL002を実行して、業者区分リストを取得する
            DataList<IfaSelfInspectItemManageSql002ResponseModel> selSql002Res = dao.selectIfaSelfInspectItemManageSql002();

            // SQL002の結果を業者区分リストにコピーする
            brokerTypeList = Optional.ofNullable(selSql002Res)
                    .map(DataList::getDataList)
                    .orElse(Collections.emptyList())
                    .stream()
                    .map(val -> val.getBrokerType())
                    .collect(Collectors.toList());

            // SQL008を実行して、登録済みの自己点検項目の件数を取得する
            IfaSelfInspectItemManageSql008RequestModel selSql008Req = new IfaSelfInspectItemManageSql008RequestModel();
            selSql008Req.setAssignMonthList(dtoReq.getAssignMonthList());
            selSql008Req.setBrokerTypeList(brokerTypeList);
            DataList<IfaSelfInspectItemManageSql008ResponseModel> selSql008Res = dao.selectIfaSelfInspectItemManageSql008(selSql008Req);

            // 登録済みの自己点検項目が1件以上であれば、エラーを返却する。
            if (
                    selSql008Res == null
                    || selSql008Res.get(0) == null
                    || 0 < selSql008Res.get(0).getCount()
            ) {
                DataList<IfaSelfInspectItemManageA009ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                        new ArrayList<>(),
                        ErrorLevel.FATAL,
                        ERRORS_COR_SELF_CHECK_BROKER_DIVISION_REGISTERED,
                        IfaCommonUtil.getMessage(ERRORS_COR_SELF_CHECK_BROKER_DIVISION_REGISTERED)
                );

                return dtoRes;
            }
        }

        /* ====================================================================== */
        /* 2. 業者回答パターンマスタの存在チェックを行う。                           */
        /* ====================================================================== */

        // SQL006を呼び出す
        IfaSelfInspectItemManageSql006RequestModel selSql006Req = new IfaSelfInspectItemManageSql006RequestModel();
        selSql006Req.setBrokerTypeList(brokerTypeList);
        DataList<IfaSelfInspectItemManageSql006ResponseModel> selSql006Res = dao.selectIfaSelfInspectItemManageSql006(selSql006Req);

        // SQL006レスポンス.件数 <= 0 の場合、業者区分なしエラーを返す
        if (
                selSql006Res == null
                || selSql006Res.get(0) == null
                || selSql006Res.get(0).getCount() <= 0
        ) {
            DataList<IfaSelfInspectItemManageA009ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_COR_SELF_CHECK_BROKER_DIVISION_UNSELECTED,
                    IfaCommonUtil.getMessage(ERRORS_COR_SELF_CHECK_BROKER_DIVISION_UNSELECTED)
            );

            return dtoRes;
        }

        /* ====================================================================== */
        /* 3. 業者回答パターンマスタの重複チェックを行う。                           */
        /* ====================================================================== */

        // SQL007を呼び出す
        IfaSelfInspectItemManageSql007RequestModel selSql007Req = new IfaSelfInspectItemManageSql007RequestModel();
        selSql007Req.setAssignMonthList(dtoReq.getAssignMonthList());
        selSql007Req.setBrokerTypeList(brokerTypeList);
        DataList<IfaSelfInspectItemManageSql007ResponseModel> selSql007Res = dao.selectIfaSelfInspectItemManageSql007(selSql007Req);

        // SQL007.件数 != 0 の場合、業者区分なしエラーを返す
        if (
                selSql007Res == null
                || selSql007Res.size() != 0
        ) {
            DataList<IfaSelfInspectItemManageA009ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_COR_SELF_CHECK_BROKER_QUESTION_DUPLICATE,
                    IfaCommonUtil.getMessage(ERRORS_COR_SELF_CHECK_BROKER_QUESTION_DUPLICATE)
            );

            return dtoRes;
        }

        /* ====================================================================== */
        /* 5. 自己点検項目を更新する                                               */
        /* ====================================================================== */

        for (IfaSelfInspectItemManageSelfAssessmentItemDto selfAssessmentItem : dtoReq.getSelfAssessmentItemList()) {
            IfaSelfInspectItemManageSql004RequestModel delSql004Req = new IfaSelfInspectItemManageSql004RequestModel();
            BeanUtils.copyProperties(delSql004Req, selfAssessmentItem);
            delSql004Req.setRegisterDate(dtoReq.getAssignMonthList());
            delSql004Req.setConfirmationInit(CONFIRMATION_INIT_YES);
            delSql004Req.setDisplayFlag(DISPLAY_FLAG_DISPLAY);

            String userId = IfaCommonUtil.getUserAccount().getUserId();
            delSql004Req.setUserId(userId);

            for (String brokerType : brokerTypeList) {
                delSql004Req.setBrokerType(brokerType);
                dao.updateIfaSelfInspectItemManageSql004(delSql004Req);
            }
        }

        /* ====================================================================== */
        /* 6. A002を呼び出す                                                       */
        /* ====================================================================== */

        // A002を呼び出す
        IfaSelfInspectItemManageA002RequestDto dtoReqA002 = new IfaSelfInspectItemManageA002RequestDto();
        dtoReqA002.setAssignMonthList(dtoReq.getAssignMonthList());
        dtoReqA002.setBrokerTypeList(dtoReq.getBrokerTypeList());
        DataList<IfaSelfInspectItemManageA002ResponseDto> dtoResA002 = displayYearMonthChangeA002(dtoReqA002);

        // A002の結果をA001のレスポンスにコピーする 
        IfaSelfInspectItemManageA009ResponseDto innerData = new IfaSelfInspectItemManageA009ResponseDto();
        List<IfaSelfInspectItemManageBrokerTypeDto> brokerTypeListA002Res = Optional.ofNullable(dtoResA002)
                .map(val -> val.getDataList())
                .map(val -> val.get(0))
                .map(val -> val.getBrokerTypeList())
                .orElse(Collections.emptyList());

        innerData.setBrokerTypeList(brokerTypeListA002Res);

        /* ====================================================================== */
        /* レスポンスを返却する                                                    */
        /* ====================================================================== */

        DataList<IfaSelfInspectItemManageA009ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                Arrays.asList(innerData),
                ErrorLevel.INFO,
                INFO_UPDATE_COMPLETED,
                IfaCommonUtil.getMessage(
                        INFO_UPDATE_COMPLETED,
                        new String[] { "自己点検項目" }
                )
        );

        return dtoRes;
    }

    /**
     * アクションID：A010
     * アクション名：削除
     * Dto リクエスト：IfaSelfInspectItemManageA010RequestDto
     * Dto レスポンス：IfaSelfInspectItemManageA010ResponseDto
     * model リクエスト：IfaSelfInspectItemManageSql001RequestModel
     * model レスポンス：IfaSelfInspectItemManageSql001ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return 削除に必要な情報
     * @exception Exception システムエラー
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataList<IfaSelfInspectItemManageA010ResponseDto> deleteA010(
            IfaSelfInspectItemManageA010RequestDto dtoReq
    ) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSelfInspectItemManageServiceImplL.deleteA010");
        }

        /* ====================================================================== */
        /* 3. 画面で指定した自己点検を削除する。                                    */
        /* ====================================================================== */

        IfaSelfInspectItemManageSql005RequestModel delSql005Req = new IfaSelfInspectItemManageSql005RequestModel();
        BeanUtils.copyProperties(delSql005Req, dtoReq);
        dao.deleteIfaSelfInspectItemManageSql005(delSql005Req);

        /* ====================================================================== */
        /* レスポンスを返却する                                                    */
        /* ====================================================================== */

        DataList<IfaSelfInspectItemManageA010ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                new ArrayList<>(),
                ErrorLevel.INFO,
                INFO_DELETE_COMPLETED,
                IfaCommonUtil.getMessage(
                        INFO_DELETE_COMPLETED,
                        new String[] {"自己点検項目"}
                )
        );

        return dtoRes;
    }

    /**
     * アクションID：A012
     * アクション名：ファイル取込
     * Dto リクエスト：IfaSelfInspectItemManageA012RequestDto
     * Dto レスポンス：IfaSelfInspectItemManageA012ResponseDto
     * model リクエスト：IfaSelfInspectItemManageSql001RequestModel
     * model レスポンス：IfaSelfInspectItemManageSql001ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return ファイル取込に必要な情報
     * @exception Exception システムエラー
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataList<IfaSelfInspectItemManageA012ResponseDto> fileImportA012(
            IfaSelfInspectItemManageA012RequestDto dtoReq
    ) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSelfInspectItemManageServiceImplL.fileImportA012");
        }

        /* ====================================================================== */
        /* 2. 業者区分名称を取得する。　                                           */
        /* ====================================================================== */

        // SQL002を実行して、業者区分リストを取得する
        DataList<IfaSelfInspectItemManageSql002ResponseModel> selSql002Res = dao.selectIfaSelfInspectItemManageSql002();

        // SQL002の結果を業者区分リストにコピーする
        List<String> brokerTypeList = Optional.ofNullable(selSql002Res)
                .map(val -> val.getDataList())
                .orElse(Collections.emptyList())
                .stream()
                .map(val -> val.getBrokerType())
                .collect(Collectors.toList());

        // SQL002の結果に含まれない業者区分が、リクエスト.自己点検項目リスト.業者区分に設定されていればエラー
        boolean notExistsInputBrokerType = Optional.ofNullable(dtoReq.getSelfAssessmentItemList())
                .orElse(Collections.emptyList())
                .stream()
                .anyMatch(val -> !brokerTypeList.contains(val.getBrokerType()));

        if (notExistsInputBrokerType) {
            DataList<IfaSelfInspectItemManageA012ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_INPUT_ITEM_NOT_EXIST,
                    IfaCommonUtil.getMessage(ERRORS_INPUT_ITEM_NOT_EXIST)
            );

            return dtoRes;
        }


        /* ====================================================================== */
        /* 3. 回答と理由の関連チェックを行う。                                      */
        /* ====================================================================== */

        // 回答入力エラーのチェック
        boolean isReasonUnnecessary = Optional.ofNullable(dtoReq.getSelfAssessmentItemList())
                .orElse(Collections.emptyList())
                .stream()
                .anyMatch(val -> 
                        StringUtil.isNullOrEmpty(val.getAnswer())
                        && StringUtils.equals(val.getReasonRequiredFlag(), REASON_REQUIRED_FLAG_UNNECESSARY)
                );

        if (isReasonUnnecessary) {
            DataList<IfaSelfInspectItemManageA012ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_COR_SELF_CHECK_ANSWER_REASON_UNNECESSARY,
                    IfaCommonUtil.getMessage(ERRORS_COR_SELF_CHECK_ANSWER_REASON_UNNECESSARY)
            );

            return dtoRes;
        }

        // 回答入力不可エラーのチェック
        boolean isReasonNecessary = Optional.ofNullable(dtoReq.getSelfAssessmentItemList())
                .orElse(Collections.emptyList())
                .stream()
                .anyMatch(val -> 
                        !StringUtil.isNullOrEmpty(val.getAnswer())
                        && StringUtils.equals(val.getReasonRequiredFlag(), REASON_REQUIRED_FLAG_NECESSARY)
                );

        if (isReasonNecessary) {
            DataList<IfaSelfInspectItemManageA012ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_COR_SELF_CHECK_ANSWER_REASON_NECESSARY,
                    IfaCommonUtil.getMessage(ERRORS_COR_SELF_CHECK_ANSWER_REASON_NECESSARY)
            );

            return dtoRes;
        }

        /* ====================================================================== */
        /* 4. 業者回答パターンマスタの存在チェックを行う。                           */
        /* ====================================================================== */

        // SQL006を呼び出す
        IfaSelfInspectItemManageSql006RequestModel selSql006Req = new IfaSelfInspectItemManageSql006RequestModel();
        selSql006Req.setBrokerTypeList(brokerTypeList);
        DataList<IfaSelfInspectItemManageSql006ResponseModel> selSql006Res = dao.selectIfaSelfInspectItemManageSql006(selSql006Req);

        // SQL006レスポンス.件数 <= 0 の場合、業者区分なしエラーを返す
        if (
                selSql006Res == null
                || selSql006Res.get(0) == null
                || selSql006Res.get(0).getCount() <= 0
        ) {
            DataList<IfaSelfInspectItemManageA012ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_COR_SELF_CHECK_BROKER_DIVISION_UNSELECTED,
                    IfaCommonUtil.getMessage(ERRORS_COR_SELF_CHECK_BROKER_DIVISION_UNSELECTED)
            );

            return dtoRes;
        }

        /* ====================================================================== */
        /* 5. 業者回答パターンマスタの重複チェックを行う。                           */
        /* ====================================================================== */

        // SQL007を呼び出す
        IfaSelfInspectItemManageSql007RequestModel selSql007Req = new IfaSelfInspectItemManageSql007RequestModel();
        String assignMonthList = Optional.ofNullable(dtoReq.getSelfAssessmentItemList())
                .map(val -> val.get(0))
                .map(val -> val.getRegisterDate())
                .orElse("");
        selSql007Req.setAssignMonthList(assignMonthList);
        selSql007Req.setBrokerTypeList(brokerTypeList);
        DataList<IfaSelfInspectItemManageSql007ResponseModel> selSql007Res = dao.selectIfaSelfInspectItemManageSql007(selSql007Req);

        // SQL007レスポンス.件数 != 0 の場合、業者区分重複エラーを返す
        if (
                selSql007Res == null
                || selSql007Res.size() != 0
        ) {
            DataList<IfaSelfInspectItemManageA012ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_COR_SELF_CHECK_BROKER_QUESTION_DUPLICATE,
                    IfaCommonUtil.getMessage(ERRORS_COR_SELF_CHECK_BROKER_QUESTION_DUPLICATE)
            );

            return dtoRes;
        }

        /* ====================================================================== */
        /* 6. 登録済みデータがあれば削除する。                                      */
        /* ====================================================================== */

        // SQL008を呼び出す
        IfaSelfInspectItemManageSql008RequestModel selSql008Req = new IfaSelfInspectItemManageSql008RequestModel();
        selSql008Req.setAssignMonthList(assignMonthList);
        selSql008Req.setBrokerTypeList(brokerTypeList);
        DataList<IfaSelfInspectItemManageSql008ResponseModel> selSql008Res = dao.selectIfaSelfInspectItemManageSql008(selSql008Req);

        if (0 < Optional.ofNullable(selSql008Res)
                .map(val -> val.get(0))
                .map(val -> val.getCount())
                .orElse(0)
        ) {
            // SQL009を呼び出す
            IfaSelfInspectItemManageSql009RequestModel delSql009Req = new IfaSelfInspectItemManageSql009RequestModel();
            delSql009Req.setAssignMonthList(assignMonthList);
            delSql009Req.setBrokerTypeList(brokerTypeList);
            dao.deleteIfaSelfInspectItemManageSql009(delSql009Req);
        }

        /* ====================================================================== */
        /* 7. 入力した情報を登録する。                                             */
        /* ====================================================================== */

        // SQL004を呼び出す
        int delSql004Res = 0;
        for (IfaSelfInspectItemManageSelfAssessmentItemDto selfAssessmentItem : dtoReq.getSelfAssessmentItemList()) {
            IfaSelfInspectItemManageSql004RequestModel delSql004Req = new IfaSelfInspectItemManageSql004RequestModel();
            BeanUtils.copyProperties(delSql004Req, selfAssessmentItem);
            delSql004Req.setConfirmationInit(CONFIRMATION_INIT_YES);
            delSql004Req.setDisplayFlag(DISPLAY_FLAG_DISPLAY);

            String userId = IfaCommonUtil.getUserAccount().getUserId();
            delSql004Req.setUserId(userId);
            
            delSql004Res += dao.updateIfaSelfInspectItemManageSql004(delSql004Req);
        }

        /* ====================================================================== */
        /* 8. A002を呼び出す。                                                     */
        /* ====================================================================== */

        // A002を呼び出す
        IfaSelfInspectItemManageA002RequestDto dtoReqA002 = new IfaSelfInspectItemManageA002RequestDto();

        dtoReqA002.setAssignMonthList(assignMonthList);

        List<IfaSelfInspectItemManageBrokerTypeDto> brokerTypeListForA002 = Optional.ofNullable(selSql002Res)
                .map(val -> val.getDataList())
                .orElse(Collections.emptyList())
                .stream()
                .map(val -> {
                    IfaSelfInspectItemManageBrokerTypeDto brokerType = new IfaSelfInspectItemManageBrokerTypeDto();
                    brokerType.setBrokerType(val.getBrokerType());
                    brokerType.setClassificationName(val.getClassificationName());
                    return brokerType;
                })
                .collect(Collectors.toList());
        dtoReqA002.setBrokerTypeList(brokerTypeListForA002);

        DataList<IfaSelfInspectItemManageA002ResponseDto> dtoResA002 = displayYearMonthChangeA002(dtoReqA002);

        // A002の結果をA001のレスポンスにコピーする
        IfaSelfInspectItemManageA012ResponseDto innerData = new IfaSelfInspectItemManageA012ResponseDto();
        innerData.setBrokerTypeList(
                Optional.ofNullable(dtoResA002)
                        .map(val -> val.getDataList())
                        .map(val -> val.get(0))
                        .map(val -> val.getBrokerTypeList())
                        .orElse(Collections.emptyList())
        );

        /* ====================================================================== */
        /* レスポンスを返却する                                                    */
        /* ====================================================================== */

        DataList<IfaSelfInspectItemManageA012ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                Arrays.asList(innerData),
                ErrorLevel.INFO,
                INFO_UPLOAD_INSERT_COMPLETED,
                IfaCommonUtil.getMessage(
                        INFO_UPLOAD_INSERT_COMPLETED,
                        new String[] { String.valueOf(delSql004Res) }
                )
        );

        return dtoRes;
    }

}
