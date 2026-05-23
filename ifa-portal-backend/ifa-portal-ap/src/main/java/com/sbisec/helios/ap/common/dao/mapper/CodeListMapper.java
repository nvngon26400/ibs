package com.sbisec.helios.ap.common.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.common.model.IfaCodeListDispPatternModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 区分定義公開機能のMapperインターフェイス
 *
 * @author 河口
 *
 */
@Mapper
public interface CodeListMapper {
    
    /**
     * ＜２＞リスト取得
     *
     * @param codeListId 区分ID
     * @return 区分定義リスト
     */
    public List<IfaCodeListDispPatternModel> getCodeList(@Param("codeListId") String codeListId);
    
    /**
     * ＜２＞リスト取得
     *
     * @param codeListId  区分ID
     * @param dispPattern 表示パターン
     * @return 区分定義リスト
     */
    public List<IfaCodeListDispPatternModel> getCodeList(@Param("codeListId") String codeListId,
            @Param("dispPattern") String dispPattern);
    
    /**
     * ＜２＞リスト取得
     *
     * @param codeListId    区分ID
     * @param dispPattern   表示パターン
     * @param selectPattern 取得パターン
     * @return 区分定義リスト
     */
    public List<IfaCodeListDispPatternModel> getCodeList(@Param("codeListId") String codeListId,
            @Param("dispPattern") String dispPattern, @Param("selectPattern") String selectPattern);
    
    /**
     * ＜３＞外部コードと内部コードの相互変換<br/>
     * ①APIリクエストパラメータの編集で内部コード→外部コードへ変換
     *
     * @param codeListId  区分ID
     * @param apiType     APIタイプ
     * @param codeListKey 区分値
     * @return 外部区分値
     */
    public String convertKeyToExtKey(@Param("codeListId") String codeListId, @Param("apiType") String apiType,
            @Param("codeListKey") String codeListKey);
    
    /**
     * ＜３＞外部コードと内部コードの相互変換<br/>
     * ②APIレスポンスパラメータの編集で外部コード→内部コードへ変換
     *
     * @param codeListId     区分ID
     * @param apiType        APIタイプ
     * @param codeListExtKey 外部区分値
     * @return 区分値
     */
    public String convertExtKeyToKey(@Param("codeListId") String codeListId, @Param("apiType") String apiType,
            @Param("codeListExtKey") String codeListExtKey);
    
    /**
     * ＜４＞区分値の日本語名称取得<br/>
     * ①区分値⇒区分名称
     *
     * @param codeListId  区分ID
     * @param codeListKey 区分値
     * @param dispPattern 表示パターン
     * @return 区分値名称
     */
    public String getValue(@Param("codeListId") String codeListId, @Param("codeListKey") String codeListKey,
            @Param("dispPattern") String dispPattern);
    
    /**
     * ＜４＞区分値の日本語名称取得<br/>
     * ②区分名称⇒区分値
     *
     * @param codeListId    区分ID
     * @param codeListValue 区分値名
     * @param dispPattern   表示パターン
     * @return 区分値
     */
    public String getKey(@Param("codeListId") String codeListId, @Param("codeListValue") String codeListValue,
            @Param("dispPattern") String dispPattern);
}
