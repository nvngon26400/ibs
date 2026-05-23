package com.sbisec.helios.ap.testtool.interceptor;

import java.lang.reflect.Method;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbisec.helios.ap.athena.utils.AthenaBusinessException;
import com.sbisec.helios.common.testtool.IfaStubUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * APIスタブツール
 *
 * @author 河口
 *
 */
@Component
@Aspect
public class IfaDoApiStubExecuteInterceptor {
    
    /** 共通関数のスタブ化フラグ */
    @Value("#{systemEnvironment['stub_fct'] == 'true'}")
    private boolean isStubFct;
    
    /* ロガー */
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaDoApiStubExecuteInterceptor.class);
    
    /**
     * Springframeworkのコンテナで管理されたクラスをスタブ化する。<br />
     * モック対象を増やす場合は、Aroundアノテーションで対象を増やしてください。<br />
     * モック対象はSpringframeworkのコンテナで管理されている必要があります（Componentアノテーションを付与した上で、ComponentScanの対象になっている必要があります）。
     * 各環境へのリリースは、pom.xmlのprofiles.profile.buildの設定で制御してください。
     * ※共通関数のスタブ化は環境変数で指定できます。
     *
     * @param joinPoint ProceedingJoinPoint
     * @return テストデータを設定したオブジェクト、テストデータが取得できなかった場合は空のオブジェクトを返却する
     * @throws AthenaBusinessException 
     */
    
    //@Around("execution(* com.sbisec.helios.ap.bizcommon.component.*.*(..)) || "
    //        + "execution(* com.sbisec.helios.ap.common.util.ApiWrapper.*(..)) || "
    //        + "execution(* com.sbisec.helios.ap.athena.service.impl.CometForeignStockFrnTradeDateServiceImpl.*(..)) ||"
    //        + "execution(* com.sbisec.helios.ap.athena.service.impl.CometForeignStockOrderServiceImpl.*(..))　||"
    //        + "execution(* com.sbisec.helios.ap.athena.ifa.ExchangeService.*(..))")
    @Around("execution(* com.sbisec.helios.ap.common.util.ApiWrapper.*(..)) || "
            + "execution(* com.sbisec.helios.common.util.NriApiWrapper.*(..)) || "
    		//+ "execution(* com.sbisec.helios.ap.bizcommon.component.*.*(..)) || "
            + "execution(* com.sbisec.helios.ap.athena.service.impl.*.*(..)) || "
            + "execution(* com.sbisec.helios.ap.athena.service.*.*(..)) || "
            + "execution(* com.sbisec.helios.ap.athena.ifa.*.*(..)) || "
            + "execution(* com.sbisec.helios.ap.nri.ifa.impl.*.*(..)) || "
            + "execution(* com.sbisec.helios.ap.common.util.HeracrossApiWrapper.getRealQuoteSnapshot*(..)) || "
            + "execution(* com.sbisec.helios.common.util.NriApiWrapper.*(..)) ||"
            + "execution(* com.sbisec.helios.ap.broker.service.impl.PapyImprintServiceImpl.getImprintImage*(..)) ||"
            + "execution(* com.sbisec.helios.ap.pom.service.impl.*.*(..)) || "
            + "execution(* com.sbisec.helios.ap.pom.service.*.*(..))")
    public Object doMock(ProceedingJoinPoint joinPoint) throws AthenaBusinessException {
        
        LOGGER.debug("【開始】 APIスタブが呼び出されました、次のメソッドは実行されません: {}", joinPoint.toLongString());
        ObjectMapper mapper = new ObjectMapper();
        try {
            String prm = mapper.writeValueAsString(joinPoint.getArgs());
            LOGGER.debug("入力パラメータ： {}", prm);
        } catch (Exception e) {
            LOGGER.debug("入力パラメータ： パラメータの整形に失敗しました。");
        }
        
        // メソッド情報を取得
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        
        // 戻り値の型を取得
        Class<?> resultType = ((MethodSignature) joinPoint.getSignature()).getReturnType();
        
        LOGGER.debug("スタブ対象のデータの型: {}", resultType.getCanonicalName());
        
        // 通常、Interceptorは元の処理を呼び出すが、本処理はモック化を目的とする処理のためインターセプト対象の処理は呼び出さない
        // result = joinPoint.proceed();
        
        try {
            return IfaStubUtil.getResultObject(resultType, method);
            
        } catch (Exception e) {
            
            AthenaBusinessException athenaBusinessException = new AthenaBusinessException(e);
            athenaBusinessException.setErrorCode("テストエラーCode");
            athenaBusinessException.setMessage("エラーメッセージ");
            throw athenaBusinessException;
            
        } finally {
            
            LOGGER.debug("【終了】 APIスタブの処理を終了します: {}", joinPoint.toLongString());
        }
    }
    
    /**
     * 共通関数のスタブ化(UT時に使用を想定)
     * @param joinPoint ジョインポイント
     * @return スタブ化が有効な場合はスタブObject、そうでない場合は実行結果Object
     * @throws Throwable 例外発生時にスローされる例外
     */
    @Around("execution(* com.sbisec.helios.ap.bizcommon.component.*.*(..))")
    public Object doMockFct(ProceedingJoinPoint joinPoint) throws Throwable {
        
        if (isStubFct) {
            return doMock(joinPoint);
        }
        return joinPoint.proceed();
    }
}
