package com.sbisec.helios.ap.common.service;

import java.util.List;

import com.sbisec.helios.ap.common.model.IfaCodeListDispPatternModel;
import com.sbisec.helios.ap.service.Service;

/**
 * 区分定義公開機能サービスインターフェイス
 *
 * @author 河口
 *
 */
public interface CodeListService extends Service {
    
    /**
     * 区分定義リスト取得機能<br/>
     * 下記で取得を行う<br/>
     * ・表示パターン=1<br/>
     * ・取得パターン=1
     *
     * @param codeListId 区分ID
     * @return 区分定義リスト
     */
    public List<IfaCodeListDispPatternModel> getCodeList(String codeListId);
    
    /**
     * 区分定義リスト取得機能<br/>
     * 下記で取得を行う<br/>
     * ・取得パターン=1
     *
     * @param codeListId  区分ID
     * @param dispPattern 表示パターン
     * @return 区分定義リスト
     */
    public List<IfaCodeListDispPatternModel> getCodeList(String codeListId, String dispPattern);
    
    /**
     * 区分定義リスト取得機能<br/>
     * 表示パターン、取得パターンがnullの場合、「1」で取得を行う
     *
     * @param codeListId    区分ID
     * @param dispPattern   表示パターン
     * @param selectPattern 取得パターン
     * @return 区分定義リスト
     */
    public List<IfaCodeListDispPatternModel> getCodeList(String codeListId, String dispPattern, String selectPattern);
    
    /**
     * 外部コードと内部コードの相互変換機能<br/>
     * 内部コード→外部コードへ変換する<br/>
     * 区分値がnullもしくは空文字の場合、「$NULL」として取得を行う
     *
     * @param codeListId  区分ID
     * @param apiType     APIタイプ
     * @param codeListKey 区分値
     * @return 外部区分値
     */
    public String convertKeyToExtKey(String codeListId, String apiType, String codeListKey);
    
    /**
     * 外部コードと内部コードの相互変換機能<br/>
     * 外部コード→内部コードへ変換する<br/>
     * 外部区分値がnullもしくは空文字の場合、「$NULL」として取得を行う
     *
     * @param codeListId     区分ID
     * @param apiType        APIタイプ
     * @param codeListExtKey 外部区分値
     * @return 区分値
     */
    public String convertExtKeyToKey(String codeListId, String apiType, String codeListExtKey);
    
    /**
     * 区分値の日本語名称取得機能<br/>
     * 区分値を指定し、区分値名称を取得する<br/>
     * 表示パターン=1で取得を行う<br/>
     * 区分値がnullもしくは空文字の場合、「$NULL」として取得を行う
     *
     * @param codeListId  区分ID
     * @param codeListKey 区分値
     * @return 区分値名称
     */
    public String getValue(String codeListId, String codeListKey);
    
    /**
     * 区分値の日本語名称取得機能<br/>
     * 区分値を指定し、区分値名称を取得する<br/>
     * 区分値がnullもしくは空文字の場合、「$NULL」として取得を行う
     *
     * @param codeListId  区分ID
     * @param codeListKey 区分値
     * @param dispPattern 表示パターン
     * @return 区分値名称
     */
    public String getValue(String codeListId, String codeListKey, String dispPattern);
    
    /**
     * 区分値の日本語名称取得機能<br/>
     * 区分値名称を指定し、区分値を取得する<br/>
     * 表示パターン=1で取得を行う
     *
     * @param codeListId    区分ID
     * @param codeListValue 区分値名称
     * @return 区分値
     */
    public String getKey(String codeListId, String codeListValue);
    
    /**
     * 区分値の日本語名称取得機能<br/>
     * 区分名称を指定し、区分値を取得する
     *
     * @param codeListId    区分ID
     * @param codeListValue 区分値名称
     * @param dispPattern   表示パターン
     * @return 区分値
     */
    public String getKey(String codeListId, String codeListValue, String dispPattern);
}
