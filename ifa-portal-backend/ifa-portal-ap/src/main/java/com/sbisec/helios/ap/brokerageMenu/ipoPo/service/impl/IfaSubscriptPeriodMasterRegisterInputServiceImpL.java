package com.sbisec.helios.ap.brokerageMenu.ipoPo.service.impl;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.IfaSubscriptPeriodMasterRegisterInputDao;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptPeriodMasterRegisterInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptPeriodMasterRegisterInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptPeriodMasterRegisterInputA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptPeriodMasterRegisterInputA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.service.IfaSubscriptPeriodMasterRegisterInputService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

/**
 * 画面ID：SUB0204_01-04_1
 * 画面名：募集期間マスタ登録
 * 2024/03/26 新規作成
 *
 * @author SCSK 濱田
 */
@Component(value = "cmpIfaSubscriptPeriodMasterRegisterInputService")
public class IfaSubscriptPeriodMasterRegisterInputServiceImpL implements IfaSubscriptPeriodMasterRegisterInputService {

    /** 入力タイプエラー */
    private static final String ERRORS_TYPE = "errors.type";
    
    /** 募集入力済みエラー */
    private static final String ERRORS_ORDERD_CHECK = "errors.orderedCheck";

    /** 更新不可 */
    private static final String ERRORS_EXCLUSIVE = "errors.exclusive";

    /** 更新完了 */
    private static final String INFO_UPDATE_COMPLETED = "info.updateCompleted";
    
    /** 登録不可 */
    private static final String ERRORS_PROCESSING_FAILED = "errors.processingFailed";

