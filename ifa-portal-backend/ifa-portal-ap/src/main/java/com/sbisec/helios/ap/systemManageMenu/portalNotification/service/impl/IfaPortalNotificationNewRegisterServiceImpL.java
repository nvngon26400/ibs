package com.sbisec.helios.ap.systemManageMenu.portalNotification.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.IfaDateUtil;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.IfaPortalNotificationNewRegisterDao;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationNewRegisterA007RequestDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationNewRegisterA007ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.model.IfaPortalNotificationNewRegisterSql001RequestModel;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.service.IfaPortalNotificationNewRegisterService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 画面ID：SUB0602-02_1
 * 画面名：ポータルお知らせ新規登録
 *
 * @author 松尾
 2024/06/11 新規作成
 */
@Component(value = "cmpIfaPortalNotificationNewRegisterService")
public class IfaPortalNotificationNewRegisterServiceImpL implements IfaPortalNotificationNewRegisterService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaPortalNotificationNewRegisterServiceImpL.class);
    
    @Autowired
    private IfaPortalNotificationNewRegisterDao dao;
    
    @Autowired
    private IfaDateUtil ifaDateUtil;
    
    /** 表示期間チェックエラーtoday  */
    private static final String ERRORS_DATE_SPECIFYAFTERTODAY = "errors.date.specifyAfterToday";
    
    /** 表示期間チェックエラーfromTo  */
    private static final String ERRORS_DATE_SPECIFYFROMTO = "errors.date.specifyFromTo";
    
    /** SQL登録完了メッセージ  */
    private static final String INFO_INSERTCOMPLETED = "info.insertCompleted";
    
    /** "表示期間To" */
    private static final String DATETO = "表示期間To";
    
    /** "表示期間" */
    private static final String DATE = "表示期間";
    
    /** "SBI証券からのご連絡"*/
    private static final String CONTACT = "SBI証券からのご連絡";
    
    /**
     * アクションID：A007
     * アクション名：ポータルお知らせ新規登録OK
     * Dto リクエスト：IfaPortalNotificationNewRegisterA007RequestDto
     * Dto レスポンス：IfaPortalNotificationNewRegisterA007ResponseDto
     * model リクエスト：IfaPortalNotificationNewRegisterSql001RequestModel
     * model レスポンス：IfaPortalNotificationNewRegisterSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaPortalNotificationNewRegisterA007ResponseDto> portalNoticeNewRegistrationOkA007(
            IfaPortalNotificationNewRegisterA007RequestDto dtoReq) throws Exception {
        
        DataList<IfaPortalNotificationNewRegisterA007ResponseDto> dtoRes = new DataList<IfaPortalNotificationNewRegisterA007ResponseDto>();
        List<IfaPortalNotificationNewRegisterA007ResponseDto> resDtoList = new ArrayList<IfaPortalNotificationNewRegisterA007ResponseDto>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaPortalNotificationNewRegisterServiceImplL.portalNoticeNewRegistrationOkA007");
        
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
        
        //③   SBI証券からのご連絡情報を登録し、メッセージを表示する。
        IfaPortalNotificationNewRegisterSql001RequestModel insSql001Req = new IfaPortalNotificationNewRegisterSql001RequestModel();
        // 表示期間From
        insSql001Req.setDisplayPeriodFrom(dtoReq.getDisplayPeriodFrom());
        // 表示期間To
        insSql001Req.setDisplayPeriodTo(dtoReq.getDisplayPeriodTo());
        // お知らせ内容
        insSql001Req.setNotificationContent(dtoReq.getNotificationContent());
        // 重要なお知らせ
        insSql001Req.setImportantNotification(dtoReq.getImportantNotification());
        // 非表示
        insSql001Req.setNonDisplay(dtoReq.getNonDisplay());
        // 登録ユーザ
        insSql001Req.setCreateUser(IfaCommonUtil.getUserAccount().getUserId());
        // 更新ユーザ
        insSql001Req.setUpdateUser(IfaCommonUtil.getUserAccount().getUserId());
        
        dao.insertIfaPortalNotificationNewRegisterSql001(insSql001Req);
        
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, INFO_INSERTCOMPLETED,
                IfaCommonUtil.getMessage(INFO_INSERTCOMPLETED, new String[] { CONTACT }));
        return dtoRes;
    }
    
}
