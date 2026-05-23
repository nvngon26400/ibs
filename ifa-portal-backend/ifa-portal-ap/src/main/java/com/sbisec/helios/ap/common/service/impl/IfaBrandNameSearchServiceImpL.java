package com.sbisec.helios.ap.common.service.impl;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.dao.IfaBrandNameSearchDao;
import com.sbisec.helios.ap.common.dao.model.IfaBrandNameSearchRequestModel;
import com.sbisec.helios.ap.common.dao.model.IfaBrandNameSearchResponseModel;
import com.sbisec.helios.ap.common.dto.IfaBrandNameSearchRequestDto;
import com.sbisec.helios.ap.common.dto.IfaBrandNameSearchResponseDto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.service.IfaBrandNameSearchService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB07-03_1 画面名：投信銘柄検索ポップアップ
 *
 * @author xin.huang
 */
@Component(value = "cmpIfaBrandNameSearchService")
public class IfaBrandNameSearchServiceImpL implements IfaBrandNameSearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaBrandNameSearchServiceImpL.class);

    @Autowired
    private IfaBrandNameSearchDao dao;

    enum MessageId {
        // errors.dataList.notfound
        ERRORS_DATALIST_NOTFOUND("errors.dataList.notfound");

        private String key;

        private MessageId(String key) {
            this.key = key;
        }
    }

    /**
     * アクションID：A002
     * アクション名：投信銘柄検索
     * Dtoリクエスト：IfaBrandNameSearchRequestDto
     * Dtoレスポンス：IfaBrandNameSearchResponseDto
     * Modelリクエスト：IfaBrandNameSearchRequestModel
     * Modelレスポンス：IfaBrandNameSearchResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBrandNameSearchResponseDto> brandNameSearch(IfaBrandNameSearchRequestDto dtoReq)
            throws Exception {
        DataList<IfaBrandNameSearchResponseDto> dtoRes = new DataList<IfaBrandNameSearchResponseDto>();
        List<IfaBrandNameSearchResponseDto> resDtoList = new ArrayList<IfaBrandNameSearchResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaBrandNameSearchServiceImpL.brandNameSearch");
        }

        IfaBrandNameSearchRequestModel sqlReq = new IfaBrandNameSearchRequestModel();
        // 検索キーワード(リクエスト.検索キーワード)をセットする
        sqlReq.setKeyWordSearch(dtoReq.getKeyWordSearch());
        DataList<IfaBrandNameSearchResponseModel> sqlRes = dao.brandNameSearch(sqlReq);
        // SQLの実行結果が0件の場合はレスポンス結果を処理せず終了するINFO
        if (sqlRes.getDataList().isEmpty()) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_DATALIST_NOTFOUND.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_DATALIST_NOTFOUND.key));
            return dtoRes;
        }
        IfaBrandNameSearchResponseDto resDto = new IfaBrandNameSearchResponseDto();
        // 投信銘柄詳細
        resDto.setBrandInfo(sqlRes.getDataList());
        // レスポンスをコントローラーに返却する。
        resDtoList.add(resDto);
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }
}
