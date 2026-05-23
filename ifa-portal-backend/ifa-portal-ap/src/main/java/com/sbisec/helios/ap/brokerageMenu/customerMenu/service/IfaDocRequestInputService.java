package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA008RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA008ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA009RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA009ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA013RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA013ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA014RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA014ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0704-01 画面名：書類請求入力
 *
 * @author xin.huang
 */
public interface IfaDocRequestInputService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dtoリクエスト：IfaDocRequestInputA001RequestDto
     * Dtoレスポンス：IfaDocRequestInputA001ResponseDto
     * 
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    public DataList<IfaDocRequestInputA001ResponseDto> initializeA001(IfaDocRequestInputA001RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A002
     * アクション名：分類選択-書類リスト取得
     * Dtoリクエスト：IfaDocRequestInputA002RequestDto
     * Dtoレスポンス：IfaDocRequestInputA002ResponseDto
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 書類リスト取得の際、例外が発生した場合
     */
    public DataList<IfaDocRequestInputA002ResponseDto> selectShoruiListA002(IfaDocRequestInputA002RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A003
     * アクション名：書類選択-書類データ取得
     * Dtoリクエスト：IfaDocRequestInputA003RequestDto
     * Dtoレスポンス：IfaDocRequestInputA003ResponseDto
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 書類データ取得の際、例外が発生した場合
     */
    public DataList<IfaDocRequestInputA003ResponseDto> selectShoruiDateA003(IfaDocRequestInputA003RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A005
     * アクション名：投信銘柄情報取得
     * Dtoレスポンス：IfaDocRequestInputA005RequestDto
     * Dtoレスポンス：IfaDocRequestInputA005ResponseDto
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 投信銘柄情報取得の際、例外が発生した場合
     */
    public DataList<IfaDocRequestInputA005ResponseDto> selectMFNameA005(IfaDocRequestInputA005RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A008
     * アクション名：書類請求取消
     * Dtoレスポンス：IfaDocRequestInputA008RequestDto
     * Dtoレスポンス：IfaDocRequestInputA008ResponseDto
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 書類請求取消の際、例外が発生した場合
     */
    public DataList<IfaDocRequestInputA008ResponseDto> cancelConfirmA008(IfaDocRequestInputA008RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A009
     * アクション名：書類請求詳細
     * Dtoレスポンス：IfaDocRequestInputA009RequestDto
     * Dtoレスポンス：IfaDocRequestInputA009ResponseDto
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 書類請求詳細の際、例外が発生した場合
     */
    public DataList<IfaDocRequestInputA009ResponseDto> detailA009(IfaDocRequestInputA009RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A013
     * アクション名：BM交付取消
     * Dtoレスポンス：IfaDocRequestInputA013RequestDto
     * Dtoレスポンス：IfaDocRequestInputA013ResponseDto
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception BM交付取消の際、例外が発生した場合
     */
    public DataList<IfaDocRequestInputA013ResponseDto> cancelConfirmA013(IfaDocRequestInputA013RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A014
     * アクション名：BM交付詳細
     * Dtoレスポンス：IfaDocRequestInputA014RequestDto
     * Dtoレスポンス：IfaDocRequestInputA014ResponseDto
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception BM交付詳細の際、例外が発生した場合
     */
    public DataList<IfaDocRequestInputA014ResponseDto> detailA014(IfaDocRequestInputA014RequestDto dtoReq)
            throws Exception;
}
