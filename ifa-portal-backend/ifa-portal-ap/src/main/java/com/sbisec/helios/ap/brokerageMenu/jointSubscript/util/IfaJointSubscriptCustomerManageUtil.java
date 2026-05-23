package com.sbisec.helios.ap.brokerageMenu.jointSubscript.util;

import java.net.InetAddress;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.IfaJointSubscriptCustomerManageDao;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql010RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql011RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql012RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageCommModel;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

/**
 * 共通部品
 * 共同募集 顧客管理用
 * 
 * @author 大連 王永宝
 */
@Component
public class IfaJointSubscriptCustomerManageUtil {

    /** 検索制限の最大件数.5000 */
    private final int QUERY_SIZE_LIMIT = 5000;

    /** 画面IDの列挙型 */
    public enum ScreenId {
        /** 画面ID.SUB0206_01-01 */
        SUB0206_01_01("SUB0206_01-01"),
        /** 画面ID.SUB0206_01-02 */
        SUB0206_01_02("SUB0206_01-03"),
        /** 画面ID.SUB0206_01-02 */
        SUB0206_01_03("SUB0206_01-03");

        public String key;
        ScreenId(String key) {this.key = key;}
    }

    /** DB処理結果の列挙型 */
    public enum DBexecRtn {
        /** 排他発生 */
        EXCLUSIVE("ec"),
        /** 正常終了 */
        SUCCESS("ok"),
        /** 処理エラー */
        FAILED("ng");

        public String key;
        DBexecRtn(String key) {this.key = key;}
    }

    /** エラーメッセージの列挙型 */
    public enum Message {
        /** インフォメッセージ： {0}を完了しました。 */
        INFO_ENDCOMPLETED("info.endCompleted"),

        /** ワーニングメッセージ： 検索結果が{0}件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。 */
        WARNINGS_DATALIST_OVERMAXROWNUM("warnings.dataList.overMaxRownum"),
        /** ワーニングメッセージ： 検索結果が{0}件を超過しています（{1}件ヒット）。\n{2}件のcsv出力を行います。 */
        WARNINGS_DATALIST_CSV_OVERMAXROWNUM("warnings.dataList.csv.overMaxRownum"),

        /** エラーメッセージ： 検索結果が0件です。\n条件を設定して再度検索して下さい。 */
        ERRORS_DATALIST_NOTFOUND("errors.dataList.notfound"),
        /** エラーメッセージ： {0}処理の権限を持っていないです。 */
        ERRORS_ACTION_PERMISSION_DENIED("errors.action.permission.denied"),
        /** エラーメッセージ: データを{0}選択して下さい。 */
        ERRORS_JOINT_DATA_SELECTION_REQUIRED("errors.joint.data.selection.required"),
        /** エラーメッセージ: 手続状況が承認のものが選択されています。 */
        ERRORS_JOINT_DATA_SELECTION_APPROVED("errors.joint.data.selection.approved"),
        /** エラーメッセージ: 他のユーザーは当該{0}を操作しました。ご確認ください。 */
        ERRORS_EXCLUSIVE("errors.exclusive"),
        /** エラーメッセージ: {0}が失敗しました。 */
        ERRORS_PROCESSINGFAILED("errors.processingFailed"),

