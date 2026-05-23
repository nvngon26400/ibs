package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

/**
 * 接触履歴 A002レスポンスDto

 * @author 趙韫慧
 *
 */
@Data
public class IfaContactA002ResponseDto {

    /** 接触履歴リスト */
    private List<IfaContactCommonResponseDto> contactInfoList;
}
