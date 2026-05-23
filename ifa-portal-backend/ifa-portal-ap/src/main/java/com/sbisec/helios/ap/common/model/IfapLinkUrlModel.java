package com.sbisec.helios.ap.common.model;

import java.util.Date;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * IFAPリンクURLテーブルの値を保持するModel
 *
 * @author 河口
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class IfapLinkUrlModel extends ModelBase {
    
    /** シリアルID */
    private static final long serialVersionUID = 1L;
    
    /** URL_ID */
    private Long urlId;
    
    /** URL */
    private String url;
    
    /** リンク表示名 */
    private String dispName;
    
    /** 外部リンクフラグ */
    private String extLinkFlg;
    
    /** 作成者 */
    private String createUser;
    
    /** 作成日 */
    private Date createDate;
    
    /** 更新者 */
    private String updateUse;
    
    /** 更新日 */
    private Date updateDate;
}
