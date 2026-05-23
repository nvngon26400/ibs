package com.sbisec.helios.ap.bizcommon.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 共通関数DTO：FCT008
 *
 * @author SCSK
 */

@Data
@EqualsAndHashCode(callSuper=false)
public class OutputFct008Dto extends BaseOutputDto {
    
    //営業日リスト
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
    private List<Date> bussiessDaylist;
}
