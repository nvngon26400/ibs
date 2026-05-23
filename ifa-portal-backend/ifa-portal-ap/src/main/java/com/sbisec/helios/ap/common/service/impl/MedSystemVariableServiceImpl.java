package com.sbisec.helios.ap.common.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sbibits.earth.util.cache.CachedData;
import com.sbisec.helios.ap.common.dao.MedSystemVariableDao;
import com.sbisec.helios.ap.common.model.MedSystemVariable;
import com.sbisec.helios.ap.common.service.MedSystemVariableService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 仲介業システム値取得サービス
 *
 * @author SCSK
 *
 */
@Component(value = "medSystemVariableService")
public class MedSystemVariableServiceImpl implements MedSystemVariableService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MedSystemVariableServiceImpl.class);
    
    private static final String CACHE_KEY = "ck";
    
    private volatile Map<String, MedSystemVariable> valCache = Collections
            .synchronizedMap(new HashMap<String, MedSystemVariable>());
    
    private volatile Map<String, CachedData<List<MedSystemVariable>>> listCache = Collections
            .synchronizedMap(new HashMap<String, CachedData<List<MedSystemVariable>>>());
    
    @Autowired
    protected MedSystemVariableDao medSystemVariableDao;
    
    // Expire time of cache. Default : 5 min.
    private long expireTime = 5L * 60L * 1000L;
    
    /**
     * 仲介業システム値取得処理<br/>
     * キャッシュに存在する場合はキャッシュから取得する
     *
     * @param varName 名称
     * @return システム値
     * @throws Exception 例外
     * @see com.sbisec.helios.ap.common.service.MedSystemVariableService#getMedSystemVariable(java.lang.String)
     */
    @Override
    public String getMedSystemVariable(String varName) throws Exception {
        
        LOGGER.debug("MedSystemVariableServiceImplL.getMedSystemVariable:[{}]", varName);
        
        // Check and load.
        checkAndLoad();
        
        MedSystemVariable medSystemVariable = valCache.get(varName);
        String varValue = medSystemVariable != null ? medSystemVariable.getVarValue() : null;
        
        return varValue;
    }
    
    private List<MedSystemVariable> getMedSystemVariableListInternal() throws Exception {
        
        LOGGER.debug("MedSystemVariableServiceImplL.getMedSystemVariableListInternal:[]");
        
        return medSystemVariableDao.getMedSystemVariableList();
    }
    
    /**
     * キャッシュに存在するか確認し、存在しない場合はキャッシュにロードする。
     *
     * @throws Exception 例外
     */
    private synchronized void checkAndLoad() throws Exception {
        
        // Get from cache.
        CachedData<List<MedSystemVariable>> cachedData = listCache.get(CACHE_KEY);
        // Current time.
        long currentTime = System.currentTimeMillis();
        
        // Until the effective time, using cached data.
        if (cachedData == null || cachedData.getValiedTime() < currentTime) {
            
            // Get new data from the database, and recache.
            List<MedSystemVariable> list = getMedSystemVariableListInternal();
            cachedData = new CachedData<List<MedSystemVariable>>(list, System.currentTimeMillis() + expireTime);
            listCache.put(CACHE_KEY, cachedData);
            int size = list.size();
            
            // Put to the map.
            Map<String, MedSystemVariable> tempMap = Collections
                    .synchronizedMap(new HashMap<String, MedSystemVariable>());
            
            for (int i = 0; i < size; i++) {
                
                MedSystemVariable medSystemVariable = list.get(i);
                tempMap.put(medSystemVariable.getVarName(), medSystemVariable);
            }
            
            // Replace
            valCache = tempMap;
        }
    }
}
