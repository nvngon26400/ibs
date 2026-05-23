package com.sbisec.helios.ap.brokerageMenu.commFee.util;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaCommFeeCsvItems;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

/**
 * 画面ID：SUB020502-01
 * 画面名：手数料・報酬
 * 2024/05/31 新規作成
 *
 * @author SCSK 江口
 */
public class IfaCommFeeCsvOut extends CsvOutPutUtil {

    /**
     * コンストラクタ
     *
     * @param comp コンプライアンス
     */
    public IfaCommFeeCsvOut(ComplianceService comp) {
        super(comp);
    }

    /**
     * 手数料・報酬のCSVファイルのヘッダーを出力する。
     *
     * @param pattern パターン
     */
    protected String getCsvHeader(IfaCommFeePattern pattern) {
        return getCsvHeaderInner(pattern);
    }

    /**
     * 手数料・報酬のCSVファイルのヘッダーを出力する。
     *
     * @param patternData シリアライズされたIfaCommFeePattern
     */
    public static String getHeader(String patternData) {
        IfaCommFeePattern pattern = IfaCommFeePattern.deserialize(patternData);

        return getCsvHeaderInner(pattern);
    }

    /**
     * 手数料・報酬のCSVファイルのヘッダーを出力する。
     *
     * @param pattern パターン
     */
    private static String getCsvHeaderInner(IfaCommFeePattern pattern) {
        StringBuilder sb = new StringBuilder(512);

        /* ============================================================== */
        /* 年月日はNo.1, 2, 3, 10のみ表示                                  */
        /* ============================================================== */
        if (pattern.anyMatch("1", "2", "3", "10")) {
            sb.append(DOUBLE_QUOTATION + "年月日" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);
        }

        /* ============================================================== */
        /* 年月はNo.4, 5, 6, 7, 8, 9, 11, 12のみ表示                       */
        /* ============================================================== */
        if (pattern.anyMatch("4", "5", "6", "7", "8", "9", "11", "12")) {
            sb.append(DOUBLE_QUOTATION + "年月" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);
        }

        /* ============================================================== */
        /* 仲介業者コード～仲介業者名は全てのパターンで表示                  */
        /* ============================================================== */
        sb.append(DOUBLE_QUOTATION + "仲介業者コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "仲介業者名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);

        /* ============================================================== */
        /* 営業員コード、営業員名はNo.3, 6, 9のみ表示                       */
        /* ============================================================== */
        if (pattern.anyMatch("3", "6", "9")) {
            sb.append(DOUBLE_QUOTATION + "営業員コード" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);
            sb.append(DOUBLE_QUOTATION + "営業員名" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);
        }

        /* ============================================================== */
        /* 扱者コード、取引コースはNo.2, 5, 8のみ表示                       */
        /* ============================================================== */
        if (pattern.anyMatch("2", "5", "8")) {
            sb.append(DOUBLE_QUOTATION + "扱者コード" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);
            sb.append(DOUBLE_QUOTATION + "取引コース" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);
        }

        /* ============================================================== */
        /* 国内株式 現物～為替取引は全てのパターンで表示                     */
        /* ============================================================== */
        sb.append(DOUBLE_QUOTATION + "国内株式 現物" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "国内株式 信用" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "国内株式 IPO・PO" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "国内CB" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "国内投信 販売" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "国内投信 信報" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "外国投信 販売" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "外国投信 信報" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "外国投信 信報（その他）" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "先物・OP" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "国内債券" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "外国債券（円建）" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "外国債券（外貨建）" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "外国株式" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "米株信用" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "ST" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "ST信報" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "外貨建MMF 信報" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "為替取引" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);

        /* ============================================================== */
        /* 投信（SBIラップ）マイレージ～現株ポイントはNo.7, 8, 9, 12のみ表示 */
        /* ============================================================== */
        if (pattern.anyMatch("7", "8", "9", "12")) {
            sb.append(DOUBLE_QUOTATION + "投信（SBIラップ）マイレージ" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);        
            sb.append(DOUBLE_QUOTATION + "投信マイレージ" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);

            if (pattern.isShowSpotStockPoint()) {
                sb.append(DOUBLE_QUOTATION + "現株ポイント" + DOUBLE_QUOTATION);
                sb.append(CSV_SEPARATER);
            }
        }

        /* ============================================================== */
        /* SBIラップ管理報酬（ネット）～（x投資）は表示制御に従う            */
        /* ============================================================== */
        if (pattern.isShowSbiRapCourse01()) {
            sb.append(DOUBLE_QUOTATION + pattern.getSbiRapCourse01ServiceName() + DOUBLE_QUOTATION);        // SBIラップ管理報酬(ネット)
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse02()) {
            sb.append(DOUBLE_QUOTATION + pattern.getSbiRapCourse02ServiceName() + DOUBLE_QUOTATION);      // SBIラップ管理報酬(店頭)
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse03()) {
            sb.append(DOUBLE_QUOTATION + pattern.getSbiRapCourse03ServiceName() + DOUBLE_QUOTATION);     // SBIラップ管理報酬(匠)
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse04()) {
            sb.append(DOUBLE_QUOTATION + pattern.getSbiRapCourse04ServiceName() + DOUBLE_QUOTATION); // SBIラップ管理報酬(x投資)
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse05()) {
            sb.append(DOUBLE_QUOTATION + pattern.getSbiRapCourse05ServiceName() + DOUBLE_QUOTATION); // SBIラップコース05
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse06()) {
            sb.append(DOUBLE_QUOTATION + pattern.getSbiRapCourse06ServiceName() + DOUBLE_QUOTATION); // SBIラップコース06
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse07()) {
            sb.append(DOUBLE_QUOTATION + pattern.getSbiRapCourse07ServiceName() + DOUBLE_QUOTATION); // SBIラップコース07
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse08()) {
            sb.append(DOUBLE_QUOTATION + pattern.getSbiRapCourse08ServiceName() + DOUBLE_QUOTATION); // SBIラップコース08
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse09()) {
            sb.append(DOUBLE_QUOTATION + pattern.getSbiRapCourse09ServiceName() + DOUBLE_QUOTATION); // SBIラップコース09
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse10()) {
            sb.append(DOUBLE_QUOTATION + pattern.getSbiRapCourse10ServiceName() + DOUBLE_QUOTATION); // SBIラップコース10
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse11()) {
            sb.append(DOUBLE_QUOTATION + pattern.getSbiRapCourse11ServiceName() + DOUBLE_QUOTATION); // SBIラップコース11
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse12()) {
            sb.append(DOUBLE_QUOTATION + pattern.getSbiRapCourse12ServiceName() + DOUBLE_QUOTATION); // SBIラップコース12
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse13()) {
            sb.append(DOUBLE_QUOTATION + pattern.getSbiRapCourse13ServiceName() + DOUBLE_QUOTATION); // SBIラップコース13
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse14()) {
            sb.append(DOUBLE_QUOTATION + pattern.getSbiRapCourse14ServiceName() + DOUBLE_QUOTATION); // SBIラップコース14
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse15()) {
            sb.append(DOUBLE_QUOTATION + pattern.getSbiRapCourse15ServiceName() + DOUBLE_QUOTATION); // SBIラップコース15
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse16()) {
            sb.append(DOUBLE_QUOTATION + pattern.getSbiRapCourse16ServiceName() + DOUBLE_QUOTATION); // SBIラップコース16
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse17()) {
            sb.append(DOUBLE_QUOTATION + pattern.getSbiRapCourse17ServiceName() + DOUBLE_QUOTATION); // SBIラップコース17
            sb.append(CSV_SEPARATER);
        }

        /* ============================================================== */
        /* 小計～その他6はNo.7, 8のみ表示                                  */
        /* ============================================================== */
        if (pattern.anyMatch("7", "8")) {
            sb.append(DOUBLE_QUOTATION + "小計" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);
            sb.append(DOUBLE_QUOTATION + "その他1" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);
            sb.append(DOUBLE_QUOTATION + "その他2" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);
            sb.append(DOUBLE_QUOTATION + "その他3" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);
            sb.append(DOUBLE_QUOTATION + "その他4" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);
            sb.append(DOUBLE_QUOTATION + "その他5" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);
            sb.append(DOUBLE_QUOTATION + "その他6" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);
        }

        /* ============================================================== */
        /* 手数料合計はNo.1, 2, 3, 4, 5, 6, 10, 11のみ表示                 */
        /* ============================================================== */
        if (pattern.anyMatch("1", "2", "3", "4", "5", "6", "10", "11")) {
            sb.append(DOUBLE_QUOTATION + "手数料合計" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);
        }

        /* ============================================================== */
        /* 合計はNo.7, 8, 9, 12のみ表示                                    */
        /* ============================================================== */
        if (pattern.anyMatch("7", "8", "9", "12")) {
            sb.append(DOUBLE_QUOTATION + "合計" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);
        }

        /* ============================================================== */
        /* 消費税、支払報酬額はNo.7のみ表示                                 */
        /* ============================================================== */
        if (pattern.anyMatch("7")) {
            sb.append(DOUBLE_QUOTATION + "消費税" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);
            sb.append(DOUBLE_QUOTATION + "支払報酬額" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);
        }

        /* ============================================================== */
        /* 支店コード、支店名はNo.3, 6, 9, 10, 11, 12のみ表示               */
        /* ============================================================== */
        if (pattern.anyMatch("3", "6", "9", "10", "11", "12")) {
            sb.append(DOUBLE_QUOTATION + "支店コード" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);
            sb.append(DOUBLE_QUOTATION + "支店名" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);
        }

        // 末尾のカンマを削除
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    /**
     * 手数料・報酬のCSVファイルの本体を出力する。
     *
     * @param csvItems IfaCommFeeCsvItems型のCSV1行分のデータ
     * @param patternStr IfaCommFeePatternのインスタンスを文字列にシリアライズした値
     */
    protected String getCsvLine(ModelBase modelBase, IfaCommFeePattern pattern) {

        IfaCommFeeCsvItems csvItems = (IfaCommFeeCsvItems) modelBase;
        StringBuilder sb = new StringBuilder();

        /* ============================================================== */
        /* 年月日はNo.1, 2, 3, 10のみ表示                                  */
        /* ============================================================== */
        if (pattern.anyMatch("1", "2", "3", "10")) {
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getDateYmd()) + DOUBLE_QUOTATION);                       // 年月日
            sb.append(CSV_SEPARATER);
        }

