package com.sbisec.helios.gw.common.controller;

import java.util.Map;

import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.exception.IfaRuntimeException;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.common.util.CheckUtil;
import com.sbisec.helios.gw.common.util.FullWidthCharacterRedisEnvironment;
import com.sbisec.helios.gw.common.util.FullWidthCharacterRedisEnvironment.FullwidthCharacterRoles;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.gw.common.util.UnicodeCheckUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * チェックコントローラ
 *
 * @author SCSK
 *
 */
@RestController
@RequestMapping(value = "/common", method = { RequestMethod.POST })
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "COMMON", id = "IfaCheckUtil", ignoreCheck = true)
public class IfaCheckUtilController extends BaseController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaCheckUtilController.class);
    
    private static final String REDIS_KEY_CHECK_FULL_WIDTH_CHARACTER = "checkFullWidthCharacter";
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 全角文字チェックコントローラ
     *
     * @param params パラメータ
     * @return チェック結果
     * @throws Exception 任意の例外
     */
    @PostMapping("/ifaCheckUtilCheckFullWidthCharacter")
    public String checkFullWidthCharacter(@RequestBody Map<String, String> params) throws Exception {
        
        String screenId = params.get("screenId");
        String fieldId = params.get("fieldId");
        String value = params.get("value");
        
        String message;
        
        // キャッシュされたデータから適切なチェックルールを取得
        String jsonString = IfaGwCommonUtil.getDataFromRedis(IfaGwCommonUtil.ATTR_REDIS_GROUP_ENVIRONMENT, false,
                REDIS_KEY_CHECK_FULL_WIDTH_CHARACTER, String.class);
        if (com.sbibits.earth.util.StringUtil.isNullOrEmpty(jsonString)) {
            String[] exceptionMessage = { "全角文字チェック用の環境変数" };
            throw new IfaRuntimeException(ERRORS_TARGET_PICKUP_FAILURE, exceptionMessage);
        }
        FullWidthCharacterRedisEnvironment dataList = JsonConverter.getInstance().toObject(jsonString,
                FullWidthCharacterRedisEnvironment.class);
        
        FullwidthCharacterRoles checkRoles = dataList.getFullwidthCharacterRole(screenId, fieldId);
        if (checkRoles == null) {
            String[] exceptionMessage = { "全角文字チェック用の環境変数（キー：screenId=[" + screenId + "], fieldId=[" + fieldId + "]）" };
            throw new IfaRuntimeException(ERRORS_TARGET_PICKUP_FAILURE, exceptionMessage);
        }
        LOGGER.debug(checkRoles.toString());
        
        // SJIS無効文字存在チェック
        String utf8Str = UnicodeCheckUtil.getErrorUtf8Str(value);
        if (!utf8Str.isEmpty()) {
            // 無効文字がある場合、Redis環境変数のエラーコードに応じてメッセージを返却
            String utf8StrCheckMassageCode = checkRoles.getUtf8StrCheckMassageCode();
            if (ERRORS_SPECIAL_WORDS.equals(utf8StrCheckMassageCode)) {
                message = getMessage(ERRORS_SPECIAL_WORDS,
                        new String[] { checkRoles.getUtf8StrCheckPlaceholder1(), utf8Str });
                return jc.toString(
                        IfaCommonUtil.createDataList(null, ErrorLevel.WARNING, ERRORS_SPECIAL_WORDS, message));
            } else if (ERRORS_CONTEXT_DEPENDENT_WORDS.equals(utf8StrCheckMassageCode)) {
                message = getMessage(ERRORS_CONTEXT_DEPENDENT_WORDS, new String[] {
                        checkRoles.getUtf8StrCheckPlaceholder1(), checkRoles.getUtf8StrCheckPlaceholder2(), utf8Str });
                return jc.toString(IfaCommonUtil.createDataList(null, ErrorLevel.WARNING,
                        ERRORS_CONTEXT_DEPENDENT_WORDS, message));
            } // メッセージコードが未定義の場合は無効文字チェック対象外
        }
        
        // EUC_JP変換後のバイト数でのレングスチェック
        int maxSize;
        String lengthCheckMassageCode = checkRoles.getLengthCheckMessageCode();
        if (ERRORS_STRING_BYTES.equals(lengthCheckMassageCode)) {
            maxSize = Integer.valueOf(checkRoles.getLength1()) * 2;
            if (!CheckUtil.checkStringBytes(value, maxSize)) {
                // レングス超過の場合、Redis環境変数のエラーコードに応じてメッセージを返却
                message = getMessage(ERRORS_STRING_BYTES,
                        new String[] { checkRoles.getLengthCheckPlaceholder(), checkRoles.getLength1() });
                return jc
                        .toString(IfaCommonUtil.createDataList(null, ErrorLevel.WARNING, ERRORS_STRING_BYTES, message));
            }
        } else if (ERRORS_SIZE.equals(lengthCheckMassageCode)) {
            maxSize = Integer.valueOf(checkRoles.getLength2()) * 2;
            if (!CheckUtil.checkStringBytes(value, maxSize)) {
                // レングス超過の場合、Redis環境変数のエラーコードに応じてメッセージを返却
                message = getMessage(ERRORS_SIZE, new String[] { checkRoles.getLengthCheckPlaceholder(),
                        checkRoles.getLength1(), checkRoles.getLength2() });
                return jc.toString(IfaCommonUtil.createDataList(null, ErrorLevel.WARNING, ERRORS_SIZE, message));
            }
        } // メッセージコードが未定義の場合はレングスチェック対象外
        
        // チェックOKの場合、空メッセージを返却
        return jc.toString(IfaCommonUtil.createDataList(null, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), ""));
    }
    
}
