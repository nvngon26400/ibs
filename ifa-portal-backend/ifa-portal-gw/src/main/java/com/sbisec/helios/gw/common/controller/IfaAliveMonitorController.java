package com.sbisec.helios.gw.common.controller;

import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.util.json.JsonConverter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 死活監視用コントローラ
 * 
 * @author SCSK
 *
 */
@RestController
@ScreenId(groupId = "COMMON", id = "AliveMonitor", ignoreCheck = true)
public class IfaAliveMonitorController extends BaseController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaAliveMonitorController.class);
    
    private static final String PATH = "/alive";
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    @GetMapping(PATH)
    public String watch() throws Exception {
        
        long plReceivedTime = System.currentTimeMillis();
        long blReceivedTime = 0;
        boolean completed = false;
        
        try {
            
            // Call service.
            blReceivedTime = ApiRequestUtil.invoke("ifaAliveMonitorService", "watch", new TypeReference<Long>() {}, "");
            completed = true;
            
        } catch (Throwable e) {
            
            LOGGER.warn("Exception at alive check.", e);
        }
        
        IfaAliveMonitorApiResponse result = new IfaAliveMonitorApiResponse(plReceivedTime, blReceivedTime, completed);
        
        String jsonString = jc.toString(result);
        
        return jsonString;
    }
    
    public class IfaAliveMonitorApiResponse {
        
        private long plReceivedTime = 0;
        
        private long blReceivedTime = 0;
        
        private boolean result = false;
        
        private IfaAliveMonitorApiResponse(long plReceivedTime, long blReceivedTime, boolean result) {
            
            this.plReceivedTime = plReceivedTime;
            this.blReceivedTime = blReceivedTime;
            this.result = result;
        }
        
        public long getPlReceivedTime() {
            
            return plReceivedTime;
        }
        
        public long getBlReceivedTime() {
            
            return blReceivedTime;
        }
        
        public boolean getResult() {
            
            return result;
        }
    }
}
