package com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.util;


import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dto.IfaPersonalInfoManageLedgerListDtoResponsePersonalInfoManageLedgerListInfo;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

/**
 * 画面ID：SUB0403-01
 * 画面名：個人情報管理台帳一覧

 * @author 大崎 辰弥
    2023/12/27 新規作成
 */

public class IfaPersonalInfoManageLedgerListCsvOut extends CsvOutPutUtil {

    public IfaPersonalInfoManageLedgerListCsvOut(ComplianceService comp) {
        super(comp);
    }

    /**
     * Csvタイトル行
     *
     * @return String タイトル行
     */
    @Override
    protected String getCsvHeader() {
        return getHeaders();
    }

    /**
     * csvファイルのヘッダー
     *
     * @return String csvヘッダー
     */
    public static String getHeaders() {
        StringBuilder strCsvHead = new StringBuilder(512);
        strCsvHead.append(DOUBLE_QUOTATION + "処理日時" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "名前" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "住所" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "ＴＥＬ" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "勤務先" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "生年月日" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "性別" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "資産" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "職業" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "部店口座" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "Eメール" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "出金口座" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "書類名・ファイル名" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "取得データ顧客数" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "処理内容" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "個人情報取得者" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "ログインID" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "営業員名" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "保管/送付媒体" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "預託先" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "提供先" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "保管場所" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "保管期間" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "廃棄方法" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "破棄しない" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "廃棄した年月日" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "摘要（預託先詳細）" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "摘要（提供先詳細）" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "摘要（保管場所詳細）" + DOUBLE_QUOTATION);

        return strCsvHead.toString();
    }

