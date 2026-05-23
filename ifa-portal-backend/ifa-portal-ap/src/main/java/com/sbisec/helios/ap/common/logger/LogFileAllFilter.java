package com.sbisec.helios.ap.common.logger;

import com.sbisec.helios.ap.common.enums.LogKeyEnum;
import com.sbisec.helios.ap.common.enums.LogTypeEnum;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * Log file filter, print all logs.
 * <p>
 * Excluding internal interface access and sensitive information logs.
 * </p>
 * 
 * @Organization SBIBITS DaLian CB Group
 */
public class LogFileAllFilter extends Filter<ILoggingEvent> {

    @Override
    public FilterReply decide(ILoggingEvent event) {

        String kind = MDC.get(LogKeyEnum.kind.getKey());
        if (StringUtils.isBlank(kind)) {
            return FilterReply.ACCEPT;
        }

        if (kind.equals(LogTypeEnum.API.getType()) || kind.equals(LogTypeEnum.Security.getType())) {
            // Exclude internal interfaces and sensitive information.
            return FilterReply.DENY;
        } else {
            // The rest of the log content will be output.
            return FilterReply.ACCEPT;
        }
    }

}
