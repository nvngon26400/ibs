package com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.IfaReleaseNoteRegisterDao;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteRegisterSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteRegisterSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteRegisterSql002ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteRegisterA007aResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteRegisterA007bRequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteRegisterA007bResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.service.IfaReleaseNoteRegisterService;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.util.IfaReleaseNoteException;
import io.micrometer.core.instrument.util.StringUtils;

/**
 * SUB0512-02 リリースノート登録
 *
 * @author SBI大連 夏
 * @date 2025/10/23
 */

@Component(value = "cmpIfaReleaseNoteRegisterService")
public class IfaReleaseNoteRegisterServiceImpl implements IfaReleaseNoteRegisterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaReleaseNoteRegisterServiceImpl.class);

    /** 添付ファイルの格納先が取得できないため、処理を中止しました。 */
    private static final String ERRORS_CMN_DIRECTORY_NOTFOUND = "errors.cmn.directory.notfound";

    /** 機能ID. */
    private static final String RELEASE_NOTE_FUNC_ID = "M14";

    /** カテゴリID. */
    private static final String CATEGORY_ID_ZERO = "0";

    @Autowired
    private IfaReleaseNoteRegisterDao dao;

    @Autowired
    private IfaReleaseNoteRegisterServiceImplEx serviceImpLEx;

    /**
     * アクションID：A007a
     * アクション名：登録
     * Dto レスポンス：IfaReleaseNoteRegisterA007aResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 処理で例外が発生した場合
     */
    @Override
    public DataList<IfaReleaseNoteRegisterA007aResponseDto> selectA007a() throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaReleaseNoteRegisterServiceImpl.selectA007a");
        }

        DataList<IfaReleaseNoteRegisterA007aResponseDto> res = new DataList<IfaReleaseNoteRegisterA007aResponseDto>();
        IfaReleaseNoteRegisterA007aResponseDto resDto = new IfaReleaseNoteRegisterA007aResponseDto();

        IfaReleaseNoteRegisterSql001ResponseModel sql001Res = new IfaReleaseNoteRegisterSql001ResponseModel();
        // リリースノートNoを取得する。
        sql001Res = dao.selectIfaReleaseNoteRegisterSql001();

        resDto.setReleaseNoteNo(sql001Res.getReleaseNoteNo());
        res.setDataList(List.of(resDto));

        return res;
    }

    /**
     * アクションID：A007b
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
    @Override
    public DataList<IfaReleaseNoteRegisterA007bResponseDto> registerA007b(IfaReleaseNoteRegisterA007bRequestDto dtoReq)
        throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaReleaseNoteRegisterServiceImpl.registerA007b");
        }

        DataList<IfaReleaseNoteRegisterA007bResponseDto> res = new DataList<IfaReleaseNoteRegisterA007bResponseDto>();

        // 必須入力チェック：タイトル、画面表示開始日!＝ "" or !NULL
        if (StringUtils.isEmpty(dtoReq.getTitle()) || StringUtils.isEmpty(dtoReq.getDisplayedDate())) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, null, null);
        }

        try {
            res = serviceImpLEx.registerA007bEx(dtoReq);
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
     * @exception Exception 処理で例外が発生した場合
     */
    @Override
    public DataList<String> getReleaseNoteFileDir() throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaReleaseNoteRegisterServiceImpl.getReleaseNoteFileDir");
        }

        DataList<String> res = new DataList<String>();

        // ファイルディレクトリ情報を取得する。
        IfaReleaseNoteRegisterSql002RequestModel sql002Req = new IfaReleaseNoteRegisterSql002RequestModel();
        sql002Req.setFunctionId(RELEASE_NOTE_FUNC_ID);
        sql002Req.setCategoryId(CATEGORY_ID_ZERO);

        IfaReleaseNoteRegisterSql002ResponseModel sql002Res = new IfaReleaseNoteRegisterSql002ResponseModel();
        sql002Res = dao.selectIfaReleaseNoteRegisterSql002(sql002Req);

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
