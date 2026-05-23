package com.sbisec.helios.ap.suggestionBox.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonDetailSql001RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonDetailSql001ResponseModel;


@Mapper
public interface IfaSuggestionBoxCommonDetailMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：皆様からの要望詳細取得
     * SQLタイプ：select
     * リクエストクラス：IfaSuggestionBoxCommonDetailSql001RequestModel
     * レスポンスクラス：IfaSuggestionBoxCommonDetailSql001ResponseModel
     *
     * @param req パラメータ
     * @return 皆様からの要望詳細
     * @exception Exception システムエラー
     */
    public List<IfaSuggestionBoxCommonDetailSql001ResponseModel> selectIfaSugBoxCommonDetailSql001(
            @Param("req") IfaSuggestionBoxCommonDetailSql001RequestModel req
    ) throws Exception;

    
}
