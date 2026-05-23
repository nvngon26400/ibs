package com.sbisec.helios.ap.brokerageMenu.customerMenu.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupCsvItems;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

public class IfaSendReceiveStatusLookupCsvOut extends CsvOutPutUtil{



    public IfaSendReceiveStatusLookupCsvOut(ComplianceService comp) {
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
        strCsvHead.append(DOUBLE_QUOTATION + "資料No." + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "受発送日" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "受発信状況" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "資料名" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "内容･備考" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "通数" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "請求日時" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "取扱者" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "郵便記録" + DOUBLE_QUOTATION);

        return strCsvHead.toString();
    }

    /**
     * 受発信状況データをcsv形式に変換
     *
     * @param modelBase 受発信状況データ
     * @return String csv形式受発信状況データ
     */
    @Override
    protected String getCsvLine(ModelBase modelBase) {
        IfaSendReceiveStatusLookupCsvItems csvItems = (IfaSendReceiveStatusLookupCsvItems) modelBase;

        StringBuilder strCsv = new StringBuilder();
        // 資料No.
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getRequestNo()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 受発送日
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getChangeStatusDate())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 受発信状況
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getSt()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 資料名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getPaperName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 内容･備考
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(getComment(csvItems)) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 通数
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getCopies()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 請求日時
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getRequestDate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 取扱者
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getAgentName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 郵便記録
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getSendMail()) + DOUBLE_QUOTATION);
       
        return strCsv.toString();
    }

    /**
     * 内容備考の処理
     *
     * @param modelBase 受発信状況データ
     * @return String 内容備考
     */
    
    private String getComment(IfaSendReceiveStatusLookupCsvItems csvItems) {
        StringBuilder strComment = new StringBuilder();
        // ①'目論見書番号≠null、かつ"T "の場合、"[かんたん口座開設経由]"を出力
        if (!StringUtil.isNullOrEmpty(csvItems.getProspectusNo()) && csvItems.getProspectusNo().equals("T       ")) {
            strComment.append("[かんたん口座開設経由] ");
        }
        // ②内容≠nullなら、内容 + ' ' を出力
        if (!StringUtil.isNullOrEmpty(csvItems.getContents())) {
            strComment.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getContents())) + ' ');
        }
        // ③備考≠nullなら、備考+ ' ' を出力
        if (!StringUtil.isNullOrEmpty(csvItems.getRemarks())) {
            strComment.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getRemarks())) + ' ');
        }
        // ④理由名称≠nullなら、理由名称+ ' ' を出力
        if (!StringUtil.isNullOrEmpty(csvItems.getPurposeName())) {
            strComment.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getPurposeName())) + ' ');
        }
        // ⑤目論見書番号≠null、かつ目論見書番号が"T "または""でない場合、" 目論見書No." + 目論見書番号を出力
        if (!StringUtil.isNullOrEmpty(csvItems.getProspectusNo())
                && !(csvItems.getProspectusNo().equals("T       ") || csvItems.getProspectusNo().equals(""))) {
            strComment.append("目論見書No." + csvItems.getProspectusNo());
        }
        // タグを含むことがあるので、正規表現(<("[^"]*"|'[^']*'|[^'">])*>)にマッチするものは削除してから結合
        String strCommentTmp = strComment.toString();
        String strCommentFinal = "";
        Pattern pattern = Pattern.compile("<(\"[^\"]*\"|'[^']*'|[^'\">])*>");
        Matcher matcher = pattern.matcher(strCommentTmp);

        if (matcher.find()) {
            strCommentFinal = strCommentTmp.replaceAll(pattern.toString(), "");
        } else {
            strCommentFinal = strCommentTmp;
        }

        return strCommentFinal;
    }
}
