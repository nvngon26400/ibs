package com.sbisec.helios.ap.extapi.servicenow.service;

import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowBranchAndBrokerInfomationA001RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowBranchAndBrokerInfomationA001ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowBranchAndBrokerInfomationA002RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowBranchAndBrokerInfomationA002ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowBranchAndBrokerInfomationA003RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowBranchAndBrokerInfomationA003ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowBranchAndBrokerInfomationA004RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowBranchAndBrokerInfomationA004ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * IfaServiceNowBranchAndBrokerInfomationService サービス
 *
 * @author SCSK
 */
public interface IfaServiceNowBranchAndBrokerInfomationService extends Service {
    
    /**
     * 本店・支店情報取得
     *
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public IfaServiceNowBranchAndBrokerInfomationA001ResponseDto invokeA001(
            IfaServiceNowBranchAndBrokerInfomationA001RequestDto dtoReq) throws Exception;
    
    /**
     * SBI証券本支店の仲介業者支店を取得
     *
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public IfaServiceNowBranchAndBrokerInfomationA002ResponseDto invokeA002(
            IfaServiceNowBranchAndBrokerInfomationA002RequestDto dtoReq) throws Exception;

    /**
     * 仲介業社支店一覧取得
     *
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public IfaServiceNowBranchAndBrokerInfomationA003ResponseDto invokeA003(
            IfaServiceNowBranchAndBrokerInfomationA003RequestDto dtoReq) throws Exception;
    
    /**
     * 営業員一覧を取得
     *
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public IfaServiceNowBranchAndBrokerInfomationA004ResponseDto invokeA004(
            IfaServiceNowBranchAndBrokerInfomationA004RequestDto dtoReq) throws Exception;
}
