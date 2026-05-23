package com.sbisec.helios.ap.common.util;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;

/**
 * Beanに対する操作を行う処理を行います.
 *
 * @author SCSK 笹倉 秀行
 */
public class BeanUtil {
    
    /**
     * 引数のBeanのString型の全フィールドに対して、trimを行う処理です.
     *
     * @param bean trimを行うBean
     * @throws IllegalAccessException 不正アクセスエラー
     * @throws IllegalArgumentException 不正な引数に対するエラー
     */
    public static void trimStringFields(Object bean) throws IllegalArgumentException, IllegalAccessException {
        
        if (bean == null) {
            return;
        }
        for (Field field : bean.getClass().getDeclaredFields()) {
            if (field.getType().equals(String.class)) {
                field.setAccessible(true);
                // 現在の値を取得
                String value = (String) field.get(bean);
                if (StringUtils.isNoneEmpty(value)) {
                    // trimを行い、セットする
                    field.set(bean, value.trim());
                }
            }
        }
    }
}