        /** エラーメッセージ: {0}には当日以前の日付を入力してください。 */
        ERRORS_DATE_SPECIFYBEFORETODAY("errors.date.specifyBeforeToday"),
        /** エラーメッセージ: 終了日は同意日より後の日付で入力してください。 */
        ERRORS_JOINT_DATE_ENDBEFORESTART("errors.joint.date.endBeforeStart"),
        /** エラーメッセージ: {0}は{1}を超えます。ご確認ください。 */
        ERRORS_IPOOVERLIMIT("errors.ipoOverLimit"),
        /** エラーメッセージ: 入力した共募顧客情報は既に登録済みです。ご確認ください。<br>部店:[{0}] 口座番号:[{1}] */
        ERRORS_JOINT_DATA_EXISTS("errors.joint.data.exists"),
        /** エラーメッセージ: 入力した共募支店コードは存在しません。<br>共募支店コード: [{0}] */
        ERRORS_JOINT_DATA_BRANCH_NOTEXIST("errors.joint.data.branch.notExist"),
        /** エラーメッセージ: 入力した部店口座に対す仲介業情報は存在しません。<br>部店:[{0}] 口座番号:[{1}] */
        ERRORS_JOINT_DATA_BROKER_NOTEXIST("errors.joint.data.broker.notExist"),
        /** エラーメッセージ: 入力した部店、口座番号の顧客は存在しません。ご確認ください。<br>部店:[{0}] 口座番号:[{1}] */
        ERRORS_JOINT_USER_NOTEXISTS("errors.joint.user.notExists"),
        /** エラーメッセージ: 入力した部店、口座番号は共同募集の契約先仲介業者の顧客ではありません。ご確認ください。<br>部店:[{0}] 口座番号:[{1}] */
        ERRORS_JOINT_BROKER_NOTEXISTS("errors.joint.broker.notExists"),
        /** メッセージパラメータ： 新規登録 */
        MSG_PARAMETER_REGISTER("新規登録"),
        /** メッセージパラメータ： 更新 */
        MSG_PARAMETER_CORRECT("更新"),
        /** メッセージパラメータ： 承認 */
        MSG_PARAMETER_APPROVE("承認"),
        /** メッセージパラメータ： 削除 */
        MSG_PARAMETER_DELETE("削除"),

        /** メッセージパラメータ： 契約締結日 */
        MSG_PARAMETER_CONTRACT_DATE("契約締結日"),
        /** メッセージパラメータ： 同意日 */
        MSG_PARAMETER_START_DATE("同意日"),
        /** メッセージパラメータ： 終了日 */
        MSG_PARAMETER_END_DATE("終了日"),
        /** メッセージパラメータ： 支払率 */
        MSG_PARAMETER_JOINT_REWARD_RATE("支払率"),
        /** メッセージパラメータ： 支払率の上限 99.9999999 */
        MSG_PARAMETER_JOINT_REWARD_RATE_MAX("99.9999999"),
        /** メッセージパラメータ： 共募仲介業者コード */
        MSG_PARAMETER_JOINT_BROKERCODE("共募仲介業者コード"),
        /** メッセージパラメータ： 共募支店コード */
        MSG_PARAMETER_JOINT_BRANCHCODE("共募支店コード");

        public String key;
        Message(String key) {this.key = key;}
    }

    /** DBのSYS_○○フィールドのデフォルト値の列挙型 */
    public enum SysFieldDefalut {
        /** トリガーID */
        SYS_TRIGGER("SUB0206_01"),
        /** 端末ID */
        SYS_MACHINEID("sec-helios");

        public String key;
        SysFieldDefalut(String key) {this.key = key;}
    }

    /** 共同募集顧客.手続状況の列挙型 */
    public enum EditStats {
        /** 新規申請 */
        REGISTER("0"),
        /** 修正申請 */
        CORRECT("1"),
        /** 承認 */
        APPROVE("2"),
        /** 削除 */
        DELETE("3");

        public String key;
        EditStats(String key) {this.key = key;}
    }

    /** コードテーブル用定数の列挙型 */
    public enum MCodeArgs {
        /** コードテーブル.種別.画面コメント */
        M_CODE_CODE_TYPE_99("99"),
        /** コードテーブル.CODE_1.01 */
        M_CODE_CODE_1_01("01"),
        /** コードテーブル.CODE_2.08 */
        M_CODE_CODE_2_08("08");

        public String key;
        MCodeArgs(String key) {this.key = key;}
    }

    /** 仲介業者コード */
    public enum BrokerCode {
        /** SBIマネープラザ */
        MONEYPLAZA("0509");

