package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 出金確認履歴Req001
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaWithdrawExecuteConfirmSql001RequestModel {

    /** トランザクションID */
    private String tranId;

    /** 枝番 */
    private String edaban;

    /** 顧客ID */
    private String kokyakuId;

    /** 部店CD */
    private String butenCd;

    /** 口座番号 */
    private String kouzaNo;

    /** 発注区分 */
    private String hacchuuKbn;

    /** ユーザーID */
    private String userId;

    /** 取消ステータスコード */
    private String torikeshiStsCd;

    /** 金額 */
    private String kingaku;

    /** 振替区分 */
    private String furikaeKbn;

    /** 仕訳ＮＯ */
    private String shiwakeNo;

    /** 資金 */
    private String shikinn;

    /** 送金料 */
    private String soukinnryou;

    /** 受渡者 */
    private String ukewatashisha;

    /** 一括 */
    private String ikkatsu;

    /** 受付経路区分 */
    private String uketsukeKeiroKbn;

    /** 商品区分 */
    private String shouhinnKbn;

    /** 余力チェック区分 */
    private String yoryokuCheckKbn;

    /** 削除日時 */
    private String sakujoNichiji;

    /** 削除フラグ */
    private String sakujoFlg;

    /** 即時入金区分 */
    private String sokujiNyuukinKbn;

    /** ジュニアNISA口座区分 */
    private String jrnisaAccountKbn;

    /** 作成者 */
    private String createUser;

    /** 更新者 */
    private String updateUser;

}
