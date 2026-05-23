package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaContactCorrectHistoryDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectHistorySql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectHistorySql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactCorrectHistoryA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactCorrectHistoryA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactCorrectHistoryListDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaContactCorrectHistoryService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

/**
 * 画面ID:SUB0202_0106-08
 * 画面名:接触履歴（入力）修正履歴
 *
 * @author SBI大連 夏
 * @date   2025/08/14
 */

@Component(value = "cmpIfaContactCorrectHistoryService")
public class IfaContactCorrectHistoryServiceImpl implements IfaContactCorrectHistoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaContactCorrectHistoryServiceImpl.class);
    
    @Autowired
    private IfaContactCorrectHistoryDao dao;
    
   /** 
    * アクションID：A001
    * アクション名：初期化
    * リクエスト：IfaContactCorrectHistoryA001DtoRequest
    * レスポンス：IfaContactCorrectHistoryA001DtoResponse
   
    * @param dtoReq リクエストDTO
    * @return dtoRes レスポンスDTO
    * @exception Exception 初期化処理で例外が発生した場合
    */
    @Override
    public DataList<IfaContactCorrectHistoryA001ResponseDto> initializeA001(IfaContactCorrectHistoryA001RequestDto dtoReq)
        throws Exception {
        
        if (LOGGER.isDebugEnabled()) LOGGER.debug("IfaContactCorrectHistoryServiceImpL.selectA001");
        
        // 戻り値の初期化
        DataList<IfaContactCorrectHistoryA001ResponseDto> dtoRes = new DataList<IfaContactCorrectHistoryA001ResponseDto>();
        List<IfaContactCorrectHistoryA001ResponseDto> contactCorrectHistoryDtoList = new ArrayList<IfaContactCorrectHistoryA001ResponseDto>();
        List<IfaContactCorrectHistoryListDto> contactCorrectHistoryList = new ArrayList<IfaContactCorrectHistoryListDto>();
        
        IfaContactCorrectHistorySql001RequestModel sql001Req = new IfaContactCorrectHistorySql001RequestModel();
        sql001Req.setIfaToiawaseNo(dtoReq.getIfaToiawaseNo());
        sql001Req.setTourokuGroup(dtoReq.getTourokuGroup());
        DataList<IfaContactCorrectHistorySql001ResponseModel> sql001Res = new DataList<IfaContactCorrectHistorySql001ResponseModel>();
        sql001Res = dao.selectIfaContactCorrectHistorySql001(sql001Req);
        
        if (sql001Res != null && CollectionUtils.isNotEmpty(sql001Res.getDataList())) {
            IfaContactCorrectHistoryA001ResponseDto res = new IfaContactCorrectHistoryA001ResponseDto();
            for (IfaContactCorrectHistorySql001ResponseModel sqlRes : sql001Res.getDataList()) {
                IfaContactCorrectHistoryListDto contactCorrectHistory = new IfaContactCorrectHistoryListDto();
                BeanUtils.copyProperties(sqlRes, contactCorrectHistory);
                contactCorrectHistory.setNyuuryokuDate(parseToIsoDate(sqlRes.getNyuuryokuDate()));
                contactCorrectHistory.setSyuuseiDate(parseToIsoDate(sqlRes.getSyuuseiDate()));
                contactCorrectHistoryList.add(contactCorrectHistory);
            }
            res.setContactCorrectHistoryList(contactCorrectHistoryList);
            contactCorrectHistoryDtoList.add(res);
        }
        
        dtoRes = IfaCommonUtil.createDataList(contactCorrectHistoryDtoList, ErrorLevel.SUCCESS, "", "");
        
        return dtoRes;
    }
    
    /**
     * 日付の変換する
     * @param String YYYY-MM-DD HH:MM:SS
     * @return String YYYY/MM/DD HH:MM:SS
     */
    private static String parseToIsoDate(String dateTimeStr) {
        if (StringUtils.isNotEmpty(dateTimeStr)) {
            return dateTimeStr.replaceAll("-", "/");
        }
        return null;
    }

}
