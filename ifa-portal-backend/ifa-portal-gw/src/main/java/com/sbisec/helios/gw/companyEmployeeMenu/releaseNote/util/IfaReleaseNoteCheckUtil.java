package com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.form.IfaContentCheckItem;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.exception.IfaRuntimeException;
import com.sbisec.helios.gw.common.util.CheckUtil;
import com.sbisec.helios.gw.common.util.FullWidthCharacterRedisEnvironment;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.gw.common.util.UnicodeCheckUtil;
import com.sbisec.helios.gw.common.util.FullWidthCharacterRedisEnvironment.FullwidthCharacterRoles;
import com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.form.ContentItem;

/**
 * SUB0512 リリースノート
 *
 * @author SBI大連 夏
 * @date 2025/11/07
 */

@Component
public class IfaReleaseNoteCheckUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaReleaseNoteCheckUtil.class);

    private static final String REDIS_KEY_CHECK_FULL_WIDTH_CHARACTER = "checkFullWidthCharacter";

    private static final String ERRORS_TARGET_PICKUP_FAILURE = "errors.targetPickUpFailure";

    private static final String ERRORS_SPECIAL_WORDS = "errors.specialWords";

    private static final String ERRORS_CONTEXT_DEPENDENT_WORDS = "errors.contextDependentWords";

    private static final String ERRORS_STRING_BYTES = "errors.stringBytes";

    private static final String ERRORS_SIZE = "errors.size";

    private static final String MENUNAME = "menuName";

    private static final String SCREENNAME = "screenName";

    private static final String CONTENT = "content";

    /**
     * MessageSource(= messages.properties)
     */
    @Autowired
    protected MessageSource messageSource;

    public List<IfaContentCheckItem> checkForSpecialCharacters(String screenId, List<ContentItem> contentItemList)
        throws Exception {

        List<IfaContentCheckItem> contentCheckItemList = new ArrayList<IfaContentCheckItem>();

        // キャッシュされたデータから適切なチェックルールを取得
        String jsonString = IfaGwCommonUtil.getDataFromRedis(IfaGwCommonUtil.ATTR_REDIS_GROUP_ENVIRONMENT, false,
            REDIS_KEY_CHECK_FULL_WIDTH_CHARACTER, String.class);
        if (com.sbibits.earth.util.StringUtil.isNullOrEmpty(jsonString)) {
            String[] exceptionMessage = { "全角文字チェック用の環境変数" };
            throw new IfaRuntimeException(ERRORS_TARGET_PICKUP_FAILURE, exceptionMessage);
        }
        FullWidthCharacterRedisEnvironment dataList =
            JsonConverter.getInstance().toObject(jsonString, FullWidthCharacterRedisEnvironment.class);
        FullwidthCharacterRoles menuNameCheckRoles = dataList.getFullwidthCharacterRole(screenId, MENUNAME);
        if (menuNameCheckRoles == null) {
            String[] exceptionMessage =
                { "全角文字チェック用の環境変数（キー：screenId=[" + screenId + "], fieldId=[" + MENUNAME + "]）" };
            throw new IfaRuntimeException(ERRORS_TARGET_PICKUP_FAILURE, exceptionMessage);
        }
        LOGGER.debug(menuNameCheckRoles.toString());
        FullwidthCharacterRoles screenNameCheckRoles = dataList.getFullwidthCharacterRole(screenId, SCREENNAME);
        if (screenNameCheckRoles == null) {
            String[] exceptionMessage =
                { "全角文字チェック用の環境変数（キー：screenId=[" + screenId + "], fieldId=[" + SCREENNAME + "]）" };
            throw new IfaRuntimeException(ERRORS_TARGET_PICKUP_FAILURE, exceptionMessage);
        }
        LOGGER.debug(screenNameCheckRoles.toString());
        FullwidthCharacterRoles contentCheckRoles = dataList.getFullwidthCharacterRole(screenId, CONTENT);
        if (contentCheckRoles == null) {
            String[] exceptionMessage = { "全角文字チェック用の環境変数（キー：screenId=[" + screenId + "], fieldId=[" + CONTENT + "]）" };
            throw new IfaRuntimeException(ERRORS_TARGET_PICKUP_FAILURE, exceptionMessage);
        }
        LOGGER.debug(contentCheckRoles.toString());

        for (int i = 0; i < contentItemList.size(); i++) {

            ContentItem contentItem = contentItemList.get(i);

            IfaContentCheckItem contentCheckItem = new IfaContentCheckItem();

            contentCheckItem.setReleaseNoteContentNo(contentItem.getReleaseNoteContentNo());
            contentCheckItem.setMenuNameErrorMessage(getCheckMessage(menuNameCheckRoles, contentItem.getMenuName()));
            contentCheckItem
                .setScreenNameErrorMessage(getCheckMessage(screenNameCheckRoles, contentItem.getScreenName()));
            contentCheckItem.setContentErrorMessage(getCheckMessage(contentCheckRoles, contentItem.getContent()));

            contentCheckItemList.add(contentCheckItem);
        }

        return contentCheckItemList;

    }

    protected String getCheckMessage(FullwidthCharacterRoles checkRoles, String value) throws Exception {

        if (StringUtils.isEmpty(value)) {
            return null;
        }

        String message;

        // SJIS無効文字存在チェック
        String utf8Str = UnicodeCheckUtil.getErrorUtf8Str(value);
        if (!utf8Str.isEmpty()) {
            // 無効文字がある場合、Redis環境変数のエラーコードに応じてメッセージを返却
            String utf8StrCheckMassageCode = checkRoles.getUtf8StrCheckMassageCode();
            if (ERRORS_SPECIAL_WORDS.equals(utf8StrCheckMassageCode)) {
                message = getMessage(ERRORS_SPECIAL_WORDS,
                    new String[] { checkRoles.getUtf8StrCheckPlaceholder1(), utf8Str });
                return message;
            } else if (ERRORS_CONTEXT_DEPENDENT_WORDS.equals(utf8StrCheckMassageCode)) {
                message = getMessage(ERRORS_CONTEXT_DEPENDENT_WORDS, new String[] {
                    checkRoles.getUtf8StrCheckPlaceholder1(), checkRoles.getUtf8StrCheckPlaceholder2(), utf8Str });
                return message;
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
                return message;
            }
        } else if (ERRORS_SIZE.equals(lengthCheckMassageCode)) {
            maxSize = Integer.valueOf(checkRoles.getLength2()) * 2;
            if (!CheckUtil.checkStringBytes(value, maxSize)) {
                // レングス超過の場合、Redis環境変数のエラーコードに応じてメッセージを返却
                message = getMessage(ERRORS_SIZE, new String[] { checkRoles.getLengthCheckPlaceholder(),
                    checkRoles.getLength1(), checkRoles.getLength2() });
                return message;
            }
        } // メッセージコードが未定義の場合はレングスチェック対象外

        // チェックOKの場合、空メッセージを返却
        return null;
    }

    /**
     * Get message from messages.properties.
     *
     * @param messageKey
     * @param values
     * @return String
     */
    protected String getMessage(String messageKey, String[] values) {

        return messageSource.getMessage(messageKey, values, Locale.JAPANESE);
    }

}
