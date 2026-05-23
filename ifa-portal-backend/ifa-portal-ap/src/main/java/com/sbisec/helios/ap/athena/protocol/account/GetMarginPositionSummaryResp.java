package com.sbisec.helios.ap.athena.protocol.account;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.account.dto.Position;

/**
 * 外国株式信用建玉サマリ取得API Response.
 * 
 * @author yunhui.zhao
 * @date 03/11/2022
 */
public class GetMarginPositionSummaryResp {
    
    public GetMarginPositionSummaryResp() {
    
    }
    
    // 当日基準余力情報
    private Position positionSummary;
    
    // 外国株式信用建玉明細一覧
    private List<Position> positions;
    
    /**
     * @return 外国株式信用建玉サマリ
     */
    public Position getPositionSummary() {
        
        return positionSummary;
    }
    
    public void setPositionSummary(Position positionSummary) {
        
        this.positionSummary = positionSummary;
    }
    
    /**
     * @return 外国株式信用建玉明細一覧
     */
    public List<Position> getPositions() {
        
        return positions;
    }
    
    public void setPositions(List<Position> positions) {
        
        this.positions = positions;
    }
}
