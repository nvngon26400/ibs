package com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.IfaReleaseNoteUpdateDao;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteUpdateSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteUpdateSql004RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteUpdateSql005RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteUpdateA009bRequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.ContentItem;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteUpdateA009bResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.util.IfaReleaseNoteCommonUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.util.IfaReleaseNoteException;
import io.micrometer.core.instrument.util.StringUtils;

/**
 * SUB0512-03 リリースノート更新
 *
 * @author SBI大連 夏
 * @date 2025/11/07
 */

@Component(value = "cmpIfaReleaseNoteUpdateServiceEx")
public class IfaReleaseNoteUpdateServiceImplEx {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaReleaseNoteUpdateServiceImplEx.class);

    /** エラーメッセージ: {0}が失敗しました。" */
    private static final String ERRORS_PROCESSINGFAILED = "errors.processingFailed";

    /** エラーメッセージ引数 */
    private static final String UPDATEED_ERROR = "リリースノートの更新";

    /** 完了メッセージ引数 */
    private static final String MESSAGE_PARAM_REGISTER = "リリースノート";

    @Autowired
    private IfaReleaseNoteUpdateDao dao;

    @Autowired
    private IfaReleaseNoteCommonUtil releaseNoteCommonUtil;

    /**
     * アクションID：A009Ex
     * アクション名：更新
     * Dto リクエスト：IfaReleaseNoteUpdateA009bRequestDto
     * Dto レスポンス：IfaReleaseNoteUpdateA009bResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 初期化処理で例外が発生した場合
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public DataList<IfaReleaseNoteUpdateA009bResponseDto> updateA009bEx(IfaReleaseNoteUpdateA009bRequestDto dtoReq)
        throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaReleaseNoteUpdateServiceImplEx.updateA009bEx");
        }

        DataList<IfaReleaseNoteUpdateA009bResponseDto> res = new DataList<IfaReleaseNoteUpdateA009bResponseDto>();

        int count = 0; // DB操作件数

        // システム日時
        String systemDate = releaseNoteCommonUtil.systemDate();

        // SQL003.リリースノート内容削除
        IfaReleaseNoteUpdateSql003RequestModel sql003Req = new IfaReleaseNoteUpdateSql003RequestModel();
        sql003Req.setReleaseNoteNo(dtoReq.getReleaseNoteNo());

        try {
            dao.deleteIfaReleaseNoteUpdateSql003(sql003Req);
        } catch (Exception e) {
            throw new IfaReleaseNoteException(ERRORS_PROCESSINGFAILED, UPDATEED_ERROR);
        }

        if (dtoReq.getContentItemList() != null && CollectionUtils.isNotEmpty(dtoReq.getContentItemList())) {

            // SQL004.リリースノート内容登録
            List<IfaReleaseNoteUpdateSql004RequestModel> sql004ReqList =
                new ArrayList<IfaReleaseNoteUpdateSql004RequestModel>();
            for (ContentItem contentItem : dtoReq.getContentItemList()) {
                IfaReleaseNoteUpdateSql004RequestModel sql004Req = new IfaReleaseNoteUpdateSql004RequestModel();
                BeanUtils.copyProperties(contentItem, sql004Req);
                sql004Req.setReleaseNoteNo(dtoReq.getReleaseNoteNo());
                sql004Req.setCreateTime(systemDate);
                sql004Req.setCreateUser(IfaCommonUtil.getUserAccount().getUserId());
                sql004Req.setUpdateTime(systemDate);
                sql004Req.setUpdateUser(IfaCommonUtil.getUserAccount().getUserId());
                sql004ReqList.add(sql004Req);
            }

            try {
                count = dao.insertIfaReleaseNoteUpdateSql004(sql004ReqList);
            } catch (Exception e) {
                throw new IfaReleaseNoteException(ERRORS_PROCESSINGFAILED, UPDATEED_ERROR);
            }

            // 登録回数が0の場合
            if (count < 1) {
                throw new IfaReleaseNoteException(ERRORS_PROCESSINGFAILED, UPDATEED_ERROR);
            }
        }

        // SQL005.リリースノート更新
        IfaReleaseNoteUpdateSql005RequestModel sql005Req = new IfaReleaseNoteUpdateSql005RequestModel();
        sql005Req.setReleaseNoteNo(dtoReq.getReleaseNoteNo());
        sql005Req.setTitle(dtoReq.getTitle());
        sql005Req.setDisplayedDate(dtoReq.getDisplayedDate());
        // 詳細ファイルの入力が場合、「ファイルタイプ」、「PDFサイズ」、「PDF向き」、「詳細ファイル」を設定する。
        if (StringUtils.isNotEmpty(dtoReq.getDetailedFileName())) {
            sql005Req.setFileType(dtoReq.getFileType());
            sql005Req.setPdfSize(dtoReq.getPdfSize());
            sql005Req.setPdfDirection(dtoReq.getPdfDirection());
            sql005Req.setDetailedFile(dtoReq.getDetailedFileName());
        }
        sql005Req.setUpdateTime(systemDate);
        sql005Req.setUpdateUser(IfaCommonUtil.getUserAccount().getUserId());

        try {
            count = dao.updateIfaReleaseNoteUpdateSql005(sql005Req);
        } catch (Exception e) {
            throw new IfaReleaseNoteException(ERRORS_PROCESSINGFAILED, UPDATEED_ERROR);
        }

        // 更新回数が0の場合
        if (count < 1) {
            throw new IfaReleaseNoteException(ERRORS_PROCESSINGFAILED, UPDATEED_ERROR);
        }

        res = IfaCommonUtil.createDataList(null, ErrorLevel.SUCCESS, MESSAGE_PARAM_REGISTER, null);
        return res;
    }

}