        public String key;
        BrokerCode(String key) {this.key = key;}
    }

    public int getQuerySizeLimit() {
        return this.QUERY_SIZE_LIMIT;
    }

    /**
     * 業務チェック: ユーザ共通情報.権限コードは本店、支店、仲介業者内管 (1~31)権限以外の場合、エラーコードとエラーメッセージを戻す
     * 
     * @param x_privId ユーザ共通情報.権限コード
     * @param x_execName 処理名(Message.MSG_PARAMETER_EXEC_○○の値を参照)
     * @return String[] エラーがあれば、[エラーコード, エラーメッセージ]の配列を戻す、エラーがなければ、nullを戻す
     */
    public String[] checkPrivId(String x_privId, String x_execName) {
        // 戻り値初期化
        String[] p_rtn = null;

        // 引数がnullまたは空文字列である場合、エラーなしを返す
        if (StringUtil.isNullOrEmpty(x_privId) || StringUtil.isNullOrEmpty(x_execName)) {
            return p_rtn;
        }

        // ユーザ共通情報.権限コードは本店、支店、仲介業者内管 (1~3)権限以外の場合、エラーコードとエラーメッセージを戻す
        Set<String> p_validIds = new HashSet<>(Arrays.asList("1", "2", "3"));
        if (!StringUtil.isNullOrEmpty(x_privId) && !p_validIds.contains(x_privId)) {
            p_rtn = new String[2];
            p_rtn[0] = Message.ERRORS_ACTION_PERMISSION_DENIED.key;
            p_rtn[1] = IfaCommonUtil.getMessage(Message.ERRORS_ACTION_PERMISSION_DENIED.key,
                    new String[] {x_execName});
        } 
        return p_rtn;
    }

    /**
     * 業務チェック: 契約締結日は未来日付の場合、エラーコードとエラーメッセージを戻す
     * 
     * @param x_contractDate 契約締結日
     * @return String[] エラーがあれば、[エラーコード, エラーメッセージ]の配列を戻す、エラーがなければ、nullを戻す
     */
    public String[] checkContractDate(String x_contractDate) {
        // 戻り値初期化
        String[] p_rtn = null;

        // 契約締結日がnullまたは空文字列である場合、エラーなしを返す
        if (StringUtil.isNullOrEmpty(x_contractDate)) {
            return p_rtn;
        }
        // 契約締結日は変換に失敗した場合、エラーなしを返す
        LocalDate p_contractDate;
        try {
            // 契約締結日をLocalDateに変換
            p_contractDate = LocalDate.parse(x_contractDate.replace("/", ""), DateTimeFormatter.ofPattern("yyyyMMdd"));
        } catch (Exception e) {
            return p_rtn;
        }

        // 契約締結日は未来日付の場合、エラーコードとエラーメッセージを戻す
        if (p_contractDate.isAfter(LocalDate.now())) {
            p_rtn = new String[2];
            p_rtn[0] = Message.ERRORS_DATE_SPECIFYBEFORETODAY.key;
            p_rtn[1] = IfaCommonUtil.getMessage(Message.ERRORS_DATE_SPECIFYBEFORETODAY.key,
                    new String[] {Message.MSG_PARAMETER_CONTRACT_DATE.key});
        }
        return p_rtn;
    }

