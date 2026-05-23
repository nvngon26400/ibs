package com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * サービス用レスポンスモデル
 * 画面ID：SUB0206_01-01
 * 画面名：共同募集 顧客管理
 * アクション：A007 削除確認
 * 2024/12/05 新規作成
 *
 * @author 大連 王永宝
 */
@Data
@JsonSerialize
public class IfaJointSubscriptCustomerManageA007ResponseDto {
    // 一覧選択したレコードのリスト
    private List<IfaJointSubscriptCustomerManageA007Model> a007ResDtoList;
}
