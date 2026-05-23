package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPortfolioA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPortfolioA001ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0101-01
 * 画面名：資産状況
 * 2023/12/26 新規作成
 *
 * @author SCSK 江口
 */
public interface IfaPortfolioService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaPortfolioA001RequestDto
     * Dto レスポンス：IfaPortfolioA001ResponseDto
     * model リクエスト：IfaPortfolioA001RequestModel
     * model レスポンス：IfaPortfolioA001ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return 受取方法変更入力画面の初期化に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaPortfolioA001ResponseDto> initializeA001(IfaPortfolioA001RequestDto dtoReq)
            throws Exception;

}
