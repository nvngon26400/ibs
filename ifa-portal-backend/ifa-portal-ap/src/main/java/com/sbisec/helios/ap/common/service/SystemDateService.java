package com.sbisec.helios.ap.common.service;

import java.util.Date;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.dto.PreviousBusinessDateDtoRequest;
import com.sbisec.helios.ap.common.dto.SystemDateDtoRequest;
import com.sbisec.helios.ap.service.Service;

/**
 * システム日付に関するサービスインターフェース
 */
public interface SystemDateService extends Service {
    
    /**
     * システム日付を基準に前営業日を取得する。
     *
     * @param req 前営業日を取得するためのリクエスト情報を含むDTO
     * @return 前営業日を表す日付をYYYYMMDD形式の文字列で返却する
     * @throws Exception システムエラーやデータアクセスエラーなど、処理中に発生した例外
     */
    public String getPreviousBusinessDate(PreviousBusinessDateDtoRequest req) throws Exception;
    
    /**
     * システム日付を取得する。
     *
     * @param req リクエストDTO
     * @return システム日付
     * @throws Exception 例外
     */
    public DataList<Date> getSystemDate(SystemDateDtoRequest req) throws Exception;
    
}
