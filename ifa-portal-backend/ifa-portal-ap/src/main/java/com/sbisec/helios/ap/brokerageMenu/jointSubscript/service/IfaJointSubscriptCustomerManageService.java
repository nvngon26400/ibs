package com.sbisec.helios.ap.brokerageMenu.jointSubscript.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA006RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA006ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA007RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA007ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA008RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA008ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * サービスのインターフェース
 * 画面ID：SUB0206_01-01
 * 画面名：共同募集 顧客管理
 * 2024/12/05 新規作成
 *
 * @author 大連 王永宝
 */
public interface IfaJointSubscriptCustomerManageService extends Service {

    /**
     * アクションID：A001 アクション名：初期化 Dto
     * リクエスト：IfaJointSubscriptCustomerManageA001RequestDto Dto
     * レスポンス：IfaJointSubscriptCustomerManageA001ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaJointSubscriptCustomerManageA001ResponseDto> initializeA001(
            IfaJointSubscriptCustomerManageA001RequestDto dtoReq) throws Exception;

    /**
     * アクションID：A002 アクション名：検索(表示) Dto
     * リクエスト：IfaJointSubscriptCustomerManageA002RequestDto Dto
     * レスポンス：IfaJointSubscriptCustomerManageA002ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaJointSubscriptCustomerManageA002ResponseDto> searchDisplayA002(
            IfaJointSubscriptCustomerManageA002RequestDto dtoReq) throws Exception;

    /**
     * アクションID：A005 アクション名：承認確認 Dto
     * リクエスト：IfaJointSubscriptCustomerManageA005RequestDto Dto
     * レスポンス：IfaJointSubscriptCustomerManageA005ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaJointSubscriptCustomerManageA005ResponseDto> confirmApproveA005(
            IfaJointSubscriptCustomerManageA005RequestDto dtoReq) throws Exception;

    /**
     * アクションID：A006 アクション名：CSV出力 Dto
     * リクエスト：IfaJointSubscriptCustomerManageA006RequestDto Dto
     * レスポンス：IfaJointSubscriptCustomerManageA006ResponseDto
     *
     * @param dtoReq    リクエストパラメータ
     * @param sessionId セクションID
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaJointSubscriptCustomerManageA006ResponseDto> csvOutputA006(
            IfaJointSubscriptCustomerManageA006RequestDto dtoReq, String sessionId) throws Exception;

    /**
     * アクションID：A007 アクション名：削除確認 Dto
     * リクエスト：IfaJointSubscriptCustomerManageA007RequestDto Dto
     * レスポンス：IfaJointSubscriptCustomerManageA007ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaJointSubscriptCustomerManageA007ResponseDto> confirmDeleteA007(
            IfaJointSubscriptCustomerManageA007RequestDto dtoReq) throws Exception;

    /**
     * アクションID：A008 アクション名：顧客情報詳細 Dto
     * リクエスト：IfaJointSubscriptCustomerManageA008RequestDto Dto
     * レスポンス：IfaJointSubscriptCustomerManageA008ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaJointSubscriptCustomerManageA008ResponseDto> customerDetailA008(
            IfaJointSubscriptCustomerManageA008RequestDto dtoReq) throws Exception;

}
