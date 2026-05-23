package com.sbisec.helios.ap.suggestionBox.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.suggestionBox.dao.IfaSuggestionBoxCommonRegisterDao;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonRegisterSql003RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonRegisterSql004RequestModel;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonRegisterA007bRequestDto;
import com.sbisec.helios.ap.suggestionBox.util.IfaSuggestionBoxException;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.wholePortal.service.impl.FileDownloadUtil;
import org.apache.commons.beanutils.BeanUtils;

/**
 * 画面ID：SUB0511_02-02
 * 画面名：皆様からの要望新規登録
 * トランザクション分割のためExファイルを定義
 *
 2025/06/23 新規作成
 */

@Component(value = "cmpIfaSuggestionBoxCommonRegisterServiceEx")
public class IfaSuggestionBoxCommonRegisterServiceImpLEx {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaSuggestionBoxCommonRegisterServiceImpL.class);

    // --------------------------------
    // 固定値
    // --------------------------------
    /** 確定フラグ：確定 */
    private static final String CONFIRM = "1";


    // --------------------------------
    // メッセージ
    // --------------------------------
    /** {0}を登録しました。 */
    private static final String INFO_INSERT_COMPLETED = "info.insertCompleted";

    /** {0}が失敗しました。 */
    private static final String ERRORS_PROCESSING_FAILED = "errors.processingFailed";

    /** エラーメッセージ引数 */
    private static final String REGISTERED_ERROR = "要望の新規登録";

    /** メッセージ用パラメータ 登録内容 */
    private static final String MESSAGE_PARAM_REGISTER = "皆様からの要望";


    @Autowired
    private IfaSuggestionBoxCommonRegisterDao dao;

    @Autowired
    FileDownloadUtil fileDownloadUtil;

    /**
     * A007b 新規登録 実処理
     *
     * @param req リクエストDTO
     * @return res レスポンス
     * @exception IfaSuggestionBoxException, Exception
     * @see <reference item>
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public DataList<String> registA007bEx(
            IfaSuggestionBoxCommonRegisterA007bRequestDto dtoReq,
            String fileDirectory
        ) throws IfaSuggestionBoxException, Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSuggestionBoxCommonUpdateServiceImpL.RegisterA007bEx");
        }

        DataList<String> res = new DataList<String>();
        
        int count = 0; // DB操作件数

		// --------------------------------
        // SQL003.要望新規登録
        // --------------------------------
        IfaSuggestionBoxCommonRegisterSql003RequestModel insertSql003Req = new IfaSuggestionBoxCommonRegisterSql003RequestModel();
        BeanUtils.copyProperties(insertSql003Req, dtoReq);
        
        String userId = IfaCommonUtil.getUserAccount().getUserId(); 
        insertSql003Req.setUserId(userId);
        
        try {
            count = dao.insertIfaSuggestionBoxCommonRegisterSql003(insertSql003Req);
        } catch (Exception e) {
            throw new IfaSuggestionBoxException(ERRORS_PROCESSING_FAILED, REGISTERED_ERROR);
        }

        // 更新件数が1件でない場合
        if (count != 1) {
            throw new IfaSuggestionBoxException(ERRORS_PROCESSING_FAILED, REGISTERED_ERROR);
        }

		// --------------------------------
        // SQL004.回答登録
        // --------------------------------
        IfaSuggestionBoxCommonRegisterSql004RequestModel insSql004Req = new IfaSuggestionBoxCommonRegisterSql004RequestModel();
        
        for (IfaSuggestionBoxCommonRegisterA007bRequestDto.answerList answer : dtoReq.getAnswerList()) {
            // 回答確定フラグ=1(確定) の回答を登録する。
        	
            if (CONFIRM.equals(answer.getAnswerConfirmFlg())){
            	
                BeanUtils.copyProperties(insSql004Req, answer); 
                
                insSql004Req.setSbcNo(dtoReq.getSbcNo());
                insSql004Req.setUserId(userId);

                try {
                    count = dao.insertIfaSuggestionBoxCommonRegisterSql004(insSql004Req);
                } catch (Exception e) {
                    throw new IfaSuggestionBoxException(ERRORS_PROCESSING_FAILED, REGISTERED_ERROR);
                }

                // 登録件数が1件でない場合
                if (count != 1) {
                    throw new IfaSuggestionBoxException(ERRORS_PROCESSING_FAILED, REGISTERED_ERROR);
                }
            }
        }

        // 登録完了
        String message = IfaCommonUtil.getMessage(INFO_INSERT_COMPLETED, new String[] { MESSAGE_PARAM_REGISTER });
        res = IfaCommonUtil.createDataList(null, ErrorLevel.INFO, INFO_INSERT_COMPLETED, message);

        return res;
    }
}
