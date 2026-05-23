package com.sbisec.helios.ap.athena.protocol.common;

import java.io.Serializable;

import lombok.Data;

/**
 * クエリページング Dto.
 * 
 * @author 松田
 */
@Data
public class Page implements Serializable {
    
    private static final long serialVersionUID = -4242074727766705142L;
    
    public Page() {
        
    }
    
    // Page number.
    private int pageNum;
    
    // Page size.
    private int pageSize;
    
    // Total count.
    private long totalCount;
    
    // Returns true if it is the first page.
    private Boolean firstPage;
    
    // Returns true if it is the last page.
    private Boolean lastPage;
    
    // Returns true if there is a previous page.
    private Boolean hasPreviousPage;
    
    // Returns true if there is a next page.
    private Boolean hasNextPage;
    
}
