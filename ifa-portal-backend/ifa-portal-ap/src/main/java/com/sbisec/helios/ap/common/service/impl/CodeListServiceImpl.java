package com.sbisec.helios.ap.common.service.impl;

import java.util.List;

import com.sbisec.helios.ap.common.dao.mapper.CodeListMapper;
import com.sbisec.helios.ap.common.exception.IfaRuntimeException;
import com.sbisec.helios.ap.common.model.IfaCodeListDispPatternModel;
import com.sbisec.helios.ap.common.service.CodeListService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * 区分定義公開機能サービス実装クラス
 *
 * @author 河口
 *
 */
@Component(value = "codeListService")
public class CodeListServiceImpl implements CodeListService {
    
    //    /** ロガー */
    //    private static final Logger LOGGER = LoggerFactory.getLogger(CodeListServiceImpl.class);
    
    // NULL値検索用
    private final String nullValue = "$NULL";
    
    // 必須チェックエラー
    private final String paramError = "errors.requiredParam";
    
    // 数値チェックエラー
    private final String numericError = "errors.notNumeric";
    
    // エラーメッセージ用String配列
    private final String[] codeIdStr = { "区分ID" };
    
    private final String[] apiTypeStr = { "APIタイプ" };
    
    private final String[] dispPatternStr = { "表示パターン" };
    
    private final String[] codeValueStr = { "区分値名称" };
    
    @Autowired
    private CodeListMapper codeListMapper;
    
    @Override
    public List<IfaCodeListDispPatternModel> getCodeList(String codeListId) {
        
        return codeListMapper.getCodeList(codeListId, null, null);
    }
    
    @Override
    public List<IfaCodeListDispPatternModel> getCodeList(String codeListId, String dispPattern) {
        
        return codeListMapper.getCodeList(codeListId, dispPattern, null);
    }
    
    @Override
    public List<IfaCodeListDispPatternModel> getCodeList(String codeListId, String dispPattern, String selectPattern) {
        
        return codeListMapper.getCodeList(codeListId, dispPattern, selectPattern);
    }
    
    @Override
    @Cacheable(value = "extCodes", key = "#codeListId + '_' + #apiType + '_' + #codeListKey")
    public String convertKeyToExtKey(String codeListId, String apiType, String codeListKey) {
        
        // 引数チェック
        if (StringUtils.isEmpty(codeListId)) {
            throw new IfaRuntimeException(paramError, codeIdStr);
        }
        
        if (StringUtils.isEmpty(apiType)) {
            throw new IfaRuntimeException(paramError, apiTypeStr);
        }
        
        // 区分値がnullもしくは空文字の場合、$NULLとして検索を行う
        if (StringUtils.isEmpty(codeListKey)) {
            codeListKey = nullValue;
        }
        
        return codeListMapper.convertKeyToExtKey(codeListId, apiType, codeListKey);
    }
    
    @Override
    @Cacheable(value = "intCodes", key = "#codeListId + '_' + #apiType + '_' + #codeListExtKey")
    public String convertExtKeyToKey(String codeListId, String apiType, String codeListExtKey) {
        
        // 引数チェック
        if (StringUtils.isEmpty(codeListId)) {
            throw new IfaRuntimeException(paramError, codeIdStr);
        }
        
        if (StringUtils.isEmpty(apiType)) {
            throw new IfaRuntimeException(paramError, apiTypeStr);
        }
        
        // 外部区分値がnullもしくは空文字の場合、$NULLとして検索を行う
        if (StringUtils.isEmpty(codeListExtKey)) {
            codeListExtKey = nullValue;
        }
        
        return codeListMapper.convertExtKeyToKey(codeListId, apiType, codeListExtKey);
    }
    
    @Override
    @Cacheable(value = "valuesDispPattern1", key = "#codeListId + '_' + #codeListKey")
    public String getValue(String codeListId, String codeListKey) {
        
        // 引数チェック
        if (StringUtils.isEmpty(codeListId)) {
            throw new IfaRuntimeException(paramError, codeIdStr);
        }
        
        // 区分値がnullもしくは空文字の場合、$NULLとして検索を行う
        if (StringUtils.isEmpty(codeListKey)) {
            codeListKey = nullValue;
        }
        
        return codeListMapper.getValue(codeListId, codeListKey, null);
    }
    
    @Override
    @Cacheable(value = "valuesWithDispPattern", key = "#codeListId + '_' + #codeListKey + '_' + #dispPattern")
    public String getValue(String codeListId, String codeListKey, String dispPattern) {
        
        // 引数チェック
        if (StringUtils.isEmpty(codeListId)) {
            throw new IfaRuntimeException(paramError, codeIdStr);
        }
        
        // 表示パターンがnullの場合はSQLで1に読み替えを行うため、nullは許容する
        if (!StringUtils.isNumeric(dispPattern) && dispPattern != null) {
            throw new IfaRuntimeException(numericError, dispPatternStr);
        }
        
        // 区分値がnullもしくは空文字の場合、$NULLとして検索を行う
        if (StringUtils.isEmpty(codeListKey)) {
            codeListKey = nullValue;
        }
        
        return codeListMapper.getValue(codeListId, codeListKey, dispPattern);
    }
    
    @Override
    public String getKey(String codeListId, String codeListValue) {
        
        // 引数チェック
        if (StringUtils.isEmpty(codeListId)) {
            throw new IfaRuntimeException(paramError, codeIdStr);
        }
        
        if (StringUtils.isEmpty(codeListValue)) {
            throw new IfaRuntimeException(paramError, codeValueStr);
        }
        
        return codeListMapper.getKey(codeListId, codeListValue, null);
    }
    
    @Override
    public String getKey(String codeListId, String codeListValue, String dispPattern) {
        
        // 引数チェック
        if (StringUtils.isEmpty(codeListId)) {
            throw new IfaRuntimeException(paramError, codeIdStr);
        }
        
        if (StringUtils.isEmpty(codeListValue)) {
            throw new IfaRuntimeException(paramError, codeValueStr);
        }
        
        // 表示パターンがnullの場合はSQLで1に読み替えを行うため、nullは許容する
        if (!StringUtils.isNumeric(dispPattern) && dispPattern != null) {
            throw new IfaRuntimeException(numericError, dispPatternStr);
        }
        
        return codeListMapper.getKey(codeListId, codeListValue, dispPattern);
    }
}
