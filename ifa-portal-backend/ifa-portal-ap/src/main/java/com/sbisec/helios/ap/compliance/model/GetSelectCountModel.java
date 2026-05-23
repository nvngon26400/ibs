
package com.sbisec.helios.ap.compliance.model;

import com.sbibits.earth.model.ModelBase;

@SuppressWarnings("serial")
public class GetSelectCountModel extends ModelBase {
    
    private String cnt;
    
    /**
     * cntを取得します。
     *
     * @return cnt
     */
    public String getCnt() {
        
        return cnt;
    }
    
    /**
     * cntを設定します。
     *
     * @param cnt cnt
     */
    public void setCnt(String cnt) {
        
        this.cnt = cnt;
    }
}
