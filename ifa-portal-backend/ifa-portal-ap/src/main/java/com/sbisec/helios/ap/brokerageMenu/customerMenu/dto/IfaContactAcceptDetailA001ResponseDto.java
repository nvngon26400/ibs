package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

/**
 * 接触履歴受付詳細 A001レスポンスDto
 * 
 * @author 趙韫慧
 *
 */
@Data
public class IfaContactAcceptDetailA001ResponseDto {

    /** 接触履歴受付詳細リスト */
    private List<IfaContactAcceptDetailCommonResponseDto> contactAcceptDetailInfoList;

}
