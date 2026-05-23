package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.annotations.VisibleForTesting;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct023;
import com.sbisec.helios.ap.bizcommon.component.Fct024;
import com.sbisec.helios.ap.bizcommon.model.InputFct023Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct024Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaMutualFundDetailInfoDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql009RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql009ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql010RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql010ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql011RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql011ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql012RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql012ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundDetailInfoA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundDetailInfoA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaMutualFundDetailInfoService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.DateUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.netty.util.internal.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * 画面ID：SUB0202_0401-03
 * 画面名：投信詳細情報
 *
 * @author SCSK
 *
 *     2024/04/15 新規作成
 */
@Component(value = "cmpIfaMutualFundDetailInfoService")
public class IfaMutualFundDetailInfoServiceImpl implements IfaMutualFundDetailInfoService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaMutualFundDetailInfoServiceImpl.class);
    
    @Autowired
    private IfaMutualFundDetailInfoDao dao;
    
    @Autowired
    private Fct023 fct023;
    
    @Autowired
    private Fct024 fct024;
    
    /** マスタ取得エラー */
    private static final String ERR_NO_INFO = "errors.fnd.selectedBrand.noInformation";
    
    /** 基準価額、手数料取得エラー */
    private static final String ERR_NO_PRICE = "errors.fnd.selectedBrand.noPriceAndCommission";
    
    /** 日付フォーマット YYYY/MM/DD HH:MM */
    private static final String FORMAT_YYYYMMDDHHMM = "yyyy/MM/dd HH:mm";
    
    /** 窓あきファンド区分 */
    private static final String TOKUSYU_MADOAKI = "1";
    
    /** ブリテン種類 当社 */
    private static final String BULLETIN_TOSYA = "SS";
    
    /** ブリテン種類 委託 */
    private static final String BULLETIN_ITAKU = "OT";
    
    /** ブリテン種類 ウェルスアドバイザー */
    private static final String BULLETIN_WA = "MS";
    
    /** 積立単位文言0 */
    private static final String TUMI_UNIT_ZERO = "100円以上1円単位";
    
    /** 積立単位文言1 */
    private static final String TUMI_UNIT_ONE = "1万円以上1千円単位";
    
    /** 積立単位文言2 */
    private static final String TUMI_UNIT_TWO = "1千円以上1円単位";
    
    /** 積立単位文言3 */
    private static final String TUMI_UNIT_THREE = "500円以上1円単位";
    
    /** 取引コース　両方*/
    private static final String SQL10_COURSE_BOTH = "口数・金額";
    
    /** 取引コース　ファンドタイプ1 */
    private static final String SQL10_COURSE_ONE = "口数";
    
    /** 取引コース　ファンドタイプ2 */
    private static final String SQL10_COURSE_TWO = "金額";
    
    /** 売却申し込み可 */
    private static final String SQL10_TEIKI_ON = "申込可";
    
    /** 売却申し込み不可 */
    private static final String SQL10_TEIKI_OFF = "申込不可";
    
    /** 売却文言 */
    private static final String SQL10_SELL_NOTES = "\n※定期売却の場合は 金額：1000円以上1円単位";
    
    /** 約定備考 */
    private static final String SQL10_TRADE_NOTES = "（国内・海外の休場により遅れる場合がございます）";
    
    /** 手数料なし */
    private static final String SQL13_NONE = "なし";
    
    /** NISA手数料 */
    private static final String SQL13_TESURYO_NISA = "NISA預り（成長投資枠）：";
    
    /** NISA手数料(金額) */
    private static final String SQL13_TESURYO_NISA_PRICE = "金額（NISA預り（成長投資枠））：";
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaMutualFundDetailInfoA001DtoRequest
     * Dto レスポンス：IfaMutualFundDetailInfoA001DtoResponse
     * model リクエスト：IfaMutualFundDetailInfoA001RequestModel
     * model レスポンス：IfaMutualFundDetailInfoA001ResponseModel
     *
     * @param dtoReq リクエストDTO
     * @return dtoレスポンス
     * @exception Exception 実行時例外
     */
    @Override
    public DataList<IfaMutualFundDetailInfoA001ResponseDto> initializeA001(IfaMutualFundDetailInfoA001RequestDto dtoReq)
            throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMutualFundDetailInfoServiceImpl.initializeA001");
        }
        
        var res = new IfaMutualFundDetailInfoA001ResponseDto();
        // 更新日時
        res.setUpdateTime(DateUtil.format(FORMAT_YYYYMMDDHHMM));
        
        // 銘柄情報を取得する
        // 共通関数呼び出し用のNRICDは7桁
        var nriCd = dtoReq.getFundCodeTimes() + dtoReq.getFundCodeIssues();
        if (!executeFct023(nriCd, res)) {
            // 取得エラーを返却する
            return IfaCommonUtil.createDtaList(null, ErrorLevel.FATAL, ERR_NO_INFO);
        }
        // 協会コード、基準価額、高値安値を取得する
        if (!executeFct024(nriCd, res)) {
            // 取得エラーを返却する
            return IfaCommonUtil.createDtaList(null, ErrorLevel.FATAL, ERR_NO_PRICE);
        }
        // SQL001
        var sql001res = executeSql001(dtoReq.getFundCodeTimes(), dtoReq.getFundCodeIssues(), res);
        if (Objects.isNull(sql001res)) {
            // 取得エラーを返却する
            return IfaCommonUtil.createDtaList(null, ErrorLevel.FATAL, ERR_NO_INFO);
        }
        // SQL002
        executeSql002(res.getKyoukaiCd(), res);
        // SQL003
        executeSql003(res.getKyoukaiCd(), res);
        // SQL004 SQL001.特殊区分≠'1'の場合、対象銘柄の締切日（直近）を取得し、レスポンスにセットする。
        if (!StringUtils.equals(sql001res.getMFTokusyu(), TOKUSYU_MADOAKI)) {
            executeSql004(res.getKyoukaiCd(), sql001res.getMFShimekiri(), res);
            // SQL005 SQL001.特殊区分='1'　かつ　締切日（直近）が取得できた場合、対象銘柄の締切日（次回）を取得し、レスポンスにセットする。
            if (Objects.nonNull(res.getDeadlineDateRecent())) {
                executeSql005(res.getKyoukaiCd(), res.getDeadlineDateRecent(), res);
            }
        }
        // SQL006
        executeSql006(res.getKyoukaiCd(), BULLETIN_TOSYA).ifPresent(sqlRes -> {
            // 当社からのお知らせ更新日、コンテンツ
            res.setShrineNotificationUpdateDate(sqlRes.getFbmStartTime());
            res.setShrineNotificationContents(sqlRes.getFbmBody());
        });
        // SQL007
        executeSql006(res.getKyoukaiCd(), BULLETIN_ITAKU).ifPresent(sqlRes -> {
            // 委託会社からのお知らせ更新日、コンテンツ
            res.setOutsourcingCompanyNotificationUpdateDate(sqlRes.getFbmStartTime());
            res.setOutsourcingCompanyNotificationContents(sqlRes.getFbmBody());
        });
        // SQL008
        executeSql006(res.getKyoukaiCd(), BULLETIN_WA).ifPresent(sqlRes -> {
            // ウェルスアドバイザーからのお知らせ更新日、コンテンツ
            res.setWealthAdvisorCommentUpdateDate(sqlRes.getFbmStartTime());
            res.setWealthAdvisorCommentContents(sqlRes.getFbmBody());
        });
        // SQL009
        var sql009res = executeSql009(res.getKyoukaiCd(), res);
        // SQL010
        var sql010res = executeSql010(res.getKyoukaiCd(), sql009res, res);
        // SQL011
        var sql011res = executeSql011(res.getKyoukaiCd(), res);
        // SQL012
        executeSql012(res.getKyoukaiCd(), sql010res, res);
        // SQL013
        executeSql013(res.getKyoukaiCd(), sql009res, sql010res, sql011res, res);
        return IfaCommonUtil.createDataList(List.of(res), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
    }
    
    /**
     * FCT023の処理
     *
     * @param nriCd NRIコード
     * @param res レスポンスDTO
     * @return データありの場合true
     */
    private boolean executeFct023(String nriCd, IfaMutualFundDetailInfoA001ResponseDto res) {
        
        var input23 = new InputFct023Dto();
        input23.setNriCd(nriCd);
        var ret23 = fct023.getData(input23);
        if (StringUtil.isNullOrEmpty(ret23.getFundOfficalName())) {
            return false;
        }
        // ファンド正式名
        res.setFundOfficalName(ret23.getFundOfficalName());
        return true;
        
    }
    
    /**
     * FCT024の処理
     *
     * @param nriCd NRIコード
     * @param res レスポンスDTO
     * @return データありの場合true
     */
    private boolean executeFct024(String nriCd, IfaMutualFundDetailInfoA001ResponseDto res) {
        
        var input24 = new InputFct024Dto();
        input24.setNriCd(nriCd);
        var ret24 = fct024.getData(input24);
        if (Objects.isNull(ret24.getKyoukaiCd())) {
            return false;
        }
        // 基準価額日付
        res.setPriceDate(ret24.getBasePriceDate());
        // 基準価額
        res.setPrice(toNullableString(ret24.getBasePrice()));
        // 前日比
        res.setDiff(toNullableString(ret24.getDiff()));
        // 前日比率
        res.setRatio(toNullableString(ret24.getRatio()));
        // 純資産
        res.setJunshisan(toNullableString(ret24.getJunshisan()));
        // 52週高値
        res.setW52Takane(toNullableString(ret24.getW52Takane()));
        // 52週高値日付
        res.setW52Takanedate(ret24.getW52Takanedate());
        // 52週安値
        res.setW52Yasune(toNullableString(ret24.getW52Yasune()));
        // 52週安値日付
        res.setW52Yasunedate(ret24.getW52Yasunedate());
        return true;
    }
    
    /**
     * SQL001の処理
     *
     * @param fundCodeTimes ファンドコード（回数）
     * @param fundCodeIssues ファンドコード（号）
     * @param res レスポンスDTO
     * @return データありの場合レスポンス
     * @exception Exception 実行時例外
     */
    private IfaMutualFundDetailInfoSql001ResponseModel executeSql001(String fundCodeTimes, String fundCodeIssues,
            IfaMutualFundDetailInfoA001ResponseDto res) throws Exception {
        
        return dao
                .selectIfaMutualFundDetailInfoSql001(
                        new IfaMutualFundDetailInfoSql001RequestModel(fundCodeTimes, fundCodeIssues))
                .getDataList().stream().findFirst().map(sqlres -> {
                    // 協会コード
                    res.setKyoukaiCd(sqlres.getMFCode());
                    return sqlres;
                }).orElse(null);
    }
    
    /**
     * SQL002の処理
     *
     * @param kyoukaiCd 協会コード
     * @param res レスポンスDTO
     * @exception Exception 実行時例外
     */
    private void executeSql002(String kyoukaiCd, IfaMutualFundDetailInfoA001ResponseDto res) throws Exception {
        
        var items = dao.selectIfaMutualFundDetailInfoSql002(new IfaMutualFundDetailInfoSql002RequestModel(kyoukaiCd))
                .getDataList().stream()
                .map(sqlRes -> new IfaMutualFundDetailInfoA001ResponseDto.ClosedDayItem(sqlRes.getFdcCtlDate(),
                        sqlRes.getBusinessDayFlag()))
                .collect(Collectors.toList());
        // 当月休場日リスト
        res.setThisMonthClosedDayList(items);
    }
    
    /**
     * SQL003の処理
     *
     * @param kyoukaiCd 協会コード
     * @param res レスポンスDTO
     * @exception Exception 実行時例外
     */
    private void executeSql003(String kyoukaiCd, IfaMutualFundDetailInfoA001ResponseDto res) throws Exception {
        
        var items = dao.selectIfaMutualFundDetailInfoSql003(new IfaMutualFundDetailInfoSql003RequestModel(kyoukaiCd))
                .getDataList().stream()
                .map(sqlRes -> new IfaMutualFundDetailInfoA001ResponseDto.ClosedDayItem(sqlRes.getFdcCtlDate(),
                        sqlRes.getBusinessDayFlag()))
                .collect(Collectors.toList());
        // 翌月休場日リスト
        res.setNextMonthClosedDayList(items);
        
    }
    
    /**
     * SQL004の処理
     *
     * @param kyoukaiCd 協会コード
     * @param deadLines 締切時間
     * @param res レスポンスDTO
     * @exception Exception 実行時例外
     */
    private void executeSql004(String kyoukaiCd, String deadLines, IfaMutualFundDetailInfoA001ResponseDto res)
            throws Exception {
        
        dao.selectIfaMutualFundDetailInfoSql004(new IfaMutualFundDetailInfoSql004RequestModel(kyoukaiCd)).getDataList()
                .stream().findFirst().ifPresent(sqlRes -> {
                    // 締切日直近
                    res.setDeadlineDateRecent(sqlRes.getDeadlineDate());
                    // 締切時間
                    res.setDeadlines(deadLines);
                });
    }
    
    /**
     * SQL005の処理
     *
     * @param kyoukaiCd 協会コード
     * @param res レスポンスDTO
     * @exception Exception 実行時例外
     */
    private void executeSql005(String kyoukaiCd, String deadlineDate, IfaMutualFundDetailInfoA001ResponseDto res)
            throws Exception {
        
        // 締切日次回
        dao.selectIfaMutualFundDetailInfoSql005(new IfaMutualFundDetailInfoSql005RequestModel(kyoukaiCd, deadlineDate))
                .getDataList().stream().findFirst()
                .ifPresent(sqlRes -> res.setDeadlineDateNext(sqlRes.getDeadlineDate()));
    }
    
    /**
     * SQL006の処理
     *
     * @param kyoukaiCd 協会コード
     * @param res レスポンスDTO
     * @exception Exception 実行時例外
     * @return SQL006レスポンス
     */
    private Optional<IfaMutualFundDetailInfoSql006ResponseModel> executeSql006(String kyoukaiCd, String type)
            throws Exception {
        
        return dao.selectIfaMutualFundDetailInfoSql006(new IfaMutualFundDetailInfoSql006RequestModel(kyoukaiCd, type))
                .getDataList().stream().findFirst();
    }
    
    /**
     * SQL009の処理
     *
     * @param kyoukaiCd 協会コード
     * @param res レスポンスDTO
     * @exception Exception 実行時例外
     * @return SQL009レスポンス
     */
    private IfaMutualFundDetailInfoSql009ResponseModel executeSql009(String kyoukaiCd,
            IfaMutualFundDetailInfoA001ResponseDto res) throws Exception {
        
        return dao.selectIfaMutualFundDetailInfoSql009(new IfaMutualFundDetailInfoSql009RequestModel(kyoukaiCd))
                .getDataList().stream().findFirst().map(sqlres -> {
                    // 運用方針
                    res.setOperationPolicy(sqlres.getFDPolicy());
                    // 信託報酬
                    res.setTrustFeeAmount(sqlres.getFDTrustCharge());
                    // 信託財産留保額
                    res.setPartialRedemptionCharge(sqlres.getFDReservedAsset());
                    // 決算日
                    res.setSettleLastDay(sqlres.getFDAccountingDate());
                    // 分配金
                    res.setDividendHandling(sqlres.getFDDividendsSchedule());
                    // 設定日
                    res.setSettingDate(sqlres.getFDSettingDate());
                    
                    return sqlres;
                }).orElse(null);
    }
    
    /**
     * 積立単位文言の取得
     *
     * @param unit 単位0-3
     * @return 積立単位文言
     */
    private String getTumitateUnitText(String unit) {
        
        if (Objects.isNull(unit) || StringUtils.equals(unit, "0")) {
            return TUMI_UNIT_ZERO;
        } else if (StringUtils.equals(unit, "1")) {
            return TUMI_UNIT_ONE;
        } else if (StringUtils.equals(unit, "2")) {
            return TUMI_UNIT_TWO;
        } else if (StringUtils.equals(unit, "3")) {
            return TUMI_UNIT_THREE;
        }
        // 通常無い
        return "";
    }
    
    /**
     * SQL013の処理
     *
     * @param kyoukaiCd 協会コード
     * @param res レスポンスDTO
     * @exception Exception 実行時例外
     */
    private void executeSql013(String kyoukaiCd, IfaMutualFundDetailInfoSql009ResponseModel sql009res,
            Sql010DataByFundType sql10Data, Sql011DataByFundType sql11Data, IfaMutualFundDetailInfoA001ResponseDto res)
            throws Exception {
        
        var entryText = Optional.ofNullable(sql009res).map(IfaMutualFundDetailInfoSql009ResponseModel::getFDEntryText)
                .orElse("");
        // 消費税レコードは存在する前提
        BigDecimal todayTax = dao.selectIfaMutualFundDetailInfoSql013().getDataList().stream().findFirst()
                .map(resp -> new BigDecimal(resp.getTodayTax())).orElseThrow();
        // 買付手数料(税込)左
        if (sql10Data.isExistsOne() && sql10Data.isExistsTwo()) {
            var textOne = getTesuryoText(true, sql10Data.getOne().getMFHKubun(),
                    getSql10TesuryoRecords(sql10Data.getOne()), sql10Data.getOne().getMFHTanikuchi(), todayTax);
            var textTwo = getTesuryoText(false, sql10Data.getTwo().getMFHKubun(),
                    getSql10TesuryoRecords(sql10Data.getTwo()), sql10Data.getTwo().getMFHTanikuchi(), todayTax);
            res.setBuyCommLeft(String.format("口数：%s／金額：%s%s", textOne, textTwo, entryText));
        } else if (sql10Data.isExistsOne()) {
            res.setBuyCommLeft(
                    getTesuryoText(true, sql10Data.getOne().getMFHKubun(), getSql10TesuryoRecords(sql10Data.getOne()),
                            sql10Data.getOne().getMFHTanikuchi(), todayTax) + entryText);
        } else if (sql10Data.isExistsTwo()) {
            res.setBuyCommLeft(
                    getTesuryoText(false, sql10Data.getTwo().getMFHKubun(), getSql10TesuryoRecords(sql10Data.getTwo()),
                            sql10Data.getTwo().getMFHTanikuchi(), todayTax) + entryText);
        } else {
            res.setBuyCommLeft(SQL13_NONE);
        }
        // 買付手数料（税込）右
        // SQL010に呼応するSQL011のデータは存在する前提
        if (sql10Data.isExistsOne() && sql10Data.isExistsTwo()) {
            if (sql11Data.isExistsOne() && sql11Data.isExistsTwo()) {
                var textOne = getTesuryoText(true, sql11Data.getOne().getFcmKubun(),
                        getSql11TesuryoRecords(sql11Data.getOne()), sql10Data.getOne().getMFHTanikuchi(), todayTax);
                var textTwo = getTesuryoText(false, sql11Data.getTwo().getFcmKubun(),
                        getSql11TesuryoRecords(sql11Data.getTwo()), sql10Data.getTwo().getMFHTanikuchi(), todayTax);
                res.setBuyCommRight(String.format("口数：%s／金額：%s%s", textOne, textTwo, entryText));
            } else if (sql11Data.isExistsOne()) {
                res.setBuyCommRight("口数：" + 
                        getTesuryoText(true, sql11Data.getOne().getFcmKubun(), getSql11TesuryoRecords(sql11Data.getOne()),
                                sql10Data.getOne().getMFHTanikuchi(), todayTax) + entryText);
            } else if (sql11Data.isExistsTwo()) {
                res.setBuyCommRight("金額：" + 
                        getTesuryoText(false, sql11Data.getTwo().getFcmKubun(), getSql11TesuryoRecords(sql11Data.getTwo()),
                                sql10Data.getTwo().getMFHTanikuchi(), todayTax) + entryText);
            }
        } else if (sql10Data.isExistsOne()) {
            if (sql11Data.isExistsOne()) {
                res.setBuyCommRight(
                        getTesuryoText(true, sql11Data.getOne().getFcmKubun(), getSql11TesuryoRecords(sql11Data.getOne()),
                                sql10Data.getOne().getMFHTanikuchi(), todayTax) + entryText);
            }
        } else if (sql10Data.isExistsTwo()) {
            if (sql11Data.isExistsTwo()) {
                res.setBuyCommRight(
                        getTesuryoText(false, sql11Data.getTwo().getFcmKubun(), getSql11TesuryoRecords(sql11Data.getTwo()),
                                sql10Data.getTwo().getMFHTanikuchi(), todayTax) + entryText);
            }
        } else {
            res.setBuyCommRight(SQL13_NONE);
        }
        // 買付手数料（税込）(NISA)左
        if (sql10Data.isExistsOne() || sql10Data.isExistsTwo()) {
            if (sql10Data.isExistsTwo() && StringUtils.equals(sql10Data.getTwo().getMFSougoSeichouServiceKbn(), "1")
                    && !StringUtils.equals(sql10Data.getTwo().getMFSeichouTesuryoSetting(), "0")) {
                var text = getTesuryoText(false, sql10Data.getTwo().getMFHKubun(),
                        getSql10NisaTesuryoRecords(sql10Data.getTwo()), sql10Data.getTwo().getMFHTanikuchi(), todayTax);
                if (!sql10Data.isExistsOne()) { //ファンドタイプ=2　のレコードのみ
                    res.setBuyCommNisaLeft(SQL13_TESURYO_NISA + text + entryText);
                } else { // ファンドタイプ=1,2　のレコードあり
                    res.setBuyCommNisaLeft(SQL13_TESURYO_NISA_PRICE + text + entryText);
                }
            } else if (sql10Data.isExistsTwo()){
                if (!sql10Data.isExistsOne()) { //ファンドタイプ=2　のレコードのみ
                    res.setBuyCommNisaLeft(SQL13_TESURYO_NISA + SQL13_NONE + entryText);
                } else { // ファンドタイプ=1,2　のレコードあり
                    res.setBuyCommNisaLeft(SQL13_TESURYO_NISA_PRICE + SQL13_NONE + entryText);
                }
            }
        }
        // 買付手数料（税込）(NISA)右
        if (sql10Data.isExistsOne() && sql10Data.isExistsTwo()) {
            if (sql11Data.isExistsTwo() && StringUtils.equals(sql10Data.getTwo().getMFSougoSeichouServiceKbn(), "1")
                    && !StringUtils.equals(sql11Data.getTwo().getFcmHSettingSeichou(), "0")) {
                var text = getTesuryoText(false, sql11Data.getTwo().getFcmKubun(),
                        getSql11NisaTesuryoRecords(sql11Data.getTwo()), sql10Data.getTwo().getMFHTanikuchi(), todayTax);
                res.setBuyCommNisaRight(SQL13_TESURYO_NISA_PRICE + text + entryText);
            } else {
                if (sql11Data.isExistsTwo()) { //ファンドタイプ=2　のレコードあり
                    res.setBuyCommNisaRight(SQL13_TESURYO_NISA_PRICE + SQL13_NONE + entryText);
                }
            }
        } else if (sql10Data.isExistsTwo()) { //ファンドタイプ=2　のレコードのみ
            if (sql11Data.isExistsTwo() && StringUtils.equals(sql10Data.getTwo().getMFSougoSeichouServiceKbn(), "1")
                    && !StringUtils.equals(sql11Data.getTwo().getFcmHSettingSeichou(), "0")) {
                var text = getTesuryoText(false, sql11Data.getTwo().getFcmKubun(),
                        getSql11NisaTesuryoRecords(sql11Data.getTwo()), sql10Data.getTwo().getMFHTanikuchi(), todayTax);
                res.setBuyCommNisaRight(SQL13_TESURYO_NISA + text + entryText);
            } else {
                if (sql11Data.isExistsTwo()) { //ファンドタイプ=2　のレコードあり
                    res.setBuyCommNisaRight(SQL13_TESURYO_NISA + SQL13_NONE + entryText);
                }
            }
        }
    }
    
    /**
     * 手数料テキストを返却する
     *
     * @param kubun FUNDタイプが1かどうか
     * @param records 手数料リスト
     * @param kuchisu 口数
     * @return 手数料テキスト
     */
    private String getTesuryoText(boolean isTypeOne, String kubun, List<TesuryoRecord> records, String kuchisu,
            BigDecimal tax) {
        
        if (StringUtils.equals(" ", kubun)) {
            return SQL13_NONE;
        }
        var unitText = (StringUtils.equals(kubun, "0") ? "口" : "円");
        var sb = new StringBuilder();
        for (int i = 0; i < records.size(); i++) {
            var current = records.get(i);
            if (i == 0) { // 先頭レコード
                if (records.get(1).isValid()) {
                    var currentUpper = calcUpper(isTypeOne, current.getUpper(), kuchisu);
                    sb.append(currentUpper + unitText + "未満 " + getTesuryo(current.getGaku(), current.getRitsu(), tax));
                } else {
                    sb.append("一" + unitText + "につき " + getTesuryo(current.getGaku(), current.getRitsu(), tax));
                    break;
                }
            } else { // 中間または最終レコード
                sb.append("、");
                // 最終レコード
                if (i == records.size() - 1 || !records.get(i + 1).isValid()) {
                    var currentUpper = calcUpper(isTypeOne, current.getUpper(), kuchisu);
                    sb.append(currentUpper + unitText + "以上 " + getTesuryo(current.getGaku(), current.getRitsu(), tax));
                    break;
                } else { // 中間レコード
                    var prevUpper = calcUpper(isTypeOne, records.get(i - 1).getUpper(), kuchisu);
                    var currentUpper = calcUpper(isTypeOne, current.getUpper(), kuchisu);
                    sb.append(prevUpper + unitText + "以上 " + currentUpper + unitText + "未満 "
                            + getTesuryo(current.getGaku(), current.getRitsu(), tax));
                }
            }
        }
        return sb.toString();
    }
    
    /**
     * 手数料上限額を計算する
     *
     * @param isTypeOne FUNDタイプが1かどうか
     * @param upper 上限金額
     * @param kuchisu 口数
     * @return 手数料N(コンパクト表示)
     */
    private String calcUpper(boolean isTypeOne, String upper, String kuchisu) {
        
        var baseUpper = new BigDecimal(upper).add(BigDecimal.ONE);
        if (isTypeOne) {
            var result = baseUpper.multiply(new BigDecimal(kuchisu));
            return toCompactNum(result.toPlainString());
        } else {
            return toCompactNum(baseUpper.toPlainString());
        }
    }
    
    /**
     * 手数料を返却する
     *
     * @param price 手数料額
     * @param ratio 手数料率
     * @param tax 税率
     * @return nn円またはnn.00%の形式
     */
    private String getTesuryo(String price, String ratio, BigDecimal tax) {
        
        if (!StringUtils.equals(price, "0")) {
            var value = new BigDecimal(price).multiply(tax).toPlainString();
            return toCompactNum(value) + "円";
        } else {
            var bdRatio = new BigDecimal(ratio);
            if (bdRatio.compareTo(BigDecimal.ZERO) != 0) {
                return bdRatio.multiply(tax).toPlainString() + "%";
            }
        }
        return "";
    }
    
    /**
     * SQL012の処理
     *
     * @param kyoukaiCd 協会コード
     * @param sql10Data SQL010の結果
     * @param res レスポンスDTO
     * @exception Exception 実行時例外
     */
    private void executeSql012(String kyoukaiCd, Sql010DataByFundType sql10Data,
            IfaMutualFundDetailInfoA001ResponseDto res) throws Exception {
        
        String reserveUnit = getTumitateUnitText(
                dao.selectIfaMutualFundDetailInfoSql012(new IfaMutualFundDetailInfoSql012RequestModel(kyoukaiCd))
                        .getDataList().stream().findFirst()
                        .map(IfaMutualFundDetailInfoSql012ResponseModel::getReserveUnit).orElse(null));
        // 買付単位
        if (sql10Data.isExistsOne() && sql10Data.isExistsTwo()) {
            switch (sql10Data.getTwo().getMFTumitateKubun()) {
            case " ":
                res.setBuyUnitWord(String.format("口数：%s／金額：%s",
                        toCompactNum(sql10Data.getOne().getMFHMinkuchi()) + "口以上"
                                + toCompactNum(sql10Data.getOne().getMFHKuchisu()) + "口単位",
                        toCompactNum(sql10Data.getTwo().getMFHMintani2()) + "円以上"
                                + toCompactNum(sql10Data.getTwo().getMFHBstani2() + "円単位")));
                break;
            case "1":
                res.setBuyUnitWord(String.format("口数：%s／積立：%s", toCompactNum(sql10Data.getOne().getMFHMinkuchi())
                        + "口以上" + toCompactNum(sql10Data.getOne().getMFHKuchisu()) + "口単位", reserveUnit));
                break;
            case "2":
                res.setBuyUnitWord(String.format("口数：%s／金額：%s／積立：%s",
                        toCompactNum(sql10Data.getOne().getMFHMinkuchi()) + "口以上"
                                + toCompactNum(sql10Data.getOne().getMFHKuchisu()) + "口単位",
                        toCompactNum(sql10Data.getTwo().getMFHMintani2()) + "円以上"
                                + toCompactNum(sql10Data.getTwo().getMFHBstani2()) + "円単位",
                        reserveUnit));
                break;
            default:
                break;
            }
        } else if (sql10Data.isExistsOne()) {
            res.setBuyUnitWord(toCompactNum(sql10Data.getOne().getMFHMinkuchi()) + "口以上"
                    + toCompactNum(sql10Data.getOne().getMFHKuchisu()) + "口単位");
        } else if (sql10Data.isExistsTwo()) {
            switch (sql10Data.getTwo().getMFTumitateKubun()) {
            case " ":
                res.setBuyUnitWord(toCompactNum(sql10Data.getTwo().getMFHMintani2()) + "円以上"
                        + toCompactNum(sql10Data.getTwo().getMFHBstani2()) + "円単位");
                break;
            case "1":
                res.setBuyUnitWord(reserveUnit);
                break;
            case "2":
                res.setBuyUnitWord(String.format("金額：%s／積立：%s", toCompactNum(sql10Data.getTwo().getMFHMintani2())
                        + "円以上" + toCompactNum(sql10Data.getTwo().getMFHBstani2()) + "円単位", reserveUnit));
                break;
            default:
                break;
            }
        }
        
    }
    
    /**
     * SQL011の処理
     *
     * @param kyoukaiCd 協会コード
     * @param res レスポンスDTO
     * @exception Exception 実行時例外
     */
    private Sql011DataByFundType executeSql011(String kyoukaiCd, IfaMutualFundDetailInfoA001ResponseDto res)
            throws Exception {
        
        return Sql011DataByFundType
                .of(dao.selectIfaMutualFundDetailInfoSql011(new IfaMutualFundDetailInfoSql011RequestModel(kyoukaiCd))
                        .getDataList());
    }
    
    /**
     * SQL010の処理
     *
     * @param kyoukaiCd 協会コード
     * @param res009 SQL009の結果(SQL010でレコードがある場合は、SQL009も存在する前提)
     * @param res レスポンスDTO
     * @exception Exception 実行時例外
     */
    private Sql010DataByFundType executeSql010(String kyoukaiCd, IfaMutualFundDetailInfoSql009ResponseModel res009,
            IfaMutualFundDetailInfoA001ResponseDto res) throws Exception {
        
        var data = Sql010DataByFundType
                .of(dao.selectIfaMutualFundDetailInfoSql010(new IfaMutualFundDetailInfoSql010RequestModel(kyoukaiCd))
                        .getDataList());
        // 取引コース
        if (data.isExistsOne() && data.isExistsTwo()) {
            res.setCourse(SQL10_COURSE_BOTH);
        } else if (data.isExistsOne()) {
            res.setCourse(SQL10_COURSE_ONE);
        } else if (data.isExistsTwo()) {
            res.setCourse(SQL10_COURSE_TWO);
        }
        // 定期売却
        if (data.isExistsOne()) {
            res.setFundTypeName(SQL10_TEIKI_ON);
        } else {
            res.setFundTypeName(SQL10_TEIKI_OFF);
        }
        // 売却単位
        if (data.isExistsOne() && !data.isExistsTwo()) {
            res.setSellSharesWord(String.format("%s口以上%s口単位%s", toCompactNum(data.getOne().getMFKMinkuchi()),
                    toCompactNum(data.getOne().getMFKKuchisu()), SQL10_SELL_NOTES));
        } else if (data.isExistsTwo()) {
            res.setSellSharesWord(
                    String.format("口数：%s口以上%s口単位／金額：%s円以上%s円単位%s", toCompactNum(data.getTwo().getMFKMinkuchi()),
                            toCompactNum(data.getTwo().getMFKKuchisu()), toCompactNum(data.getTwo().getMFKMintani2()),
                            toCompactNum(data.getTwo().getMFKBstani2()), SQL10_SELL_NOTES));
        }
        // 当初一口当り元本
        if (data.isExistsOne()) {
            res.setIndividualPrincipal(String.format("%s円", toCompactNum(data.one.getMFGakumen())));
        } else if (data.isExistsTwo()) {
            res.setIndividualPrincipal("1円");
        }
        // 当社締切時間
        if (data.isExistsOne() && data.isExistsTwo()) {
            if (!StringUtils.equals(data.getOne().getMFShimekiri(), data.getTwo().getMFShimekiri())) {
                res.setShrineDeadlines(
                        String.format("一般口：%s／累投口：%s", data.getOne().getMFShimekiri(), data.getTwo().getMFShimekiri()));
            } else {
                res.setShrineDeadlines(data.getOne().getMFShimekiri());
            }
        } else if (data.isExistsOne()) {
            res.setShrineDeadlines(data.getOne().getMFShimekiri());
        } else if (data.isExistsTwo()) {
            res.setShrineDeadlines(data.getTwo().getMFShimekiri());
        }
        // 約定日
        if (data.isExistsOne() && data.isExistsTwo()) {
            if (!StringUtils.equals(data.getOne().getMFYkujyokubun(), data.getTwo().getMFYkujyokubun())) {
                res.setTradeDate(String.format("%s(一般口)／%s(累投口)%s", data.getOne().getMFYkujyokubun(),
                        data.getTwo().getMFYkujyokubun(), SQL10_TRADE_NOTES));
            } else {
                res.setTradeDate(data.getOne().getMFYkujyokubun() + SQL10_TRADE_NOTES);
            }
        } else if (data.isExistsOne()) {
            res.setTradeDate(data.getOne().getMFYkujyokubun() + SQL10_TRADE_NOTES);
        } else if (data.isExistsTwo()) {
            res.setTradeDate(data.getTwo().getMFYkujyokubun() + SQL10_TRADE_NOTES);
        }
        // 受渡日
        if (data.isExistsOne() && data.isExistsTwo()) {
            if (!StringUtils.equals(data.getOne().getMFUkewatashi(), data.getTwo().getMFUkewatashi())) {
                res.setSettlementDate(String.format("一般口：%s／累投口：%s", data.getOne().getMFUkewatashi(),
                        data.getTwo().getMFUkewatashi()));
            } else {
                res.setSettlementDate(data.getOne().getMFUkewatashi());
            }
        } else if (data.isExistsOne()) {
            res.setSettlementDate(data.getOne().getMFUkewatashi());
        } else if (data.isExistsTwo()) {
            res.setSettlementDate(data.getTwo().getMFUkewatashi());
        }
        // 分配金受取方法
        if (data.isExistsOne() && data.isExistsTwo()) {
            res.setDistributionReceiveMethodWord(
                    String.format("%s／%s", data.getOne().getMFSaitoushiKubun(), data.getTwo().getMFSaitoushiKubun()));
        } else if (data.isExistsOne()) {
            res.setDistributionReceiveMethodWord(data.getOne().getMFSaitoushiKubun());
        } else if (data.isExistsTwo()) {
            res.setDistributionReceiveMethodWord(data.getTwo().getMFSaitoushiKubun());
        }
        // 償還日
        var syokanKbn = Optional.ofNullable(res009).map(IfaMutualFundDetailInfoSql009ResponseModel::getFDSyokanKbn)
                .orElse("");
        
        if (data.isExistsOne() && data.isExistsTwo()) {
            if (!StringUtils.equals(data.getOne().getMFSyokanDate(), data.getTwo().getMFSyokanDate())) {
                res.setRedemptionDate(String.format("一般口：%s／累投口：%s%s", data.getOne().getMFSyokanDate(),
                        data.getTwo().getMFSyokanDate(), syokanKbn));
            } else {
                res.setRedemptionDate(data.getOne().getMFSyokanDate() + syokanKbn);
            }
        } else if (data.isExistsOne()) {
            res.setRedemptionDate(data.getOne().getMFSyokanDate() + syokanKbn);
        } else if (data.isExistsTwo()) {
            res.setRedemptionDate(data.getTwo().getMFSyokanDate() + syokanKbn);
        }
        //
        return data;
    }
    
    /**
     * BigDecimalをString形式に変換
     *
     * @param bd BigDecimal
     * @return String(bdがnullの場合はnull返却)
     */
    private String toNullableString(BigDecimal bd) {
        
        if (Objects.isNull(bd)) {
            return null;
        }
        return bd.toPlainString();
    }
    
    /**
     * SQL010ファンドタイプごとのデータ
     *
     * @author SCSK
     *
     */
    @Getter
    private static class Sql010DataByFundType {
        
        /** ファンドタイプ1がある */
        private boolean existsOne;
        
        /** ファンドタイプ2がある */
        private boolean existsTwo;
        
        /** ファンドタイプ1データ */
        private IfaMutualFundDetailInfoSql010ResponseModel one;
        
        /** ファンドタイプ2データ */
        private IfaMutualFundDetailInfoSql010ResponseModel two;
        
        /**
         * インスタンス生成
         *
         * @param list sql010レスポンスリスト
         * @return インスタンス
         */
        static Sql010DataByFundType of(List<IfaMutualFundDetailInfoSql010ResponseModel> list) {
            
            var ret = new Sql010DataByFundType();
            for (var item : list) {
                if (StringUtils.equals(item.getMFType(), "1")) {
                    ret.existsOne = true;
                    ret.one = item;
                } else if (StringUtils.equals(item.getMFType(), "2")) {
                    ret.existsTwo = true;
                    ret.two = item;
                }
            }
            return ret;
        }
    }
    
    /**
     * 手数料レコード
     *
     */
    @Data
    @AllArgsConstructor
    private static class TesuryoRecord {
        
        /** 上限額 */
        private String upper;
        
        /** 手数料額 */
        private String gaku;
        
        /** 手数料率 */
        private String ritsu;
        
        /** レコードが有効かどうか */
        public boolean isValid() {
            
            return !StringUtils.equals(upper, "0");
        }
    }
    
    /**
     * SQL011ファンドタイプごとのデータ
     *
     * @author SCSK
     *
     */
    @Getter
    private static class Sql011DataByFundType {
        
        /** ファンドタイプ1がある */
        private boolean existsOne;
        
        /** ファンドタイプ2がある */
        private boolean existsTwo;
        
        /** ファンドタイプ1データ */
        private IfaMutualFundDetailInfoSql011ResponseModel one;
        
        /** ファンドタイプ2データ */
        private IfaMutualFundDetailInfoSql011ResponseModel two;
        
        /**
         * インスタンス生成
         *
         * @param list sql011レスポンスリスト
         * @return インスタンス
         */
        static Sql011DataByFundType of(List<IfaMutualFundDetailInfoSql011ResponseModel> list) {
            
            var ret = new Sql011DataByFundType();
            for (var item : list) {
                if (StringUtils.equals(item.getFcmType(), "1")) {
                    ret.existsOne = true;
                    ret.one = item;
                } else if (StringUtils.equals(item.getFcmType(), "2")) {
                    ret.existsTwo = true;
                    ret.two = item;
                }
            }
            return ret;
        }
    }
    
    /**
     * 兆憶万をつけた形式で戻す
     *
     * @param value 数値文字列(小数点は非対応。※消費税計算結果に.0がついてしまうと変換できないが、既存も同様なので問題なしとする)
     * @return 短縮形式
     */
    @VisibleForTesting
    String toCompactNum(String value) {
        
        if (!StringUtils.isNumeric(value)) {
            return value;
        }
        long numValue = Long.parseLong(value);
        if (numValue == 0L) {
            return "0";
        }
        String numberString = "";
        var div = 1000000000000L;
        for (var unit : List.of("兆", "億", "万", "")) {
            if (Math.abs(numValue / div) > 0L) {
                numberString += (numValue / div) + unit;
            }
            numValue -= (numValue / div) * div;
            div = div / 10000L;
        }
        return numberString;
    }
    
    /** SQL10 手数料リストを返却する */
    private List<TesuryoRecord> getSql10TesuryoRecords(IfaMutualFundDetailInfoSql010ResponseModel model) {
        
        return List.of(new TesuryoRecord(model.getMFHUpper1(), model.getMFHGaku1(), model.getMFHRitsu1()),
                new TesuryoRecord(model.getMFHUpper2(), model.getMFHGaku2(), model.getMFHRitsu2()),
                new TesuryoRecord(model.getMFHUpper3(), model.getMFHGaku3(), model.getMFHRitsu3()),
                new TesuryoRecord(model.getMFHUpper4(), model.getMFHGaku4(), model.getMFHRitsu4()),
                new TesuryoRecord(model.getMFHUpper5(), model.getMFHGaku5(), model.getMFHRitsu5()),
                new TesuryoRecord(model.getMFHUpper6(), model.getMFHGaku6(), model.getMFHRitsu6()),
                new TesuryoRecord(model.getMFHUpper7(), model.getMFHGaku7(), model.getMFHRitsu7()));
    }
    
    /** SQL10 NISA手数料リストを返却する */
    private List<TesuryoRecord> getSql10NisaTesuryoRecords(IfaMutualFundDetailInfoSql010ResponseModel model) {
        
        return List.of(
                new TesuryoRecord(model.getMFHUpperSeichou1(), model.getMFHGakuSeichou1(), model.getMFHRitsuSeichou1()),
                new TesuryoRecord(model.getMFHUpperSeichou2(), model.getMFHGakuSeichou2(), model.getMFHRitsuSeichou2()),
                new TesuryoRecord(model.getMFHUpperSeichou3(), model.getMFHGakuSeichou3(), model.getMFHRitsuSeichou3()),
                new TesuryoRecord(model.getMFHUpperSeichou4(), model.getMFHGakuSeichou4(), model.getMFHRitsuSeichou4()),
                new TesuryoRecord(model.getMFHUpperSeichou5(), model.getMFHGakuSeichou5(), model.getMFHRitsuSeichou5()),
                new TesuryoRecord(model.getMFHUpperSeichou6(), model.getMFHGakuSeichou6(), model.getMFHRitsuSeichou6()),
                new TesuryoRecord(model.getMFHUpperSeichou7(), model.getMFHGakuSeichou7(),
                        model.getMFHRitsuSeichou7()));
    }
    
    /** SQL11 NISA手数料リストを返却する */
    private List<TesuryoRecord> getSql11NisaTesuryoRecords(IfaMutualFundDetailInfoSql011ResponseModel model) {
        
        return List.of(new TesuryoRecord(model.getFcmHUpperSeichou1(), "0", model.getFcmHRitsuSeichou1()),
                new TesuryoRecord(model.getFcmHUpperSeichou2(), "0", model.getFcmHRitsuSeichou2()),
                new TesuryoRecord(model.getFcmHUpperSeichou3(), "0", model.getFcmHRitsuSeichou3()),
                new TesuryoRecord(model.getFcmHUpperSeichou4(), "0", model.getFcmHRitsuSeichou4()),
                new TesuryoRecord(model.getFcmHUpperSeichou5(), "0", model.getFcmHRitsuSeichou5()),
                new TesuryoRecord(model.getFcmHUpperSeichou6(), "0", model.getFcmHRitsuSeichou6()),
                new TesuryoRecord(model.getFcmHUpperSeichou7(), "0", model.getFcmHRitsuSeichou7()));
    }
    
    /** SQL11 手数料リストを返却する */
    private List<TesuryoRecord> getSql11TesuryoRecords(IfaMutualFundDetailInfoSql011ResponseModel model) {
        
        return List.of(new TesuryoRecord(model.getFcmUpper1(), "0", model.getFcmRitsu1()),
                new TesuryoRecord(model.getFcmUpper2(), "0", model.getFcmRitsu2()),
                new TesuryoRecord(model.getFcmUpper3(), "0", model.getFcmRitsu3()),
                new TesuryoRecord(model.getFcmUpper4(), "0", model.getFcmRitsu4()),
                new TesuryoRecord(model.getFcmUpper5(), "0", model.getFcmRitsu5()),
                new TesuryoRecord(model.getFcmUpper6(), "0", model.getFcmRitsu6()),
                new TesuryoRecord(model.getFcmUpper7(), "0", model.getFcmRitsu7()));
    }
    
}
