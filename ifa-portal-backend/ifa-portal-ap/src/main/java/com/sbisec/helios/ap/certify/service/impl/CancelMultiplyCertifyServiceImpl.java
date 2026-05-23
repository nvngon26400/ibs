package com.sbisec.helios.ap.certify.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sbibits.earth.util.mail.MailProperties;
import com.sbibits.earth.util.mail.SendMail;
import com.sbisec.helios.ap.certify.service.CancelMultiplyCertifyService;
import com.sbisec.helios.ap.common.dao.MedVerifyCodeDao;
import com.sbisec.helios.ap.common.dao.MedVerifyUsersDao;
import com.sbisec.helios.ap.common.model.MCode;
import com.sbisec.helios.ap.common.model.MedVerifyCode;
import com.sbisec.helios.ap.common.model.MedVerifyUser;
import com.sbisec.helios.ap.common.service.MCodeService;
import com.sbisec.helios.ap.common.util.RandCodeCreateUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

/**
 * 認証サービス
 *
 * @author SCSK
 *
 */
@Component(value = "cancelMultiplyCertifyService")
public class CancelMultiplyCertifyServiceImpl implements CancelMultiplyCertifyService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CancelMultiplyCertifyServiceImpl.class);

    private static final String CODE_TYPE_VERIFY_CODE = "37";
    private static final String CODE_1_SEND_MAIL = "1";
    private static final String CODE_2_FROM = "FROM";
    private static final String CODE_2_MAIL_TITLE = "IFAポータルシステムワンタイム認証コードのご案内（XXXX）";
    private static final String CODE_2_MAIL_TEXT = 
            "〇〇〇〇様\n"
            + "いつも大変お世話になっております。\n"
            + "以下認証コードを10分以内に認証画面から入力ください。\n"
            + "\n"
            + "　XXXX\n"
            + "\n"
            + "株式会社SBI証券";

    @Autowired
    protected MedVerifyUsersDao medVerifyUsersDao;
    
    @Autowired
    protected MedVerifyCodeDao medVerifyCodeDao;

    @Autowired
    protected MCodeService mCodeService;

    /**
     * 認証ユーザ情報取得メソッド。
     *
     * @param userId ユーザID
     * @return 認証ユーザ情報
     * @throws Exception 例外
     * @see com.sbisec.helios.ap.certify.service.CancelMultiplyCertifyService#getVerifyUserInfo(java.lang.String)
     */
    @Override
    public MedVerifyUser getVerifyUserInfo(String userId) throws Exception {
        
        LOGGER.debug("CancelMultiplyCertifyServiceImpl.getVerifyUserInfo:[{}]", userId);
        
        MedVerifyUser medVerifyUsers = medVerifyUsersDao.getMedVerifyUserInfo(userId);
        
        return medVerifyUsers;
    }

    /**
     * 認証コード送信メソッド。
     *
     * @param userId ユーザID
     * @param userName ユーザ名
     * @param mailAddress メールアドレス
     * @return 結果
     * @throws Exception 例外
     * @see com.sbisec.helios.ap.certify.service.CancelMultiplyCertifyService#sendVerifyCode(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public boolean sendVerifyCode(String userId, String userName, String mailAddress) throws Exception {
        
        LOGGER.debug("CancelMultiplyCertifyServiceImpl.sendVerifyCode:[{}, {}]", userId, mailAddress);
        
        //Create verify code
        String verifyCode = RandCodeCreateUtil.createCode(4);
        
        //Insert verify code into Db
        MedVerifyCode medVerifyCode = new MedVerifyCode();
        medVerifyCode.setUserId(userId);
        medVerifyCode.setVerifyCode(verifyCode);
        updateMedVerifyCode(medVerifyCode);
        //Send verify code mail
        return sendMail(mailAddress, verifyCode, userName);
    }
    
    /**
     * 認証コード情報を更新する
     *
     * @param medVerifyCode 認証コード情報
     * @return 更新結果件数
     * @throws Exception 例外
     */
    @Transactional(rollbackFor = Throwable.class)
    private Integer updateMedVerifyCode(MedVerifyCode medVerifyCode) throws Exception {
        
        LOGGER.debug("CancelMultiplyCertifyServiceImpl.updateMedVerifyCode:[{}]", medVerifyCode);
        
        //Delete verify code info
        medVerifyCodeDao.deleteMedVerifyCode(medVerifyCode);
        //Insert verify code info       
        return medVerifyCodeDao.insertMedVerifyCode(medVerifyCode);
    }
    
    /**
     * 認証コードをメールで送信する
     *
     * @param mailAddesse メールアドレス
     * @param verifyCodeTxt 認証コード
     * @param userName ユーザ名
     * @return 送信結果
     * @throws Exception 例外
     */
    private boolean sendMail(String mailAddesse, String verifyCodeTxt, String userName) throws Exception {
        
        String from = "";
        
        // M_CODEを取得する
        List<MCode> mCodeList = mCodeService.getMCodeList(CODE_TYPE_VERIFY_CODE, CODE_1_SEND_MAIL);
        if (mCodeList != null && !mCodeList.isEmpty()) {
            
            for (MCode mCode : mCodeList) {
                if (CODE_2_FROM.equals(mCode.getCode2())) {
                    from = mCode.getName();
                }
            }
        }
        
        if (ObjectUtils.isEmpty(from)) {
            return false;
        }
        String todaysDate = new SimpleDateFormat("MM月dd日").format(new Date());
        
        String mailTitle = CODE_2_MAIL_TITLE;
        String mailContent = CODE_2_MAIL_TEXT;
        
        MailProperties mailProperties = new MailProperties();
        mailProperties.setFrom(from);
        mailTitle = mailTitle.replace("XXXX", todaysDate);
        mailProperties.setSubject(mailTitle);
        List<String> addressTo = new ArrayList<String>();
        addressTo.add(mailAddesse);
        
        mailProperties.setTo((String[]) addressTo.toArray(new String[addressTo.size()]));
        mailContent = mailContent.replace("〇〇〇〇", userName).replace("XXXX", verifyCodeTxt);
        
        mailProperties.setText(mailContent);
        try {
            return SendMail.sendMail(mailProperties);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * メールアドレス更新メソッド。
     *
     * @param userId ユーザID
     * @param mailAddress メールアドレス
     * @return 更新結果件数
     * @throws Exception 例外
     * @see com.sbisec.helios.ap.certify.service.CancelMultiplyCertifyService#updateMedMailAddress(java.lang.String, java.lang.String)
     */
    @Transactional(rollbackFor = Throwable.class)
    @Override
    public Integer updateMedMailAddress(String userId, String mailAddress) throws Exception {
        
        LOGGER.debug("CancelMultiplyCertifyServiceImpl.updateMedMailAddress:[{}, {}]", userId, mailAddress);
        
        MedVerifyUser medVerifyUser = new MedVerifyUser();
        medVerifyUser.setUserId(userId);
        medVerifyUser.setUpdateBy(userId);
        medVerifyUser.setMailAddress(mailAddress);
        return medVerifyUsersDao.updateMedVerifyMailAddress(medVerifyUser);
    }
    
    /**
     * 認証コード情報取得メソッド。
     *
     * @param userId ユーザID
     * @return 認証コード情報
     * @throws Exception 例外
     * @see com.sbisec.helios.ap.certify.service.CancelMultiplyCertifyService#getVerifyCodeInfo(java.lang.String)
     */
    @Override
    public MedVerifyCode getVerifyCodeInfo(String userId) throws Exception {
        
        LOGGER.debug("CancelMultiplyCertifyServiceImpl.getVerifyCodeInfo:[{}]", userId);
        
        MedVerifyCode medVerifyCode = medVerifyCodeDao.getMedVerifyCode(userId);
        
        return medVerifyCode;
    }

}
