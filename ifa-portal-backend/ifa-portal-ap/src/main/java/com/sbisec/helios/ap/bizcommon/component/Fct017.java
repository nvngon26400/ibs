package com.sbisec.helios.ap.bizcommon.component;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbisec.helios.ap.bizcommon.dao.Fct017Dao;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct017Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct017Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct017Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct017Sql003RequestModel;
import com.sbisec.helios.ap.bizcommon.model.InputFct017Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct017Dto.InquiryMutualFundBrand;
import com.sbisec.helios.ap.bizcommon.model.OutputFct017Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct017Dto.Brand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import lombok.Data;

/**
 * 共通関数：FCT017
 * 確認書受入および強制注文が必要な国内投信銘柄情報取得
　*
 *　@author　SCSK
 */

@Component
public class Fct017 {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Fct017.class);
    
    // 銘柄取得可否　---　1（可能）
    private static final String BRAND_ACQUIRE_PROPRIETY_TRUE = "1";
    
    // 銘柄取得可否　---　0（不可）
    private static final String BRAND_ACQUIRE_PROPRIETY_FALSE = "0";
    
    // 受入要否 --- 1（必要）
    private static final String ACCEPTANCE_NECESSITY_TRUE = "1";
    
    // 受入要否 --- 0（不要）
    private static final String ACCEPTANCE_NECESSITY_FALSE = "0";
    
    // 受入書類分類 --- 1（共通）
    private static final String MUTUAL_FUND_SHORUI_TYPE_COMMON = "1";
    
    // 受入書類分類 --- 0（銘柄別）
    private static final String MUTUAL_FUND_SHORUI_TYPE_BRAND = "0";
    
    // 受入状況 --- 1（受入済）
    private static final String ACCEPTANCE_STATUS_TRUE = "1";
    
    // 受入状況 --- 0（未受入）
    private static final String ACCEPTANCE_STATUS_FALSE = "0";
    
    // 件数1件
    private static final int NUM_1 = 1;
    
    @Autowired
    private Fct017Dao dao;

    /**
     * 共通関数サービス：確認書受入および強制注文が必要な国内投信銘柄情報取得
     *
     * @param inputFct017Dto インプットパラメータ
     * @return OutputFct017Dto アウトプットパラメータ
     */
    public OutputFct017Dto getData(InputFct017Dto inputFct017Dto) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("FCT017.getData");
        }
        // 入力チェック
        if (isInputModelNull(inputFct017Dto)) {
            return new OutputFct017Dto();
        }
        OutputFct017Dto output = new OutputFct017Dto();
        ObjectMapper objectMapper = new ObjectMapper();
        List<InquiryMutualFundBrand> inquiryMutualFundBrandList = objectMapper.convertValue(inputFct017Dto.getInquiryMutualFundBrandList(), 
                new TypeReference<List<InquiryMutualFundBrand>>() {});
        List<Brand> brandList = inquiryMutualFundBrandList.stream()
                .map(InquiryMutualFundBrand::getNriCd)
                .map(nriCd -> {
                    //編集用エリアを用意する。
                    Area area = new Area();
                    //NRIコードを編集用エリアにセットする。
                    area.setBrandCode(nriCd);
                    //リクエスト項目.照会銘柄リスト.NRIコードの全件について、受入が必要な書類の有無を取得する。
                    Fct017Sql001RequestModel sql001RequestModel = new Fct017Sql001RequestModel();
                    sql001RequestModel.setNriCd(nriCd);
                    Fct017Sql001ResponseModel sql001Responsemodel = dao.acceptanceAcquire(sql001RequestModel);
                    if (sql001Responsemodel != null) {
                        if (isAnyAcceptanceCdAcuqired(sql001Responsemodel)) {
                            //受入書類コード１、受入書類コード２、受入書類コード３のいずれか取得した場合
                            readToAreaWhenAcceptanceCdIsAcuqired(area, sql001Responsemodel);
                        } else {
                            //受入書類コード１、受入書類コード２、受入書類コード３のいずれも書類コードが登録されていなかった場合、
                            readToAreaWhenAcceptanceCdIsNotAcuqired(area, sql001Responsemodel);
                        }
                    } else {
                        //以外の場合。
                        readToAreaWhenSql001ResponseModelIsNull(area);
                    }
                    //編集用エリアに保持した受入要否、投信書類分類をもとに、編集用エリアの編集用.銘柄コード.書類コード毎に書類受入状況を取得する。
                    readAcceptanceStatusToArea(area, inputFct017Dto);
                    //編集用エリア全件について、編集用.銘柄コード.書類コード毎に編集用エリアの内容をレスポンスにセットする。
                    Brand brand = new Brand();
                    //銘柄リスト.NRIコードへ、編集用.銘柄コードを格納
                    brand.setNriCd(area.getBrandCode());
                    //銘柄リスト.NRIコード.銘柄取得可否
                    brand.setBrandAcquirePropriety(area.getBrandAcquirePropriety());
                    //銘柄リスト.NRIコード.書類コードへ、編集用.銘柄コード.書類コードを格納
                    brand.setShoruiCd(area.getShoruiCd());
                    //銘柄リスト.NRIコード.書類コード.受入要否へ、編集用.銘柄コード.書類コード.受入要否を格納
                    brand.setAcceptanceNecessity(area.getAcceptanceNecessity());
                    //銘柄リスト.NRIコード.書類コード.投信銘柄種別へ、編集用.銘柄コード.書類コード.投信銘柄種別を格納
                    brand.setMutualFundBrandClass(area.getMutualFundBrandClass());
                    //銘柄リスト.NRIコード.書類コード.受入状況へ、編集用.銘柄コード.書類コード.受入状況を格納
                    brand.setAcceptanceStatus(area.getAcceptanceStatus());
                    return brand;
                }).collect(Collectors.toList());
        //呼び出し元に処理結果を返却する。
        output.setBrandList(brandList);
        return output;
    }
    
    /**
     * 入力チェック
     *
     * @param inputFct017Dto FCT017入力モデル
     * @return boolean チェック結果
     */
    private boolean isInputModelNull(InputFct017Dto inputFct017Dto) {
        return inputFct017Dto.getAccountNumber() == null
                || inputFct017Dto.getButenCode() == null
                || inputFct017Dto.getInquiryMutualFundBrandList() == null;
    }
    
    /**
     * 受入書類コード１、受入書類コード２、受入書類コード３のいずれか取得したかどうかを確認
     *
     * @param sql001Responsemodel SQL001の出力モデル
     * @return boolean 確認結果
     */
    private boolean isAnyAcceptanceCdAcuqired(Fct017Sql001ResponseModel sql001Responsemodel) {
        return !StringUtils.isEmpty(sql001Responsemodel.getAcceptanceCd1()) 
                || !StringUtils.isEmpty(sql001Responsemodel.getAcceptanceCd2()) 
                || !StringUtils.isEmpty(sql001Responsemodel.getAcceptanceCd3());
    }
    
    /**
     * 受入書類コード１、受入書類コード２、受入書類コード３のいずれか取得した場合の処理
     *
     * @param area 編集用エリア
     * @param sql001Responsemodel SQL001の出力モデル
     */
    private void readToAreaWhenAcceptanceCdIsAcuqired(Area area, Fct017Sql001ResponseModel sql001Responsemodel) {
        //編集用.銘柄コード.銘柄取得可否　←　1（可能）をセット。
        area.setBrandAcquirePropriety(BRAND_ACQUIRE_PROPRIETY_TRUE);
        //取得したの書類コードについて、それに紐づく受入書類分類を取得した場合。
        if (isAcceptanceCd1Acuqired(sql001Responsemodel)) {
            //編集用.銘柄コード.書類コードへ、取得した受入書類コード１～3を書類コード毎にセット。
            area.setShoruiCd(sql001Responsemodel.getAcceptanceCd1());
            //編集用.銘柄コード.書類コード.受入書類分類へ、取得した受入書類コード１～3に紐づく投信受入書類リスト.コード２をセット。
            area.setMutualFundShoruiType(sql001Responsemodel.getCode2BondingAcceptanceCd1());
            //編集用.銘柄コード.書類コード.受入要否へ、取得した受入書類コード毎に1（必要）をセット。
            area.setAcceptanceNecessity(ACCEPTANCE_NECESSITY_TRUE);
            //編集用.銘柄コード.書類コード.投信銘柄種別へ、取得した受入書類コード１～3に紐づく投信受入書類リスト.値をセット。
            area.setMutualFundBrandClass(sql001Responsemodel.getNameBondingAcceptanceCd1());
        } else if (isAcceptanceCd2Acuqired(sql001Responsemodel)) {
            area.setShoruiCd(sql001Responsemodel.getAcceptanceCd2());
            area.setMutualFundShoruiType(sql001Responsemodel.getCode2BondingAcceptanceCd2());
            area.setAcceptanceNecessity(ACCEPTANCE_NECESSITY_TRUE);
            area.setMutualFundBrandClass(sql001Responsemodel.getNameBondingAcceptanceCd2());
        } else if (isAcceptanceCd3Acuqired(sql001Responsemodel)) {
            area.setShoruiCd(sql001Responsemodel.getAcceptanceCd3());
            area.setMutualFundShoruiType(sql001Responsemodel.getCode2BondingAcceptanceCd3());
            area.setAcceptanceNecessity(ACCEPTANCE_NECESSITY_TRUE);
            area.setMutualFundBrandClass(sql001Responsemodel.getNameBondingAcceptanceCd3());
        } else {
            //取得した書類コードについて、それに紐づく受入書類分類を取得していない場合。
            //編集用.銘柄コード.書類コードへ、取得した受入書類コード１～3を書類コード毎にセット。
            if (!StringUtils.isEmpty(sql001Responsemodel.getAcceptanceCd1())) {
                area.setShoruiCd(sql001Responsemodel.getAcceptanceCd1());
            } else if (!StringUtils.isEmpty(sql001Responsemodel.getAcceptanceCd2())) {
                area.setShoruiCd(sql001Responsemodel.getAcceptanceCd2());
            } else if (!StringUtils.isEmpty(sql001Responsemodel.getAcceptanceCd3())) {
                area.setShoruiCd(sql001Responsemodel.getAcceptanceCd3());
            }
            //編集用.銘柄コード.書類コード.受入書類分類へ、Nullをセット。
            area.setMutualFundShoruiType(null);
            //編集用.銘柄コード.書類コード.受入要否へ、取得した受入書類コード毎に0（不要）をセット。
            area.setAcceptanceNecessity(ACCEPTANCE_NECESSITY_FALSE);
            //編集用.銘柄コード.書類コード.投信銘柄種別へ、Nullをセット。
            area.setMutualFundBrandClass(null);
        }
    }
    
    /**
     * 受入書類コード１、受入書類コード２、受入書類コード３のいずれも書類コードが登録されていなかった場合の処理
     *
     * @param area 編集用エリア
     * @param sql001Responsemodel SQL001の出力モデル
     */
    private void readToAreaWhenAcceptanceCdIsNotAcuqired(Area area, Fct017Sql001ResponseModel sql001Responsemodel) {
        //編集用.銘柄コード.銘柄取得可否へ、1（可能）をセット。
        area.setBrandAcquirePropriety(BRAND_ACQUIRE_PROPRIETY_TRUE);
        //編集用.銘柄コード.書類コード、 編集用.銘柄コード.書類コード.受入書類分類、編集用.銘柄コード.書類コード.受入要否、編集用.銘柄コード.書類コード.投信銘柄種別の4項目には、何もセットしない。
    }
    
    /**
     * SQL001のレスポンスがnullの場合の処理
     *
     * @param area 編集用エリア
     */
    private void  readToAreaWhenSql001ResponseModelIsNull(Area area) {
        //編集用.銘柄コード.銘柄取得可否へ、0（不可）をセット。
        area.setBrandAcquirePropriety(BRAND_ACQUIRE_PROPRIETY_FALSE);
        //編集用.銘柄コード.書類コード、 編集用.銘柄コード.書類コード.受入書類分類、編集用.銘柄コード.書類コード.受入要否、編集用.銘柄コード.書類コード.投信銘柄種別の4項目には、何もセットしない。
    }
    
    /**
     * 編集用エリアに保持した受入要否、投信書類分類をもとに、編集用エリアの編集用.銘柄コード.書類コード毎に書類受入状況を取得する
     *
     * @param area 編集用エリア
     * @param inputFct017Dto FCT017入力モデル
     */
    private void readAcceptanceStatusToArea(Area area, InputFct017Dto inputFct017Dto) {
        if (ACCEPTANCE_NECESSITY_TRUE.equals(area.getAcceptanceNecessity()) && MUTUAL_FUND_SHORUI_TYPE_BRAND.equals(area.getMutualFundShoruiType())) {
            //編集用.銘柄コード.書類コード.受入要否が1（必要）かつ編集用.銘柄コード.書類コード.受入書類分類が0（銘柄別）の場合
            //SQL002を発行
            Fct017Sql002RequestModel sql002RequestModel = new Fct017Sql002RequestModel();
            sql002RequestModel.setButenCode(inputFct017Dto.getButenCode());
            sql002RequestModel.setAccountNumber(inputFct017Dto.getAccountNumber());
            sql002RequestModel.setBrandCode(area.getBrandCode());
            sql002RequestModel.setShoruiCd(area.getShoruiCd());
            int count = dao.brandDocumentAcceptanceAcquire(sql002RequestModel);
            if (count >= NUM_1) {
                //取得件数1件の場合
                //編集用.銘柄コード.書類コード.受入状況へ、1（受入済）をセット。
                area.setAcceptanceStatus(ACCEPTANCE_STATUS_TRUE);
            } else {
                //取得件数0件の場合
                //編集用.銘柄コード.書類コード.受入状況へ、0（未受入）をセット。
                area.setAcceptanceStatus(ACCEPTANCE_STATUS_FALSE);
            }
        } else if (ACCEPTANCE_NECESSITY_TRUE.equals(area.getAcceptanceNecessity()) && MUTUAL_FUND_SHORUI_TYPE_COMMON.equals(area.getMutualFundShoruiType())) {
            //編集用.銘柄コード.書類コード.受入要否が1（必要）かつ編集用.銘柄コード.書類コード.受入書類分類が1（共通）の場合
            //SQL003を発行
            Fct017Sql003RequestModel sql003RequestModel = new Fct017Sql003RequestModel();
            sql003RequestModel.setButenCode(inputFct017Dto.getButenCode());
            sql003RequestModel.setAccountNumber(inputFct017Dto.getAccountNumber());
            sql003RequestModel.setShoruiCd(area.getShoruiCd());
            int count = dao.commonDocumentAcceptanceAcquire(sql003RequestModel);
            if (count >= NUM_1) {
                //取得件数1件以上の場合
                //編集用.銘柄コード.書類コード.受入状況へ、1（受入済）をセット。
                area.setAcceptanceStatus(ACCEPTANCE_STATUS_TRUE);
            } else {
                //取得件数0件の場合
                //用.銘柄コード.書類コード.受入状況へ、0（未受入）をセット。
                area.setAcceptanceStatus(ACCEPTANCE_STATUS_FALSE);
            }
        }
    }
    
    /**
     * 受入書類コード１を取得したかどうかを確認
     *
     * @param sql001Responsemodel SQL001の出力モデル
     * @return boolean 確認結果
     */
    private boolean isAcceptanceCd1Acuqired(Fct017Sql001ResponseModel sql001Responsemodel) {
        return !StringUtils.isEmpty(sql001Responsemodel.getAcceptanceCd1()) 
                && !StringUtils.isEmpty(sql001Responsemodel.getCode2BondingAcceptanceCd1());
    }
    
    /**
     * 受入書類コード2を取得したかどうかを確認
     *
     * @param sql001Responsemodel SQL001の出力モデル
     * @return boolean 確認結果
     */
    private boolean isAcceptanceCd2Acuqired(Fct017Sql001ResponseModel sql001Responsemodel) {
        return !StringUtils.isEmpty(sql001Responsemodel.getAcceptanceCd2()) 
                && !StringUtils.isEmpty(sql001Responsemodel.getCode2BondingAcceptanceCd2());
    }
    
    /**
     * 受入書類コード3を取得したかどうかを確認
     *
     * @param sql001Responsemodel SQL001の出力モデル
     * @return boolean 確認結果
     */
    private boolean isAcceptanceCd3Acuqired(Fct017Sql001ResponseModel sql001Responsemodel) {
        return !StringUtils.isEmpty(sql001Responsemodel.getAcceptanceCd3()) 
                && !StringUtils.isEmpty(sql001Responsemodel.getCode2BondingAcceptanceCd3());
    }
    
    
    
    /**
     * 編集用エリア
     */
    @Data
    private static class Area {
        
        //編集用.銘柄コード
        private String brandCode;
        
        //編集用.銘柄コード.銘柄取得可否
        private String brandAcquirePropriety;
        
        //編集用.銘柄コード.書類コード
        private String shoruiCd;
        
        //編集用.銘柄コード.受入要否
        private String acceptanceNecessity;
        
        //編集用.銘柄コード.投信書類分類
        private String mutualFundShoruiType;
        
        //編集用.銘柄コード.投信銘柄種別
        private String mutualFundBrandClass;
        
        //編集用.銘柄コード.受入状況
        private String acceptanceStatus;
        
    }
    
}
