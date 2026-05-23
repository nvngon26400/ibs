package com.sbisec.helios.ap.faq.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.faq.dao.IfaFaqDao;
import com.sbisec.helios.ap.faq.dao.model.IfaFaqSql001RequestModel;
import com.sbisec.helios.ap.faq.dao.model.IfaFaqSql001ResponseModel;
import com.sbisec.helios.ap.faq.dao.model.IfaFaqSql002ResponseModel;
import com.sbisec.helios.ap.faq.dao.model.IfaFaqSql003RequestModel;
import com.sbisec.helios.ap.faq.dao.model.IfaFaqSql003ResponseModel;
import com.sbisec.helios.ap.faq.dto.IfaFaqA002DtoRequest;
import com.sbisec.helios.ap.faq.dto.IfaFaqA002DtoResponse;
import com.sbisec.helios.ap.faq.dto.IfaFaqA002SearchResultDtoResponse;
import com.sbisec.helios.ap.faq.dto.IfaFaqA005DtoRequest;
import com.sbisec.helios.ap.faq.dto.IfaFaqA005DtoResponse;
import com.sbisec.helios.ap.faq.dto.IfaFaqX001ContentsDtoResponse;
import com.sbisec.helios.ap.faq.dto.IfaFaqX001DtoRequest;
import com.sbisec.helios.ap.faq.dto.IfaFaqX001DtoResponse;
import com.sbisec.helios.ap.faq.service.IfaFaqService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 画面ID：SUB00-05
 * 画面名：よくある質問
 *
 * @author SCSK 仁井田
 2024/05/30 新規作成
 */
