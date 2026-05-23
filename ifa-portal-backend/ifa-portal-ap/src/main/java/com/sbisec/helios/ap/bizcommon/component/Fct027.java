package com.sbisec.helios.ap.bizcommon.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbisec.helios.ap.bizcommon.model.InputFct027Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct027Dto;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.dao.Fct027Dao;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct027Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct027Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct027Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct027Sql002ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct027Sql003RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct027Sql003ResponseModel;

/**
 * 共通関数：FCT027
 * 国内株式情報取得
 *
 * @author SCSK
 * 
 */

@Component
public class Fct027 {
    
    // Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(Fct027.class);
    
    // 空の値
    private static final String RESPONSE_EMPTY = "";
    
    @Autowired
    private Fct027Dao fct027Dao;
    
    /**
     * FCT027 国内株式情報取得
     *
     * @param input リクエスト
     * @return OutputFct027Dto レスポンス
     */
    public OutputFct027Dto getData(InputFct027Dto input) {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Fct027.getData");
        }
        
        // レスポンスDTOのインスタンス生成
        OutputFct027Dto outputFct027Dto = new OutputFct027Dto();
        // 必須項目判断処理
        // 銘柄コードが設定されていない場合、空のレスポンスを返却する
        if (input.getBrandCode().isEmpty()) {
            return outputFct027Dto;
        }

        outputFct027Dto.setBrandCode(input.getBrandCode());
        
