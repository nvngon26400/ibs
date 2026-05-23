package com.sbisec.helios.ap.suggestionBox.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonSql002RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonSql002ResponseModel;


@Mapper
public interface IfaSuggestionBoxCommonMapper {
    
    /**
     * SQLID：SQL002
     * SQL名：皆様からの要望一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaSuggestionBoxCommonSql002Request
     * レスポンスクラス：IfaSuggestionBoxCommonSql002Response
     *
     * @param req パラメータ
     * @return 皆様からの要望一覧
     * @exception Exception システムエラー
     */
    public List<IfaSuggestionBoxCommonSql002ResponseModel> selectIfaSugBoxCommonSql002(
            @Param("req") IfaSuggestionBoxCommonSql002RequestModel req
    ) throws Exception;

    
}
