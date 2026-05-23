package com.sbisec.helios.ap.releaseNote.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.releaseNote.dao.IfaReleaseNoteDao;
import com.sbisec.helios.ap.releaseNote.dao.mapper.IfaReleaseNoteMapper;
import com.sbisec.helios.ap.releaseNote.dao.model.IfaReleaseNoteSql002ResponseModel;
import com.sbisec.helios.ap.releaseNote.dao.model.IfaReleaseNoteSql004RequestModel;

/**
 * 画面ID：SUB00-07_1
 * 画面名：リリースノート
 * 2025/10/27 新規作成
 *
 * @author 大連 葉
 */
@Component
public class IfaReleaseNoteDaoImpL extends RowSelectableDao implements IfaReleaseNoteDao{

    @Autowired
    private IfaReleaseNoteMapper mapper;

    /**
     * SQLID：Sql001
     * SQL名：リリースノート次回表示フラグ取得
     * SQLタイプ：select
     * リクエストクラス：String
     * レスポンスクラス：String
     *
     * @param userId ユーザID
     * @return 次回表示フラグ
     * @exception Exception 次回表示フラグ取得処理で例外が発生した場合
     */
    public String selectIfaReleaseNoteNextDispFlgSql001(String userId) throws Exception {
        return mapper.selectIfaReleaseNoteNextDispFlgSql001(userId);
    }

    /**
     * SQLID：Sql002
     * SQL名：リリースノート一覧取得
     * SQLタイプ：select
     * リクエストクラス：String
     * レスポンスクラス：IfaReleaseNoteSql002ResponseModel
     *
     * @param displayYear 画面表示開始日
     * @return レスポンスパラメタ
     * @exception Exception 一覧取得処理で例外が発生した場合
     */
    public DataList<IfaReleaseNoteSql002ResponseModel> selectIfaReleaseNoteSql002(String displayYear) throws Exception {

        DataList<IfaReleaseNoteSql002ResponseModel> res = new DataList<IfaReleaseNoteSql002ResponseModel>();

        res.setDataList(mapper.selectIfaReleaseNoteSql002(displayYear));
        return res;
    }

    /**
     * SQLID：Sql003
     * SQL名：リリースノート閲覧状況更新
     * SQLタイプ：update
     * リクエストクラス：String
     *
     * @param userId ユーザID
     * @return 更新レコード数
     * @exception Exception 閲覧状況更新処理で例外が発生した場合
     */
    public int updateIfaReleaseNoteSql003(String userId) throws Exception {

        int rtnCnt = 0;
        rtnCnt = mapper.updateIfaReleaseNoteSql003(userId);
        return rtnCnt;
    }

    /**
     * SQLID：Sql004
     * SQL名：リリースノート次回表示フラグ更新
     * SQLタイプ：update
     * リクエストクラス：IfaReleaseNoteSql004RequestModel
     *
     * @param req リクエストパラメタ
     * @return 更新レコード数
     * @exception Exception 次回表示フラグ更新処理で例外が発生した場合
     */
    public int updateIfaReleaseNoteNextDispFlgSql004(IfaReleaseNoteSql004RequestModel req) throws Exception {

        int rtnCnt = 0;
        rtnCnt = mapper.updateIfaReleaseNoteNextDispFlgSql004(req);
        return rtnCnt;
    }

    /**
     * SQLID：Sql005
     * SQL名：リリースノート表示対象年取得
     * SQLタイプ：select
     * レスポンスクラス：String
     *
     * @return 最小年数
     * @exception Exception 最小年数取得処理で例外が発生した場合
     */
    public String selectIfaReleaseNoteDisplayYearSql005() throws Exception {
        return mapper.selectIfaReleaseNoteDisplayYearSql005();
    }
}
