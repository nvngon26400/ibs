package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * その他余力拘束注文確認 SQL001用リクエストモデル
 * 2025/03/10 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaOtherRemainPowerRestrainInputConfirmSql001RequestModel {

    /** トランザクションＩＤ */
    private String tranId;

    /** 枝番 */
    private Integer edaban;

    /** 顧客ID */
    private String kokyakuId;

    /** 部店CD */
    private String butenCd;

    /** 口座番号 */
    private String kouzaNo;

    /** 発注区分 */
    private String hattyuuKbn;

    /** 特定預り売買区分 */
    private String tokuteiAzukariBaibaiKbn;

    /** 特定口座区分 */
    private String tokuteiKouzaKbn;

    /** 受注日 */
    private String juchubi;

    /** EC受注番号（その他商品） */
    private String ecJuchuuNo;

    /** 継投区分 */
    private String keitouKbn;

    /** 会社コード */
    private String kaishaCd;

    /** 新旧区分 */
    private String shinkyuKbn;

    /** 回数 */
    private String kaisuu;

    /** 号 */
    private String gou;

    /** 商品名 */
    private String shouhinmei;

    /** 商品区分 */
    private String shouhinKbn;

    /** ステータスコード */
    private String stsCd;

    /** ユーザーＩＤ */
    private String userId;

    /** エラーコード */
    private String errorCd;

    /** エラーメッセージ */
    private String errorMessage;

    /** EC受注日時 */
    private String ecJuchuuNichiji;

    /** 売買区分 */
    private String baibaiKbn;

    /** 精算金額 */
    private String seisanKingaku;

    /** 確定精算金額 */
    private String kakuteiSeisanKingaku;

    /** 注文・出来区分 */
    private String chumonDekiKbn;

    /** 取消区分 */
    private String torikeshiKbn;

    /** 受渡方法 */
    private String ukewatashiHouho;

    /** 支払い方法 */
    private String siharaiHouho;

    /** 数量 */
    private String suryou;

    /** 出来数量 */
    private String dekiSuryou;

    /** 指成区分 */
    private String sashinariKbn;

    /** 単価 */
    private String tanka;

    /** 経過利子 */
    private String keikaRisi;

    /** 譲渡益税区分 */
    private String joutoekizeiKbn;

    /** 通貨 */
    private String tsuuka;

    /** 為替レート */
    private String kawaseRate;

    /** 為替手数料 */
    private String kawaseTesuuryou;

    /** クーポンレート */
    private String couponRate;

    /** クーポン回数（年） */
    private String couponKasu;

    /** 受付経路区分 */
    private String uketsukeKeiroKbn;

    /** 余力チェック区分 */
    private String yoryokuCheckKbn;

    /** 種別 */
    private String shubetsu;

    /** 買付可能金額 */
    private String kaitsukekanouKingaku;

    /** 注文後の買付可能金額 */
    private String chumonKaitsukekanouKingaku;

    /** 失効区分 */
    private String shikkouKbn;

    /** 市場 */
    private String shijou;

    /** 処理フラグ */
    private String shoriFlg;

    /** 解約指定区分 */
    private String kaiyakuShitei_kbn;

    /** 約定予定日 */
    private String yakujouYoteibi;

    /** 受渡予定日 */
    private String ukewatashiYoteibi;

    /** 入力受注日時 */
    private String nyuuryokuJuchuuNichiji;

    /** 出来日時 */
    private String dekiHichiji;

    /** 国内約定日 */
    private String kokunaiYakujoubi;

    /** 国内受渡日 */
    private String kokunaiUkewatashibi;

    /** 現地受渡日 */
    private String genchiUkewatashibi;

    /** 発注理由 */
    private String hattyuRiyu;

    /** 印刷フラグ */
    private String insatsuFlg;

    /** 登録日時 */
    private String tourokuNichiji;

    /** 変更日時 */
    private String henkouNichiji;

    /** 削除日時 */
    private String sakujo_nichiji;

    /** 削除フラグ */
    private String sakujoFlg;

    /** 勧誘区分 */
    private String kannyuuKbn;

    /** 受注方法 */
    private String jyuuchuuHouhou;

    /** 資金性格 */
    private String shikinSeikaku;

    /** マル優口座区分 */
    private String maruyuKouzaKbn;

    /** 拘束区分 */
    private String kousokuKbn;

    /** 拘束金額（NISA） */
    private String nisaKingaku;

    /** 拘束種別 */
    private String kousokuSyubetu;

    /** CCS送付日 */
    private String ccsSendDate;

}
