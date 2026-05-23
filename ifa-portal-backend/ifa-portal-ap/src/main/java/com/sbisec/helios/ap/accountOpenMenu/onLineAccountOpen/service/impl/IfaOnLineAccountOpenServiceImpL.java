package com.sbisec.helios.ap.accountOpenMenu.onLineAccountOpen.service.impl;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.accountOpenMenu.onLineAccountOpen.dao.IfaOnLineAccountOpenDao;
import com.sbisec.helios.ap.accountOpenMenu.onLineAccountOpen.dao.model.IfaOnLineAccountOpenSql001RequestModel;
import com.sbisec.helios.ap.accountOpenMenu.onLineAccountOpen.dao.model.IfaOnLineAccountOpenSql001ResponseModel;
import com.sbisec.helios.ap.accountOpenMenu.onLineAccountOpen.dto.IfaOnLineAccountOpenA001RequestDto;
import com.sbisec.helios.ap.accountOpenMenu.onLineAccountOpen.dto.IfaOnLineAccountOpenA001ResponseDto;
import com.sbisec.helios.ap.accountOpenMenu.onLineAccountOpen.service.IfaOnLineAccountOpenService;


/**
 * 画面ID：SUB0207_0201
 * 画面名：オンライン口座開設
 *
 * @author SCSK 木村
 2025/02/06 新規作成
 */
@Component(value = "cmpIfaOnLineAccountOpenService")
public class IfaOnLineAccountOpenServiceImpL implements IfaOnLineAccountOpenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaOnLineAccountOpenServiceImpL.class);

    // --------------------------------
    // メッセージ
    // --------------------------------

    /** 所属仲介業者に該当する口座開設URLが設定されていません。. */
    private static final String ERRORS_OPEN_ACCOUNT_LINK_NOT_FOUND = "errors.openAccountLink.notFound";
    
    /** 仲介業者毎の遷移先が複数設定されているため、表示できません。. */
    private static final String ERRORS_OPEN_ACCOUNT_LINK_COUNT_CHECK = "errors.openAccountLink.countCheck";

    @Autowired
    private IfaOnLineAccountOpenDao dao;

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaOnLineAccountOpenA001RequestDto
     * Dto レスポンス：IfaOnLineAccountOpenA001ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaOnLineAccountOpenA001ResponseDto> initializeA001(
            IfaOnLineAccountOpenA001RequestDto dtoReq ) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaOnLineAccountOpenServiceImplL.initializeA001");
        }

        IfaOnLineAccountOpenA001ResponseDto innerData = new IfaOnLineAccountOpenA001ResponseDto();

        // ユーザ共通情報
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // SQL001を実行
        IfaOnLineAccountOpenSql001RequestModel selSql001Req = new IfaOnLineAccountOpenSql001RequestModel();
        selSql001Req.setBrokerCode(userAccount.getBrokerCode());
        selSql001Req.setUserId(userAccount.getUserId());
        selSql001Req.setPrivId(userAccount.getPrivId());
        DataList<IfaOnLineAccountOpenSql001ResponseModel> selSql001Res = dao.selectIfaOnLineAccountOpenSql001(selSql001Req);
        
        // レスポンスの設定
        if (selSql001Res.size() == 0) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL,
                    ERRORS_OPEN_ACCOUNT_LINK_NOT_FOUND, 
                    IfaCommonUtil.getMessage(ERRORS_OPEN_ACCOUNT_LINK_NOT_FOUND));
        } else if (selSql001Res.size() > 1) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL,
                    ERRORS_OPEN_ACCOUNT_LINK_COUNT_CHECK, 
                    IfaCommonUtil.getMessage(ERRORS_OPEN_ACCOUNT_LINK_COUNT_CHECK));
        }

        innerData.setUrl(selSql001Res.get(0).getUrl());
        

        DataList<IfaOnLineAccountOpenA001ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                Arrays.asList(innerData),
                ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.name(),
                StringUtil.EMPTY_STRING
        );

        return dtoRes;

    }
}