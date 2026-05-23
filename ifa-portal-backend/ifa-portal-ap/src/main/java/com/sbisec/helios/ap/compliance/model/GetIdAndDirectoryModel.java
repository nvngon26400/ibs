
package com.sbisec.helios.ap.compliance.model;

import com.sbibits.earth.model.ModelBase;

@SuppressWarnings("serial")
public class GetIdAndDirectoryModel extends ModelBase {

    private String funcId;
    private String catId;
    private String fileDir;

    /**
     * funcIdを取得します。
     * @return funcId
     */
    public String getFuncId() {
        return funcId;
    }
    /**
     * funcIdを設定します。
     * @param funcId
     */
    public void setFuncId(String funcId) {
        this.funcId = funcId;
    }
    /**
     * catIdを取得します。
     * @return catId
     */
    public String getCatId() {
        return catId;
    }
    /**
     * catIdを設定します。
     * @param catId
     */
    public void setCatId(String catId) {
        this.catId = catId;
    }
    /**
     * fileDirを取得します。
     * @return fileDir
     */
    public String getFileDir() {
        return fileDir;
    }
    /**
     * fileDirを設定します。
     * @param fileDir
     */
    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
    }
}
