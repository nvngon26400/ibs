package com.sbisec.helios.ap.fasthelp.service.dto;

import lombok.Data;

/**
 * InputのDtoベース
 */
@Data
public abstract class BaseDtoIn {

    /** 処理区分 */
    private String process_id;

    /** コール番号 */
    private String call_id;

    /** サブコール番号 */
    private String q_toiawase_no;

    /** 顧客番号 */
    private String q_kokyaku_id;

    /** 登録担当者番号 */
    private String q_user_id;

    /** 現在担当者番号 */
    private String q_kosin_user_id;

    /** （call_category）問合せカテゴリコード */
    private String q_toiawase_cd;

    /** （call_category）カテゴリ名 */
    private String q_toiawase_mei;

    /** （call_category）問合せカテゴリコード（大） */
    private String q_toiawase_d_cd;

    /** （call_category）カテゴリ名 */
    private String q_toiawase_d_mei;

    /** （call_category）問合せカテゴリコード（小） */
    private String q_toiawase_s_cd;

    /** （call_category）カテゴリ名 */
    private String q_toiawase_s_mei;

    /** 問題詳細_ロング */
    private String q_toiawase_naiyou;

    /** 問合せ日時/登録日時 */
    private String q_toiawase_nichiji;

    /** 優先度 */
    private String q_juuyoudo;

    /** クレームフラグ（1:あり, 1以外：なし） */
    private String q_cream;

    /** リクエストフラグ（1:あり, 1以外：なし） */
    private String q_request;

    /** 受付種別 */
    private String q_houkou;

    /** ステータス1 */
    private String q_taiou_sts;

    /** 登録日時 */
    private String q_touroku_nichiji;

    /** 更新日時 */
    private String q_henkou_nichiji;

    /** 訪問日 */
    private String q_houmonbi;

    /** 次回訪問予定日 */
    private String q_next_houmonbi;

    /** サブコール番号 */
    private String a_toiawase_no;

    /** 回答NO */
    private String a_kaitou_no;

    /** tb_answerユーザID/登録担当者番号 */
    private String a_user_id;

    /** 更新担当者番号 */
    private String a_kosin_user_id;

    /** 回答詳細_ロング */
    private String a_kaitou_naiyou;

    /** 更新日時/最終回答日時/登録日時 */
    private String a_kaitou_nichiji;

    /** tb_answer登録日時/登録日時 */
    private String a_touroku_nichiji;

    /** 更新日時 */
    private String a_henkou_nichiji;
}
