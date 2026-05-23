package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.mail.MailProperties;
import com.sbibits.earth.util.mail.SendMail;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.IfaInfoNewRegisterDao;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoNewRegisterSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoNewRegisterSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoNewRegisterSql004RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoNewRegisterSql005RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoNewRegisterSql007RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoNewRegisterSql008RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoNewRegisterSql008ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoNewRegisterA001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoNewRegisterA001ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoNewRegisterA008aRequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoNewRegisterA008bRequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoNewRegisterA008bResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.service.IfaInfoNewRegisterService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 画面ID：SUB0501_01-02_1
 * 画面名：情報新規登録
 *
 * @author SCSK
 2024/05/17 新規作成
 */
@Component(value = "cmpIfaInfoNewRegisterService")
public class IfaInfoNewRegisterServiceImpl implements IfaInfoNewRegisterService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaInfoNewRegisterServiceImpl.class);
    
    /** デフォルトカテゴリ */
    private static final IfaInfoNewRegisterA001ResponseDto.NewNotificationCategory DEFAULT_CATEGORY = new IfaInfoNewRegisterA001ResponseDto.NewNotificationCategory(
            "-1", "", "1");
    
    /** アップロード先 機能ID */
    private static final String FUNC_ID = "0";
    
    /** アップロード先 カテゴリID */
    private static final String CAT_ID = "0";
    
    /** 閲覧設定 ALL */
    private static final String VIEWERS_ALL = "1";
    
    /** 閲覧設定 権限 */
    private static final String VIEWERS_PRIV = "2";
    
    /** 閲覧設定 担当者 */
    private static final String VIEWERS_INDIVIDUAL = "3";
    
    /** メール本文 */
    private static final String MAILBODY = "%s\n" //
            + "%s　様\n" //
            + "\n" //
            + "平素はたいへんお世話になっております。\n" //
            + "IFAポータルお知らせに新着情報をアップロードいたしましたので、\n" //
            + "ご確認くださいますようお願い申し上げます。\n" //
            + "\n" //
            + "%s\n" //
            + "\n" //
            + "\n" //
            + "株式会社SBI証券\n";
    
    /** メッセージID:メール送信失敗エラー */
    private static final String WARNINGS_CMN_SENDMAIL_FAILED = "warnings.cmn.sendMail.failed";

    /** メッセージID:送信元メールアドレス未設定エラー */
    private static final String WARNINGS_CMN_MAILADRESS_UNREGISTERD = "warnings.cmn.mailAdress.unregistered";  

    @Autowired
    private IfaInfoNewRegisterDao dao;
    
    /**
     * アクションID：A008b
     * アクション名：登録
     * Dto リクエスト：IfaInfoNewRegisterA008bRequestDto
     * Dto レスポンス：IfaInfoNewRegisterA008bResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Transactional(rollbackFor = Throwable.class)
    @Override
    public DataList<IfaInfoNewRegisterA008bResponseDto> registerA008b(IfaInfoNewRegisterA008bRequestDto dtoReq)
            throws Exception {
        
        var userId = IfaCommonUtil.getUserAccount().getUserId();
        insertInformation(userId, dtoReq);
        
        if (StringUtils.equals(dtoReq.getViewerSetting(), VIEWERS_ALL)) { // 参照範囲が全担当の場合
            // 参照権限に仲介業者権限コードを登録する
            dao.insertIfaInfoNewRegisterSql003(
                    new IfaInfoNewRegisterSql003RequestModel(userId, dtoReq.getNotificationReferenceAuthorityList()));
            
        } else if (StringUtils.equals(dtoReq.getViewerSetting(), VIEWERS_PRIV)) { // 参照範囲が権限担当の場合
            // 参照権限に仲介業者権限コードを登録する
            dao.insertIfaInfoNewRegisterSql003(
                    new IfaInfoNewRegisterSql003RequestModel(userId, dtoReq.getNotificationReferenceAuthorityList()));
            
        } else if (StringUtils.equals(dtoReq.getViewerSetting(), VIEWERS_INDIVIDUAL)) { // 参照範囲が個別担当の場合
            // 既読にログインIDを登録する。
            dao.insertIfaInfoNewRegisterSql004(
                    new IfaInfoNewRegisterSql004RequestModel(userId, dtoReq.getIndividualRepList()));
        }

        // メール送信
        DataList<IfaInfoNewRegisterA008bResponseDto> dtoRes = new DataList<IfaInfoNewRegisterA008bResponseDto>();
        if (StringUtils.equals(dtoReq.getSubjectSendFlag(), "1")) {
            dtoRes = sendMail(userId, dtoReq);
            // メール送信時のエラー
            if (dtoRes != null) {
                return dtoRes;
            }
        }

        return IfaCommonUtil.createDataList(List.of(new IfaInfoNewRegisterA008bResponseDto()), ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.toString(), null);
    }
    
    /**
     * メールを送信する
     *
     * @param userId ユーザーID
     * @param dtoReq リクエストDTO
     * @throws Exception システムエラー
     */
    private DataList<IfaInfoNewRegisterA008bResponseDto> sendMail(String userId, IfaInfoNewRegisterA008bRequestDto dtoReq) throws Exception {
        
        // 送信元情報を取得
        var sql007res = dao.selectIfaInfoNewRegisterSql007(new IfaInfoNewRegisterSql007RequestModel(userId))//
                .getDataList().stream()//
                .filter(res -> StringUtils.isNotBlank(res.getMailAddress())) //
                .findFirst()//
                .orElse(null);
        
        // SQL007.メールアドレスが未設定の場合、エラーメッセージを出力
        if (sql007res == null) {
            return IfaCommonUtil.createDataList(List.of(new IfaInfoNewRegisterA008bResponseDto()),
                    ErrorLevel.WARNING,
                    WARNINGS_CMN_MAILADRESS_UNREGISTERD,
                    IfaCommonUtil.getMessage(WARNINGS_CMN_MAILADRESS_UNREGISTERD));
        }

        // 宛先リストを取得
        var sql005resList = dao
                .selectIfaInfoNewRegisterSql005(new IfaInfoNewRegisterSql005RequestModel(dtoReq.getViewerSetting(),
                        dtoReq.getNotificationReferenceAuthorityList(), dtoReq.getIndividualRepList())) //
                .getDataList().stream()//
                .filter(res -> StringUtils.isNotBlank(res.getMailAddress())) //
                .collect(Collectors.toList());
        
        // エラー担当者（SQL005.メールアドレスが存在しない担当者は含まない）
        List<String> errorMailList = new ArrayList<String>();
        var errors = 0;
        
        for (var sql005res : sql005resList) {
            var prop = new MailProperties();
            prop.setFrom(sql007res.getMailAddress());
            prop.setSubject(dtoReq.getSubject());
            prop.setTo(sql005res.getMailAddress());
            prop.setText(String.format(MAILBODY, StringUtil.nullToEmpty(sql005res.getBranchName()), sql005res.getUserNm(), dtoReq.getTitle()));
            try {
                if (!SendMail.sendMail(prop)) {
                    errorMailList.add("氏名:" + sql005res.getUserNm() + ";E-mail:" + sql005res.getMailAddress());
                    LOGGER.debug("sendMail failed.to={}", Arrays.toString(prop.getTo()));
                    ++errors;
                }
            } catch (Exception e) {
                errorMailList.add("氏名:" + sql005res.getUserNm() + ";E-mail:" + sql005res.getMailAddress());
                LOGGER.debug(String.format("sendMail Exception.to=%s", Arrays.toString(prop.getTo())), e);
                ++errors;
            }
        }
        LOGGER.info("sendMail finished.total={},errors={}", sql005resList.size(), errors);

        // 送信失敗　又は　送信異常の場合、エラーメッセージを出力
        if (!CollectionUtils.isEmpty(errorMailList)) {
            String categoryNames = dtoReq.getCategoryIdList().stream()
                                    .map(c -> c.getCategoryName())
                                    .collect(Collectors.joining(","));
                                    
            return IfaCommonUtil.createDataList(List.of(new IfaInfoNewRegisterA008bResponseDto()),
                ErrorLevel.WARNING,
                WARNINGS_CMN_SENDMAIL_FAILED,
                IfaCommonUtil.getMessage(WARNINGS_CMN_SENDMAIL_FAILED,
                    new String[] { categoryNames, String.join("\n", errorMailList) }));
        }

        // 正常終了
        return null;
    }

    /**
     * お知らせ登録を行う
     *
     * @param userId ユーザーID
     * @param dtoReq リクエストDTO
     * @throws Exception システムエラー
     */
    private void insertInformation(String userId, IfaInfoNewRegisterA008bRequestDto dtoReq) throws Exception {
        
        var sql002builder = IfaInfoNewRegisterSql002RequestModel.builder()//
                .t4nTitle(dtoReq.getTitle())//
                .t4nContent(dtoReq.getContent()) //
                .t4nFile1(dtoReq.getRegisterFile1())//
                .t4nFile2(dtoReq.getRegisterFile2())//
                .t4nFile3(dtoReq.getRegisterFile3())//
                .t4nFileCom1(dtoReq.getFileComment1()) //
                .t4nFileCom2(dtoReq.getFileComment2()) //)
                .t4nFileCom3(dtoReq.getFileComment3()) //
                .t4nUrl(dtoReq.getUrl())//
                .corUrlCom(dtoReq.getUrlComment())//
                .corReferenceCondition(dtoReq.getViewerSetting())//
                .corDisclaimer(dtoReq.getDisclaimer())//
                .corReadFlg(dtoReq.getReadManage())//
                .corCreateBy(userId) //
                .corUpdateBy(userId) //
        ;
        // 大カテゴリ
        // 必須フラグのついているカテゴリID
        var cat1 = dtoReq.getCategoryIdList().stream()//
                .filter(c -> StringUtils.equals("1", c.getRequiredFlag())) //
                .findFirst()//
                .map(c -> c.getCategoryId()) //
                .orElseThrow();
        sql002builder.t4nCat1(cat1);
        dao.insertIfaInfoNewRegisterSql002(sql002builder.build());
        
    }
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaInfoNewRegisterA008RequestDto
     * Dto レスポンス：IfaInfoNewRegisterA008ResponseDto
     * model リクエスト：IfaInfoNewRegisterA008RequestModel
     * model レスポンス：IfaInfoNewRegisterA008ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaInfoNewRegisterA001ResponseDto> initializeA001(IfaInfoNewRegisterA001RequestDto dtoReq)
            throws Exception {
        
        var categories = dao.selectIfaInfoNewRegisterSql001().getDataList().stream()
                .map(res -> new IfaInfoNewRegisterA001ResponseDto.NewNotificationCategory(res.getT9nInfoCat(),
                        res.getT9nName(), res.getT9nRequiredFlg()))
                .collect(Collectors.toList());
        categories.add(0, DEFAULT_CATEGORY);
        
        return IfaCommonUtil.createDataList(List.of(new IfaInfoNewRegisterA001ResponseDto(categories)),
                ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
    }
    
    /**
     * アクションID：A008a
     * アクション名：登録(ファイルアップロード)
     * Dto リクエスト：IfaInfoNewRegisterA008aRequestDto
     * Dto レスポンス：String
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<String> registerA008a(IfaInfoNewRegisterA008aRequestDto dtoReq) throws Exception {
        
        var ret = dao.selectIfaInfoNewRegisterSql008(new IfaInfoNewRegisterSql008RequestModel(FUNC_ID, CAT_ID)) //
                .getDataList().stream().findFirst()//
                .map(IfaInfoNewRegisterSql008ResponseModel::getFileDir).orElseThrow();
        return IfaCommonUtil.createDataList(List.of(ret), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
    }
    
}