    /**
     * 業務チェック: 同意日は未来日付の場合、エラーコードとエラーメッセージを戻す
     * 
     * @param x_startDate 同意日
     * @return String[] エラーがあれば、[エラーコード, エラーメッセージ]の配列を戻す、エラーがなければ、nullを戻す
     */
    public String[] checkStartDate(String x_startDate) {
        // 戻り値初期化
        String[] p_rtn = null;

        // 同意日がnullまたは空文字列である場合、エラーなしを返す
        if (StringUtil.isNullOrEmpty(x_startDate)) {
            return p_rtn;
        }
        // 同意日は変換に失敗した場合、エラーなしを返す
        LocalDate p_startDate;
        try {
            // 同意日をLocalDateに変換
            p_startDate = LocalDate.parse(x_startDate.replace("/", ""), DateTimeFormatter.ofPattern("yyyyMMdd"));
        } catch (Exception e) {
            return p_rtn;
        }

        // 同意日は未来日付の場合、エラーコードとエラーメッセージを戻す
        if (p_startDate.isAfter(LocalDate.now())) {
            p_rtn = new String[2];
            p_rtn[0] = Message.ERRORS_DATE_SPECIFYBEFORETODAY.key;
            p_rtn[1] = IfaCommonUtil.getMessage(Message.ERRORS_DATE_SPECIFYBEFORETODAY.key,
                    new String[] {Message.MSG_PARAMETER_START_DATE.key});
        }
        return p_rtn;
    }

    /**
     * 業務チェック: 終了日が同意日より前の日付の場合、エラーコードとエラーメッセージを戻す
     * 
     * @param x_startDate 同意日
     * @param x_endDate 終了日
     * @return String[] エラーがあれば、[エラーコード, エラーメッセージ]の配列を戻す、エラーがなければ、nullを戻す
     */
    public String[] checkEndDate(String x_startDate, String x_endDate) {
        // 戻り値初期化
        String[] p_rtn = null;

        // 引数がnullまたは空文字列である場合、エラーなしを返す
        if (StringUtil.isNullOrEmpty(x_startDate) || StringUtil.isNullOrEmpty(x_endDate)) {
            return p_rtn;
        }
        // 同意日は変換に失敗した場合、エラーなしを返す
        LocalDate p_startDate;
        try {
            // 同意日をLocalDateに変換
            p_startDate = LocalDate.parse(x_startDate.replace("/", ""), DateTimeFormatter.ofPattern("yyyyMMdd"));
        } catch (Exception e) {
            return p_rtn;
        }
        // 終了日は変換に失敗した場合、エラーなしを返す
        LocalDate p_endDate;
        try {
            // 同意日をLocalDateに変換
            p_endDate = LocalDate.parse(x_endDate.replace("/", ""), DateTimeFormatter.ofPattern("yyyyMMdd"));
        } catch (Exception e) {
            return p_rtn;
        }

        // 終了日が同意日より前の日付の場合、エラーコードとエラーメッセージを戻す
        if (!p_endDate.isAfter(p_startDate)) {
            p_rtn = new String[2];
            p_rtn[0] = Message.ERRORS_JOINT_DATE_ENDBEFORESTART.key;
            p_rtn[1] = IfaCommonUtil.getMessage(Message.ERRORS_JOINT_DATE_ENDBEFORESTART.key);
        }
        return p_rtn;
    }

    /**
     * 業務チェック: 支払率が99.9999999を超える場合、エラーコードとエラーメッセージを戻す
     * 
     * @param x_jointRewardRate 支払率
     * @return String[] エラーがあれば、[エラーコード, エラーメッセージ]の配列を戻す、エラーがなければ、nullを戻す
     */
    public String[] checkJointRewardRate(String x_jointRewardRate) {
        // 戻り値初期化
        String[] p_rtn = null;

        // 支払率がnullまたは空文字列である場合、エラーなしを返す
        if (StringUtil.isNullOrEmpty(x_jointRewardRate)) {
            return p_rtn;
        }
        // 支払率が数値以外である場合、エラーなしを返す
        double p_rewardRate = 0;
        try {
            p_rewardRate = Double.valueOf(x_jointRewardRate);
        } catch (Exception e) {
            return p_rtn;
        }

        // 支払率が99.9999999を超える場合、エラーコードとエラーメッセージを戻す
        if (p_rewardRate > Double.valueOf(Message.MSG_PARAMETER_JOINT_REWARD_RATE_MAX.key)) {
            p_rtn = new String[2];
            p_rtn[0] = Message.ERRORS_IPOOVERLIMIT.key;
            p_rtn[1] = IfaCommonUtil.getMessage(Message.ERRORS_IPOOVERLIMIT.key,
                    new String[] {Message.MSG_PARAMETER_JOINT_REWARD_RATE.key, Message.MSG_PARAMETER_JOINT_REWARD_RATE_MAX.key});
        }
        return p_rtn;
    }

