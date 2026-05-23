package com.sbisec.helios.ap.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import com.sbisec.helios.ap.common.dao.mapper.LinkUrlMapper;
import com.sbisec.helios.ap.common.dto.LinkUrlDto;
import com.sbisec.helios.ap.common.exception.IfaRuntimeException;
import com.sbisec.helios.ap.common.model.IfapLinkUrlModel;
import com.sbisec.helios.ap.common.model.IfapLinkUrlParamModel;
import com.sbisec.helios.ap.common.service.LinkUrlService;
import com.sbisec.helios.ap.common.util.HttpApiWrapper;
import com.sbisec.helios.ap.common.util.SpelExpressionParserWrapper;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * リンクURLサービス実装クラス
 *
 * @author 河口
 *
 */
@Component(value = "linkUrlService")
public class LinkUrlServiceImpl implements LinkUrlService {
    
    /* ロガー */
    // private static final Logger LOGGER =
    // LoggerFactory.getLogger(LinkUrlServiceImpl.class);
    
    /* HTTPメソッド GET */
    private static final String HTTP_METHOD_GET = "GET";
    
    /* リンクURLMapper */
    @Autowired
    private LinkUrlMapper linkUrlMapper;
    
    @Override
    public LinkUrlDto getLinkUrl(String urlId, String patternId, String httpMethod) {
        
        LinkUrlDto linkUrlDto = new LinkUrlDto();
        
        // ① プロパティ．URLIDの値に従い、以下処理を行う。
        if (!StringUtils.equals(urlId, "0")) {
            
            // ② URL情報を取得する。
            IfapLinkUrlModel linkUrlModel = linkUrlMapper.getIfapLinkUrl(urlId);
            
            if (linkUrlModel != null) {
                
                // ③ URLパラメータ情報を取得する。
                // サービスの下記の引数に値が設定されていない場合、Mapperへのパラメータにデフォルト値を引き渡す。
                // ・パターンID = 1
                // ・HTTPメソッド = GET
                List<IfapLinkUrlParamModel> linkUrlParams = linkUrlMapper.getIfapLinkUrlParam(urlId,
                        StringUtils.defaultString(patternId, "1"),
                        StringUtils.defaultString(httpMethod, HTTP_METHOD_GET));
                
                linkUrlDto.setLinkUrl(linkUrlModel.getUrl()); // GETかつパラメータが存在する場合は、後続の処理で再設定する
                linkUrlDto.setDispName(linkUrlModel.getDispName());
                linkUrlDto.setExtLinkFlag(linkUrlModel.getExtLinkFlg());
                
                // EL式のパーサーの初期化
                SpelExpressionParserWrapper spelExpressionParserWrapper = new SpelExpressionParserWrapper();
                
                if (HTTP_METHOD_GET.equals(httpMethod)) {
                    
                    // GETかつパラメータが存在する場合、URLにパラメータを追記し再設定する
                    if (CollectionUtils.isNotEmpty(linkUrlParams)) {
                        
                        StringJoiner urlString = new StringJoiner("&", "?", "");
                        
                        for (IfapLinkUrlParamModel linkUrlParam : linkUrlParams) {
                            
                            urlString.add(linkUrlParam.getParamKey() + "="
                                    + spelExpressionParserWrapper.parseExpression(linkUrlParam.getParamValue()));
                            
                        }
                        linkUrlDto.setLinkUrl(linkUrlModel.getUrl() + urlString.toString());
                    }
                    
                } else {
                    
                    for (IfapLinkUrlParamModel linkUrlParam : linkUrlParams) {
                        
                        linkUrlDto.getPostRequest().put(linkUrlParam.getParamKey(),
                                spelExpressionParserWrapper.parseExpression(linkUrlParam.getParamValue()));
                    }
                }
            }
        }
        if (StringUtils.isEmpty(linkUrlDto.getLinkUrl())) {
            
            throw new IfaRuntimeException("errors.targetPickUpFailure", new String[] { "URL" });
        }
        return linkUrlDto;
    }
    
    @Override
    public Map<String, String> getLinkHtml(String urlId, String patternId, String httpMethod) {
        
        LinkUrlDto linkUrlDto = this.getLinkUrl(urlId, patternId, httpMethod);
        
        Map<String, String> html = new HashMap<String, String>();
        
        String result = null;
        
        if (StringUtils.equals(HTTP_METHOD_GET, httpMethod)) {
            
            result = HttpApiWrapper.callAsGet(linkUrlDto.getLinkUrl());
            
        } else {
            
            result = HttpApiWrapper.callAsPost(linkUrlDto.getLinkUrl(), linkUrlDto.getPostRequest());
        }
        
        html.put("html", result);
        
        return html;
    }
}
