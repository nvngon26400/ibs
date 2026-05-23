package com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto;

import java.util.List;

import lombok.Data;

/**
 * サービス用リクエストモデル
 * 画面ID：SUB0206_01-01
 * 画面名：共同募集 顧客管理
 * アクション：A005 承認確認
 * 2024/12/05 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaJointSubscriptCustomerManageA005RequestDto {

    // 一覧画面選択行目のデータモデルのリスト
    private List<IfaJointSubscriptCustomerManageA005Model> a005Model;

}
