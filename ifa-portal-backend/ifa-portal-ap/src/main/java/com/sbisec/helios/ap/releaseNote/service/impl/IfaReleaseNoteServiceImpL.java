package com.sbisec.helios.ap.releaseNote.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.releaseNote.dao.IfaReleaseNoteDao;
import com.sbisec.helios.ap.releaseNote.dao.model.IfaReleaseNoteSql002ResponseModel;
import com.sbisec.helios.ap.releaseNote.dao.model.IfaReleaseNoteSql004RequestModel;
import com.sbisec.helios.ap.releaseNote.dto.IfaReleaseNoteA001RequestDto;
import com.sbisec.helios.ap.releaseNote.dto.IfaReleaseNoteA001ResponseDto;
import com.sbisec.helios.ap.releaseNote.dto.IfaReleaseNoteA002RequestDto;
import com.sbisec.helios.ap.releaseNote.dto.IfaReleaseNoteA002ResponseDto;
import com.sbisec.helios.ap.releaseNote.dto.IfaReleaseNoteA003RequestDto;
import com.sbisec.helios.ap.releaseNote.dto.IfaReleaseNoteA003ResponseDto;
import com.sbisec.helios.ap.releaseNote.service.IfaReleaseNoteService;

/**
 * 画面ID：SUB00-07_1
 * 画面名：リリースノート
 * 2025/10/27 新規作成
 *
 * @author 大連 葉
 */
