package com.sbisec.helios.ap.releaseNote.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.releaseNote.dao.IfaReleaseNoteDetailDao;
import com.sbisec.helios.ap.releaseNote.dao.mapper.IfaReleaseNoteDetailMapper;
import com.sbisec.helios.ap.releaseNote.dao.model.IfaReleaseNoteDetailSql002ResponseModel;

/**
 * 画面ID：SUB00-07_2
 * 画面名：リリースノート詳細
 * 2025/11/04 新規作成
 *
 * @author 大連 葉
 */
@Component
public class IfaReleaseNoteDetailDaoImpL  extends RowSelectableDao implements IfaReleaseNoteDetailDao{

    @Autowired
    private IfaReleaseNoteDetailMapper mapper;

    /**
     * SQLID：Sql001
     * SQL名：リリースノートファイルディレクトリ取得
     * SQLタイプ：select
     * レスポンスクラス：String
     *
     * @return ファイルディレクトリ
     * @exception Exception ファイルディレクトリ取得処理で例外が発生した場合
     */
    public String selectIfaReleaseNoteFileDirectorySql001() throws Exception {

        return mapper.selectIfaReleaseNoteFileDirectorySql001();
    }

    /**
     * SQLID：Sql002
     * SQL名：リリースノート詳細ファイル取得
     * SQLタイプ：select
     * リクエストクラス：String
     * レスポンスクラス：IfaReleaseNoteDetailSql002ResponseModel
     *
     * @param releaseNoteNo リリースノートNo
     * @return レスポンスパラメタ
     * @exception Exception ファイルディレクトリ取得処理で例外が発生した場合
     */
    public DataList<IfaReleaseNoteDetailSql002ResponseModel> selectIfaReleaseNoteDetailSql002(String releaseNoteNo)
            throws Exception {

        DataList<IfaReleaseNoteDetailSql002ResponseModel> res = new DataList<IfaReleaseNoteDetailSql002ResponseModel>();

        res.setDataList(mapper.selectIfaReleaseNoteDetailSql002(releaseNoteNo));
        return res;
    }

}
