package com.sbisec.helios.ap.suggestionBox.service.impl;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.suggestionBox.service.IfaSuggestionBoxService;
import com.sbisec.helios.ap.wholePortal.psite.model.GetTempFileDirForDocumentCategoryIdModel;
import com.sbisec.helios.ap.wholePortal.psite.service.PSiteService;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 目安箱共通処理
 */

@Component(value = "ifaSuggestionBoxService")
public class IfaSuggestionBoxServiceImpL implements IfaSuggestionBoxService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaSuggestionBoxServiceImpL.class);

    // --------------------------------
    // メッセージ
    // --------------------------------
    /** 添付ファイルの格納先が取得できないため、処理を中止しました。 */
    private static final String ERRORS_CMN_SUGBOXDIRECTORY_NOT_FOUND = "errors.cmn.SugBoxDirectory.notfound";

    /** PSiteService */
    @Autowired
    private PSiteService pSiteService;

    /** 機能ID */
    private static final String SUG_BOX_FUNC_ID = "M13";

    /**
     * 
     * Dto レスポンス：String
     * @param catId カテゴリID
     * @return DataList<String>
     * @exception Exception 例外
     * @see <reference item>
     */
    @Override
    public DataList<String> getSugBoxFileDir(String catId) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSuggestionBoxServiceImpl.getSugBoxFileDir");
        }

        DataList<String> res = new DataList<String>();
        List<String> resList = new ArrayList<String>();

        DataList<GetTempFileDirForDocumentCategoryIdModel> dataList = pSiteService
                .getTempFileDirForDocumentCategoryId(SUG_BOX_FUNC_ID, catId);

        // 取得結果が0件の場合
        if (ObjectUtils.isEmpty(dataList) || dataList.size() < 1) {
            res = IfaCommonUtil.createDataList(resList, ErrorLevel.FATAL, ERRORS_CMN_SUGBOXDIRECTORY_NOT_FOUND,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SUGBOXDIRECTORY_NOT_FOUND));
            return res;
        }

        // レスポンスを設定
        String fileDir = dataList.getDataList().get(0).getfile_dir();
        resList.add(fileDir);
        
        res = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");

        return res;
    }
}
