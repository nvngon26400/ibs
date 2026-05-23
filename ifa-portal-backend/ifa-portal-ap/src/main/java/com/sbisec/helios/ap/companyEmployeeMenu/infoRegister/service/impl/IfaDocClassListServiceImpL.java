package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.IfaDocClassListDao;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaDocClassListSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaDocClassListSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaDocClassListSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaDocClassListSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaDocClassListSql004RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaDocClassListSql005RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaDocClassListSql005ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA001ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA002RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA002ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA003RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA003ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA004RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA004ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA005RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA005ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA006RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA006ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA007RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA007ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.service.IfaDocClassListService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.netty.util.internal.StringUtil;

/**
 * 画面ID：SUB0501_02-01
 * 画面名：資料種別一覧
 *
 * @author SCSK
 *     2024/02/05 新規作成
 */
@Component(value = "cmpIfaDocClassListService")
public class IfaDocClassListServiceImpL implements IfaDocClassListService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaDocClassListServiceImpL.class);
    
    @Autowired
    private IfaDocClassListDao dao;
    
    /** 必須入力チェックエラー */
    public static final String ERRORS_REQUIRED = "errors.required";
    
    /** 最大桁数チェックエラー */
    public static final String ERRORS_MAXSIZE = "errors.maxSize";
    
    /** 環境依存文字チェックエラー */
    public static final String ERRORS_SPECIALWORDS = "errors.specialWords";
    
    /** 登録存在チェックエラー */
    public static final String ERRORS_INSERTDATAEXIST = "errors.insertDataExist";
    
    /** 必須選択チェックエラー */
    public static final String ERRORS_SELECTED = "errors.selected";
    
    /** 完了メッセージ */
    public static final String INFO_ENDCOMPLETED = "info.endCompleted";
    
    /**
     * アクションID：A001
     * アクション名：カテゴリ一覧
     * Dtoリクエスト：IfaDocClassListA001RequestDto
     * Dtoレスポンス：IfaDocClassListA001ResponseDto
     * model リクエスト：IfaDocClassListA001RequestModel
     * model レスポンス：IfaDocClassListA001ResponseModel
     *
     * @param dtoReq リクエストパラメタ
     * @return レスポンスパラメタ
     * @exceptionException カテゴリ一覧取得処理で例外が発生した場合
     */
    public DataList<IfaDocClassListA001ResponseDto> categoryListA001(IfaDocClassListA001RequestDto dtoReq)
            throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDocClassListServiceImplL.categoryListA001");
        }
        
        //①  SQL001の呼び出し
        // 登録済カテゴリのカテゴリ名を取得する。
        DataList<IfaDocClassListSql001ResponseModel> selSql001Res = this.callSql001();
        
        List<IfaDocClassListA001ResponseDto.InfoCategory> infoCategoryList = 
                new ArrayList<IfaDocClassListA001ResponseDto.InfoCategory>();
        IfaDocClassListA001ResponseDto.InfoCategory category = null;
        for (IfaDocClassListSql001ResponseModel sql001Res : selSql001Res.getDataList()) {
            category = new IfaDocClassListA001ResponseDto.InfoCategory();
            
            category.setT9nInfoCat(sql001Res.getT9nInfoCat());
            category.setT9nName(sql001Res.getT9nName());
            infoCategoryList.add(category);
        }
        
        IfaDocClassListA001ResponseDto dtoRes = new IfaDocClassListA001ResponseDto();
        dtoRes.setInfoCategoryList(infoCategoryList);
        
        List<IfaDocClassListA001ResponseDto> resList = new ArrayList<IfaDocClassListA001ResponseDto>();
        resList.add(dtoRes);
        
        DataList<IfaDocClassListA001ResponseDto> dtoResList = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS,
                "", "");
        return dtoResList;
    }
    
    /**
     * アクションID：A002
     * アクション名：カテゴリ登録確認
     * Dtoリクエスト：IfaDocClassListA002RequestDto
     * model リクエスト：IfaDocClassListSql005RequestModel
     * model レスポンス：IfaDocClassListSql005ResponseModel
     *
     * @param dtoReq リクエストパラメタ
     * @return レスポンスパラメタ
     * @exceptionException カテゴリ登録確認処理で例外が発生した場合
     */
    public DataList<IfaDocClassListA002ResponseDto> categoryRegistrationConfirmA002(
            IfaDocClassListA002RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDocClassListServiceImplL.categoryRegistrationConfirmA002");
        }
        
        //① カテゴリの入力チェックを行う。
        
        //必須入力チェック：カテゴリ＝ "" or NULLである場合エラー（errors.required）を表示する。
        //【メッセージ】
        //"{0}を入力してください。"
        //{0}："カテゴリ"
        if (StringUtil.isNullOrEmpty(dtoReq.getCategory())) {
            List<IfaDocClassListA002ResponseDto> resDtoList = new ArrayList<IfaDocClassListA002ResponseDto>();
            String message = IfaCommonUtil.getMessage(ERRORS_REQUIRED, new String[] { "カテゴリ" });
            DataList<IfaDocClassListA002ResponseDto> dtoRes = 
                    IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_REQUIRED,
                    message);
            return dtoRes;
        }
        
        //最大桁数チェック：カテゴリが127文字よりも長い場合エラー（errors.maxSize）を表示する。
        //【メッセージ】
        //"{0}は{1}桁以下で入力してください。"
        //{0}："カテゴリ"
        //{1}："127"
        if (dtoReq.getCategory().length() > 127) {
            String message = IfaCommonUtil.getMessage(
                    ERRORS_MAXSIZE, new String[] { "カテゴリ", "127" });
            DataList<IfaDocClassListA002ResponseDto> dtoRes = 
                    IfaCommonUtil.createDataList(new ArrayList<IfaDocClassListA002ResponseDto>(), ErrorLevel.FATAL,
                    ERRORS_MAXSIZE, message);
            return dtoRes;
        }
        
        DataList<IfaDocClassListSql005ResponseModel> selSql005Res = this.callSql005(dtoReq.getCategory());
        //登録存在チェック：SQL005を呼び出す。
        //レコードが抽出された場合エラー（errors.insertDataExist）を表示する。
        //【メッセージ】
        //"入力した{0}は既に登録済みです。ご確認ください。"
        //{0}："カテゴリ"
        if (!selSql005Res.getDataList().get(0).getCount().equals("0")) {
            String message = IfaCommonUtil.getMessage(ERRORS_INSERTDATAEXIST, new String[] { "カテゴリ" });
            DataList<IfaDocClassListA002ResponseDto> dtoRes = 
                    IfaCommonUtil.createDataList(new ArrayList<IfaDocClassListA002ResponseDto>(), ErrorLevel.FATAL,
                    ERRORS_INSERTDATAEXIST, message);
            return dtoRes;
        }
        
        IfaDocClassListA002ResponseDto dtoRes = new IfaDocClassListA002ResponseDto();
        dtoRes.setCategory(dtoReq.getCategory());
        
        List<IfaDocClassListA002ResponseDto> resList = new ArrayList<IfaDocClassListA002ResponseDto>();
        resList.add(dtoRes);

        DataList<IfaDocClassListA002ResponseDto> dtoResList = 
                IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, "", "");
        return dtoResList;
    }
    
    /**
     * アクションID：A003
     * アクション名：カテゴリ登録
     * Dtoリクエスト：IfaDocClassListA003RequestDto
     * Dtoレスポンス：IfaDocClassListA003ResponseDto
     * model リクエスト：IfaDocClassListA003RequestModel
     * model レスポンス：IfaDocClassListA003ResponseModel
     *
     * @param dtoReq リクエストパラメタ
     * @return レスポンスパラメタ
     * @exceptionException カテゴリ登録処理で例外が発生した場合
     */
    public DataList<IfaDocClassListA003ResponseDto> categoryRegistrationA003(IfaDocClassListA003RequestDto dtoReq)
            throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDocClassListServiceImplL.categoryRegistrationA003");
        }
        
        //① SQL002の呼び出し
        //カテゴリの新規登録を行う。
        IfaDocClassListSql002RequestModel selSql002Req = new IfaDocClassListSql002RequestModel();
        selSql002Req.setCategory(dtoReq.getCategory());
        dao.insertIfaDocClassListSql002(selSql002Req);
        
        //② SQL001の呼び出し
        //登録済カテゴリのカテゴリ名を取得する。
        DataList<IfaDocClassListSql001ResponseModel> selSql001Res = this.callSql001();
        
        List<IfaDocClassListA003ResponseDto.InfoCategory> infoCategoryList = 
                new ArrayList<IfaDocClassListA003ResponseDto.InfoCategory>();
        IfaDocClassListA003ResponseDto.InfoCategory category = null;
        for (IfaDocClassListSql001ResponseModel sql001Res : selSql001Res.getDataList()) {
            category = new IfaDocClassListA003ResponseDto.InfoCategory();
            
            category.setT9nInfoCat(sql001Res.getT9nInfoCat());
            category.setT9nName(sql001Res.getT9nName());
            infoCategoryList.add(category);
        }
        
        IfaDocClassListA003ResponseDto dtoRes = new IfaDocClassListA003ResponseDto();
        dtoRes.setInfoCategoryList(infoCategoryList);
        
        List<IfaDocClassListA003ResponseDto> resList = new ArrayList<IfaDocClassListA003ResponseDto>();
        resList.add(dtoRes);
        
        String message = IfaCommonUtil.getMessage(INFO_ENDCOMPLETED, new String[] { dtoReq.getCategory() + "の登録" });
        DataList<IfaDocClassListA003ResponseDto> dtoResList = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS,
                INFO_ENDCOMPLETED, message);
        return dtoResList;
    }
    
    /**
     * アクションID：A004
     * アクション名：カテゴリ更新確認
     * Dto リクエスト：IfaDocClassListA004RequestDto
     * model リクエスト：IfaDocClassListA004RequestModel
     * model レスポンス：IfaDocClassListA004ResponseModel
     *
     * @param dtoReq リクエストパラメタ
     * @return レスポンスパラメタ
     * @exceptionException カテゴリ更新確認処理で例外が発生した場合
     */
    public DataList<IfaDocClassListA004ResponseDto> categoryUpdateConfirmA004(
            IfaDocClassListA004RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDocClassListServiceImplL.categoryUpdateConfirmA004");
        }
        List<IfaDocClassListA004ResponseDto> resDtoList = new ArrayList<IfaDocClassListA004ResponseDto>();
        
        //① カテゴリの入力チェックを行う。
        //必須入力チェック：カテゴリ＝ "" or NULLである場合エラー（errors.required）を表示する。
        //【メッセージ】
        //"{0}を入力してください。"
        //{0}："カテゴリ"
        for (IfaDocClassListA004RequestDto.Category dtoReqList : dtoReq.getRegisterCategoryList()) {
            if (StringUtil.isNullOrEmpty(dtoReqList.getInfoCat()) || StringUtil.isNullOrEmpty(dtoReqList.getName())) {
                String message = IfaCommonUtil.getMessage(ERRORS_REQUIRED, new String[] { "カテゴリ" });
                DataList<IfaDocClassListA004ResponseDto> dtoRes = 
                        IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_REQUIRED,
                                message);
                return dtoRes;
            }
        }
        
        //最大桁数チェック：カテゴリが127文字よりも長い場合エラー（errors.maxSize）を表示する。
        //【メッセージ】
        //"{0}は{1}桁以下で入力してください。"
        //{0}："カテゴリ"
        //{1}："127"
        for (IfaDocClassListA004RequestDto.Category dtoReqList : dtoReq.getRegisterCategoryList()) {
            if (dtoReqList.getName().length() > 127) {
                String message = IfaCommonUtil.getMessage(ERRORS_MAXSIZE, new String[] { "カテゴリ", "127" });
                DataList<IfaDocClassListA004ResponseDto> dtoRes = 
                        IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                                ERRORS_MAXSIZE, message);
                return dtoRes;
            }
        }
        
        
        for (IfaDocClassListA004RequestDto.Category dtoReqList : dtoReq.getRegisterCategoryList()) {
            //登録存在チェック：SQL005を呼び出す。
            DataList<IfaDocClassListSql005ResponseModel> selSql005Res = this
                    .callSql005(dtoReqList.getName());
            //レコードが抽出された場合エラー（errors.insertDataExist）を表示する。
            //【メッセージ】
            //"入力した{0}は既に登録済みです。ご確認ください。"
            //{0}："カテゴリ"
            if (!selSql005Res.getDataList().get(0).getCount().equals("0")) {
                String message = IfaCommonUtil.getMessage(ERRORS_INSERTDATAEXIST, new String[] { "カテゴリ" });
                DataList<IfaDocClassListA004ResponseDto> dtoRes = 
                        IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                                ERRORS_INSERTDATAEXIST, message);
                return dtoRes;
            }
        }
        
        List<IfaDocClassListA004ResponseDto.Category> dtoResCategoryList = new ArrayList<IfaDocClassListA004ResponseDto.Category>();
        for (IfaDocClassListA004RequestDto.Category dtoReqList : dtoReq.getRegisterCategoryList()) {
            IfaDocClassListA004ResponseDto.Category dtoResCategory = new IfaDocClassListA004ResponseDto.Category();
            dtoResCategory.setName(dtoReqList.getName());
            dtoResCategoryList.add(dtoResCategory);
        }
        
        IfaDocClassListA004ResponseDto resDto = new IfaDocClassListA004ResponseDto();
        resDto.setRegisterCategoryList(dtoResCategoryList);
        resDtoList.add(resDto);

        DataList<IfaDocClassListA004ResponseDto> dtoResList = 
                IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "", "");
        return dtoResList;
    }
    
    /**
     * アクションID：A005
     * アクション名：カテゴリ更新
     * Dto リクエスト：IfaDocClassListA005RequestDto
     * Dto レスポンス：IfaDocClassListA005ResponseDto
     * model リクエスト：IfaDocClassListA005RequestModel
     * model レスポンス：IfaDocClassListA005ResponseModel
     *
     * @param dtoReq リクエストパラメタ
     * @return レスポンスパラメタ
     * @exceptionException カテゴリ更新処理で例外が発生した場合
     */
    public DataList<IfaDocClassListA005ResponseDto> categoryUpdateA005(IfaDocClassListA005RequestDto dtoReq)
            throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDocClassListServiceImplL.categoryUpdateA005");
        }
        
        
        //① 以下の処理をお知らせカテゴリリスト分繰り返す。
        //SQL003の呼び出し
        //登録済カテゴリのカテゴリ名を更新する。
        this.callSql003(dtoReq.getRegisterCategoryList());
        
        //② SQL001の呼び出し
        //登録済カテゴリのカテゴリ名を取得する。
        DataList<IfaDocClassListSql001ResponseModel> selSql001Res = this.callSql001();
        
        List<IfaDocClassListA005ResponseDto.InfoCategory> infoCategoryList = 
                new ArrayList<IfaDocClassListA005ResponseDto.InfoCategory>();
        IfaDocClassListA005ResponseDto.InfoCategory category = null;
        for (IfaDocClassListSql001ResponseModel sql001Res : selSql001Res.getDataList()) {
            category = new IfaDocClassListA005ResponseDto.InfoCategory();
            
            category.setT9nInfoCat(sql001Res.getT9nInfoCat());
            category.setT9nName(sql001Res.getT9nName());
            infoCategoryList.add(category);
        }
        
        IfaDocClassListA005ResponseDto dtoRes = new IfaDocClassListA005ResponseDto();
        dtoRes.setInfoCategoryList(infoCategoryList);
        
        List<IfaDocClassListA005ResponseDto> resList = new ArrayList<IfaDocClassListA005ResponseDto>();
        resList.add(dtoRes);
        
        String message = IfaCommonUtil.getMessage(INFO_ENDCOMPLETED, new String[] { "更新" });
        DataList<IfaDocClassListA005ResponseDto> dtoResList = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS,
                INFO_ENDCOMPLETED, message);
        return dtoResList;
    }
    
    /**
     * アクションID：A006
     * アクション名：カテゴリ削除確認
     * Dto リクエスト：IfaDocClassListA006RequestDto
     * model リクエスト：IfaDocClassListA006RequestModel
     * model レスポンス：IfaDocClassListA006ResponseModel
     *
     * @param dtoReq リクエストパラメタ
     * @return レスポンスパラメタ
     * @exceptionException カテゴリ削除確認処理で例外が発生した場合
     */
    public DataList<IfaDocClassListA006ResponseDto> categoryDeletionConfirmA006(
            IfaDocClassListA006RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDocClassListServiceImplL.categoryDeletionConfirmA006");
        }
        List<IfaDocClassListA006ResponseDto> resDtoList = new ArrayList<IfaDocClassListA006ResponseDto>();
        
        // ① 削除選択の入力チェックを行う。
        //必須入力チェック：登録済カテゴリ.カテゴリID＝ "" or NULLである場合エラー（errors.selected）を表示する。
        //【メッセージ】
        //"{0}を選択してください。"
        //{0}："カテゴリ"
        for (IfaDocClassListA006RequestDto.Category dtoReqList : dtoReq.getRegisterCategoryList()) {
            if (StringUtil.isNullOrEmpty(dtoReqList.getInfoCat())) {
                String message = IfaCommonUtil.getMessage(ERRORS_SELECTED, new String[] { "カテゴリ" });
                DataList<IfaDocClassListA006ResponseDto> dtoRes = 
                        IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_SELECTED,
                                message);
                return dtoRes;
            }
        }
        
        List<IfaDocClassListA006ResponseDto.Category> dtoResCategoryList = new ArrayList<IfaDocClassListA006ResponseDto.Category>();
        for (IfaDocClassListA006RequestDto.Category dtoReqList : dtoReq.getRegisterCategoryList()) {
            IfaDocClassListA006ResponseDto.Category dtoResCategory = new IfaDocClassListA006ResponseDto.Category();
            dtoResCategory.setName(dtoReqList.getName());
            dtoResCategoryList.add(dtoResCategory);
        }
        
        IfaDocClassListA006ResponseDto resDto = new IfaDocClassListA006ResponseDto();
        resDto.setRegisterCategoryList(dtoResCategoryList);
        resDtoList.add(resDto);

        DataList<IfaDocClassListA006ResponseDto> dtoResList = 
                IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "", "");
        return dtoResList;
    }
    
    /**
     * アクションID：A007
     * アクション名：カテゴリ削除
     * Dto リクエスト：IfaDocClassListA007RequestDto
     * Dto レスポンス：IfaDocClassListA007ResponseDto
     * model リクエスト：IfaDocClassListSql001RequestModel
     *               IfaDocClassListSql004RequestModel
     * model レスポンス：IfaDocClassListSql001ResponseModel
     *               IfaDocClassListSql004ResponseModel
     *
     * @param dtoReq リクエストパラメタ
     * @return レスポンスパラメタ
     * @exceptionException カテゴリ削除処理で例外が発生した場合
     */
    public DataList<IfaDocClassListA007ResponseDto> categoryDeletionA007(IfaDocClassListA007RequestDto dtoReq)
            throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDocClassListServiceImplL.categoryDeletionA007");
        }
        
        //① SQL004の呼び出し
        // 選択したカテゴリを削除する。
        for (IfaDocClassListA007RequestDto.Category dtoReqList : dtoReq.getRegisterCategoryList()) {
            this.callSql004(dtoReqList.getInfoCat());
        }

        //② SQL001の呼び出し
        //登録済カテゴリのカテゴリ名を取得する。
        DataList<IfaDocClassListSql001ResponseModel> selSql001Res = this.callSql001();
        
        List<IfaDocClassListA007ResponseDto.InfoCategory> infoCategoryList = 
                new ArrayList<IfaDocClassListA007ResponseDto.InfoCategory>();
        IfaDocClassListA007ResponseDto.InfoCategory category = null;
        for (IfaDocClassListSql001ResponseModel sql001Res : selSql001Res.getDataList()) {
            category = new IfaDocClassListA007ResponseDto.InfoCategory();
            
            category.setT9nInfoCat(sql001Res.getT9nInfoCat());
            category.setT9nName(sql001Res.getT9nName());
            infoCategoryList.add(category);
        }
        
        IfaDocClassListA007ResponseDto dtoRes = new IfaDocClassListA007ResponseDto();
        dtoRes.setInfoCategoryList(infoCategoryList);
        
        List<IfaDocClassListA007ResponseDto> resList = new ArrayList<IfaDocClassListA007ResponseDto>();
        resList.add(dtoRes);

        String message = IfaCommonUtil.getMessage(INFO_ENDCOMPLETED, new String[] { "削除" });
        DataList<IfaDocClassListA007ResponseDto> dtoResList = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS,
                INFO_ENDCOMPLETED, message);
        return dtoResList;
    }
    
    /**
     * Sql001 の呼び出し
     *
     * @return レスポンスパラメタ
     * @exceptionException カテゴリ一覧取得処理で例外が発生した場合
     */
    private DataList<IfaDocClassListSql001ResponseModel> callSql001() throws Exception {
        
        // SQL001の呼び出し
        // 登録済カテゴリのカテゴリ名を取得する。
        IfaDocClassListSql001RequestModel selSql001Req = new IfaDocClassListSql001RequestModel();
        DataList<IfaDocClassListSql001ResponseModel> selSql001Res = dao.selectIfaDocClassListSql001(selSql001Req);
        return selSql001Res;
    }
    
    /**
     * Sql003 の呼び出し
     *
     * @param registerCategoryList 登録済みカテゴリリスト
     * @return 更新件数
     * @exceptionException カテゴリ一覧取得処理で例外が発生した場合
     */
    private int callSql003(List<IfaDocClassListA005RequestDto.Category> registerCategoryList) throws Exception {
        int updateCnt = 0;
        
        //SQL003の呼び出し
        //登録済カテゴリのカテゴリ名を更新する。
        for (IfaDocClassListA005RequestDto.Category registerCategory : registerCategoryList) {
            
            IfaDocClassListSql003RequestModel selSql003Req = new IfaDocClassListSql003RequestModel();
            selSql003Req.setT9nInfoCat(registerCategory.getInfoCat());
            selSql003Req.setName(registerCategory.getName());
            updateCnt += dao.updateIfaDocClassListSql003(selSql003Req);
        }
        
        return updateCnt;
    }
    
    /**
     * Sql004 の呼び出し
     *
     * @param infoCat カテゴリID
     * @return 更新件数
     * @exceptionException カテゴリ一覧取得処理で例外が発生した場合
     */
    private int callSql004(String infoCat) throws Exception {
        int updateCnt = 0;
        
        //SQL003の呼び出し
        //登録済カテゴリのカテゴリ名を更新する。
        IfaDocClassListSql004RequestModel selSql004Req = new IfaDocClassListSql004RequestModel();
        selSql004Req.setInfoCat(infoCat);
        updateCnt = dao.updateIfaDocClassListSql004(selSql004Req);
        
        return updateCnt;
    }
    
    /**
     * Sql005 の呼び出し
     *
     * @param req リクエストパラメタ
     * @return レスポンスパラメタ
     * @exceptionException 登録存在チェック処理で例外が発生した場合
     */
    private DataList<IfaDocClassListSql005ResponseModel> callSql005(String t9nInfoCat) throws Exception {
        
        IfaDocClassListSql005RequestModel selSql005Req = new IfaDocClassListSql005RequestModel();
        selSql005Req.setCategory(t9nInfoCat);
        DataList<IfaDocClassListSql005ResponseModel> selSql005Res = dao.selectIfaDocClassListSql005(selSql005Req);
        return selSql005Res;
    }
}
