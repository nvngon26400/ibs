package com.sbisec.helios.ap.releaseNote.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.releaseNote.dao.IfaReleaseNoteDetailDao;
import com.sbisec.helios.ap.releaseNote.dao.model.IfaReleaseNoteDetailSql002ResponseModel;
import com.sbisec.helios.ap.releaseNote.dto.IfaReleaseNoteDetailA001RequestDto;
import com.sbisec.helios.ap.releaseNote.dto.IfaReleaseNoteDetailA001ResponseDto;
import com.sbisec.helios.ap.releaseNote.service.IfaReleaseNoteDetailService;

/**
 * 画面ID：SUB00-07_2
 * 画面名：リリースノート詳細
 * 2025/11/04 新規作成
 *
 * @author 大連 葉
 */
@Component(value="cmpIfaReleaseNoteDetailService")
public class IfaReleaseNoteDetailServiceImpL implements IfaReleaseNoteDetailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaReleaseNoteDetailServiceImpL.class);

    /** ファイルの格納先が取得できないため、処理を中止しました。 */
    private static final String ERRORS_DIRECTORY_NOTFOUND = "errors.cmn.directory.notfound";
    /** ファイルを取得できませんでした。 */
    private static final String ERRORS_FILE_NOTFOUND = "errors.cmn.file.notfound";

    @Autowired
    private IfaReleaseNoteDetailDao dao;

    /**
     * アクションID：A001
     * アクション名：初期化
     * リクエスト：IfaReleaseNoteDetailA001RequestDto
     * レスポンス：IfaReleaseNoteDetailA001ResponseDto
     *
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaReleaseNoteDetailA001ResponseDto> initializeA001(IfaReleaseNoteDetailA001RequestDto dtoReq)
            throws Exception {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaReleaseNoteDetailServiceImpL.initializeA001");
        }

        IfaReleaseNoteDetailA001ResponseDto res = new IfaReleaseNoteDetailA001ResponseDto(); 
        List<IfaReleaseNoteDetailA001ResponseDto> resList  = new ArrayList<IfaReleaseNoteDetailA001ResponseDto>(); 
        DataList<IfaReleaseNoteDetailA001ResponseDto> dtoRes = new DataList<IfaReleaseNoteDetailA001ResponseDto>();

        // ①ファイルディレクトリ情報を取得する。
        String fileDir = dao.selectIfaReleaseNoteFileDirectorySql001();
        // 取得結果が0件の場合、エラーメッセージを表示して処理を終了する。
        if(StringUtil.isNullOrEmpty(fileDir)) {
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.FATAL, ERRORS_DIRECTORY_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_DIRECTORY_NOTFOUND, new String[] {}));
            return dtoRes;
        }
        // ②リリースノートの詳細ファイル情報を取得する。
        DataList<IfaReleaseNoteDetailSql002ResponseModel> sql002Res = dao.selectIfaReleaseNoteDetailSql002(dtoReq.getReleaseNoteNo());
        // 取得結果が0件の場合、エラーメッセージを表示して処理を終了する。
        if (sql002Res == null || sql002Res.getDataList().isEmpty()) {
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.FATAL, ERRORS_FILE_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_FILE_NOTFOUND, new String[] {}));
            return dtoRes;
        }
        // ③ ①のディレクトリから②の詳細ファイルを取得し、画面に表示する。
        res.setFileDir(fileDir);
        res.setFileType(sql002Res.getDataList().get(0).getFileType());
        res.setPdfSize(sql002Res.getDataList().get(0).getPdfSize());
        res.setPdfDirection(sql002Res.getDataList().get(0).getPdfDirection());
        res.setDetailedFile(sql002Res.getDataList().get(0).getDetailedFile());
        resList.add(res);
        dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), StringUtil.EMPTY_STRING);
        return dtoRes;
    }

}
