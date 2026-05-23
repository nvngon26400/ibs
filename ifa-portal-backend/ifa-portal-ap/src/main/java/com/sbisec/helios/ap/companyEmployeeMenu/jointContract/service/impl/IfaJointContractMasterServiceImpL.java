package com.sbisec.helios.ap.companyEmployeeMenu.jointContract.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.IfaJointContractMasterDao;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model.IfaJointContractMasterSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model.IfaJointContractMasterSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model.IfaJointContractMasterSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model.IfaJointContractMasterSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model.IfaJointContractMasterSql004RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dto.IfaJointContractMasterA002RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dto.IfaJointContractMasterA002ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dto.IfaJointContractMasterA006RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dto.IfaJointContractMasterA006ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dto.IfaJointContractMasterA008RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dto.IfaJointContractMasterA008ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.service.IfaJointContractMasterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 画面ID：SUB0513_01-01
 * 画面名：共同募集契約マスタ
 * 2025/11/25 新規作成
 *
 * @author 大連　苗萌
 * 
 */
@Component(value = "cmpIfaJointContractMasterService")
public class IfaJointContractMasterServiceImpL implements IfaJointContractMasterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaJointContractMasterServiceImpL.class);
    
    @Autowired
    private IfaJointContractMasterDao dao;
    
    /** 検索結果が0件です。\n条件を設定して再度検索して下さい。 */
    private static final String ERRORS_DATALIST_NOT_FOUND = "errors.dataList.notfound";
    
    /** "検索結果が{0}件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。" */
    private static final String WARNINGS_DATA_LIST_OVER_MAX_ROWNUM = "warnings.dataList.overMaxRownum";

    /** 変更／削除の取得が失敗しました。 */
    private static final String ERRORS_PROCESSINGFAILED = "errors.processingFailed";

    /** 変更／削除の取得が成功しました。 */
    private static final String INFO_ENDCOMPLETED = "info.endCompleted";

    /** 削除 */
    private static final String DELETE = "削除";

    /** 変更 */
    private static final String UPDATE = "変更";

    /** 契約状態 : 0:未契約*/
    private static final String CONTRACT_STATUS_0 = "0";

    /** 契約状態 :1:契約 */
    private static final String CONTRACT_STATUS_1 = "1";
    
    
    /** 最小取得件数 */
    private static final int MIN_COUNT = 1;
    
    /** 最大取得件数（表示用） */
    private static final int MAX_DISPLAY_ROWS = 5000;
    
    /**
     * アクションID：A002、A003
     * アクション名：全仲介業者名表示、表示
     *
     * @param dtoReq リクエスト
     * @return dtoRes レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaJointContractMasterA002ResponseDto> displayA002A003(
            IfaJointContractMasterA002RequestDto dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaJointContractMasterServiceImpL.displayA002A003");
        }
        
        /** ②共同募集契約マスタ情報を検索する。 */
        DataList<IfaJointContractMasterA002ResponseDto> dtoRes = new DataList<IfaJointContractMasterA002ResponseDto>();
        List<IfaJointContractMasterA002ResponseDto> resList = new ArrayList<IfaJointContractMasterA002ResponseDto>();
        
        // SQL001のリクエスト値を設定
        IfaJointContractMasterSql001RequestModel selSql001Req = new IfaJointContractMasterSql001RequestModel();
        // 仲介業者コード
        selSql001Req.setBrokerCode(dtoReq.getBrokerCode());
        // 仲介業者名
        selSql001Req.setBrokerName(dtoReq.getBrokerName());
        // 共募契約
        selSql001Req.setJointContract(dtoReq.getJointContract());
        // 最大取得件数
        selSql001Req.setMaxRowNum(MAX_DISPLAY_ROWS);
        
        // 共同募集契約マスタを取得する
        DataList<IfaJointContractMasterSql001ResponseModel> selSql001ResList = new DataList<IfaJointContractMasterSql001ResponseModel>();
        selSql001ResList = dao.selectIfaJointContractMasterSql001(selSql001Req);
        
        for (IfaJointContractMasterSql001ResponseModel val : selSql001ResList.getDataList()) {
            IfaJointContractMasterA002ResponseDto selSql001Item = new IfaJointContractMasterA002ResponseDto();
             // 仲介業者コード
            selSql001Item.setBrokerCode(val.getBrokerCode());
            // 仲介業者名
            selSql001Item.setBrokerName(val.getBrokerName());
            // 共募契約
            selSql001Item.setContractStatus(val.getContractStatus());
            
            resList.add(selSql001Item);
        }
        
        // 共同募集契約マスタの件数が0件の場合、0件メッセージをセットして終了
        if (ObjectUtils.isEmpty(selSql001ResList) || selSql001ResList.size() < MIN_COUNT) {
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.INFO, ERRORS_DATALIST_NOT_FOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATALIST_NOT_FOUND));
            return dtoRes;
        }
        // 共同募集契約マスタの総件数が最大取得件数を超過していた場合、件数超過メッセージをセットして終了
        else if (selSql001ResList.get(0).getTotalCount() > MAX_DISPLAY_ROWS) {
            String msg = IfaCommonUtil.getMessage(WARNINGS_DATA_LIST_OVER_MAX_ROWNUM,
                    new String[] { String.valueOf(MAX_DISPLAY_ROWS),
                            String.valueOf(selSql001ResList.get(0).getTotalCount()) });
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.WARNING, WARNINGS_DATA_LIST_OVER_MAX_ROWNUM, msg);
        } else {
            // 正常終了
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        }
        
        return dtoRes;
    }

    /**
     * アクションID：A006
     * アクション名：変更
     *
     * @param dtoReq リクエスト
     * @return dtoRes レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaJointContractMasterA006ResponseDto> updateA006(
            IfaJointContractMasterA006RequestDto dtoReq) throws Exception {
    
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaJointContractMasterServiceImpL.updateA006");
        }
        
        /** ①一覧の選択行目のデータにより、共同募集契約先仲介業者を取得する。 */
        DataList<IfaJointContractMasterA006ResponseDto> dtoRes = new DataList<IfaJointContractMasterA006ResponseDto>();
        
        // SQL002のリクエスト値を設定
        IfaJointContractMasterSql002RequestModel selSql002Req = new IfaJointContractMasterSql002RequestModel();
        // 仲介業者コード
        selSql002Req.setBrokerCode(dtoReq.getBrokerCodeParam());
        
        // 共同募集契約先仲介業者の契約状態を取得する
        String contractStatus = dao.selectIfaJointContractMasterSql002(selSql002Req);
        
        // 共同募集契約先仲介業者取得の契約状態がnullの場合、0件メッセージをセットして終了
        if (StringUtil.isNullOrEmpty(contractStatus)) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, ERRORS_PROCESSINGFAILED,
                    IfaCommonUtil.getMessage(ERRORS_PROCESSINGFAILED,
                            new String[] { UPDATE }));
            
            return dtoRes;
        }
        
        /** ②共同募集契約先仲介業者を更新する。 */
        // SQL003のリクエスト値を設定
        IfaJointContractMasterSql003RequestModel selSql003Req = new IfaJointContractMasterSql003RequestModel();
        // 仲介業者コード
        selSql003Req.setBrokerCode(dtoReq.getBrokerCodeParam());
        // 0:未契約の場合、1:契約を設定
        if (CONTRACT_STATUS_0.equals(contractStatus)) {
            // 契約状態
            selSql003Req.setContractStatus(CONTRACT_STATUS_1);
            
        } else if (CONTRACT_STATUS_1.equals(contractStatus)) {
            // 1:契約の場合、0:未契約を設定する
            // 契約状態
            selSql003Req.setContractStatus(CONTRACT_STATUS_0);
        }
        
        // 共同募集契約先仲介業者を契約状態を変更する。
        int count = dao.updateIfaJointContractMasterSql003(selSql003Req);
        
        // 更新結果が1件の場合、DBコミットし、削除成功のメッセージを表示する。
        if (count == MIN_COUNT) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.SUCCESS, INFO_ENDCOMPLETED,
                    IfaCommonUtil.getMessage(INFO_ENDCOMPLETED, new String[] { UPDATE }));
        } else {
            // 更新結果が1件以外の場合、エラーメッセージを表示し、処理終了。
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, ERRORS_PROCESSINGFAILED,
                    IfaCommonUtil.getMessage(ERRORS_PROCESSINGFAILED,
                            new String[] { UPDATE }));
            
            return dtoRes;
        }
        
        return dtoRes;
    }

    /**
     * アクションID：A008
     * アクション名：削除
     *
     * @param dtoReq リクエスト
     * @return dtoRes レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaJointContractMasterA008ResponseDto> deleteA008(
            IfaJointContractMasterA008RequestDto dtoReq) throws Exception {
    
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaJointContractMasterServiceImpL.deleteA008");
        }
        
        /** ①一覧の選択行目のデータにより、共同募集契約先仲介業者を取得する。 */
        DataList<IfaJointContractMasterA008ResponseDto> dtoRes = new DataList<IfaJointContractMasterA008ResponseDto>();
        
        // SQL002のリクエスト値を設定
        IfaJointContractMasterSql002RequestModel selSql002Req = new IfaJointContractMasterSql002RequestModel();
        // 仲介業者コード
        selSql002Req.setBrokerCode(dtoReq.getBrokerCodeParam());
        
        // 共同募集契約先仲介業者の契約状態を取得する
        String contractStatus = dao.selectIfaJointContractMasterSql002(selSql002Req);
        
        // 共同募集契約先仲介業者取得の契約状態がnullの場合、0件メッセージをセットして終了
        if (StringUtil.isNullOrEmpty(contractStatus)) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, ERRORS_PROCESSINGFAILED,
                    IfaCommonUtil.getMessage(ERRORS_PROCESSINGFAILED,
                            new String[] { DELETE }));
            
            return dtoRes;
        }
        
        /** ②共同募集契約先仲介業者を更新する。 */
        // SQL004のリクエスト値を設定
        IfaJointContractMasterSql004RequestModel selSql004Req = new IfaJointContractMasterSql004RequestModel();
        // 仲介業者コード
        selSql004Req.setBrokerCode(dtoReq.getBrokerCodeParam());
        // 共同募集契約先仲介業者を削除する。
        int count = dao.updateIfaJointContractMasterSql004(selSql004Req);
        
        // 更新結果が1件の場合、DBコミットし、削除成功のメッセージを表示する。
        if (count == MIN_COUNT) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.SUCCESS, INFO_ENDCOMPLETED,
                    IfaCommonUtil.getMessage(INFO_ENDCOMPLETED, new String[] { DELETE }));
        } else {
            // 更新結果が1件以外の場合、エラーメッセージを表示し、処理終了。
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, ERRORS_PROCESSINGFAILED,
                    IfaCommonUtil.getMessage(ERRORS_PROCESSINGFAILED,
                            new String[] { DELETE }));
            
            return dtoRes;
        }
        
        return dtoRes;
    }
}