@Component(value = "cmpIfaFaqService")
public class IfaFaqServiceImpL implements IfaFaqService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaFaqServiceImpL.class);
    
    // --------------------------------
    // メッセージ
    // --------------------------------
    /** "検索結果が0件です。\n条件を設定して再度検索して下さい。" */
    private static final String ERRORS_DATALIST_NOTFOUND = "errors.dataList.notfound";
    
    /** "検索結果が{0}件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。" */
    private static final String WARNINGS_OVER_MAX_ROWNUM = "warnings.dataList.overMaxRownum";
    
    /** "該当する情報は存在しません。" */
    private static final String ERRORS_INFOMATION_NOTFOUND = "errors.cmn.information.notfound";
    
    // --------------------------------
    // 変数定義
    // --------------------------------
    /** 最小取得件数 */
    private static final int MIN_COUNT = 1;
    
    /** 最大取得件数 */
    private static final int MAX_COUNT = 1000;
    
    @Autowired
    private IfaFaqDao dao;
    
    /**
     * アクションID：X001
     * アクション名：初期表示
     * DTO リクエスト：IfaFaqX001DtoRequest
     * DTO レスポンス：IfaFaqX001DtoResponse
     * model リクエスト：IfaFaqX001RequestModel
     * model レスポンス：IfaFaqX001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaFaqX001DtoResponse> initialDisplayX001(IfaFaqX001DtoRequest dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaFaqServiceImplL.initialDisplayX001");
        }
        
        // コンテンツリストを取得（SQL002）
        DataList<IfaFaqSql002ResponseModel> sql002ResList = dao.selectIfaFaqSql002();
        
        List<IfaFaqX001ContentsDtoResponse> contentsList = new ArrayList<IfaFaqX001ContentsDtoResponse>();
        
        // レスポンスの設定
        for (IfaFaqSql002ResponseModel sql002res : sql002ResList.getDataList()) {
            IfaFaqX001ContentsDtoResponse contents = new IfaFaqX001ContentsDtoResponse();
            contents.setPrimaryItem(sql002res.getPrimaryItem());
            contents.setTertiaryItem(sql002res.getTertiaryItem());
            contents.setHeading(sql002res.getHeading());
            contents.setContentsNo(sql002res.getContentsNo());
            contentsList.add(contents);
        }
        
        List<IfaFaqX001DtoResponse> resList = new ArrayList<IfaFaqX001DtoResponse>();
        IfaFaqX001DtoResponse res = new IfaFaqX001DtoResponse();
        
        res.setContentsNo(dtoReq.getContentsNo());
        res.setContentsList(contentsList);
        resList.add(res);
        
        DataList<IfaFaqX001DtoResponse> dtoRes = new DataList<IfaFaqX001DtoResponse>();
        
        dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        
        return dtoRes;
    }
    
    /**
     * アクションID：A002
     * アクション名：全文検索
     * DTO リクエスト：IfaFaqA002DtoRequest
     * DTO レスポンス：IfaFaqA002DtoResponse
     * model リクエスト：IfaFaqA002RequestModel
     * model レスポンス：IfaFaqA002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaFaqA002DtoResponse> fullTextSearchA002(IfaFaqA002DtoRequest dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaFaqServiceImplL.fullTextSearchA002");
        }
        
        // 検索キーワードリストを作成
        List<String> searchKeyWordList = new ArrayList<String>();
        searchKeyWordList = splitSearchKeyWord(dtoReq.getSearchWord());
        
        // SQL001のリクエスト値を設定
        IfaFaqSql003RequestModel selSql003Req = new IfaFaqSql003RequestModel();
        selSql003Req.setSearchKeyWordList(searchKeyWordList);
        selSql003Req.setMaxCount(MAX_COUNT);
        
        // 検索キーワードリストより、検索結果リストを取得(SQL003)
        DataList<IfaFaqSql003ResponseModel> selSql003ResList = dao.selectIfaFaqSql003(selSql003Req);
        
        DataList<IfaFaqA002DtoResponse> dtoRes = new DataList<IfaFaqA002DtoResponse>();
        List<IfaFaqA002DtoResponse> resList = new ArrayList<IfaFaqA002DtoResponse>();
        IfaFaqA002DtoResponse res = new IfaFaqA002DtoResponse();
        
        // SQL003.検索結果リストが0件の場合、0件メッセージをセットして終了
        if (ObjectUtils.isEmpty(selSql003ResList) || selSql003ResList.size() < MIN_COUNT) {
            String msg = IfaCommonUtil.getMessage(ERRORS_DATALIST_NOTFOUND, new String[] {});
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.INFO, ERRORS_DATALIST_NOTFOUND, msg);
            return dtoRes;
        }
        
        // レスポンス.検索結果リストを作成
        List<IfaFaqA002SearchResultDtoResponse> searchResultList = new ArrayList<IfaFaqA002SearchResultDtoResponse>();
        
        for (IfaFaqSql003ResponseModel result : selSql003ResList.getDataList()) {
            
            IfaFaqA002SearchResultDtoResponse searchResult = new IfaFaqA002SearchResultDtoResponse();
            
            // ダイジェストを作成
            String digest = createDigest(result.getContentText(), searchKeyWordList);
            
            searchResult.setContentsNo(result.getContentsNo());
            searchResult.setDigest(digest);
            searchResult.setHeading(result.getHeading());
            searchResultList.add(searchResult);
        }
        
        // レスポンスの設定 
        res.setSearchKeyWordList(searchKeyWordList);
        res.setSearchResultListCount(selSql003ResList.size());
        res.setSearchResultList(searchResultList);
        resList.add(res);
        
        // SQL003.検索結果リストが1001件の場合、件数超過メッセージをセットして終了
        if (selSql003ResList.get(0).getCount() > MAX_COUNT) {
            String msg = IfaCommonUtil.getMessage(WARNINGS_OVER_MAX_ROWNUM,
                    new String[] { String.valueOf(MAX_COUNT), String.valueOf(selSql003ResList.get(0).getCount()) });
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.WARNING, WARNINGS_OVER_MAX_ROWNUM, msg);
        } else {
            // 正常終了
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        }
        
        return dtoRes;
    }
    
    /**
     * アクションID：A005
     * アクション名：コンテンツ表示
     * DTO リクエスト：IfaFaqA005DtoRequest
     * DTO レスポンス：IfaFaqA005DtoResponse
     * model リクエスト：IfaFaqA005RequestModel
     * model レスポンス：IfaFaqA005ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaFaqA005DtoResponse> contentsDisplayA005(IfaFaqA005DtoRequest dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaFaqServiceImplL.contentsDisplayA005");
        }
        
        // SQL001のリクエスト値を設定
        IfaFaqSql001RequestModel selSql001Req = new IfaFaqSql001RequestModel();
        selSql001Req.setContentsNo(dtoReq.getContentsNo());
        
        // コンテンツ本文を取得（SQL001）
        DataList<IfaFaqSql001ResponseModel> selSql001ResList = dao.selectIfaFaqSql001(selSql001Req);
        
        DataList<IfaFaqA005DtoResponse> dtoRes = new DataList<IfaFaqA005DtoResponse>();
        List<IfaFaqA005DtoResponse> resList = new ArrayList<IfaFaqA005DtoResponse>();
        IfaFaqA005DtoResponse res = new IfaFaqA005DtoResponse();
        
        // コンテンツが取得できなかった場合、エラーを返す
        if (ObjectUtils.isEmpty(selSql001ResList) || selSql001ResList.size() < MIN_COUNT) {
            String msg = IfaCommonUtil.getMessage(ERRORS_INFOMATION_NOTFOUND, new String[] {});
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.FATAL, ERRORS_INFOMATION_NOTFOUND, msg);
            return dtoRes;
        }
        
        // レスポンスの設定
        res.setContentsNo(dtoReq.getContentsNo());
        res.setHeading(selSql001ResList.get(0).getHeading());
        res.setContentText(selSql001ResList.get(0).getContentText());
        resList.add(res);
        
        dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        
        return dtoRes;
    }
    
    /**
     * 検索キーワードリスト作成
     *
     * @param searchWord 検索ワード（分割前）
     * @return searchKeyWordList 検索キーワードリスト
     */
    private List<String> splitSearchKeyWord(String searchWord) {
        
        // 半角スペースおよび全角スペースで分割
        String[] parts = searchWord.split("[ 　]+");
        
        // 分割した各要素をトリムして検索キーワードリストに格納
        List<String> searchKeyWordList = new ArrayList<String>();
        for (String part : parts) {
            if (!ObjectUtils.isEmpty(part)) {
                searchKeyWordList.add(part.trim());
            }
        }
        
        return searchKeyWordList;
    }
    
    /**
     * ダイジェスト作成
     *
     * @param searchWord コンテンツ本文
     * @param searchWord 検索キーワードリスト
     * @return digest ダイジェスト
     */
    private String createDigest(String contentText, List<String> searchKeyWordList) {
        
        // 改行コードをLF（\n）に統一
        String fixedContentText = contentText.replaceAll("\\n|＼n|＼＼n", "\n");
        
        // 1. 改行または句点で分割
        String[] sentences = fixedContentText.split("\n|(?<=。)");
        
        // 2. 各文章をトリム
        List<String> trimmedSentences = new ArrayList<>();
        for (String sentence : sentences) {
            trimmedSentences.add(sentence.trim());
        }
        
        // 3. 各文章が条件に当てはまる場合、編集し、ダイジェストに文字連結
        StringBuilder digestBuilder = new StringBuilder();
        for (String sentence : trimmedSentences) {
            for (String keyword : searchKeyWordList) {
                // 文章が検索キーワードを少なくともひとつ含む場合
                if (sentence.contains(keyword)) {
                    
                    // 文章に最初に現れる検索キーワードの末尾位置
                    int keywordEndIndex = sentence.indexOf(keyword) + keyword.length();
                    
                    // 検索キーワードの末尾位置が先頭から100文字以内の場合
                    if (keywordEndIndex <= 100) {
                        // 文章の長さが100文字以内　の場合、文章は編集しない
                        if (sentence.length() <= 100) {
                            digestBuilder.append(sentence);
                            // 文章全体の長さが100文字を超える場合、「文章の先頭から100文字+"..."」に変換
                        } else {
                            String editedSentence = sentence.substring(0, 100) + "...";
                            digestBuilder.append(editedSentence);
                        }
                        // 検索キーワードの末尾位置が100文字より大きい場合
                    } else {
                        String editedSentence = sentence.substring(0, keywordEndIndex) + "...";
                        digestBuilder.append(editedSentence);
                    }
                    
                    break;
                }
            }
        }
        
        // 4.  ダイジェストを整形
        String digest = digestBuilder.toString();
        // ダイジェストが200文字を超える場合、201文字以降を切り捨て
        if (digest.length() > 200) {
            digest = digest.substring(0, 200);
            // 末尾が"..."でなければ"..."を末尾に追加
            if (!digest.endsWith("...")) {
                digest = digest + "...";
            }
        }
        
        return digest;
    }
}
