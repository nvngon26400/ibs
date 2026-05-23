package com.sbisec.helios.ap.athena.service;

import com.sbisec.helios.ap.athena.protocol.account.ConfirmMarginTransferReq;
import com.sbisec.helios.ap.athena.protocol.account.ConfirmMarginTransferResp;
import com.sbisec.helios.ap.athena.protocol.account.CreateMarginTransferReq;
import com.sbisec.helios.ap.athena.protocol.account.CreateMarginTransferResp;
import com.sbisec.helios.ap.athena.protocol.account.ConfirmCollateralSecuritiesTransferReq;
import com.sbisec.helios.ap.athena.protocol.account.ConfirmCollateralSecuritiesTransferResp;
import com.sbisec.helios.ap.athena.protocol.account.CreateCollateralSecuritiesTransferReq;
import com.sbisec.helios.ap.athena.protocol.account.CreateCollateralSecuritiesTransferResp;

/**
 * Comet API 口座情報サービス Service
 * <p>
 * 一覧
 * <p>
 * 口座情報サービス - 委託保証金振替確認API<br>
 * 口座情報サービス - 委託保証金振替登録API<br>
 * 口座情報サービス - 代用有価証券振替確認API<br>
 *
 * @author shuchen.xin
 * @date 02/22/2022
 */
public interface CometCollateralSecuritiesTransferService {
    
    /**
     * 口座情報サービス - 委託保証金振替確認API.
     *
     * @param confirmMarginTransferReq Httpリクエスト
     * @return 委託保証金振替確認結果
     * @throws Exception 異常
     * 
     * @see com.sbisec.helios.ap.athena.protocol.account.ConfirmMarginTransferReq
     * @see com.sbisec.helios.ap.athena.protocol.account.ConfirmMarginTransferResp
     */
    public ConfirmMarginTransferResp confirmMarginTransfer(ConfirmMarginTransferReq confirmMarginTransferReq)
            throws Exception;
    
    /**
     * 口座情報サービス - 委託保証金振替登録API.
     * 
     * @param createMarginTransferReq Httpリクエスト
     * @return 委託保証金振替登録結果
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.account.CreateMarginTransferReq
     * @see com.sbibits.horus.ap.athena.protocol.account.CreateMarginTransferResp
     */
    public CreateMarginTransferResp createMarginTransfer(CreateMarginTransferReq createMarginTransferReq)
            throws Exception;
    
    /**
     * 入出金・入出庫サービス - 代用有価証券振替確認API.
     *
     * @param request Httpリクエスト
     * @return 代用有価証券振替情報
     * @throws Exception 異常
     * 
     * @see com.sbisec.helios.ap.athena.protocol.account.ConfirmCollateralSecuritiesTransferReq
     * @see com.sbisec.helios.ap.athena.protocol.account.ConfirmCollateralSecuritiesTransferResp
     */
    public ConfirmCollateralSecuritiesTransferResp confirmCollateralSecuritiesTransfer(
            ConfirmCollateralSecuritiesTransferReq request) throws Exception;
    
    /**
     * 入出金・入出庫サービス - 代用有価証券振替登録API.
     *
     * @param request Httpリクエスト
     * @return 代用有価証券振替情報
     * @throws Exception 異常
     * 
     * @see com.sbisec.helios.ap.athena.protocol.account.CreateCollateralSecuritiesTransferReq
     * @see com.sbisec.helios.ap.athena.protocol.account.CreateCollateralSecuritiesTransferResp
     */
    public CreateCollateralSecuritiesTransferResp createCollateralSecuritiesTransfer(
            CreateCollateralSecuritiesTransferReq request) throws Exception;
}
