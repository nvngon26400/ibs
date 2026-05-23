package com.sbisec.helios.ap.companyEmployeeMenu.jointContract.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dto.IfaJointContractMasterNewRegisterA004RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dto.IfaJointContractMasterNewRegisterA004ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dto.IfaJointContractMasterNewRegisterA005RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dto.IfaJointContractMasterNewRegisterA005ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0513_01-02
 * 画面名：共同募集契約マスタ登録
 * 2025/11/25 新規作成
 *
 * @author 大連　苗萌
 * 
 */
public interface IfaJointContractMasterNewRegisterService extends Service {
    
    /**
     * アクションID：A004
     * アクション名：登録確認
     * Dto リクエスト：IfaJointContractMasterNewRegisterA004RequestDto
     * Dto レスポンス：IfaJointContractMasterNewRegisterA004ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointContractMasterNewRegisterA004ResponseDto> insertConfirmA004(
            IfaJointContractMasterNewRegisterA004RequestDto dtoReq) throws Exception;

    /**
     * アクションID：A005
     * アクション名：登録
     * Dto リクエスト：IfaJointContractMasterNewRegisterA005RequestDto
     * Dto レスポンス：IfaJointContractMasterNewRegisterA005ResponseDto
     *
     * @param dtoReq リクエスト
     * @return dtoRes レスポンス
     * @exception exception システムエラー
     */
    DataList<IfaJointContractMasterNewRegisterA005ResponseDto> insertA005(IfaJointContractMasterNewRegisterA005RequestDto dtoReq)
            throws Exception;


}