        /* ============================================================== */
        /* 年月はNo.4, 5, 6, 7, 8, 9, 11, 12のみ表示                       */
        /* ============================================================== */
        if (pattern.anyMatch("4", "5", "6", "7", "8", "9", "11", "12")) {
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getDateYm()) + DOUBLE_QUOTATION);                        // 年月
            sb.append(CSV_SEPARATER);
        }

        /* ============================================================== */
        /* 仲介業者コード～仲介業者名は全てのパターンで表示                  */
        /* ============================================================== */
        sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getBrokerCode()) + DOUBLE_QUOTATION);                        // 仲介業者コード
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBrokerName())) + DOUBLE_QUOTATION);           // 仲介業者名
        sb.append(CSV_SEPARATER);

        /* ============================================================== */
        /* 営業員コード、営業員名はNo.3, 6, 9のみ表示                       */
        /* ============================================================== */
        if (pattern.anyMatch("3", "6", "9")) {
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getEmpCode()) + DOUBLE_QUOTATION);                       // 営業員コード
            sb.append(CSV_SEPARATER);
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBrokerChargeName())) + DOUBLE_QUOTATION); // 営業員名
            sb.append(CSV_SEPARATER);
        }

        /* ============================================================== */
        /* 扱者コード、取引コースはNo.2, 5, 8のみ表示                       */
        /* ============================================================== */
        if (pattern.anyMatch("2", "5", "8")) {
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getDealerNumber()) + DOUBLE_QUOTATION);                  // 扱者コード
            sb.append(CSV_SEPARATER);
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getCourse())) + DOUBLE_QUOTATION);           // 取引コース
            sb.append(CSV_SEPARATER);
        }

        /* ============================================================== */
        /* 国内株式 現物～為替取引は全てのパターンで表示                     */
        /* ============================================================== */
        sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getDomesticStockSpot()) + DOUBLE_QUOTATION);                 // 国内株式 現物
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getDomesticStockMargin()) + DOUBLE_QUOTATION);               // 国内株式 信用
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getDomesticStockIpoPo()) + DOUBLE_QUOTATION);                // 国内株式 IPO・PO
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getDomesticCb()) + DOUBLE_QUOTATION);                        // 国内CB
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getDomesticMutualFundSales()) + DOUBLE_QUOTATION);           // 国内投信 販売
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getDomesticMutualFundTrustFee()) + DOUBLE_QUOTATION);        // 国内投信 信報
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getForeignMutualFundSales()) + DOUBLE_QUOTATION);            // 外国投信 販売
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getForeignMutualFundTrustFee()) + DOUBLE_QUOTATION);         // 外国投信 信報
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getForeignMutualFundOtherTrustFee()) + DOUBLE_QUOTATION);    // 外国投信 信報(その他)
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getFuturesOptions()) + DOUBLE_QUOTATION);                    // 先物 OP
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getDomesticBond()) + DOUBLE_QUOTATION);                      // 国内債券
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getJpyForeignBond()) + DOUBLE_QUOTATION);                    // 外国債券(円建)
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getForeignBond()) + DOUBLE_QUOTATION);                       // 外国債券(外貨建)
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getForeignStock()) + DOUBLE_QUOTATION);                      // 外国株式
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getAmericaStockMargin()) + DOUBLE_QUOTATION);                // 米株信用
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getSt()) + DOUBLE_QUOTATION);                                // ST
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getStSintaku()) + DOUBLE_QUOTATION);                       // ST信報
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getForeignMmfTrustFee()) + DOUBLE_QUOTATION);                // 外貨建MMF
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getFxTrade()) + DOUBLE_QUOTATION);                           // 為替取引
        sb.append(CSV_SEPARATER);

        /* ============================================================== */
        /* 投信（SBIラップ）マイレージ～現株ポイントはNo.7, 8, 9, 12のみ表示 */
        /* ============================================================== */
        if (pattern.anyMatch("7", "8", "9", "12")) {
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getMutualFundSbiWrapMileage()) + DOUBLE_QUOTATION);      // 投信(SBIラップ)マイレージ
            sb.append(CSV_SEPARATER);        
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getMutualFundMileage()) + DOUBLE_QUOTATION);             // 投信マイレージ
            sb.append(CSV_SEPARATER);

            if (pattern.isShowSpotStockPoint()) {
                sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getSpotStockPoint()) + DOUBLE_QUOTATION);            // 現株ポイント
                sb.append(CSV_SEPARATER);
            }
        }

        /* ============================================================== */
        /* SBIラップ管理報酬（ネット）～（x投資）はは表示制御に従う          */
        /* ============================================================== */
        if (pattern.isShowSbiRapCourse01()) {
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getSbiRap01()) + DOUBLE_QUOTATION);            // SBIラップ管理報酬(ネット)
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse02()) {
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getSbiRap02()) + DOUBLE_QUOTATION);          // SBIラップ管理報酬(店頭)
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse03()) {
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getSbiRap03()) + DOUBLE_QUOTATION);         // SBIラップ管理報酬(匠)
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse04()) {
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getSbiRap04()) + DOUBLE_QUOTATION);     // SBIラップ管理報酬(x投資)
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse05()) {
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getSbiRap05()) + DOUBLE_QUOTATION);                // SBIラップコース05
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse06()) {
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getSbiRap06()) + DOUBLE_QUOTATION);                // SBIラップコース06
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse07()) {
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getSbiRap07()) + DOUBLE_QUOTATION);                // SBIラップコース07
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse08()) {
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getSbiRap08()) + DOUBLE_QUOTATION);                // SBIラップコース08
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse09()) {
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getSbiRap09()) + DOUBLE_QUOTATION);                // SBIラップコース09
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse10()) {
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getSbiRap10()) + DOUBLE_QUOTATION);                // SBIラップコース10
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse11()) {
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getSbiRap11()) + DOUBLE_QUOTATION);                // SBIラップコース11
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse12()) {
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getSbiRap12()) + DOUBLE_QUOTATION);                // SBIラップコース12
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse13()) {
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getSbiRap13()) + DOUBLE_QUOTATION);                // SBIラップコース13
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse14()) {
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getSbiRap14()) + DOUBLE_QUOTATION);                // SBIラップコース14
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse15()) {
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getSbiRap15()) + DOUBLE_QUOTATION);                // SBIラップコース15
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse16()) {
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getSbiRap16()) + DOUBLE_QUOTATION);                // SBIラップコース16
            sb.append(CSV_SEPARATER);
        }

        if (pattern.isShowSbiRapCourse17()) {
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getSbiRap17()) + DOUBLE_QUOTATION);                // SBIラップコース17
            sb.append(CSV_SEPARATER);
        }


        /* ============================================================== */
        /* 小計～その他6はNo.7, 8のみ表示                                  */
        /* ============================================================== */
        if (pattern.anyMatch("7", "8")) {
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getRewardSubtotal()) + DOUBLE_QUOTATION);                // 小計
            sb.append(CSV_SEPARATER);
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getOther1()) + DOUBLE_QUOTATION);                        // その他1
            sb.append(CSV_SEPARATER);
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getOther2()) + DOUBLE_QUOTATION);                        // その他2
            sb.append(CSV_SEPARATER);
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getOther3()) + DOUBLE_QUOTATION);                        // その他3
            sb.append(CSV_SEPARATER);
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getOther4()) + DOUBLE_QUOTATION);                        // その他4
            sb.append(CSV_SEPARATER);
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getOther5()) + DOUBLE_QUOTATION);                        // その他5
            sb.append(CSV_SEPARATER);
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getOther6()) + DOUBLE_QUOTATION);                        // その他6
            sb.append(CSV_SEPARATER);
        }

        /* ============================================================== */
        /* 手数料合計はNo.1, 2, 3, 4, 5, 6, 10, 11のみ表示                 */
        /* ============================================================== */
        if (pattern.anyMatch("1", "2", "3", "4", "5", "6", "10", "11")) {
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getCommTotal()) + DOUBLE_QUOTATION);                     // 手数料合計
            sb.append(CSV_SEPARATER);
        }

        /* ============================================================== */
        /* 合計はNo.7, 8, 9, 12のみ表示                                    */
        /* ============================================================== */
        if (pattern.anyMatch("7", "8", "9", "12")) {
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getTotal()) + DOUBLE_QUOTATION);                         // 合計
            sb.append(CSV_SEPARATER);
        }

        /* ============================================================== */
        /* 消費税、支払報酬額はNo.7のみ表示                                 */
        /* ============================================================== */
        if (pattern.anyMatch("7")) {
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getConsumptionTax()) + DOUBLE_QUOTATION);                // 消費税
            sb.append(CSV_SEPARATER);
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getPaymentFeeAmount()) + DOUBLE_QUOTATION);              // 支払報酬額
            sb.append(CSV_SEPARATER);
        }

        /* ============================================================== */
        /* 支店コード、支店名はNo.3, 6, 9, 10, 11, 12のみ表示               */
        /* ============================================================== */
        if (pattern.anyMatch("3", "6", "9", "10", "11", "12")) {
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getBranchCode()) + DOUBLE_QUOTATION);                    // 支店コード
            sb.append(CSV_SEPARATER);
            sb.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBranchName())) + DOUBLE_QUOTATION);       // 支店名
            sb.append(CSV_SEPARATER);
        }

        // 末尾のカンマを削除
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    /**
     * doCreateCsvFile
     * csvファイルをテンポラリディレクトリーに出力する。
     *
     * @param dataList 出力データ
     * @param sessionId フレームワークセッションID
     * @param userId ユーザ共通情報.ユーザID
     * @param pattern 出力パターン
     * @return String フルパスファイル名
     * @throws Exception システムエラー
     */
    @SuppressWarnings("rawtypes")
    public String doCreateCsvFile(DataList dataList, String sessionId, String userId, IfaCommFeePattern pattern) throws Exception {
        
        // Create csv temporary file name
        String tmpCsv = String.format(TEMPORARY_CSV_FILE_NAME,
                DateTimeFormatter.ofPattern(CSV_TEMPORARY_DATE_PATTERN).format(LocalDateTime.now()), sessionId, userId);
        try {
            tmpCsv = getCsvTemporaryDirectory() + tmpCsv;
            File file = new File(tmpCsv);
            PrintWriter out = new PrintWriter(file);
            
            List list = dataList.getDataList();
            int listSize = list.size();
            
            // Write header
            out.println(getCsvHeader(pattern));
            
            for (int i = 0; i < listSize; i++) {
                out.println(getCsvLine((ModelBase) list.get(i), pattern));
            }
            
            out.flush();
            out.close();
            
            // Catch and ingore.
        } catch (Exception e) {
            throw e;
        }
        
        return tmpCsv;
    }
}
