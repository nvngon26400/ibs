package com.sbisec.helios.ap.brokerageMenu.customerMenu.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.sbisec.helios.ap.common.util.DateUtil;

public class IfaMutualFundAccumulateSettingUtil {

    /**
     * ボーナス買付予定日の処理
     * 
     * ■ボーナス２買付予定日の設定有の場合 
     *   ■ボーナス１買付予定日＞ボーナス２買付予定日の場合 
     *     ボーナス２買付予定日をセット 
     *   ■上記以外
     *     ボーナス１買付予定をセット 
     * ■上記以外 
     *   ボーナス１買付予定をセット
     * 
     * @param bonusPlanDate1 ボーナス１買付予定日
     * @param bonusPlanDate2 ボーナス２買付予定日
     * @return ボーナス買付予定日
     */
    public static String getBonusPlanDate(String bonusPlanDate1, String bonusPlanDate2) {
        // ■ボーナス２買付予定日の設定有の場合
        if (bonusPlanDate2 != null && bonusPlanDate2.length() == 8) {
            LocalDate localBonusPlanDate2 = LocalDate.parse(bonusPlanDate2,
                    DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD));

            if (bonusPlanDate1 != null && bonusPlanDate1.length() == 8) {
                LocalDate localBonusPlanDate1 = LocalDate.parse(bonusPlanDate1,
                        DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD));

                // ■ボーナス１買付予定日＞ボーナス２買付予定日の場合
                if (localBonusPlanDate1.isAfter(localBonusPlanDate2)) {
                    // ボーナス２買付予定日をセット
                    return localBonusPlanDate2.format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD));
                } else {
                    // ボーナス１買付予定をセット
                    return localBonusPlanDate1.format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD));
                }
            } else {
                // その他の場合
            }
        } else {
            // ■上記以外
            if (bonusPlanDate1 != null && bonusPlanDate1.length() == 8) {
                // ボーナス１買付予定をセット
                return LocalDate.parse(bonusPlanDate1, DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD))
                        .format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD));
            } else {
                // その他の場合
            }
        }

        return "";
    }

    /**
     * NISA枠ぎりぎり買付区分対象の判断
     * 
     * ■預り区分="H"、"I”の場合 
     *   trueを戻す 
     * ■上記以外
     *   false 
     * 
     * @param accountType 預り区分
     * @return NISA枠ぎりぎり買付区分対象の判断
     */
    public static boolean isNisaBarelyBuyingTypeObject(String accountType) {
        if ("H".equals(accountType) || "I".equals(accountType)) {
            return true;
        }

        return false;
    }

    /**
     * NISA枠超過時買付区分対象の判断
     * 
     * ■預り区分="H"の場合 
     *   trueを戻す 
     * ■上記以外
     *   false 
     * 
     * @param accountType 預り区分
     * @return NISA枠超過時買付区分対象の判断
     */
    public static boolean isTaxShiftTypeObject(String accountType) {
        if ("H".equals(accountType)) {
            return true;
        }

        return false;
    }

    /**
     * 指定されたソートルールに基づき、Mapのキーまたは値をカンマ区切りの文字列に変換します。
     *
     * @param entryKeyMap 処理対象となるキーと値のペアを持つMap
     * @param sortBy      ソートの基準を指定します。"KEY"の場合キーでソートし、それ以外の場合は値でソートします。
     * @param order       ソートの順序を指定します。"ASC"の場合昇順で、それ以外の場合は降順でソートします。
     * @return フォーマットされた文字列を返します。
     */
    public static String getBrandCodesStrByList(Map<String, String> entryKeyMap, String sortBy, String order) {
        if (entryKeyMap == null || entryKeyMap.isEmpty()) {
            return "";
        }

        List<Map.Entry<String, String>> brandCodesList = new ArrayList<>(entryKeyMap.entrySet());

        boolean isSortByKey = "KEY".equals(sortBy);
        boolean isAscending = "ASC".equals(order);

        // 使用 Stream 进行排序
        List<String> sortedList = brandCodesList.stream()
            .sorted((e1, e2) -> {
                int compareResult;
                if (isSortByKey) {
                    compareResult = e1.getKey().compareTo(e2.getKey());
                } else {
                    compareResult = e1.getValue().compareTo(e2.getValue());
                }
                return isAscending ? compareResult : -compareResult;
            })
            .map(entry -> isSortByKey ? entry.getKey() : entry.getValue())
            .collect(Collectors.toList());

        return String.join("、", sortedList);
    }

}
