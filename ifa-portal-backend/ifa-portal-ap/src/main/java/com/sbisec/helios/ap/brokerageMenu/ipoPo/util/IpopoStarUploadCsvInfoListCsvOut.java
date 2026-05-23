package com.sbisec.helios.ap.brokerageMenu.ipoPo.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoStarUploadCsvInfoListModel;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

public class IpopoStarUploadCsvInfoListCsvOut extends CsvOutPutUtil {

    public IpopoStarUploadCsvInfoListCsvOut(ComplianceService comp) {    
        super(comp);    
    }

    private static final int KEEP_DAY_CNT = 30;
    private static final String STAR_UPLOAD_CSV_FILE_NAME = "wkdcyup1256Z10";
    public static final String HHMM = "HHmm";

    /**
     * Csvタイトル行
     *
     * @return String タイトル行
     */
    @Override
    protected String getCsvHeader() {
        return getHeader();
    }

    public static String getHeader() {
        return StringUtil.EMPTY_STRING;
    }

    /**
     * csv形式に変換
     *
     * @param データ
     * @return String cvs形式データ
     */
    @Override
    protected String getCsvLine(ModelBase modelBase) {
        IpopoStarUploadCsvInfoListModel ipopoStarUploadCsvInfoListModel = (IpopoStarUploadCsvInfoListModel) modelBase;

        StringBuilder strCsv = new StringBuilder();
        // 部店
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(ipopoStarUploadCsvInfoListModel.getButenCode())));
        strCsv.append(CSV_SEPARATER);
        // 口座番号
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(ipopoStarUploadCsvInfoListModel.getAccountNumber())));
        strCsv.append(CSV_SEPARATER);
        // 銘柄商品区分
        strCsv.append("01 ");
        strCsv.append(CSV_SEPARATER);
        // 銘柄コード
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(ipopoStarUploadCsvInfoListModel.getProductCode())));
        strCsv.append(CSV_SEPARATER);
        // 数量:金額
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(ipopoStarUploadCsvInfoListModel.getOrderCount())));
        strCsv.append(CSV_SEPARATER);
        // 受渡方法
        strCsv.append("1");
        strCsv.append(CSV_SEPARATER);
        // 預り売買区分
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(ipopoStarUploadCsvInfoListModel.getDepositType())));
        strCsv.append(CSV_SEPARATER);
        // 入金予定日
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(ipopoStarUploadCsvInfoListModel.getPaymentDate())));
        strCsv.append(CSV_SEPARATER);
        // 扱者コード
        strCsv.append(" ");
        strCsv.append(CSV_SEPARATER);
        // 申込日
        strCsv.append("    ");
        strCsv.append(CSV_SEPARATER);
        // 摘要
        strCsv.append(" ");
        strCsv.append(CSV_SEPARATER);
        // 勧誘区分
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(ipopoStarUploadCsvInfoListModel.getInvitationType())));
        strCsv.append(CSV_SEPARATER);
        // 受注方法
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(ipopoStarUploadCsvInfoListModel.getOrderMethod())));
        strCsv.append(CSV_SEPARATER);
        // 資金性格
        strCsv.append("1");
        strCsv.append(CSV_SEPARATER);
        // 受注日
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(ipopoStarUploadCsvInfoListModel.getOrderDay())));
        strCsv.append(CSV_SEPARATER);
        // 受注時刻
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(ipopoStarUploadCsvInfoListModel.getOrderHour())));
        strCsv.append(CSV_SEPARATER);
        // 手書区分
        strCsv.append("1");
        strCsv.append(CSV_SEPARATER);
        // 注文チャネル
        strCsv.append("1");
        strCsv.append(CSV_SEPARATER);
        // 受注者
        strCsv.append(" ");
        strCsv.append(CSV_SEPARATER);
        // 外部接続区分
        strCsv.append(" ");
        strCsv.append(CSV_SEPARATER);
        // SSI区分
        strCsv.append(" ");
        strCsv.append(CSV_SEPARATER);
        // リファレンスＮＯ
        strCsv.append("                ");
        strCsv.append(CSV_SEPARATER);
        // 登録請求区分
        strCsv.append(" ");
        strCsv.append(CSV_SEPARATER);
        // DVP区分
        strCsv.append(" ");
        strCsv.append(CSV_SEPARATER);
        // ネット受渡区分
        strCsv.append(" ");
        strCsv.append(CSV_SEPARATER);
        // 強制承認区分
        strCsv.append("1");
        strCsv.append(CSV_SEPARATER);
        // 資金振替チェックアラート(信用口座)
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(ipopoStarUploadCsvInfoListModel.getAlertForMargin())));
        strCsv.append(CSV_SEPARATER);
        // 資金振替チェックアラート(NISA)
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(ipopoStarUploadCsvInfoListModel.getAlertForNisa())));
        strCsv.append(CSV_SEPARATER);
        // 資金振替チェックアラート(未成年口座)
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(ipopoStarUploadCsvInfoListModel.getAlertForMinor())));
        strCsv.append(CSV_SEPARATER);
        // 資金振替チェックアラート(ハイブリッド口座)
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(ipopoStarUploadCsvInfoListModel.getAlertForSsnb())));
        strCsv.append(CSV_SEPARATER);
        // ワーニング申請済フラグ
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(ipopoStarUploadCsvInfoListModel.getWarningApply())));
        strCsv.append(CSV_SEPARATER);
        // セクション名
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(ipopoStarUploadCsvInfoListModel.getBbCreateSectionName())));
        return strCsv.toString();
    }

    /**
     * doCreateCsvFile csvファイルをテンポラリディレクトリーに出力する。
     * 
     * @param DataList dataList
     * @param String   sessionId
     * @param String   userId
     * @param String   pattern
     * @param String   path
     * @return String フルパスファイル名
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public String doCreateCsvFile(DataList dataList, String sessionId, String userId, String pattern, String path)
            throws Exception {
        // Create csv temporary file name
        String fileName = STAR_UPLOAD_CSV_FILE_NAME + DateUtil.format(new Date(), DateUtil.YYYYMMDD) + "-"
                + DateUtil.format(new Date(), HHMM) + ".csv";
        String tmpCsv = StringUtil.EMPTY_STRING;
        try {
            String makeDate = DateUtil.format(new Date(), DateUtil.YYYYMMDD);
            File pathFile = new File(path + File.separator + makeDate);
            // パス存在しない場合
            if (!pathFile.exists()) {
                // パスを作成
                pathFile.mkdirs();
            }
            tmpCsv = path + File.separator + makeDate + File.separator + fileName;
            File file = new File(tmpCsv);
            PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file), Charset.forName("Shift_JIS")));

            List list = dataList.getDataList();
            int listSize = list.size();

            for (int i = 0; i < listSize; i++) {
                if (pattern != null) {
                    out.write(getCsvLine((ModelBase) list.get(i), pattern));
                    out.write(CRLF);
                } else {
                    out.write(getCsvLine((ModelBase) list.get(i)));
                    out.write(CRLF);
                }
            }

            out.flush();
            out.close();

            deleteFolder(path);

            // Catch and ingore.
        } catch (Exception e) {
            throw e;
        }

        return tmpCsv;
    }

    private void deleteFolder(String path) throws Exception {
        LocalDate today = LocalDate.now();
        LocalDate lastModifyDay = null;
        
        File file = new File(path);
        File[] files = file.listFiles();
        List<File> fileList = Arrays.asList(files);

        Collections.sort(fileList, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                if (o1.isDirectory() && o2.isFile()) {
                    return -1;
                }
                if (o1.isFile() && o2.isDirectory()) {
                    return 1;
                }
                return o2.getName().compareTo(o1.getName());
            }
        });
        for (File newfile : files) {
            File delFile = new File(path + File.separator + newfile.getName());
            lastModifyDay = LocalDateTime.ofInstant( Instant.ofEpochMilli(delFile.lastModified()), ZoneId.systemDefault()).toLocalDate();
            if (lastModifyDay.until(today, ChronoUnit.DAYS) > KEEP_DAY_CNT) {
                delFiles(delFile);
            }
        }
    }

    // Recursive delete files
    private boolean delFiles(File file) throws Exception {
        boolean result = false;
        // Directory
        if (file.isDirectory()) {
            File[] childrenFiles = file.listFiles();
            for (File childFile : childrenFiles) {
                result = delFiles(childFile);
                if (!result) {
                    return result;
                }
            }
        }
        // delete file、empty folder
        result = file.delete();
        return result;
    }
}