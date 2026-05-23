package com.sbisec.helios.ap.ccsApi.enums;

import org.springframework.util.ObjectUtils;

/**
 * エラーコード一覧の列挙型
 */
public enum ErrorCode {

    // 接触履歴検索結果0件
    IFAP00("IFAP00", "該当のデータはありません。"),
    // 顧客IDが数字ではない
    IFAP01("IFAP01", "IFAPの顧客IDが不正です。"),
    // CCSアカウントIDが半角英数ではない
    IFAP02("IFAP02", "IFAPのCCSアカウントIDが不正です。"),
    // カテゴリコードが00~13以外
    IFAP03("IFAP03", "IFAPのカテゴリコードが不正です。"),
    // 記録日時の前10桁が日付ではない 
    IFAP04("IFAP04", "IFAPの記録日時が不正です。"),
    // 枝番が数字ではない
    IFAP05("IFAP05", "IFAPの枝番が不正です。"),
    // 詳細区分が1、４、5以外
    IFAP06("IFAP06", "IFAPの詳細区分が不正です。"),
    // ユーザー情報検索結果0件
    IFAP07("IFAP07", "該当のユーザーはありません。"),
    // 顧客情報検索結果0件、口座参照権限チェック失敗
    IFAP08("IFAP08", "該当の口座はありません。"),
    // 接触履歴、接触履歴受付詳細Functionエラー
    IFAP99("IFAP99", "CCSにエラーが発生しました。");

    ErrorCode(String id, String name) {
        this.id = id;
        this.name = name;
    }

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static ErrorCode getById(String id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        ErrorCode[] enums = values();
        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getId().equals(id)) {
                return enums[i];
            }
        }
        return null;
    }
}
