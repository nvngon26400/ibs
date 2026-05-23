package com.sbisec.helios.ap.bizcommon.component;

import com.sbisec.helios.ap.bizcommon.dao.Fct029Dao;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct029Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct029Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.model.InputFct029Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct029Dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 共通関数：FCT029
 * 英文開示銘柄チェック
 *
 * @author SCSK
 *
 */
@Component
public class Fct029 {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Fct029.class);
    
    @Autowired
    private Fct029Dao fct029Dao;
    
    //英文開示銘柄に該当しない ： "0"
    private static final String ENGLISH_BRAND_JUDGE_FLAG_0 = "0";
    
    //英文開示銘柄に該当する ： "1"
    private static final String ENGLISH_BRAND_JUDGE_FLAG_1 = "1";
    
    /**
     * 共通関数：FCT029
     *
     * @param input リクエスト
     * @return outputFct029Dto
     */
    public OutputFct029Dto doCheck(InputFct029Dto input) {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Fct029.doCheck");
        }
        
        OutputFct029Dto outputFct029Dto = new OutputFct029Dto();
        //①リクエストした国コード、銘柄コードを取得する。
        Fct029Sql001RequestModel fct029Sql001RequestModel = new Fct029Sql001RequestModel();
        fct029Sql001RequestModel.setBrandCode(input.getBrandCode());
        fct029Sql001RequestModel.setCountryCode(input.getNationalityCode());
        //➁英文開示銘柄管理テーブルから、リクエストを抽出条件として一致する銘柄コードを抽出する。
        //銘柄コード、国コードに一致する銘柄コードの件数を取得する
        Fct029Sql001ResponseModel fct029Sql001ResponseModel = fct029Dao.getFct029Sql001(fct029Sql001RequestModel);
        
        //③取得した銘柄コードの件数より、英文開示銘柄判定に変換する。
        if (null != fct029Sql001ResponseModel) {
            if (fct029Sql001ResponseModel.getCount() >= 1) {
                outputFct029Dto.setIssuesDisclosedInEnglishBrandJudge(ENGLISH_BRAND_JUDGE_FLAG_1);
            } else {
                outputFct029Dto.setIssuesDisclosedInEnglishBrandJudge(ENGLISH_BRAND_JUDGE_FLAG_0);
            }
        }
        //④処理を終了して呼出元にレスポンスを返却する。
        return outputFct029Dto;
    }
}
