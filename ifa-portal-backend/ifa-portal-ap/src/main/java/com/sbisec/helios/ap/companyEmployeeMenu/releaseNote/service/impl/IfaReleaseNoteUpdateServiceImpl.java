package com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.IfaReleaseNoteUpdateDao;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteUpdateSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteUpdateSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteUpdateSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteUpdateSql002ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteUpdateA001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteUpdateA001ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.ContentItem;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteUpdateA009bRequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteUpdateA009bResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.service.IfaReleaseNoteUpdateService;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.util.IfaReleaseNoteException;
import io.micrometer.core.instrument.util.StringUtils;

/**
 * SUB0512-03 リリースノート更新
 *
 * @author SBI大連 夏
 * @date 2025/10/23
 */

@Component(value = "cmpIfaReleaseNoteUpdateService")
public class IfaReleaseNoteUpdateServiceImpl implements IfaReleaseNoteUpdateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaReleaseNoteUpdateServiceImpl.class);

    /** 添付ファイルの格納先が取得できないため、処理を中止しました。 */
    private static final String ERRORS_CMN_DIRECTORY_NOTFOUND = "errors.cmn.directory.notfound";

    /** 機能ID. */
    private static final String RELEASE_NOTE_FUNC_ID = "M14";

    /** カテゴリID. */
    private static final String CATEGORY_ID_ZERO = "0";

    @Autowired
    private IfaReleaseNoteUpdateDao dao;

    @Autowired
    private IfaReleaseNoteUpdateServiceImplEx serviceImplEx;

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaReleaseNoteUpdateA001RequestDto
     * Dto レスポンス：IfaReleaseNoteUpdateA001ResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 初期化処理で例外が発生した場合
     */
    @Override
    public DataList<IfaReleaseNoteUpdateA001ResponseDto> initializeA001(IfaReleaseNoteUpdateA001RequestDto dtoReq)
        throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaReleaseNoteUpdateServiceImpl.initializeA001");
        }

        DataList<IfaReleaseNoteUpdateA001ResponseDto> res = new DataList<IfaReleaseNoteUpdateA001ResponseDto>();
        IfaReleaseNoteUpdateA001ResponseDto resDto = new IfaReleaseNoteUpdateA001ResponseDto();

        IfaReleaseNoteUpdateSql001RequestModel sql001Req = new IfaReleaseNoteUpdateSql001RequestModel();

        sql001Req.setReleaseNoteNo(dtoReq.getReleaseNoteNo());

        List<IfaReleaseNoteUpdateSql001ResponseModel> sql001Res =
            new ArrayList<IfaReleaseNoteUpdateSql001ResponseModel>();

        sql001Res = dao.selectIfaReleaseNoteUpdateSql001(sql001Req);

        List<ContentItem> contentItemList = new ArrayList<ContentItem>();

        if (sql001Res != null && CollectionUtils.isNotEmpty(sql001Res)) {

            for (IfaReleaseNoteUpdateSql001ResponseModel model : sql001Res) {
                ContentItem contentItem = new ContentItem();
                contentItem.setReleaseNoteContentNo(model.getReleaseNoteContentNo());
                contentItem.setMenuName(model.getMenuName());
                contentItem.setScreenName(model.getScreenName());
                contentItem.setContent(model.getContent());
                contentItemList.add(contentItem);
            }

            resDto.setReleaseNoteNo(sql001Res.get(0).getReleaseNoteNo());
            resDto.setTitle(sql001Res.get(0).getTitle());
            resDto.setDisplayedDate(sql001Res.get(0).getDisplayedDate());
            resDto.setFileType(sql001Res.get(0).getFileType());
            resDto.setPdfSize(sql001Res.get(0).getPdfSize());
            resDto.setPdfDirection(sql001Res.get(0).getPdfDirection());
            resDto.setDetailedFileName(sql001Res.get(0).getDetailedFile());
            resDto.setContentItemList(contentItemList);
        }

        res = IfaCommonUtil.createDataList(List.of(resDto), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), null);

        return res;

    }

    /**
     * アクションID：A004
     * アクション名：ダウンロード
     * Dto レスポンス：String
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 初期化処理で例外が発生した場合
     */
    @Override
    public DataList<String> downloadA004() throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaReleaseNoteUpdateServiceImpl.downloadA004");
        }

        DataList<String> res = new DataList<String>();

        IfaReleaseNoteUpdateSql002RequestModel sql002Req = new IfaReleaseNoteUpdateSql002RequestModel();
        sql002Req.setFunctionId(RELEASE_NOTE_FUNC_ID);
        sql002Req.setCategoryId(CATEGORY_ID_ZERO);

        IfaReleaseNoteUpdateSql002ResponseModel sql002Res = new IfaReleaseNoteUpdateSql002ResponseModel();

        sql002Res = dao.selectIfaReleaseNoteUpdateSql002(sql002Req);

        if (sql002Res == null || StringUtils.isEmpty(sql002Res.getFileDirectory())) {
            res = IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_CMN_DIRECTORY_NOTFOUND,
                IfaCommonUtil.getMessage(ERRORS_CMN_DIRECTORY_NOTFOUND));
            return res;
        }

        String fileDirectory = sql002Res.getFileDirectory();

        res = IfaCommonUtil.createDataList(List.of(fileDirectory), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");

        return res;

    }

    /**
     * アクションID：A009
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
    @Override
    public DataList<IfaReleaseNoteUpdateA009bResponseDto> updateA009b(IfaReleaseNoteUpdateA009bRequestDto dtoReq)
        throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaReleaseNoteUpdateServiceImpl.updateA009b");
        }

        DataList<IfaReleaseNoteUpdateA009bResponseDto> res = new DataList<IfaReleaseNoteUpdateA009bResponseDto>();

        // 必須入力チェック：タイトル、画面表示開始日!＝ "" or !NULL
        if (StringUtils.isEmpty(dtoReq.getTitle()) || StringUtils.isEmpty(dtoReq.getDisplayedDate())) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, null, null);
        }

        try {
            res = serviceImplEx.updateA009bEx(dtoReq);
        } catch (IfaReleaseNoteException ex) {
            String message = IfaCommonUtil.getMessage(ex.getMessageKey(), new String[] { ex.getMessageParam() });
            res = IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ex.getMessageKey(), message);
        }

        return res;

    }

    /**
     * アクション名：ファイルディレクトリ情報を取得する
     * Dto レスポンス：String
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 初期化処理で例外が発生した場合
     */
    @Override
    public DataList<String> getReleaseNoteFileDir() throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaReleaseNoteRegisterServiceImpl.getReleaseNoteFileDir");
        }

        DataList<String> res = new DataList<String>();

        // ファイルディレクトリ情報を取得する。
        IfaReleaseNoteUpdateSql002RequestModel sql002Req = new IfaReleaseNoteUpdateSql002RequestModel();
        sql002Req.setFunctionId(RELEASE_NOTE_FUNC_ID);
        sql002Req.setCategoryId(CATEGORY_ID_ZERO);

        IfaReleaseNoteUpdateSql002ResponseModel sql002Res = new IfaReleaseNoteUpdateSql002ResponseModel();
        sql002Res = dao.selectIfaReleaseNoteUpdateSql002(sql002Req);

        // 取得結果が0件の場合
        if (sql002Res == null || StringUtils.isEmpty(sql002Res.getFileDirectory())) {
            res = IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_CMN_DIRECTORY_NOTFOUND,
                IfaCommonUtil.getMessage(ERRORS_CMN_DIRECTORY_NOTFOUND));
            return res;
        }

        String fileDirectory = sql002Res.getFileDirectory();

        res = IfaCommonUtil.createDataList(List.of(fileDirectory), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");

        return res;
    }

}
