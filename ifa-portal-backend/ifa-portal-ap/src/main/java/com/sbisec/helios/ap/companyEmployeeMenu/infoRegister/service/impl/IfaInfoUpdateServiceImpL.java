package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.IfaInfoUpdateDao;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoUpdateSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoUpdateSql001ResponseModel;

import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoUpdateSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoUpdateSql002ResponseModel;

import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoUpdateSql003RequestModel;

import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoUpdateSql004RequestModel;

import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoUpdateSql005RequestModel;

import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoUpdateSql008RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoUpdateSql008ResponseModel;

import org.apache.commons.beanutils.BeanUtils;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoUpdateA001DtoRequest;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoUpdateA001DtoResponse;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoUpdateDtoResponseNotificationCategoryList;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoUpdateA002DtoRequest;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoUpdateA002DtoResponse;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoUpdateA008aDtoRequest;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoUpdateA008bDtoRequest;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.service.IfaInfoUpdateService;
import com.sbibits.earth.util.StringUtil;

/**
 * 画面ID：SUB0501_01-03_1
 * 画面名：情報更新
 *
 * @author SCSK 大崎
 2024/05/23 新規作成
 */

@Component(value = "cmpIfaInfoUpdateService")
public class IfaInfoUpdateServiceImpL implements IfaInfoUpdateService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaInfoUpdateServiceImpL.class);
    
    // --------------------------------
    // エラーメッセージ
    // --------------------------------

    /** "選択されたエントリーを見つけることができませんでした。" */
    
    private static final String ERRORS_EMP_NOTIFICATION_NOT_FOUND = "errors.emp.notification.notFound";

    // --------------------------------
    // 定数
    // --------------------------------

    /** アップロード先 機能ID */
    private static final String FUNC_ID = "0";
    
    /** アップロード先 カテゴリID */
    private static final String CAT_ID = "0";

    /** 参照範囲 （2:権限担当者）*/
    private static final String REFERENCE_CONDITION_PRIV = "2";

    /** 参照範囲 （3:個別担当者）*/
    private static final String REFERENCE_CONDITION_INDIVIDUAL = "3";

    @Autowired
    private IfaInfoUpdateDao dao;

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaInfoUpdateA001DtoRequest
     * Dto レスポンス：IfaInfoUpdateA001DtoResponse
     * model リクエスト：IfaInfoUpdateSql007RequestModel
     * model レスポンス：IfaInfoUpdateSql007ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoUpdateA001DtoResponse> initializeA001(IfaInfoUpdateA001DtoRequest dtoReq)
            throws Exception {
        IfaInfoUpdateA001DtoResponse response = new IfaInfoUpdateA001DtoResponse();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaInfoUpdateServiceImplL.initializeA001");
        }
        
        // --------------------------------
        // 情報明細取得（SQL002）
        // --------------------------------
        IfaInfoUpdateSql002RequestModel selSql002Req = new IfaInfoUpdateSql002RequestModel(dtoReq.getNotificationId());
        DataList<IfaInfoUpdateSql002ResponseModel> selSql002Res = dao.selectIfaInfoUpdateSql002(selSql002Req);
        
        // SQL002の取得件数が0件の場合、エラー返却。
        if (selSql002Res == null || selSql002Res.getDataList().size() == 0) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_EMP_NOTIFICATION_NOT_FOUND,
                    IfaCommonUtil.getMessage(ERRORS_EMP_NOTIFICATION_NOT_FOUND));
        }
        BeanUtils.copyProperties(response, selSql002Res.getDataList().get(0));

        // --------------------------------
        // 資料種別一覧（SQL001）
        // --------------------------------
        response.setNotificationCategoryList(this.getNotificationCategoryList()); 

        return IfaCommonUtil.createDataList(List.of(response), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
    }

    /**
     * アクションID：A002
     * アクション名：リセット
     * Dto リクエスト：IfaInfoUpdateA002DtoRequest
     * Dto レスポンス：IfaInfoUpdateA002DtoResponse
     * model リクエスト：IfaInfoUpdateSql007RequestModel
     * model レスポンス：IfaInfoUpdateSql007ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoUpdateA002DtoResponse> resetA002(IfaInfoUpdateA002DtoRequest dtoReq)
            throws Exception {
        IfaInfoUpdateA002DtoResponse response = new IfaInfoUpdateA002DtoResponse();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaInfoUpdateServiceImplL.resetA002");
        }

        // --------------------------------
        // 情報明細取得（SQL002）
        // --------------------------------
        IfaInfoUpdateSql002RequestModel selSql002Req = new IfaInfoUpdateSql002RequestModel(dtoReq.getNotificationId());
        DataList<IfaInfoUpdateSql002ResponseModel> selSql002Res = dao.selectIfaInfoUpdateSql002(selSql002Req);
        
        // SQL002の取得件数が0件の場合、エラー返却。
        if (selSql002Res == null || selSql002Res.getDataList().size() == 0) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_EMP_NOTIFICATION_NOT_FOUND,
                    IfaCommonUtil.getMessage(ERRORS_EMP_NOTIFICATION_NOT_FOUND));
        }
        BeanUtils.copyProperties(response, selSql002Res.getDataList().get(0));

        // --------------------------------
        // 資料種別一覧（SQL001）
        // --------------------------------
        response.setNotificationCategoryList(this.getNotificationCategoryList()); 

        return IfaCommonUtil.createDataList(List.of(response), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);

    }
    
    /**
     * アクションID：A008a
     * アクション名：更新
     *
     * @param dtoReq リクエスト
     * @return SQL008.ファイルディレクトリ {@code DataList<String> }
     * @exception exception システムエラー
     */
    public DataList<String> updateA008a(IfaInfoUpdateA008aDtoRequest dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaInfoUpdateServiceImplL.updateA008a");
        }
        
        // --------------------------------
        // 登録ファイルディレクトリ取得（SQL008）
        // --------------------------------
        IfaInfoUpdateSql008RequestModel selSql008Req = new IfaInfoUpdateSql008RequestModel(FUNC_ID, CAT_ID);
        DataList<IfaInfoUpdateSql008ResponseModel> selSql008Res = dao.selectIfaInfoUpdateSql008(selSql008Req);

        // ファイルディレクトリを返却
        String ret = selSql008Res.getDataList().get(0).getFileDirectory();
        return IfaCommonUtil.createDataList(List.of(ret), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
    }
    
    /**
    * SQL002.添付ファイル名を取得
    *
    * @param notificationId {@code String }
    * @return 添付ファイル名リスト{@code DataList<String> }
    * @throws Exception システムエラー
    */
    public DataList<String> getAttachFileA008b(String notificationId)
            throws Exception {
        // --------------------------------
        // 情報明細取得（SQL002）
        // --------------------------------
        IfaInfoUpdateSql002RequestModel selSql002Req = new IfaInfoUpdateSql002RequestModel(notificationId);
        DataList<IfaInfoUpdateSql002ResponseModel> selSql002Res = dao.selectIfaInfoUpdateSql002(selSql002Req);
        
        List<String> ret = new ArrayList<>();
        if (selSql002Res != null && selSql002Res.getDataList().size() != 0) {
            // ファイル削除のため、SQL002.添付ファイル名を返却
            IfaInfoUpdateSql002ResponseModel model = selSql002Res.getDataList().get(0);
            ret.add(model.getAttachFileName1() != null ? model.getAttachFileName1() : "");
            ret.add(model.getAttachFileName2() != null ? model.getAttachFileName2() : "");
            ret.add(model.getAttachFileName3() != null ? model.getAttachFileName3() : "");
        }
        return IfaCommonUtil.createDataList(ret, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
    }
    /**
     * アクションID：A008b
     * アクション名：更新
    *
    * @param dtoReq リクエスト
    * @return {@code DataList<String> }
    * @throws Exception システムエラー
    */
    @Transactional(rollbackFor = Throwable.class)
    public DataList<String> updateA008b(IfaInfoUpdateA008bDtoRequest dtoReq)
            throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaInfoUpdateServiceImplL.updateA008b");
        }

        // --------------------------------
        // 新お知らせテーブル更新（SQL003）
        // --------------------------------
        IfaInfoUpdateSql003RequestModel updSql003Req = new IfaInfoUpdateSql003RequestModel();
        BeanUtils.copyProperties(updSql003Req, dtoReq);

        // SQL003リクエスト.登録ファイル1（ファイル名）
        if (!StringUtil.isNullOrEmpty(dtoReq.getRegisterFileName1())) {
            // 登録ファイル1（ファイル名）が新しくアップロードしたファイルの場合、リネームしたファイル名を設定する。
            updSql003Req.setAttachFile1(dtoReq.getRegisterFileName1());
        } else if (!StringUtil.isNullOrEmpty(dtoReq.getFileDelete1())) {
            // 登録ファイル1（削除）が空でない場合、更新しない。
            updSql003Req.setAttachFile1(null);
        } else {
            // 上記以外の場合、空を設定する。
            updSql003Req.setAttachFile1("");
        }

        // SQL003リクエスト.登録ファイル2（ファイル名）
        if (!StringUtil.isNullOrEmpty(dtoReq.getRegisterFileName2())) {
            updSql003Req.setAttachFile2(dtoReq.getRegisterFileName2());
        } else if (!StringUtil.isNullOrEmpty(dtoReq.getFileDelete2())) {
            updSql003Req.setAttachFile2(null);
        } else {
            updSql003Req.setAttachFile2("");
        }

        // SQL003リクエスト.登録ファイル3（ファイル名）
        if (!StringUtil.isNullOrEmpty(dtoReq.getRegisterFileName3())) {
            updSql003Req.setAttachFile3(dtoReq.getRegisterFileName3());
        } else if (!StringUtil.isNullOrEmpty(dtoReq.getFileDelete3())) {
            updSql003Req.setAttachFile3(null);
        }  else {
            updSql003Req.setAttachFile3("");
        }

        // SQL003リクエスト.カテゴリIDリスト
        // 大カテゴリ
        // 必須フラグのついているカテゴリID
        String cat1 = dtoReq.getCategoryIdList().stream()//
                .filter(c -> StringUtils.equals("1", c.getRequiredFlag())) //
                .findFirst()//
                .map(c -> c.getCategoryId()) //
                .orElseThrow();
        updSql003Req.setDocumentKindInput1(cat1);
        
        updSql003Req.setAttachFileComment1(dtoReq.getFileComment1()); // SQL003リクエスト.登録ファイル1（コメント）
        updSql003Req.setAttachFileComment2(dtoReq.getFileComment2()); // SQL003リクエスト.登録ファイル2（コメント）
        updSql003Req.setAttachFileComment3(dtoReq.getFileComment3()); // SQL003リクエスト.登録ファイル3（コメント）
        updSql003Req.setCorReferenceCondition(dtoReq.getViewerSetting()); // SQL003リクエスト.閲覧者
        updSql003Req.setCorReadFlg(dtoReq.getReadManage()); // SQL003リクエスト.既読管理
        
        String userId = IfaCommonUtil.getUserAccount().getUserId();
        updSql003Req.setUpdateUser(userId); // SQL003リクエスト.更新者
        
        dao.updateIfaInfoUpdateSql003(updSql003Req);

        // --------------------------------
        // 参照範囲に基づくレコード登録
        // --------------------------------
        // 参照範囲が（リクエスト.閲覧者）　"2"　の場合（2:権限担当者）、参照権限に仲介業者権限コードを登録する。（SQL004）
        // ただし、更新データがない場合はSQLを実行しない。
        if (
                StringUtils.equals(dtoReq.getViewerSetting(), REFERENCE_CONDITION_PRIV)
                && CollectionUtils.isNotEmpty(dtoReq.getNotificationReferenceAuthorityList())
        ) {
            IfaInfoUpdateSql004RequestModel insSql004Req = new IfaInfoUpdateSql004RequestModel();
            insSql004Req.setNotificationId(dtoReq.getNotificationId());
            insSql004Req.setNotificationReferenceAuthorityList(dtoReq.getNotificationReferenceAuthorityList());
            insSql004Req.setCreateBy(userId);
            insSql004Req.setUpdateUser(userId);

            dao.insertIfaInfoUpdateSql004(insSql004Req);
        
        // 参照範囲（リクエスト.閲覧者）が　"3"　の場合（3:個別担当者）
        } else if (StringUtils.equals(dtoReq.getViewerSetting(), REFERENCE_CONDITION_INDIVIDUAL)) {
            // 個別担当者リストが空（閲覧者の追加がない）ではない場合
            // 既読にログインIDを登録する。（SQL005）
            if (dtoReq.getIndividualRepList() != null && !dtoReq.getIndividualRepList().isEmpty()) {
                IfaInfoUpdateSql005RequestModel insSql005Req = new IfaInfoUpdateSql005RequestModel();
                insSql005Req.setNotificationId(dtoReq.getNotificationId());
                insSql005Req.setIndividualRepList(dtoReq.getIndividualRepList());

                dao.insertIfaInfoUpdateSql005(insSql005Req);
            }
        }

        return IfaCommonUtil.createDataList(null, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
    }

    /**
     * お知らせカテゴリリストの取得
    *
    * @return お知らせカテゴリリスト {@code List<IfaInfoUpdateDtoResponseNotificationCategoryList> }
    * @throws Exception システムエラー
    */
    private List<IfaInfoUpdateDtoResponseNotificationCategoryList> getNotificationCategoryList() throws Exception {
        IfaInfoUpdateSql001RequestModel selSql001Req = new IfaInfoUpdateSql001RequestModel();
        DataList<IfaInfoUpdateSql001ResponseModel> selSql001Res = dao.selectIfaInfoUpdateSql001(selSql001Req);

        // レスポンス.お知らせカテゴリリストをセット
        List<IfaInfoUpdateDtoResponseNotificationCategoryList> notificationCategoryList = new ArrayList<>();
        for (IfaInfoUpdateSql001ResponseModel sqlRes : selSql001Res.getDataList()) {
            IfaInfoUpdateDtoResponseNotificationCategoryList notificationCategory = new IfaInfoUpdateDtoResponseNotificationCategoryList();
            BeanUtils.copyProperties(notificationCategory, sqlRes);
            notificationCategoryList.add(notificationCategory);
        }

        return notificationCategoryList; 
    }
}
