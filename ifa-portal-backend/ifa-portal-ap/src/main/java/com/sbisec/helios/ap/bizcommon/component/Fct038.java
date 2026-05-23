package com.sbisec.helios.ap.bizcommon.component;

import com.sbisec.helios.ap.bizcommon.dao.Fct038Dao;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct038Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct038Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 共通関数：FCT038
 * CSVダウンロードMAX件数取得
 *
 * @author SCSK
 */
@Component
public class Fct038 {
    
    @Autowired
    private Fct038Dao dao;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Fct038.class);
    
    /** ユーザ権限 = "1" */
    private static final String USERAUTHORITY_1 = "1";
    
    /** ユーザ権限 = "2" */
    private static final String USERAUTHORITY_2 = "2";
    
    /** ユーザ権限 = "3" */
    private static final String USERAUTHORITY_3 = "3";
    
    /**
     * CSVダウンロードMAX件数取得
     *
     * @param input リクエストDto
     * @return レスポンスDto
     * @throws Exception CSVダウンロードMAX件数取得に例外が発生した場合
     */
    public OutputFct038Dto getData(InputFct038Dto input) {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Fct038.getData");
        }
        // 必須チェック
        if (input.getScreenId().isEmpty() || input.getUserAuthority() == null) {
            return new OutputFct038Dto();
        }
        
        // ①リクエスト.ユーザ権限に応じてSQLの「権限キー」をセットする。
        Fct038Sql001RequestModel sql001Req = new Fct038Sql001RequestModel();
        sql001Req.setScreenId(input.getScreenId());
        // ユーザ権限=1：'HEAD_OFFICE'
        // ユーザ権限=2：'BRANCH_OFFICE'
        // ユーザ権限=3：'DEFAULT'
        // 上記以外 　　：'OTHERS'
        String userAuth;
        if (StringUtils.equals(input.getUserAuthority(), USERAUTHORITY_1)) {
            userAuth = "HEAD_OFFICE";
        } else if (StringUtils.equals(input.getUserAuthority(), USERAUTHORITY_2)) {
            userAuth = "BRANCH_OFFICE";
        } else if (StringUtils.equals(input.getUserAuthority(), USERAUTHORITY_3)) {
            userAuth = "DEFAULT";
        } else {
            userAuth = "OTHERS";
        }
        sql001Req.setUserPriv(userAuth);
        Fct038Sql001ResponseModel sql001Res = new Fct038Sql001ResponseModel();
        // ②コードマスタから、CSVダウンロードMAX件数を取得する。
        try {
            sql001Res = dao.getCsvMax(sql001Req);
        } catch (Exception e) {
            LOGGER.error("Fct038.getData Fct038DaoImpl.getCsvMax Exception[{}]", e.getMessage());
            return new OutputFct038Dto();
        }
        
        OutputFct038Dto resDto = new OutputFct038Dto();
        try {
            // 取得レコードが0件の場合は、レスポンスに0をセットする。
            if (sql001Res == null) {
                resDto.setCsvDownloadNum(0);
            } else {
                // 取得した件数を整数に変換してレスポンスにセットする。
                Integer name = Integer.parseInt(sql001Res.getName());
                resDto.setCsvDownloadNum(name);
            }
        } catch (Exception e) {
            // 整数に変換出来ない場合は、レスポンスに0をセットする。
            resDto.setCsvDownloadNum(0);
        }
        
        return resDto;
    }
}
