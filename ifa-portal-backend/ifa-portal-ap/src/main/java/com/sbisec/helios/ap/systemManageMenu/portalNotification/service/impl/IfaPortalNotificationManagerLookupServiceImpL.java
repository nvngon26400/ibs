package com.sbisec.helios.ap.systemManageMenu.portalNotification.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.IfaPortalNotificationManagerLookupDao;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.model.IfaPortalNotificationManagerLookupSql001RequestModel;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.model.IfaPortalNotificationManagerLookupSql001ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.model.IfaPortalNotificationManagerLookupSql002RequestModel;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationManagerLookupA002RequestDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationManagerLookupA002ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationManagerLookupA008RequestDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationManagerLookupA008ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationManagerLookupPortalNotificationList;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.service.IfaPortalNotificationManagerLookupService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0602-01
 * 画面名：ポータルお知らせ（管理者用）照会
 *
 * @author
 2024/06/06 新規作成
 */
@Component(value = "cmpIfaPortalNotificationManagerLookupService")
public class IfaPortalNotificationManagerLookupServiceImpL implements IfaPortalNotificationManagerLookupService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaPortalNotificationManagerLookupServiceImpL.class);
    
    @Autowired
    private IfaPortalNotificationManagerLookupDao dao;

    /** インフォ.検索結果が0件です。\n条件を設定して再度検索して下さい。 */
    private static final String ERRORS_DATALIST_NOT_FOUND = "errors.dataList.notfound";

    /** ワーニング.検索結果が{0}件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。 */
    private static final String WARNINGS_DATALIST_OVER_MAX_ROWNUM = "warnings.dataList.overMaxRownum";
    
    /** 削除メッセージ  */
    private static final String INFO_DELETECOMPLETED = "info.deleteCompleted";
    
    /** DELETECOMPLETED */
    private static final String DELETECOMPLETED_0 = "SBI証券からのご連絡";

    /** SQL001 最大取得件数 */
    private static final int MAX_ROWNUM = 5000;

    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaPortalNotificationManagerLookupA002RequestDto
     * Dto レスポンス：IfaPortalNotificationManagerLookupA002ResponseDto
     * model リクエスト：IfaPortalNotificationManagerLookupSql001RequestModel
     * model レスポンス：IfaPortalNotificationManagerLookupSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaPortalNotificationManagerLookupA002ResponseDto> displayA002(
            IfaPortalNotificationManagerLookupA002RequestDto dtoReq) throws Exception {
        
        DataList<IfaPortalNotificationManagerLookupA002ResponseDto> dtoRes = new DataList<IfaPortalNotificationManagerLookupA002ResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaPortalNotificationManagerLookupServiceImplL.displayA002");
        }
        
        //①   ポータルお知らせリストを取得する。
        IfaPortalNotificationManagerLookupSql001RequestModel selSql001Req = new IfaPortalNotificationManagerLookupSql001RequestModel();
        // 検索年月日(From)
        selSql001Req.setSearchDateYmdFrom((dtoReq.getSearchDateYmdFrom()));
        // 検索年月日(To)
        selSql001Req.setSearchDateYmdTo(dtoReq.getSearchDateYmdTo());
        // 過去日除外
        selSql001Req.setPastDateExcrude(dtoReq.getPastDateExcrude());
        DataList<IfaPortalNotificationManagerLookupSql001ResponseModel> selSql001Res = dao
                .selectIfaPortalNotificationManagerLookupSql001(selSql001Req);
        
        // SQL001.総件数
        int totalRow = 0;
        if (selSql001Res.getDataList().size() != 0) {
            totalRow = selSql001Res.get(0).getTotalRow();
        }
        
        // SQL001.総件数が0件の場合：メッセージを表示し、処理終了。
        if (totalRow == 0) {
            dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.INFO,
                    ERRORS_DATALIST_NOT_FOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATALIST_NOT_FOUND)
            );

            return dtoRes;

        }

        // SQL001の結果をDTOレスポンスにコピー
        List<IfaPortalNotificationManagerLookupPortalNotificationList> portalNotificationList = new ArrayList<>();
        for (IfaPortalNotificationManagerLookupSql001ResponseModel sqlRec : selSql001Res.getDataList()) {
            IfaPortalNotificationManagerLookupPortalNotificationList portalNotification = new IfaPortalNotificationManagerLookupPortalNotificationList();
            // お知らせID（数字）
            portalNotification.setNotificationId(sqlRec.getInfoId());
            // お知らせ（全角半角）
            portalNotification.setNotification(sqlRec.getInfoDetail());
            // 重要フラグ
            portalNotification.setImportantFlag(sqlRec.getImportantFlg());
            // 非表示フラグ
            portalNotification.setNonDisplayFlag(sqlRec.getDeleteFlg());
            // 表示期間FROM
            portalNotification.setDisplayPeriodFrom(sqlRec.getDisplayPeriodFrom());
            // 表示期間TO
            portalNotification.setDisplayPeriodTo(sqlRec.getDisplayPeriodTo());
            // 登録日時
            portalNotification.setRegisterDayTime(sqlRec.getCreateTime());
            
            portalNotificationList.add(portalNotification);
        }

        IfaPortalNotificationManagerLookupA002ResponseDto innerData = new IfaPortalNotificationManagerLookupA002ResponseDto();
        innerData.setPortalNotificationList(portalNotificationList);

        // SQL001の結果件数が5000件を超過している場合
        if (totalRow > MAX_ROWNUM) {
            dtoRes = IfaCommonUtil.createDataList(
                    Arrays.asList(innerData),
                    ErrorLevel.WARNING,
                    WARNINGS_DATALIST_OVER_MAX_ROWNUM,
                    IfaCommonUtil.getMessage(
                            WARNINGS_DATALIST_OVER_MAX_ROWNUM,
                            new String[] {"5,000", String.format("%,d", totalRow)}
                    )
            );

            dtoRes.setTotalSize(totalRow);
            dtoRes.setOverMaxRownum(true);

            return dtoRes;
        }
        
        // 正常終了の場合
        dtoRes = IfaCommonUtil.createDataList(
                Arrays.asList(innerData),
                ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.name(),
                ""
        );

        return dtoRes;
    }
    
    /**
     * アクションID：A008
     * アクション名：ポータルお知らせ削除確認OK
     * Dto リクエスト：IfaPortalNotificationManagerLookupA008RequestDto
     * Dto レスポンス：IfaPortalNotificationManagerLookupA008ResponseDto
     * model リクエスト：IfaPortalNotificationManagerLookupSql002RequestModel
     * model レスポンス：IfaPortalNotificationManagerLookupSql002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaPortalNotificationManagerLookupA008ResponseDto> portalNoticeDeletionConfirmOkA008(
            IfaPortalNotificationManagerLookupA008RequestDto dtoReq) throws Exception {
        
        DataList<IfaPortalNotificationManagerLookupA008ResponseDto> dtoRes
                = new DataList<IfaPortalNotificationManagerLookupA008ResponseDto>();
        IfaPortalNotificationManagerLookupA008ResponseDto resDto = new IfaPortalNotificationManagerLookupA008ResponseDto();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaPortalNotificationManagerLookupServiceImplL.portalNoticeDeletionConfirmOkA008");
        }
        
        //①   対象のポータルお知らせレコードを削除し、メッセージを表示する。
        IfaPortalNotificationManagerLookupSql002RequestModel delSql002Req
                = new IfaPortalNotificationManagerLookupSql002RequestModel();
        // お知らせID
        delSql002Req.setNotificationId(dtoReq.getNotificationId());
        dao.deleteIfaPortalNotificationManagerLookupSql002(delSql002Req);
        
        dtoRes = IfaCommonUtil.createDataList(List.of(resDto), ErrorLevel.INFO, INFO_DELETECOMPLETED,
                IfaCommonUtil.getMessage(INFO_DELETECOMPLETED, new String[] { DELETECOMPLETED_0 }));

        return dtoRes;
    }
}
