package com.sbisec.helios.ap.brokerageMenu.ipoPo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct006;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct006Dto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.IpopoBBApplyNewDao;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.IpopoBBApplyUploadDao;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.BBInvestorAttModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoBBApplyEdelivAgreementModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoBBApplyUploadModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoBBCustomerInfoModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoUploadBBAcceptModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoUploadBBCustomerOverMaxCheckModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.SectionModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.service.IpopoBBApplyUploadService;
import com.sbisec.helios.ap.common.annotation.dao.EtintraTransactional;
import com.sbisec.helios.ap.common.model.MCode;
import com.sbisec.helios.ap.common.service.MCodeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;

@Component(value = "comIpopoBBApplyUploadService")
public class IpopoBBApplyUploadServiceImplL implements IpopoBBApplyUploadService {
    
    private static final Logger logger = LoggerFactory.getLogger(IpopoBBApplyUploadServiceImplL.class);
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct003 fct003;
    
    @Autowired
    private Fct006 fct006;
    
    @Autowired
    protected MCodeService mCodeService;
    
    @Autowired
    private IpopoBBApplyUploadDao ipopoBBApplyUploadDao;
    
    @Autowired
    private IpopoBBApplyNewDao ipopoBBApplyNewDao;
    
    /** 証券金銭種別：　国内株式 */
    private static final String DOMESTIC_STOCK = "01";
    
    /** 取引種別：　BB申込 */
    private static final String BB_APPLY = "9";
    
    /** 国内外国区分：　国内 */
    private static final String DOMESTIC = "0";
    
    /** 商品区分：　株式 */
    private static final String STOCK = "1 ";
    
    /** コンプラチェック種類：　買付注文 */
    private static final String BUY_ORDER = "1";
    
    /**
     * 業務チェック用な銘柄情報を取得する
     */
    @Override
    public IpopoBBApplyUploadModel getIpopoBBBrandInfo(String brandCode) {
        
        logger.debug("IpopoBBApplyUploadServiceImplL.getIpopoBBBrandInfo: brandCode：" + brandCode);
        return ipopoBBApplyUploadDao.getIpopoBBBrandInfo(brandCode);
    }
    
    /**
     * 年間裁量配分割当回数情報を取得する
     */
    @Override
    public IpopoUploadBBCustomerOverMaxCheckModel getOverMaxSairyouCount(String butenCode, String accountNumber,
            String brandCode) throws Exception {
        
        logger.debug("IpopoBBApplyUploadServiceImplL.toCheckOverMaxSairyouCount: butenCode：" + butenCode
                + ",accountNumber:" + accountNumber + ",brandCode:" + brandCode);
        // 年間裁量配分割当回数情報を検索する
        IpopoUploadBBCustomerOverMaxCheckModel model = ipopoBBApplyUploadDao.getMaybeSairyouCount(butenCode,
                accountNumber);
        if (model != null) {
            // 未抽選の段階裁量件数を検索する(裁量配分割当回数(未抽選) + 移管前の裁量配分割当回数(未抽選))
            // 1.裁量配分割当回数(未抽選)
            int unSelectionCountForAfter = ipopoBBApplyUploadDao.getUnSelectionCount(butenCode, accountNumber,
                    brandCode);
            // 2.移管前の裁量配分割当回数(未抽選)
            int unSelectionCountForBefore = 0;
            if (!StringUtil.isNullOrEmpty(model.getOldBranchNo())
                    && !StringUtil.isNullOrEmpty(model.getOldAccountNo())) {
                unSelectionCountForBefore = ipopoBBApplyUploadDao.getUnSelectionCount(model.getOldBranchNo(),
                        model.getOldAccountNo(), brandCode);
            }
            // 未抽選の段階裁量件数を計算する裁量配分割当回数(未抽選) + 移管前の裁量配分割当回数(未抽選)
            int unSelectionCount = unSelectionCountForAfter + unSelectionCountForBefore;
            
            // 本年の年間裁量配分割当回数合計、未抽選 + 移管前 + 移管後
            int sairyouAlloCountTotal = unSelectionCount + model.getSairyouAlloCount() + model.getOldSairyouAlloCount();
            model.setSairyouAlloCountTotal(sairyouAlloCountTotal);
            
            // 裁量配分可能余回数を計算する
            int maybeSairyouCount = model.getMaybeSairyouCount() - unSelectionCount;
            model.setMaybeSairyouCount(maybeSairyouCount);
        }
        
        return model;
    }
    
    /**
     * 投資家属性を取得
     *
     * @throws Exception
     */
    @Override
    public List<BBInvestorAttModel> getBBInvestorAttInfoList(String brandCode, String bbPresentAtionFromYmd)
            throws Exception {
        
        logger.debug("IpopoBBApplyUploadServiceImplL.getBBInvestorAttInfoList: brandCode：" + brandCode
                + ",bbPresentAtionFromYmd:" + bbPresentAtionFromYmd);
        return ipopoBBApplyNewDao.getBBInvestorAttInfoList(brandCode, bbPresentAtionFromYmd);
    }
    
    /**
     * ブックビルディング受付に存在チェック用
     */
    @Override
    public int getBBAcceptInfoCount(String brandCode, String bbPresentationFromYmd, String butenCode,
            String accountNumber) throws Exception {
        
        logger.debug(
                "IpopoBBApplyUploadServiceImplL.getCustomerInfo: brandCode：" + brandCode + ",bbPresentationFromYmd:"
                        + bbPresentationFromYmd + ",butenCode:" + butenCode + ",accountNumber:" + accountNumber);
        return ipopoBBApplyNewDao.getBBAcceptInfoCount(brandCode, bbPresentationFromYmd, butenCode, accountNumber);
    }
    