@Component(value="cmpIfaReleaseNoteService")
public class IfaReleaseNoteServiceImpL implements IfaReleaseNoteService{

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaReleaseNoteServiceImpL.class);

    /** データベースの更新に失敗しました。 */
    private static final String ERRORS_PROCESSING_FAILED = "errors.processingFailed";

    private static final String ERRORS_PROCESSING_FAILED_PARAM = "次回表示設定の更新";

    @Autowired
    private IfaReleaseNoteDao dao;

    /**
     * アクションID：A001
     * アクション名：初期化
     * リクエスト：IfaReleaseNoteA001RequestDto
     * レスポンス：IfaReleaseNoteA001ResponseDto
     *
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaReleaseNoteA001ResponseDto> initializeA001(IfaReleaseNoteA001RequestDto dtoReq)
            throws Exception {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaReleaseNoteServiceImpL.initializeA001");
        }

        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String userId = userAccount.getUserId();
        // ①リリースノートの次回表示フラグを取得する。
        String nextDispFlg = dao.selectIfaReleaseNoteNextDispFlgSql001(userId);
        // 取得結果が空の場合：次回表示フラグに '1'(表示) をセットする。
        if(StringUtil.isNullOrEmpty(nextDispFlg)) {
            nextDispFlg = "1";
        }
        // ②リリースノート一覧を取得する。
        DataList<IfaReleaseNoteSql002ResponseModel> sql002Res = dao.selectIfaReleaseNoteSql002(dtoReq.getDisplayYear());

        // ③リリースノート表示対象年を取得する。
        String minYear = dao.selectIfaReleaseNoteDisplayYearSql005();
        // 現在年<SQL005.最小年数の場合、最小年数を現在年に更新する
        int currentYear = LocalDate.now().getYear();
        if (currentYear < Integer.parseInt(minYear)) {
            minYear = String.valueOf(currentYear);
        }

        // ④リリースノートの閲覧状況を更新する。
        dao.updateIfaReleaseNoteSql003(userId);

        // レスポンスの設定
        IfaReleaseNoteA001ResponseDto res = new IfaReleaseNoteA001ResponseDto();
        List<IfaReleaseNoteA001ResponseDto> resList = new ArrayList<IfaReleaseNoteA001ResponseDto>();
        res.setNextDispFlg(nextDispFlg);
        if (sql002Res.getDataList().isEmpty()) {
            // SQL002の実行結果が0件の場合
            res.setIfaReleaseNoteList(new ArrayList <IfaReleaseNoteSql002ResponseModel>());
        } else {
            res.setIfaReleaseNoteList(sql002Res.getDataList());
        }
        res.setMinYear(minYear);
        resList.add(res);

        DataList<IfaReleaseNoteA001ResponseDto> dtoRes = new DataList<IfaReleaseNoteA001ResponseDto>();
        dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), StringUtil.EMPTY_STRING);
        return dtoRes;
    }

    /**
     * アクションID：A002
     * アクション名：表示対象年選択
     * リクエスト：IfaReleaseNoteA002RequestDto
     * レスポンス：IfaReleaseNoteA002ResponseDto
     *
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 表示対象年選択処理で例外が発生した場合
     */
    public DataList<IfaReleaseNoteA002ResponseDto> selectDisplayYearA002(IfaReleaseNoteA002RequestDto dtoReq)
            throws Exception {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaExternalLinkServiceImpL.selectDisplayYearA002");
        }

        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String userId = userAccount.getUserId();
        // ②リリースノート一覧を取得する。
        DataList<IfaReleaseNoteSql002ResponseModel> sql002Res = dao.selectIfaReleaseNoteSql002(dtoReq.getDisplayYear());
        // ③リリースノートの閲覧状況を更新する。
        dao.updateIfaReleaseNoteSql003(userId);

        // レスポンスの設定
        IfaReleaseNoteA002ResponseDto res = new IfaReleaseNoteA002ResponseDto();
        List<IfaReleaseNoteA002ResponseDto> resList = new ArrayList<IfaReleaseNoteA002ResponseDto>();
        if (sql002Res.size() == 0) {
            // SQL002の実行結果が0件の場合
            res.setIfaReleaseNoteList(new ArrayList <IfaReleaseNoteSql002ResponseModel>());
        } else {
            res.setIfaReleaseNoteList(sql002Res.getDataList());
        }
        resList.add(res);

        DataList<IfaReleaseNoteA002ResponseDto> dtoRes = new DataList<IfaReleaseNoteA002ResponseDto>();
        dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), StringUtil.EMPTY_STRING);
        return dtoRes;
    }

    /**
     * アクションID：A003
     * アクション名：次回表示フラグ更新
     * リクエスト：IfaReleaseNoteA003RequestDto
     * レスポンス：IfaReleaseNoteA003ResponseDto
     *
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 次回表示フラグ更新処理で例外が発生した場合
     */
    public DataList<IfaReleaseNoteA003ResponseDto> updateNextDispFlgA003(IfaReleaseNoteA003RequestDto dtoReq)
            throws Exception {
        DataList<IfaReleaseNoteA003ResponseDto> dtoRes = new DataList<IfaReleaseNoteA003ResponseDto>();
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        IfaReleaseNoteSql004RequestModel ifaReleaseNoteSql004RequestModel = new IfaReleaseNoteSql004RequestModel();
        ifaReleaseNoteSql004RequestModel.setNextDispFlg(dtoReq.getNextDispFlg());
        ifaReleaseNoteSql004RequestModel.setUserId(userAccount.getUserId());
        try {
            // SQL003を実行
            // 異常時はエラーメッセージを出力
            int sql004Res = dao.updateIfaReleaseNoteNextDispFlgSql004(ifaReleaseNoteSql004RequestModel);
            if (sql004Res == 0) {
                // DB登録エラーを表示する。
                dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                        ErrorLevel.FATAL, ERRORS_PROCESSING_FAILED,
                        IfaCommonUtil.getMessage(ERRORS_PROCESSING_FAILED, new String[] {ERRORS_PROCESSING_FAILED_PARAM})
                    );
                return dtoRes;
            }
        } catch (Exception e) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, ERRORS_PROCESSING_FAILED,
                    IfaCommonUtil.getMessage(ERRORS_PROCESSING_FAILED, new String[] {ERRORS_PROCESSING_FAILED_PARAM})
                );
        }
        return dtoRes;
    }
}