        // ①SQL001呼出
        Fct027Sql001RequestModel fct027Sql001RequestModel = new Fct027Sql001RequestModel();
        // 銘柄コードの設定
        fct027Sql001RequestModel.setBrandCodeFirst(input.getBrandCode().substring(0, 4));
        fct027Sql001RequestModel.setBrandCodeLast(input.getBrandCode().substring(input.getBrandCode().length() - 1));
        List<Fct027Sql001ResponseModel> fct027Sql001ResponseList = fct027Dao.getFct027Sql001(fct027Sql001RequestModel);
        // データが取得できない場合
        if (fct027Sql001ResponseList.isEmpty()) {
            // 空の値をset
            outputFct027Dto.setBrandName(RESPONSE_EMPTY);
        } else {
            if (null != fct027Sql001ResponseList
                    && (null == fct027Sql001ResponseList.get(0)
                       || null == fct027Sql001ResponseList.get(0).getBrandName())) {
                outputFct027Dto.setBrandName(RESPONSE_EMPTY);
            } else {
                outputFct027Dto.setBrandName(fct027Sql001ResponseList.get(0).getBrandName());
            }
        }
        // ①SQL002呼出
        Fct027Sql002RequestModel fct027Sql002RequestModel = new Fct027Sql002RequestModel();
        // 銘柄コードの設定
        fct027Sql002RequestModel.setBrandCodeFirst(input.getBrandCode());
        List<Fct027Sql002ResponseModel> fct027Sql002ResponseList = fct027Dao.getFct027Sql002(fct027Sql002RequestModel);
        // 実行結果をもとにレスポンスを生成する
        if (null != fct027Sql002ResponseList && fct027Sql002ResponseList.size() != 0) {
            // データが取得できている場合、最初の1件を参照してデータを生成する
            // 規制銘柄区分
            if (null == fct027Sql002ResponseList.get(0).getRegKbn()) {
                outputFct027Dto.setRegKbn(RESPONSE_EMPTY);
            } else {
                outputFct027Dto.setRegKbn(fct027Sql002ResponseList.get(0).getRegKbn());
            }
            // 東証一般信用区分
            if (null == fct027Sql002ResponseList.get(0).getMktIppanLoanKbnTky()) {
                outputFct027Dto.setMktIppanLoanKbnTky(RESPONSE_EMPTY);
            } else {
                outputFct027Dto.setMktIppanLoanKbnTky(fct027Sql002ResponseList.get(0).getMktIppanLoanKbnTky());
            }
            // 名証一般信用区分
            if (null == fct027Sql002ResponseList.get(0).getMktIppanLoanKbnNgy()) {
                outputFct027Dto.setMktIppanLoanKbnNgy(RESPONSE_EMPTY);
            } else {
                outputFct027Dto.setMktIppanLoanKbnNgy(fct027Sql002ResponseList.get(0).getMktIppanLoanKbnNgy());
            }
            // 福証一般信用区分
            if (null == fct027Sql002ResponseList.get(0).getMktIppanLoanKbnFko()) {
                outputFct027Dto.setMktIppanLoanKbnFko(RESPONSE_EMPTY);
            } else {
                outputFct027Dto.setMktIppanLoanKbnFko(fct027Sql002ResponseList.get(0).getMktIppanLoanKbnFko());
            }
            // 札証一般信用区分
            if (null == fct027Sql002ResponseList.get(0).getMktIppanLoanKbnSpr()) {
                outputFct027Dto.setMktIppanLoanKbnSpr(RESPONSE_EMPTY);
            } else {
                outputFct027Dto.setMktIppanLoanKbnSpr(fct027Sql002ResponseList.get(0).getMktIppanLoanKbnSpr());
            }
            // SOR取扱区分
            if (null == fct027Sql002ResponseList.get(0).getSorServiceKbn()) {
                outputFct027Dto.setSorServiceKbn(RESPONSE_EMPTY);
            } else {
                outputFct027Dto.setSorServiceKbn(fct027Sql002ResponseList.get(0).getSorServiceKbn());
            }
            // プレミアム空売り区分
            // set methodのcategoryの頭文字cが一つ多い？
            if (null == fct027Sql002ResponseList.get(0).getPremiumShortSellingKbn()) {
                outputFct027Dto.setPremiumShortSaleCcategory(RESPONSE_EMPTY);
            } else {
                outputFct027Dto.setPremiumShortSaleCcategory(fct027Sql002ResponseList.get(0).getPremiumShortSellingKbn());
            }
            // 売買単位
            if (null == fct027Sql002ResponseList.get(0).getBsUnit()) {
                outputFct027Dto.setUnit(RESPONSE_EMPTY);
            } else {
                outputFct027Dto.setUnit(fct027Sql002ResponseList.get(0).getBsUnit());
            }
            // 東証貸借区分
            if (null == fct027Sql002ResponseList.get(0).getMktLoanKbnTky()) {
                outputFct027Dto.setMktLoanKbnTky(RESPONSE_EMPTY);
            } else {
                outputFct027Dto.setMktLoanKbnTky(fct027Sql002ResponseList.get(0).getMktLoanKbnTky());
            }
        } else {
            // データが取得できていない場合、レスポンスに空をセット
            outputFct027Dto.setRegKbn(RESPONSE_EMPTY);
            outputFct027Dto.setMktIppanLoanKbnTky(RESPONSE_EMPTY);
            outputFct027Dto.setMktIppanLoanKbnNgy(RESPONSE_EMPTY);
            outputFct027Dto.setMktIppanLoanKbnFko(RESPONSE_EMPTY);
            outputFct027Dto.setMktIppanLoanKbnSpr(RESPONSE_EMPTY);
            outputFct027Dto.setSorServiceKbn(RESPONSE_EMPTY);
            outputFct027Dto.setPremiumShortSaleCcategory(RESPONSE_EMPTY);
            outputFct027Dto.setUnit(RESPONSE_EMPTY);
            outputFct027Dto.setMktLoanKbnTky(RESPONSE_EMPTY);
        }
        // ①SQL003呼出
        Fct027Sql003RequestModel fct027Sql003RequestModel = new Fct027Sql003RequestModel();
        // 銘柄コードの設定
        fct027Sql003RequestModel.setBrandCodeFirst(input.getBrandCode());
        List<Fct027Sql003ResponseModel> fct027Sql003ResponseList = fct027Dao.getFct027Sql003(fct027Sql003RequestModel);
        // 実行結果をもとにレスポンスを生成する
        if (null != fct027Sql003ResponseList && fct027Sql003ResponseList.size() != 0 && fct027Sql003ResponseList.get(0) != null) {
            // データが取得できている場合、最初の1件を参照してデータを生成する
            // PTS貸借区分
            if (null == fct027Sql003ResponseList.get(0).getMktLoanKbnPts()) {
                outputFct027Dto.setMktLoanKbnPts(RESPONSE_EMPTY);
            } else {
                outputFct027Dto.setMktLoanKbnPts(fct027Sql003ResponseList.get(0).getMktLoanKbnPts());
            }
            // PTS一般信用区分
            if (null == fct027Sql003ResponseList.get(0).getMktIppanLoanKbnPts()) {
                outputFct027Dto.setMktIppanLoanKbnPts(RESPONSE_EMPTY);
            } else {
                outputFct027Dto.setMktIppanLoanKbnPts(fct027Sql003ResponseList.get(0).getMktIppanLoanKbnPts());
            }
        } else {
            // データが取得できていない場合、レスポンスに空をセット
        	outputFct027Dto.setMktLoanKbnPts(RESPONSE_EMPTY);
        	outputFct027Dto.setMktIppanLoanKbnPts(RESPONSE_EMPTY);
        }
        return outputFct027Dto;
    }
}
