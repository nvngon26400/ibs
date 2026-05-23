package com.sbisec.helios.gw.common.interceptor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.common.form.DataListWithUserAccount;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * GWのレスポンスを編集するインターセプター
 *
 * @author 河口
 *
 */
@Component
@Aspect
public class IfaGwResponseModificationInterceptor {
    
    /* JSONコンバータ */
    private static JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * GWのレスポンスのDataListにUserAccountを挿入する<br />
     * IfaGwCommonUtil.reloadUserAccount()がリクエスト処理中に呼び出されている場合、UserAccountを追加したデータをリストを返却する
     *
     * @param joinPoint ProceedingJoinPoint
     * @return IfaGwCommonUtil.reloadUserAccount()がリクエスト処理中に呼び出されている場合、UserAccountを追加したデータをリストを返却する
     * @throws Throwable 任意の例外
     * @see com.sbisec.helios.gw.common.util.IfaGwCommonUtil#reloadUserAccount()
     */
    @Around("execution(String com.sbisec.helios.gw..controller.*Controller.*(..))")
    public Object insertUserAccount(ProceedingJoinPoint joinPoint) throws Throwable {
        
        Object result;
        
        try {
            
            result = joinPoint.proceed();
            
            if (result != null) {
                
                if (IfaCommonUtil.getRequestAttribute(IfaGwCommonUtil.ATTR_REQUIRES_USER_ACCOUNT_RETURN,
                        Boolean.class)) {
                    
                    DataListWithUserAccount<?> dataListWithUserAccount = jc.toObject((String) result,
                            new TypeReference<DataListWithUserAccount<?>>() {
                            });
                    
                    // ユーザ共通情報をレスポンスに追加
                    dataListWithUserAccount.setUserAccount(IfaCommonUtil.getUserAccount());
                    
                    return jc.toString(dataListWithUserAccount);
                }
            }
            
        } catch (Throwable t) {
            
            throw t;
        }
        return result;
    }
}
