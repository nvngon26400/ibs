package com.sbisec.helios.ap.common.service.impl;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.dto.LinkNewMainSiteParamRequestDto;
import com.sbisec.helios.ap.common.dto.LinkNewMainSiteParamResponseDto;
import com.sbisec.helios.ap.common.dto.NewMainSiteParamResponseDto;
import com.sbisec.helios.ap.common.enums.ExternalLinkTargetPath;
import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.LinkNewMainSiteParamService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component(value = "linkNewMainSiteParamService")
public class LinkNewMainSiteParamServiceImpL implements LinkNewMainSiteParamService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinkNewMainSiteParamServiceImpL.class);

    /**
     * 新メインサイト用パラメータ生成サービス
     *
     * @param dtoReq {@code LinkNewMainSiteParamRequestDto }
     * @return {@code LinkNewMainSiteParamResponseDto} 新メインサイト用パラメータ
     * @throws Exception システムエラーやデータアクセスエラーなど、処理中に発生した例外
     */
    @Override
    public LinkNewMainSiteParamResponseDto getNewMainSiteParam(LinkNewMainSiteParamRequestDto dtoReq) throws Exception {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("LinkNewMainSiteParamServiceImpL.getNewMainSiteParam");
        }
        LinkNewMainSiteParamResponseDto res = new LinkNewMainSiteParamResponseDto();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        // 顧客情報の取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        String butenCode = customerCommon.getButenCode();
        String accountNumber = customerCommon.getAccountNumber();

        // ②CCSログインIDとして設定する文字列の生成 （ユーザID用設定値）
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String privId = userAccount.getPrivId();
        String ccsOpId = userAccount.getUserId();
        if (StringUtils.equals(privId, PrivId.HEAD_OFFICE.getId()) || StringUtils.equals(privId, PrivId.BRANCH.getId())) {
            ccsOpId = ccsOpId.length() >= 7 ? ccsOpId.substring(ccsOpId.length()-7) + "@" : ccsOpId + "@";
        } else {
            ccsOpId = ccsOpId.length() >= 8 ? ccsOpId.substring(ccsOpId.length()-8) : ccsOpId;
        }
        // 共通鍵の値を取得
        String commonKey = IfaCommonUtil.getYmlDefinition("responsivesite.commonKey");
        // 初期化ベクトル取得
        String iv = RandomStringUtils.randomAlphanumeric(16);
        // 新メインサイト用のList
        List<NewMainSiteParamResponseDto> list = new ArrayList<NewMainSiteParamResponseDto>();
        // ③新メインサイト用のパラメータを生成する
        NewMainSiteParamResponseDto newMainSiteParamResponseDto = new NewMainSiteParamResponseDto();
        // ①param パラメーター
        String plainText = new StringBuilder(butenCode).append("-").append(StringUtils.leftPad(accountNumber, 7, "0")).append("_").append(ccsOpId)
                .append("_").append(ExternalLinkTargetPath.getAuthorizedPath(dtoReq.getUrlId())).toString();
        // 「暗号化+Base64URLEncode」実施する
        String parm = encrypt(plainText, iv, commonKey);
        // ②path パス
        String path = URLEncoder.encode(ExternalLinkTargetPath.valueOfId(dtoReq.getUrlId()).getFormattedPath(dtoReq.getBrandCode() != null ? dtoReq.getBrandCode() : null), "UTF-8");
        // ③timestamp システム日時を取得
        String timestamp = dateFormat.format(date);
        // ④initializationVector 初期化ベクトル
        String initializationVector = toHex(iv.getBytes());
        // ⑤hash ハッシュ
        String hash = toHash(new StringBuffer(ccsOpId).append(timestamp).toString());
        // ⑥intMedKBN 仲介業区分
        String intMedKBN = "2";
        // ⑦mediationCode 仲介業者コード
        String mediationCode = StringUtil.isNullOrEmpty(customerCommon.getBrokerCode())
                ? "0000"
                : customerCommon.getBrokerCode();
        // パラメータ値を設定
        newMainSiteParamResponseDto.setParm(parm);
        newMainSiteParamResponseDto.setPath(path);
        newMainSiteParamResponseDto.setTimestamp(timestamp);
        newMainSiteParamResponseDto.setInitializationVector(initializationVector);
        newMainSiteParamResponseDto.setHash(hash);
        newMainSiteParamResponseDto.setIntMedKBN(intMedKBN);
        newMainSiteParamResponseDto.setMediationCode(mediationCode);

        list.add(newMainSiteParamResponseDto);
        // 新メインサイトの設定
        res.setNewMainSiteParamList(list);
        return res;
    }

    public static String encrypt(final String plainText, final String iv, final String publicKey)
            throws Exception {
        if (plainText == null || iv == null) {
            return null;
        }
        final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(publicKey.getBytes("UTF-8"), "AES"),
                new IvParameterSpec(iv.getBytes("UTF-8")));
        final byte[] encryptedText = cipher.doFinal(plainText.getBytes("UTF-8"));
        return Base64.encodeBase64URLSafeString(encryptedText);
    }

    public static String toHash(final String plainText) {
        try {
            final MessageDigest md = MessageDigest.getInstance("SHA-256");
            final byte[] hash = md.digest(plainText.getBytes());
            return toHex(hash);
        } catch (final Exception e) {
        }
        return "";
    }

    public static String toHex(final byte[] bytes) {
        final StringBuilder sb = new StringBuilder();
        for (final byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
}