    /** 登録完了 */
    private static final String INFO_INSERT_COMPLETED = "info.insertCompleted";


    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaSubscriptPeriodMasterRegisterInputServiceImpL.class);

    @Autowired
    private IfaSubscriptPeriodMasterRegisterInputDao dao;

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaSubscriptPeriodMasterRegisterInputA001RequestDto
     * Dto レスポンス：IfaSubscriptPeriodMasterRegisterInputA001ResponseDto
     * model リクエスト：IfaSubscriptPeriodMasterRegisterInputSql004RequestModel
     * model レスポンス：IfaSubscriptPeriodMasterRegisterInputSql004ResponseModel
     *
     * @param dtoReq リクエストボディ
     * @return 画面の初期化に必要な情報
     * @exception Exception システムエラー
     */
    
    @Override
    public DataList<IfaSubscriptPeriodMasterRegisterInputA001ResponseDto> initializeA001(IfaSubscriptPeriodMasterRegisterInputA001RequestDto dtoReq) throws Exception {
        IfaSubscriptPeriodMasterRegisterInputA001ResponseDto innerData
                = new IfaSubscriptPeriodMasterRegisterInputA001ResponseDto();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSubscriptPeriodMasterRegisterInputServiceImplL.initializeA001");
        }
        
        //SQL001：「IPO/PO銘柄一覧」で選択された銘柄情報を表示する。
        IfaSubscriptPeriodMasterRegisterInputSql001RequestModel selSql001Req 
                = new IfaSubscriptPeriodMasterRegisterInputSql001RequestModel();
        BeanUtils.copyProperties(selSql001Req, dtoReq);
        DataList<IfaSubscriptPeriodMasterRegisterInputSql001ResponseModel> selSql001Res = dao.selectIfaSubscriptPeriodMasterRegisterInputSql001(selSql001Req);
        if (selSql001Res.getDataList().size() >= 1) {
            BeanUtils.copyProperties(innerData, selSql001Res.getDataList().get(0));
        }

        //SQL004：対面募集期間マスタが登録されているかどうかを取得する。
        IfaSubscriptPeriodMasterRegisterInputSql004RequestModel selSql004Req 
                = new IfaSubscriptPeriodMasterRegisterInputSql004RequestModel();
        BeanUtils.copyProperties(selSql004Req, dtoReq);
        DataList<IfaSubscriptPeriodMasterRegisterInputSql004ResponseModel> selSql004Res 
                = dao.selectIfaSubscriptPeriodMasterRegisterInputSql004(selSql004Req);
        if (selSql004Res.getDataList().size() >= 1) {
            BeanUtils.copyProperties(innerData, selSql004Res.getDataList().get(0));
        }

        //SQL002：「IPO/PO銘柄一覧」で選択された銘柄の募集期間情報を表示する。
        IfaSubscriptPeriodMasterRegisterInputSql002RequestModel selSql002Req 
                = new IfaSubscriptPeriodMasterRegisterInputSql002RequestModel();
        BeanUtils.copyProperties(selSql002Req, dtoReq);
        DataList<IfaSubscriptPeriodMasterRegisterInputSql002ResponseModel> selSql002Res 
                = dao.selectIfaSubscriptPeriodMasterRegisterInputSql002(selSql002Req);
        if (selSql002Res.getDataList().size() >= 1) {
            BeanUtils.copyProperties(innerData, selSql002Res.getDataList().get(0));
        }
        
        //SQL003：「IPO/PO銘柄一覧」で選択された銘柄が募集入力済みかどうかを取得する。
        IfaSubscriptPeriodMasterRegisterInputSql003RequestModel selSql003Req 
                = new IfaSubscriptPeriodMasterRegisterInputSql003RequestModel();
        BeanUtils.copyProperties(selSql003Req, dtoReq);
        DataList<IfaSubscriptPeriodMasterRegisterInputSql003ResponseModel> selSql003Res = dao.selectIfaSubscriptPeriodMasterRegisterInputSql003(selSql003Req);
        if (selSql003Res.getDataList().size() >= 1) {
            BeanUtils.copyProperties(innerData, selSql003Res.getDataList().get(0));
        }

        DataList<IfaSubscriptPeriodMasterRegisterInputA001ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                Arrays.asList(innerData),
                ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.toString(),
                ""
        );
        return dtoRes;
    }

    /**
     * アクションID：A004
     * アクション名：OK
     * Dto リクエスト：IfaSubscriptPeriodMasterRegisterInputA004RequestDto
     * Dto レスポンス：IfaSubscriptPeriodMasterRegisterInputA004ResponseDto
     * model リクエスト：IfaSubscriptPeriodMasterRegisterInputSql007RequestModel
     * model レスポンス：IfaSubscriptPeriodMasterRegisterInputSql007ResponseModel
     *
     * @param dtoReq リクエストボディ
     * @return 更新の成否
     * @exception Exception システムエラー
     */
    public DataList<IfaSubscriptPeriodMasterRegisterInputA004ResponseDto> okA004(
            IfaSubscriptPeriodMasterRegisterInputA004RequestDto dtoReq
    ) throws Exception {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSubscriptPeriodMasterRegisterInputServiceImplL.okA004");
        }
        
        // ユーザアカウント情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();
        
        IfaSubscriptPeriodMasterRegisterInputSql007RequestModel selSql007Req
                = new IfaSubscriptPeriodMasterRegisterInputSql007RequestModel();

        // SQL007：募集期間（FROM）の営業日チェックを行う
        selSql007Req.setDate(dtoReq.getBbPeriodFrom());
        DataList<IfaSubscriptPeriodMasterRegisterInputSql007ResponseModel> selSql007BbPeriodFrom = dao.selectIfaSubscriptPeriodMasterRegisterInputSql007(selSql007Req);
        
        if (
                selSql007BbPeriodFrom.getDataList().size() > 0
                && !selSql007BbPeriodFrom.getDataList().get(0).getNonBusinessDayCount().equals("0")
        ) {
            DataList<IfaSubscriptPeriodMasterRegisterInputA004ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_TYPE, 
                    IfaCommonUtil.getMessage(ERRORS_TYPE, new String[] {"募集期間（FROM）", "営業日"})
            );

            return dtoRes;
        }

        // SQL007：募集期間（TO）の営業日チェックを行う。
        selSql007Req.setDate(dtoReq.getBbPeriodTo());
        DataList<IfaSubscriptPeriodMasterRegisterInputSql007ResponseModel> selSql007BbPeriodTo = dao.selectIfaSubscriptPeriodMasterRegisterInputSql007(selSql007Req);
        
        if (
                selSql007BbPeriodTo.getDataList().size() > 0
                && !selSql007BbPeriodTo.getDataList().get(0).getNonBusinessDayCount().equals("0")
        ) {
            DataList<IfaSubscriptPeriodMasterRegisterInputA004ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_TYPE, 
                    IfaCommonUtil.getMessage(ERRORS_TYPE, new String[] {"募集期間（TO）", "営業日"})
            );
            
            return dtoRes;
        }

        // SQL007：入金予定日（募集最終日）の営業日チェックを行う。
        selSql007Req.setDate(dtoReq.getDepositScheduleDate());
        DataList<IfaSubscriptPeriodMasterRegisterInputSql007ResponseModel> selSql007DepositScheduleDate = dao.selectIfaSubscriptPeriodMasterRegisterInputSql007(selSql007Req);
        
        if (
                selSql007DepositScheduleDate.getDataList().size() > 0
                && !selSql007DepositScheduleDate.getDataList().get(0).getNonBusinessDayCount().equals("0")
        ) {
            DataList<IfaSubscriptPeriodMasterRegisterInputA004ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_TYPE, 
                    IfaCommonUtil.getMessage(ERRORS_TYPE, new String[] {"入金予定日（募集最終日）", "営業日"})
            );

            return dtoRes;
        }

        // SQL007：上場日（受渡期日）の営業日チェックを行う。
        selSql007Req.setDate(dtoReq.getListedDate());
        DataList<IfaSubscriptPeriodMasterRegisterInputSql007ResponseModel> selSql007ListedDate = dao.selectIfaSubscriptPeriodMasterRegisterInputSql007(selSql007Req);
        
        if (
                selSql007ListedDate.getDataList().size() > 0 
                && !selSql007ListedDate.getDataList().get(0).getNonBusinessDayCount().equals("0")
        ) {
            DataList<IfaSubscriptPeriodMasterRegisterInputA004ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_TYPE, 
                    IfaCommonUtil.getMessage(ERRORS_TYPE, new String[] {"上場日（受渡期日）", "営業日"})
            );

            return dtoRes;
        }


        // SQL004：対面募集期間マスタが登録されているかどうかを取得する。
        IfaSubscriptPeriodMasterRegisterInputSql004RequestModel selSql004Req 
                = new IfaSubscriptPeriodMasterRegisterInputSql004RequestModel();
        selSql004Req.setBrandCode12(dtoReq.getBrandCode());
        selSql004Req.setBbPresentationFrom(dtoReq.getBookBuildingPresentationFrom());
        DataList<IfaSubscriptPeriodMasterRegisterInputSql004ResponseModel> selSql004Res = dao.selectIfaSubscriptPeriodMasterRegisterInputSql004(selSql004Req);
        
        // 募集期間マスタ登録済みの場合
        if (
                selSql004Res.getDataList().size() > 0
                && Integer.parseInt(selSql004Res.getDataList().get(0).getSubscriptPeriodInfoRegisterCount()) > 0
        ) {

            // SQL003：対面募集期間マスタが登録されているかどうかを取得する。
            IfaSubscriptPeriodMasterRegisterInputSql003RequestModel selSql003Req
                    = new IfaSubscriptPeriodMasterRegisterInputSql003RequestModel();
            selSql003Req.setBrandCode12(dtoReq.getBrandCode());
            selSql003Req.setBbPresentationFrom(dtoReq.getBookBuildingPresentationFrom());
            DataList<IfaSubscriptPeriodMasterRegisterInputSql003ResponseModel> selSql003Res = dao.selectIfaSubscriptPeriodMasterRegisterInputSql003(selSql003Req);

            if (
                    //入力済みかつ発効価格が変更された場合                        
                    selSql003Res.getDataList().size() > 0
                    && Integer.parseInt(selSql003Res.getDataList().get(0).getSubscriptInputCount()) > 0
                    && !dtoReq.getIssueBbPrice().equals(dtoReq.getIssueBbPriceHiddenItem()) 
                    && dtoReq.getIssuePriceType().equals("2")
                ) {
               
                DataList<IfaSubscriptPeriodMasterRegisterInputA004ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                        new ArrayList<>(),
                        ErrorLevel.FATAL,
                        ERRORS_ORDERD_CHECK, 
                        IfaCommonUtil.getMessage(ERRORS_ORDERD_CHECK, new String[] {"、発行価格を変更"})
                    );

                return dtoRes;

            } else if (
                    // 入力済みかつ電子交付のみが変更された場合
                    selSql003Res.getDataList().size() > 0
                    && Integer.parseInt(selSql003Res.getDataList().get(0).getSubscriptInputCount()) > 0
                    && !StringUtils.equals(dtoReq.getOnlyElectronicDelivery(), dtoReq.getOnlyElectronicDeliveryHiddenItem())
            ) {
                DataList<IfaSubscriptPeriodMasterRegisterInputA004ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                        new ArrayList<>(),
                        ErrorLevel.FATAL,
                        ERRORS_ORDERD_CHECK, 
                        IfaCommonUtil.getMessage(ERRORS_ORDERD_CHECK, new String[] {"、電子交付のみを変更"})
                );

                return dtoRes;

            } else {
                // 上記以外の場合
                // SQL006：対面募集期間マスタを更新
                IfaSubscriptPeriodMasterRegisterInputSql006RequestModel updSql006Req 
                        = new IfaSubscriptPeriodMasterRegisterInputSql006RequestModel();
                BeanUtils.copyProperties(updSql006Req, dtoReq);
                updSql006Req.setUpdateUser(ua.getUserId());
                int updSql006Res = dao.updateIfaSubscriptPeriodMasterRegisterInputSql006(updSql006Req);
                
                if (updSql006Res == 0) {
                    // 更新不可
                    DataList<IfaSubscriptPeriodMasterRegisterInputA004ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                            new ArrayList<>(),
                            ErrorLevel.FATAL,
                            ERRORS_EXCLUSIVE, 
                            IfaCommonUtil.getMessage(ERRORS_EXCLUSIVE, new String[] {"BB募集期間情報"})
                    );

                    return dtoRes;

                } else {
                    // 更新完了
                    DataList<IfaSubscriptPeriodMasterRegisterInputA004ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                            new ArrayList<>(),
                            ErrorLevel.INFO,
                            INFO_UPDATE_COMPLETED, 
                            IfaCommonUtil.getMessage(INFO_UPDATE_COMPLETED, new String[] {"BB募集期間情報"})
                    );

                    return dtoRes;
                }
            }
            
        // 募集期間マスタ未登録の場合
        } else {
            // SQL005：対面募集期間マスタを登録
            IfaSubscriptPeriodMasterRegisterInputSql005RequestModel insSql005Req 
                            = new IfaSubscriptPeriodMasterRegisterInputSql005RequestModel();
            BeanUtils.copyProperties(insSql005Req, dtoReq);
            insSql005Req.setCreateUser(ua.getUserId());
            insSql005Req.setUpdateUser(ua.getUserId());
            int insSql005Res = dao.insertIfaSubscriptPeriodMasterRegisterInputSql005(insSql005Req);
            
            if (insSql005Res == 0) {
                // 登録不可
                DataList<IfaSubscriptPeriodMasterRegisterInputA004ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                        new ArrayList<>(),
                        ErrorLevel.FATAL,
                        ERRORS_PROCESSING_FAILED,
                        IfaCommonUtil.getMessage(ERRORS_PROCESSING_FAILED, new String[] {"BB募集期間情報の登録"})
                );

                return dtoRes;

            } else {
                // 登録完了
                DataList<IfaSubscriptPeriodMasterRegisterInputA004ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                        new ArrayList<>(),
                        ErrorLevel.INFO,
                        INFO_INSERT_COMPLETED, 
                        IfaCommonUtil.getMessage(INFO_INSERT_COMPLETED, new String[] {"BB募集期間情報"})
                );

                return dtoRes;

            }
        }

    }
}
