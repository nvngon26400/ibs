package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ResponseFile;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeTrendSearchA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeTrendSearchA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeTrendSearchA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeTrendSearchA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeTrendSearchA004aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeTrendSearchA004aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util.IfaTradeTrendSearchCsvOut;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaTradeTrendSearchA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaTradeTrendSearchA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaTradeTrendSearchA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaTradeTrendSearchA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaTradeTrendSearchA004aApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaTradeTrendSearchA004aApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaTradeTrendSearchA004bApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaTradeTrendSearchMultiSelectApiRequest;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB020302_0401-01
 * 画面名：取引動向検索
 *
 * @author SBI 苗萌
 * 2025/04/10 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB020302_0401-01", screenNumber = "74")
public class IfaTradeTrendSearchController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /** ダウンロードファイルの接頭語 */
    private static final String STAR_UPLOAD_CSV_FILE_NAME = "取引動向照会";
    
    
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaTradeTrendSearchInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaTradeTrendSearchA001ApiRequest
     * APIレスポンス：IfaTradeTrendSearchA001ApiResponse
     * DTOリクエスト：IfaTradeTrendSearchA001DtoRequest
     * DTOレスポンス：IfaTradeTrendSearchA001DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaTradeTrendSearchInitializeA001")
    public String initializeA001(@RequestBody IfaTradeTrendSearchA001ApiRequest apiReq) throws Exception {
        
        IfaTradeTrendSearchA001DtoRequest appReq = new IfaTradeTrendSearchA001DtoRequest();
        
        // APIリクエストをDTOリクエストにコピー
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaTradeTrendSearchA001DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaTradeTrendSearchService", "initializeA001",
                new TypeReference<DataList<IfaTradeTrendSearchA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaTradeTrendSearchA001ApiResponse> apiRes = new DataList<IfaTradeTrendSearchA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaTradeTrendSearchDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaTradeTrendSearchA002ApiRequest
     * APIレスポンス：IfaTradeTrendSearchA002ApiResponse
     * DTOリクエスト：IfaTradeTrendSearchA002DtoRequest
     * DTOレスポンス：IfaTradeTrendSearchA002DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaTradeTrendSearchDisplayA002")
    public String displayA002(@RequestBody IfaTradeTrendSearchA002ApiRequest apiReq) throws Exception {
        
        DataList<IfaTradeTrendSearchA002ApiResponse> apiRes = new DataList<IfaTradeTrendSearchA002ApiResponse>();
        
        // 閲覧可能部店(複数指定)をカンマ区切りで分割しリストに格納
        List<String> visibleButenCodeList = splitStringToList(apiReq.getVisibleButenCode());
        // 仲介業者コード(複数指定)をカンマ区切りで分割しリストに格納
        List<String> brokerCodeList = splitStringToList(apiReq.getBrokerCode());
        // 選択されている取引コースをリストに格納
        List<String> courseList = getSelectedItemList(apiReq.getCourse());
        // 選択されている証券種別をリストに格納
        List<String> securityClassList = getSelectedItemList(apiReq.getSecurityClass());

        
        IfaTradeTrendSearchA002DtoRequest appReq = new IfaTradeTrendSearchA002DtoRequest();

        // APIリクエストをDTOリクエストにコピー
        BeanUtils.copyProperties(appReq, apiReq);
        appReq.setVisibleButenCodeList(visibleButenCodeList);
        appReq.setBrokerCodeList(brokerCodeList);
        appReq.setCourseList(courseList);
        appReq.setSecurityClassList(securityClassList);
        appReq.setPeriodFrom(apiReq.getPeriodMonth().get(0));
        appReq.setPeriodTo(apiReq.getPeriodMonth().get(1));
        
        DataList<IfaTradeTrendSearchA002DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaTradeTrendSearchService", "displayA002",
                new TypeReference<DataList<IfaTradeTrendSearchA002DtoResponse>>() {
                }, appReq);
        
        // レスポンスをコピー
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaTradeTrendSearchCsvOutputA004a
     * アクションID：A004a
     * アクション名：CSV出力
     * APIリクエスト：IfaTradeTrendSearchA004aApiRequest
     * APIレスポンス：IfaTradeTrendSearchA004aApiResponse
     * DTOリクエスト：IfaTradeTrendSearchA004aDtoRequest
     * DTOレスポンス：IfaTradeTrendSearchA004aDtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaTradeTrendSearchCsvOutputA004a")
    public String csvOutputA004a(@RequestBody IfaTradeTrendSearchA004aApiRequest apiReq) throws Exception {
        
        DataList<IfaTradeTrendSearchA004aApiResponse> apiRes = new DataList<IfaTradeTrendSearchA004aApiResponse>();
        
        // 閲覧可能部店(複数指定)をカンマ区切りで分割しリストに格納
        List<String> visibleButenCodeList = splitStringToList(apiReq.getVisibleButenCode());
        // 仲介業者コードをカンマ区切りで分割しリストに格納
        List<String> brokerCodeList = splitStringToList(apiReq.getBrokerCode());
        // 選択されている取引コースをリストに格納
        List<String> courseList = getSelectedItemList(apiReq.getCourse());
        // 選択されている証券種別をリストに格納
        List<String> securityClassList = getSelectedItemList(apiReq.getSecurityClass());
        
        IfaTradeTrendSearchA004aDtoRequest appReq = new IfaTradeTrendSearchA004aDtoRequest();
        
        // APIリクエストをDTOリクエストにコピー
        BeanUtils.copyProperties(appReq, apiReq);
        appReq.setVisibleButenCodeList(visibleButenCodeList);
        appReq.setBrokerCodeList(brokerCodeList);
        appReq.setCourseList(courseList);
        appReq.setSecurityClassList(securityClassList);
        appReq.setPeriodFrom(apiReq.getPeriodMonth().get(0));
        appReq.setPeriodTo(apiReq.getPeriodMonth().get(1));
        
        String frameworkSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID,
                String.class);
        
        DataList<IfaTradeTrendSearchA004aDtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaTradeTrendSearchService", "csvOutputA004a",
                new TypeReference<DataList<IfaTradeTrendSearchA004aDtoResponse>>() {
                }, appReq, frameworkSessionId);
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaTradeTrendSearchCsvOutputA004b
     * アクションID：A004b
     * アクション名：CSV出力
     * リクエスト：IfaTradeTrendSearchA004bApiRequest
     * レスポンス：HttpServletResponse 
     *
     * @param apiReq リクエスト
     * @param response httpレスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaTradeTrendSearchCsvOutputA004b")
    @ResponseFile
    public void csvOutputA004b(@RequestBody IfaTradeTrendSearchA004bApiRequest apiReq, HttpServletResponse response)
            throws Exception {
        
        // A004aで作成したファイルをダウンロード
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName(STAR_UPLOAD_CSV_FILE_NAME), IfaCommonUtil.getUserAccount(),
                apiReq.getPattern());
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
    
    @Override
    protected String getCsvHeader(String pattern) {
        
        return IfaTradeTrendSearchCsvOut.getHeader(pattern);
    }
    
    /**
     * カンマ区切りの文字列をリストに変換
     *
     * @param str 文字列
     * @return list  分割した文字列を格納したリス
     */
    private List<String> splitStringToList(String str) {
        
        List<String> list = new ArrayList<String>();
        
        // null、空文字の場合は空のリストを返却
        if (str == null || str.isEmpty()) {
            return list;
        }
        
        // カンマ区切りで分割する
        String[] parts = str.split((","));
        for (String part : parts) {
            if (!ObjectUtils.isEmpty(part)) {
                list.add(part.trim());
            }
        }
        
        return list;
    }
       
    /**
     * 選択された項目のみをリストに格納して返却
     *
     * @param multiSelectList マルチ選択リスト
     * @return selectedItemList  選択された項目のみを格納したリスト 
     */
    List<String> getSelectedItemList(List<IfaTradeTrendSearchMultiSelectApiRequest> multiSelectList) {
        
        List<String> selectedItemList = new ArrayList<String>();
        
        for (IfaTradeTrendSearchMultiSelectApiRequest item : multiSelectList) {
            if (Boolean.parseBoolean(item.getIsSelected())) {
                selectedItemList.add(item.getId());
            }
        }
        
        return selectedItemList;
    }

}