    /**
     * 業務チェック: 入力した新しい共同募集顧客データが既に存在する場合、エラーコードとエラーメッセージを戻す
     * 
     * @param x_dao DB処理のインターフェース
     * @param x_butenCode 部店コード
     * @param x_accountNumber 口座番号
     * @return String[] エラーがあれば、[エラーコード, エラーメッセージ]の配列を戻す、エラーがなければ、nullを戻す
     * @throws Exception 
     */
    public String[] checkCustomerExistence(IfaJointSubscriptCustomerManageDao x_dao,
            String x_butenCode, String x_accountNumber) throws Exception {
        // 戻り値初期化
        String[] p_rtn = null;

        // nullまたは空文字列である場合、エラーなしを返す
        if (StringUtil.isNullOrEmpty(x_butenCode)
                || StringUtil.isNullOrEmpty(x_accountNumber)) {
            return p_rtn;
        }

        // 入力した新しい共同募集顧客データが既に存在する場合、エラーコードとエラーメッセージを戻す
        IfaJointSubscriptCustomerManageSql008RequestModel p_sql008Req = new IfaJointSubscriptCustomerManageSql008RequestModel();
        p_sql008Req.setButenCode(x_butenCode);
        p_sql008Req.setAccountNumber(x_accountNumber);
        if (x_dao.selectIfaJointSubscriptCustomerManageSql008(p_sql008Req) >= 1) {
            p_rtn = new String[2];
            p_rtn[0] = Message.ERRORS_JOINT_DATA_EXISTS.key;
            p_rtn[1] = IfaCommonUtil.getMessage(Message.ERRORS_JOINT_DATA_EXISTS.key,
                    new String[] {x_butenCode, x_accountNumber});
        }
        return p_rtn;
    }

    /**
     * 業務チェック: 部店、口座番号の存在チェックを行う。
     * @param x_dao DB処理のインターフェース
     * @param x_butenCode 部店コード
     * @param x_accountNumber 口座番号
     * @return String[] エラーがあれば、[エラーコード, エラーメッセージ]の配列を戻す、エラーがなければ、nullを戻す
     * @throws Exception
     * */
    public String[] checkButenAndAccountExistence(IfaJointSubscriptCustomerManageDao x_dao,
                                                   String x_butenCode, String x_accountNumber) throws Exception {
        // 戻り値初期化
        String[] p_rtn = null;

        // nullまたは空文字列である場合、エラーなしを返す
        if (StringUtil.isNullOrEmpty(x_butenCode)
                || StringUtil.isNullOrEmpty(x_accountNumber)) {
            return p_rtn;
        }

        //IFA顧客属性テーブルに、指定の部店、口座番号が存在するか確認する。
        IfaJointSubscriptCustomerManageSql011RequestModel p_sql011Req = new IfaJointSubscriptCustomerManageSql011RequestModel();
        p_sql011Req.setButenCode(x_butenCode);
        p_sql011Req.setAccountNumber(x_accountNumber);
        if (x_dao.selectIfaJointSubscriptCustomerManageSql011(p_sql011Req) == 0) {
            p_rtn = new String[2];
            p_rtn[0] = Message.ERRORS_JOINT_USER_NOTEXISTS.key;
            p_rtn[1] = IfaCommonUtil.getMessage(Message.ERRORS_JOINT_USER_NOTEXISTS.key,
                    new String[] {x_butenCode, x_accountNumber});
        }

        return p_rtn;
    }

