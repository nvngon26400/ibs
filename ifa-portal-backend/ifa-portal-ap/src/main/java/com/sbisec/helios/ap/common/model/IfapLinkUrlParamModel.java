package com.sbisec.helios.ap.common.model;

import java.util.Date;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * IFAPリンクURLパラメータ テーブルの値を保持するModel
 *
 * @author 河口
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class IfapLinkUrlParamModel extends ModelBase {
    
    /** シリアルID */
    private static final long serialVersionUID = 1L;
    
    /** URL_ID */
    private Long urlId;
    
    /** HTTPメソッド */
    private String httpMethod;
    
    /** パラメータパターンID */
    private Long patternId;
    
    /** パラメータ連番 */
    private Long paramSeq;
    
    /** パラメータ項目名 */
    private String paramKey;
    
    /** パラメータ項目値 */
    private String paramValue;
    
    /** 作成者 */
    private String createUser;
    
    /** 作成日 */
    private Date createDate;
    
    /** 更新者 */
    private String updateUser;
    
    /** 更新日 */
    private Date updateDate;
}
