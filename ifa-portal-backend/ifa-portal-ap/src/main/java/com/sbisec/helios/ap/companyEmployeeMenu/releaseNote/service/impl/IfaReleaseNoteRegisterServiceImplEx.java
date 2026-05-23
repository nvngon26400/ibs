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
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.IfaReleaseNoteRegisterDao;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteRegisterSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteRegisterSql004RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteRegisterA007bRequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.ContentItem;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteRegisterA007bResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.util.IfaReleaseNoteCommonUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.util.IfaReleaseNoteException;
import io.micrometer.core.instrument.util.StringUtils;

/**
 * SUB0512-02 リリースノート登録
 *
 * @author SBI大連 夏
 * @date 2025/11/06
 */

@Component(value = "cmpIfaReleaseNoteRegisterServiceEx")
public class IfaReleaseNoteRegisterServiceImplEx {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaReleaseNoteRegisterServiceImplEx.class);

    /** エラーメッセージ: {0}が失敗しました。" */
    private static final String ERRORS_PROCESSINGFAILED = "errors.processingFailed";

    /** エラーメッセージ引数 */
    private static final String REGISTERED_ERROR = "リリースノートの新規登録";

    /** 完了メッセージ引数 */
    private static final String MESSAGE_PARAM_REGISTER = "リリースノート";

    @Autowired
    private IfaReleaseNoteRegisterDao dao;

    @Autowired
    private IfaReleaseNoteCommonUtil releaseNoteCommonUtil;

    /**
     * アクションID：A007bEx
     * アクション名：登録
     * Dto リクエスト：IfaReleaseNoteRegisterA007bRequestDto
     * Dto レスポンス：IfaReleaseNoteRegisterA007bResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 処理で例外が発生した場合
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public DataList<IfaReleaseNoteRegisterA007bResponseDto> registerA007bEx(
        IfaReleaseNoteRegisterA007bRequestDto dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaReleaseNoteRegisterServiceImpl.registerA007b");
        }

        DataList<IfaReleaseNoteRegisterA007bResponseDto> res = new DataList<IfaReleaseNoteRegisterA007bResponseDto>();

        int count = 0; // DB操作件数

        // システム日時
        String systemDate = releaseNoteCommonUtil.systemDate();

        // SQL003.リリースノート登録
        IfaReleaseNoteRegisterSql003RequestModel sql003Req = new IfaReleaseNoteRegisterSql003RequestModel();
        sql003Req.setReleaseNoteNo(dtoReq.getReleaseNoteNo());
        sql003Req.setTitle(dtoReq.getTitle());
        sql003Req.setDisplayedDate(dtoReq.getDisplayedDate());
        // 詳細ファイルの入力が場合、「ファイルタイプ」、「PDFサイズ」、「PDF向き」、「詳細ファイル」を設定する。
        if (StringUtils.isNotEmpty(dtoReq.getDetailedFileName())) {
            sql003Req.setFileType(dtoReq.getFileType());
            sql003Req.setPdfSize(dtoReq.getPdfSize());
            sql003Req.setPdfDirection(dtoReq.getPdfDirection());
            sql003Req.setDetailedFile(dtoReq.getDetailedFileName());
        }
        sql003Req.setCreateTime(systemDate);
        sql003Req.setCreateUser(IfaCommonUtil.getUserAccount().getUserId());
        sql003Req.setUpdateTime(systemDate);
        sql003Req.setUpdateUser(IfaCommonUtil.getUserAccount().getUserId());

        try {
            count = dao.insertIfaReleaseNoteRegisterSql003(sql003Req);
        } catch (Exception e) {
            throw new IfaReleaseNoteException(ERRORS_PROCESSINGFAILED, REGISTERED_ERROR);
        }

        // 登録回数が0の場合
        if (count < 1) {
            throw new IfaReleaseNoteException(ERRORS_PROCESSINGFAILED, REGISTERED_ERROR);
        }

        // SQL004.リリースノート内容登録
        if (dtoReq.getContentItemList() != null && CollectionUtils.isNotEmpty(dtoReq.getContentItemList())) {
            List<IfaReleaseNoteRegisterSql004RequestModel> sql004ReqList =
                new ArrayList<IfaReleaseNoteRegisterSql004RequestModel>();
            for (ContentItem contentItem : dtoReq.getContentItemList()) {
                IfaReleaseNoteRegisterSql004RequestModel sql004Req = new IfaReleaseNoteRegisterSql004RequestModel();
                BeanUtils.copyProperties(contentItem, sql004Req);
                sql004Req.setReleaseNoteNo(dtoReq.getReleaseNoteNo());
                sql004Req.setCreateTime(systemDate);
                sql004Req.setCreateUser(IfaCommonUtil.getUserAccount().getUserId());
                sql004Req.setUpdateTime(systemDate);
                sql004Req.setUpdateUser(IfaCommonUtil.getUserAccount().getUserId());
                sql004ReqList.add(sql004Req);
            }

            try {
                count = dao.insertIfaReleaseNoteRegisterSql004(sql004ReqList);
            } catch (Exception e) {
                throw new IfaReleaseNoteException(ERRORS_PROCESSINGFAILED, REGISTERED_ERROR);
            }

            // 登録回数が0の場合
            if (count < 1) {
                throw new IfaReleaseNoteException(ERRORS_PROCESSINGFAILED, REGISTERED_ERROR);
            }
        }

        res = IfaCommonUtil.createDataList(null, ErrorLevel.SUCCESS, MESSAGE_PARAM_REGISTER, null);
        return res;
    }

}
