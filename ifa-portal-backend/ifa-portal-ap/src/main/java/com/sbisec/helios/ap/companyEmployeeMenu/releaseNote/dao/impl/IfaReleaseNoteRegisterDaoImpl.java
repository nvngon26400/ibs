package com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.IfaReleaseNoteRegisterDao;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.mapper.IfaReleaseNoteRegisterMapper;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteRegisterSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteRegisterSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteRegisterSql002ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteRegisterSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteRegisterSql004RequestModel;

/**
 * SUB0512-02 リリースノート登録
 *
 * @author SBI大連 夏
 * @date 2025/10/23
 */

@Component
public class IfaReleaseNoteRegisterDaoImpl implements IfaReleaseNoteRegisterDao {

    @Autowired
    private IfaReleaseNoteRegisterMapper mapper;

    /**
     * SQLID:SQL001
     * SQL名：リリースノートNo取得
     * SQLタイプ：select
     * レスポンスクラス：IfaReleaseNoteRegisterSql001ResponseModel
     * 
     * @return res レスポンス
     * @throws Exception システムエラー
     */
    @Override
    public IfaReleaseNoteRegisterSql001ResponseModel selectIfaReleaseNoteRegisterSql001() throws Exception {

        IfaReleaseNoteRegisterSql001ResponseModel res = new IfaReleaseNoteRegisterSql001ResponseModel();
        res = mapper.selectIfaReleaseNoteRegisterSql001();
        return res;
    }

    /**
     * SQLID:SQL002
     * SQL名：リリースノートファイルディレクトリ取得
     * SQLタイプ：select
     * リクエストクラス：IfaReleaseNoteRegisterSql002RequestModel
     * レスポンスクラス：IfaReleaseNoteRegisterSql002ResponseModel
     * 
     * @param req リクエスト
     * @return res レスポンス
     * @throws Exception システムエラー
     */
    @Override
    public IfaReleaseNoteRegisterSql002ResponseModel selectIfaReleaseNoteRegisterSql002(
        IfaReleaseNoteRegisterSql002RequestModel req) throws Exception {

        IfaReleaseNoteRegisterSql002ResponseModel res = new IfaReleaseNoteRegisterSql002ResponseModel();
        res = mapper.selectIfaReleaseNoteRegisterSql002(req);
        return res;
    }

    /**
     * SQLID:SQL003
     * SQL名：リリースノート登録
     * SQLタイプ：insert
     * リクエストクラス：IfaReleaseNoteRegisterSql003RequestModel
     * 
     * @param req リクエスト
     * @return res レスポンス
     * @throws Exception システムエラー
     */
    @Override
    public int insertIfaReleaseNoteRegisterSql003(IfaReleaseNoteRegisterSql003RequestModel req) throws Exception {

        return mapper.insertIfaReleaseNoteRegisterSql003(req);
    }

    /**
     * SQLID:SQL004
     * SQL名：リリースノート内容登録
     * SQLタイプ：insert
     * リクエストクラス：IfaReleaseNoteRegisterSql003RequestModel
     * 
     * @param req リクエスト
     * @return res レスポンス
     * @throws Exception システムエラー
     */
    @Override
    public int insertIfaReleaseNoteRegisterSql004(List<IfaReleaseNoteRegisterSql004RequestModel> req) throws Exception {

        return mapper.insertIfaReleaseNoteRegisterSql004(req);
    }

}
