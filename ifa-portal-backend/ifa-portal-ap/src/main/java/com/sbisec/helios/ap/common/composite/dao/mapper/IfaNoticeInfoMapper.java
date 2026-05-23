package com.sbisec.helios.ap.common.composite.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.common.composite.model.IfaNoticeInfoSql001RequestModel;
import com.sbisec.helios.ap.common.composite.model.IfaNoticeInfoSql001ResponseModel;
import com.sbisec.helios.ap.common.composite.model.IfaNoticeInfoSql002RequestModel;
import com.sbisec.helios.ap.common.composite.model.IfaNoticeInfoSql002ResponseModel;
import com.sbisec.helios.ap.common.composite.model.IfaNoticeInfoSql003RequestModel;
import com.sbisec.helios.ap.common.composite.model.IfaNoticeInfoSql003ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;



/**
 * 画面ID：CC016
 * 画面名：注意情報
 *
 * @author SCSK
 2024/06/20 新規作成
 */
@Mapper
public interface IfaNoticeInfoMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：注意情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaNoticeInfoSql001RequestModel
     * レスポンスクラス：IfaNoticeInfoSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaNoticeInfoSql001ResponseModel> selectIfaNoticeInfoSql001(
            @Param("req") IfaNoticeInfoSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：重要なお知らせによる取引制限取得
     * SQLタイプ：select
     * リクエストクラス：IfaNoticeInfoSql002RequestModel
     * レスポンスクラス：IfaNoticeInfoSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaNoticeInfoSql002ResponseModel> selectIfaNoticeInfoSql002(
            @Param("req") IfaNoticeInfoSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：重要なお知らせによる取引制限の制限番号に対する制限内容の表示文言取得
     * SQLタイプ：select
     * リクエストクラス：IfaNoticeInfoSql003RequestModel
     * レスポンスクラス：IfaNoticeInfoSql003ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaNoticeInfoSql003ResponseModel> selectIfaNoticeInfoSql003(
            @Param("req") IfaNoticeInfoSql003RequestModel req) throws Exception;
    
    
    
    
}
