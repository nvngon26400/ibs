package com.sbisec.helios.ap.common.service;

import java.util.Map;

import com.sbisec.helios.ap.common.dto.LinkUrlDto;
import com.sbisec.helios.ap.service.Service;

/**
 * リンクURLサービス
 *
 * @author 河口
 *
 */
public interface LinkUrlService extends Service {
    
    /**
     * 指定されたパラメータに対応するリンクURLを編集して返却する
     *
     * @param urlId      URLID
     * @param patternId  パラメータパターンID
     * @param httpMethod HTTPメソッド
     * @return リンク情報
     */
    public LinkUrlDto getLinkUrl(String urlId, String patternId, String httpMethod);
    
    /**
     * 指定されたパラメータに対応するリンクURLを編集した後、当該URLを使用して
     *
     * @param urlId      URLID
     * @param patternId  パラメータパターンID
     * @param httpMethod HTTPメソッド
     * @return HTMLテキスト
     */
    public Map<String, String> getLinkHtml(String urlId, String patternId, String httpMethod);
}
