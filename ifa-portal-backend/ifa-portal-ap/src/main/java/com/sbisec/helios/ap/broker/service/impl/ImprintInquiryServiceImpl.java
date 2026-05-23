package com.sbisec.helios.ap.broker.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.broker.model.ImprintInquiryModel;
import com.sbisec.helios.ap.broker.protocol.GetImageForIFAResp;
import com.sbisec.helios.ap.broker.service.ImprintInquiryService;
import com.sbisec.helios.ap.broker.service.PapyImprintService;
import com.sbisec.helios.ap.broker.util.PapyApiUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImprintInquiryServiceImpl implements ImprintInquiryService {
    
    /** 印影取得用のServiceクラス */
    @Autowired
    private PapyImprintService papyImprintService;
    
    @Autowired
    private static final Logger LOGGER = LoggerFactory.getLogger(ImprintInquiryServiceImpl.class);
    
    /**
     * 印影情報を取得する
     * 
     * @param ifaIdentifier IFA識別子
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @return DataList:DataList<ImprintInquiryModel>
     */
    @Override
    public DataList<ImprintInquiryModel> getImprintImage(String ifaIdentifier, String butenCode, String accountNumber)
            throws Exception {
        
        // 取得したレスポンスを画面返却用のデータリストに格納する。
        ImprintInquiryModel model = new ImprintInquiryModel();
        List<ImprintInquiryModel> imprintImageList = new ArrayList<ImprintInquiryModel>();
        DataList<ImprintInquiryModel> dataList = new DataList<ImprintInquiryModel>();
        
        GetImageForIFAResp resp = null;
        
        // PAPY-APIに接続して、印影を取得する。
        try {
            resp = papyImprintService.getImprintImage(ifaIdentifier, butenCode, accountNumber);
        } catch (Exception e) {
            PapyApiUtil.getValidationException(dataList, e);
            return dataList;
        }
        
        // APIから取得した値をImprintInquiryModelに格納する。
        setImprintData(model, resp);
        
        // 取得結果をdataListに格納して返却する。
        imprintImageList.add(model);
        dataList.setDataList(imprintImageList);
        
        return dataList;
    }
    
    /**
     * 印影情報をModelに格納する
     * @param model　ImprintInquiryモデル
     * @param resp 印影情報レスポンス
     * @return model ImprintInquiryモデル
     */
    private ImprintInquiryModel setImprintData(ImprintInquiryModel model, GetImageForIFAResp resp) {
        
        // Modelに印影情報を格納する
        model.setReportCode(resp.getReportCode());
        model.setReportGroup(resp.getReportGroup());
        model.setReportName(resp.getReportName());
        model.setAcceptDate(resp.getAcceptDate());
        model.setRegisterDate(resp.getRegisterDate());
        model.setReportFile(resp.getReportFile());
        model.setWidth(resp.getWidth());
        model.setHeight(resp.getHeight());
        model.setErrorCode(resp.getErrorCode());
        model.setErrorMessage(resp.getErrorMessage());
        // 印影情報を格納したModelを返却する
        return model;
    }
}
