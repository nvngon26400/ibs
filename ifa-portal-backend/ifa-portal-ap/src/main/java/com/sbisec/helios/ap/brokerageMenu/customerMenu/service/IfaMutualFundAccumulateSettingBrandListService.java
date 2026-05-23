package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListA007RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListA007ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0403-01 
 * 画面名：投信積立設定済銘柄一覧 
 * アクションID：A001 
 * アクション名：初期化 
 * アクションID：A002 
 * アクション名：初期化 
 * アクションID：A003 
 * アクション名：追加 
 * アクションID：A004 
 * アクション名：設定変更 
 *
 * @author nicksen.li
 *
 *         2025/04/03 新規作成
 */
public interface IfaMutualFundAccumulateSettingBrandListService extends Service {

    /**
     * アクションID：A001 
     * アクション名：初期化 Dto
     * リクエスト：IfaMutualFundAccumulateSettingBrandListA001RequestDto Dto
     * レスポンス：IfaMutualFundAccumulateSettingBrandListA001ResponseDto model
     * リクエスト：IfaMutualFundAccumulateSettingBrandListA001RequestModel model
     * レスポンス：IfaMutualFundAccumulateSettingBrandListA001ResponseModel
     *
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaMutualFundAccumulateSettingBrandListA001ResponseDto> initializeA001(
            IfaMutualFundAccumulateSettingBrandListA001RequestDto dtoReq) throws Exception;

    /**
     * アクションID：A002 
     * アクション名：初期化 Dto
     * リクエスト：IfaMutualFundAccumulateSettingBrandListA002RequestDto Dto
     * レスポンス：IfaMutualFundAccumulateSettingBrandListA002ResponseDto model
     * リクエスト：IfaMutualFundAccumulateSettingBrandListA002RequestModel model
     * レスポンス：IfaMutualFundAccumulateSettingBrandListA002ResponseModel
     *
     * @param dtoReq リクエストDTO
     * @return レスポンスデータ
     * @exception Exception 例外処理
     */
    public DataList<IfaMutualFundAccumulateSettingBrandListA002ResponseDto> initializeA002(
            IfaMutualFundAccumulateSettingBrandListA002RequestDto dtoReq) throws Exception;

    /**
     * アクションID：A003 
     * アクション名：追加 Dto
     * リクエスト：IfaMutualFundAccumulateSettingBrandListA003RequestDto Dto
     * レスポンス：IfaMutualFundAccumulateSettingBrandListA003ResponseDto model
     * リクエスト：IfaMutualFundAccumulateSettingBrandListA003RequestModel model
     * レスポンス：IfaMutualFundAccumulateSettingBrandListA003ResponseModel
     *
     * @param dtoReq リクエストDTO
     * @return レスポンスデータ
     * @exception Exception 例外処理
     */
    public DataList<IfaMutualFundAccumulateSettingBrandListA003ResponseDto> addA003(
            IfaMutualFundAccumulateSettingBrandListA003RequestDto dtoReq) throws Exception;

    /**
     * アクションID：A004 
     * アクション名：設定変更 Dto
     * リクエスト：IfaMutualFundAccumulateSettingBrandListA004RequestDto Dto
     * レスポンス：IfaMutualFundAccumulateSettingBrandListA004ResponseDto model
     * リクエスト：IfaMutualFundAccumulateSettingBrandListA004RequestModel model
     * レスポンス：IfaMutualFundAccumulateSettingBrandListA004ResponseModel
     *
     * @param dtoReq リクエストDTO
     * @return レスポンスデータ
     * @exception Exception 例外処理
     */
    public DataList<IfaMutualFundAccumulateSettingBrandListA004ResponseDto> changeA004(
            IfaMutualFundAccumulateSettingBrandListA004RequestDto dtoReq) throws Exception;

    /**
     * アクションID：A007 
     * アクション名：設定一括変更 Dto
     * リクエスト：IfaMutualFundAccumulateSettingBrandListA007RequestDto Dto
     * レスポンス：IfaMutualFundAccumulateSettingBrandListA007ResponseDto model
     * リクエスト：IfaMutualFundAccumulateSettingBrandListA007RequestModel model
     * レスポンス：IfaMutualFundAccumulateSettingBrandListA007ResponseModel
     *
     * @param dtoReq リクエストDTO
     * @return レスポンスデータ
     * @exception Exception 例外処理
     */
    public DataList<IfaMutualFundAccumulateSettingBrandListA007ResponseDto> bulkChangeA007(
            IfaMutualFundAccumulateSettingBrandListA007RequestDto dtoReq) throws Exception;

}
