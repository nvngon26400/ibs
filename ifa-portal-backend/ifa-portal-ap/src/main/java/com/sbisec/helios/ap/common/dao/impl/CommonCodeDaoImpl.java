package com.sbisec.helios.ap.common.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.dao.CommonCodeDao;
import com.sbisec.helios.ap.common.exception.IfaRuntimeException;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.binding.MapperProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

/**
 * コード値公開用汎用コントローラ（API）DAOの実装クラス
 *
 * @author 河口
 *
 */
@Component
public class CommonCodeDaoImpl implements CommonCodeDao {
    
    /** ロガー */
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonCodeDaoImpl.class);
    
    @Autowired
    private ApplicationContext applicationContext;
    
    @SuppressWarnings({ "unchecked" })
    @Override
    public DataList<Object> getCommonCode(String mapperName, String sqlId, List<Object> params) {
        
        // ApplicationContextからMapper名でBeanを取得、Proxyオブジェクトでラッピングされた状態のBeanが取得される
        Object proxy = applicationContext.getBean(mapperName);
        
        // Proxyから、MapperProxyを取得する
        MapperProxy<?> mapperProxy = (MapperProxy<?>) Proxy.getInvocationHandler(proxy);
        
        // 呼び出し対象のMapperMethodを取得
        Method mapperMethod = findMapperMethod(mapperProxy, mapperName, sqlId, params.size());
        
        // パラメータの個数調整（省略されたパラメータをnullで補完する）
        Object[] mapperParams = new Object[mapperMethod.getParameterCount()];
        
        for (int i = 0; i < params.size(); i++) {
            mapperParams[i] = params.get(i);
        }
        
        // Mapperを実行
        Object result = null;
        try {
            
            result = mapperProxy.invoke(proxy, mapperMethod, mapperParams);
            
        } catch (Throwable ex) {
            throw new IfaRuntimeException("errors.targetCallFailure",
                    new String[] { "Mapper", mapperName + "." + sqlId }, ex);
        }
        
        DataList<Object> dataList = new DataList<>();
        
        if (result instanceof List<?>) {
            
            dataList.setDataList((List<Object>) result);
            
        } else if (result != null) {
            
            dataList.getDataList().add(result);
            
        }
        dataList.setTotalSize(dataList.getDataList().size());
        
        return dataList;
    }
    
    /**
     * 呼び出し対象のMapperのMethod情報を探索する
     *
     * @param mapperProxy 呼び出し対象のMapperのMapperProxyオブジェクト
     * @param mapperName  呼び出し対象のMapper名
     * @param sqlId       呼び出し対象のSQLID
     * @param paramCount      呼び出し対象のSQLのパラメータ数
     * @return メソッド情報
     */
    private Method findMapperMethod(MapperProxy<?> mapperProxy, String mapperName, String sqlId, int paramCount) {
        
        // Mapperのインターフェイスの情報を取得
        Field field = ReflectionUtils.findField(mapperProxy.getClass(), "mapperInterface");
        
        if (field == null) {
            throw new IfaRuntimeException("errors.targetNotFound",
                    new String[] { "mapperInterface", mapperProxy.getClass().getName() });
        }
        
        ReflectionUtils.makeAccessible(field);
        Object mapperInterface = ReflectionUtils.getField(field, mapperProxy);
        
        if (mapperInterface == null) {
            throw new IfaRuntimeException("errors.targetNotFound",
                    new String[] { field.getName(), mapperProxy.getClass().getName() });
        }
        
        // Mapperインターフェイスからメソッドの情報を取得する
        Method[] methods = ReflectionUtils.getDeclaredMethods((Class<?>) mapperInterface);
        
        Method mapperMethod = null;
        
        // Mapperインターフェイスのメソッドから、パラメータとして指定されたメソッド名、メソッドの個数に一致するメソッドを特定する
        for (Method method : methods) {
            
            if (StringUtils.equals(method.getName(), sqlId)) {
                
                if (method.getParameterCount() == paramCount) {
                    
                    mapperMethod = method;
                    break;
                    
                } else if (method.getParameterCount() > paramCount) {
                    // パラメータ省略のケースを考慮し、「入力されたパラメータ＜Mapperメソッドのパラメータ」のケースも検出の対象とする
                    
                    // mapperMethodが未設定、又は検出済のメソッドのパラメータ数が検査中のメソッドのパラメータより少ない場合
                    if (mapperMethod == null || mapperMethod.getParameterCount() < method.getParameterCount()) {
                        mapperMethod = method;
                    }
                    
                } else {
                    LOGGER.debug("指定されたMapperと同名のMapperを検出しましたが、入力パラメータが過多のため確認を継続します。パラメータ数：{}", paramCount);
                }
            }
        }
        
        if (mapperMethod == null) {
            
            throw new IfaRuntimeException("errors.targetNotFound", new String[] { "Mapper", mapperName + "." + sqlId });
        }
        
        return mapperMethod;
    }
}
