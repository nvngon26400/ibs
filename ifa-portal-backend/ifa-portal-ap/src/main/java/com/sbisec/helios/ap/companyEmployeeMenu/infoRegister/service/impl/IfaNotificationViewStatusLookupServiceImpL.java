package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.IfaNotificationViewStatusLookupDao;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql002ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql003ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql004RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql004ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql005RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql006RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql007RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaNotificationViewStatusLookupA001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaNotificationViewStatusLookupA001ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaNotificationViewStatusLookupA001ResponseDtoNotificationList;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaNotificationViewStatusLookupA001ResponseDtoNotificationViewStatusLookupList;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaNotificationViewStatusLookupA003RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaNotificationViewStatusLookupA003ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaNotificationViewStatusLookupA005RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaNotificationViewStatusLookupA005ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaNotificationViewStatusLookupA006RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaNotificationViewStatusLookupA006ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaNotificationViewStatusLookupCsvItems;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.service.IfaNotificationViewStatusLookupService;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.util.IfaNotificationViewStatusLookupManagerCsvOut;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0501_01-05
 * 画面名：お知らせ閲覧状況照会
 *
 */
@Component(value = "cmpIfaNotificationViewStatusLookupService")
public class IfaNotificationViewStatusLookupServiceImpL implements IfaNotificationViewStatusLookupService {
    
    /** DB登録、更新エラー */
    private static final String ERRORS_CONFIRM_EXISTS = "errors.Confirm.exist";
    
    /** DB登録完了 */
    private static final String INFO_ENDCOMPLETED = "info.endCompleted";
    
