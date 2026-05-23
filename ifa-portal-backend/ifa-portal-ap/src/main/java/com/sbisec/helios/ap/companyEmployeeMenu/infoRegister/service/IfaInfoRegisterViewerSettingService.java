package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterViewerSettingA002DtoRequest;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterViewerSettingA002DtoResponse;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterViewerSettingA009DtoRequest;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterViewerSettingA009DtoResponse;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterViewerSettingA012DtoRequest;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterViewerSettingA012DtoResponse;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterViewerSettingA016DtoRequest;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterViewerSettingA016DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0501_01-04
 * 画面名：情報登録閲覧者設定
 *
 * @author SCSK 矢口
 2024/05/24 新規作成
 */
public interface IfaInfoRegisterViewerSettingService extends Service {
    
    /**
     * アクションID：A002
     * アクション名：初期化（情報更新）
     * Dto リクエスト：IfaInfoRegisterViewerSettingA002DtoRequest
     * Dto レスポンス：IfaInfoRegisterViewerSettingA002DtoResponse
     * model リクエスト：IfaInfoRegisterViewerSettingA002RequestModel
     * model レスポンス：IfaInfoRegisterViewerSettingA002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoRegisterViewerSettingA002DtoResponse> initializeInfoUpdateA002(
            IfaInfoRegisterViewerSettingA002DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A009
     * アクション名：仲介業者検索
     * Dto リクエスト：IfaInfoRegisterViewerSettingA009DtoRequest
     * Dto レスポンス：IfaInfoRegisterViewerSettingA009DtoResponse
     * model リクエスト：IfaInfoRegisterViewerSettingA009RequestModel
     * model レスポンス：IfaInfoRegisterViewerSettingA009ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoRegisterViewerSettingA009DtoResponse> intermediarySearchA009(
            IfaInfoRegisterViewerSettingA009DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A012
     * アクション名：担当者一覧表示
     * Dto リクエスト：IfaInfoRegisterViewerSettingA012DtoRequest
     * Dto レスポンス：IfaInfoRegisterViewerSettingA012DtoResponse
     * model リクエスト：IfaInfoRegisterViewerSettingA012RequestModel
     * model レスポンス：IfaInfoRegisterViewerSettingA012ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoRegisterViewerSettingA012DtoResponse> personInChargeListDisplayA012(
            IfaInfoRegisterViewerSettingA012DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A016
     * アクション名：担当者検索
     * Dto リクエスト：IfaInfoRegisterViewerSettingA016DtoRequest
     * Dto レスポンス：IfaInfoRegisterViewerSettingA016DtoResponse
     * model リクエスト：IfaInfoRegisterViewerSettingA016RequestModel
     * model レスポンス：IfaInfoRegisterViewerSettingA016ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoRegisterViewerSettingA016DtoResponse> personInChargeSearchA016(
            IfaInfoRegisterViewerSettingA016DtoRequest dtoReq) throws Exception;
    
}
