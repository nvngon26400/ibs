package com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA001ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA002RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA002ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA003RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA003ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA009RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA009ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA010RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA010ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA012RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA012ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0506_02-01
 * 画面名：自己点検項目管理
 * 2024/06/19 新規作成
 *
 * @author SCSK 江口
 */
public interface IfaSelfInspectItemManageService extends Service {


    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaSelfInspectItemManageA001RequestDto
     * Dto レスポンス：IfaSelfInspectItemManageA001ResponseDto
     * model リクエスト：IfaSelfInspectItemManageA001RequestModel
     * model レスポンス：IfaSelfInspectItemManageA001ResponseModel
     *
     * @return 初期化に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaSelfInspectItemManageA001ResponseDto> initializeA001() throws Exception;

    /**
     * アクションID：A002
     * アクション名：表示年月変更
     * Dto リクエスト：IfaSelfInspectItemManageA002RequestDto
     * Dto レスポンス：IfaSelfInspectItemManageA002ResponseDto
     * model リクエスト：IfaSelfInspectItemManageA002RequestModel
     * model レスポンス：IfaSelfInspectItemManageA002ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return 表示年月変更に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaSelfInspectItemManageA002ResponseDto> displayYearMonthChangeA002(
            IfaSelfInspectItemManageA002RequestDto dtoReq
    ) throws Exception;

    /**
     * アクションID：A003
     * アクション名：検索表示
     * Dto リクエスト：IfaSelfInspectItemManageA003RequestDto
     * Dto レスポンス：IfaSelfInspectItemManageA003ResponseDto
     * model リクエスト：IfaSelfInspectItemManageA003RequestModel
     * model レスポンス：IfaSelfInspectItemManageA003ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return 検索表示に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaSelfInspectItemManageA003ResponseDto> searchDisplayA003(
            IfaSelfInspectItemManageA003RequestDto dtoReq
    ) throws Exception;

    /**
     * アクションID：A009
     * アクション名：更新
     * Dto リクエスト：IfaSelfInspectItemManageA009RequestDto
     * Dto レスポンス：IfaSelfInspectItemManageA009ResponseDto
     * model リクエスト：IfaSelfInspectItemManageA009RequestModel
     * model レスポンス：IfaSelfInspectItemManageA009ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return 更新に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaSelfInspectItemManageA009ResponseDto> updateA009(
            IfaSelfInspectItemManageA009RequestDto dtoReq
    ) throws Exception;

    /**
     * アクションID：A010
     * アクション名：削除
     * Dto リクエスト：IfaSelfInspectItemManageA010RequestDto
     * Dto レスポンス：IfaSelfInspectItemManageA010ResponseDto
     * model リクエスト：IfaSelfInspectItemManageA010RequestModel
     * model レスポンス：IfaSelfInspectItemManageA010ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return 削除に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaSelfInspectItemManageA010ResponseDto> deleteA010(
            IfaSelfInspectItemManageA010RequestDto dtoReq
    ) throws Exception;

    /**
     * アクションID：A012
     * アクション名：ファイル取込
     * Dto リクエスト：IfaSelfInspectItemManageA012RequestDto
     * Dto レスポンス：IfaSelfInspectItemManageA012ResponseDto
     * model リクエスト：IfaSelfInspectItemManageA012RequestModel
     * model レスポンス：IfaSelfInspectItemManageA012ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return ファイル取込に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaSelfInspectItemManageA012ResponseDto> fileImportA012(
            IfaSelfInspectItemManageA012RequestDto dtoReq
    ) throws Exception;


}
