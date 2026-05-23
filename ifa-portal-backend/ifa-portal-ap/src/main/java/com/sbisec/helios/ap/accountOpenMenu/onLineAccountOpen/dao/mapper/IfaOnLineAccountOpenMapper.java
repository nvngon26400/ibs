package com.sbisec.helios.ap.accountOpenMenu.onLineAccountOpen.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.accountOpenMenu.onLineAccountOpen.dao.model.IfaOnLineAccountOpenSql001RequestModel;
import com.sbisec.helios.ap.accountOpenMenu.onLineAccountOpen.dao.model.IfaOnLineAccountOpenSql001ResponseModel;


/**
 * 画面ID：SUB0207_0201
 * 画面名：オンライン口座開設
 *
 * @author SCSK 木村
 2025/02/06 新規作成
 */
@Mapper
public interface IfaOnLineAccountOpenMapper {

    /**
     * SQLID：SQL001
     * SQL名：Kintone口座開設URL取得
     * SQLタイプ：select
     * リクエストクラス：IfaOnLineAccountOpenSql001RequestModel
     * レスポンスクラス：IfaOnLineAccountOpenSql001ResponseModel
     *
     * @param req パラメータ
     * @return URL
     * @exception Exception システムエラー
     */
    public List<IfaOnLineAccountOpenSql001ResponseModel> selectIfaOnLineAccountOpenSql001(
            @Param("req") IfaOnLineAccountOpenSql001RequestModel req
    ) throws Exception;

}