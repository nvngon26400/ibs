package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.impl;

import java.util.List;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.IpopoBBApplyNewDao;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.mapper.BBAcceptMapper;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.mapper.BBInvestorAttMapper;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.mapper.IpopoBBApplyNewMapper;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.BBInvestorAttModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoBBCustomerInfoModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IpopoBBApplyNewDaoImpl extends RowSelectableDao implements IpopoBBApplyNewDao {
    
    private static final Logger logger = LoggerFactory.getLogger(IpopoBBApplyNewDaoImpl.class);
    
    @Autowired
    private BBInvestorAttMapper bbInvestorAttMapper;
    
    @Autowired
    private IpopoBBApplyNewMapper ipopoBBApplyNewMapper;
    
    @Autowired
    private BBAcceptMapper bbAcceptMapper;
    
    /**
     * 投資家属性のプルダウンリスト情報を取得する.
     * 
     * @param bbProductCode      銘柄コード
     * @param bbPresentationFrom ブックビルディング申込期間（開始）(YYYYMMDD)
     * @return 投資家属性のプルダウンリスト情報
     * @throws Exception 異常
     */
    public List<BBInvestorAttModel> getBBInvestorAttInfoList(String bbProductCode, String bbPresentationFrom)
            throws Exception {
        
        logger.debug("IpopoBBApplyNewDaoImpl.getBBInvestorAttInfoList: bbProductCode：" + bbProductCode
                + ",bbPresentationFrom：" + bbPresentationFrom);
        
        return getBBInvestorAttInfoListInternal(bbProductCode, bbPresentationFrom);
    }
    
    private List<BBInvestorAttModel> getBBInvestorAttInfoListInternal(String bbProductCode, String bbPresentationFrom)
            throws Exception {
        
        logger.debug("IpopoBBApplyNewDaoImpl.getBBInvestorAttInfoListInternal: bbProductCode：" + bbProductCode
                + ",bbPresentationFrom：" + bbPresentationFrom);
        
        // 投資家属性のプルダウンリスト情報を取得する。
        List<BBInvestorAttModel> sqlResult = bbInvestorAttMapper.getBBInvestorAttInfoList(bbProductCode,
                bbPresentationFrom);
        
        return sqlResult;
        
    }
    
    /**
     * 顧客情報を取得する.
     * 
     * @param butenCode      部店コード
     * @param accountNumber  口座番号
     * @param chargeCodeList 支店コードリスト
     * @param privId         権限ID
     * @param empCode        営業員担当者
     * @param brokerCode     仲介業者コード
     * @return 顧客情報
     * @throws Exception 異常
     */
    public IpopoBBCustomerInfoModel getIpopoBBCustomerInfo(String butenCode, String accountNumber) throws Exception {
        
        logger.debug("IpopoBBApplyNewDaoImpl.getIpopoBBCustomerInfo: butenCode：" + butenCode + ",accountNumber："
                + accountNumber);
        
        return getIpopoBBCustomerInfoInternal(butenCode, accountNumber);
    }
    
    private IpopoBBCustomerInfoModel getIpopoBBCustomerInfoInternal(String butenCode, String accountNumber)
            throws Exception {
        
        logger.debug("IpopoBBApplyNewDaoImpl.getIpopoBBCustomerInfoInternal: butenCode：" + butenCode + ",accountNumber："
                + accountNumber);
        
        // 顧客情報を取得する。
        IpopoBBCustomerInfoModel sqlResult = ipopoBBApplyNewMapper.getIpopoBBCustomerInfo(butenCode, accountNumber);
        
        return sqlResult;
    }
    
    /**
     * 入力データがブックビルディング受付に存在する件数を取得する.
     * 
     * @param bbProductCode      銘柄コード
     * @param bbPresentationFrom ブックビルディング申込期間（開始）(YYYYMMDD)
     * @param butenCode          部店コード
     * @param accountNumber      口座番号
     * @return データ存在件数
     * @throws Exception 異常
     */
    public int getBBAcceptInfoCount(String bbProductCode, String bbPresentationFrom, String butenCode,
            String accountNumber) throws Exception {
        
        logger.debug(
                "IpopoBBApplyNewDaoImpl.getBBAcceptInfoCount: bbProductCode：" + bbProductCode + ",bbPresentationFrom："
                        + bbPresentationFrom + ",butenCode：" + butenCode + ",accountNumber：" + accountNumber);
        
        return getBBAcceptInfoCountInternal(bbProductCode, bbPresentationFrom, butenCode, accountNumber);
    }
    
    private int getBBAcceptInfoCountInternal(String bbProductCode, String bbPresentationFrom, String butenCode,
            String accountNumber) throws Exception {
        
        logger.debug("IpopoBBApplyNewDaoImpl.getBBAcceptInfoCountInternal: bbProductCode：" + bbProductCode
                + ",bbPresentationFrom：" + bbPresentationFrom + ",butenCode：" + butenCode + ",accountNumber："
                + accountNumber);
        
        // 入力データがブックビルディング受付に存在する件数を取得する。
        int sqlResult = bbAcceptMapper.getBBAcceptInfoCount(bbProductCode, bbPresentationFrom, butenCode,
                accountNumber);
        
        return sqlResult;
    }
    
}
