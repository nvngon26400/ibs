package com.sbisec.helios.ap.common.exception;

import com.sbibits.earth.model.DataList;

/**
 * System abnormality.
 *
 * @Organization SBIBITS DaLian CB Group
 */
public class SystemErrorException extends AbstractDataListPreservingException {
    
    /* serialVersionUID */
    private static final long serialVersionUID = -4722649086136173748L;
    
    /**
     * コンストラクタ
     *
     * @param message メッセージ
     */
    public SystemErrorException(String message) {
        
        super(message);
    }
    
    /**
     * コンストラクタ
     *
     * @param message メッセージ
     * @param cause 発生元例外
     */
    public SystemErrorException(String message, Throwable cause) {
        
        super(message, cause);
    }
    
    /**
     * コンストラクタ
     *
     * @param dataList DataList
     */
    public SystemErrorException(DataList<?> dataList) {
        
        super(dataList);
    }
    
    /**
     * コンストラクタ
     *
     * @param dataList DataList
     * @param cause 発生元例外
     */
    public SystemErrorException(DataList<?> dataList, Throwable cause) {
        
        super(dataList, cause);
    }
    
    public SystemErrorException(Throwable cause) {
        
        super(cause);
    }
}
