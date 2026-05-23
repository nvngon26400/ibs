package com.sbisec.helios.gw.testtool.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.common.service.TokenService;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Regisにテスト用の値を登録するためのコントローラ
 *
 * @author 河口
 *
 */
@ScreenId(groupId = "COMMON", id = "Login", ignoreCheck = true)
@RestController
@RequestMapping(value = "/testTool", method = { RequestMethod.POST })
public class RedisRegistRestController {
    
    /* ロガー */
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisRegistRestController.class);
    
    @Autowired
    private TokenService tokenService;
    
    private static JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * リクエストで送信された内容をRedisに登録します
     *
     * @param regisRegist JSON
     * @return 登録結果
     * @throws Exception 任意の例外
     */
    @PostMapping("/redisRegister")
    public String redisRegis(@RequestBody Map<String, Object> regisRegist) throws Exception {
        
        // FWセッションID
        String frameworkSessionId = (String) regisRegist.get("frameworkSessionId");
        
        ObjectMapper objectMapper = new ObjectMapper();
        
        if (StringUtils.isEmpty(frameworkSessionId)) {
            
            LOGGER.warn("frameworkSessionId が設定されていません。ユーザ共通情報の設定をスキップします。");
            
        } else {
            // フレームワークセッションIDをリクエストスコープにセット
            IfaCommonUtil.setRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, frameworkSessionId);
            
            // UserAccount
            Map<?, ?> userAccountMap = (Map<?, ?>) regisRegist.get("userAccount");
            
            if (userAccountMap == null) {
                
                LOGGER.warn("userAccountMap が設定されていません。ユーザ共通情報の設定をスキップします。");
                
            } else {
                
                UserAccount userAccount = objectMapper.convertValue(userAccountMap, UserAccount.class);
                
                // Redisにユーザ共通情報を登録
                tokenService.createSessionInfo(frameworkSessionId, userAccount);
            }
        }
        
        // 任意のオブジェクトをRedisに登録
        
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> redisRegistList = (List<Map<String, Object>>) regisRegist.get("redisRegistList");
        
        if (CollectionUtils.isNotEmpty(redisRegistList)) {
            
            for (Map<String, Object> redisRegist : redisRegistList) {
                
                String redisGroup = (String) redisRegist.get("redisGroup");
                Boolean addFrameworkSessionIdToRedisGroup = Boolean
                        .valueOf((String) redisRegist.get("addFrameworkSessionIdToRedisGroup"));
                String redisKey = (String) redisRegist.get("redisKey");
                String className = (String) redisRegist.get("class");
                Class<?> clazz = className != null ? Class.forName(className) : null;
                Object data = redisRegist.get("data");
                
                IfaGwCommonUtil.putDataToRedis(redisGroup, addFrameworkSessionIdToRedisGroup, redisKey, data, clazz);
                LOGGER.debug("group={}, key={}, class={} を登録しました。",
                        IfaGwCommonUtil.editRedisGroup(redisGroup, addFrameworkSessionIdToRedisGroup), redisKey,
                        data.getClass().getCanonicalName());
            }
        }
        
        return jc.toString(IfaCommonUtil.createDtaList(null, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString()));
    }
    
    /**
     * リクエストしたキーを一括で削除する<br>
     *
     * @param reqData 削除するキーのリスト（JSON形式文字列）
     * @return 削除したキーリスト
     * @throws Exception 例外
     */
    @PostMapping("/redisEraser")
    public String evictRedis(@RequestBody Map<String, Object> reqData) throws Exception {
        
        // FWセッションID
        String frameworkSessionId = (String) reqData.get("frameworkSessionId");
        
        if (!StringUtils.isEmpty(frameworkSessionId)) {
            IfaCommonUtil.setRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, frameworkSessionId);
        }
        
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> redisDataList = (List<Map<String, Object>>) reqData.get("redisRegistList");
        
        List<String> keyList = new ArrayList<>();
        
        if (CollectionUtils.isNotEmpty(redisDataList)) {
            
            for (Map<String, Object> data : redisDataList) {
                
                String redisGroup = (String) data.get("redisGroup");
                String redisKey = (String) data.get("redisKey");
                Boolean addFrameworkSessionIdToRedisGroup = Boolean
                        .valueOf((String) data.get("addFrameworkSessionIdToRedisGroup"));
                
                IfaGwCommonUtil.evictDataFromRedis(redisGroup, addFrameworkSessionIdToRedisGroup, redisKey);
                LOGGER.debug("group={}, key={} を削除しました。",
                        IfaGwCommonUtil.editRedisGroup(redisGroup, addFrameworkSessionIdToRedisGroup), redisKey);
                keyList.add(redisKey);
            }
        }
        return jc.toString(IfaCommonUtil.createDtaList(keyList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString()));
    }
    
    /**
     * リクエストしたキーの値を一括で取得する
     *
     * @param reqData 取得するキーのリスト（JSON形式文字列）
     * @return 取得データ
     * @throws Exception 例外
     */
    @PostMapping("/redisSelector")
    public String getRedis(@RequestBody Map<String, Object> reqData) throws Exception {
        
        // FWセッションID
        String frameworkSessionId = (String) reqData.get("frameworkSessionId");
        
        if (!StringUtils.isEmpty(frameworkSessionId)) {
            IfaCommonUtil.setRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, frameworkSessionId);
        }
        
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> redisDataList = (List<Map<String, Object>>) reqData.get("redisRegistList");
        
        Map<String, Object> resultDataList = new HashMap<String, Object>();
        
        if (CollectionUtils.isNotEmpty(redisDataList)) {
            
            for (Map<String, Object> data : redisDataList) {
                
                String redisGroup = (String) data.get("redisGroup");
                String redisKey = (String) data.get("redisKey");
                Boolean addFrameworkSessionIdToRedisGroup = Boolean
                        .valueOf((String) data.get("addFrameworkSessionIdToRedisGroup"));
                // クラス名が未設定の場合、String型とみなす
                String className = (String) data.get("class");
                Class<?> clazz = className != null ? Class.forName(className) : String.class;
                
                Object dataFromRedis = IfaGwCommonUtil.getDataFromRedis(redisGroup, addFrameworkSessionIdToRedisGroup,
                        redisKey, clazz);
                LOGGER.debug("group={}, key={} を取得しました。",
                        IfaGwCommonUtil.editRedisGroup(redisGroup, addFrameworkSessionIdToRedisGroup), redisKey);
                resultDataList.put(redisKey, dataFromRedis);
            }
        }
        List<Map<String, Object>> dataList = new ArrayList<>();
        dataList.add(resultDataList);
        return jc.toString(IfaCommonUtil.createDtaList(dataList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString()));
    }
}
