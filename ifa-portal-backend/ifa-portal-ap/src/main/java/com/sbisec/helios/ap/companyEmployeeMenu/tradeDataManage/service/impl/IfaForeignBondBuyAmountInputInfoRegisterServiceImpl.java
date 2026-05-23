package com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.athena.enums.MarginAccountType;
import com.sbisec.helios.ap.athena.ifa.ForeignAccountService;
import com.sbisec.helios.ap.athena.protocol.account.CheckMarginAccountOpenResp;
import com.sbisec.helios.ap.bizcommon.component.Fct035;
import com.sbisec.helios.ap.bizcommon.model.InputFct035Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct035Dto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.ForeignMarginAccountType;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CometCommonService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.IfaForeignBondBuyAmountInputInfoRegisterDao;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.model.IfaForeignBondBuyAmountInputInfoRegisterSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.model.IfaForeignBondBuyAmountInputInfoRegisterSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.model.IfaForeignBondBuyAmountInputInfoRegisterSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.model.IfaForeignBondBuyAmountInputInfoRegisterSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaForeignBondBuyAmountInputInfoRegisterA003RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaForeignBondBuyAmountInputInfoRegisterA003ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaForeignBondBuyAmountInputInfoRegisterA005RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaForeignBondBuyAmountInputInfoRegisterA005ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.service.IfaForeignBondBuyAmountInputInfoRegisterService;

/**
 * 画面ID：SUB0504_04-02_1 
 * 画面名：外債買付代金入力情報登録
 *
 * @author SBIFPT 2026/04/13 CuongHD
 */
