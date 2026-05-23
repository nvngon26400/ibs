package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaRegisterInfoRequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaRegisterInfoResponseSql001Model;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaRegisterInfoResponseSql002Model;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaRegisterInfoResponseSql003Model;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaRegisterInfoResponseSql004Model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 登録情報
 * 2025/02/21 新規作成
 *
 * @author 大連 苗
 */
@Mapper
public interface IfaRegisterInfoMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：優先表示分類情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaRegisterInfoRequestModel
     * レスポンスクラス：IfaRegisterInfoResponseSql001Model
     *
     * @param req {@code IfaRegisterInfoRequestModel }
     * @return {@code IfaRegisterInfoResponseSql001Model}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public List<IfaRegisterInfoResponseSql001Model> selectIfaRegisterInfoSql001(
            @Param("req") IfaRegisterInfoRequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：優先表示分類ヘッダ取得
     * SQLタイプ：select
     * リクエストクラス：IfaRegisterInfoRequestModel
     * レスポンスクラス：IfaRegisterInfoResponseSql002Model
     *
     * @param req {@code IfaRegisterInfoRequestModel }
     * @return {@code IfaRegisterInfoResponseSql002Model}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public List<IfaRegisterInfoResponseSql002Model> selectIfaRegisterInfoSql002(
            @Param("req") IfaRegisterInfoRequestModel req) throws Exception;
    
    
    /**
     * SQLID：Sql003
     * SQL名：分類情報一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaRegisterInfoRequestModel
     * レスポンスクラス：IfaRegisterInfoResponseSql003Model
     *
     * @param req {@code IfaRegisterInfoRequestModel }
     * @return {@code IfaRegisterInfoResponseSql003Model}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public List<IfaRegisterInfoResponseSql003Model> selectIfaRegisterInfoSql003(
            @Param("req") IfaRegisterInfoRequestModel req) throws Exception;
    
    
    /**
     * SQLID：Sql004
     * SQL名：登録情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaRegisterInfoRequestModel
     * レスポンスクラス：IfaRegisterInfoResponseSql004Model
     *
     * @param req {@code IfaRegisterInfoRequestModel }
     * @return {@code IfaRegisterInfoResponseSql004Model}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public List<IfaRegisterInfoResponseSql004Model> selectIfaRegisterInfoSql004(
            @Param("req") IfaRegisterInfoRequestModel req) throws Exception;
    
}
