package com.sbisec.helios.ap.wholePortal.service.impl;

import java.util.Arrays;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.exception.IfaRuntimeException;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.wholePortal.dao.IfaInfoDetailDao;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaInfoDetailSql001RequestModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaInfoDetailSql002RequestModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaInfoDetailSql002ResponseModel;
import com.sbisec.helios.ap.wholePortal.dto.IfaInfoDetailA001RequestDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaInfoDetailA001ResponseDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaInfoDetailA003aRequestDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaInfoDetailA003aResponseDto;
import com.sbisec.helios.ap.wholePortal.service.IfaInfoDetailService;


/**
 * 画面ID：SUB01-03
 * 画面名：Information詳細
 * 2025/01/20 新規作成
 *
 * @author SCSK 江口
 */
@Component(value = "cmpIfaInfoDetailService")
public class IfaInfoDetailServiceImpL implements IfaInfoDetailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaInfoDetailServiceImpL.class);

    @Autowired
    private IfaInfoDetailDao dao;

    @Autowired
    FileDownloadUtil fileDownloadUtil;

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaInfoDetailA001RequestDto
     * Dto レスポンス：IfaInfoDetailA001ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return 表示に必要な情報
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaInfoDetailA001ResponseDto> initializeA001(
            IfaInfoDetailA001RequestDto dtoReq
    ) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaInfoDetailServiceImplL.initializeA001");
        }

        // DTOレスポンスの内部データ
        IfaInfoDetailA001ResponseDto innerData = new IfaInfoDetailA001ResponseDto();

        // リクエストの折り返し項目をセット
        innerData.setFileDirectory(dtoReq.getFileDirectory());

        /* =========================================================================== */
        /* 1. 新お知らせテーブルよりお知らせ情報を取得する。                               */
        /* =========================================================================== */

        // SQL002を実行
        IfaInfoDetailSql002RequestModel selSql002Req = new IfaInfoDetailSql002RequestModel();
        selSql002Req.setNotificationId(dtoReq.getNotificationId());
        DataList<IfaInfoDetailSql002ResponseModel> selSql002Res = dao.selectIfaInfoDetailSql002(selSql002Req);

        // SQL002の結果をDTOレスポンスの内部データにコピー
        if (selSql002Res != null && selSql002Res.size() != 0) {
            IfaInfoDetailSql002ResponseModel notificationInfo = selSql002Res.get(0);
            BeanUtils.copyProperties(innerData, notificationInfo);
        }

        /* =========================================================================== */
        /* 2. 新お知らせ既読テーブルでログイン者の情報を更新する。                          */
        /* =========================================================================== */

        // ユーザ共通情報を取得
        UserAccount userAccount = IfaCommonUtil.getUserAccount();

        // SQL001を実行
        IfaInfoDetailSql001RequestModel updateSql001Req = new IfaInfoDetailSql001RequestModel();
        updateSql001Req.setUserId(userAccount.getUserId());
        updateSql001Req.setNotificationId(dtoReq.getNotificationId());

        Integer updateSql001Res = dao.updateIfaInfoDetailSql001(updateSql001Req);

        // 更新行数が1行ではない場合、IfaRuntimeExceptionを発行する。
        if (updateSql001Res != 1) {
            throw new IfaRuntimeException("IfaInfoDetailServiceImpL.A003a SQL001: Affected rows are not equal to one.");
        }

        /* =========================================================================== */
        /* 3. レスポンスを返却する。                                                     */
        /* =========================================================================== */

        DataList<IfaInfoDetailA001ResponseDto> response = IfaCommonUtil.createDataList(
                Arrays.asList(innerData),
                ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.name(),
                ""
        );
        return response;

    }

    /**
     * アクションID：A03a
     * アクション名：ダウンロード
     * Dto リクエスト：IfaInfoDetailA003aRequestDto
     * Dto レスポンス：IfaInfoDetailA003aResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @param fwSessionId フレームワークセッションID
     * @return ダウンロードファイル名
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaInfoDetailA003aResponseDto> downloadA003a(
            IfaInfoDetailA003aRequestDto dtoReq,
            String fwSessionId
    ) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaInfoDetailServiceImplL.downloadA003a");
        }

        // ユーザ共通情報を取得
        UserAccount userAccount = IfaCommonUtil.getUserAccount();

        // 添付ファイルを一時フォルダに出力して、ファイル名を取得
        String fileName = fileDownloadUtil.postDownload(
                dtoReq.getFileDirectory() + dtoReq.getAttachFile(),
                userAccount.getBrokerCode(),
                dtoReq.getDisclaimer(),
                fwSessionId,
                userAccount.getUserId()
        );

        // レスポンスを返却
        IfaInfoDetailA003aResponseDto innerData = new IfaInfoDetailA003aResponseDto();
        innerData.setPdfFileName(fileName);
        innerData.setPdfFileOutputName(dtoReq.getAttachFile());

        DataList<IfaInfoDetailA003aResponseDto> response = IfaCommonUtil.createDataList(
                Arrays.asList(innerData),
                ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.name(),
                ""
        );

        return response;

    }
}
