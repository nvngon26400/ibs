package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import java.util.Date;

import lombok.Data;

/**
 * 出金取消履歴Req002
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaWithdrawCancelConfirmSql002RequestModel {

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

    /** 取消ステータスコード */
    private String torikeshiStsCd;

    /** 取消ユーザーID */
    private String torikeshiUserId;

    /** EC入出金番号 */
    private String ecNyushukkinNo;

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

    /** 削除フラグ */
    private String sakujoFlg;

    /** 即時入金区分 */
    private String sokujiNyuukinKbn;

    /** ジュニアNISA口座区分 */
    private String jrnisaAccountKbn;

    /** 登録日時 */
    private Date tourokuNichiji;

    /** 作成者 */
    private String createUser;

    /** 更新者 */
    private String updateUser;

}
