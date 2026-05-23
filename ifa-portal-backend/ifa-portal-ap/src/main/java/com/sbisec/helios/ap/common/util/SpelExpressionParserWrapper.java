package com.sbisec.helios.ap.common.util;

import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.SpelParseException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.web.context.request.RequestAttributes;

import lombok.Data;

/**
 * Spring Expression Language（SpEL式）のパーサークラス<br/>
 * SrringのSpelExpressionParserをラップする
 *
 * @author 河口
 *
 */
public class SpelExpressionParserWrapper {
    
    /* ロガー */
    private static final Logger LOGGER = LoggerFactory.getLogger(SpelExpressionParserWrapper.class);
    
    /* EL式パーサー */
    private ExpressionParser seParser;
    
    /* EL式探査対象のルートオブジェクト */
    private SpelRootObject spelRootObject;
    
    /**　コンストラクタ　*/
    public SpelExpressionParserWrapper() {
        
        // EL式のパーサを初期化
        seParser = new SpelExpressionParser();
        
        spelRootObject = new SpelRootObject();
        spelRootObject.setUserAccount((UserAccount) IfaCommonUtil.getRequestAttributes().getAttribute("userAccount",
                RequestAttributes.SCOPE_REQUEST));
        spelRootObject.setCustomerInfo(IfaCommonUtil.getCustomerCommon());
    }
    
    /**
     * EL式をパースし、パースした値を返却します。 パースに失敗した場合は元の文字列をそのまま返します。
     *
     * @param expressionString EL式
     * @return パース結果
     */
    public String parseExpression(String expressionString) {
        
        String parsedExpression = expressionString;
        
        try {
            parsedExpression = seParser.parseExpression(parsedExpression).getValue(spelRootObject, String.class);
            
        } catch (SpelEvaluationException | SpelParseException ex) {
            
            LOGGER.warn("IFAPリンクURLパラメータテーブルのパラメータ列のEL式のパースに失敗しました。EL式：" + parsedExpression, ex);
        }
        return parsedExpression;
    }
    
    /** EL式のパーサーに渡す置換用の値を保持する */
    @Data
    class SpelRootObject {
        
        /** ユーザ情報 */
        private UserAccount userAccount;
        /** 顧客共通情報 */
        private CustomerCommon customerInfo;
        
        /**
         * 文字列の左側を、指定文字数までゼロ埋めして返却する。
         *
         * @param value 変換対象の文字列
         * @param length 文字数
         * @return 変換後の文字列。変換対象文字が指定した文字数より大きい場合、文字埋めはしない。
         */
        public String zeroPadding(String value, int length) {
            
            return com.sbibits.earth.util.StringUtil.fillLeft(value, '0', length);
        }
    }
}