    /**
     * 業務チェック: 契約先仲介業者のチェックを行う
     * @param x_dao DB処理のインターフェース
     * @param x_butenCode 部店コード
     * @param x_accountNumber 口座番号
     * @return String[] エラーがあれば、[エラーコード, エラーメッセージ]の配列を戻す、エラーがなければ、nullを戻す
     * @throws Exception
     * */
    public String[] checkBrokerExistence(IfaJointSubscriptCustomerManageDao x_dao,
                                                  String x_butenCode, String x_accountNumber) throws Exception {
        // 戻り値初期化
        String[] p_rtn = null;

        // nullまたは空文字列である場合、エラーなしを返す
        if (StringUtil.isNullOrEmpty(x_butenCode)
                || StringUtil.isNullOrEmpty(x_accountNumber)) {
            return p_rtn;
        }

        //IFA顧客属性テーブルに、指定の部店、口座番号が契約先仲介業者に紐づいているか確認する
        IfaJointSubscriptCustomerManageSql012RequestModel p_sql012Req = new IfaJointSubscriptCustomerManageSql012RequestModel();
        p_sql012Req.setButenCode(x_butenCode);
        p_sql012Req.setAccountNumber(x_accountNumber);
        if (x_dao.selectIfaJointSubscriptCustomerManageSql012(p_sql012Req) == 0){
            p_rtn = new String[2];
            p_rtn[0] = Message.ERRORS_JOINT_BROKER_NOTEXISTS.key;
            p_rtn[1] = IfaCommonUtil.getMessage(Message.ERRORS_JOINT_BROKER_NOTEXISTS.key,
                    new String[] {x_butenCode, x_accountNumber});
        }

        return p_rtn;
    }

