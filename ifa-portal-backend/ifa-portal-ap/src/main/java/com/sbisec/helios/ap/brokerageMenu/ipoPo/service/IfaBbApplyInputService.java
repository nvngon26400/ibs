package com.sbisec.helios.ap.brokerageMenu.ipoPo.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyInputA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyInputA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyInputA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyInputA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyInputA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyInputA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyInputA005ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0204_01-02_1
 * 画面名：BB申込入力
 *
 * @author BASE李
 *
 　 2024/02/09 新規作成
 */
public interface IfaBbApplyInputService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化（IPO/PO一覧遷移）
     * Dto リクエスト：IfaBbApplyInputA001RequestDto
     * Dto レスポンス：IfaBbApplyInputA001ResponseDto
     * model リクエスト：IfaBbApplyInputA001RequestModel
     * model レスポンス：IfaBbApplyInputA001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return IfaBbApplyInputA001ResponseDto
     * @exception Exception 例外
     */
    public DataList<IfaBbApplyInputA001ResponseDto> initializeIpoPoListTransitionA001(IfaBbApplyInputA001RequestDto dtoReq)
            throws Exception;

    
    /**
     * アクションID：A002
     * アクション名：初期化（顧客一覧遷移）
     * Dto リクエスト：IfaBbApplyInputA002RequestDto
     * Dto レスポンス：IfaBbApplyInputA002ResponseDto
     * model リクエスト：IfaBbApplyInputA002RequestModel
     * model レスポンス：IfaBbApplyInputA002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return IfaBbApplyInputA001ResponseDto
     * @exception Exception 例外
     */
    public DataList<IfaBbApplyInputA002ResponseDto> initializeCustomerListTransitionA002(IfaBbApplyInputA002RequestDto dtoReq)
            throws Exception;
    
    
    /**
     * アクションID：A003
     * アクション名：銘柄変更
     * Dto リクエスト：IfaBbApplyInputA003RequestDto
     * Dto レスポンス：IfaBbApplyInputA003ResponseDto
     * model リクエスト：IfaBbApplyInputA003RequestModel
     * model レスポンス：IfaBbApplyInputA003ResponseModel
     *
     * @param dtoReq リクエスト
     * @return IfaBbApplyInputA001ResponseDto
     * @exception Exception 例外
     */
    public DataList<IfaBbApplyInputA003ResponseDto> brandChangeA003(IfaBbApplyInputA003RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A004
     * アクション名：部店・口座番号入力
     * Dto リクエスト：IfaBbApplyInputA004RequestDto
     * Dto レスポンス：IfaBbApplyInputA004ResponseDto
     * model リクエスト：IfaBbApplyInputA004RequestModel
     * model レスポンス：IfaBbApplyInputA004ResponseModel
     *
     * @param dtoReq リクエスト
     * @return IfaBbApplyInputA001ResponseDto
     * @exception Exception 例外
     */
    public DataList<IfaBbApplyInputA004ResponseDto> branchAccountNumberInputA004(IfaBbApplyInputA004RequestDto dtoReq)
            throws Exception;

    
    /**
     * アクションID：A005
     * アクション名：申込確認
     * Dto リクエスト：IfaBbApplyInputA005RequestDto
     * Dto レスポンス：IfaBbApplyInputA005ResponseDto
     * model リクエスト：IfaBbApplyInputA005RequestModel
     * model レスポンス：IfaBbApplyInputA005ResponseModel
     *
     * @param dtoReq リクエスト
     * @return IfaBbApplyInputA001ResponseDto
     * @exception Exception 例外
     */
    public DataList<IfaBbApplyInputA005ResponseDto> applicationConfirmA005(IfaBbApplyInputA005RequestDto dtoReq)
            throws Exception;
    
    

}
