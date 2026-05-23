package com.sbisec.helios.ap.bizcommon.component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbisec.helios.ap.bizcommon.dao.Fct022Dao;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct022Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct022Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct022Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct022Sql002ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct022Sql003RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct022Sql003RequestModel.Sql003ReqModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct022Sql003ResponseModel;
import com.sbisec.helios.ap.bizcommon.model.InputFct022Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct022Dto.BrandForInput;
import com.sbisec.helios.ap.bizcommon.model.OutputFct022Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct022Dto.Brand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 共通関数：FCT022 国内投信基準価格・手数料取得
 *
 * @author 陳
 */

@Component
public class Fct022 {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Fct022.class);
    
    @Autowired
    private Fct022Dao fct022Dao;
    
    /**
     * 国内投信銘柄のNRIコードを指定し、情報を取得する。
     *
     * @param input FCT022リクエストDTO
     * @return output FCT022レスポンスDTO
     */
    public OutputFct022Dto getData(InputFct022Dto input) {
        
        // ログ出力
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Fct022.getData");
        }
        
        // レスポンスDTO
        OutputFct022Dto result = new OutputFct022Dto();
        result.setBrandList(new ArrayList<>());
        
        // 必須項目について、入力値の有無チェック、ないの場合は空DTOをリターンする
        if (input == null || input.getBrandList().isEmpty()) {
            return result;
        }
        
        // リクエストDTO中の銘柄リストを取得、後のSQLリクエストモデルを使用するため
        ObjectMapper mapper = new ObjectMapper();
        List<BrandForInput> inputList = mapper.convertValue(input.getBrandList(),
                new TypeReference<List<BrandForInput>>() {
                });
        
        // 編集用Areaリスト
        List<AreaBrand> areaBrandList = new ArrayList<>();
        
        /* １．投信銘柄マスタテーブルから取得した各データをNRIコードをキーにレスポンスの銘柄リストにセットする。(SQL002) */
        // sql002リクエストのmodel、inputListからNRIコードを取得してNRIコードリストとしてSQL002リクエストモデルにセットする
        List<String> nriCdList = inputList.stream().map(BrandForInput::getNriCd).collect(Collectors.toList());
        
        Fct022Sql002RequestModel sql002Req = new Fct022Sql002RequestModel();
        sql002Req.setNriCd(nriCdList);
        
        List<Fct022Sql002ResponseModel> fct022Sql002ResponseModelList = fct022Dao.getFct022Sql002(sql002Req);
        
        // SQL002取得した各データを編集用リストにセットする
        for (Fct022Sql002ResponseModel sql002Res : fct022Sql002ResponseModelList) {
            AreaBrand areaBrand = new AreaBrand();
            // SQL002取得したNRIコードを編集用リストにセットする
            areaBrand.setNriCd(sql002Res.getNriCd());
            // SQL002取得した販売手数料率1を編集用リストにセットする
            areaBrand.setSalesCommRate1(sql002Res.getSalesCommRate1());
            // SQL002取得した販売手数料率2を編集用リストにセットする
            areaBrand.setSalesCommRate2(sql002Res.getSalesCommRate2());
            // SQL002取得した乗換優遇率分母を編集用リストにセットする
            areaBrand.setTransfersPreferentialRateDenominator(sql002Res.getTransfersPreferentialRateDenominator());
            // SQL002取得した乗換優遇率分子を編集用リストにセットする
            areaBrand.setTransfersPreferentialRateNumerator(sql002Res.getTransfersPreferentialRateNumerator());
            // SQL002取得した注文締切時間を編集用リストにセットする
            areaBrand.setOrderDeadlineTime(sql002Res.getOrderDeadlineTime());
            // SQL002取得したファンドタイプを編集用リストにセットする
            areaBrand.setFundType(sql002Res.getFundType());
            // SQL002取得した協会コードを編集用リストにセットする
            areaBrand.setKyoukaiCd(sql002Res.getKyoukaiCd());
            areaBrandList.add(areaBrand);
        }
        
        /* ２．投信基準価額テーブルから取得した各データを協会コードをキーにレスポンスの銘柄リストにセットする。(SQL001) */
        // sql001リクエストのModel、SQL002取得した
        List<String> kyoukaiCdList = fct022Sql002ResponseModelList.stream().map(Fct022Sql002ResponseModel::getKyoukaiCd)
                .collect(Collectors.toList());
        Fct022Sql001RequestModel sql001Req = new Fct022Sql001RequestModel();
        sql001Req.setKyoukaiCd(kyoukaiCdList);
        
        List<Fct022Sql001ResponseModel> fct022Sql001ResponseModelList = fct022Dao.getFct022Sql001(sql001Req);
        
        // SQL001取得した各データを協会コードをキーに編集用リストにセットする
        for (Fct022Sql001ResponseModel sql001Res : fct022Sql001ResponseModelList) {
            for (AreaBrand areaBrand : areaBrandList) {
                if (sql001Res.getKyoukaiCd().equals(areaBrand.getKyoukaiCd())) {
                    // SQL001取得した基準価額を編集用リストにセットする
                    areaBrand.setBasePrice(sql001Res.getBasePrice());
                    // SQL001取得した前日比を編集用リストにセットする
                    areaBrand.setDiff(sql001Res.getDiff());
                }
            }
        }
        
        /* ３．扱者コードの指定がある場合、扱者個別投信手数料マスタテーブルからレコードを取得する。(SQL003) */
        // SQL002取得したファンドタイプと協会コードをリクエストの対象リストにセットする
        List<Sql003ReqModel> sql003ReqList = fct022Sql002ResponseModelList.stream()
                .map(fct022Sql002ResponseModel -> new Sql003ReqModel(fct022Sql002ResponseModel.getFundType(),
                        fct022Sql002ResponseModel.getKyoukaiCd()))
                .collect(Collectors.toList());
        
        // 扱者コードありの場合
        if (null != input.getDealerNumber() && !input.getDealerNumber().isEmpty()) {
            
            // SQL003リクエストモデルを作成
            Fct022Sql003RequestModel sql003Req = new Fct022Sql003RequestModel();
            // 扱者コードをセットする
            sql003Req.setDealerNumber(input.getDealerNumber());
            // 対象リストをセットする
            sql003Req.setSql003ReqList(sql003ReqList);
            // DAOを実行する
            List<Fct022Sql003ResponseModel> fct022Sql003ResponseModelList = fct022Dao.getFct022Sql003(sql003Req);
            
            // レコードありの場合は、販売手数料率1と2の値を更新する
            if (!fct022Sql003ResponseModelList.isEmpty()) {
                for (Fct022Sql003ResponseModel sql003Res : fct022Sql003ResponseModelList) {
                    for (AreaBrand areaBrand : areaBrandList) {
                        // NRIコードに紐づくファンドタイプ、協会コードをキーに編集用リストにセットする
                        if (sql003Res.getFundType().equals(areaBrand.getFundType())
                                && sql003Res.getKyoukaiCd().equals(areaBrand.getKyoukaiCd())) {
                            // 編集用リストの値を更新する
                            areaBrand.setSalesCommRate1(sql003Res.getSalesCommRate1());
                            areaBrand.setSalesCommRate2(sql003Res.getSalesCommRate2());
                        }
                    }
                }
            }
        }
        
        // 編集用リストからレスポンスの銘柄リストにセットする
        for (AreaBrand areaBrand : areaBrandList) {
            
            Brand brand = new Brand();
            // 編集用リストから取得したNRIコードをレスポンスの銘柄リストにセットする
            brand.setNriCd(areaBrand.getNriCd());
            // 編集用リストから取得した基準価額をレスポンスの銘柄リストにセットする
            brand.setBasePrice(areaBrand.getBasePrice());
            // 編集用リストから取得した前日比をレスポンスの銘柄リストにセットする
            brand.setDiff(areaBrand.getDiff());
            // 編集用リストから取得した販売手数料率1をレスポンスの銘柄リストにセットする
            brand.setSalesCommRate1(areaBrand.getSalesCommRate1());
            // 編集用リストから取得した販売手数料率2をレスポンスの銘柄リストにセットする
            brand.setSalesCommRate2(areaBrand.getSalesCommRate2());
            // 編集用リストから取得した乗換優遇率分母をレスポンスの銘柄リストにセットする
            brand.setTransfersPreferentialRateDenominator(areaBrand.getTransfersPreferentialRateDenominator());
            // 編集用リストから取得した乗換優遇率分子をレスポンスの銘柄リストにセットする
            brand.setTransfersPreferentialRateNumerator(areaBrand.getTransfersPreferentialRateNumerator());
            // 編集用リストから取得した注文締切時間をレスポンスの銘柄リストにセットする
            brand.setOrderDeadlineTime(areaBrand.getOrderDeadlineTime());
            result.getBrandList().add(brand);
            
        }
        
        return result;
    }
    
    /**
     * 編集用Area
     *
     * @author 陳
     */
    @Data
    private static class AreaBrand {
        
        // NRIコード
        private String nriCd;
        
        // 販売手数料率1
        private BigDecimal salesCommRate1;
        
        // 販売手数料率2
        private BigDecimal salesCommRate2;
        
        // 乗換優遇率分母
        private BigDecimal transfersPreferentialRateDenominator;
        
        // 乗換優遇率分子
        private BigDecimal transfersPreferentialRateNumerator;
        
        // 注文締切時間
        private String orderDeadlineTime;
        
        // ファンドタイプ
        private String fundType;
        
        // 協会コード
        private String kyoukaiCd;
        
        // 基準価額
        private BigDecimal basePrice;
        
        // 前日比
        private BigDecimal diff;
    }
}