    /**
     * DBの共通処理部
     * 
     * @param x_dao DB処理のインターフェース
     * @param x_model 共同募集 顧客管理のDB処理用モデル
     * @return DBexecRtn(DB処理結果の列挙体)を戻す
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
    public String execDBcommonLogic(IfaJointSubscriptCustomerManageDao x_dao,
            IfaJointSubscriptCustomerManageCommModel x_model) 
                    throws IfaJointSubscriptCustomerException, Exception {

        // 1.共同募集顧客情報を取得する
        IfaJointSubscriptCustomerManageSql003RequestModel p_sql003Req =
                new IfaJointSubscriptCustomerManageSql003RequestModel();
        BeanUtils.copyProperties(p_sql003Req, x_model);
        // SQL実行
        DataList<IfaJointSubscriptCustomerManageSql003ResponseModel> p_sql003Res = 
                x_dao.selectIfaJointSubscriptCustomerManageSql003(p_sql003Req);
        // SQLのデータが取得あり時
        if (!CollectionUtils.isEmpty(p_sql003Res.getDataList())) {
            IfaJointSubscriptCustomerManageSql004RequestModel p_sql004Req = null;
            for (IfaJointSubscriptCustomerManageSql003ResponseModel p_sql003Dto : p_sql003Res.getDataList()) {
                // 2.(部店コードが空であるか、口座番号が空であるか、編集履歴号が空であるか、同意日が空であるか、同意日が8桁ではない)時、エラーを戻す
                if (StringUtil.isNullOrEmpty(p_sql003Dto.getStartDate())
                            || p_sql003Dto.getStartDate().length() != 8
                            || StringUtil.isNullOrEmpty(p_sql003Dto.getButenCode())
                            || StringUtil.isNullOrEmpty(p_sql003Dto.getAccountNumber())
                            || StringUtil.isNullOrEmpty(p_sql003Dto.getEditNumber())
                    ) {
                    throw new IfaJointSubscriptCustomerException(DBexecRtn.FAILED.key);
                }
                // 3.取得したデータにより、顧客情報の編集履歴を更新する
                p_sql004Req = new IfaJointSubscriptCustomerManageSql004RequestModel();
                BeanUtils.copyProperties(p_sql004Req, p_sql003Dto);
                // 更新結果が1件以外時、エラーを戻す
                if (x_dao.updateIfaJointSubscriptCustomerManageSql004(p_sql004Req) != 1) {
                    throw new IfaJointSubscriptCustomerException(DBexecRtn.FAILED.key);
                }
            }
        }

        // ユーザアカウント情報取得
        UserAccount p_userAccount = IfaCommonUtil.getUserAccount();
        // 4.新しい顧客情報を共同募集顧客マスタに新規登録する        
        IfaJointSubscriptCustomerManageSql005RequestModel p_sql005Req =
                new IfaJointSubscriptCustomerManageSql005RequestModel();
        BeanUtils.copyProperties(p_sql005Req, x_model);
        p_sql005Req.setLoginId(p_userAccount.getUserId());             // ログインユーザ
        p_sql005Req.setLoginName(p_userAccount.getUserNm());           // ログイン名
        p_sql005Req.setSysMachineId(getHostName());                    // 端末ID
        p_sql005Req.setSysTrigger(SysFieldDefalut.SYS_TRIGGER.key);    // トリガーID
        // 処理はエラーがあれば、エラーメッセージを戻す
        if (x_dao.insertIfaJointSubscriptCustomerManageSql005(p_sql005Req) != 1) {
            throw new IfaJointSubscriptCustomerException(DBexecRtn.FAILED.key);
        }

        //新しい共同募集顧客情報を共同募集顧客仲介業者情報に新規登録する。
        String editStatus = x_model.getEditStatus();
        if(Set.of(EditStats.REGISTER.key,EditStats.CORRECT.key).contains(editStatus)){
            IfaJointSubscriptCustomerManageSql010RequestModel p_sql010Req =
                    new IfaJointSubscriptCustomerManageSql010RequestModel();
            BeanUtils.copyProperties(p_sql010Req, x_model);
            //更新理由
            p_sql010Req.setReasonCode("0"); //0：未設定
            if (x_dao.insertIfaJointSubscriptCustomerManageSql010(p_sql010Req) != 1){
                throw new IfaJointSubscriptCustomerException(DBexecRtn.FAILED.key);
            }
        }

        return DBexecRtn.SUCCESS.key;
    }

    /**
     * 仲介業者支店名を取得する (共募仲介業名又は共募支店名のセット用)
     * @param x_dao DB処理のインターフェース
     * @param x_jointBranchCode 共募支店コード
     * @return 取得できない時、nullを戻す
     */
    public String getBranchName(IfaJointSubscriptCustomerManageDao x_dao, String x_jointBranchCode) {
        // 検索条件をセットする
        IfaJointSubscriptCustomerManageSql007RequestModel p_sql007Req = new IfaJointSubscriptCustomerManageSql007RequestModel();
        p_sql007Req.setJointBranchCode(x_jointBranchCode);       // 共募支店コード
        p_sql007Req.setBroker0509(BrokerCode.MONEYPLAZA.key);    // SBIマネープラザ
        // SQL007を実行する
        DataList<IfaJointSubscriptCustomerManageSql007ResponseModel> p_sql007Res = null;
        // 取得できない時、nullを戻す
        try {
            p_sql007Res = x_dao.selectIfaJointSubscriptCustomerManageSql007(p_sql007Req);
        } catch (Exception e) {
            return null;
        }
        if (CollectionUtils.isEmpty(p_sql007Res.getDataList())) {
            return null;
        }
        // 取得した仲介業者支店名を戻す
        IfaJointSubscriptCustomerManageSql007ResponseModel p_sql007res = p_sql007Res.getDataList().get(0);
        if (p_sql007res != null) {
            return p_sql007res.getBranchName();
        }
        return StringUtil.EMPTY_STRING;
    }