    /**
     * CSV登録処理を行う。
     */
    @Override
    @EtintraTransactional(propagation = Propagation.REQUIRES_NEW)
    public int insertUploadIpopoBBApplyInfo(List<IpopoUploadBBAcceptModel> insertList) throws Exception {
        
        logger.debug("IpopoBBApplyUploadServiceImplL.insertUploadIpopoBBApplyInfo: insertList：" + insertList);
        ObjectMapper mapper = new ObjectMapper();
        List<IpopoUploadBBAcceptModel> paramslist = mapper.convertValue(insertList,
                new TypeReference<List<IpopoUploadBBAcceptModel>>() {
                });
        
        ipopoBBApplyUploadDao.insertUploadIpopoBBApplyInfoToBBAccept(paramslist);
        // 裁量配分リストを作成する
        List<IpopoUploadBBAcceptModel> sairyouList = new ArrayList<IpopoUploadBBAcceptModel>();
        for (IpopoUploadBBAcceptModel ipopoUploadBBAcceptModel : paramslist) {
            // 裁量希望数量 が非空非零の場合、SR_ACCEPT(裁量配分受付)を登録する
            if (!StringUtil.isNullOrEmpty(ipopoUploadBBAcceptModel.getQuantitySairyou())
                    && !"0".equals(ipopoUploadBBAcceptModel.getQuantitySairyou())) {
                sairyouList.add(ipopoUploadBBAcceptModel);
            }
        }
        if (sairyouList.size() > 0) {
            ipopoBBApplyUploadDao.insertUploadIpopoBBApplyInfoToSrAccept(sairyouList);
        }
        // 更新成功の場合、1を設定する
        return 1;
    }
    
    /**
     * 顧客情報を取得する.
     */
    @Override
    public IpopoBBCustomerInfoModel getIpopoBBCustomerInfo(String butenCode, String accountNumber) throws Exception {
        
        logger.debug("IpopoBBApplyUploadServiceImplL.getIpopoBBCustomerInfo: butenCode：" + butenCode + ",accountNumber："
                + accountNumber);
        
        return ipopoBBApplyNewDao.getIpopoBBCustomerInfo(butenCode, accountNumber);
    }
    
    /**
     * セクションIDとセクション名を検索する
     */
    @Override
    public SectionModel getSectionInfo(String butenCode, String accountNumber) throws Exception {
        
        return ipopoBBApplyUploadDao.getSectionInfo(butenCode, accountNumber);
    }
    
    /**
     * 電子交付同意状況を取得する
     */
    @Override
    public IpopoBBApplyEdelivAgreementModel getEdelivAgreementInfo(String butenCode, String accountNumber)
            throws Exception {
        
        return ipopoBBApplyUploadDao.getEdelivAgreementInfo(butenCode, accountNumber);
    }
    
    /**
     * 共通関数（FCT001:利用者顧客参照権限チェック）情報を取得する。
     */
    @Override
    public OutputFct001Dto getFct001(String butenCode, String accountNumber) throws Exception {
        
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        //　部店コード
        inputFct001Dto.setButenCode(butenCode);
        // 口座番号　
        inputFct001Dto.setAccountNumber(accountNumber);
        
        return fct001.doCheck(inputFct001Dto);
    }
    
    /**
     * 共通関数（FCT003:取引コース媒介可否チェック）情報を取得する。
     */
    @Override
    public OutputFct003Dto getFct003(String butenCode, String accountNumber) throws Exception {
        
        InputFct003Dto inputFct003Dto = new InputFct003Dto();
        //　部店コード
        inputFct003Dto.setButenCode(butenCode);
        // 口座番号　
        inputFct003Dto.setAccountNumber(accountNumber);
        // 証券金銭種別
        inputFct003Dto.setProductCd(DOMESTIC_STOCK);
        // 取引種別
        inputFct003Dto.setTradeCd(BB_APPLY);
        
        return fct003.doCheck(inputFct003Dto);
    }
    
    /**
     * 共通関数（FCT006:コンプラランクチェック）情報を取得する。
     */
    @Override
    public OutputFct006Dto getFct006(String butenCode, String accountNumber, String brandCode, String invitationType,
            String orderMethod) throws Exception {
        
        InputFct006Dto inputFct006Dto = new InputFct006Dto();
        //　部店コード
        inputFct006Dto.setButenCode(butenCode);
        // 口座番号　
        inputFct006Dto.setAccountNumber(accountNumber);
        // 国内外国区分
        inputFct006Dto.setBrDomesticFgnInd(DOMESTIC);
        // 商品区分
        inputFct006Dto.setBrBrandInd(STOCK);
        // 銘柄コード１
        inputFct006Dto.setBrandCode1(brandCode);
        // 勧誘区分
        inputFct006Dto.setInvitationType(invitationType);
        // 受注方法
        inputFct006Dto.setOrderMethod(orderMethod);
        // コンプラチェック種類
        inputFct006Dto.setComplaCheckKind(BUY_ORDER);
        
        return fct006.doCheck(inputFct006Dto);
    }
    
    /**
     * コードマスタから画面用コメントを取得する。
     */
    @Override
    public List<MCode> getMCodeList(String codeType, String code1, String code2) throws Exception {
        
        return mCodeService.getMCodeList(codeType, code1, code2);
    }
    
}