@Component(value = "cmpIfaForeignBondBuyAmountInputInfoRegisterService")
public class IfaForeignBondBuyAmountInputInfoRegisterServiceImpl
        implements IfaForeignBondBuyAmountInputInfoRegisterService
{
    
    private static final Logger LOGGER = LoggerFactory
            .getLogger(IfaForeignBondBuyAmountInputInfoRegisterServiceImpl.class);
    
    private static final String ERRORS_BUTEN_ACCOUNT_NOT_EXIST = "errors.butenAccountNotExist";
    
    /** {0}が失敗しました。 */
    private static final String ERRORS_PROCESSING_FAILED = "errors.processingFailed";
    
    /** 区分.建余力不足 自動振替設定(米国株式).true */
    private static final String MARGIN_BUYING_POWER_SHORTFALL_SECURITIES_TRUE = "1";
    
    public static final String INFO_INSERT_COMPLETED = "info.insertCompleted";
    
    /**
     * エラーメッセージはAPIから取得し、処理を終了。
     */
    private static final String ERRORS_FRS_ACCOUNT_AUTO_TRANSFER_SETTING = "errors.frs.accountAutoTransferSetting.on.failed";
    
    @Autowired
    private IfaForeignBondBuyAmountInputInfoRegisterDao dao;
    
    @Autowired
    private ForeignAccountService foreignAccountService;
    
    @Autowired
    private CometCommonService cometCommonService;
    
    @Autowired
    private Fct035 fct035;
    
    /**
     * アクションID：A003
     * アクション名：検索処理
     * Dto リクエスト：IfaForeignBondBuyAmountInputInfoRegisterA003RequestDto
     * Dto レスポンス：IfaForeignBondBuyAmountInputInfoRegisterA003ResponseDto
     * model リクエスト：IfaForeignBondBuyAmountInputInfoRegisterSql001RequestModel
     * model レスポンス：IfaForeignBondBuyAmountInputInfoRegisterSql001ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception  検索処理で例外が発生した場合
     */
    @Override
    public DataList<IfaForeignBondBuyAmountInputInfoRegisterA003ResponseDto> nameKanjiInputA003(
            IfaForeignBondBuyAmountInputInfoRegisterA003RequestDto dtoReq) throws Exception {
        
        DataList<IfaForeignBondBuyAmountInputInfoRegisterA003ResponseDto> dtoRes = new DataList<IfaForeignBondBuyAmountInputInfoRegisterA003ResponseDto>();
        
        List<IfaForeignBondBuyAmountInputInfoRegisterA003ResponseDto> a003Res = dtoRes.getDataList();
        IfaForeignBondBuyAmountInputInfoRegisterA003ResponseDto a003ResponseDto = new IfaForeignBondBuyAmountInputInfoRegisterA003ResponseDto();
        a003Res.add(a003ResponseDto);
        BeanUtils.copyProperties(a003ResponseDto, dtoReq);
        IfaForeignBondBuyAmountInputInfoRegisterSql001RequestModel delSql001Req = new IfaForeignBondBuyAmountInputInfoRegisterSql001RequestModel();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignBondBuyAmountInputInfoRegisterServiceImpl.nameKanjiInputA003");
        }
        BeanUtils.copyProperties(delSql001Req, dtoReq);
        
        DataList<IfaForeignBondBuyAmountInputInfoRegisterSql001ResponseModel> sql001ResponseModelList = dao
                .selectIfaForeignBondBuyAmountInputInfoRegisterSql001(delSql001Req);
        int totalRow = sql001ResponseModelList.getDataList().size();
        
        if (totalRow > 0) {
            IfaForeignBondBuyAmountInputInfoRegisterSql001ResponseModel sql001ResponseModel = sql001ResponseModelList
                    .get(0);
            a003ResponseDto.setNameKanji(sql001ResponseModel.getNameKanji());
        }
        
        return dtoRes;
    }
    
    /**
     * アクションID：A005
     * アクション名：挿入処理
     * Dto リクエスト：IfaForeignBondBuyAmountInputInfoRegisterA005RequestDto
     * Dto レスポンス：IfaForeignBondBuyAmountInputInfoRegisterA005ResponseDto
     * model リクエスト：IfaForeignBondBuyAmountInputInfoRegisterSql003RequestModel
     * model レスポンス：IfaForeignBondBuyAmountInputInfoRegisterSql003ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception データ挿入処理中に例外が発生した場合
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public DataList<IfaForeignBondBuyAmountInputInfoRegisterA005ResponseDto> insertA005(
            IfaForeignBondBuyAmountInputInfoRegisterA005RequestDto dtoReq) throws Exception {
        
        DataList<IfaForeignBondBuyAmountInputInfoRegisterA005ResponseDto> dtoRes = new DataList<IfaForeignBondBuyAmountInputInfoRegisterA005ResponseDto>();

        IfaForeignBondBuyAmountInputInfoRegisterSql003RequestModel sql003Req = new IfaForeignBondBuyAmountInputInfoRegisterSql003RequestModel();
        IfaForeignBondBuyAmountInputInfoRegisterSql002RequestModel sql002Req = new IfaForeignBondBuyAmountInputInfoRegisterSql002RequestModel();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignBondBuyAmountInputInfoRegisterServiceImpl.insertA005");
        }
        
        // ユーザアカウント情報取得
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        BeanUtils.copyProperties(sql002Req, dtoReq);
        BeanUtils.copyProperties(sql003Req, dtoReq);
        sql003Req.setCreateUser(userAccount.getUserId());
        sql003Req.setUpdateUser(userAccount.getUserId());

        Integer count = dao.selectIfaForeignBondBuyAmountInputInfoRegisterSql002(sql002Req);

        if (count == null || count == 0) {
            dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_BUTEN_ACCOUNT_NOT_EXIST,
                    IfaCommonUtil.getMessage(ERRORS_BUTEN_ACCOUNT_NOT_EXIST,
                            new String[] { sql003Req.getButenCode(), sql003Req.getAccountNumber() }));
            return dtoRes;
        }
        
        // 信用口座開設状態を取得する （口座情報サービス - 信用口座開設判定APIを呼出）
        CheckMarginAccountOpenResp checkMarginAccountOpenResp = null;
        try {
            
            checkMarginAccountOpenResp = foreignAccountService.checkMarginAccountOpen(sql003Req.getButenCode(),
            		sql003Req.getAccountNumber(), MarginAccountType.FOREIGN.getId());
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                      ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        // 信用口座開設状態 = true
        if (checkMarginAccountOpenResp != null && checkMarginAccountOpenResp.getOpened() != null) {
        	if (StringUtils.equals(ForeignMarginAccountType.MARGIN.getId(), checkMarginAccountOpenResp.getConvOpened())) {
        		InputFct035Dto inputFct035Dto = new InputFct035Dto();
                inputFct035Dto.setButenCode(sql003Req.getButenCode());
                inputFct035Dto.setAccountNumber(sql003Req.getAccountNumber());
                OutputFct035Dto outputFct035Dto = fct035.doCheck(inputFct035Dto);
                
                // FCT035 建余力不足 自動振替設定(米ドル)フラグ = true
                if (outputFct035Dto == null || outputFct035Dto.getCapacityShortageAutoTransferSettingUSDFlag() == null
                        || outputFct035Dto.getCapacityShortageAutoTransferSettingUSDFlag()
                                .equals(MARGIN_BUYING_POWER_SHORTFALL_SECURITIES_TRUE)) {
                    dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                            ERRORS_FRS_ACCOUNT_AUTO_TRANSFER_SETTING,
                            IfaCommonUtil.getMessage(ERRORS_FRS_ACCOUNT_AUTO_TRANSFER_SETTING));
                    
                    return dtoRes;
                }
            }
        }
        
        try {
            dao.insertIfaForeignBondBuyAmountInputInfoRegisterSql003(sql003Req);
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignBondBuyAmountInputInfoRegisterServiceImpl.insertA005}", e);
            }
            //DB登録NG：DB登録エラーを返す。
            return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_PROCESSING_FAILED,
                    IfaCommonUtil.getMessage(ERRORS_PROCESSING_FAILED, new String[] { "外債買付代金入力情報の登録" }));
        }
        
        dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.SUCCESS, INFO_INSERT_COMPLETED,
                IfaCommonUtil.getMessage(INFO_INSERT_COMPLETED, new String[] { "外債買付代金入力情報" }));
        return dtoRes;
    }
}
