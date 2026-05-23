package com.sbisec.helios.ap.common.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.common.model.IfapLinkUrlModel;
import com.sbisec.helios.ap.common.model.IfapLinkUrlParamModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * リンクURL機能のMapper
 *
 * @author 河口
 *
 */
@Mapper
public interface LinkUrlMapper {
    
    /**
     * IFAPリンクURL（IFAP_LINK_URL）テーブルから、指定したURLIDのURL情報を取得する。
     *
     * @param urlId URLID
     * @return リンクURL情報
     */
    public IfapLinkUrlModel getIfapLinkUrl(@Param("urlId") String urlId);
    
    /**
     * IFAPリンクURLパラメータ（IFAP_LINK_URL_PARAM）テーブルから、指定したURLのURLパラメータ情報を取得する。
     *
     * @param urlId      URLID
     * @param patternId  パターンID
     * @param httpMethod HTTPメソッド
     * @return リンクURLパラメータ情報
     */
    public List<IfapLinkUrlParamModel> getIfapLinkUrlParam(@Param("urlId") String urlId,
            @Param("patternId") String patternId, @Param("httpMethod") String httpMethod);
    
}
