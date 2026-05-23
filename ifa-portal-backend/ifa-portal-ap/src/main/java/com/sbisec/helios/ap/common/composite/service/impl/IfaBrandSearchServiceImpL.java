package com.sbisec.helios.ap.common.composite.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.composite.dao.IfaBrandSearchDao;
import com.sbisec.helios.ap.common.composite.dto.IfaBrandSearchA001DtoRequest;
import com.sbisec.helios.ap.common.composite.dto.IfaBrandSearchA001DtoResponse;
import com.sbisec.helios.ap.common.composite.dto.IfaBrandSearchA002DtoRequest;
import com.sbisec.helios.ap.common.composite.dto.IfaBrandSearchA002DtoResponse;
import com.sbisec.helios.ap.common.composite.dto.IfaBrandSearchA002DtoResponse_SearchResultDetail;
import com.sbisec.helios.ap.common.composite.dto.IfaBrandSearchA003DtoRequest;
import com.sbisec.helios.ap.common.composite.dto.IfaBrandSearchA003DtoResponse;
import com.sbisec.helios.ap.common.composite.dto.IfaBrandSearchA003DtoResponse_SearchResultDetail;
import com.sbisec.helios.ap.common.composite.dto.IfaBrandSearchA006DtoRequest;
import com.sbisec.helios.ap.common.composite.dto.IfaBrandSearchA006DtoResponse;
import com.sbisec.helios.ap.common.composite.model.IfaBrandSearchSql001RequestModel;
import com.sbisec.helios.ap.common.composite.model.IfaBrandSearchSql001ResponseModel;
import com.sbisec.helios.ap.common.composite.model.IfaBrandSearchSql002RequestModel;
import com.sbisec.helios.ap.common.composite.model.IfaBrandSearchSql002ResponseModel;
import com.sbisec.helios.ap.common.composite.model.IfaBrandSearchSql003RequestModel;
import com.sbisec.helios.ap.common.composite.model.IfaBrandSearchSql003ResponseModel;
import com.sbisec.helios.ap.common.composite.model.IfaBrandSearchSql004RequestModel;
import com.sbisec.helios.ap.common.composite.model.IfaBrandSearchSql004ResponseModel;
import com.sbisec.helios.ap.common.composite.model.IfaBrandSearchSql005RequestModel;
import com.sbisec.helios.ap.common.composite.model.IfaBrandSearchSql005ResponseModel;
import com.sbisec.helios.ap.common.composite.service.IfaBrandSearchService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.SelectMarket;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import lombok.Data;

/**
 * 画面ID：CC014
 * 画面名：画面共通部品_銘柄検索
 * @author <author-name>
 *
 * 2023/08/21 新規作成
 */