    /**
     * 仲介業者コードと名を取得する
     * @param x_dao DB処理のインターフェース
     * @param x_butenCode 部店コード
     * @param x_accountNumer 口座番号
     * @return String[] 取得できる時、[仲介業者コード, 仲介業者名]の配列を戻す、取得できない時、nullを戻す
     */
    public String[] getBrokerCodeAndName (IfaJointSubscriptCustomerManageDao x_dao,
            String x_butenCode, String x_accountNumer) {
        // 検索条件をセットする
        IfaJointSubscriptCustomerManageSql006RequestModel p_sql006Req = new IfaJointSubscriptCustomerManageSql006RequestModel();
        p_sql006Req.setButenCode(x_butenCode);           // 部店コード
        p_sql006Req.setAccountNumber(x_accountNumer);    // 口座番号
        // SQL006を実行する
        DataList<IfaJointSubscriptCustomerManageSql006ResponseModel> p_sql006Res = null;
        // 取得できない時、nullを戻す
        try {
            p_sql006Res = x_dao.selectIfaJointSubscriptCustomerManageSql006(p_sql006Req);
        } catch (Exception e) {
            return null;
        }
        if (CollectionUtils.isEmpty(p_sql006Res.getDataList())) {
            return null;
        }
        // [仲介業者コード, 仲介業者名]の配列を戻す
        IfaJointSubscriptCustomerManageSql006ResponseModel p_sql006res = p_sql006Res.getDataList().get(0);
        String[] p_rtn = {StringUtil.EMPTY_STRING, StringUtil.EMPTY_STRING};
        if (p_sql006res != null) {
            p_rtn[0] = p_sql006res.getBrokerCode();
            p_rtn[1] = p_sql006res.getBrokerName();
        }
        return p_rtn;
    }

    /**
     * サービスの戻るレスポンスDataListを作成する
     * @param x_dbResult DB処理結果
     * @param x_exeKbn 処理区分 Message.MSG_PARAMETER_XXXX利用
     * @return DataList<T> 
     */
    public <T> DataList<T> creatDataList(String x_dbResult, String x_exeKbn) {
        // エラーがあれば、対するエラーメッセージを戻す
        if (IfaJointSubscriptCustomerManageUtil.DBexecRtn.EXCLUSIVE.key.equals(x_dbResult)) {
            // 排他発生
            return IfaCommonUtil.createDataList(
                        Collections.emptyList(), 
                        ErrorLevel.FATAL, 
                        Message.ERRORS_EXCLUSIVE.key,
                        IfaCommonUtil.getMessage(Message.ERRORS_EXCLUSIVE.key, 
                                new String[] {x_exeKbn})
                    );
        }
        if (IfaJointSubscriptCustomerManageUtil.DBexecRtn.FAILED.key.equals(x_dbResult)) {
            // 処理エラー
            return IfaCommonUtil.createDataList(
                        Collections.emptyList(), 
                        ErrorLevel.FATAL, 
                        Message.ERRORS_PROCESSINGFAILED.key,
                        IfaCommonUtil.getMessage(Message.ERRORS_PROCESSINGFAILED.key,
                                new String[] {x_exeKbn})
                    );
        }
        // エラーがなければ、正常を戻す
        return IfaCommonUtil.createDataList(
                    Collections.emptyList(),
                    ErrorLevel.SUCCESS, 
                    Message.INFO_ENDCOMPLETED.key,
                    IfaCommonUtil.getMessage(Message.INFO_ENDCOMPLETED.key,
                            new String[] {x_exeKbn})
                );
    }

    /**
     * ホスト名を取得する
     * 
     * @return String ホスト名。取得できない場合は "sec-helios" を返る
     */
    private String getHostName() {
        try {
            // ローカルホストのホスト名を取得する
            String p_sysMachineId = InetAddress.getLocalHost().getHostName();
            // ホスト名が20文字を超える場合は、先頭20文字だけを取得する。
            if (p_sysMachineId.length() > 20) {
                p_sysMachineId = p_sysMachineId.substring(0, 20);
            }
            return p_sysMachineId;
        } catch (Exception e) {
            // ホスト名を取得できない場合は "sec-helios" を返る。
            return SysFieldDefalut.SYS_MACHINEID.key;
        }
    }

}
