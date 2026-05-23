package com.sbisec.helios.ap.common.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * BeanUtilに対するテストケースです.
 *
 * @author SCSK 笹倉 秀行
 */
class BeanUtilTest {
    
    /**
     * テスト用のBeanです.
     *
     * @author SCSK 笹倉 秀行
     */
    @Data
    @AllArgsConstructor
    private class BeanUtilTestBean {
        
        private String field1;
        
        private Integer field2;
        
        private String field3;
        
        private int field4;
        
        private String field5;
    }
    
    /**
     * BeanUtil.trimStringFieldsに対するテストケースです.
     *
     * @param bean trimを行うBean
     * @throws IllegalAccessException 不正アクセスエラー
     * @throws IllegalArgumentException 不正な引数に対するエラー
     */
    @Test
    void testTrimStringFields1() throws IllegalArgumentException, IllegalAccessException {
        
        BeanUtilTestBean expected = new BeanUtilTestBean("  111", 2, "3333  ", 4, "   55555     ");
        BeanUtil.trimStringFields(expected);
        BeanUtilTestBean actual = new BeanUtilTestBean("111", 2, "3333", 4, "55555");
        assertEquals(expected, actual);
    }
    
}
