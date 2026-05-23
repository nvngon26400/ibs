package com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.IfaForeignBondBuyAmountInputListDao;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.model.IfaForeignBondBuyAmountInputListSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.model.IfaForeignBondBuyAmountInputListSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaForeignBondBuyAmountInputListA001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaForeignBondBuyAmountInputListA001ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaForeignBondBuyAmountInputListA001ResponseDtoForeignBondBuyAmount;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaForeignBondBuyAmountInputListA004RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaForeignBondBuyAmountInputListA004ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.service.IfaForeignBondBuyAmountInputListService;

/**
 * 画面ID：SUB0504_04-01 
 * 画面名：外債買付代金入力一覧
 *
 * @author SBIFPT 2026/04/13 CuongHD
 */
@Component(value = "cmpIfaForeignBondBuyAmountInputListService")
public class IfaForeignBondBuyAmountInputListServiceImpl implements IfaForeignBondBuyAmountInputListService
{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaForeignBondBuyAmountInputListServiceImpl.class);
    
    /** 正常終了 */
    private static final String SUCCESS_MESSAGE = "正常終了";
    
    /** 情報.自己点検項目を削除しました。 */
    private static final String INFO_DELETE_COMPLETED = "info.deleteCompleted";
    
    public static final String ERRORS_DELETE_DATA_EXIST = "errors.deleteDataExist";
    
    /** "検索結果が{0}件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。" */
    private static final String WARNINGS_DATALIST_OVER_MAX_ROWNUM = "warnings.dataList.overMaxRownum";
    
    /** 表示最大件数 */
    private static final int DISPLAY_MAX_ROW = 5000;
    
    /** 最小取得件数 */
    private static final int MIN_COUNT = 1;
    
    @Autowired
    private IfaForeignBondBuyAmountInputListDao dao;
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaForeignBondBuyAmountInputListA001RequestDto
     * Dto レスポンス：IfaForeignBondBuyAmountInputListA001ResponseDto
     * model レスポンス：IfaForeignBondBuyAmountInputListSql001ResponseModel
     *
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    @Override
    public DataList<IfaForeignBondBuyAmountInputListA001ResponseDto> initializeA001(
            IfaForeignBondBuyAmountInputListA001RequestDto dtoReq) throws Exception {
        
        DataList<IfaForeignBondBuyAmountInputListA001ResponseDto> dtoRes = new DataList<IfaForeignBondBuyAmountInputListA001ResponseDto>();
        List<IfaForeignBondBuyAmountInputListA001ResponseDto> resDtoList = new ArrayList<IfaForeignBondBuyAmountInputListA001ResponseDto>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaForeignBondBuyAmountInputListServiceImpl.initializeA001");
        IfaForeignBondBuyAmountInputListA001ResponseDto a001DtoResponse = new IfaForeignBondBuyAmountInputListA001ResponseDto();
        // 外債買付代金入力情報を取得する
        DataList<IfaForeignBondBuyAmountInputListSql001ResponseModel> selSql001Res = dao
                .selectIfaForeignBondBuyAmountInputListSql001(DISPLAY_MAX_ROW);
        
        List<IfaForeignBondBuyAmountInputListA001ResponseDtoForeignBondBuyAmount> foreignBondBuyAmountInputList = new ArrayList<IfaForeignBondBuyAmountInputListA001ResponseDtoForeignBondBuyAmount>();
        
        for (IfaForeignBondBuyAmountInputListSql001ResponseModel sql001ResponseModel : selSql001Res.getDataList()) {

            IfaForeignBondBuyAmountInputListA001ResponseDtoForeignBondBuyAmount a001ResponseDtoForeignBondBuyAmount = new IfaForeignBondBuyAmountInputListA001ResponseDtoForeignBondBuyAmount();
            BeanUtils.copyProperties(a001ResponseDtoForeignBondBuyAmount, sql001ResponseModel);
            foreignBondBuyAmountInputList.add(a001ResponseDtoForeignBondBuyAmount);
        }
        
        a001DtoResponse.setForeignBondBuyAmountInputList(foreignBondBuyAmountInputList);
        resDtoList.add(a001DtoResponse);
        
        if (ObjectUtils.isEmpty(selSql001Res) || selSql001Res.size() < MIN_COUNT) {

        }
        else if (selSql001Res.get(0).getTotalCount() > DISPLAY_MAX_ROW) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.WARNING, WARNINGS_DATALIST_OVER_MAX_ROWNUM,
                    IfaCommonUtil.getMessage(WARNINGS_DATALIST_OVER_MAX_ROWNUM,
                            new String[] { String.valueOf(DISPLAY_MAX_ROW), String.valueOf(selSql001Res.get(0).getTotalCount()) }));
            dtoRes.setOverMaxRownum(true);
        } else {

            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, SUCCESS_MESSAGE, null);
        }
        dtoRes.setMaxRownum(DISPLAY_MAX_ROW);
        
        return dtoRes;
    }
    
    /**
     * アクションID：A004
     * アクション名：削除
     * Dto リクエスト：IfaForeignBondBuyAmountInputListA004RequestDto
     * Dto レスポンス：IfaForeignBondBuyAmountInputListA004ResponseDto
     * model リクエスト：IfaForeignBondBuyAmountInputListSql002RequestModel
     * model レスポンス：IfaForeignBondBuyAmountInputListSql001ResponseModel
     * 
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception Exception データ削除処理で例外が発生した場合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataList<IfaForeignBondBuyAmountInputListA004ResponseDto> deleteA004(
            IfaForeignBondBuyAmountInputListA004RequestDto dtoReq) throws Exception {
        
        IfaForeignBondBuyAmountInputListSql002RequestModel delSql002Req = new IfaForeignBondBuyAmountInputListSql002RequestModel();
        DataList<IfaForeignBondBuyAmountInputListA004ResponseDto> dtoRes = new DataList<IfaForeignBondBuyAmountInputListA004ResponseDto>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaForeignBondBuyAmountInputListServiceImpl.deleteA004");
        
        BeanUtils.copyProperties(delSql002Req, dtoReq);
        // 選択した外債買付代金入力情報削除処理を行う
        int deleteResult = dao.deleteIfaForeignBondBuyAmountInputListSql002(delSql002Req);
        // 更新NG：エラーを表示し、処理終了。
        if (deleteResult == 0) {
            dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_DELETE_DATA_EXIST,
                    IfaCommonUtil.getMessage(ERRORS_DELETE_DATA_EXIST, new String[] { "外債買付代金入力情報" }));
            
            return dtoRes;
        }
        // 取消完了メッセージを表示し、外債買付代金入力情報削除確認ダイアログを閉じる
        dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.SUCCESS, INFO_DELETE_COMPLETED,
                IfaCommonUtil.getMessage(INFO_DELETE_COMPLETED, new String[] { "外債買付代金入力情報" }));
        
        return dtoRes;
    }
}
