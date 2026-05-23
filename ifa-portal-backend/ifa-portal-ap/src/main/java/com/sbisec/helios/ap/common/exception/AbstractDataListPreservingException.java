package com.sbisec.helios.ap.common.exception;

import com.sbibits.earth.model.DataList;

/**
 * DataListを保持する例外の基底クラス
 *
 */
public abstract class AbstractDataListPreservingException extends RuntimeException {
    
    /* serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    /* データリスト */
    private DataList<?> dataList = null;

    /**
     * コンストラクタ
     *
     * @param message メッセージ
     */
    public AbstractDataListPreservingException(String message) {
        
        super(message);
    }

    /**
     * コンストラクタ
     *
     * @param cause 発生元例外
     */
    public AbstractDataListPreservingException(Throwable cause) {
        
        super(cause);
    }
    
    /**
     * コンストラクタ
     *
     * @param message メッセージ
     * @param cause 発生元例外
     */
    public AbstractDataListPreservingException(String message, Throwable cause) {
        
        super(message, cause);
    }
    
    /**
     * コンストラクタ
     *
     * @param dataList DataList
     */
    public AbstractDataListPreservingException(DataList<?> dataList) {
        
        this(dataList, null);
    }
        
    /**
     * コンストラクタ
     *
     * @param dataList DataList
     * @param cause 発生元例外
     */
    public AbstractDataListPreservingException(DataList<?> dataList, Throwable cause) {
        
        super(dataList.getReturnCode() + ": " + dataList.getMessage(), cause);
        
        this.dataList = dataList;
    }
    
    /**
     * DataListを取得する。
     *
     * @return DataList
     */
    public DataList<?> getDataList() {
        
        return this.dataList;
    }
}
