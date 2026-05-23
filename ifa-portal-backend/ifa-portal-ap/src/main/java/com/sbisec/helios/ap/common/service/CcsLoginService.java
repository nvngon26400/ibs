package com.sbisec.helios.ap.common.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.common.dto.UserHasCcsDataRequestDto;
import com.sbisec.helios.ap.common.dto.UserHasCcsDataResponseDto;
import com.sbisec.helios.ap.common.dto.CcsLoginRequestDto;
import com.sbisec.helios.ap.common.dto.CcsLoginResponseDto;
import com.sbisec.helios.ap.common.dto.UpdateCcsDataRequestDto;
import com.sbisec.helios.ap.common.dto.UpdateCcsDataResponseDto;
import com.sbisec.helios.ap.common.dto.ClearCcsDataSelfRequestDto;
import com.sbisec.helios.ap.common.dto.ClearCcsDataSelfResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * CCSログイン共通サービス
 *
 * @author SCSK 矢口
 2024/07/24 新規作成
 */
public interface CcsLoginService extends Service {
    
    /**
     * アクションID：画面共通部品_CCSログインユーザー情報登録 A001
     * アクション名：CCSログイン情報チェック
     * Dto リクエスト：UserHasCcsDataResponseDto
     * Dto レスポンス：UserHasCcsDataRequestDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<UserHasCcsDataResponseDto> userHasCcsData(UserHasCcsDataRequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：画面共通部品_CCSログインユーザー情報登録 A002
     * アクション名：CCSログイン
     * Dto リクエスト：CcsLoginRequestDto
     * Dto レスポンス：CcsLoginResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<CcsLoginResponseDto> ccsLogin(CcsLoginRequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：画面共通部品_CCSログインユーザー情報登録 A003
     * アクション名：CCSログイン情報登録
     * Dto リクエスト：UpdateCcsDataRequestDto
     * Dto レスポンス：UpdateCcsDataResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<UpdateCcsDataResponseDto> updateCcsData(UpdateCcsDataRequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：画面共通部品_CC017_共通ヘッダ A005
     * アクション名：CCS情報リセット
     * Dto リクエスト：ClearCcsDataSelfRequestDto
     * Dto レスポンス：ClearCcsDataSelfResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<ClearCcsDataSelfResponseDto> clearCcsDataSelf(ClearCcsDataSelfRequestDto dtoReq) throws Exception;
    
}
