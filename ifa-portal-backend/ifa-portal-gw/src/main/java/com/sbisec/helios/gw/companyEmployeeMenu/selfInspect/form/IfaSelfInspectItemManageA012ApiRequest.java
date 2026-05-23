package com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.form;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * 画面ID：SUB0506_02-01
 * 画面名：自己点検項目管理
 * 2024/06/19 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaSelfInspectItemManageA012ApiRequest {

    /** 自己点検項目リスト */
    @NotEmpty(message = "自己点検項目リスト")
    private List<SelfAssessmentItem> selfAssessmentItemList;


    /**
     * A009APIリクエスト.自己点検項目クラス
     *
     * @author SCSK 江口
     */
    @Data
    public static class SelfAssessmentItem {

        /** 自己点検項目ID（数字） */
        @Digits(integer = 8, fraction = 0, message = "自己点検項目ID")
        @NotEmpty(message = "自己点検項目ID")
        @Pattern(regexp = "0-9", message = "自己点検項目ID")
        @Size(max = 8, message = "自己点検項目ID")
        private String selfCheckItemId;

        /** 表示順（数字） */
        @NotEmpty(message = "表示順")
        @Pattern(regexp = "0-9", message = "表示順")
        @Size(max = 7, message = "表示順")
        private String sortNumber;

        /** チェック項目（全角半角） */
        @NotEmpty(message = "チェック項目")
        @Size(max = 1000, message = "チェック項目")
        private String selfAssessmentItemName;

        /** 回答（半角英数字） */
        @Size(min = 1, max = 1, message = "回答")
        private String answer;

        /** 理由必須（半角英数字） */
        @NotEmpty(message = "理由必須")
        @Size(min = 1, max = 1, message = "理由必須")
        private String reasonRequiredFlag;

        /** 登録年月 */
        @DateTimeFormat(pattern = "yyyyMM")
        @JsonFormat(pattern = "yyyyMM")
        @NotEmpty(message = "登録年月")
        @Size(min = 6, max = 6, message = "登録年月")
        private String registerDate;

        /** 業者区分（全角半角） */
        @NotEmpty(message = "業者区分")
        @Size(max = 20, message = "業者区分")
        private String brokerType;

    }

}
