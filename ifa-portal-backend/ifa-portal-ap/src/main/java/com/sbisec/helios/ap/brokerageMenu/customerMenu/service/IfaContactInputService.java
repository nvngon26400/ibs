package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactInputA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactInputA007RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactInputA007ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactInputA008RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactInputA008ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactInputX001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactInputX001ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * サービスのインターフェース
 * 画面ID:SUB0202_0106-03
 * 画面名:接触履歴入力
 * 2025/04/24 新規作成
 *
 * @author 大連 王永宝
 */
public interface IfaContactInputService extends Service {

    /**
     * アクションID:A001
     * アクション名:初期化
     * リクエスト:IfaContactInputA001RequestDto
     * レスポンス:IfaContactInputA001ResponseDto
     *
     * @param x_dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaContactInputA001ResponseDto> initializeA001(
            IfaContactInputA001RequestDto x_dtoReq) throws Exception;

    /**
     * アクションID:X001
     * アクション名:追加入力/管理項目修正
     * リクエスト:IfaContactInputX001RequestDto
     * レスポンス:IfaContactInputX001ResponseDto
     *
     * @param x_dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaContactInputX001ResponseDto> initializeX001(
            IfaContactInputX001RequestDto x_dtoReq) throws Exception;

    /**
     * アクションID:A002
     * アクション名:確認
     * リクエスト:IfaContactInputA002RequestDto
     * レスポンス:IfaContactInputA002ResponseDto
     *
     * @param x_dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaContactInputA002ResponseDto> confirmA002(
            IfaContactInputA002RequestDto x_dtoReq) throws Exception;
    
    /**
     * アクションID:A007
     * アクション名:カテゴリ選択（大）
     * リクエスト:IfaContactInputA007RequestDto
     * レスポンス:IfaContactInputA007ResponseDto
     *
     * @param x_dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaContactInputA007ResponseDto> categoryChangeA007(
        IfaContactInputA007RequestDto x_dtoReq) throws Exception;
    
    /**
     * アクションID:A008
     * アクション名:カテゴリ選択（中）
     * リクエスト:IfaContactInputA008RequestDto
     * レスポンス:IfaContactInputA008ResponseDto
     *
     * @param x_dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaContactInputA008ResponseDto> categoryChangeA008(
        IfaContactInputA008RequestDto x_dtoReq) throws Exception;
}
