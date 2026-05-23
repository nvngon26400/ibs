package com.sbisec.helios.ap.athena.protocol.account;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;

/**
 * 外貨金銭残高スケジュール Request
 *
 * @author shuchen.xin
 * @version 1.0
 * @date 5/26/2021
 */
public class ListForeignScheduleCashBalancesReq implements BaseRequest {
    public ListForeignScheduleCashBalancesReq() {
    }

    // headerとparameterインスタンス化
    private Header header = new Header();
    private Parameter parameter = new Parameter();

    @SuppressWarnings("unchecked")
    @Override
    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public class Header {
        public Header() {
        }

        private String token;

        public String getToken() {
            return token;
        }
        
        /**
         * @param token 「必須」 部店コード(3桁) + '-' + 顧客コード(7桁)を設定
         */
        public void setToken(String token) {
            this.token = token;
        }
    }

    public class Parameter {
        public Parameter() {
        }

        private String currencyCode;
        private String accountKind;
        private Integer days;

        public String getCurrencyCode() {
            return currencyCode;
        }

        /**
         * @param currencyCode 「非必須」 通貨コード - enums - Currency
         */
        public void setCurrencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
        }

        public String getAccountKind() {
            return accountKind;
        }
        /**
         * @param accountKind 「非必須」 口座分類 - enums - AccountKind
         */
        public void setAccountKind(String accountKind) {
            this.accountKind = accountKind;
        }

        public Integer getDays() {
            return days;
        }

        /**
         * @param days 「非必須」 取得日数
         */
        public void setDays(Integer days) {
            this.days = days;
        }
    }
}