@Component(value = "cmpIfaBrandSearchService")
public class IfaBrandSearchServiceImpL implements IfaBrandSearchService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaBrandSearchServiceImpL.class);
    
    @Autowired
    private IfaBrandSearchDao dao;
    
    /** 区分ID：選択市場 */
    private static final String SELECT_MARKET = "SELECT_MARKET";
    
    /** 指定した銘柄の取扱市場を取得できません。 */
    private static final String ERRORS_DMS_SELECTEDBRAND_MARKETNOTFOUND = "errors.dms.selectedBrand.marketNotFound";
    
    //APIタイプ
    private static final String API_TYPE_HERACROSS = "heracross";

    /**
     * 区分マスタ取得クラス
     */
    @Autowired
    private CodeListService codeListService;
    
    /**
     * （共通）銘柄検索Model
     * @author SCSK
     *
     */
    @Data
    private class SqlResultModel {
        
        /** 銘柄コード（半角英数字）. */
        private String brandCode;
        
        /** 銘柄名（全角半角）. */
        private String brandName;
        
        /** SOR. */
        private String mktSor;
        
        /** 東証. */
        // 手修正
        private String mktTky;
        
        /** 名証. */
        // 手修正
        private String mktNgy;
        
        /** 福証. */
        // 手修正
        private String mktFko;
        
        /** 札証. */
        // 手修正
        private String mktSpr;
        
        /** PTS. */
        // 手修正
        private String mktPts;
        
        /** 最上位上場市場（全角半角）. */
        private String upperRankMkt;
        
    }
    
    /**
     * （共通）銘柄選択初期化Model
     * @author SCSK
     *
     */
    @Data
    private class SelectBrandModel {
        
        /** 銘柄コード（半角英数字）. */
        private String brandCode;
        
        /** 銘柄名（全角半角）. */
        private String brandName;
        
        /** 銘柄市場リスト. */
        private List<MarketList> selectMarketList;
        
        /** TOPIX採用フラグ（全角半角）. */
        private String topixFlg;
        
        /** 規制銘柄区分（全角半角）. */
        private String regKbn;
        
        /** 最上位上場市場（全角半角）. */
        private String upperRankMkt;
        
    }
    
    /**
     * （共通）銘柄市場リストModel
     * @author SCSK
     *
     */
    @Data
    private class MarketList {
        
        /** キー */
        private String key;
        
        /** バリュー */
        private String value;
        
    }
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaBrandSearchA001DtoRequest
     * Dto レスポンス：IfaBrandSearchA001DtoResponse
     * model リクエスト：IfaBrandSearchSql001RequestModel
     * model レスポンス：IfaBrandSearchSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaBrandSearchA001DtoResponse> initializeA001(IfaBrandSearchA001DtoRequest dtoReq)
            throws Exception {
        
        DataList<IfaBrandSearchA001DtoResponse> dtoRes = new DataList<IfaBrandSearchA001DtoResponse>();
        IfaBrandSearchA001DtoResponse response = new IfaBrandSearchA001DtoResponse();
        List<IfaBrandSearchA001DtoResponse> resList = new ArrayList<IfaBrandSearchA001DtoResponse>();
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaBrandSearchServiceImplL.searchBrandA001");
        // エラー情報の初期化（[0]：エラーコード、[1]：エラーメッセージ）
        String[] errorInfo = new String[2];
        SelectBrandModel result = new SelectBrandModel();
        
        result = selectBrand(dtoReq.getBrandCode(), dtoReq.getTradeType(), errorInfo);
        if (!StringUtil.isNullOrEmpty(errorInfo[1])) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, errorInfo[0], errorInfo[1]);
        }
        if (result != null) {
            copyFields(result, response);
            resList.add(response);
        }
        dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
        
        return dtoRes;
    }
    
    /**
     * アクションID：A002
     * アクション名：銘柄検索
     * Dto リクエスト：IfaBrandSearchA002DtoRequest
     * Dto レスポンス：IfaBrandSearchA002DtoResponse
     * model リクエスト：IfaBrandSearchSql003RequestModel
     * model レスポンス：IfaBrandSearchSql003ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaBrandSearchA002DtoResponse> searchBrandA002(IfaBrandSearchA002DtoRequest dtoReq)
            throws Exception {
        
        DataList<IfaBrandSearchA002DtoResponse> dtoRes = new DataList<IfaBrandSearchA002DtoResponse>();
        List<IfaBrandSearchA002DtoResponse> resList = new ArrayList<IfaBrandSearchA002DtoResponse>();
        IfaBrandSearchA002DtoResponse response = new IfaBrandSearchA002DtoResponse();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaBrandSearchServiceImplL.searchBrandA002");

        DataList<SqlResultModel> selectResult = searchBrand(dtoReq.getSearch());
        if (isExistsDataList(selectResult)) {
            response.setSearchResultCount(selectResult.getDataList().size());
            List<IfaBrandSearchA002DtoResponse_SearchResultDetail> detailList = new ArrayList<IfaBrandSearchA002DtoResponse_SearchResultDetail>();
            
            for (SqlResultModel model : selectResult.getDataList()) {
                IfaBrandSearchA002DtoResponse_SearchResultDetail detail = new IfaBrandSearchA002DtoResponse_SearchResultDetail();
                copyFields(model, detail);
                detailList.add(detail);
            }
            
            response.setSearchResultDetail(detailList);
        } else {
            response.setSearchResultCount(0);
        }
        resList.add(response);
        dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
        
        return dtoRes;
    }
    
    /**
     * アクションID：A003
     * アクション名：銘柄検索
     * Dto リクエスト：IfaBrandSearchA003DtoRequest
     * Dto レスポンス：IfaBrandSearchA003DtoResponse
     * model リクエスト：IfaBrandSearchSql003RequestModel
     * model レスポンス：IfaBrandSearchSql003ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaBrandSearchA003DtoResponse> searchBrandA003(IfaBrandSearchA003DtoRequest dtoReq)
            throws Exception {
        
        DataList<IfaBrandSearchA003DtoResponse> dtoRes = new DataList<IfaBrandSearchA003DtoResponse>();
        List<IfaBrandSearchA003DtoResponse> resList = new ArrayList<IfaBrandSearchA003DtoResponse>();
        IfaBrandSearchA003DtoResponse response = new IfaBrandSearchA003DtoResponse();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaBrandSearchServiceImplL.searchBrandA003");
        
        DataList<SqlResultModel> selectResult = searchBrand(dtoReq.getSearch());
        if (isExistsDataList(selectResult)) {
            response.setSearchResultCount(selectResult.getDataList().size());
            List<IfaBrandSearchA003DtoResponse_SearchResultDetail> detailList = new ArrayList<IfaBrandSearchA003DtoResponse_SearchResultDetail>();
            
            for (SqlResultModel model : selectResult.getDataList()) {
                IfaBrandSearchA003DtoResponse_SearchResultDetail detail = new IfaBrandSearchA003DtoResponse_SearchResultDetail();
                copyFields(model, detail);
                detailList.add(detail);
            }
            
            response.setSearchResultDetail(detailList);
        } else {
            response.setSearchResultCount(0);
        }
        resList.add(response);
        dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
        
        return dtoRes;
    }
    
    /**
     * アクションID：A006
     * アクション名：銘柄選択
     * Dto リクエスト：IfaBrandSearchA006DtoRequest
     * Dto レスポンス：IfaBrandSearchA006DtoResponse
     * model リクエスト：IfaBrandSearchSql006RequestModel
     * model レスポンス：IfaBrandSearchSql006ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaBrandSearchA006DtoResponse> selectBrandA006(IfaBrandSearchA006DtoRequest dtoReq)
            throws Exception {
        
        DataList<IfaBrandSearchA006DtoResponse> dtoRes = new DataList<IfaBrandSearchA006DtoResponse>();
        List<IfaBrandSearchA006DtoResponse> resList = new ArrayList<IfaBrandSearchA006DtoResponse>();
        IfaBrandSearchA006DtoResponse response = new IfaBrandSearchA006DtoResponse();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaBrandSearchServiceImplL.selectBrandA006");
        // エラー情報の初期化（[0]：エラーコード、[1]：エラーメッセージ）
        String[] errorInfo = new String[2];
        SelectBrandModel result = selectBrand(dtoReq.getBrandCode(), dtoReq.getTradeType(), errorInfo);
        if (!StringUtil.isNullOrEmpty(errorInfo[1])) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, errorInfo[0], errorInfo[1]);
        }
        
        if (result != null) {
            copyFields(result, response);
            resList.add(response);
        }
        
        dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
        
        return dtoRes;
    }
    
    /**
     * DataListの存在チェック
     * @param dataList
     * @return true:有り　false:無し
     */
    private boolean isExistsDataList(DataList<?> dataList) {
        
        return dataList != null && !CollectionUtils.isEmpty(dataList.getDataList());
    }
    
    /**
     * 銘柄選択初期化処理
     * @param brandCode 銘柄コード
     * @param marginKbn 信用区分
     * @param errorInfo エラー情報
     * @return 編集済み検索結果共通Model
     * @throws Exception
     */
    private SelectBrandModel selectBrand(String brandCode, String marginKbn, String[] errorInfo) throws Exception {
        
        SelectBrandModel response = new SelectBrandModel();
        
        if (brandCode == null || brandCode.length() == 0) {
            return null;
        }
        
        // 正常処理結果取得判定
        boolean isSql001Result = false;
        boolean isSql002Result = false;
        boolean isSql003Result = false;
        
        // SQL001
        IfaBrandSearchSql001RequestModel selSql001Req = new IfaBrandSearchSql001RequestModel();
        selSql001Req.setBrandCode(brandCode);
        DataList<IfaBrandSearchSql001ResponseModel> selSql001Res = dao.selectIfaBrandSearchSql001(selSql001Req);
        isSql001Result = isExistsDataList(selSql001Res);
        
        // SQL002
        IfaBrandSearchSql002RequestModel selSql002Req = new IfaBrandSearchSql002RequestModel();
        selSql002Req.setBrandCode(brandCode);
        DataList<IfaBrandSearchSql002ResponseModel> selSql002Res = dao.selectIfaBrandSearchSql002(selSql002Req);
        isSql002Result = isExistsDataList(selSql002Res);
        
        // SQL003
        IfaBrandSearchSql003RequestModel selSql003Req = new IfaBrandSearchSql003RequestModel();
        selSql003Req.setBrandCode(brandCode);
        selSql003Req.setDomesticMarginAccountType(marginKbn);
        DataList<IfaBrandSearchSql003ResponseModel> selSql003Res = dao.selectIfaBrandSearchSql003(selSql003Req);
        isSql003Result = isExistsDataList(selSql003Res);
        
        // レスポンス項目編集
        response.setBrandCode(brandCode);
        if (isSql001Result && selSql001Res.getDataList().get(0) != null) {
            response.setBrandName(selSql001Res.getDataList().get(0).getBrandName());
        }
        if (isSql002Result) {
            response.setTopixFlg(selSql002Res.getDataList().get(0).getTopixFlg());
            response.setRegKbn(selSql002Res.getDataList().get(0).getRegKbn());
            // 区分.選択市場の区分値に変換
            response.setUpperRankMkt(
                codeListService.convertExtKeyToKey(SELECT_MARKET, API_TYPE_HERACROSS, selSql002Res.getDataList().get(0).getUpperRankMkt().trim())
            );
        }
        if (isSql003Result) {
            response.setSelectMarketList(setSelectMarketList(selSql003Res.getDataList()));
        } else {
            // SQL003 銘柄市場リストが0件
            errorInfo[0] = ERRORS_DMS_SELECTEDBRAND_MARKETNOTFOUND;
            errorInfo[1] = IfaCommonUtil.getMessage(errorInfo[0]);
            return null;
        }
        
        return response;
        
    }
    
    /**
     * 選択市場リスト作成処理
     * 
     * @param dataList SQL003結果
     * @return mktList 選択市場リスト
     */
    private List<MarketList> setSelectMarketList(List<IfaBrandSearchSql003ResponseModel> dataList) {
        
        List<MarketList> mktList = new ArrayList<MarketList>();
        //区分.選択市場のキーバリューを取得
        Map<String, String> codeMap = codeListService.getCodeList(SELECT_MARKET).stream()
                .collect(Collectors.toMap(s -> s.getKey(), s -> s.getValue()));
        IfaBrandSearchSql003ResponseModel selectMarket = dataList.get(0);
        if (selectMarket == null) {
            return null;
        }
        // 区分値から名称を取得する
        // 名称はDBデータから取得する
        if (selectMarket.getMktTky() != null) {
            mktList.add(createMarketList(SelectMarket.TKY, codeMap));
        }
        if (selectMarket.getMktFko() != null) {
            mktList.add(createMarketList(SelectMarket.FKO, codeMap));
        }
        if (selectMarket.getMktSpr() != null) {
            mktList.add(createMarketList(SelectMarket.SPR, codeMap));
        }
        if (selectMarket.getMktNgy() != null) {
            mktList.add(createMarketList(SelectMarket.NGY, codeMap));
        }
        if (!StringUtils.equals(selectMarket.getMktPts(), "0")) {
            mktList.add(createMarketList(SelectMarket.PTS, codeMap));
        }
        if (selectMarket.getMktSor() != null && !StringUtils.equals(selectMarket.getMktSor(), "0")) {
            mktList.add(createMarketList(SelectMarket.SOR, codeMap));
        }
        
        return mktList;
    }
    
    /**
     * 選択市場データ作成
     * @param target 対象選択市場（enum）
     * @param codeMap 区分.選択市場（dbデータ）
     * @return mkt 選択市場データ
     */
    private MarketList createMarketList(SelectMarket target, Map<String, String> codeMap) {
        
        MarketList mkt = new MarketList();
        mkt.setKey(target.getId());
        mkt.setValue(codeMap.get(target.getId()));
        return mkt;
    }
    
    /**
     * 銘柄検索処理
     * @param keyWord
     * @return
     * @throws Exception 
     */
    private DataList<SqlResultModel> searchBrand(String keyWord) throws Exception {
        
        List<SqlResultModel> resultDataList = new ArrayList<SqlResultModel>();
        
        if (keyWord == null || keyWord.length() == 0) {
            return IfaCommonUtil.createDataList(resultDataList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(),
                    null);
        }
        
        String word = keyWord.substring(0, 1);
        if (Pattern.matches("^[0-9]*$", word)) {
            // 先頭文字が半角数字ならSQL004（銘柄コード検索）
            IfaBrandSearchSql004RequestModel selSql004Req = new IfaBrandSearchSql004RequestModel();
            selSql004Req.setSearch(keyWord);
            DataList<IfaBrandSearchSql004ResponseModel> selSql004Res = dao.selectIfaBrandSearchSql004(selSql004Req);
            if (!isExistsDataList(selSql004Res)) {
                return IfaCommonUtil.createDataList(resultDataList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(),
                        null);
            }
            // 後続処理のため共通Modelに詰め替える
            selSql004Res.getDataList().stream().forEach(s -> {
                SqlResultModel result = new SqlResultModel();
                copyFields(s, result);
                resultDataList.add(result);
            });
            
        } else {
            // 先頭文字が半角数字以外ならSQL005（銘柄名検索）
            IfaBrandSearchSql005RequestModel selSql005Req = new IfaBrandSearchSql005RequestModel();
            selSql005Req.setSearch(keyWord);
            DataList<IfaBrandSearchSql005ResponseModel> selSql005Res = dao.selectIfaBrandSearchSql005(selSql005Req);
            if (!isExistsDataList(selSql005Res)) {
                return IfaCommonUtil.createDataList(resultDataList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(),
                        null);
            }
            // 後続処理のため共通Modelに詰め替える
            selSql005Res.getDataList().stream().forEach(s -> {
                SqlResultModel result = new SqlResultModel();
                copyFields(s, result);
                resultDataList.add(result);
            });
        }
        
        return IfaCommonUtil.createDataList(resultDataList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
    }
    
    /**
     * 別クラスの同名フィールドをコピーする
     * (BeanUtils.copyPropertiesが機能しないため)
     * @param source コピー元のインスタンス
     * @param target コピー先のインスタンス
     */
    private static void copyFields(Object source, Object target) {
        
        final String prefixSet = "set";
        final String prefixGet = "get";
        
        Set<String> methodSet = new HashSet<String>();
        for (Method method : target.getClass().getMethods())
            if (method.getName().startsWith(prefixSet))
                methodSet.add(method.getName().substring(prefixSet.length()));
            
        try {
            for (Method method : source.getClass().getMethods()) {
                if (!method.getName().startsWith(prefixGet))
                    continue;
                String curName = method.getName().substring(prefixGet.length());
                if (methodSet.contains(curName)) {
                    Method newMethod = target.getClass().getMethod(prefixSet + curName, method.getReturnType());
                    newMethod.invoke(target, method.invoke(source));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
