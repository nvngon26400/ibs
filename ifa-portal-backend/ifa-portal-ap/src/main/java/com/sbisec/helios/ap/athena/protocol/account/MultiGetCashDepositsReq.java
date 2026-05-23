package com.sbisec.helios.ap.athena.protocol.account;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;
/**
 * 預り金一括取得API
 * @author SCSK
 *
 */
public class MultiGetCashDepositsReq implements BaseRequest {
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
        private String accountKind;
        private List<String> currencyCodes;
        private Integer days;
        public Parameter() {
        }
        public List<String> getCurrencyCodes() {
            return currencyCodes;
        }

        /**
         * @param currencyCode 「必須」 通貨コード - enums - Currency
         */
        public void setCurrencyCodes(List<String> currencyCode) {
            this.currencyCodes = currencyCode;
        }

        public String getAccountKind() {
            return accountKind;
        }
        /**
         * @param accountKind 「必須」 口座分類 - enums - AccountKind
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
