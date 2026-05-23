package com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.IfaReleaseNoteEmployeeDao;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.mapper.IfaReleaseNoteEmployeeMapper;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteEmployeeSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteEmployeeSql002ResponseModel;

/**
 * 画面ID：SUB0512-01
 * 画面名：リリースノート(社員用)
 * 2025/11/06 新規作成
 *
 * @author 大連 葉
 */
@Component
public class IfaReleaseNoteEmployeeDaoImpL extends RowSelectableDao implements IfaReleaseNoteEmployeeDao {

    @Autowired
    private IfaReleaseNoteEmployeeMapper mapper;

    /**
     * SQLID：Sql001
     * SQL名：リリースノート一覧取得
     * SQLタイプ：select
     * リクエストクラス：String
     * レスポンスクラス：IfaReleaseNoteEmployeeSql001ResponseModel
     *
     * @param displayYear 画面表示開始日
     * @return レスポンスパラメタ
     * @exception Exception 一覧取得処理で例外が発生した場合
     */
    public DataList<IfaReleaseNoteEmployeeSql001ResponseModel> selectIfaReleaseNoteEmployeeSql001(String displayYear)
            throws Exception {

        DataList<IfaReleaseNoteEmployeeSql001ResponseModel> res = new DataList<IfaReleaseNoteEmployeeSql001ResponseModel>();

        res.setDataList(mapper.selectIfaReleaseNoteEmployeeSql001(displayYear));
        return res;
    }

    /**
     * SQLID：Sql002
     * SQL名：リリースノート表示対象年取得
     * SQLタイプ：select
     * レスポンスクラス：IfaReleaseNoteEmployeeSql002ResponseModel
     *
     * @return レスポンスパラメタ
     * @throws Exception 一覧取得処理で例外が発生した場合
     */
    @Override
    public DataList<IfaReleaseNoteEmployeeSql002ResponseModel> selectIfaReleaseNoteEmployeeSql002() throws Exception {

        DataList<IfaReleaseNoteEmployeeSql002ResponseModel> res = new DataList<IfaReleaseNoteEmployeeSql002ResponseModel>();

        res.setDataList(mapper.selectIfaReleaseNoteDisplayYearSql002());
        return res;
    }

}
