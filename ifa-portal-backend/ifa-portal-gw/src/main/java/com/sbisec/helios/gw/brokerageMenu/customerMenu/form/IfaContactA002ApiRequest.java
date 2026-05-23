package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * 接触履歴 A002リクエスト

 * @author 趙韫慧
 *
 */
@Data
public class IfaContactA002ApiRequest {

    /** 接触履歴大分類 */
    private List<BigClass> bigClassList;

    /**
     * 接触履歴 大分類
     *
     * @author 趙韫慧
     * 
     */
    @Data
    public static class BigClass {

        /** id */
        @NotEmpty(message = "大分類(id)")
        private String id;

        /** isSelected */
        @NotEmpty(message = "大分類(isSelected)")
        private String isSelected;

    }
}
