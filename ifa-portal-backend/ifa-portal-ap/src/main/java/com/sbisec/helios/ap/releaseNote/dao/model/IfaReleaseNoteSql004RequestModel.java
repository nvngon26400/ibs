package com.sbisec.helios.ap.releaseNote.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB00-07_1
 * リリースノート SQL004（リリースノート次回表示フラグ更新） リクエストパラメタ
 * 2025/10/24 新規作成
 *
 * @author 大連 葉
 */
@Data
public class IfaReleaseNoteSql004RequestModel {

    /** 次回表示フラグ */
    private String nextDispFlg;

    /** ユーザーID */
    private String userId;
}
