package com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.util;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectBlotterConfirmManagerA004CsvModel;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

import org.apache.commons.codec.binary.StringUtils;

/**
 * 画面ID：SUB0506_01-01 CSVユーティリティ
 * 画面名：自己点検記録簿確認（管理者用）
 *
 * @author SCSK
 2024/06/10 新規作成
 */
public class IfaSelfInspectBlotterConfirmManagerCsvUtil extends CsvOutPutUtil {
    
    /** CSVヘッダ */
    public static final String HEADER = "仲介業者コード,仲介業者名,登録年月,最新更新日,"
            + "項目1_回答内容,項目1_回答理由,項目2_回答内容,項目2_回答理由,項目3_回答内容,項目3_回答理由,"
            + "項目4_回答内容,項目4_回答理由,項目5_回答内容,項目5_回答理由,項目6_回答内容,項目6_回答理由,項目7_回答内容,項目7_回答理由,項目8_回答内容,項目8_回答理由,"
            + "項目9_回答内容,項目9_回答理由,項目10_回答内容,項目10_回答理由,項目11_回答内容,項目11_回答理由,項目12_回答内容,項目12_回答理由,"
            + "項目13_回答内容,項目13_回答理由,項目14_回答内容,項目14_回答理由,項目15_回答内容,項目15_回答理由,項目16_回答内容,項目16_回答理由,"
            + "項目17_回答内容,項目17_回答理由,項目18_回答内容,項目18_回答理由,項目19_回答内容,項目19_回答理由,項目20_回答内容,項目20_回答理由";
    
    @Override
    protected String getCsvHeader() {
        
        return String.join(",", Arrays.stream(HEADER.split(",")).map(str -> DOUBLE_QUOTATION + str + DOUBLE_QUOTATION).collect(Collectors.toList()));
    }
    
    @Override
    protected String getCsvLine(ModelBase model) {
        
        var m = (IfaSelfInspectBlotterConfirmManagerA004CsvModel) model;
        var list = Arrays.asList(m.getBrokerCode(), Cp932.toJIS(m.getBrokerName()), //
                formatRegisterDate(m.getRegisterDate()), m.getConfirmationDate(), //
                formatConf(m.getConfirm1()), Cp932.toJIS(m.getAnswerReason1()), //
                formatConf(m.getConfirm2()), Cp932.toJIS(m.getAnswerReason2()), //
                formatConf(m.getConfirm3()), Cp932.toJIS(m.getAnswerReason3()), //
                formatConf(m.getConfirm4()), Cp932.toJIS(m.getAnswerReason4()), //
                formatConf(m.getConfirm5()), Cp932.toJIS(m.getAnswerReason5()), //
                formatConf(m.getConfirm6()), Cp932.toJIS(m.getAnswerReason6()), //
                formatConf(m.getConfirm7()), Cp932.toJIS(m.getAnswerReason7()), //
                formatConf(m.getConfirm8()), Cp932.toJIS(m.getAnswerReason8()), //
                formatConf(m.getConfirm9()), Cp932.toJIS(m.getAnswerReason9()), //
                formatConf(m.getConfirm10()), Cp932.toJIS(m.getAnswerReason10()), //
                formatConf(m.getConfirm11()), Cp932.toJIS(m.getAnswerReason11()), //
                formatConf(m.getConfirm12()), Cp932.toJIS(m.getAnswerReason12()), //
                formatConf(m.getConfirm13()), Cp932.toJIS(m.getAnswerReason13()), //
                formatConf(m.getConfirm14()), Cp932.toJIS(m.getAnswerReason14()), //
                formatConf(m.getConfirm15()), Cp932.toJIS(m.getAnswerReason15()), //
                formatConf(m.getConfirm16()), Cp932.toJIS(m.getAnswerReason16()), //
                formatConf(m.getConfirm17()), Cp932.toJIS(m.getAnswerReason17()), //
                formatConf(m.getConfirm18()), Cp932.toJIS(m.getAnswerReason18()), //
                formatConf(m.getConfirm19()), Cp932.toJIS(m.getAnswerReason19()), //
                formatConf(m.getConfirm20()), Cp932.toJIS(m.getAnswerReason20())) //
                .stream() //
                .map(str -> DOUBLE_QUOTATION + StringUtil.nullToEmpty(str) + DOUBLE_QUOTATION) //
                .collect(Collectors.toList());
        return String.join(",", list);
    }
    
    private String formatConf(String confirmFlg) {
        
        if (StringUtils.equals(confirmFlg, "0")) {
            return "いいえ";
        } else if (StringUtils.equals(confirmFlg, "1")) {
            return "はい";
        } else {
            return "-";
        }
    }
    
    String formatRegisterDate(String registerDate) {
        
        return Objects.nonNull(registerDate)
                ? String.format("%s年%s月", registerDate.substring(0, 4), registerDate.substring(4, 6))
                : null;
    }
    
    /**
     * コンストラクタ
     *
     * @param comp コンプライアンスサービス
     */
    public IfaSelfInspectBlotterConfirmManagerCsvUtil(ComplianceService comp) {
        
        super(comp);
        
    }
    
    /**
     * CSV出力を行う
     *
     * @param models CSVモデルのリスト
     * @return ファイルパス
     * @throws Exception システム例外
     */
    public String doCreateCsvFile(List<IfaSelfInspectBlotterConfirmManagerA004CsvModel> models) throws Exception {
        
        var dataList = new DataList<IfaSelfInspectBlotterConfirmManagerA004CsvModel>();
        dataList.setDataList(models);
        // CSVの生成
        return doCreateCsvFile(dataList,
                IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class), //
                IfaCommonUtil.getUserAccount().getUserId(), null);
        
    }
    
}
