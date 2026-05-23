package com.sbisec.helios.common.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * GWとAPサーバで共有する情報<br />
 * GW→APサーバへのREST通信時に送信され、APサーバでリクエストスコープに設定される。
 *
 * @author 河口
 *
 */
@Data
public class GwApSharedInfo {
    
    /** APIスタブ対象テストケース */
    private String apiStubTargetTestCase;
    
    /** 外部APIスタブテストデータ一覧 */
    private List<Map<String, String>> apiStubTestDataList;
}
