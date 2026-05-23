package com.sbisec.helios.ap.common.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sbibits.earth.util.cache.CachedData;
import com.sbisec.helios.ap.common.dao.MCodeDao;
import com.sbisec.helios.ap.common.model.MCode;
import com.sbisec.helios.ap.common.service.MCodeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * コードマスタサービス
 *
 * @author SCSK
 *
 */
@Component(value = "mCodeService")
public class MCodeServiceImpl implements MCodeService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MCodeServiceImpl.class);
    
    private static final String CACHE_KEY = "ck";
    
    private static final String SEPARATA = "::";
    
    private volatile Map<String, List<MCode>> codeTypeCache = Collections
            .synchronizedMap(new HashMap<String, List<MCode>>());
    
    private volatile Map<String, List<MCode>> codeTypeAndCode1Cache = Collections
            .synchronizedMap(new HashMap<String, List<MCode>>());
    
    private volatile Map<String, List<MCode>> codeTypeAndCode12Cache = Collections
            .synchronizedMap(new HashMap<String, List<MCode>>());
    
    private volatile Map<String, CachedData<List<MCode>>> listCache = Collections
            .synchronizedMap(new HashMap<String, CachedData<List<MCode>>>());
    
    // Expire time of cache. Default : 60 min.
    private long expireTime = 60L * 60L * 1000L;
    
    @Autowired
    protected MCodeDao mCodeDao;
    
    @Override
    public List<MCode> getMCodeList(String codeType) throws Exception {
        
        LOGGER.debug("MCodeServiceImpl.getMCodeList:[{}]", codeType);
        
        // Check and load.
        checkAndLoad();
        
        // Get from cache.
        List<MCode> list = codeTypeCache.get(getCacheKey1(codeType));
        
        return list;
    }
    
    @Override
    public List<MCode> getMCodeList(String codeType, String code1) throws Exception {
        
        LOGGER.debug("MCodeServiceImpl.getMCodeList:[{}, {}]", codeType, code1);
        
        // Check and load.
        checkAndLoad();
        
        // Get from cache.
        List<MCode> list = codeTypeAndCode1Cache.get(getCacheKey2(codeType, code1));
        
        return list;
    }
    
    @Override
    public List<MCode> getMCodeList(String codeType, String code1, String code2) throws Exception {
        
        LOGGER.debug("MCodeServiceImpl.getMCodeList:[{}, {}, {}]", codeType, code1, code2);
        
        // Check and load.
        checkAndLoad();
        
        // Get from cache.
        List<MCode> list = codeTypeAndCode12Cache.get(getCacheKey3(codeType, code1, code2));
        
        return list;
    }
    
    private List<MCode> getMCodeListInternal() throws Exception {
        
        LOGGER.debug("MCodeServiceImpl.getMCodeListInternal:[]");
        
        return mCodeDao.getMCodeList();
    }
    
    private synchronized void checkAndLoad() throws Exception {
        
        // Get from cache.
        CachedData<List<MCode>> cachedData = listCache.get(CACHE_KEY);
        // Current time.
        long currentTime = System.currentTimeMillis();
        
        // Until the effective time, using cached data.
        if (cachedData == null || cachedData.getValiedTime() < currentTime) {
            
            // Get new data from the database, and recache.
            List<MCode> list = getMCodeListInternal();
            cachedData = new CachedData<List<MCode>>(list, System.currentTimeMillis() + expireTime);
            listCache.put(CACHE_KEY, cachedData);
            int size = list.size();
            
            // Put to the map.
            Map<String, List<MCode>> tempMap1 = Collections.synchronizedMap(new HashMap<String, List<MCode>>());
            Map<String, List<MCode>> tempMap2 = Collections.synchronizedMap(new HashMap<String, List<MCode>>());
            Map<String, List<MCode>> tempMap3 = Collections.synchronizedMap(new HashMap<String, List<MCode>>());
            
            for (int i = 0; i < size; i++) {
                
                MCode mCode = list.get(i);
                
                // code type cache
                String cacheKey1 = getCacheKey1(mCode);
                List<MCode> list1 = tempMap1.get(cacheKey1);
                
                if (list1 == null) {
                    list1 = new ArrayList<MCode>();
                    tempMap1.put(cacheKey1, list1);
                }
                
                list1.add(mCode);
                
                // code type and code1 cache
                String cacheKey2 = getCacheKey2(mCode);
                List<MCode> list2 = tempMap2.get(cacheKey2);
                
                if (list2 == null) {
                    list2 = new ArrayList<MCode>();
                    tempMap2.put(cacheKey2, list2);
                }
                
                list2.add(mCode);
                
                // code type and code1 and code2 cache
                String cacheKey3 = getCacheKey3(mCode);
                List<MCode> list3 = tempMap3.get(cacheKey3);
                
                if (list3 == null) {
                    list3 = new ArrayList<MCode>();
                    tempMap3.put(cacheKey3, list3);
                }
                
                list3.add(mCode);
            }
            
            // Replace
            codeTypeCache = tempMap1;
            codeTypeAndCode1Cache = tempMap2;
            codeTypeAndCode12Cache = tempMap3;
        }
    }
    
    private String getCacheKey1(MCode mCode) {
        
        return getCacheKey1(mCode.getCodeType());
    }
    
    private String getCacheKey1(String codeType) {
        
        return codeType;
    }
    
    private String getCacheKey2(MCode mCode) {
        
        return getCacheKey2(mCode.getCodeType(), mCode.getCode1());
    }
    
    private String getCacheKey2(String codeType, String code1) {
        
        return codeType + SEPARATA + code1;
    }
    
    private String getCacheKey3(MCode mCode) {
        
        return getCacheKey3(mCode.getCodeType(), mCode.getCode1(), mCode.getCode2());
    }
    
    private String getCacheKey3(String codeType, String code1, String code2) {
        
        return codeType + SEPARATA + code1 + SEPARATA + code2;
    }
}