    /**
     * 個人情報管理台帳一覧情報リストをcvs形式に変換

     * @return String cvs形式個人情報管理台帳一覧情報リスト
     */
    @Override
    protected String getCsvLine(ModelBase modelBase) {

        IfaPersonalInfoManageLedgerListDtoResponsePersonalInfoManageLedgerListInfo personalInfoManageLedgerListInfo 
                = (IfaPersonalInfoManageLedgerListDtoResponsePersonalInfoManageLedgerListInfo) modelBase;
        StringBuilder strCsv = new StringBuilder();
        // 処理日時
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(personalInfoManageLedgerListInfo.getProcessDayTime()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 名前（全角のみ）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(personalInfoManageLedgerListInfo.getName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 住所（全角のみ）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(personalInfoManageLedgerListInfo.getAdress())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // TEL（全角のみ）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(personalInfoManageLedgerListInfo.getTel())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 勤務先（全角のみ）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(personalInfoManageLedgerListInfo.getOffice())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 生年月日（全角のみ）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(personalInfoManageLedgerListInfo.getCorBirthFlg())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 性別（全角のみ）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(personalInfoManageLedgerListInfo.getGender())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 資産（全角のみ）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(personalInfoManageLedgerListInfo.getAssets())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 職業（全角のみ）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(personalInfoManageLedgerListInfo.getProfession())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 部店口座（全角のみ）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(personalInfoManageLedgerListInfo.getButenAccount())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // Ｅメール（全角のみ）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(personalInfoManageLedgerListInfo.getEmail())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 出金口座（全角のみ）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(personalInfoManageLedgerListInfo.getWithdrawAccount())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);  
        // 書類名・ファイル名（全角半角）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(personalInfoManageLedgerListInfo.getDocNameFileName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);  
        // 取得データ顧客数
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(personalInfoManageLedgerListInfo.getAcquireDataCustomerCount()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 処理内容（全角半角）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(personalInfoManageLedgerListInfo.getProcessContent())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);  
        // 個人情報取得者（全角半角）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(personalInfoManageLedgerListInfo.getPersonalInfoAcquire())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);  
        // ログインID
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(personalInfoManageLedgerListInfo.getLoginId()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 担当者名（全角半角）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(personalInfoManageLedgerListInfo.getChargeName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);  
        // 保管/送付媒体（全角半角）
        strCsv.append(DOUBLE_QUOTATION + converStorageSendingMedium(StringUtil.nullToEmpty(Cp932.toJIS(personalInfoManageLedgerListInfo.getStorageSendingMedium()))) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);  
        // 預託先（全角半角）
        strCsv.append(DOUBLE_QUOTATION + convertDepositDestinations(StringUtil.nullToEmpty(Cp932.toJIS(personalInfoManageLedgerListInfo.getDepositDestinations()))) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);  
        // 提供先（全角半角）
        strCsv.append(DOUBLE_QUOTATION + convertDestination(StringUtil.nullToEmpty(Cp932.toJIS(personalInfoManageLedgerListInfo.getDestination()))) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);   
        // 保管場所（全角半角）
        strCsv.append(DOUBLE_QUOTATION + convertStorageSpace(StringUtil.nullToEmpty(Cp932.toJIS(personalInfoManageLedgerListInfo.getStorageSpace()))) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);  
        // 保管期間（全角半角）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(personalInfoManageLedgerListInfo.getPreservePeriod())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);  
        // 廃棄方法（全角半角）
        strCsv.append(DOUBLE_QUOTATION + convertDisposeMethod(StringUtil.nullToEmpty(Cp932.toJIS(personalInfoManageLedgerListInfo.getDisposeMethod()))) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);  
        // 破棄しない（全角半角）
        strCsv.append(DOUBLE_QUOTATION + convertnotDisposeg(StringUtil.nullToEmpty(Cp932.toJIS(personalInfoManageLedgerListInfo.getNotDispose()))) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);  
        // 廃棄した年月日
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(personalInfoManageLedgerListInfo.getDisposeDateYmd()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 摘要(預託先詳細)（全角半角）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(personalInfoManageLedgerListInfo.getCorDepositOutline())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);  
        // 摘要(提供先詳細)（全角半角）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(personalInfoManageLedgerListInfo.getCorDonationOutline())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);  
        // 摘要(保管場所詳細)（全角半角）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(personalInfoManageLedgerListInfo.getCorDepositoryOutline())) + DOUBLE_QUOTATION);

        return strCsv.toString();

    }

    /**
     * converStorageSendingMedium
     *
     * @param src 保管/送付媒体
     * @return String
     */
    public static String converStorageSendingMedium(String src) {

        switch (src.trim()) {
            case "1":
                return "紙";
            case "2":
                return "データ";
            case "3":
                return "紙とデータ";
            case "4":
                return "保管なし/送付しない";
            default:
                return "";
        }
    }

    /**
     * convertDepositDestinations
     *
     * @param src 預託先
     * @return String
     */
    public static String convertDepositDestinations(String src) {

        switch (src.trim()) {
            case "1":
                return "-";
            case "2":
                return "なし";
            case "3":
                return "あり";
            default:
                return "";
        }
    }

    /**
     * convertDestination
     *
     * @param src 提供先
     * @return String
     */
    public static String convertDestination(String src) {

        switch (src.trim()) {
            case "1":
                return "-";
            case "2":
                return "顧客";
            case "3":
                return "顧客以外";
            case "4":
                return "提供先なし";
            default:
                return "";
        }
    }

    /**
     * convertStorageSpace
     *
     * @param src 保管場所
     * @return String
     */
    public static String convertStorageSpace(String src) {

        switch (src.trim()) {
            case "1":
                return "-";
            case "2":
                return "施錠キャビネ";
            case "3":
                return "PC共有フォルダ";
            case "4":
                return "施錠キャビネとPC共有フォルダ";
            case "5":
                return "保管なし";
            default:
                return "";
        }
    }

    /**
     * convertDisposeMethod
     *
     * @param src 廃棄方法
     * @return String
     */
    public static String convertDisposeMethod(String src) {

        switch (src.trim()) {
            case "1":
                return "-";
            case "2":
                return "シュレッダー";
            case "3":
                return "データ削除";
            case "4":
                return "シュレッダーとデータ削除";
            default:
                return "";
        }
    }

    /**
     * convertnotDisposeg
     *
     * @param src 破棄しない
     * @return String
     */
    public static String convertnotDisposeg(String src) {

        switch (src.trim()) {
            case "1":
                return "-";
            case "2":
                return "○";
            default:
                return "";
        }
    }
}

