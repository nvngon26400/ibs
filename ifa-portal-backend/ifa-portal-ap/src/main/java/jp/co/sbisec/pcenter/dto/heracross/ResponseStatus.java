package jp.co.sbisec.pcenter.dto.heracross;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseStatus {
    
    @lombok.Getter(onMethod = @__(@JsonProperty("returnCode")))
    @lombok.Setter(onMethod = @__(@JsonProperty("returnCode")))
    private Integer returnCode;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("messageType")))
    @lombok.Setter(onMethod = @__(@JsonProperty("messageType")))
    private Integer messageType;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("messageCode")))
    @lombok.Setter(onMethod = @__(@JsonProperty("messageCode")))
    private String messageCode;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("messageText")))
    @lombok.Setter(onMethod = @__(@JsonProperty("messageText")))
    private String messageText;
    
    public ResponseStatus(Integer returnCode, Integer messageType, String messageCode, String messageText) {
        
        this.returnCode = returnCode;
        
        this.messageType = messageType;
        
        this.messageCode = messageCode;
        
        this.messageText = messageText;
    }
    
    public ResponseStatus(Integer returnCode) {
        
        this.returnCode = returnCode;
    }
    
    public ResponseStatus() {
        
    }
    
}
