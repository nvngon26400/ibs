package com.sbisec.helios.ap.bizcommon.component;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbisec.helios.ap.bizcommon.dao.Fct025Dao;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct025Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct025Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct025Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct025Sql002ResponseModel;
import com.sbisec.helios.ap.bizcommon.model.InputFct025Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct025Dto;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 共通関数：FCT025
 * 国内投信Indigo銘柄設定チェック

 * @author 鄒
 *
 */
@Component
public class Fct025 {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Fct025.class);
    
    @Autowired
    private Fct025Dao fct025Dao;
    
    //0：クローズド
    private static final String ET_TYPE_0 = "0";
    
    //1：取引中
    private static final String ET_TYPE_1 = "1";
    
    //2：買付停止中
    private static final String ET_TYPE_2 = "2";
    
    //購入可否判定区分(1：E*TRADE及び全仲介業者取扱)
    private static final String BUY_TYPE_1 = "1";
    
    //購入可否判定区分(2：E*TRADE及び一部仲介業者取扱)
    private static final String BUY_TYPE_2 = "2";
    
    //購入可否判定区分(3：一部仲介業者のみ取扱)
    private static final String BUY_TYPE_3 = "3";
    
    //購入可否判定区分(4：E*TRADEのみ取扱)
    private static final String BUY_TYPE_4 = "4";
    
    //購入可否判定区分(5：E*TRADEは取扱で一部仲介業者のみ取扱不可)
    private static final String BUY_TYPE_5 = "5";
    
    //購入可否判定区分(6：E*TRADEは取扱不可で一部仲介業者のみ取扱不可)
    private static final String BUY_TYPE_6 = "6";
    
    //E*TRADE扱い可否=0:売買不可
    private static final String ETRADE_0 = "0";
    
    //E*TRADE扱い可否=1:売買可
    private static final String ETRADE_1 = "1";
    
    //E*TRADE扱い可否=2:売のみ可
    private static final String ETRADE_2 = "2";
    
    //仲介業者コードが存在する
    private static final String BROKER_0 = "0";
    
    //仲介業者コードが存在しない
    private static final String BROKER_1 = "1";
    
    /**
     * 共通関数：FCT025
    
     * @param input リクエスト
     * @return outputFct025Dto
     */
    public OutputFct025Dto doCheck(InputFct025Dto input) {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Fct025.doCheck");
        }
        ObjectMapper mapper = new ObjectMapper();
        
        input.setBrandList(mapper.convertValue(input.getBrandList(), new TypeReference<List<InputFct025Dto.Brand>>() {
        }));
        
        OutputFct025Dto outputFct025Dto = new OutputFct025Dto();
        outputFct025Dto.setBrokerCode(input.getBrokerCode());
        
        List<String> nriCodeList = new ArrayList();
        for (InputFct025Dto.Brand brand : input.getBrandList()) {
            nriCodeList.add(brand.getNriCd());
        }
        //①投信銘柄マスタテーブルからE*TRADE扱い区分を取得し以下の状態を各銘柄のレスポンスにセットする。
        Fct025Sql001RequestModel fct025Sql001RequestModel = new Fct025Sql001RequestModel();
        fct025Sql001RequestModel.setNriCodeList(nriCodeList);
        List<Fct025Sql001ResponseModel> fct025Sql001ResponseModelList = fct025Dao
                .getFct025Sql001(fct025Sql001RequestModel);
        
        if (null != fct025Sql001ResponseModelList && fct025Sql001ResponseModelList.size() > 0) {
            List brandList = new ArrayList();
            for (Fct025Sql001ResponseModel fct025Sql001ResponseModel : fct025Sql001ResponseModelList) {
                OutputFct025Dto.Brand brandOutput = new OutputFct025Dto.Brand();
                brandOutput.setNriCd(fct025Sql001ResponseModel.getNriCode());
                if (ET_TYPE_0.equals(fct025Sql001ResponseModel.getEtType())) {
                    //0：クローズド　の場合（データ0件の場合も含む）
                    //銘柄リスト.E*TRADE扱い可否=0:売買不可
                    brandOutput.setIsEtradeService(ETRADE_0);
                    nriCodeList.remove(fct025Sql001ResponseModel.getNriCode());
                } else if (ET_TYPE_1.equals(fct025Sql001ResponseModel.getEtType())) {
                    //1：取引中（売買可能）の場合
                    //銘柄リスト.E*TRADE扱い可否=1:売買可
                    brandOutput.setIsEtradeService(ETRADE_1);
                } else if (ET_TYPE_2.equals(fct025Sql001ResponseModel.getEtType())) {
                    //2：買付停止中　の場合
                    //銘柄リスト.E*TRADE扱い可否=2:売のみ可
                    brandOutput.setIsEtradeService(ETRADE_2);
                }
                brandList.add(brandOutput);
            }
            outputFct025Dto.setBrandList(brandList);
        } else {
            List brandList = new ArrayList();
            for (InputFct025Dto.Brand brandInput : input.getBrandList()) {
                OutputFct025Dto.Brand brandOutput = new OutputFct025Dto.Brand();
                brandOutput.setNriCd(brandInput.getNriCd());
                brandOutput.setIsEtradeService(ETRADE_0);
                brandList.add(brandOutput);
            }
            outputFct025Dto.setBrandList(brandList);
            return outputFct025Dto;
        }
        //②投信銘柄マスタから購入可否判定区分、投信明細から取扱仲介業者を取得する。
        Fct025Sql002RequestModel fct025Sql002RequestModel = new Fct025Sql002RequestModel();
        fct025Sql002RequestModel.setNriCodeList(nriCodeList);
        List<Fct025Sql002ResponseModel> fct025Sql002ResponseModelList = fct025Dao
                .getFct025Sql002(fct025Sql002RequestModel);
        for (Fct025Sql002ResponseModel fct025Sql002ResponseModel : fct025Sql002ResponseModelList) {
            //データがある場合、次の処理
            
            for (OutputFct025Dto.Brand brand : outputFct025Dto.getBrandList()) {
                if (brand.getNriCd().equals(fct025Sql002ResponseModel.getNriCode())) {
                    if (fct025Sql002ResponseModel.getBuyType().equals(BUY_TYPE_1)) {
                        //購入可否判定区分=1：E*TRADE及び全仲介業者取扱　の場合
                        //銘柄リスト.仲介業者扱可否=1:可
                        brand.setIsBrokerService(BROKER_1);
                    } else if (fct025Sql002ResponseModel.getBuyType().equals(BUY_TYPE_2)) {
                        //購入可否判定区分=2：E*TRADE及び一部仲介業者取扱　の場合
                        if (StringUtils.isNotEmpty(fct025Sql002ResponseModel.getMediationType())) {
                            String[] mediationTypeArray = fct025Sql002ResponseModel.getMediationType().split(";");
                            List mediationTypeList = new ArrayList();
                            for (String mediationType : mediationTypeArray) {
                                mediationTypeList.add(mediationType.substring(1));
                            }
                            if (mediationTypeList.contains(outputFct025Dto.getBrokerCode())) {
                                //取扱仲介業者リストにリクエスト.仲介業者コードが存在する
                                //銘柄リスト.仲介業者扱可否=1:可
                                brand.setIsBrokerService(BROKER_1);
                            } else {
                                //取扱仲介業者リストにリクエスト.仲介業者コードが存在しない
                                //銘柄リスト.仲介業者扱可否=0:不可
                                brand.setIsBrokerService(BROKER_0);
                            }
                        }
                    } else if (fct025Sql002ResponseModel.getBuyType().equals(BUY_TYPE_3)) {
                        //購入可否判定区分=3：一部仲介業者のみ取扱　の場合
                        if (StringUtils.isNotEmpty(fct025Sql002ResponseModel.getMediationType())) {
                            String[] mediationTypeArray = fct025Sql002ResponseModel.getMediationType().split(";");
                            List mediationTypeList = new ArrayList();
                            for (String mediationType : mediationTypeArray) {
                                mediationTypeList.add(mediationType.substring(1));
                            }
                            if (mediationTypeList.contains(outputFct025Dto.getBrokerCode())) {
                                //取扱仲介業者リストにリクエスト.仲介業者コードが存在する
                                brand.setIsBrokerService(BROKER_1);
                            } else {
                                //取扱仲介業者リストにリクエスト.仲介業者コードが存在しない
                                brand.setIsBrokerService(BROKER_0);
                            }
                        }
                    } else if (fct025Sql002ResponseModel.getBuyType().equals(BUY_TYPE_4)) {
                        //購入可否判定区分=4：E*TRADEのみ取扱　の場合
                        //銘柄リスト.仲介業者扱可否=0:不可
                        brand.setIsBrokerService(BROKER_0);
                    } else if (fct025Sql002ResponseModel.getBuyType().equals(BUY_TYPE_5)
                            || fct025Sql002ResponseModel.getBuyType().equals(BUY_TYPE_6)) {
                        //購入可否判定区分=5：E*TRADEは取扱で一部仲介業者のみ取扱不可　または
                        //購入可否判定区分=6：E*TRADEは取扱不可で一部仲介業者のみ取扱不可　の場合
                        if (StringUtils.isNotEmpty(fct025Sql002ResponseModel.getMediationType())) {
                            String[] mediationTypeArray = fct025Sql002ResponseModel.getMediationType().split(";");
                            List mediationTypeList = new ArrayList();
                            for (String mediationType : mediationTypeArray) {
                                mediationTypeList.add(mediationType.substring(1));
                            }
                            if (mediationTypeList.contains(outputFct025Dto.getBrokerCode())) {
                                //取扱仲介業者リストにリクエスト.仲介業者コードが存在する
                                //銘柄リスト.仲介業者扱可否=0:不可
                                brand.setIsBrokerService(BROKER_0);
                            } else {
                                //取扱仲介業者リストにリクエスト.仲介業者コードが存在しない
                                //銘柄リスト.仲介業者扱可否=1:可
                                brand.setIsBrokerService(BROKER_1);
                            }
                        }
                    }
                }
            }
            
        }
        
        return outputFct025Dto;
    }
}
