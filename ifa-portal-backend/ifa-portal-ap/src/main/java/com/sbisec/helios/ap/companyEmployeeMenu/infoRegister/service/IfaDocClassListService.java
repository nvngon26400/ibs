package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA002RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA003RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA004RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA005RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA006RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA007RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA001ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA002ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA003ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA004ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA005ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA006ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA007ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0501_02-01
 * 画面名：資料種別一覧
 *
 * @author SCSK
 *     2024/02/05 新規作成
 */
public interface IfaDocClassListService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：カテゴリ一覧
     * Dtoリクエスト：IfaDocClassListA001RequestDto
     * Dtoレスポンス：IfaDocClassListA001ResponseDto
     * model リクエスト：IfaDocClassListA001RequestModel
     * model レスポンス：IfaDocClassListA001ResponseModel
     *
     * @param dtoReq リクエストパラメタ
     * @return レスポンスパラメタ
     * @exceptionException カテゴリ一覧取得処理で例外が発生した場合
     */
    public DataList<IfaDocClassListA001ResponseDto> categoryListA001(IfaDocClassListA001RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：カテゴリ登録確認
     * Dtoリクエスト：IfaDocClassListA002RequestDto
     * model リクエスト：IfaDocClassListA002RequestModel
     * model レスポンス：IfaDocClassListA002ResponseModel
     *
     * @param dtoReq リクエストパラメタ
     * @return レスポンスパラメタ
     * @exceptionException カテゴリ登録確認処理で例外が発生した場合
     */
    public DataList<IfaDocClassListA002ResponseDto> categoryRegistrationConfirmA002(IfaDocClassListA002RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A003
     * アクション名：カテゴリ登録
     * Dtoリクエスト：IfaDocClassListA003RequestDto
     * Dtoレスポンス：IfaDocClassListA003ResponseDto
     * model リクエスト：IfaDocClassListA003RequestModel
     * model レスポンス：IfaDocClassListA003ResponseModel
     *
     * @param dtoReq リクエストパラメタ
     * @return レスポンスパラメタ
     * @exceptionException カテゴリ登録処理で例外が発生した場合
     */
    public DataList<IfaDocClassListA003ResponseDto> categoryRegistrationA003(IfaDocClassListA003RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A004
     * アクション名：カテゴリ更新確認
     * Dto リクエスト：IfaDocClassListA004RequestDto
     * model リクエスト：IfaDocClassListA004RequestModel
     * model レスポンス：IfaDocClassListA004ResponseModel
     *
     * @param dtoReq リクエストパラメタ
     * @return レスポンスパラメタ
     * @exceptionException カテゴリ更新確認処理で例外が発生した場合
     */
    public DataList<IfaDocClassListA004ResponseDto> categoryUpdateConfirmA004(IfaDocClassListA004RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A005
     * アクション名：カテゴリ更新
     * Dto リクエスト：IfaDocClassListA005RequestDto
     * Dto レスポンス：IfaDocClassListA005ResponseDto
     * model リクエスト：IfaDocClassListA005RequestModel
     * model レスポンス：IfaDocClassListA005ResponseModel
     *
     * @param dtoReq リクエストパラメタ
     * @return レスポンスパラメタ
     * @exceptionException カテゴリ更新処理で例外が発生した場合
     */
    public DataList<IfaDocClassListA005ResponseDto> categoryUpdateA005(IfaDocClassListA005RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A006
     * アクション名：カテゴリ削除確認
     * Dto リクエスト：IfaDocClassListA006RequestDto
     * model リクエスト：IfaDocClassListA006RequestModel
     * model レスポンス：IfaDocClassListA006ResponseModel
     *
     * @param dtoReq リクエストパラメタ
     * @return レスポンスパラメタ
     * @exceptionException カテゴリ削除確認処理で例外が発生した場合
     */
    public DataList<IfaDocClassListA006ResponseDto> categoryDeletionConfirmA006(IfaDocClassListA006RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A007
     * アクション名：カテゴリ削除
     * Dto リクエスト：IfaDocClassListA007RequestDto
     * Dto レスポンス：IfaDocClassListA007ResponseDto
     * model リクエスト：IfaDocClassListA007RequestModel
     * model レスポンス：IfaDocClassListA007ResponseModel
     *
     * @param dtoReq リクエストパラメタ
     * @return レスポンスパラメタ
     * @exceptionException カテゴリ削除処理で例外が発生した場合
     */
    public DataList<IfaDocClassListA007ResponseDto> categoryDeletionA007(IfaDocClassListA007RequestDto dtoReq)
            throws Exception;
    
}
