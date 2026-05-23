package com.sbisec.helios.gw.brokerageMenu.jointSubscript.form;

import java.util.List;

import lombok.Data;

/**
 * リクエストパラメータ
 * 画面ID：SUB0206_01-01
 * 画面名：共同募集 顧客管理
 * アクション：A007 削除確認
 * 2024/12/12 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaJointSubscriptCustomerManageA007ApiRequest {

    // 一覧画面選択行目のデータモデルのリスト
    private List<IfaJointSubscriptCustomerManageA007ApiModel> a007Model;

}