    /** DB登録完了 */
    private static final String ERRORS_SERVERERROR = "errors.serverError";
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaNotificationViewStatusLookupServiceImpL.class);
    
    @Autowired
    private IfaNotificationViewStatusLookupDao dao;

    @Autowired
    private ComplianceService complianceService;

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaNotificationViewStatusLookupA001RequestDto
     * Dto レスポンス：IfaNotificationViewStatusLookupA001ResponseDto
     * model リクエスト：IfaNotificationViewStatusLookupA001RequestModel
     * model レスポンス：IfaNotificationViewStatusLookupA001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaNotificationViewStatusLookupA001ResponseDto> initializeA001(
            IfaNotificationViewStatusLookupA001RequestDto dtoReq) throws Exception {
        
        DataList<IfaNotificationViewStatusLookupA001ResponseDto> dtoRes = new DataList<IfaNotificationViewStatusLookupA001ResponseDto>();
        List<IfaNotificationViewStatusLookupA001ResponseDto> resDtoList = new ArrayList<IfaNotificationViewStatusLookupA001ResponseDto>();
        IfaNotificationViewStatusLookupA001ResponseDto resDto = new IfaNotificationViewStatusLookupA001ResponseDto();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaNotificationViewStatusLookupServiceImplL.initializeA001");
        }
        
        // ①お知らせ閲覧状況の情報を取得する。
        // 参照範囲が「1：全担当者」の場合
        if (StringUtils.equals(dtoReq.getCorReferenceCondition(), "1")) {
            
            IfaNotificationViewStatusLookupSql001RequestModel selSql001Req = new IfaNotificationViewStatusLookupSql001RequestModel();
            selSql001Req.setT5nInfoId(dtoReq.getNotificationId());
            
            // SQL001実行
            DataList<IfaNotificationViewStatusLookupSql001ResponseModel> selSql001Res = dao
                    .selectIfaNotificationViewStatusLookupSql001(selSql001Req);
            
            List<IfaNotificationViewStatusLookupSql001ResponseModel> selSql001ResList = selSql001Res.getDataList();
            
            // ②お知らせ閲覧状況の情報を画面に表示する
            // 下記の条件で並び替えて表示する
            // SQL***.仲介業者支店コード昇順 > SQL***.仲介業者担当者コード昇順
            
            selSql001ResList.sort(Comparator
                    .comparing(IfaNotificationViewStatusLookupSql001ResponseModel::getSubBrokerId,
                            Comparator.nullsLast(Comparator.naturalOrder()))
                    .thenComparing(IfaNotificationViewStatusLookupSql001ResponseModel::getEmployeeId,
                            Comparator.nullsLast(Comparator.naturalOrder())));
            
            // SQL001の結果をレスポンスDTOのお知らせリストに設定する
            List<IfaNotificationViewStatusLookupA001ResponseDtoNotificationViewStatusLookupList> notificationViewStatusLookupList = new ArrayList<>();
            for (IfaNotificationViewStatusLookupSql001ResponseModel model : selSql001Res.getDataList()) {
                IfaNotificationViewStatusLookupA001ResponseDtoNotificationViewStatusLookupList notificationViewStatusLookup = new IfaNotificationViewStatusLookupA001ResponseDtoNotificationViewStatusLookupList();
                BeanUtils.copyProperties(notificationViewStatusLookup, model);
                notificationViewStatusLookupList.add(notificationViewStatusLookup);
            }
            
            // 結果をレスポンスDTOに設定する
            resDto.setNotificationViewStatusLookupList(notificationViewStatusLookupList);
            
            // 参照範囲が「2：権限担当者」の場合
        } else if (StringUtils.equals(dtoReq.getCorReferenceCondition(), "2")) {
            
            IfaNotificationViewStatusLookupSql002RequestModel selSql002Req = new IfaNotificationViewStatusLookupSql002RequestModel();
            selSql002Req.setT5nInfoId(dtoReq.getNotificationId());
            
            DataList<IfaNotificationViewStatusLookupSql002ResponseModel> selSql002Res = dao
                    .selectIfaNotificationViewStatusLookupSql002(selSql002Req);
            
            List<IfaNotificationViewStatusLookupSql002ResponseModel> selSql002ResList = selSql002Res.getDataList();
            
            // ②お知らせ閲覧状況の情報を画面に表示する
            // 下記の条件で並び替えて表示する
            // SQL***.仲介業者支店コード昇順 > SQL***.仲介業者担当者コード昇順
            selSql002ResList.sort(Comparator
                    .comparing(IfaNotificationViewStatusLookupSql002ResponseModel::getSubBrokerId,
                            Comparator.nullsLast(Comparator.naturalOrder()))
                    .thenComparing(IfaNotificationViewStatusLookupSql002ResponseModel::getEmployeeId,
                            Comparator.nullsLast(Comparator.naturalOrder())));
            
            // SQL002の結果をレスポンスDTOのお知らせリストに設定する
            List<IfaNotificationViewStatusLookupA001ResponseDtoNotificationViewStatusLookupList> notificationViewStatusLookupList = new ArrayList<>();
            for (IfaNotificationViewStatusLookupSql002ResponseModel model : selSql002Res.getDataList()) {
                IfaNotificationViewStatusLookupA001ResponseDtoNotificationViewStatusLookupList notificationViewStatusLookup = new IfaNotificationViewStatusLookupA001ResponseDtoNotificationViewStatusLookupList();
                BeanUtils.copyProperties(notificationViewStatusLookup, model);
                notificationViewStatusLookupList.add(notificationViewStatusLookup);
            }
            
            // 結果をレスポンスDTOに設定する
            resDto.setNotificationViewStatusLookupList(notificationViewStatusLookupList);
            
            // 参照範囲が「3：個別担当者」の場合
        } else if (StringUtils.equals(dtoReq.getCorReferenceCondition(), "3")) {
            
            IfaNotificationViewStatusLookupSql003RequestModel selSql003Req = new IfaNotificationViewStatusLookupSql003RequestModel();
            selSql003Req.setT5nInfoId(dtoReq.getNotificationId());
            
            DataList<IfaNotificationViewStatusLookupSql003ResponseModel> selSql003Res = dao
                    .selectIfaNotificationViewStatusLookupSql003(selSql003Req);
            
            List<IfaNotificationViewStatusLookupSql003ResponseModel> selSql003ResList = selSql003Res.getDataList();
            
            // ②お知らせ閲覧状況の情報を画面に表示する
            // 下記の条件で並び替えて表示する
            // SQL***.仲介業者支店コード昇順 > SQL***.仲介業者担当者コード昇順
            selSql003ResList.sort(Comparator
                    .comparing(IfaNotificationViewStatusLookupSql003ResponseModel::getSubBrokerId,
                            Comparator.nullsLast(Comparator.naturalOrder()))
                    .thenComparing(IfaNotificationViewStatusLookupSql003ResponseModel::getEmployeeId,
                            Comparator.nullsLast(Comparator.naturalOrder())));
            
            // SQL003の結果をレスポンスDTOのお知らせ閲覧状況照会リストに設定する
            List<IfaNotificationViewStatusLookupA001ResponseDtoNotificationViewStatusLookupList> notificationViewStatusLookupList = new ArrayList<>();
            for (IfaNotificationViewStatusLookupSql003ResponseModel model : selSql003Res.getDataList()) {
                IfaNotificationViewStatusLookupA001ResponseDtoNotificationViewStatusLookupList notificationViewStatusLookup = new IfaNotificationViewStatusLookupA001ResponseDtoNotificationViewStatusLookupList();
                BeanUtils.copyProperties(notificationViewStatusLookup, model);
                notificationViewStatusLookupList.add(notificationViewStatusLookup);
            }
            
            // 結果をレスポンスDTOに設定する
            resDto.setNotificationViewStatusLookupList(notificationViewStatusLookupList);
            
        }
        
        // レスポンスセット
        IfaNotificationViewStatusLookupA001ResponseDtoNotificationList notificationList = new IfaNotificationViewStatusLookupA001ResponseDtoNotificationList();
        // お知らせID（数字）
        notificationList.setNotificationId(dtoReq.getNotificationId());
        // 参照範囲
        notificationList.setCorReferenceCondition(dtoReq.getCorReferenceCondition());
        // タイトル
        notificationList.setTitle(dtoReq.getTitle());
        // 登録日時
        notificationList.setRegisterDayTime(dtoReq.getRegisterDayTime());
        
        resDto.setNotificationList(List.of(notificationList));
        
        resDtoList.add(resDto);
        
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "", "");
        return dtoRes;
    }
    
    /**
     * アクションID：A003
     * アクション名：代理開封OK
     * Dto リクエスト：IfaNotificationViewStatusLookupA003RequestDto
     * Dto レスポンス：IfaNotificationViewStatusLookupA003ResponseDto
     * model リクエスト：IfaNotificationViewStatusLookupA003RequestModel
     * model レスポンス：IfaNotificationViewStatusLookupA003ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaNotificationViewStatusLookupA003ResponseDto> proxyOpeningOkA003(
            IfaNotificationViewStatusLookupA003RequestDto dtoReq) throws Exception {
        
        DataList<IfaNotificationViewStatusLookupA003ResponseDto> dtoRes = new DataList<IfaNotificationViewStatusLookupA003ResponseDto>();
        List<IfaNotificationViewStatusLookupA003ResponseDto> dtoResList = new ArrayList<IfaNotificationViewStatusLookupA003ResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaNotificationViewStatusLookupServiceImplL.proxyOpeningOkA003");
        }
        
        IfaNotificationViewStatusLookupSql004RequestModel selSql004Req = new IfaNotificationViewStatusLookupSql004RequestModel();
        
        selSql004Req.setT5nInfoId(dtoReq.getNotificationId());
        selSql004Req.setLoginId(dtoReq.getLoginId());
        
        // ①ログイン者に紐づく既読情報（件数）を取得する。
        DataList<IfaNotificationViewStatusLookupSql004ResponseModel> selSql004Res = dao
                .selectIfaNotificationViewStatusLookupSql004(selSql004Req);
        
        // ②既読情報の有無より情報の追加もしくは情報の更新をする。
        //  お知らせ既読の件数（SQL004.COUNT）= "0"件の場合：既読情報を追加する
        if (selSql004Res.getDataList().get(0).getCount().equals("0")) {
            IfaNotificationViewStatusLookupSql005RequestModel insSql005Req = new IfaNotificationViewStatusLookupSql005RequestModel();
            
            insSql005Req.setT5nInfoId(dtoReq.getNotificationId());
            insSql005Req.setLoginId(dtoReq.getLoginId());
            
            // SQL005実行
            int insSql005Res = 0;
            try {
                insSql005Res = dao.insertIfaNotificationViewStatusLookupSql005(insSql005Req);
            } catch (Exception e) {
                insSql005Res = 0;
            }
            
            // 登録件数＝"0件"の場合、SQL実行エラーの場合、処理を終了する、
            if (insSql005Res == 0) {
                // レスポンスにエラーを設定する
                dtoRes = IfaCommonUtil.createDataList(dtoResList, ErrorLevel.FATAL, ERRORS_CONFIRM_EXISTS,
                        IfaCommonUtil.getMessage(ERRORS_CONFIRM_EXISTS));
                return dtoRes;
            }
            
            //  お知らせ既読の件数（SQL004.COUNT）= "1"件以上の場合：既読情報を更新する
        } else if (!selSql004Res.getDataList().get(0).getCount().equals("0")) {
            
            IfaNotificationViewStatusLookupSql006RequestModel updSql006Req = new IfaNotificationViewStatusLookupSql006RequestModel();
            
            updSql006Req.setT5nInfoId(dtoReq.getNotificationId());
            updSql006Req.setLoginId(dtoReq.getLoginId());
            
            // SQL006実行
            int updSql006Res = 0;
            try {
                updSql006Res = dao.updateIfaNotificationViewStatusLookupSql006(updSql006Req);
            } catch (Exception e) {
                updSql006Res = 0;
            }
            
            // 登録件数＝"0件"の場合、SQL実行エラーの場合、処理を終了する、
            if (updSql006Res == 0) {
                // レスポンスにエラーを設定する
                dtoRes = IfaCommonUtil.createDataList(dtoResList, ErrorLevel.FATAL, ERRORS_CONFIRM_EXISTS,
                        IfaCommonUtil.getMessage(ERRORS_CONFIRM_EXISTS));
                return dtoRes;
            }
        }
        // 登録件数＝"1件"以上：次の処理へ
        // レスポンスを返す。
        dtoRes = IfaCommonUtil.createDataList(dtoResList, ErrorLevel.SUCCESS, INFO_ENDCOMPLETED,
                IfaCommonUtil.getMessage(INFO_ENDCOMPLETED, new String[] { "開封" }));
        return dtoRes;
    }
    
    /**
     * アクションID：A005
     * アクション名：削除OK
     * Dto リクエスト：IfaNotificationViewStatusLookupA005RequestDto
     * Dto レスポンス：IfaNotificationViewStatusLookupA005ResponseDto
     * model リクエスト：IfaNotificationViewStatusLookupSql007RequestModel
     * model レスポンス：IfaNotificationViewStatusLookupSql007ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaNotificationViewStatusLookupA005ResponseDto> deleteOkA005(
            IfaNotificationViewStatusLookupA005RequestDto dtoReq) throws Exception {
        
        DataList<IfaNotificationViewStatusLookupA005ResponseDto> dtoRes = new DataList<IfaNotificationViewStatusLookupA005ResponseDto>();
        
        List<IfaNotificationViewStatusLookupA005ResponseDto> dtoResList = new ArrayList<IfaNotificationViewStatusLookupA005ResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaNotificationViewStatusLookupServiceImplL.deleteOkA005");
        }
        
        IfaNotificationViewStatusLookupSql007RequestModel delSql007Req = new IfaNotificationViewStatusLookupSql007RequestModel();
        
        delSql007Req.setT5nInfoId(dtoReq.getNotificationId());
        delSql007Req.setLoginId(dtoReq.getLoginId());
        
        // ①既読情報を削除する。
        // SQL007実行
        try {
            dao.deleteIfaNotificationViewStatusLookupSql007(delSql007Req);
        } catch (Exception e) {
            // レスポンスにエラーを設定する
            dtoRes = IfaCommonUtil.createDataList(dtoResList, ErrorLevel.FATAL, ERRORS_SERVERERROR,
                    IfaCommonUtil.getMessage(ERRORS_SERVERERROR));
            return dtoRes;
        }
        
        dtoRes = IfaCommonUtil.createDataList(dtoResList, ErrorLevel.INFO, INFO_ENDCOMPLETED,
                IfaCommonUtil.getMessage(INFO_ENDCOMPLETED, new String[] { "削除" }));
        return dtoRes;
    }

    /**
     * アクションID：A006
     * アクション名：CSV出力
     * Dto リクエスト：IfaNotificationViewStatusLookupA006RequestDto
     * Dto レスポンス：IfaNotificationViewStatusLookupA006ResponseDto
     * model リクエスト：IfaNotificationViewStatusLookupSql001RequestModel、IfaNotificationViewStatusLookupSql002RequestModel、IfaNotificationViewStatusLookupSql003RequestModel
     * model レスポンス：IfaNotificationViewStatusLookupSql001ResponseModel、IfaNotificationViewStatusLookupSql002ResponseModel、IfaNotificationViewStatusLookupSql003ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaNotificationViewStatusLookupA006ResponseDto> csvDownloadA006a(
            IfaNotificationViewStatusLookupA006RequestDto dtoReq, String fwSessionId) throws Exception {

        DataList<IfaNotificationViewStatusLookupA006ResponseDto> dtoRes = new DataList<IfaNotificationViewStatusLookupA006ResponseDto>();

        IfaNotificationViewStatusLookupManagerCsvOut csvOut = new IfaNotificationViewStatusLookupManagerCsvOut(complianceService);
        DataList<IfaNotificationViewStatusLookupCsvItems> exportList = new DataList<IfaNotificationViewStatusLookupCsvItems>();
        List<IfaNotificationViewStatusLookupCsvItems> csvItemsList = new ArrayList<>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaNotificationViewStatusLookupServiceImplL.csvDownloadA006a");
        }
        
        // ①お知らせ閲覧状況の情報を取得する。
        // 参照範囲が「1：全担当者」の場合
        if (StringUtils.equals(dtoReq.getCorReferenceCondition(), "1")) {
            
            IfaNotificationViewStatusLookupSql001RequestModel selSql001Req = new IfaNotificationViewStatusLookupSql001RequestModel();
            selSql001Req.setT5nInfoId(dtoReq.getNotificationId());
            
            // SQL001実行
            DataList<IfaNotificationViewStatusLookupSql001ResponseModel> selSql001Res = dao
                    .selectIfaNotificationViewStatusLookupSql001(selSql001Req);
            
            List<IfaNotificationViewStatusLookupSql001ResponseModel> selSql001ResList = selSql001Res.getDataList();
            
            // ②お知らせ閲覧状況の情報をCSVに出力する
            // 下記の条件で並び替えて出力する
            // SQL***.仲介業者支店コード昇順 > SQL***.仲介業者担当者コード昇順
            
            selSql001ResList.sort(Comparator
                    .comparing(IfaNotificationViewStatusLookupSql001ResponseModel::getSubBrokerId,
                            Comparator.nullsLast(Comparator.naturalOrder()))
                    .thenComparing(IfaNotificationViewStatusLookupSql001ResponseModel::getEmployeeId,
                            Comparator.nullsLast(Comparator.naturalOrder())));
            
            // SQL001の結果をCSVファイルに設定する
            for (IfaNotificationViewStatusLookupSql001ResponseModel model : selSql001Res.getDataList()) {
                IfaNotificationViewStatusLookupCsvItems csvItems = new IfaNotificationViewStatusLookupCsvItems();
                csvItems.setBranchName(model.getBranchName());
                csvItems.setEmployeeName(model.getEmployeeName());
                csvItems.setT5nReadFlg(model.getT5nReadFlg());
                csvItems.setReadDate((model.getReadDate()));
                csvItemsList.add(csvItems);
            }
            
            exportList.setDataList((csvItemsList));
            
            // 参照範囲が「2：権限担当者」の場合
        } else if (StringUtils.equals(dtoReq.getCorReferenceCondition(), "2")) {
            
            IfaNotificationViewStatusLookupSql002RequestModel selSql002Req = new IfaNotificationViewStatusLookupSql002RequestModel();
            selSql002Req.setT5nInfoId(dtoReq.getNotificationId());
            
            DataList<IfaNotificationViewStatusLookupSql002ResponseModel> selSql002Res = dao
                    .selectIfaNotificationViewStatusLookupSql002(selSql002Req);
            
            List<IfaNotificationViewStatusLookupSql002ResponseModel> selSql002ResList = selSql002Res.getDataList();
            
            // ②お知らせ閲覧状況の情報をCSVに出力する
            // 下記の条件で並び替えて出力する
            // SQL***.仲介業者支店コード昇順 > SQL***.仲介業者担当者コード昇順
            selSql002ResList.sort(Comparator
                    .comparing(IfaNotificationViewStatusLookupSql002ResponseModel::getSubBrokerId,
                            Comparator.nullsLast(Comparator.naturalOrder()))
                    .thenComparing(IfaNotificationViewStatusLookupSql002ResponseModel::getEmployeeId,
                            Comparator.nullsLast(Comparator.naturalOrder())));
            
            // SQL002の結果をCSVファイルに設定する
            for (IfaNotificationViewStatusLookupSql002ResponseModel model : selSql002Res.getDataList()) {
                IfaNotificationViewStatusLookupCsvItems csvItems = new IfaNotificationViewStatusLookupCsvItems();
                csvItems.setBranchName(model.getBranchName());
                csvItems.setEmployeeName(model.getEmployeeName());
                csvItems.setT5nReadFlg(model.getT5nReadFlg());
                csvItems.setReadDate((model.getReadDate()));
                csvItemsList.add(csvItems);
            }
            
            exportList.setDataList((csvItemsList));
            
            // 参照範囲が「3：個別担当者」の場合
        } else if (StringUtils.equals(dtoReq.getCorReferenceCondition(), "3")) {
            
            IfaNotificationViewStatusLookupSql003RequestModel selSql003Req = new IfaNotificationViewStatusLookupSql003RequestModel();
            selSql003Req.setT5nInfoId(dtoReq.getNotificationId());
            
            DataList<IfaNotificationViewStatusLookupSql003ResponseModel> selSql003Res = dao
                    .selectIfaNotificationViewStatusLookupSql003(selSql003Req);
            
            List<IfaNotificationViewStatusLookupSql003ResponseModel> selSql003ResList = selSql003Res.getDataList();
            
            // ②お知らせ閲覧状況の情報をCSVに出力する
            // 下記の条件で並び替えて出力する
            // SQL***.仲介業者支店コード昇順 > SQL***.仲介業者担当者コード昇順
            selSql003ResList.sort(Comparator
                    .comparing(IfaNotificationViewStatusLookupSql003ResponseModel::getSubBrokerId,
                            Comparator.nullsLast(Comparator.naturalOrder()))
                    .thenComparing(IfaNotificationViewStatusLookupSql003ResponseModel::getEmployeeId,
                            Comparator.nullsLast(Comparator.naturalOrder())));
            
            // SQL003の結果をCSVファイルに設定する
            for (IfaNotificationViewStatusLookupSql003ResponseModel model : selSql003Res.getDataList()) {
                IfaNotificationViewStatusLookupCsvItems csvItems = new IfaNotificationViewStatusLookupCsvItems();
                csvItems.setBranchName(model.getBranchName());
                csvItems.setEmployeeName(model.getEmployeeName());
                csvItems.setT5nReadFlg(model.getT5nReadFlg());
                csvItems.setReadDate((model.getReadDate()));
                csvItemsList.add(csvItems);
            }
            
            exportList.setDataList((csvItemsList));
            
        }

        //③   CSVファイルを出力する。
        dtoRes.setTitle(
                csvOut.doCreateCsvFile(exportList, fwSessionId, IfaCommonUtil.getUserAccount().getUserId(), null));
        dtoRes.setTotalSize(exportList.size());
        dtoRes.setRequestedTime(IfaCommonUtil.getFormattedRequestedTime());
        return dtoRes;
    }
    
}
