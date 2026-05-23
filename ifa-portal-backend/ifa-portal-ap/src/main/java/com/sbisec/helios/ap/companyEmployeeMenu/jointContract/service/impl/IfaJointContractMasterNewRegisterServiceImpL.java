package com.sbisec.helios.ap.companyEmployeeMenu.jointContract.service.impl;

import java.util.Collections;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.IfaJointContractMasterNewRegisterDao;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model.IfaJointContractMasterNewRegisterSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model.IfaJointContractMasterNewRegisterSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model.IfaJointContractMasterNewRegisterSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model.IfaJointContractMasterNewRegisterSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dto.IfaJointContractMasterNewRegisterA004RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dto.IfaJointContractMasterNewRegisterA004ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dto.IfaJointContractMasterNewRegisterA005RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dto.IfaJointContractMasterNewRegisterA005ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.service.IfaJointContractMasterNewRegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 画面ID：SUB0513_01-02
 * 画面名：共同募集契約マスタ登録
 * 2025/11/25 新規作成
 *
 * @author 大連　苗萌
 * 
 */
@Component(value = "cmpIfaJointContractMasterNewRegisterService")
public class IfaJointContractMasterNewRegisterServiceImpL implements IfaJointContractMasterNewRegisterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaJointContractMasterNewRegisterServiceImpL.class);
    
    @Autowired
    private IfaJointContractMasterNewRegisterDao dao;
    
    /** 指定した仲介業者コードは既に登録済みです。<br>仲介業者コード:[{0}] */
    private static final String ERRORS_JOIN_BROKER_EXISTS = "errors.joint.broker.exists";
    
    /** 入力した仲介業者コードは、報酬率マスタの適用期間内の仲介業者コードではないため、登録できません。<br>仲介業者コード: [{0}] */
    private static final String ERRORS_JOIN_BROKER_NOTEXISTS_REWARDGROUP = "errors.joint.broker.notExistsRewardGroup";
    
    /** 共同募集契約マスタ登録が失敗しました。 */
    private static final String ERRORS_PROCESSINGFAILED = "errors.processingFailed";

    /** {0}を登録しました。 */
    private static final String INFO_INSTERCOMPLETED = "info.insertCompleted";

    /** 共同募集契約マスタ */
    private static final String JOINT_CONTRACT_SETTING = "共同募集契約マスタ";

    /** 共同募集契約マスタ登録 */
    private static final String JOINT_CONTRACT_SETTING_INSERT = "共同募集契約マスタ登録";
    
    /** 最小取得件数 */
    private static final int MIN_COUNT = 1;

    
    /**
     * アクションID：A004
     * アクション名：登録確認
     *
     * @param dtoReq リクエスト
     * @return dtoRes レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaJointContractMasterNewRegisterA004ResponseDto> insertConfirmA004(
            IfaJointContractMasterNewRegisterA004RequestDto dtoReq) throws Exception {
    
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaJointContractMasterNewRegisterServiceImpL.insertConfirmA004");
        }
        
        /** ①仲介業者コードの存在チェックを行う。 */
        DataList<IfaJointContractMasterNewRegisterA004ResponseDto> dtoRes = new DataList<IfaJointContractMasterNewRegisterA004ResponseDto>();
        
        // SQL001のリクエスト値を設定
        IfaJointContractMasterNewRegisterSql001RequestModel selSql001Req = new IfaJointContractMasterNewRegisterSql001RequestModel();
        // 仲介業者コード
        selSql001Req.setBrokerCode(dtoReq.getBrokerCode());
        
        DataList<IfaJointContractMasterNewRegisterSql001ResponseModel> selSql001ResList = 
                new DataList<IfaJointContractMasterNewRegisterSql001ResponseModel>();
        
        // 共同募集契約先仲介業者の一覧を取得する
        selSql001ResList = dao.selectIfaJointContractMasterNewRegisterSql001(selSql001Req);
        
        // 共同募集契約マスタ登録の総件数が1件以上の場合、メッセージをセットして終了
        if (!ObjectUtils.isEmpty(selSql001ResList) && selSql001ResList.size() >= MIN_COUNT) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, ERRORS_JOIN_BROKER_EXISTS,
                    IfaCommonUtil.getMessage(ERRORS_JOIN_BROKER_EXISTS,
                            new String[] { dtoReq.getBrokerCode() }));
            return dtoRes;
        }

        /** ②集計グループマスタの仲介業者コードの存在チェックを行う。 */
        // SQL003のリクエスト値を設定
        IfaJointContractMasterNewRegisterSql003RequestModel selSql003Req = new IfaJointContractMasterNewRegisterSql003RequestModel();
        // 仲介業者コード
        selSql003Req.setBrokerCode(dtoReq.getBrokerCode());
        
        // 集計グループマスタの仲介業者コードの件数を取得する
        int countSql003 = dao.selectIfaJointContractMasterNewRegisterSql003(selSql003Req);
        
        // 総件数が0件の場合、メッセージをセットして終了
        if (countSql003 < MIN_COUNT) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, ERRORS_JOIN_BROKER_NOTEXISTS_REWARDGROUP,
                    IfaCommonUtil.getMessage(ERRORS_JOIN_BROKER_NOTEXISTS_REWARDGROUP,
                            new String[] { dtoReq.getBrokerCode() }));
            return dtoRes;
        }
        
        return dtoRes;
    }
    
    
    /**
     * アクションID：A005
     * アクション名：登録
     *
     * @param dtoReq リクエスト
     * @return dtoRes レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaJointContractMasterNewRegisterA005ResponseDto> insertA005(
            IfaJointContractMasterNewRegisterA005RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaJointContractMasterNewRegisterServiceImpL.insertA005");
        }
        
        /** ①入力した情報を登録する。 */
        DataList<IfaJointContractMasterNewRegisterA005ResponseDto> dtoRes = new DataList<IfaJointContractMasterNewRegisterA005ResponseDto>();
        
        // SQL002のリクエスト値を設定
        IfaJointContractMasterNewRegisterSql002RequestModel selSql002Req = new IfaJointContractMasterNewRegisterSql002RequestModel();
        // 仲介業者コード
        selSql002Req.setBrokerCode(dtoReq.getBrokerCode());
        // 共募契約
        selSql002Req.setJointContract(dtoReq.getJointContract());
        
        try {
            // 共同募集契約先仲介業者登録／更新
            dao.updateIfaJointContractMasterNewRegisterSql002(selSql002Req);
            
            // 正常終了：完了メッセージを設定する。
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.SUCCESS, INFO_INSTERCOMPLETED,
                    IfaCommonUtil.getMessage(INFO_INSTERCOMPLETED, new String[] { JOINT_CONTRACT_SETTING }));
            
        } catch (Exception e) {
            // 異常終了メッセージをセットして終了
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, ERRORS_PROCESSINGFAILED,
                    IfaCommonUtil.getMessage(ERRORS_PROCESSINGFAILED,
                            new String[] { JOINT_CONTRACT_SETTING_INSERT }));
            return dtoRes;
        }
        return dtoRes;
    }
}
