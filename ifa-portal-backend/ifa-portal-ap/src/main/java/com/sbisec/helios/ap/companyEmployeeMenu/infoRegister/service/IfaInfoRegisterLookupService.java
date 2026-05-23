package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterLookupA001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterLookupA001ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterLookupA009RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterLookupA009ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterLookupX001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterLookupX001ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0501_01-01
 * 画面名：情報登録照会
 * @author SCSK今井
 * 2024/05/23 新規作成
 */
public interface IfaInfoRegisterLookupService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * 処理概要：
     * ①登録済カテゴリのカテゴリ名を取得する。
     * ②"全て" ＋ 必須フラグが '1'（必須）のカテゴリを表示する。
     * ③登録済全情報一覧を取得する。
     * 呼出機能：SQL001, SQL002
     * @return 初期化情報を含むレスポンスDTO
     * @exception Exception システムエラー
     */
    public DataList<IfaInfoRegisterLookupA001ResponseDto> initializeA001(IfaInfoRegisterLookupA001RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：X001
     * アクション名：検索表示
     * 処理概要：
     * ①タイトルの入力チェックを行う。
     * ②情報一覧を取得する。
     * 呼出機能：SQL002
     * @param req リクエストDTO
     * @return 検索結果を含むレスポンスDTO
     * @exception Exception システムエラー
     */
    public DataList<IfaInfoRegisterLookupX001ResponseDto> searchDisplayX001(IfaInfoRegisterLookupX001RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A009
     * アクション名：削除
     * 処理概要：
     * ①選択した情報の添付ファイルのディレクトリを取得する。
     * ②選択した情報の添付ファイルを削除する。
     * ③選択した情報を削除し、メッセージを表示する。
     * 呼出機能：SQL003, SQL004, SQL005, SQL006
     * @param req リクエストDTO
     * @return 削除の結果を含むレスポンスDTO
     * @exception Exception システムエラー
     */
    public DataList<IfaInfoRegisterLookupA009ResponseDto> deleteA009(IfaInfoRegisterLookupA009RequestDto dtoReq)
            throws Exception;
}
