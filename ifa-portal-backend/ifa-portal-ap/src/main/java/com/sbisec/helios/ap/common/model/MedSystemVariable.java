package com.sbisec.helios.ap.common.model;

import com.sbibits.earth.model.ModelBase;

/**
 * 仲介業システム値
 *
 * @author SCSK
 *
 */
@SuppressWarnings("serial")
public class MedSystemVariable extends ModelBase {
    
    // 名称
    private String varName;
    
    // システム値
    private String varValue;
    
    public String getVarName() {
        
        return varName;
    }
    
    public void setVarName(String varName) {
        
        this.varName = varName;
    }
    
    public String getVarValue() {
        
        return varValue;
    }
    
    public void setVarValue(String varValue) {
        
        this.varValue = varValue;
    }
    
}
