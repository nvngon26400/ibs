package com.sbisec.helios.ap.bizcommon.component;


import com.sbisec.helios.ap.bizcommon.dao.Fct009Dao;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct009Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.model.InputFct009Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct009Dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 共通関数：FCT009
 * 共同募集顧客(共募顧客)チェック

 * @author SCSK
 */

@Component
public class Fct009 {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Fct009.class);
    
    @Autowired
    private Fct009Dao dao;

    /**
     * 共通募集_顧客（M_JOINT_USER）テーブルに、指定の条件で口座が存在するか判定する。
     *
     * @param inputFct009Dto インプットパモデル
     * @return OutputFct009Dto アウトプットモデル
     */
    public OutputFct009Dto doCheck(InputFct009Dto inputFct009Dto) {
        LOGGER.debug("Fct009.doCheck");
        //入力チェック
        if (inputFct009Dto == null || inputFct009Dto.getAccountNumber() == null || inputFct009Dto.getButenCode() == null) {
            return new OutputFct009Dto();
        }
        Fct009Sql001RequestModel model = new Fct009Sql001RequestModel();
        model.setButenCode(inputFct009Dto.getButenCode());
        model.setAccountNumber(inputFct009Dto.getAccountNumber());
        //共通募集_顧客（M_JOINT_USER）テーブルより、指定された顧客口座情報の件数を取得する。
        int count = dao.jointSubscriptCustomerCheck(model);
        //レスポンス項目にSQL001.件数を設定する
        OutputFct009Dto output = new OutputFct009Dto();
        output.setAcquireNumber(String.valueOf(count));
        //処理を終了して呼出元にレスポンスを返却する。
        return output;
    }
}
