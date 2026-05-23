package com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.IfaReleaseNoteUpdateDao;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.mapper.IfaReleaseNoteUpdateMapper;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteUpdateSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteUpdateSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteUpdateSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteUpdateSql002ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteUpdateSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteUpdateSql004RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteUpdateSql005RequestModel;

/**
 * SUB0512-03 リリースノート更新
 *
 * @author SBI大連 夏
 * @date 2025/10/23
 */

@Component
public class IfaReleaseNoteUpdateDaoImpl implements IfaReleaseNoteUpdateDao {

    @Autowired
    private IfaReleaseNoteUpdateMapper mapper;

    /**
     * SQLID:SQL001
     * SQL名：リリースノート詳細取得
     * SQLタイプ：select
     * リクエストクラス：IfaReleaseNoteUpdateSql001RequestModel
     * レスポンスクラス：IfaReleaseNoteUpdateSql001ResponseModel
     * 
     * @param req リクエスト
     * @return res レスポンス
     * @throws Exception システムエラー
     */
    @Override
    public List<IfaReleaseNoteUpdateSql001ResponseModel> selectIfaReleaseNoteUpdateSql001(
        IfaReleaseNoteUpdateSql001RequestModel req) throws Exception {

        List<IfaReleaseNoteUpdateSql001ResponseModel> res = new ArrayList<IfaReleaseNoteUpdateSql001ResponseModel>();
        res = mapper.selectIfaReleaseNoteUpdateSql001(req);

        return res;
    }

    /**
     * SQLID:SQL002
     * SQL名：リリースノートファイルディレクトリ取得
     * SQLタイプ：select
     * リクエストクラス：IfaReleaseNoteUpdateSql002RequestModel
     * レスポンスクラス：IfaReleaseNoteUpdateSql002ResponseModel
     * 
     * @param req リクエスト
     * @return res レスポンス
     * @throws Exception システムエラー
     */
    @Override
    public IfaReleaseNoteUpdateSql002ResponseModel selectIfaReleaseNoteUpdateSql002(
        IfaReleaseNoteUpdateSql002RequestModel req) throws Exception {

        IfaReleaseNoteUpdateSql002ResponseModel res = new IfaReleaseNoteUpdateSql002ResponseModel();
        res = mapper.selectIfaReleaseNoteUpdateSql002(req);

        return res;
    }

    /**
     * SQLID:SQL003
     * SQL名：リリースノート内容削除
     * SQLタイプ：select
     * リクエストクラス：IfaReleaseNoteUpdateSql003RequestModel
     * 
     * @param req リクエスト
     * @return res レスポンス
     * @throws Exception システムエラー
     */
    @Override
    public int deleteIfaReleaseNoteUpdateSql003(IfaReleaseNoteUpdateSql003RequestModel req) throws Exception {

        return mapper.deleteIfaReleaseNoteUpdateSql003(req);
    }

    /**
     * SQLID:SQL004
     * SQL名：リリースノート内容登録
     * SQLタイプ：select
     * リクエストクラス：IfaReleaseNoteUpdateSql004RequestModel
     * 
     * @param req リクエスト
     * @return res レスポンス
     * @throws Exception システムエラー
     */
    @Override
    public int insertIfaReleaseNoteUpdateSql004(List<IfaReleaseNoteUpdateSql004RequestModel> req) throws Exception {

        return mapper.insertIfaReleaseNoteUpdateSql004(req);
    }

    /**
     * SQLID:SQL005
     * SQL名：リリースノート更新
     * SQLタイプ：select
     * リクエストクラス：IfaReleaseNoteUpdateSql005RequestModel
     * 
     * @param req リクエスト
     * @return res レスポンス
     * @throws Exception システムエラー
     */
    @Override
    public int updateIfaReleaseNoteUpdateSql005(IfaReleaseNoteUpdateSql005RequestModel req) throws Exception {

        return mapper.updateIfaReleaseNoteUpdateSql005(req);
    }

}
