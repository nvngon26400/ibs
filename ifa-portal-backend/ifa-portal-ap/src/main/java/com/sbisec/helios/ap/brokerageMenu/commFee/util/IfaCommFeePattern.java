package com.sbisec.helios.ap.brokerageMenu.commFee.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaCommFeeSql013ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaCommFeeSql015ResponseModel;


/**
 * 画面ID：SUB020502-01
 * 画面名：手数料・報酬
 * 2024/05/31 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaCommFeePattern {

    /** 表示内容（手数料/報酬).手数料 */
    private static final String FEE_COMM_DISPLAY_CONTENT_FEE = "0";

    /** 表示内容（手数料/報酬).報酬 */
    private static final String FEE_COMM_DISPLAY_CONTENT_COMM = "1";

    /** 集計単位（日次/月次）.日次 */
    private static final String DAILY_MONTHLY_COUNTING_UNIT_DAILY = "0";

    /** 集計単位（日次/月次）.日次 */
    private static final String DAILY_MONTHLY_COUNTING_UNIT_MONTHLY = "1";

    /** 集計単位（仲介業者/営業員/支店）.仲介業者 */
    private static final String BROKER_CHARGE_BRANCH_COUNTING_UNIT_BROKER = "0";

    /** 集計単位（仲介業者/営業員/支店）.営業員 */
    private static final String BROKER_CHARGE_BRANCH_COUNTING_UNIT_CHARGE = "1";

    /** 集計単位（仲介業者/営業員/支店）.支店 */
    private static final String BROKER_CHARGE_BRANCH_COUNTING_UNIT_BRANCH = "2";

    /** 集計単位（集計/扱者）.集計 */
    private static final String AGGREGATOR_HANDLER_COUNTING_UNIT_AGGREGATOR = "0";

    /** 集計単位（集計/扱者）.集計 */
    private static final String AGGREGATOR_HANDLER_COUNTING_UNIT_HANDLER = "1";

    /** 権限コード.SBI証券本店 */
    private static final String PRIV_ID_SBI_HEAD = "1";

    /** 権限コード.SBI証券支店 */
    private static final String PRIV_ID_SBI_BRANCH = "2";

    /** SBIラップ(ネット) */
    private static final String SBI_RAP_COURSE_01 = "sbiRap01";

    /** SBIラップ(店頭) */
    private static final String SBI_RAP_COURSE_02 = "sbiRap02";

    /** SBIラップ(匠) */
    private static final String SBI_RAP_COURSE_03 = "sbiRap03";

    /** SBIラップ(x投資) */
    private static final String SBI_RAP_COURSE_04 = "sbiRap04";

    /** SBIラップコース05 */
    private static final String SBI_RAP_COURSE_05 = "sbiRap05";

    /** SBIラップコース06 */
    private static final String SBI_RAP_COURSE_06 = "sbiRap06";

    /** SBIラップコース07 */
    private static final String SBI_RAP_COURSE_07 = "sbiRap07";

    /** SBIラップコース08 */
    private static final String SBI_RAP_COURSE_08 = "sbiRap08";

    /** SBIラップコース09 */
    private static final String SBI_RAP_COURSE_09 = "sbiRap09";

    /** SBIラップコース10 */
    private static final String SBI_RAP_COURSE_10 = "sbiRap10";

    /** SBIラップコース11 */
    private static final String SBI_RAP_COURSE_11 = "sbiRap11";

    /** SBIラップコース12 */
    private static final String SBI_RAP_COURSE_12 = "sbiRap12";

    /** SBIラップコース13 */
    private static final String SBI_RAP_COURSE_13 = "sbiRap13";

    /** SBIラップコース14 */
    private static final String SBI_RAP_COURSE_14 = "sbiRap14";

    /** SBIラップコース15 */
    private static final String SBI_RAP_COURSE_15 = "sbiRap15";

    /** SBIラップコース16 */
    private static final String SBI_RAP_COURSE_16 = "sbiRap16";

    /** SBIラップコース17 */
    private static final String SBI_RAP_COURSE_17 = "sbiRap17";

    /** 表示制御フラグ.表示 */
    private static final String DISPLAY_CONTROL_FLAG_DISPLAY = "0";

    /** TRUE */
    private static final String TRUE = "true";

    /** CSVセパレータ */
    private static final String CSV_SEPARATER = ",";

    /** パターン */
    private String patternNo = "0";

    /** 手数料/報酬 */
    private String displayContent = "";

    /** 現株ポイント 表示制御 */
    private boolean showSpotStockPoint = false;

    /** SBIラップ管理報酬(ネット) 表示制御 */
    private boolean showSbiRapCourse01 = false;

    /** SBIラップ管理報酬(店頭) 表示制御 */
    private boolean showSbiRapCourse02 = false;

    /** SBIラップ管理報酬(匠) 表示制御 */
    private boolean showSbiRapCourse03 = false;

    /** SBIラップ管理報酬(x投資) 表示制御 */
    private boolean showSbiRapCourse04 = false;

    /** SBIラップコース05 表示制御 */
    private boolean showSbiRapCourse05 = false;

    /** SBIラップコース06 表示制御 */
    private boolean showSbiRapCourse06 = false;

    /** SBIラップコース07 表示制御 */
    private boolean showSbiRapCourse07 = false;

    /** SBIラップコース08 表示制御 */
    private boolean showSbiRapCourse08 = false;

    /** SBIラップコース09 表示制御 */
    private boolean showSbiRapCourse09 = false;

    /** SBIラップコース10 表示制御 */
    private boolean showSbiRapCourse10 = false;

    /** SBIラップコース11 表示制御 */
    private boolean showSbiRapCourse11 = false;

    /** SBIラップコース12 表示制御 */
    private boolean showSbiRapCourse12 = false;

    /** SBIラップコース13 表示制御 */
    private boolean showSbiRapCourse13 = false;

    /** SBIラップコース14 表示制御 */
    private boolean showSbiRapCourse14 = false;

    /** SBIラップコース15 表示制御 */
    private boolean showSbiRapCourse15 = false;

    /** SBIラップコース16 表示制御 */
    private boolean showSbiRapCourse16 = false;

    /** SBIラップコース17 表示制御 */
    private boolean showSbiRapCourse17 = false;

    /** SBIラップ管理報酬(ネット) サービス名 */
    private String sbiRapCourse01ServiceName = "";

    /** SBIラップ管理報酬(店頭) サービス名 */
    private String sbiRapCourse02ServiceName = "";

    /** SBIラップ管理報酬(匠) サービス名 */
    private String sbiRapCourse03ServiceName = "";

    /** SBIラップ管理報酬(x投資) サービス名 */
    private String sbiRapCourse04ServiceName = "";

    /** SBIラップコース05 サービス名 */
    private String sbiRapCourse05ServiceName = "";

    /** SBIラップコース06 サービス名 */
    private String sbiRapCourse06ServiceName = "";

    /** SBIラップコース07 サービス名 */
    private String sbiRapCourse07ServiceName = "";

    /** SBIラップコース08 サービス名 */
    private String sbiRapCourse08ServiceName = "";

    /** SBIラップコース09 サービス名 */
    private String sbiRapCourse09ServiceName = "";

    /** SBIラップコース10 サービス名 */
    private String sbiRapCourse10ServiceName = "";

    /** SBIラップコース11 サービス名 */
    private String sbiRapCourse11ServiceName = "";

    /** SBIラップコース12 サービス名 */
    private String sbiRapCourse12ServiceName = "";

    /** SBIラップコース13 サービス名 */
    private String sbiRapCourse13ServiceName = "";

    /** SBIラップコース14 サービス名 */
    private String sbiRapCourse14ServiceName = "";

    /** SBIラップコース15 サービス名 */
    private String sbiRapCourse15ServiceName = "";

    /** SBIラップコース16 サービス名 */
    private String sbiRapCourse16ServiceName = "";

    /** SBIラップコース17 サービス名 */
    private String sbiRapCourse17ServiceName = "";

    private static final Map<String, String> CONDITION_MAP = new HashMap<>();

    static {
        // 日次 手数料
        CONDITION_MAP.put(FEE_COMM_DISPLAY_CONTENT_FEE + DAILY_MONTHLY_COUNTING_UNIT_DAILY + BROKER_CHARGE_BRANCH_COUNTING_UNIT_BROKER + AGGREGATOR_HANDLER_COUNTING_UNIT_AGGREGATOR, "1");
        CONDITION_MAP.put(FEE_COMM_DISPLAY_CONTENT_FEE + DAILY_MONTHLY_COUNTING_UNIT_DAILY + BROKER_CHARGE_BRANCH_COUNTING_UNIT_BROKER + AGGREGATOR_HANDLER_COUNTING_UNIT_HANDLER, "2");
        CONDITION_MAP.put(FEE_COMM_DISPLAY_CONTENT_FEE + DAILY_MONTHLY_COUNTING_UNIT_DAILY + BROKER_CHARGE_BRANCH_COUNTING_UNIT_CHARGE + AGGREGATOR_HANDLER_COUNTING_UNIT_AGGREGATOR, "3");
        CONDITION_MAP.put(FEE_COMM_DISPLAY_CONTENT_FEE + DAILY_MONTHLY_COUNTING_UNIT_DAILY + BROKER_CHARGE_BRANCH_COUNTING_UNIT_BRANCH + AGGREGATOR_HANDLER_COUNTING_UNIT_AGGREGATOR, "10");

        // 月次 手数料
        CONDITION_MAP.put(FEE_COMM_DISPLAY_CONTENT_FEE + DAILY_MONTHLY_COUNTING_UNIT_MONTHLY + BROKER_CHARGE_BRANCH_COUNTING_UNIT_BROKER + AGGREGATOR_HANDLER_COUNTING_UNIT_AGGREGATOR, "4");
        CONDITION_MAP.put(FEE_COMM_DISPLAY_CONTENT_FEE + DAILY_MONTHLY_COUNTING_UNIT_MONTHLY + BROKER_CHARGE_BRANCH_COUNTING_UNIT_BROKER + AGGREGATOR_HANDLER_COUNTING_UNIT_HANDLER, "5");
        CONDITION_MAP.put(FEE_COMM_DISPLAY_CONTENT_FEE + DAILY_MONTHLY_COUNTING_UNIT_MONTHLY + BROKER_CHARGE_BRANCH_COUNTING_UNIT_CHARGE + AGGREGATOR_HANDLER_COUNTING_UNIT_AGGREGATOR, "6");
        CONDITION_MAP.put(FEE_COMM_DISPLAY_CONTENT_FEE + DAILY_MONTHLY_COUNTING_UNIT_MONTHLY + BROKER_CHARGE_BRANCH_COUNTING_UNIT_BRANCH + AGGREGATOR_HANDLER_COUNTING_UNIT_AGGREGATOR, "11");

        // 月次 報酬
        CONDITION_MAP.put(FEE_COMM_DISPLAY_CONTENT_COMM + DAILY_MONTHLY_COUNTING_UNIT_MONTHLY + BROKER_CHARGE_BRANCH_COUNTING_UNIT_BROKER + AGGREGATOR_HANDLER_COUNTING_UNIT_AGGREGATOR, "7");
        CONDITION_MAP.put(FEE_COMM_DISPLAY_CONTENT_COMM + DAILY_MONTHLY_COUNTING_UNIT_MONTHLY + BROKER_CHARGE_BRANCH_COUNTING_UNIT_BROKER + AGGREGATOR_HANDLER_COUNTING_UNIT_HANDLER, "8");
        CONDITION_MAP.put(FEE_COMM_DISPLAY_CONTENT_COMM + DAILY_MONTHLY_COUNTING_UNIT_MONTHLY + BROKER_CHARGE_BRANCH_COUNTING_UNIT_CHARGE + AGGREGATOR_HANDLER_COUNTING_UNIT_AGGREGATOR, "9");
        CONDITION_MAP.put(FEE_COMM_DISPLAY_CONTENT_COMM + DAILY_MONTHLY_COUNTING_UNIT_MONTHLY + BROKER_CHARGE_BRANCH_COUNTING_UNIT_BRANCH + AGGREGATOR_HANDLER_COUNTING_UNIT_AGGREGATOR, "12");
    }

    /**
     * serializeメソッドでStringに変換したデータから
     * このクラスのインスタンスを生成する。
     *
     * @param serializedData シリアライズされたデータ
     * @return このクラスのインスタンス
     */
    public static IfaCommFeePattern deserialize(String serializedData) {
        String[] datas = StringUtils.splitPreserveAllTokens(serializedData, CSV_SEPARATER);

        IfaCommFeePattern instance = new IfaCommFeePattern();
        instance.setPatternNo(datas[0]);                                                // パターン
        instance.setDisplayContent(datas[1]);                                           // 表示内容(手数料 / 報酬)
        instance.setShowSpotStockPoint(StringUtils.equals(datas[2], TRUE));             // 現株ポイント 表示制御
        instance.setShowSbiRapCourse01(StringUtils.equals(datas[3], TRUE));         // SBIラップ管理報酬(ネット) 表示制御
        instance.setShowSbiRapCourse02(StringUtils.equals(datas[4], TRUE));       // SBIラップ管理報酬(店頭) 表示制御
        instance.setShowSbiRapCourse03(StringUtils.equals(datas[5], TRUE));      // SBIラップ管理報酬(匠) 表示制御
        instance.setShowSbiRapCourse04(StringUtils.equals(datas[6], TRUE));  // SBIラップ管理報酬(X投資) 表示制御
        instance.setShowSbiRapCourse05(StringUtils.equals(datas[7], TRUE));             // SBIラップコース05 表示制御
        instance.setShowSbiRapCourse06(StringUtils.equals(datas[8], TRUE));             // SBIラップコース06 表示制御
        instance.setSbiRapCourse01ServiceName(datas[9]);                            // SBIラップ管理報酬(ネット) サービス名
        instance.setSbiRapCourse02ServiceName(datas[10]);                         // SBIラップ管理報酬(店頭) サービス名
        instance.setSbiRapCourse03ServiceName(datas[11]);                        // SBIラップ管理報酬(匠) サービス名
        instance.setSbiRapCourse04ServiceName(datas[12]);                    // SBIラップ管理報酬(X投資) サービス名
        instance.setSbiRapCourse05ServiceName(datas[13]);                               // SBIラップコース05 サービス名
        instance.setSbiRapCourse06ServiceName(datas[14]);                               // SBIラップコース06 サービス名
        instance.setShowSbiRapCourse07(StringUtils.equals(datas[15], TRUE));             // SBIラップコース07 表示制御
        instance.setShowSbiRapCourse08(StringUtils.equals(datas[16], TRUE));             // SBIラップコース08 表示制御
        instance.setShowSbiRapCourse09(StringUtils.equals(datas[17], TRUE));             // SBIラップコース09 表示制御
        instance.setShowSbiRapCourse10(StringUtils.equals(datas[18], TRUE));             // SBIラップコース10 表示制御
        instance.setShowSbiRapCourse11(StringUtils.equals(datas[19], TRUE));             // SBIラップコース11 表示制御
        instance.setShowSbiRapCourse12(StringUtils.equals(datas[20], TRUE));             // SBIラップコース12 表示制御
        instance.setShowSbiRapCourse13(StringUtils.equals(datas[21], TRUE));             // SBIラップコース13 表示制御
        instance.setShowSbiRapCourse14(StringUtils.equals(datas[22], TRUE));             // SBIラップコース14 表示制御
        instance.setShowSbiRapCourse15(StringUtils.equals(datas[23], TRUE));             // SBIラップコース15 表示制御
        instance.setShowSbiRapCourse16(StringUtils.equals(datas[24], TRUE));             // SBIラップコース16 表示制御
        instance.setShowSbiRapCourse17(StringUtils.equals(datas[25], TRUE));             // SBIラップコース17 表示制御
        instance.setSbiRapCourse07ServiceName(datas[26]);                               // SBIラップコース07 サービス名
        instance.setSbiRapCourse08ServiceName(datas[27]);                               // SBIラップコース08 サービス名
        instance.setSbiRapCourse09ServiceName(datas[28]);                               // SBIラップコース09 サービス名
        instance.setSbiRapCourse10ServiceName(datas[29]);                               // SBIラップコース10 サービス名
        instance.setSbiRapCourse11ServiceName(datas[30]);                               // SBIラップコース11 サービス名
        instance.setSbiRapCourse12ServiceName(datas[31]);                               // SBIラップコース12 サービス名
        instance.setSbiRapCourse13ServiceName(datas[32]);                               // SBIラップコース13 サービス名
        instance.setSbiRapCourse14ServiceName(datas[33]);                               // SBIラップコース14 サービス名
        instance.setSbiRapCourse15ServiceName(datas[34]);                               // SBIラップコース15 サービス名
        instance.setSbiRapCourse16ServiceName(datas[35]);                               // SBIラップコース16 サービス名
        instance.setSbiRapCourse17ServiceName(datas[36]);                               // SBIラップコース17 サービス名

        return instance;
    }

    /**
     * このクラスのデータをString型にシリアライズする。
     *
     * @return シリアライズされたこのクラスのデータ
     */
    public String serialize() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.patternNo);                              // パターン
        sb.append(CSV_SEPARATER);
        sb.append(this.displayContent);                         // 表示内容(手数料 / 報酬)
        sb.append(CSV_SEPARATER);
        sb.append(this.showSpotStockPoint);                     // 現株ポイント 表示制御
        sb.append(CSV_SEPARATER);
        sb.append(this.showSbiRapCourse01);                 // SBIラップ管理報酬(ネット) 表示制御
        sb.append(CSV_SEPARATER);
        sb.append(this.showSbiRapCourse02);               // SBIラップ管理報酬(店頭) 表示制御
        sb.append(CSV_SEPARATER);
        sb.append(this.showSbiRapCourse03);              // SBIラップ管理報酬(匠) 表示制御
        sb.append(CSV_SEPARATER);
        sb.append(this.showSbiRapCourse04);          // SBIラップ管理報酬(X投資) 表示制御
        sb.append(CSV_SEPARATER);
        sb.append(this.showSbiRapCourse05);                     // SBIラップコース05 表示制御
        sb.append(CSV_SEPARATER);
        sb.append(this.showSbiRapCourse06);                     // SBIラップコース06 表示制御
        sb.append(CSV_SEPARATER);
        sb.append(this.sbiRapCourse01ServiceName);          // SBIラップ管理報酬(ネット) サービス名
        sb.append(CSV_SEPARATER);
        sb.append(this.sbiRapCourse02ServiceName);        // SBIラップ管理報酬(店頭) サービス名
        sb.append(CSV_SEPARATER);
        sb.append(this.sbiRapCourse03ServiceName);       // SBIラップ管理報酬(匠) サービス名
        sb.append(CSV_SEPARATER);
        sb.append(this.sbiRapCourse04ServiceName);   // SBIラップ管理報酬(X投資) サービス名
        sb.append(CSV_SEPARATER);
        sb.append(this.sbiRapCourse05ServiceName);              // SBIラップコース05 サービス名
        sb.append(CSV_SEPARATER);
        sb.append(this.sbiRapCourse06ServiceName);              // SBIラップコース06 サービス名
        sb.append(CSV_SEPARATER);
        sb.append(this.showSbiRapCourse07);                     // SBIラップコース07 表示制御
        sb.append(CSV_SEPARATER);
        sb.append(this.showSbiRapCourse08);                     // SBIラップコース08 表示制御
        sb.append(CSV_SEPARATER);
        sb.append(this.showSbiRapCourse09);                     // SBIラップコース09 表示制御
        sb.append(CSV_SEPARATER);
        sb.append(this.showSbiRapCourse10);                     // SBIラップコース10 表示制御
        sb.append(CSV_SEPARATER);
        sb.append(this.showSbiRapCourse11);                     // SBIラップコース11 表示制御
        sb.append(CSV_SEPARATER);
        sb.append(this.showSbiRapCourse12);                     // SBIラップコース12 表示制御
        sb.append(CSV_SEPARATER);
        sb.append(this.showSbiRapCourse13);                     // SBIラップコース13 表示制御
        sb.append(CSV_SEPARATER);
        sb.append(this.showSbiRapCourse14);                     // SBIラップコース14 表示制御
        sb.append(CSV_SEPARATER);
        sb.append(this.showSbiRapCourse15);                     // SBIラップコース15 表示制御
        sb.append(CSV_SEPARATER);
        sb.append(this.showSbiRapCourse16);                     // SBIラップコース16 表示制御
        sb.append(CSV_SEPARATER);
        sb.append(this.showSbiRapCourse17);                     // SBIラップコース17 表示制御
        sb.append(CSV_SEPARATER);
        sb.append(this.sbiRapCourse07ServiceName);              // SBIラップコース07 サービス名
        sb.append(CSV_SEPARATER);
        sb.append(this.sbiRapCourse08ServiceName);              // SBIラップコース08 サービス名
        sb.append(CSV_SEPARATER);
        sb.append(this.sbiRapCourse09ServiceName);              // SBIラップコース09 サービス名
        sb.append(CSV_SEPARATER);
        sb.append(this.sbiRapCourse10ServiceName);              // SBIラップコース10 サービス名
        sb.append(CSV_SEPARATER);
        sb.append(this.sbiRapCourse11ServiceName);              // SBIラップコース11 サービス名
        sb.append(CSV_SEPARATER);
        sb.append(this.sbiRapCourse12ServiceName);              // SBIラップコース12 サービス名
        sb.append(CSV_SEPARATER);
        sb.append(this.sbiRapCourse13ServiceName);              // SBIラップコース13 サービス名
        sb.append(CSV_SEPARATER);
        sb.append(this.sbiRapCourse14ServiceName);              // SBIラップコース14 サービス名
        sb.append(CSV_SEPARATER);
        sb.append(this.sbiRapCourse15ServiceName);              // SBIラップコース15 サービス名
        sb.append(CSV_SEPARATER);
        sb.append(this.sbiRapCourse16ServiceName);              // SBIラップコース16 サービス名
        sb.append(CSV_SEPARATER);
        sb.append(this.sbiRapCourse17ServiceName);              // SBIラップコース17 サービス名

        return sb.toString();
    }


    /**
     * リクエスト内容をもとにパターンをセットする
     *
     * @param displayContent 表示内容
     * @param dailyMonthlyCountingUnit 集計単位(日次/月次)
     * @param brokerChargeBranchCountingUnit 集計単位(仲介業者/営業員/支店)
     * @param aggregatorHandlerCountingUnit 集計単位(集計/扱者)
     */
    public void setPatternWithCondition(
            String displayContent,
            String dailyMonthlyCountingUnit,
            String brokerChargeBranchCountingUnit,
            String aggregatorHandlerCountingUnit
    ) {

        // 値を初期化
        this.patternNo = "0";

        // 判定対象を1つにまとめる
        String condition = displayContent + dailyMonthlyCountingUnit + brokerChargeBranchCountingUnit + aggregatorHandlerCountingUnit;

        // パターンを設定
        String pattern = CONDITION_MAP.get(condition);
        this.patternNo = pattern;

        // 表示内容を保存
        this.displayContent = displayContent;

    }

    /**
     * 現株ポイントとSBIラップ管理報酬各種の表示 / 非表示の設定をする
     *
     * @param privId ユーザ共通情報.権限コード
     * @param brokerCode ユーザ共通情報.仲介業者コード
     * @param sql013Res SQL013 取得結果
     * @param sql015Res SQL015 取得結果
    */
    public void setVisibilityFlags(
            String privId,
            String brokerCode,
            List<IfaCommFeeSql013ResponseModel> sql013Res,
            List<IfaCommFeeSql015ResponseModel> sql015Res
    ) {
        // SBI証券ユーザか判定
        boolean isHeadOfficeUser = privId != null && Arrays.asList(PRIV_ID_SBI_HEAD, PRIV_ID_SBI_BRANCH).contains(privId);

        // 現株ポイント 表示 / 非表示の判定
        if (isHeadOfficeUser) { // SBI証券ユーザの場合
            this.showSpotStockPoint = true;

        } else { // SBI証券
            this.showSpotStockPoint = sql013Res.stream()
                .anyMatch(val -> StringUtils.equals(val.getBrokerCode(), brokerCode));
        }

        // SBIラップ管理報酬(ネット) 表示 / 非表示の判定
        this.showSbiRapCourse01 = sql015Res.stream()
            .anyMatch(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_01)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            );

        // SBIラップ管理報酬(店頭) 表示 / 非表示の判定
        this.showSbiRapCourse02 = sql015Res.stream()
            .anyMatch(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_02)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            );

        // SBIラップ管理報酬(匠) 表示 / 非表示の判定
        this.showSbiRapCourse03 = sql015Res.stream()
            .anyMatch(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_03)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            );

        // SBIラップ管理報酬(x投資) 表示 / 非表示の判定
        this.showSbiRapCourse04 = sql015Res.stream()
            .anyMatch(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_04)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            );

        // SBIラップコース05表示 / 非表示の判定
        this.showSbiRapCourse05 = sql015Res.stream()
            .anyMatch(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_05)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            );

        // SBIラップコース06表示 / 非表示の判定
        this.showSbiRapCourse06 = sql015Res.stream()
            .anyMatch(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_06)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            );

        // SBIラップコース07表示 / 非表示の判定
        this.showSbiRapCourse07 = sql015Res.stream()
            .anyMatch(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_07)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            );

        // SBIラップコース08表示 / 非表示の判定
        this.showSbiRapCourse08 = sql015Res.stream()
            .anyMatch(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_08)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            );

        // SBIラップコース09表示 / 非表示の判定
        this.showSbiRapCourse09 = sql015Res.stream()
            .anyMatch(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_09)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            );

        // SBIラップコース10表示 / 非表示の判定
        this.showSbiRapCourse10 = sql015Res.stream()
            .anyMatch(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_10)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            );

        // SBIラップコース11表示 / 非表示の判定
        this.showSbiRapCourse11 = sql015Res.stream()
            .anyMatch(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_11)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            );

        // SBIラップコース12表示 / 非表示の判定
        this.showSbiRapCourse12 = sql015Res.stream()
            .anyMatch(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_12)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            );

        // SBIラップコース13表示 / 非表示の判定
        this.showSbiRapCourse13 = sql015Res.stream()
            .anyMatch(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_13)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            );

        // SBIラップコース14表示 / 非表示の判定
        this.showSbiRapCourse14 = sql015Res.stream()
            .anyMatch(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_14)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            );

        // SBIラップコース15表示 / 非表示の判定
        this.showSbiRapCourse15 = sql015Res.stream()
            .anyMatch(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_15)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            );

        // SBIラップコース16表示 / 非表示の判定
        this.showSbiRapCourse16 = sql015Res.stream()
            .anyMatch(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_16)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            );

        // SBIラップコース17表示 / 非表示の判定
        this.showSbiRapCourse17 = sql015Res.stream()
            .anyMatch(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_17)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            );
    }

    /**
     * SBIラップのサービス名をセット
     *
     * @param sql015Res SQL015レスポンス
     */
    public void setServiceNames(List<IfaCommFeeSql015ResponseModel> sql015Res) {
        // SBIラップ管理報酬(ネット) のサービス名を取得
        this.sbiRapCourse01ServiceName = sql015Res.stream()
            .filter(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_01)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            )
            .findFirst()
            .map(val -> val.getServiceName())
            .orElse("");

        // SBIラップ管理報酬(店頭) のサービス名を取得
        this.sbiRapCourse02ServiceName = sql015Res.stream()
            .filter(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_02)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            )
            .findFirst()
            .map(val -> val.getServiceName())
            .orElse("");

        // SBIラップ管理報酬(匠) のサービス名を取得
        this.sbiRapCourse03ServiceName = sql015Res.stream()
            .filter(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_03)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            )
            .findFirst()
            .map(val -> val.getServiceName())
            .orElse("");

        // SBIラップ管理報酬(x投資) のサービス名を取得
        this.sbiRapCourse04ServiceName = sql015Res.stream()
            .filter(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_04)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            )
            .findFirst()
            .map(val -> val.getServiceName())
            .orElse("");

        // SBIラップコース05のサービス名を取得
        this.sbiRapCourse05ServiceName = sql015Res.stream()
            .filter(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_05)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            )
            .findFirst()
            .map(val -> val.getServiceName())
            .orElse("");

        // SBIラップコース06のサービス名を取得
        this.sbiRapCourse06ServiceName = sql015Res.stream()
            .filter(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_06)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            )
            .findFirst()
            .map(val -> val.getServiceName())
            .orElse("");

        // SBIラップコース07のサービス名を取得
        this.sbiRapCourse07ServiceName = sql015Res.stream()
            .filter(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_07)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            )
            .findFirst()
            .map(val -> val.getServiceName())
            .orElse("");

        // SBIラップコース08のサービス名を取得
        this.sbiRapCourse08ServiceName = sql015Res.stream()
            .filter(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_08)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            )
            .findFirst()
            .map(val -> val.getServiceName())
            .orElse("");

        // SBIラップコース09のサービス名を取得
        this.sbiRapCourse09ServiceName = sql015Res.stream()
            .filter(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_09)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            )
            .findFirst()
            .map(val -> val.getServiceName())
            .orElse("");

        // SBIラップコース10のサービス名を取得
        this.sbiRapCourse10ServiceName = sql015Res.stream()
            .filter(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_10)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            )
            .findFirst()
            .map(val -> val.getServiceName())
            .orElse("");

        // SBIラップコース11のサービス名を取得
        this.sbiRapCourse11ServiceName = sql015Res.stream()
            .filter(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_11)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            )
            .findFirst()
            .map(val -> val.getServiceName())
            .orElse("");

        // SBIラップコース12のサービス名を取得
        this.sbiRapCourse12ServiceName = sql015Res.stream()
            .filter(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_12)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            )
            .findFirst()
            .map(val -> val.getServiceName())
            .orElse("");

        // SBIラップコース13のサービス名を取得
        this.sbiRapCourse13ServiceName = sql015Res.stream()
            .filter(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_13)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            )
            .findFirst()
            .map(val -> val.getServiceName())
            .orElse("");

        // SBIラップコース14のサービス名を取得
        this.sbiRapCourse14ServiceName = sql015Res.stream()
            .filter(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_14)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            )
            .findFirst()
            .map(val -> val.getServiceName())
            .orElse("");

        // SBIラップコース15のサービス名を取得
        this.sbiRapCourse15ServiceName = sql015Res.stream()
            .filter(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_15)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            )
            .findFirst()
            .map(val -> val.getServiceName())
            .orElse("");

        // SBIラップコース16のサービス名を取得
        this.sbiRapCourse16ServiceName = sql015Res.stream()
            .filter(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_16)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            )
            .findFirst()
            .map(val -> val.getServiceName())
            .orElse("");

        // SBIラップコース17のサービス名を取得
        this.sbiRapCourse17ServiceName = sql015Res.stream()
            .filter(val ->
                    StringUtils.equals(val.getItemName(), SBI_RAP_COURSE_17)
                    && StringUtils.equals(val.getDisplayControlFlag(), DISPLAY_CONTROL_FLAG_DISPLAY)
            )
            .findFirst()
            .map(val -> val.getServiceName())
            .orElse("");
    }

    /**
     * インスタンスに設定されたパターンが、
     * 引数で指定されたパターンのいずれかに一致する場合trueを返す
     *
     * @param patterns 判定対象のパターン
     * @return パターンが引数でしたいされたいずれかに一致するか
     */
    public boolean anyMatch(String... patterns) {
        boolean result = Arrays.stream(patterns)
                .anyMatch(pattern -> StringUtils.equals(pattern, this.patternNo));
        return result;
    }

}
