package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.IfaInfoRegisterLookupDao;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterLookupSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterLookupSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterLookupSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterLookupSql002ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterLookupSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterLookupSql004RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterLookupSql005RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterLookupSql006RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterLookupSql006ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterLookupA001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterLookupA001ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterLookupA001ResponseDtoNotificationCategoryList;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterLookupResponseDtoNotificationList;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterLookupA009RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterLookupA009ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterLookupX001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterLookupX001RequestDtoNotificationCategoryList;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterLookupX001ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.service.IfaInfoRegisterLookupService;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0501_01-01
 * 画面名：情報登録照会
 *
 * @author SCSK今井
 2024/05/23 新規作成
 */
@Component(value = "cmpIfaInfoRegisterLookupService")
public class IfaInfoRegisterLookupServiceImpL implements IfaInfoRegisterLookupService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaInfoRegisterLookupServiceImpL.class);
    
    // --------------------------------
    // メッセージ
    // --------------------------------
    /** 正常終了 */
    private static final String SUCCESS_MESSAGE = "正常終了";
    
    /** 検索結果が0件です。\n条件を設定して再度検索して下さい。 */
    private static final String ERRORS_DATA_LIST_NOTFOUND = "errors.dataList.notfound";
    
    /** {0}を完了しました。{0}：削除 */
    private static final String INFO_END_COMPLETED = "info.endCompleted";
    
    // --------------------------------
    // 変数定義
    // --------------------------------   
    /** ID設定値 */
    private static final String T9N_INFO_CAT = "0";
    
    /** 必須フラグ */
    private static final String REQUIRED_FLAG = "1";
    
    /** 機能ID */
    private static final String FUNC_ID = "0";
    
    /** カテゴリID */
    private static final String CAT_ID = "0";
    
    /** 削除メッセージ */
    private static final String CURRENCY_CHECK_VALUE = "削除";
    
    @Autowired
    private IfaInfoRegisterLookupDao dao;
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaInfoRegisterLookupA001RequestDto
     * Dto レスポンス：IfaInfoRegisterLookupA001ResponseDto
     * model リクエスト：IfaInfoRegisterLookupSql005RequestModel
     * model レスポンス：IfaInfoRegisterLookupSql005ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoRegisterLookupA001ResponseDto> initializeA001(IfaInfoRegisterLookupA001RequestDto dtoReq)
            throws Exception {
        
        DataList<IfaInfoRegisterLookupA001ResponseDto> dtoRes = new DataList<IfaInfoRegisterLookupA001ResponseDto>();
        List<IfaInfoRegisterLookupA001ResponseDto> resDto = new ArrayList<IfaInfoRegisterLookupA001ResponseDto>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaInfoRegisterLookupServiceImplL.initializeA001");
        
        // SQL001を実行し、カテゴリ名リストを取得する（リクエスト不要）
        DataList<IfaInfoRegisterLookupSql001ResponseModel> selSql001Res = dao
                .selectIfaInfoRegisterLookupSql001(new IfaInfoRegisterLookupSql001RequestModel());
        
        // SQL001の結果をレスポンスDTOのカテゴリリストに設定する
        List<IfaInfoRegisterLookupA001ResponseDtoNotificationCategoryList> notificationCategoryList = new ArrayList<>();
        for (IfaInfoRegisterLookupSql001ResponseModel model : selSql001Res.getDataList()) {
            IfaInfoRegisterLookupA001ResponseDtoNotificationCategoryList category = new IfaInfoRegisterLookupA001ResponseDtoNotificationCategoryList();
            BeanUtils.copyProperties(category, model);//listでないのでカテゴリで止めた方が良い
            notificationCategoryList.add(category);
        }
        
        // SQL002：全情報一覧
        IfaInfoRegisterLookupSql002RequestModel selSql002Req = new IfaInfoRegisterLookupSql002RequestModel();
        
        // カテゴリリストを初期化し、0行目に値を設定
        List<IfaInfoRegisterLookupSql002RequestModel.NotificationCategory> reqNotificationCategoryList = new ArrayList<>();
        IfaInfoRegisterLookupSql002RequestModel.NotificationCategory category = new IfaInfoRegisterLookupSql002RequestModel.NotificationCategory();
        category.setT9nInfoCat(T9N_INFO_CAT);
        category.setRequiredFlag(REQUIRED_FLAG);
        reqNotificationCategoryList.add(category);
        selSql002Req.setNotificationCategoryList(reqNotificationCategoryList);
        
        // SQL002を実行し、全情報一覧を取得する
        DataList<IfaInfoRegisterLookupSql002ResponseModel> selSql002Res = dao
                .selectIfaInfoRegisterLookupSql002(selSql002Req);
        
        // SQL002の結果をレスポンスDTOのお知らせリストに設定する
        List<IfaInfoRegisterLookupResponseDtoNotificationList> notificationList = new ArrayList<>();
        for (IfaInfoRegisterLookupSql002ResponseModel model : selSql002Res.getDataList()) {
            IfaInfoRegisterLookupResponseDtoNotificationList notification = new IfaInfoRegisterLookupResponseDtoNotificationList();
            BeanUtils.copyProperties(notification, model);
            notificationList.add(notification);
        }
        // 結果をレスポンスDTOに設定する
        IfaInfoRegisterLookupA001ResponseDto responseDto = new IfaInfoRegisterLookupA001ResponseDto();
        responseDto.setNotificationCategoryList(notificationCategoryList);
        responseDto.setNotificationList(notificationList);
        
        resDto.add(responseDto);
        
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, SUCCESS_MESSAGE, null);
        return dtoRes;
    }
    
    /**
     * アクションID：X001
     * アクション名：検索表示
     * Dto リクエスト：IfaInfoRegisterLookupX001RequestDto
     * Dto レスポンス：IfaInfoRegisterLookupX001ResponseDto
     * model リクエスト：IfaInfoRegisterLookupSql002RequestModel
     * model レスポンス：IfaInfoRegisterLookupSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoRegisterLookupX001ResponseDto> searchDisplayX001(IfaInfoRegisterLookupX001RequestDto dtoReq)
            throws Exception {
        
        DataList<IfaInfoRegisterLookupX001ResponseDto> dtoRes = new DataList<IfaInfoRegisterLookupX001ResponseDto>();
        List<IfaInfoRegisterLookupX001ResponseDto> resDto = new ArrayList<IfaInfoRegisterLookupX001ResponseDto>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaInfoRegisterLookupServiceImpl.searchDisplayX001");
        
        // SQL002を実行するためのリクエストモデルを生成
        IfaInfoRegisterLookupSql002RequestModel selSql002Req = new IfaInfoRegisterLookupSql002RequestModel();
        
        // カテゴリリストを初期化し、リクエストに値を設定
        List<IfaInfoRegisterLookupSql002RequestModel.NotificationCategory> reqNotificationCategoryList = new ArrayList<>();
        for (IfaInfoRegisterLookupX001RequestDtoNotificationCategoryList x001Category : dtoReq
                .getNotificationCategoryList()) {
            IfaInfoRegisterLookupSql002RequestModel.NotificationCategory category = new IfaInfoRegisterLookupSql002RequestModel.NotificationCategory();
            category.setT9nInfoCat(x001Category.getT9nInfoCat());
            category.setRequiredFlag(x001Category.getRequiredFlag());
            reqNotificationCategoryList.add(category);
        }
        selSql002Req.setNotificationCategoryList(reqNotificationCategoryList);
            
        // SQL002を実行し、検索結果のリストを取得
        DataList<IfaInfoRegisterLookupSql002ResponseModel> selSql002Res = dao
                .selectIfaInfoRegisterLookupSql002(selSql002Req);
        
        // SQL002の結果をレスポンスDTOのお知らせリストに設定する
        List<IfaInfoRegisterLookupResponseDtoNotificationList> notificationList = new ArrayList<>();
        for (IfaInfoRegisterLookupSql002ResponseModel model : selSql002Res.getDataList()) {
            IfaInfoRegisterLookupResponseDtoNotificationList notification = new IfaInfoRegisterLookupResponseDtoNotificationList();
            BeanUtils.copyProperties(notification, model);
            notificationList.add(notification);
        }
        
        // 結果をレスポンスDTOに設定する
        IfaInfoRegisterLookupX001ResponseDto responseDto = new IfaInfoRegisterLookupX001ResponseDto();
        responseDto.setNotificationList(notificationList);
        
        resDto.add(responseDto);
        
        // 取得件数が0件の場合、エラーメッセージを出して例外をスロー
        if (selSql002Res == null || CollectionUtils.isEmpty(selSql002Res.getDataList())
                || selSql002Res.getDataList().size() == 0) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.INFO, ERRORS_DATA_LIST_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATA_LIST_NOTFOUND));
        }
        
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, SUCCESS_MESSAGE, null);
        return dtoRes;
    }
    
    /**
     * アクションID：A009
     * アクション名：削除
     * Dto リクエスト：IfaInfoRegisterLookupA009RequestDto
     * Dto レスポンス：IfaInfoRegisterLookupA009ResponseDto
     * model リクエスト：IfaInfoRegisterLookupSql005RequestModel
     * model レスポンス：IfaInfoRegisterLookupSql005ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoRegisterLookupA009ResponseDto> deleteA009(IfaInfoRegisterLookupA009RequestDto dtoReq)
            throws Exception {
        
        DataList<IfaInfoRegisterLookupA009ResponseDto> dtoRes = new DataList<IfaInfoRegisterLookupA009ResponseDto>();
        List<IfaInfoRegisterLookupA009ResponseDto> resDto = new ArrayList<IfaInfoRegisterLookupA009ResponseDto>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaInfoRegisterLookupServiceImplL.deleteA009");
        
        // ② 選択した情報の添付ファイルのディレクトリを取得する。SQL006
        // 引数：機能IDは "0"、カテゴリIDは "0" を設定する。
        IfaInfoRegisterLookupSql006RequestModel selSql006Req = new IfaInfoRegisterLookupSql006RequestModel();
        selSql006Req.setFuncId(FUNC_ID);
        selSql006Req.setCatId(CAT_ID);
        DataList<IfaInfoRegisterLookupSql006ResponseModel> selSql006Res = dao
                .selectIfaInfoRegisterLookupSql006(selSql006Req);
        
        // 添付ファイルディレクトリを取得
        String fileDirectory = null;
        if (!selSql006Res.getDataList().isEmpty()) {
            fileDirectory = selSql006Res.getDataList().get(0).getFileDir();
        }
        
        // ③ 選択した情報の添付ファイルを削除する。
        // 以下の添付ファイルが存在する場合、削除する。
        if (fileDirectory != null) {
            if (dtoReq.getAttachFile1() != null && !dtoReq.getAttachFile1().isEmpty()) {
                deleteFile(fileDirectory + dtoReq.getAttachFile1());
            }
            if (dtoReq.getAttachFile2() != null && !dtoReq.getAttachFile2().isEmpty()) {
                deleteFile(fileDirectory + dtoReq.getAttachFile2());
            }
            if (dtoReq.getAttachFile3() != null && !dtoReq.getAttachFile3().isEmpty()) {
                deleteFile(fileDirectory + dtoReq.getAttachFile3());
            }
        }
        
        // ④ 選択した情報を削除し、メッセージを表示する。SQL003, SQL004, SQL005
        // リクエストがあればそれぞれ該当するSQLを実行し、無ければ実行しない。
        
        // お知らせテーブル情報削除
        if (dtoReq.getNotificationId() != null && !dtoReq.getNotificationId().isEmpty()) {
            IfaInfoRegisterLookupSql003RequestModel delSql003Req = new IfaInfoRegisterLookupSql003RequestModel();
            delSql003Req.setNotificationId(Integer.parseInt(dtoReq.getNotificationId()));
            dao.deleteIfaInfoRegisterLookupSql003(delSql003Req);
        }
        
        // お知らせ既読テーブル情報削除
        if (dtoReq.getNotificationId() != null && !dtoReq.getNotificationId().isEmpty()) {
            IfaInfoRegisterLookupSql004RequestModel delSql004Req = new IfaInfoRegisterLookupSql004RequestModel();
            delSql004Req.setNotificationId(Integer.parseInt(dtoReq.getNotificationId()));
            dao.deleteIfaInfoRegisterLookupSql004(delSql004Req);
        }
        
        // お知らせ参照権限テーブル情報削除
        if (dtoReq.getNotificationId() != null && !dtoReq.getNotificationId().isEmpty()) {
            IfaInfoRegisterLookupSql005RequestModel delSql005Req = new IfaInfoRegisterLookupSql005RequestModel();
            delSql005Req.setNotificationId(Integer.parseInt(dtoReq.getNotificationId()));
            dao.deleteIfaInfoRegisterLookupSql005(delSql005Req);
        }
        
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, INFO_END_COMPLETED,
                IfaCommonUtil.getMessage(INFO_END_COMPLETED, new String[] { CURRENCY_CHECK_VALUE }));
        
        return dtoRes;
    }
    
    /**
     * ファイルを削除するヘルパーメソッド
     *
     * @param filePath 削除するファイルのパス
     */
    private void deleteFile(String filePath) {
        
        // 指定されたファイルパスのファイルオブジェクトを作成
        File file = new File(filePath);
        
        // ファイルが存在し、かつそれがファイルである場合
        if (file.exists() && file.isFile()) {
            // ファイルの削除を試みる
            if (!file.delete()) {
                // 削除に失敗した場合に警告をログに出力
                LOGGER.warn("Failed to delete file: " + filePath);
            }
        }
    }
}
