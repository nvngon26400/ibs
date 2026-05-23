package com.sbisec.helios.gw.common.logger;

import com.sbisec.helios.ap.common.enums.LogKeyEnum;
import com.sbisec.helios.ap.common.enums.LogTypeEnum;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * Log file filter.
 * <p>
 * Only print the log of sensitive information.
 * </p>
 * 
 * @Organization SBIBITS DaLian CB Group
 */
public class LogFileSecurityFilter extends Filter<ILoggingEvent> {

    @Override
    public FilterReply decide(ILoggingEvent event) {

        String kind = MDC.get(LogKeyEnum.kind.getKey());

        if (StringUtils.isBlank(kind)) {
            return FilterReply.DENY;
        }

        if (kind.equals(LogTypeEnum.Security.getType())) {
            return FilterReply.ACCEPT;
        } else {
            // exclude others
            return FilterReply.DENY;
        }
    }

}
