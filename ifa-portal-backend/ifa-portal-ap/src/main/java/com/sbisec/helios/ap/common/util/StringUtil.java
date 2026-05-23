package com.sbisec.helios.ap.common.util;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbisec.helios.ap.common.constants.AppConstants;
import com.sbisec.helios.ap.common.exception.SystemErrorException;

/**
 * Utility class for strings.
 */
public class StringUtil {

    /**
     * Get the corresponding value in json by keyword.
     * 
     * @param body json body
     * @param key  json keyword
     * @return
     */
    public static String jsonValueByKey(String body, String key) {
        try {
            JsonNode jn = new ObjectMapper().readTree(body).get(key);
            if (jn == null) {
                return StringUtils.EMPTY;
            }
            return jn.textValue();
        } catch (Exception e) {
            throw new SystemErrorException(e);
        }
    }

    /**
     * Build the mail subject according to envId.<br>
     * <p>
     * If it is not a production environment, the subject of the email needs to be marked with an
     * environmental mark.
     * </p>
     * 
     * @param subject the mail subject.
     * @param envId   Environmental id (DEV, UAT, PROD)
     * @param mark    Environmental mark
     * @return
     */
    public static String bulidMailSubject(String subject, String envId, String mark) {
        if (StringUtils.isBlank(envId) || StringUtils.compareIgnoreCase(envId, AppConstants.ENV_ID_PROD) == 0) {
            return subject;
        } else {
            String envStr = StringUtils.isBlank(mark) ? envId : mark;
            return new StringBuffer("[").append(envStr).append("]").append(subject).toString();
        }
    }
}
