package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaMutualFundAccumulateSettingBrandListDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaMutualFundAccumulateSettingBrandListMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingBrandListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingBrandListSql001ResponseModel;

/**
 * 画面ID：SUB0202_0403-01 画面名：投信積立設定済銘柄一覧
 *
 * @author nicksen.li
 */
@Component
public class IfaMutualFundAccumulateSettingBrandListDaoImpL extends RowSelectableDao
        implements IfaMutualFundAccumulateSettingBrandListDao {

    @Autowired
    private IfaMutualFundAccumulateSettingBrandListMapper mapper;

    /**
     * SQLID：Sql001 SQL名：銘柄コード取得 SQLタイプ：select
     * リクエストクラス：IfaDomesticPositionListSql001RequestModel
     * レスポンスクラス：IfaDomesticPositionListSql001ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @throws Exception 銘柄コード取得の際、例外が発生した場合
     */
    public DataList<IfaMutualFundAccumulateSettingBrandListSql001ResponseModel> selectIfaMutualFundAccumulateSettingBrandListSql001(
            IfaMutualFundAccumulateSettingBrandListSql001RequestModel req) throws Exception {

        DataList<IfaMutualFundAccumulateSettingBrandListSql001ResponseModel> res = new DataList<IfaMutualFundAccumulateSettingBrandListSql001ResponseModel>();

        List<IfaMutualFundAccumulateSettingBrandListSql001ResponseModel> resultList = new ArrayList<>();

        List<String> fundCodeList = req.getFundCodeList();
        List<String> divisionList = new ArrayList<>();

        if (fundCodeList != null && fundCodeList.size() > 0) {
            int cnt = 1;

            IfaMutualFundAccumulateSettingBrandListSql001RequestModel divisionListRequestModel = new IfaMutualFundAccumulateSettingBrandListSql001RequestModel();

            for (int i = 0; i < fundCodeList.size(); i++) {
                if (cnt % 200 == 0) {
                    divisionListRequestModel.setFundCodeList(divisionList);
                    // 200行毎Queryを実行する
                    resultList.addAll(
                            mapper.selectIfaMutualFundAccumulateSettingBrandListSql001(divisionListRequestModel));
                    divisionList.clear();
                    divisionListRequestModel.setFundCodeList(null);
                    cnt = 1;
                }
                divisionList.add(fundCodeList.get(i));
                cnt++;
            }

            if (divisionList != null && divisionList.size() >= 0) {
                divisionListRequestModel.setFundCodeList(divisionList);
                resultList.addAll(mapper.selectIfaMutualFundAccumulateSettingBrandListSql001(divisionListRequestModel));
            }
        }

        res.setDataList(resultList);
        return res;
    }

}
