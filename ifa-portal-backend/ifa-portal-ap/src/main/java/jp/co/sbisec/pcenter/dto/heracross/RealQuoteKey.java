package jp.co.sbisec.pcenter.dto.heracross;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RealQuoteKey {
    
    @lombok.Getter
    @lombok.Setter
    private String symbol;
    
    @lombok.Getter
    @lombok.Setter
    private String market;
    
    public RealQuoteKey(String symbol, String market) {
        
        this.symbol = symbol;
        this.market = market;
    }
    
    public RealQuoteKey() {
        
    }
}
