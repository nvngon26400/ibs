package com.sbisec.helios.ap.companyEmployeeMenu.jointContract.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dto.IfaJointContractMasterA002RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dto.IfaJointContractMasterA002ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dto.IfaJointContractMasterA006RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dto.IfaJointContractMasterA006ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dto.IfaJointContractMasterA008RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dto.IfaJointContractMasterA008ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0513_01-01
 * 画面名：共同募集契約マスタ
 * 2025/11/25 新規作成
 *
 * @author 大連　苗萌
 * 
 */
public interface IfaJointContractMasterService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaJointContractMasterA002RequestDto
     * Dto レスポンス：IfaJointContractMasterA002ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointContractMasterA002ResponseDto> displayA002A003(
            IfaJointContractMasterA002RequestDto dtoReq) throws Exception;

    /**
     * アクションID：A008
     * アクション名：削除
     * Dto リクエスト：IfaJointContractMasterA008RequestDto
     * Dto レスポンス：IfaJointContractMasterA008ResponseDto
     *
     * @param dtoReq リクエスト
     * @return dtoRes レスポンス
     * @exception exception システムエラー
     */
    DataList<IfaJointContractMasterA008ResponseDto> deleteA008(IfaJointContractMasterA008RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A006
     * アクション名：変更
     *
     * @param dtoReq リクエスト
     * @return dtoRes レスポンス
     * @exception exception システムエラー
     */
    DataList<IfaJointContractMasterA006ResponseDto> updateA006(IfaJointContractMasterA006RequestDto dtoReq)
            throws Exception;
    
}
