package com.sbisec.helios.ap.internalAdminMenu.formAcquire.service;



import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dto.IfaBrokerageSubLedgerAcquireA001ReponseDto;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dto.IfaBrokerageSubLedgerAcquireA001RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dto.IfaBrokerageSubLedgerAcquireA002RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dto.IfaBrokerageSubLedgerAcquireA002ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0402_01-01
 * 画面名：仲介業補助簿取得
 *
 * @author BASE 丁
 2024/06/21 新規作成
 */
public interface IfaBrokerageSubLedgerAcquireService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaBrokerageSubLedgerAcquireA001RequestDto
     * Dto レスポンス：IfaBrokerageSubLedgerAcquireA001ReponseDto
     * model リクエスト：IfaBrokerageSubLedgerAcquireA001RequestModel
     * model レスポンス：IfaBrokerageSubLedgerAcquireA001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBrokerageSubLedgerAcquireA001ReponseDto> initializeA001(IfaBrokerageSubLedgerAcquireA001RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaBrokerageSubLedgerAcquireA002RequestDto
     * Dto レスポンス：IfaBrokerageSubLedgerAcquireA002ResponseDto
     * model リクエスト：IfaBrokerageSubLedgerAcquireA002RequestModel
     * model レスポンス：IfaBrokerageSubLedgerAcquireA002ResponseModel
	 *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBrokerageSubLedgerAcquireA002ResponseDto> displayA002(IfaBrokerageSubLedgerAcquireA002RequestDto dtoReq)
            throws Exception;

    
}
