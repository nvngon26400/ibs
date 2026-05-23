package com.sbisec.helios.ap.wholePortal.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.wholePortal.dao.model.IfaInfoDetailSql001RequestModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaInfoDetailSql002RequestModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaInfoDetailSql002ResponseModel;


/**
 * 画面ID：SUB01-03
 * 画面名：Information詳細
 * 2025/01/20 新規作成
 *
 * @author SCSK 江口
 */
@Mapper
public interface IfaInfoDetailMapper {

    /**
     * SQLID：SQL001
     * SQL名：お知らせ既読テーブル更新
     * SQLタイプ：update
     * リクエストクラス：IfaInfoDetailSql001RequestModel
     *
     * @param req パラメータ
     * @return 更新行数
     * @exception Exception システムエラー
     */
    public int updateIfaInfoDetailSql001(
            @Param("req")  IfaInfoDetailSql001RequestModel req
    ) throws Exception;

    /**
     * SQLID：SQL002
     * SQL名：お知らせ情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaInfoDetailSql002RequestModel
     * レスポンスクラス：IfaInfoDetailSql002ResponseModel
     *
     * @param req パラメータ
     * @return お知らせ情報
     * @exception Exception システムエラー
     */
    public List<IfaInfoDetailSql002ResponseModel> selectIfaInfoDetailSql002(
            @Param("req") IfaInfoDetailSql002RequestModel req
    ) throws Exception;

}
