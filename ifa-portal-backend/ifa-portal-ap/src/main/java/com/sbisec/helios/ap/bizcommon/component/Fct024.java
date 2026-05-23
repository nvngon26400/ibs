package com.sbisec.helios.ap.bizcommon.component;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.sbisec.helios.ap.bizcommon.dao.Fct024Dao;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct024Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct024Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct024Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct024Sql002ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct024Sql003RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct024Sql003ResponseModel;
import com.sbisec.helios.ap.bizcommon.model.InputFct024Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct024Dto;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 共通関数：FCT024
 * 国内投信基準価格・手数料取得（口座別）
 *
 * @author SCSK
 *
 */
@Component
public class Fct024 {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Fct022.class);
    
    // 扱者個別データ有無あり
    public static final String DEALER_EXIST = "1";
    
    // 扱者個別データ有無なし
    public static final String DEALER_NONE = "0";
    
    @Autowired
    private Fct024Dao fct024Dao;
    
    /**
     *　国内投信銘柄のNRIコードを指定し、情報を取得する。
     *
     * @param input リクエスト
     * @return レスポンス
     */
    public OutputFct024Dto getData(InputFct024Dto input) {
        
        // ログ出力
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Fct024.getData");
        }
        
        // レスポンスDTO
        OutputFct024Dto result = new OutputFct024Dto();
        
        // 必須項目について、入力値の有無チェック、ないの場合は空DTOをリターンする
        if (input == null || input.getNriCd() == null) {
            return result;
        }
        
        // リクエストNRIコードをセットする
        result.setNriCd(input.getNriCd());
        
        // beanUtils Beanコピー用
        BeanUtilsBean beanUtils = new BeanUtilsBean();
        beanUtils.getConvertUtils().register(new BigDecimalConverter(null), BigDecimal.class);
        
        /* １，投信銘柄マスタテーブルからデータを取得しレスポンスにセットする。 「扱者個別データ有無」を「0:なし」に更新する。 */
        Fct024Sql002RequestModel sql002Req = new Fct024Sql002RequestModel();
        sql002Req.setNriCd(input.getNriCd());
        
        Fct024Sql002ResponseModel sql002Res = fct024Dao.getFct024Sql002(sql002Req);
        
        if (null != sql002Res) {
            try {
                beanUtils.copyProperties(result, sql002Res);
            } catch (IllegalAccessException | InvocationTargetException e) {
                LOGGER.error("Fct024.getData Exception[{}]", e.getMessage());
                LOGGER.error("Exception occured.");
                LOGGER.info("Exception occured.", e);
            }
        }
        
        result.setDealer(DEALER_NONE);
        /* ２，投信基準価額テーブルからデータを取得しレスポンスにセットする。 */
        Fct024Sql001RequestModel sql001Req = new Fct024Sql001RequestModel();
        if (null != sql002Res) {
            sql001Req.setKyoukaiCd(sql002Res.getKyoukaiCd());
        }
        
        Fct024Sql001ResponseModel sql001Res = fct024Dao.getFct024Sql001(sql001Req);
        if (null != sql001Res) {
            try {
                beanUtils.copyProperties(result, sql001Res);
            } catch (IllegalAccessException | InvocationTargetException e) {
                LOGGER.error("Fct024.getData Exception[{}]", e.getMessage());
                LOGGER.error("Exception occured.");
                LOGGER.info("Exception occured.", e);
            }
        }
        
        /* ３，レスポンスの、「扱者個別データ有無」を「0:なし」に更新する。 扱者コードの指定がある場合、データを扱者個別投信手数料マスタテーブルから取得する。 */
        result.setDealer(DEALER_NONE);
        if (null != input.getDealerNumber() && !input.getDealerNumber().isEmpty()) {
            
            Fct024Sql003RequestModel sql003Req = new Fct024Sql003RequestModel();
            sql003Req.setDealerNumber(input.getDealerNumber());
            if (null != sql002Res) {
                sql003Req.setFundType(sql002Res.getFundType());
                sql003Req.setKyoukaiCd(sql002Res.getKyoukaiCd());
            }
            
            Fct024Sql003ResponseModel sql003Res = fct024Dao.getFct024Sql003(sql003Req);
            
            /* レコードが存在する場合は、レスポンスにセットし、「扱者個別データ有無」を「1:あり」に更新する。 */
            if (null != sql003Res) {
                try {
                    beanUtils.copyProperties(result, sql003Res);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    LOGGER.error("Fct024.getData Exception[{}]", e.getMessage());
                    LOGGER.error("Exception occured.");
                    LOGGER.info("Exception occured.", e);
                }
                result.setDealer(DEALER_EXIST);
            }
        }
        //前日比率(前日比÷前日基準価額)
        if (null != sql001Res && null != sql001Res.getDiff() && null != sql001Res.getZenjitsuKijunkakaku()
                && sql001Res.getZenjitsuKijunkakaku().compareTo(BigDecimal.ZERO) != 0) {
            result.setRatio(sql001Res.getDiff().divide(sql001Res.getZenjitsuKijunkakaku(), 2, RoundingMode.HALF_UP));
        } else {
            result.setRatio(null);
        }
        
        return result;
    }
}
