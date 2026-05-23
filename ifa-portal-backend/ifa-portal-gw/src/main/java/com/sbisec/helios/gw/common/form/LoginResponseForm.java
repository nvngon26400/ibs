package com.sbisec.helios.gw.common.form;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.model.UserAccount;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ログイン返却フォーム
 *
 * @author SCSK
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class LoginResponseForm extends DataList<String> {
    
    /** フレームワークセッションID。 */
    private String frameworkSessionId;
    
    /** 認証トークン。 */
    private String authToken;
    
    /** 最終リクエスト時刻。 */
    private Date lastRequestSec;
    
    /** 無操作タイムアウト秒数。 */
    private int idleLimitSec;
    
    /** リクエストタイムアウト秒数。 */
    private int requestTimeOutSec;
    
    /** データグリッドページング情報 */
    @JsonProperty("rPPOptions")
    private String rppOptions;
    
    /** 前営業日 */
    private String previousBusinessDay;
    
    /** 目安箱未読件数 */
    private int sugBoxUnreadItems;
    
    /** リリースノート表示フラグ */
    private boolean releaseNoteDispFlg;
    
    /** ユーザ共通情報。 */
    private UserAccount userAccount;
    
}
