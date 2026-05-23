package com.sbisec.helios.ap.bizcommon.component;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.dao.Fct039Dao;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct039Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct039Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.model.InputFct039Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct039Dto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.pom.protocol.ReferPointReq;
import com.sbisec.helios.ap.pom.protocol.ReferPointRes;
import com.sbisec.helios.ap.pom.service.ReferPointService;
import com.sbisec.helios.ap.pom.utils.PomCode;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 共通関数：FCT039
 * ポイント照会
 *
 * @author SCSK
 */
@Component
public class Fct039 {
    
    @Autowired
    private Fct039Dao dao;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Fct039.class);
    
    // 連携済み
    private static final String POM_ACC_STATUS_LINKED = "2";
    
    // メッセージID
    private enum MessageId {

        // warnings.cmn.referPoint.systemError
        WARNIGNS_REFERPOINT_SYSTEMERROR("warnings.cmn.referPoint.systemError"),
        // warnings.cmn.referPoint.apiError
        WARNIGNS_REFERPOINT_APIERROR("warnings.cmn.referPoint.apiError");

        private String key;

        private MessageId(String key) {

            this.key = key;
        }
 
    }

    @Autowired
    private ReferPointService referPointService;
    
    /**
     * ポイント照会
     *
     * @param input リクエストDto
     * @return レスポンスDto
     * @throws Exception 例外発生時
     */
    @NotNull
    public OutputFct039Dto getData(InputFct039Dto input) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Fct039.getData");
        }
        OutputFct039Dto resDto = new OutputFct039Dto();
        ReferPointRes resPom = null;
        try {
            // ポイント残高の取得(pom)
            resPom = referPointService.getReferPoint(new ReferPointReq(input.getButenCode(),
                    input.getAccountNumber(), input.getLinkKbn(), PomCode.CHANNEL_TYPE.getValue()));
        } catch (Exception e) {
            // システムエラー
            // 画面メッセージの設定
            resDto.setScreenMessage(IfaCommonUtil.getMessage(MessageId.WARNIGNS_REFERPOINT_SYSTEMERROR.key));
            return resDto;
        }

        if (Objects.isNull(resPom.getPointAccount()) || StringUtil.isNullOrEmpty(resPom.getPointAccount().getPointType()) || StringUtil.isNullOrEmpty(resPom.getResultCode())) {
            // レスポンス項目のいずれかがNullの場合
            LOGGER.info("Fct039.getData pointType is null.[{}]", resPom.toString());
            // 画面メッセージの設定
            resDto.setScreenMessage(IfaCommonUtil.getMessage(MessageId.WARNIGNS_REFERPOINT_SYSTEMERROR.key));
            return resDto;
        }

        boolean pomSuccess = false;
        if (StringUtils.equals(resPom.getPointAccount().getPointType(), PomCode.POINT_NONE.getValue().toString())) {
            // ポイント利用なしの場合
            return resDto;
        } else {
            pomSuccess = StringUtils.equals(resPom.getResultCode(), PomCode.RESULT_SUCCESS.getValue().toString());
            if (pomSuccess) {
                // 結果コードが正常の場合
                resDto.setPointType(resPom.getPointAccount().getPointType());
                resDto.setState(resPom.getPointAccount().getState());
                // ポイント数 どちらかがnullでない場合に算出する。
                if (ObjectUtils.anyNotNull(resPom.getAvailablePoint(), resPom.getRestrictPointSum())) {
                    resDto.setPointNumber(Objects.requireNonNullElse(resPom.getAvailablePoint(), 0)
                            + Objects.requireNonNullElse(resPom.getRestrictPointSum(), 0));
                }
                resDto.setRestrictPointSum(resPom.getRestrictPointSum());
                resDto.setExpiredDate(resPom.getExpiredDate());
                resDto.setMinimumUsePoint(resPom.getMinimumUsePoint());
                resDto.setUsePointUnit(resPom.getUsePointUnit());
            } else {
                // 画面メッセージの設定（結果コードあり）
                resDto.setScreenMessage(IfaCommonUtil.getMessage(MessageId.WARNIGNS_REFERPOINT_APIERROR.key, new String[] {resPom.getResultCode().toString()}));
                return resDto;
            }
        }

        // SQL呼び出し
        // ポイント関連項目表示可否取得
        Fct039Sql001ResponseModel sql001Res = dao.getPointDisplayPermission(
                new Fct039Sql001RequestModel(input.getBrokerCode(), resPom.getPointAccount().getPointType()));
        
        if (Objects.isNull(sql001Res)) {
            LOGGER.info("Fct039.getData sql001Res is null.");
            return resDto;
        }
        String value = sql001Res.getName();
        switch (value.length()) {
        case 6:
            applyPermissions(value, resDto);
            break;
        case 12:
            if (pomSuccess) {
                if (StringUtils.equals(resPom.getPointAccount().getState(), POM_ACC_STATUS_LINKED)) {
                    applyPermissions(value, resDto);
                } else {
                    applyPermissions(value.substring(6), resDto);
                }
            }
            break;
        default:
            break;
        }
        LOGGER.debug("Fct039.getData res:{}", resDto);
        return resDto;
    }
    
    /**
     * 表示可否を適用する
     *
     * @param value 6桁以上の設定値
     * @param target 設定対象のdto
     */
    private void applyPermissions(String value, OutputFct039Dto target) {
        
        target.setPointDisplayAreaAvailability(value.substring(0, 1));
        target.setPointNameDisplayAvailability(value.substring(1, 2));
        target.setPointNumberDisplayAvailability(value.substring(2, 3));
        target.setFixedTermPointDisplayAvailability(value.substring(3, 4));
        target.setPointShortLimitDisplayAvailability(value.substring(4, 5));
        target.setPointUseAreaAvailability(value.substring(5, 6));
        
    }
    
}
