package com.sbisec.helios.ap.bizcommon.dao.model;

import java.util.List;

import com.sbibits.earth.model.ModelBase;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 利用者顧客参照権限
 *
 * @author SCSK
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Fct001Sql001RequestModel extends ModelBase {
    
    /** 権限コード */
    private String privId;
    
    /** 部店コード */
    private String butenCode;
    
    /** 口座番号 */
    private String accountNumber;
    
    /** 仲介業者営業員リスト */
    private List<BrokerCharge> brokerChargeList;
    
}
