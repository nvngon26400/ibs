package com.sbisec.helios.ap.systemManageMenu.portalNotification.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.IfaDateUtil;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.IfaPortalNotificationUpdateDao;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.model.IfaPortalNotificationUpdateSql001RequestModel;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.model.IfaPortalNotificationUpdateSql001ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.model.IfaPortalNotificationUpdateSql002RequestModel;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationUpdateA001RequestDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationUpdateA001ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationUpdateA004RequestDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationUpdateA004ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationUpdateA006RequestDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationUpdateA006ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationUpdateA007RequestDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationUpdateA007ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.service.IfaPortalNotificationUpdateService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 画面ID：SUB0602-04_1
 * 画面名：ポータルお知らせ更新
 *
 * @author 松尾
 2024/06/13 新規作成
 */
@Component(value = "cmpIfaPortalNotificationUpdateService")
public class IfaPortalNotificationUpdateServiceImpL implements IfaPortalNotificationUpdateService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaPortalNotificationUpdateServiceImpL.class);
    
    @Autowired
    private IfaPortalNotificationUpdateDao dao;
    
    @Autowired
    private IfaDateUtil ifaDateUtil;
    
    /** 0件メッセージ */
    private static final String ERRORS_CMN_INFORMATION_NOTFOUND = "errors.cmn.information.notfound";
    
    /** 表示期間チェックエラーtoday  */
    private static final String ERRORS_DATE_SPECIFYAFTERTODAY = "errors.date.specifyAfterToday";
    
    /** 表示期間チェックエラーfromTo  */
    private static final String ERRORS_DATE_SPECIFYFROMTO = "errors.date.specifyFromTo";
    
    /** "表示期間To" */
    private static final String DATETO = "表示期間To";
    
    /** "表示期間" */
    private static final String DATE = "表示期間";
    
    /** 更新完了メッセージ  */
    private static final String INFO_ENDCOMPLETED = "info.endCompleted";
    
    /** "更新"*/
    private static final String UPDATE = "更新";

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaPortalNotificationUpdateA001RequestDto
     * Dto レスポンス：IfaPortalNotificationUpdateA001ResponseDto
     * model リクエスト：IfaPortalNotificationUpdateSql001RequestModel
     * model レスポンス：IfaPortalNotificationUpdateSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaPortalNotificationUpdateA001ResponseDto> initializeA001(
            IfaPortalNotificationUpdateA001RequestDto dtoReq) throws Exception {
        
        DataList<IfaPortalNotificationUpdateA001ResponseDto> dtoRes = new DataList<IfaPortalNotificationUpdateA001ResponseDto>();
        List<IfaPortalNotificationUpdateA001ResponseDto> resDtoList = new ArrayList<IfaPortalNotificationUpdateA001ResponseDto>();
        IfaPortalNotificationUpdateA001ResponseDto resDto = new IfaPortalNotificationUpdateA001ResponseDto();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaPortalNotificationUpdateServiceImplL.initializeA001");
        
        //①   ポータルお知らせテーブルからお知らせの情報を取得する。
        IfaPortalNotificationUpdateSql001RequestModel selSql001Req = new IfaPortalNotificationUpdateSql001RequestModel();
        // お知らせID
        selSql001Req.setNotificationId(dtoReq.getNotificationId());
        DataList<IfaPortalNotificationUpdateSql001ResponseModel> selSql001Res = dao
                .selectIfaPortalNotificationUpdateSql001(selSql001Req);
        
        // SQL001で0件の場合、エラーメッセージを表示し、処理を終了する。
        if (ObjectUtils.isEmpty(selSql001Res.getDataList())) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_CMN_INFORMATION_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_CMN_INFORMATION_NOTFOUND));
            return dtoRes;
        }
        
        //　レスポンスセット
        // 表示期間From
        resDto.setDisplayPeriodFrom(selSql001Res.getDataList().get(0).getDisplayPeriodFrom());
        // 表示期間To
        resDto.setDisplayPeriodTo(selSql001Res.getDataList().get(0).getDisplayPeriodTo());
        // ご連絡内容
        resDto.setNotificationContent(selSql001Res.getDataList().get(0).getInfoDetail());
        // 重要なご連絡
        resDto.setImportantNotification(selSql001Res.getDataList().get(0).getImportantFlg());
        // 非表示
        resDto.setNonDisplay(selSql001Res.getDataList().get(0).getDeleteFlg());
        
        resDtoList.add(resDto);
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "0", "");
        return dtoRes;
    }
    
    /**
     * アクションID：A004
     * アクション名：リセット
     * Dto リクエスト：IfaPortalNotificationUpdateA004RequestDto
     * Dto レスポンス：IfaPortalNotificationUpdateA004ResponseDto
     * model リクエスト：IfaPortalNotificationUpdateSql001RequestModel
     * model レスポンス：IfaPortalNotificationUpdateSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaPortalNotificationUpdateA004ResponseDto> resetA004(
            IfaPortalNotificationUpdateA004RequestDto dtoReq) throws Exception {
        
        DataList<IfaPortalNotificationUpdateA004ResponseDto> dtoRes = new DataList<IfaPortalNotificationUpdateA004ResponseDto>();
        List<IfaPortalNotificationUpdateA004ResponseDto> resDtoList = new ArrayList<IfaPortalNotificationUpdateA004ResponseDto>();
        IfaPortalNotificationUpdateA004ResponseDto resDto = new IfaPortalNotificationUpdateA004ResponseDto();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaPortalNotificationUpdateServiceImplL.resetA004");
        
        //①   ポータルお知らせテーブルからお知らせの情報を取得する。
        IfaPortalNotificationUpdateSql001RequestModel selSql001Req = new IfaPortalNotificationUpdateSql001RequestModel();
        // お知らせID
        selSql001Req.setNotificationId(dtoReq.getNotificationId());
        DataList<IfaPortalNotificationUpdateSql001ResponseModel> selSql001Res = dao
                .selectIfaPortalNotificationUpdateSql001(selSql001Req);
        
        // SQL001で0件の場合、エラーメッセージを表示し、処理を終了する。
        if (ObjectUtils.isEmpty(selSql001Res.getDataList())) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_CMN_INFORMATION_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_CMN_INFORMATION_NOTFOUND));
            return dtoRes;
        }
        
        //　レスポンスセット
        // 表示期間From
        resDto.setDisplayPeriodFrom(selSql001Res.getDataList().get(0).getDisplayPeriodFrom());
        // 表示期間To
        resDto.setDisplayPeriodTo(selSql001Res.getDataList().get(0).getDisplayPeriodTo());
        // ご連絡内容
        resDto.setNotificationContent(selSql001Res.getDataList().get(0).getInfoDetail());
        // 重要なご連絡
        resDto.setImportantNotification(selSql001Res.getDataList().get(0).getImportantFlg());
        // 非表示
        resDto.setNonDisplay(selSql001Res.getDataList().get(0).getDeleteFlg());
        
        resDtoList.add(resDto);
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "0", "");
        return dtoRes;
    }
    
    /**
     * アクションID：A006
     * アクション名：更新
     * Dto リクエスト：IfaPortalNotificationUpdateA006RequestDto
     * Dto レスポンス：IfaPortalNotificationUpdateA006ResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaPortalNotificationUpdateA006ResponseDto> updateA006(
            IfaPortalNotificationUpdateA006RequestDto dtoReq) throws Exception {
        
        DataList<IfaPortalNotificationUpdateA006ResponseDto> dtoRes = new DataList<IfaPortalNotificationUpdateA006ResponseDto>();
        List<IfaPortalNotificationUpdateA006ResponseDto> resDtoList = new ArrayList<IfaPortalNotificationUpdateA006ResponseDto>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaPortalNotificationUpdateServiceImplL.updateA006");
        
        //①   表示期間の入力値チェック。
        // システム日付取得
        String systemDate = ifaDateUtil.format(IfaDateUtil.SEPARATED_YYYYMMDD);
        LocalDate now = LocalDate.parse(systemDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        
        LocalDate fromDate = null;
        LocalDate toDate = null;
        if(!ObjectUtils.isEmpty(dtoReq.getDisplayPeriodFrom())) {
            fromDate = LocalDate.parse(dtoReq.getDisplayPeriodFrom(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        }
        if(!ObjectUtils.isEmpty(dtoReq.getDisplayPeriodTo())) {
            toDate = LocalDate.parse(dtoReq.getDisplayPeriodTo(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        }
        // 表示期間To に値がある かつ 表示期間To < 当日日付(システム日付) の場合、メッセージを表示し、処理を終了する。
        if (!ObjectUtils.isEmpty(toDate) && toDate.compareTo(now) < 0) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_DATE_SPECIFYAFTERTODAY,
                    IfaCommonUtil.getMessage(ERRORS_DATE_SPECIFYAFTERTODAY, new String[] { DATETO }));
            return dtoRes;
            // 表示期間From と 表示期間To に値がある かつ 表示期間From＞表示期間To の場合、メッセージを表示し、処理を終了する。
        } else if (!ObjectUtils.isEmpty(fromDate) && !ObjectUtils.isEmpty(toDate) && fromDate.compareTo(toDate) > 0) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_DATE_SPECIFYFROMTO,
                    IfaCommonUtil.getMessage(ERRORS_DATE_SPECIFYFROMTO, new String[] { DATE }));
            return dtoRes;
        }
        // 上記以外の場合、次の処理へ。
        
        //②   環境依存文字チェック。
        // コントローラーに実装

        return dtoRes;
    }
    
    /**
     * アクションID：A007
     * アクション名：ポータルお知らせ更新確認OK
     * Dto リクエスト：IfaPortalNotificationUpdateA007RequestDto
     * Dto レスポンス：IfaPortalNotificationUpdateA007ResponseDto
     * model リクエスト：IfaPortalNotificationUpdateSql002RequestModel
     * model レスポンス：IfaPortalNotificationUpdateSql002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaPortalNotificationUpdateA007ResponseDto> portalNoticeUpdateConfirmOkA007(
            IfaPortalNotificationUpdateA007RequestDto dtoReq) throws Exception {
        
        DataList<IfaPortalNotificationUpdateA007ResponseDto> dtoRes = new DataList<IfaPortalNotificationUpdateA007ResponseDto>();
        List<IfaPortalNotificationUpdateA007ResponseDto> resDtoList = new ArrayList<IfaPortalNotificationUpdateA007ResponseDto>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaPortalNotificationUpdateServiceImplL.portalNoticeUpdateConfirmOkA007");
        
        //①   ポータルお知らせを更新し、メッセージを表示する。
        IfaPortalNotificationUpdateSql002RequestModel updSql002Req = new IfaPortalNotificationUpdateSql002RequestModel();
        // お知らせID
        updSql002Req.setNotificationId(dtoReq.getNotificationId());
        // 表示期間From
        updSql002Req.setDisplayPeriodFrom(dtoReq.getDisplayPeriodFrom());
        // 表示期間To
        updSql002Req.setDisplayPeriodTo(dtoReq.getDisplayPeriodTo());
        // ご連絡内容
        updSql002Req.setNotificationContent(dtoReq.getNotificationContent());
        // 重要なご連絡
        updSql002Req.setImportantNotification(dtoReq.getImportantNotification());
        // 非表示
        updSql002Req.setNonDisplay(dtoReq.getNonDisplay());
        // 更新ユーザ
        updSql002Req.setUpdateUser(IfaCommonUtil.getUserAccount().getUserId());

        dao.updateIfaPortalNotificationUpdateSql002(updSql002Req);
        
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, INFO_ENDCOMPLETED,
                IfaCommonUtil.getMessage(INFO_ENDCOMPLETED, new String[] { UPDATE }));
        return dtoRes;
    }
}
