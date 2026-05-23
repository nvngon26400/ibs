package com.sbisec.helios.ap.brokerageMenu.customerList.dao.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * 顧客一覧_基本 SQL001 リクエストモデル
 *
 * @author SCSK池田
 *
 */
@Data
public class IfaCustomerListSql001RequestModel {

    /** 支店コード（数字） */
    private String branchCode;

    /** 最終約定日FROM */
    private String lastTradeDateFrom;

    /** 最終約定日TO */
    private String lastTradeDateTo;

    /** 最終約定日表示（チェック） */
    private String lastTradeDateDisplay;
    
    /** 評価額FROM */
    private String valuationFrom;

    /** 評価額TO */
    private String valuationTo;

    /** 評価額（条件リスト）（全角半角） */
    private String valuationConditionList;

    /** 手数料累計（全角半角） */
    private String commTotalList;

    /** 手数料累計FROM */
    private String commTotalAmountFrom;

    /** 手数料累計TO */
    private String commTotalAmountTo;

    /** 手数料累計期間FROM.  */
    private String commTotalPeriodFrom;

    /** 手数料累計期間TO */
    private String commTotalPeriodTo;

    /** 手数料累計額（条件リスト）（全角半角） */
    private String commTotalAmountConditionList;

    /** 年齢FROM */
    private String ageFrom;

    /** 年齢TO */
    private String ageTo;

    /** 生年月日FROM */
    private String birthDateFrom;

    /** 生年月日TO */
    private String birthDateTo;

    /** 取引制限ありチェックボックス */
    private String tradeRestrictionCheckbox;

    /** 部店コード（半角英数字） */
    private String butenCode;

    /** 口座番号（数字） */
    private String accountNumber;

    /** 仲介業者除外チェックボックス */
    private String brokerCodeExcludeCheckbox;

    /** リスト.仲介業者コード */
    private List<String> brokerCodeList;

    /** 仲介業者営業員コード（半角英数字） */
    private String brokerChargeCode;

    /** 顧客名(漢字/カナ)（全角半角） */
    private String customerNameKanjiKana;

    /** 顧客名検索方法 */
    private String customerNameSearchMethod;

    /** リスト.取引コース */
    private List<String> tradeCourseList;

    /** コンプラランク（From） */
    private String complianceLankFrom;

    /** コンプラランク（To） */
    private String complianceLankTo;

    /** 住所（全角半角） */
    private String adress;

    /** 住所　（条件リスト）（全角半角） */
    private String adressConditionList;

    /** 口座開設年月日FROM */
    private String openAccountFrom;

    /** 口座開設年月日TO */
    private String openAccountTo;

    /** 仲介業者営業員リスト */
    private List<BrokerCharge> brokerChargeList;

    /** 外国証券取引口座 */
    private String foreignSecurityAccountList;
    
    /** 閲覧可能部店（半角英数字） */
    private List<String> butenCodeArray;

    /** NISA口座 */
    private String nisaContractKbnList;

    /** コース変更完了日FROM */
    private String courseChangeFinishDateFrom;

    /** コース変更完了日TO */
    private String courseChangeFinishDateTo;

    /** 検索上限件数 */
    private int searchLimitRow;

    /** 電話番号 */
    private String corporatePhoneNumber;

    /** 法人区分（全角半角） */
    private String personalCorporation;

    /** 投資方針（全角半角） */
    private List<String> investmentPlanList;

    /** 資産運用期間（全角半角） */
    private List<String> employmentPeriodList;

    /** 年収（全角半角） */
    private List<String> annualIncomeList;

    /** 金融資産（全角半角） */
    private List<String> financialAssetsList;

    /** 職業（全角半角） */
    private List<String> occupationList;

    /** 投資経験（全角半角） */
    private List<String> investmentExpList;

    /** 資金の性格（全角半角） */
    private List<String> fundCharacterList;

    /** 主な収入源（全角半角） */
    private List<String> incomeFormList;

    /** 投資経験パラメータmap */
    Map<String, List<String>> investmentExpKbnMap = new HashMap<>();

    /** 投資経験パラメータkey */
    List<String> investmentExpKeyList = new ArrayList<String>();

    /** ドロップダウンボックスの内容選択の判定 */
    String selectFlg = null;

    /** NISA口座の内容選択の判定 */
    String selectNisaFlg = null;

    /** 「未登録」検索パラメータMap */
    Map<String, List<String>> notLoggedMap = new HashMap<>();

    /** ユーザ共通情報.権限コード */
    private String privId;

    /**
     * 仲介業者営業員
     */
    @Data
    public static class BrokerCharge {

        //仲介業者コード
        private String brokerCode;

        //営業員コード
        private String empCode;
    }
}
