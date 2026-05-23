package jp.co.sbisec.pcenter.dto.heracross;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jp.co.sbisec.pcenter.dto.DtoOut;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RealQuoteSnapshotOut extends DtoOut {

    @lombok.Getter(onMethod = @__(@JsonProperty("responseStatus")))
    @lombok.Setter(onMethod = @__(@JsonProperty("responseStatus")))
    private ResponseStatus responseStatus;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("itemsArraySize")))
    @lombok.Setter(onMethod = @__(@JsonProperty("itemsArraySize")))
    private int itemsArraySize;

    @lombok.Getter(onMethod = @__(@JsonProperty("feedData")))
    @lombok.Setter(onMethod = @__(@JsonProperty("feedData")))
    private List<RealQuoteSnapshot> feedData;
}