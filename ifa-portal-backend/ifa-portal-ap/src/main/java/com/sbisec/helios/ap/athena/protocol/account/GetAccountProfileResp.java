package com.sbisec.helios.ap.athena.protocol.account;

import lombok.Data;

/**
 * 
 * @Description:口座情報サービス - アカウントプロファイル取得 Response
 * @author xiu.yan
 * @date: 2/14/2022
 */

@Data
public class GetAccountProfileResp {
    
    // アカウントプロファイル名
    private String profileName;
    
    // アカウントプロファイル設定値
    private String profileValue;
    
}
