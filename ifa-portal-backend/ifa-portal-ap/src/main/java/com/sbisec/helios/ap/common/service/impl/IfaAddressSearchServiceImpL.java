package com.sbisec.helios.ap.common.service.impl;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.dao.IfaAddressSearchDao;
import com.sbisec.helios.ap.common.dao.model.IfaAddressSearchRequestModel;
import com.sbisec.helios.ap.common.dao.model.IfaAddressSearchResponseModel;
import com.sbisec.helios.ap.common.dto.IfaAddressSearchRequestDto;
import com.sbisec.helios.ap.common.dto.IfaAddressSearchResponseDto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.service.IfaAddressSearchService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB07-01 画面名：住所検索ポップアップ
 *
 * @author xin.huang
 */
@Component(value = "cmpIfaAddressSearchService")
public class IfaAddressSearchServiceImpL implements IfaAddressSearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaAddressSearchServiceImpL.class);

    @Autowired
    private IfaAddressSearchDao dao;

    enum MessageId {
        // errors.dataList.notfound
        ERRORS_DATALIST_NOTFOUND("errors.dataList.notfound");

        private String key;

        private MessageId(String key) {
            this.key = key;
        }
    }

    /**
     * アクションID：A001
     * アクション名：住所検索
     * Dto リクエスト：IfaAddressSearchRequestDto
     * Dto レスポンス：IfaAddressSearchResponseDto
     * Modelリクエスト：IfaAddressSearchRequestModel
     * Modelレスポンス：IfaAddressSearchResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaAddressSearchResponseDto> addressSearch(IfaAddressSearchRequestDto dtoReq) throws Exception {
        DataList<IfaAddressSearchResponseDto> dtoRes = new DataList<IfaAddressSearchResponseDto>();
        List<IfaAddressSearchResponseDto> resDtoList = new ArrayList<IfaAddressSearchResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaAddressSearchServiceImpL.addressSearch");
        }

        // リスト.住所を取得する｡
        IfaAddressSearchRequestModel sqlReq = new IfaAddressSearchRequestModel();
        // 郵便番号(リクエスト.郵便番号)をセットする
        sqlReq.setYuusouNumber(dtoReq.getYuusouNumber());
        DataList<IfaAddressSearchResponseModel> sqlRes = dao.addressSearch(sqlReq);
        // SQLの実行結果が0件の場合はレスポンス結果を処理せず終了する
        if (sqlRes.getDataList().isEmpty()) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_DATALIST_NOTFOUND.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_DATALIST_NOTFOUND.key));
            return dtoRes;
        }
        IfaAddressSearchResponseDto resDto = new IfaAddressSearchResponseDto();
        // 郵便番号
        resDto.setAddressList(sqlRes.getDataList());
        // レスポンスをコントローラーに返却する。
        resDtoList.add(resDto);
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }
}
