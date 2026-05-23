package com.sbisec.helios.ap.suggestionBox.dto;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class IfaSuggestionBoxPersonalA006CsvItem extends ModelBase {

   private static final long serialVersionUID = 1L;

   /** 要望No */
   private String sbpNo;

   /** 更新日 */
   private String ansUpdateTime;

   /** 登録日 */
   private String registerDate;

   /** 仲介業者コード  */
   private String brokerCode;

   /** 仲介業者名 */
   private String brokerName;

   /** 営業員コード  */
   private String empCode;

   /** 営業員名  */
   private String empName;

   /** タイトル  */
   private String title;

   /** カテゴリ  */
   private String category;

   /** ステータス  */
   private String status;

   /** 要望内容 */
   private String suggestion;

}
