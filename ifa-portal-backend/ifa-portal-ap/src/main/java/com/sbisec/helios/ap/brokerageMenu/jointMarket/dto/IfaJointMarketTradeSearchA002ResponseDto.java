package com.sbisec.helios.ap.brokerageMenu.jointMarket.dto;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB0208_01-01
 * 画面名：共同店舗取引検索
 * 
 * @author lianhua.xia
 * 2024/12/06 新規作成
 */

@Data
public class IfaJointMarketTradeSearchA002ResponseDto {

    private List<IfaJointMarketTradeSearchList> jointMarketTradeSearchList;

}
