package com.sbisec.helios.ap.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.dao.CommonCodeDao;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.service.CommonCodeService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * コード値公開用汎用コントローラ（API）サービス実装クラス
 *
 * @author 河口
 *
 */
@Component(value = "commonCodeService")
public class CommonCodeServiceImpl implements CommonCodeService {
    
    /** ロガー */
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonCodeServiceImpl.class);
    
    private static final String ERRORS_CODE_NOT_FOUND = "errors.codeNotFound";
    
    @Autowired
    private CommonCodeDao commonCodeDao;
    
    @Override
    public DataList<Object> getCommonCode(String codeListId, String dispPattern, String selectPattern) {
        
        List<Object> params = new ArrayList<Object>();
        params.add(codeListId);
        params.add(dispPattern);
        params.add(selectPattern);
        
        DataList<Object> dataList = commonCodeDao.getCommonCode("codeListMapper", "getCodeList", params);
        
        // 結果が0件の場合、エラーログを出力する
        if (dataList.getTotalSize() == 0) {
            String[] paramStr = { params.toString() };
            LOGGER.error(IfaCommonUtil.getMessage(ERRORS_CODE_NOT_FOUND, paramStr));
        }
        
        dataList.setErrorLevel(ErrorLevel.SUCCESS.getId());
        dataList.setReturnCode("0");
        
        return dataList;
    }
}
